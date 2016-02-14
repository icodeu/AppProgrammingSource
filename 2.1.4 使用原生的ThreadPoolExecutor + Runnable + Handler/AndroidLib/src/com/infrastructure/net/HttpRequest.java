package com.infrastructure.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.infrastructure.utils.BaseUtils;

public class HttpRequest implements Runnable {
	private HttpUriRequest request = null;
	private URLData urlData = null;
	private RequestCallback requestCallback = null;
	private List<RequestParameter> parameter = null;
	private String url = null;
	private HttpResponse response = null;
	private DefaultHttpClient httpClient;

	protected Handler handler;

	public HttpRequest(final URLData data, final List<RequestParameter> params,
			final RequestCallback callBack) {
		urlData = data;

		url = urlData.getUrl();
		this.parameter = params;
		requestCallback = callBack;

		if (httpClient == null) {
			httpClient = new DefaultHttpClient();
		}

		handler = new Handler();
	}

	/**
	 * 获取HttpUriRequest请求
	 * 
	 * @return
	 */
	public HttpUriRequest getRequest() {
		return request;
	}

	@Override
	public void run() {
		try {
			if (urlData.getNetType().equals("get")) {
				// 添加参数
				final StringBuffer paramBuffer = new StringBuffer();
				if ((parameter != null) && (parameter.size() > 0)) {
					for (final RequestParameter p : parameter) {
						if (paramBuffer.length() == 0) {
							paramBuffer.append(p.getName() + "="
									+ BaseUtils.UrlEncodeUnicode(p.getValue()));
						} else {
							paramBuffer.append("&" + p.getName() + "="
									+ BaseUtils.UrlEncodeUnicode(p.getValue()));
						}
					}

					String newUrl = url + "?" + paramBuffer.toString();
					request = new HttpGet(newUrl);
				} else {
					request = new HttpGet(url);
				}
			} else if (urlData.getNetType().equals("post")) {
				request = new HttpPost(url);
				// 添加参数
				if ((parameter != null) && (parameter.size() > 0)) {
					final List<BasicNameValuePair> list = new ArrayList<BasicNameValuePair>();
					for (final RequestParameter p : parameter) {
						list.add(new BasicNameValuePair(p.getName(), p
								.getValue()));
					}

					((HttpPost) request).setEntity(new UrlEncodedFormEntity(
							list, HTTP.UTF_8));
				}
			} else {
				return;
			}

			request.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 30000);
			request.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,
					30000);

			// 发送请求
			response = httpClient.execute(request);
			// 获取状态
			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				final ByteArrayOutputStream content = new ByteArrayOutputStream();

				response.getEntity().writeTo(content);
				String strResponse = new String(content.toByteArray()).trim();
				strResponse = "{'isError':false,'errorType':0,'errorMessage':'','result':{'city':'北京','cityid':'101010100','temp':'17','WD':'西南风','WS':'2级','SD':'54%','WSE':'2','time':'23:15','isRadar':'1','Radar':'JC_RADAR_AZ9010_JB','njd':'暂无实况','qy':'1016'}}";

				// 设置回调函数
				if ((requestCallback != null)) {
					final Response responseInJson = JSON.parseObject(
							strResponse, Response.class);
					if (responseInJson.hasError()) {
						handleNetworkError(responseInJson.getErrorMessage());
					} else {
						handler.post(new Runnable() {

							@Override
							public void run() {
								HttpRequest.this.requestCallback
										.onSuccess(responseInJson.getResult());
							}

						});
					}
				} else {
					handleNetworkError("网络异常");
				}
			} else {
				handleNetworkError("网络异常");
			}
		} catch (final java.lang.IllegalArgumentException e) {
			handleNetworkError("网络异常");
		} catch (final UnsupportedEncodingException e) {
			handleNetworkError("网络异常");
		} catch (final IOException e) {
			handleNetworkError("网络异常");
		}
	}

	public void handleNetworkError(final String errorMsg) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				HttpRequest.this.requestCallback.onFail(errorMsg);
			}
		});
	}

	public static String inputStreamToString(final InputStream is)
			throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}
}
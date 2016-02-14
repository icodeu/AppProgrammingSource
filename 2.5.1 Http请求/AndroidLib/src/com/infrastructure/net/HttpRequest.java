package com.infrastructure.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;

import android.os.Handler;

import com.alibaba.fastjson.JSON;
import com.infrastructure.cache.CacheManager;
import com.infrastructure.utils.BaseUtils;
import com.infrastructure.utils.FrameConstants;

public class HttpRequest implements Runnable {
	private final static String cookiePath = "/data/data/com.youngheart/cache/cookie";
	// 区分get还是post的枚举
	public static final String REQUEST_GET = "get";
	public static final String REQUEST_POST = "post";

	private HttpUriRequest request = null;
	private URLData urlData = null;
	private RequestCallback requestCallback = null;
	private List<RequestParameter> parameter = null;
	private String url = null; // 原始url
	private String newUrl = null; // 拼接key-value后的url
	private HttpResponse response = null;
	private DefaultHttpClient httpClient;

	// 切换回UI线程
	protected Handler handler;

	protected boolean cacheRequestData = true;

	//头信息
	HashMap<String, String> headers;

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
		
		headers = new HashMap<String, String>();
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
			if (urlData.getNetType().equals(REQUEST_GET)) {
				// 添加参数
				final StringBuffer paramBuffer = new StringBuffer();
				if ((parameter != null) && (parameter.size() > 0)) {

					// 这里要对key进行排序
					sortKeys();

					for (final RequestParameter p : parameter) {
						if (paramBuffer.length() == 0) {
							paramBuffer.append(p.getName() + "="
									+ BaseUtils.UrlEncodeUnicode(p.getValue()));
						} else {
							paramBuffer.append("&" + p.getName() + "="
									+ BaseUtils.UrlEncodeUnicode(p.getValue()));
						}
					}

					newUrl = url + "?" + paramBuffer.toString();
				} else {
					newUrl = url;
				}

				// 如果这个get的API有缓存时间（大于0）
				if (urlData.getExpires() > 0) {
					final String content = CacheManager.getInstance()
							.getFileCache(newUrl);
					if (content != null) {
						handler.post(new Runnable() {

							@Override
							public void run() {
								requestCallback.onSuccess(content);
							}

						});

						return;
					}
				}

				request = new HttpGet(newUrl);

			} else if (urlData.getNetType().equals(REQUEST_POST)) {
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

			
            /**
             * 添加必要的头信息
             */
            setHttpHeaders(request);
			
			// 添加Cookie到请求头中
			addCookie();

			// 发送请求
			response = httpClient.execute(request);
			// 获取状态
			final int statusCode = response.getStatusLine().getStatusCode();
			// 设置回调函数，但如果requestCallback，说明不需要回调，不需要知道返回结果
			if ((requestCallback != null)) {
				if (statusCode == HttpStatus.SC_OK) {
					final ByteArrayOutputStream content = new ByteArrayOutputStream();

					String strResponse = "";			
					response.getEntity().writeTo(content);
					strResponse = new String(content.toByteArray()).trim();
					strResponse = "{'isError':false,'errorType':0,'errorMessage':'','result':{'city':'北京','cityid':'101010100','temp':'17','WD':'西南风','WS':'2级','SD':'54%','WSE':'2','time':'23:15','isRadar':'1','Radar':'JC_RADAR_AZ9010_JB','njd':'暂无实况','qy':'1016'}}";

					final Response responseInJson = JSON.parseObject(
							strResponse, Response.class);
					if (responseInJson.hasError()) {
						if(responseInJson.getErrorType() == 1) {
							handler.post(new Runnable() {
								@Override
								public void run() {
									requestCallback.onCookieExpired();
								}
							});
						} else {
							handleNetworkError(responseInJson.getErrorMessage());
						}						
					} else {
						// 把成功获取到的数据记录到缓存
						if (urlData.getNetType().equals(REQUEST_GET)
								&& urlData.getExpires() > 0) {
							CacheManager.getInstance().putFileCache(newUrl,
									responseInJson.getResult(),
									urlData.getExpires());
						}

						handler.post(new Runnable() {

							@Override
							public void run() {
								requestCallback.onSuccess(responseInJson
										.getResult());
							}

						});

						// 保存Cookie
						saveCookie();
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
		if (requestCallback != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					requestCallback.onFail(errorMsg);
				}
			});
		}
	}

	static String inputStreamToString(final InputStream is) throws IOException {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int i = -1;
		while ((i = is.read()) != -1) {
			baos.write(i);
		}
		return baos.toString();
	}

	void sortKeys() {
		for (int i = 1; i < parameter.size(); i++) {
			for (int j = i; j > 0; j--) {
				RequestParameter p1 = parameter.get(j - 1);
				RequestParameter p2 = parameter.get(j);
				if (compare(p1.getName(), p2.getName())) {
					// 交互p1和p2这两个对象，写的超级恶心
					String name = p1.getName();
					String value = p1.getValue();

					p1.setName(p2.getName());
					p1.setValue(p2.getValue());

					p2.setName(name);
					p2.setValue(value);
				}
			}
		}
	}

	// 返回true说明str1大，返回false说明str2大
	boolean compare(String str1, String str2) {
		String uppStr1 = str1.toUpperCase();
		String uppStr2 = str2.toUpperCase();

		boolean str1IsLonger = true;
		int minLen = 0;

		if (str1.length() < str2.length()) {
			minLen = str1.length();
			str1IsLonger = false;
		} else {
			minLen = str2.length();
			str1IsLonger = true;
		}

		for (int index = 0; index < minLen; index++) {
			char ch1 = uppStr1.charAt(index);
			char ch2 = uppStr2.charAt(index);
			if (ch1 != ch2) {
				if (ch1 > ch2) {
					return true; // str1大
				} else {
					return false; // str2大
				}
			}
		}

		return str1IsLonger;
	}

	/**
	 * cookie列表保存到本地
	 * 
	 * @return
	 */
	public synchronized void saveCookie() {
		// 获取本次访问的cookie
		final List<Cookie> cookies = httpClient.getCookieStore().getCookies();
		// 将普通cookie转换为可序列化的cookie
		List<SerializableCookie> serializableCookies = null;

		if ((cookies != null) && (cookies.size() > 0)) {
			serializableCookies = new ArrayList<SerializableCookie>();

			for (final Cookie c : cookies) {
				serializableCookies.add(new SerializableCookie(c));
			}
		}

		BaseUtils.saveObject(cookiePath, serializableCookies);
	}

	/**
	 * 从本地获取cookie列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void addCookie() {
		List<SerializableCookie> cookieList = null;
		Object cookieObj = BaseUtils.restoreObject(cookiePath);
		if (cookieObj != null) {
			cookieList = (ArrayList<SerializableCookie>) cookieObj;
		}

		if ((cookieList != null) && (cookieList.size() > 0)) {
			final BasicCookieStore cs = new BasicCookieStore();
			cs.addCookies(cookieList.toArray(new Cookie[] {}));
			httpClient.setCookieStore(cs);
		} else {
			httpClient.setCookieStore(null);
		}
	}
	
    void setHttpHeaders(final HttpUriRequest httpMessage)
    {
        headers.clear();
        headers.put(FrameConstants.ACCEPT_CHARSET, "UTF-8,*");
        headers.put(FrameConstants.USER_AGENT, "Young Heart Android App ");

        if ((httpMessage != null) && (headers != null))
        {
            for (final Entry<String, String> entry : headers.entrySet())
            {
                if (entry.getKey()!=null)
                {
                    httpMessage.addHeader(entry.getKey(), entry.getValue());
                }
            }
        }
    }
}
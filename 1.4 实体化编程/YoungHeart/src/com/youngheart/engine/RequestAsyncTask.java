package com.youngheart.engine;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.os.AsyncTask;

public abstract class RequestAsyncTask extends AsyncTask<String, Void, Response> {
	public abstract void onSuccess(String content);

	public abstract void onFail(String errorMessage);

	@Override
	protected void onPreExecute() {
	}

	@Override
	protected Response doInBackground(String... url) {
		return getResponseFromURL(url[0]);
	}

	@Override
	protected void onPostExecute(Response response) {
		if(response.hasError()) {
			onFail(response.getErrorMessage());
		} else {
			onSuccess(response.getResult());
		}
	}

	private Response getResponseFromURL(String url) {
		Response response = new Response(); 
		
		HttpGet get = new HttpGet(url);

		String strResponse = null;
		try {
			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 8000);
			HttpClient httpClient = new DefaultHttpClient(httpParameters);

			HttpResponse httpResponse = httpClient.execute(get);
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				strResponse = EntityUtils.toString(httpResponse.getEntity());
			}
		} catch (Exception e) {
			response.setError(true);
			response.setErrorMessage(e.getMessage());
		}

		if (strResponse == null) {
			response.setError(true);
			response.setErrorMessage("网络异常");
		} else {
			response.setError(false);
			response.setResult(strResponse);				
		}

		return response;
	}
}

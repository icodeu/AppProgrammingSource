package com.youngheart.activity.weather;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.youngheart.R;
import com.youngheart.base.AppBaseActivity;

public class WeatherByJsonObjectActivity extends AppBaseActivity {
	TextView tvCity;
	TextView tvCityId;

	@Override
	protected void initVariables() {
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_weather);

		tvCity = (TextView) findViewById(R.id.tvCity);
		tvCityId = (TextView) findViewById(R.id.tvCityId);
	}

	@Override
	protected void loadData() {
		String url = "http://www.weather.com.cn/data/sk/101010100.html";
		new WeatherAsyncTask().execute(url);
	}

	public class WeatherAsyncTask extends AsyncTask<String, Void, String> {

		@Override
		protected void onPreExecute() {
		}

		@Override
		protected String doInBackground(String... url) {
			return getResponseFromURL(url[0]);
		}

		@Override
		protected void onPostExecute(String result) {
			// 第一种写法，基于JSONObject
			try {
				JSONObject jsonResponse = new JSONObject(result);
				JSONObject weatherinfo = jsonResponse
						.getJSONObject("weatherinfo");
				String city = weatherinfo.getString("city");
				int cityId = weatherinfo.getInt("cityid");

				tvCity.setText(city);
				tvCityId.setText(String.valueOf(cityId));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		/*
		 * 根据URL通过Http请求远程调用Google Directions API 并将所要的数据封装到JSONObject中
		 */
		private String getResponseFromURL(String url) {
			HttpGet get = new HttpGet(url);

			String strResponse = null;
			try {
				HttpParams httpParameters = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParameters, 8000);
				HttpClient httpClient = new DefaultHttpClient(httpParameters);

				HttpResponse httpResponse = httpClient.execute(get);
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					strResponse = EntityUtils
							.toString(httpResponse.getEntity());
				}
			} catch (Exception e) {
				return null;
			}

			if (strResponse == null) {
				return null;
			}

			return strResponse;
		}
	}
}

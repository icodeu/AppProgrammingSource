package com.youngheart.activity.weather;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.infrastructure.net.RequestAsyncTask;
import com.youngheart.R;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.entity.WeatherInfo;

public class WeatherByFastJsonActivity extends AppBaseActivity {
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

		RequestAsyncTask task = new RequestAsyncTask() {

			@Override
			public void onSuccess(String result) {
				// 第2种写法，基于fastJSON
				WeatherInfo weatherInfo = JSON.parseObject(
						result,
						WeatherInfo.class);
				if (weatherInfo != null) {
					tvCity.setText(weatherInfo.getCity());
					tvCityId.setText(weatherInfo.getCityid());
				}
			}

			@Override
			public void onFail(String errorMessage) {
				new AlertDialog.Builder(WeatherByFastJsonActivity.this)
						.setTitle("出错啦").setMessage(errorMessage)
						.setPositiveButton("确定", null).show();
			}
		};
		task.execute(url);
	}
}

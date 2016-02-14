package com.youngheart.activity.weather;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.youngheart.R;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.engine.RequestAsyncTask;
import com.youngheart.entity.WeatherEntity;
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
			public void onSuccess(String content) {
				// 第2种写法，基于fastJSON
				WeatherEntity weatherEntity = JSON.parseObject(content,
						WeatherEntity.class);
				WeatherInfo weatherInfo = weatherEntity.getWeatherInfo();
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

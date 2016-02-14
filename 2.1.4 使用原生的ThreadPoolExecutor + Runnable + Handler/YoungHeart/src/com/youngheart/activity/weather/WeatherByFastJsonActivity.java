package com.youngheart.activity.weather;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.infrastructure.net.RequestCallback;
import com.infrastructure.net.RequestParameter;
import com.youngheart.R;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.engine.RemoteService;
import com.youngheart.entity.WeatherInfo;

public class WeatherByFastJsonActivity extends AppBaseActivity {
	TextView tvCity;
	TextView tvCityId;

	private RequestCallback weatherCallback = null;

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
		weatherCallback = new RequestCallback() {

			@Override
			public void onSuccess(String content) {
				WeatherInfo weatherInfo = JSON.parseObject(content,
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

		ArrayList<RequestParameter> params = new ArrayList<RequestParameter>();
		RequestParameter rp1 = new RequestParameter("cityId", "111");
		RequestParameter rp2 = new RequestParameter("cityName", "Beijing");
		params.add(rp1);
		params.add(rp2);

		RemoteService.getInstance().invoke(this, "getWeatherInfo", params,
				weatherCallback);
	}
}

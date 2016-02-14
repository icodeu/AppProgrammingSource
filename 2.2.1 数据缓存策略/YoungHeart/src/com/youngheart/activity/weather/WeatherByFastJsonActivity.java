package com.youngheart.activity.weather;

import java.util.ArrayList;

import android.os.Bundle;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.infrastructure.net.RequestCallback;
import com.infrastructure.net.RequestParameter;
import com.youngheart.R;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.engine.RemoteService;
import com.youngheart.entity.WeatherInfo;
import com.youngheart.utils.Utils;

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
		dlg = Utils.createProgressDialog(this,
				this.getString(R.string.str_loading));
		dlg.show();

		loadAPIData1();
	}

	void loadAPIData1() {
		weatherCallback = new AbstractRequestCallback() {

			@Override
			public void onSuccess(String content) {
				WeatherInfo weatherInfo = JSON.parseObject(content,
						WeatherInfo.class);
				if (weatherInfo != null) {
					tvCity.setText(weatherInfo.getCity());
					tvCityId.setText(weatherInfo.getCityid());
				}

				// 继续执行下一个请求
				loadAPIData2();
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

	void loadAPIData2() {
		weatherCallback = new AbstractRequestCallback() {

			@Override
			public void onSuccess(String content) {
				dlg.dismiss();

				WeatherInfo weatherInfo = JSON.parseObject(content,
						WeatherInfo.class);
				if (weatherInfo != null) {
					tvCity.setText(weatherInfo.getCity());
					tvCityId.setText(weatherInfo.getCityid());
				}

				// 继续执行下一个请求
				loadAPIData3();
			}

			@Override
			public void onFail(String errorMessage) {
				dlg.dismiss();

				// 重启App或者啥都不做
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

	void loadAPIData3() {
		ArrayList<RequestParameter> params 
			= new ArrayList<RequestParameter>();
		RequestParameter rp1 = new RequestParameter("cityId", "111");
		RequestParameter rp2 = new RequestParameter("cityName", "Beijing");
		params.add(rp1);
		params.add(rp2);

		RemoteService.getInstance()
				.invoke(this, "getWeatherInfo", params, null);
	}
}

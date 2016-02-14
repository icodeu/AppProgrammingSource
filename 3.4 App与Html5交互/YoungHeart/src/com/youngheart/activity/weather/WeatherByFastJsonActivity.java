package com.youngheart.activity.weather;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
	Button btnShowTime;

	private RequestCallback weatherCallback = null;

	@Override
	protected void initVariables() {
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_weather);

		tvCity = (TextView) findViewById(R.id.tvCity);
		tvCityId = (TextView) findViewById(R.id.tvCityId);

		btnShowTime = (Button) findViewById(R.id.btnShowTime);
		btnShowTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String strCurrentTime = Utils.getServerTime().toString();

				new AlertDialog.Builder(WeatherByFastJsonActivity.this)
						.setTitle("当前时间是：").setMessage(strCurrentTime)
						.setPositiveButton("确定", null).show();
			}
		});

	}

	@Override
	protected void loadData() {
		dlg = Utils.createProgressDialog(this,
				this.getString(R.string.str_loading));
		dlg.show();

		loadAPIData1();
	}

	void loadAPIData1() {
		// 示例1：共用AppBaseActivity中的onFail方法
		weatherCallback = new AbstractRequestCallback() {

			@Override
			public void onSuccess(String content) {
				WeatherInfo weatherInfo = JSON.parseObject(content,
						WeatherInfo.class);
				if (weatherInfo != null) {
					tvCity.setText(weatherInfo.getCity());
					tvCityId.setText(weatherInfo.getCityid());
				}

				// 接下来调用MobileAPI2，由于是同一MobileAPI，所以从缓存读取
				loadAPIData2();
			}
		};

		ArrayList<RequestParameter> params = new ArrayList<RequestParameter>();
		RequestParameter rp3 = new RequestParameter("cityAa2", "111");
		RequestParameter rp1 = new RequestParameter("cityaA", "111");
		RequestParameter rp2 = new RequestParameter("cityName", "Beijing");
		params.add(rp1);
		params.add(rp3);
		params.add(rp2);

		RemoteService.getInstance().invoke(this, "getWeatherInfo", params,
				weatherCallback);
	}

	void loadAPIData2() {
		// 示例2：复写AppBaseActivity中的onFail方法
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

			}

			@Override
			public void onFail(String errorMessage) {
				dlg.dismiss();

				// 重启App或者啥都不做
			}
		};

		ArrayList<RequestParameter> params = new ArrayList<RequestParameter>();
		RequestParameter rp1 = new RequestParameter("cityaA2", "111");
		RequestParameter rp3 = new RequestParameter("cityAa", "111");
		RequestParameter rp2 = new RequestParameter("cityName", "Beijing");
		params.add(rp1);
		params.add(rp3);
		params.add(rp2);

		RemoteService.getInstance().invoke(this, "getWeatherInfo", params,
				weatherCallback);
	}
}

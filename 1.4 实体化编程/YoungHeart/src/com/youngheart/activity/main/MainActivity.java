package com.youngheart.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.youngheart.R;
import com.youngheart.activity.others.LoginActivity;
import com.youngheart.activity.others.LoginNewActivity;
import com.youngheart.activity.weather.WeatherByFastJsonActivity;
import com.youngheart.activity.weather.WeatherByGsonActivity;
import com.youngheart.activity.weather.WeatherByJsonObjectActivity;
import com.youngheart.engine.AppConstants;
import com.youngheart.engine.GlobalVariables;
import com.youngheart.entity.CinemaBean;

public class MainActivity extends Activity {
	private Button btnLogin;
	private Button btnLoginNew;

	private Button btnByJsonObject;
	private Button btnByFastJson;
	private Button btnByGson;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.activity_main);

		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				intent.putExtra(AppConstants.Email, "jianqiang.bao@qq.com");
								
				CinemaBean cinema = new CinemaBean();
				cinema.setCinemaId("1");
				cinema.setCinemaName("星美");

				//使用全局变量的方式传递参数
				GlobalVariables.Cinema = cinema;
				
				startActivity(intent);
			}
		});

		btnLoginNew = (Button) findViewById(R.id.btnLoginNew);
		btnLoginNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						LoginNewActivity.class);
				intent.putExtra(AppConstants.Email, "jianqiang.bao@qq.com");
				
				CinemaBean cinema = new CinemaBean();
				cinema.setCinemaId("1");
				cinema.setCinemaName("星美");
				
				//使用intent上挂可序列化实体的方式传递参数
				intent.putExtra(AppConstants.Cinema, cinema);

				startActivity(intent);
			}
		});
		
		btnByJsonObject = (Button) findViewById(R.id.btnByJsonObject);
		btnByJsonObject.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						WeatherByJsonObjectActivity.class);
				startActivity(intent);
			}
		});

		btnByFastJson = (Button) findViewById(R.id.btnByFastJson);
		btnByFastJson.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						WeatherByFastJsonActivity.class);
				startActivity(intent);
			}
		});

		btnByGson = (Button) findViewById(R.id.btnByGson);
		btnByGson.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						WeatherByGsonActivity.class);
				startActivity(intent);
			}
		});
	}
}

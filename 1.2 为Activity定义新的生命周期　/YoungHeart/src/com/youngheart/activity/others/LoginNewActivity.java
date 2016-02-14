package com.youngheart.activity.others;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.youngheart.R;
import com.youngheart.activity.personcenter.PersonCenterActivity;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.engine.AppConstants;

public class LoginNewActivity extends AppBaseActivity implements View.OnClickListener {
	private int loginTimes;
	private String strEmail;
	
	private EditText etPassword;
	private EditText etEmail;
	private Button btnLogin;

	@Override
	protected void initVariables() {
		loginTimes = -1;
		
		Bundle bundle = getIntent().getExtras();
		strEmail = bundle.getString(AppConstants.Email);
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);		

		etEmail = (EditText)findViewById(R.id.email);
		etEmail.setText(strEmail);		
		etPassword = (EditText)findViewById(R.id.password);
		
		//登录事件
		btnLogin = (Button)findViewById(R.id.sign_in_button);
		btnLogin.setOnClickListener(this);
	}

	@Override
	protected void loadData() {
		//获取2个MobileAPI，获取天气数据，获取城市数据
		loadWeatherData();
		loadCityData();
	}
		
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.sign_in_button:
			gotoLoginActivity();
		}
	}
	
	private void gotoLoginActivity() {
		Intent intent = new Intent(LoginNewActivity.this, 
				PersonCenterActivity.class);		
		startActivity(intent);
	}
	
	private void loadWeatherData() {
		//发起网络请求，代码从略
	}
	
	private void loadCityData() {
		//发起网络请求，代码从略
	}	
}
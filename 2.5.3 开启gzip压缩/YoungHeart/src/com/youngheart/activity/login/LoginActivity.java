package com.youngheart.activity.login;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.fastjson.JSON;
import com.infrastructure.net.RequestCallback;
import com.infrastructure.net.RequestParameter;
import com.youngheart.R;
import com.youngheart.activity.personcenter.PersonCenterActivity;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.engine.AppConstants;
import com.youngheart.engine.RemoteService;
import com.youngheart.engine.User;
import com.youngheart.entity.UserInfo;

public class LoginActivity extends AppBaseActivity {
	private Button btnLogin;
	
	@Override
	protected void initVariables() {
		Bundle bundle = getIntent().getExtras();
		if(bundle == null)
			return;
		
		needCallback = bundle.getBoolean(AppConstants.NeedCallback, false);
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);		
		
		//登录事件
		btnLogin = (Button)findViewById(R.id.sign_in_button);
		btnLogin.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						login();
					}
				});
	}

	@Override
	protected void loadData() {

	}

	private void login() {
		RequestCallback loginCallback = new AbstractRequestCallback() {

			@Override
			public void onSuccess(String content) {
				UserInfo userInfo = JSON.parseObject(content,
						UserInfo.class);
				if (userInfo != null) {
					User.getInstance().reset();
					User.getInstance().setLoginName(userInfo.getLoginName());
					User.getInstance().setScore(userInfo.getScore());
					User.getInstance().setUserName(userInfo.getUserName());
					User.getInstance().setLoginStatus(true);
					User.getInstance().save();
				}
				
				if(needCallback) {
					setResult(Activity.RESULT_OK);
					finish();					
				} else {
					Intent intent = new Intent(LoginActivity.this, 
							PersonCenterActivity.class);
					startActivity(intent);
				}
			}
		};

		ArrayList<RequestParameter> params = new ArrayList<RequestParameter>();
		RequestParameter rp1 = new RequestParameter("loginName", "jianqiang.bao");
		RequestParameter rp2 = new RequestParameter("password", "1111");
		params.add(rp1);
		params.add(rp2);

		RemoteService.getInstance().invoke(this, "login", params,
				loginCallback);
	}	
}
package com.youngheart.activity.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.infrastructure.net.RequestCallback;
import com.youngheart.R;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.engine.AppConstants;
import com.youngheart.engine.RemoteService;

public class CookieExpiredActivity extends AppBaseActivity {
	private static final int LOGIN_EXPIRED = 3002;	//Cookie失效

	private Button btnMockExpiredAction;
	
	@Override
	protected void initVariables() {
		Bundle bundle = getIntent().getExtras();
		if (bundle == null)
			return;

		needCallback = bundle.getBoolean(AppConstants.NeedCallback, false);
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_login);

		// 登录事件
		btnMockExpiredAction = (Button) findViewById(R.id.btnMockExpiredAction);
		btnMockExpiredAction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				mockExpiredAction();
			}
		});
	}

	@Override
	protected void loadData() {

	}

	private void mockExpiredAction() {
		RequestCallback weatherCallback = new AbstractRequestCallback() {

			@Override
			public void onSuccess(String content) {
				// do something
			}
		};

		RemoteService.getInstance().invoke(this, "getWeatherInfo", null,
				weatherCallback);
	}
}
package com.youngheart.activity.personcenter;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.youngheart.R;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.engine.User;

public class PersonCenterActivity extends AppBaseActivity {
	TextView tvPersonCenter;
	
	@Override
	protected void initVariables() {

	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		setContentView(R.layout.activity_personcenter);	
		
		tvPersonCenter = (TextView)findViewById(R.id.tvPersonCenter);
		tvPersonCenter.setText(
				User.getInstance().getUserName());
	}

	
	
	@Override
	protected void loadData() {

	}
}

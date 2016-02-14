package com.youngheart.activity.others;

import android.os.Bundle;

import com.youngheart.base.AppBaseActivity;

public class TestNullValueActivity extends AppBaseActivity {	

	@Override
	protected void initVariables() {
		Object obj = null;
		int result = Integer.valueOf(obj.toString());	
		
	}

	@Override
	protected void initViews(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void loadData() {
		// TODO Auto-generated method stub
		
	}
}

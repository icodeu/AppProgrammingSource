package com.example.switchenvbymenu;

import android.os.Bundle;
import android.widget.TextView;

public class NextPageActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nextpage);
		
		TextView tvIP = (TextView)findViewById(R.id.tvIP);
		tvIP.setText(Globals.IP);
	}
}

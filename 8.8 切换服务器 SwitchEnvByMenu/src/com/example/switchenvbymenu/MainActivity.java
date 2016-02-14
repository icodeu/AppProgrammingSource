package com.example.switchenvbymenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		TextView tvIP = (TextView)findViewById(R.id.tvIP);
		tvIP.setText(Globals.IP);
		
		Button btnPhone = (Button)findViewById(R.id.btnNext);		
		btnPhone.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MainActivity.this, NextPageActivity.class);
						startActivity(intent);
					}
				});

	}
}

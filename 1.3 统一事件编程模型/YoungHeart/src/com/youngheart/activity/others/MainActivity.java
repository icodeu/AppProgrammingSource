package com.youngheart.activity.others;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.youngheart.R;
import com.youngheart.engine.AppConstants;

public class MainActivity extends Activity {
	private Button btnNext;
	private Button btnNextNew;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.activity_main);

		btnNext = (Button) findViewById(R.id.btnNext);
		btnNext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						LoginActivity.class);
				intent.putExtra(AppConstants.Email, "jianqiang.bao@qq.com");
				startActivity(intent);
			}
		});

		btnNextNew = (Button) findViewById(R.id.btnNextNew);
		btnNextNew.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						LoginNewActivity.class);
				intent.putExtra(AppConstants.Email, "jianqiang.bao@qq.com");
				startActivity(intent);
			}
		});
	}
}

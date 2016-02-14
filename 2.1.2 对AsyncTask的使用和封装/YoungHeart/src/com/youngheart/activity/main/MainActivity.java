package com.youngheart.activity.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.youngheart.R;
import com.youngheart.activity.weather.WeatherByFastJsonActivity;

public class MainActivity extends Activity {
	private Button btnByFastJson;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);		
		
		setContentView(R.layout.activity_main);

		btnByFastJson = (Button) findViewById(R.id.btnByFastJson);
		btnByFastJson.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this,
						WeatherByFastJsonActivity.class);
				startActivity(intent);
			}
		});
	}
}

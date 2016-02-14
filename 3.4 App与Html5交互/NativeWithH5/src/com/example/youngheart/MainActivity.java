package com.example.youngheart;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private Button btnShowAlert;
	private TextView tvMessage;
	private WebView wvAds;

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnShowAlert = (Button) findViewById(R.id.btnShowAlert);
		tvMessage = (TextView) findViewById(R.id.tvMessage);
		wvAds = (WebView) findViewById(R.id.wvAds);

		wvAds.getSettings().setJavaScriptEnabled(true);
		wvAds.loadUrl("file:///android_asset/104.html");
				
		btnShowAlert.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String color = "#00ee00";
				wvAds.loadUrl("javascript: changeColor ('" + color + "');");
			}
		});

		wvAds.addJavascriptInterface(new JSInteface1(), "baobao");
//		wvAds.addJavascriptInterface(this, "baobao");
	}

	class JSInteface1 {
		public void callAndroidMethod(int a, float b, String c, boolean d) {
			if (d) {
				String strMessage = "-" + (a + 1) + "-" + (b + 1) + "-" + c
						+ "-" + d;

				new AlertDialog.Builder(MainActivity.this).setTitle("title")
						.setMessage(strMessage).show();
			}
		}

		public void gotoAnyWhere(String url) {
			if (url != null) {
				if (url.startsWith("gotoMovieDetail:")) {
					String strMovieId = url.substring(24);
					int movieId = Integer.valueOf(strMovieId);

					Intent intent = new Intent(MainActivity.this,
							MovieDetailActivity.class);
					intent.putExtra("movieId", movieId);
					startActivity(intent);
				} else if (url.startsWith("gotoNewsList:")) {
					//as above
				} else if (url.startsWith("gotoPersonCenter")) {
					Intent intent = new Intent(MainActivity.this,
							PersonCenterActivity.class);
					startActivity(intent);
				} else if (url.startsWith("gotoUrl:")) {
					String strUrl = url.substring(8);
					wvAds.loadUrl(strUrl);
				}
			}
		}
	}
	
	public void callAndroidMethod(int a, float b, String c, boolean d) {
		if (d) {
			String strMessage = "-" + (a + 1) + "-" + (b + 1) + "-" + c
					+ "-" + d;

			new AlertDialog.Builder(MainActivity.this).setTitle("title")
					.setMessage(strMessage).show();
		}
	}
}

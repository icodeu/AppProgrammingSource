package com.youngheart.activity.others;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.youngheart.utils.Utils;

public class TestNullValueActivity extends Activity {	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Object obj = null;
//		int result = Integer.valueOf(obj.toString());	
		int result = Utils.convertToInt(obj, 0);
		
		String cityName = "T";
		String firstLetter = "";
		if(cityName.length() > 1) {
			firstLetter = cityName.substring(1, 2);
		}
		
		Log.d(firstLetter, firstLetter);
		result = 1;
	}
}

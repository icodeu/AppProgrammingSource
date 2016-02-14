package com.example.youngheart;

import android.app.Activity;
import android.content.Intent;

public class Dispatcher {
	
	public static void gotoAnyWhere(String url, final Activity activity) {
		String findKey = null;
		
		Intent intent = new Intent();

		int pos = url.indexOf(":");
		if (pos == -1) {
			findKey = url;
		} else {
			findKey = url.substring(0, pos);

			String strParams = url.substring(pos);
			String[] pairs = strParams.split("&");
			for (String strKeyAndValue : pairs) {
				String[] arr = strKeyAndValue.split("=");
				String key = arr[0];
				String value = arr[1];
				if (value.startsWith("(int)")) {
					intent.putExtra(key, Integer.valueOf(value.substring(5)));
				} else if (value.startsWith("(Double)")) {
					intent.putExtra(key, Double.valueOf(value.substring(8)));
				} else {
					intent.putExtra(key, value);
				}
			}
		}
		
		ProtocolData protocol = ProtocolManager.findProtocol(findKey, activity);
		try {
			intent.setClass(activity, Class.forName(protocol.getTarget()));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}

		activity.startActivity(intent);
	}
}

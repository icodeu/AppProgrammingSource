package com.example.switchenvbymenu;

import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;

public class BaseActivity extends Activity {

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		
		if (Config.isDebug) {
			getMenuInflater().inflate(R.menu.activity_main, menu);			
		}

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
		case R.id.menu_ip1:
			Globals.IP = "http://212.1.2.3";
			break;
		case R.id.menu_ip2:
			Globals.IP = "http://192.168.1.14";
			break;
		case R.id.menu_ip3:
			Globals.IP = "http://192.168.2.28";
			break;
	    default:
	    	return super.onOptionsItemSelected(item);
	    }
	    return true;
	}
}

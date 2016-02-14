package com.youngheart.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.infrastructure.activity.BaseActivity;
import com.infrastructure.net.RequestCallback;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.youngheart.activity.login.LoginActivity;
import com.youngheart.engine.AppConstants;
import com.youngheart.engine.CrashHandler;

public abstract class AppBaseActivity extends BaseActivity {
	protected boolean needCallback;

	protected ProgressDialog dlg;

	public ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		CrashHandler.getInstance().switchCrashActivity(this);
		
		super.onCreate(savedInstanceState);
	}

	protected void onDestroy() {
		// 回收该页面缓存在内存的图片
		imageLoader.clearMemoryCache();

		super.onDestroy();
	}

	public abstract class AbstractRequestCallback implements RequestCallback {

		public abstract void onSuccess(String content);

		public void onFail(String errorMessage) {
			dlg.dismiss();

			new AlertDialog.Builder(AppBaseActivity.this).setTitle("出错啦")
					.setMessage(errorMessage).setPositiveButton("确定", null)
					.show();
		}

		public void onCookieExpired() {
			dlg.dismiss();

			new AlertDialog.Builder(AppBaseActivity.this)
					.setTitle("出错啦")
					.setMessage("Cookie过期，请重新登录")
					.setPositiveButton("确定",
							new DialogInterface.OnClickListener() {

								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											AppBaseActivity.this,
											LoginActivity.class);
									intent.putExtra(AppConstants.NeedCallback,
											true);
									startActivity(intent);
								}
							}).show();
		}
	}
}

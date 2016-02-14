package com.youngheart.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;

import com.infrastructure.activity.BaseActivity;
import com.infrastructure.net.RequestCallback;

public abstract class AppBaseActivity extends BaseActivity {
	protected boolean needCallback;

	protected ProgressDialog dlg;

	public abstract class AbstractRequestCallback implements RequestCallback {

		public abstract void onSuccess(String content);

		public void onFail(String errorMessage) {
			dlg.dismiss();

			new AlertDialog.Builder(AppBaseActivity.this).setTitle("出错啦")
					.setMessage(errorMessage).setPositiveButton("确定", null)
					.show();
		}
	}
}

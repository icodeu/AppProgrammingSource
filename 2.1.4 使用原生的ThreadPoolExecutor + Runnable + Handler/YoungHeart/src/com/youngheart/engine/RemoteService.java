package com.youngheart.engine;

import java.util.List;

import com.infrastructure.activity.BaseActivity;
import com.infrastructure.net.DefaultThreadPool;
import com.infrastructure.net.HttpRequest;
import com.infrastructure.net.RequestCallback;
import com.infrastructure.net.RequestParameter;
import com.infrastructure.net.URLData;
import com.infrastructure.net.UrlConfigManager;

public class RemoteService {
	private static RemoteService service = null;

	private RemoteService() {

	}

	public static synchronized RemoteService getInstance() {
		if (RemoteService.service == null) {
			RemoteService.service = new RemoteService();
		}
		return RemoteService.service;
	}

	public void invoke(final BaseActivity activity,
			final String apiKey,
			final List<RequestParameter> params,
			final RequestCallback callBack) {
		final URLData urlData = UrlConfigManager.findURL(activity, apiKey);
		HttpRequest request = activity.getRequestManager().createRequest(
				urlData, params, callBack);
		DefaultThreadPool.getInstance().execute(request);
	}
}
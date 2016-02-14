package com.youngheart.engine;

import com.infrastructure.cache.CacheManager;

import android.app.Application;

public class YoungHeartApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate(); 
		
		CacheManager.getInstance().initCacheDir();
	}
}

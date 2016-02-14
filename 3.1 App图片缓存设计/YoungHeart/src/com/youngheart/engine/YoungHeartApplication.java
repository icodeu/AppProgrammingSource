package com.youngheart.engine;

import android.app.Application;

import com.infrastructure.cache.CacheManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

public class YoungHeartApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		CacheManager.getInstance().initCacheDir();

		ImageLoaderConfiguration config = 
				new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.memoryCacheExtraOptions(480, 480)
				.memoryCacheSize(2 * 1024 * 1024)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.memoryCache(new WeakMemoryCache()).build();

		ImageLoader.getInstance().init(config);

	}
}

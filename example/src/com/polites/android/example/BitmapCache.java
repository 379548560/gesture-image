package com.polites.android.example;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader.ImageCache;

@SuppressLint("NewApi") public class BitmapCache implements ImageCache {

	private static final String TAG = "BitmapCache";
	private LruCache<String, Bitmap> mCache;
	private static BitmapCache lruImageCache;

	public BitmapCache() {
		// 获取应用程序最大可用内存
		int maxMemory = (int) Runtime.getRuntime().maxMemory();
		int cacheSize = maxMemory / 8;
		mCache = new LruCache<String, Bitmap>(cacheSize) {
			@Override
			protected int sizeOf(String key, Bitmap bitmap) {
				return bitmap.getRowBytes() * bitmap.getHeight();
			}
		};
	}

	public static BitmapCache instance() {
		if (lruImageCache == null) {
			lruImageCache = new BitmapCache();
		}
		return lruImageCache;
	}

	@Override
	public Bitmap getBitmap(String url) {
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		if (getBitmap(url) == null) {
			mCache.put(url, bitmap);
		}
	}

}

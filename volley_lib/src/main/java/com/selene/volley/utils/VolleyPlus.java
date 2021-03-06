package com.selene.volley.utils;

import android.content.Context;
import android.widget.ImageView;

import com.selene.volley.cache.BitmapImageCache;
import com.selene.volley.core.Request;
import com.selene.volley.core.RequestQueue;
import com.selene.volley.image.ImageLoader;
import com.selene.volley.image.SelfImageLoader;
import com.selene.volley.request.ImageRequest;
import com.selene.volley.tool.Volley;


/**
 * @author Tony E-mail:solaris0403@gmail.com
 * @version Create Data：Aug 25, 2015 4:01:55 PM
 */
public class VolleyPlus {
	private static Context mContext;
	private static RequestQueue mRequestQueue;
	private static ImageLoader mImageLoader;

	private VolleyPlus(Context context) {
	}

	/**
	 * 在Application里初始化
	 * 
	 * @param context
	 * @param config
	 */
	public static void init(Context context) {
		if (context == null) {
			throw new NullPointerException("context is null");
		}
		mContext = context;
		start();
	}

	private static void start() {
		if (mRequestQueue != null) {
			throw new IllegalStateException("RequestQueue has initialized");
		}
		mRequestQueue = getRequestQueue();
	}

	public static RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
		}
		return mRequestQueue;
	}

	public static void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

	public static <T> void addToRequestQueue(Request<T> req) {
		getRequestQueue().add(req);
	}

	public static ImageLoader getImageLoader() {
		if (mImageLoader == null) {
			mImageLoader = new SelfImageLoader(getRequestQueue(), new BitmapImageCache(), mContext.getResources(), mContext.getAssets()){
				@Override
				public void makeRequest(ImageRequest request) {
					super.makeRequest(request);
				//	request.setCacheExpireTime(TimeUnit.MINUTES, 10);
				}
			};
		}
		return mImageLoader;
	}

	public static void load(ImageView imageView, String url, int defaultImageResId, int errorImageResId ){
		ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, defaultImageResId, errorImageResId);
		getImageLoader().get(url, listener);
	}
}

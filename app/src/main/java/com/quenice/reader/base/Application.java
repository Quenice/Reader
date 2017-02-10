package com.quenice.reader.base;


import com.squareup.picasso.Picasso;

/**
 * QReader Application
 * Created by qiubb on 2017/2/9.
 */
public class Application extends android.app.Application {
	private static Application mApplication;

	public static synchronized Application getInstance() {
		return mApplication;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mApplication = this;
		initVars();
	}

	private void initVars() {
		Picasso.with(this).setLoggingEnabled(true);
		Picasso.with(this).setIndicatorsEnabled(true);
	}
}

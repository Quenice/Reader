package com.quenice.reader.module.zhihudaily.http;

import android.content.Context;

import com.quenice.reader.module.zhihudaily.model.ZhihuDailyDetail;
import com.quenice.reader.module.zhihudaily.model.ZhihuDaily;
import com.quenice.reader.common.callback.Callback;

import rx.Observable;

/**
 * 知乎日报http辅助类
 * Created by qiubb on 2017/2/22.
 */
public class ZhihuDailyHttpHelper {
	private static ZhihuDailyHttpHelper mHelper;

	private ZhihuDailyHttpHelper() {
	}

	public static synchronized ZhihuDailyHttpHelper getInstance() {
		return mHelper == null ? (mHelper = new ZhihuDailyHttpHelper()) : mHelper;
	}


	/**
	 * 获得知乎日报最新消息
	 * @param context
	 * @param callback
	 */
	public void listLatest(Context context, Callback<ZhihuDaily> callback) {
		Observable<ZhihuDaily> observable = RetrofitClient.getInstance().getHttpService().listLatest();
		RetrofitClient.getInstance().doRequest(context, observable, callback);
	}

	/**
	 * 获得知乎日报的往日消息
	 * @param context
	 * @param date
	 * @param callback
	 */
	public void listBefore(Context context, String date, Callback<ZhihuDaily> callback) {
		Observable<ZhihuDaily> observable = RetrofitClient.getInstance().getHttpService().listBefore(date);
		RetrofitClient.getInstance().doRequest(context, observable, callback);
	}

	/**
	 * 获得知乎日报详情
	 * @param context
	 * @param id
	 * @param callback
	 */
	public void detail(Context context, long id, Callback<ZhihuDailyDetail> callback) {
		Observable<ZhihuDailyDetail> observable = RetrofitClient.getInstance().getHttpService().detail(id);
		RetrofitClient.getInstance().doRequest(context, observable, callback);
	}
}

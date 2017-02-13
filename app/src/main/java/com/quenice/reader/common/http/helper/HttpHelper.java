package com.quenice.reader.common.http.helper;

import android.content.Context;

import com.quenice.reader.common.callback.Callback;
import com.quenice.reader.common.http.client.RetrofitClient;
import com.quenice.reader.detail.model.ZhihuDailyDetail;
import com.quenice.reader.main.model.ZhihuDaily;

import rx.Observable;

/**
 * retrofit http helper
 * Created by qiubb on 2017/02/09.
 */
public class HttpHelper {
	private static HttpHelper mHelper;

	private HttpHelper() {
	}

	public static synchronized HttpHelper getInstance() {
		return mHelper == null ? (mHelper = new HttpHelper()) : mHelper;
	}

	/**
	 * 获得知乎日报最新消息
	 * @param context
	 * @param callback
	 */
	public void zhihuDailyListLatest(Context context, Callback<ZhihuDaily> callback) {
		Observable<ZhihuDaily> observable = RetrofitClient.getInstance().getHttpService().zhihuDailyListLatest();
		RetrofitClient.getInstance().doRequest(context, observable, callback);
	}

	/**
	 * 获得知乎日报的往日消息
	 * @param context
	 * @param date
	 * @param callback
	 */
	public void zhihuDailyListBefore(Context context, String date, Callback<ZhihuDaily> callback) {
		Observable<ZhihuDaily> observable = RetrofitClient.getInstance().getHttpService().zhihuDailyListBefore(date);
		RetrofitClient.getInstance().doRequest(context, observable, callback);
	}

	/**
	 * 获得知乎日报详情
	 * @param context
	 * @param id
	 * @param callback
	 */
	public void zhihuDailyDetail(Context context, long id, Callback<ZhihuDailyDetail> callback) {
		Observable<ZhihuDailyDetail> observable = RetrofitClient.getInstance().getHttpService().zhihuDailyDetail(id);
		RetrofitClient.getInstance().doRequest(context, observable, callback);
	}
}

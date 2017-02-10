package com.quenice.reader.common.http.helper;

import android.content.Context;

import com.quenice.reader.base.Callback;
import com.quenice.reader.common.http.client.RetrofitClient;
import com.quenice.reader.common.http.model.ZhihuDaily;

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
}

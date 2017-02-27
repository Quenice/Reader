package com.quenice.reader.module.zhihudaily.http;

import com.quenice.reader.common.http.BaseRetrofitClient;
import com.quenice.reader.common.utils.Constants;

/**
 * 知乎日报网络连接
 * Created by qiubb on 2017/2/22.
 */
public class RetrofitClient extends BaseRetrofitClient<HttpService> {
	private static RetrofitClient mRetrofitClient;

	private RetrofitClient() {
		super();
	}

	public static synchronized RetrofitClient getInstance() {
		return mRetrofitClient == null ? (mRetrofitClient = new RetrofitClient()) : mRetrofitClient;
	}
	@Override
	protected String baseUrl() {
		return Constants.Urls.ZhiHuDaily.BASE;
	}

	@Override
	protected Class<HttpService> httpServiceClass() {
		return HttpService.class;
	}
}

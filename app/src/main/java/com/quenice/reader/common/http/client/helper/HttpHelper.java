package com.quenice.reader.common.http.client.helper;

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
}

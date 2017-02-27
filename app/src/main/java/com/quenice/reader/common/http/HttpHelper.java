package com.quenice.reader.common.http;

import com.quenice.reader.module.zhihudaily.http.ZhihuDailyHttpHelper;

/**
 * retrofit http helper
 * Created by qiubb on 2017/02/09.
 */
public class HttpHelper {

	public static ZhihuDailyHttpHelper zhihuDaily() {
		return ZhihuDailyHttpHelper.getInstance();
	}
}

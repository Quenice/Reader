package com.quenice.reader.common.utils;

/**
 * 常量
 * Created by qiubb on 2017/2/9.
 */

public final class Constants {
	private Constants() {
	}

	/**
	 * 当前的内容类型
	 */
	public interface Content {
		/**
		 * 知乎日报
		 */
		int ZHIHUDAILY = 0;
	}

	public interface Urls {
		interface ZhiHuDaily {
			String BASE = "http://news-at.zhihu.com/api/4/";
			String LIST_LATEST = "news/latest";
			String LIST_BEFORE = "news/before";
			String DETAIL = "news";
		}
	}

	public interface ErrorCode {
		int NET_ERROR = 996;
		int JSON_PARSE_ERROR = 997;
		int RESPONSE_NULL = 998;
		int NONE_NET = 999;
	}
}

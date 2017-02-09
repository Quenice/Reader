package com.quenice.reader.common.utils;

/**
 * 常量
 * Created by qiubb on 2017/2/9.
 */

public final class Constants {
	private Constants() {
	}

	public interface Urls {
		interface ZhiHu {
			String LIST = "";
			String DETAIL = "";
		}
	}

	public interface ErrorCode {
		int NET_ERROR = 996;
		int JSON_PARSE_ERROR = 997;
		int RESPONSE_NULL = 998;
		int NONE_NET = 999;
	}
}

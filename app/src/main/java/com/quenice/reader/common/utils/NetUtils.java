package com.quenice.reader.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.quenice.reader.base.Application;

/**
 * 网络工具类
 * Created by qiubb on 2017/2/9.
 */

public class NetUtils {
	public static final int NETWORN_NONE = 0;
	public static final int NETWORN_WIFI = 1;
	public static final int NETWORN_MOBILE = 2;

	public static int getNetworkState(Context context) {
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		// Wifi
		NetworkInfo.State state = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
			return NETWORN_WIFI;
		}

		// 3G
		state = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
				.getState();
		if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
			return NETWORN_MOBILE;
		}
		return NETWORN_NONE;
	}

	public static boolean hasNet() {
		return getNetworkState(Application.getInstance()) != NETWORN_NONE;
	}
}

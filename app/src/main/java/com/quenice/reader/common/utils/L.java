package com.quenice.reader.common.utils;

import android.util.Log;

/**
 * Log管理类<br>
 * 尽量使用该类来打印log，以便于在开发/发布版本的时候开启/禁用log信息，因为print log也会占用CPU
 * 
 * @author qiubb
 * 
 */
public final class L {
	public static void init(boolean debugMode) {
		L.debugMode = debugMode;
	}

	/**
	 * 是否处于debug模式
	 * 
	 * @return
	 */
	public static boolean isDebugMode() {
		return debugMode;
	}

	/**
	 * 是否是debug模式，可以在application中调用L.init，来开启或关闭log打印
	 * 
	 */
	private static boolean debugMode = true;
	private static String TAG = "dayxar_default_log";

	/**
	 * print log for level android.util.Log.INFO
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void i(String tag, String msg) {
		log(tag, debugMode, Log.INFO, msg);
	}

	/**
	 * print log for level android.util.Log.WARN
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void w(String tag, String msg) {
		log(tag, debugMode, Log.WARN, msg);
	}

	/**
	 * print log for level android.util.Log.VERBOSE
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void v(String tag, String msg) {
		log(tag, debugMode, Log.VERBOSE, msg);
	}

	/**
	 * print log for level android.util.Log.DEBUG
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void d(String tag, String msg) {
		log(tag, debugMode, Log.DEBUG, msg);
	}

	/**
	 * print log for level android.util.Log.ERROR
	 * 
	 * @param tag
	 * @param msg
	 */
	public static void e(String tag, String msg) {
		log(tag, debugMode, Log.ERROR, msg);
	}

	/**
	 * print log info
	 * 
	 * @param tag
	 *            TAG
	 * @param debugMode
	 *            是否处于debug模式. true:log, false:no log
	 * @param level
	 *            log level, see android.util.Log for detail
	 * @param msg
	 *            messages
	 */
	public static void log(String tag, boolean debugMode, int level, String msg) {
		if (Utils.isEmpty(tag))
			tag = TAG;
		if (!debugMode)
			return;

		if (msg == null)
			msg = "null";
		switch (level) {
		case Log.INFO:
			Log.i(tag, msg);
			break;
		case Log.WARN:
			Log.w(tag, msg);
			break;
		case Log.DEBUG:
			Log.d(tag, msg);
			break;
		case Log.ERROR:
			Log.e(tag, msg);
			break;
		case Log.VERBOSE:
			Log.v(tag, msg);
			break;
		default:
			Log.i(tag, msg);
			break;
		}
	}
}

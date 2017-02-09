package com.quenice.reader.common.utils;

import java.util.List;
import java.util.Map;

/**
 * Created by qiubb on 2017/2/9.
 */

public final class Utils {
	private Utils() {
	}

	/**
	 * 字符串是否为空
	 *
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(CharSequence value) {
		return value == null || value.length() == 0 || isEmpty(value.toString());
	}

	/**
	 * 字符串是否为空
	 *
	 * @param value
	 * @return
	 */
	public static boolean isEmpty(String value) {
		return value == null || value.trim().equals("") || value.trim().equals("null");
	}

	public static <T> boolean isEmpty(T[] arr) {
		return arr == null || arr.length == 0;
	}

	public static <K, V> boolean isEmpty(Map<K, V> map) {
		return map == null || map.size() == 0;
	}

	/**
	 * list是否为空
	 *
	 * @param list
	 * @return
	 */
	public static <E> boolean isEmpty(List<E> list) {
		return list == null || list.size() == 0;
	}
}

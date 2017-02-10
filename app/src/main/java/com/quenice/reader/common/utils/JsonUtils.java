package com.quenice.reader.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * json工具类
 *
 * @author qiubb
 */
public final class JsonUtils {
	private JsonUtils() {
	}

	private static Gson gson;

	private static Gson doubleGson;

	public final static synchronized Gson gson() {
		if (gson == null) {
			gson = new GsonBuilder().create();
		}
		return gson;
	}

	/**
	 * 特殊处理过double类型。当double的值等于longValue时，当做long类型处理，后面不带小数点
	 *
	 * @return
	 */
	public static synchronized Gson doubleGson() {
		if (doubleGson == null) {
			doubleGson = new GsonBuilder().
					registerTypeAdapter(Double.class, new JsonSerializer<Double>() {

						@Override
						public JsonElement serialize(Double src, Type typeOfSrc, JsonSerializationContext context) {
							if (src == src.longValue())
								return new JsonPrimitive(src.longValue());
							return new JsonPrimitive(src);
						}
					}).create();
		}
		return doubleGson;
	}
}

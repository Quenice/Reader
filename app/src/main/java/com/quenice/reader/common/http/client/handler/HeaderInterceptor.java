package com.quenice.reader.common.http.client.handler;

import com.dayxar.android.base.Application;
import com.dayxar.android.common.http.helper.HttpService;
import com.dayxar.android.common.http.model.Req;
import com.dayxar.android.common.util.AppUtils;
import com.dayxar.android.common.util.JsonUtils;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;


/**
 * header
 * Created by qiubb on 2016/10/31.
 */
public class HeaderInterceptor implements Interceptor {
	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		try {
			//不需要包裹参数
			if ("false".equalsIgnoreCase(request.header(HttpService.HEADER_WRAPPED_PARAM))) {
				return chain.proceed(request);
			}
			//是否需要token信息
			boolean needToken = !"false".equalsIgnoreCase(request.header(HttpService.HEADER_NEEDTOKEN));
			RequestBody requestBody = request.body();
			Charset charset = Charset.forName("UTF-8");
			MediaType contentType = requestBody.contentType();
			charset = contentType == null ? charset : contentType.charset(Charset.forName("UTF-8"));
			Buffer buffer = new Buffer();
			requestBody.writeTo(buffer);
			String paramsStr = buffer.readString(charset);
			Object paramsEntity = null;
			if (!AppUtils.isEmpty(paramsStr)) {
				paramsEntity = JsonUtils.gson().fromJson(paramsStr, Object.class);
			}
			Req<Object> req = new Req<>(paramsEntity);
			if (needToken) {
				req.setTokenInfo(Application.getInstance().getTokenInfo());
			}

			String p = JsonUtils.doubleGson().toJson(req);
			request = request.newBuilder().post(RequestBody.create(MediaType.parse("application/json;charset=utf-8"), p)).build();
		} catch (Exception e) {
			e.printStackTrace();
			AppUtils.reportError(Application.getInstance(), e);
		}
		return chain.proceed(request);
	}
}

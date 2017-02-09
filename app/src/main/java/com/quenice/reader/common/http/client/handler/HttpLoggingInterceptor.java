/*
 * Copyright (C) 2015 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.quenice.reader.common.http.client.handler;


import com.quenice.reader.common.utils.L;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.concurrent.TimeUnit;

import okhttp3.Connection;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpHeaders;
import okio.Buffer;
import okio.BufferedSource;

/**
 * An OkHttp interceptor which logs request and response information. Can be applied as an
 * {@linkplain OkHttpClient#interceptors() application interceptor} or as a {@linkplain
 * OkHttpClient#networkInterceptors() network interceptor}. <p> The format of the logs created by
 * this class should not be considered stable and may change slightly between releases. If you need
 * a stable logging format, use your own interceptor.
 */
public final class HttpLoggingInterceptor implements Interceptor {
	private static final Charset UTF8 = Charset.forName("UTF-8");

	@Override
	public Response intercept(Chain chain) throws IOException {

		Request request = chain.request();
		if (!L.isDebugMode()) {
			return chain.proceed(request);
		}

		RequestBody requestBody = request.body();
		boolean hasRequestBody = requestBody != null;
		StringBuilder log = new StringBuilder();
		Connection connection = chain.connection();
		Protocol protocol = connection != null ? connection.protocol() : Protocol.HTTP_1_1;
		log.append("--> ").append(request.method()).append(" ").append(request.url()).append(" ").append(protocol).append("\n");

		if (hasRequestBody) {
			// Request body headers are only present when installed as a network interceptor. Force
			// them to be included (when available) so there values are known.
			if (requestBody.contentType() != null) {
				log.append("Content-Type: ").append(requestBody.contentType()).append("\n");
			}
			if (requestBody.contentLength() != -1) {
				log.append("Content-Length: ").append(requestBody.contentLength()).append("\n");
			}
		}

		Headers headers = request.headers();
		for (int i = 0, count = headers.size(); i < count; i++) {
			String name = headers.name(i);
			// Skip headers from the request body as they are explicitly logged above.
			if (!"Content-Type".equalsIgnoreCase(name) && !"Content-Length".equalsIgnoreCase(name)) {
				log.append(name).append(": ").append(headers.value(i)).append("\n");
			}
		}

		if (!hasRequestBody) {
			log.append("--> END ").append(request.method()).append("\n");
		} else if (bodyEncoded(request.headers())) {
			log.append("--> END ").append(request.method()).append(" (encoded body omitted)").append("\n");
		} else {
			Buffer buffer = new Buffer();
			requestBody.writeTo(buffer);

			Charset charset = UTF8;
			MediaType contentType = requestBody.contentType();
			if (contentType != null) {
				charset = contentType.charset(UTF8);
			}

			if (isPlaintext(buffer)) {
				log.append(buffer.readString(charset)).append("\n");
				log.append("--> END ").append(request.method()).append(" (").append(requestBody.contentLength()).append("-byte body)").append("\n");
			} else {
				log.append("--> END ").append(request.method()).append(" (binary ").append(requestBody.contentLength()).append("-byte body omitted)").append("\n");
			}
		}

		L.i("HttpClient", log.toString());
		log = new StringBuilder();

		long startNs = System.nanoTime();
		Response response;
		try {
			response = chain.proceed(request);
		} catch (Exception e) {
			log.append("<-- HTTP FAILED: ").append(e).append("\n");
			throw e;
		}
		long tookMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNs);

		ResponseBody responseBody = response.body();
		long contentLength = responseBody.contentLength();
		log.append("<-- ").append(response.code()).append(" ").append(response.message()).append(" ").append(response.request().url()).append(" (").append(tookMs).append("ms" + ')').append("\n");

		headers = response.headers();
		for (int i = 0, count = headers.size(); i < count; i++) {
			log.append(headers.name(i)).append(": ").append(headers.value(i)).append("\n");
		}

		if (!HttpHeaders.hasBody(response)) {
			log.append("<-- END HTTP").append("\n");
		} else if (bodyEncoded(response.headers())) {
			log.append("<-- END HTTP (encoded body omitted)").append("\n");
		} else {
			BufferedSource source = responseBody.source();
			source.request(Long.MAX_VALUE); // Buffer the entire body.
			Buffer buffer = source.buffer();

			Charset charset = UTF8;
			MediaType contentType = responseBody.contentType();
			if (contentType != null) {
				try {
					charset = contentType.charset(UTF8);
				} catch (UnsupportedCharsetException e) {
					log.append("Couldn't decode the response body; charset is likely malformed.").append("\n");
					log.append("<-- END HTTP").append("\n");

					return response;
				}
			}

			if (!isPlaintext(buffer)) {
				log.append("<-- END HTTP (binary ").append(buffer.size()).append("-byte body omitted)").append("\n");
				return response;
			}

			if (contentLength != 0) {
				log.append(buffer.clone().readString(charset)).append("\n");
			}
			log.append("<-- END HTTP (").append(buffer.size()).append("-byte body)").append("\n");
		}
		L.i("HttpClient", log.toString());
		return response;
	}

	/**
	 * Returns true if the body in question probably contains human readable text. Uses a small sample
	 * of code points to detect unicode control characters commonly used in binary file signatures.
	 */
	static boolean isPlaintext(Buffer buffer) {
		try {
			Buffer prefix = new Buffer();
			long byteCount = buffer.size() < 64 ? buffer.size() : 64;
			buffer.copyTo(prefix, 0, byteCount);
			for (int i = 0; i < 16; i++) {
				if (prefix.exhausted()) {
					break;
				}
				int codePoint = prefix.readUtf8CodePoint();
				if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
					return false;
				}
			}
			return true;
		} catch (EOFException e) {
			return false; // Truncated UTF-8 sequence.
		}
	}

	private boolean bodyEncoded(Headers headers) {
		String contentEncoding = headers.get("Content-Encoding");
		return contentEncoding != null && !contentEncoding.equalsIgnoreCase("identity");
	}
}

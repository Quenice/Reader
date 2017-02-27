package com.quenice.reader.common.http;

import android.content.Context;

import com.google.gson.JsonSyntaxException;
import com.quenice.reader.common.callback.Callback;
import com.quenice.reader.common.utils.Constants;
import com.quenice.reader.common.utils.NetUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Retrofit网络请求类
 * Created by qiubb on 2016/10/31.
 */
public abstract class BaseRetrofitClient<HTTPSERVICE> {
	private OkHttpClient mOkHttpClient;
	private Retrofit mRetrofit;
	private HTTPSERVICE mHttpService;
	private HttpLoggingInterceptor mHttpLoggingInterceptor;
	private final Map<Context, List<Subscription>> callMap = new HashMap<>();

	public BaseRetrofitClient() {
		init();
	}

	public HTTPSERVICE getHttpService() {
		return mHttpService;
	}

	/**
	 * 初始化知乎日报网络连接
	 */
	protected void init() {
		mHttpLoggingInterceptor = new HttpLoggingInterceptor();
		mOkHttpClient = new OkHttpClient.Builder()
				.addInterceptor(mHttpLoggingInterceptor)
				.connectTimeout(20, TimeUnit.SECONDS)
				.readTimeout(30, TimeUnit.SECONDS)
				.writeTimeout(30, TimeUnit.SECONDS)
				.build();
		mRetrofit = new Retrofit.Builder()
				.client(mOkHttpClient)
				.baseUrl(baseUrl())
				.addConverterFactory(GsonConverterFactory.create())
				.addCallAdapterFactory(RxJavaCallAdapterFactory.create())
				.build();
		mHttpService = mRetrofit.create(httpServiceClass());
	}

	protected abstract String baseUrl();

	protected abstract Class<HTTPSERVICE> httpServiceClass();

	/**
	 * 发起请求
	 *
	 * @param context
	 * @param observable
	 * @param callback
	 * @param <T>
	 */
	public <T> void doRequest(Context context, Observable<T> observable, Callback<T> callback) {
		if (callback == null) {
			callback = new Callback<T>() {
				@Override
				public void onSuccess(T data, String msg) {

				}

				@Override
				public void onFailure(int code, String msg) {

				}
			};
		}
		//无网络
		if (!NetUtils.hasNet()) {
			callback.onFailure(Constants.ErrorCode.NONE_NET, "无网络连接");
			callback.onComplete();
			return;
		}
		Subscription subscription = observable.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.unsubscribeOn(Schedulers.io())
				.subscribe(new RetrofitSubscriber<>(callback));
		addRunningCall(context, subscription);
	}

	/**
	 * retrofit subscriber
	 *
	 * @param <T>
	 */
	public static class RetrofitSubscriber<T> extends Subscriber<T> {
		private Callback<T> callback;


		public RetrofitSubscriber(Callback<T> callback) {
			super();
			this.callback = callback;
		}

		@Override
		public void onNext(T resp) {
			try {
				if (resp == null) {
					callback.onFailure(Constants.ErrorCode.RESPONSE_NULL, "服务器返回内容异常：response=null");
					return;
				}
				callback.onSuccess(resp, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public void onError(Throwable e) {
			try {
				if (e instanceof JsonSyntaxException) {
					//可能会展示给用户看
					callback.onFailure(Constants.ErrorCode.JSON_PARSE_ERROR, "服务器异常，请稍后再试");
				} else {
					callback.onFailure(Constants.ErrorCode.NET_ERROR, "网络连接异常");
				}
				callback.onComplete();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		@Override
		public void onCompleted() {
			try {
				callback.onComplete();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * 取消同一个Context下所有的请求。<br>
	 *
	 * @param context
	 */
	public synchronized void cancelCalls(final Context context) {
		if (context == null) return;
		if (callMap == null || callMap.size() == 0) return;
		final List<Subscription> calls = callMap.get(context);
		if (calls == null || calls.size() == 0) return;

		for (Subscription call : calls) {
			if (!call.isUnsubscribed()) {
				call.unsubscribe();
			}
		}
		callMap.remove(context);
	}

	/**
	 * 加入当前context请求队列
	 *
	 * @param context
	 * @param call
	 */
	public synchronized void addRunningCall(Context context, Subscription call) {
		if (context == null || call == null || call.isUnsubscribed()) return;
		if (callMap.get(context) == null) callMap.put(context, new ArrayList<Subscription>());
		callMap.get(context).add(call);
	}
}

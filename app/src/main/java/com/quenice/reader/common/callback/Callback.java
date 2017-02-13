package com.quenice.reader.common.callback;

/**
 * 回调接口
 * Created by qiubb on 2017/2/9.
 */
public abstract class Callback<T> {
	public abstract void onSuccess(T data, String msg);

	public abstract void onFailure(int code, String msg);

	public void onComplete() {
	}
}

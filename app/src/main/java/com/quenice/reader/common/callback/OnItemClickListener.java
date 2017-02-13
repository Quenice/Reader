package com.quenice.reader.common.callback;

import android.view.View;

/**
 * recyclerview的item点击监听
 * Created by qiubb on 2017/02/13.
 *
 * @param <T> 绑定的数据的类型
 */
public interface OnItemClickListener<T> {
	/**
	 * 点击事件回调
	 *
	 * @param bindData  当前行绑定的数据
	 * @param itemView  当前item
	 * @param eventView 当前点击的view
	 * @param position  当前点击的位置
	 */
	void onClick(T bindData, View itemView, View eventView, int position);
}

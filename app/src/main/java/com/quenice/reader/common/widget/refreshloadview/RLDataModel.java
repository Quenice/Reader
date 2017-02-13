package com.quenice.reader.common.widget.refreshloadview;

import android.support.annotation.IntDef;

import com.quenice.reader.common.model.Protection;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 加载/刷新model
 * Created by qiubb on 2016/6/29.
 */
public class RLDataModel<DATA> implements Protection {
	//普通内容
	public final static int VIEWTYPE_NORMAL = 0;
	//footer
	public final static int VIEWTYPE_FOOTER = 1;

	/**
	 * footer类型-正常模式，可以点击
	 */
	public final static int FOOTERTYPE_NORMAL = 0;
	/**
	 * footer类型-正在加载
	 */
	public final static int FOOTERTYPE_LOADING = 1;
	/**
	 * footer类型-已加载完全部数据
	 */
	public final static int FOOTERTYPE_NOMOREDATA = 2;
	/**
	 * footer类型-无数据
	 */
	public final static int FOOTERTYPE_NONEDATA = 3;

	private int viewType;
	@FOOTERTYPE
	private int footerType = FOOTERTYPE_NORMAL;
	private DATA data;

	public int getViewType() {
		return viewType;
	}

	public void setViewType(int viewType) {
		this.viewType = viewType;
	}

	public DATA getData() {
		return data;
	}

	public void setData(DATA data) {
		this.data = data;
	}

	@FOOTERTYPE
	public int getFooterType() {
		return footerType;
	}

	public void setFooterType(@FOOTERTYPE int footerType) {
		this.footerType = footerType;
	}

	@IntDef({FOOTERTYPE_NORMAL, FOOTERTYPE_LOADING, FOOTERTYPE_NOMOREDATA, FOOTERTYPE_NONEDATA})
	@Retention(RetentionPolicy.SOURCE)
	public @interface FOOTERTYPE {

	}
}

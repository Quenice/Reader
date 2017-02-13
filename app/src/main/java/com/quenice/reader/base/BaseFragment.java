package com.quenice.reader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Fragment基类
 * Created by qiubb on 2017/2/13.
 */

public abstract class BaseFragment extends Fragment {
	protected View mView;

	protected void initVars() {

	}

	protected void initListeners() {

	}

	protected void initData() {

	}

	/**
	 * content view resource id
	 *
	 * @return
	 */
	protected abstract int getContentView();

	@Nullable
	@Override
	public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		mView = inflater.inflate(getContentView(), container, false);
		try {
			initVars();
			initData();
			initListeners();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mView;
	}
}

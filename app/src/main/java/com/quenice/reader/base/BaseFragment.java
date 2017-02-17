package com.quenice.reader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Fragment基类
 * Created by qiubb on 2017/2/13.
 */

public abstract class BaseFragment extends Fragment {
	protected View mView;
	private Unbinder mUnbinder;

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
		if (butterKnife())
			mUnbinder = ButterKnife.bind(this, mView);
		try {
			initVars();
			initData();
			initListeners();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mView;
	}

	/**
	 * 是否需要用到butterknife
	 *
	 * @return
	 */
	protected boolean butterKnife() {
		return true;
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (mUnbinder != null)
			mUnbinder.unbind();
	}
}

package com.quenice.reader.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.quenice.reader.R;

import butterknife.ButterKnife;

/**
 * Activity 基类
 * Created by qiubb on 2017/2/9.
 */
public abstract class BaseActivity extends AppCompatActivity {
	private boolean hasToolbar;
	private Toolbar mToolbar;

	@Override
	protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(getContentView());
		if (butterKnife())
			ButterKnife.bind(this);
		hasToolbar = hasToolbar();
		if (hasToolbar) {
			mToolbar = (Toolbar) findViewById(R.id.toolbar);
			if (mToolbar != null) {
				mToolbar.setTitle(initTitle());
				setSupportActionBar(mToolbar);
			} else
				hasToolbar = false;
		}

		if (hasToolbar) {
			ActionBar actionBar = getSupportActionBar();
			if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
		}

		initVars();
		initListeners();
		initData();
	}

	/**
	 * 是否需要用到butterknife
	 *
	 * @return
	 */
	protected boolean butterKnife() {
		return true;
	}

	/**
	 * layout resource id
	 *
	 * @return
	 */
	protected abstract int getContentView();

	/**
	 * 是否设置toolbar，如果有个性化需求，可以设置为false，然后自己实现toolbar
	 *
	 * @return
	 */
	protected abstract boolean hasToolbar();

	/**
	 * 获得toolbar
	 *
	 * @return
	 */
	public final Toolbar getToolbar() {
		return mToolbar;
	}

	/**
	 *
	 */
	protected void initVars() {

	}

	protected void initListeners() {

	}

	protected void initData() {

	}


	/**
	 * 设置title
	 *
	 * @return
	 */
	protected int initTitle() {
		return R.string.app_name;
	}
}

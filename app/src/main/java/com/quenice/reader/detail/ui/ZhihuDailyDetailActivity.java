package com.quenice.reader.detail.ui;

import android.annotation.SuppressLint;
import android.support.design.widget.CollapsingToolbarLayout;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.quenice.reader.R;
import com.quenice.reader.base.BaseActivity;
import com.quenice.reader.common.callback.Callback;
import com.quenice.reader.common.http.helper.HttpHelper;
import com.quenice.reader.detail.model.ZhihuDailyDetail;

/**
 * Created by qiubb on 2017/2/13.
 */

public class ZhihuDailyDetailActivity extends BaseActivity {
	private WebView mWebView;
	private long mNewsId;
	private String mNewsTitle;
	private CollapsingToolbarLayout mCollapsingToolbarLayout;

	@Override
	protected int getContentView() {
		return R.layout.activity_zhihudaily_detail;
	}

	@Override
	protected boolean hasToolbar() {
		return false;
	}

	@Override
	protected void initVars() {
		super.initVars();
		mWebView = (WebView) findViewById(R.id.webView);
		mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
		mNewsId = getIntent().getLongExtra("newsId", 0);
		mNewsTitle = getIntent().getStringExtra("newsTitle");
		mCollapsingToolbarLayout.setTitle(mNewsTitle);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void initData() {
		super.initData();
		mWebView.setWebChromeClient(new WebChromeClient() {

		});
		mWebView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		mWebView.getSettings().setDefaultTextEncodingName("UTF-8");
		mWebView.getSettings().setJavaScriptEnabled(true);

		HttpHelper.getInstance().zhihuDailyDetail(this, mNewsId, new Callback<ZhihuDailyDetail>() {
			@Override
			public void onSuccess(ZhihuDailyDetail data, String msg) {
				mWebView.loadUrl(data.getCss().get(0));
				mWebView.loadData(data.getBody(), "text/html; charset=UTF-8", null);
			}

			@Override
			public void onFailure(int code, String msg) {

			}

			@Override
			public void onComplete() {
				super.onComplete();
			}
		});
	}
}

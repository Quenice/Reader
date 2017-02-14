package com.quenice.reader.detail.ui;

import android.annotation.SuppressLint;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.quenice.reader.R;
import com.quenice.reader.base.BaseActivity;
import com.quenice.reader.common.callback.Callback;
import com.quenice.reader.common.http.helper.HttpHelper;
import com.quenice.reader.common.utils.Utils;
import com.quenice.reader.detail.model.ZhihuDailyDetail;
import com.squareup.picasso.Picasso;

import java.util.List;

import static com.quenice.reader.R.id.webView;

/**
 * Created by qiubb on 2017/2/13.
 */

public class ZhihuDailyDetailActivity extends BaseActivity {
	private WebView mWebView;
	private ImageView iv_header;
	private Toolbar toolbar;
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
		mWebView = (WebView) findViewById(webView);
		iv_header = (ImageView) findViewById(R.id.iv_header);
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbarLayout);
		mNewsId = getIntent().getLongExtra("newsId", 0);
		mNewsTitle = getIntent().getStringExtra("newsTitle");
		mCollapsingToolbarLayout.setTitle(mNewsTitle);
	}

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void initData() {
		super.initData();

		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				ZhihuDailyDetailActivity.this.finish();
			}
		});
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
		//缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
		mWebView.getSettings().setBuiltInZoomControls(false);
		//开启DOM storage API功能
		mWebView.getSettings().setDomStorageEnabled(true);
		//开启application Cache功能
		mWebView.getSettings().setAppCacheEnabled(false);
		mWebView.getSettings().setLoadWithOverviewMode(true);
		mWebView.getSettings().setDatabaseEnabled(true);
		mWebView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

		HttpHelper.getInstance().zhihuDailyDetail(this, mNewsId, new Callback<ZhihuDailyDetail>() {
			@Override
			public void onSuccess(ZhihuDailyDetail data, String msg) {
				Picasso.with(ZhihuDailyDetailActivity.this).load(data.getImage()).into(iv_header);
				showDetail(data);
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

	private void showDetail(ZhihuDailyDetail data) {
		List<String> csss = data.getCss();
		List<String> jss = data.getJs();
		String body = data.getBody();
		body = body.replace("<div class=\"headline\">\n\n<div class=\"img-place-holder\"></div>\n\n\n\n</div>", "");
		StringBuilder html = new StringBuilder();
//		html.append("<!DOCTYPE html>\n");
//		html.append("<html lang=\"ZH-CN\" xmlns=\"http://www.w3.org/1999/xhtml\">\n");
//		html.append("<head>\n<meta charset=\"utf-8\" />\n");
		if (!Utils.isEmpty(csss)) {
			for (String css : csss) {
				html.append("<link rel=\"stylesheet\" href=\"");
				html.append(css);
				html.append("\" type=\"text/css\" />\n");
			}
		}
//		html.append("\n</head>\n<body>\n");
		html.append(body);
		if (!Utils.isEmpty(jss)) {
			for (String js : jss) {
				html.append("<script src=\"");
				html.append(js);
				html.append("\" type=\"text/javascript\" charset=\"utf-8\"></script>\n");
			}
		}
//		html.append("</body>\n</html>");
		mWebView.loadDataWithBaseURL("file:///android_asset/", html.toString(), "text/html", "utf-8", "http//:daily.zhihu.com/");
//		mWebView.loadData(html.toString(), "text/html;charset=UTF-8", null);
//		Log.i("aaa", html.toString());
	}
}

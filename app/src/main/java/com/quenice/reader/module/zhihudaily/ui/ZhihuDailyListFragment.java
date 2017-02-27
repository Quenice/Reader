package com.quenice.reader.module.zhihudaily.ui;

import android.content.Intent;
import android.view.View;

import com.quenice.reader.R;
import com.quenice.reader.base.BaseFragment;
import com.quenice.reader.module.zhihudaily.adapter.NewsListAdapter;
import com.quenice.reader.base.main.listener.ScrollToTopListener;
import com.quenice.reader.module.zhihudaily.model.ZhihuDaily;
import com.quenice.reader.common.callback.Callback;
import com.quenice.reader.common.callback.OnItemClickListener;
import com.quenice.reader.common.http.HttpHelper;
import com.quenice.reader.common.utils.Utils;
import com.quenice.reader.common.widget.refreshloadview.RefreshLoadView;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 知乎日报内容fragment
 * Created by qiubb on 2017/2/13.
 */

public class ZhihuDailyListFragment extends BaseFragment implements OnItemClickListener<ZhihuDaily.Story>, ScrollToTopListener {
	private NewsListAdapter mNewListAdapter;
	private RefreshLoadView<ZhihuDaily.Story> mRecyclerView;
	private String newsDate;
	private Calendar newsCalendar;

	public final static synchronized ZhihuDailyListFragment getInstance() {
		return new ZhihuDailyListFragment();
	}

	@Override
	protected int getContentView() {
		return R.layout.fragment_main;
	}

	@Override
	protected void initVars() {
		super.initVars();
		mRecyclerView = (RefreshLoadView<ZhihuDaily.Story>) mView.findViewById(R.id.recyclerView);
	}

	@Override
	protected void initData() {
		super.initData();
		mRecyclerView.setRefreshing(true);
		loadNews(null, new RefreshLoadView.FinishDataCallback<ZhihuDaily.Story>() {
			@Override
			public void onFinish(List<ZhihuDaily.Story> data, boolean noMoreData) {
				mNewListAdapter = new NewsListAdapter(getActivity(), data);
				mNewListAdapter.setOnItemClickListener(ZhihuDailyListFragment.this);
				mRecyclerView.setAdapter(mNewListAdapter);
				mRecyclerView.init(noMoreData);
				mRecyclerView.setRefreshing(false);
			}
		});
	}

	@Override
	protected void initListeners() {
		super.initListeners();
		mRecyclerView.setRefreshLoadListener(new RefreshLoadView.SimpleRefreshLoadListener<ZhihuDaily.Story>() {
			@Override
			public void onRefresh(RefreshLoadView.FinishDataCallback<ZhihuDaily.Story> callback) {
				super.onRefresh(callback);
				loadNews(null, callback);
			}

			@Override
			public void onLoad(RefreshLoadView.FinishDataCallback<ZhihuDaily.Story> callback) {
				super.onLoad(callback);
				loadNews(newsDate, callback);
			}
		});
		mRecyclerView.setMode(RefreshLoadView.MODE_BOTH);
	}

	private void loadNews(String date, final RefreshLoadView.FinishDataCallback<ZhihuDaily.Story> callback) {
		if (Utils.isEmpty(date))
			HttpHelper.zhihuDaily().listLatest(getActivity(), new Callback<ZhihuDaily>() {
				List<ZhihuDaily.Story> list = null;

				@Override
				public void onSuccess(ZhihuDaily data, String msg) {
					list = data == null ? null : data.getStories();
				}

				@Override
				public void onFailure(int code, String msg) {
					list = null;
				}

				@Override
				public void onComplete() {
					super.onComplete();
					callback.onFinish(list, false);
					newsDate = getBeforeDay();
				}
			});
		else
			HttpHelper.zhihuDaily().listBefore(getActivity(), newsDate, new Callback<ZhihuDaily>() {
				List<ZhihuDaily.Story> list = null;

				@Override
				public void onSuccess(ZhihuDaily data, String msg) {
					list = data == null ? null : data.getStories();
				}

				@Override
				public void onFailure(int code, String msg) {
					list = null;
				}

				@Override
				public void onComplete() {
					super.onComplete();
					callback.onFinish(list, false);
					newsDate = getBeforeDay();
				}
			});
	}


	/**
	 * 获得上一天
	 *
	 * @return
	 */
	private String getBeforeDay() {
		if (newsCalendar == null) {
			newsCalendar = new GregorianCalendar();
		} else {
			newsCalendar.add(Calendar.DAY_OF_MONTH, -1);
		}
		Date d = newsCalendar.getTime();
		return getString(R.string.format_ymd, d);
	}

	@Override
	public void onClick(ZhihuDaily.Story bindData, View itemView, View eventView, int position) {
		Intent intent = new Intent(getActivity(), ZhihuDailyDetailActivity.class);
		intent.putExtra("newsId", bindData.getId());
		intent.putExtra("newsTitle", bindData.getTitle());
		startActivity(intent);
	}

	@Override
	public void scrollToTop() {
		if (mRecyclerView != null && mRecyclerView.getRecyclerView() != null && mRecyclerView.getRecyclerView().getChildCount() > 0)
			mRecyclerView.getRecyclerView().smoothScrollToPosition(0);
	}
}

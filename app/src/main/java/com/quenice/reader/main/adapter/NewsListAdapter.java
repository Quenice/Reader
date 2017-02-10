package com.quenice.reader.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quenice.reader.R;
import com.quenice.reader.common.http.model.ZhihuDaily;
import com.quenice.reader.common.utils.Utils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qiubb on 2017/2/10.
 */

public class NewsListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
	private Context mContext;
	private List<ZhihuDaily.Story> mData;

	public NewsListAdapter(Context context, List<ZhihuDaily.Story> data) {
		this.mContext = context;
		this.mData = data == null ? new ArrayList<ZhihuDaily.Story>() : data;

	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false));
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((ContentViewHolder) holder).onBind(mContext, mData.get(position));
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	private static class ContentViewHolder extends RecyclerView.ViewHolder {
		ImageView iv_image;
		TextView tv_title;

		public ContentViewHolder(View itemView) {
			super(itemView);
			iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
			tv_title = (TextView) itemView.findViewById(R.id.tv_title);
		}

		void onBind(Context context, ZhihuDaily.Story content) {
			if (!Utils.isEmpty(content.getImages())) {
				Picasso.with(context).load(content.getImages().get(0)).into(iv_image);
			}
			tv_title.setText(content.getTitle());
		}
	}
}

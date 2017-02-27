package com.quenice.reader.module.zhihudaily.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.quenice.reader.R;
import com.quenice.reader.module.zhihudaily.model.ZhihuDaily;
import com.quenice.reader.common.callback.OnItemClickListener;
import com.quenice.reader.common.utils.Utils;
import com.quenice.reader.common.widget.refreshloadview.RLDataModel;
import com.quenice.reader.common.widget.refreshloadview.RLRecyclerAdapter;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by qiubb on 2017/2/10.
 */

public class NewsListAdapter extends RLRecyclerAdapter<ZhihuDaily.Story> {

	private OnItemClickListener<ZhihuDaily.Story> onItemClickListener;

	public NewsListAdapter(Context context, List<ZhihuDaily.Story> data) {
		super(context, data);
	}

	@Override
	protected RecyclerView.ViewHolder onCreateCustomViewHolder(ViewGroup parent, int viewType) {
		return new ContentViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_news, parent, false));
	}

	@Override
	protected void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position) {
		RLDataModel<ZhihuDaily.Story> m = getItemData(position);
		((ContentViewHolder) holder).onBind(mContext, m == null ? null : m.getData(), onItemClickListener);
	}

	@Override
	public int getItemCount() {
		return mData.size();
	}

	public void setOnItemClickListener(OnItemClickListener<ZhihuDaily.Story> onItemClickListener) {
		this.onItemClickListener = onItemClickListener;
	}

	private static class ContentViewHolder extends RecyclerView.ViewHolder {
		ImageView iv_image;
		TextView tv_title;

		public ContentViewHolder(View itemView) {
			super(itemView);
			iv_image = (ImageView) itemView.findViewById(R.id.iv_image);
			tv_title = (TextView) itemView.findViewById(R.id.tv_title);
		}

		void onBind(Context context, final ZhihuDaily.Story content, final OnItemClickListener<ZhihuDaily.Story> onItemClickListener) {
			if (!Utils.isEmpty(content.getImages())) {
				Picasso.with(context).load(content.getImages().get(0)).into(iv_image);
			}
			tv_title.setText(content.getTitle());
			if (onItemClickListener != null)
				itemView.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						onItemClickListener.onClick(content, itemView, itemView, 0);
					}
				});
		}
	}
}

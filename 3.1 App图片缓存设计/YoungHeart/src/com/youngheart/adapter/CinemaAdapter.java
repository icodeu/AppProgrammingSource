package com.youngheart.adapter;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.youngheart.R;
import com.youngheart.base.AppBaseActivity;
import com.youngheart.entity.CinemaBean;

public class CinemaAdapter extends BaseAdapter {
	private final ArrayList<CinemaBean> cinemaList;
	private final AppBaseActivity context;
	private DisplayImageOptions options;

	public CinemaAdapter(ArrayList<CinemaBean> cinemaList,
			AppBaseActivity context) {
		this.cinemaList = cinemaList;
		this.context = context;

		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.ic_launcher)
				.showImageForEmptyUri(R.drawable.ic_launcher)
				.cacheInMemory()
				.cacheOnDisc()
				.build();
	}

	public int getCount() {
		return cinemaList.size();
	}

	public CinemaBean getItem(final int position) {
		return cinemaList.get(position);
	}

	public long getItemId(final int position) {

		return position;
	}

	public View getView(final int position, View convertView,
			final ViewGroup parent) {
		final Holder holder;
		if (convertView == null) {
			holder = new Holder();
			convertView = context.getLayoutInflater().inflate(
					R.layout.item_cinemalist, null);
			holder.tvCinemaName = (TextView) convertView
					.findViewById(R.id.tvCinemaName);
			holder.tvCinemaId = (TextView) convertView
					.findViewById(R.id.tvCinemaId);
			holder.imgPhoto = (ImageView) convertView
					.findViewById(R.id.imgPhoto);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		CinemaBean cinema = cinemaList.get(position);
		holder.tvCinemaName.setText(cinema.getCinemaName());
		holder.tvCinemaId.setText(cinema.getCinemaId());

		context.imageLoader.displayImage(cinemaList.get(position)
				.getCinemaPhotoUrl(), holder.imgPhoto);

		return convertView;
	}

	class Holder {
		TextView tvCinemaName;
		TextView tvCinemaId;
		ImageView imgPhoto;
	}
}

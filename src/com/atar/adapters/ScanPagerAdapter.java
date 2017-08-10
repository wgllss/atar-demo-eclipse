package com.atar.adapters;

import java.util.List;

import uk.co.senab.photoview.PhotoView;
import android.activity.CommonActivity;
import android.adapter.CommonPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

public class ScanPagerAdapter extends CommonPagerAdapter<String> {

	private int defaultImg;

	public ScanPagerAdapter(List<String> list, int defaultImg) {
		super(list);
		this.defaultImg = defaultImg;
	}

	@Override
	public View instantiateItem(ViewGroup container, int position) {
		PhotoView photoView = new PhotoView(container.getContext());
		if (getList() != null && getList().size() > 0) {
			photoView.setMinimumScale(0.2f);
			String imgUrl = getList().get(position);
			// ShowLog.i("缩放图片地址--->", imgUrl);
			// if (!AppConfigSetting.getInstance().getBoolean(GlobeSettings.SHOW_IMAGE_KEY, true)) {// 无图模式
			// photoView.setImageResource(defaultImg);
			// } else {
			((CommonActivity) container.getContext()).LoadImageView(imgUrl, photoView, defaultImg);
			// }
			container.addView(photoView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		}
		return photoView;
	}
}

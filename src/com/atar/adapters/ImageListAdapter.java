package com.atar.adapters;

import java.util.List;

import android.activity.CommonActivity;
import android.adapter.CommonPagerAdapter;
import android.appconfig.AppConfigSetting;
import android.enums.SkinMode;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;

import com.atar.beans.WonderfulTopicBean;

public class ImageListAdapter extends CommonPagerAdapter<WonderfulTopicBean> {

	public ImageListAdapter(List<?> list) {
		super(list);
	}

	@Override
	public View instantiateItem(final ViewGroup container, final int position) {
		ImageView imageView = new ImageView(container.getContext());
		if (getList() != null && getList().size() > 0) {
			WonderfulTopicBean info = getList().get(position);
			int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
			imageView.setScaleType(ScaleType.FIT_XY);
			if (info != null && info.getImageUrl() != null) {
				((CommonActivity) container.getContext()).LoadImageView(info.getImageUrl(), imageView, 0, 0);
			} else {
				// imageView.setImageResource(GlobeSettings.defaultLoadImg[skinType]);
			}
			container.addView(imageView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		}
		return imageView;
	}
}

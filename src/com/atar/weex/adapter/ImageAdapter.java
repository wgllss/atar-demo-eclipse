package com.atar.weex.adapter;

import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.common.WXImageStrategy;
import com.taobao.weex.dom.WXImageQuality;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :
 * @createTime: 17/3/3
 * @version:
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */
public class ImageAdapter implements IWXImgLoaderAdapter {

	public ImageLoader imageLoader = ImageLoader.getInstance();

	@Override
	public void setImage(String imgUrl, ImageView mImageView, WXImageQuality quality, WXImageStrategy strategy) {
		int defaultImg = 0;// GlobeSettings.defaultLoadImg9068[AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0)];
		if (imgUrl == null || "".equals(imgUrl)) {
			mImageView.setImageResource(defaultImg);
			return;
		}
		imgUrl = imgUrl.replace("..", "");// 去掉这两个点"../img/2016/06/27/uz0y4gd5egl7.jpg"
		if (imgUrl.contains("?")) {
			imgUrl = imgUrl.substring(0, imgUrl.lastIndexOf("?"));
		}
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(defaultImg).showImageForEmptyUri(defaultImg).showImageOnFail(defaultImg).cacheInMemory().cacheOnDisc()
				.displayer(new RoundedBitmapDisplayer(0)).build();
		imageLoader.displayImage(imgUrl, mImageView, options);
	}
}
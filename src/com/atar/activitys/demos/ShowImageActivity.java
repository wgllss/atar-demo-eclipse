/**
 * 
 */
package com.atar.activitys.demos;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.os.Bundle;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.utils.FileUtils;
import android.view.View;
import android.widget.CommonToast;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.atar.adapters.ScanPagerAdapter;
import com.atar.globe.GlobeSettings;
import com.atar.utils.IntentUtil;
import com.atar.widgets.HackyViewPager;

/**
 *****************************************************************************************************************************************************************************
 * 显示图片
 * @author :Atar
 * @createTime:2014-7-23下午6:05:01
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ShowImageActivity extends AtarCommonActivity implements OnPageChangeListener {

	private static final String IMAGE_LIST_KEY = "IMAGE_LIST_KEY";
	private static final String DEFAULT_POSITION_KEY = "DEFAULT_POSITION_KEY";
	private static final String ISLOCKED_ARG = "ISLOCKED_KEY";
	private HackyViewPager viewPager;
	private List<String> imgList;
	private int defaultPosition;
	private ScanPagerAdapter mScanPagerAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addContentView(R.layout.activity_show_img);
		if (savedInstanceState != null && viewPager != null) {
			boolean isLocked = savedInstanceState.getBoolean(ISLOCKED_ARG, false);
			viewPager.setLocked(isLocked);
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		if (viewPager != null && viewPager instanceof HackyViewPager) {
			outState.putBoolean(ISLOCKED_ARG, viewPager.isLocked());
		}
		super.onSaveInstanceState(outState);
	}

	@Override
	protected void initControl() {
		viewPager = (HackyViewPager) findViewById(R.id.view_pager);
	}

	@Override
	protected void bindEvent() {
		viewPager.setOnPageChangeListener(this);
	}

	@Override
	protected void initValue() {
		setActivityTitle("显示图片");
		setLoadingViewGone();
		setTopRightText("保存");
		imgList = getIntent().getStringArrayListExtra(IMAGE_LIST_KEY);
		if (imgList != null && imgList.size() > 0) {
			defaultPosition = getIntent().getIntExtra(DEFAULT_POSITION_KEY, 0);
			mScanPagerAdapter = new ScanPagerAdapter(imgList, 0);
			viewPager.setAdapter(mScanPagerAdapter);
			viewPager.setCurrentItem(defaultPosition);
		}
		if (defaultPosition == 0) {
			setOnDrawerBackEnabled(true);
		} else {
			setOnDrawerBackEnabled(false);
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.txt_common_top_right:// 保存图片
			if (imgList != null && imgList.size() > 0 && viewPager != null) {
				String currentImgUrl = imgList.get(viewPager.getCurrentItem());
				if (imageLoader != null && imageLoader.getDiscCache() != null && imageLoader.getDiscCache().get(currentImgUrl) != null
						&& imageLoader.getDiscCache().get(currentImgUrl).getPath() != null && imageLoader.getDiscCache().get(currentImgUrl).getPath().length() > 0
						&& FileUtils.exists(imageLoader.getDiscCache().get(currentImgUrl).getPath())) {
					String fileName = currentImgUrl.substring(currentImgUrl.lastIndexOf("/") + 1, currentImgUrl.length());
					final String savaPath = GlobeSettings.IMG_SAVE_PATH + fileName;
					if (!FileUtils.exists(savaPath)) {
						FileUtils.createDir(GlobeSettings.IMG_SAVE_PATH);
						FileUtils.copyFile(imageLoader.getDiscCache().get(currentImgUrl).getPath(), savaPath);
						/* 更新相册 */
						MediaScannerConnection.scanFile(ShowImageActivity.this, new String[] { savaPath }, new String[] { "image/*" }, null);
						CommonToast.show("已经保存到:" + savaPath);
					} else {
						CommonToast.show("已经存在:" + savaPath);
					}
				}
			}
			break;
		}
	}

	/**
	 * 跳转到图片浏览器
	 * @author :Atar
	 * @createTime:2014-10-14上午9:37:28
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @param imgList
	 * @param position
	 * @description:
	 */
	public static void startShowImage(Context context, ArrayList<String> imgList, int position) {
		Intent intent = new Intent(context, ShowImageActivity.class);
		intent.putStringArrayListExtra(IMAGE_LIST_KEY, imgList);
		intent.putExtra(DEFAULT_POSITION_KEY, position);
		IntentUtil.startOtherActivity(context, intent);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		if (arg0 == 0) {
			setOnDrawerBackEnabled(true);
		} else {
			setOnDrawerBackEnabled(false);
		}
	}
}

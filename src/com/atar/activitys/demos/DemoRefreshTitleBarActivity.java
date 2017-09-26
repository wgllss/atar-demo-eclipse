/**
 * 
 */
package com.atar.activitys.demos;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.common.CommonHandler;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Message;
import android.skin.SkinUtils;
import android.util.Log;
import android.utils.ColorUtil;
import android.utils.ScreenUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollableHelper;

import com.atar.activitys.AtarRefreshListViewActivity;
import com.atar.activitys.R;
import com.atar.adapters.MainDemoAdapter;
import com.atar.beans.MenuItemBean;
import com.atar.enums.EnumMsgWhat;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-24下午5:22:39
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("InflateParams")
public class DemoRefreshTitleBarActivity extends AtarRefreshListViewActivity {
	private List<MenuItemBean> list = new ArrayList<MenuItemBean>();
	private MainDemoAdapter mMainDemoAdapter = new MainDemoAdapter(list);
	private ImplOnScrollListener mImplOnScrollListener;

	private View itemHeaderAdView; // 从ListView头部View
	private int headerViewHeight; // 广告视图的高度
	private int headerViewTopSpace; // headerView距离顶部的距离
	private int titleViewHeight = 45; // 标题栏的高度

	@Override
	protected int getResLayoutID() {
		return R.layout.activity_demo_refresh_titlebar;
	}

	@Override
	protected void addContentView(int layoutResId) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		commonContentBg = inflater.inflate(layoutResId, null);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		lp.gravity = Gravity.TOP | Gravity.LEFT;
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT && topTitleBarBg != null && isFlagTranslucentStatus) {
			int statusBarHeight = ScreenUtils.getStatusBarHeight(this);
			int layoutHeight = (statusBarHeight > 0 ? statusBarHeight : 36) + (int) ScreenUtils.getIntToDip(45);
			topTitleBarBg.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, layoutHeight));
			topTitleBarBg.setPadding(0, statusBarHeight, 0, 0);
			// lp.topMargin = layoutHeight;
		} else {
			lp.topMargin = (int) ScreenUtils.getIntToDip(45);
		}
		addContentView(commonContentBg, lp);
		topTitleBarBg.bringToFront();
		init();
	}

	@Override
	protected void applyScrollListener() {
		if (listView != null && mImplOnScrollListener != null) {
			listView.setOnScrollListener(mImplOnScrollListener);
		}
	}

	@Override
	protected void initControl() {
		super.initControl();
		View view = getLayoutInflater().inflate(R.layout.listview_header, null);
		getRefreshView().addHeaderView(view);

		mImplOnScrollListener = new ImplOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling);
		getRefreshView().setOnScrollListener(mImplOnScrollListener);
		ImageView img_header = (ImageView) view.findViewById(R.id.img_header);
		LoadImageView("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=3350331176,1143185189&fm=116&gp=0.jpg", img_header, 0);
	}

	@Override
	protected void initValue() {
		super.initValue();
		setActivityTitle(getIntent().getStringExtra(DemoRefreshActivity.TITLE_KEY));
		for (int i = 0; i < 50; i++) {
			list.add(new MenuItemBean("0", "refresh-item-" + i));
		}
		mMainDemoAdapter.notifyDataSetChanged();
		setAdapter(mMainDemoAdapter);
	}

	@Override
	public void onHandlerData(Message msg) {
		super.onHandlerData(msg);
		switch (msg.what) {
		case EnumMsgWhat.LOAD_FROM_SQL:
			setRefreshing();
			break;
		case EnumMsgWhat.LOAD_FROM_SQL_COMPLETE:
			break;
		case EnumMsgWhat.REFRESH_PULL_DOWN:
		case EnumMsgWhat.REFRESH_PULL_UP:
		case EnumMsgWhat.REFRESH_HANDLER:
			CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {

				@Override
				public void run() {
					onStopRefresh();
				}
			}, 1000);
			break;
		}
	}

	@SuppressLint("NewApi")
	private void handleTitleBarColorEvaluate(int firstVisibleItem) {
		float fraction;
		if (headerViewTopSpace > 0) {
			fraction = 1f - headerViewTopSpace * 1f / 60;
			fraction = fraction < 0 ? 0f : fraction;
			if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
				topTitleBarBg.setAlpha(fraction);
			} else {
				AlphaAnimation inAlphaAnimation = new AlphaAnimation(fraction, 1f);
				topTitleBarBg.setAnimation(inAlphaAnimation);
			}
			return;
		}
		float space = Math.abs(headerViewTopSpace) * 1f;
		fraction = space / (headerViewHeight - titleViewHeight);
		fraction = fraction < 0 ? 0f : fraction;
		fraction = fraction > 1f ? 1f : fraction;
		fraction = firstVisibleItem > 1 ? 1f : fraction;
		if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
			topTitleBarBg.setAlpha(1f);
			topTitleBarBg.setAlpha(fraction);
		} else {
			AlphaAnimation inAlphaAnimation = new AlphaAnimation(fraction, 1f);
			topTitleBarBg.setAnimation(inAlphaAnimation);
		}
		topTitleBarBg.setBackgroundColor(ColorUtil.evaluate(fraction, SkinUtils.getColor(this, R.string.transparent0), SkinUtils.getColor(this, R.string.common_top_title_bar_bg_day)));
	}

	public class ImplOnScrollListener extends PauseOnScrollListener {

		public ImplOnScrollListener(ImageLoader imageLoader, boolean pauseOnScroll, boolean pauseOnFling) {
			super(imageLoader, pauseOnScroll, pauseOnFling);
		}

		@Override
		public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
			super.onScroll(view, firstVisibleItem, visibleItemCount, totalItemCount);
			if (itemHeaderAdView == null) {
				itemHeaderAdView = getRefreshView().getChildAt(1 - firstVisibleItem);
			}
			if (itemHeaderAdView != null) {
				headerViewTopSpace = (int) ScreenUtils.pxToDpCeilInt(DemoRefreshTitleBarActivity.this, itemHeaderAdView.getTop());
				headerViewHeight = (int) ScreenUtils.pxToDpCeilInt(DemoRefreshTitleBarActivity.this, itemHeaderAdView.getHeight());
			}
			Log.i("SingleListViewActivity", "headerViewTopSpace:-->" + headerViewTopSpace + "--headerViewHeight-->" + headerViewHeight + "--firstVisibleItem-->" + firstVisibleItem);
			if (ScrollableHelper.isAdapterViewTop(view)) {
				topTitleBarBg.setBackgroundColor(SkinUtils.getColor(DemoRefreshTitleBarActivity.this, R.string.transparent0));
			} else {
				topTitleBarBg.setBackgroundColor(SkinUtils.getColor(DemoRefreshTitleBarActivity.this, R.string.common_top_title_bar_bg_day));
			}
			handleTitleBarColorEvaluate(firstVisibleItem);
		}
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		if (mMainDemoAdapter != null) {
			mMainDemoAdapter.setSkinType(skinType);
		}
	}
}

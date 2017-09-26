/**
 * 
 */
package com.atar.activitys.demos;

import android.annotation.SuppressLint;
import android.common.CommonHandler;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.skin.SkinUtils;
import android.utils.ScreenUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollableHelper.ScrollableContainer;
import android.widget.ScrollableLayout;
import android.widget.ScrollableLayout.ScrollLayoutListener;

import com.atar.activitys.AtarCommonTabActivity;
import com.atar.activitys.R;
import com.atar.fragment.demos.WonderTopicFragment;
import com.atar.widgets.refresh.PullToRefreshScrollableLayout;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-29下午1:56:38
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshViewPagerTabTopActivity extends AtarCommonTabActivity<RelativeLayout> implements OnRefreshListener<ScrollableLayout>, ScrollLayoutListener {

	private PullToRefreshScrollableLayout pullToRefreshScrollableLayout;

	protected int getResLayoutID() {
		return R.layout.activity_demo_refresh_viewpage_tab_top;
	}

	@Override
	protected void initControl() {
		super.initControl();
		pullToRefreshScrollableLayout = (PullToRefreshScrollableLayout) findViewById(R.id.pulltorefresh_scrollable_layout);
	}

	@Override
	protected void bindEvent() {
		super.bindEvent();
		pullToRefreshScrollableLayout.setOnRefreshListener(this);
		pullToRefreshScrollableLayout.setMode(Mode.PULL_FROM_START);
		pullToRefreshScrollableLayout.getRefreshableView().getHelper().setCurrentScrollableContainer((ScrollableContainer) getFragmentList().get(0));
		pullToRefreshScrollableLayout.getRefreshableView().setScrollLayoutListener(this);
	}

	@Override
	protected void initValue() {
		super.initValue();
		setActivityTitle(getIntent().getStringExtra(DemoRefreshActivity.TITLE_KEY));
		setTextTab(SkinUtils.getStringArray(this, R.string.viewpager_demo_tabs), false, true);
		setActivityTitle("比赛");
		setOnDrawerBackEnabled(false);
		setLoadingViewGone();
		setTopRightViewVisible();
		clearViewPager();
		addFragmentToList(WonderTopicFragment.newInstance());
		addFragmentToList(WonderTopicFragment.newInstance());
		addFragmentToList(WonderTopicFragment.newInstance());
		setViewPagerAdapter();
	}

	@Override
	public void onPageSelected(int position) {
		if (pullToRefreshScrollableLayout.getRefreshableView() != null) {
			pullToRefreshScrollableLayout.getRefreshableView().getHelper().setCurrentScrollableContainer(((ScrollableContainer) getFragmentList().get(position)));
		}
		setOnDrawerBackEnabled(position == 0);
	}

	@Override
	protected void addContentView(int layoutResId) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		commonContentBg = inflater.inflate(layoutResId, null);
		FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
		lp.gravity = Gravity.TOP | Gravity.LEFT;
		addContentView(commonContentBg, lp);
		// rel_core = (RelativeLayout) findViewById(R.id.rel_core);
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			int statusBarHeight = ScreenUtils.getStatusBarHeight(this) > 0 ? ScreenUtils.getStatusBarHeight(this) : 36;
			int layoutHeight = statusBarHeight + (int) ScreenUtils.getIntToDip(45);
			topTitleBarBg.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, layoutHeight));
			topTitleBarBg.setPadding(0, statusBarHeight, 0, 0);
			setTitleBarGone();
			// rel_core.setPadding(0, statusBarHeight, 0, 0);
		} else {
			topTitleBarBg.bringToFront();
			topTitleBarBg.setVisibility(View.INVISIBLE);
		}
		init();
	}

	private View itemHeaderAdView; // tab以上头部布局
	private int headerViewHeight; // tab以上头部高度
	private int titleViewHeight = 45; // 标题栏的高度
	private int stickyOffset = 0;
	private int statusBarHeight = 0;

	@SuppressLint("NewApi")
	@Override
	public void onScrollLayout(ScrollableLayout view, boolean isSmoothTop, int scrollDistence) {
		if (itemHeaderAdView == null) {
			itemHeaderAdView = findViewById(R.id.txt_top);
			if (pullToRefreshScrollableLayout != null && pullToRefreshScrollableLayout.getRefreshableView() != null && topTitleBarBg != null) {
				if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
					statusBarHeight = ScreenUtils.getStatusBarHeight(this) > 0 ? ScreenUtils.getStatusBarHeight(this) : 36;
				}
				stickyOffset = topTitleBarBg.getHeight() - statusBarHeight;
				pullToRefreshScrollableLayout.getRefreshableView().setStickyOffset(stickyOffset);
			}
			headerViewHeight = (int) ScreenUtils.pxToDpCeilInt(this, itemHeaderAdView.getHeight() - stickyOffset);

			topTitleBarBg.setVisibility(View.VISIBLE);
			topTitleBarBg.bringToFront();
			// topTitleBarBg.setBackgroundColor(getResources().getColor(R.color.transparent0));
		}

		scrollDistence = scrollDistence > (itemHeaderAdView.getHeight() - stickyOffset - statusBarHeight) ? (itemHeaderAdView.getHeight() - stickyOffset - statusBarHeight) : scrollDistence;
		int headerViewTopSpace = (int) ScreenUtils.pxToDpCeilInt(this, -scrollDistence);
		// topTitleBarBg.setVisibility(View.VISIBLE);
		// topTitleBarBg.bringToFront();

		if (isSmoothTop) {
			// topTitleBarBg.setBackgroundColor(getResources().getColor(R.color.transparent0));
		} else {
			// topTitleBarBg.setBackgroundColor(getResources().getColor(R.color.green));
		}
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
		if (VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB) {
			topTitleBarBg.setAlpha(fraction);
		} else {
			AlphaAnimation inAlphaAnimation = new AlphaAnimation(fraction, 1f);
			topTitleBarBg.setAnimation(inAlphaAnimation);
		}
		// int endColor = getCurrentSkinType() == SkinMode.DAY_MODE ? R.color.common_top_title_bar_bg_day : R.color.common_buttom_bg_night;
		// topTitleBarBg.setBackgroundColor(ColorUtil.getNewColorByStartEndColor(this, fraction, R.color.transparent0, endColor));
	}

	@Override
	public void onRefresh(PullToRefreshBase<ScrollableLayout> refreshView) {
		CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {
			@Override
			public void run() {
				pullToRefreshScrollableLayout.onRefreshComplete();
			}
		}, 1000);
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);

		// if (pullToRefreshScrollableLayout != null) {
		// if (pullToRefreshScrollableLayout.getHeaderLayout() != null && pullToRefreshScrollableLayout.getHeaderLayout().getHeaderText() != null) {
		// pullToRefreshScrollableLayout.getHeaderLayout().setHeaderTextColor(getResources().getColor(R.color.black));
		// }
		// if (pullToRefreshScrollableLayout.getFooterLayout() != null && pullToRefreshScrollableLayout.getFooterLayout().getHeaderText() != null) {
		// pullToRefreshScrollableLayout.getFooterLayout().setHeaderTextColor(getResources().getColor(R.color.black));
		// }
		// if (pullToRefreshScrollableLayout.getHeaderLayout() != null && pullToRefreshScrollableLayout.getHeaderLayout().getSubHeaderText() != null) {
		// pullToRefreshScrollableLayout.getHeaderLayout().setSubHeaderTextColor(getResources().getColor(R.color.black));
		// }
		// if (pullToRefreshScrollableLayout.getFooterLayout() != null && pullToRefreshScrollableLayout.getFooterLayout().getSubHeaderText() != null) {
		// pullToRefreshScrollableLayout.getFooterLayout().setSubHeaderTextColor(getResources().getColor(R.color.black));
		// }
		// if (pullToRefreshScrollableLayout.getHeaderLayout() != null) {
		// pullToRefreshScrollableLayout.getHeaderLayout().setRefreshBgColor(getResources().getColor(R.color.common_txt_hint_color_day));
		// }
		// if (pullToRefreshScrollableLayout.getFooterLayout() != null) {
		// pullToRefreshScrollableLayout.getFooterLayout().setRefreshBgColor(getResources().getColor(R.color.common_txt_hint_color_day));
		// }
		// pullToRefreshScrollableLayout.setBackgroundColor(getResources().getColor(R.color.white));
		// }
		// if (topTitleBarBg != null) {
		// topTitleBarBg.setBackgroundColor(getResources().getColor(R.color.green));
		// }
	}
}

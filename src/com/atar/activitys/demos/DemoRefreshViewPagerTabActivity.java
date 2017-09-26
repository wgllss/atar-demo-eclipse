/**
 * 
 */
package com.atar.activitys.demos;

import android.skin.SkinUtils;
import android.widget.RelativeLayout;
import android.widget.ScrollableHelper.ScrollableContainer;
import android.widget.ScrollableLayout;

import com.atar.activitys.AtarCommonTabActivity;
import com.atar.activitys.R;
import com.atar.fragment.demos.WonderTopicFragment;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-24上午10:17:46
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshViewPagerTabActivity extends AtarCommonTabActivity<RelativeLayout> {

	private ScrollableLayout mScrollLayout;

	protected int getResLayoutID() {
		return R.layout.activity_demo_refresh_viewpager;
	}

	@Override
	protected void initControl() {
		super.initControl();
		mScrollLayout = (ScrollableLayout) findViewById(R.id.scrollableLayout);
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

		mScrollLayout.getHelper().setCurrentScrollableContainer((ScrollableContainer) getFragmentList().get(0));
	}

	@Override
	public void onPageSelected(int position) {
		if (mScrollLayout != null) {
			mScrollLayout.getHelper().setCurrentScrollableContainer(((ScrollableContainer) getFragmentList().get(position)));
		}
		setOnDrawerBackEnabled(position == 0);
	}
}

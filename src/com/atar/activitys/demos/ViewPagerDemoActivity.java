/**
 * 
 */
package com.atar.activitys.demos;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.atar.activitys.AtarCommonTabActivity;
import com.atar.activitys.R;
import com.atar.fragments.WonderTopicFragment;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-10下午5:12:28
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ViewPagerDemoActivity extends AtarCommonTabActivity<RelativeLayout> {
	private int[] txtTabResID = { R.string.txt_week_contest, R.string.txt_month_contest, R.string.txt_history_contest };

	@Override
	protected void onCreate(Bundle bundle) {
		setIsExtendsTaogubaCommonTab(true);
		super.onCreate(bundle);
		setOnDrawerBackEnabled(false);
	}

	@Override
	protected void initValue() {
		super.initValue();
		setTextTab(txtTabResID, false, true);
		setActivityTitle("比赛");
		setLoadingViewGone();
		setTopRightViewVisible();
		clearViewPager();
		addFragmentToList(WonderTopicFragment.newInstance());
		addFragmentToList(WonderTopicFragment.newInstance());
		addFragmentToList(WonderTopicFragment.newInstance());
		setViewPagerAdapter();
	}
}

/**
 * 
 */
package com.atar.activitys.demos;

import com.atar.activitys.htmls.AtarCommonWebViewPagerActivity;
import com.atar.config.AppConfigJson;
import com.atar.config.HtmlsViewPagerJson;
import com.atar.config.TabMenuItemBean;
import com.atar.fragment.demos.DemoRefreshDragSortListViewFragment;
import com.atar.fragment.demos.DemoRefreshExpandableListViewFragment;
import com.atar.fragment.demos.DemoRefreshGridViewFragment;
import com.atar.fragment.demos.DemoRefreshListViewFragment;
import com.atar.fragment.demos.DemoRefreshPinnedSectionListViewFragment;
import com.atar.fragment.demos.DemoRefreshQuickReturnListViewFragment;
import com.atar.fragment.demos.DemoRefreshScrollViewFragment;
import com.atar.fragment.demos.DemoRefreshWebViewFragment;
import com.google.gson.Gson;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-29下午3:44:28
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshInFragmentActivity extends AtarCommonWebViewPagerActivity {

	@Override
	protected void initValue() {
		super.initValue();
		Gson gson = new Gson();
		String andriodAppConfig = "{\"versionName\":\"5.34\",\"isReplace\":true,\"CommunityActivityJson\":{\"TITLE\":\"今日推荐\",\"showDividerLine\":false,\"IndicatorColor\":\"#1191f6,#1191f6\",\"underlineColor\":\"#00000000,#00000000\",\"shouldExpand\":false,\"listFragment\":[{\"ID\":0,\"menuName\":\"listView\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false},{\"ID\":1,\"menuName\":\"gridview\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false},{\"ID\":2,\"menuName\":\"scrollview\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false},{\"ID\":3,\"menuName\":\"expandablelist\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false},{\"ID\":4,\"menuName\":\"dragsort\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false},{\"ID\":5,\"menuName\":\"listSection\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false},{\"ID\":6,\"menuIcon\":\"assets://images/kxian.png\",\"menuName\":\"quickReturn\",\"menuNameTextSize\":14,\"orientation\":\"1\",\"isShowIcon\":false,\"menuIconWidthAndHeight\":25,\"infoNum\":10,\"infoNumColor\":\"#FFFFFF\",\"infoNumMarginLeft\":10},{\"ID\":9,\"menuIcon\":\"assets://images/zhibo.png\",\"menuName\":\"webview\",\"menuNameTextSize\":14,\"orientation\":\"1\",\"isShowIcon\":false,\"menuIconWidthAndHeight\":25,\"infoNum\":0,\"infoNumColor\":\"#FFFFFF\",\"huo_flag_icon_url\":\"assets://images/huo_flag.png\",\"huo_flag_marginLeft\":15,\"huo_flag_marginTop\":0}]}}";// AppConfigModel.getInstance().getString(AppConfigUtils.ANDRIOD_APP_CONFIG_HOME_KEY,
																																																																																																																																																																																																																																																																																																																																																																																					// AppConfigUtils.getDefaultSetting());
		AppConfigJson mAppConfigJson = null;
		try {
			mAppConfigJson = gson.fromJson(andriodAppConfig, AppConfigJson.class);
		} catch (Exception e) {

		}
		if (mAppConfigJson != null) {
			HtmlsViewPagerJson mHtmlsViewPagerJson = mAppConfigJson.getCommunityActivityJson();
			initPagerFragmentVualue(mHtmlsViewPagerJson);
			mMenuAdapter.setWebViewPagerActivityTop(true);
		}
		final int currentItem = getIntent().getIntExtra("setCurrentItem", -1);
		if (currentItem != -1) {
			postDelayedL(new Runnable() {
				@Override
				public void run() {
					setCurrentItem(currentItem, true);
				}
			}, 1000);
		}
	}

	@Override
	protected void setDynamicFragment(TabMenuItemBean mTabMenuItemBean) {
		if (mTabMenuItemBean == null) {
			return;
		}
		switch (mTabMenuItemBean.getID()) {
		case 0:
			addFragmentToList(new DemoRefreshListViewFragment());
			break;
		case 1:// 固定ID
			addFragmentToList(new DemoRefreshGridViewFragment());
			break;
		case 2:// 固定ID
			addFragmentToList(new DemoRefreshScrollViewFragment());
			break;
		case 3:// 固定ID
			addFragmentToList(new DemoRefreshExpandableListViewFragment());
			break;
		case 4:// 固定ID
			addFragmentToList(new DemoRefreshDragSortListViewFragment());
			break;
		case 5:// 固定ID
			addFragmentToList(new DemoRefreshPinnedSectionListViewFragment());
			break;
		case 6:// 固定ID
			addFragmentToList(new DemoRefreshQuickReturnListViewFragment());
			break;
		// case 7:// 固定ID
		// addFragmentToList(new DemoRefreshSwipeMenuListViewFragment());
		// break;
		// case 8:// 固定ID
		// addFragmentToList(new DemoRefreshSwipeMenuListViewDiffFragment());
		// break;
		case 9:// 固定ID
			addFragmentToList(new DemoRefreshWebViewFragment());
			break;
		default:
			super.setDynamicFragment(mTabMenuItemBean);
			break;
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		super.onPageSelected(arg0);
		setOnDrawerBackEnabled(arg0 == 0);
		setActivityTitle(listMenu.get(getCurrentItem()).getMenuName());
	}
}

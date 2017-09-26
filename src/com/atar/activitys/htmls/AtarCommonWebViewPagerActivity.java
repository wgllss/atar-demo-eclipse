/**
 * 
 */
package com.atar.activitys.htmls;

import java.util.ArrayList;
import java.util.List;

import android.adapter.FragmentAdapter;
import android.fragment.CommonFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;

import com.atar.activitys.AtarDropTitleBarActivity;
import com.atar.activitys.R;
import com.atar.activitys.weex.WeexUtils;
import com.atar.adapters.MenuAdapter;
import com.atar.config.HtmlsViewPagerJson;
import com.atar.config.TabMenuItemBean;
import com.atar.widgets.PagerSlidingTabStrip;

/**
 *****************************************************************************************************************************************************************************
 * 动态fragment配置activity
 * @author :Atar
 * @createTime:2017-7-24下午5:30:13
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarCommonWebViewPagerActivity extends AtarDropTitleBarActivity implements OnPageChangeListener {
	private boolean isExtendsAtarCommonWebViewPagerActivity = true;

	protected List<TabMenuItemBean> listMenu = new ArrayList<TabMenuItemBean>();
	protected MenuAdapter mMenuAdapter = new MenuAdapter(listMenu);
	protected PagerSlidingTabStrip tabs;
	protected ViewPager mViewPager;
	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
	private FragmentAdapter mFragmentPagerAdapter;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (isExtendsAtarCommonWebViewPagerActivity) {
			addContentView(R.layout.activity_webview_pager);
		}
	}

	@Override
	protected void initControl() {
		if (isExtendsAtarCommonWebViewPagerActivity) {
			tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
			mViewPager = (ViewPager) findViewById(R.id.view_pager);
		}
	}

	@Override
	public void onPageSelected(int arg0) {
		if (mMenuAdapter != null) {
			mMenuAdapter.setCurrentPostiotn(arg0);
			if (tabs != null) {
				tabs.notifyDataSetChanged();
			}
		}
		try {
			// ((AtarDynamicFragment) getFragmentList().get(getCurrentItem())).loadWebViewUrl("javascript:onPageSelected('" + arg0 + "')");
		} catch (Exception e) {
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	public void setExtendsAtarCommonWebViewPagerActivity(boolean isExtendsAtarCommonWebViewPagerActivity) {
		this.isExtendsAtarCommonWebViewPagerActivity = isExtendsAtarCommonWebViewPagerActivity;
	}

	protected void setViewPagerAdapter() {
		if (mViewPager != null && getFragmentList() != null && getSupportFragmentManager() != null) {
			getFragmentList().get(0).setUserVisibleHint(true);
			mFragmentPagerAdapter = new FragmentAdapter(getSupportFragmentManager(), getFragmentList());
			mFragmentPagerAdapter.notifyDataSetChanged();
			mViewPager.setAdapter(mFragmentPagerAdapter);
			mViewPager.setOffscreenPageLimit(getFragmentList().size() > 5 ? 5 : getFragmentList().size());
		}

	}

	/**
	 * 初始化PagerSlidingTabStrip类容
	 * @author :Atar
	 * @createTime:2017-7-24下午5:45:11
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	protected void initPagerFragmentVualue(HtmlsViewPagerJson mHtmlsViewPagerJson) {
		if (mHtmlsViewPagerJson != null) {
			/* 标题start */
			if (mHtmlsViewPagerJson.getTITLE() != null && mHtmlsViewPagerJson.getTITLE().length() > 0) {
				setActivityTitle(mHtmlsViewPagerJson.getTITLE());
			} else {
				setTitleBarGone();
			}
			/* 标题end */
			/* 顶部右边部分处理start */
			String top_right_img_url = mHtmlsViewPagerJson.getTOP_RIGHT_IMG_URL();
			String top_right_txt = mHtmlsViewPagerJson.getTOP_RIGHT_TXT();
			if (top_right_txt != null && top_right_txt.length() > 0) {
				setTopRightText(top_right_txt);
			}
			if (top_right_img_url != null && top_right_img_url.length() > 0 && imgCommonTopRight != null) {
				imgCommonTopRight.setVisibility(View.VISIBLE);
				LoadImageView(top_right_img_url, imgCommonTopRight, 0);
			}
			/* 顶部右边部分处理end */
			listMenu.clear();
			for (TabMenuItemBean mTabMenuItemBean : mHtmlsViewPagerJson.getListFragment()) {
				listMenu.add(mTabMenuItemBean);
				setDynamicFragment(mTabMenuItemBean);
			}
			getFragmentList().get(0).setUserVisibleHint(true);
			mFragmentPagerAdapter = new FragmentAdapter(getSupportFragmentManager(), getFragmentList());
			mFragmentPagerAdapter.notifyDataSetChanged();
			mViewPager.setAdapter(mFragmentPagerAdapter);

			mMenuAdapter.setWebViewPagerActivityTop(true);
			mMenuAdapter.setContext(this);
			mMenuAdapter.setSkinType(getCurrentSkinType());
			tabs.setShouldExpand(mHtmlsViewPagerJson.isShouldExpand());
			mMenuAdapter.setCondition(mHtmlsViewPagerJson.isShouldExpand() ? 1 : 0);
			if (mHtmlsViewPagerJson.getIndicatorColor() != null && mHtmlsViewPagerJson.getIndicatorColor().length() > 0 && mHtmlsViewPagerJson.getIndicatorColor().contains(",")) {
				String[] IndicatorColor = mHtmlsViewPagerJson.getIndicatorColor().split(",");
				if (IndicatorColor != null && IndicatorColor.length > 0) {
					tabs.setIndicatorColor(Color.parseColor(IndicatorColor[getCurrentSkinType()]));
				}
			}
			if (mHtmlsViewPagerJson.getUnderlineColor() != null && mHtmlsViewPagerJson.getUnderlineColor().length() > 0 && mHtmlsViewPagerJson.getUnderlineColor().contains(",")) {
				String[] UnderlineColor = mHtmlsViewPagerJson.getUnderlineColor().split(",");
				if (UnderlineColor != null && UnderlineColor.length > 0) {
					tabs.setUnderlineColor(Color.parseColor(UnderlineColor[getCurrentSkinType()]));
				}
			}
			tabs.setAdapter(mMenuAdapter, mHtmlsViewPagerJson.isShowDividerLine());
			tabs.setViewPager(mViewPager);
			mViewPager.setOffscreenPageLimit(getFragmentList().size());
			tabs.setOnPageChangeListener(this);
			setCurrentItem(0, true);
		}
	}

	/**
	 * 设置动态fragment
	 * @author :Atar
	 * @createTime:2017-7-24下午5:53:09
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mTabMenuItemBean
	 * @description:
	 */
	protected void setDynamicFragment(TabMenuItemBean mTabMenuItemBean) {
		if (mTabMenuItemBean.getOnClickInfo() != null) {
			String url = mTabMenuItemBean.getOnClickInfo().getClassName();
			if (url != null && url.length() > 0 && url.contains("assets/html") && !url.contains("http")) {
				url = WeexUtils.WEEX_HOST + url;
				// mFragmentList.add(AtarDynamicFragment.newInstance(mTabMenuItemBean.getOnClickInfo().getOptionJson(), mTabMenuItemBean.getOnClickInfo().getPULL_TO_REFRESH_MODE(), url));
			}
		}
	}

	protected void addFragmentToList(Fragment mFragment) {
		mFragmentList.add(mFragment);
	}

	public int getCurrentItem() {
		return mViewPager != null ? mViewPager.getCurrentItem() : 0;
	}

	public List<Fragment> getFragmentList() {
		return mFragmentList;
	}

	public void setCurrentItem(int position, boolean smoothScroll) {
		if (mMenuAdapter != null) {
			mMenuAdapter.setCurrentPostiotn(position);
			if (tabs != null) {
				tabs.notifyDataSetChanged();
			}
		}
		if (mViewPager != null) {
			if (position < mFragmentList.size()) {
				mViewPager.setCurrentItem(position, smoothScroll);
			}
		}
	}

	@Override
	public void setActivityTitle(String strTitile) {
		super.setActivityTitle(strTitile);
	}

	/**
	 * 设置小红点数字
	 */
	public void setNewInfoNum(int position, int num) {
		if (listMenu != null && listMenu.size() > position) {
			listMenu.get(position).setInfoNum(num);
			if (mMenuAdapter != null) {
				mMenuAdapter.notifyDataSetChanged();
				if (tabs != null) {
					tabs.notifyDataSetChanged();
				}
			}
		}
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		// LoadUtil.setBackgroundColor(this, R.array.common_tab_bg_color, skinType, tabs);
		if (tabs != null) {
			if (mMenuAdapter != null) {
				mMenuAdapter.setSkinType(skinType);
			}
			tabs.notifyDataSetChanged();
		}

		if (getFragmentList() != null && getFragmentList().size() > 0) {
			for (Fragment fragment : getFragmentList()) {
				if (fragment instanceof CommonFragment) {
					((CommonFragment) fragment).ChangeSkin(skinType);
				}
			}
		}
	}
}

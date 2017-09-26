/**
 * 
 */
package com.atar.activitys;

import java.util.List;

import android.activity.CommonActivity;
import android.adapter.FragmentAdapter;
import android.annotation.SuppressLint;
import android.fragment.CommonFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.atar.interfaces.ImplOnTabInterface;
import com.atar.interfaces.OnTabInterface;

/**
 *****************************************************************************************************************************************************************************
 * 含有动态个数tab标签
 * @author :Atar
 * @createTime:2014-7-17下午4:43:15
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: V 为ViewPager上一层布局layout 如LinearLayout RelativeLayout
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("Recycle")
public abstract class AtarCommonTabActivity<V extends ViewGroup> extends AtarDropTitleBarActivity implements OnTabInterface<V, CommonActivity> {
	protected ImplOnTabInterface<V, CommonActivity> mImplOnTabInterface;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (getResLayoutID() > 0) {
			addContentView(getResLayoutID());
		}
	}

	protected int getResLayoutID() {
		return R.layout.common_tab_viewpager;
	}

	@Override
	protected void initControl() {
		if (mImplOnTabInterface == null) {
			setTabUI((ViewPager) findViewById(R.id.view_pager), (FrameLayout) findViewById(R.id.frame_move), (LinearLayout) findViewById(R.id.linear_tab_bar), findViewById(R.id.view_move));
		}
	}

	/**
	 * 设置tabUI
	 * @author :Atar
	 * @createTime:2015-7-1下午1:49:11
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mFragmentActivity
	 * @param mOnTabListener
	 * @param mViewPager
	 * @param frameMove
	 * @param mLinearLayout
	 * @param viewMove
	 * @description:
	 */
	protected void setTabUI(ViewPager mViewPager, FrameLayout frameMove, LinearLayout mLinearLayout, View viewMove) {
		if (mImplOnTabInterface == null) {
			mImplOnTabInterface = ImplOnTabInterface.LoadTabView(this, AtarCommonTabActivity.this, mViewPager, frameMove, mLinearLayout, viewMove);
		}
	}

	@Override
	public void setCurrentItem(int position, boolean smoothScroll) {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.setCurrentItem(position, true);
		}
	}

	@Override
	public int getCurrentItem() {
		return mImplOnTabInterface != null ? mImplOnTabInterface.getCurrentItem() : 0;
	}

	@Override
	public ViewPager getViewPager() {
		return mImplOnTabInterface != null ? mImplOnTabInterface.getViewPager() : null;
	}

	@Override
	public List<Fragment> getFragmentList() {
		return mImplOnTabInterface != null ? mImplOnTabInterface.getFragmentList() : null;
	}

	@Override
	public FragmentAdapter getFragmentPagerAdapter() {
		return mImplOnTabInterface != null ? mImplOnTabInterface.getFragmentPagerAdapter() : null;
	}

	@Override
	public void addFragmentToList(Fragment mFragment) {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.addFragmentToList(mFragment);
		}
	}

	@Override
	public void clearList() {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.clearList();
		}
	}

	@Override
	public void setViewPagerAdapter() {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.setViewPagerAdapter();
		}
	}

	@Override
	public void clearViewPager() {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.clearViewPager();
		}
	}

	@Override
	public void setMoveLineWidth(int paddingNum) {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.setMoveLineWidth(paddingNum);
		}
	}

	@Override
	public void onPageSelected(int position) {
	}

	@Override
	public void setTextTab(String[] strArray, boolean isDropDown, boolean smoothScroll) {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.setTextTab(strArray, isDropDown, smoothScroll);
		}
	}

	@Override
	public void setTextTab(int[] intArray, boolean isDropDown, boolean smoothScroll) {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.setTextTab(intArray, isDropDown, smoothScroll);
		}
	}

	@Override
	public CommonActivity getFragmentActivity() {
		return mImplOnTabInterface != null ? mImplOnTabInterface.getFragmentActivity() : null;
	}

	@Override
	public void onTabItemClick(int position, View v) {
	}

	@Override
	public void setTextItemLayoutParams(LayoutParams lp) {
	}

	@Override
	public void setChangeTabSkin(int skinType) {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.setChangeTabSkin(skinType);
		}
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		setChangeTabSkin(skinType);
		try {
			for (Fragment fragment : getFragmentList()) {
				if (fragment instanceof CommonFragment) {
					((CommonFragment) fragment).ChangeSkin(skinType);
				}
			}
		} catch (Exception e) {

		}
	}
}

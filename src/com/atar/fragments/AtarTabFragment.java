/**
 * 
 */
package com.atar.fragments;

import java.util.List;

import android.activity.CommonActivity;
import android.adapter.FragmentAdapter;
import android.fragment.CommonFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

import com.atar.activitys.R;
import com.atar.interfaces.ImplOnTabInterface;
import com.atar.interfaces.OnTabInterface;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2015-6-30下午4:28:05
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarTabFragment<V extends ViewGroup> extends CommonFragment implements OnTabInterface<V, CommonActivity> {
	protected ImplOnTabInterface<V, CommonActivity> mImplOnTabInterface;
	protected View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.common_tab_viewpager, container, false);
			setTabUI((ViewPager) view.findViewById(R.id.view_pager), (FrameLayout) view.findViewById(R.id.frame_move), (LinearLayout) view.findViewById(R.id.linear_tab_bar),
					view.findViewById(R.id.view_move));
		}
		if (view != null && view.getParent() != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
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
			mImplOnTabInterface = ImplOnTabInterface.LoadTabView(this, (CommonActivity) getActivity(), mViewPager, frameMove, mLinearLayout, viewMove);
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
	public void setTextItemLayoutParams(LayoutParams lp) {

	}

	@Override
	public void onPageSelected(int position) {
	}

	@Override
	public void setTextTab(String[] strArray) {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.setTextTab(strArray);
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
	public void OnChangeSkin(int skinType) {
		super.OnChangeSkin(skinType);
		setChangeTabSkin(skinType);
		try {
			if (getFragmentList() != null) {
				for (Fragment fragment : getFragmentList()) {
					if (fragment instanceof CommonFragment) {
						((CommonFragment) fragment).OnChangeSkin(skinType);
					}
				}
			}
		} catch (Exception e) {
		}
	}

	@Override
	public void setChangeTabSkin(int skinType) {
		if (mImplOnTabInterface != null) {
			mImplOnTabInterface.setChangeTabSkin(skinType);
		}
	}

	@Override
	public void onTabItemClick(int position, View v) {
	}
}

/**
 * 
 */
package com.atar.interfaces;

import java.util.ArrayList;
import java.util.List;

import android.activity.CommonActivity;
import android.adapter.FragmentAdapter;
import android.annotation.SuppressLint;
import android.application.CrashHandler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.tween.CommonTraslate;
import android.util.TypedValue;
import android.utils.DeviceManager;
import android.utils.ScreenUtils;
import android.utils.ShowLog;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

/**
 *****************************************************************************************************************************************************************************
 * 公共 实现 上面一排tab 下面viewPager接口
 * @author :Atar
 * @createTime:2015-6-30下午5:02:36
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("Recycle")
public class ImplOnTabInterface<V extends ViewGroup, A extends CommonActivity> implements OnTabInterface<V, A>, OnPageChangeListener {

	private static String TAG = ImplOnTabInterface.class.getSimpleName();

	private A mFragmentActivity;
	private List<Fragment> mFragmentList = new ArrayList<Fragment>();
	private ViewPager mViewPager;
	private FrameLayout frameMove;
	private FragmentAdapter mFragmentPagerAdapter;
	private LinearLayout mLinearLayout;
	private View viewMove;
	private int tabCount;
	private int startLeft;
	private OnTabInterface<V, A> mOnTabListener;
	private boolean isDropDown;

	@SuppressWarnings("deprecation")
	public ImplOnTabInterface(A mFragmentActivity, OnTabInterface<V, A> mOnTabListener, ViewPager mViewPager, FrameLayout frameMove, LinearLayout mLinearLayout, View viewMove) {
		this.mFragmentActivity = mFragmentActivity;
		this.mOnTabListener = mOnTabListener;
		this.mViewPager = mViewPager;
		this.frameMove = frameMove;
		this.mLinearLayout = mLinearLayout;
		this.viewMove = viewMove;
		mViewPager.setOnPageChangeListener(this);
	}

	public static <V extends ViewGroup, A extends CommonActivity> ImplOnTabInterface<V, A> LoadTabView(OnTabInterface<V, A> mOnTabListener, A mFragmentActivity, ViewPager mViewPager,
			FrameLayout frameMove, LinearLayout mLinearLayout, View viewMove) {
		ImplOnTabInterface<V, A> mImplOnTabInterface = null;
		try {
			mImplOnTabInterface = new ImplOnTabInterface<V, A>(mFragmentActivity, mOnTabListener, mViewPager, frameMove, mLinearLayout, viewMove);
		} catch (Exception e) {
			ShowLog.e(TAG, "LoadTabView--->" + CrashHandler.crashToString(e));
		}
		return mImplOnTabInterface;
	}

	private void initTabView() {
		if (mLinearLayout != null) {
			mLinearLayout.removeAllViews();
			for (int i = 0; i < tabCount; i++) {
				TextView txtItem = new TextView(mFragmentActivity);
				txtItem.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
				txtItem.setSingleLine();
				int paddingSize = (int) ScreenUtils.getIntToDip(5);
				txtItem.setPadding(paddingSize, paddingSize, paddingSize, paddingSize);
				LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
				setTextItemLayoutParams(lp);
				txtItem.setLayoutParams(lp);
				txtItem.setGravity(Gravity.CENTER);
				mLinearLayout.addView(txtItem, i);
			}
			// ((TextView) mLinearLayout.getChildAt(0)).setTextColor(mFragmentActivity.getResources().obtainTypedArray(R.array.common_app_color).getColor(0, 0));
			if (mViewPager != null)
				mViewPager.setOffscreenPageLimit(tabCount);
		}
		initCursorView();
	}

	@Override
	public void setTextItemLayoutParams(LayoutParams lp) {
		if (mOnTabListener != null) {
			mOnTabListener.setTextItemLayoutParams(lp);
		}
	}

	// tab标签移动背景控件初始化
	private void initCursorView() {
		if (frameMove != null) {
			V.LayoutParams lp = (V.LayoutParams) frameMove.getLayoutParams();
			lp.width = (DeviceManager.getScreenWidth(mFragmentActivity)) / tabCount;
			frameMove.setLayoutParams(lp);
		}
	}

	@Override
	public void setCurrentItem(int position, boolean smoothScroll) {
		if (mViewPager != null) {
			if (position < mFragmentList.size()) {
				mViewPager.setCurrentItem(position, smoothScroll);
			}
		}
	}

	@Override
	public int getCurrentItem() {
		return mViewPager != null ? mViewPager.getCurrentItem() : 0;
	}

	@Override
	public ViewPager getViewPager() {
		return mViewPager;
	}

	@Override
	public List<Fragment> getFragmentList() {
		return mFragmentList;
	}

	@Override
	public FragmentAdapter getFragmentPagerAdapter() {
		return mFragmentPagerAdapter;
	}

	@Override
	public void addFragmentToList(Fragment mFragment) {
		mFragmentList.add(mFragment);
	}

	@Override
	public void clearList() {
		mFragmentList.clear();
		if (mFragmentPagerAdapter != null) {
			mFragmentPagerAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void setTextTab(String[] strArray) {
		if (strArray != null) {
			tabCount = strArray.length;
			initTabView();
			if (mLinearLayout != null) {
				for (int i = 0; i < strArray.length; i++) {
					((TextView) mLinearLayout.getChildAt(i)).setText(strArray[i]);
					((TextView) mLinearLayout.getChildAt(i)).setOnClickListener(new OnClickMenuListener(i, true));
				}
			}
		}
	}

	@Override
	public void setTextTab(int[] intArray, boolean isDropDown, boolean smoothScroll) {
		this.isDropDown = isDropDown;
		if (intArray != null) {
			tabCount = intArray.length;
			initTabView();
			if (mLinearLayout != null) {
				for (int i = 0; i < intArray.length; i++) {
					((TextView) mLinearLayout.getChildAt(i)).setText(mFragmentActivity.getResources().getString(intArray[i]));
					if (isDropDown) {
						// ((TextView) mLinearLayout.getChildAt(i)).setCompoundDrawablesWithIntrinsicBounds(null, null, mFragmentActivity.getResources().getDrawable(R.drawable.down02), null);
						int padding = (int) ScreenUtils.getIntToDip(11);
						((TextView) mLinearLayout.getChildAt(i)).setPadding(padding, (int) ScreenUtils.getIntToDip(4), padding, 0);
					}
					((TextView) mLinearLayout.getChildAt(i)).setOnClickListener(new OnClickMenuListener(i, smoothScroll));
				}
			}
		}
	}

	@Override
	public void setViewPagerAdapter() {
		if (mFragmentActivity != null && mViewPager != null && getFragmentList() != null && mFragmentActivity.getSupportFragmentManager() != null) {
			getFragmentList().get(0).setUserVisibleHint(true);
			mFragmentPagerAdapter = new FragmentAdapter(mFragmentActivity.getSupportFragmentManager(), getFragmentList());
			mFragmentPagerAdapter.notifyDataSetChanged();
			mViewPager.setAdapter(mFragmentPagerAdapter);
			if (frameMove != null) {
				CommonTraslate.TranslateAnimation(frameMove, startLeft, 0, 0, 0, 400, null);
			}
		}
	}

	@Override
	public void clearViewPager() {
		if (mViewPager != null) {
			mViewPager.removeAllViews();
		}
		clearList();
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		if (frameMove != null) {
			CommonTraslate.TranslateAnimation(frameMove, startLeft, arg0 * frameMove.getWidth(), 0, 0, 400, null);
		}
		if (mLinearLayout != null) {
			for (int i = 0; i < tabCount; i++) {
				if (arg0 == i) {
					// LoadUtil.setTextColor(mFragmentActivity, ((TextView) mLinearLayout.getChildAt(i)), R.array.select_tab_txt_color, mFragmentActivity.getCurrentSkinType());
				} else {
					// LoadUtil.setTextColor(mFragmentActivity, ((TextView) mLinearLayout.getChildAt(i)), R.array.tab_txt_color, mFragmentActivity.getCurrentSkinType());
				}
			}
		}
		if (frameMove != null) {
			startLeft = arg0 * frameMove.getWidth();
		}
		mOnTabListener.onPageSelected(arg0);
	}

	private class OnClickMenuListener implements OnClickListener {

		private int position;
		private boolean smoothScroll;

		public OnClickMenuListener(int position, boolean smoothScroll) {
			this.position = position;
			this.smoothScroll = smoothScroll;
		}

		@Override
		public void onClick(View v) {
			if (isDropDown) {
				// ((TextView) v).setCompoundDrawablesWithIntrinsicBounds(null, null, mFragmentActivity.getResources().getDrawable(R.drawable.top02), null);
				int padding = (int) ScreenUtils.getIntToDip(11);
				((TextView) v).setPadding(padding, (int) ScreenUtils.getIntToDip(4), padding, 0);
			}
			onTabItemClick(position, v);
			setCurrentItem(position, smoothScroll);
		}
	}

	@Override
	public void setMoveLineWidth(int paddingNum) {
		int paddingSize = (int) ScreenUtils.getIntToDip(paddingNum);
		if (frameMove != null) {
			frameMove.setPadding(paddingSize, 0, paddingSize, 0);
		}
	}

	@Override
	public void setChangeTabSkin(int skinType) {
		// LoadUtil.setBackgroundColor(mFragmentActivity, viewMove, R.array.common_tab_line_move_color, skinType);
		// LoadUtil.setBackgroundColor(mFragmentActivity, mLinearLayout, R.array.common_tab_bg_color, skinType);
		// LoadUtil.setBackgroundColor(mFragmentActivity, mViewPager, R.array.common_content_bg_color, skinType);
		// if (mLinearLayout != null) {
		// for (int i = 0; i < tabCount; i++) {
		// LoadUtil.setTextColor(mFragmentActivity, ((TextView) mLinearLayout.getChildAt(i)), R.array.tab_txt_color, skinType);
		// }
		// LoadUtil.setTextColor(mFragmentActivity, ((TextView) mLinearLayout.getChildAt(getCurrentItem())), R.array.select_tab_txt_color, skinType);
		// }
	}

	@Override
	public A getFragmentActivity() {
		return mFragmentActivity;
	}

	@Override
	public void onTabItemClick(int position, View v) {
		mOnTabListener.onTabItemClick(position, v);
	}
}

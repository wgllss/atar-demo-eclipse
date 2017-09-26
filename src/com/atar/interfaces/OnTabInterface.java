/**
 * 
 */
package com.atar.interfaces;

import java.util.List;

import android.activity.CommonActivity;
import android.adapter.FragmentAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout.LayoutParams;

/**
 *****************************************************************************************************************************************************************************
 * 公共上面一排tab 下面viewPager接口
 * @author :Atar
 * @createTime:2015-6-30下午4:53:31
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public interface OnTabInterface<V extends ViewGroup, A extends CommonActivity> {

	/**
	 * 设置ViewPager显示第几页
	 * @author :Atar
	 * @createTime:2015-7-1下午3:58:44
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param position
	 * @description:
	 */
	void setCurrentItem(int position, boolean smoothScroll);

	/**
	 * 得到ViewPager当前页面
	 * @author :Atar
	 * @createTime:2015-7-1下午3:59:08
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	int getCurrentItem();

	/**
	 * 得到ViewPager
	 * @author :Atar
	 * @createTime:2015-7-1下午3:59:28
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	ViewPager getViewPager();

	/**
	 * 得到List<Fragment> 
	 * @author :Atar
	 * @createTime:2015-7-1下午4:00:04
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	List<Fragment> getFragmentList();

	/**
	 * 得到FragmentAdapter
	 * @author :Atar
	 * @createTime:2015-7-1下午4:00:27
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	FragmentAdapter getFragmentPagerAdapter();

	/**
	 * 添加到fragment到List<Fragment>
	 * @author :Atar
	 * @createTime:2015-7-1下午4:00:41
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mFragment
	 * @description:
	 */
	void addFragmentToList(Fragment mFragment);

	/**
	 * 清除List<Fragment>
	 * @author :Atar
	 * @createTime:2015-7-1下午4:01:13
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	void clearList();

	/**
	 * 设置tab文字
	 * @author :Atar
	 * @createTime:2015-7-1下午4:01:27
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param strArray
	 * @description:
	 */
	void setTextTab(String[] strArray, boolean isDropDown, boolean smoothScroll);

	/**
	 * 设置tab文字
	 * @author :Atar
	 * @createTime:2015-7-1下午4:01:27
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param strArray tab文字
	 * @param isDropDown tab文字上方是否下拉
	 * @param smoothScroll viewpager切换是否滑动
	 * @description:
	 */
	void setTextTab(int[] intArray, boolean isDropDown, boolean smoothScroll);

	/**
	 * 设置ViewPager数据
	 * @author :Atar
	 * @createTime:2015-7-1下午4:01:27
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param strArray
	 * @description:
	 */
	void setViewPagerAdapter();

	/**
	 * 清除ViewPager
	 * @author :Atar
	 * @createTime:2015-7-1下午4:02:13
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	void clearViewPager();

	/**
	 * 设置滑动条显示宽度
	 * @author :Atar
	 * @createTime:2015-7-1下午4:02:26
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param paddingNum
	 * @description:
	 */
	void setMoveLineWidth(int paddingNum);

	/**
	 * 设置皮肤
	 * @author :Atar
	 * @createTime:2015-7-1下午4:02:47
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param skinType
	 * @description:
	 */
	void setChangeTabSkin(int skinType);

	/**
	 * ViewPager当前滑动到第几页
	 * @author :Atar
	 * @createTime:2015-7-1下午4:03:00
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param arg0
	 * @description:
	 */
	void onPageSelected(int arg0);

	/**
	 * tab点击了第几个
	 * @author :Atar
	 * @createTime:2015-7-1下午4:03:25
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param position
	 * @description:
	 */
	void onTabItemClick(int position, View v);

	/**
	 * 得到FragmentActivity
	 * @author :Atar
	 * @createTime:2015-7-1下午4:03:39
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	A getFragmentActivity();

	/**
	 * tab中textview LayoutParams属性
	 * @author :Atar
	 * @createTime:2016-2-23上午11:19:01
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param lp
	 * @description:
	 */
	void setTextItemLayoutParams(LayoutParams lp);
}

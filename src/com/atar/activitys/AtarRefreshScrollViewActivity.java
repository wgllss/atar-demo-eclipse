/**
 * 
 */
package com.atar.activitys;

import android.content.Context;
import android.os.Bundle;
import android.utils.ShowLog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-14上午11:03:08
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public abstract class AtarRefreshScrollViewActivity extends AtarRefreshActivity<PullToRefreshScrollView, ScrollView> {

	private static String TAG = AtarRefreshScrollViewActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (getResLayoutID() > 0) {
			addContentView(getResLayoutID());
		}
	}

	protected int getResLayoutID() {
		return R.layout.common_refresh_srollview;
	}

	@Override
	protected void initControl() {
		setRefreshView((PullToRefreshScrollView) findViewById(R.id.atar_refresh_scrollview));
		setTextView((TextView) findViewById(R.id.txt_list_toast));
		setRefreshMode(Mode.PULL_FROM_START);
	}

	/**
	 * 添加下拉刷新，上拉加载更多页
	 * @author :Atar
	 * @createTime:2014-7-15下午1:40:15
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param layoutResId
	 * @description:
	 */
	protected void addScrollView(int layoutResId) {
		LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		try {
			View v = inflater.inflate(layoutResId, null);
			if (getRefreshView() != null) {
				getRefreshView().addView(v);
				initScrollControl();
				initValue();
				bindEvent();
			}
		} catch (Exception e) {
			ShowLog.i(TAG, "addScrollView" + e.getMessage());
		}
	}

	/**
	 * 初始化滚动页控件
	 * @author :Atar
	 * @createTime:2014-7-15下午1:43:19
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:findViewById(R.id.xxxx)
	 */
	protected abstract void initScrollControl();
}

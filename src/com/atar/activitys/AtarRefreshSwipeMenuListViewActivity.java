/**
 * 
 */
package com.atar.activitys;

import android.os.Bundle;
import android.skin.SkinUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.atar.widget.swipmenulistview_lib.SwipeMenuCreator;
import com.atar.widget.swipmenulistview_lib.SwipeMenuListView;
import com.atar.widget.swipmenulistview_lib.SwipeMenuListView.OnMenuItemClickListener;
import com.atar.widget.swipmenulistview_lib.SwipeMenuListView.OnSwipeListener;
import com.atar.widgets.refresh.PullToRefreshSwipeMenuListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23下午5:35:38
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public abstract class AtarRefreshSwipeMenuListViewActivity extends AtarRefreshActivity<PullToRefreshSwipeMenuListView, SwipeMenuListView> implements OnItemLongClickListener, OnItemClickListener,
		SwipeMenuCreator, OnMenuItemClickListener, OnSwipeListener {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (getResLayoutID() > 0) {
			addContentView(getResLayoutID());
		}
	}

	protected int getResLayoutID() {
		return R.layout.common_refresh_swipmenu_listview;
	}

	@Override
	protected void initControl() {
		super.initControl();
		setRefreshView((PullToRefreshSwipeMenuListView) findViewById(R.id.atar_refresh_swipe_list));
		setTextView((TextView) findViewById(R.id.txt_list_toast));
	}

	/**
	 * 设置listview 适配器
	 * @author :Atar
	 * @createTime:2015-6-3上午10:44:38
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param adapter
	 * @description:
	 */
	public void setAdapter(BaseAdapter adapter) {
		if (getRefreshView() != null) {
			getRefreshView().setAdapter(adapter);
			getRefreshView().setOnItemLongClickListener(this);
			getRefreshView().setOnItemClickListener(this);
			getRefreshView().setOnMenuItemClickListener(this);
			getRefreshView().setOnSwipeListener(this);
			listView = getRefreshView();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		return false;
	}

	@Override
	public void onSwipeStart(int position) {
		// swipe start
	}

	@Override
	public void onSwipeEnd(int position) {
		// swipe end
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		if (getPullView() != null) {
			if (getPullView().getHeaderLoadingView() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_text_color, skinType, getPullView().getHeaderLoadingView().getHeaderText());
			}
			if (getPullView().getFooterLoadingView() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_text_color, skinType, getPullView().getFooterLoadingView().getHeaderText());
			}
			if (getPullView().getHeaderLoadingView() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_sub_text_color, skinType, getPullView().getHeaderLoadingView().getSubHeaderText());
			}
			if (getPullView().getFooterLoadingView() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_sub_text_color, skinType, getPullView().getFooterLoadingView().getSubHeaderText());
			}
			SkinUtils.setBackgroundColor(this, R.string.refresh_bg_color, skinType, getPullView().getHeaderLoadingView());
			SkinUtils.setBackgroundColor(this, R.string.refresh_bg_color, skinType, getPullView().getFooterLoadingView());
		}
	}
}

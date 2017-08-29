/**
 * 
 */
package com.atar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.widget.swipmenulistview_lib.SwipeMenu;
import com.atar.widget.swipmenulistview_lib.SwipeMenuCreator;
import com.atar.widget.swipmenulistview_lib.SwipeMenuListView;
import com.atar.widget.swipmenulistview_lib.SwipeMenuListView.OnMenuItemClickListener;
import com.atar.widget.swipmenulistview_lib.SwipeMenuListView.OnSwipeListener;
import com.atar.widgets.refresh.PullToRefreshSwipeMenuListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-29下午2:55:00
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarRefreshSwipeMenuListViewFragment extends AratRefreshAbsListViewFragment<PullToRefreshSwipeMenuListView, SwipeMenuListView> implements SwipeMenuCreator, OnMenuItemClickListener,
		OnSwipeListener {
	protected View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.common_refresh_swipmenu_listview, container, false);
			setTextView((TextView) view.findViewById(R.id.txt_list_toast));
			setRefreshView((PullToRefreshSwipeMenuListView) view.findViewById(R.id.atar_refresh_swipe_list));
		}
		if (view != null && view.getParent() != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
	}

	@Override
	public void onSwipeStart(int position) {
	}

	@Override
	public void onSwipeEnd(int position) {
	}

	@Override
	public void onMenuItemClick(int position, SwipeMenu menu, int index) {
	}

	@Override
	public void create(SwipeMenu menu) {
	}
}

package com.atar.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.atar.activitys.R;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 *****************************************************************************************************************************************************************************
 * 淘股吧订制 公用部分fragment 大多数都只有ListView ,写在此处
 * @author :Atar
 * @createTime:2014-7-4下午5:36:25
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:只含有一个刷新ListView的控件，继承此类轻松搞定，传入不同适配器即可
 *****************************************************************************************************************************************************************************
 */
@SuppressLint({ "HandlerLeak", "Recycle" })
public abstract class AtarRefreshListFragment extends AratRefreshAbsListViewFragment<PullToRefreshListView, ListView> {
	protected View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.common_refresh_listview, container, false);
			setTextView((TextView) view.findViewById(R.id.txt_list_toast));
			setRefreshView((PullToRefreshListView) view.findViewById(R.id.atar_refresh_lst));
		}
		if (view != null && view.getParent() != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
	}
}

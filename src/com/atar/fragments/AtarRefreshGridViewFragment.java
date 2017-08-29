/**
 * 
 */
package com.atar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.atar.activitys.R;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

/**
 *****************************************************************************************************************************************************************************
 * 淘股吧带刷新的GridView fragment
 * @author :Atar
 * @createTime:2015-5-6下午2:36:20
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public abstract class AtarRefreshGridViewFragment extends AratRefreshAbsListViewFragment<PullToRefreshGridView, GridView> {

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.common_refresh_gridview, container, false);
			setRefreshView((PullToRefreshGridView) view.findViewById(R.id.pull_refresh_gridview));
			setTextView((TextView) view.findViewById(R.id.txt_list_toast));
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

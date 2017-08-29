/**
 * 
 */
package com.atar.fragments;

import android.activity.CommonActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

import com.atar.activitys.R;
import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-29下午2:54:29
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarRefreExpandableListViewFragment extends AtarRefreshFragment<PullToRefreshExpandableListView, ExpandableListView> implements OnGroupClickListener, OnChildClickListener,
		OnGroupExpandListener, OnGroupCollapseListener {

	protected View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.common_refresh_expandablelistview, container, false);
			setTextView((TextView) view.findViewById(R.id.txt_list_toast));
			setRefreshView((PullToRefreshExpandableListView) view.findViewById(R.id.atar_refresh_expandableListView_lst));
		}
		if (view != null && view.getParent() != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
	}

	public void setAdapter(ExpandableListAdapter adapter) {
		if (getActivity() != null && getRefreshView() != null) {
			((CommonActivity) getActivity()).listView = getRefreshView();
			getRefreshView().setAdapter(adapter);
			getRefreshView().setGroupIndicator(null);
			getRefreshView().setOnGroupClickListener(this);
			getRefreshView().setOnChildClickListener(this);
			getRefreshView().setOnGroupExpandListener(this);
			getRefreshView().setOnGroupCollapseListener(this);
		}
	}

	@Override
	public void onGroupCollapse(int groupPosition) {
	}

	@Override
	public void onGroupExpand(int groupPosition) {
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		return false;
	}

	@Override
	public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
		return false;
	}

}

/**
 * 
 */
package com.atar.fragments;

import android.activity.CommonActivity;
import android.annotation.SuppressLint;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-29下午3:19:19
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AratRefreshAbsListViewFragment<T extends PullToRefreshBase<V>, V extends View> extends AtarRefreshFragment<T, V> implements OnItemLongClickListener, OnItemClickListener {

	@SuppressLint("NewApi")
	public void setAdapter(BaseAdapter adapter) {
		if (getActivity() != null && getRefreshView() != null) {
			((CommonActivity) getActivity()).listView = (AbsListView) getRefreshView();
			((AbsListView) getRefreshView()).setOnItemClickListener(this);
			((AbsListView) getRefreshView()).setOnItemLongClickListener(this);
			((AbsListView) getRefreshView()).setAdapter(adapter);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		return false;
	}

}

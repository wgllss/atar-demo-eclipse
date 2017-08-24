/**
 * 
 */
package com.atar.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshExpandableListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-24上午11:29:33
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarRefreshExpandableListViewActivity extends AtarRefreshActivity<PullToRefreshExpandableListView, ExpandableListView> implements OnGroupClickListener, OnChildClickListener,
		OnGroupExpandListener, OnGroupCollapseListener {
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addContentView(getResLayoutID());
	}

	protected int getResLayoutID() {
		return R.layout.common_refresh_expandablelistview;
	}

	@Override
	protected void initControl() {
		setRefreshView((PullToRefreshExpandableListView) findViewById(R.id.atar_refresh_expandableListView_lst));
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
	public void setAdapter(ExpandableListAdapter adapter) {
		if (getRefreshView() != null) {
			listView = getRefreshView();
			getRefreshView().setAdapter(adapter);
			getRefreshView().setGroupIndicator(null);
			getRefreshView().setOnGroupClickListener(this);
			getRefreshView().setOnChildClickListener(this);
			getRefreshView().setOnGroupExpandListener(this);
			getRefreshView().setOnGroupCollapseListener(this);
		}
	}

	/**
	 * 展开第几项
	 * @author :Atar
	 * @createTime:2015-6-8下午2:12:16
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param groupPosition
	 * @description:
	 */
	public void setExpandGroup(int groupPosition) {
		if (getRefreshView() != null) {
			getRefreshView().expandGroup(groupPosition);
		}
	}

	/**
	 * 收缩第几项
	 * @author :Atar
	 * @createTime:2015-6-8下午2:13:24
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param groupPosition
	 * @description:
	 */
	public void setCollapseGroup(int groupPosition) {
		if (getRefreshView() != null) {
			getRefreshView().collapseGroup(groupPosition);
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

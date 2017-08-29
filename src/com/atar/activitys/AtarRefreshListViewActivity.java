/**
 * 
 */
package com.atar.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-14上午11:26:16
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarRefreshListViewActivity extends AtarRefreshActivity<PullToRefreshListView, ListView> implements OnItemLongClickListener, OnItemClickListener {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (getResLayoutID() > 0) {
			addContentView(getResLayoutID());
		}
	}

	protected int getResLayoutID() {
		return R.layout.common_refresh_listview;
	}

	@Override
	protected void initControl() {
		setRefreshView((PullToRefreshListView) findViewById(R.id.atar_refresh_lst));
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
}

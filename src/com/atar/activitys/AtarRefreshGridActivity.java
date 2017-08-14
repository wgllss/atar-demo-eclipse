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
import android.widget.GridView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshGridView;

/**
 *****************************************************************************************************************************************************************************
 * 带GridView和刷新
 * @author :Atar
 * @createTime:2015-10-16下午4:43:06
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public abstract class AtarRefreshGridActivity extends AtarRefreshActivity<PullToRefreshGridView, GridView> implements OnItemLongClickListener, OnItemClickListener {

	protected boolean isExtendsRefreshGridView = true;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (isExtendsRefreshGridView) {
			addContentView(R.layout.common_refresh_gridview);
		}
	}

	@Override
	protected void initControl() {
		if (isExtendsRefreshGridView) {
			setRefreshView((PullToRefreshGridView) findViewById(R.id.pull_refresh_gridview));
			setTextView((TextView) findViewById(R.id.txt_list_toast));
		}
	}

	/**
	 * 设置是否继承refreshListView;一定要super.onCreate(bundle)之前 默认继承
	 * @author :Atar
	 * @createTime:2014-7-9下午3:45:26
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param isExtendsRefreshGridView 默认true
	 * @description:
	 */
	protected void setExtendsRefreshGridView(boolean isExtendsRefreshGridView) {
		this.isExtendsRefreshGridView = isExtendsRefreshGridView;
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

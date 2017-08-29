/**
 * 
 */
package com.atar.fragment.demos;

import java.util.ArrayList;
import java.util.List;

import android.common.CommonHandler;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.CommonToast;
import android.widget.ExpandableListView;

import com.atar.adapters.DemoExpandableAdapter;
import com.atar.beans.MenuItemBean;
import com.atar.enums.EnumMsgWhat;
import com.atar.fragments.AtarRefreExpandableListViewFragment;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-24上午11:37:04
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshExpandableListViewFragment extends AtarRefreExpandableListViewFragment {

	private List<MenuItemBean> list = new ArrayList<MenuItemBean>();
	private DemoExpandableAdapter mDemoExpandableAdapter = new DemoExpandableAdapter(list);

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		for (int i = 0; i < 30; i++) {
			list.add(new MenuItemBean("" + i, "item组" + i));
		}
		mDemoExpandableAdapter.notifyDataSetChanged();
		setAdapter(mDemoExpandableAdapter);
	}

	@Override
	public void onHandlerData(Message msg) {
		super.onHandlerData(msg);
		switch (msg.what) {
		case EnumMsgWhat.LOAD_FROM_SQL:
			setRefreshing();
			break;
		case EnumMsgWhat.LOAD_FROM_SQL_COMPLETE:
			break;
		case EnumMsgWhat.REFRESH_PULL_DOWN:
		case EnumMsgWhat.REFRESH_PULL_UP:
		case EnumMsgWhat.REFRESH_HANDLER:
			CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {

				@Override
				public void run() {
					onStopRefresh();
				}
			}, 1000);
			break;
		}
	}

	@Override
	public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
		List<MenuItemBean> childList = new ArrayList<MenuItemBean>();
		for (int i = 0; i < 10; i++) {
			childList.add(new MenuItemBean("" + i, "item" + i));
		}
		if (mDemoExpandableAdapter != null) {
			mDemoExpandableAdapter.addToChildList(groupPosition, childList);
		}
		return false;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
		CommonToast.show("onChildClick--" + id);
		return false;
	}
}

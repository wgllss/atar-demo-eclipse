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
import android.widget.AdapterView;

import com.atar.adapters.MainDemoAdapter;
import com.atar.beans.MenuItemBean;
import com.atar.enums.EnumMsgWhat;
import com.atar.fragments.AtarRefreshGridViewFragment;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23上午10:02:16
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshGridViewFragment extends AtarRefreshGridViewFragment {

	private List<MenuItemBean> list = new ArrayList<MenuItemBean>();
	private MainDemoAdapter mMainDemoAdapter = new MainDemoAdapter(list);

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		for (int i = 0; i < 20; i++) {
			list.add(new MenuItemBean("0", "refresh-ListView"));
			list.add(new MenuItemBean("1", "refresh-GridView"));
			list.add(new MenuItemBean("2", "refresh-ScrollView"));
			list.add(new MenuItemBean("3", "refresh-Webview"));
			list.add(new MenuItemBean("4", "refresh-PinnedSectionListView"));
		}
		mMainDemoAdapter.notifyDataSetChanged();
		mMainDemoAdapter.notifyDataSetChanged();
		setAdapter(mMainDemoAdapter);
		getRefreshView().setNumColumns(2);
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
	public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		return false;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		if (mMainDemoAdapter != null) {
			mMainDemoAdapter.setSkinType(skinType);
		}
	}
}

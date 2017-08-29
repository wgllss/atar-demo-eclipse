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

import com.atar.adapters.DragAdapter;
import com.atar.beans.MenuItemBean;
import com.atar.enums.EnumMsgWhat;
import com.atar.fragments.AtarRefreshDragSortListViewFragment;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23下午2:01:27
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshDragSortListViewFragment extends AtarRefreshDragSortListViewFragment<MenuItemBean> {
	private List<MenuItemBean> list = new ArrayList<MenuItemBean>();
	private DragAdapter mDragAdapter;// 可拖动的ListView对应的适配器

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		for (int i = 0; i < 50; i++) {
			list.add(new MenuItemBean("" + i, "item--" + i));
		}
		mDragAdapter = new DragAdapter(list);
		mDragAdapter.notifyDataSetChanged();
		setRefreshMode(Mode.BOTH);
		setAdapter(mDragAdapter);
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
}

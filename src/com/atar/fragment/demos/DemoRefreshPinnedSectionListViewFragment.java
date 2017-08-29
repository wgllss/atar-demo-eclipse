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

import com.atar.adapters.DemoPinnesSectionAdapter;
import com.atar.beans.DemoPinnesBean;
import com.atar.enums.EnumMsgWhat;
import com.atar.fragments.AtarRefreshSectionListViewFragment;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23上午11:18:53
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshPinnedSectionListViewFragment extends AtarRefreshSectionListViewFragment {

	private List<DemoPinnesBean> list = new ArrayList<DemoPinnesBean>();
	private DemoPinnesSectionAdapter mDemoPinnesSectionAdapter = new DemoPinnesSectionAdapter(list);

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		for (int i = 0; i < 50; i++) {
			list.add(new DemoPinnesBean(i % 5 == 1 ? 1 : 0, "item" + i));
		}
		mDemoPinnesSectionAdapter.notifyDataSetChanged();
		setAdapter(mDemoPinnesSectionAdapter);
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

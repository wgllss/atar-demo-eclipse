/**
 * 
 */
package com.atar.fragment.demos;

import java.util.ArrayList;
import java.util.List;

import android.common.CommonHandler;
import android.os.Bundle;
import android.os.Message;
import android.utils.QuickReturnViewUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.adapters.MainDemoAdapter;
import com.atar.beans.MenuItemBean;
import com.atar.enums.EnumMsgWhat;
import com.atar.fragments.AtarRefreshListFragment;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-24上午11:13:52
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshQuickReturnListViewFragment extends AtarRefreshListFragment {
	private List<MenuItemBean> list = new ArrayList<MenuItemBean>();
	private MainDemoAdapter mMainDemoAdapter = new MainDemoAdapter(list);
	private TextView mQuickReturnView;

	private QuickReturnViewUtil mQuickReturnViewUtil;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.activity_quickreturn_buttom, container, false);
			setTextView((TextView) view.findViewById(R.id.txt_list_toast));
			setRefreshView((PullToRefreshListView) view.findViewById(R.id.atar_refresh_lst));
			mQuickReturnView = (TextView) view.findViewById(R.id.footer);
		}
		if (view != null && view.getParent() != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		for (int i = 0; i < 50; i++) {
			list.add(new MenuItemBean("0", "refresh-item-" + i));
		}
		mMainDemoAdapter.notifyDataSetChanged();
		setAdapter(mMainDemoAdapter);

		mQuickReturnViewUtil = new QuickReturnViewUtil();
		mQuickReturnViewUtil.setReturnView(getRefreshView(), mQuickReturnView);
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
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		if (mMainDemoAdapter != null) {
			mMainDemoAdapter.setSkinType(skinType);
		}
	}
}

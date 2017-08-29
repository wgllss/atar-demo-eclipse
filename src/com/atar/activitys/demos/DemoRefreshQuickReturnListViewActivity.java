/**
 * 
 */
package com.atar.activitys.demos;

import java.util.ArrayList;
import java.util.List;

import android.common.CommonHandler;
import android.os.Message;
import android.utils.QuickRturnListViewUtil;
import android.widget.TextView;

import com.atar.activitys.AtarRefreshListViewActivity;
import com.atar.activitys.R;
import com.atar.adapters.MainDemoAdapter;
import com.atar.beans.MenuItemBean;
import com.atar.enums.EnumMsgWhat;

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
public class DemoRefreshQuickReturnListViewActivity extends AtarRefreshListViewActivity {
	private List<MenuItemBean> list = new ArrayList<MenuItemBean>();
	private MainDemoAdapter mMainDemoAdapter = new MainDemoAdapter(list);
	private TextView mQuickReturnView;

	private QuickRturnListViewUtil mQuickRturnListViewUtil;

	@Override
	protected int getResLayoutID() {
		return R.layout.activity_quickreturn_buttom;
	}

	@Override
	protected void applyScrollListener() {
		if (listView != null && mQuickRturnListViewUtil != null) {
			listView.setOnScrollListener(mQuickRturnListViewUtil.getImplOnScrollListener());
		}
	}

	@Override
	protected void initControl() {
		super.initControl();
		mQuickReturnView = (TextView) findViewById(R.id.footer);
	}

	@Override
	protected void initValue() {
		super.initValue();
		setActivityTitle(getIntent().getStringExtra(DemoRefreshActivity.TITLE_KEY));
		for (int i = 0; i < 50; i++) {
			list.add(new MenuItemBean("0", "refresh-item-" + i));
		}
		mMainDemoAdapter.notifyDataSetChanged();
		setAdapter(mMainDemoAdapter);

		mQuickRturnListViewUtil = new QuickRturnListViewUtil(QuickRturnListViewUtil.BUTTOM, false, getRefreshView());
		mQuickRturnListViewUtil.setPauseOnScrollListenerParams(imageLoader, pauseOnScroll, pauseOnFling);
		mQuickRturnListViewUtil.setQuickReturnEvent(mQuickReturnView, null);

		sendEmptyMessageDelayed(EnumMsgWhat.LOAD_FROM_SQL, 400);
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

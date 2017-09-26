/**
 * 
 */
package com.atar.activitys.demos;

import java.util.ArrayList;
import java.util.List;

import android.common.CommonHandler;
import android.os.Message;
import android.skin.SkinUtils;
import android.utils.QuickReturnViewUtil;
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

	private QuickReturnViewUtil mQuickReturnViewUtil;

	@Override
	protected int getResLayoutID() {
		return R.layout.activity_quickreturn_buttom;
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

		mQuickReturnViewUtil = new QuickReturnViewUtil();
		mQuickReturnViewUtil.setReturnView(getRefreshView(), mQuickReturnView);

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

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		if (mMainDemoAdapter != null) {
			mMainDemoAdapter.setSkinType(skinType);
		}
		if (mQuickReturnView != null) {
			mQuickReturnView.setBackgroundColor(SkinUtils.getColor(this, R.string.home_green_color));
			mQuickReturnView.setText(SkinUtils.getString(this, R.string.string_name_app_name));
			mQuickReturnView.setTextColor(SkinUtils.getColor(this, R.string.linen));
		}
	}
}

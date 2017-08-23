/**
 * 
 */
package com.atar.activitys.demos;

import android.common.CommonHandler;
import android.os.Bundle;
import android.os.Message;

import com.atar.activitys.AtarRefreshScrollViewActivity;
import com.atar.activitys.R;
import com.atar.enums.EnumMsgWhat;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

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
public class DemoRefreshScrollViewActivity extends AtarRefreshScrollViewActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addScrollView(R.layout.activity_demo_refersh_scrollview);
	}

	@Override
	protected void initScrollControl() {

	}

	@Override
	protected void initValue() {
		super.initValue();
		setRefreshMode(Mode.BOTH);
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
			CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {

				@Override
				public void run() {
					onStopRefresh();
				}
			}, 1000);
			break;
		case EnumMsgWhat.REFRESH_HANDLER:
			break;
		}
	}

}

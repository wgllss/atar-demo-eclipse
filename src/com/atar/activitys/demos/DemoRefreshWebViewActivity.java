/**
 * 
 */
package com.atar.activitys.demos;

import android.application.CommonApplication;
import android.common.CommonHandler;
import android.http.HttpRequest;
import android.os.Message;

import com.atar.activitys.AtarRefreshWebViewActivity;
import com.atar.enums.EnumMsgWhat;
import com.atar.widgets.ToastWhthCheck;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23上午10:27:33
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshWebViewActivity extends AtarRefreshWebViewActivity {

	@Override
	protected void initValue() {
		super.initValue();
		setActivityTitle(getIntent().getStringExtra(DemoRefreshActivity.TITLE_KEY));
		if (getRefreshView() != null) {
			getRefreshView().loadUrl("file:///android_asset/html/atar_topic5.html");
		}
	}

	@Override
	public void onHandlerData(Message msg) {
		super.onHandlerData(msg);
		switch (msg.what) {
		case EnumMsgWhat.LOAD_FROM_SQL:
			if (!HttpRequest.IsUsableNetWork(CommonApplication.getContext())) {
				ToastWhthCheck.show(this, "当前网络不可用");
			} else {
				setRefreshing();
			}
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

/**
 * 
 */
package com.atar.activitys.demos;

import java.util.HashMap;
import java.util.Map;

import android.appconfig.AppConfigSetting;
import android.content.Intent;
import android.interfaces.NetWorkCallTListenet;
import android.os.Bundle;
import android.reflection.NetWorkMsg;
import android.utils.CommonStringUtil;
import android.view.View;
import android.widget.CommonToast;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.atar.enums.EnumMsgWhat;
import com.atar.modles.WonderfulTopicJson;
import com.atar.net.NetWorkInterfaces;
import com.atar.net.UrlParamCommon;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-9下午4:04:04
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MainDemoActivity extends AtarCommonActivity {
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addContentView(R.layout.activity_maindemo);
	}

	@Override
	protected void bindEvent() {
		findViewById(R.id.btn_net_test).setOnClickListener(this);
		findViewById(R.id.btn_net_test2).setOnClickListener(this);
		findViewById(R.id.btn_viewpagerdemoactivity).setOnClickListener(this);
	}

	@Override
	protected void initValue() {
		super.initValue();
		setActivityTitle("demo主界面");
		AppConfigSetting.getInstance().saveLoginUserId("15616915");
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_net_test:
			NetWorkInterfaces.GetWonderTopicList(this, this, "1");
			break;
		case R.id.btn_net_test2:
			Map<String, String> map = new HashMap<String, String>();
			CommonStringUtil.setMap(map, "pageNo", "1");
			NetWorkInterfaces.NetWorkCall(this, new NetWorkCallTListenet<WonderfulTopicJson>() {
				@Override
				public void NetWorkCall(WonderfulTopicJson t) {
					if (t != null) {
						CommonToast.show(t.getDto().get(0).getSubject());
					}
				}
			}, UrlParamCommon.UrlWonderfulList, "GET", map, WonderfulTopicJson.class);
			break;
		case R.id.btn_viewpagerdemoactivity:
			startActivity(new Intent(this, ViewPagerDemoActivity.class));
			break;
		}
	}

	@Override
	public void NetWorkCall(NetWorkMsg msg) {
		super.NetWorkCall(msg);
		switch (msg.what) {
		case EnumMsgWhat.EInterface_Get_Wonder_Topic_List:
			if (msg.obj != null) {
				WonderfulTopicJson mWonderfulTopicJson = (WonderfulTopicJson) msg.obj;
				if (mWonderfulTopicJson != null) {
					CommonToast.show(mWonderfulTopicJson.getDto().get(1).getSubject());
				}
			}
			break;
		default:
			break;
		}
	}
}

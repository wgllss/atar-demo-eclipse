/**
 * 
 */
package com.atar.activitys.demos;

import java.util.ArrayList;
import java.util.List;

import android.appconfig.AppConfigSetting;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;

import com.atar.activitys.AtarRefreshListViewActivity;
import com.atar.adapters.MainDemoAdapter;
import com.atar.beans.MenuItemBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-22下午4:14:29
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class RefreshDemoActivity extends AtarRefreshListViewActivity {
	private List<MenuItemBean> list = new ArrayList<MenuItemBean>();
	private MainDemoAdapter mMainDemoAdapter = new MainDemoAdapter(list);

	@Override
	protected void initValue() {
		super.initValue();
		setActivityTitle("demo主界面");
		AppConfigSetting.getInstance().saveLoginUserId("15616915");
		list.add(new MenuItemBean("0", "refresh-ListView"));
		list.add(new MenuItemBean("1", "refresh-GridView"));
		list.add(new MenuItemBean("2", "refresh-ScrollView"));
		list.add(new MenuItemBean("3", "refresh-Webview"));
		list.add(new MenuItemBean("4", "refresh-PinnedSectionListView"));
		mMainDemoAdapter.notifyDataSetChanged();
		setAdapter(mMainDemoAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		super.onItemClick(arg0, arg1, arg2, arg3);
		int itemID = Integer.valueOf(list.get((int) arg3).getItemId());
		switch (itemID) {
		case 0:
			startActivity(new Intent(this, DemoRefreshListViewActivity.class));
			break;
		case 1:
			startActivity(new Intent(this, DemoRefreshGridViewActivity.class));
			break;
		case 2:
			startActivity(new Intent(this, DemoRefreshScrollViewActivity.class));
			break;
		case 3:
			startActivity(new Intent(this, DemoRefreshWebViewActivity.class));
			break;
		case 4:
			break;
		case 5:
			break;
		case 6:
			break;
		case 7:
			break;
		}
	}
}

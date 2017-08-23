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
public class DemoRefreshActivity extends AtarRefreshListViewActivity {
	public final static String TITLE_KEY = "TITLE_KEY";

	private List<MenuItemBean> list = new ArrayList<MenuItemBean>();
	private MainDemoAdapter mMainDemoAdapter = new MainDemoAdapter(list);

	@Override
	protected void initValue() {
		super.initValue();
		setActivityTitle("demo主界面");
		AppConfigSetting.getInstance().saveLoginUserId("15616915");
		list.add(new MenuItemBean("0", "refresh-ListView", DemoRefreshListViewActivity.class.getName()));
		list.add(new MenuItemBean("1", "refresh-GridView", DemoRefreshGridViewActivity.class.getName()));
		list.add(new MenuItemBean("2", "refresh-ScrollView", DemoRefreshScrollViewActivity.class.getName()));
		list.add(new MenuItemBean("3", "refresh-Webview", DemoRefreshWebViewActivity.class.getName()));
		list.add(new MenuItemBean("4", "refresh-PinnedSectionListView", DemoRefreshPinnedSectionListViewActivity.class.getName()));
		list.add(new MenuItemBean("5", "refresh-DragSortListView", DemoRefreshDragSortListViewActivity.class.getName()));
		// list.add(new MenuItemBean("4", "refresh-PinnedSectionListView", DemoRefreshPinnedSectionListViewActivity.class.getName()));
		// list.add(new MenuItemBean("4", "refresh-PinnedSectionListView", DemoRefreshPinnedSectionListViewActivity.class.getName()));
		// list.add(new MenuItemBean("4", "refresh-PinnedSectionListView", DemoRefreshPinnedSectionListViewActivity.class.getName()));
		// list.add(new MenuItemBean("4", "refresh-PinnedSectionListView", DemoRefreshPinnedSectionListViewActivity.class.getName()));
		// mMainDemoAdapter.notifyDataSetChanged();
		setAdapter(mMainDemoAdapter);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		try {
			Intent intent = new Intent(this, Class.forName(list.get((int) arg3).getItemName2()));
			intent.putExtra(TITLE_KEY, list.get((int) arg3).getMenuItemName());
			startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

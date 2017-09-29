/**
 * 
 */
package com.atar.activitys.demos;

import java.util.List;

import android.common.CommonHandler;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.ResolveInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Message;
import android.skin.SkinUtils;
import android.utils.ScreenUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CommonToast;

import com.atar.activitys.AtarRefreshSwipeMenuListViewActivity;
import com.atar.activitys.R;
import com.atar.adapters.AppAdapter;
import com.atar.enums.EnumMsgWhat;
import com.atar.widget.swipmenulistview_lib.SwipeMenu;
import com.atar.widget.swipmenulistview_lib.SwipeMenuItem;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23下午5:46:47
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoRefreshSwipeMenuListViewActivity extends AtarRefreshSwipeMenuListViewActivity {
	private List<ApplicationInfo> mAppList;
	private AppAdapter mAdapter;

	@Override
	public void create(SwipeMenu menu) {
		// create "open" item
		SwipeMenuItem openItem = new SwipeMenuItem(getApplicationContext());
		// set item background
		openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
		// set item width
		openItem.setWidth((int) ScreenUtils.dpToPx(DemoRefreshSwipeMenuListViewActivity.this, 90));
		// set item title
		openItem.setTitle("Open");
		// set item title fontsize
		openItem.setTitleSize(18);
		// set item title font color
		openItem.setTitleColor(Color.WHITE);
		// add to menu
		menu.addMenuItem(openItem);
		// create "delete" item
		SwipeMenuItem deleteItem = new SwipeMenuItem(getApplicationContext());
		// set item background
		deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
		// set item width
		deleteItem.setWidth((int) ScreenUtils.dpToPx(DemoRefreshSwipeMenuListViewActivity.this, 90));
		// set a icon
		deleteItem.setIconDrawable(SkinUtils.getDrawable(this, R.string.drawable_ic_delete));
		// add to menu
		menu.addMenuItem(deleteItem);
	}

	@Override
	protected void initValue() {
		super.initValue();
		setActivityTitle(getIntent().getStringExtra(DemoRefreshActivity.TITLE_KEY));
		mAppList = getPackageManager().getInstalledApplications(0);
		mAdapter = new AppAdapter(mAppList);
		setAdapter(mAdapter);
		// set creator
		getRefreshView().setMenuCreator(this);

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
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		CommonToast.show("点击第" + arg3);
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		CommonToast.show("onItemLongClick点击第" + arg3);
		return false;
	}

	@Override
	public void onMenuItemClick(int position, SwipeMenu menu, int index) {
		ApplicationInfo item = mAppList.get(position);
		switch (index) {
		case 0:
			// open
			open(item);
			break;
		case 1:
			// delete
			// delete(item);
			mAppList.remove(position);
			mAdapter.notifyDataSetChanged();
			break;
		}
	}

	private void open(ApplicationInfo item) {
		// open app
		Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
		resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		resolveIntent.setPackage(item.packageName);
		List<ResolveInfo> resolveInfoList = getPackageManager().queryIntentActivities(resolveIntent, 0);
		if (resolveInfoList != null && resolveInfoList.size() > 0) {
			ResolveInfo resolveInfo = resolveInfoList.get(0);
			String activityPackageName = resolveInfo.activityInfo.packageName;
			String className = resolveInfo.activityInfo.name;

			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			ComponentName componentName = new ComponentName(activityPackageName, className);

			intent.setComponent(componentName);
			startActivity(intent);
		}
	}

}

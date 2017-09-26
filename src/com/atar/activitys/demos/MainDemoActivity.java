/**
 * 
 */
package com.atar.activitys.demos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.activity.ActivityManager;
import android.activity.CommonActivity;
import android.app.Activity;
import android.appconfig.AppConfigSetting;
import android.common.CommonHandler;
import android.content.Intent;
import android.enums.SkinMode;
import android.interfaces.NetWorkCallTListenet;
import android.os.Message;
import android.reflection.NetWorkMsg;
import android.utils.CommonStringUtil;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CommonToast;

import com.atar.activitys.AtarRefreshListViewActivity;
import com.atar.activitys.R;
import com.atar.adapters.MainDemoAdapter;
import com.atar.beans.MenuItemBean;
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
public class MainDemoActivity extends AtarRefreshListViewActivity {
	private List<MenuItemBean> list = new ArrayList<MenuItemBean>();
	private MainDemoAdapter mMainDemoAdapter = new MainDemoAdapter(list);

	@Override
	protected void initValue() {
		super.initValue();
		setOnDrawerBackEnabled(false);

		setActivityTitle("demo主界面");
		AppConfigSetting.getInstance().saveLoginUserId("15616915");
		list.add(new MenuItemBean("0", "网络测试1"));
		list.add(new MenuItemBean("1", "网络测试2"));
		list.add(new MenuItemBean("2", "ViewPagerDemoActivity"));
		list.add(new MenuItemBean("3", "相册"));
		// list.add(new MenuItemBean("4", "DemoRefreshListViewActivity"));
		list.add(new MenuItemBean("5", "图片浏览"));
		list.add(new MenuItemBean("6", "下载"));
		list.add(new MenuItemBean("7", "刷新"));
		list.add(new MenuItemBean("8", "夜间模式"));
		// list.add(new MenuItemBean("8", "网络测试1"));
		mMainDemoAdapter.notifyDataSetChanged();
		setAdapter(mMainDemoAdapter);
	}

	@Override
	public void onHandlerData(Message msg) {
		super.onHandlerData(msg);
		switch (msg.what) {
		case EnumMsgWhat.REFRESH_PULL_DOWN:
		case EnumMsgWhat.REFRESH_PULL_UP:
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		super.onItemClick(arg0, arg1, arg2, arg3);
		int itemID = Integer.valueOf(list.get((int) arg3).getItemId());
		switch (itemID) {
		case 0:
			NetWorkInterfaces.GetWonderTopicList(this, this, "1");
			break;
		case 1:
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
		case 2:
			startActivity(new Intent(this, ViewPagerDemoActivity.class));
			break;
		case 3:
			startActivity(new Intent(this, AlbumActivity.class));
			break;
		case 4:
			startActivity(new Intent(this, DemoRefreshListViewActivity.class));
			break;
		case 5:
			ArrayList<String> imgList = new ArrayList<String>();
			imgList.add("http://img4.imgtn.bdimg.com/it/u=1906744648,758477532&fm=26&gp=0.jpg");
			imgList.add("http://img0.imgtn.bdimg.com/it/u=3368323297,2417132385&fm=26&gp=0.jpg");
			imgList.add("http://img2.imgtn.bdimg.com/it/u=917052547,4265044967&fm=26&gp=0.jpg");
			ShowImageActivity.startShowImage(this, imgList, 1);
			break;
		case 6:
			startActivity(new Intent(this, DownLoadActivity.class));
			break;
		case 7:
			startActivity(new Intent(this, DemoRefreshActivity.class));
			break;
		case 8:

			int skinType = 0;
			if (getCurrentSkinType() == SkinMode.DAY_MODE) {// 白天模式
				skinType = SkinMode.NIGHT_MODE;
				list.get((int) arg3).setMenuItemName("白天模式");
			} else {// 晚上模式
				skinType = SkinMode.DAY_MODE;
				list.get((int) arg3).setMenuItemName("夜间模式");
			}
			AppConfigSetting.getInstance().putInt(SkinMode.SKIN_MODE_KEY, skinType);
			for (Activity activity : ActivityManager.getActivityManager().getActivityStack()) {
				if (activity instanceof CommonActivity) {
					((CommonActivity) activity).loadSkin(skinType);
				}
			}
			mMainDemoAdapter.notifyDataSetChanged();
			break;
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.img_common_top_left:// 顶部左边
			onBackPressed();
			break;
		default:
			super.onClick(v);
			break;
		}
	}

	@Override
	public void onBackPressed() {
		runOnBackground();
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		if (mMainDemoAdapter != null) {
			mMainDemoAdapter.setSkinType(skinType);
		}
	}
}

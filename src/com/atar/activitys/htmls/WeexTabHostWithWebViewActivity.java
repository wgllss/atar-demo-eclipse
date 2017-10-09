/**
 * 
 */
package com.atar.activitys.htmls;

import java.util.ArrayList;
import java.util.List;

import android.activity.ActivityManager;
import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.appconfig.AppConfigSetting;
import android.content.Intent;
import android.enums.SkinMode;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.skin.SkinUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TabHost;

import com.atar.activitys.R;
import com.atar.adapters.WeexTabAdapter;
import com.atar.config.AppConfigUtils;
import com.atar.config.OnClickInfo;
import com.atar.config.TabMenuItemBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-6-22下午2:41:33
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressWarnings("deprecation")
public class WeexTabHostWithWebViewActivity extends TabActivity {

	private String TAB_OPTION_JSON_KEY = "TAB_OPTION_JSON_KEY";

	private TabHost mTabHost;
	private GridView buttomGridView;
	/* 当前皮肤类型 默认为白天模式 */
	private int currentSkinType = SkinMode.DAY_MODE;
	private List<TabMenuItemBean> menuList = new ArrayList<TabMenuItemBean>();
	private WeexTabAdapter mMenuAdapter = new WeexTabAdapter(menuList);

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weex_webview_tabhost);
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
		}
		ActivityManager.getActivityManager().pushActivity(this);
		mTabHost = getTabHost();
		String tab_option_json = getIntent().getStringExtra(TAB_OPTION_JSON_KEY);
		if (tab_option_json == null) {
			return;
		}
		Gson gson = new Gson();
		List<TabMenuItemBean> list = gson.fromJson(tab_option_json, new TypeToken<List<TabMenuItemBean>>() {
		}.getType());
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				try {
					OnClickInfo onClickInfo = list.get(i).getOnClickInfo();
					if (onClickInfo != null) {
						Intent intent = new Intent(this, Class.forName(onClickInfo.getClassName()));
						String optionJson = list.get(i).getOnClickInfo().getOptionJson();
						String onEventInfo = list.get(i).getOnClickInfo().getOnEventInfo();
						intent = AppConfigUtils.getIntentFromOptionJson(this, intent, optionJson, onEventInfo);
						mTabHost.addTab(mTabHost.newTabSpec("" + i).setIndicator("" + i).setContent(intent));
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mTabHost.setCurrentTab(0);
			menuList.clear();
			menuList.addAll(list);
		}

		if (menuList.size() == 0) {
			return;
		}
		buttomGridView = (GridView) findViewById(R.id.common_buttom_menu);
		buttomGridView.setNumColumns(menuList.size());
		mMenuAdapter.setCurrentPostiotn(0);
		buttomGridView.setAdapter(mMenuAdapter);
		buttomGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				try {
					mMenuAdapter.setCurrentPostiotn(arg2);
					mTabHost.setCurrentTab(arg2);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		currentSkinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
		OnChangeSkin(currentSkinType);
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		currentSkinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
		OnChangeSkin(currentSkinType);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		ActivityManager.getActivityManager().finishActivity(this.getClass());
	}

	/**
	 * 设置小红点数字
	 */
	public void setNewInfoNum(int position, int num) {
		if (menuList != null && menuList.size() > position) {
			menuList.get(position).setInfoNum(num);
			if (mMenuAdapter != null) {
				mMenuAdapter.notifyDataSetChanged();
			}
		}
	}

	/**
	 * 切换tab
	 */
	public void setCurrentTab(int position) {
		if (mTabHost != null) {
			mMenuAdapter.setCurrentPostiotn(position);
			mTabHost.setCurrentTab(position);
		}
	}

	public void OnChangeSkin(int skinType) {
		SkinUtils.setBackgroundColor(this, R.string.common_tab_bg_color, skinType, buttomGridView);
		SkinUtils.setBackgroundColor(this, R.string.common_tab_line_move_color, skinType, findViewById(R.id.common_line));
		if (mMenuAdapter != null) {
			mMenuAdapter.setSkinType(skinType);
		}
	}
}

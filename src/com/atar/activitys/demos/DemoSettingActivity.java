package com.atar.activitys.demos;

import java.io.File;

import android.activity.ActivityManager;
import android.activity.CommonActivity;
import android.app.Activity;
import android.appconfig.AppConfigSetting;
import android.content.res.Resources;
import android.download.DownLoadFileManager;
import android.enums.SkinMode;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.skin.SkinResourcesManager;
import android.skin.SkinUtils;
import android.skin.SkinResourcesManager.loadSkinCallBack;
import android.utils.FileUtils;
import android.utils.MDPassword;
import android.utils.ShowLog;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import com.atar.activitys.AtarRefreshScrollViewActivity;
import com.atar.activitys.R;
import com.atar.widgets.DownloadProgressButton;
import com.atar.widgets.DownloadProgressButton.OnDownLoadClickListener;
import com.zf.view.UISwitchButton;

/**
 * ****************************************************************************************************************************************************************************
 *
 * @author :Atar
 * @createTime:2017-09-30-上午10:26
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: ****************************************************************************************************************************************************************************
 */

public class DemoSettingActivity extends AtarRefreshScrollViewActivity implements OnCheckedChangeListener, OnDownLoadClickListener {
	private String TAG = DemoSettingActivity.class.getSimpleName();

	private UISwitchButton common_ui_switch_button;

	/**SD卡目录 下载 资源文件 皮肤资源*/
	private String SD_PATH = Environment.getExternalStorageDirectory() + "/.Android/.cache/.";

	private DownloadProgressButton mDownloadProgressButton;
	// https://raw.githubusercontent.com/wgllss/atar-skin-eclipse/master/skin/purple/download_skin.apk
	private String downloadUrl = "https://raw.githubusercontent.com/wgllss/atar-skin-eclipse/master/skin/purple/download_skin.apk";
	private String strDownloadFileName = "purple";
	private String strDownloadDir;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addScrollView(R.layout.activity_setting);
	}

	@Override
	protected void initScrollControl() {
		common_ui_switch_button = (UISwitchButton) findViewById(R.id.common_ui_switch_button);
		mDownloadProgressButton = (DownloadProgressButton) findViewById(R.id.down_btn);
		if (getCurrentSkinType() == SkinMode.DAY_MODE) {
			common_ui_switch_button.setChecked(false);
		} else {
			common_ui_switch_button.setChecked(true);
		}
	}

	@Override
	protected void initScrollInitValue() {
		super.initScrollInitValue();
		SD_PATH = SD_PATH + MDPassword.getPassword32(getPackageName()) + "/";
		strDownloadDir = SD_PATH;
		strDownloadFileName = MDPassword.getPassword32(strDownloadFileName);

		mDownloadProgressButton.setEnablePause(false);
		mDownloadProgressButton.setButtonRadius(15f);
	}

	@Override
	protected void initScrollBindEvent() {
		super.initScrollBindEvent();
		common_ui_switch_button.setOnCheckedChangeListener(this);
		mDownloadProgressButton.setOnDownLoadClickListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		int skinType = 0;
		if (isChecked) {
			skinType = SkinMode.NIGHT_MODE;
		} else {
			skinType = SkinMode.DAY_MODE;
		}
		AppConfigSetting.getInstance().putInt(SkinMode.SKIN_MODE_KEY, skinType);
		for (Activity activity : ActivityManager.getActivityManager().getActivityStack()) {
			if (activity instanceof CommonActivity) {
				((CommonActivity) activity).loadSkin(skinType);
			}
		}
	}

	@Override
	public void onHandlerData(Message msg) {
		super.onHandlerData(msg);
		switch (msg.what) {
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_FAIL:
			ShowLog.i(TAG, msg.arg2 + "---" + "下载失败");
			break;
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_SUCCESS:
			mDownloadProgressButton.setProgress(100);
			ShowLog.i(TAG, msg.arg2 + "---" + "下载成功");
			ShowLog.i(TAG, msg.arg2 + "---" + "下载目录:" + strDownloadDir + strDownloadFileName);
			break;
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_ING:
			int progress = (Integer) msg.obj;
			mDownloadProgressButton.setProgress(progress);
			break;
		}
	}

	@Override
	public void clickDownload(View v) {
		DownLoadFileManager.getInstance().downLoad(this, this, 0, downloadUrl, strDownloadFileName, strDownloadDir);
	}

	@Override
	public void clickPause(View v) {
		DownLoadFileManager.getInstance().pauseDownload(0);
	}

	@Override
	public void clickResume(View v) {
		clickDownload(v);
	}

	@Override
	public void clickFinish(View v) {
		final File oldDownloadFile = new File(SD_PATH + MDPassword.getPassword32("download_skin"));
		oldDownloadFile.deleteOnExit();
		FileUtils.copyFile(SD_PATH + MDPassword.getPassword32(strDownloadFileName), SD_PATH + MDPassword.getPassword32("download_skin"));
		SkinResourcesManager.getInstance(this).loadSkinResources(new loadSkinCallBack() {

			@Override
			public void loadSkinSuccess(Resources mResources) {
				ChangeSkin(getCurrentSkinType());
			}
		});
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		SkinUtils.setTextColor(this, R.string.txt_day_black_night_greywhite_color, skinType, (TextView) findViewById(R.id.txt_yejianmoshi));
		mDownloadProgressButton.setTextColor(SkinUtils.getArrayColor(this, R.string.txt_day_black_night_greywhite_color, skinType));
		mDownloadProgressButton.setTextCoverColor(SkinUtils.getArrayColor(this, R.string.common_activity_title_color, skinType));
		mDownloadProgressButton.setBackgroundColor(SkinUtils.getArrayColor(this, R.string.common_top_title_bar_bg_color, skinType));
		mDownloadProgressButton.setBackgroundSecondColor(SkinUtils.getArrayColor(this, R.string.common_tab_bg_color, skinType));
	}
}

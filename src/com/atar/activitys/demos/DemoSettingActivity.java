package com.atar.activitys.demos;

import java.io.File;

import android.activity.ActivityManager;
import android.activity.CommonActivity;
import android.app.Activity;
import android.appconfig.AppConfigSetting;
import android.common.CommonHandler;
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
import com.atar.enums.EnumMsgWhat;
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

	private DownloadProgressButton[] mDownloadProgressButtons = new DownloadProgressButton[4];
	private int[] resIDs = { R.id.down_btn_purple, R.id.down_btn_red, R.id.down_btn_green, R.id.down_btn_blue };

	// https://raw.githubusercontent.com/wgllss/atar-skin-eclipse/master/skin/purple/download_skin.apk
	private String[] downloadUrls = { "https://raw.githubusercontent.com/wgllss/atar-skin-eclipse/master/skin/purple/download_skin.apk",
			"https://raw.githubusercontent.com/wgllss/atar-skin-eclipse/master/skin/red/download_skin.apk",
			"https://raw.githubusercontent.com/wgllss/atar-skin-eclipse/master/skin/green/download_skin.apk",
			"https://raw.githubusercontent.com/wgllss/atar-skin-eclipse/master/skin/blue/download_skin.apk" };

	private String[] strDownloadFileNames = { "purple", "red", "green", "blue" };
	private String strDownloadDir;

	private String[] downloadTexts = { "下载紫色皮肤", "下载红色皮肤", "下载绿色皮肤", "下载蓝色皮肤" };
	private String[] loadTexts = { "加载紫色皮肤", "加载红色皮肤", "加载绿色皮肤", "加载蓝色皮肤" };

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addScrollView(R.layout.activity_setting);
	}

	@Override
	protected void initScrollControl() {
		common_ui_switch_button = (UISwitchButton) findViewById(R.id.common_ui_switch_button);
	}

	@Override
	protected void initScrollInitValue() {
		super.initScrollInitValue();
		SD_PATH = SD_PATH + MDPassword.getPassword32(getPackageName()) + "/";
		strDownloadDir = SD_PATH;

		for (int i = 0; i < mDownloadProgressButtons.length; i++) {
			mDownloadProgressButtons[i] = (DownloadProgressButton) findViewById(resIDs[i]);
			strDownloadFileNames[i] = MDPassword.getPassword32(strDownloadFileNames[i]);

			mDownloadProgressButtons[i].setEnablePause(false);
			mDownloadProgressButtons[i].setButtonRadius(15f);
			mDownloadProgressButtons[i].setCurrentText(downloadTexts[i]);
			DownLoadFileManager.getInstance().initTempFilePercent(i, this, downloadUrls[i], strDownloadFileNames[i], strDownloadDir);

			mDownloadProgressButtons[i].setOnDownLoadClickListener(this);
		}

		if (getCurrentSkinType() == SkinMode.DAY_MODE) {
			common_ui_switch_button.setChecked(false);
		} else {
			common_ui_switch_button.setChecked(true);
		}
	}

	@Override
	protected void initScrollBindEvent() {
		super.initScrollBindEvent();
		common_ui_switch_button.setOnCheckedChangeListener(this);

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
			mDownloadProgressButtons[msg.arg2].setProgress(100);
			mDownloadProgressButtons[msg.arg2].finish();
			mDownloadProgressButtons[msg.arg2].setCurrentText(loadTexts[msg.arg2]);
			ShowLog.i(TAG, msg.arg2 + "---" + "下载成功");
			break;
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_ING:
			int progress = (Integer) msg.obj;
			mDownloadProgressButtons[msg.arg2].setProgress(progress);
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
	public void clickDownload(View v) {
		switch (v.getId()) {
		case R.id.down_btn_purple:
			DownLoadFileManager.getInstance().downLoad(this, this, 0, downloadUrls[0], strDownloadFileNames[0], strDownloadDir);
			break;
		case R.id.down_btn_red:
			DownLoadFileManager.getInstance().downLoad(this, this, 1, downloadUrls[1], strDownloadFileNames[1], strDownloadDir);
			break;
		case R.id.down_btn_green:
			DownLoadFileManager.getInstance().downLoad(this, this, 2, downloadUrls[2], strDownloadFileNames[2], strDownloadDir);
			break;
		case R.id.down_btn_blue:
			DownLoadFileManager.getInstance().downLoad(this, this, 3, downloadUrls[3], strDownloadFileNames[3], strDownloadDir);
			break;
		}
	}

	@Override
	public void clickPause(View v) {
		switch (v.getId()) {
		case R.id.down_btn_purple:
			DownLoadFileManager.getInstance().pauseDownload(0);
			break;
		case R.id.down_btn_red:
			DownLoadFileManager.getInstance().pauseDownload(1);
			break;
		case R.id.down_btn_green:
			DownLoadFileManager.getInstance().pauseDownload(2);
			break;
		case R.id.down_btn_blue:
			DownLoadFileManager.getInstance().pauseDownload(3);
			break;
		}

	}

	@Override
	public void clickResume(View v) {
		clickDownload(v);
	}

	@Override
	public void clickFinish(View v) {
		final File oldDownloadFile = new File(SD_PATH + MDPassword.getPassword32("download_skin"));
		oldDownloadFile.deleteOnExit();
		switch (v.getId()) {
		case R.id.down_btn_purple:
			FileUtils.copyFile(SD_PATH + strDownloadFileNames[0], SD_PATH + MDPassword.getPassword32("download_skin"));
			break;
		case R.id.down_btn_red:
			FileUtils.copyFile(SD_PATH + strDownloadFileNames[1], SD_PATH + MDPassword.getPassword32("download_skin"));
			break;
		case R.id.down_btn_green:
			FileUtils.copyFile(SD_PATH + strDownloadFileNames[2], SD_PATH + MDPassword.getPassword32("download_skin"));
			break;
		case R.id.down_btn_blue:
			FileUtils.copyFile(SD_PATH + strDownloadFileNames[3], SD_PATH + MDPassword.getPassword32("download_skin"));
			break;
		}

		SkinResourcesManager.getInstance(this).loadSkinResources(new loadSkinCallBack() {

			@Override
			public void loadSkinSuccess(Resources mResources) {
				if (getCurrentSkinType() == SkinMode.NIGHT_MODE) {
					AppConfigSetting.getInstance().putInt(SkinMode.SKIN_MODE_KEY, SkinMode.DAY_MODE);
					CommonHandler.getInstatnce().getHandler().post(new Runnable() {
						@Override
						public void run() {
							common_ui_switch_button.setChecked(false);
						}
					});

				}
				for (final Activity activity : ActivityManager.getActivityManager().getActivityStack()) {
					if (activity instanceof CommonActivity) {
						CommonHandler.getInstatnce().getHandler().post(new Runnable() {
							@Override
							public void run() {
								((CommonActivity) activity).ChangeSkin(getCurrentSkinType());
							}
						});
					}
				}
			}
		});
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		SkinUtils.setTextColor(this, R.string.txt_day_black_night_greywhite_color, skinType, (TextView) findViewById(R.id.txt_yejianmoshi));
		for (int i = 0; i < mDownloadProgressButtons.length; i++) {
			mDownloadProgressButtons[i].setTextColor(SkinUtils.getArrayColor(this, R.string.txt_day_black_night_greywhite_color, skinType));
			mDownloadProgressButtons[i].setTextCoverColor(SkinUtils.getArrayColor(this, R.string.common_activity_title_color, skinType));
			mDownloadProgressButtons[i].setBackgroundColor(SkinUtils.getArrayColor(this, R.string.common_top_title_bar_bg_color, skinType));
			mDownloadProgressButtons[i].setBackgroundSecondColor(SkinUtils.getArrayColor(this, R.string.common_tab_bg_color, skinType));
			final int index = i;
			CommonHandler.getInstatnce().getHandler().post(new Runnable() {
				@Override
				public void run() {
					mDownloadProgressButtons[index].invalidate();
				}
			});
		}
	}
}

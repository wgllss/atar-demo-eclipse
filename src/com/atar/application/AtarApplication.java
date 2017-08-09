/**
 * 
 */
package com.atar.application;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.application.CommonApplication;
import android.application.CrashHandler;
import android.content.Context;
import android.interfaces.CommonNetWorkExceptionToast;
import android.os.Build;
import android.support.multidex.MultiDex;
import android.utils.ShowLog;
import android.widget.CommonToast;

import com.atar.activity.R;
import com.lidroid.xutils.DbUtils;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.mm.sdk.openapi.IWXAPI;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-9下午2:54:23
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarApplication extends Application {
	public static AtarApplication mInstance;
	private DbUtils db;
	public static String JSESSIONID;
	/* 微信登陆 */
	private IWXAPI api;

	@Override
	protected void attachBaseContext(Context base) {
		super.attachBaseContext(base);
		MultiDex.install(this);
	}

	@TargetApi(Build.VERSION_CODES.GINGERBREAD)
	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
		CommonApplication.initApplication(this);// 初始化全局Context
		CommonNetWorkExceptionToast.initToastError(this, R.array.err_toast_string);// 初始化全局网络错误提示信息
		ShowLog.setDebug(true);// 设置不显示日志 上线前记得改成false
		CommonNetWorkExceptionToast.setIsShowErrorToast(true);// 上线前记得设置不显示错误网络具体提示 测试时可开启
		CommonApplication.initImageLoader(getApplicationContext());// 初始化加载图片配置
		CommonToast.initToastResouseId(R.drawable.corners_toast, R.color.black);// 初始化toast字体颜色和背景
		CrashHandler.getInstance().init(this);// 接收错误异常
		db = getDb();
		initHotfix();
	}

	public static AtarApplication getApplication() {
		return mInstance;
	}

	public DbUtils getDb() {
		if (db == null) {
			db = DbUtils.create(this);
			db.configAllowTransaction(true);
			db.configDebug(false);
		}
		return db;
	}

	public IWXAPI getWxApi(Activity activity) {
		if (api == null) {
			// api = WXAPIFactory.createWXAPI(activity, GlobeSettings.WeiXin_AppID, false);
		}
		return api;
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		// UrlParamCommon.JSESSIONID = "";
	}

	@Override
	public void onLowMemory() {
		super.onLowMemory();
		// UrlParamCommon.JSESSIONID = "";
	}

	/**
	 * 阿里热修复功能
	 * @author :Atar
	 * @createTime:2017-8-4下午5:49:44
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	private void initHotfix() {
		String appVersion;
		try {
			appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
		} catch (Exception e) {
			appVersion = "1.0.0";
		}
		SophixManager.getInstance().setContext(this).setAppVersion(appVersion).setAesKey(null).setEnableDebug(true).setPatchLoadStatusStub(new PatchLoadStatusListener() {
			@Override
			public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
				ShowLog.i("TaogubaApplication", "code-->" + code + "--info-->" + info + "--handlePatchVersion-->" + handlePatchVersion);
				switch (code) {
				case PatchStatus.CODE_LOAD_SUCCESS:// 1 表明补丁加载成功
					break;
				case PatchStatus.CODE_LOAD_RELAUNCH:// 12 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
					break;
				case PatchStatus.CODE_LOAD_FAIL:// 13 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
					SophixManager.getInstance().cleanPatches();
					break;
				}
			}
		}).initialize();
		SophixManager.getInstance().queryAndLoadNewPatch();
	}
}

/**
 * 
 */
package com.atar.htmls;

import java.io.InputStream;

import android.annotation.SuppressLint;
import android.appconfig.AppConfigModel;
import android.utils.ShowLog;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;

import com.atar.activitys.AtarCommonActivity;
import com.atar.config.AppConfigUtils;
import com.atar.widgets.refresh.OnHandlerDataListener;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-6-2上午10:37:14
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class OffineImplWebViewClient extends ImplWebViewClient {
	/**assets离线文件*/
	private String strOfflineResources = "";

	public OffineImplWebViewClient(AtarCommonActivity activity, OnHandlerDataListener<PullToRefreshWebView, WebView> onHandlerDataListener) {
		super(activity, onHandlerDataListener);
		strOfflineResources = AppConfigModel.getInstance().getString(AppConfigUtils.OFFINE_FILE_PATH_KEY, "");
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("NewApi")
	@Override
	public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
		ShowLog.i(TAG, "shouldInterceptRequest thread id: " + Thread.currentThread().getId() + "---url-->" + url);
		int lastSlash = url.lastIndexOf("/");
		if (lastSlash != -1) {
			String suffix = url.substring(lastSlash + 1);
			if (strOfflineResources != null && strOfflineResources.length() > 0 && suffix != null && suffix.length() > 0 && strOfflineResources.contains(suffix) && activity != null) {
				String mimeType = "";
				String offline_res = "";
				if (suffix.endsWith(".js")) {
					mimeType = "application/x-javascript";
					offline_res = "js/";
				} else if (suffix.endsWith(".css")) {
					mimeType = "text/css";
					offline_res = "css/";
				} else if (suffix.endsWith(".png")) {
					mimeType = "image/png";
					offline_res = "img/";// 主要加载预置表情
				} else {
					if (url.contains("img/null")) {
						return super.shouldInterceptRequest(view, url);
					} else {
						mimeType = "text/html";
					}
				}
				try {
					InputStream is = activity.getAssets().open(offline_res + suffix);
					return new WebResourceResponse(mimeType, "UTF-8", is);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return super.shouldInterceptRequest(view, url);
	}
}

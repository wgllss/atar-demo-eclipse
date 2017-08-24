package com.atar.utils;

import android.app.Activity;
import android.appconfig.AppConfigSetting;
import android.content.Context;
import android.utils.ShowLog;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

import com.atar.net.UrlParamCommon;

public class CookieTool {
	private static String TAG = CookieTool.class.getSimpleName();
	public static final String DOMAIN = ".taoguba.com.cn";

	public static String getCookieStr(String cookieName) {
		StringBuilder sb = new StringBuilder(cookieName);
		sb.append("=");
		// if (cookieName.equals("JSESSIONID")) {
		// sb.append(UrlParamCommon.JSESSIONID);
		// }
		String userId = AppConfigSetting.getInstance().getUserId();
		if (!"".equals(userId)) {
			if (cookieName.equals("atar_user")) {
				sb.append(userId);
			}
			if (cookieName.equals("atar_pwd")) {
				sb.append(AppConfigSetting.getInstance().getPassword());
			}
		}
		sb.append(";path=/;domain=").append(DOMAIN).append(";");
		return sb.toString();
	}

	/**
	 * 同步cookie webview
	 * @author :Atar
	 * @createTime:2015-5-28下午6:18:18
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @param url
	 * @description:
	 */
	public static void synCookies(Context context, String url, WebView webview) {
		CookieSyncManager.createInstance(context);
		CookieManager cookieManager = CookieManager.getInstance();
		cookieManager.removeSessionCookie();// 移除
		// cookieManager.removeAllCookie();
		// if (VERSION.SDK_INT > VERSION_CODES.KITKAT) {
		// cookieManager.setAcceptThirdPartyCookies(webview, true);
		// }
		cookieManager.setAcceptCookie(true);
		cookieManager.setCookie(url, CookieTool.getCookieStr("JSESSIONID"));// cookies是在HttpClient中获得的cookie
		cookieManager.setCookie(url, CookieTool.getCookieStr("atar_user"));// cookies是在HttpClient中获得的cookie
		cookieManager.setCookie(url, CookieTool.getCookieStr("atar_pwd"));// cookies是在HttpClient中获得的cookie
		StringBuilder sb = new StringBuilder("token");
		sb.append("=");
		sb.append(AppConfigSetting.getInstance().getToken());
		sb.append(";path=/;domain=").append(DOMAIN).append(";");
		cookieManager.setCookie(url, sb.toString());// 设置token
		CookieSyncManager.getInstance().sync();
	}

	/**
	 * 从webview 得到cookie
	 * @author :Atar
	 * @createTime:2015-5-28下午6:42:55
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param url
	 * @description:
	 */
	public static void getCookieFromWebViewUrl(Activity activity, String url) {
		CookieManager cookieManager = CookieManager.getInstance();
		String strCookie = cookieManager.getCookie(url);
		ShowLog.i(TAG, "strCookie---->" + strCookie);
		String atar_user = "";
		String atar_pwd = "";
		if (strCookie != null && strCookie.length() > 0) {
			String[] cookies = strCookie.split(";");
			if (cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i] != null && cookies[i].length() > 0) {
						if (cookies[i].contains("JSESSIONID")) {
							String[] valueName = cookies[i].split("=");
							if (valueName != null && valueName.length > 1) {
								String JSESSIONID = valueName[1];
								// UrlParamCommon.JSESSIONID = JSESSIONID;
								ShowLog.i(TAG, "得到jsessionID---------->" + JSESSIONID);
							}
						}
						if (cookies[i].contains("atar_user")) {
							String[] valueName = cookies[i].split("=");
							if (valueName != null && valueName.length > 1) {
								atar_user = valueName[1];
							}
						}
						if (cookies[i].contains("atar_pwd")) {
							String[] valueName = cookies[i].split("=");
							if (valueName != null && valueName.length > 1) {
								atar_pwd = valueName[1];
							}
						}
					}
				}
			}
		}
		if (atar_user == null || atar_user.length() == 0) {// 退出登陆了
			// LoginTool.getInstance().loginOut(activity);
		} else {
			ShowLog.i(TAG, "得到atar_user---------->" + atar_user);
			if (atar_user != null && atar_user.length() > 0) {
				AppConfigSetting.getInstance().saveLoginUserId(atar_user);
			}
			ShowLog.i(TAG, "得到atar_pwd---------->" + atar_pwd);
			if (atar_pwd != null && atar_pwd.length() > 0) {
				AppConfigSetting.getInstance().saveLoginPassword(atar_pwd);
			}
		}
	}
}

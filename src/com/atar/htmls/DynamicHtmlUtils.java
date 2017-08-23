/**
 * 
 */
package com.atar.htmls;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import android.appconfig.AppConfigSetting;
import android.application.CrashHandler;
import android.enums.TypefaceMode;
import android.http.HttpRequest;
import android.os.Environment;
import android.reflection.ThreadPoolTool;
import android.utils.CommonStringUtil;
import android.utils.FileUtils;
import android.utils.MDPassword;
import android.utils.ShowLog;
import android.webkit.WebView;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.atar.widgets.ToastWhthCheck;
import com.atar.widgets.refresh.OnHandlerDataListener;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

/**
 *****************************************************************************************************************************************************************************
 * 动态webview 处理工具
 * @author :Atar
 * @createTime:2017-6-28下午3:28:37
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DynamicHtmlUtils {
	private static String TAG = DynamicHtmlUtils.class.getSimpleName();
	private static String SAVE_HTML_PATH = Environment.getExternalStorageDirectory() + "/.Android/.cache/.jnkmlhtfilecj/";
	/**存储文件状态，让每启动一次app 保存html文件一次，删除上次启动保存的文件*/
	public static Map<String, String> map = new HashMap<String, String>();

	/**
	 * 初始化动态webview 所有加载项
	 * @author :Atar
	 * @createTime:2017-7-28上午11:39:05
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param activity
	 * @param handler
	 * @param mPullToRefreshWebView
	 * @param options
	 * @param mode
	 * @param url
	 * @param skinType
	 * @param mImplOnTouchChanceTextSizeListener
	 * @return
	 * @description:
	 */
	public static String getInitValue(AtarCommonActivity activity, OnHandlerDataListener<PullToRefreshWebView, WebView> onHandlerDataListener, PullToRefreshWebView mPullToRefreshWebView,
			String options, String mode, String url, int skinType, ImplOnTouchChanceTextSizeListener mImplOnTouchChanceTextSizeListener) {
		String optionsJson = "";
		/* 向html传入初始参数 start */
		try {
			Map<String, String> optionsMap = new HashMap<String, String>();
			optionsMap.put("skinType", Integer.toString(skinType));
			String isLoadImage = "Y";// AppConfigSetting.getInstance().getBoolean(GlobeSettings.SHOW_IMAGE_KEY, true) ? "Y" : "N";

			optionsMap.put("isLoadImage", isLoadImage);
			if (options != null && options.length() > 0) {
				JSONObject jsonObject;
				jsonObject = new JSONObject(options);
				if (jsonObject != null) {
					Iterator<String> iterator = jsonObject.keys();
					while (iterator.hasNext()) {
						String key = (String) iterator.next();
						String value = jsonObject.getString(key);
						optionsMap.put(key, value);
					}
				}
			}
			optionsJson = new Gson().toJson(optionsMap);
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
		optionsJson = CommonStringUtil.emptyIfNull(optionsJson);
		/* 向html传入初始参数 end */

		OffineImplWebViewClient mOffineImplWebViewClient = new OffineImplWebViewClient(activity, onHandlerDataListener);
		if (mPullToRefreshWebView != null && mPullToRefreshWebView.getRefreshableView() != null) {
			mImplOnTouchChanceTextSizeListener = new ImplOnTouchChanceTextSizeListener(mPullToRefreshWebView, activity);
			mImplOnTouchChanceTextSizeListener.setTypefaceModeStasus(AppConfigSetting.getInstance().getString(TypefaceMode.TYPE_FACE_MODE_KEY.getValue(), TypefaceMode.MIDDLE.getValue()));
			// mImplOnTouchChanceTextSizeListener.setWebViewLoadSuccess(false);
			mPullToRefreshWebView.getRefreshableView().setOnTouchListener(mImplOnTouchChanceTextSizeListener);

			/* 刷新模式 0:上下都可以刷新 1:只上面可下拉 2:只有下面可上拉 start */
			if ("0".equals(mode)) {
				mPullToRefreshWebView.setMode(Mode.BOTH);
			} else if ("1".equals(mode)) {
				mPullToRefreshWebView.setMode(Mode.PULL_FROM_START);
			} else if ("2".equals(mode)) {
				mPullToRefreshWebView.setMode(Mode.PULL_FROM_END);
			} else if ("3".equals(mode)) {
				mPullToRefreshWebView.setMode(Mode.DISABLED);
			}
			/* 刷新模式 end */

			mOffineImplWebViewClient.initWebViewSettings(mPullToRefreshWebView.getRefreshableView());

			mPullToRefreshWebView.getRefreshableView().addJavascriptInterface(new ImplInAndroidScript(activity, onHandlerDataListener), "injs");

			mPullToRefreshWebView.getRefreshableView().setWebViewClient(mOffineImplWebViewClient);
			mPullToRefreshWebView.getRefreshableView().setWebChromeClient(new ImplWebChromeClient(activity));

			if (url != null && url.length() > 0) {
				// if (url.contains("taoguba")) {
				// CookieTool.synCookies(activity, url, mPullToRefreshWebView.getRefreshableView());
				// }
				ShowLog.i(TAG, "webView--url--->" + url);
				if (ShowLog.mDebug) {// 测试的时候直接加载服务器上的
					if (HttpRequest.IsUsableNetWork(activity)) {
						mPullToRefreshWebView.getRefreshableView().loadUrl(url);
					} else {
						loadLocalHtml(activity, mPullToRefreshWebView, url);
					}
				} else {
					loadLocalHtml(activity, mPullToRefreshWebView, url);
				}
				if (!map.containsKey(url) && HttpRequest.IsUsableNetWork(activity)) {
					saveHtml(url);
					map.put(url, "1");
				}
			}
		}
		return optionsJson;
	}

	/**
	 * 加载本地html
	 * @author :Atar
	 * @createTime:2017-7-27下午4:38:19
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param url
	 * @description:
	 */
	public static void loadLocalHtml(AtarCommonActivity activity, PullToRefreshWebView mPullToRefreshWebView, String url) {
		try {
			File file = new File(SAVE_HTML_PATH);
			if (!FileUtils.exists(SAVE_HTML_PATH)) {
				FileUtils.createDir(SAVE_HTML_PATH);
			}
			String strLocalFileName = url.substring(url.lastIndexOf("/") + 1, url.length()).replace(".html", "");
			File htmlFile = new File(file.getAbsolutePath(), MDPassword.getPassword32(strLocalFileName));
			if (htmlFile.exists()) {
				mPullToRefreshWebView.getRefreshableView().loadUrl("file:///" + htmlFile.getAbsolutePath());
			} else {
				if (HttpRequest.IsUsableNetWork(activity)) {
					mPullToRefreshWebView.getRefreshableView().loadUrl(url);
				} else {
					ToastWhthCheck.show(activity, activity.getResources().getString(R.string.emobilenetuseless_msg));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 存储html文件
	 * @author :Atar
	 * @createTime:2017-7-26下午4:19:43
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param url
	 * @description:
	 */
	public static void saveHtml(final String htmlUrl) {
		ThreadPoolTool.getInstance().execute(new Runnable() {
			@Override
			public void run() {
				try {
					if (htmlUrl.contains(".html")) {
						URL url = new URL(htmlUrl);
						HttpURLConnection conn = (HttpURLConnection) url.openConnection();
						conn.setConnectTimeout(5000);
						InputStream instream = conn.getInputStream();
						if (instream != null) {

							String result = "";
							InputStreamReader inputreader = new InputStreamReader(instream);
							BufferedReader buffreader = new BufferedReader(inputreader);
							String line;
							// 分行读取
							while ((line = buffreader.readLine()) != null) {
								result += "\n" + line;
							}
							inputreader.close();
							instream.close();
							buffreader.close();
							if (result != null && result.length() > 0) {
								File file = new File(SAVE_HTML_PATH);
								if (!FileUtils.exists(SAVE_HTML_PATH)) {
									FileUtils.createDir(SAVE_HTML_PATH);
								}
								String strLocalFileName = htmlUrl.substring(htmlUrl.lastIndexOf("/") + 1, htmlUrl.length()).replace(".html", "");
								File htmlFile = new File(file.getAbsolutePath(), MDPassword.getPassword32(strLocalFileName));
								htmlFile.deleteOnExit();
								htmlFile.createNewFile();
								FileOutputStream outStream = new FileOutputStream(htmlFile);
								outStream.write(result.getBytes());
								outStream.flush();
								outStream.close();
							}
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}

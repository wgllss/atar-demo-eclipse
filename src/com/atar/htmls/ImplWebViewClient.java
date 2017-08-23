/**
 * 
 */
package com.atar.htmls;

import android.annotation.SuppressLint;
import android.net.http.SslError;
import android.os.Build;
import android.utils.ShowLog;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.atar.activitys.AtarCommonActivity;
import com.atar.enums.EnumMsgWhat;
import com.atar.utils.CookieTool;
import com.atar.widgets.refresh.OnHandlerDataListener;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-6-2上午10:30:56
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ImplWebViewClient extends WebViewClient {
	protected String TAG = ImplWebViewClient.class.getSimpleName();

	protected AtarCommonActivity activity;

	private OnHandlerDataListener<PullToRefreshWebView, WebView> onHandlerDataListener;

	public ImplWebViewClient(AtarCommonActivity activity, OnHandlerDataListener<PullToRefreshWebView, WebView> onHandlerDataListener) {
		super();
		this.activity = activity;
		this.onHandlerDataListener = onHandlerDataListener;
	}

	public boolean shouldOverrideUrlLoading(WebView view, String url) {
		if (url.indexOf("tel:") < 0) {
			view.loadUrl(url);
		}
		if (url != null && url.contains("taoguba")) {
			initUrl(view, url);
		} else {
			view.loadUrl(url);
		}
		return true;
	}

	@Override
	public void onPageFinished(WebView view, String url) {
		super.onPageFinished(view, url);
		if (url.contains("taoguba")) {
			CookieManager cookieManager = CookieManager.getInstance();
			String CookieStr = cookieManager.getCookie(url);
			ShowLog.i(TAG, "Cookies = " + CookieStr);
			CookieTool.getCookieFromWebViewUrl(activity, url);
		}
		if (onHandlerDataListener != null) {
			onHandlerDataListener.sendEmptyMessage(EnumMsgWhat.LOAD_FROM_SQL);
		}
		if (activity != null) {
			activity.setLoadingViewGone();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
		// 错误处理
		try {
			view.stopLoading();
		} catch (Exception e) {
		}
		try {
			view.clearView();
		} catch (Exception e) {
		}
		if (view.canGoBack()) {
			view.goBack();
		}
		view.loadUrl("file:///android_asset/html/webview_template.html");
		// webView.getSettings().setCacheMode(WebSettings.LOAD_NORMAL);
		if (activity != null) {
			activity.setLoadingViewGone();
		}
	}

	@Override
	public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
		// handler.cancel(); 默认的处理方式，WebView变成空白页
		handler.proceed();// 接受证书
		// handleMessage(Message msg); 其他处理
	}

	private void initUrl(WebView view, String mUrl) {
		// 页面手动拼写不 入数据库
		// href="http://m.taoguba.com.cn/mViewTopic?topicID=1293091&replyID=890&pageNo=1" 帖子最终页面
		// href="http://m.taoguba.com.cn/stockBar?stockCode=sh600770" 个股吧
		// href="http://m.taoguba.com.cn/themeBar?subjectID=890&keywordID=1004&themeName=次新股" 主题吧
		// href="http://m.taoguba.com.cn/blog?userID=1293091" 个人博客
		// try {
		// if (mUrl.contains("mViewTopic")) {// 到帖子最终页
		// if (mUrl.contains("?")) {
		// String strArrayValue = mUrl.substring(mUrl.lastIndexOf("?"), mUrl.length());
		// if (strArrayValue != null && strArrayValue.length() > 1) {
		// String topicID = strArrayValue.split("&")[0].split("=")[1];
		// String replyID = strArrayValue.split("&")[1].split("=")[1];
		// TaogubaTopicActivity.startTaogubaTopicActivity(activity, topicID, replyID);
		// }
		// }
		// } else if (mUrl.contains("stockBar")) {// 个股吧
		// String strArrayValue = mUrl.substring(mUrl.lastIndexOf("?"), mUrl.length());
		// if (strArrayValue != null && strArrayValue.length() > 1) {
		// String stockCode = strArrayValue.split("&")[0].split("=")[1];
		// NetWorkInterfaces.SearchStock(activity, new HandleMessageListener() {
		// @Override
		// public void NetWorkHandlMessage(Message msg) {
		// switch (msg.what) {
		// case EnumMsgWhat.EInterface_Search_Stock:
		// if (msg.obj != null) {
		// SearchCodeJson mSearchCodeJson = (SearchCodeJson) msg.obj;
		// if (mSearchCodeJson != null) {
		// if (mSearchCodeJson.isStatus()) {
		// if (mSearchCodeJson.getDto() != null && mSearchCodeJson.getDto().size() > 0) {
		// final StockSearchBean mStockSearchBean = mSearchCodeJson.getDto().get(0);
		// if (mStockSearchBean != null) {
		// final String stockCode = mStockSearchBean.getStockCode();
		// NetWorkInterfaces.GetStockListFromSinaWhitOutToast(activity, new HandleMessageListener() {
		// @Override
		// public void NetWorkHandlMessage(Message msg) {
		// switch (msg.what) {
		// case EnumMsgWhat.EInterface_Get_Stock_List_From_Sina:
		// if (msg.obj != null) {
		// String result = (String) msg.obj;
		// if (result != null && result.length() > 0) {
		// Dphangqing dh = new Dphangqing();
		// dh.setStockCode(stockCode);
		// dh.setStockName(mStockSearchBean.getStockName());
		// String[] strs = result.split(",");
		// String[] names = result.split("=");
		// if (strs != null && names != null && strs.length > 1 && names.length > 0) {
		// dh = StockDataUtil.getDphangqing(dh, result);
		// }
		// StockActivity.startStockActivity(activity, dh, true);
		// }
		// }
		// break;
		// default:
		// break;
		// }
		// }
		// }, -1, stockCode);
		// }
		// }
		// }
		// }
		// }
		// break;
		// default:
		// break;
		// }
		// }
		// }, 0, stockCode);
		// }
		// } else if (mUrl.contains("themeBar")) {// 主题吗
		// String strArrayValue = mUrl.substring(mUrl.lastIndexOf("?"), mUrl.length());
		// if (strArrayValue != null && strArrayValue.length() > 1) {
		// String subjectID = strArrayValue.split("&")[0].split("=")[1];
		// String keywordID = strArrayValue.split("&")[1].split("=")[1];
		// String themeName = strArrayValue.split("&")[2].split("=")[1];
		// if (subjectID != null && subjectID.length() > 0 && keywordID != null && keywordID.length() > 0) {
		// ThemeDetalActivity.startThemeDetalActivity(activity, new ThemeBean(Integer.valueOf(subjectID), themeName, false, 1, Integer.valueOf(keywordID)));
		// }
		// }
		// } else if (mUrl.contains("blog")) {// 到个人博客
		// String strArrayValue = mUrl.substring(mUrl.lastIndexOf("?"), mUrl.length());
		// if (strArrayValue != null && strArrayValue.length() > 1) {
		// String userID = strArrayValue.split("&")[0].split("=")[1];
		// UserCenterActivity.startToUserCenter(activity, userID);
		// }
		// } else {
		// view.loadUrl(mUrl);
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// if (e != null)
		// ShowLog.e(TAG, e.getMessage());
		// }
	}

	@SuppressWarnings("deprecation")
	@SuppressLint({ "NewApi", "SetJavaScriptEnabled" })
	public void initWebViewSettings(WebView webview) {
		if (webview != null) {
			WebSettings webSettings = webview.getSettings();
			webSettings.setDefaultTextEncodingName("UTF-8");
			webSettings.setJavaScriptEnabled(true);
			webSettings.setAllowFileAccess(true);
			webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
			webSettings.setTextSize(WebSettings.TextSize.NORMAL);
			webSettings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
			webSettings.setDatabaseEnabled(true);

			// 本地数据库
			webSettings.setDomStorageEnabled(true);
			webSettings.setAppCacheMaxSize(1024 * 1024 * 20);
			String appCachePath = activity.getCacheDir().getAbsolutePath();
			webSettings.setAppCachePath(appCachePath);
			webSettings.setAppCacheEnabled(true);

			webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
			webSettings.setSupportMultipleWindows(true);
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
				webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
			}

			webview.setOnLongClickListener(new View.OnLongClickListener() {
				@Override
				public boolean onLongClick(View view) {
					return true;
				}
			});
		}
	}
}

/**
 * 
 */
package com.atar.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebView;

import com.atar.htmls.ImplInAndroidScript;
import com.atar.htmls.ImplWebViewClient;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-14上午11:34:23
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("SetJavaScriptEnabled")
public class AtarRefreshWebViewActivity extends AtarRefreshActivity<PullToRefreshWebView, WebView> {
	/* 是否继承AtarWebViewActivity布局 */
	protected boolean isExtendsAtarWebViewActivity;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (isExtendsAtarWebViewActivity) {
			addContentView(R.layout.common_refresh_webview);
		}
	}

	@Override
	protected void initControl() {
		if (isExtendsAtarWebViewActivity) {
			setRefreshView((PullToRefreshWebView) findViewById(R.id.pull_refresh_webview));
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void initValue() {
		if (getRefreshView() != null) {
			getRefreshView().clearHistory();
			ImplInAndroidScript mInAndroidScript = new ImplInAndroidScript(this, getLocalHandler());
			getRefreshView().addJavascriptInterface(mInAndroidScript, "injs");
			WebSettings webSettings = getPullView().getRefreshableView().getSettings();
			webSettings.setJavaScriptEnabled(true);
			webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);
			webSettings.setTextSize(WebSettings.TextSize.NORMAL);
			webSettings.setLayoutAlgorithm(LayoutAlgorithm.NORMAL);
			getRefreshView().setWebViewClient(new ImplWebViewClient(this, getLocalHandler()));
			// getRefreshView().loadUrl("file:///android_asset/html/TGBTemplate.html");
		}
	}

}

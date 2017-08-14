/**
 * 
 */
package com.atar.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.atar.activitys.R;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;

/**
 *****************************************************************************************************************************************************************************
 * 淘股吧带刷新的webview fragment
 * @author :Atar
 * @createTime:2015-5-6下午2:36:20
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public abstract class AtarRefreshWebViewFragment extends AtarRefreshFragment<PullToRefreshWebView, WebView> {

	private View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.common_refresh_webview, container, false);
			setRefreshView((PullToRefreshWebView) view.findViewById(R.id.pull_refresh_webview));
		}
		if (view != null && view.getParent() != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
	}
}

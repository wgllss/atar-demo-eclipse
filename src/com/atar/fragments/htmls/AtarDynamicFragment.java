/**
 * 
 */
package com.atar.fragments.htmls;

import android.os.Bundle;
import android.os.Message;
import android.skin.SkinUtils;
import android.view.View;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.atar.enums.EnumMsgWhat;
import com.atar.fragments.AtarRefreshWebViewFragment;
import com.atar.htmls.DynamicHtmlUtils;
import com.atar.htmls.ImplOnTouchChanceTextSizeListener;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-6-28下午1:53:58
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarDynamicFragment extends AtarRefreshWebViewFragment {

	private ImplOnTouchChanceTextSizeListener mImplOnTouchChanceTextSizeListener;
	private boolean isWebViewLoadSuccess;

	private String optionsJson = "";

	private String options;
	private String mode;
	private String url;
	private int isVisibleToUserCount;

	public static AtarDynamicFragment newInstance(String options, String mode, String url) {
		AtarDynamicFragment fragment = new AtarDynamicFragment();
		fragment.options = options;
		fragment.mode = mode;
		fragment.url = url;
		return fragment;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		/* 向html传入初始参数 start */
		optionsJson = DynamicHtmlUtils.getInitValue((AtarCommonActivity) getActivity(), this, getPullView(), options, mode, url, getCurrentSkinType(), mImplOnTouchChanceTextSizeListener);
	}

	@Override
	public void onHandlerData(Message msg) {
		super.onHandlerData(msg);
		switch (msg.what) {
		case EnumMsgWhat.LOAD_FROM_SQL:
			isVisibleToUserCount++;
			if (isWebViewLoadSuccess && isVisibleToUserCount == 2) {
				loadWebViewUrl("javascript:initValue('" + optionsJson + "')");
			}
			break;
		case EnumMsgWhat.REFRESH_PULL_DOWN:
			if (isWebViewLoadSuccess) {
				loadWebViewUrl("javascript:onPullDown()");
			}
			break;
		case EnumMsgWhat.REFRESH_PULL_UP:
			if (isWebViewLoadSuccess) {
				loadWebViewUrl("javascript:onPullUp()");
			}
			break;
		case EnumMsgWhat.REFRESH_HANDLER1:// 加载html初始化完成
			isWebViewLoadSuccess = true;
			isVisibleToUserCount++;
			if (isVisibleToUserCount == 2) {
				loadWebViewUrl("javascript:initValue('" + optionsJson + "')");
			}
			if (mImplOnTouchChanceTextSizeListener != null) {
				mImplOnTouchChanceTextSizeListener.setWebViewLoadSuccess(isWebViewLoadSuccess);
			}
			break;
		case EnumMsgWhat.REFRESH_HANDLER2:// webview loadUrl
			String javascriptUrl = (String) msg.obj;
			loadWebViewUrl(javascriptUrl);
			break;
		case EnumMsgWhat.REFRESH_HANDLER3:// 停止刷新
			onStopRefresh();
			break;
		case EnumMsgWhat.REFRESH_HANDLER4:// 设置缩放字体
			if (mImplOnTouchChanceTextSizeListener != null) {
				mImplOnTouchChanceTextSizeListener.setZoomTextSize((Boolean) msg.obj);
			}
			break;
		case EnumMsgWhat.REFRESH_HANDLER5:// 退出登录

			break;
		case EnumMsgWhat.REFRESH_HANDLER6:// 设置右上角图片
			// setTopRightImgUrl((String) msg.obj);
			break;
		case EnumMsgWhat.REFRESH_HANDLER7:// 主动调刷新
			setRefreshing();
			break;
		}
	}

	/**
	 * webview loadUrl
	 * @author :Atar
	 * @createTime:2017-6-22下午2:06:05
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param url
	 * @description:
	 */
	public void loadWebViewUrl(String url) {
		if (getRefreshView() != null) {
			getRefreshView().loadUrl(url);
		}
	}

	/**
	 * @author :Atar
	 * @createTime:2017-6-29上午10:55:05
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public void onBackPressed() {
		if (isWebViewLoadSuccess && getRefreshView() != null && getRefreshView().canGoBack()) {
			getRefreshView().goBack();
		} else {
			if (getActivity() != null) {
				((AtarCommonActivity) getActivity()).onBackPressed();
			}
		}
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		SkinUtils.setBackgroundColor(getActivity(), R.string.item_white_black_bg, skinType, getRefreshView(), getPullView());
		try {
			if (isWebViewLoadSuccess) {
				loadWebViewUrl("javascript:changeSkin('" + skinType + "')");
			}
		} catch (Exception e) {
		}
	}
}

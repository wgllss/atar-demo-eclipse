///**
// * 
// */
//package com.atar.fragments.htmls;
//
//import android.os.Bundle;
//import android.os.Message;
//import android.view.View;
//
//import com.atar.activitys.R;
//import com.atar.enums.EnumMsgWhat;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :Atar
// * @createTime:2017-6-28下午1:53:58
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class TaogubaDynamicFragment extends TaogubaRefreshWebViewFragment {
//
//	private ImplOnTouchChanceTextSizeListener mImplOnTouchChanceTextSizeListener;
//	private boolean isWebViewLoadSuccess;
//
//	private String optionsJson = "";
//
//	private String options;
//	private String mode;
//	private String url;
//	private int isVisibleToUserCount;
//
//	public static TaogubaDynamicFragment newInstance(String options, String mode, String url) {
//		TaogubaDynamicFragment fragment = new TaogubaDynamicFragment();
//		fragment.options = options;
//		fragment.mode = mode;
//		fragment.url = url;
//		return fragment;
//	}
//
//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState) {
//		super.onViewCreated(view, savedInstanceState);
//		/* 向html传入初始参数 start */
//		optionsJson = DynamicHtmlUtils.getInitValue((TaogubaCommonActivity) getActivity(), getHandler(), getPullView(), options, mode, url, getCurrentSkinType(), mImplOnTouchChanceTextSizeListener);
//	}
//
//	@Override
//	public void onHandlerData(Message msg, PullToRefreshWebView t) {
//		super.onHandlerData(msg, t);
//		switch (msg.what) {
//		case EnumMsgWhat.LOAD_FROM_SQL:
//			isVisibleToUserCount++;
//			if (isWebViewLoadSuccess && isVisibleToUserCount == 2) {
//				loadWebViewUrl("javascript:initValue('" + optionsJson + "')");
//			}
//			break;
//		case EnumMsgWhat.REFRESH_PULL_DOWN:
//			if (isWebViewLoadSuccess) {
//				loadWebViewUrl("javascript:onPullDown()");
//			}
//			break;
//		case EnumMsgWhat.REFRESH_PULL_UP:
//			if (isWebViewLoadSuccess) {
//				loadWebViewUrl("javascript:onPullUp()");
//			}
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER1:// 加载html初始化完成
//			isWebViewLoadSuccess = true;
//			isVisibleToUserCount++;
//			if (isVisibleToUserCount == 2) {
//				loadWebViewUrl("javascript:initValue('" + optionsJson + "')");
//			}
//			if (mImplOnTouchChanceTextSizeListener != null) {
//				mImplOnTouchChanceTextSizeListener.setWebViewLoadSuccess(isWebViewLoadSuccess);
//			}
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER2:// webview loadUrl
//			String javascriptUrl = (String) msg.obj;
//			loadWebViewUrl(javascriptUrl);
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER3:// 停止刷新
//			onStopRefresh();
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER4:// 设置缩放字体
//			if (mImplOnTouchChanceTextSizeListener != null) {
//				mImplOnTouchChanceTextSizeListener.setZoomTextSize((Boolean) msg.obj);
//			}
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER5:// 退出登录
//			if (url.contains("taoguba")) {
//				new LoginTool().loginOut(getActivity());
//			}
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER6:// 设置右上角图片
//			// setTopRightImgUrl((String) msg.obj);
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER7:// 主动调刷新
//			setRefreshing();
//			break;
//		}
//	}
//
//	/**
//	 * webview loadUrl
//	 * @author :Atar
//	 * @createTime:2017-6-22下午2:06:05
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param url
//	 * @description:
//	 */
//	public void loadWebViewUrl(String url) {
//		if (getRefreshView() != null) {
//			getRefreshView().loadUrl(url);
//		}
//	}
//
//	/**
//	 * @author :Atar
//	 * @createTime:2017-6-29上午10:55:05
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	public void onBackPressed() {
//		if (isWebViewLoadSuccess && getRefreshView() != null && getRefreshView().canGoBack()) {
//			getRefreshView().goBack();
//		} else {
//			if (getActivity() != null) {
//				((TaogubaCommonActivity) getActivity()).onBackPressed();
//			}
//		}
//	}
//
//	@Override
//	public void OnChangeSkin(int skinType) {
//		super.OnChangeSkin(skinType);
//		LoadUtil.setBackgroundColor(getActivity(), getRefreshView(), R.array.more_white_black_bg, skinType);
//		LoadUtil.setBackgroundColor(getActivity(), getPullView(), R.array.more_white_black_bg, skinType);
//		try {
//			if (isWebViewLoadSuccess) {
//				loadWebViewUrl("javascript:changeSkin('" + skinType + "')");
//			}
//		} catch (Exception e) {
//		}
//	}
//}

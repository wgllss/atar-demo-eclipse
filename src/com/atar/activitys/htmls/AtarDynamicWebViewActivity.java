package com.atar.activitys.htmls;
///**
// * 
// */
//package com.atar.activitys.htmls;
//
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.content.Intent;
//import android.os.Bundle;
//import android.os.Message;
//import android.view.View;
//import android.webkit.WebView;
//
//import com.atar.activitys.R;
//import com.atar.enums.EnumMsgWhat;
//import com.atar.globe.GlobeSettings;
//import com.atar.utils.IntentUtil;
//import com.umeng.socialize.UMShareAPI;
//
///**
// *****************************************************************************************************************************************************************************
// * 淘股吧 动态加载html:js 和部分图片放在本在asstes ,html页面放在服务端
// * @author :Atar
// * @createTime:2017-6-1下午2:17:48
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//@SuppressLint("SetJavaScriptEnabled")
//public class TaogubaDynamicWebViewActivity extends TaogubaRefreshActivity<PullToRefreshWebView, WebView> {
//
//	private static String URL_KEY = "URL_KEY";
//	private static String TITLE_KEY = "TITLE_KEY";
//	private static String TOP_RIGHT_IMG_URL_KEY = "TOP_RIGHT_IMG_URL_KEY";
//	private static String TOP_RIGHT_TXT_KEY = "TOP_RIGHT_TXT_KEY";
//	private static String PULL_TO_REFRESH_MODE_KEY = "PULL_TO_REFRESH_MODE_KEY";
//	private static String OPTIONS_KEY = "OPTIONS_KEY";
//	private String url;
//	private String strTitle;
//	private String optionsJson = "";
//
//	private ImplOnTouchChanceTextSizeListener mImplOnTouchChanceTextSizeListener;
//
//	private boolean isWebViewLoadSuccess;
//
//	//
//	// /* 缩放字体 end */
//	// String imageUri = "http://site.com/image.png"; // from Web
//	// String imageUri = "file:///mnt/sdcard/image.png"; // from SD card
//	// String imageUri = "content://media/external/audio/albumart/13"; // from content provider
//	// String imageUri = "assets://image.png"; // from assets
//	// String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)
//
//	@Override
//	protected void onCreate(Bundle bundle) {
//		super.onCreate(bundle);
//		addContentView(R.layout.common_refresh_webview);
//	}
//
//	@Override
//	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
//	}
//
//	@Override
//	protected void onResume() {
//		super.onResume();
//		loadWebViewUrl("javascript:onResume()");
//	}
//
//	@Override
//	protected void initControl() {
//		setRefreshView((PullToRefreshWebView) findViewById(R.id.pull_refresh_webview));
//	}
//
//	@Override
//	protected void initValue() {
//		/* 标题start */
//		if (getIntent().getStringExtra(TITLE_KEY) != null && getIntent().getStringExtra(TITLE_KEY).length() > 0) {
//			strTitle = getIntent().getStringExtra(TITLE_KEY);
//			setActivityTitle(strTitle);
//		} else {
//			setTitleBarGone();
//		}
//		/* 标题end */
//
//		/* 顶部右边部分处理start */
//		String top_right_img_url = getIntent().getStringExtra(TOP_RIGHT_IMG_URL_KEY);
//		String top_right_txt = getIntent().getStringExtra(TOP_RIGHT_TXT_KEY);
//		if (top_right_txt != null && top_right_txt.length() > 0) {
//			setTopRightText(top_right_txt);
//		}
//		setTopRightImgUrl(top_right_img_url);
//		/* 顶部右边部分处理end */
//
//		String options = getIntent().getStringExtra(OPTIONS_KEY);
//		String mode = getIntent().getStringExtra(PULL_TO_REFRESH_MODE_KEY);
//		url = getIntent().getStringExtra(URL_KEY);
//		/* 向html传入初始参数 start */
//		mImplOnTouchChanceTextSizeListener = new ImplOnTouchChanceTextSizeListener(getPullView(), this);
//		optionsJson = DynamicHtmlUtils.getInitValue(this, getHandler(), getPullView(), options, mode, url, getCurrentSkinType(), mImplOnTouchChanceTextSizeListener);
//		/* 向html传入初始参数 end */
//	}
//
//	@Override
//	public void onHandlerData(Message msg, PullToRefreshWebView t) {
//		super.onHandlerData(msg, t);
//		switch (msg.what) {
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
//			loadWebViewUrl("javascript:initValue('" + optionsJson + "')");
//			if (mImplOnTouchChanceTextSizeListener != null) {
//				mImplOnTouchChanceTextSizeListener.setWebViewLoadSuccess(isWebViewLoadSuccess);
//				getRefreshView().setOnTouchListener(mImplOnTouchChanceTextSizeListener);
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
//				getRefreshView().setOnTouchListener(mImplOnTouchChanceTextSizeListener);
//			}
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER5:// 退出登录
//			if (url.contains("taoguba")) {
//				new LoginTool().loginOut(this);
//			}
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER6:// 设置右上角图片
//			setTopRightImgUrl((String) msg.obj);
//			break;
//		case EnumMsgWhat.REFRESH_HANDLER7:// 主动调刷新
//			setRefreshing();
//			break;
//		}
//
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.img_common_top_left:// 顶部左边
//			onBackPressed();
//			break;
//		case R.id.img_common_top_right:// 顶部右边图片
//		case R.id.txt_common_top_right:// 顶部右边文字
//			if (isWebViewLoadSuccess) {
//				loadWebViewUrl("javascript:onClickTopRight()");
//			}
//			break;
//		default:
//			super.onClick(v);
//			break;
//		}
//	}
//
//	@Override
//	public void setOnDrawerBackEnabled(boolean enable) {
//		super.setOnDrawerBackEnabled(enable);
//		if (mImplOnTouchChanceTextSizeListener != null) {
//			mImplOnTouchChanceTextSizeListener.setEnable(enable);
//		}
//	}
//
//	public void setOnZoomTextSizeOnDrawerBackEnabled(boolean enable) {
//		super.setOnDrawerBackEnabled(enable);
//	}
//
//	/**
//	*  设置右上角图片
//	* @author :Atar
//	* @createTime:2017-6-6下午3:46:11
//	* @version:1.0.0
//	* @modifyTime:
//	* @modifyAuthor:
//	* @param top_right_img_url
//	* @description:
//	*/
//	private void setTopRightImgUrl(String top_right_img_url) {
//		if (top_right_img_url != null && top_right_img_url.length() > 0 && imgCommonTopRight != null) {
//			imgCommonTopRight.setVisibility(View.VISIBLE);
//			LoadImageView(top_right_img_url, imgCommonTopRight, GlobeSettings.defaultLoadImg9090[getCurrentSkinType()]);
//		}
//	}
//
//	/**
//	* webview loadUrl
//	* @author :Atar
//	* @createTime:2017-6-22下午2:06:05
//	* @version:1.0.0
//	* @modifyTime:
//	* @modifyAuthor:
//	* @param url
//	* @description:
//	*/
//	public void loadWebViewUrl(String url) {
//		if (getRefreshView() != null) {
//			getRefreshView().loadUrl(url);
//		}
//	}
//
//	/**
//	 * 跳转到动态加载 webbiew activity
//	 * @author :Atar
//	 * @createTime:2015-5-18上午11:01:01
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param context
//	 * @param url
//	 * @description:站外链接和不处理链接此页面展示
//	 */
//	public static void startTaogubaSysWebViewActivity(Context context, String url, String strTitle, String top_right_img_url, String top_right_txt, String options, String mode,
//			boolean isFlagActivityNewTask) {
//		Intent intent = new Intent(context, TaogubaDynamicWebViewActivity.class);
//		if (isFlagActivityNewTask) {
//			intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		}
//		intent.putExtra(URL_KEY, url);
//		intent.putExtra(TITLE_KEY, strTitle);
//		intent.putExtra(TOP_RIGHT_IMG_URL_KEY, top_right_img_url);
//		intent.putExtra(TOP_RIGHT_TXT_KEY, top_right_txt);
//		intent.putExtra(PULL_TO_REFRESH_MODE_KEY, mode);
//		intent.putExtra(OPTIONS_KEY, options);
//		IntentUtil.startOtherActivity(context, intent);
//	}
//
//	@Override
//	public void onBackPressed() {
//		if (isWebViewLoadSuccess && getRefreshView() != null && getRefreshView().canGoBack()) {
//			getRefreshView().goBack();
//		} else {
//			super.onBackPressed();
//		}
//	}
//
//	@Override
//	public void ChangeSkin(int skinType) {
//		super.ChangeSkin(skinType);
//		LoadUtil.setBackgroundColor(this, getRefreshView(), R.array.more_white_black_bg, skinType);
//		LoadUtil.setBackgroundColor(this, getPullView(), R.array.more_white_black_bg, skinType);
//		try {
//			if (isWebViewLoadSuccess) {
//				loadWebViewUrl("javascript:changeSkin('" + skinType + "')");
//			}
//		} catch (Exception e) {
//		}
//	}
//}

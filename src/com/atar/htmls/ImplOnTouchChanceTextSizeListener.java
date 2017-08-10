/**
 * 
 */
package com.atar.htmls;

import android.activity.CommonActivity;
import android.annotation.SuppressLint;
import android.appconfig.AppConfigSetting;
import android.enums.TypefaceMode;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CommonToast;

import com.handmark.pulltorefresh.library.PullToRefreshWebView;

/**
 *****************************************************************************************************************************************************************************
 * webview 缩放字体
 * @author :Atar
 * @createTime:2017-6-22下午1:31:38
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ImplOnTouchChanceTextSizeListener implements OnTouchListener {
	/* 缩放字体 start */
	private int mode = 0;
	private float oldDist;
	private boolean isChangeTextSize;
	/* 当前字体状态 默认中 */
	protected String TypefaceModeStasus = TypefaceMode.MIDDLE.getValue();
	public boolean enable = true;
	private boolean isWebViewLoadSuccess = false;
	private boolean isZoomTextSize = true;

	/* 缩放字体 end */

	private PullToRefreshWebView mPullToRefreshWebView;
	private CommonActivity mCommonActivity;

	public ImplOnTouchChanceTextSizeListener(PullToRefreshWebView mPullToRefreshWebView, CommonActivity mCommonActivity) {
		super();
		this.mPullToRefreshWebView = mPullToRefreshWebView;
		this.mCommonActivity = mCommonActivity;
	}

	public void setTypefaceModeStasus(String typefaceModeStasus) {
		TypefaceModeStasus = typefaceModeStasus;
	}

	public void setWebViewLoadSuccess(boolean isWebViewLoadSuccess) {
		this.isWebViewLoadSuccess = isWebViewLoadSuccess;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public void setZoomTextSize(boolean isZoomTextSize) {
		this.isZoomTextSize = isZoomTextSize;
	}

	@SuppressLint("ClickableViewAccessibility")
	@SuppressWarnings("deprecation")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if (!isWebViewLoadSuccess || !isZoomTextSize) {
			return false;
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mode = 1;
			break;
		case MotionEvent.ACTION_UP:
			mode = 0;
			oldDist = 0;
			isChangeTextSize = false;
			if (enable && mCommonActivity != null) {
				try {
//					if (mCommonActivity instanceof TaogubaDynamicWebViewActivity) {
//						((TaogubaDynamicWebViewActivity) mCommonActivity).setOnZoomTextSizeOnDrawerBackEnabled(true);
//					} else if (mCommonActivity instanceof WebViewPagerActivity) {
//						((WebViewPagerActivity) mCommonActivity).setOnZoomTextSizeOnDrawerBackEnabled(true);
//					}
				} catch (Exception e) {

				}
			}
			if (mPullToRefreshWebView != null) {
				mPullToRefreshWebView.requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_POINTER_UP:
			mode -= 1;
			oldDist = 0;
			isChangeTextSize = false;
			if (enable && mCommonActivity != null) {
				try {
//					if (mCommonActivity instanceof TaogubaDynamicWebViewActivity) {
//						((TaogubaDynamicWebViewActivity) mCommonActivity).setOnZoomTextSizeOnDrawerBackEnabled(true);
//					} else if (mCommonActivity instanceof WebViewPagerActivity) {
//						((WebViewPagerActivity) mCommonActivity).setOnZoomTextSizeOnDrawerBackEnabled(true);
//					}
				} catch (Exception e) {

				}
			}
			if (mPullToRefreshWebView != null) {
				mPullToRefreshWebView.requestDisallowInterceptTouchEvent(false);
			}
			break;
		case MotionEvent.ACTION_POINTER_2_DOWN:
			if (mPullToRefreshWebView != null) {
				mPullToRefreshWebView.requestDisallowInterceptTouchEvent(true);
			}
			if (enable && mCommonActivity != null) {
				try {
//					if (mCommonActivity instanceof TaogubaDynamicWebViewActivity) {
//						((TaogubaDynamicWebViewActivity) mCommonActivity).setOnZoomTextSizeOnDrawerBackEnabled(false);
//					} else if (mCommonActivity instanceof WebViewPagerActivity) {
//						((WebViewPagerActivity) mCommonActivity).setOnZoomTextSizeOnDrawerBackEnabled(false);
//					}
				} catch (Exception e) {

				}
			}
			isChangeTextSize = false;
			oldDist = spacing(event);
			mode += 1;
			break;
		case MotionEvent.ACTION_MOVE:
			if (mode >= 2) {
				float newDist = spacing(event);
				if (newDist > oldDist + 1) {
					if (!isChangeTextSize && newDist > 500) {
						isChangeTextSize = true;
						if (TypefaceMode.SMALL_MODE.getValue().equals(TypefaceModeStasus)) {
							TypefaceModeStasus = TypefaceMode.MIDDLE.getValue();
							CommonToast.show("中号字体");
						} else if (TypefaceMode.MIDDLE.getValue().equals(TypefaceModeStasus)) {
							TypefaceModeStasus = TypefaceMode.LARGER_MODE.getValue();
							CommonToast.show("大号字体");
						} else if (TypefaceMode.LARGER_MODE.getValue().equals(TypefaceModeStasus)) {
							TypefaceModeStasus = TypefaceMode.GREAT_MODE.getValue();
							CommonToast.show("超大号字体");
						} else if (TypefaceMode.GREAT_MODE.getValue().equals(TypefaceModeStasus)) {
							TypefaceModeStasus = TypefaceMode.BIG_MODE.getValue();
							CommonToast.show("巨大号字体");
						}
						if (mPullToRefreshWebView != null && mPullToRefreshWebView.getRefreshableView() != null) {
							mPullToRefreshWebView.getRefreshableView().loadUrl("javascript:setTextSize('" + TypefaceModeStasus + "')");
						}
						AppConfigSetting.getInstance().putString(TypefaceMode.TYPE_FACE_MODE_KEY.getValue(), TypefaceModeStasus);
					}
					oldDist = newDist;
				}
				if (newDist < oldDist - 1) {
					if (!isChangeTextSize && newDist > 100 && newDist < 500) {
						isChangeTextSize = true;
						if (TypefaceMode.BIG_MODE.getValue().equals(TypefaceModeStasus)) {
							TypefaceModeStasus = TypefaceMode.GREAT_MODE.getValue();
							CommonToast.show("超大号字体");
						} else if (TypefaceMode.GREAT_MODE.getValue().equals(TypefaceModeStasus)) {
							TypefaceModeStasus = TypefaceMode.LARGER_MODE.getValue();
							CommonToast.show("大号字体");
						} else if (TypefaceMode.LARGER_MODE.getValue().equals(TypefaceModeStasus)) {
							TypefaceModeStasus = TypefaceMode.MIDDLE.getValue();
							CommonToast.show("中号字体");
						} else if (TypefaceMode.MIDDLE.getValue().equals(TypefaceModeStasus)) {
							TypefaceModeStasus = TypefaceMode.SMALL_MODE.getValue();
							CommonToast.show("小号字体");
						}
						if (mPullToRefreshWebView != null && mPullToRefreshWebView.getRefreshableView() != null) {
							mPullToRefreshWebView.getRefreshableView().loadUrl("javascript:setTextSize('" + TypefaceModeStasus + "')");
						}
						AppConfigSetting.getInstance().putString(TypefaceMode.TYPE_FACE_MODE_KEY.getValue(), TypefaceModeStasus);
					}
					oldDist = newDist;
				}
			}
			break;
		}
		return false;
	}

	@SuppressLint("FloatMath")
	private float spacing(MotionEvent event) {
		float x = 0;
		float y = 0;
		if (event.getPointerCount() >= 2) {
			x = event.getX(0) - event.getX(1);
			y = event.getY(0) - event.getY(1);
		}
		return FloatMath.sqrt(x * x + y * y);
	}
}

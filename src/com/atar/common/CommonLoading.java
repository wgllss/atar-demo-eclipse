/**
 * 
 */
package com.atar.common;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

/**
 *****************************************************************************************************************************************************************************
 * 淘股吧公共加载对话框
 * @author :Atar
 * @createTime:2014-6-20下午9:21:29
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: 单例模式，保证只有一个对话框显示
 *****************************************************************************************************************************************************************************
 */
public class CommonLoading {
	// private static CommonLoading instance;
	private static Dialog dialog;
	private static TextView txtLoading;

	// public static synchronized CommonLoading getInstance() {
	// if (instance == null) {
	// instance = new CommonLoading();
	// }
	// return instance;
	// }

	public static void showLoading(Context mContext) {
		if (dialog != null && dialog.isShowing()) {
			return;
		}
		if (mContext == null) {
			return;
		}
		// dialog = new Dialog(mContext, R.style.MyDialog);
		// dialog.setContentView(R.layout.common_taoguba_loading);
		// dialog.findViewById(R.id.linear_loading).setVisibility(View.VISIBLE);
		// dialog.show();
	}

	/**
	 * touch外面不消失
	 * @author :Atar
	 * @createTime:2014-12-2上午10:49:15
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mContext
	 * @param isOnTouchOutside
	 * @description:
	 */
	public static void showLoadingOutSideFalse(Context mContext) {
		if (dialog != null && dialog.isShowing()) {
			return;
		}
		if (mContext == null) {
			return;
		}
//		dialog = new Dialog(mContext, R.style.MyDialog);
//		dialog.setContentView(R.layout.common_taoguba_loading);
//		dialog.findViewById(R.id.linear_loading).setVisibility(View.VISIBLE);
//		dialog.setCanceledOnTouchOutside(false);
//		dialog.show();
	}

	public static void showLoading(Context mContext, String strContent) {
		if (dialog != null && dialog.isShowing()) {
			return;
		}
		if (mContext == null) {
			return;
		}
//		dialog = new Dialog(mContext, R.style.MyDialog);
//		dialog.setContentView(R.layout.common_taoguba_loading);
//		dialog.findViewById(R.id.linear_loading).setVisibility(View.VISIBLE);
//		txtLoading = (TextView) dialog.findViewById(R.id.txt_loading_notice);
//		txtLoading.setText(strContent);
//		dialog.show();
	}

	public static void setProgressText(String strProgressText) {
		txtLoading.setText(strProgressText);
	}

	public static void dissMissLoading() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
		if (dialog != null) {
			dialog.dismiss();
		}
	}

 }

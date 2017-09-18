package com.atar.common;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.appconfig.AppConfigSetting;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.enums.SkinMode;
import android.graphics.Typeface;
import android.os.Message;
import android.text.Html;
import android.text.InputFilter;
import android.text.InputType;
import android.util.TypedValue;
import android.utils.CommonStringUtil;
import android.utils.ScreenUtils;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CommonToast;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.application.AtarApplication;
import com.atar.enums.EnumMsgWhat;
import com.atar.net.NetWorkInterfaces;

/**
 ***************************************************************************************************************************************************************************** 
 * 淘股吧订制 各种统一风格 对话框 样式，属性
 * 
 * @author :Atar
 * @createTime:2014-7-4下午4:08:02
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
@SuppressLint("InflateParams")
public class CommonDialog {
	static Dialog mDialog;

	public static void alertDialog(Context mContext, String strTitle, String strContent, OnClickListener ls) {
		if (mContext == null) {
			return;
		}
		dialogDismiss();
		// mDialog = new Dialog(mContext, R.style.MyDialog);
		// mDialog.setContentView(R.layout.common_taoguba_alert_dialog);
		// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
		// if (mContext.getResources().getConfiguration().orientation == 1) {
		// // 1竖屏
		// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
		// } else {
		// // 0横屏
		// mDialog.getWindow().setLayout(8 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
		// }
		//
		// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
		// if (strTitle != null && !strTitle.equals("")) {
		// txtTitle.setText(strTitle);
		// }
		// TextView txtContent = (TextView) mDialog.getWindow().findViewById(R.id.txt_content0);
		// if (strContent != null && !strContent.equals("")) {
		// txtContent.setText(strContent);
		// txtContent.setVisibility(View.VISIBLE);
		// } else {
		// txtContent.setVisibility(View.GONE);
		// }
		// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok);
		// View view = mDialog.getWindow().findViewById(R.id.common_dialog_main);
		// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
		// LoadUtil.setBackgroundDrawable(mContext, ok, R.array.common_ok_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, view, R.array.common_dialog_bg_drawable, skinType);
		// LoadUtil.setTextColor(mContext, txtTitle, R.array.common_dialog_txt_color, skinType);
		// LoadUtil.setTextColor(mContext, txtContent, R.array.common_dialog_txt_color, skinType);
		// LoadUtil.setTextColor(mContext, ok, R.array.common_activity_title_color, skinType);
		// if (ls != null) {
		// ok.setOnClickListener(ls);
		// } else {
		// ok.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });
		// }
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
				return true;
			}
		});
		mDialog.show();
	}

	/**
	 * 
	 * @param mContext
	 *            上下文
	 * @param strTitle
	 *            标题
	 * @param strContent
	 *            内容
	 * @param strCancle
	 *            取消文字
	 * @param lsOk
	 *            确定事件
	 * @param lsCacle
	 *            取消事件
	 * 
	 */
	public static void conformDialog(Context mContext, String strTitle, String strContent, String strOk, String strCancle, OnClickListener lsOk, OnClickListener lsCacle) {
		if (mContext == null) {
			return;
		}
		// 取得自定义View
		//mDialog = new Dialog(mContext, R.style.MyDialog);
		// mDialog.setContentView(R.layout.common_taoguba_conform_dialog);
		// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
		// if (mContext.getResources().getConfiguration().orientation == 1) {
		// // 1竖屏
		// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
		// } else {
		// // 0横屏
		// mDialog.getWindow().setLayout(8 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
		// }
		//
		// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
		// if (strTitle != null && !strTitle.equals("")) {
		// txtTitle.setText(strTitle);
		// } else {
		// txtTitle.setVisibility(View.GONE);
		// }
		// TextView txtContent = (TextView) mDialog.getWindow().findViewById(R.id.txt_content1);
		// if (strContent != null && !strContent.equals("")) {
		// txtContent.setText(strContent);
		// txtContent.setVisibility(View.VISIBLE);
		// } else {
		// txtContent.setVisibility(View.GONE);
		// }
		// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok2);
		// TextView cancel = (TextView) mDialog.getWindow().findViewById(R.id.cancel2);
		// View view = mDialog.getWindow().findViewById(R.id.common_dialog_main);
		// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
		// LoadUtil.setBackgroundDrawable(mContext, ok, R.array.common_ok_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, cancel, R.array.common_cancle_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, view, R.array.common_dialog_bg_drawable, skinType);
		// LoadUtil.setTextColor(mContext, txtTitle, R.array.common_dialog_txt_color, skinType);
		// LoadUtil.setTextColor(mContext, txtContent, R.array.common_dialog_txt_color, skinType);
		// LoadUtil.setTextColor(mContext, ok, R.array.common_activity_title_color, skinType);
		// LoadUtil.setTextColor(mContext, cancel, R.array.common_activity_title_color, skinType);
		// if (strOk != null && !strOk.equals("")) {
		// ok.setText(strOk);
		// }
		// if (strCancle != null && !strCancle.equals("")) {
		// cancel.setText(strCancle);
		// }
		// if (lsOk != null) {
		// ok.setOnClickListener(lsOk);
		// } else {
		// ok.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });
		// }
		// if (lsCacle != null) {
		// cancel.setOnClickListener(lsCacle);
		// } else {
		// cancel.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });
		// }
		mDialog.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
				return true;
			}
		});
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.show();
	}

	public static void dialogDismiss() {
		if (mDialog != null && mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}

	/**
	 * 带有编辑框的对话框
	 * 
	 * @author :Atar
	 * @createTime:2014-7-17上午10:45:23
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mContext
	 * @param strTitle
	 * @param strOk
	 * @param strCancle
	 * @param lsOk
	 * @param lsCacle
	 * @description:
	 */
	public static void conformDialogWithEditText(Context mContext, String strTitle, final EditFinishListener lsOk) {
		// 取得自定义View
		//mDialog = new Dialog(mContext, R.style.MyDialog);
		// mDialog.setContentView(R.layout.common_taoguba_conform_edit_dialog);
		// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
		// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
		// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
		// if (strTitle != null && !strTitle.equals("")) {
		// txtTitle.setText(strTitle);
		// }
		// final EditText edtContent = (EditText) mDialog.getWindow().findViewById(R.id.txt_content1);
		// edtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
		// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok2);
		// TextView cancel = (TextView) mDialog.getWindow().findViewById(R.id.cancel2);
		// View view = mDialog.getWindow().findViewById(R.id.common_dialog_main);
		// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
		// LoadUtil.setBackgroundDrawable(mContext, ok, R.array.common_ok_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, cancel, R.array.common_cancle_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, view, R.array.common_dialog_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, edtContent, R.array.common_dialog_edit_bg_drawable, skinType);
		// LoadUtil.setTextColor(mContext, ok, R.array.common_activity_title_color, skinType);
		// LoadUtil.setTextColor(mContext, cancel, R.array.common_activity_title_color, skinType);
		// LoadUtil.setTextColor(mContext, txtTitle, R.array.common_dialog_txt_color, skinType);
		// ok.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View arg0) {
		// if (lsOk != null) {
		// lsOk.finish(edtContent.getText().toString());
		// } else {
		// dialogDismiss();
		// }
		// }
		// });
		// cancel.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.show();
	}

	/**
	 * 
	 * @author :Atar
	 * @createTime:2017-6-23上午11:31:57
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mContext
	 * @param strTitle
	 * @param hinttext
	 * @param maxLength maxLength 默认不需要传0
	 * @param lsOk
	 * @description:
	 */
	public static void conformDialogWithEditText(Context mContext, String strTitle, String hinttext, int maxLength, final EditFinishListener lsOk) {
		// 取得自定义View
		//mDialog = new Dialog(mContext, R.style.MyDialog);
		// mDialog.setContentView(R.layout.common_taoguba_conform_edit_dialog);
		// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
		// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
		// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
		// if (strTitle != null && !strTitle.equals("")) {
		// txtTitle.setText(strTitle);
		// }
		// final EditText edtContent = (EditText) mDialog.getWindow().findViewById(R.id.txt_content1);
		// if (maxLength > 0) {
		// edtContent.setFilters(new InputFilter[] { new InputFilter.LengthFilter(maxLength) });
		// }
		// edtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
		// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok2);
		// TextView cancel = (TextView) mDialog.getWindow().findViewById(R.id.cancel2);
		// View view = mDialog.getWindow().findViewById(R.id.common_dialog_main);
		// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
		// if (hinttext != null) {
		// edtContent.setHint(hinttext);
		// edtContent.setHintTextColor(mContext.getResources().getIntArray(R.array.txt_day_grey_night_greyblack_color)[skinType]);
		// }
		// LoadUtil.setBackgroundDrawable(mContext, ok, R.array.common_ok_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, cancel, R.array.common_cancle_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, view, R.array.common_dialog_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, edtContent, R.array.common_dialog_edit_bg_drawable, skinType);
		// LoadUtil.setTextColor(mContext, ok, R.array.common_activity_title_color, skinType);
		// LoadUtil.setTextColor(mContext, cancel, R.array.common_activity_title_color, skinType);
		// LoadUtil.setTextColor(mContext, txtTitle, R.array.common_dialog_txt_color, skinType);
		// ok.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View arg0) {
		// if (lsOk != null) {
		// lsOk.finish(edtContent.getText().toString());
		// } else {
		// dialogDismiss();
		// }
		// }
		// });
		// cancel.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.show();
	}

	/**
	 * 报名需要验证码
	 * 
	 * @author :Atar
	 * @createTime:2014-7-17上午10:45:23
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mContext
	 * @param strTitle
	 * @param strOk
	 * @param strCancle
	 * @param lsOk
	 * @param lsCacle
	 * @description:
	 */
	public static void conformDialogForShiPanSignUp(Context mContext, final EditFinishListener lsOk) {
		// 取得自定义View
		//mDialog = new Dialog(mContext, R.style.MyDialog);
		// mDialog.setContentView(R.layout.common_taoguba_shipan_signup_edit_dialog);
		// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
		// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
		//
		// final EditText edtContent = (EditText) mDialog.getWindow().findViewById(R.id.edit_need_code);
		//
		// Button btn_code = (Button) mDialog.getWindow().findViewById(R.id.btn_code);
		// View view = mDialog.getWindow().findViewById(R.id.common_dialog_main);
		// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
		//
		// LoadUtil.setBackgroundDrawable(mContext, btn_code, R.array.selector_blue_bg_drawable, skinType);
		// LoadUtil.setBackgroundColor(mContext, view.findViewById(R.id.rel_layout), R.array.more_white_black_bg, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, edtContent, R.array.common_edittext_bg, skinType);
		// edtContent.setHintTextColor(mContext.getResources().obtainTypedArray(R.array.txt_day_grey_night_greyblack_color).getColor(skinType, 0));
		// LoadUtil.setTextColor(mContext, R.array.common_top_title_bar_bg_color, skinType, btn_code, (TextView) view.findViewById(R.id.txt_title));
		// LoadUtil.setTextColor(mContext, R.array.txt_day_black_night_greywhite_color, skinType, edtContent);
		// LoadUtil.setTextColor(mContext, R.array.common_activity_title_color, skinType, btn_code);
		//
		// btn_code.setOnClickListener(new View.OnClickListener() {
		// @Override
		// public void onClick(View arg0) {
		// if (lsOk != null) {
		// lsOk.finish(edtContent.getText().toString());
		// } else {
		// dialogDismiss();
		// }
		// }
		// });
		// view.findViewById(R.id.img_close).setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });

		mDialog.setCanceledOnTouchOutside(false);
		mDialog.setCancelable(false);
		mDialog.setOnKeyListener(new OnKeyListener() {
			@Override
			public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
				return true;
			}
		});
		mDialog.show();
	}

	/**
	 * 带有编辑框的对话框
	 * 
	 * @author :Atar
	 * @createTime:2015-5-25下午3:55:52
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mContext
	 * @param strTitle
	 * @param strOk
	 * @param strCancle
	 * @param lsOk
	 * @param lsCacle
	 * @param isSingleLine
	 * @param maxLines
	 *            最多输入字数
	 * @description:
	 */
	public static void conformDialogWithEditText(Context mContext, String strTitle, String strOk, String strCancle, final EditFinishListener lsOk, OnClickListener lsCacle, boolean isSingleLine,
			int maxLines) {
		// 取得自定义View
		//mDialog = new Dialog(mContext, R.style.MyDialog);
		// mDialog.setContentView(R.layout.common_taoguba_conform_edit_dialog);
		// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
		// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
		// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
		// if (strTitle != null && !strTitle.equals("")) {
		// txtTitle.setText(strTitle);
		// }
		// final EditText edtContent = (EditText) mDialog.getWindow().findViewById(R.id.txt_content1);
		// edtContent.setSingleLine(isSingleLine);
		// if (maxLines > 0) {
		// InputFilter[] filters = { new InputFilter.LengthFilter(maxLines) };
		// edtContent.setFilters(filters);
		// }
		// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok2);
		// TextView cancel = (TextView) mDialog.getWindow().findViewById(R.id.cancel2);
		// View view = mDialog.getWindow().findViewById(R.id.common_dialog_main);
		// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
		// LoadUtil.setBackgroundDrawable(mContext, ok, R.array.common_ok_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, cancel, R.array.common_cancle_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, view, R.array.common_dialog_bg_drawable, skinType);
		// LoadUtil.setBackgroundDrawable(mContext, edtContent, R.array.common_dialog_edit_bg_drawable, skinType);
		// LoadUtil.setTextColor(mContext, txtTitle, R.array.common_dialog_txt_color, skinType);
		// LoadUtil.setTextColor(mContext, ok, R.array.common_activity_title_color, skinType);
		// LoadUtil.setTextColor(mContext, cancel, R.array.common_activity_title_color, skinType);
		// if (strOk != null && !strOk.equals("")) {
		// ok.setText(strOk);
		// }
		// if (strCancle != null && !strCancle.equals("")) {
		// cancel.setText(strCancle);
		// }
		// if (lsOk != null) {
		// ok.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // dialogDismiss();
		// lsOk.finish(edtContent.getText().toString());
		// }
		// });
		// } else {
		// ok.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });
		// }
		// if (lsCacle != null) {
		// cancel.setOnClickListener(lsCacle);
		// } else {
		// cancel.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });
		// }
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.show();
	}

	/**
	 * 带有编辑框的对话框
	 * 
	 * @author :Atar
	 * @createTime:2015-5-25下午3:55:52
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mContext
	 * @param strTitle
	 * @param strOk
	 * @param strCancle
	 * @param lsOk
	 * @param lsCacle
	 * @param isSingleLine
	 * @param maxLines 最多输入字数
	 *            
	 * @description:
	 */
	public static void KGameConformDialogWithEditText(Context mContext, String strTitle, String strOk, String strCancle, final EditFinishListener lsOk, OnClickListener lsCacle, boolean isSingleLine,
			int maxLines, Typeface typeFace) {
		// 取得自定义View
		//mDialog = new Dialog(mContext, R.style.MyDialog);
		// mDialog.setContentView(R.layout.dialog_conform_edit_kgame);
		// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
		// mDialog.getWindow().setLayout(8 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
		// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
		// if (strTitle != null && !strTitle.equals("")) {
		// txtTitle.setText(strTitle);
		// }
		// final EditText edtContent = (EditText) mDialog.getWindow().findViewById(R.id.txt_content1);
		// edtContent.setSingleLine(isSingleLine);
		// if (maxLines > 0) {
		// InputFilter[] filters = { new InputFilter.LengthFilter(maxLines) };
		// edtContent.setFilters(filters);
		// }
		// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok2);
		// TextView cancel = (TextView) mDialog.getWindow().findViewById(R.id.cancel2);
		// int skinType = SkinMode.NIGHT_MODE;
		// LoadUtil.setBackgroundDrawable(mContext, edtContent, R.array.common_dialog_edit_bg_drawable, skinType);
		// LoadUtil.setTextColor(mContext, txtTitle, R.array.common_dialog_txt_color, skinType);
		// LoadUtil.setTextColor(mContext, ok, R.array.common_activity_title_color, skinType);
		// LoadUtil.setTextColor(mContext, cancel, R.array.common_activity_title_color, skinType);
		// LoadUtil.setTextTypeFace(ok, typeFace);
		// LoadUtil.setTextTypeFace(cancel, typeFace);
		// LoadUtil.setTextTypeFace(txtTitle, typeFace);
		// if (strOk != null && !strOk.equals("")) {
		// ok.setText(strOk);
		// }
		// if (strCancle != null && !strCancle.equals("")) {
		// cancel.setText(strCancle);
		// }
		// if (lsOk != null) {
		// ok.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // dialogDismiss();
		// lsOk.finish(edtContent.getText().toString());
		// }
		// });
		// } else {
		// ok.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });
		// }
		// if (lsCacle != null) {
		// cancel.setOnClickListener(lsCacle);
		// } else {
		// cancel.setOnClickListener(new View.OnClickListener() {
		//
		// @Override
		// public void onClick(View v) {
		// dialogDismiss();
		// }
		// });
		// }
		mDialog.setCanceledOnTouchOutside(false);
		mDialog.show();
	}

	// /**
	// * 滚轮式对话框两列
	// *
	// * @author :Atar
	// * @createTime:2014-10-6下午5:28:11
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @param context
	// * @param strTitle
	// * 标题
	// * @param array1
	// * 第一列数组
	// * @param array2
	// * 第二列数组
	// * @param mOnWheelChangedListener1
	// * 第一列滚动监听事件
	// * @param mOnWheelChangedListener2
	// * 第二列滚动监听事件
	// * @param currentPosition1
	// * 第一列默认显示哪个 用于保存状态
	// * @param currentPosition2
	// * 第二列默认显示哪个 用于保存状态
	// * @param lsOk
	// * 确认监听点击事件
	// * @param lsCacle
	// * 取消监听点击事件
	// * @description:
	// */
	// public static void WheelDialog(Context context, String strTitle, List<String> list1, List<String> list2, OnWheelChangeListener mOnWheelChangedListener1,
	// OnWheelChangeListener mOnWheelChangedListener2, int currentPosition1, int currentPosition2, OnClickListener lsOk, OnClickListener lsCacle) {
	// mDialog = new Dialog(context, R.style.MyDialog);
	// mDialog.setContentView(R.layout.common_taoguba_wheel_dialog);
	// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
	// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
	// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
	// if (strTitle != null && !strTitle.equals("")) {
	// txtTitle.setText(strTitle);
	// }
	// LoopView wheelStart = (LoopView) mDialog.getWindow().findViewById(R.id.wheel_start_time);
	// LoopView wheelEnd = (LoopView) mDialog.getWindow().findViewById(R.id.wheel_end_time);
	// wheelStart.setItems(list1);
	// wheelStart.setInitPosition(currentPosition1);
	// if (mOnWheelChangedListener1 != null) {
	// wheelStart.setOnWheelChangeListener(mOnWheelChangedListener1);
	// }
	// wheelEnd.setItems(list2);
	// wheelEnd.setInitPosition(currentPosition2);
	// if (mOnWheelChangedListener2 != null) {
	// wheelEnd.setOnWheelChangeListener(mOnWheelChangedListener2);
	// }
	// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok);
	// TextView cancel = (TextView) mDialog.getWindow().findViewById(R.id.cancel);
	// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
	// LoadUtil.setBackgroundDrawable(context, ok, R.array.common_ok_bg_drawable, skinType);
	// LoadUtil.setBackgroundDrawable(context, cancel, R.array.common_cancle_bg_drawable, skinType);
	// LoadUtil.setTextColor(context, ok, R.array.common_activity_title_color, skinType);
	// LoadUtil.setTextColor(context, cancel, R.array.common_activity_title_color, skinType);
	// if (lsOk != null) {
	// ok.setOnClickListener(lsOk);
	// } else {
	// ok.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// dialogDismiss();
	// }
	// });
	// }
	// if (lsCacle != null) {
	// cancel.setOnClickListener(lsCacle);
	// } else {
	// cancel.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// dialogDismiss();
	// }
	// });
	// }
	// mDialog.setCanceledOnTouchOutside(false);
	// mDialog.show();
	// }
	//
	// /**
	// * 滚轮式对话框一列
	// *
	// * @author :Atar
	// * @createTime:2014-10-6下午5:28:11
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @param context
	// * @param strTitle
	// * 标题
	// * @param array1
	// * 第一列数组
	// * @param isCyclic
	// * 是否循环
	// * @param VisibleItemsCount
	// * 可见条目数量
	// * @param mOnWheelChangedListener1
	// * 列滚动监听事件
	// * @param currentPosition1
	// * 列默认显示哪个 用于保存状态
	// * @param lsOk
	// * 确认监听点击事件
	// * @param lsCacle
	// * 取消监听点击事件
	// * @description:
	// */
	// public static void WheelDialogOnlyOne(Context context, String strTitle, List<String> list, boolean isCyclic, OnWheelChangeListener mOnWheelChangedListener1, int currentPosition1,
	// OnClickListener lsOk, OnClickListener lsCacle) {
	// mDialog = new Dialog(context, R.style.MyDialog);
	// mDialog.setContentView(R.layout.common_taoguba_wheel_dialog_only_one);
	// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
	// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
	// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
	// if (strTitle != null && !strTitle.equals("")) {
	// txtTitle.setText(strTitle);
	// }
	// LoopView wheelStart = (LoopView) mDialog.getWindow().findViewById(R.id.wheel_start_time);
	// wheelStart.setItems(list);
	// wheelStart.setInitPosition(currentPosition1);
	// wheelStart.setFullScrenWidth(true);
	// wheelStart.setTextHorizontalCenter(true);
	// if (mOnWheelChangedListener1 != null) {
	// wheelStart.setOnWheelChangeListener(mOnWheelChangedListener1);
	// }
	// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok);
	// TextView cancel = (TextView) mDialog.getWindow().findViewById(R.id.cancel);
	// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
	// LoadUtil.setBackgroundDrawable(context, ok, R.array.common_ok_bg_drawable, skinType);
	// LoadUtil.setBackgroundDrawable(context, cancel, R.array.common_cancle_bg_drawable, skinType);
	// LoadUtil.setTextColor(context, ok, R.array.common_activity_title_color, skinType);
	// LoadUtil.setTextColor(context, cancel, R.array.common_activity_title_color, skinType);
	// if (lsOk != null) {
	// ok.setOnClickListener(lsOk);
	// } else {
	// ok.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// dialogDismiss();
	// }
	// });
	// }
	// if (lsCacle != null) {
	// cancel.setOnClickListener(lsCacle);
	// } else {
	// cancel.setOnClickListener(new View.OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// dialogDismiss();
	// }
	// });
	// }
	// mDialog.setCanceledOnTouchOutside(false);
	// mDialog.show();
	// }
	//
	// public static boolean isShowing() {
	// if (mDialog != null && mDialog.isShowing()) {
	// return true;
	// } else {
	// return false;
	// }
	// }
	//
	public interface EditFinishListener {
		public void finish(String strEditContent);
	}
	//
	// /**
	// *
	// * @param mContext
	// * @param strTitle
	// * @param strContent
	// * @param list
	// */
	// @SuppressLint("InflateParams")
	// public static void CommonListDialog(Context mContext, String strTitle, String strContent, List<DialogListBean> list, Typeface typeFace) {
	// dialogDismiss();
	// mDialog = null;
	// mDialog = new Dialog(mContext, R.style.MyDialog);
	// LayoutInflater inflater = LayoutInflater.from(mContext);
	// View view = inflater.inflate(R.layout.common_taoguba_list_dialog, null);
	// mDialog.setContentView(view);
	//
	// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
	// int heightPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().heightPixels;
	//
	// if (mContext.getResources().getConfiguration().orientation == 1) {
	// // 1竖屏
	// if (list != null && list.size() > 0) {
	// mDialog.getWindow().setLayout(11 * heightPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
	// } else {
	// mDialog.getWindow().setLayout(11 * heightPixels / 15, (int) (widthPixels * 0.8));
	// }
	// } else {
	// // 0横屏
	// if (list != null && list.size() > 0) {
	// mDialog.getWindow().setLayout(11 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
	// } else {
	// mDialog.getWindow().setLayout(11 * widthPixels / 15, (int) (heightPixels * 0.8));
	// }
	// }
	//
	// TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
	// LoadUtil.setTextTypeFace(txtTitle, typeFace);
	// if (strTitle != null && !strTitle.equals("")) {
	// txtTitle.setText(Html.fromHtml(strTitle));
	// }
	// TextView txtContent = (TextView) view.findViewById(R.id.txt_content);
	// ScrollView mScrollView = (ScrollView) view.findViewById(R.id.scroll_content);
	// if (strTitle != null && !strTitle.equals("") && strContent != null && !strContent.equals("")) {
	// mScrollView.setBackgroundColor(mContext.getResources().getColor(R.color.transparent0));
	// }
	// if (strContent != null && !strContent.equals("")) {
	// if (strContent.contains("<font")) {
	// txtContent.setText(Html.fromHtml(strContent));
	// } else {
	// txtContent.setText(strContent);
	// }
	// txtContent.setVisibility(View.VISIBLE);
	// mScrollView.setVisibility(View.VISIBLE);
	// } else {
	// txtContent.setVisibility(View.GONE);
	// mScrollView.setVisibility(View.GONE);
	// }
	// ListView listview = (ListView) view.findViewById(R.id.listview);
	// if (list != null && list.size() > 0) {
	// listview.setAdapter(new DialogListAdapter(list));
	// listview.setVisibility(View.VISIBLE);
	// } else {
	// listview.setVisibility(View.GONE);
	// }
	//
	// ImageView img_close = (ImageView) view.findViewById(R.id.img_close);
	// img_close.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// mDialog.dismiss();
	// }
	// });
	// mDialog.setCanceledOnTouchOutside(true);
	// mDialog.show();
	// }
	//
	// /**
	// *
	// * @param mContext
	// * @param strTitle
	// * @param strContent
	// * @param list
	// */
	// @SuppressLint("InflateParams")
	// public static void CommonSettingsDialog(Context mContext, String strTitle, String strContent, List<DialogListBean> list, Typeface typeFace) {
	// dialogDismiss();
	// mDialog = null;
	// mDialog = new Dialog(mContext, R.style.MyDialog);
	// LayoutInflater inflater = LayoutInflater.from(mContext);
	// View view = inflater.inflate(R.layout.common_taoguba_kgame_settings_dialog, null);
	// mDialog.setContentView(view);
	//
	// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
	// int heightPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().heightPixels;
	// if (list != null && list.size() > 0) {
	// mDialog.getWindow().setLayout(8 * widthPixels / 15, (int) (heightPixels * 0.5));
	// } else {
	// mDialog.getWindow().setLayout(11 * widthPixels / 15, (int) (heightPixels * 0.8));
	// }
	//
	// TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
	// LoadUtil.setTextTypeFace(txtTitle, typeFace);
	// if (strTitle != null && !strTitle.equals("")) {
	// txtTitle.setText(Html.fromHtml(strTitle));
	// }
	//
	// ListView listview = (ListView) view.findViewById(R.id.listview);
	// if (list != null && list.size() > 0) {
	// listview.setAdapter(new DialogListAdapter(list));
	// listview.setVisibility(View.VISIBLE);
	// } else {
	// listview.setVisibility(View.GONE);
	// }
	//
	// ImageView img_close = (ImageView) view.findViewById(R.id.img_close);
	// img_close.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// mDialog.dismiss();
	// }
	// });
	// mDialog.setCanceledOnTouchOutside(true);
	// mDialog.show();
	// }
	//
	// @SuppressWarnings("static-access")
	// public static void KSignDialog(Context context, Typeface typeFace, String strTitle, int todayBeans, OnClickListener mOnClickListener) {
	// if (context != null) {
	// dialogDismiss();
	// mDialog = null;
	// mDialog = new Dialog(context, R.style.MyDialog2);
	// LayoutInflater mLayoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
	// ViewGroup view = (ViewGroup) mLayoutInflater.inflate(R.layout.common_k_popupwindow, null);
	// TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
	// TextView txt_gold_plus = (TextView) view.findViewById(R.id.txt_gold_plus);
	// Button btnSign = (Button) view.findViewById(R.id.btn_sign);
	// LoadUtil.setTextTypeFace(txtTitle, typeFace);
	// LoadUtil.setTextTypeFace(btnSign, typeFace);
	// txt_gold_plus.setText("金豆+" + todayBeans);
	// mDialog.setContentView(view);
	// int widthPixels = context.getResources().getDisplayMetrics().widthPixels;
	// if (context.getResources().getConfiguration().orientation == 1) {
	// // 1竖屏
	// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
	// } else {
	// // 0横屏
	// mDialog.getWindow().setLayout(8 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
	// }
	// if (strTitle != null && strTitle.length() > 0) {
	// txtTitle.setText(strTitle);
	// }
	// if (mOnClickListener != null) {
	// btnSign.setOnClickListener(mOnClickListener);
	// }
	// mDialog.setCanceledOnTouchOutside(false);
	// mDialog.setOnKeyListener(new OnKeyListener() {
	// @Override
	// public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
	// return true;
	// }
	// });
	// mDialog.show();
	// }
	// }
	//
	// /**
	// *
	// * @author :Atar
	// * @createTime:2016-1-13下午4:47:12
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @param mContext
	// * @param width 默认传为为 13/15
	// * @param strTitle
	// * @param strContent
	// * @param strOk
	// * @param strCancle
	// * @param lsOk
	// * @param lsCacle
	// * @description:
	// */
	// public static void KToastDialog(Context mContext, Typeface typeFace, int width, String strTitle, String strContent, String strOk, String strCancle, OnClickListener lsOk, OnClickListener
	// lsCacle) {
	// if (mContext == null) {
	// return;
	// }
	// dialogDismiss();
	// mDialog = null;
	// mDialog = new Dialog(mContext, R.style.MyDialog);
	// mDialog.setContentView(R.layout.common_taoguba_k_toast_dialog);
	// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
	// if (width == 0) {
	// width = 13 * widthPixels / 15;
	// }
	// if (mContext.getResources().getConfiguration().orientation == 1) {
	// // 1竖屏
	// mDialog.getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
	// } else {
	// // 0横屏
	// mDialog.getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
	// }
	//
	// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
	// TextView txtContent = (TextView) mDialog.getWindow().findViewById(R.id.txt_content1);
	// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok2);
	// TextView cancel = (TextView) mDialog.getWindow().findViewById(R.id.cancel2);
	// LoadUtil.setTextTypeFace(ok, typeFace);
	// LoadUtil.setTextTypeFace(cancel, typeFace);
	// if (strTitle != null && !strTitle.equals("")) {
	// txtTitle.setText(Html.fromHtml(strTitle));
	// } else {
	// txtTitle.setVisibility(View.GONE);
	// cancel.setVisibility(View.GONE);
	// }
	// if (strContent != null && !strContent.equals("")) {
	// txtContent.setText((Html.fromHtml(strContent)));
	// txtContent.setVisibility(View.VISIBLE);
	// } else {
	// txtContent.setVisibility(View.GONE);
	// }
	// LoadUtil.setTextTypeFace(txtTitle, typeFace);
	// if (strTitle != null && strTitle.length() > 0 && (strContent == null || strContent.length() == 0)) {
	// txtTitle.setBackgroundColor(mContext.getResources().getColor(R.color.transparent0));
	// int padding = (int) ScreenUtils.getIntToDip(mContext, 5);
	// txtTitle.setPadding(3 * padding, 6 * padding, 3 * padding, 3 * padding);
	// txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
	// }
	//
	// ok.setText(CommonStringUtil.emptyIfNull(strOk));
	// if (strCancle != null && strCancle.length() > 0) {
	// cancel.setText(strCancle);
	// cancel.setVisibility(View.VISIBLE);
	// } else {
	// cancel.setVisibility(View.GONE);
	// }
	// if (lsOk != null) {
	// ok.setOnClickListener(lsOk);
	// }
	// if (lsCacle != null) {
	// cancel.setOnClickListener(lsCacle);
	// } else {
	// cancel.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View arg0) {
	// dialogDismiss();
	// }
	// });
	// }
	// mDialog.setCanceledOnTouchOutside(false);
	// mDialog.show();
	// }
	//
	// /**
	// *
	// * @author :Atar
	// * @createTime:2016-6-27下午3:14:39
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @param mContext
	// * @param stytle 样式 0：没有标题 1：内容第一行正常字体 第二行大点特殊字体 2：第一行大点特殊字体 第二行普通字体 3：只有一种字体一行 可换行 4:第一行大点特殊字体,隐藏 第二行
	// * @param width 默认传为为 13/15
	// * @param typeFace
	// * @param strTitle
	// * @param txtLine1
	// * @param txtLine2
	// * @param strOk
	// * @param strCancle
	// * @param lsOk
	// * @description:
	// */
	// public static void KToastDialog(Context mContext, int stytle, int width, Typeface typeFace, String strTitle, String txtLine1, String txtLine2, String strOk, String strCancle,
	// OnClickListener lsOk, OnClickListener lsCacle) {
	// if (mContext == null) {
	// return;
	// }
	// dialogDismiss();
	// mDialog = null;
	// mDialog = new Dialog(mContext, R.style.MyDialog);
	// mDialog.setContentView(R.layout.common_taoguba_kgame_dialog_mutil_style);
	// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
	// if (width == 0) {
	// width = 13 * widthPixels / 15;
	// }
	// if (mContext.getResources().getConfiguration().orientation == 1) {
	// // 1竖屏
	// mDialog.getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
	// } else {
	// // 0横屏
	// mDialog.getWindow().setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT);
	// }
	// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
	// TextView txtContentLine1 = (TextView) mDialog.getWindow().findViewById(R.id.txt_line1);
	// TextView txtContentLine2 = (TextView) mDialog.getWindow().findViewById(R.id.txt_line2);
	// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok2);
	// TextView cancel = (TextView) mDialog.getWindow().findViewById(R.id.cancel2);
	// ImageView img_close = (ImageView) mDialog.findViewById(R.id.img_close);
	// LoadUtil.setTextTypeFace(ok, typeFace);
	// LoadUtil.setTextTypeFace(cancel, typeFace);
	// if (strTitle != null && strTitle.length() > 0) {
	// txtTitle.setText(Html.fromHtml(strTitle));
	// LoadUtil.setTextTypeFace(txtTitle, typeFace);
	// } else {
	// txtTitle.setVisibility(View.GONE);
	// img_close.setVisibility(View.GONE);
	// }
	// if (txtLine1 != null && txtLine1.length() > 0) {
	// txtContentLine1.setText(Html.fromHtml(txtLine1));
	// }
	// if (txtLine2 != null && txtLine2.length() > 0) {
	// txtContentLine2.setText(Html.fromHtml(txtLine2));
	// }
	// switch (stytle) {
	// case 0:
	// txtTitle.setVisibility(View.GONE);
	// LoadUtil.setTextTypeFace(txtContentLine1, typeFace);
	// txtContentLine2.setVisibility(View.GONE);
	// break;
	// case 1:
	// txtTitle.setVisibility(View.VISIBLE);
	// txtContentLine2.setTextColor(mContext.getResources().getColor(R.color.color_k_game_txt_yellow));
	// txtContentLine2.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
	// LoadUtil.setTextTypeFace(txtContentLine2, typeFace);
	// break;
	// case 2:
	// txtTitle.setVisibility(View.VISIBLE);
	// txtContentLine1.setTextColor(mContext.getResources().getColor(R.color.color_k_game_txt_yellow));
	// txtContentLine1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
	// LoadUtil.setTextTypeFace(txtContentLine1, typeFace);
	// LoadUtil.setTextTypeFace(txtContentLine2, typeFace);
	// break;
	// case 3:
	// txtTitle.setVisibility(View.VISIBLE);
	// txtContentLine2.setVisibility(View.GONE);
	// break;
	// case 4:
	// txtTitle.setVisibility(View.VISIBLE);
	// txtContentLine1.setTextColor(mContext.getResources().getColor(R.color.color_k_game_txt_yellow));
	// txtContentLine1.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 20);
	// LoadUtil.setTextTypeFace(txtContentLine1, typeFace);
	// LoadUtil.setTextTypeFace(txtContentLine2, typeFace);
	// txtContentLine2.setVisibility(View.GONE);
	// break;
	// default:
	// break;
	// }
	//
	// if (strCancle != null && strCancle.length() > 0) {
	// cancel.setText(strCancle);
	// cancel.setVisibility(View.VISIBLE);
	// img_close.setVisibility(View.GONE);
	// } else {
	// cancel.setVisibility(View.GONE);
	// LayoutParams lp = (LayoutParams) ok.getLayoutParams();
	// lp.rightMargin = 0;
	// ok.setLayoutParams(lp);
	// }
	// ok.setText(strOk);
	// if (lsOk != null) {
	// ok.setOnClickListener(lsOk);
	// } else {
	// ok.setOnClickListener(new OnClickListener() {
	//
	// @Override
	// public void onClick(View v) {
	// mDialog.dismiss();
	// }
	// });
	// }
	// if (lsCacle != null) {
	// cancel.setOnClickListener(lsCacle);
	// } else {
	// cancel.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// mDialog.dismiss();
	// }
	// });
	// }
	// img_close.setOnClickListener(new OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// mDialog.dismiss();
	// }
	// });
	// mDialog.setCanceledOnTouchOutside(false);
	// mDialog.setOnKeyListener(new OnKeyListener() {
	// @Override
	// public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
	// return true;
	// }
	// });
	// mDialog.show();
	// }
	//
	// public static void scoreDialog(Context mContext, String strTitle, String strContent, OnClickListener ls) {
	// // if ((mDialog != null && mDialog.isShowing()) || mContext == null) {
	// // return;
	// // } else {
	// dialogDismiss();
	// // }
	// mDialog = new Dialog(mContext, R.style.MyDialog);
	// mDialog.setContentView(R.layout.common_taoguba_score_dialog);
	// int widthPixels = AtarApplication.getApplication().getResources().getDisplayMetrics().widthPixels;
	// if (mContext.getResources().getConfiguration().orientation == 1) {
	// // 1竖屏
	// mDialog.getWindow().setLayout(13 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
	// } else {
	// // 0横屏
	// mDialog.getWindow().setLayout(8 * widthPixels / 15, WindowManager.LayoutParams.WRAP_CONTENT);
	// }
	//
	// TextView txtTitle = (TextView) mDialog.getWindow().findViewById(R.id.txt_title);
	// if (strTitle != null && !strTitle.equals("")) {
	// txtTitle.setText(strTitle);
	// }
	// TextView txtContent = (TextView) mDialog.getWindow().findViewById(R.id.txt_content0);
	// if (strContent != null && !strContent.equals("")) {
	// txtContent.setText(strContent);
	// txtContent.setVisibility(View.VISIBLE);
	// } else {
	// txtContent.setVisibility(View.GONE);
	// }
	// TextView ok = (TextView) mDialog.getWindow().findViewById(R.id.ok);
	// View view = mDialog.getWindow().findViewById(R.id.common_dialog_main);
	// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
	// LoadUtil.setBackgroundDrawable(mContext, ok, R.array.common_ok_bg_drawable, skinType);
	// LoadUtil.setBackgroundDrawable(mContext, view, R.array.common_dialog_bg_drawable, skinType);
	// LoadUtil.setTextColor(mContext, txtTitle, R.array.common_dialog_txt_color, skinType);
	// LoadUtil.setTextColor(mContext, txtContent, R.array.common_dialog_txt_color, skinType);
	// LoadUtil.setTextColor(mContext, ok, R.array.common_activity_title_color, skinType);
	// if (ls != null) {
	// ok.setOnClickListener(ls);
	// } else {
	// ok.setOnClickListener(new View.OnClickListener() {
	// @Override
	// public void onClick(View v) {
	// dialogDismiss();
	// }
	// });
	// }
	// mDialog.setCanceledOnTouchOutside(false);
	// mDialog.show();
	// }
	//
	// /**
	// * 实盘查看他的战绩
	// * @author :Atar
	// * @createTime:2016-11-30下午5:34:59
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @param v
	// * @param strTitle 查看谁
	// * @param needPoint 查看需要积分
	// * @param hasPoint 当前已有积分
	// * @param mOnClickListener
	// * @description:
	// */
	// public static void shipanLookHisGains(Context mContext, String strTitle, final int needPoint, final OnClickListener ls) {
	// dialogDismiss();
	// mDialog = new Dialog(mContext, R.style.MyDialog);
	// mDialog.setContentView(R.layout.pupwin_look_his_gains);
	// Window win = mDialog.getWindow();
	// mDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
	// TextView txt_look_whose_gains = (TextView) win.findViewById(R.id.txt_look_whose_gains);
	// View linear_layout_button = win.findViewById(R.id.linear_layout_button);
	// TextView txt_look_his_gains = (TextView) win.findViewById(R.id.txt_look_his_gains);
	// TextView txt_look_his_gains_point = (TextView) win.findViewById(R.id.txt_look_his_gains_point);
	// TextView txt_current_lable = (TextView) win.findViewById(R.id.txt_current_lable);
	// final TextView txt_current_point = (TextView) win.findViewById(R.id.txt_current_point);
	//
	// final int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
	// LoadUtil.setTextColor(mContext, R.array.common_activity_title_color, skinType, txt_look_his_gains, txt_look_his_gains_point);
	// LoadUtil.setTextColor(mContext, R.array.txt_day_black_night_greywhite_color2, skinType, txt_current_lable, txt_current_point);
	// LoadUtil.setBackgroundDrawable(mContext, R.array.selector_blue_bg_drawable, skinType, linear_layout_button);
	// LoadUtil.setBackgroundColor(mContext, R.array.more_white_black_bg, skinType, win.getDecorView());
	// NetWorkInterfaces.GetUserScore((Activity) mContext, new HandleMessageListener() {
	// @Override
	// public void NetWorkHandlMessage(Message msg) {
	// switch (msg.what) {
	// case EnumMsgWhat.EInterface_Get_User_Score:
	// if (msg.obj != null) {
	// ScoreJson mScoreJson = (ScoreJson) msg.obj;
	// if (mScoreJson != null) {
	// if (mScoreJson.isStatus()) {
	// txt_current_point.setText(Html.fromHtml("<font color=\"#ff3e1e\">" + mScoreJson.getDto() + "积分</font>"));
	// } else {
	// CommonToast.show(mScoreJson.getErrorMessage());
	// }
	// }
	// }
	// break;
	// default:
	// break;
	// }
	// }
	// }, "");
	// if (strTitle != null && strTitle.length() > 0) {
	// txt_look_whose_gains.setText(strTitle);
	// }
	// txt_look_his_gains_point.setText("(" + needPoint + "积分)");
	// // txt_current_point.setText(hasPoint + "");
	// if (ls != null) {
	// linear_layout_button.setOnClickListener(ls);
	// }
	// // mDialog.setCanceledOnTouchOutside(false);
	// android.view.WindowManager.LayoutParams params = win.getAttributes();
	// params.gravity = Gravity.BOTTOM;
	// win.setAttributes(params);
	// mDialog.show();
	// }
	//
	// /**
	// * 实盘查看他的战绩
	// * @author :Atar
	// * @createTime:2016-11-30下午5:34:59
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @param v
	// * @param strTitle “再战杯2016.10.09-2016.10.10”
	// * @param lookNeedPoint 查看一次需要积分
	// * @param baochangNeedPoint 包场需要积分
	// * @param mOnClickListener
	// * @description:
	// */
	// public static void shipanOrderMatch(Context mContext, String strTitle, final int lookNeedPoint, int baochangNeedPoint, final OnClickListener ls1, final OnClickListener ls2,
	// final OnClickListener ls3) {
	// dialogDismiss();
	// mDialog = new Dialog(mContext, R.style.MyDialog);
	// mDialog.setContentView(R.layout.dialog_order_match);
	// Window win = mDialog.getWindow();
	// mDialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
	// TextView txt_title = (TextView) win.findViewById(R.id.txt_title);
	// TextView txt_look_once = (TextView) win.findViewById(R.id.txt_look_once);
	// TextView txt_look_point = (TextView) win.findViewById(R.id.txt_look_point);
	// TextView txt_baochang = (TextView) win.findViewById(R.id.txt_baochang);
	// TextView txt_baochang_point = (TextView) win.findViewById(R.id.txt_baochang_point);
	// TextView txt_free_look = (TextView) win.findViewById(R.id.txt_free_look);
	// TextView txt_has_point_lable = (TextView) win.findViewById(R.id.txt_has_point_lable);
	// final TextView txt_has_point = (TextView) win.findViewById(R.id.txt_has_point);
	//
	// View linear_layout_look_once = win.findViewById(R.id.linear_layout_look_once);
	// View linear_layout_baochang = win.findViewById(R.id.linear_layout_baochang);
	//
	// final int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
	// LoadUtil.setTextColor(mContext, R.array.common_activity_title_color, skinType, txt_look_once, txt_look_point, txt_baochang, txt_baochang_point, txt_free_look);
	// LoadUtil.setTextColor(mContext, R.array.txt_day_black_night_greywhite_color2, skinType, txt_has_point_lable, txt_title);
	// LoadUtil.setBackgroundDrawable(mContext, R.array.selector_blue_bg_drawable, skinType, linear_layout_look_once, linear_layout_baochang, txt_free_look);
	// LoadUtil.setBackgroundColor(mContext, R.array.more_white_black_bg, skinType, win.getDecorView());
	// NetWorkInterfaces.GetUserScore((Activity) mContext, new HandleMessageListener() {
	// @Override
	// public void NetWorkHandlMessage(Message msg) {
	// switch (msg.what) {
	// case EnumMsgWhat.EInterface_Get_User_Score:
	// if (msg.obj != null) {
	// ScoreJson mScoreJson = (ScoreJson) msg.obj;
	// if (mScoreJson != null) {
	// if (mScoreJson.isStatus()) {
	// txt_has_point.setText(mScoreJson.getDto() + "积分");
	// } else {
	// CommonToast.show(mScoreJson.getErrorMessage());
	// }
	// }
	// }
	// break;
	// default:
	// break;
	// }
	// }
	// }, "");
	// if (strTitle != null && strTitle.length() > 0) {
	// txt_title.setText("您想订阅“" + strTitle + "”这场比赛，请选择订阅方式");
	// }
	// txt_look_point.setText("(" + lookNeedPoint + "积分)");
	// txt_baochang_point.setText("(" + baochangNeedPoint + "积分)");
	// if (ls1 != null) {
	// linear_layout_look_once.setOnClickListener(ls1);
	// }
	// if (ls2 != null) {
	// linear_layout_baochang.setOnClickListener(ls2);
	// }
	// if (ls3 != null) {
	// txt_free_look.setOnClickListener(ls3);
	// }
	// android.view.WindowManager.LayoutParams params = win.getAttributes();
	// params.gravity = Gravity.BOTTOM;
	// win.setAttributes(params);
	// mDialog.show();
	// }
}

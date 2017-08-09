///**
// * 
// */
//package com.atar.common;
//
//import java.util.List;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.appconfig.AppConfigSetting;
//import android.content.Context;
//import android.enums.SkinMode;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.graphics.drawable.Drawable;
//import android.interfaces.HandleMessageListener;
//import android.os.Handler;
//import android.os.Message;
//import android.text.Html;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.view.WindowManager;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ArrayAdapter;
//import android.widget.Button;
//import android.widget.CommonToast;
//import android.widget.EditText;
//import android.widget.GridView;
//import android.widget.ListView;
//import android.widget.PopupWindow;
//import android.widget.PopupWindow.OnDismissListener;
//import android.widget.TextView;
//
//import com.atar.activity.R;
//
///**
// ***************************************************************************************************************************************************************************** 
// * 淘股吧公用popwindw
// * 
// * @author :Atar
// * @createTime:2014-8-11下午3:29:29
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// ***************************************************************************************************************************************************************************** 
// */
//@SuppressLint({ "Recycle", "InflateParams" })
//public class CommonPopWindow {
//	private static boolean isShowing;
//	private static PopupWindow mPopupWindow;
//
//	/**
//	 * 显示popwindow gravity默认传-1为showAsDropDown
//	 * @author :Atar
//	 * @createTime:2017-6-8下午1:57:09
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param v 依附View
//	 * @param isAnimation
//	 * @param mPopupWindowDrawable
//	 * @param gravity   默认-1
//	 * @param x x偏移量
//	 * @param y y偏移量
//	 * @param popwindowWidth PopupWindow宽度
//	 * @param mList 类型
//	 *            <p>
//	 *            List<PopWindowItemBean>
//	 *            </p>
//	 * @param listener OnItemClickListener
//	 * @description:
//	 */
//	@SuppressWarnings("static-access")
//	public static void show(View v, boolean isAnimation, Drawable mPopupWindowDrawable, int gravity, int x, int y, int popwindowWidth, List<PopWindowItemBean> mList, OnItemClickListener listener) {
//		if (v.getContext() != null) {
//			String[] strArr;
//			if (mList != null && mList.size() > 0) {
//				strArr = new String[mList.size()];
//				for (int i = 0; i < mList.size(); i++) {
//					strArr[i] = mList.get(i).getDropDownItemName();
//				}
//			} else {
//				return;
//			}
//			LayoutInflater mLayoutInflater = (LayoutInflater) v.getContext().getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
//			ViewGroup view = (ViewGroup) mLayoutInflater.inflate(R.layout.activity_taoguba_common_title_drop, null, true);
//			ArrayAdapter<String> adapter = new ArrayAdapter<String>(v.getContext(), R.layout.adapter_item_drop_title, strArr);
//			((ListView) view).setAdapter(adapter);
//			((ListView) view).setOnItemClickListener(listener);
//			if (mPopupWindow != null) {
//				mPopupWindow.dismiss();
//			}
//			mPopupWindow = new PopupWindow(view, popwindowWidth, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//			mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//			if (isAnimation) {
//				mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
//			}
//			if (mPopupWindowDrawable == null) {
//				mPopupWindow.setBackgroundDrawable(v.getContext().getResources().getDrawable(R.drawable.select_dailog_back));
//			} else {
//				mPopupWindow.setBackgroundDrawable(mPopupWindowDrawable);
//			}
//			isShowing = true;
//			mPopupWindow.setOnDismissListener(new OnDismissListener() {
//				@Override
//				public void onDismiss() {
//					isShowing = false;
//				}
//			});
//			if (gravity == -1) {
//				mPopupWindow.showAsDropDown(v, x, y);
//			} else {
//				mPopupWindow.showAtLocation(v, gravity, x, y);
//			}
//		}
//	}
//
//	/**
//	 * 显示popwindow gravity默认传-1为showAsDropDown
//	 * 
//	 * @author :Atar
//	 * @createTime:2014-8-11下午3:46:34
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param v
//	 * @param gravity
//	 *            默认-1
//	 * @param x
//	 * @param y
//	 * @param popwindowWidth
//	 * @param mList
//	 *            类型
//	 *            <p>
//	 *            List<PopWindowItemBean>
//	 *            </p>
//	 * @param OnItemClickListener
//	 * @description:
//	 */
//	public static void show(View v, int gravity, int x, int y, int popwindowWidth, List<PopWindowItemBean> mList, OnItemClickListener listener) {
//		show(v, true, null, gravity, x, y, popwindowWidth, mList, listener);
//	}
//
//	@SuppressWarnings("static-access")
//	public static void show(View v, int x, int y, int popwindowWidth, int currentPosition, List<PopWindowItemBean> mList, OnItemClickListener listener, final OnDismissListener mOnDismissListener) {
//		if (v != null && v.getContext() != null) {
//			LayoutInflater mLayoutInflater = (LayoutInflater) v.getContext().getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
//			ViewGroup dropView = (ViewGroup) mLayoutInflater.inflate(R.layout.activity_taoguba_common_title_drop, null, true);
//			DropDownAdapter mDropDownAdapter = new DropDownAdapter(mList);
//			mDropDownAdapter.setTxtSize(18);
//			mDropDownAdapter.setCurrentPostiotn(currentPosition);
//			((ListView) dropView).setAdapter(mDropDownAdapter);
//			((ListView) dropView).setDividerHeight(0);
//			((ListView) dropView).setOnItemClickListener(listener);
//			if (mPopupWindow != null) {
//				mPopupWindow.dismiss();
//			}
//			mPopupWindow = new PopupWindow(dropView, popwindowWidth, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//			mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//			mPopupWindow.setOnDismissListener(new OnDismissListener() {
//				@Override
//				public void onDismiss() {
//					if (mOnDismissListener != null) {
//						mOnDismissListener.onDismiss();
//					}
//					mPopupWindow = null;
//				}
//			});
//			mPopupWindow.setBackgroundDrawable(v.getContext().getResources().getDrawable(R.drawable.corners_transparent8));
//			mPopupWindow.showAsDropDown(v, x, y);
//		}
//	}
//
//	@SuppressWarnings("static-access")
//	public static void showKGamePopwin(View v, int gravity, int x, int y, int popwindowWidth, int currentPosition, List<PopWindowItemBean> mList, OnItemClickListener listener,
//			final OnDismissListener mOnDismissListener) {
//		if (v != null && v.getContext() != null) {
//			LayoutInflater mLayoutInflater = (LayoutInflater) v.getContext().getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
//			ViewGroup dropView = (ViewGroup) mLayoutInflater.inflate(R.layout.activity_taoguba_common_title_drop, null, true);
//			SwitchKCharAdapter mDropDownAdapter = new SwitchKCharAdapter(mList);
//			mDropDownAdapter.setCurrentPostiotn(currentPosition);
//			((ListView) dropView).setAdapter(mDropDownAdapter);
//			((ListView) dropView).setOnItemClickListener(listener);
//			LoadUtil.setDivider(v.getContext(), ((ListView) dropView), 2);
//			if (mPopupWindow != null) {
//				mPopupWindow.dismiss();
//			}
//			mPopupWindow = new PopupWindow(dropView, popwindowWidth, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//			mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//			mPopupWindow.setOnDismissListener(new OnDismissListener() {
//				@Override
//				public void onDismiss() {
//					if (mOnDismissListener != null) {
//						mOnDismissListener.onDismiss();
//					}
//					mPopupWindow = null;
//				}
//			});
//			mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#083042")));
//			mPopupWindow.showAtLocation(v, gravity, x, y);
//		}
//	}
//
//	/**
//	 * PopupWindow toast
//	 * 
//	 * @author :Atar
//	 * @createTime:2015-6-10下午3:11:54
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param v
//	 * @param context
//	 * @param strToast
//	 * @description:
//	 */
//	@SuppressWarnings("static-access")
//	public static void show(View v, Context context, String strToast) {
//		if (context != null && v != null) {
//			LayoutInflater mLayoutInflater = (LayoutInflater) v.getContext().getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
//			ViewGroup view = (ViewGroup) mLayoutInflater.inflate(R.layout.toast_popupwindow, null);
//			TextView txtToast = (TextView) view.findViewById(R.id.txt_toast_popupwindow);
//			txtToast.setText(strToast);
//			if (mPopupWindow != null) {
//				mPopupWindow.dismiss();
//			}
//			mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
//			int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
//			LoadUtil.setBackgroundDrawable(context, view, R.array.drop_down_pop, skinType);
//			LoadUtil.setTextColor(context, txtToast, R.array.txt_day_black_night_greywhite_color, skinType);
//			mPopupWindow.setOnDismissListener(new OnDismissListener() {
//				@Override
//				public void onDismiss() {
//					mPopupWindow = null;
//				}
//			});
//			mPopupWindow.showAsDropDown(v);
//			Handler handler = new Handler();
//			handler.postDelayed(new Runnable() {
//
//				@Override
//				public void run() {
//					dismiss();
//				}
//			}, 3000);
//		}
//	}
//
//	/**
//	 * 显示打赏
//	 * 
//	 * @author :Atar
//	 * @createTime:2014-10-20下午4:28:11
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param v
//	 * @param gravity
//	 * @param usableScoreNum
//	 * @param currentPosition
//	 * @param listener
//	 * @param mOnClickListener
//	 * @description:
//	 */
//	@SuppressWarnings("static-access")
//	public static void show(final View v, AmusementAdapter mAmusementAdapter, String name, OnItemClickListener listener, OnClickListener mOnClickListener) {
//		if (v.getContext() != null) {
//			LayoutInflater mLayoutInflater = (LayoutInflater) v.getContext().getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
//			ViewGroup view = (ViewGroup) mLayoutInflater.inflate(R.layout.common_popup_score, null, true);
//			final TextView txtUsableScore = (TextView) view.findViewById(R.id.txt_usable_score);
//			View view_top_line = view.findViewById(R.id.view_top_line);
//			TextView txtReloadScore = (TextView) view.findViewById(R.id.txt_reload_score);
//			TextView txtToWho = (TextView) view.findViewById(R.id.txt_to_who);
//			Button btnOk = (Button) view.findViewById(R.id.btn_ok);
//			GridView gridView = (GridView) view.findViewById(R.id.grid_view);
//			gridView.setAdapter(mAmusementAdapter);
//			if (listener != null) {
//				gridView.setOnItemClickListener(listener);
//			}
//			if (mOnClickListener != null) {
//				btnOk.setOnClickListener(mOnClickListener);
//			}
//			txtReloadScore.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					StatService.onEvent(v.getContext(), "0001", "打赏-充值积分", 1);
//					SubSettingActivity.startSubSettingActivity(v.getContext(), 101);
//				}
//			});
//			final int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
//			LoadUtil.setBackgroundColor(v.getContext(), btnOk, R.array.common_top_title_bar_bg_color, skinType);
//			LoadUtil.setBackgroundColor(v.getContext(), view_top_line, R.array.txt_line_color, skinType);
//			LoadUtil.setTextColor(v.getContext(), btnOk, R.array.common_activity_title_color, skinType);
//			LoadUtil.setBackgroundDrawable(v.getContext(), txtReloadScore, R.array.txt_reload_score_bg_drawable, skinType);
//			LoadUtil.setTextColor(v.getContext(), txtReloadScore, R.array.txt_white_to_black, skinType);
//			mAmusementAdapter.setSkinType(skinType);
//			String nameColor = v.getContext().getResources().getStringArray(R.array.txt_day_black_night_greywhite_string)[skinType];
//			txtToWho.setText(Html.fromHtml("<font color=\"" + nameColor + "\">打赏给</font><font color=\"#FF0000\">" + name + "</font>"));
//			String numColor = v.getContext().getResources().getStringArray(R.array.txt_day_grey_night_greyblack_string)[skinType];
//			txtUsableScore.setText(Html.fromHtml("<font color=\"" + numColor + "\">可用</font><font color=\"#FF0000\"> 0 </font><font color=\"" + numColor + "\">积分</font>"));
//			NetWorkInterfaces.GetUserScore((Activity) v.getContext(), new HandleMessageListener() {
//				@Override
//				public void NetWorkHandlMessage(Message msg) {
//					switch (msg.what) {
//					case EnumMsgWhat.EInterface_Get_User_Score:
//						if (msg.obj != null) {
//							ScoreJson mScoreJson = (ScoreJson) msg.obj;
//							if (mScoreJson != null) {
//								if (mScoreJson.isStatus(v.getContext())) {
//									String numColor = v.getContext().getResources().getStringArray(R.array.txt_day_grey_night_greyblack_string)[skinType];
//									txtUsableScore.setText(Html.fromHtml("<font color=\"" + numColor + "\">可用</font><font color=\"#FF0000\"> " + mScoreJson.getDto() + " </font><font color=\""
//											+ numColor + "\">积分</font>"));
//								} else {
//									CommonToast.show(mScoreJson.getErrorMessage());
//								}
//							} else {
//
//							}
//						}
//						break;
//					default:
//						break;
//					}
//				}
//			}, "");
//			mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//			mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//			mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
//			mPopupWindow.setBackgroundDrawable(v.getContext().getResources().obtainTypedArray(R.array.more_white_black_bg).getDrawable(skinType));
//			mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
//		}
//	}
//
//	/**
//	 * 显示自定义软键盘
//	 * 
//	 * @author :Atar
//	 * @createTime:2015-3-18下午6:15:22
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param context
//	 * @description:
//	 */
//	public static void showKeabord(Context context, View v, EditText mEditText) {
//		int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
//		TaogubaKeboardView mTaogubaKeboardView = new TaogubaKeboardView(context);
//		mTaogubaKeboardView.setEditText(mEditText);
//		mTaogubaKeboardView.setSkinType(skinType);
//		mPopupWindow = new PopupWindow(mTaogubaKeboardView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, false);
//		mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
//		mPopupWindow.setBackgroundDrawable(context.getResources().obtainTypedArray(R.array.keaoard_bg_color).getDrawable(skinType));
//		mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
//		isShowing = true;
//		mPopupWindow.setOnDismissListener(new OnDismissListener() {
//			@Override
//			public void onDismiss() {
//				isShowing = false;
//			}
//		});
//		mTaogubaKeboardView.setOnDismissListener(new OnDismissListener() {
//			@Override
//			public void onDismiss() {
//				mPopupWindow.dismiss();
//			}
//		});
//	}
//
//	@SuppressWarnings("static-access")
//	public static void KSignDialog(View v, String strTitle, OnClickListener mOnClickListener) {
//		if (v.getContext() != null) {
//			LayoutInflater mLayoutInflater = (LayoutInflater) v.getContext().getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
//			ViewGroup view = (ViewGroup) mLayoutInflater.inflate(R.layout.common_k_popupwindow, null);
//			TextView txtTitle = (TextView) view.findViewById(R.id.txt_title);
//			Button btnSign = (Button) view.findViewById(R.id.btn_sign);
//
//			mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//			int widthPixels = v.getContext().getResources().getDisplayMetrics().widthPixels;
//			if (v.getContext().getResources().getConfiguration().orientation == 1) {
//				// 1竖屏
//				mPopupWindow.setWidth(13 * widthPixels / 15);
//			} else {
//				// 0横屏
//				mPopupWindow.setWidth(8 * widthPixels / 15);
//			}
//			if (strTitle != null && strTitle.length() > 0) {
//				txtTitle.setText(strTitle);
//			}
//			if (mOnClickListener != null) {
//				btnSign.setOnClickListener(mOnClickListener);
//			}
//			mPopupWindow.setBackgroundDrawable(v.getContext().getResources().getDrawable(R.drawable.corners_k_dialog_bg));
//			mPopupWindow.setAnimationStyle(R.style.popwin_anim_top_in_top_out_style);
//			mPopupWindow.showAtLocation(v, Gravity.CENTER, 0, 0);
//		}
//	}
//
//	/**
//	 * 直呼偷偷听
//	 * 
//	 * @author :Atar
//	 * @createTime:2014-10-20下午4:28:11
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param v
//	 * @param gravity
//	 * @param usableScoreNum
//	 * @param currentPosition
//	 * @param listener
//	 * @param mOnClickListener
//	 * @description:
//	 */
//	@SuppressWarnings("static-access")
//	public static void secretListen(final View v, final ZhihuQuestionJson mZhihuQuestionJson, OnClickListener ls) {
//		if (v.getContext() != null) {
//			LayoutInflater mLayoutInflater = (LayoutInflater) v.getContext().getSystemService(v.getContext().LAYOUT_INFLATER_SERVICE);
//			ViewGroup view = (ViewGroup) mLayoutInflater.inflate(R.layout.common_popup_secret_listen, null, true);
//
//			final TextView txtUsableScore = (TextView) view.findViewById(R.id.txt_usable_score);
//			TextView txtReloadScore = (TextView) view.findViewById(R.id.txt_reload_score);
//			TextView txt_listen_whoinfo = (TextView) view.findViewById(R.id.txt_listen_whoinfo);
//			TextView txt_need_score = (TextView) view.findViewById(R.id.txt_need_score);
//			TextView div_need_score = (TextView) view.findViewById(R.id.div_need_score);
//			TextView txt_need_money = (TextView) view.findViewById(R.id.txt_need_money);
//			Button btnOk = (Button) view.findViewById(R.id.btn_ok);
//			if (ls != null) {
//				btnOk.setOnClickListener(ls);
//			}
//
//			txtReloadScore.setOnClickListener(new OnClickListener() {
//				@Override
//				public void onClick(View arg0) {
//					StatService.onEvent(v.getContext(), "0001", "打赏-充值积分", 1);
//					SubSettingActivity.startSubSettingActivity(v.getContext(), 101);
//				}
//			});
//			final int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
//
//			LoadUtil.setBackgroundColor(v.getContext(), btnOk, R.array.common_top_title_bar_bg_color, skinType);
//			LoadUtil.setTextColor(v.getContext(), btnOk, R.array.common_activity_title_color, skinType);
//			LoadUtil.setTextColor(v.getContext(), txt_need_money, R.array.txt_day_grey_night_greyblack_color, skinType);
//			LoadUtil.setBackgroundColor(v.getContext(), div_need_score, R.array.common_dynamic_content_bg_color, skinType);
//
//			String greyColor = v.getContext().getResources().getStringArray(R.array.txt_day_grey_night_greyblack_string)[skinType];
//			String blueColor = v.getContext().getResources().getStringArray(R.array.common_top_title_bar_bg_string)[skinType];
//
//			txtUsableScore.setText(Html.fromHtml("<font color=\"" + greyColor + "\">可用积分</font><font color=\"#FF0000\">" + mZhihuQuestionJson.getDto().getMyPoint() + " </font>"));
//			txt_listen_whoinfo.setText(Html.fromHtml("<font color=\"" + greyColor + "\">偷偷听</font><font color=\"" + blueColor + "\">" + mZhihuQuestionJson.getDto().getAnswerUserName()
//					+ "</font><font color=\"" + greyColor + "\">回答</font><font color=\"" + blueColor + "\">" + mZhihuQuestionJson.getDto().getUserName() + "</font><font color=\"" + greyColor
//					+ "\">的直呼问题</font>"));
//			txt_need_score.setText(Html.fromHtml("<font color=\"" + greyColor + "\">需要</font><font color=\"#FF0000\">" + mZhihuQuestionJson.getDto().getNeedPoint() + "积分</font>"));
//			txt_need_money.setText("（" + mZhihuQuestionJson.getDto().getNeedPoint() / 50 + "元）");
//
//			mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//			mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//			mPopupWindow.setAnimationStyle(R.style.popwin_anim_style);
//			mPopupWindow.setBackgroundDrawable(v.getContext().getResources().obtainTypedArray(R.array.common_edittext_bg).getDrawable(skinType));
//			mPopupWindow.showAtLocation(v, Gravity.BOTTOM, 0, 0);
//		}
//	}
//
//	public static boolean isShowing() {
//		return isShowing;
//	}
//
//	/**
//	 * 消失popwindow
//	 * 
//	 * @author :Atar
//	 * @createTime:2014-8-11下午3:57:03
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	public static void dismiss() {
//		if (mPopupWindow != null) {
//			mPopupWindow.dismiss();
//		}
//	}
// }

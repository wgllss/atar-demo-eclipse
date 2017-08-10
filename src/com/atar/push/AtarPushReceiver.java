package com.atar.push;
//package com.atar.push;
//
//import android.activity.ActivityManager;
//import android.appconfig.AppConfigSetting;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.enums.SkinMode;
//import android.net.Uri;
//import android.os.Bundle;
//import android.utils.ShowLog;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonSyntaxException;
//
///**
// * 
// *****************************************************************************************************************************************************************************
// * 接受推送系统
// * @author :Atar
// * @createTime:2014-8-21下午2:58:22
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class TaogubaPushReceiver extends BroadcastReceiver {
//	private static final String TAG = TaogubaPushReceiver.class.getSimpleName();
//
//	@Override
//	public void onReceive(Context context, Intent intent) {
//
//		if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {// 点击通知栏
//			Bundle bundle = intent.getExtras();
//			if (bundle != null) {
//				try {
//					JPushInterface.reportNotificationOpened(context, bundle.getString(JPushInterface.EXTRA_MSG_ID));
//					String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//					ShowLog.i(TAG, "extras---0-->" + extras);
//					ShowLog.i(TAG, "EXTRA_MESSAGE---0-->" + bundle.getString(JPushInterface.EXTRA_MESSAGE));
//					if (ActivityManager.getActivityManager().getActivityStack() != null && ActivityManager.getActivityManager().getActivityStack().size() > 0) {
//						setStartActivity(context, extras, true);
//					} else {
//						WelcomeActivity.startWelcomeActivity(context, extras);
//					}
//				} catch (Exception e) {
//
//				}
//			}
//		}
//	}
//
//	public static void setStartActivity(Context context, String extras, boolean isFlagActivityNewTask) {
//		try {
//			ShowLog.d(TAG, "extras===" + extras);
//			PushBean obj = null;
//			Gson gson = new Gson();
//			if (extras != null && !"".equals(extras)) {
//				try {
//					obj = gson.fromJson(extras, PushBean.class);
//				} catch (JsonSyntaxException e) {
//					e.printStackTrace();
//				}
//			}
//			if (obj != null) {
//				ShowLog.i(TAG, "extras---1->" + extras);
//				String actionName = obj.getActionName();
//				String topicID = obj.getObjectID();
//				if ("1".equals(obj.getType())) {// 特别关注
//					ShowLog.i(TAG, "extras---2->" + extras);
//					Intent i = new Intent();
//					if ("T".equals(actionName) || "TT".equals(actionName) || "SC".equals(actionName) || "R".equals(actionName) || "TR".equals(actionName)) {
//						i.setClass(context, TaogubaTopicActivity.class);
//						i.putExtra(TaogubaTopicActivity.TOPIC_ID_KEY, topicID);
//						if ("R".equals(actionName) || "TR".equals(actionName)) {
//							String otherId = String.valueOf(obj.getOtherID());
//							ShowLog.d(TAG, "otherId------>" + otherId);
//							i.putExtra(TaogubaTopicActivity.REPLY_ID_KEY, otherId);
//						}
//					} else if ("W".equals(actionName)) {// 到说说
//						i.setClass(context, ShuoWebViewActivity.class);
//						i.putExtra(ShuoWebViewActivity.TOPIC_FEED_ID_KEY, topicID);
//						i.putExtra(ShuoWebViewActivity.TOPIC_USER_ID_KEY, obj.getUserID());
//						ShowLog.i(TAG, "extras w ===" + extras);
//					} else if ("P".equals(actionName)) {// 说说评论
//						i.setClass(context, ShuoWebViewActivity.class);
//						i.putExtra(ShuoWebViewActivity.TOPIC_FEED_ID_KEY, topicID);
//						i.putExtra(ShuoWebViewActivity.TOPIC_USER_ID_KEY, obj.getOtherID());
//					} else if ("ZW".equals(actionName)) {
//						if (topicID != null && topicID.length() > 0) {
//							i.setClass(context, AnswerActivity.class);
//							i.putExtra(AnswerActivity.ZHIHU_SEQ_KEY, Integer.valueOf(topicID));
//						}
//					} else if ("DZ".equals(actionName)) {
//						if (ActivityManager.getActivityManager().getActivity(MyCallActivity.class) != null) {
//							ActivityManager.getActivityManager().finishActivity(MyCallActivity.class);
//						}
//						i.setClass(context, MyCallActivity.class);
//						i.setAction(Intent.ACTION_VIEW);
//						Uri uri = Uri.parse("taoguba://app.qrcode/myCall?currentItem=1");
//						i.setData(uri);
//					} else if ("DV".equals(actionName)) {
//						String top_right_img_url = "";
//						if (AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0) == 0) {
//							top_right_img_url = "assets://img/fenxiang.png";
//						} else if (AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0) == 1) {
//							top_right_img_url = "assets://img/fenxiang_n.png";
//						}
//						String options = "{\"seq_key\":\"" + obj.getObjectID() + "\"}";
//						TaogubaDynamicWebViewActivity.startTaogubaSysWebViewActivity(context, WeexUtils.WEEX_HOST + "assets/html/taoguba_dav.html", "大V观点", top_right_img_url, "", options, "0",
//								isFlagActivityNewTask);
//						return;
//					} else {
//						return;
//					}
//					if (i.getClass() != null) {
//						if (isFlagActivityNewTask) {
//							i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//						}
//						context.startActivity(i);
//					}
//				} else if ("2".equals(obj.getType())) {// 新闻
//					ShuoWebViewActivity.startShuoWebViewActivity(context, topicID, obj.getUserID(), isFlagActivityNewTask);
//				} else if ("3".equals(obj.getType())) {// 公告
//					ShuoWebViewActivity.startShuoWebViewActivity(context, topicID, obj.getUserID(), isFlagActivityNewTask);
//				} else if ("4".equals(obj.getType())) {// 研报
//					ShuoWebViewActivity.startShuoWebViewActivity(context, topicID, obj.getUserID(), isFlagActivityNewTask);
//				} else if ("5".equals(obj.getType())) {// 交易计划
//					if (obj.getOtherID() != null && !"null".equals(obj.getOtherID()) && obj.getOtherID().length() > 0) {
//						DealPlanDetailActivity.startDealPlanDetailActivity(context, Integer.valueOf(obj.getOtherID()), 0, isFlagActivityNewTask);
//					}
//				} else if ("9999".equals(obj.getType())) {// 自定义跳转类型
//					if (obj.getLinkurl() != null && obj.getLinkurl().length() > 0) {
//						try {
//							AppLinkUrlModeBean mlinkUrlMode = gson.fromJson(obj.getLinkurl(), AppLinkUrlModeBean.class);
//							if (mlinkUrlMode != null) {
//								AppConfigUtils.switchActivity(mlinkUrlMode.getAndroid(), context);
//							}
//						} catch (Exception e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
// }

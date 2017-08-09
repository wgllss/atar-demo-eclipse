//package com.atar.push;
//
//import java.util.List;
//
//import android.activity.ActivityManager;
//import android.app.NotificationManager;
//import android.appconfig.AppConfigModel;
//import android.content.Context;
//import android.os.Bundle;
//import android.telephony.TelephonyManager;
//import android.utils.ShowLog;
//
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import com.huawei.android.pushagent.api.PushEventReceiver;
//
///*
// * 接收Push所有消息的广播接收器
// */
//public class HuaWeiPushReceiver extends PushEventReceiver {
//	public static final String TAG = HuaWeiPushReceiver.class.getSimpleName();
//	public static final String HUAWEI_TOKEN = "HUAWEI_TOKEN";
//
//	/*
//	 * 显示Push消息
//	 */
//	public void showPushMessage(int type, String msg) {
//		// PustDemoActivity mPustTestActivity =
//		// MyApplication.instance().getMainActivity();
//		// if (mPustTestActivity != null) {
//		// Handler handler = mPustTestActivity.getHandler();
//		// if (handler != null) {
//		// Message message = handler.obtainMessage();
//		// message.what = type;
//		// message.obj = msg;
//		// handler.sendMessageDelayed(message, 1L);
//		// }
//		// }
//	}
//
//	@Override
//	public void onToken(Context context, String token, Bundle extras) {
//		String belongId = extras.getString("belongId");
//		String content = "获取token和belongId成功，token = " + token + ",belongId = " + belongId;
//		ShowLog.i(TAG, content);
//		String imei = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
//		ShowLog.i(TAG, "imei===" + imei);
//
//		AppConfigModel.getInstance().putString(HuaWeiPushReceiver.HUAWEI_TOKEN, token, true);
//
//		// showPushMessage(PustDemoActivity.RECEIVE_TOKEN_MSG, content);
//	}
//
//	@Override
//	public boolean onPushMsg(Context context, byte[] msg, Bundle bundle) {
//		try {
//			String content = "收到一条Push消息： " + new String(msg, "UTF-8");
//			ShowLog.i(TAG, content);
//			// showPushMessage(PustDemoActivity.RECEIVE_PUSH_MSG, content);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
//
//	@Override
//	public void onEvent(Context context, Event event, Bundle extras) {
//		String extra = extras.getString(BOUND_KEY.pushMsgKey);
//		// {"notification_title":"hello","notification_content ":"hello","doings":1}
//		// 官网key pushbean-value {\"actionName\":\"W\",\"userID\":\"3881\",\"type\":\"1\",\"otherID\":\"0\",\"objectID\":\"2884817\"}
//		// [{"pushbean":"{\"actionName\":\"W\",\"userID\":\"3881\",\"type\":\"1\",\"otherID\":\"0\",\"objectID\":\"2884817\"}"}]
//		try {
//			ShowLog.i(TAG, extra);
//			Gson gson = new Gson();
//			List<HauWeiPushBean> pushlist = gson.fromJson(extra, new TypeToken<List<HauWeiPushBean>>() {
//			}.getType());
//			if (pushlist != null && pushlist.size() > 0 && pushlist.get(0) != null) {
//				// {"actionName":"W","userID":"3881","type":"1","otherID":"0","objectID":"2884817"}
//				ShowLog.i(TAG, "pushbean----->" + pushlist.get(0).getPushbean());
//				if (ActivityManager.getActivityManager().getActivityStack() != null && ActivityManager.getActivityManager().getActivityStack().size() > 0) {
//					TaogubaPushReceiver.setStartActivity(context, pushlist.get(0).getPushbean(), true);
//				} else {
//					WelcomeActivity.startWelcomeActivity(context, pushlist.get(0).getPushbean());
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		if (Event.NOTIFICATION_OPENED.equals(event) || Event.NOTIFICATION_CLICK_BTN.equals(event)) {
//			int notifyId = extras.getInt(BOUND_KEY.pushNotifyId, 0);
//			if (0 != notifyId) {
//				NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//				manager.cancel(notifyId);
//			}
//			// showPushMessage(PustDemoActivity.RECEIVE_NOTIFY_CLICK_MSG,
//			// content);
//		} else if (Event.PLUGINRSP.equals(event)) {
//			final int TYPE_LBS = 1;
//			final int TYPE_TAG = 2;
//			int reportType = extras.getInt(BOUND_KEY.PLUGINREPORTTYPE, -1);
//			boolean isSuccess = extras.getBoolean(BOUND_KEY.PLUGINREPORTRESULT, false);
//			String message = "";
//			if (TYPE_LBS == reportType) {
//				message = "LBS report result :";
//			} else if (TYPE_TAG == reportType) {
//				message = "TAG report result :";
//			}
//			ShowLog.i(TAG, message + isSuccess);
//			// showPushMessage(PustDemoActivity.RECEIVE_TAG_LBS_MSG, message +
//			// isSuccess);
//		}
//
//		super.onEvent(context, event, extras);
//	}
//}

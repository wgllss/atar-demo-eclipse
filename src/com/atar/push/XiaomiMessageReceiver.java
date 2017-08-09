//package com.atar.push;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//import android.activity.ActivityManager;
//import android.annotation.SuppressLint;
//import android.content.Context;
//import android.text.TextUtils;
//import android.utils.ShowLog;
//
//import com.atar.activity.R;
//import com.google.gson.Gson;
//import com.xiaomi.mipush.sdk.ErrorCode;
//import com.xiaomi.mipush.sdk.MiPushClient;
//import com.xiaomi.mipush.sdk.MiPushCommandMessage;
//import com.xiaomi.mipush.sdk.MiPushMessage;
//import com.xiaomi.mipush.sdk.PushMessageReceiver;
//
///**
// * 1、PushMessageReceiver 是个抽象类，该类继承了 BroadcastReceiver。<br/>
// * 2、需要将自定义的 DemoMessageReceiver 注册在 AndroidManifest.xml 文件中：
// * <pre>
// * {@code
// *  <receiver
// *      android:name="com.xiaomi.mipushdemo.DemoMessageReceiver"
// *      android:exported="true">
// *      <intent-filter>
// *          <action android:name="com.xiaomi.mipush.RECEIVE_MESSAGE" />
// *      </intent-filter>
// *      <intent-filter>
// *          <action android:name="com.xiaomi.mipush.MESSAGE_ARRIVED" />
// *      </intent-filter>
// *      <intent-filter>
// *          <action android:name="com.xiaomi.mipush.ERROR" />
// *      </intent-filter>
// *  </receiver>
// *  }</pre>
// * 3、DemoMessageReceiver 的 onReceivePassThroughMessage 方法用来接收服务器向客户端发送的透传消息。<br/>
// * 4、DemoMessageReceiver 的 onNotificationMessageClicked 方法用来接收服务器向客户端发送的通知消息，
// * 这个回调方法会在用户手动点击通知后触发。<br/>
// * 5、DemoMessageReceiver 的 onNotificationMessageArrived 方法用来接收服务器向客户端发送的通知消息，
// * 这个回调方法是在通知消息到达客户端时触发。另外应用在前台时不弹出通知的通知消息到达客户端也会触发这个回调函数。<br/>
// * 6、DemoMessageReceiver 的 onCommandResult 方法用来接收客户端向服务器发送命令后的响应结果。<br/>
// * 7、DemoMessageReceiver 的 onReceiveRegisterResult 方法用来接收客户端向服务器发送注册命令后的响应结果。<br/>
// * 8、以上这些方法运行在非 UI 线程中。
// *
// *
// *为了接收消息，您需要自定义一个继承自PushMessageReceiver类的BroadcastReceiver，实现其中的onReceivePassThroughMessage，onNotificationMessageClicked，onNotificationMessageArrived，onCommandResult和onReceiveRegisterResult方法，
// *然后把该receiver注册到AndroidManifest.xml文件中。
// *onReceivePassThroughMessage用来接收服务器发送的透传消息，
// *onNotificationMessageClicked用来接收服务器发来的通知栏消息（用户点击通知栏时触发），
// *onNotificationMessageArrived用来接收服务器发来的通知栏消息（消息到达客户端时触发，并且可以接收应用在前台时不弹出通知的通知消息），
// *onCommandResult用来接收客户端向服务器发送命令消息后返回的响应，
// *onReceiveRegisterResult用来接受客户端向服务器发送注册命令消息后返回的响应。
// * @author mayixiang
// */
//public class XiaomiMessageReceiver extends PushMessageReceiver {
//	public static final String TAG = XiaomiMessageReceiver.class.getSimpleName();
//	private String mRegId;
//	private String mTopic;
//	private String mAlias;
//	private String mAccount;
//	private String mStartTime;
//	private String mEndTime;
//
//	@Override
//	public void onReceivePassThroughMessage(Context context, MiPushMessage message) {
//		ShowLog.v(TAG, "onReceivePassThroughMessage is called. " + message.toString());
//		String log = context.getString(R.string.recv_passthrough_message, message.getContent());
//		// MainActivity.logList.add(0, getSimpleDate() + " " + log);
//
//		if (!TextUtils.isEmpty(message.getTopic())) {
//			mTopic = message.getTopic();
//		} else if (!TextUtils.isEmpty(message.getAlias())) {
//			mAlias = message.getAlias();
//		}
//		ShowLog.v(TAG, log);
//
//		// Message msg = Message.obtain();
//		// msg.obj = log;
//		// DemoApplication.getHandler().sendMessage(msg);
//	}
//
//	@Override
//	public void onNotificationMessageClicked(Context context, MiPushMessage message) {
//		ShowLog.v(TAG, "onNotificationMessageClicked is called. " + message.toString());
//		String log = context.getString(R.string.click_notification_message, message.getContent());
//		// MainActivity.logList.add(0, getSimpleDate() + " " + log);
//
//		if (!TextUtils.isEmpty(message.getTopic())) {
//			mTopic = message.getTopic();
//		} else if (!TextUtils.isEmpty(message.getAlias())) {
//			mAlias = message.getAlias();
//		}
//		ShowLog.v(TAG, log);
//		// Toast.makeText(context, "onNotificationMessageClicked===="+message.toString(), Toast.LENGTH_SHORT).show();
//		// 处理用户点击通知栏事件
//		// messageId={alm49b72472785419423Rh},passThrough={0},alias={990006449531446114},topic={null},userAccount={null},content={},
//		// description={帅八戒说说内容:loo啦啦啦},title={帅八戒说说内容:loo啦啦啦},isNotified={false},notifyId={0},notifyType={1}, category={null},
//		// extra={{actionName=W, userID=3881, type=1, otherID=0, objectID=2884817}}
//		try {
//			Map<String, String> extraMap = message.getExtra();
//			Gson gson = new Gson();
//			ShowLog.i(TAG, "content---0-->" + message.toString());
//			if (ActivityManager.getActivityManager().getActivityStack() != null && ActivityManager.getActivityManager().getActivityStack().size() > 0) {
//				TaogubaPushReceiver.setStartActivity(context, gson.toJson(extraMap), true);
//			} else {
//				WelcomeActivity.startWelcomeActivity(context, gson.toJson(extraMap));
//			}
//		} catch (Exception e) {
//
//		}
//		// Message msg = Message.obtain();
//		// if (message.isNotified()) {
//		// msg.obj = log;
//		// }
//		// DemoApplication.getHandler().sendMessage(msg);
//	}
//
//	@Override
//	public void onNotificationMessageArrived(Context context, MiPushMessage message) {
//		ShowLog.v(TAG, "onNotificationMessageArrived is called. " + message.toString());
//		String log = context.getString(R.string.arrive_notification_message, message.getContent());
//		// MainActivity.logList.add(0, getSimpleDate() + " " + log);
//
//		if (!TextUtils.isEmpty(message.getTopic())) {
//			mTopic = message.getTopic();
//		} else if (!TextUtils.isEmpty(message.getAlias())) {
//			mAlias = message.getAlias();
//		}
//		ShowLog.v(TAG, log);
//		// Toast.makeText(context, "onNotificationMessageArrived===="+message.toString(), Toast.LENGTH_SHORT).show();
//		// Message msg = Message.obtain();
//		// msg.obj = log;
//		// DemoApplication.getHandler().sendMessage(msg);
//	}
//
//	@Override
//	public void onCommandResult(Context context, MiPushCommandMessage message) {
//		ShowLog.v(TAG, "onCommandResult is called. " + message.toString());
//		String command = message.getCommand();
//		List<String> arguments = message.getCommandArguments();
//		String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
//		String cmdArg2 = ((arguments != null && arguments.size() > 1) ? arguments.get(1) : null);
//		String log;
//		if (MiPushClient.COMMAND_REGISTER.equals(command)) {
//			if (message.getResultCode() == ErrorCode.SUCCESS) {
//				mRegId = cmdArg1;
//				log = context.getString(R.string.register_success);
//			} else {
//				log = context.getString(R.string.register_fail);
//			}
//		} else if (MiPushClient.COMMAND_SET_ALIAS.equals(command)) {
//			if (message.getResultCode() == ErrorCode.SUCCESS) {
//				mAlias = cmdArg1;
//				log = context.getString(R.string.set_alias_success, mAlias);
//			} else {
//				log = context.getString(R.string.set_alias_fail, message.getReason());
//			}
//		} else if (MiPushClient.COMMAND_UNSET_ALIAS.equals(command)) {
//			if (message.getResultCode() == ErrorCode.SUCCESS) {
//				mAlias = cmdArg1;
//				log = context.getString(R.string.unset_alias_success, mAlias);
//			} else {
//				log = context.getString(R.string.unset_alias_fail, message.getReason());
//			}
//		} else if (MiPushClient.COMMAND_SET_ACCOUNT.equals(command)) {
//			if (message.getResultCode() == ErrorCode.SUCCESS) {
//				mAccount = cmdArg1;
//				log = context.getString(R.string.set_account_success, mAccount);
//			} else {
//				log = context.getString(R.string.set_account_fail, message.getReason());
//			}
//		} else if (MiPushClient.COMMAND_UNSET_ACCOUNT.equals(command)) {
//			if (message.getResultCode() == ErrorCode.SUCCESS) {
//				mAccount = cmdArg1;
//				log = context.getString(R.string.unset_account_success, mAccount);
//			} else {
//				log = context.getString(R.string.unset_account_fail, message.getReason());
//			}
//		} else if (MiPushClient.COMMAND_SUBSCRIBE_TOPIC.equals(command)) {
//			if (message.getResultCode() == ErrorCode.SUCCESS) {
//				mTopic = cmdArg1;
//				log = context.getString(R.string.subscribe_topic_success, mTopic);
//			} else {
//				log = context.getString(R.string.subscribe_topic_fail, message.getReason());
//			}
//		} else if (MiPushClient.COMMAND_UNSUBSCRIBE_TOPIC.equals(command)) {
//			if (message.getResultCode() == ErrorCode.SUCCESS) {
//				mTopic = cmdArg1;
//				log = context.getString(R.string.unsubscribe_topic_success, mTopic);
//			} else {
//				log = context.getString(R.string.unsubscribe_topic_fail, message.getReason());
//			}
//		} else if (MiPushClient.COMMAND_SET_ACCEPT_TIME.equals(command)) {
//			if (message.getResultCode() == ErrorCode.SUCCESS) {
//				mStartTime = cmdArg1;
//				mEndTime = cmdArg2;
//				log = context.getString(R.string.set_accept_time_success, mStartTime, mEndTime);
//			} else {
//				log = context.getString(R.string.set_accept_time_fail, message.getReason());
//			}
//		} else {
//			log = message.getReason();
//		}
//		ShowLog.v(TAG, log);
//		// MainActivity.logList.add(0, getSimpleDate() + "    " + log);
//		// Message msg = Message.obtain();
//		// msg.obj = log;
//		// DemoApplication.getHandler().sendMessage(msg);
//	}
//
//	@Override
//	public void onReceiveRegisterResult(Context context, MiPushCommandMessage message) {
//		ShowLog.v(TAG, "onReceiveRegisterResult is called. " + message.toString());
//		String command = message.getCommand();
//		List<String> arguments = message.getCommandArguments();
//		String cmdArg1 = ((arguments != null && arguments.size() > 0) ? arguments.get(0) : null);
//		String log;
//		if (MiPushClient.COMMAND_REGISTER.equals(command)) {
//			if (message.getResultCode() == ErrorCode.SUCCESS) {
//				mRegId = cmdArg1;
//				log = context.getString(R.string.register_success);
//			} else {
//				log = context.getString(R.string.register_fail);
//			}
//		} else {
//			log = message.getReason();
//		}
//
//		ShowLog.v(TAG, log);
//		// Message msg = Message.obtain();
//		// msg.obj = log;
//		// DemoApplication.getHandler().sendMessage(msg);
//	}
//
//	@SuppressLint("SimpleDateFormat")
//	private static String getSimpleDate() {
//		return new SimpleDateFormat("MM-dd hh:mm:ss").format(new Date());
//	}
//
//}

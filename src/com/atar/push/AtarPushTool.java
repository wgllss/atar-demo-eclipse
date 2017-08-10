package com.atar.push;
///**
// * 
// */
//package com.atar.push;
//
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.Map;
//import java.util.Set;
//import java.util.UUID;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import android.annotation.SuppressLint;
//import android.appconfig.AppConfigModel;
//import android.appconfig.AppConfigSetting;
//import android.content.Context;
//import android.interfaces.HandleMessageListener;
//import android.os.Build;
//import android.os.Message;
//import android.telephony.TelephonyManager;
//import android.util.Log;
//import android.utils.ApplicationManagement;
//import android.utils.ShowLog;
//
//import com.huawei.android.pushagent.api.PushManager;
//import com.xiaomi.mipush.sdk.MiPushClient;
//
///**
// *****************************************************************************************************************************************************************************
// * 极光推送工具
// * @author :Atar
// * @createTime:2014-8-7下午6:03:13
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//@SuppressLint("HandlerLeak")
//public class TaogubaPushTool implements HandleMessageListener {
//	// private Handler netWorkHandler = NetWorkHandler.newInstance(this);
//	private static String TAG = TaogubaPushTool.class.getSimpleName();
//
//	private static TaogubaPushTool mTaogubaPushTool;
//	/** 是否绑定极光服务器 */
//	private boolean isBindJpushSuccessed;
//	/** 是否绑定淘股吧服务器 */
//	private boolean isBindTaogubaServer;
//	/** 单个线程池开启向极光发送 */
//	private ExecutorService exec = Executors.newSingleThreadExecutor();
//	private PushToJGRunnable mPushToJGRunnable = new PushToJGRunnable();
//	private String VersionName = "";
//
//	/**小米推送 app信息**/
//	// user your appid the key.
//	private static final String APP_ID = "2882303761517144714";
//	// user your appid the key.
//	private static final String APP_KEY = "5931714421714";
//
//	// 此TAG在adb logcat中检索自己所需要的信息， 只需在命令行终端输入 adb logcat | grep
//
//	public TaogubaPushTool() {
//		super();
//	}
//
//	public synchronized static TaogubaPushTool getInstance() {
//		if (mTaogubaPushTool == null) {
//			mTaogubaPushTool = new TaogubaPushTool();
//			mTaogubaPushTool.VersionName = AppConfigModel.getInstance().getString(AppConfigModel.VERSION_KEY, "5.27");
//		}
//		return mTaogubaPushTool;
//	}
//
//	/**
//	 * 重置绑定状态
//	 * @author :Atar
//	 * @createTime:2015-12-30下午5:35:31
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	public void reSetPushStatus() {
//		isBindJpushSuccessed = false;
//		isBindTaogubaServer = false;
//	}
//
//	/**
//	 * 根据MANUFACTURER初始化 不同的推送 Xiaomi 小米推送  default 极光推送
//	 * @author :Atar
//	 * @createTime:2014-8-8下午3:06:48
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	public void initPush(Context context) {
//		try {
//			if (android.os.Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
//				if (ApplicationManagement.shouldInit()) {
//					MiPushClient.registerPush(context, APP_ID, APP_KEY);
//				}
//				JPushInterface.stopPush(AtarApplication.getApplication());
//				PushManager.enableReceiveNormalMsg(AtarApplication.getApplication(), false);
//				PushManager.enableReceiveNotifyMsg(AtarApplication.getApplication(), false);
//			} else if (android.os.Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
//				// 获取客户端AccessToken,获取之前请先确定该应用（包名）已经在开发者联盟上创建成功，并申请、审核通过Push权益
//				// 该测试应用已经注册过
//				PushManager.requestToken(context);
//				PushManager.enableReceiveNormalMsg(AtarApplication.getApplication(), true);
//				PushManager.enableReceiveNotifyMsg(AtarApplication.getApplication(), true);
//				Log.i(TAG, "try to get Token ,current packageName is " + context.getPackageName());
//				MiPushClient.pausePush(AtarApplication.getApplication(), null);
//				JPushInterface.stopPush(AtarApplication.getApplication());
//			} else {
//				JPushInterface.setDebugMode(true); // 极光设置开启日志,发布时请关闭日志
//				JPushInterface.init(context); // 初始化 JPush
//				JPushInterface.setLatestNotificationNumber(context, 50);
//
//				MiPushClient.pausePush(AtarApplication.getApplication(), null);
//				PushManager.enableReceiveNormalMsg(AtarApplication.getApplication(), false);
//				PushManager.enableReceiveNotifyMsg(AtarApplication.getApplication(), false);
//			}
//		} catch (Exception e) {
//			ShowLog.i(TAG, "initPush()--->" + e.getMessage());
//		}
//	}
//
//	/**
//	 * 暂停推送
//	 * @author :Atar
//	 * @createTime:2014-12-4下午2:40:37
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	public void onPause(Context context) {
//		if (android.os.Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
//			MiPushClient.pausePush(context, null);
//		} else if (android.os.Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
//			PushManager.enableReceiveNormalMsg(context, false);
//			PushManager.enableReceiveNotifyMsg(context, false);
//		} else {
//			JPushInterface.onPause(context);
//		}
//	}
//
//	/**
//	 * 停止极光推送
//	 * @author :Atar
//	 * @createTime:2014-8-8下午3:07:04
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	public void stopPush() {
//		if (android.os.Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
//			MiPushClient.pausePush(AtarApplication.getApplication(), null);
//		} else if (android.os.Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
//			PushManager.enableReceiveNormalMsg(AtarApplication.getApplication(), false);
//			PushManager.enableReceiveNotifyMsg(AtarApplication.getApplication(), false);
//		} else {
//			JPushInterface.stopPush(AtarApplication.getApplication());
//		}
//		AppConfigSetting.getInstance().setIsBindOpen(false);// 状态关闭
//		ShowLog.i(TAG, "stopPush");
//	}
//
//	/**
//	 * 恢复极光推送
//	 * @author :Atar
//	 * @createTime:2014-8-8下午3:07:52
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description: 绑定推送极光推送系统 只针对登陆没有登陆，不针对用户，因为手机只针对别名 ，推送内容针对登陆用户
//	 */
//	public void resumePush() {
//		if (android.os.Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
//			MiPushClient.resumePush(AtarApplication.getApplication(), null);
//		} else if (android.os.Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
//			PushManager.enableReceiveNormalMsg(AtarApplication.getApplication(), true);
//			PushManager.enableReceiveNotifyMsg(AtarApplication.getApplication(), true);
//		} else {
//			JPushInterface.resumePush(AtarApplication.getApplication());
//		}
//		AppConfigSetting.getInstance().setIsBindOpen(true);// 状态打开
//		ShowLog.i(TAG, "恢复极光推送成功");
//	}
//
//	/**
//	 * 得到IMIE码作为alias
//	 * @author :Atar
//	 * @createTime:2014-8-8下午3:21:55
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @return
//	 * @description:
//	 */
//	public String getAlias() {
//		String alias = "";
//		try {
//			alias = getOnlyAlias() + AppConfigSetting.getInstance().getUserId();
//		} catch (Exception e) {
//			if (e != null) {
//				ShowLog.i(TAG, e.getMessage());
//			}
//		}
//		return alias;
//	}
//
//	/**
//	 * 只有设备ID
//	 * @author :Atar
//	 * @createTime:2015-10-29下午5:57:29
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @return
//	 * @description:
//	 */
//	public String getOnlyAlias() {
//		String alias = "";
//		try {
//			TelephonyManager telephonyManager = (TelephonyManager) AtarApplication.getApplication().getSystemService(Context.TELEPHONY_SERVICE);
//			alias = telephonyManager.getDeviceId();
//		} catch (Exception e) {
//			if (e != null) {
//				ShowLog.i(TAG, e.getMessage());
//			}
//		}
//		alias = (alias == null || alias.length() == 0) ? getUniquePsuedoID() : alias;
//		return alias;
//	}
//
//	// 获得独一无二的Psuedo ID
//	public static String getUniquePsuedoID() {
//		String serial = null;
//		String m_szDevIDShort = "35" + Build.BOARD.length() % 10 + Build.BRAND.length() % 10 + Build.CPU_ABI.length() % 10 + Build.DEVICE.length() % 10 + Build.DISPLAY.length() % 10
//				+ Build.HOST.length() % 10 + Build.ID.length() % 10 + Build.MANUFACTURER.length() % 10 + Build.MODEL.length() % 10 + Build.PRODUCT.length() % 10 + Build.TAGS.length() % 10
//				+ Build.TYPE.length() % 10 + Build.USER.length() % 10; // 13 位
//		try {
//			serial = android.os.Build.class.getField("SERIAL").get(null).toString();
//			// API>=9 使用serial号
//			return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
//		} catch (Exception exception) {
//			// serial需要一个初始化
//			serial = "serial"; // 随便一个初始化
//		}
//		// 使用硬件信息拼凑出来的15位号码
//		return new UUID(m_szDevIDShort.hashCode(), serial.hashCode()).toString();
//	}
//
//	/**
//	 * 设置推送时间段
//	 * @author :Atar
//	 * @createTime:2014-12-29上午10:50:31
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param startTime
//	 * @param endTime
//	 * @description:
//	 */
//	public void setTime(String time) {
//		StringBuffer daysSB = new StringBuffer();
//		Set<Integer> days = new HashSet<Integer>();
//		days.add(0);
//		daysSB.append("0,");
//		days.add(1);
//		daysSB.append("1,");
//		days.add(2);
//		daysSB.append("2,");
//		days.add(3);
//		daysSB.append("3,");
//		days.add(4);
//		daysSB.append("4,");
//		days.add(5);
//		daysSB.append("5,");
//		days.add(6);
//		daysSB.append("6,");
//		if (time != null && time.length() > 8) {
//			String strStartTime = time.substring(0, 2);
//			String strEndTime = time.substring(6, 8);
//			if (strStartTime != null && strStartTime.length() > 0) {
//				if (strStartTime.substring(0, 1).contains("0")) {
//					strStartTime = strStartTime.substring(1, 2);
//				}
//			}
//			if (strEndTime != null && strEndTime.length() > 0) {
//				if (strEndTime.substring(0, 1).contains("0")) {
//					strEndTime = strEndTime.substring(1, 2);
//				}
//			}
//			strStartTime = strStartTime.replace(":", "");
//			strEndTime = strEndTime.replace(":", "");
//			int starTime = Integer.valueOf(strStartTime);
//			int endtime = Integer.valueOf(strEndTime);
//
//			if (android.os.Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
//				MiPushClient.setAcceptTime(AtarApplication.getApplication(), starTime, 0, endtime, 0, null);
//			} else if (android.os.Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
//			} else {
//				JPushInterface.setPushTime(AtarApplication.getApplication(), days, starTime, endtime);
//			}
//		}
//
//	}
//
//	/**
//	 * 极光推送是否停止
//	 * @author :Atar
//	 * @createTime:2014-12-31下午4:00:13
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @return
//	 * @description:
//	 */
//	public boolean isStopPush() {
//		if (android.os.Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
//			return false;
//		} else if (android.os.Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
//			return false;
//		} else {
//			return JPushInterface.isPushStopped(AtarApplication.getApplication());
//		}
//
//	}
//
//	/**
//	 * 绑定极光推送
//	 * @author :Atar
//	 * @createTime:2014-12-31下午12:26:20
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	public void bindPush() {
//		if (isBindJpushSuccessed) {
//			return;
//		}
//		if (AppConfigSetting.getInstance().getUserId() != null && AppConfigSetting.getInstance().getUserId().length() > 0) {
//			if (VersionName == null || VersionName.length() == 0) {
//				VersionName = AppConfigModel.getInstance().getString(AppConfigModel.VERSION_KEY, "5.27");
//			}
//			boolean isFirst = AppConfigModel.getInstance().getBoolean(VersionName + TaogubaPushTool.class.getSimpleName(), true);
//			if (isFirst) {
//				AppConfigModel.getInstance().putBoolean(VersionName + TaogubaPushTool.class.getSimpleName(), false);
//				AppConfigModel.getInstance().commit();
//				NetWorkInterfaces.ApiResetAllPushSet(TaogubaPushTool.this);// 每个版本第一次安装
//			}
//			if (!isBindTaogubaServer && !isBindJpushSuccessed) {
//				String token = " ";
//				if (android.os.Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
//					NetWorkInterfaces.BindPush(TaogubaPushTool.this, getAlias(), "xiaomi");
//				} else if (android.os.Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
//					token = AppConfigModel.getInstance().getString(HuaWeiPushReceiver.HUAWEI_TOKEN, "");
//					if (token != null && token.length() > 0) {
//						NetWorkInterfaces.BindPush(TaogubaPushTool.this, token, "huawei");
//					}
//				} else {
//					NetWorkInterfaces.BindPush(TaogubaPushTool.this, getAlias(), "android");
//				}
//			} else if (isBindTaogubaServer && !isBindJpushSuccessed && AppConfigSetting.getInstance().IsBindOpen()) {
//				// 绑定淘股吧 并且推送打开 但绑定极光失败
//				exec.execute(mPushToJGRunnable);
//			}
//		}
//	}
//
//	/**
//	 * 设置别名
//	 * @author :Atar
//	 * @createTime:2014-8-8下午3:08:00
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:用手机IMIE作为唯一号
//	 */
//	public void setAlias() {
//		String alias = getAlias();
//		if (alias != null && alias.length() > 0) {
//			Set<String> tag = new HashSet<String>();
//			tag.add("tag");// 极光此参不能为空
//			if (android.os.Build.MANUFACTURER.equalsIgnoreCase("Xiaomi")) {
//				MiPushClient.setAlias(AtarApplication.getApplication(), alias, null);
//			} else if (android.os.Build.MANUFACTURER.equalsIgnoreCase("HUAWEI")) {
//				Map<String, String> tags = new HashMap<String, String>();
//				tags.put("IMEI_KEY", alias);
//				PushManager.setTags(AtarApplication.getApplication(), tags);
//			} else {
//				JPushInterface.setAliasAndTags(AtarApplication.getApplication(), alias, tag, mAliasCallback);
//			}
//		}
//	}
//
//	/**
//	 * 取消极光推送设置
//	 * @author :Atar
//	 * @createTime:2014-12-31下午4:04:07
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	public void clearPushSetting() {
//		JPushInterface.setAliasAndTags(AtarApplication.getApplication(), "", null, mAliasCallback);
//	}
//
//	private TagAliasCallback mAliasCallback = new TagAliasCallback() {
//
//		@Override
//		public void gotResult(int code, String alias, Set<String> tags) {
//			switch (code) {
//			case 0:
//				// 设置成功 Set tag and alias success
//				if (alias != null && alias.length() > 0) {
//					isBindJpushSuccessed = true;
//					ShowLog.i(TAG, "alias--->" + alias);
//					ShowLog.i(TAG, "设置成功 Set tag and alias success---alias--->" + alias);
//					ShowLog.i(TAG, "极光推送绑定别名到极光服务器成功");
//				} else {
//					isBindJpushSuccessed = false;
//				}
//				resumePush();
//				break;
//			// case 6002:// "Failed to set alias and tags due to timeout
//			// // setAlias();
//			// break;
//			default:
//				ShowLog.i(TAG, "别名设计绑定极光设置不成功---->" + code);
//			}
//		}
//	};
//
//	@Override
//	public void NetWorkHandlMessage(Message msg) {
//		switch (msg.what) {
//		case EnumMsgWhat.EInterface_Bing_Push:
//			if (msg.obj != null) {
//				@SuppressWarnings("rawtypes")
//				TaogubaCommonResult mTaogubaCommonResult = (TaogubaCommonResult) msg.obj;
//				if (mTaogubaCommonResult != null) {
//					isBindTaogubaServer = mTaogubaCommonResult.isStatus();
//					if (mTaogubaCommonResult.isStatus()) {
//						if (!isBindJpushSuccessed) {
//							exec.execute(mPushToJGRunnable);
//						}
//					}
//				}
//			}
//			break;
//		// case EnumMsgWhat.EInterface_Api_Reset_All_Push_Set:// 重置推送
//		// break;
//		default:
//			break;
//		}
//	}
//
//	class PushToJGRunnable implements Runnable {
//		@Override
//		public void run() {
//			while (!isBindJpushSuccessed) {
//				try {
//					Thread.sleep(12000);
//					setAlias();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//}

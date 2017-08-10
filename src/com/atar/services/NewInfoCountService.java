///**
// * 
// */
//package com.atar.services;
//
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
//import android.app.Service;
//import android.appconfig.AppConfigSetting;
//import android.common.ServiceUtil;
//import android.content.Context;
//import android.content.Intent;
//import android.interfaces.HandleMessageListener;
//import android.os.IBinder;
//import android.os.Message;
//
//import com.taoguba.bean.InfoCountJson;
//import com.taoguba.bean.InfoCountBean;
//import com.taoguba.enums.EnumMsgWhat;
//import com.taoguba.globe.GlobeSettings;
//import com.taoguba.net.HttpRequest;
//import com.taoguba.net.NetWorkInterfaces;
//
///**
// *****************************************************************************************************************************************************************************
// * 新信息数量轮循获取
// * @author :Atar
// * @createTime:2014-8-18下午2:28:39
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class NewInfoCountService extends Service implements HandleMessageListener {
//	private static final int sleepWiFiTime = 60000;
//	private static final int sleepGprsTime = 120000;
//	/* 是否接收数量提醒消息 */
//	public static boolean isNoticeCount = true;
//
//	// private Handler handler = NetWorkHandler.newInstance(this);
//	private ExecutorService exec = Executors.newSingleThreadExecutor();
//	private InfoCountRunnable mInfoCountRunnable = new InfoCountRunnable();
//
//	@Override
//	public IBinder onBind(Intent arg0) {
//		return null;
//	}
//
//	@Override
//	public void onCreate() {
//		super.onCreate();
//		exec.execute(mInfoCountRunnable);
//	}
//
//	class InfoCountRunnable implements Runnable {
//		@Override
//		public void run() {
//			while (isNoticeCount) {
//				try {
//					if (HttpRequest.getNetWorkType() > 0) {// 有网络
//						if (HttpRequest.getNetWorkType() == 1) {
//							Thread.sleep(sleepGprsTime);
//						} else if (HttpRequest.getNetWorkType() == 2) {
//							Thread.sleep(sleepWiFiTime);
//						}
//						if (AppConfigSetting.getInstance().isLogin() || AppConfigSetting.getInstance().IsVisitorLogin()) {
//							NetWorkInterfaces.GetNewInfoCount(null, NewInfoCountService.this, "");
//						}
//					} else {
//						Thread.sleep(sleepWiFiTime);
//					}
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//	}
//
//	@Override
//	public void NetWorkHandlMessage(Message msg) {
//		switch (msg.what) {
//		case EnumMsgWhat.EInterface_Get_New_Info_Count:
//			if (msg.obj != null) {
//				InfoCountJson mInfoCountJson = (InfoCountJson) msg.obj;
//				if (mInfoCountJson != null && mInfoCountJson.getDto() != null) {
//					InfoCountBean mNewInfoCountBean = mInfoCountJson.getDto();
//					Intent intent = new Intent(GlobeSettings.newInfoCountAction);
//					intent.putExtra(GlobeSettings.NEW_INFO_COUNT_JSON_KEY, mNewInfoCountBean);
//					sendOrderedBroadcast(intent, null);
//				}
//			}
//			break;
//		default:
//			break;
//		}
//	}
//
//	/**
//	 * 启动本服务
//	 * @author :Atar
//	 * @createTime:2014-8-18下午3:03:21
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param context
//	 * @description:
//	 */
//	public static void startService(Context context) {
//		isNoticeCount = true;
//		if (!ServiceUtil.isServiceExisted(context, NewInfoCountService.class.getName())) {
//			ServiceUtil.startService(context, NewInfoCountService.class);
//		}
//	}
//
//	/**
//	 * 停止本服务
//	 * @author :Atar
//	 * @createTime:2014-8-18下午3:03:40
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param context
//	 * @description:
//	 */
//	public static void stopService(Context context) {
//		isNoticeCount = false;
//		if (ServiceUtil.isServiceExisted(context, NewInfoCountService.class.getName())) {
//			ServiceUtil.stopService(context, NewInfoCountService.class);
//		}
//	}
//}

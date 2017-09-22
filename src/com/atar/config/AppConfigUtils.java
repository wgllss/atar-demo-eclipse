/**
 * 
 */
package com.atar.config;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.activity.ActivityManager;
import android.app.Activity;
import android.appconfig.AppConfigDownloadManager;
import android.appconfig.AppConfigDownloadManager.HttpCallBackResult;
import android.appconfig.AppConfigModel;
import android.application.CrashHandler;
import android.common.CommonHandler;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.interfaces.HandlerListener;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.reflection.ThreadPoolTool;
import android.utils.ApplicationManagement;
import android.utils.ShowLog;
import android.widget.CommonToast;

import com.atar.activitys.weex.WeexUtils;
import com.atar.utils.IntentUtil;
import com.google.gson.Gson;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-5-24上午11:13:53
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AppConfigUtils {
	private static String TAG = AppConfigUtils.class.getSimpleName();

	/**下载配置andriodAppConfig地址key*/
	// public static final String andriod_app_config_home_url = WeexUtils.WEEX_HOST + "andriodAppConfig.txt";

	public static final String andriod_app_config_home_url = "http://192.168.1.10:8080/andriodAppConfig.txt";
	/**保存配置文件json key*/
	public static final String ANDRIOD_APP_CONFIG_HOME_KEY = "ANDRIOD_APP_CONFIG_HOME_KEY";
	/**保存开机引道json key*/
	public static final String APP_LOADING_IMAGES_KEY = "APP_LOADING_IMAGES_KEY";
	/**保存webview 已有离线文件 key*/
	public static final String OFFINE_FILE_PATH_KEY = "OFFINE_FILE_PATH_KEY";
	/**保存andriodAppConfig.txt文件版本 key*/
	public static final String APP_CONFIG_TEXT_VERSION_KEY = "APP_CONFIG_TEXT_VERSION_KEY";

	/**
	 * 获取离线assets下webview 所用文件
	 * @author :Atar
	 * @createTime:2017-6-6上午10:13:20
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public static void getOffineFilePath(final Activity activity) {
		ThreadPoolTool.getInstance().execute(new Runnable() {
			@Override
			public void run() {
				AssetManager am = activity.getAssets();
				try {

					String[] resJs = am.list("js");
					String[] resImg = am.list("img");
					String[] resCss = am.list("css");
					String strOfflineResources = "";
					if (resJs != null && resJs.length > 0) {
						for (int i = 0; i < resJs.length; i++) {
							strOfflineResources += resJs[i];
						}
					}
					if (resImg != null && resImg.length > 0) {
						for (int i = 0; i < resImg.length; i++) {
							strOfflineResources += resImg[i];
						}
					}
					if (resCss != null && resCss.length > 0) {
						for (int i = 0; i < resCss.length; i++) {
							strOfflineResources += resCss[i];
						}
					}
					AppConfigModel.getInstance().putString(OFFINE_FILE_PATH_KEY, strOfflineResources);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 读取服务端文件 如txt中的json
	 * @author :Atar
	 * @createTime:2017-5-24上午11:15:57
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param FileUrl :文件地址
	 * @param saveToSharedPreferencesKey :需要保存到SharedPreferences中的Key
	 * @param handler:回调
	 * @param handlerMsgWhat:回调msg.what
	 * @description:
	 */
	public static void getServerTextJson(final String FileUrl, final String saveToSharedPreferencesKey, final HandlerListener handlerListener, final int resultMsgWhat) {
		AppConfigDownloadManager.getInstance().getServerJson(FileUrl, new HttpCallBackResult() {
			@Override
			public void onResult(String result) {
				if (result != null && saveToSharedPreferencesKey != null && result.length() > 0) {
					Gson gson = new Gson();
					AppConfigJson mAppConfigJson = gson.fromJson(result, AppConfigJson.class);
					if (mAppConfigJson == null) {
						return;
					}
					String versionName = "";
					String localVersion = "";
					if (AppConfigModel.getInstance().getString(AppConfigModel.VERSION_KEY, "") != null && AppConfigModel.getInstance().getString(AppConfigModel.VERSION_KEY, "").length() > 0) {
						localVersion = AppConfigModel.getInstance().getString(AppConfigModel.VERSION_KEY, "");
					} else {
						try {
							localVersion = ApplicationManagement.getVersionName();
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
					}
					boolean isReplace = true;
					// versionName 版本号 和apk 的versionName一样的值
					// isReplace 如果apk新发版本 这个配置也新发配置.txt文件，为true: 老版本要替换该配置.txt, false :老版本不替换该.txt
					versionName = mAppConfigJson.getVersionName();
					AppConfigModel.getInstance().putString(APP_CONFIG_TEXT_VERSION_KEY, versionName, true);

					isReplace = mAppConfigJson.isReplace();
					if (versionName.compareToIgnoreCase(localVersion) > 0) {
						if (isReplace) {
							AppConfigModel.getInstance().putString(saveToSharedPreferencesKey, result, true);
						}
					} else {
						AppConfigModel.getInstance().putString(saveToSharedPreferencesKey, result, true);
					}
					if (mAppConfigJson.getLoading_images() != null && mAppConfigJson.getLoading_images().size() > 0 && handlerListener != null) {
						AppConfigModel.getInstance().putString(APP_LOADING_IMAGES_KEY, gson.toJson(mAppConfigJson.getLoading_images()));
						CommonHandler.getInstatnce().handerMessage(handlerListener, resultMsgWhat, 0, 0, mAppConfigJson.getLoading_images());
					}
					// 处理下载皮肤
					CommonHandler.getInstatnce().handerMessage(handlerListener, resultMsgWhat, 1, 0, mAppConfigJson);
				}
			}
		});
	}

	/**
	 * 
	 * @author :Atar
	 * @createTime:2017-5-24下午3:17:37
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public static void switchActivity(OnClickInfo onClickInfo, Context context) {
		try {
			if (onClickInfo != null) {
				// if (onClickInfo.isNeedLogin() && JoinInTGBActivity.startJoinInTGBActivity(context)) {// 需要登陆
				// return;
				// }

				String className = onClickInfo.getClassName();
				if (className != null && className.contains("com.open.taogubaweex") && VERSION.SDK_INT < VERSION_CODES.ICE_CREAM_SANDWICH) {
					CommonToast.show("Android系统版本太低,请升级系统");
					return;
				}
				if (onClickInfo.getSpecialInfo() != null && onClickInfo.getSpecialInfo().length() > 0) {// 特殊点击事件 跳转 切换tabhost等
					switchMainTab(onClickInfo.getSpecialInfo(), className);
					return;
				}
				if (className == null || className.length() == 0) {
					return;
				}
				Class<?> cls = Class.forName(className);
				Intent intent = new Intent(context, cls);
				if (ActivityManager.getActivityManager().getActivityStack() != null && ActivityManager.getActivityManager().getActivityStack().size() > 0) {
					intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
				}
				intent = getIntentFromOptionJson(context, intent, onClickInfo.getOptionJson(), onClickInfo.getOnEventInfo());
				IntentUtil.startOtherActivity(context, intent);
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	 * 处理特殊跳转事件
	 * @author :Atar
	 * @createTime:2017-5-24下午3:15:07
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param specialInfo
	 * @param subTabClassActivityName
	 * @description:
	 */
	public static void switchMainTab(String specialInfo, String subTabClassActivityName) {
		try {
			if (specialInfo != null && specialInfo.length() > 0) {
				JSONObject specialJson = new JSONObject(specialInfo);
				int tabHostposition = 0;
				int viewPagerItem = -1;
				if (specialJson != null && specialJson.length() > 0) {
					if (specialJson.has("tabHostposition")) {
						tabHostposition = specialJson.getInt("tabHostposition");
					}
					if (specialJson.has("viewPagerItem")) {
						viewPagerItem = specialJson.getInt("viewPagerItem");
					} else {
						viewPagerItem = -1;
					}
				}
				// if (ActivityManager.getActivityManager().getActivity(TaogubaMainTabActivity.class) != null) {
				// ActivityManager.getActivityManager().getActivity(TaogubaMainTabActivity.class).switchMainTab(tabHostposition, subTabClassActivityName, viewPagerItem);
				// }
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	 * 从json中获取参数传入到intent中去 适合app中所有跳转
	 * @author :Atar
	 * @createTime:2017-5-24上午11:25:36
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param intent
	 * @param list
	 * @return
	 * @description:
	 */
	public static Intent getIntentFromOptionJson(Context context, Intent intent, String optionJson, String onEventInfo) {
		try {
			if (optionJson != null && optionJson.length() > 0) {// 解析跳转传入参数
				JSONArray jsonArray = new JSONArray(optionJson);
				if (jsonArray != null && jsonArray.length() > 0) {
					for (int i = 0; i < jsonArray.length(); i++) {
						JSONObject jsonObject = jsonArray.getJSONObject(i);
						if (jsonObject != null) {
							if (jsonObject.has("intentKeyValueClassName")) {
								String intentKeyValueClassName = jsonObject.getString("intentKeyValueClassName");
								String intentKey = jsonObject.getString("intentKey");
								if ("int".equals(intentKeyValueClassName)) {
									int intentKeyValue = jsonObject.getInt("intentKeyValue");
									intent.putExtra(intentKey, intentKeyValue);
								} else if ("String".equals(intentKeyValueClassName)) {
									String intentKeyValue = jsonObject.getString("intentKeyValue");
									if (intentKeyValue != null) {
										if (!"TAB_OPTION_JSON_KEY".equals(intentKey) && !"VIEWPAGER_OPTION_JSON_KEY".equals(intentKey) && intentKeyValue.contains(".js")
												&& intentKeyValue.contains("build/src")) {
											// 传入weex js地址 ，自动原生 拼接可切换环境
											try {
												String[] str = intentKeyValue.split("/");
												if (str != null && str.length > 0) {
													String path = str[0];
													path = WeexUtils.IP.contains("com.cn") ? path + "/" : "";
													intentKeyValue = WeexUtils.WEEX_HOST + path + intentKeyValue;
												}
											} catch (Exception e) {

											}
										} else if (!"TAB_OPTION_JSON_KEY".equals(intentKey) && !"VIEWPAGER_OPTION_JSON_KEY".equals(intentKey) && intentKeyValue.contains(".html")
												&& intentKeyValue.contains("assets/html")) {
											// 传入html地址 ，自动原生 拼接可切换环境
											intentKeyValue = WeexUtils.WEEX_HOST + intentKeyValue;
										}
									}
									intent.putExtra(intentKey, intentKeyValue);
								} else if ("double".equals(intentKeyValueClassName)) {
									double intentKeyValue = jsonObject.getDouble("intentKeyValue");
									intent.putExtra(intentKey, intentKeyValue);
								} else if ("ArrayList<String>".equals(intentKeyValueClassName)) {
									String json = jsonObject.getString("intentKeyValue");
									if (json != null && json.length() > 0) {
										JSONArray jsonArray1 = new JSONArray(json);
										ArrayList<String> list = new ArrayList<String>();
										for (int j = 0; j < jsonArray1.length(); j++) {
											list.add(jsonArray1.getString(j));
										}
										intent.putStringArrayListExtra(intentKey, list);
									}
								} else if ("MsgAndReMsgBean".equals(intentKeyValueClassName)) {
									// String json = jsonObject.getString("intentKeyValue");
									// Gson gson = new Gson();
									// MsgAndReMsgBean mMsgAndReMsgBean = gson.fromJson(json, MsgAndReMsgBean.class);
									// intent.putExtra(intentKey, (Serializable) mMsgAndReMsgBean);
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
		setEventInfo(context, onEventInfo);
		return intent;
	}

	/**
	 * 设置统计事件
	 * @author :Atar
	 * @createTime:2017-7-24下午4:11:49
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param onEventInfo
	 * @description:
	 */
	public static void setEventInfo(Context context, String onEventInfo) {
		try {
			if (onEventInfo != null && onEventInfo.length() > 0) {// 解析点击统计事件
				JSONObject onEventJson = new JSONObject(onEventInfo);
				if (onEventJson != null) {
					String eventType = "";
					String eventID = "";
					String eventName = "";
					if (onEventJson.has("eventType")) {
						eventType = onEventJson.getString("eventType");
					}
					if (onEventJson.has("eventID")) {
						eventID = onEventJson.getString("eventID");
					}
					if (onEventJson.has("eventName")) {
						eventName = onEventJson.getString("eventName");
					}
					if ("0".equals(eventType)) {
						// 百度统计
						// StatService.onEvent(context, eventID, eventName, 1);
					} else if ("1".equals(eventType)) {
						// 友盟统计
						// MobclickAgent.onEvent(context, eventName);
					}
				}
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	 * 得到默认配置数据
	 * @author :Atar
	 * @createTime:2017-5-24下午1:28:35
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	public static String getDefaultSetting() {
		return "{\"versionName\":\"5.35\",\"isReplace\":true,\"tpyTel\":\"95397\",\"fmTel\":\"400-058-0188\",\"topMenu\":{\"showDividerLine\":false,\"shouldExpand\":false,\"menuList\":[{\"ID\":4554,\"menuName\":\"大V观点\",\"menuNameColor\":\"#656565,#999999\",\"menuNameTextSize\":13,\"orientation\":\"0\",\"infoNum\":0,\"onClickInfo\":{\"needLogin\":true,\"className\":\"com.open.taogubaweex.MainActivity\",\"optionJson\":\"[{\\\"intentKey\\\":\\\"URL_KEY\\\",\\\"intentKeyValueClassName\\\":\\\"String\\\",\\\"intentKeyValue\\\":\\\"dav/build/src/viewpoint-pager.js\\\"}]\"}},{\"ID\":1005,\"menuName\":\"淘股宝\",\"menuNameColor\":\"#656565,#999999\",\"menuNameTextSize\":13,\"orientation\":\"0\",\"infoNum\":0,\"onClickInfo\":{\"needLogin\":false,\"className\":\"com.taoguba.app.activity.DealPlanActivity\",\"onEventInfo\":\"{\\\"eventName\\\":\\\"买啥-淘股宝\\\",\\\"eventID\\\":\\\"1002\\\",\\\"eventType\\\":\\\"0\\\"}\"}},{\"ID\":4034,\"menuName\":\"直播\",\"menuNameColor\":\"#656565,#999999\",\"menuNameTextSize\":13,\"orientation\":\"0\",\"infoNum\":0,\"infoNumColor\":\"#00000000\",\"infoNumSize\":10,\"infoNumMarginLeft\":18,\"infoNumMarginTop\":7,\"onClickInfo\":{\"className\":\"com.open.taogubaweex.MainActivity\",\"optionJson\":\"[{\\\"intentKey\\\":\\\"URL_KEY\\\",\\\"intentKeyValueClassName\\\":\\\"String\\\",\\\"intentKeyValue\\\":\\\"storm/build/src/slider-pager.js\\\"}]\",\"onEventInfo\":\"{\\\"eventName\\\":\\\"股市直播\\\",\\\"eventID\\\":\\\"4034\\\",\\\"eventType\\\":\\\"0\\\"}\"}},{\"ID\":1013,\"menuName\":\"开户\",\"menuNameColor\":\"#656565,#999999\",\"menuNameTextSize\":13,\"orientation\":\"0\",\"infoNum\":0,\"onClickInfo\":{\"className\":\"com.taoguba.app.activity.tpy.TpyAccountLoginActivity\",\"onEventInfo\":\"{\\\"eventName\\\":\\\"买啥-开户\\\",\\\"eventID\\\":\\\"1013\\\",\\\"eventType\\\":\\\"0\\\"}\"}},{\"ID\":1007,\"menuName\":\"牛人榜\",\"menuNameColor\":\"#656565,#999999\",\"menuNameTextSize\":13,\"orientation\":\"0\",\"infoNum\":0,\"onClickInfo\":{\"className\":\"com.taoguba.app.activity.TopicOnListActivityNew\",\"onEventInfo\":\"{\\\"eventName\\\":\\\"买啥-牛人榜\\\",\\\"eventID\\\":\\\"1007\\\",\\\"eventType\\\":\\\"0\\\"}\"}},{\"ID\":4554,\"menuName\":\"直呼\",\"menuNameColor\":\"#656565,#999999\",\"menuNameTextSize\":13,\"orientation\":\"0\",\"infoNum\":0,\"onClickInfo\":{\"className\":\"com.taoguba.app.activity.CommunityActivity\",\"specialInfo\":\"{\\\"tabHostposition\\\":2,\\\"viewPagerItem\\\":2}\"}}]},\"centerMenu\":{\"showDividerLine\":true,\"shouldExpand\":true,\"menuList\":[{\"ID\":4554,\"menuName\":\"实盘比赛\",\"menuNameTextSize\":15,\"orientation\":\"0\",\"isShowIcon\":true,\"menuIconWidthAndHeight\":25,\"infoNumMarginLeft\":50,\"infoNum\":0,\"menuIcon\":\"assets://images/shipan_match.png\",\"huo_flag_icon_url\":\"assets://images/huo_flag.png\",\"huo_flag_marginLeft\":50,\"huo_flag_marginTop\":0,\"huo_flag_size\":10,\"onClickInfo\":{\"needLogin\":true,\"className\":\"com.taoguba.app.activity.shipan.TaogubaShiPanTabActivity\"}},{\"ID\":4554,\"menuName\":\"交易练习\",\"menuNameTextSize\":15,\"orientation\":\"0\",\"isShowIcon\":true,\"menuIconWidthAndHeight\":25,\"infoNumMarginLeft\":50,\"infoNum\":0,\"menuIcon\":\"assets://images/kxian.png\",\"onClickInfo\":{\"needLogin\":false,\"className\":\"com.taoguba.app.activity.kgame.KGameHomeActivity\",\"onEventInfo\":\"{\\\"eventName\\\":\\\"买啥-交易练习\\\",\\\"eventID\\\":\\\"1006\\\",\\\"eventType\\\":\\\"0\\\"}\"}}]},\"loading_images\":[\"https://image.taoguba.com.cn/img/2017/06/26/f041b97zltbf.jpg\",\"https://image.taoguba.com.cn/img/2017/06/26/4ewabxzwiusb.jpg\",\"https://image.taoguba.com.cn/img/2017/06/26/a4ps79sq0bfq.jpg\",\"https://image.taoguba.com.cn/img/2017/06/26/hhj5p9epuebf.jpg\"],\"CommunityActivityJson\":{\"TITLE\":\"今日推荐\",\"showDividerLine\":false,\"IndicatorColor\":\"#1191f6,#1191f6\",\"underlineColor\":\"#00000000,#00000000\",\"shouldExpand\":false,\"listFragment\":[{\"ID\":30315527,\"menuName\":\"今日推荐\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false,\"onClickInfo\":{\"onEventInfo\":\"{\\\"eventName\\\":\\\"社区-今日赞\\\",\\\"eventID\\\":\\\"0027\\\",\\\"eventType\\\":\\\"0\\\"}\"}},{\"ID\":30315528,\"menuName\":\"淘股论坛\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false,\"onClickInfo\":{\"onEventInfo\":\"{\\\"eventName\\\":\\\"社区-淘股论坛\\\",\\\"eventID\\\":\\\"0028\\\",\\\"eventType\\\":\\\"0\\\"}\"}},{\"ID\":30315529,\"menuName\":\"直呼吧\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false},{\"ID\":30315530,\"menuName\":\"港股吧\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false,\"onClickInfo\":{\"onEventInfo\":\"{\\\"eventName\\\":\\\"社区-港股吧\\\",\\\"eventID\\\":\\\"0029\\\",\\\"eventType\\\":\\\"0\\\"}\"}},{\"ID\":30315531,\"menuName\":\"新三板\",\"menuNameTextSize\":15,\"orientation\":\"1\",\"isShowIcon\":false,\"onClickInfo\":{\"onEventInfo\":\"{\\\"eventName\\\":\\\"社区-新三板\\\",\\\"eventID\\\":\\\"0030\\\",\\\"eventType\\\":\\\"0\\\"}\"}}]}}";
	}
}

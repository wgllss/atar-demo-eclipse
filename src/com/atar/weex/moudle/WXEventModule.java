package com.atar.weex.moudle;

import java.util.Iterator;

import org.json.JSONObject;

import android.activity.ActivityManager;
import android.appconfig.AppConfigSetting;
import android.application.CrashHandler;
import android.content.Intent;
import android.utils.KGameUtil;
import android.utils.ShowLog;
import android.utils.TimeFormatUtil;

import com.atar.config.AppConfigUtils;
import com.atar.utils.IntentUtil;
import com.atar.weex.MainActivity;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;

@SuppressWarnings("deprecation")
public class WXEventModule extends WXModule {
	private String TAG = WXEventModule.class.getSimpleName();

	//
	// @WXModuleAnno(runOnUIThread = true)
	// public void gotoedit(String msg) {
	// if (mWXSDKInstance.getContext() instanceof Activity) {
	// // Intent intent = new Intent(mWXSDKInstance.getContext(),
	// WeexActivity.class);
	// // intent.putExtra("url","weex/modules/send.js");
	// // mWXSDKInstance.getContext().startActivity(intent);
	// }
	// }
	//
	// @WXModuleAnno(runOnUIThread = true)
	// public void gotoabout() {
	// if (mWXSDKInstance.getContext() instanceof Activity) {
	// // Intent intent = new Intent(mWXSDKInstance.getContext(),
	// AboutActivity.class);
	// // mWXSDKInstance.getContext().startActivity(intent);
	// }
	// }
	//
	// @WXModuleAnno(runOnUIThread = true)
	// public void backtohome(String msg) {
	// if (mWXSDKInstance.getContext() instanceof Activity) {
	// ((Activity) mWXSDKInstance.getContext()).finish();
	// }
	// }
	//
	// @WXModuleAnno(runOnUIThread = true)
	// public void comment(String obj, String content) {
	// if (mWXSDKInstance.getContext() instanceof Activity) {
	//
	// // Intent intent = new Intent(mWXSDKInstance.getContext(),
	// CommentActivity.class);
	// // intent.putExtra("objectid",obj);
	// // intent.putExtra("content",content);
	// // mWXSDKInstance.getContext().startActivity(intent);
	// }
	// }
	//
	// @WXModuleAnno(runOnUIThread = true)
	// public void share(String msg) {
	//
	// if (mWXSDKInstance.getContext() instanceof Activity) {
	// Intent shareIntent = new Intent();
	// shareIntent.setAction(Intent.ACTION_SEND);
	// shareIntent.putExtra(Intent.EXTRA_TEXT, msg);
	// shareIntent.setType("text/plain");
	// shareIntent.putExtra(Intent.EXTRA_SUBJECT, "好友分享");
	// shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	// mWXSDKInstance.getContext().startActivity(Intent.createChooser(shareIntent,
	// "分享到"));
	// }
	// }
	//
	// @WXModuleAnno(runOnUIThread = true)
	// public void sp(String name, String pass) {
	// if (mWXSDKInstance.getContext() instanceof Activity) {
	// SharedPreferences sp =
	// mWXSDKInstance.getContext().getSharedPreferences("userinfo",
	// Context.MODE_WORLD_WRITEABLE);
	// SharedPreferences.Editor edit = sp.edit();
	// edit.putString("username", name);
	// edit.putString("password", pass);
	// edit.commit();
	// // Intent intent = new Intent(mWXSDKInstance.getContext(),
	// HomeActivity.class);
	// // mWXSDKInstance.getContext().startActivity(intent);
	// }
	// }

	// @WXModuleAnno(runOnUIThread = true)
	// public void showsp() {
	//
	// if (mWXSDKInstance.getContext() instanceof Activity) {
	// SharedPreferences sp =
	// mWXSDKInstance.getContext().getSharedPreferences("userinfo",
	// Context.MODE_WORLD_WRITEABLE);
	// String a = sp.getString("username", "default");
	// // Toast.makeText(mWXSDKInstance.getContext(), a, LENGTH_SHORT).show();
	// }
	// }

	// @WXModuleAnno(runOnUIThread = true)
	// public void quit() {
	// if (mWXSDKInstance.getContext() instanceof Activity) {
	// SharedPreferences sp =
	// mWXSDKInstance.getContext().getSharedPreferences("userinfo",
	// Context.MODE_WORLD_WRITEABLE);
	// SharedPreferences.Editor edit = sp.edit();
	// edit.putString("username", "");
	// edit.putString("password", "");
	// edit.commit();
	// // Intent intent = new Intent(mWXSDKInstance.getContext(),
	// LoginActivity.class);
	// // mWXSDKInstance.getContext().startActivity(intent);
	// // ((Activity) mWXSDKInstance.getContext()).finish();
	// }
	// }
	//
	// @WXModuleAnno(runOnUIThread = true)
	// public void uploadimage() {
	// int REQUESTCODE_PICK = 0;
	// Intent intent;
	// if (Build.VERSION.SDK_INT < 19) {
	// ShowLog.d("chenlei api level", "Your api is lower than 19");
	// intent = new Intent(Intent.ACTION_GET_CONTENT);
	// intent.setType("image/*");
	// } else {
	// ShowLog.d("chenlei api level", "Your api is higher than 19");
	// intent = new Intent(Intent.ACTION_PICK,
	// MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
	// }
	// ((Activity) mWXSDKInstance.getContext()).startActivityForResult(intent,
	// REQUESTCODE_PICK);
	// }

	/**
	 * 
	 * @author :Atar
	 * @createTime:2017-3-17下午5:07:15
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param tag
	 * @param message
	 * @description:例 var userID = weexEventModule.getUserID(); var loginFlag =
	 *                weexEventModule.getLoginFlag();
	 *                weexEventModule.ShowLogI(this.TAG,
	 *                'themetype=='+this.themetype+'--userID-->'+userID+'--isLogin-->'+loginFlag)
	 *                ;
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void ShowLogI(String tag, String message) {
		ShowLog.i(tag, message);
	}

	@WXModuleAnno(runOnUIThread = false)
	public String getUserID() {
		return AppConfigSetting.getInstance().getUserId();
	}

	@WXModuleAnno(runOnUIThread = false)
	public String getLoginFlag() {
		return AppConfigSetting.getInstance().isLogin() ? "1" : "0";
	}

	/**
	 * 跳转到原生activity
	 * 
	 * @author :Atar
	 * @createTime:2017-3-17下午5:18:20
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param className
	 * @param optionJson
	 *            要去的activity intent传值必须为String
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void startOtherNativeActivity(String className, String optionJson) {
		try {
			Class<?> cls = Class.forName(className);
			Intent intent = new Intent(mWXSDKInstance.getContext(), cls);
			if (optionJson != null && optionJson.length() > 0) {
				JSONObject jsonObject = new JSONObject(optionJson);
				if (jsonObject != null) {
					Iterator<String> iterator = jsonObject.keys();
					while (iterator.hasNext()) {
						String key = (String) iterator.next();
						String value = jsonObject.getString(key);
						intent.putExtra(key, value);
					}
				}
			}
			// 如跳帖子页面
			// var
			// optionJson='{"TOPIC_ID_KEY":"1415605","TOPIC_REPLY_ID_KEY":"0"}';
			// weexEventModule.startOtherNativeActivity("com.taoguba.app.activity.TaogubaTopicActivity",optionJson);
			IntentUtil.startOtherActivity(mWXSDKInstance.getContext(), intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转到原生 任意activity
	 * 
	 * @author :Atar
	 * @createTime:2017-4-21上午10:44:10
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param className
	 *            要去的activity
	 * @param optionJson
	 *            intentKeyValueClassName为intent支持传值类型
	 *            String、double、ArrayList<String>、MsgAndReMsgBean
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void startOtherNativeActivity2(String className, String optionJson) {
		try {
			Class<?> cls = Class.forName(className);
			Intent intent = new Intent(mWXSDKInstance.getContext(), cls);
			intent = AppConfigUtils.getIntentFromOptionJson(mWXSDKInstance.getContext(), intent, optionJson, null);
			// 如跳帖子页面
			// var
			// optionJson=[{"intentKey":"intnet传值Key","intentKeyValueClassName":"intent传值类型名","intentKeyValue":"intent所传值"},{"intentKey":"intnet传值Key","intentKeyValueClassName":"intent传值类型名","intentKeyValue":"intent所传值"}];
			// weexEventModule.startOtherNativeActivity("com.taoguba.app.activity.TaogubaTopicActivity",optionJson);
			IntentUtil.startOtherActivity(mWXSDKInstance.getContext(), intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 调用原生格式化时间格式 type:1 自定义时间格式不做多少分钟以前处理 type:2 做到多少分钟以前，超过多少以自定义格式显示 type:3
	 * 固定为淘股论坛格式显示 此时 timeFormat可传“”
	 * 
	 * @author :Atar
	 * @createTime:2017-3-23上午9:56:19
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param type
	 * @param strTime
	 * @param timeFormat
	 * @return
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public String TimeFormate(String type, String strTime, String timeFormat) {
		try {
			long time = Long.valueOf(strTime);
			if ("1".equals(type)) {
				return TimeFormatUtil.getStringTimeFromLongCustomFormat(time, timeFormat);
			} else if ("2".equals(type)) {
				return TimeFormatUtil.getStringTimeFromLong(time, timeFormat);
			} else if ("3".equals(type)) {
				return TimeFormatUtil.getTimeFromlong(time);
			}
		} catch (Exception e) {

		}
		return "--";
	}

	/**
	 * 计算每三位加一个逗号，大于1万1亿 汉字单位1位固定小数， 小于1万的自定义格式： accuracy 小于10000的格式
	 * 小数位数（0、1、2位） 和 format 同步一起自定义
	 * 
	 * @author :Atar
	 * @createTime:2016-1-19下午2:54:40
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param data
	 * @param format
	 *            小于10000的格式
	 * @param accuracy
	 *            小于10000的格式 小数位数（0、1、2位） 和 format 同步一起自定义
	 * @return
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public String NumFormate(String strData, String strAccuracy, String format) {
		String strNum = "--";
		try {
			double data = Double.valueOf(strData);
			int accuracy = Integer.valueOf(strAccuracy);
			strNum = KGameUtil.formatShipan(data, accuracy, format);
		} catch (Exception e) {
			strNum = strData;
		}
		return strNum;
	}

	/**
	 * 获取存入值
	 * 
	 * @author :Atar
	 * @createTime:2017-4-12上午10:23:44
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param spKey
	 * @return
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public String getString(String spKey) {
		int currentPosition = AppConfigSetting.getInstance().getInt(spKey, 0);
		return currentPosition + "";
	}

	/**
	 * 存入整型值
	 * 
	 * @author :Atar
	 * @createTime:2017-4-18上午10:34:32
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param spKey
	 * @param value
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void petString(String spKey, String value) {
		try {
			AppConfigSetting.getInstance().putInt(spKey, Integer.valueOf(value));
		} catch (Exception e) {

		}
	}

	@WXModuleAnno(runOnUIThread = false)
	public void putString(String spKey, String value) {
		try {
			AppConfigSetting.getInstance().putString(spKey, value);
		} catch (Exception e) {

		}
	}

	@WXModuleAnno(runOnUIThread = false)
	public String getString2(String spKey) {
		String value = AppConfigSetting.getInstance().getString(spKey, "");
		return value;
	}

	@WXModuleAnno(runOnUIThread = false)
	public void removeSharedPreferencesKey(String spKey) {
		try {
			AppConfigSetting.getInstance().remove(spKey);
		} catch (Exception e) {

		}
	}

	/**
	 * 设置viewpager是否滑动返回控制 0可返回 其它 不能返回
	 * 
	 * @author :Atar
	 * @createTime:2017-4-18上午10:38:00
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param value
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void setOnDrawerBackEnabled(String value) {
		try {
			if (mWXSDKInstance.getContext() instanceof MainActivity) {
				((MainActivity) mWXSDKInstance.getContext()).setOnDrawerBackEnabled("0".equals(value));
			}
		} catch (Exception e) {

		}
	}

	/**
	 * weex页面 一个we调另一个we页面
	 * 
	 * @author :Atar
	 * @createTime:2017-5-11下午2:29:09
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param activityClassName
	 * @param callback_key
	 * @param callback_value
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void callWeekActivity(String activityClassName, String callback_key, String callback_value) {
		try {
			if (activityClassName != null && activityClassName.length() > 0) {
				@SuppressWarnings("unchecked")
				Class<MainActivity> cls = (Class<MainActivity>) Class.forName(activityClassName);
				if (cls != null && ActivityManager.getActivityManager().getActivity(cls) != null) {
					ActivityManager.getActivityManager().getActivity(cls).weekCallback(callback_key, callback_value);
				}
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	 *  weex页面 一个we调另一个we页面
	 * @author :Atar
	 * @createTime:2017-6-28下午1:43:34
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param strlastPosition 为倒数第几个MainActivity or MainActivity的子类WeexNavigatorActivity （可能开了多个）
	 * @param activityClassName
	 * @param callback_key
	 * @param callback_value
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void callWeekActivity(String activityClassName, String callback_key, String callback_value, String strlastPosition) {
		try {
			if (activityClassName != null && activityClassName.length() > 0) {
				@SuppressWarnings("unchecked")
				Class<MainActivity> cls = (Class<MainActivity>) Class.forName(activityClassName);
				int lastPosition = 0;
				try {
					lastPosition = Integer.valueOf(strlastPosition);
				} catch (Exception e) {
				}
				if (cls != null && ActivityManager.getActivityManager().getActivity(cls, lastPosition) != null) {
					ActivityManager.getActivityManager().getActivity(cls, lastPosition).weekCallback(callback_key, callback_value);
				}
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	// /**
	// * weex 查询缓存信息
	// * @param params json格式 url地址；typename 类型
	// * @param callback 回调方法
	// *
	// * * 调用代码片段如下：
	// * if (json.list) {
	// if (json.list && json.list.length > 0) {
	// self.parseJSON(json);
	// var paramsCache = {
	// url:url,
	// typename: "pcmingxinghead",
	// };
	// weexEventModule.saveCache(paramsCache,json,function(ee){
	//
	// });
	// }else{
	// console.log('异常==获取缓存==');
	// //获取缓存
	// var paramsCache = {
	// url:url,
	// typename: "pcmingxinghead"
	// };
	// weexEventModule.queryCache(paramsCache,function(e){
	// console.log('queryCache=='+e);
	// var json = JSON.parse(e);
	// self.parseJSON(json);
	// });
	// }
	// }
	// */
	// @WXModuleAnno(runOnUIThread = true)
	// public void queryCache(final String params, final String callback) {
	// Log.d("WeeXEventModule", "queryCache ========" + params);
	// com.alibaba.fastjson.JSONObject jsonObject = null;
	// try {
	// jsonObject = JSON.parseObject(params);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// String whichPage = jsonObject.getString("whichPage");
	// String columnName = jsonObject.getString("columnName");
	// if(columnName==null){
	// columnName = "";
	// }
	// String columnValue = jsonObject.getString("columnValue");
	// if(columnValue==null){
	// columnValue = "";
	// }
	// try {
	//
	// LoadUtil.QueryDB(new Handler(){
	// /* (non-Javadoc)
	// * @see android.os.Handler#handleMessage(android.os.Message)
	// */
	// @Override
	// public void handleMessage(Message msg) {
	// super.handleMessage(msg);
	// switch (msg.what) {
	// case EnumMsgWhat.LOAD_FROM_SQL_COMPLETE:
	// String result = LoadUtil.loadFromSqlComelete(msg, String.class);
	// WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callback,result);
	// Log.d("WeeXEventModule", "result ========" + result);
	// break;
	// default:
	// break;
	// }
	// }
	// }, whichPage, columnName, columnValue);
	// } catch (Exception e) {
	// }
	// }
	//
	// /**
	// * weex 插入缓存信息
	// * @param params json格式 url地址；typename 类型
	// * @param savejson 存储json数据
	// * @param callback 回调方法
	// *
	// * 调用代码片段如下：
	// * if (json.list) {
	// if (json.list && json.list.length > 0) {
	// self.parseJSON(json);
	// var paramsCache = {
	// url:url,
	// typename: "pcmingxinghead",
	// };
	// weexEventModule.saveCache(paramsCache,json,function(ee){
	//
	// });
	// }else{
	// console.log('异常==获取缓存==');
	// //获取缓存
	// var paramsCache = {
	// url:url,
	// typename: "pcmingxinghead"
	// };
	// weexEventModule.queryCache(paramsCache,function(e){
	// console.log('queryCache=='+e);
	// var json = JSON.parse(e);
	// self.parseJSON(json);
	// });
	// }
	// }
	// */
	// @WXModuleAnno(runOnUIThread = true)
	// public void saveCache(final String params,final String savejson, final String callback) {
	// Log.d("WeeXEventModule", "saveCache ========" + params);
	// com.alibaba.fastjson.JSONObject jsonObject = null;
	// try {
	// jsonObject = JSON.parseObject(params);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// String whichPage = jsonObject.getString("whichPage");
	// String columnName = jsonObject.getString("columnName");
	// if(columnName==null){
	// columnName = "";
	// }
	// String columnValue = jsonObject.getString("columnValue");
	// if(columnValue==null){
	// columnValue = "";
	// }
	// try {
	// //数据入库
	// LoadUtil.saveToDB(savejson, whichPage, columnName, columnValue);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
}

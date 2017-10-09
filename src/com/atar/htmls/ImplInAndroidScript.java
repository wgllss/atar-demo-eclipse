/**
 * 
 */
package com.atar.htmls;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import android.activity.ActivityManager;
import android.annotation.SuppressLint;
import android.appconfig.AppConfigSetting;
import android.application.CrashHandler;
import android.common.CommonHandler;
import android.content.Context;
import android.content.Intent;
import android.enums.SkinMode;
import android.http.HttpUrlConnectionRequest;
import android.interfaces.HandlerListener;
import android.interfaces.NetWorkCallListener;
import android.os.Handler;
import android.os.Message;
import android.reflection.NetWorkMsg;
import android.reflection.ThreadPoolTool;
import android.skin.SkinUtils;
import android.utils.ScreenUtils;
import android.utils.ShowLog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CommonToast;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.atar.activitys.demos.ShowImageActivity;
import com.atar.activitys.htmls.AtarDynamicWebViewActivity;
import com.atar.activitys.htmls.WebViewPagerActivity;
import com.atar.activitys.htmls.WeexTabHostWithWebViewActivity;
import com.atar.adapters.DropDownAdapter;
import com.atar.beans.PopWindowItemBean;
import com.atar.beans.ShareBean;
import com.atar.common.CommonDialog;
import com.atar.common.CommonDialog.EditFinishListener;
import com.atar.common.CommonLoading;
import com.atar.config.AppConfigUtils;
import com.atar.enums.EnumMsgWhat;
import com.atar.fragments.htmls.AtarDynamicFragment;
import com.atar.modles.CommonResult;
import com.atar.net.UrlParamCommon;
import com.atar.utils.IntentUtil;
import com.atar.utils.LoadUtil;
import com.atar.utils.ShareTool;
import com.atar.weex.MainActivity;
import com.atar.widgets.ToastWhthCheck;
import com.atar.widgets.refresh.OnHandlerDataListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.handmark.pulltorefresh.library.PullToRefreshWebView;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-6-2下午1:31:36
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ImplInAndroidScript {

	private String TAG = ImplInAndroidScript.class.getSimpleName();
	// 强制回主线程
	private Handler handler = CommonHandler.getInstatnce().getHandler();

	private AtarCommonActivity activity;

	private String tempID = "";
	private Map<String, String> tempJson = new HashMap<String, String>();

	private OnHandlerDataListener<PullToRefreshWebView, WebView> onHandlerDataListener;

	public ImplInAndroidScript(AtarCommonActivity activity, OnHandlerDataListener<PullToRefreshWebView, WebView> onHandlerDataListener) {
		super();
		this.activity = activity;
		this.onHandlerDataListener = onHandlerDataListener;
	}

	/**
	 * 网络请求
	 * @author :Atar
	 * @createTime:2017-6-2下午2:11:00
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param msgWhat 哪一个请求标志，必须每一个请求标志都不相同
	 * @param whichThred1  同一请求Url 但请求中参数1不相同-------> 对应http返回时handler 中msg.arg2
	 * @param whichThred2  同一请求Url 但请求中参数2不相同---msg1值必须大于0----> 对应http返回时handler 中msg.arg1
	 * @param url 请求地址
	 * @param requestMethod 请求方法
	 * @param paramsJson 请求参数
	 * @param specialHtml 请求返回json是否含有html代码 有传:1 ,  无传:0
	 * @description:
	 */
	@JavascriptInterface
	public void networkRequest(final String msgWhat, final String whichThred1, final String whichThred2, String url, final String requestMethod, String paramsJson, final String specialHtml) {
		if (msgWhat == null || msgWhat.length() == 0) {
			return;
		}
		try {
			String httpRequestMethod = "POST".equals(requestMethod) ? HttpUrlConnectionRequest.POST : HttpUrlConnectionRequest.GET;
			int msgwhat = Integer.valueOf(msgWhat);
			int msgArg1 = 0, msgArg2 = 0;
			if (whichThred1 != null && whichThred1.length() > 0) {
				msgArg1 = Integer.valueOf(whichThred1.trim());
			}
			if (whichThred2 != null && whichThred2.length() > 0) {
				msgArg2 = Integer.valueOf(whichThred2.trim());
			}

			Map<String, String> map = null;
			if (paramsJson != null && paramsJson.length() > 0) {
				Gson gson = new Gson();
				map = gson.fromJson(paramsJson, new TypeToken<HashMap<String, String>>() {
				}.getType());
			} else {
				map = new HashMap<String, String>();
			}
			Object[] params = new Object[] { UrlParamCommon.API_HOST + url, map, UrlParamCommon.UTF_8, activity };
			final int requestMsgwhat = msgwhat;
			final String savrUrl = url + map.hashCode() + msgwhat + msgArg1 + msgArg2;
			ThreadPoolTool.getInstance().setAsyncTask(msgwhat, msgArg1, msgArg2, new NetWorkCallListener() {
				@Override
				public void NetWorkCall(final NetWorkMsg msg) {
					try {
						if (onHandlerDataListener != null) {
							onHandlerDataListener.sendEmptyMessage(EnumMsgWhat.REFRESH_HANDLER3);
							if (requestMsgwhat == msg.what && msg.obj != null) {
								String resultJson = (String) msg.obj;
								if (!"POST".equals(requestMethod)) {// GET才接口数据，post不存入
									LoadUtil.saveToDB(resultJson, savrUrl, "", "");
								}
								callWebViewData(resultJson, msgWhat, whichThred1, whichThred2, specialHtml);
							} else if (!"POST".equals(requestMethod) && EnumMsgWhat.EMobileNetUseless_Msg == msg.what) {// 没有网络
								LoadUtil.QueryDB(new HandlerListener() {
									@Override
									public void onHandlerData(Message msg1) {
										switch (msg1.what) {
										case EnumMsgWhat.LOAD_FROM_SQL_COMPLETE:
											String resultJson = LoadUtil.loadFromSqlComelete(msg1, String.class);
											callWebViewData(resultJson, msgWhat, whichThred1, whichThred2, specialHtml);
											break;
										}
									}
								}, savrUrl, "", "");
							}
						}
					} catch (Exception e) {
						ShowLog.e(TAG, CrashHandler.crashToString(e));
					}
				}
			}, activity, HttpUrlConnectionRequest.class.getName(), httpRequestMethod, params, String.class);
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	 * 返回数据通知webview调回到html
	 * @author :Atar
	 * @createTime:2017-7-28上午10:26:24
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param resultJson
	 * @param strMsgWhat
	 * @param strMsgArg1
	 * @param strMsgArg2
	 * @description:
	 */
	private void callWebViewData(String resultJson, String strMsgWhat, String strMsgArg1, String strMsgArg2, String specialHtml) {
		if (onHandlerDataListener != null) {
			if (resultJson != null && resultJson.length() > 0 && "1".equals(specialHtml)) {
				resultJson = resultJson.replace("loadImg(this,", "window.injs.runOnAndroid(");// 替换图片点击事件
				resultJson = resultJson.replace("placeHolder.png", AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0) == SkinMode.NIGHT_MODE ? "../img/loading_n.png"
						: "../img/loading_d.png");// 预加载占位图片
				tempJson.put(TAG + strMsgWhat + "_" + strMsgArg1 + "_" + strMsgArg2, resultJson);
				resultJson = "";
			}
			onHandlerDataListener.sendMessage(EnumMsgWhat.REFRESH_HANDLER2, "javascript:netWorkCallBack('" + strMsgWhat + "','" + strMsgArg1 + "','" + strMsgArg2 + "','" + resultJson + "')");
		}
	}

	/**
	 * 得到特殊包含html 代码的json
	 * @author :Atar
	 * @createTime:2017-6-2下午2:12:12
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param msgWhat
	 * @param whichThred1
	 * @param whichThred2
	 * @return
	 * @description:
	 */
	@JavascriptInterface
	public String getJson(String msgWhat, String whichThred1, String whichThred2) {
		try {
			String key = TAG + msgWhat + "_" + whichThred1 + "_" + whichThred2;
			if (tempJson.containsKey(key)) {
				String resultJson = tempJson.get(key);
				tempJson.remove(key);
				return resultJson;
			}
		} catch (Exception e) {

		}
		return "";
	}

	// 点击图片
	@JavascriptInterface
	public void runOnAndroid(final String imgUrl) {
		handler.post(new Runnable() {
			@Override
			public void run() {
				if (imgUrl == null || imgUrl.length() == 0 || activity == null) {
					return;
				}
				ArrayList<String> list = new ArrayList<String>();
				list.add(imgUrl);
				ShowImageActivity.startShowImage(activity, list, 0);
			}
		});
	}

	@JavascriptInterface
	public void startOtherNativeActivity(final String className, final String optionJson) {

		if (className == null || className.length() == 0 || activity == null) {
			return;
		}
		if ("".equals(this.tempID)) {
			this.tempID = className;
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					try {
						Class<?> cls = Class.forName(className);
						Intent intent = new Intent(activity, cls);
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
						IntentUtil.startOtherActivity(activity, intent);
						ImplInAndroidScript.this.tempID = "";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 10);
		}
	}

	/**
	 * 跳转到任意activity
	 * @author :Atar
	 * @createTime:2017-6-2下午2:17:10
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param className
	 * @param optionJson
	 * @description:
	 */
	@JavascriptInterface
	public void startOtherNativeActivity2(final String className, final String optionJson) {
		if (className == null || className.length() == 0 || activity == null) {
			return;
		}
		if ("".equals(this.tempID)) {
			this.tempID = className;
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					try {
						Class<?> cls = Class.forName(className);
						Intent intent = new Intent(activity, cls);
						intent = AppConfigUtils.getIntentFromOptionJson(activity, intent, optionJson, null);
						// 如跳帖子页面
						// var
						// optionJson=[{"intentKey":"intnet传值Key","intentKeyValueClassName":"intent传值类型名","intentKeyValue":"intent所传值"},{"intentKey":"intnet传值Key","intentKeyValueClassName":"intent传值类型名","intentKeyValue":"intent所传值"}];
						// weexEventModule.startOtherNativeActivity("com.taoguba.app.activity.TaogubaTopicActivity",optionJson);
						IntentUtil.startOtherActivity(activity, intent);
						ImplInAndroidScript.this.tempID = "";
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, 10);
		}
	}

	@JavascriptInterface
	public void ShowLogI(String tag, String message) {
		ShowLog.i(tag, message);
	}

	@JavascriptInterface
	public String getUserID() {
		return AppConfigSetting.getInstance().getUserId();
	}

	@JavascriptInterface
	public String getLoginFlag() {
		return AppConfigSetting.getInstance().isLogin() ? "1" : "0";
	}

	@JavascriptInterface
	public String isStatus(String errorCode, String status) {
		if (activity != null) {
			if (errorCode != null && errorCode.length() > 0) {
				int code = Integer.valueOf(errorCode);
				@SuppressWarnings("rawtypes")
				CommonResult mTaogubaCommonResult = new CommonResult();
				mTaogubaCommonResult.setErrorCode(code);
				boolean bstatus = Boolean.valueOf(status);
				mTaogubaCommonResult.setStatus(bstatus);
				mTaogubaCommonResult.isStatus(activity);
				return bstatus ? "1" : "0";
			}
		}
		return "0";
	}

	@JavascriptInterface
	public void putString(String spKey, String value) {
		try {
			AppConfigSetting.getInstance().putString(spKey, value);
		} catch (Exception e) {

		}
	}

	@JavascriptInterface
	public String getString(String spKey) {
		String value = AppConfigSetting.getInstance().getString(spKey, "");
		return value;
	}

	@JavascriptInterface
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
	@JavascriptInterface
	public void setOnDrawerBackEnabled(final String value) {
		try {
			if (activity != null) {
				activity.setOnDrawerBackEnabled("0".equals(value));
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 是否缩放字体
	 * @author :Atar
	 * @createTime:2017-6-22下午2:05:17
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param isZoomTextSize
	 * @description:
	 */
	@JavascriptInterface
	public void setZoomTextSize(final String value) {
		try {
			if (onHandlerDataListener != null) {
				onHandlerDataListener.sendMessage(EnumMsgWhat.REFRESH_HANDLER4, "1".equals(value));
				// activity.setZoomTextSize("0".equals(value));
			}
		} catch (Exception e) {

		}
	}

	// /**
	// * 是否需要登陆
	// * @author :Atar
	// * @createTime:2017-5-16上午10:20:23
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @return
	// * @description:
	// */
	// @JavascriptInterface
	// public String getStartJoinInTGBActivityFlag() {
	// if (activity != null) {
	// return JoinInTGBActivity.startJoinInTGBActivity(activity) ? "1" : "0";
	// }
	// return "0";
	// }

	// /**
	// * 替换淘股吧自定义表情 html
	// * @author :Atar
	// * @createTime:2017-6-2下午2:34:11
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @param strHtml
	// * @return
	// * @description:
	// */
	// @JavascriptInterface
	// public String replaceTaogubaFaceHtml(String strHtml) {
	// strHtml = UbbCode.replaceTaogubaFaceHtml(strHtml);
	// strHtml = UbbCode.cleatImgList(strHtml);
	// return strHtml;
	// }

	@JavascriptInterface
	public void toast(final String strContent) {
		if (handler != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					if (activity != null) {
						ToastWhthCheck.show(activity, strContent);
					} else {
						CommonToast.show(strContent);
					}
				}
			});
		}
	}

	@JavascriptInterface
	public void toastLong(final String strContent) {
		if (handler != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					if (activity != null) {
						CommonToast.show(activity, strContent, 2, 4, Gravity.CENTER, 3000, false);
					} else {
						CommonToast.show(strContent);
					}
				}
			});
		}
	}

	@JavascriptInterface
	public void alert(final String strTitle, final String strContent, final String callbackMethod) {
		if (handler != null && activity != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					CommonDialog.alertDialog(activity, strTitle, strContent, new OnClickListener() {
						@Override
						public void onClick(View v) {
							CommonDialog.dialogDismiss();
							if (onHandlerDataListener != null) {
								onHandlerDataListener.sendMessage(EnumMsgWhat.REFRESH_HANDLER2, "javascript:" + callbackMethod + "()");
							}
							// activity.loadWebViewUrl("javascript:" + callbackMethod + "()");
						}
					});
				}
			});
		}
	}

	@JavascriptInterface
	public void confirm(final String strTitle, final String strContent, final String strOk, final String strCancle, final String callbackMethodOk, final String callbackMethodCancle,
			final String callbackMethodOkJson, final String callbackMethodCancleJson) {
		if (handler != null && activity != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					CommonDialog.conformDialog(activity, strTitle, strContent, strOk, strCancle, new OnClickListener() {

						@Override
						public void onClick(View v) {
							CommonDialog.dialogDismiss();
							if (onHandlerDataListener != null) {
								onHandlerDataListener.sendMessage(EnumMsgWhat.REFRESH_HANDLER2, "javascript:" + callbackMethodOk + "('" + callbackMethodOkJson + "')");
							}
							// activity.loadWebViewUrl("javascript:" + callbackMethodOk + "('" + callbackMethodOkJson + "')");
						}
					}, new OnClickListener() {
						@Override
						public void onClick(View v) {
							CommonDialog.dialogDismiss();
							if (onHandlerDataListener != null) {
								onHandlerDataListener.sendMessage(EnumMsgWhat.REFRESH_HANDLER2, "javascript:" + callbackMethodCancle + "('" + callbackMethodCancleJson + "')");
							}
							// activity.loadWebViewUrl("javascript:" + callbackMethodCancle + "('" + callbackMethodCancleJson + "')");
						}
					});
				}
			});
		}
	}

	@JavascriptInterface
	public void conformDialogWithEditText(final String strTitle, final String hinttext, final String callbackMethod, final String maxLength, final String callbackMethodOptionJson) {
		if (handler != null && activity != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					String smaxLength = maxLength;
					if (smaxLength == null || smaxLength.length() == 0) {
						smaxLength = "0";
					}
					CommonDialog.conformDialogWithEditText(activity, strTitle, hinttext, Integer.valueOf(smaxLength), new EditFinishListener() {
						@Override
						public void finish(String strEditContent) {
							CommonDialog.dialogDismiss();
							if (onHandlerDataListener != null) {
								onHandlerDataListener.sendMessage(EnumMsgWhat.REFRESH_HANDLER2, "javascript:" + callbackMethod + "('" + strEditContent + "','" + callbackMethodOptionJson + "')");
							}
							// activity.loadWebViewUrl("javascript:" + callbackMethod + "('" + strEditContent + "','" + callbackMethodOptionJson + "')");
						}
					});
				}
			});
		}
	}

	/**
	 * 通用weex端PopupWindow中显示list
	 * @author :Atar
	 * @createTime:2017-4-11下午4:32:20
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param strGravity 位置 0：自定义 xy起始点 1：右上角 2：左上角 3：上边中间 4：右下角 5：左下角 6：中间 7：下中间
	 * @param strX strGravity不为0时偏移量 为0时x起点
	 * @param strY  strGravity不为0时偏移量 为0时y起点
	 * @param spKey 保存当前位置存入key 传""代表不保存状态
	 * @param strPopwindowWidth 宽度
	 * @param strTextSize 列表字体大小
	 * @param jsonList 列表数据 weex上如： var json = [{"DropDownItemId":"0","DropDownItemName":"按回帖时间"},{"DropDownItemId":"1","DropDownItemName":"按发帖时间"}]
	 * @param callbackId 回调方法
	 * @description:
	 */
	@JavascriptInterface
	public void show(final String strGravity, final String strX, final String strY, final String spKey, final String strPopwindowWidth, final String strTextSize, final String jsonList,
			final String onItemClickcallbackMethod) {
		if (handler != null && activity != null) {
			handler.post(new Runnable() {
				@SuppressWarnings({ "null", "unused" })
				@SuppressLint("InflateParams")
				@Override
				public void run() {
					try {
						int gravity = 0, x = 0, y = 0, popwindowWidth = 0, textSize = 0, currentPosition = -1, drawableResID = 0;// R.drawable.corners_transparent8;
						if (strGravity != null && strGravity.length() > 0) {
							if ("0".equals(strGravity)) {// 自定义 xy起始点
								gravity = Gravity.NO_GRAVITY;
							} else if ("1".equals(strGravity)) {// 右上角
								gravity = Gravity.TOP | Gravity.RIGHT;
								// drawableResID = R.drawable.drop_r;
							} else if ("2".equals(strGravity)) {// 左上角
								gravity = Gravity.TOP | Gravity.LEFT;
							} else if ("3".equals(strGravity)) {// 上边中间
								gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
							} else if ("4".equals(strGravity)) {// 右下角
								gravity = Gravity.BOTTOM | Gravity.RIGHT;
							} else if ("5".equals(strGravity)) {// 左下角
								gravity = Gravity.BOTTOM | Gravity.LEFT;
							} else if ("6".equals(strGravity)) {// 中间
								gravity = Gravity.CENTER;
							} else if ("7".equals(strGravity)) {// 下中间
								gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
							}
						}
						if (spKey != null && spKey.length() > 0) {
							currentPosition = AppConfigSetting.getInstance().getInt(spKey, 0);
						}
						if (strX != null && strX.length() > 0) {
							x = Integer.valueOf(strX);
							x = (int) ScreenUtils.getIntToDip(x);
						}
						if (strY != null && strY.length() > 0) {
							y = Integer.valueOf(strY);
							y = (int) ScreenUtils.getIntToDip(y);
						}
						if (strPopwindowWidth != null && strPopwindowWidth.length() > 0) {
							popwindowWidth = Integer.valueOf(strPopwindowWidth);
							popwindowWidth = (int) ScreenUtils.getIntToDip(popwindowWidth);
						}
						if (strTextSize != null && strTextSize.length() > 0) {
							textSize = Integer.valueOf(strTextSize);
						}
						List<PopWindowItemBean> mList = null;
						if (jsonList != null) {
							Gson gson = new Gson();
							mList = gson.fromJson(jsonList, new TypeToken<List<PopWindowItemBean>>() {
							}.getType());
						}
						LayoutInflater mLayoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
						ViewGroup dropView = null;// (ViewGroup) mLayoutInflater.inflate(R.layout.activity_taoguba_common_title_drop, null, true);
						final DropDownAdapter mDropDownAdapter = new DropDownAdapter(mList);
						mDropDownAdapter.setTxtSize(textSize);
						mDropDownAdapter.setCurrentPostiotn(currentPosition);
						((ListView) dropView).setAdapter(mDropDownAdapter);
						((ListView) dropView).setDividerHeight(0);
						final PopupWindow mPopupWindow = new PopupWindow(dropView, popwindowWidth, ViewGroup.LayoutParams.WRAP_CONTENT, true);
						mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
						if ("1".equals(strGravity)) {// 右上角
							mPopupWindow.setBackgroundDrawable(SkinUtils.getDrawable(activity, R.string.drawable_drop_r));
						}
						mPopupWindow.showAtLocation(activity.getWindow().getDecorView(), gravity, x, y);
						((ListView) dropView).setOnItemClickListener(new OnItemClickListener() {
							@Override
							public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
								if (spKey != null && spKey.length() > 0) {
									AppConfigSetting.getInstance().putInt(spKey, position);
								}
								mPopupWindow.dismiss();
								if (onHandlerDataListener != null) {
									onHandlerDataListener.sendMessage(EnumMsgWhat.REFRESH_HANDLER2, "javascript:" + onItemClickcallbackMethod + "('"
											+ mDropDownAdapter.getItem(position).getDropDownItemId() + "')");
								}
								// activity.loadWebViewUrl("javascript:" + onItemClickcallbackMethod + "('" + mDropDownAdapter.getItem(position).getDropDownItemId() + "')");
							}
						});
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
	{
	"shareContent":"淘股吧是中国知名的移动互联投资社交化平台",
	"shareUrl":"http://m.taoguba.com.cn/downloadApp",
	"shareTitle":"v_5.5.5",
	"ShareImgResID":"",
	"ShareImgUrl":"",
	"SHARE_MEDIA":["QZONE","WEIXIN"]
	}
	*/
	@JavascriptInterface
	public void Share(final String shareJson) {
		if (handler != null && activity != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					try {
						Gson gson = new Gson();
						ShareBean share = gson.fromJson(shareJson, ShareBean.class);
						if (share != null) {
							if (share.getSHARE_MEDIA() != null && share.getSHARE_MEDIA().size() > 0) {
								SHARE_MEDIA[] medias = new SHARE_MEDIA[share.getSHARE_MEDIA().size()];
								for (int i = 0; i < share.getSHARE_MEDIA().size(); i++) {
									String smedia = share.getSHARE_MEDIA().get(i);
									if ("WEIXIN".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.WEIXIN;
									} else if ("WEIXIN_CIRCLE".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.WEIXIN_CIRCLE;
									} else if ("QZONE".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.QZONE;
									} else if ("SINA".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.SINA;
									} else if ("QQ".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.QQ;
									}
								}
								ShareTool.getInstance().setShare(activity, share.getShareTitle(), share.getShareUrl(), share.getShareContent(), share.getShareImgUrl(), medias);
							} else {
								// ShareTool.getInstance().setShare(activity, share.getShareTitle(), share.getShareUrl(), share.getShareContent(), R.drawable.share);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	@JavascriptInterface
	public void showLoading(final String strContent) {
		if (handler != null && activity != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					CommonLoading.showLoading(activity, strContent);
				}
			});
		}
	}

	@JavascriptInterface
	public void dissMissLoading() {
		if (handler != null && activity != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					CommonLoading.dissMissLoading();
				}
			});
		}
	}

	@JavascriptInterface
	public void setRefreshing() {
		if (handler != null && onHandlerDataListener != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					onHandlerDataListener.sendEmptyMessage(EnumMsgWhat.REFRESH_HANDLER7);
					// activity.setRefreshing();
				}
			});
		}
	}

	@JavascriptInterface
	public void onStopRefresh() {
		if (handler != null && onHandlerDataListener != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					onHandlerDataListener.sendEmptyMessage(EnumMsgWhat.REFRESH_HANDLER3);
				}
			});
		}
	}

	@JavascriptInterface
	public void setTopRightImgUrl(final String top_right_img_url) {
		if (top_right_img_url != null && top_right_img_url.length() > 0 && handler != null && onHandlerDataListener != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					onHandlerDataListener.sendMessage(EnumMsgWhat.REFRESH_HANDLER6, top_right_img_url);
					// activity.setTopRightImgUrl(top_right_img_url);
				}
			});
		}
	}

	@JavascriptInterface
	public void onfinish() {
		IntentUtil.finish(activity);
	}

	@JavascriptInterface
	public void onBackPressed() {
		if (activity != null) {
			activity.onBackPressed();
		}
	}

	@JavascriptInterface
	public void loginOut() {
		if (handler != null && onHandlerDataListener != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					onHandlerDataListener.sendEmptyMessage(EnumMsgWhat.REFRESH_HANDLER5);
				}
			});
		}
	}

	@JavascriptInterface
	public void setWeexTabHostWithWebViewNewInfoNum(final String strposition, final String strnum) {
		if (handler != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					try {
						int position = 0;
						int num = 0;
						try {
							if (strposition != null && strposition.length() > 0) {
								position = Integer.valueOf(strposition);
							}
							if (strnum != null && strnum.length() > 0) {
								num = Integer.valueOf(strnum);
							}
						} catch (Exception e) {

						}
						if (ActivityManager.getActivityManager().getActivity(WeexTabHostWithWebViewActivity.class) != null) {
							ActivityManager.getActivityManager().getActivity(WeexTabHostWithWebViewActivity.class).setNewInfoNum(position, num);
						}
					} catch (Exception e) {

					}
				}
			});
		}
	}

	@JavascriptInterface
	public void setWeexTabHostWithWebViewsetCurrentTab(final String strposition) {
		if (handler != null) {
			handler.post(new Runnable() {
				@Override
				public void run() {
					try {
						int position = 0;
						try {
							if (strposition != null && strposition.length() > 0) {
								position = Integer.valueOf(strposition);
							}
						} catch (Exception e) {

						}
						if (ActivityManager.getActivityManager().getActivity(WeexTabHostWithWebViewActivity.class) != null) {
							ActivityManager.getActivityManager().getActivity(WeexTabHostWithWebViewActivity.class).setCurrentTab(position);
						}
					} catch (Exception e) {

					}
				}
			});
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
	@JavascriptInterface
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
	* html页面 调另一个we页面
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
	@JavascriptInterface
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

	/**
	* html页面 调用 另一个 TaogubaDynamicWebViewActivity html中js方法
	* @author :Atar
	* @createTime:2017-6-28下午1:43:34
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param strlastPosition 为倒数第几个TaogubaDynamicWebViewActivity（可能开了多个）
	* @param javascriptUrl
	* @description:
	*/
	@JavascriptInterface
	public void callDynamicWebViewctivity(final String strlastPosition, final String javascriptUrl) {
		try {
			if (handler != null) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						int lastPosition = 0;
						try {
							lastPosition = Integer.valueOf(strlastPosition);
						} catch (Exception e) {
						}
						if (ActivityManager.getActivityManager().getActivity(AtarDynamicWebViewActivity.class, lastPosition) != null) {
							ActivityManager.getActivityManager().getActivity(AtarDynamicWebViewActivity.class, lastPosition).loadWebViewUrl(javascriptUrl);
						}
					}
				});
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	* 设置WeOrWebViewPagerActivity 标题
	* @author :Atar
	* @createTime:2017-6-29下午2:05:19
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param title
	* @description:
	*/
	@JavascriptInterface
	public void setWebViewPagerActivityTitle(final String activityName, final String title) {
		try {
			if (handler != null) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						if (activityName.contains("WebViewPagerActivity")) {
							if (ActivityManager.getActivityManager().getActivity(WebViewPagerActivity.class) != null) {
								ActivityManager.getActivityManager().getActivity(WebViewPagerActivity.class).setActivityTitle(title);
							}
						} else if (activityName.contains("CommunityActivity")) {
							// if (ActivityManager.getActivityManager().getActivity(CommunityActivity.class) != null) {
							// ActivityManager.getActivityManager().getActivity(CommunityActivity.class).setActivityTitle(title);
							// }
						}
					}
				});
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	* 调用 另一个 WebViewPagerActivity 第几个 html中js方法
	* @author :Atar
	* @createTime:2017-6-28下午1:43:34
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param strlastPosition 为倒数第几个WebViewPagerActivity（可能开了多个）
	* @param strCurrntPosition 第几个 html
	* @param javascriptUrl
	* @description:
	*/
	@JavascriptInterface
	public void callWebViewPagerActivityFragment(final String activityName, final String strlastPosition, final String strCurrntPosition, final String javascriptUrl) {
		try {
			if (handler != null) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						int lastPosition = 0;
						int currntPosition = 0;
						try {
							lastPosition = Integer.valueOf(strlastPosition);
							currntPosition = Integer.valueOf(strCurrntPosition);
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
						try {
							if (activityName.contains("WebViewPagerActivity")) {
								((AtarDynamicFragment) ActivityManager.getActivityManager().getActivity(WebViewPagerActivity.class, lastPosition).getFragmentList().get(currntPosition))
										.loadWebViewUrl(javascriptUrl);
							} else if (activityName.contains("CommunityActivity")) {
								// ((AtarDynamicFragment) ActivityManager.getActivityManager().getActivity(CommunityActivity.class, lastPosition).getFragmentList().get(currntPosition))
								// .loadWebViewUrl(javascriptUrl);
							}
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
					}
				});
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	* 设置小红点数字
	* @author :Atar
	* @createTime:2017-7-4上午10:32:53
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param strlastPosition 倒数第几个WebViewPagerActivity
	* @param strCurrntPosition 第几个tab下数字
	* @param strNewNum 数字值
	* @description:
	*/
	@JavascriptInterface
	public void setWebViewPagerActivityNewInfoNum(final String activityName, final String strlastPosition, final String strCurrntPosition, final String strNewNum) {
		try {
			if (handler != null) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						int lastPosition = 0;
						int currntPosition = 0;
						int num = 0;
						try {
							lastPosition = Integer.valueOf(strlastPosition);
							currntPosition = Integer.valueOf(strCurrntPosition);
							num = Integer.valueOf(strNewNum);
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
						try {
							if (activityName.contains("WebViewPagerActivity")) {
								ActivityManager.getActivityManager().getActivity(WebViewPagerActivity.class, lastPosition).setNewInfoNum(currntPosition, num);
							} else if (activityName.contains("CommunityActivity")) {
								// ActivityManager.getActivityManager().getActivity(CommunityActivity.class, lastPosition).setNewInfoNum(currntPosition, num);
							}
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
					}
				});
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	* 设置WebViewPagerActivity切换tab
	* @author :Atar
	* @createTime:2017-7-4上午10:32:17
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param strlastPosition 倒数第几个WebViewPagerActivity
	* @param strCurrntPosition 第几个tab
	* @description:
	*/
	@JavascriptInterface
	public void setWebViewPagerActivityCurrentItem(final String activityName, final String strlastPosition, final String strCurrntPosition) {
		try {
			if (handler != null) {
				handler.post(new Runnable() {
					@Override
					public void run() {
						int lastPosition = 0;
						int currntPosition = 0;
						try {
							lastPosition = Integer.valueOf(strlastPosition);
							currntPosition = Integer.valueOf(strCurrntPosition);
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
						try {
							if (activityName.contains("WebViewPagerActivity")) {
								ActivityManager.getActivityManager().getActivity(WebViewPagerActivity.class, lastPosition).setCurrentItem(currntPosition, true);
							} else if (activityName.contains("CommunityActivity")) {
								// ActivityManager.getActivityManager().getActivity(CommunityActivity.class, lastPosition).setCurrentItem(currntPosition, true);
							}
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
					}
				});
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	// /**
	// * 设置CommunityActivity顶部左边图片
	// * @author :Atar
	// * @createTime:2017-7-31上午10:26:05
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @param activityName
	// * @param strlastPosition
	// * @param strCurrntPosition
	// * @description:
	// */
	// @JavascriptInterface
	// public void setCommunityActivityTopLeftImage(final String imgUrl) {
	// try {
	// if (handler != null) {
	// handler.post(new Runnable() {
	// @Override
	// public void run() {
	// try {
	// ActivityManager.getActivityManager().getActivity(CommunityActivity.class).setTopLeftImage(imgUrl);
	// } catch (Exception e) {
	// ShowLog.e(TAG, CrashHandler.crashToString(e));
	// }
	// }
	// });
	// }
	// } catch (Exception e) {
	// ShowLog.e(TAG, CrashHandler.crashToString(e));
	// }
	// }
	//
	// /**
	// * 设置顶部右边图片显示or 隐藏
	// * @author :Atar
	// * @createTime:2017-7-31上午10:38:46
	// * @version:1.0.0
	// * @modifyTime:
	// * @modifyAuthor:
	// * @param strVisivility 0：VISIBLE， 8 GONE
	// * @description:
	// */
	// @JavascriptInterface
	// public void setCommunityActivitysetRightVisibility(final String strVisivility) {
	// try {
	// if (handler != null) {
	// handler.post(new Runnable() {
	// @Override
	// public void run() {
	// int visivility = 0;
	// try {
	// visivility = Integer.valueOf(strVisivility);
	// } catch (Exception e) {
	// ShowLog.e(TAG, CrashHandler.crashToString(e));
	// }
	// try {
	// ActivityManager.getActivityManager().getActivity(CommunityActivity.class).setRightVisibility(visivility);
	// } catch (Exception e) {
	// ShowLog.e(TAG, CrashHandler.crashToString(e));
	// }
	// }
	// });
	// }
	// } catch (Exception e) {
	// ShowLog.e(TAG, CrashHandler.crashToString(e));
	// }
	// }
}
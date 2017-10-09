/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-21下午3:39:53
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.atar.weex.moudle;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-21下午3:39:53
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */

import android.activity.ActivityManager;
import android.activity.CommonActivity;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.atar.utils.IntentUtil;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.utils.WXLogUtils;

public class WXNavigatorModule extends WXModule {
	public static final String MSG_SUCCESS = "WX_SUCCESS";
	public static final String MSG_FAILED = "WX_FAILED";
	public static final String MSG_PARAM_ERR = "WX_PARAM_ERR";
	public static final String CALLBACK_RESULT = "result";
	public static final String CALLBACK_MESSAGE = "message";
	private static final String INSTANCE_ID = "instanceId";
	private static final String TAG = "Navigator";
	private static final String WEEX = "com.taobao.android.intent.category.WEEX";
	private static final String URL = "url";

	@JSMethod(uiThread = true)
	public void open(JSONObject options, JSCallback success, JSCallback failure) {
		if (options != null) {
			String url = options.getString("url");
			JSCallback callback = success;
			JSONObject result = new JSONObject();
			if (!(TextUtils.isEmpty(url))) {
				Uri rawUri = Uri.parse(url);
				String scheme = rawUri.getScheme();
				if ((TextUtils.isEmpty(scheme)) || ("http".equalsIgnoreCase(scheme)) || ("https".equalsIgnoreCase(scheme)))
					push(options.toJSONString(), success);
				else
					try {
						Intent intent = new Intent("android.intent.action.VIEW", rawUri);
						IntentUtil.startOtherActivity(this.mWXSDKInstance.getContext(), intent);
						// this.mWXSDKInstance.getContext().startActivity(intent);
						result.put("result", "WX_SUCCESS");
					} catch (Throwable e) {
						e.printStackTrace();
						result.put("result", "WX_FAILED");
						result.put("message", "open page failed");
						callback = failure;
					}
			} else {
				result.put("result", "WX_PARAM_ERR");
				result.put("message", "param error");
				callback = failure;
			}

			if (callback != null)
				callback.invoke(result);
		}
	}

	@JSMethod(uiThread = true)
	public void close(JSONObject options, JSCallback success, JSCallback failure) {
		JSONObject result = new JSONObject();
		JSCallback callback = null;
		if (this.mWXSDKInstance.getContext() instanceof Activity) {
			callback = success;
			((Activity) this.mWXSDKInstance.getContext()).finish();
		} else {
			result.put("result", "WX_FAILED");
			result.put("message", "close page failed");
			callback = failure;
		}
		if (callback != null)
			callback.invoke(result);
	}

	@JSMethod(uiThread = true)
	public void push(String param, JSCallback callback) {
		if (!(TextUtils.isEmpty(param))) {
			if ((WXSDKEngine.getActivityNavBarSetter() != null) && (WXSDKEngine.getActivityNavBarSetter().push(param))) {
				callback.invoke("WX_SUCCESS");
				return;
			}

			try {
				JSONObject jsonObject = JSON.parseObject(param);
				String url = jsonObject.getString("url");
				String jsonInitData = jsonObject.getString("jsonInitData");
				String options = jsonObject.getString("options");
				if (!(TextUtils.isEmpty(url))) {
					Uri rawUri = Uri.parse(url);
					String scheme = rawUri.getScheme();
					Uri.Builder builder = rawUri.buildUpon();
					if (TextUtils.isEmpty(scheme))
						builder.scheme("http");

					Intent intent = new Intent("android.intent.action.VIEW", builder.build());
					intent.addCategory("com.taobao.android.intent.category.WEEX");
					intent.putExtra("jsonInitData", jsonInitData);
					intent.putExtra("options", options);
					IntentUtil.startOtherActivity(this.mWXSDKInstance.getContext(), intent);
					if (callback != null) {
						callback.invoke("WX_SUCCESS");
					}

				}
			} catch (Exception e) {
				WXLogUtils.eTag("Navigator", e);
				if (callback != null) {
					callback.invoke("WX_FAILED");
				}

			}
		}
		if (callback != null) {
			callback.invoke("WX_FAILED");
		}
	}

	@JSMethod(uiThread = true)
	public void pop(String param, JSCallback callback) {
		if ((WXSDKEngine.getActivityNavBarSetter() != null) && (WXSDKEngine.getActivityNavBarSetter().pop(param))) {
			callback.invoke("WX_SUCCESS");
			return;
		}

		if (this.mWXSDKInstance.getContext() instanceof CommonActivity) {
			callback.invoke("WX_SUCCESS");
			ActivityManager.getActivityManager().popActivity((CommonActivity) mWXSDKInstance.getContext());
			((CommonActivity) this.mWXSDKInstance.getContext()).finish();
		}
	}

	@JSMethod(uiThread = true)
	public void setNavBarRightItem(String param, JSCallback callback) {
		if ((!(TextUtils.isEmpty(param))) && (WXSDKEngine.getActivityNavBarSetter() != null) && (WXSDKEngine.getActivityNavBarSetter().setNavBarRightItem(param))) {
			callback.invoke("WX_SUCCESS");
			return;
		}

		callback.invoke("WX_FAILED");
	}

	@JSMethod(uiThread = true)
	public void clearNavBarRightItem(String param, JSCallback callback) {
		if ((WXSDKEngine.getActivityNavBarSetter() != null) && (WXSDKEngine.getActivityNavBarSetter().clearNavBarRightItem(param))) {
			callback.invoke("WX_SUCCESS");
			return;
		}

		callback.invoke("WX_FAILED");
	}

	@JSMethod(uiThread = true)
	public void setNavBarLeftItem(String param, JSCallback callback) {
		if ((!(TextUtils.isEmpty(param))) && (WXSDKEngine.getActivityNavBarSetter() != null) && (WXSDKEngine.getActivityNavBarSetter().setNavBarLeftItem(param))) {
			callback.invoke("WX_SUCCESS");
			return;
		}

		callback.invoke("WX_FAILED");
	}

	@JSMethod(uiThread = true)
	public void clearNavBarLeftItem(String param, JSCallback callback) {
		if ((WXSDKEngine.getActivityNavBarSetter() != null) && (WXSDKEngine.getActivityNavBarSetter().clearNavBarLeftItem(param))) {
			callback.invoke("WX_SUCCESS");
			return;
		}

		callback.invoke("WX_FAILED");
	}

	@JSMethod(uiThread = true)
	public void setNavBarMoreItem(String param, JSCallback callback) {
		if ((!(TextUtils.isEmpty(param))) && (WXSDKEngine.getActivityNavBarSetter() != null) && (WXSDKEngine.getActivityNavBarSetter().setNavBarMoreItem(param))) {
			callback.invoke("WX_SUCCESS");
			return;
		}

		callback.invoke("WX_FAILED");
	}

	@JSMethod(uiThread = true)
	public void clearNavBarMoreItem(String param, JSCallback callback) {
		if ((WXSDKEngine.getActivityNavBarSetter() != null) && (WXSDKEngine.getActivityNavBarSetter().clearNavBarMoreItem(param))) {
			callback.invoke("WX_SUCCESS");
			return;
		}

		callback.invoke("WX_FAILED");
	}

	@JSMethod(uiThread = true)
	public void setNavBarTitle(String param, JSCallback callback) {
		if ((!(TextUtils.isEmpty(param))) && (WXSDKEngine.getActivityNavBarSetter() != null) && (WXSDKEngine.getActivityNavBarSetter().setNavBarTitle(param))) {
			callback.invoke("WX_SUCCESS");
			return;
		}

		callback.invoke("WX_FAILED");
	}

	@JSMethod
	public void setNavBarHidden(String param, String callback) {
		String message = "WX_FAILED";
		try {
			JSONObject jsObj = JSON.parseObject(param);
			int visibility = jsObj.getInteger("hidden").intValue();
			boolean success = changeVisibilityOfActionBar(this.mWXSDKInstance.getContext(), visibility);
			if (success)
				message = "WX_SUCCESS";
		} catch (JSONException e) {
			WXLogUtils.e("Navigator", WXLogUtils.getStackTrace(e));
		}
		WXBridgeManager.getInstance().callback(this.mWXSDKInstance.getInstanceId(), callback, message);
	}

	private boolean changeVisibilityOfActionBar(Context context, int visibility) {
		boolean result = false;
		boolean hasAppCompatActivity = false;
		try {
			Class.forName("android.support.v7.app.AppCompatActivity");
			hasAppCompatActivity = true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// if ((hasAppCompatActivity) && (this.mWXSDKInstance.getContext() instanceof AppCompatActivity)) {
		// android.support.v7.app.ActionBar actionbar = ((AppCompatActivity) this.mWXSDKInstance.getContext()).getSupportActionBar();
		// if (actionbar != null)
		// switch (visibility) {
		// case 1:
		// actionbar.hide();
		// result = true;
		// break;
		// case 0:
		// actionbar.show();
		// result = true;
		// }
		//
		// } else if (this.mWXSDKInstance.getContext() instanceof Activity) {
		// android.app.ActionBar actionbar = ((Activity) this.mWXSDKInstance.getContext()).getActionBar();
		// if (actionbar != null)
		// switch (visibility) {
		// case 1:
		// actionbar.hide();
		// result = true;
		// break;
		// case 0:
		// actionbar.show();
		// result = true;
		// }
		//
		// }

		return result;
	}
}
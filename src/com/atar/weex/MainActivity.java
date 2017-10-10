/**
 * 
 */
package com.atar.weex;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.appconfig.AppConfigSetting;
import android.application.CrashHandler;
import android.content.Context;
import android.content.Intent;
import android.enums.SkinMode;
import android.http.HttpRequest;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.skin.SkinUtils;
import android.text.TextUtils;
import android.utils.FileUtils;
import android.utils.ScreenUtils;
import android.utils.ShowLog;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.atar.htmls.DynamicHtmlUtils;
import com.atar.utils.IntentUtil;
import com.atar.weex.adapter.HttpAdapter;
import com.atar.weex.utils.WeexUtils;
import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-10-9下午3:20:05
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MainActivity extends AtarCommonActivity implements IWXRenderListener {

	private String TAG = MainActivity.class.getSimpleName();

	private WXSDKInstance mWXSDKInstance;
	protected Map<String, Object> options = new HashMap<String, Object>();
	public View view;
	private TextView txt_toast;

	protected int getResLayoutID() {
		return R.layout.activity_weex_main;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		WeexUtils.weexInit(this);
		setIsExtendsAtarCommonActivity(false);
		super.onCreate(savedInstanceState);
		setContentView(getResLayoutID());
		imgCommonTopLeft = (ImageView) findViewById(R.id.img_common_top_left);
		imgCommonTopLeft.setVisibility(View.GONE);
		topTitleBarBg = (RelativeLayout) findViewById(R.id.common_top_title_bar);
		txt_toast = (TextView) findViewById(R.id.txt_toast);
		try {
			if (VERSION.SDK_INT >= VERSION_CODES.KITKAT && topTitleBarBg != null) {
				int statusBarHeight = ScreenUtils.getStatusBarHeight(this);
				int layoutHeight = (statusBarHeight > 0 ? statusBarHeight : 36) + (int) ScreenUtils.getIntToDip(45);
				LayoutParams lp = (LayoutParams) topTitleBarBg.getLayoutParams();
				lp.height = layoutHeight;
				lp.gravity = Gravity.TOP | Gravity.LEFT;
				topTitleBarBg.setPadding(0, statusBarHeight, 0, 0);
				if (txt_toast != null) {
					LayoutParams lp2 = (LayoutParams) txt_toast.getLayoutParams();
					lp2.topMargin = layoutHeight;
				}
			}
			initControl();
			initValue();
			bindEvent();
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}

		mWXSDKInstance = new WXSDKInstance(this);
		mWXSDKInstance.registerRenderListener(this);
		/**
		 * WXSample 可以替换成自定义的字符串，针对埋点有效。
		 * template 是.we transform 后的 js文件。
		 * option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
		 * jsonInitData 可以为空。
		 * width 为-1 默认全屏，可以自己定制。
		 * height =-1 默认全屏，可以自己定制。
		 */

		options.put("skinType", getCurrentSkinType());
		String url = "";
		String action = getIntent().getAction();
		String weoptions = null;
		if (Intent.ACTION_VIEW.equals(action)) {
			url = getIntent().getDataString();// weex 子入口
			if (getIntent().getExtras() != null) {
				weoptions = getIntent().getExtras().getString("options");
				try {
					if (weoptions != null && weoptions.length() > 0) {
						JSONObject jsonObject;
						jsonObject = new JSONObject(weoptions);
						if (jsonObject != null) {
							Iterator<String> iterator = jsonObject.keys();
							while (iterator.hasNext()) {
								String key = (String) iterator.next();
								String value = jsonObject.getString(key);
								options.put(key, value);
							}
						}
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		} else {
			url = getIntent().getStringExtra(URL_KEY);// weex 主入口
			weoptions = getIntent().getStringExtra("options");
			try {
				if (weoptions != null && weoptions.length() > 0) {
					JSONObject jsonObject;
					jsonObject = new JSONObject(weoptions);
					if (jsonObject != null) {
						Iterator<String> iterator = jsonObject.keys();
						while (iterator.hasNext()) {
							String key = (String) iterator.next();
							String value = jsonObject.getString(key);
							options.put(key, value);
						}
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		if (url == null) {
			return;
		}
		if (ShowLog.mDebug) {// 测试的时候直接加载服务器上的
			if (HttpRequest.IsUsableNetWork(this)) {
				mWXSDKInstance.renderByUrl("MyApplication", url, options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
			} else {
				String originalData = HttpAdapter.getJSCatch(url);
				if (originalData != null && originalData.length() > 0) {
					mWXSDKInstance.render("MyApplication", originalData, options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
				} else {
					onException(mWXSDKInstance, "EMobileNetUseless_Msg", "151451");
				}
			}
		} else {
			String originalData = HttpAdapter.getJSCatch(url);
			if (originalData != null && originalData.length() > 0) {// 先加载缓存，缓存没有再加载线上的
				mWXSDKInstance.render("MyApplication", originalData, options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
				if (!DynamicHtmlUtils.map.containsKey(url)) {
					FileUtils.downLoadFileAndSaveFileToCache(url, ".js", HttpAdapter.SAVE_JS_PATH);
					DynamicHtmlUtils.map.put(url, "1");
				}
			} else {
				if (HttpRequest.IsUsableNetWork(this)) {
					mWXSDKInstance.renderByUrl("MyApplication", url, options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
				} else {
					onException(mWXSDKInstance, "EMobileNetUseless_Msg", "151451");
				}
			}
		}

		/**
		 *  
		* WXSample 可以替换成自定义的字符串，针对埋点有效。
		* template 是.we transform 后的 js文件。
		* option 可以为空，或者通过option传入 js需要的参数。例如bundle js的地址等。
		* jsonInitData 可以为空。
		* width 为-1 默认全屏，可以自己定制。
		* height =-1 默认全屏，可以自己定制。
		 main.we / main.vue 文件，也就是上面代码中的main.js文件中的this.$getConfig()来获取传进来的参数
		module.exports = {
		    data: {
		      aa: '',
		      bb: '',
		      bundleUrl: ''
		    },
		    methods: {
		      // 获取 native的传参
		      getOptions: function() {
		        this.aa = this.$getConfig().aa;
		        this.bb = this.$getConfig().bb; 
		        this.bundleUrl = this.$getConfig().bundleUrl;  
		      }
		    }
		  }
		 */
		// Map<String, Object> options = new HashMap<>();
		// options.put(WXSDKInstance.BUNDLE_URL, url); // 传递bundleUrl
		// options.put("aa", "aaa"); // 传递自定义参数 aa
		// options.put("bb", "ccc"); // 传递自定义参数 bb
		// mWXSDKInstance.render("MyApplication", WXFileUtils.loadAsset("main.js", this), options, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);

		// mWXSDKInstance.renderByUrl("MyApplication","http://192.168.1.15:8080/dist/weexbar/tabbar.js",null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
		// mWXSDKInstance.render("MyApplication", WXFileUtils.loadAsset("index.js", this), null, null, -1, -1, WXRenderStrategy.APPEND_ASYNC);
	}

	@Override
	public void onViewCreated(WXSDKInstance instance, View view) {
		this.view = view;
		LayoutParams lp = new LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
		lp.gravity = Gravity.TOP | Gravity.LEFT;
		if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			int statusBarHeight = ScreenUtils.getStatusBarHeight(this);
			lp.topMargin = statusBarHeight;
		}
		addContentView(view, lp);
	}

	@Override
	public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

	}

	@Override
	public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

	}

	@SuppressLint("InlinedApi")
	@Override
	public void onException(WXSDKInstance instance, String errCode, String msg) {
		if (txt_toast != null) {
			txt_toast.setVisibility(View.VISIBLE);
		}
		topTitleBarBg.setVisibility(View.VISIBLE);
		imgCommonTopLeft.setVisibility(View.VISIBLE);
		if (!HttpRequest.IsUsableNetWork(this) && txt_toast != null) {
			txt_toast.setText(getResources().getString(R.string.emobilenetuseless_msg));
		}

		imgCommonTopLeft.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				IntentUtil.finish(MainActivity.this);
			}
		});
		ShowLog.i(TAG, "onException-->errCode" + errCode + "--msg-->" + msg + "isInitialized-->" + WXSDKEngine.isInitialized());

	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mWXSDKInstance != null) {
			mWXSDKInstance.onActivityResume();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (mWXSDKInstance != null) {
			mWXSDKInstance.onActivityPause();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mWXSDKInstance != null) {
			mWXSDKInstance.onActivityStop();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mWXSDKInstance != null) {
			mWXSDKInstance.onActivityDestroy();
		}
	}

	protected static String URL_KEY = "URL_KEY";
	private static String OPTIONS_KY = "options";

	public static void startActivity(Context context, String url, Class<?> cls) {
		Intent intent = new Intent(context, cls);
		intent.putExtra(URL_KEY, url);
		IntentUtil.startOtherActivity(context, intent);
	}

	public static void push(Context context, String url, String options, Class<?> cls) {
		try {
			if (VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN) {
				if (!(TextUtils.isEmpty(url))) {
					Uri rawUri = Uri.parse(url);
					String scheme = rawUri.getScheme();
					Uri.Builder builder = rawUri.buildUpon();
					if (TextUtils.isEmpty(scheme))
						builder.scheme("http");
					Intent intent = new Intent("android.intent.action.VIEW", builder.build());
					Bundle bundle = new Bundle();
					bundle.putString(OPTIONS_KY, options);
					intent.putExtras(bundle);
					intent.addCategory("com.taobao.android.intent.category.WEEX");
					context.startActivity(intent);
				}
			} else {
				String color = "#1191f6";
				if (AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0) == 0) {
					color = "#1191f6";
				} else {
					color = "#192c46";
				}
				// TaogubaSysWebViewActivity.startTaogubaSysWebViewActivity(context, url, "", color);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * 主动回调方法
	 * @author :Atar
	 * @createTime:2017-5-10上午10:20:35
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param value
	 * @description:
	 */
	public void weekCallback(String callback_key, String callback_value) {
		if (mWXSDKInstance != null) {
			options.clear();
			options.put(callback_key, callback_value);
			mWXSDKInstance.fireGlobalEventCallback("weekCallback", options);
		}
	}

	public void weekCallback(String callback_key, String callback_value, boolean isEventCallback) {
		if (mWXSDKInstance != null) {
			options.put(callback_key, callback_value);
			if (isEventCallback) {
				mWXSDKInstance.fireGlobalEventCallback("weekCallback", options);
			}
		}
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		SkinUtils.setImageDrawable(this, R.string.img_back, skinType, imgCommonTopLeft);
		SkinUtils.setTextColor(this, R.string.common_activity_title_color, skinType, txtCommonTopTitle, txtCommonTopRight);
		SkinUtils.setBackgroundColor(this, R.string.common_top_title_bar_bg_color, skinType, topTitleBarBg);
		SkinUtils.setBackgroundColor(this, R.string.item_white_black_bg, skinType, view);
		if (mWXSDKInstance != null && options != null) {
			options.put("skinType", skinType);
			mWXSDKInstance.fireGlobalEventCallback("skinType_day_night", options);
		}
	}
}

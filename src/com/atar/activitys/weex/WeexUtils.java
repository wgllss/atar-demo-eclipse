/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-8下午2:31:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.atar.activitys.weex;

import android.app.Activity;
import android.appconfig.AppConfigModel;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-3-8下午2:31:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class WeexUtils {
	// /**ip*/
	public static final String IP = AppConfigModel.getInstance().getString("WEEX_IP_KEY", "192.168.1.10:8080");
	// /**网络请求方式http or https*/
	public static final String HTTP = IP.contains("com.cn") ? "https://" : "http://";

	/**打包上线时用 注意 注释掉上面2行 放开下面2行 不用走这么多判断*/
	// public static final String IP = "js.taoguba.com.cn/weex";
	// public static final String HTTP = "https";
	/**weex 服务器地址*/
	public static final String WEEX_HOST = HTTP + IP + "/";

	/**
	 * 初始化weex项目桥接模块
	 * @author :Atar
	 * @createTime:2017-3-14下午3:04:37
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @description:
	 */
	public static void weexInit(Activity activity) {
		try {
			if (VERSION.SDK_INT >= VERSION_CODES.ICE_CREAM_SANDWICH) {
				// InitConfig config = new InitConfig.Builder().setWebSocketAdapterFactory(new DefaultWebSocketAdapterFactory()).setHttpAdapter(new HttpAdapter()).setImgAdapter(new ImageAdapter())
				// .build();
				// WXSDKEngine.initialize(activity.getApplication(), config);
				// WXSDKEngine.registerModule("weexModule", WeexModule.class);
				// WXSDKEngine.registerModule("weexModalUIModule", WeexModalUIModule.class);
				// WXSDKEngine.registerModule("weexEventModule", WXEventModule.class);
				// WXSDKEngine.registerModule("weexNavigatorModule", WXNavigatorModule.class);
				// WXSDKEngine.registerModule("mywebview", WeeXWebViewModule.class);
				// // WXSDKEngine.registerComponent("myinput", MyInput.class);
				// // WXSDKEngine.registerComponent("richtext",RichText.class);
				// WXSDKEngine.registerComponent("web", WeeXWeb.class);
				// WXSDKEngine.registerComponent("myrichtext", RichText.class);
				// WXSDKEngine.registerComponent(new SimpleComponentHolder(WeeXText.class, new WeeXText.Creator()), false, "mystockview");
				// WXSDKEngine.registerDomObject("mystockview", WeeXTextDomObject.class);
				// WXSDKEngine.registerComponent("richview", WeexTextarea.class, false);
				// WXSDKEngine.registerDomObject("richview", TextAreaEditTextDomObject.class);
				// WXEnvironment.sLogLevel = LogLevel.WARN;// 上线关闭日志级别 如本地调试 注释此行
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

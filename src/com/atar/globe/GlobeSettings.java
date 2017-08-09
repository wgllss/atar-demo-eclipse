/**
 * 
 */
package com.atar.globe;

import android.annotation.SuppressLint;
import android.http.HttpRequest;
import android.os.Environment;

/**
 ******************************************************************************************
 * 自定义全局变量，系统配置静态属性
 * @author: Atar 
 * @createTime:2014年7月20日下午5:08:15
 * @modifyTime:
 * @version: 1.0.0
 * @description: 理论上不同的表flag值其实可以相同，本类没这么做
 ******************************************************************************************
 */
@SuppressLint("SdCardPath")
public class GlobeSettings {
	// 每页显示21/7=3行
	public static final int everyPageGridViewCount = 28; // 每页Gridview显示的数量
	public static final int everyPageGridViewNumColumns = 7; // 每页Gridview显示的列数
	/*************************************************************************************************************************************************/

	/* 最小刷新动作时间 */
	public static final int REFRESH_MIN_TIME = 300;
	/* 最大刷新时间 */
	public final static int REFRESH_MAX_TIME = HttpRequest.connectTimeOut + REFRESH_MIN_TIME;

	// 加载头像圆角度数
	public static final int LOAD_IMG_ROUNDE = 100;

	/**K线游戏字体*/
	public static final String kGameFontsPath = "fonts/zzgflht.OTF";
}

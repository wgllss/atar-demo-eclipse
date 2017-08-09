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
	public static final String UM_SHARE = "com.umeng.share";
	// public static final String WeiXin_AppID = "wxa03c2b9e93fd4797";
	// public static final String WeiXin_App_Secret = "e15ab47c47b0a05536248aa5972b65d4";
	public static final String WeiXin_AppID = "wx2c3419dcf80ab8ae";
	public static final String WeiXin_App_Secret = "d2096823ee0d4c754b73be7218728419";
	public static final String UPLOAD_IMAGE_SESSTION_PATH = Environment.getExternalStorageDirectory() + "/TGB/tgb_sesstion/";
	public static final String ANDROID_APP = "?requestType=app";
	/* 当前 DEMO 应用的 APP_KEY，第三方应用应该使用自己的 APP_KEY 替换该 APP_KEY */
	// public static final String LOGIN_SINA_APP_KEY = "3541752941";//
	public static final String LOGIN_SINA_APP_KEY = "730382464";// 2015.7.31新合并
	public static final String WEIBO_APP_SECRET = "63c81db727390acfddba49924c8940dd";
	public static final String QQ_APP_ID = "1103413552";
	public static final String QQ_App_Secret = "SWau9CF0m40fQkNk";
	public static final String REDIRECT_URL = "http://sns.whalecloud.com/sina2/callback";
	/* 通过 code 获取 Token 的 URL */
	public static final String OAUTH2_ACCESS_TOKEN_URL = "https://open.weibo.cn/oauth2/access_token";
	public static final String SCOPE = "email,direct_messages_read,direct_messages_write," + "friendships_groups_read,friendships_groups_write,statuses_to_me_read," + "follow_app_official_microblog,"
			+ "invitation_write";

	/* 接收信息数据广播的action */
	public static final String newInfoCountAction = "com.taoguba.cn.newinfocountaction";
	public static final String NEW_INFO_COUNT_JSON_KEY = "NEW_INFO_COUNT_JSON_KEY";
	public static final String NEW_INFO_ALL_COUNT_KEY = "NEW_INFO_ALL_COUNT_KEY";
	/* 淘股吧本地总文件夹 */
	public static final String appCommonDir = "TGB";
	/* 淘股吧拍照后保存图片 */
	public static final String IMG_CAPTURE_PATH = Environment.getExternalStorageDirectory() + "/" + appCommonDir + "/tgb_photo/";
	/* 淘股吧保存图片 */
	public static final String IMG_SAVE_PATH = Environment.getExternalStorageDirectory() + "/" + appCommonDir + "/tgb_image/";

	/* 最小刷新动作时间 */
	public static final int REFRESH_MIN_TIME = 300;
	/* 最大刷新时间 */
	public final static int REFRESH_MAX_TIME = HttpRequest.connectTimeOut + REFRESH_MIN_TIME;

	/* 本地语音文件目录 */
	public static final String audioDir = appCommonDir + "/audio/";
	/* 发送模式 */
	public static final String CURRENT_SEND_MODE_KEY = "CURRENT_SEND_MODE_KEY";

	public static final int request_code_select_pic = 1007;
	public static final int request_code_record = 1008;
	public static final int request_code_at_user = 1009;
	public static final int request_code_select_pic2 = 1010;
	public static final String UPLOAD_LOACL_FILE_PATH_KEY = "UPLOAD_LOACL_FILE_PATH_KEY";
	public static final String REMAINING_COUNT_KEY = "REMAINING_COUNT_KEY";
	/* 是否显示图片 */
	public static final String SHOW_IMAGE_KEY = "SHOW_IMAGE_KEY";
	/* 行情刷新频率 2G/3G网络 */
	public static final String REFRESH_2G_3G_KEY = "REFRESH_2G_3G_KEY";
	/* 行情刷新频率 wifi网络 */
	public static final String REFRESH_WIFI_KEY = "REFRESH_WIFI_KEY";
	/* 9宫格宽度 */
	public static int columnWidth;
	public static final String REGIST_PHOTO_KEY = "REGIST_PHOTO_KEY";

	// 加载头像圆角度数
	public static final int LOAD_IMG_ROUNDE = 100;

	// /* 默认头像 白天模式 夜间模式 */
	// public static final int[] defaultPortrait = { R.drawable.user_icon_60, R.drawable.user_icon_60_night, R.drawable.user_icon_60_night };
	// /* 默认加载图片图 白天模式 夜间模式 */
	// public static final int[] defaultLoadImg = { R.drawable.defaultloading, R.drawable.defaultloading_n, R.drawable.defaultloading_n };
	// /* 默认加载图片图90X68今日赞列表 白天模式 夜间模式 */
	// public static final int[] defaultLoadImg9068 = { R.drawable.loading9068, R.drawable.loading9068_n };
	// /* 默认加载图片图正方形 白天模式 夜间模式 */
	// public static final int[] defaultLoadImg9090 = { R.drawable.loading9090, R.drawable.loading9090_n };
	// /* 默认加载等待图片 */
	// public static final int[] loadingImg = { R.anim.anim_niu_center_day, R.anim.anim_niu_center_night, R.anim.anim_niu_center_night };
	// /* refresh图片 */
	// public static final int[] refreshImg = { R.anim.anim_refresh_day, R.anim.anim_refresh_day, R.anim.anim_refresh_day };
	// /* 个股加载默认图片 */
	// public static final int fefaultHD[] = { R.drawable.pic_noimg, R.drawable.pic_noimg_night };

	public static final int lastSearchCount = 10;// 最近搜索最大数量

	/** 交易安全设置 **/
	public static final String REFRESH_TPY_TRADING_SAFE = "REFRESH_TPY_TRADING_SAFE";
	/** 判断实盘交易功能是否可用 **/
	public static final String TPY_TRADING_IS_ENABLE = "TPY_TRADING_IS_ENABLE";
	/**K线游戏字体*/
	public static final String kGameFontsPath = "fonts/zzgflht.OTF";
}

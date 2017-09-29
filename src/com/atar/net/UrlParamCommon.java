package com.atar.net;

/**
 * 
 *****************************************************************************************************************************************************************************
 * 所有网络请求地址
 * @author :Atar
 * @createTime:2014-6-18上午9:49:40
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: 包含请求编码
 *****************************************************************************************************************************************************************************
 */
public class UrlParamCommon {

	public static String UTF_8 = "UTF-8";
	public static String GBK = "GBK";

	/** 是否请求中带MD5加密*/
	public static boolean isHasMD5 = true;
	// public static String postfix = AppConfigModel.getInstance().getString("POSTFIX_KEY", "sp/");
	// /** 前缀线上环境是https 本地和测试环境是http 2016年12.15.10:06*/
	// public static String prefix = postfix.contains("com.cn") ? "https" : "http";
	// public static final String UrlPkWebSocket = AppConfigModel.getInstance().getString("WEBSOCKET_KEY", "ws://ws.taoguba.sp/kline");
	// /** 域名后缀 com.cn 打包上线时用 注意 注释掉上面三行 放开下面三行*/

	public static String prefix = "https";
	public static String postfix = "sp/";
	public static final String UrlPkWebSocket = "ws://ws.taoguba.com.cn/kline";
	/**  www服务器地址**/
	public static final String API_HOST = prefix + "://api.taoguba." + postfix;

	/* ****************************************************************图片地址 start ************************************************************************** */
	/** 图片服务器地址**/
	public static final String IMAGE_HOST = prefix + "://image.taoguba.com.cn/";
	public static final String download_skin_url = "https://github.com/wgllss/atar-skin/raw/master/download_skin.apk";
	// public static final String download_skin_url = "http://192.168.1.10:8080/assets/html/download_skin.apk";

	/** 头像图片服务器地址**/
	public static final String TOUXIANG_IMAGEPATH = prefix + "://image.taoguba.com.cn/img/";

	/* 获取头像完整地址 */
	public static String getPortrait(String portrait) {
		if (portrait != null && portrait.length() > 0 && portrait.contains("file://")) {
			/* 若自已修改了头像成功后没有网络 显示本地文件 头像地址不作拼接 */
			return portrait;
		}
		portrait = TOUXIANG_IMAGEPATH + portrait;
		portrait = portrait.replace("/img//", "/img/");
		return portrait;
	}

	// /* 上传文件服务器路径 */
	public static final String UPLOAD_FILE_SERVER = API_HOST + "comp/user/apiUploadFile";
	/* ****************************************************************图片地址 end *************************************************************************** */

	/**  获取新浪用户信息**/
	public static final String UrlGetSinaUserInfo = "https://api.weibo.com/2/users/show.json";
	/** 获取QQ用户信息**/
	public static final String UrlGetQQUserInfo = "https://graph.qq.com/user/get_user_info";
	/** 获取微信token**/
	public static final String UrlGetWeiXinToken = "https://api.weixin.qq.com/sns/oauth2/access_token";
	/** 获取微信用户信息**/
	public static final String UrlGetWeixinUserInfo = "https://api.weixin.qq.com/sns/userinfo?";
	/** 今日赞**/
	public static final String UrlWonderfulList = "";
}

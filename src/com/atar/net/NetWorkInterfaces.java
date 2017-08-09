package com.atar.net;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.http.HttpUrlConnectionRequest;
import android.interfaces.NetWorkCallListener;
import android.reflection.ThreadPoolTool;
import android.utils.CommonStringUtil;

import com.atar.enums.EnumMsgWhat;
import com.atar.modles.TaoguBBSJson;
import com.atar.modles.WonderfulTopicJson;

/**
 * 
 ***************************************************************************************************************************************************************************** 
 * 网络层异步调用接口
 * 
 * @author :Atar
 * @createTime:2014-6-18下午8:55:09
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: 已经实现异步请求
 ***************************************************************************************************************************************************************************** 
 */
public class NetWorkInterfaces {

	/** 获取今日赞 **/
	public static void GetWonderTopicList(Activity activity, NetWorkCallListener handleMessageListener, String pageNo) {
		Map<String, String> map = new HashMap<String, String>();
		CommonStringUtil.setMap(map, "pageNo", pageNo);
		Object[] params = new Object[] { UrlParamCommon.UrlWonderfulList, map, UrlParamCommon.UTF_8, activity };
		ThreadPoolTool.getInstance().setAsyncTask(EnumMsgWhat.EInterface_Get_Wonder_Topic_List, handleMessageListener, activity, HttpUrlConnectionRequest.class.getName(),
				HttpUrlConnectionRequest.GET, params, WonderfulTopicJson.class);
	}

	/** 获取淘股论坛数据 **/
	public static void GetForumList(Activity activity, NetWorkCallListener handleMessageListener, int which, int which2, String actionName, String pageNo, String blockID, String flag) {
		Map<String, String> map = new HashMap<String, String>();
		CommonStringUtil.setMap(map, "pageNo", pageNo);
		CommonStringUtil.setMap(map, "blockID", blockID);
		CommonStringUtil.setMap(map, "flag", flag);
		Object[] params = new Object[] { actionName, map, UrlParamCommon.UTF_8, activity };
		ThreadPoolTool.getInstance().setAsyncTask(EnumMsgWhat.EInterface_Get_Forum_List, which, which2, handleMessageListener, activity, HttpUrlConnectionRequest.class.getName(),
				HttpUrlConnectionRequest.GET, params, TaoguBBSJson.class);
	}

}

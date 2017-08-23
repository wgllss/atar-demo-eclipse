package com.atar.net;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.http.HttpUrlConnectionRequest;
import android.interfaces.NetWorkCallListener;
import android.interfaces.NetWorkCallTListenet;
import android.reflection.NetWorkMsg;
import android.reflection.ThreadPoolTool;
import android.utils.CommonStringUtil;

import com.atar.enums.EnumMsgWhat;
import com.atar.modles.AtarBBSJson;
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

	/**
	 * 适用所有请求方法
	 * @author :Atar
	 * @createTime:2017-8-10上午10:58:01
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param activity
	 * @param mNetWorkCallTListenet
	 * @param url
	 * @param method
	 * @param map
	 * @param typeOfT
	 * @description:
	 */
	public static <T> void NetWorkCall(Activity activity, final NetWorkCallTListenet<T> mNetWorkCallTListenet, String url, String method, Map<String, String> map, Type typeOfT) {
		method = "POST".equals(method) ? HttpUrlConnectionRequest.POST : HttpUrlConnectionRequest.GET;
		Object[] params = null;
		if (activity != null) {
			params = new Object[] { url, map, UrlParamCommon.UTF_8, activity };
		} else {
			params = new Object[] { url, map, UrlParamCommon.UTF_8 };
		}
		ThreadPoolTool.getInstance().setAsyncTask(EnumMsgWhat.HttpDefaultRequest_Msg, new NetWorkCallListener() {
			@Override
			public void NetWorkCall(NetWorkMsg msg) {
				switch (msg.what) {
				case EnumMsgWhat.HttpDefaultRequest_Msg:
					@SuppressWarnings("unchecked")
					T t = (T) msg.obj;
					if (mNetWorkCallTListenet != null) {
						mNetWorkCallTListenet.NetWorkCall(t);
					}
					break;
				default:
					break;
				}
			}
		}, activity, HttpUrlConnectionRequest.class.getName(), method, params, typeOfT);
	}

	/** 获取今日赞 **/
	public static void GetWonderTopicList(Activity activity, NetWorkCallListener mNetWorkCallListener, String pageNo) {
		Map<String, String> map = new HashMap<String, String>();
		CommonStringUtil.setMap(map, "pageNo", pageNo);
		Object[] params = new Object[] { UrlParamCommon.UrlWonderfulList, map, UrlParamCommon.UTF_8, activity };
		ThreadPoolTool.getInstance().setAsyncTask(EnumMsgWhat.EInterface_Get_Wonder_Topic_List, mNetWorkCallListener, activity, HttpUrlConnectionRequest.class.getName(), HttpUrlConnectionRequest.GET,
				params, WonderfulTopicJson.class);
	}

	/** 获取淘股论坛数据 **/
	public static void GetForumList(Activity activity, NetWorkCallListener mNetWorkCallListener, int which, int which2, String actionName, String pageNo, String blockID, String flag) {
		Map<String, String> map = new HashMap<String, String>();
		CommonStringUtil.setMap(map, "pageNo", pageNo);
		CommonStringUtil.setMap(map, "blockID", blockID);
		CommonStringUtil.setMap(map, "flag", flag);
		Object[] params = new Object[] { actionName, map, UrlParamCommon.UTF_8, activity };
		ThreadPoolTool.getInstance().setAsyncTask(EnumMsgWhat.EInterface_Get_Forum_List, which, which2, mNetWorkCallListener, activity, HttpUrlConnectionRequest.class.getName(),
				HttpUrlConnectionRequest.GET, params, AtarBBSJson.class);
	}

}

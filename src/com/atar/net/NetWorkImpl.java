/**
 * 
 */
package com.atar.net;

import android.app.Activity;
import android.reflection.ExceptionEnum.GsonJsonParserException;
import android.reflection.ExceptionEnum.RefelectException;
import android.utils.JsonTestDataManager;

import com.atar.modles.WonderfulTopicJson;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-9-29上午10:50:05
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class NetWorkImpl {
	// 获取省市城市
	public static WonderfulTopicJson GetWonderTopicList(Activity activity, String pageNo) {
		Gson gson = new Gson();
		String url = "tuijian.txt";
		String strHttpResult = "";
		strHttpResult = JsonTestDataManager.getInstance().GetJsonByFile(url);
		WonderfulTopicJson obj = null;
		try {
			obj = gson.fromJson(strHttpResult, WonderfulTopicJson.class);
		} catch (JsonSyntaxException e) {
			throw (RefelectException) new GsonJsonParserException(GsonJsonParserException.class.getSimpleName(), e.getCause());
		}
		return obj;
	}

}

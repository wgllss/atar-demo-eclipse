/**
 * 
 */
package com.atar.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.application.CrashHandler;
import android.interfaces.HandlerListener;
import android.os.Message;
import android.reflection.ThreadPoolTool;
import android.utils.ShowLog;
import android.view.View;
import android.widget.BaseAdapter;

import com.atar.beans.JsonBean;
import com.atar.dao.DeleteRunnable;
import com.atar.dao.InsertRunnable;
import com.atar.dao.QueryRunnable;
import com.atar.db.EnumColumnName;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;

/**
 *****************************************************************************************************************************************************************************
 * 加载处理类
 * @author :Atar
 * @createTime:2014-7-31上午11:13:41
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("Recycle")
public class LoadUtil {
	private static String TAG = LoadUtil.class.getSimpleName();

	/**
	 * 查询数据库
	 * @author :Atar
	 * @createTime:2015-6-16下午2:28:23
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mHandler 
	 * @param whichPage 哪一个页面的json
	 * @param columnName 等于条件字段
	 * @param value 等于条件字段值
	 * @description:
	 */
	public static void QueryDB(HandlerListener mHandlerListener, String whichPage, String columnName, String value) {
		ThreadPoolTool.getInstance().execute(new QueryRunnable<JsonBean>(JsonBean.class, mHandlerListener, EnumColumnName.WHICH_PAGE, whichPage, columnName, value));// 从数据库获取动态分组
	}

	/**
	 * 查询数据库
	 * @author :Atar
	 * @createTime:2015-6-16下午2:28:23
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mHandler 
	 * @param whichPage 哪一个页面的json
	 * @param columnName 等于条件字段
	 * @param value 等于条件字段值
	 * @param msgWhat 查询后的标志
	 * @description:
	 */
	public static void QueryDB(HandlerListener mHandlerListener, String whichPage, String columnName, String value, int msgWhat) {
		ThreadPoolTool.getInstance().execute(new QueryRunnable<JsonBean>(JsonBean.class, mHandlerListener, EnumColumnName.WHICH_PAGE, whichPage, columnName, value, msgWhat));// 从数据库获取动态分组
	}

	/**
	 * 删除数据库
	 * @author :Atar
	 * @createTime:2015-11-16上午10:40:08
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param whichPage
	 * @param columnName
	 * @param value
	 * @description:
	 */
	public static void DeleteDB(String whichPage, String columnName, String value) {
		ThreadPoolTool.getInstance().execute(new DeleteRunnable<JsonBean>(JsonBean.class, EnumColumnName.WHICH_PAGE, whichPage, columnName, value));
	}

	/**
	 * 从数据库查询完成
	 * @author :Atar
	 * @createTime:2015-6-16下午2:22:45
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param msg handler msg
	 * @param typeOfT ：new TypeToken<List<T>>() { }.getType()
	 * @return
	 * @description:从数据库查询完居得到Json再次转化为对应的list ：new TypeToken<List<T>>() { }.getType()不能直接映射所以传入Type
	 */
	@SuppressWarnings("unchecked")
	public static <T> T loadFromSqlComelete(Message msg, Type typeOfT) {
		T t = null;
		if (msg.obj != null) {
			List<JsonBean> list = (List<JsonBean>) msg.obj;
			if (list != null && list.size() > 0) {
				String json = list.get(0).getJson();
				if (json != null && json.length() > 0) {
					Gson gson = new Gson();
					try {
						t = gson.fromJson(json, typeOfT);
					} catch (Exception e) {
						ShowLog.e(TAG, "JsonSyntaxException:" + CrashHandler.crashToString(e));
					}
				}
			}
		}
		return t;
	}

	/**
	 * 从数据库查询完成后直接加载到listview 等控件
	 * @author :Atar
	 * @createTime:2015-6-16下午2:25:55
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param msg
	 * @param allList
	 * @param mAdapter
	 * @param p
	 * @param typeOfT
	 * @description:从数据库查询完居得到Json再次转化为对应的list ：new TypeToken<List<T>>() { }.getType()不能直接映射所以传入Type
	 */
	@SuppressWarnings("unchecked")
	public static <T, P extends PullToRefreshBase<V>, V extends View> List<T> loadFromSqlComelete(Message msg, List<T> allList, BaseAdapter mAdapter, P p, Type typeOfT) {
		List<T> mList = null;
		if (msg.obj != null) {
			List<JsonBean> list = (List<JsonBean>) msg.obj;
			if (list != null && list.size() > 0) {
				String json = list.get(0).getJson();
				if (json != null && json.length() > 0) {
					Gson gson = new Gson();
					try {
						mList = gson.fromJson(json, typeOfT);
					} catch (Exception e) {
						if (e != null) {
							ShowLog.e(TAG, "JsonSyntaxException:" + CrashHandler.crashToString(e));
						}
					}
				}
			}
			if (mList != null && mList.size() > 0) {
				allList.addAll(mList);
				if (mAdapter != null) {
					mAdapter.notifyDataSetChanged();
				}
			}
		}
		if (p != null) {
			p.setRefreshing();
		}
		return mList;
	}

	/**
	 * 保存到数据库
	 * @author :Atar
	 * @createTime:2015-6-15上午10:11:06
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param list
	 * @param whichPager 哪一个页面
	 * @param columnName 条件字段
	 * @param columnValue 条件字段值
	 * @description:
	 */
	public static <T> void saveToDB(T t, String whichPage, String columnName, String columnValue) {
		try {
			if (t != null) {
				Gson gson = new Gson();
				String json = gson.toJson(t);
				JsonBean mActionListJson = new JsonBean();
				mActionListJson.setWhichPage(whichPage);
				mActionListJson.setJson(json);
				if (columnName != null && columnName.length() > 0) {
					if (EnumColumnName.SQL_FLAG.equals(columnName)) {
						mActionListJson.setSqlFlag(columnValue);
					}
				}
				List<JsonBean> listJson = new ArrayList<JsonBean>();
				listJson.add(mActionListJson);
				ThreadPoolTool.getInstance().execute(new InsertRunnable<JsonBean>(listJson, JsonBean.class, EnumColumnName.WHICH_PAGE, whichPage, columnName, columnValue));
			}
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, "e---->" + CrashHandler.crashToString(e));
			}
		}
	}
}

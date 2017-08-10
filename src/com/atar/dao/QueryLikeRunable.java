/**
 * 
 */
package com.atar.dao;

import java.util.List;

import android.appconfig.AppConfigSetting;
import android.os.Handler;
import android.os.Message;

import com.atar.application.AtarApplication;
import com.atar.db.EnumColumnName;
import com.atar.enums.EnumMsgWhat;
import com.lidroid.xutils.db.sqlite.Selector;

/**
 *****************************************************************************************************************************************************************************
 * 本地数据库搜索实现类
 * @author :Atar
 * @createTime:2014-9-15下午7:13:53
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class QueryLikeRunable<T> implements Runnable {
	private Class<T> cls;
	private Handler mHandler;
	private String columnName = "";
	private String value = "";
	private String likeColumnName2 = "";
	private String likeValue2 = "";
	private int msgWhat;
	private String loginUserId;
	private String orderBy = "";

	/**
	 * 搜索一个字段
	 * @param cls
	 * @param mHandler
	 * @param columnName
	 * @param value
	 */
	public QueryLikeRunable(Class<T> cls, Handler mHandler, String columnName, String value) {
		this.cls = cls;
		this.mHandler = mHandler;
		this.columnName = columnName;
		this.value = value;
		this.msgWhat = EnumMsgWhat.LOAD_FROM_SQL_LIKE_COMPLETE;
	}

	/**
	 * 搜索一个字段
	 * @param cls
	 * @param mHandler
	 * @param columnName
	 * @param value
	 */
	public QueryLikeRunable(Class<T> cls, Handler mHandler, String columnName, String value, String orderBy) {
		this.cls = cls;
		this.mHandler = mHandler;
		this.columnName = columnName;
		this.value = value;
		this.orderBy = orderBy;
		this.msgWhat = EnumMsgWhat.LOAD_FROM_SQL_LIKE_COMPLETE;
	}

	/**
	 * 搜索一个字段
	 * @param cls
	 * @param mHandler
	 * @param columnName
	 * @param value
	 * @param msgWhat
	 */
	public QueryLikeRunable(Class<T> cls, Handler mHandler, String columnName, String value, int msgWhat) {
		this.cls = cls;
		this.mHandler = mHandler;
		this.columnName = columnName;
		this.value = value;
		this.msgWhat = msgWhat;
	}

	/**
	 * 搜索第一个条件= 第二个条件like
	 * @param cls
	 * @param mHandler
	 * @param columnName
	 * @param value
	 * @param columnName2
	 * @param value2
	 */
	public QueryLikeRunable(Class<T> cls, Handler mHandler, String columnName, String value, String likeColumnName2, String likeValue2) {
		this.cls = cls;
		this.mHandler = mHandler;
		this.columnName = columnName;
		this.value = value;
		this.likeColumnName2 = likeColumnName2;
		this.likeValue2 = likeValue2;
		this.msgWhat = EnumMsgWhat.LOAD_FROM_SQL_LIKE_COMPLETE;
	}

	/**
	 * 搜索第一个条件= 第二个条件like
	 * @param cls
	 * @param mHandler
	 * @param columnName
	 * @param value
	 * @param columnName2
	 * @param value2
	 * @param msgWhat
	 */
	public QueryLikeRunable(Class<T> cls, Handler mHandler, String columnName, String value, String likeColumnName2, String likeValue2, int msgWhat) {
		this.cls = cls;
		this.mHandler = mHandler;
		this.columnName = columnName;
		this.value = value;
		this.likeColumnName2 = likeColumnName2;
		this.likeValue2 = likeValue2;
		this.msgWhat = msgWhat;
	}

	@Override
	public void run() {
		List<T> list = null;
		loginUserId = AppConfigSetting.getInstance().getUserId();
		if (loginUserId == null || loginUserId.equals("")) {
			return;
		}
		try {
			if (columnName != null && !"".equals(columnName)) {
				if ("".equals(orderBy)) {
					if (likeColumnName2 != null && !"".equals(likeColumnName2)) {
						list = AtarApplication.getApplication().getDb()
								.findAll(Selector.from(cls).where(columnName, "=", value).and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(likeColumnName2, "like", "%" + likeValue2 + "%"));
					} else {
						list = AtarApplication.getApplication().getDb()
								.findAll(Selector.from(cls).where(columnName, "like", "%" + value + "%").and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId));
					}
				} else {
					if (likeColumnName2 != null && !"".equals(likeColumnName2)) {
						list = AtarApplication
								.getApplication()
								.getDb()
								.findAll(
										Selector.from(cls).where(columnName, "=", value).and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(likeColumnName2, "like", "%" + likeValue2 + "%")
												.orderBy(orderBy));
					} else {
						list = AtarApplication.getApplication().getDb()
								.findAll(Selector.from(cls).where(columnName, "like", "%" + value + "%").and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).orderBy(orderBy));
					}
				}
			} else {
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		Message msg = mHandler.obtainMessage(msgWhat, list);
		mHandler.sendMessage(msg);
	}

}

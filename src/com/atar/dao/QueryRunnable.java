/**
 * 
 */
package com.atar.dao;

import java.util.List;

import android.appconfig.AppConfigSetting;
import android.common.CommonHandler;
import android.interfaces.HandlerListener;
import android.os.Message;

import com.atar.application.AtarApplication;
import com.atar.db.EnumColumnName;
import com.atar.enums.EnumMsgWhat;
import com.lidroid.xutils.db.sqlite.Selector;

/**
 *****************************************************************************************************************************************************************************
 * 异步从sql获取本地数据
 * @author :Atar
 * @createTime:2014-7-8下午5:53:28
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class QueryRunnable<T> implements Runnable {

	private Class<T> cls;
	private HandlerListener mHandlerListener;
	private String columnName = "";
	private String value = "";
	private String columnName2 = "";
	private String value2 = "";
	private int msgWhat;
	private String loginUserId;
	private String orderBy = "";

	public QueryRunnable(Class<T> cls, HandlerListener mHandlerListener) {
		this.cls = cls;
		this.mHandlerListener = mHandlerListener;
		this.msgWhat = EnumMsgWhat.LOAD_FROM_SQL_COMPLETE;// 默认用这个代替
	}

	public QueryRunnable(Class<T> cls, HandlerListener mHandlerListener, String orderBy) {
		this.cls = cls;
		this.mHandlerListener = mHandlerListener;
		this.msgWhat = EnumMsgWhat.LOAD_FROM_SQL_COMPLETE;// 默认用这个代替
		this.orderBy = orderBy;
	}

	public QueryRunnable(Class<T> cls, HandlerListener mHandlerListener, int msgWhat) {
		this.cls = cls;
		this.mHandlerListener = mHandlerListener;
		this.msgWhat = msgWhat;
	}

	/**
	 * select 一个字段
	 * @param cls
	 * @param mHandler
	 * @param columnName
	 * @param value
	 */
	public QueryRunnable(Class<T> cls, HandlerListener mHandlerListener, String columnName, String value) {
		this.cls = cls;
		this.mHandlerListener = mHandlerListener;
		this.columnName = columnName;
		this.value = value;
		this.msgWhat = EnumMsgWhat.LOAD_FROM_SQL_COMPLETE;
	}

	/**
	 * select 两个字段
	 * @param cls
	 * @param mHandler
	 * @param columnName
	 * @param value
	 * @param columnName2
	 * @param value2
	 */
	public QueryRunnable(Class<T> cls, HandlerListener mHandlerListener, String columnName, String value, String columnName2, String value2) {
		this.cls = cls;
		this.mHandlerListener = mHandlerListener;
		this.columnName = columnName;
		this.value = value;
		this.columnName2 = columnName2;
		this.value2 = value2;
		this.msgWhat = EnumMsgWhat.LOAD_FROM_SQL_COMPLETE;
	}

	/**
	 * select 两个字段
	 * @param cls
	 * @param mHandler
	 * @param columnName
	 * @param value
	 * @param columnName2
	 * @param value2
	 */
	public QueryRunnable(Class<T> cls, HandlerListener mHandlerListener, String columnName, String value, String columnName2, String value2, int msgWhat) {
		this.cls = cls;
		this.mHandlerListener = mHandlerListener;
		this.columnName = columnName;
		this.value = value;
		this.columnName2 = columnName2;
		this.value2 = value2;
		this.msgWhat = msgWhat;
	}

	/**
	 * 
	 * @param cls
	 * @param mHandler
	 * @param columnName : 查询的字段
	 * @param value :查询的字段值
	 */
	public QueryRunnable(Class<T> cls, HandlerListener mHandlerListener, String columnName, String value, int msgWhat) {
		this.cls = cls;
		this.mHandlerListener = mHandlerListener;
		this.columnName = columnName;
		this.value = value;
		this.msgWhat = msgWhat;
	}

	@Override
	public void run() {
		List<T> list = null;
		loginUserId = AppConfigSetting.getInstance().getUserId();
		if (loginUserId != null && loginUserId.length() > 0 && mHandlerListener != null) {
			try {
				if ("".equals(orderBy)) {
					if (columnName != null && !"".equals(columnName)) {
						if (columnName2 != null && !"".equals(columnName2)) {
							list = AtarApplication.getApplication().getDb()
									.findAll(Selector.from(cls).where(columnName, "=", value).and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(columnName2, "=", value2));
						} else {
							list = AtarApplication.getApplication().getDb().findAll(Selector.from(cls).where(columnName, "=", value).and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId));
						}
					} else {
						list = AtarApplication.getApplication().getDb().findAll(Selector.from(cls).where(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId));
					}
				} else {
					if (columnName != null && !"".equals(columnName)) {
						if (columnName2 != null && !"".equals(columnName2)) {
							list = AtarApplication.getApplication().getDb()
									.findAll(Selector.from(cls).where(columnName, "=", value).and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(columnName2, "=", value2).orderBy(orderBy));
						} else {
							list = AtarApplication.getApplication().getDb()
									.findAll(Selector.from(cls).where(columnName, "=", value).and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).orderBy(orderBy));
						}
					} else {
						list = AtarApplication.getApplication().getDb().findAll(Selector.from(cls).where(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).orderBy(orderBy));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		final Message msg = new Message();
		msg.what = msgWhat;
		msg.obj = list;
		CommonHandler.getInstatnce().getHandler().post(new Runnable() {

			@Override
			public void run() {
				mHandlerListener.onHandlerData(msg);
			}
		});
		// Message msg = mOnHandlerDataListener.obtainMessage(msgWhat, list);
		// mOnHandlerDataListener.sendMessage(msg);
	}
}

/**
 * 
 */
package com.atar.dao;

import java.util.List;

import android.appconfig.AppConfigSetting;
import android.utils.ShowLog;

import com.atar.application.AtarApplication;
import com.atar.beans.SqlEntry;
import com.atar.db.EnumColumnName;
import com.lidroid.xutils.db.sqlite.Selector;

/**
 *****************************************************************************************************************************************************************************
 * 异步将数据保存到本地sql
 * @author :Atar
 * @createTime:2014-7-8下午5:33:33
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class InsertRunnable<T extends SqlEntry> implements Runnable {
	private static final String TAG = InsertRunnable.class.getSimpleName();
	private List<T> list;
	private Class<T> cls;
	private String columnValue = "";
	private String columnName = "";
	private String columnValue2 = "";
	private String columnName2 = "";
	private T t;
	private String loginUserId;// 按照用户存，防止用户切换登陆

	/**
	 * 插入单条
	 * @param t
	 * @param cls
	 */
	public InsertRunnable(T t) {
		this.t = t;
		if (loginUserId == null || loginUserId.equals("")) {
			return;
		}
		t.setLoginUserId(loginUserId);
	}

	/**
	 * 插入多条
	 * @param list
	 * @param cls
	 */
	public InsertRunnable(List<T> list, Class<T> cls) {
		this.list = list;
		this.cls = cls;
		loginUserId = AppConfigSetting.getInstance().getUserId();
		if (loginUserId == null || loginUserId.equals("")) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setLoginUserId(loginUserId);
		}
	}

	/**
	 * 如果存在这一个条件的先删除
	 * @param list
	 * @param cls
	 * @param columnName 字段名
	 * @param columnValue 字段值
	 */
	public InsertRunnable(List<T> list, Class<T> cls, String columnName, String columnValue) {
		this.list = list;
		this.cls = cls;
		this.columnValue = columnValue;
		this.columnName = columnName;
		loginUserId = AppConfigSetting.getInstance().getUserId();
		if (loginUserId == null || loginUserId.equals("")) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setLoginUserId(loginUserId);
		}
	}

	/**
	 * 如果存在这两个条件的先删除
	 * @param list
	 * @param cls
	 * @param columnName
	 * @param columnValue
	 * @param columnName2
	 * @param columnValue2
	 */
	public InsertRunnable(List<T> list, Class<T> cls, String columnName, String columnValue, String columnName2, String columnValue2) {
		this.list = list;
		this.cls = cls;
		this.columnValue = columnValue;
		this.columnName = columnName;
		this.columnValue2 = columnValue2;
		this.columnName2 = columnName2;
		loginUserId = AppConfigSetting.getInstance().getUserId();
		if (loginUserId == null || loginUserId.equals("")) {
			return;
		}
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setLoginUserId(loginUserId);
		}
	}

	@Override
	public void run() {
		List<T> mList = null;
		try {
			if (t != null) {
				AtarApplication.getApplication().getDb().save(t);
				return;
			}
			if (list == null || list.size() == 0) {
				return;
			}
			if (!"".equals(columnName) && !"".equals(columnValue)) {
				if (!"".equals(columnName2) && !"".equals(columnValue2)) {
					mList = AtarApplication.getApplication().getDb()
							.findAll(Selector.from(cls).where(columnName, "=", columnValue).and(columnName2, "=", columnValue2).and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId));
				} else {
					mList = AtarApplication.getApplication().getDb().findAll(Selector.from(cls).where(columnName, "=", columnValue).and(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId));
				}
			} else {
				mList = AtarApplication.getApplication().getDb().findAll(Selector.from(cls).where(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId));
			}
			if (mList != null && mList.size() > 0) {
				try {
					AtarApplication.getApplication().getDb().deleteAll(mList);
				} catch (Exception e) {
					ShowLog.i(TAG, e.getMessage());
					e.printStackTrace();
				}
			}
			AtarApplication.getApplication().getDb().saveAll(list);
		} catch (Exception e) {
			ShowLog.i(TAG, e.getMessage());
			e.printStackTrace();
		}
	}
}

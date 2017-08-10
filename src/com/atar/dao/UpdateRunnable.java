/**
 * 
 */
package com.atar.dao;

import java.util.List;

import android.appconfig.AppConfigSetting;

import com.atar.application.AtarApplication;
import com.atar.beans.SqlEntry;
import com.atar.db.EnumColumnName;
import com.lidroid.xutils.db.sqlite.WhereBuilder;

/**
 *****************************************************************************************************************************************************************************
 * 异步线程修改数据库
 * @author :Atar
 * @createTime:2014-8-19上午10:58:08
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class UpdateRunnable<T extends SqlEntry> implements Runnable {
	private List<T> list;
	private String loginUserId;// 修改该用户的
	private T t;
	private String updateColumnName;
	private String whereColumnName1;
	private String whereValue1;
	private String whereColumnName2;
	private String whereValue2;

	/**
	 * 修改全部
	 * @param list
	 * @param columnName
	 */
	public UpdateRunnable(List<T> list, String columnName) {
		this.list = list;
		this.updateColumnName = columnName;
	}

	/**
	 * 修改list where条件一个字段
	 * @param list
	 * @param columnName
	 */
	public UpdateRunnable(List<T> list, String whereColumnName1, String whereValue1, String columnName) {
		this.list = list;
		this.updateColumnName = columnName;
	}

	/**
	 * 修改list where条件二个字段
	 * @param list
	 * @param columnName
	 */
	public UpdateRunnable(List<T> list, String whereColumnName1, String whereValue1, String whereColumnName2, String whereValue2, String columnName) {
		this.list = list;
		this.updateColumnName = columnName;
	}

	/**
	 * 修改一条数据
	 * @param list
	 * @param columnName
	 */
	public UpdateRunnable(T t, String columnName) {
		this.t = t;
		this.updateColumnName = columnName;
	}

	/**
	 * 修改一条数据 where条件一个字段
	 * @param list
	 * @param columnName
	 */
	public UpdateRunnable(T t, String whereColumnName1, String whereValue1, String updateColumnName) {
		this.t = t;
		this.whereColumnName1 = whereColumnName1;
		this.whereValue1 = whereValue1;
		this.updateColumnName = updateColumnName;
	}

	/**
	 * 修改一条数据 where条件二个字段
	 * @param list
	 * @param columnName
	 */
	public UpdateRunnable(T t, String whereColumnName1, String whereValue1, String whereColumnName2, String whereValue2, String updateColumnName) {
		this.t = t;
		this.whereColumnName1 = whereColumnName1;
		this.whereValue1 = whereValue1;
		this.whereColumnName2 = whereColumnName2;
		this.whereValue2 = whereValue2;
		this.updateColumnName = updateColumnName;
	}

	@Override
	public void run() {
		try {
			loginUserId = AppConfigSetting.getInstance().getUserId();
			if (loginUserId == null || loginUserId.equals("")) {
				return;
			}

			if (list != null && list.size() > 0) {
				if (whereColumnName1 != null && updateColumnName != null && whereValue1 != null && !"".equals(whereColumnName1) && !"".equals(updateColumnName) && !"".equals(whereValue1)) {
					if (whereColumnName2 != null && updateColumnName != null && whereValue2 != null && !"".equals(whereColumnName2) && !"".equals(updateColumnName) && !"".equals(whereValue2)) {
						AtarApplication
								.getApplication()
								.getDb()
								.updateAll(list, WhereBuilder.b(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(whereColumnName2, "=", whereValue2).and(whereColumnName1, "=", whereValue1),
										updateColumnName);
					} else {
						AtarApplication.getApplication().getDb()
								.updateAll(list, WhereBuilder.b(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(whereColumnName1, "=", whereValue1), updateColumnName);
					}
				} else {
					AtarApplication.getApplication().getDb().updateAll(list, WhereBuilder.b(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId), updateColumnName);
				}
				return;
			}
			if (t != null) {
				if (whereColumnName1 != null && updateColumnName != null && whereValue1 != null && !"".equals(whereColumnName1) && !"".equals(updateColumnName) && !"".equals(whereValue1)) {
					if (whereColumnName2 != null && updateColumnName != null && whereValue2 != null && !"".equals(whereColumnName2) && !"".equals(updateColumnName) && !"".equals(whereValue2)) {
						AtarApplication
								.getApplication()
								.getDb()
								.update(t, WhereBuilder.b(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(whereColumnName1, "=", whereValue1).and(whereColumnName2, "=", whereValue2),
										updateColumnName);
					} else {
						AtarApplication.getApplication().getDb()
								.update(t, WhereBuilder.b(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(whereColumnName1, "=", whereValue1), updateColumnName);
					}
				} else {
					AtarApplication.getApplication().getDb().update(t, WhereBuilder.b(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId), updateColumnName);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

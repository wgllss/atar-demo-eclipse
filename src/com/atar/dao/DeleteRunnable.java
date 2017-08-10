/**
 * 
 */
package com.atar.dao;

import android.appconfig.AppConfigSetting;

import com.atar.application.AtarApplication;
import com.atar.db.EnumColumnName;
import com.lidroid.xutils.db.sqlite.WhereBuilder;

/**
 *****************************************************************************************************************************************************************************
 * 删除数据
 * @author :Atar
 * @createTime:2014-9-18下午3:41:42
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DeleteRunnable<T> implements Runnable {
	private String loginUserId;// 修改该用户的
	private Class<T> cls;
	private String whereColumnName;
	private String whereValue;
	private String whereColumnName2;
	private String whereValue2;

	/**
	 * 删除数据
	 * @param cls
	 * @param whereColumnName
	 * @param whereValue
	 */
	public DeleteRunnable(Class<T> cls) {
		this.cls = cls;
	}

	/**
	 * 删除数据where =一个条件
	 * @param cls
	 * @param whereColumnName
	 * @param whereValue
	 */
	public DeleteRunnable(Class<T> cls, String whereColumnName, String whereValue) {
		this.cls = cls;
		this.whereColumnName = whereColumnName;
		this.whereValue = whereValue;
	}

	/**
	 * 删除数据where =两个条件
	 * @param cls
	 * @param whereColumnName
	 * @param whereValue
	 * @param whereColumnName2
	 * @param whereValue2
	 */
	public DeleteRunnable(Class<T> cls, String whereColumnName, String whereValue, String whereColumnName2, String whereValue2) {
		this.cls = cls;
		this.whereColumnName = whereColumnName;
		this.whereValue = whereValue;
		this.whereColumnName2 = whereColumnName2;
		this.whereValue2 = whereValue2;
	}

	@Override
	public void run() {
		try {
			loginUserId = AppConfigSetting.getInstance().getUserId();
			if (loginUserId == null || loginUserId.equals("")) {
				return;
			}
			if (cls != null) {
				if (whereColumnName != null && whereValue != null && !"".equals(whereColumnName) && !"".equals(whereValue) && whereColumnName2 != null && whereValue2 != null
						&& !"".equals(whereColumnName2) && !"".equals(whereValue2)) {
					AtarApplication.getApplication().getDb()
							.delete(cls, WhereBuilder.b(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(whereColumnName, "=", whereValue).and(whereColumnName2, "=", whereValue2));
					return;
				}
				if (whereColumnName != null && whereValue != null && !"".equals(whereColumnName) && !"".equals(whereValue)) {
					AtarApplication.getApplication().getDb().delete(cls, WhereBuilder.b(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId).and(whereColumnName, "=", whereValue));
					return;
				}
				AtarApplication.getApplication().getDb().delete(cls, WhereBuilder.b(EnumColumnName.SQL_LOGIN_USER_ID, "=", loginUserId));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

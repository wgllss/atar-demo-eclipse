/**
 * 
 */
package com.atar.beans;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2014-7-9下午1:03:36
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:  更改名称一定要和EnumColumnName.class内的一起改 
 *****************************************************************************************************************************************************************************
 */
public class SqlEntry {
	private int id;
	private String loginUserId = "";
	private String sqlFlag = "";

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getSqlFlag() {
		return sqlFlag;
	}

	public void setSqlFlag(String sqlFlag) {
		this.sqlFlag = sqlFlag;
	}

}

/**
 * 
 */
package com.atar.beans;

/**
 *****************************************************************************************************************************************************************************
 * 动态数据Listjson
 * @author :Atar
 * @createTime:2014-11-26下午2:09:03
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class JsonBean extends SqlEntry {
	private String json;
	/* 都保存成json用于区分哪一个页面 */
	private String whichPage;

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	public String getWhichPage() {
		return whichPage;
	}

	public void setWhichPage(String whichPage) {
		this.whichPage = whichPage;
	}
}

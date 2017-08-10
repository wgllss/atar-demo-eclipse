/**
 * 
 */
package com.atar.beans;

import java.io.Serializable;

/**
 *****************************************************************************************************************************************************************************
 * 构建顶部下拉选择组bean
 * @author :Atar
 * @createTime:2014-7-11上午9:46:58
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressWarnings("serial")
public class PopWindowItemBean implements Serializable {
	/* 自定义ID */
	private String DropDownItemId;
	/* 下拉条目 */
	private String DropDownItemName;
	/* 是否选中 */
	private boolean isSelect;
	/* 是否显示线 */
	private boolean isShowLine;
	private boolean isSpecial;

	public PopWindowItemBean(String DropDownItemId, String DropDownItemName) {
		this.DropDownItemId = DropDownItemId;
		this.DropDownItemName = DropDownItemName;
	}

	public PopWindowItemBean(String DropDownItemId, String DropDownItemName, boolean isSelect, boolean isShowLine) {
		this.DropDownItemId = DropDownItemId;
		this.DropDownItemName = DropDownItemName;
		this.isSelect = isSelect;
		this.isShowLine = isShowLine;
	}

	public PopWindowItemBean(String DropDownItemId, String DropDownItemName, boolean isSpecial) {
		this.DropDownItemId = DropDownItemId;
		this.DropDownItemName = DropDownItemName;
		this.isSpecial = isSpecial;
	}

	public String getDropDownItemId() {
		return DropDownItemId;
	}

	public void setDropDownItemId(String dropDownItemId) {
		DropDownItemId = dropDownItemId;
	}

	public String getDropDownItemName() {
		return DropDownItemName;
	}

	public void setDropDownItemName(String dropDownItemName) {
		DropDownItemName = dropDownItemName;
	}

	public boolean isSelect() {
		return isSelect;
	}

	public void setSelect(boolean isSelect) {
		this.isSelect = isSelect;
	}

	public boolean isShowLine() {
		return isShowLine;
	}

	public void setShowLine(boolean isShowLine) {
		this.isShowLine = isShowLine;
	}

	public boolean isSpecial() {
		return isSpecial;
	}

	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
}

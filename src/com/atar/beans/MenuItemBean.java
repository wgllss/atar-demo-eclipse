/**
 * 
 */
package com.atar.beans;

import java.io.Serializable;

/**
 *****************************************************************************************************************************************************************************
 * DragBean:
 * @author :Atar
 * @createTime:2014-5-19下午5:26:04
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class MenuItemBean implements Serializable {
	private static final long serialVersionUID = 1L;
	/* 条目图标Id灰色 or 背景暗色 */
	private int idGrey;
	/* 条目图标Id高亮 or 背景亮色 */
	private int idHight;//
	/* 图标 */
	private int iconID;
	/* 条目名称 */
	private String menuItemName;
	/* 是否有选中图标 */
	private boolean hasSelectIcon;
	/* 自定义条目ID */
	private String itemId;
	/* 未读数量0 */
	private int unReadNew;
	/* 是否显示未读数量 or 开关是否打开 */
	private boolean isShow;
	/* 条目第二文字 */
	private String itemName2;
	private String flag = "";
	/**红点数字颜色 默认白色*/
	private String unReadNewColor = "#FFFFFF";

	/**
	 * 总设置
	 * @param itemId
	 * @param menuItemName
	 */
	public MenuItemBean(String itemId, String menuItemName, String itemName2) {
		this.menuItemName = menuItemName;
		this.itemId = itemId;
		this.itemName2 = itemName2;
	}

	/**
	 * 总设置
	 * @param itemId
	 * @param menuItemName
	 */
	public MenuItemBean(String itemId, String menuItemName) {
		this.menuItemName = menuItemName;
		this.itemId = itemId;
		isShow = true;
	}

	public int getIdGrey() {
		return idGrey;
	}

	public void setIdGrey(int idGrey) {
		this.idGrey = idGrey;
	}

	public int getIdHight() {
		return idHight;
	}

	public void setIdHight(int idHight) {
		this.idHight = idHight;
	}

	public int getIconID() {
		return iconID;
	}

	public void setIconID(int iconID) {
		this.iconID = iconID;
	}

	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public String getItemName2() {
		return itemName2;
	}

	public void setItemName2(String itemName2) {
		this.itemName2 = itemName2;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public int getUnReadNew() {
		return unReadNew;
	}

	public void setUnReadNew(int unReadNew) {
		this.unReadNew = unReadNew;
	}

	public boolean isShow() {
		return isShow;
	}

	public void setShow(boolean isShow) {
		this.isShow = isShow;
	}

	public boolean isHasSelectIcon() {
		return hasSelectIcon;
	}

	public void setHasSelectIcon(boolean hasSelectIcon) {
		this.hasSelectIcon = hasSelectIcon;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUnReadNewColor() {
		return unReadNewColor;
	}

	public void setUnReadNewColor(String unReadNewColor) {
		this.unReadNewColor = unReadNewColor;
	}
}

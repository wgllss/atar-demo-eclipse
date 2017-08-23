/**
 * 
 */
package com.atar.beans;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23上午11:23:31
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class PinnedSectionBean {
	public static final int ITEM = 0;
	public static final int SECTION = 1;
	private int itemType = ITEM;

	public PinnedSectionBean() {
	}

	public PinnedSectionBean(int itemType) {
		this.itemType = itemType;
	}

	public int getItemType() {
		return itemType;
	}

	public void setItemType(int itemType) {
		this.itemType = itemType;
	}
}

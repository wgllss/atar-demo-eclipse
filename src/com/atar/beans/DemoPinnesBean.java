/**
 * 
 */
package com.atar.beans;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23上午11:29:23
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoPinnesBean extends PinnedSectionBean {

	private String itemName;

	public DemoPinnesBean(int itemType, String itemName) {
		super(itemType);
		this.itemName = itemName;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}

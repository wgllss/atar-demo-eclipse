/**
 * 
 */
package com.atar.beans;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-10-17下午5:40:24
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DynamicSkinBean {
	/**
	 * @param url
	 * @param downloadName
	 * @param loadName
	 * @param skinColor
	 * @param skinName
	 */
	public DynamicSkinBean(String url, String downloadName, String loadName, String skinColor, String skinName) {
		super();
		this.url = url;
		this.downloadName = downloadName;
		this.loadName = loadName;
		this.skinColor = skinColor;
		this.skinName = skinName;
	}

	private int progress;
	private String url;
	private String downloadName;
	private String loadName;
	private String skinColor;
	private String skinName;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDownloadName() {
		return downloadName;
	}

	public void setDownloadName(String downloadName) {
		this.downloadName = downloadName;
	}

	public String getLoadName() {
		return loadName;
	}

	public void setLoadName(String loadName) {
		this.loadName = loadName;
	}

	public String getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}

	public String getSkinName() {
		return skinName;
	}

	public void setSkinName(String skinName) {
		this.skinName = skinName;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
}

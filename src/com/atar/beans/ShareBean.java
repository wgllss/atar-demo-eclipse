/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-4-26下午3:27:15
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.atar.beans;

import java.util.List;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-4-26下午3:27:15
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ShareBean {
 
	/**
		{
		"shareContent":"淘股吧是中国知名的移动互联投资社交化平台",
		"shareUrl":"http://m.taoguba.com.cn/downloadApp",
		"shareTitle":"v_5.5.5",
		"ShareImgResID":"",
		"ShareImgUrl":"",
		"SHARE_MEDIA":["QZONE","WEIXIN"]
		}
	 */
	private String shareContent;
	private String shareUrl;
	private String shareTitle;
	private String ShareImgResID;
	private String ShareImgUrl;
	private List<String> SHARE_MEDIA;
	
	public String getShareContent() {
		return shareContent;
	}
	public void setShareContent(String shareContent) {
		this.shareContent = shareContent;
	}
	public String getShareUrl() {
		return shareUrl;
	}
	public void setShareUrl(String shareUrl) {
		this.shareUrl = shareUrl;
	}
	public String getShareTitle() {
		return shareTitle;
	}
	public void setShareTitle(String shareTitle) {
		this.shareTitle = shareTitle;
	}
	public String getShareImgResID() {
		return ShareImgResID;
	}
	public void setShareImgResID(String shareImgResID) {
		ShareImgResID = shareImgResID;
	}
	public String getShareImgUrl() {
		return ShareImgUrl;
	}
	public void setShareImgUrl(String shareImgUrl) {
		ShareImgUrl = shareImgUrl;
	}
	public List<String> getSHARE_MEDIA() {
		return SHARE_MEDIA;
	}
	public void setSHARE_MEDIA(List<String> sHARE_MEDIA) {
		SHARE_MEDIA = sHARE_MEDIA;
	}
 
	
	
	
}

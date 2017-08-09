/**
 * 
 */
package com.atar.beans;

/**
 ******************************************************************************************
 * WonderfulTopicBean:
 * @author: Atar 
 * @createTime:2014年7月21日下午11:49:01
 * @modifyTime:
 * @version: 1.0.0
 * @description:
 ******************************************************************************************
 */
public class WonderfulTopicBean {
	private String imageUrl;
	private int replyNum;
	private String subbody;
	private String subject;
	private int topicID;
	private int userID;
	private String linkID;
	private int usefulNum;
	private String type;
	private String userName;
	private String poratrait;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(int replyNum) {
		this.replyNum = replyNum;
	}

	public String getSubbody() {
		return subbody;
	}

	public void setSubbody(String subbody) {
		this.subbody = subbody;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public int getTopicID() {
		return topicID;
	}

	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLinkID() {
		return linkID;
	}

	public void setLinkID(String linkID) {
		this.linkID = linkID;
	}

	public int getUsefulNum() {
		return usefulNum;
	}

	public void setUsefulNum(int usefulNum) {
		this.usefulNum = usefulNum;
	}

	public String getPoratrait() {
		return poratrait;
	}

	public void setPoratrait(String poratrait) {
		this.poratrait = poratrait;
	}
}

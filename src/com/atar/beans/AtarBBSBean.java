/**
 * 
 */
package com.atar.beans;

/**
 ******************************************************************************************
 * TaoguBBSBean:
 * @author: Atar 
 * @createTime:2014年7月23日下午10:55:05
 * @modifyTime:
 * @version: 1.0.0
 * @description:
 ******************************************************************************************
 */
public class AtarBBSBean {
	private long lastReplyDate;// (Timestamp, optional): 回帖时间,
	private long postDate;// (Timestamp, optional): 发表时间,
	private String subject;// (string, optional): 帖子标题
	private int topicID;// (integer, optional): 主贴ID,
	private int totalReplyNum;// (integer, optional): 回复数,
	private int userID;// (integer, optional): 用户ID,
	private String userName;// (string, optional): 用户名,
	private int usefulNum;// (integer, optional): 赞数,
	private String thumbnail;// (string, optional): 缩略图,

	public long getPostDate() {
		return postDate;
	}

	public void setPostDate(long postDate) {
		this.postDate = postDate;
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

	public int getTotalReplyNum() {
		return totalReplyNum;
	}

	public void setTotalReplyNum(int totalReplyNum) {
		this.totalReplyNum = totalReplyNum;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getLastReplyDate() {
		return lastReplyDate;
	}

	public void setLastReplyDate(long lastReplyDate) {
		this.lastReplyDate = lastReplyDate;
	}

	public int getUsefulNum() {
		return usefulNum;
	}

	public void setUsefulNum(int usefulNum) {
		this.usefulNum = usefulNum;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof AtarBBSBean) {
			AtarBBSBean mTaoguBBSBean = (AtarBBSBean) o;
			return this.topicID == mTaoguBBSBean.topicID;
		}
		return super.equals(o);
	}

}

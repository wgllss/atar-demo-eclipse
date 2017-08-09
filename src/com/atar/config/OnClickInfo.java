/**
 * 
 */
package com.atar.config;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-5-25下午2:44:13
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class OnClickInfo {
	private boolean needLogin;// 是否需要登陆
	private String className;// 类名
	// intentKeyValueClassName; // int or double or String or ArrayList<String> or MsgAndReMsgBean
	// optionJson=[{"intentKey":"intnet传值Key","intentKeyValueClassName":"intent传值类型名","intentKeyValue":"intent所传值"},{"intentKey":"intnet传值Key","intentKeyValueClassName":"intent传值类型名","intentKeyValue":"intent所传值"}];
	private String optionJson;// 参数
	// eventType :0百度统计 1:友盟统计
	// {\"eventName\":\"买啥-淘股宝\",\"eventID\":\"1002\",\"eventType\":\"0\"}
	private String onEventInfo;// 统计点击事件json
	// 特殊点击事件类型，会先判断再判断上面的 optionJson
	private String specialInfo;
	private String PULL_TO_REFRESH_MODE;// 刷新模式 0:上下都可以刷新 1:只上面可下拉 2:只有下面可上拉 viewpager 用到

	public OnClickInfo(boolean needLogin, String className, String optionJson) {
		super();
		this.needLogin = needLogin;
		this.className = className;
		this.optionJson = optionJson;
	}

	public String getClassName() {
		return className;
	}

	public String getOptionJson() {
		return optionJson;
	}

	public boolean isNeedLogin() {
		return needLogin;
	}

	public void setNeedLogin(boolean needLogin) {
		this.needLogin = needLogin;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setOptionJson(String optionJson) {
		this.optionJson = optionJson;
	}

	public void setOnEventInfo(String onEventInfo) {
		this.onEventInfo = onEventInfo;
	}

	public String getOnEventInfo() {
		return onEventInfo;
	}

	public String getSpecialInfo() {
		return specialInfo;
	}

	public String getPULL_TO_REFRESH_MODE() {
		return PULL_TO_REFRESH_MODE;
	}
}

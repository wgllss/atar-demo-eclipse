/**
 * 
 */
package com.atar.modles;

import android.content.Context;
import android.utils.ShowLog;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2014-9-15下午2:07:38
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class CommonResult<T> {
	private static final String TAG = CommonResult.class.getSimpleName();
	private boolean status;
	private int errorCode;
	private String errorMessage;
	private T dto;

	public boolean isStatus() {
		return status;
	}

	public boolean isStatus(final Context context) {
		// if (!status) {
		switch (errorCode) {
		case 500:
			ShowLog.i(TAG, "服务器出错");
			break;
		case 404:
			ShowLog.i(TAG, "请求地址错误");
			break;
		case 4001:
			ShowLog.i(TAG, "服务端抛异常");
			break;
		case 302:
			ShowLog.i(TAG, "IP被禁");
			break;
		case 5003:
			break;
		case 5004:
			break;
		case 5001:
			ShowLog.i(TAG, "未登陆");

			break;
		default:
			break;
		}
		return status && errorCode == 0;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public T getDto() {
		return dto;
	}
}

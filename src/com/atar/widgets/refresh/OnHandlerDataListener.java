package com.atar.widgets.refresh;

import android.interfaces.HandlerListener;
import android.view.View;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

/**
 ******************************************************************************************
 * 操作本地动作或数据接口
 * @author: Atar 
 * @createTime:2014年8月8日上午12:05:12
 * @modifyTime:
 * @version: 1.0.0
 * @description:
 ******************************************************************************************
 */
public interface OnHandlerDataListener<T extends PullToRefreshBase<V>, V extends View> extends HandlerListener {

	/**
	 * 发送操作标志
	 *@atour: Atar
	 *@createTime:2014年8月18日下午10:14:26
	 *@modifyTime:
	 *@modifyAtour:
	 *@version: 1.0.0
	 *@param msgWhat:
	 *@description:
	 */
	void sendEmptyMessage(int msgWhat);

	/**
	 * 延迟发送送操作标志
	 *@atour: Atar
	 *@createTime:2014年8月18日下午10:14:32
	 *@modifyTime:
	 *@modifyAtour:
	 *@version: 1.0.0
	 *@param msgWhat
	 *@param delayMillis:
	 *@description:
	 */
	void sendEmptyMessageDelayed(int msgWhat, long delayMillis);

	/**
	 * 发送消息带obj
	 * @author :Atar
	 * @createTime:2014-9-16下午5:48:28
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param msgWhat
	 * @param obj
	 * @description:
	 */
	void sendMessage(int msgWhat, Object obj);

	/**
	 * 发送消息带msg1,msg2
	 * @author :Atar
	 * @createTime:2015-5-6上午9:50:31
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param msgWhat
	 * @param msg1
	 * @param msg2
	 * @description:
	 */
	void sendMessage(int msgWhat, int msg1, int msg2);

	/**
	 *  发送消息带msg1,msg2,obj
	 * @author :Atar
	 * @createTime:2015-5-6上午9:50:35
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param msgWhat
	 * @param msg1
	 * @param msg2
	 * @param obj
	 * @description:
	 */
	void sendMessage(int msgWhat, int msg1, int msg2, Object obj);

	/**
	 * 设置正在刷新
	 *@atour: Atar
	 *@createTime:2014年9月1日下午11:06:59
	 *@modifyTime:
	 *@modifyAtour:
	 *@version: 1.0.0:
	 *@description:
	 */
	void setRefreshing();

	/**
	 * 停止刷新
	 * @author :Atar
	 * @createTime:2014-9-2上午10:52:52
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	void onStopRefresh();

	/**
	 * 得到DataDispose
	 *@atour: Atar
	 *@createTime:2014年9月1日下午11:09:48
	 *@modifyTime:
	 *@modifyAtour:
	 *@version: 1.0.0
	 *@return:
	 *@description:
	 */
	DataDispose<T, V> getDataDispose();

	/**
	 * 是否下拉刷新
	 * @author :Atar
	 * @createTime:2014-9-2上午10:40:55
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	boolean isPullDownRefresh();

	/**
	 * 是否是第一次加载
	 * @author :Atar
	 * @createTime:2014-9-2下午2:35:47
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	boolean isFirstLoad();

	/**
	 * 设置是否第一次加载
	 * @author :Atar
	 * @createTime:2014-9-2下午2:38:33
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param isFirstLoad
	 * @description:
	 */
	void setIsFirstLoad(boolean isFirstLoad);

	/**
	 * 得到刷新的控件 包含被刷新的控件
	 * @author :Atar
	 * @createTime:2014-9-2上午10:21:12
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	T getPullView();

	/**
	 * 得到被刷新的控件
	 * @author :Atar
	 * @createTime:2015-5-5上午10:03:07
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	V getRefreshView();

	/**
	 * 设置刷新模式
	 * @author :Atar
	 * @createTime:2014-9-2下午3:41:19
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	void setRefreshMode(Mode mode);

	/**
	 * 不否切换Listview数据
	 * @author :Atar
	 * @createTime:2014-9-2下午5:08:31
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	boolean isSwitchData();

	/**
	 * 设置是否切变listview数据
	 * @author :Atar
	 * @createTime:2014-9-2下午5:10:07
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param isSwitchListViewData
	 * @description:
	 */
	void setIsSwitchViewData(boolean isSwitchViewData);
}

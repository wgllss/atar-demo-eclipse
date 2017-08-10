package com.atar.widgets.refresh;
//package com.taoguba.widget.refresh;
//
//import android.view.View;
//
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
//
///**
// ******************************************************************************************
// * 导入刷新控件接口
// * @author: Atar 
// * @createTime:2014年7月31日下午11:41:06
// * @modifyTime:
// * @version: 1.0.0
// * @description: T里面包含V
// ******************************************************************************************
// */
//public interface LoadRefreshView<T extends PullToRefreshBase<V>, V extends View> {
//
//	/**
//	 *刷新最外层控件
//	 *@atour: Atar
//	 *@createTime:2014年8月8日下午10:40:05
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0
//	 *@return:
//	 *@description: T里面包含V
//	 */
//	T getPullView();
//
//	/**
//	 *刷新最里层控件
//	 *@atour: Atar
//	 *@createTime:2014年8月8日下午10:40:34
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0
//	 *@return:
//	 *@description: T里面包含V
//	 */
//	V getRefreshView();
//
//	/**
//	 * 得到Handler
//	 *@atour: Atar
//	 *@createTime:2014年9月1日下午11:08:27
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0
//	 *@return:
//	 *@description:
//	 */
//	DataHandler<T, V> getDataHandler();
//
//	/**
//	 * 发送操作标志
//	 *@atour: Atar
//	 *@createTime:2014年8月18日下午10:14:26
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0
//	 *@param msgWhat:
//	 *@description:
//	 */
//	void sendEmptyMessage(int msgWhat);
//
//	/**
//	 * 延迟发送送操作标志
//	 *@atour: Atar
//	 *@createTime:2014年8月18日下午10:14:32
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0
//	 *@param msgWhat
//	 *@param delayMillis:
//	 *@description:
//	 */
//	void sendEmptyMessageDelayed(int msgWhat, long delayMillis);
//
//	/**
//	 * 发送消息带obj
//	 * @author :Atar
//	 * @createTime:2014-9-16下午5:48:28
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param msgWhat
//	 * @param obj
//	 * @description:
//	 */
//	void sendObtainMessage(int msgWhat, Object obj);
//
//	/**
//	 * 发送消息带msg1,msg2
//	 * @author :Atar
//	 * @createTime:2015-5-6上午9:50:31
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param msgWhat
//	 * @param msg1
//	 * @param msg2
//	 * @description:
//	 */
//	void sendObtainMessage(int msgWhat, int msg1, int msg2);
//
//	/**
//	 *  发送消息带msg1,msg2,obj
//	 * @author :Atar
//	 * @createTime:2015-5-6上午9:50:35
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param msgWhat
//	 * @param msg1
//	 * @param msg2
//	 * @param obj
//	 * @description:
//	 */
//	void sendObtainMessage(int msgWhat, int msg1, int msg2, Object obj);
//
//	/**
//	 * 停止刷新
//	 *@atour: Atar
//	 *@createTime:2014年8月31日下午11:45:32
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0:
//	 *@description:
//	 */
//	void onStopRefresh();
//
//	/**
//	 * 设置正在刷新
//	 *@atour: Atar
//	 *@createTime:2014年9月1日下午11:08:08
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0:
//	 *@description:
//	 */
//	void setRefreshing();
//
//	/**
//	 * 是否第一次载入
//	 *@atour: Atar
//	 *@createTime:2014年9月1日下午10:39:29
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0
//	 *@return:
//	 *@description:
//	 */
//	boolean isFirstLoad();
//
//	/**
//	 * 设置是否第一次载入
//	 *@atour: Atar
//	 *@createTime:2014年9月1日下午10:40:52
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0
//	 *@param isFirstLoad:
//	 *@description:
//	 */
//	void setIsFirstLoad(boolean isFirstLoad);
//
//	/**
//	 * 得到当前是下拉或者上拉模式
//	 *@atour: Atar
//	 *@createTime:2014年9月1日下午11:32:32
//	 *@modifyTime:
//	 *@modifyAtour:
//	 *@version: 1.0.0
//	 *@return:
//	 *@description:
//	 */
//	Mode getCurrentMode();
//
//	/**
//	 * 是否下拉刷新
//	 * @author :Atar
//	 * @createTime:2014-9-2上午10:40:55
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @return
//	 * @description:
//	 */
//	boolean isPullDownRefresh();
//
//	/**
//	 * 设置刷新模式
//	 * @author :Atar
//	 * @createTime:2014-9-2下午3:41:19
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	void setRefreshMode(Mode mode);
//
//	/**
//	 * 不否切换Listview数据
//	 * @author :Atar
//	 * @createTime:2014-9-2下午5:08:31
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @return
//	 * @description:
//	 */
//	boolean isSwitchData();
//
//	/**
//	 * 设置是否切变listview数据
//	 * @author :Atar
//	 * @createTime:2014-9-2下午5:10:07
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param isSwitchListViewData
//	 * @description:
//	 */
//	void setIsSwitchViewData(boolean isSwitchViewData);
//}

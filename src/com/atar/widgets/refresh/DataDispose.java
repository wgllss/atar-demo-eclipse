/**
 * 
 */
package com.atar.widgets.refresh;

import android.annotation.SuppressLint;
import android.application.CrashHandler;
import android.os.Message;
import android.utils.ShowLog;
import android.view.View;
import android.widget.CommonToast;

import com.atar.enums.EnumMsgWhat;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;

/**
 ******************************************************************************************
 *操作本地数据的DataDispose
 * @author: Atar 
 * @createTime:2014年8月8日上午12:25:08
 * @modifyTime:
 * @version: 1.0.0
 * @description:本地数据 包括本地数据库或代码中的数据
 ******************************************************************************************
 */
@SuppressLint({ "SimpleDateFormat", "Recycle" })
public class DataDispose<T extends PullToRefreshBase<V>, V extends View> implements OnRefreshListener2<V>, OnPullEventListener<V> {
	private T t;
	private OnHandlerDataListener<T, V> listener;

	private boolean isRequestingButNoutResult;

	// private String pullFromStartLastTime = "";
	// private String pullFromEndLastTime = "";

	public DataDispose(T t, OnHandlerDataListener<T, V> listener) {
		this.t = t;
		this.listener = listener;
		if (this.t != null && t.getContext() != null) {
			this.t.setOnRefreshListener(this);
			// this.t.setOnPullEventListener(this);
			this.t.setMode(Mode.BOTH);
		}
	}

	public void onHandlerData(Message msg) {
		try {
			if (listener != null) {
				listener.onHandlerData(msg);
			}
			switch (msg.what) {
			case EnumMsgWhat.REFRESHING_VIEW:
				if (t != null) {
					if (!isRequestingButNoutResult) {
						isRequestingButNoutResult = true;
						if (t.getCurrentMode() == Mode.PULL_FROM_START) {
							listener.sendEmptyMessage(EnumMsgWhat.REFRESH_PULL_DOWN);
						} else if (t.getCurrentMode() == Mode.PULL_FROM_END) {
							listener.sendEmptyMessage(EnumMsgWhat.REFRESH_PULL_UP);
						}
					} else {
						// t.getLoadingLayoutProxy().setLastUpdatedLabel(t.getContext().getResources().getString(R.string.refreshing_waiting));
						t.onRefreshComplete();
					}
				}
				break;
			case EnumMsgWhat.REFRESH_COMPLETE:
				if (t != null && t.getCurrentMode() == Mode.PULL_FROM_START) {// 下拉刷新
					if (listener == null || listener.isFirstLoad()) {
						CommonToast.showWhihPeriod("亲,木有获取到数据");
					} else {
						CommonToast.showWhihPeriod("亲,木有新的数据了");
					}
				} else if (t != null && t.getCurrentMode() == Mode.PULL_FROM_END) {// 上拉加载更多
					CommonToast.showWhihPeriod("亲,木有更多数据了");
				}
				onStopRefresh();
				break;
			case EnumMsgWhat.REFRESH_ERROR:
				onStopRefresh();
				break;
			// case EnumMsgWhat.REFRESH_COMPLETE3:
			// isRequestingButNoutResult = false;
			// break;
			// default:
			// break;
			}
		} catch (Exception e) {
			ShowLog.e(DataDispose.class.getSimpleName(), "handleMessage--e--->" + CrashHandler.crashToString(e));
		}
	}

	/**
	 * 停止刷新
	 *@atour: Atar
	 *@createTime:2014年8月31日下午11:43:26
	 *@modifyTime:
	 *@modifyAtour:
	 *@version: 1.0.0:
	 *@description:
	 */
	public void onStopRefresh() {
		isRequestingButNoutResult = false;
		if (t != null) {
			t.onRefreshComplete();
			// SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-HH:mm:ss");
			// Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			// String time = formatter.format(curDate);
			// if (t.getCurrentMode() == Mode.PULL_FROM_START) {
			// pullFromStartLastTime = time;
			// } else if (t.getCurrentMode() == Mode.PULL_FROM_END) {
			// pullFromEndLastTime = time;
			// }
			// t.getLoadingLayoutProxy().setLastUpdatedLabel(t.getContext().getResources().getString(R.string.refreshing_waiting));
		}
	}

	@Override
	public void onPullUpToRefresh(PullToRefreshBase<V> refreshView) {
		if (refreshView != null)
			listener.sendEmptyMessage(EnumMsgWhat.REFRESHING_VIEW);
	}

	@Override
	public void onPullDownToRefresh(PullToRefreshBase<V> refreshView) {
		if (refreshView != null)
			listener.sendEmptyMessage(EnumMsgWhat.REFRESHING_VIEW);
	}

	@SuppressLint("Recycle")
	@Override
	public void onPullEvent(PullToRefreshBase<V> refreshView, State state, Mode direction) {
		if (refreshView != null && refreshView.getContext() != null) {
			// int skinType = AppConfigSetting.getInstance().getInt(SkinMode.SKIN_MODE_KEY, 0);
			// if (refreshView.getHeaderLayout() != null) {
			// ((DynamicLoadingLayout) refreshView.getHeaderLayout()).setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
			// }
			// if (refreshView.getFooterLayout() != null) {
			// ((DynamicLoadingLayout) refreshView.getFooterLayout()).setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
			// }
			// if (direction == Mode.PULL_FROM_START) {
			// refreshView.getLoadingLayoutProxy().setRefreshingLabel(refreshView.getContext().getResources().getString(R.string.refreshing_waiting));
			// refreshView.getLoadingLayoutProxy().setPullLabel(refreshView.getContext().getResources().getString(R.string.pull_to_start_refresh));
			// refreshView.getLoadingLayoutProxy().setReleaseLabel(refreshView.getContext().getResources().getString(R.string.pull_to_start_reset));
			// if (!"".equals(pullFromStartLastTime)) {
			// refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(refreshView.getContext().getResources().getString(R.string.refreshing_waiting));
			// }
			// } else if (direction == Mode.PULL_FROM_END) {
			// refreshView.getLoadingLayoutProxy().setRefreshingLabel(refreshView.getContext().getResources().getString(R.string.refreshing_waiting));
			// refreshView.getLoadingLayoutProxy().setPullLabel(refreshView.getContext().getResources().getString(R.string.pull_to_up_load_more));
			// refreshView.getLoadingLayoutProxy().setReleaseLabel(refreshView.getContext().getResources().getString(R.string.pull_to_up_reset));
			// if (!"".equals(pullFromEndLastTime)) {
			// refreshView.getLoadingLayoutProxy().setLastUpdatedLabel(refreshView.getContext().getResources().getString(R.string.refreshing_waiting));
			// }
			// } else {
			//
			// }
		}
	}
}

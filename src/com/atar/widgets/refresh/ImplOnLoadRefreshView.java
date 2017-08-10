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
// * 实现加载刷新控件内容
// * @author: Atar 
// * @createTime:2014年7月31日下午11:54:11
// * @modifyTime:
// * @version: 1.0.0
// * @description:
// ******************************************************************************************
// */
//public class ImplOnLoadRefreshView<T extends PullToRefreshBase<V>, V extends View> implements LoadRefreshView<T, V> {
//	private T t;
//	private DataHandler<T, V> mHandler;
//	private boolean isFirstLoad = true;
//	private boolean isSwitchViewData;
//
//	private ImplOnLoadRefreshView(T t, DataHandler<T, V> mHandler) {
//		this.t = t;
//		this.mHandler = mHandler;
//	}
//
//	public static <T extends PullToRefreshBase<V>, V extends View> LoadRefreshView<T, V> LoadRefreshView(T t, OnHandlerDataListener<T, V> listener) {
//		ImplOnLoadRefreshView<T, V> mInstance = new ImplOnLoadRefreshView<T, V>(t, new DataHandler<T, V>(t, listener));
//		return mInstance;
//	}
//
//	@Override
//	public V getRefreshView() {
//		return t != null ? t.getRefreshableView() : null;
//	}
//
//	@Override
//	public T getPullView() {
//		return t;
//	}
//
//	@Override
//	public DataHandler<T, V> getDataHandler() {
//		return mHandler;
//	}
//
//	@Override
//	public void sendEmptyMessage(int msgWhat) {
//		if (mHandler != null) {
//			mHandler.sendEmptyMessage(msgWhat);
//		}
//	}
//
//	@Override
//	public void sendEmptyMessageDelayed(int msgWhat, long delayMillis) {
//		if (mHandler != null) {
//			mHandler.sendEmptyMessageDelayed(msgWhat, delayMillis);
//		}
//	}
//
//	@Override
//	public void sendObtainMessage(int msgWhat, Object obj) {
//		if (mHandler != null) {
//			mHandler.sendMessage(mHandler.obtainMessage(msgWhat, obj));
//		}
//	}
//
//	@Override
//	public void sendObtainMessage(int msgWhat, int msg1, int msg2) {
//		if (mHandler != null) {
//			mHandler.sendMessage(mHandler.obtainMessage(msgWhat, msg1, msg2));
//		}
//	}
//
//	@Override
//	public void sendObtainMessage(int msgWhat, int msg1, int msg2, Object obj) {
//		if (mHandler != null) {
//			mHandler.sendMessage(mHandler.obtainMessage(msgWhat, msg1, msg2, obj));
//		}
//	}
//
//	@Override
//	public void onStopRefresh() {
//		if (mHandler != null) {
//			mHandler.onStopRefresh();
//		}
//	}
//
//	@Override
//	public void setIsFirstLoad(boolean isFirstLoad) {
//		this.isFirstLoad = isFirstLoad;
//	}
//
//	@Override
//	public boolean isFirstLoad() {
//		return isFirstLoad;
//	}
//
//	@Override
//	public void setRefreshing() {
//		if (t != null) {
//			mHandler.postDelayed(new Runnable() {
//				@Override
//				public void run() {
//					t.setRefreshing();
//				}
//			}, 100);
//		}
//	}
//
//	@Override
//	public Mode getCurrentMode() {
//		return t != null ? t.getCurrentMode() : Mode.PULL_FROM_START;
//	}
//
//	@Override
//	public boolean isPullDownRefresh() {
//		return getCurrentMode() == Mode.PULL_FROM_START;
//	}
//
//	@Override
//	public void setRefreshMode(Mode mode) {
//		if (t != null) {
//			t.setMode(mode);
//		}
//	}
//
//	@Override
//	public boolean isSwitchData() {
//		return isSwitchViewData;
//	}
//
//	@Override
//	public void setIsSwitchViewData(boolean isSwitchViewData) {
//		this.isSwitchViewData = isSwitchViewData;
//	}
//}

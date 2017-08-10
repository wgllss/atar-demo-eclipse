package com.atar.fragments;
///**
// * 
// */
//package com.atar.fragment;
//
//import android.annotation.SuppressLint;
//import android.fragment.CommonFragment;
//import android.interfaces.HandleMessageListener;
//import android.os.Handler;
//import android.os.Message;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.TextView;
//
//import com.handmark.pulltorefresh.library.PullToRefreshBase;
//import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
//import com.taoguba.app.R;
//import com.taoguba.common.CommonLoading;
//import com.taoguba.enums.EnumMsgWhat;
//import com.taoguba.globe.GlobeSettings;
//import com.taoguba.utils.LoadUtil;
//import com.taoguba.widget.refresh.DataHandler;
//import com.taoguba.widget.refresh.OnHandlerDataListener;
//
///**
// *****************************************************************************************************************************************************************************
// * 淘股吧刷新Fragment
// * @author :Atar
// * @createTime:2014-6-3上午11:08:30
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description: 一步解决实现各种控件刷新 同里带有操作数据handler
// *****************************************************************************************************************************************************************************
// */
//@SuppressLint("Recycle")
//public class TaogubaRefreshFragment<T extends PullToRefreshBase<V>, V extends View> extends CommonFragment implements OnHandlerDataListener<T, V>, HandleMessageListener, OnClickListener {
//	protected T t;
//	private DataHandler<T, V> mHandler;
//	private boolean isFirstLoad = true;
//	private boolean isSwitchViewData;
//
//	// protected LoadRefreshView<T, V> onLoadRefreshView;
//	protected TextView txtToast;
//
//	protected HandleMessageListener handleMessageListener = this;
//
//	@Override
//	public void onDestroy() {
//		super.onDestroy();
//		try {
//			if (mHandler != null) {
//				mHandler.removeCallbacksAndMessages(null);
//			}
//			// if (netWorkHandler != null) {
//			// netWorkHandler.removeCallbacksAndMessages(null);
//			// }
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	@Override
//	public void onHandlerData(Message msg, T t) {
//		CommonLoading.dissMissLoading();
//	}
//
//	@Override
//	public void NetWorkHandlMessage(Message msg) {
//		CommonLoading.dissMissLoading();
//		onStopRefresh();
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
//	public void setRefreshing() {
//		if (t != null && mHandler != null) {
//			mHandler.postDelayed(new Runnable() {
//
//				@Override
//				public void run() {
//					t.setRefreshing();
//				}
//			}, 100);
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
//	public DataHandler<T, V> getHandler() {
//		return mHandler;// onLoadRefreshView != null ? onLoadRefreshView.getDataHandler() : null;
//	}
//
//	@Override
//	public boolean isPullDownRefresh() {
//		return (t != null ? t.getCurrentMode() : Mode.PULL_FROM_START) == Mode.PULL_FROM_START;// onLoadRefreshView == null ? true : onLoadRefreshView.isPullDownRefresh();
//	}
//
//	@Override
//	public boolean isFirstLoad() {
//		return isFirstLoad;// onLoadRefreshView == null ? true : onLoadRefreshView.isFirstLoad();
//	}
//
//	@Override
//	public void setIsFirstLoad(boolean isFirstLoad) {
//		this.isFirstLoad = isFirstLoad;
//		// if (onLoadRefreshView != null) {
//		// onLoadRefreshView.setIsFirstLoad(isFirstLoad);
//		// }
//	}
//
//	@Override
//	public T getPullView() {
//		return t;// onLoadRefreshView != null ? onLoadRefreshView.getPullView() : null;
//	}
//
//	@Override
//	public V getRefreshView() {
//		return t != null ? t.getRefreshableView() : null;
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
//		return isSwitchViewData;// onLoadRefreshView != null ? onLoadRefreshView.isSwitchData() : false;
//	}
//
//	@Override
//	public void setIsSwitchViewData(boolean isSwitchViewData) {
//		this.isSwitchViewData = isSwitchViewData;
//		// if (onLoadRefreshView != null) {
//		// onLoadRefreshView.setIsSwitchViewData(isSwitchViewData);
//		// }
//	}
//
//	@Override
//	public void setUserVisibleHint(boolean isVisibleToUser) {
//		initUI(isVisibleToUser);
//	}
//
//	private static Handler handler;
//
//	protected void initUI(final boolean isVisibleToUser) {
//		if (handler == null) {
//			handler = new Handler();
//		}
//		handler.postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				if (t == null || getActivity() == null || !isVisibleToUser) {
//					initUI(isVisibleToUser);
//				} else {
//					if (isFirstLoad()) {
//						sendEmptyMessageDelayed(EnumMsgWhat.LOAD_FROM_SQL, 50);
//						setIsFirstLoad(false);
//					}
//				}
//			}
//		}, 100);
//	}
//
//	/**
//	 * 设置刷新控件
//	 * @author :Atar
//	 * @createTime:2015-4-3上午10:19:26
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param t
//	 * @description:
//	 */
//	protected void setRefreshView(T t) {
//		// if (onLoadRefreshView == null) {
//		// onLoadRefreshView = ImplOnLoadRefreshView.LoadRefreshView(t, this);
//		// }
//		this.t = t;
//		if (mHandler == null) {
//			mHandler = new DataHandler<T, V>(t, this);
//		}
//	}
//
//	/**
//	 * 设置toast提示内容
//	 * @author :Atar
//	 * @createTime:2014-11-14下午4:21:23
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param strToastContent
//	 * @description:
//	 */
//	protected void setToastText(String strToastContent) {
//		if (txtToast != null) {
//			txtToast.setText(strToastContent);
//			txtToast.setVisibility(View.VISIBLE);
//		}
//	}
//
//	/**
//	 * 隐藏ToastText
//	 * @author :Atar
//	 * @createTime:2014-11-26下午3:18:21
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	protected void setToastViewGone() {
//		if (txtToast != null) {
//			txtToast.setVisibility(View.GONE);
//		}
//	}
//
//	/**
//	 * 设置txtToast
//	 * @author :Atar
//	 * @createTime:2015-4-30上午10:31:02
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param textView
//	 * @description:
//	 */
//	protected void setTextView(TextView textView) {
//		txtToast = textView;
//	}
//
//	@Override
//	public void onClick(View v) {
//	}
//
//	public void onSwitchUI(int which, int which2, String changeID) {
//	}
//
//	public void onRefreshUI(int which, int which2, Object obj) {
//	}
//
//	@Override
//	public void OnChangeSkin(int skinType) {
//		super.OnChangeSkin(skinType);
//		if (getPullView() != null && getActivity() != null) {
//			if (getPullView().getHeaderLayout() != null) {
//				getPullView().getHeaderLayout().setRefreshingLabel(getResources().getString(R.string.refreshing_waiting));
//				getPullView().getHeaderLayout().setPullLabel(getResources().getString(R.string.pull_to_start_refresh));
//				getPullView().getHeaderLayout().setReleaseLabel(getResources().getString(R.string.pull_to_start_reset));
//				getPullView().getHeaderLayout().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
//			}
//			if (getPullView().getFooterLayout() != null) {
//				getPullView().getFooterLayout().setRefreshingLabel(getResources().getString(R.string.refreshing_waiting));
//				getPullView().getFooterLayout().setPullLabel(getResources().getString(R.string.pull_to_up_load_more));
//				getPullView().getFooterLayout().setReleaseLabel(getResources().getString(R.string.pull_to_up_reset));
//				getPullView().getFooterLayout().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
//			}
//
//			if (getPullView().getHeaderLayout() != null && getPullView().getHeaderLayout().getHeaderText() != null) {
//				getPullView().getHeaderLayout().setHeaderTextColor(getActivity().getResources().obtainTypedArray(R.array.txt_day_grey_night_greyblack_color).getColor(skinType, 0));
//			}
//			if (getPullView().getFooterLayout() != null && getPullView().getFooterLayout().getHeaderText() != null) {
//				getPullView().getFooterLayout().setHeaderTextColor(getActivity().getResources().obtainTypedArray(R.array.txt_day_grey_night_greyblack_color).getColor(skinType, 0));
//			}
//			if (getPullView().getHeaderLayout() != null && getPullView().getHeaderLayout().getSubHeaderText() != null) {
//				getPullView().getHeaderLayout().setSubHeaderTextColor(getActivity().getResources().obtainTypedArray(R.array.txt_day_grey_night_greyblack_color).getColor(skinType, 0));
//			}
//			if (getPullView().getFooterLayout() != null && getPullView().getFooterLayout().getSubHeaderText() != null) {
//				getPullView().getFooterLayout().setSubHeaderTextColor(getActivity().getResources().obtainTypedArray(R.array.txt_day_grey_night_greyblack_color).getColor(skinType, 0));
//			}
//			if (getPullView().getHeaderLayout() != null) {
//				getPullView().getHeaderLayout().setRefreshBgColor(getActivity().getResources().obtainTypedArray(R.array.refresh_bg_color).getColor(skinType, 0));
//			}
//			if (getPullView().getFooterLayout() != null) {
//				getPullView().getFooterLayout().setRefreshBgColor(getActivity().getResources().obtainTypedArray(R.array.refresh_bg_color).getColor(skinType, 0));
//			}
//		}
//		LoadUtil.setTextColor(getActivity(), txtToast, R.array.txt_day_grey_night_greyblack_color, skinType);
//	}
//}

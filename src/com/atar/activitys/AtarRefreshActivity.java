/**
 * 
 */
package com.atar.activitys;

import android.common.CommonHandler;
import android.os.Message;
import android.reflection.NetWorkMsg;
import android.skin.SkinUtils;
import android.view.View;
import android.widget.TextView;

import com.atar.common.CommonLoading;
import com.atar.widgets.refresh.DataDispose;
import com.atar.widgets.refresh.OnHandlerDataListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-10下午3:50:36
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarRefreshActivity<T extends PullToRefreshBase<V>, V extends View> extends AtarDropTitleBarActivity implements OnHandlerDataListener<T, V> {

	private T t;
	private DataDispose<T, V> mDataDispose;
	private boolean isFirstLoad = true;
	private boolean isSwitchViewData;

	private TextView txtToast;

	@Override
	public void NetWorkCall(NetWorkMsg msg) {
		super.NetWorkCall(msg);
		CommonLoading.dissMissLoading();
		onStopRefresh();
	}

	@Override
	public void onHandlerData(Message msg) {
		CommonLoading.dissMissLoading();
	}

	@Override
	public void sendEmptyMessage(int msgWhat) {
		if (mDataDispose != null) {
			Message msg = new Message();
			msg.what = msgWhat;
			mDataDispose.onHandlerData(msg);
		}
	}

	@Override
	public void sendEmptyMessageDelayed(final int msgWhat, long delayMillis) {
		CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {
			@Override
			public void run() {
				sendEmptyMessage(msgWhat);
			}
		}, delayMillis);
	}

	@Override
	public void sendMessage(int msgWhat, Object obj) {
		if (mDataDispose != null) {
			Message msg = new Message();
			msg.what = msgWhat;
			msg.obj = obj;
			mDataDispose.onHandlerData(msg);
		}
	}

	@Override
	public void sendMessage(int msgWhat, int msg1, int msg2) {
		if (mDataDispose != null) {
			Message msg = new Message();
			msg.what = msgWhat;
			msg.arg1 = msg1;
			msg.arg2 = msg2;
			mDataDispose.onHandlerData(msg);
		}
	}

	@Override
	public void sendMessage(int msgWhat, int msg1, int msg2, Object obj) {
		if (mDataDispose != null) {
			Message msg = new Message();
			msg.what = msgWhat;
			msg.arg1 = msg1;
			msg.arg2 = msg2;
			msg.obj = obj;
			mDataDispose.onHandlerData(msg);
		}
	}

	@Override
	public void setRefreshing() {
		if (t != null) {
			t.setRefreshing();
		}
	}

	@Override
	public void onStopRefresh() {
		if (mDataDispose != null) {
			mDataDispose.onStopRefresh();
		}
	}

	@Override
	public DataDispose<T, V> getDataDispose() {
		return mDataDispose;
	}

	@Override
	public boolean isPullDownRefresh() {
		return (t != null ? t.getCurrentMode() : Mode.PULL_FROM_START) == Mode.PULL_FROM_START;
	}

	@Override
	public boolean isFirstLoad() {
		return isFirstLoad;
	}

	@Override
	public void setIsFirstLoad(boolean isFirstLoad) {
		this.isFirstLoad = isFirstLoad;
	}

	@Override
	public T getPullView() {
		return t;
	}

	@Override
	public V getRefreshView() {
		return t != null ? t.getRefreshableView() : null;
	}

	@Override
	public void setRefreshMode(Mode mode) {
		if (t != null) {
			t.setMode(mode);
		}
	}

	@Override
	public boolean isSwitchData() {
		return isSwitchViewData;
	}

	@Override
	public void setIsSwitchViewData(boolean isSwitchViewData) {
		this.isSwitchViewData = isSwitchViewData;
	}

	/**
	 * 设置toast提示内容
	 * @author :Atar
	 * @createTime:2014-11-14下午4:21:23
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param strToastContent
	 * @description:
	 */
	public void setToastText(String strToastContent) {
		if (txtToast != null) {
			txtToast.setText(strToastContent);
			txtToast.setVisibility(View.VISIBLE);
		}
	}

	/**
	 * 隐藏toast
	 * @author :Atar
	 * @createTime:2014-11-19下午9:01:47
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public void setToastTextGone() {
		if (txtToast != null) {
			txtToast.setVisibility(View.GONE);
		}
	}

	/**
	 * 设置toast提示
	 * @author :Atar
	 * @createTime:2015-6-3下午1:42:15
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param txtToast
	 * @description:
	 */
	public void setTextView(TextView txtToast) {
		this.txtToast = txtToast;
	}

	/**
	 * 设置刷新控件
	 * @author :Atar
	 * @createTime:2015-4-3上午10:19:26
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param t
	 * @description:
	 */
	protected void setRefreshView(T t) {
		this.t = t;
		if (mDataDispose == null) {
			mDataDispose = new DataDispose<T, V>(t, this);
		}
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		setRefreshSkin(skinType);
	}

	protected void setRefreshSkin(int skinType) {
		if (getPullView() != null) {
			// if (getPullView().getHeaderLayout() != null) {
			// getPullView().getHeaderLayout().setRefreshingLabel(getResources().getString(R.string.refreshing_waiting));
			// getPullView().getHeaderLayout().setPullLabel(getResources().getString(R.string.pull_to_start_refresh));
			// getPullView().getHeaderLayout().setReleaseLabel(getResources().getString(R.string.pull_to_start_reset));
			// getPullView().getHeaderLayout().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
			// }
			// if (getPullView().getFooterLayout() != null) {
			// getPullView().getFooterLayout().setRefreshingLabel(getResources().getString(R.string.refreshing_waiting));
			// getPullView().getFooterLayout().setPullLabel(getResources().getString(R.string.pull_to_up_load_more));
			// getPullView().getFooterLayout().setReleaseLabel(getResources().getString(R.string.pull_to_up_reset));
			// ((DynamicLoadingLayout) getPullView().getHeaderLayout()).setRefreshingDrawable(com.handmark.pulltorefresh.library.R.drawable.default_ptr_rotate);
			// getPullView().getFooterLayout().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
			// }

			if (getPullView().getHeaderLayout() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_text_color, skinType, getPullView().getHeaderLayout().getHeaderText());
			}
			if (getPullView().getFooterLayout() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_text_color, skinType, getPullView().getFooterLayout().getHeaderText());
			}
			if (getPullView().getHeaderLayout() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_sub_text_color, skinType, getPullView().getHeaderLayout().getSubHeaderText());
			}
			if (getPullView().getFooterLayout() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_sub_text_color, skinType, getPullView().getFooterLayout().getSubHeaderText());
			}
			SkinUtils.setBackgroundColor(this, R.string.refresh_bg_color, skinType, getPullView().getHeaderLayout());
			SkinUtils.setBackgroundColor(this, R.string.refresh_bg_color, skinType, getPullView().getFooterLayout());
		}
		SkinUtils.setTextColor(this, R.string.txt_day_grey_night_greyblack_color, skinType, txtToast);
	}
}

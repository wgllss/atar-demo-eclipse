/**
 * 
 */
package com.atar.activitys;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.utils.DeviceManager;
import android.utils.ScreenUtils;
import android.utils.ViewUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

import com.atar.adapters.DropDownAdapter;
import com.atar.beans.PopWindowItemBean;
import com.atar.interfaces.OnDropDownListener;

/**
 *****************************************************************************************************************************************************************************
 * 酷炫，动态，我的说说 等页面顶部含有下拉ListView页面的公共title 可下拉中间，或者下拉右边
 * @author :Atar
 * @createTime:2014-6-30下午5:05:43
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description: 需要此公共布局及功能继承此Activity
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("Recycle")
public abstract class AtarDropTitleBarActivity extends AtarCommonActivity implements OnDropDownListener, OnDismissListener {

	/* 是否继承 */
	private boolean isExtendsAtarDropTitleBarActivity;
	private OnDropDownListener mOnDropDownListener;
	private PopupWindow mPopupWindow;
	/* 可选设置下拉模式 默认下拉中间 */
	private DropDownMode mDropDownMode = DropDownMode.DROP_DOWN_CENTER;
	/* 当前模式状态 */
	protected DropDownMode statusMode;

	/* 枚举下拉模式 */
	public static enum DropDownMode {
		/* 下拉中间 */
		DROP_DOWN_CENTER(31),
		/* 下拉右边 */
		DROP_DOWN_RIGHT(32),
		/* 双重模式 */
		DROP_DOWN_BOTH(33);

		@SuppressWarnings("unused")
		private int value;

		DropDownMode(int value) {
			this.value = value;
		}
	};

	/* 是否 在选择时，改变标题 */
	private boolean isChangeTitleAtarDropTitleBarActivity = true;

	@Override
	protected void onPause() {
		super.onPause();
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mPopupWindow != null) {
			mPopupWindow.dismiss();
		}
	}

	@Override
	protected void bindEvent() {
		if (txtCommonTopTitle != null && isExtendsAtarDropTitleBarActivity) {
			txtCommonTopTitle.setOnClickListener(this);
			setOnDropDownListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.txt_common_top_title:
			if (mOnDropDownListener != null && (mDropDownMode == DropDownMode.DROP_DOWN_CENTER || mDropDownMode == DropDownMode.DROP_DOWN_BOTH)) {
				statusMode = DropDownMode.DROP_DOWN_CENTER;
				mOnDropDownListener.OnDropDownCenter(v);
			}
			break;
		case R.id.img_common_top_right:
			if (mOnDropDownListener != null && (mDropDownMode == DropDownMode.DROP_DOWN_RIGHT || mDropDownMode == DropDownMode.DROP_DOWN_BOTH)) {
				statusMode = DropDownMode.DROP_DOWN_RIGHT;
				mOnDropDownListener.OnDropDownRight(v);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 设置下拉模式
	 * @author :Atar
	 * @createTime:2014-7-18上午11:12:19
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mDropDownMode
	 * @description:
	 */
	public void setDropDownMode(DropDownMode mDropDownMode) {
		this.mDropDownMode = mDropDownMode;
	}

	/**
	 * 是否继承
	 * @author :Atar
	 * @createTime:2015-1-26上午11:38:25
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param isExtendsAtarDropTitleBarActivity
	 * @description:
	 */
	public void setExtendsAtarDropTitleBarActivity(boolean isExtendsAtarDropTitleBarActivity) {
		this.isExtendsAtarDropTitleBarActivity = isExtendsAtarDropTitleBarActivity;
	}

	/**
	 * 装载弹出下拉菜单
	 * @author :Atar
	 * @createTime:2014-7-2上午11:47:11
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param v
	 * @param mList 适配器所用list
	 * @description:
	 */
	protected void setDropDownList(View v, final List<PopWindowItemBean> mList, int currentPosition, int textSize) {
		if (mList == null || mList.size() == 0) {
			return;
		}
		if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
			// txtCommonTopTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().obtainTypedArray(R.array.drop_up).getDrawable(getCurrentSkinType()), null);
		}
		LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		ViewGroup dropView = (ViewGroup) mLayoutInflater.inflate(R.layout.activity_atar_common_title_drop, null, true);
		DropDownAdapter mDropDownAdapter = new DropDownAdapter(mList);
		mDropDownAdapter.setTxtSize(textSize);
		mDropDownAdapter.setCurrentPostiotn(currentPosition);
		((ListView) dropView).setAdapter(mDropDownAdapter);
		((ListView) dropView).setDividerHeight(0);
		((ListView) dropView).setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (mPopupWindow != null && mPopupWindow.isShowing()) {
					mPopupWindow.dismiss();
				}
				if (mOnDropDownListener != null) {
					if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
						if (isChangeTitleAtarDropTitleBarActivity) {
							txtCommonTopTitle.setText(mList.get(arg2).getDropDownItemName());
						}
						// txtCommonTopTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().obtainTypedArray(R.array.drop_down).getDrawable(getCurrentSkinType()), null);
						mOnDropDownListener.OnClickCenterDropDownItem(arg2, mList);
					} else if (statusMode == DropDownMode.DROP_DOWN_RIGHT) {
						mOnDropDownListener.OnClickRightDropDownItem(arg2, mList);
					} else {

					}
				}
			}
		});
		int width = 0;
		int height = 0;
		if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
			width = DeviceManager.getScreenWidth(this) / 2;
			if (ViewUtils.getAbsListViewHeightBasedOnChildren((ListView) dropView) > (int) ScreenUtils.getIntToDip(315)) {
				height = (int) ScreenUtils.getIntToDip(315);
			} else {
				height = ViewUtils.getAbsListViewHeightBasedOnChildren((ListView) dropView) + (int) ScreenUtils.getIntToDip(24);
			}
		} else if (statusMode == DropDownMode.DROP_DOWN_RIGHT) {
			width = (int) ScreenUtils.getIntToDip(120);
			height = ViewUtils.getAbsListViewHeightBasedOnChildren((ListView) dropView) + (int) ScreenUtils.getIntToDip(24);
		}
		mPopupWindow = new PopupWindow(dropView, width, height, true);
		setPopupWIndowAttr(mPopupWindow);
		if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
			setCenterPopupWIndowAttr(mPopupWindow);
		}
		mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
			mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.drop_c));
			if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
				mPopupWindow.showAtLocation(v, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, txtCommonTopTitle.getBottom());
			} else {
				mPopupWindow.showAtLocation(v, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, txtCommonTopTitle.getBottom() + (int) ScreenUtils.getIntToDip(25));
			}
		} else if (statusMode == DropDownMode.DROP_DOWN_RIGHT) {
			mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.drop_r));
			if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
				mPopupWindow.showAtLocation(v, Gravity.TOP | Gravity.RIGHT, (int) ScreenUtils.getIntToDip(2), txtCommonTopTitle.getBottom());
			} else {
				mPopupWindow.showAtLocation(v, Gravity.TOP | Gravity.RIGHT, (int) ScreenUtils.getIntToDip(2), v.getBottom() + (int) ScreenUtils.getIntToDip(25));
			}
		}
		mPopupWindow.setOnDismissListener(this);
	}

	/**
	 * 装载弹出下拉菜单
	 * @author :Atar
	 * @createTime:2014-7-2上午11:47:11
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param v
	 * @param mList 适配器所用list
	 * @description:
	 */
	protected void setDropDownList(View v, final List<PopWindowItemBean> mList, String strLastTxt, OnClickListener onClickLastListener, int currentPosition) {
		// if (mList == null || mList.size() == 0) {
		// return;
		// }
		// if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
		// txtCommonTopTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().obtainTypedArray(R.array.drop_up).getDrawable(getCurrentSkinType()), null);
		// }
		// LayoutInflater mLayoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
		// ViewGroup dropView = (ViewGroup) mLayoutInflater.inflate(R.layout.activity_drop_down_with_last_item, null, true);
		// ListView listView = (ListView) dropView.findViewById(R.id.lst_pop_win);
		// TextView txtLast = (TextView) dropView.findViewById(R.id.txt_drop_last);
		// DropDownAdapter mDropDownAdapter = new DropDownAdapter(mList);
		// mDropDownAdapter.setTxtSize(18);
		// mDropDownAdapter.setCurrentPostiotn(currentPosition);
		// listView.setAdapter(mDropDownAdapter);
		// listView.setDividerHeight(0);
		// txtLast.setText(strLastTxt);
		// if (onClickLastListener != null) {
		// txtLast.setOnClickListener(onClickLastListener);
		// }
		// listView.setOnItemClickListener(new OnItemClickListener() {
		// @Override
		// public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// if (mPopupWindow != null && mPopupWindow.isShowing()) {
		// mPopupWindow.dismiss();
		// }
		// if (mOnDropDownListener != null) {
		// if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
		// txtCommonTopTitle.setText(mList.get(arg2).getDropDownItemName());
		// // txtCommonTopTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().obtainTypedArray(R.array.drop_down).getDrawable(getCurrentSkinType()), null);
		// mOnDropDownListener.OnClickCenterDropDownItem(arg2, mList);
		// } else if (statusMode == DropDownMode.DROP_DOWN_RIGHT) {
		// mOnDropDownListener.OnClickRightDropDownItem(arg2, mList);
		// } else {
		//
		// }
		// }
		// }
		// });
		// int width = 0;
		// int height = 0;
		// if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
		// width = DeviceManager.getScreenWidth(this) / 2;
		// if (ViewUtils.getAbsListViewHeightBasedOnChildren(listView) > (int) ScreenUtils.getIntToDip(315)) {
		// height = (int) ScreenUtils.getIntToDip(315);
		// } else {
		// height = ViewUtils.getAbsListViewHeightBasedOnChildren(listView) + (int) ScreenUtils.getIntToDip(40) + (int) ScreenUtils.getIntToDip(24);
		// }
		// } else if (statusMode == DropDownMode.DROP_DOWN_RIGHT) {
		// width = (int) ScreenUtils.getIntToDip(120);
		// height = ViewGroup.LayoutParams.WRAP_CONTENT;
		// }
		// mPopupWindow = new PopupWindow(dropView, width, height, true);
		// setPopupWIndowAttr(mPopupWindow);
		// mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		//
		// if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
		// mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.drop_c));
		// if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
		// mPopupWindow.showAtLocation(v, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, txtCommonTopTitle.getBottom());
		// } else {
		// mPopupWindow.showAtLocation(v, Gravity.TOP | Gravity.CENTER_HORIZONTAL, 0, txtCommonTopTitle.getBottom() + (int) ScreenUtils.getIntToDip(25));
		// }
		//
		// } else if (statusMode == DropDownMode.DROP_DOWN_RIGHT) {
		// mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.drop_r));
		// if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
		// mPopupWindow.showAtLocation(v, Gravity.TOP | Gravity.RIGHT, (int) ScreenUtils.getIntToDip(2), txtCommonTopTitle.getBottom());
		// } else {
		// mPopupWindow.showAtLocation(v, Gravity.TOP | Gravity.RIGHT, (int) ScreenUtils.getIntToDip(2), txtCommonTopTitle.getBottom() + (int) ScreenUtils.getIntToDip(25));
		// }
		// }
		// mPopupWindow.setOnDismissListener(this);

	}

	/**
	 * 设置PopupWindow的属性
	 * @author :Atar
	 * @createTime:2016-2-25上午11:44:46
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mPopupWindow
	 * @description:
	 */
	protected void setPopupWIndowAttr(PopupWindow mPopupWindow) {
	}

	/**
	 * 设置PopupWindow的属性
	 * @author :Atar
	 * @createTime:2016-2-25上午11:44:46
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mPopupWindow
	 * @description:
	 */
	protected void setCenterPopupWIndowAttr(PopupWindow mPopupWindow) {
	}

	private void setOnDropDownListener(OnDropDownListener mOnDropDownListener) {
		this.mOnDropDownListener = mOnDropDownListener;
	}

	@Override
	public void onDismiss() {
		if (mPopupWindow != null && mPopupWindow.isShowing()) {
			mPopupWindow.dismiss();
		}
		if (statusMode == DropDownMode.DROP_DOWN_CENTER) {
			// txtCommonTopTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().obtainTypedArray(R.array.drop_down).getDrawable(getCurrentSkinType()), null);
		} else if (statusMode == DropDownMode.DROP_DOWN_RIGHT) {

		} else {

		}
	}

	@Override
	public void OnDropDownCenter(View v) {
	}

	@Override
	public void OnClickCenterDropDownItem(int position, List<PopWindowItemBean> list) {
	}

	@Override
	public void OnDropDownRight(View v) {
	}

	@Override
	public void OnClickRightDropDownItem(int position, List<PopWindowItemBean> list) {
	}

	@Override
	public void ChangeSkin(Resources mResources, int skinType) {
		super.ChangeSkin(mResources, skinType);
		if (isExtendsAtarDropTitleBarActivity && (mDropDownMode == DropDownMode.DROP_DOWN_CENTER || mDropDownMode == DropDownMode.DROP_DOWN_BOTH)) {
			// txtCommonTopTitle.setCompoundDrawablesWithIntrinsicBounds(null, null, getResources().obtainTypedArray(R.array.drop_down).getDrawable(skinType), null);
		}
		if (isExtendsAtarDropTitleBarActivity && (mDropDownMode == DropDownMode.DROP_DOWN_RIGHT || mDropDownMode == DropDownMode.DROP_DOWN_BOTH)) {
			// LoadUtil.setImageDrawable(this, imgCommonTopRight, R.array.common_more_drop, skinType);
		}
	}

	public void setChangeTitleAtarDropTitleBarActivity(boolean isChangeTitleAtarDropTitleBarActivity) {
		this.isChangeTitleAtarDropTitleBarActivity = isChangeTitleAtarDropTitleBarActivity;
	}

}

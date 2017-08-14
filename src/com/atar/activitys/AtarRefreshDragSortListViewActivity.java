/**
 * 
 */
package com.atar.activitys;

import android.adapter.CommonAdapter;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.CommonToast;

import com.atar.widget.draglistview_lib.DragSortController.OnRemoveingDoingListener;
import com.atar.widget.draglistview_lib.DragSortController.OnRemoveingListener;
import com.atar.widget.draglistview_lib.DragSortListView;
import com.atar.widget.draglistview_lib.DragSortListView.DropListener;
import com.atar.widget.draglistview_lib.DragSortListView.RemoveListener;
import com.atar.widgets.refresh.PullToRefreshDragSortListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2015-6-18下午2:09:48
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("Recycle")
public abstract class AtarRefreshDragSortListViewActivity<T> extends AtarRefreshActivity<PullToRefreshDragSortListView, DragSortListView> implements DropListener, RemoveListener, OnRemoveingListener,
		OnRemoveingDoingListener, OnItemClickListener, OnItemLongClickListener {

	private boolean isAtarRefreshDragSortListViewActivity = true;
	private CommonAdapter<T> adapter;
	private boolean isRemoveEnable;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (isAtarRefreshDragSortListViewActivity) {
			addContentView(R.layout.common_refresh_dragsort_list);
		}
	}

	@Override
	protected void initControl() {
		if (isAtarRefreshDragSortListViewActivity) {
			setRefreshView((PullToRefreshDragSortListView) findViewById(R.id.common_refresh_dragsort_lst));
		}
	}

	@Override
	protected void bindEvent() {
		super.bindEvent();
		if (isAtarRefreshDragSortListViewActivity) {

		}
	}

	/**
	 * 设置listview 适配器
	 * @author :Atar
	 * @createTime:2015-6-3上午10:44:38
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param adapter
	 * @description:
	 */
	public void setAdapter(CommonAdapter<T> adapter) {
		this.adapter = adapter;
		if (getRefreshView() != null) {
			getRefreshView().setDropListener(this);
			getRefreshView().setRemoveListener(this);
			getRefreshView().setOnRemoveingListener(this);
			getRefreshView().setOnItemClickListener(this);
			getRefreshView().setOnItemLongClickListener(this);
			getRefreshView().setDragEnabled(true);
			// getRefreshView().setRemoveEnabled(true);
			getRefreshView().setAdapter(adapter);
			listView = getRefreshView();
		}
	}

	/**
	 * 设置是否继承AtarRefreshDragSortListViewActivity内布局
	 * @author :Atar
	 * @createTime:2015-6-18下午3:01:14
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param isAtarRefreshDragSortListViewActivity
	 * @description:
	 */
	protected void setIsAtarRefreshDragSortListViewActivity(boolean isAtarRefreshDragSortListViewActivity) {
		this.isAtarRefreshDragSortListViewActivity = isAtarRefreshDragSortListViewActivity;
	}

	/**
	 * 设置是否滑动删除
	 * @author :Atar
	 * @createTime:2014-8-28下午2:55:54
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param isRemoveEnable
	 * @description:
	 */
	public void setRemoveEnabled(boolean isRemoveEnable) {
		if (getRefreshView() != null) {
			this.isRemoveEnable = isRemoveEnable;
			getRefreshView().setRemoveEnabled(isRemoveEnable);
		}
	}

	/**
	 * 是否拖动排序
	 * @author :Atar
	 * @createTime:2015-6-18下午4:49:02
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param enabled
	 * @description:
	 */
	public void setDragEnabled(boolean enabled) {
		if (getRefreshView() != null) {
			getRefreshView().setDragEnabled(enabled);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}

	@Override
	public void OnRemoveing(boolean IsRemoving) {
		if (isRemoveEnable) {
			CommonToast.show("亲,这是删除的节奏!!!请拖回原处");
		}
	}

	@Override
	public void OnRemoveingDoing(boolean IsRemoving, int position, int deltaX) {

	}

	@Override
	public void remove(int which) {
		adapter.removeItem(which);
	}

	@Override
	public void drop(int from, int to) {
		if (adapter != null) {
			T t = adapter.getItem(from);
			adapter.removeItem(from);
			adapter.insertItem(t, to);
		}
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		return false;
	}

	// @Override
	// protected void setRefreshSkin(int skinType) {
	// super.setRefreshSkin(skinType);
	// if (getPullView() != null) {
	// if (getPullView().getHeaderLoadingView() != null) {
	// getPullView().getHeaderLoadingView().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
	// }
	// if (getPullView().getFooterLoadingView() != null) {
	// getPullView().getFooterLoadingView().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
	// }
	// if (getPullView().getHeaderLoadingView() != null && getPullView().getHeaderLayout().getHeaderText() != null) {
	// getPullView().getHeaderLoadingView().setHeaderTextColor(getResources().obtainTypedArray(R.array.txt_day_grey_night_greyblack_color).getColor(skinType, 0));
	// }
	// if (getPullView().getFooterLoadingView() != null && getPullView().getFooterLayout().getHeaderText() != null) {
	// getPullView().getFooterLoadingView().setHeaderTextColor(getResources().obtainTypedArray(R.array.txt_day_grey_night_greyblack_color).getColor(skinType, 0));
	// }
	// if (getPullView().getHeaderLoadingView() != null && getPullView().getHeaderLayout().getSubHeaderText() != null) {
	// getPullView().getHeaderLoadingView().setSubHeaderTextColor(getResources().obtainTypedArray(R.array.txt_day_grey_night_greyblack_color).getColor(skinType, 0));
	// }
	// if (getPullView().getFooterLoadingView() != null && getPullView().getFooterLayout().getSubHeaderText() != null) {
	// getPullView().getFooterLoadingView().setSubHeaderTextColor(getResources().obtainTypedArray(R.array.txt_day_grey_night_greyblack_color).getColor(skinType, 0));
	// }
	// if (getPullView().getHeaderLoadingView() != null) {
	// getPullView().getHeaderLoadingView().setRefreshBgColor(getResources().obtainTypedArray(R.array.refresh_bg_color).getColor(skinType, 0));
	// }
	// if (getPullView().getFooterLoadingView() != null) {
	// getPullView().getFooterLoadingView().setRefreshBgColor(getResources().obtainTypedArray(R.array.refresh_bg_color).getColor(skinType, 0));
	// }
	// }
	// }

}

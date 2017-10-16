/**
 * 
 */
package com.atar.fragments;

import android.adapter.CommonAdapter;
import android.os.Bundle;
import android.skin.SkinUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CommonToast;
import android.widget.TextView;

import com.atar.activitys.R;
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
 * @createTime:2017-8-29下午2:51:15
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AtarRefreshDragSortListViewFragment<T> extends AratRefreshAbsListViewFragment<PullToRefreshDragSortListView, DragSortListView> implements DropListener, RemoveListener,
		OnRemoveingListener, OnRemoveingDoingListener {
	private CommonAdapter<T> adapter;
	private boolean isRemoveEnable;

	protected View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.common_refresh_dragsort_list, container, false);
			setTextView((TextView) view.findViewById(R.id.txt_list_toast));
			setRefreshView((PullToRefreshDragSortListView) view.findViewById(R.id.common_refresh_dragsort_lst));
		}
		if (view != null && view.getParent() != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
	}

	public void setAdapter(CommonAdapter<T> adapter) {
		this.adapter = adapter;
		super.setAdapter(adapter);
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
	public void OnRemoveingDoing(boolean IsRemoving, int position, int deltaX) {
	}

	@Override
	public void OnRemoveing(boolean IsRemoving) {
		if (isRemoveEnable) {
			CommonToast.show("亲,这是删除的节奏!!!请拖回原处");
		}
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
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		if (getPullView() != null) {
			if (getPullView().getHeaderLoadingView() != null) {
				SkinUtils.setTextColor(getActivity(), R.string.refresh_header_text_color, skinType, getPullView().getHeaderLoadingView().getHeaderText());
			}
			if (getPullView().getFooterLoadingView() != null) {
				SkinUtils.setTextColor(getActivity(), R.string.refresh_header_text_color, skinType, getPullView().getFooterLoadingView().getHeaderText());
			}
			if (getPullView().getHeaderLoadingView() != null) {
				SkinUtils.setTextColor(getActivity(), R.string.refresh_header_sub_text_color, skinType, getPullView().getHeaderLoadingView().getSubHeaderText());
			}
			if (getPullView().getFooterLoadingView() != null) {
				SkinUtils.setTextColor(getActivity(), R.string.refresh_header_sub_text_color, skinType, getPullView().getFooterLoadingView().getSubHeaderText());
			}
			SkinUtils.setBackgroundColor(getActivity(), R.string.refresh_bg_color, skinType, getPullView().getHeaderLoadingView());
			SkinUtils.setBackgroundColor(getActivity(), R.string.refresh_bg_color, skinType, getPullView().getFooterLoadingView());
		}
	}
}

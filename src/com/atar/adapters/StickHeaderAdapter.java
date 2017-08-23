/**
 * 
 */
package com.atar.adapters;

import java.util.List;

import android.adapter.CommonAdapter;
import android.utils.ShowLog;
import android.view.View;
import android.view.ViewGroup;

import com.atar.beans.PinnedSectionBean;
import com.atar.widgets.PinnedSectionListView.PinnedSectionListAdapter;

/**
 *****************************************************************************************************************************************************************************
 * @author :Atar
 * @createTime:2015-6-4上午10:12:06
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public abstract class StickHeaderAdapter<T extends PinnedSectionBean> extends CommonAdapter<T> implements PinnedSectionListAdapter {

	public StickHeaderAdapter(List<?> list) {
		super(list);
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		try {
			setContext(parent.getContext());
			if (getItemViewType(position) == T.SECTION) {
				convertView = getSectionView(position, convertView, parent);
			} else if (getItemViewType(position) == T.ITEM) {
				convertView = getItemView(position, convertView, parent);
			}
		} catch (Exception e) {
			ShowLog.e(StickHeaderAdapter.class.getSimpleName(), e.getMessage());
		}
		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	@Override
	public int getItemViewType(int position) {
		return getItem(position).getItemType();
	}

	@Override
	public boolean isItemViewTypePinned(int viewType) {
		return viewType == T.SECTION;
	}

	/**
	 * 清除section
	 * @author :Atar
	 * @createTime:2015-11-30上午9:51:08
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public void clearExceptSection() {
		if (getList() != null && getList().size() > 0) {
			for (int i = 0; i < getList().size(); i++) {
				if (getItemViewType(i) != T.SECTION) {
					getList().remove(i);
				}
			}
			notifyDataSetChanged();
		}
	}

	protected abstract View getSectionView(int position, View convertView, ViewGroup parent);

	protected abstract View getItemView(int position, View convertView, final ViewGroup parent);

}

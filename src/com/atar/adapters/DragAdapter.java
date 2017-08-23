/**
 * 
 */
package com.atar.adapters;

import java.util.List;

import android.adapter.CommonAdapter;
import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.beans.MenuItemBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23下午2:04:39
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("InflateParams")
public class DragAdapter extends CommonAdapter<MenuItemBean> {

	public DragAdapter(List<?> list) {
		super(list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolderItem mViewHolder = null;
		if (convertView == null) {
			mViewHolder = new ViewHolderItem();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_drag_item, null);
			mViewHolder.txt_item = (TextView) convertView.findViewById(R.id.txt_item);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolderItem) convertView.getTag();
		}
		MenuItemBean info = getItem(position);
		if (info != null) {
			mViewHolder.txt_item.setText(info.getMenuItemName());
		}
		return convertView;
	}

	static class ViewHolderItem {
		TextView txt_item;
	}
}

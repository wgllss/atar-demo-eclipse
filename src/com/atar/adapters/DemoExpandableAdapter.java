/**
 * 
 */
package com.atar.adapters;

import java.util.List;

import android.adapter.CommonExpandableListAdapter;
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
 * @createTime:2017-8-24上午11:42:29
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("InflateParams")
public class DemoExpandableAdapter extends CommonExpandableListAdapter<MenuItemBean, MenuItemBean> {

	public DemoExpandableAdapter(List<MenuItemBean> listGroup) {
		super(listGroup);
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		ViewHolderG mViewHolder = null;
		if (convertView == null) {
			mViewHolder = new ViewHolderG();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main_demo_item, null);
			mViewHolder.txt_item_name = (TextView) convertView.findViewById(R.id.txt_item_name);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolderG) convertView.getTag();
		}
		MenuItemBean info = getGroup(groupPosition);
		if (info != null) {
			mViewHolder.txt_item_name.setText(info.getMenuItemName());
		}
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		ViewHolderC mViewHolder = null;
		if (convertView == null) {
			mViewHolder = new ViewHolderC();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_demo_pinnes_item, null);
			mViewHolder.txt_item = (TextView) convertView.findViewById(R.id.txt_item);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolderC) convertView.getTag();
		}
		MenuItemBean info = getChild(groupPosition, childPosition);
		if (info != null) {
			mViewHolder.txt_item.setText(info.getMenuItemName());
		}
		return convertView;
	}

	static class ViewHolderG {
		TextView txt_item_name;
	}

	static class ViewHolderC {
		TextView txt_item;
	}
}

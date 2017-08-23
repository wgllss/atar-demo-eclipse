/**
 * 
 */
package com.atar.adapters;

import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.beans.DemoPinnesBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23上午11:23:04
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoPinnesSectionAdapter extends StickHeaderAdapter<DemoPinnesBean> {

	public DemoPinnesSectionAdapter(List<?> list) {
		super(list);
	}

	@Override
	protected View getSectionView(int position, View convertView, ViewGroup parent) {
		ViewHolderSection mViewHolder = null;
		if (convertView == null) {
			mViewHolder = new ViewHolderSection();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_demo_pinnes_item_section, null);
			mViewHolder.txt_section = (TextView) convertView.findViewById(R.id.txt_section);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolderSection) convertView.getTag();
		}
		DemoPinnesBean info = getItem(position);
		if (info != null) {
			mViewHolder.txt_section.setText(info.getItemName());
		}
		return convertView;
	}

	@Override
	protected View getItemView(int position, View convertView, ViewGroup parent) {
		ViewHolderItem mViewHolder = null;
		if (convertView == null) {
			mViewHolder = new ViewHolderItem();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_demo_pinnes_item, null);
			mViewHolder.txt_item = (TextView) convertView.findViewById(R.id.txt_item);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolderItem) convertView.getTag();
		}
		DemoPinnesBean info = getItem(position);
		if (info != null) {
			mViewHolder.txt_item.setText(info.getItemName());
		}
		return convertView;
	}

	static class ViewHolderSection {
		TextView txt_section;
	}

	static class ViewHolderItem {
		TextView txt_item;
	}
}

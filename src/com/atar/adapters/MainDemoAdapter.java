/**
 * 
 */
package com.atar.adapters;

import java.util.List;

import android.adapter.CommonAdapter;
import android.annotation.SuppressLint;
import android.skin.SkinUtils;
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
 * @createTime:2017-8-22下午4:23:28
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("InflateParams")
public class MainDemoAdapter extends CommonAdapter<MenuItemBean> {

	public MainDemoAdapter(List<?> list) {
		super(list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mViewHolder = null;
		if (convertView == null) {
			mViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_main_demo_item, null);
			mViewHolder.txt_item_name = (TextView) convertView.findViewById(R.id.txt_item_name);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		SkinUtils.setTextColor(parent.getContext(), R.string.txt_day_black_night_greywhite_color, getSkinType(), mViewHolder.txt_item_name);

		MenuItemBean info = getItem(position);
		if (info != null) {
			mViewHolder.txt_item_name.setText(info.getMenuItemName());
		}

		return convertView;
	}

	static class ViewHolder {
		TextView txt_item_name;
	}
}

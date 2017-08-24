/**
 * 
 */
package com.atar.adapters;

import java.util.List;

import android.adapter.CommonAdapter;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atar.activitys.R;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23下午5:51:47
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AppAdapter extends CommonAdapter<ApplicationInfo> {

	/**
	 * @param list
	 */
	public AppAdapter(List<?> list) {
		super(list);
	}

	@Override
	public int getViewTypeCount() {
		// menu type count
		return 3;
	}

	@Override
	public int getItemViewType(int position) {
		// current menu type
		return position % 3;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(parent.getContext(), R.layout.adapter_item_list_app, null);
			new ViewHolder(convertView);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		ApplicationInfo item = getItem(position);
		holder.iv_icon.setImageDrawable(item.loadIcon(parent.getContext().getPackageManager()));
		holder.tv_name.setText(item.loadLabel(parent.getContext().getPackageManager()));
		return convertView;
	}

	class ViewHolder {
		ImageView iv_icon;
		TextView tv_name;

		public ViewHolder(View view) {
			iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
			tv_name = (TextView) view.findViewById(R.id.tv_name);
			view.setTag(this);
		}
	}
}

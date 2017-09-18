/**
 * 
 */
package com.atar.adapters;

import java.util.List;

import android.adapter.CommonAdapter;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.beans.PopWindowItemBean;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2014-10-4下午4:04:51
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("ViewHolder")
public class DropDownAdapter extends CommonAdapter<PopWindowItemBean> {

	public int txtSize = 18;

	public int getTxtSize() {
		return txtSize;
	}

	public void setTxtSize(int txtSize) {
		this.txtSize = txtSize;
	}

	/* 默认列表文字颜色 array 数组颜色值 需要再次转化 */
	public int color = -1;

	public DropDownAdapter(List<?> list) {
		super(list);
	}

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	@Override
	public View getView(int position, View convertView, final ViewGroup parent) {
		convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_drop_dowm_item, null);
		View viewLineLeft = convertView.findViewById(R.id.view_drop_dowm_line_left);
		View viewLineRight = convertView.findViewById(R.id.view_drop_dowm_line_right);
		View viewSelect = convertView.findViewById(R.id.view_select);
		TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_drop_dowm_title);
		PopWindowItemBean info = getList().get(position);
		if (info != null) {
			txtTitle.setText(info.getDropDownItemName());
			if (info.isSelect()) {
				// viewSelect.setBackgroundColor(parent.getContext().getResources().getColor(R.color.common_drop_down_select_bg_color));
			} else {
				viewSelect.setBackgroundColor(Color.TRANSPARENT);
			}
			if (info.isSpecial()) {
				viewSelect.setVisibility(View.VISIBLE);
			} else {
				viewSelect.setVisibility(View.GONE);
			}
			if (info.isShowLine()) {
				viewLineLeft.setVisibility(View.VISIBLE);
				viewLineRight.setVisibility(View.VISIBLE);
				txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
				// viewLineLeft.setBackgroundColor(parent.getResources().getColor(R.color.common_drop_down_special_txt_color));
				// viewLineRight.setBackgroundColor(parent.getResources().getColor(R.color.common_drop_down_special_txt_color));
				// txtTitle.setTextColor(parent.getResources().getColor(R.color.common_drop_down_special_txt_color));
			} else {
				txtTitle.setTextSize(TypedValue.COMPLEX_UNIT_DIP, txtSize);
				if (getCurrentPostiotn() == -1) {
					viewSelect.setBackgroundColor(Color.TRANSPARENT);
					if (getColor() != -1) {
						// LoadUtil.setTextColor(parent.getContext(), txtTitle, getColor(), getSkinType());
					} else {
						// txtTitle.setTextColor(parent.getContext().getResources().getColor(R.color.white));
					}
					convertView.setBackgroundColor(Color.TRANSPARENT);
				} else if (getCurrentPostiotn() == position) {
					// LoadUtil.setTextColor(parent.getContext(), txtTitle, R.array.common_top_title_bar_bg_color, getSkinType());
					// convertView.setBackgroundColor(parent.getContext().getResources().getColor(R.color.transparent3));
				} else {
					convertView.setBackgroundColor(Color.TRANSPARENT);
					viewSelect.setBackgroundColor(Color.TRANSPARENT);
					if (getColor() != -1) {
						// LoadUtil.setTextColor(parent.getContext(), txtTitle, getColor(), getSkinType());
					} else {
						// txtTitle.setTextColor(parent.getContext().getResources().getColor(R.color.white));
					}
				}
				viewLineLeft.setVisibility(View.INVISIBLE);
				viewLineRight.setVisibility(View.GONE);
			}
		}
		return convertView;
	}
}

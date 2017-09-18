/**
 * 
 */
package com.atar.adapters;

import java.util.List;

import android.util.TypedValue;
import android.utils.ScreenUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.beans.PinnedSectionBean;
import com.atar.enums.EnumMsgWhat;

/**
 *****************************************************************************************************************************************************************************
 * listview 顶部有View下面tab再下面list适配器
 * @author :Atar
 * @createTime:2015-6-4上午10:28:36
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public abstract class StickHeaderTabAdapter<T extends PinnedSectionBean> extends StickHeaderAdapter<T> {
	private int tabIndicator = 0;
	private String[] strTabArray;

	public StickHeaderTabAdapter(List<?> list) {
		super(list);
	}

	public int getTabIndicator() {
		return tabIndicator;
	}

	public void setTabIndicator(int tabIndicator) {
		this.tabIndicator = tabIndicator;
		notifyDataSetChanged();
	}

	public String[] getStrTabArray() {
		return strTabArray;
	}

	public void setStickTabArray(String[] strTabArray) {
		this.strTabArray = strTabArray;
	}

	@Override
	protected View getSectionView(int position, View convertView, ViewGroup parent) {
		final ViewSectionHolder mViewSectionHolder;
		if (convertView == null) {
			mViewSectionHolder = new ViewSectionHolder();
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_section_tab_item, null);
			mViewSectionHolder.lineaTab = (LinearLayout) convertView.findViewById(R.id.linear_tab);
			mViewSectionHolder.linearTabLine = (LinearLayout) convertView.findViewById(R.id.linear_tab_line);
			if (getStrTabArray() != null && getStrTabArray().length > 0) {
				LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
				int size = (int) ScreenUtils.getIntToDip(5);
				int size2 = (int) ScreenUtils.getIntToDip(1.5f);
				LayoutParams lp2 = new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, size2, 1);
				setStickTabLineWidth(lp2, size);
				for (int i = 0; i < getStrTabArray().length; i++) {
					TextView txtTabItem = new TextView(getContext());
					View viewLine = new View(getContext());
					txtTabItem.setPadding(size, size, size, size);
					txtTabItem.setLayoutParams(lp);
					txtTabItem.setGravity(Gravity.CENTER);
					viewLine.setLayoutParams(lp2);
					txtTabItem.setText(getStrTabArray()[i]);
					txtTabItem.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
					mViewSectionHolder.lineaTab.addView(txtTabItem);
					mViewSectionHolder.linearTabLine.addView(viewLine);
				}
			}
			convertView.setTag(mViewSectionHolder);
		} else {
			mViewSectionHolder = (ViewSectionHolder) convertView.getTag();
		}
		if (mViewSectionHolder.lineaTab.getChildCount() > 0) {
			for (int i = 0; i < mViewSectionHolder.lineaTab.getChildCount(); i++) {
				if (i == getTabIndicator()) {
					// LoadUtil.setTextColor(getContext(), ((TextView) mViewSectionHolder.lineaTab.getChildAt(i)), R.array.select_tab_txt_color, getSkinType());
					// LoadUtil.setBackgroundColor(getContext(), mViewSectionHolder.linearTabLine.getChildAt(i), R.array.select_tab_txt_color, getSkinType());
				} else {
					// LoadUtil.setTextColor(getContext(), ((TextView) mViewSectionHolder.lineaTab.getChildAt(i)), R.array.tab_txt_color, getSkinType());
					// mViewSectionHolder.linearTabLine.getChildAt(i).setBackgroundColor(getContext().getResources().getColor(R.color.transparent0));
				}
				final int m = i;
				mViewSectionHolder.lineaTab.getChildAt(i).setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						if (getHandler() != null) {
							getHandler().sendMessage(getHandler().obtainMessage(EnumMsgWhat.REFRESH_HANDLER1, m, -1));
							setTabIndicator(m);
						}
					}
				});
			}
		}
		// LoadUtil.setBackgroundColor(getContext(), convertView, R.array.common_content_bg_color, getSkinType());
		setSectionViewUI(convertView, mViewSectionHolder);
		return convertView;
	}

	/**
	 * 设置线条左右距离
	 * @author :Atar
	 * @createTime:2015-7-13下午1:47:07
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param lp2
	 * @description:
	 */
	protected void setStickTabLineWidth(LayoutParams lp2, int size) {

	}

	/**
	 * 单独设置SectionViewUI(颜色背景)
	 * @author :Atar
	 * @createTime:2015-6-4上午11:17:03
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param convertView
	 * @param mViewSectionHolder
	 * @description:
	 */
	protected void setSectionViewUI(View convertView, ViewSectionHolder mViewSectionHolder) {
	}

	static class ViewSectionHolder {
		public LinearLayout lineaTab, linearTabLine;
	}

	class OnTabClickListener implements OnClickListener {
		private int position;

		public OnTabClickListener(int position) {
			this.position = position;
		}

		@Override
		public void onClick(View arg0) {
			if (getHandler() != null) {
				getHandler().sendMessage(getHandler().obtainMessage(EnumMsgWhat.REFRESH_HANDLER1, position, -1));
				setTabIndicator(position);
			}
		}
	}

}

/**
 * 
 */
package com.atar.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.skin.SkinUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshPinnedSectionListView;

/**
 *****************************************************************************************************************************************************************************
 * 带PullToRefreshPinnedSectionListView 的刷新Activity
 * @author :Atar
 * @createTime:2015-6-3上午9:56:09
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("Recycle")
public abstract class AtarRefreshPinnedSectionListViewActivity extends AtarRefreshActivity<PullToRefreshPinnedSectionListView, ListView> implements OnItemLongClickListener, OnItemClickListener {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		if (getResLayoutID() > 0) {
			addContentView(getResLayoutID());
		}
	}

	protected int getResLayoutID() {
		return R.layout.common_refresh_section_listveiw;
	}

	@Override
	protected void initControl() {
		setTextView((TextView) findViewById(R.id.txt_list_toast));
		setRefreshView((PullToRefreshPinnedSectionListView) findViewById(R.id.common_refresh_section_list));
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
	public void setAdapter(BaseAdapter adapter) {
		if (getRefreshView() != null) {
			getRefreshView().setOnItemClickListener(this);
			getRefreshView().setOnItemLongClickListener(this);
			getRefreshView().setAdapter(adapter);
			listView = getRefreshView();
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	}

	@Override
	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		return false;
	}

	@Override
	protected void setRefreshSkin(int skinType) {
		super.setRefreshSkin(skinType);
		if (getPullView() != null) {
			// if (getPullView().getHeaderLoadingView() != null) {
			// getPullView().getHeaderLoadingView().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
			// }
			// if (getPullView().getFooterLoadingView() != null) {
			// getPullView().getFooterLoadingView().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
			// }
			if (getPullView().getHeaderLoadingView() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_text_color, skinType, getPullView().getHeaderLoadingView().getHeaderText());
			}
			if (getPullView().getFooterLoadingView() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_text_color, skinType, getPullView().getFooterLoadingView().getHeaderText());
			}
			if (getPullView().getHeaderLoadingView() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_sub_text_color, skinType, getPullView().getHeaderLoadingView().getSubHeaderText());
			}
			if (getPullView().getFooterLoadingView() != null) {
				SkinUtils.setTextColor(this, R.string.refresh_header_sub_text_color, skinType, getPullView().getFooterLoadingView().getSubHeaderText());
			}
			SkinUtils.setBackgroundColor(this, R.string.refresh_bg_color, skinType, getPullView().getHeaderLoadingView());
			SkinUtils.setBackgroundColor(this, R.string.refresh_bg_color, skinType, getPullView().getFooterLoadingView());
		}
	}
}

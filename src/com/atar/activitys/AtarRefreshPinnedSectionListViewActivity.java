/**
 * 
 */
package com.atar.activitys;

import android.annotation.SuppressLint;
import android.os.Bundle;
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
		// super.setRefreshSkin(skinType);
		if (getPullView() != null) {
			// if (getPullView().getHeaderLoadingView() != null) {
			// getPullView().getHeaderLoadingView().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
			// }
			// if (getPullView().getFooterLoadingView() != null) {
			// getPullView().getFooterLoadingView().setRefreshingDrawable(GlobeSettings.refreshImg[skinType]);
			// }
			if (getPullView().getHeaderLoadingView() != null) {
				getPullView().getHeaderLoadingView().setHeaderTextColor(getResources().getColor(R.color.black));
			}
			if (getPullView().getFooterLoadingView() != null) {
				getPullView().getFooterLoadingView().setHeaderTextColor(getResources().getColor(R.color.black));
			}
			if (getPullView().getHeaderLoadingView() != null) {
				getPullView().getHeaderLoadingView().setSubHeaderTextColor(getResources().getColor(R.color.black));
			}
			if (getPullView().getFooterLoadingView() != null) {
				getPullView().getFooterLoadingView().setSubHeaderTextColor(getResources().getColor(R.color.black));
			}
			if (getPullView().getHeaderLoadingView() != null) {
				getPullView().getHeaderLoadingView().setRefreshBgColor(getResources().getColor(R.color.common_txt_hint_color_day));
			}
			if (getPullView().getFooterLoadingView() != null) {
				getPullView().getFooterLoadingView().setRefreshBgColor(getResources().getColor(R.color.common_txt_hint_color_day));
			}
		}
	}
}

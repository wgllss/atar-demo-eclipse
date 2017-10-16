/**
 * 
 */
package com.atar.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.skin.SkinUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.atar.activitys.R;
import com.handmark.pulltorefresh.library.PullToRefreshPinnedSectionListView;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2015-4-30下午4:47:47
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("Recycle")
public abstract class AtarRefreshSectionListViewFragment extends AratRefreshAbsListViewFragment<PullToRefreshPinnedSectionListView, ListView> {
	protected View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.common_refresh_section_listveiw, container, false);
			setTextView((TextView) view.findViewById(R.id.txt_list_toast));
			setRefreshView((PullToRefreshPinnedSectionListView) view.findViewById(R.id.common_refresh_section_list));
		}
		if (view != null && view.getParent() != null) {
			ViewGroup parent = (ViewGroup) view.getParent();
			if (parent != null) {
				parent.removeView(view);
			}
		}
		return view;
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		if (getPullView() != null) {
			if (getPullView().getHeaderLoadingView() != null) {
				SkinUtils.setTextColor(getActivity(), R.string.refresh_header_text_color, skinType, getPullView().getHeaderLoadingView().getHeaderText());
			}
			if (getPullView().getFooterLoadingView() != null) {
				SkinUtils.setTextColor(getActivity(), R.string.refresh_header_text_color, skinType, getPullView().getFooterLoadingView().getHeaderText());
			}
			if (getPullView().getHeaderLoadingView() != null) {
				SkinUtils.setTextColor(getActivity(), R.string.refresh_header_sub_text_color, skinType, getPullView().getHeaderLoadingView().getSubHeaderText());
			}
			if (getPullView().getFooterLoadingView() != null) {
				SkinUtils.setTextColor(getActivity(), R.string.refresh_header_sub_text_color, skinType, getPullView().getFooterLoadingView().getSubHeaderText());
			}
			SkinUtils.setBackgroundColor(getActivity(), R.string.refresh_bg_color, skinType, getPullView().getHeaderLoadingView());
			SkinUtils.setBackgroundColor(getActivity(), R.string.refresh_bg_color, skinType, getPullView().getFooterLoadingView());
		}
	}
}

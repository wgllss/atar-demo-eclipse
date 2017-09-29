///**
// * 
// */
//package com.atar.fragment.demos;
//
//import java.util.List;
//
//import android.common.CommonHandler;
//import android.content.pm.ApplicationInfo;
//import android.graphics.Color;
//import android.graphics.drawable.ColorDrawable;
//import android.os.Bundle;
//import android.os.Message;
//import android.utils.ScreenUtils;
//import android.view.View;
//import android.widget.AdapterView;
//import android.widget.CommonToast;
//
//import com.atar.activitys.R;
//import com.atar.adapters.AppAdapter;
//import com.atar.enums.EnumMsgWhat;
//import com.atar.fragments.AtarRefreshSwipeMenuListViewFragment;
//import com.atar.widget.swipmenulistview_lib.SwipeMenu;
//import com.atar.widget.swipmenulistview_lib.SwipeMenuItem;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :Atar
// * @createTime:2017-8-23下午5:46:47
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class DemoRefreshSwipeMenuListViewDiffFragment extends AtarRefreshSwipeMenuListViewFragment {
//	private List<ApplicationInfo> mAppList;
//	private AppAdapter mAdapter;
//
//	@Override
//	public void create(SwipeMenu menu) {
//		// Create different menus depending on the view type
//		switch (menu.getViewType()) {
//		case 0:
//			SwipeMenuItem item1 = new SwipeMenuItem(getActivity());
//			item1.setBackground(new ColorDrawable(Color.rgb(0xE5, 0x18, 0x5E)));
//			item1.setWidth((int) ScreenUtils.dpToPx(getActivity(), 90));
//			item1.setIcon(R.drawable.ic_action_favorite);
//			menu.addMenuItem(item1);
//
//			SwipeMenuItem item2 = new SwipeMenuItem(getActivity());
//			item2.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
//			item2.setWidth((int) ScreenUtils.dpToPx(getActivity(), 90));
//			item2.setIcon(R.drawable.ic_action_good);
//			menu.addMenuItem(item2);
//			break;
//		case 1:
//			SwipeMenuItem item3 = new SwipeMenuItem(getActivity());
//			item3.setBackground(new ColorDrawable(Color.rgb(0xE5, 0xE0, 0x3F)));
//			item3.setWidth((int) ScreenUtils.dpToPx(getActivity(), 90));
//			item3.setIcon(R.drawable.ic_action_important);
//			menu.addMenuItem(item3);
//			SwipeMenuItem item4 = new SwipeMenuItem(getActivity());
//			item4.setBackground(new ColorDrawable(Color.rgb(0xF9, 0x3F, 0x25)));
//			item4.setWidth((int) ScreenUtils.dpToPx(getActivity(), 90));
//			item4.setIcon(R.drawable.ic_action_discard);
//			menu.addMenuItem(item4);
//			break;
//		case 2:
//			SwipeMenuItem item5 = new SwipeMenuItem(getActivity());
//			item5.setBackground(new ColorDrawable(Color.rgb(0x30, 0xB1, 0xF5)));
//			item5.setWidth((int) ScreenUtils.dpToPx(getActivity(), 90));
//			item5.setIcon(R.drawable.ic_action_about);
//			menu.addMenuItem(item5);
//			SwipeMenuItem item6 = new SwipeMenuItem(getActivity());
//			item6.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0xCE)));
//			item6.setWidth((int) ScreenUtils.dpToPx(getActivity(), 90));
//			item6.setIcon(R.drawable.ic_action_share);
//			menu.addMenuItem(item6);
//			break;
//		}
//	}
//
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//		mAppList = getActivity().getPackageManager().getInstalledApplications(0);
//		mAdapter = new AppAdapter(mAppList);
//		// set creator
//		getRefreshView().setMenuCreator(this);
//		setAdapter(mAdapter);
//	}
//
//	@Override
//	public void onHandlerData(Message msg) {
//		super.onHandlerData(msg);
//		switch (msg.what) {
//		case EnumMsgWhat.LOAD_FROM_SQL:
//			setRefreshing();
//			break;
//		case EnumMsgWhat.LOAD_FROM_SQL_COMPLETE:
//			break;
//		case EnumMsgWhat.REFRESH_PULL_DOWN:
//		case EnumMsgWhat.REFRESH_PULL_UP:
//		case EnumMsgWhat.REFRESH_HANDLER:
//			CommonHandler.getInstatnce().getHandler().postDelayed(new Runnable() {
//
//				@Override
//				public void run() {
//					onStopRefresh();
//				}
//			}, 1000);
//			break;
//		}
//	}
//
//	@Override
//	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		CommonToast.show("点击第" + arg3);
//	}
//
//	@Override
//	public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		CommonToast.show("onItemLongClick点击第" + arg3);
//		return false;
//	}
//
//	@Override
//	public void onMenuItemClick(int position, SwipeMenu menu, int index) {
//		// ApplicationInfo item = mAppList.get(position);
//		switch (index) {
//		case 0:
//			// open
//			break;
//		case 1:
//			// delete
//			// delete(item);
//			mAppList.remove(position);
//			mAdapter.notifyDataSetChanged();
//			break;
//		}
//	}
//}

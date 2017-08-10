///**
// * 
// */
//package com.atar.activitys.htmls;
//
//import android.os.Bundle;
//import android.support.v4.view.ViewPager;
//import android.support.v4.view.ViewPager.OnPageChangeListener;
//import android.view.View;
//
//import com.google.gson.Gson;
//import com.taoguba.app.R;
//import com.taoguba.app.appconfig.HtmlsViewPagerJson;
//import com.taoguba.fragment.htmls.TaogubaDynamicFragment;
//import com.taoguba.widgets.PagerSlidingTabStrip;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :Atar
// * @createTime:2017-6-28下午1:38:41
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class WebViewPagerActivity extends TaogubaCommonWebViewPagerActivity implements OnPageChangeListener {
//	private String VIEWPAGER_OPTION_JSON_KEY = "VIEWPAGER_OPTION_JSON_KEY";
//
//	@Override
//	protected void onCreate(Bundle bundle) {
//		super.onCreate(bundle);
//		try {
//			String viewpager_option_json = getIntent().getStringExtra(VIEWPAGER_OPTION_JSON_KEY);
//			if (viewpager_option_json != null && viewpager_option_json.length() > 0) {
//				Gson gson = new Gson();
//				HtmlsViewPagerJson mHtmlsViewPagerJson = gson.fromJson(viewpager_option_json, HtmlsViewPagerJson.class);
//
//				if (mHtmlsViewPagerJson != null) {
//					if (mHtmlsViewPagerJson.getPosition() == 0) {
//						addContentView(R.layout.activity_webview_pager);
//						mMenuAdapter.setWebViewPagerActivityTop(true);
//					} else if (mHtmlsViewPagerJson.getPosition() == 1) {
//						addContentView(R.layout.activity_webview_pager_buttom);
//					}
//					tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
//					mViewPager = (ViewPager) findViewById(R.id.view_pager);
//					initPagerFragmentVualue(mHtmlsViewPagerJson);
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void setOnZoomTextSizeOnDrawerBackEnabled(boolean enable) {
//		super.setOnDrawerBackEnabled(enable);
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.img_common_top_left:// 顶部左边
//		case R.id.txt_common_top_left:
//			try {
//				((TaogubaDynamicFragment) getFragmentList().get(getCurrentItem())).onBackPressed();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			break;
//		case R.id.img_common_top_right:// 顶部右边图片
//		case R.id.txt_common_top_right:// 顶部右边文字
//			try {
//				((TaogubaDynamicFragment) getFragmentList().get(getCurrentItem())).loadWebViewUrl("javascript:onClickTopRight('" + getCurrentItem() + "')");
//			} catch (Exception e) {
//			}
//			break;
//		default:
//			super.onClick(v);
//			break;
//		}
//	}
//
//	@Override
//	public void onPageSelected(int position) {
//		super.onPageSelected(position);
//		setOnDrawerBackEnabled(position == 0);
//	}
//}

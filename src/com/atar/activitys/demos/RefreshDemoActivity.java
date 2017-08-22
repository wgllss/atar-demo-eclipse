/**
 * 
 */
package com.atar.activitys.demos;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-22下午4:14:29
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class RefreshDemoActivity extends AtarCommonActivity {
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addContentView(R.layout.refresh_list_view);
		final PullToRefreshListView mPullToRefreshListView = (PullToRefreshListView) findViewById(R.id.refresh_quick_list_view);
		mPullToRefreshListView.setOnRefreshListener(new OnRefreshListener2<ListView>() {

			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						mPullToRefreshListView.onRefreshComplete();

					}
				}, 1000);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				new Handler().postDelayed(new Runnable() {

					@Override
					public void run() {
						mPullToRefreshListView.onRefreshComplete();
					}
				}, 1000);
			}
		});
	}
}

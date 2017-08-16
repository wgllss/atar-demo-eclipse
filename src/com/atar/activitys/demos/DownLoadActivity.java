/**
 * 
 */
package com.atar.activitys.demos;

import android.os.Bundle;
import android.view.View;

import com.atar.activitys.AtarRefreshScrollViewActivity;
import com.atar.activitys.R;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-16下午5:23:25
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DownLoadActivity extends AtarRefreshScrollViewActivity {

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addScrollView(R.layout.activity_download);
	}

	@Override
	protected void initScrollControl() {
		findViewById(R.id.btn_down).setOnClickListener(this);
		findViewById(R.id.btn_down2).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_down:

			break;
		case R.id.btn_down2:
			break;
		}
	}

}

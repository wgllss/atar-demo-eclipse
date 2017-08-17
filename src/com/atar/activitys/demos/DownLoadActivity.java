/**
 * 
 */
package com.atar.activitys.demos;

import android.download.DownLoadFileUtil;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.utils.ShowLog;
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
	private String TAG = DownLoadActivity.class.getSimpleName();

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
			String strDownloadFileName = "baiduxinwen.apk";
			String strDownloadDir = Environment.getExternalStorageDirectory() + "";
			new DownLoadFileUtil().downFile(this, "http://gdown.baidu.com/data/wisegame/23315dd5f0bbe8ab/baiduxinwen_6402.apk", strDownloadFileName, strDownloadDir);
			break;
		case R.id.btn_down2:
			break;
		}
	}

	@Override
	public void onHandlerData(Message msg) {
		super.onHandlerData(msg);
		switch (msg.what) {
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_FAIL:
			ShowLog.i(TAG, "DOWLOAD_FLAG_FAIL");
			break;
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_SUCCESS:
			ShowLog.i(TAG, "DOWLOAD_FLAG_SUCCESS");
			break;
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_ING:
			ShowLog.i(TAG, "下载进度-->" + msg.obj);
			break;
		default:
			break;
		}
	}

}

/**
 * 
 */
package com.atar.activitys.demos;

import android.download.DownLoadFile;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.utils.DownLoadUtil;
import android.utils.ShowLog;
import android.view.View;
import android.widget.ProgressBar;

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

	private DownLoadFile mDownLoadFile = new DownLoadFile();
	private ProgressBar progressbar;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addScrollView(R.layout.activity_download);
	}

	@Override
	protected void initScrollControl() {
		findViewById(R.id.btn_down).setOnClickListener(this);
		findViewById(R.id.btn_down2).setOnClickListener(this);
		progressbar = (ProgressBar) findViewById(R.id.progressbar);
		findViewById(R.id.btn_down_cancle).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_down:
			String strDownloadFileName = "baiduteiba.apk";
			String strDownloadDir = Environment.getExternalStorageDirectory() + "";
			mDownLoadFile.downLoad("http://gdown.baidu.com/data/wisegame/6e220cc18d807060/shoujibaidu_38032640.apk", strDownloadFileName, strDownloadDir, this);
			// new DownLoadFileUtil().downFile(this, "http://gdown.baidu.com/data/wisegame/23315dd5f0bbe8ab/baiduxinwen_6402.apk", strDownloadFileName, strDownloadDir);
			break;
		case R.id.btn_down2:
			String strDownloadFileName1 = "baiduxinwen.apk";
			String strDownloadDir1 = Environment.getExternalStorageDirectory() + "";
			new DownLoadFile().downLoad("http://gdown.baidu.com/data/wisegame/23315dd5f0bbe8ab/baiduxinwen_6402.apk", strDownloadFileName1, strDownloadDir1, this);

			// new DownLoadUtil().downFile(this, "http://gdown.baidu.com/data/wisegame/6e220cc18d807060/shoujibaidu_38032640.apk", "title", "说明", "tgb", "baiduteiba.apk");
			break;
		case R.id.btn_down_cancle:
			mDownLoadFile.pauseDownload();
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
			progressbar.setProgress(100);
			ShowLog.i(TAG, "DOWLOAD_FLAG_SUCCESS");
			break;
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_ING:
			ShowLog.i(TAG, "下载进度-->" + msg.obj);
			int progress = (Integer) msg.obj;
			progressbar.setProgress(progress);
			break;
		default:
			break;
		}
	}
}

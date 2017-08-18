/**
 * 
 */
package com.atar.activitys.demos;

import android.download.DownLoadFileManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Message;
import android.utils.DownLoadUtil;
import android.utils.ShowLog;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

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

	private ProgressBar progressbar, progressbar2, progressbar3;

	private String strDownloadDir = Environment.getExternalStorageDirectory() + "";

	private String strDownloadFileName1 = "baiduteiba.apk";
	private String strDownloadFileName2 = "baiduxinwen.apk";
	private String strDownloadFileName3 = "baiduxinwen1.apk";

	private String url1 = "http://gdown.baidu.com/data/wisegame/6e220cc18d807060/shoujibaidu_38032640.apk";
	private String url2 = "http://gdown.baidu.com/data/wisegame/23315dd5f0bbe8ab/baiduxinwen_6402.apk";
	private String url3 = "http://gdown.baidu.com/data/wisegame/23315dd5f0bbe8ab/baiduxinwen_6402.apk";

	private TextView txt_percent1, txt_percent2, txt_percent3;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addScrollView(R.layout.activity_download);
	}

	@Override
	protected void initScrollControl() {
		findViewById(R.id.btn_down).setOnClickListener(this);
		findViewById(R.id.btn_down2).setOnClickListener(this);
		findViewById(R.id.btn_down3).setOnClickListener(this);
		findViewById(R.id.btn_down4).setOnClickListener(this);
		progressbar = (ProgressBar) findViewById(R.id.progressbar);
		progressbar2 = (ProgressBar) findViewById(R.id.progressbar2);
		progressbar3 = (ProgressBar) findViewById(R.id.progressbar3);
		findViewById(R.id.btn_down_cancle).setOnClickListener(this);
		findViewById(R.id.btn_down_cancle2).setOnClickListener(this);
		findViewById(R.id.btn_down_cancle3).setOnClickListener(this);

		txt_percent1 = (TextView) findViewById(R.id.txt_percent);
		txt_percent2 = (TextView) findViewById(R.id.txt_percent2);
		txt_percent3 = (TextView) findViewById(R.id.txt_percent3);
	}

	@Override
	protected void initValue() {
		super.initValue();
		DownLoadFileManager.getInstance().initTempFilePercent(0, this, url1, strDownloadFileName1, strDownloadDir);
		DownLoadFileManager.getInstance().initTempFilePercent(1, this, url2, strDownloadFileName2, strDownloadDir);
		DownLoadFileManager.getInstance().initTempFilePercent(2, this, url3, strDownloadFileName3, strDownloadDir);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.btn_down:
			DownLoadFileManager.getInstance().downLoad(this, this, 0, url1, strDownloadFileName1, strDownloadDir);
			break;
		case R.id.btn_down2:
			DownLoadFileManager.getInstance().downLoad(this, this, 1, url2, strDownloadFileName2, strDownloadDir);
			break;
		case R.id.btn_down3:
			DownLoadFileManager.getInstance().downLoad(this, this, 2, url3, strDownloadFileName3, strDownloadDir);
			break;
		case R.id.btn_down_cancle:
			DownLoadFileManager.getInstance().pauseDownload(0);
			break;
		case R.id.btn_down_cancle2:
			DownLoadFileManager.getInstance().pauseDownload(1);
			break;
		case R.id.btn_down_cancle3:
			DownLoadFileManager.getInstance().pauseDownload(2);
			break;
		case R.id.btn_down4:
			new DownLoadUtil().downFile(this, "http://gdown.baidu.com/data/wisegame/6e220cc18d807060/shoujibaidu_38032640.apk", "title", "说明", "tgb", "baiduteiba.apk");
			break;
		}
	}

	@Override
	public void onHandlerData(Message msg) {
		super.onHandlerData(msg);
		switch (msg.what) {
		case 0:
			switch (msg.arg2) {
			case android.download.DownLoadFileBean.DOWLOAD_FLAG_FAIL:
				ShowLog.i(TAG, msg.what + "---" + "下载失败");
				break;
			case android.download.DownLoadFileBean.DOWLOAD_FLAG_SUCCESS:
				progressbar.setProgress(100);
				txt_percent1.setText("100%");
				ShowLog.i(TAG, msg.what + "---" + "DOWLOAD_FLAG_SUCCESS");
				break;
			case android.download.DownLoadFileBean.DOWLOAD_FLAG_ING:
				ShowLog.i(TAG, msg.what + "---" + "下载成功");
				int progress = (Integer) msg.obj;
				progressbar.setProgress(progress);
				txt_percent1.setText(progress + "%");
				break;
			}
			break;
		case 1:
			switch (msg.arg2) {
			case android.download.DownLoadFileBean.DOWLOAD_FLAG_FAIL:
				ShowLog.i(TAG, msg.what + "---" + "下载失败");
				break;
			case android.download.DownLoadFileBean.DOWLOAD_FLAG_SUCCESS:
				progressbar2.setProgress(100);
				txt_percent2.setText("100%");
				ShowLog.i(TAG, msg.what + "---" + "下载成功");
				break;
			case android.download.DownLoadFileBean.DOWLOAD_FLAG_ING:
				ShowLog.i(TAG, "下载进度-->" + msg.what + "---" + msg.obj);
				int progress = (Integer) msg.obj;
				progressbar2.setProgress(progress);
				txt_percent2.setText(progress + "%");
				break;
			}
			break;
		case 2:
			switch (msg.arg2) {
			case android.download.DownLoadFileBean.DOWLOAD_FLAG_FAIL:
				ShowLog.i(TAG, msg.what + "---" + "下载失败");
				break;
			case android.download.DownLoadFileBean.DOWLOAD_FLAG_SUCCESS:
				progressbar3.setProgress(100);
				txt_percent3.setText("100%");
				ShowLog.i(TAG, msg.what + "---" + "下载成功");
				break;
			case android.download.DownLoadFileBean.DOWLOAD_FLAG_ING:
				ShowLog.i(TAG, "下载进度-->" + msg.what + "---" + msg.obj);
				int progress = (Integer) msg.obj;
				progressbar3.setProgress(progress);
				txt_percent3.setText(progress + "%");
				break;
			}
			break;
		}
	}
}

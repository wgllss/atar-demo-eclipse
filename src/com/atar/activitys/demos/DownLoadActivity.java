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
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;

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
	private int[] resProgressBarArrID = { R.id.progressbar, R.id.progressbar2, R.id.progressbar3, R.id.progressbar4, R.id.progressbar5, R.id.progressbar6 };
	private int[] restxt_percentArrID = { R.id.txt_percent, R.id.txt_percent2, R.id.txt_percent3, R.id.txt_percent4, R.id.txt_percent5, R.id.txt_percent6 };

	private ProgressBar[] ProgressBarArr = new ProgressBar[6];
	private TextView[] txt_percentArr = new TextView[6];

	private String strDownloadDir = Environment.getExternalStorageDirectory() + "";

	private String strDownloadFileName1 = "baiduteiba.apk";
	private String strDownloadFileName2 = "baidushoujizushou.apk";
	private String strDownloadFileName3 = "baiduxinwen3.apk";
	private String strDownloadFileName4 = "taoguba.apk";
	private String strDownloadFileName5 = "baiduxinwen5.apk";
	private String strDownloadFileName6 = "baiduxinwen6.apk";

	private String url1 = "http://gdown.baidu.com/data/wisegame/6e220cc18d807060/shoujibaidu_38032640.apk";
	private String url2 = "https://github.com/wgllss/atar-skin/raw/master/download_skin.apk";
	private String url3 = "https://gdown.baidu.com/data/wisegame/23315dd5f0bbe8ab/baiduxinwen_6402.apk";
	private String url4 = "https://m.taoguba.com.cn/downloadApp?channelType=android";

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
		findViewById(R.id.btn_down5).setOnClickListener(this);
		findViewById(R.id.btn_down6).setOnClickListener(this);
		findViewById(R.id.btn_down7).setOnClickListener(this);
		for (int i = 0; i < ProgressBarArr.length; i++) {
			ProgressBarArr[i] = (ProgressBar) findViewById(resProgressBarArrID[i]);
			txt_percentArr[i] = (TextView) findViewById(restxt_percentArrID[i]);
		}
		findViewById(R.id.btn_down_cancle).setOnClickListener(this);
		findViewById(R.id.btn_down_cancle2).setOnClickListener(this);
		findViewById(R.id.btn_down_cancle3).setOnClickListener(this);
		findViewById(R.id.btn_down_cancle4).setOnClickListener(this);
		findViewById(R.id.btn_down_cancle5).setOnClickListener(this);
		findViewById(R.id.btn_down_cancle6).setOnClickListener(this);
	}

	@Override
	protected void initValue() {
		super.initValue();
		setRefreshMode(Mode.DISABLED);

		DownLoadFileManager.getInstance().initTempFilePercent(0, this, url1, strDownloadFileName1, strDownloadDir);
		DownLoadFileManager.getInstance().initTempFilePercent(1, this, url2, strDownloadFileName2, strDownloadDir);
		DownLoadFileManager.getInstance().initTempFilePercent(2, this, url3, strDownloadFileName3, strDownloadDir);
		DownLoadFileManager.getInstance().initTempFilePercent(3, this, url4, strDownloadFileName4, strDownloadDir);
		DownLoadFileManager.getInstance().initTempFilePercent(4, this, url3, strDownloadFileName5, strDownloadDir);
		DownLoadFileManager.getInstance().initTempFilePercent(5, this, url3, strDownloadFileName6, strDownloadDir);
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
		case R.id.btn_down4:
			DownLoadFileManager.getInstance().downLoad(this, this, 3, url4, 2, true, strDownloadFileName4, strDownloadDir);
			break;
		case R.id.btn_down5:
			DownLoadFileManager.getInstance().downLoad(this, this, 4, url3, 3, false, strDownloadFileName5, strDownloadDir);
			break;
		case R.id.btn_down6:
			DownLoadFileManager.getInstance().downLoad(this, this, 5, url3, 4, false, strDownloadFileName6, strDownloadDir);
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
		case R.id.btn_down_cancle4:
			DownLoadFileManager.getInstance().pauseDownload(3);
			break;
		case R.id.btn_down_cancle5:
			DownLoadFileManager.getInstance().pauseDownload(4);
			break;
		case R.id.btn_down_cancle6:
			DownLoadFileManager.getInstance().pauseDownload(5);
			break;
		case R.id.btn_down7:
			new DownLoadUtil().downFile(this, "http://gdown.baidu.com/data/wisegame/6e220cc18d807060/shoujibaidu_38032640.apk", "title", "说明", "atar_", "baiduteiba.apk");
			break;
		}
	}

	@Override
	public void onHandlerData(Message msg) {
		super.onHandlerData(msg);
		switch (msg.what) {
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_FAIL:
			ShowLog.i(TAG, msg.arg2 + "---" + "下载失败");
			break;
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_SUCCESS:
			ProgressBarArr[msg.arg2].setProgress(100);
			txt_percentArr[msg.arg2].setText("100%");
			ShowLog.i(TAG, msg.arg2 + "---" + "下载成功");
			break;
		case android.download.DownLoadFileBean.DOWLOAD_FLAG_ING:
			int progress = (Integer) msg.obj;
			ProgressBarArr[msg.arg2].setProgress(progress);
			txt_percentArr[msg.arg2].setText(progress + "%");
			break;
		}

	}
}

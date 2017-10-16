/**
 * 
 */
package com.atar.widgets;

import java.io.File;
import java.util.Timer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.appconfig.AppConfigSetting;
import android.content.Context;
import android.content.pm.PackageManager;
import android.media.AudioRecord;
import android.os.Handler;
import android.os.Message;
import android.reflection.ThreadPoolTool;
import android.util.AttributeSet;
import android.utils.FileUtils;
import android.view.Gravity;
import android.widget.CommonToast;
import android.widget.ImageView;

import com.atar.globe.GlobeSettings;
import com.ytsoft.cpvs2.common.speex.SpeexRecorder;

/**
 *****************************************************************************************************************************************************************************
 * 按住录音按钮
 * @author :Atar
 * @createTime:2014-9-24下午6:40:36
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint({ "SimpleDateFormat", "HandlerLeak", "ClickableViewAccessibility" })
public class RecordImageView extends ImageView {

	@SuppressWarnings("unused")
	private static final String TAG = RecordImageView.class.getSimpleName();

	/** 录音时间 */
	private int second = 0;
	/** 定时器 */
	private Timer timer;
	/** Speex录音工具 */
	private SpeexRecorder recorderInstance = null;
	/** 录音文件格式 */
	private final String SUFFIX = ".spx";
	/** 录音文件 */
	private File myRecAudioFile;
	/** 缓冲文件 */
	private File myRecAudioDir;
	/** 保存文件路径 */
	private String path;
	/** 录音文件名 */
	private String strVoiceName;
	/** 录音时间 */
	private int strRecordTime;
	/** 录音监听 */
	private OnAudioImageViewStatusListener listener;
	/** 最大录音长度 */
	private int maxVoiceTime = 59;
	/**录音前*/
	public static final int RECORD_PRE = 1;
	/**录音开始*/
	public static final int RECORD_START = 2;
	/**录音中*/
	public static final int RECORD_ING = 3;
	/**录音完成*/
	public static final int RECORD_COMPLETE = 4;
	/**播放开始*/
	public static final int PLAY_START = 5;
	/**播放中*/
	public static final int PLAYING = 6;
	/**播放完成*/
	public static final int PLAY_COMPLETE = 7;
	/**录音状态*/
	public int recordStatus = RECORD_PRE;
	/** 单个线程池开启计时 */
	private ExecutorService exec = Executors.newSingleThreadExecutor();

	public void setOnZhiHuImageViewStatusListener(OnAudioImageViewStatusListener listener) {
		this.listener = listener;
	}

	public RecordImageView(Context context) {
		super(context);
	}

	public RecordImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RecordImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public void setRecordStatus(int recordStatus) {
		this.recordStatus = recordStatus;
	}

	public int getRecordStatus() {
		return recordStatus;
	}

	public void onClickRecord() {
		if (RECORD_PRE == recordStatus) {
			second = 0;
			strRecordTime = 0;
			if (path != null && !"".equals(path)) {
				FileUtils.deleteFile(path);
			}
			recordStart();
		} else if (RECORD_ING == recordStatus) {
			recordStop();
		} else if (RECORD_COMPLETE == recordStatus || PLAY_COMPLETE == recordStatus) {
			second = 0;
			isTimer = true;
			recordStatus = PLAY_START;
			listener.onAudioImageViewStatus(recordStatus, strVoiceName, second, path);
			if (exec != null) {
				exec.execute(mImplTimer);
			}
		} else if (PLAYING == recordStatus) {
			isTimer = false;
			recordStatus = PLAY_COMPLETE;
			listener.onAudioImageViewStatus(recordStatus, strVoiceName, second, path);
		}
	}

	public void setTimer(boolean isTimer) {
		this.isTimer = isTimer;
	}

	/* 主线程中通知UI 正在录音变化 */
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case RECORD_ING:
				if (listener != null && isTimer) {
					listener.onAudioImageViewStatus(recordStatus, strVoiceName, second, path);
				}
				strRecordTime = second;
				if (second > maxVoiceTime) {
					recordStop();
				}
				break;
			case PLAYING:
				if (listener != null && isTimer) {
					listener.onAudioImageViewStatus(recordStatus, strVoiceName, second, path);
				}
				break;
			case AudioRecord.STATE_UNINITIALIZED:
				CommonToast.show("请打开录音权限,你当前录音不可用");
				break;
			default:
				break;
			}
		}
	};

	private boolean isTimer = true;
	private ImplTimer mImplTimer = new ImplTimer();

	class ImplTimer implements Runnable {
		@Override
		public void run() {
			while (isTimer) {
				try {
					Thread.sleep(1000);
					second++;
					handler.sendEmptyMessage(recordStatus);
				} catch (Exception e) {

				}
			}
		}
	}

	/**
	 * 开始录音
	 * @author :Atar
	 * @createTime:2014-9-24下午7:09:59
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	private void recordStart() {
		try {
			isTimer = true;
			if (exec != null) {
				exec.execute(mImplTimer);
			}
			String mMinute1 = System.currentTimeMillis() + "";
			if (mMinute1 != null && mMinute1.length() > 10) {
				mMinute1 = mMinute1.substring(0, 10);
			}
			myRecAudioFile = new File(myRecAudioDir, "a" + AppConfigSetting.getInstance().getUserId() + "_" + mMinute1 + SUFFIX);// a:安卓文件录音文件名
			strVoiceName = myRecAudioFile.getAbsolutePath();
			if (android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED)) {// 有SD卡
				String str = android.os.Environment.getExternalStorageDirectory().toString();
				if (!str.endsWith("/") && !str.endsWith("\\")) {
					str += '/';
				}
				path = str + GlobeSettings.audioDir + strVoiceName;
			} else {
				path = getContext().getFilesDir().getAbsolutePath() + GlobeSettings.audioDir + strVoiceName;
			}
			FileUtils.createDir(path);
			if (checkAudioPermission()) {
				recorderInstance = new SpeexRecorder(path, getContext(), handler);
			} else {
				return;
			}
			ThreadPoolTool.getInstance().execute(recorderInstance);
			if (listener != null) {
				recordStatus = RECORD_START;
				listener.onAudioImageViewStatus(recordStatus, strVoiceName, second, path);
			}
			recorderInstance.setRecording(true);
		} catch (Exception e) {

		}
	}

	/**
	 * 停止录音
	 * @author :Atar
	 * @createTime:2014-9-24下午7:10:16
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public void recordStop() {
		isTimer = false;
		if (recorderInstance != null) {
			recorderInstance.setRecording(false);
		}
		if (timer != null) {
			timer.cancel();
		}
		if (strRecordTime < 1) {
			if (!((Activity) getContext()).isFinishing()) {
				CommonToast.show(getContext(), "时间太短", 3, 4, Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 600, false);
			}
			if (path != null && path.length() > 0) {
				File file = new File(path);
				file.delete();
			}
			if (listener != null) {
				recordStatus = RECORD_PRE;
				listener.onAudioImageViewStatus(recordStatus, strVoiceName, second, path);
			}
			return;
		}
		if (listener != null) {
			recordStatus = RECORD_COMPLETE;
			listener.onAudioImageViewStatus(recordStatus, strVoiceName, second, path);
		}
	}

	/**
	 * 检查录音权限
	 * @author :Atar
	 * @createTime:2014-12-26下午4:50:22
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	private boolean checkAudioPermission() {
		if (getContext() != null) {
			String permName = "android.permission.RECORD_AUDIO";
			String pkgName = getContext().getPackageName();
			// 结果为0则表示使用了该权限，-1则表求没有使用该权限
			int reslut = getContext().getPackageManager().checkPermission(permName, pkgName);
			if (reslut == PackageManager.PERMISSION_GRANTED) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	/**
	 * 直呼录音播放监听
	 * @author :Atar
	 * @createTime:2016-6-13下午2:32:36
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public interface OnAudioImageViewStatusListener {
		/**
		 * 
		 * @author :Atar
		 * @createTime:2016-6-13下午2:33:18
		 * @version:1.0.0
		 * @modifyTime:
		 * @modifyAuthor:
		 * @param recordStatus 当前状态
		 * @param strVoiceName 录音文件名
		 * @param strTime 录音时长
		 * @param filePath 录音本地文件路径
		 * @description:
		 */
		void onAudioImageViewStatus(int recordStatus, String strVoiceName, int strTime, String filePath);
	}
}

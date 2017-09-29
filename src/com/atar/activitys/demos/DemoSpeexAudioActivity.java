/**
 * 
 */
package com.atar.activitys.demos;

import android.common.CommonHandler;
import android.graphics.drawable.AnimationDrawable;
import android.interfaces.HandlerListener;
import android.os.Bundle;
import android.os.Message;
import android.skin.SkinUtils;
import android.utils.FileUtils;
import android.view.View;
import android.widget.TextView;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.atar.widgets.ZhiHuRecordImageView;
import com.atar.widgets.ZhiHuRecordImageView.OnAudioImageViewStatusListener;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-9-29上午11:29:25
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressWarnings("deprecation")
public class DemoSpeexAudioActivity extends AtarCommonActivity implements OnAudioImageViewStatusListener, HandlerListener {
	/**语音时间秒，重录，状态描述*/
	private TextView txt_second, txt_re_record, txt_record_introduce;

	/**录音按钮*/
	private ZhiHuRecordImageView img_record;
	/** 录音本地文件路径 */
	private String strRecordFilePath;
	/** 录音时长 */
	private String strVoiceTime;
	private AnimationDrawable mAnimationDrawable;

	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		addContentView(R.layout.activity_atar_speex_audio);
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (img_record != null) {
			img_record.recordStop();
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (img_record != null) {
			img_record.recordStop();
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (img_record != null) {
			img_record.recordStop();
		}
	}

	@Override
	protected void initControl() {
		super.initControl();
		txt_second = (TextView) findViewById(R.id.txt_second);
		txt_re_record = (TextView) findViewById(R.id.txt_re_record);
		txt_record_introduce = (TextView) findViewById(R.id.txt_record_introduce);
		img_record = (ZhiHuRecordImageView) findViewById(R.id.img_record);
	}

	@Override
	protected void bindEvent() {
		super.bindEvent();
		txt_re_record.setOnClickListener(this);
		img_record.setOnZhiHuImageViewStatusListener(this);
		img_record.setOnClickListener(this);
	}

	@Override
	protected void initValue() {
		super.initValue();
		img_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_zhi_hu_record_pre));
		txt_re_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_oval_grey_c3c3c3));
	}

	@Override
	public void onHandlerData(Message msg) {
		switch (msg.what) {
		case AUDIO_STOP:// 播放完成
			if (img_record != null && (img_record.getRecordStatus() == ZhiHuRecordImageView.PLAYING || img_record.getRecordStatus() == ZhiHuRecordImageView.PLAY_COMPLETE)) {
				img_record.setTimer(false);
				img_record.setRecordStatus(ZhiHuRecordImageView.PLAY_COMPLETE);
				img_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_zhi_hu_play));
				txt_record_introduce.setText("播放试听");
				if (strVoiceTime != null && strVoiceTime.length() > 0) {
					txt_second.setText(strVoiceTime + "\"");
				}
			}
			break;
		}
	}

	@Override
	public void onAudioImageViewStatus(int recordStatus, String strVoiceName, int strTime, String filePath) {
		switch (recordStatus) {
		case ZhiHuRecordImageView.RECORD_PRE:// 录音前
			img_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_zhi_hu_record_pre));
			txt_record_introduce.setText("按住开始录音 最多可录制60\"");
			break;
		case ZhiHuRecordImageView.RECORD_START:// 录音开始
			img_record.setRecordStatus(ZhiHuRecordImageView.RECORD_ING);
			txt_record_introduce.setText("录制中再次点击停止录制");
			img_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_zhi_hu_recording));
			if (mAnimationDrawable == null) {
				mAnimationDrawable = (AnimationDrawable) getResources().getDrawable(R.anim.anim_recording);
			}
			img_record.setImageDrawable(mAnimationDrawable);
			mAnimationDrawable.start();
			txt_second.setText("0\"");
			break;
		case ZhiHuRecordImageView.RECORD_ING:// *录音中
			txt_second.setText(strTime + "\"");
			break;
		case ZhiHuRecordImageView.RECORD_COMPLETE:// 录音完成
			if (mAnimationDrawable != null) {
				mAnimationDrawable.stop();
			}
			img_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_zhi_hu_play));
			txt_re_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_oval_red_zhi_hu));
			strRecordFilePath = filePath;
			strVoiceTime = strTime + "";
			txt_record_introduce.setText("播放试听");
			break;
		case ZhiHuRecordImageView.PLAY_START:// 播放开始
			txt_second.setText("0\"");
			img_record.setRecordStatus(ZhiHuRecordImageView.PLAYING);
			img_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_zhi_hu_stop));
			txt_record_introduce.setText("播放中...");
			txt_re_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_oval_red_zhi_hu));
			stop();
			play(strRecordFilePath, null, img_record, 0, this);
			break;
		case ZhiHuRecordImageView.PLAYING:// 播放中
			txt_second.setText(strTime + "\"");
			break;
		case ZhiHuRecordImageView.PLAY_COMPLETE:// 播放完成
			stop();
			CommonHandler.getInstatnce().handerMessage(this, AUDIO_STOP, 0, 0, null);
			break;
		}
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.txt_re_record:
			if (img_record.getRecordStatus() != ZhiHuRecordImageView.RECORD_ING || img_record.getRecordStatus() != ZhiHuRecordImageView.RECORD_PRE) {
				stop();
				if (strRecordFilePath != null && !"".equals(strRecordFilePath)) {
					FileUtils.deleteFile(strRecordFilePath);
				}
				txt_second.setText("");
				img_record.setTimer(false);
				img_record.setRecordStatus(ZhiHuRecordImageView.RECORD_PRE);
				img_record.setBackgroundDrawable(SkinUtils.getDrawable(this, R.string.drawable_zhi_hu_record_pre));
				txt_record_introduce.setText("点击开始录音 最多可录制60\"");
				SkinUtils.setBackgroundColor(this, R.string.oval_grey_bg, getCurrentSkinType(), txt_re_record);
			}
			break;
		case R.id.img_record:
			if (img_record != null) {
				img_record.onClickRecord();
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		SkinUtils.setTextColor(this, R.string.common_activity_title_color, skinType, txt_re_record);
	}
}

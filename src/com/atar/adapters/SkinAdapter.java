/**
 * 
 */
package com.atar.adapters;

import java.io.File;
import java.util.List;

import android.activity.ActivityManager;
import android.activity.CommonActivity;
import android.adapter.CommonAdapter;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.appconfig.AppConfigSetting;
import android.common.CommonHandler;
import android.content.res.Resources;
import android.download.DownLoadFileManager;
import android.enums.SkinMode;
import android.graphics.Color;
import android.interfaces.HandlerListener;
import android.os.Message;
import android.skin.SkinResourcesManager;
import android.skin.SkinResourcesManager.loadSkinCallBack;
import android.skin.SkinUtils;
import android.utils.FileUtils;
import android.utils.MDPassword;
import android.utils.ShowLog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.activitys.demos.DemoSettingActivity;
import com.atar.beans.DynamicSkinBean;
import com.atar.widgets.DownloadProgressButton;
import com.atar.widgets.DownloadProgressButton.OnDownLoadClickListener;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-10-17下午5:43:50
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("InflateParams")
public class SkinAdapter extends CommonAdapter<DynamicSkinBean> {
	private String TAG = SkinAdapter.class.getSimpleName();

	/**SD卡目录 下载 资源文件 皮肤资源*/
	private String strDownloadDir;

	public SkinAdapter(List<?> list) {
		super(list);
	}

	public void setStrDownloadDir(String strDownloadDir) {
		this.strDownloadDir = strDownloadDir;
	}

	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		final ViewHolder mViewHolder;
		if (convertView == null) {
			mViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_dynamic_item, null);
			mViewHolder.mDownloadProgressButton = (DownloadProgressButton) convertView.findViewById(R.id.down_btn);
			mViewHolder.txt_re_download = (TextView) convertView.findViewById(R.id.txt_re_download);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		SkinUtils.setTextColor(parent.getContext(), R.string.common_activity_title_color, 0, mViewHolder.txt_re_download);
		mViewHolder.mDownloadProgressButton.setTextColor(SkinUtils.getArrayColor(parent.getContext(), R.string.txt_day_black_night_greywhite_color, getSkinType()));
		mViewHolder.mDownloadProgressButton.setTextCoverColor(SkinUtils.getArrayColor(parent.getContext(), R.string.common_activity_title_color, 0));
		mViewHolder.mDownloadProgressButton.setBackgroundSecondColor(SkinUtils.getArrayColor(parent.getContext(), R.string.common_tab_bg_color, getSkinType()));

		final DynamicSkinBean info = getItem(position);
		if (info != null) {
			try {
				mViewHolder.mDownloadProgressButton.setButtonRadius(0);
				mViewHolder.mDownloadProgressButton.setEnablePause(true);
				mViewHolder.mDownloadProgressButton.setBackgroundColor(Color.parseColor(info.getSkinColor()));
				mViewHolder.mDownloadProgressButton.setProgress(info.getProgress());
				mViewHolder.txt_re_download.setBackgroundColor(Color.parseColor(info.getSkinColor()));
			} catch (Exception e) {

			}
			mViewHolder.mDownloadProgressButton.setCurrentText(info.getDownloadName());
			if (info.getProgress() >= 100) {
				mViewHolder.mDownloadProgressButton.finish();
				mViewHolder.mDownloadProgressButton.setCurrentText(info.getLoadName());
				mViewHolder.txt_re_download.setVisibility(View.VISIBLE);
				mViewHolder.txt_re_download.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						File oldDownloadFile = new File(strDownloadDir + MDPassword.getPassword32(info.getSkinName()));
						oldDownloadFile.delete();
						mViewHolder.mDownloadProgressButton.reset();
						mViewHolder.mDownloadProgressButton.setCurrentText(info.getDownloadName());
					}
				});
			} else {
				mViewHolder.txt_re_download.setVisibility(View.GONE);
			}
			mViewHolder.mDownloadProgressButton.setOnDownLoadClickListener(new OnDownLoadClickListener() {

				@Override
				public void clickResume(View v) {
					clickDownload(v);
				}

				@Override
				public void clickPause(View v) {
					DownLoadFileManager.getInstance().pauseDownload(position);
				}

				@Override
				public void clickFinish(View v) {
					final File oldDownloadFile = new File(strDownloadDir + MDPassword.getPassword32("download_skin"));
					oldDownloadFile.deleteOnExit();

					FileUtils.copyFile(strDownloadDir + MDPassword.getPassword32(info.getSkinName()), strDownloadDir + MDPassword.getPassword32("download_skin"));

					SkinResourcesManager.getInstance(parent.getContext()).loadSkinResources(new loadSkinCallBack() {

						@Override
						public void loadSkinSuccess(Resources mResources) {
							if (getSkinType() == SkinMode.NIGHT_MODE) {
								AppConfigSetting.getInstance().putInt(SkinMode.SKIN_MODE_KEY, SkinMode.DAY_MODE);
								CommonHandler.getInstatnce().getHandler().post(new Runnable() {
									@Override
									public void run() {
										((DemoSettingActivity) parent.getContext()).common_ui_switch_button.setChecked(false);
									}
								});
							}
							for (final Activity activity : ActivityManager.getActivityManager().getActivityStack()) {
								if (activity instanceof CommonActivity) {
									CommonHandler.getInstatnce().getHandler().post(new Runnable() {
										@Override
										public void run() {
											((CommonActivity) activity).ChangeSkin(getSkinType());
										}
									});
								}
							}
						}
					});
				}

				@Override
				public void clickDownload(View v) {
					DownLoadFileManager.getInstance().downLoad((Activity) parent.getContext(), mViewHolder.mHandlerListener, position, info.getUrl(), MDPassword.getPassword32(info.getSkinName()),
							strDownloadDir);
				}
			});
		}
		return convertView;
	}

	class ViewHolder {
		TextView txt_re_download;
		DownloadProgressButton mDownloadProgressButton;
		HandlerListener mHandlerListener = new HandlerListener() {
			@Override
			public void onHandlerData(Message msg) {
				switch (msg.what) {
				case android.download.DownLoadFileBean.DOWLOAD_FLAG_FAIL:
					ShowLog.i(TAG, msg.arg2 + "---" + "下载失败");
					break;
				case android.download.DownLoadFileBean.DOWLOAD_FLAG_SUCCESS:
					getList().get(msg.arg2).setProgress(100);
					mDownloadProgressButton.setProgress(100);
					mDownloadProgressButton.finish();
					mDownloadProgressButton.setCurrentText(getList().get(msg.arg2).getLoadName());
					ShowLog.i(TAG, msg.arg2 + "---" + "下载成功");
					break;
				case android.download.DownLoadFileBean.DOWLOAD_FLAG_ING:
					int progress = (Integer) msg.obj;
					mDownloadProgressButton.setProgress(progress);
					break;
				}
			}
		};
	}
}

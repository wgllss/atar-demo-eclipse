/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-3-8下午3:33:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.atar.weex.moudle;

import java.util.List;

import android.activity.ActivityManager;
import android.app.Activity;
import android.appconfig.AppConfigSetting;
import android.application.CommonApplication;
import android.application.CrashHandler;
import android.os.Handler;
import android.os.Looper;
import android.utils.ScreenUtils;
import android.utils.ShowLog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CommonToast;
import android.widget.ListView;
import android.widget.PopupWindow;

import com.atar.activitys.htmls.AtarDynamicWebViewActivity;
import com.atar.activitys.htmls.WebViewPagerActivity;
import com.atar.activitys.htmls.WeexTabHostWithWebViewActivity;
import com.atar.adapters.DropDownAdapter;
import com.atar.beans.PopWindowItemBean;
import com.atar.beans.ShareBean;
import com.atar.common.CommonDialog;
import com.atar.common.CommonDialog.EditFinishListener;
import com.atar.common.CommonLoading;
import com.atar.fragments.htmls.AtarDynamicFragment;
import com.atar.utils.ShareTool;
import com.atar.weex.MainActivity;
import com.atar.widgets.ToastWhthCheck;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.common.WXModuleAnno;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 ***************************************************************************************************************************************************************************** 
 * 
 * @author :fengguangjing
 * @createTime:2017-3-8下午3:33:48
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 ***************************************************************************************************************************************************************************** 
 */
@SuppressWarnings("deprecation")
public class WeexModalUIModule extends WXModule {
	private String TAG = WeexModalUIModule.class.getSimpleName();
	private Handler mHandler = new Handler(Looper.getMainLooper());

	public static final String OK = "OK";
	public static final String CANCEL = "Cancel";
	public static final String RESULT = "result";
	public static final String DATA = "data";
	public static final String MESSAGE = "message";
	public static final String DURATION = "duration";
	public static final String OK_TITLE = "okTitle";
	public static final String CANCEL_TITLE = "cancelTitle";
	public static final String DEFAULT = "default";

	// weex.js上面写法 taost alert 事例
	// weexModalUIModule.alert("title","content",function(options) {
	// weexEventModule.ShowLogI(self.TAG,options.toString());
	// weexModalUIModule.toast(options.toString());
	// weexModule.openUrl(taoguba.getDefaultUrl(name))
	//
	// });
	@WXModuleAnno(runOnUIThread = true)
	public void toast(final String strContent) {
		if (mHandler != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					if (mWXSDKInstance != null && mWXSDKInstance.getContext() != null && mWXSDKInstance.getContext() instanceof Activity) {
						ToastWhthCheck.show((Activity) mWXSDKInstance.getContext(), strContent);
					} else {
						CommonToast.show(strContent);
					}
				}
			});
		}
	}

	@WXModuleAnno(runOnUIThread = true)
	public void toastLong(final String strContent) {
		if (mHandler != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					CommonToast.show(CommonApplication.getContext(), strContent, 2, 4, Gravity.CENTER, 3000, false);
				}
			});
		}
	}

	@WXModuleAnno(runOnUIThread = true)
	public void alert(final String strTitle, final String strContent, final String callbackId) {
		if (mHandler != null && mWXSDKInstance != null && mWXSDKInstance.getContext() != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					CommonDialog.alertDialog(mWXSDKInstance.getContext(), strTitle, strContent, new OnClickListener() {
						@Override
						public void onClick(View v) {
							CommonDialog.dialogDismiss();
							WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, "callbackId-165161615");
						}
					});
				}
			});
		}
	}

	@WXModuleAnno(runOnUIThread = true)
	public void confirm(final String strTitle, final String strContent, final String strOk, final String strCancle, final String callbackId) {
		if (mHandler != null && mWXSDKInstance != null && mWXSDKInstance.getContext() != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					CommonDialog.conformDialog(mWXSDKInstance.getContext(), strTitle, strContent, strOk, strCancle, new OnClickListener() {

						@Override
						public void onClick(View v) {
							CommonDialog.dialogDismiss();
							WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, "ok");

						}
					}, new OnClickListener() {
						@Override
						public void onClick(View v) {
							CommonDialog.dialogDismiss();
							WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, "cancle");
						}
					});
				}
			});
		}
	}

	@WXModuleAnno(runOnUIThread = true)
	public void conformDialogWithEditText(final String strTitle, final String hinttext, final String maxLength, final String callbackId) {
		if (mHandler != null && mWXSDKInstance != null && mWXSDKInstance.getContext() != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					String smaxLength = maxLength;
					if (smaxLength == null || smaxLength.length() == 0) {
						smaxLength = "0";
					}
					CommonDialog.conformDialogWithEditText(mWXSDKInstance.getContext(), strTitle, hinttext, Integer.valueOf(smaxLength), new EditFinishListener() {
						@Override
						public void finish(String strEditContent) {
							WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, strEditContent, true);
						}
					});
				}
			});
		}
	}

	/**
	 * 通用weex端PopupWindow中显示list
	 * @author :Atar
	 * @createTime:2017-4-11下午4:32:20
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param strGravity 位置 0：自定义 xy起始点 1：右上角 2：左上角 3：上边中间 4：右下角 5：左下角 6：中间 7：下中间
	 * @param strX strGravity不为0时偏移量 为0时x起点
	 * @param strY  strGravity不为0时偏移量 为0时y起点
	 * @param spKey 保存当前位置存入key
	 * @param strPopwindowWidth 宽度
	 * @param strTextSize 列表字体大小
	 * @param jsonList 列表数据 weex上如： var json = [{"DropDownItemId":"0","DropDownItemName":"按回帖时间"},{"DropDownItemId":"1","DropDownItemName":"按发帖时间"}]
	 * @param callbackId 回调方法
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = true)
	public void show(final String strGravity, final String strX, final String strY, final String spKey, final String strPopwindowWidth, final String strTextSize, final String jsonList,
			final String callbackId) {
		if (mHandler != null && mWXSDKInstance != null && mWXSDKInstance.getContext() != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					try {
						int gravity = 0, x = 0, y = 0, popwindowWidth = 0, textSize = 0, currentPosition = 0, drawableResID = 0;// ; R.drawable.corners_transparent8;
						if (strGravity != null && strGravity.length() > 0) {
							if ("0".equals(strGravity)) {// 自定义 xy起始点
								gravity = Gravity.NO_GRAVITY;
							} else if ("1".equals(strGravity)) {// 右上角
								gravity = Gravity.TOP | Gravity.RIGHT;
								drawableResID = 0;// R.drawable.drop_r;
							} else if ("2".equals(strGravity)) {// 左上角
								gravity = Gravity.TOP | Gravity.LEFT;
							} else if ("3".equals(strGravity)) {// 上边中间
								gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
							} else if ("4".equals(strGravity)) {// 右下角
								gravity = Gravity.BOTTOM | Gravity.RIGHT;
							} else if ("5".equals(strGravity)) {// 左下角
								gravity = Gravity.BOTTOM | Gravity.LEFT;
							} else if ("6".equals(strGravity)) {// 中间
								gravity = Gravity.CENTER;
							} else if ("7".equals(strGravity)) {// 下中间
								gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
							}
						}
						currentPosition = AppConfigSetting.getInstance().getInt(spKey, 0);
						if (strX != null && strX.length() > 0) {
							x = Integer.valueOf(strX);
							x = (int) ScreenUtils.getIntToDip(x);
						}
						if (strY != null && strY.length() > 0) {
							y = Integer.valueOf(strY);
							y = (int) ScreenUtils.getIntToDip(y);
						}
						if (strPopwindowWidth != null && strPopwindowWidth.length() > 0) {
							popwindowWidth = Integer.valueOf(strPopwindowWidth);
							popwindowWidth = (int) ScreenUtils.getIntToDip(popwindowWidth);
						}
						if (strTextSize != null && strTextSize.length() > 0) {
							textSize = Integer.valueOf(strTextSize);
						}
						List<PopWindowItemBean> mList = null;
						if (jsonList != null) {
							Gson gson = new Gson();
							mList = gson.fromJson(jsonList, new TypeToken<List<PopWindowItemBean>>() {
							}.getType());
						}
						if (mWXSDKInstance.getContext() instanceof MainActivity) {
							LayoutInflater mLayoutInflater = (LayoutInflater) mWXSDKInstance.getContext().getSystemService(mWXSDKInstance.getContext().LAYOUT_INFLATER_SERVICE);
							ViewGroup dropView = null;// (ViewGroup) mLayoutInflater.inflate(R.layout.activity_taoguba_common_title_drop, null, true);
							final DropDownAdapter mDropDownAdapter = new DropDownAdapter(mList);
							mDropDownAdapter.setTxtSize(textSize);
							mDropDownAdapter.setCurrentPostiotn(currentPosition);
							((ListView) dropView).setAdapter(mDropDownAdapter);
							((ListView) dropView).setDividerHeight(0);
							final PopupWindow mPopupWindow = new PopupWindow(dropView, popwindowWidth, ViewGroup.LayoutParams.WRAP_CONTENT, true);
							mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
							mPopupWindow.setBackgroundDrawable(mWXSDKInstance.getContext().getResources().getDrawable(drawableResID));
							mPopupWindow.showAtLocation(((MainActivity) mWXSDKInstance.getContext()).view, gravity, x, y);
							((ListView) dropView).setOnItemClickListener(new OnItemClickListener() {
								@Override
								public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
									AppConfigSetting.getInstance().putInt(spKey, position);
									WXBridgeManager.getInstance().callback(mWXSDKInstance.getInstanceId(), callbackId, mDropDownAdapter.getItem(position).getDropDownItemId());
									mPopupWindow.dismiss();
								}
							});
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	/**
		{
		"shareContent":"淘股吧是中国知名的移动互联投资社交化平台",
		"shareUrl":"http://m.taoguba.com.cn/downloadApp",
		"shareTitle":"v_5.5.5",
		"ShareImgResID":"",
		"ShareImgUrl":"",
		"SHARE_MEDIA":["QZONE","WEIXIN"]
		}
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void Share(final String shareJson) {
		if (mHandler != null && mWXSDKInstance != null && mWXSDKInstance.getContext() != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					try {
						Gson gson = new Gson();
						ShareBean share = gson.fromJson(shareJson, ShareBean.class);
						if (share != null) {
							if (share.getSHARE_MEDIA() != null && share.getSHARE_MEDIA().size() > 0) {
								SHARE_MEDIA[] medias = new SHARE_MEDIA[share.getSHARE_MEDIA().size()];
								for (int i = 0; i < share.getSHARE_MEDIA().size(); i++) {
									String smedia = share.getSHARE_MEDIA().get(i);
									if ("WEIXIN".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.WEIXIN;
									} else if ("WEIXIN_CIRCLE".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.WEIXIN_CIRCLE;
									} else if ("QZONE".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.QZONE;
									} else if ("SINA".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.SINA;
									} else if ("QQ".equalsIgnoreCase(smedia)) {
										medias[i] = SHARE_MEDIA.QQ;
									}
								}
								ShareTool.getInstance().setShare((MainActivity) mWXSDKInstance.getContext(), share.getShareTitle(), share.getShareUrl(), share.getShareContent(),
										share.getShareImgUrl(), medias);
							} else {
								// ShareTool.getInstance().setShare((MainActivity) mWXSDKInstance.getContext(), share.getShareTitle(), share.getShareUrl(), share.getShareContent(), R.drawable.share);
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
	}

	@WXModuleAnno(runOnUIThread = false)
	public void showLoading(final String strContent) {
		if (mHandler != null && mWXSDKInstance != null && mWXSDKInstance.getContext() != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					CommonLoading.showLoading(mWXSDKInstance.getContext(), strContent);
				}
			});
		}
	}

	@WXModuleAnno(runOnUIThread = false)
	public void dissMissLoading() {
		if (mHandler != null && mWXSDKInstance != null && mWXSDKInstance.getContext() != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					CommonLoading.dissMissLoading();
				}
			});
		}
	}

	@WXModuleAnno(runOnUIThread = false)
	public void setWeexTabHostWithWebViewNewInfoNum(final String strposition, final String strnum) {
		if (mHandler != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					try {
						int position = 0;
						int num = 0;
						try {
							if (strposition != null && strposition.length() > 0) {
								position = Integer.valueOf(strposition);
							}
							if (strnum != null && strnum.length() > 0) {
								num = Integer.valueOf(strnum);
							}
						} catch (Exception e) {

						}
						if (ActivityManager.getActivityManager().getActivity(WeexTabHostWithWebViewActivity.class) != null) {
							ActivityManager.getActivityManager().getActivity(WeexTabHostWithWebViewActivity.class).setNewInfoNum(position, num);
						}
					} catch (Exception e) {

					}
				}
			});
		}
	}

	@WXModuleAnno(runOnUIThread = false)
	public void setWeexTabHostWithWebViewsetCurrentTab(final String strposition) {
		if (mHandler != null) {
			mHandler.post(new Runnable() {
				@Override
				public void run() {
					try {
						int position = 0;
						try {
							if (strposition != null && strposition.length() > 0) {
								position = Integer.valueOf(strposition);
							}
						} catch (Exception e) {

						}
						if (ActivityManager.getActivityManager().getActivity(WeexTabHostWithWebViewActivity.class) != null) {
							ActivityManager.getActivityManager().getActivity(WeexTabHostWithWebViewActivity.class).setCurrentTab(position);
						}
					} catch (Exception e) {

					}
				}
			});
		}
	}

	/**
	 * weex 调用 TaogubaDynamicWebViewActivity html中js方法
	 * @author :Atar
	 * @createTime:2017-6-28下午1:43:34
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param strlastPosition 为倒数第几个TaogubaDynamicWebViewActivity（可能开了多个）
	 * @param javascriptUrl
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void callDynamicWebViewctivity(final String strlastPosition, final String javascriptUrl) {
		try {
			if (mHandler != null) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						int lastPosition = 0;
						try {
							lastPosition = Integer.valueOf(strlastPosition);
						} catch (Exception e) {
						}
						if (ActivityManager.getActivityManager().getActivity(AtarDynamicWebViewActivity.class, lastPosition) != null) {
							ActivityManager.getActivityManager().getActivity(AtarDynamicWebViewActivity.class, lastPosition).loadWebViewUrl(javascriptUrl);
						}
					}
				});
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	 *  调用 另一个 WebViewPagerActivity 第几个 html中js方法
	 * @author :Atar
	 * @createTime:2017-6-28下午1:43:34
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param activityName activity名字
	 * @param strlastPosition 为倒数第几个WebViewPagerActivity（可能开了多个）
	 * @param strCurrntPosition 第几个 html
	 * @param javascriptUrl
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void callWebViewPagerActivityFragment(final String activityName, final String strlastPosition, final String strCurrntPosition, final String javascriptUrl) {
		try {
			if (mHandler != null) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						int lastPosition = 0;
						int currntPosition = 0;
						try {
							lastPosition = Integer.valueOf(strlastPosition);
							currntPosition = Integer.valueOf(strCurrntPosition);
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
						try {
							if (activityName.contains("WebViewPagerActivity")) {
								((AtarDynamicFragment) ActivityManager.getActivityManager().getActivity(WebViewPagerActivity.class, lastPosition).getFragmentList().get(currntPosition))
										.loadWebViewUrl(javascriptUrl);
							} else if (activityName.contains("CommunityActivity")) {
								// ((TaogubaDynamicFragment) ActivityManager.getActivityManager().getActivity(CommunityActivity.class, lastPosition).getFragmentList().get(currntPosition))
								// .loadWebViewUrl(javascriptUrl);
							}
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
					}
				});
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}

	/**
	 * 设置小红点数字
	 * @author :Atar
	 * @createTime:2017-7-4上午10:32:53
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param activityName activity名字
	 * @param strlastPosition 倒数第几个WebViewPagerActivity
	 * @param strCurrntPosition 第几个tab下数字
	 * @param strNewNum 数字值
	 * @description:
	 */
	@WXModuleAnno(runOnUIThread = false)
	public void setWebViewPagerActivityNewInfoNum(final String activityName, final String strlastPosition, final String strCurrntPosition, final String strNewNum) {
		try {
			if (mHandler != null) {
				mHandler.post(new Runnable() {
					@Override
					public void run() {
						int lastPosition = 0;
						int currntPosition = 0;
						int num = 0;
						try {
							lastPosition = Integer.valueOf(strlastPosition);
							currntPosition = Integer.valueOf(strCurrntPosition);
							num = Integer.valueOf(strNewNum);
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
						try {
							if (activityName.contains("WebViewPagerActivity")) {
								ActivityManager.getActivityManager().getActivity(WebViewPagerActivity.class, lastPosition).setNewInfoNum(currntPosition, num);
							} else if (activityName.contains("CommunityActivity")) {
								// ActivityManager.getActivityManager().getActivity(CommunityActivity.class, lastPosition).setNewInfoNum(currntPosition, num);
							}
						} catch (Exception e) {
							ShowLog.e(TAG, CrashHandler.crashToString(e));
						}
					}
				});
			}
		} catch (Exception e) {
			ShowLog.e(TAG, CrashHandler.crashToString(e));
		}
	}
}

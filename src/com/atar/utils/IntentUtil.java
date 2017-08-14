/**
 * 
 */
package com.atar.utils;

import android.activity.ActivityManager;
import android.activity.CommonActivity;
import android.app.Activity;
import android.application.CrashHandler;
import android.content.Context;
import android.content.Intent;
import android.utils.ShowLog;

import com.atar.activitys.R;

/**
 *****************************************************************************************************************************************************************************
 * Intent 跳转集合
 * @author :Atar
 * @createTime:2014-7-22上午11:12:50
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class IntentUtil {

	private static String TAG = IntentUtil.class.getSimpleName();

	/**
	 * 跳转到另一个Activity 带有从右向左进入动画，不关闭当前
	 * @author :Atar
	 * @createTime:2014-6-23下午3:23:20
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @param intent
	 * @description:
	 */
	public static void startOtherActivity(Context context, Intent intent) {
		try {
			if (context == null || intent == null) {
				return;
			}
			context.startActivity(intent);
			if (context instanceof Activity) {
				((Activity) context).overridePendingTransition(R.anim.anim_right_in, R.anim.anim_alpha_121);
			}
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 跳转到另一个startActivityForResult 带有从右向左进入动画 不带关闭当前
	 * @author :Atar
	 * @createTime:2014-7-16上午10:52:04
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @param intent
	 * @param requestCode
	 * @description:
	 */
	public static void startOtherActivityForResult(Context context, Intent intent, int requestCode) {
		try {
			if (context == null || intent == null) {
				return;
			}
			((Activity) context).startActivityForResult(intent, requestCode);
			((Activity) context).overridePendingTransition(R.anim.anim_right_in, R.anim.anim_alpha_121);
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 实现Activity 之间跳转没有动画,不关闭当前
	 * @author :Atar
	 * @createTime:2014-6-13下午9:50:31
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @param intent
	 * @description:
	 */
	public static void startActivityWithOutTween(Context context, Intent intent) {
		try {
			if (intent != null && context != null) {
				context.startActivity(intent);
				((Activity) context).overridePendingTransition(R.anim.anim_alpha_121, R.anim.anim_alpha_121);
			}
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 实现Activity 之间跳转渐变动画
	 * @author :Atar
	 * @createTime:2014-6-13下午9:50:31
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @param intent
	 * @description:
	 */
	public static void startActivityAlpha021(Context context, Intent intent) {
		try {
			if (intent != null && context != null) {
				context.startActivity(intent);
				((Activity) context).overridePendingTransition(R.anim.anim_alpha_021, R.anim.anim_alpha_120);
			}
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 实现Activity 之间跳转渐变动画和放大缩小
	 * @author :Atar
	 * @createTime:2014-6-13下午9:50:31
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @param intent
	 * @description:
	 */
	public static void startActivityAlpha021Scale(Context context, Intent intent) {
		try {
			if (intent != null && context != null) {
				context.startActivity(intent);
				((Activity) context).overridePendingTransition(R.anim.anim_alpha_021_scale_to_large, R.anim.anim_alpha_120_scale_to_small);
			}
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 淘股吧订制关闭当前Activity,带有从左向右动画
	 * @author :Atar
	 * @createTime:2014-6-20下午4:14:25
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public static void finish(Activity mActivity) {
		try {
			if (mActivity != null) {
				ActivityManager.getActivityManager().finishActivity(mActivity.getClass());
				mActivity.overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
			}
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 淘股吧订制关闭当前Activity,不带动画
	 * @author :Atar
	 * @createTime:2014-6-20下午4:14:25
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public static void finishWithOutTween(Activity mActivity) {
		try {
			if (mActivity != null) {
				ActivityManager.getActivityManager().finishActivity(mActivity.getClass());
				mActivity.overridePendingTransition(R.anim.anim_alpha_121, R.anim.anim_alpha_121);
			}
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 淘股吧订制关闭当前Activity,带渐变退出动画
	 * @author :Atar
	 * @createTime:2014-6-20下午4:14:25
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public static void finishWitTweenAlpha(Activity mActivity) {
		try {
			if (mActivity != null) {
				ActivityManager.getActivityManager().finishActivity(mActivity.getClass());
				mActivity.overridePendingTransition(R.anim.anim_alpha_121, R.anim.anim_alpha_120);
			}
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 关闭指定的activity
	 * @author :Atar
	 * @createTime:2014-9-26下午5:01:26
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param cls
	 * @description:
	 */
	public static <A extends CommonActivity> void finishActivityOfClass(Class<A> cls) {
		try {
			ActivityManager.getActivityManager().finishActivity(cls);
			A a = ActivityManager.getActivityManager().getActivity(cls);
			if (a != null) {
				a.overridePendingTransition(R.anim.anim_left_in, R.anim.anim_right_out);
			}
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 关闭当前Activity,不带任何动画
	 * @author :Atar
	 * @createTime:2014-6-20下午4:14:25
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public static void finishWhthoutTween(Activity mActivity) {
		try {
			ActivityManager.getActivityManager().finishActivity(mActivity.getClass());
			mActivity.overridePendingTransition(R.anim.anim_alpha_121, R.anim.anim_alpha_121);
		} catch (Exception e) {
			if (e != null) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}
}

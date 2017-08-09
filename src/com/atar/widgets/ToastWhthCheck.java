/**
 * 
 */
package com.atar.widgets;

import android.app.Activity;
import android.utils.CheckPermissionUtils.OnResultListener;
import android.widget.CommonToast;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2015-9-23下午4:14:16
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ToastWhthCheck {

	/**
	 * toast前先检查是否有权限
	 * @author :Atar
	 * @createTime:2015-9-23下午4:20:54
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param activity
	 * @param strContent
	 * @description:
	 */
	public static void show(final Activity activity, String strContent) {
		if (activity == null) {
			return;
		}
		CommonToast.showWhthCheck(activity, strContent, new OnResultListener() {
			@Override
			public void OnResult(int resultOp) {
				if (resultOp != 0) {
					// CommonDialog.conformDialog(activity, "请设置成 显示通知", "", "确定", "取消", new OnClickListener() {
					// @Override
					// public void onClick(View v) {
					// CommonDialog.dialogDismiss();
					// Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
					// Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
					// intent.setData(uri);
					// activity.startActivity(intent);
					// }
					// }, null);
				}
			}
		});
	}
}

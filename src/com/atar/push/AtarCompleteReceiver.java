package com.atar.push;
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :fengguangjing
// * @createTime:2016-4-6下午2:38:15
// * @version:3.13
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//package com.taoguba.push;
//
//import java.io.File;
//
//import android.app.DownloadManager;
//import android.common.AppConfigModel;
//import android.common.ApplicationManagement;
//import android.common.ShowLog;
//import android.content.BroadcastReceiver;
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.os.Environment;
//
//import cn.jpush.android.util.ac;
//
//import com.taoguba.app.activity.BuyWhatActivity;
//import com.taoguba.net.TPYTransferSyncImpl;
//import com.taoguba.utils.DownloadManagerUtils;
//
///**
// ***************************************************************************************************************************************************************************** 
// * 
// * @author :fengguangjing
// * @createTime:2016-4-6下午2:38:15
// * @version:3.13
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// ***************************************************************************************************************************************************************************** 
// */
//public class TaogubaCompleteReceiver extends BroadcastReceiver {
//
//	@Override
//	public void onReceive(Context context, Intent intent) {
//		/**
//		 * get the id of download which have download success, if the id is my
//		 * id and it's status is successful, then install it
//		 **/
//		String action = intent.getAction();
//		if(action!=null){
//			if(action.equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)){
//				ShowLog.i("TaogubaCompleteReceiver", action);
//				long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
//				int downloadId = AppConfigModel.getInstance().getInt(TPYTransferSyncImpl.UrlTpyPackageName, 0);
//				if (completeDownloadId == downloadId) {
//					// if download successful, install apk
//					DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);  
//					DownloadManagerUtils mDownloadManagerUtils = new DownloadManagerUtils(downloadManager);
//					if (mDownloadManagerUtils.getStatusById(downloadId) == DownloadManager.STATUS_SUCCESSFUL) {
//						String apkFilePath = new StringBuilder(Environment.getExternalStorageDirectory().getAbsolutePath()).append(File.separator).append(TPYTransferSyncImpl.TPY_DOWNLOAD_FOLDER_NAME).append(File.separator)
//								.append(TPYTransferSyncImpl.TPY_DOWNLOAD_FILE_NAME).toString();
//						install(context, apkFilePath);
//					}
//				}
//			}
//		}
//		
//	}
//
//	/**
//	 * install app
//	 * 
//	 * @param context
//	 * @param filePath
//	 * @return whether apk exist
//	 */
//	public static boolean install(Context context, String filePath) {
//		Intent i = new Intent(Intent.ACTION_VIEW);
//		File file = new File(filePath);
//		if (file != null && file.length() > 0 && file.exists() && file.isFile()) {
//			i.setDataAndType(Uri.parse("file://" + filePath), "application/vnd.android.package-archive");
//			i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//			context.startActivity(i);
//			return true;
//		}
//		return false;
//	}
//}
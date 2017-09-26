package com.atar.activitys.demos;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.skin.SkinUtils;
import android.utils.FileUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.atar.application.AtarApplication;
import com.atar.globe.GlobeSettings;
import com.atar.photoscan.AlbumAdapter;
import com.atar.photoscan.AlbumBean;
import com.atar.photoscan.AlbumChildrenBean;
import com.atar.photoscan.AlbumContainer;
import com.atar.utils.IntentUtil;
import com.atar.widgets.ToastWhthCheck;

/**
 *****************************************************************************************************************************************************************************
 * 相册页
 * @author :Atar
 * @createTime:2014-9-25下午3:24:27
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("SimpleDateFormat")
public class AlbumActivity extends AtarCommonActivity implements OnItemClickListener, OnClickListener {

	private ListView listView;
	private List<AlbumBean> aibumList = new ArrayList<AlbumBean>();
	private AlbumAdapter mAlbumAdapter = new AlbumAdapter(aibumList);
	public static final int request_select_album = 2011;
	public static final int request_capture_image = 2022;

	private static final String[] STORE_IMAGES = {
	/* 显示名称 */
	MediaStore.Images.Media.DISPLAY_NAME,
	/* 维度 */
	MediaStore.Images.Media.LATITUDE,
	/* 经度 */
	MediaStore.Images.Media.LONGITUDE,
	/* id */
	MediaStore.Images.Media._ID,
	/* dir id 目录 */
	MediaStore.Images.Media.BUCKET_ID,
	/* dir name 目录名字 */
	MediaStore.Images.Media.BUCKET_DISPLAY_NAME,
	/* 文件路径信息 */
	MediaStore.Images.Media.DATA };
	/* 当前模式 */
	private int currentMode;
	private String upLoadImgFilePath = "";
	private int remainingCount;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		addContentView(R.layout.common_listview);
	}

	@Override
	protected void initControl() {
		listView = (ListView) findViewById(R.id.common_normal_lst);
	}

	@Override
	protected void bindEvent() {
		listView.setOnItemClickListener(this);
	}

	@Override
	protected void initValue() {
		// currentMode = getIntent().getIntExtra(GlobeSettings.CURRENT_SEND_MODE_KEY, 0);// (SendMode) getIntent().getSerializableExtra(GlobeSettings.CURRENT_SEND_MODE_KEY);
		remainingCount = getIntent().getIntExtra(GlobeSettings.REMAINING_COUNT_KEY, 0);
		aibumList.clear();
		aibumList.addAll(getPhotoAlbum());
		listView.setAdapter(mAlbumAdapter);
		setActivityTitle("相册");
		setLoadingViewGone();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (request_select_album == requestCode && resultCode == RESULT_OK) {// 选择相册
			Intent intent = new Intent();
			ArrayList<String> list = new ArrayList<String>();
			list.clear();
			for (AlbumChildrenBean mAlbumChildrenBean : AlbumContainer.getInstance().getList()) {
				list.add(mAlbumChildrenBean.getImgPath());
			}
			AlbumContainer.getInstance().clearAll();
			intent.putStringArrayListExtra(GlobeSettings.UPLOAD_LOACL_FILE_PATH_KEY, list);
			setResult(AlbumActivity.RESULT_OK, intent);
			finish();
		} else if (request_capture_image == requestCode && resultCode == RESULT_OK) {// 拍照
			if (upLoadImgFilePath != null && !"".equals(upLoadImgFilePath)) {
				Intent intent = new Intent();
				ArrayList<String> list = new ArrayList<String>();
				list.clear();
				list.add(upLoadImgFilePath);
				intent.putStringArrayListExtra(GlobeSettings.UPLOAD_LOACL_FILE_PATH_KEY, list);
				setResult(AlbumActivity.RESULT_OK, intent);
				finish();
			}
		}
	}

	/**
	 * 获取相册图片
	 * @author :Atar
	 * @createTime:2014-9-25下午6:05:41
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	private List<AlbumBean> getPhotoAlbum() {
		// /* 更新相册 */
		MediaScannerConnection.scanFile(AtarApplication.getApplication(), new String[] { GlobeSettings.UPLOAD_IMAGE_SESSTION_PATH }, new String[] { "image/*" }, null);
		List<AlbumBean> aibumList = new ArrayList<AlbumBean>();
		Cursor cursor = MediaStore.Images.Media.query(getContentResolver(), MediaStore.Images.Media.EXTERNAL_CONTENT_URI, STORE_IMAGES);
		if (cursor != null) {
			Map<String, AlbumBean> countMap = new HashMap<String, AlbumBean>();
			AlbumBean pa = null;
			while (cursor.moveToNext()) {
				String id = cursor.getString(3);
				String dir_id = cursor.getString(4);
				String dir = cursor.getString(5);
				String imgPath = cursor.getString(6);
				if (!countMap.containsKey(dir_id)) {
					pa = new AlbumBean();
					pa.setName(dir);
					pa.setBitmap(Integer.parseInt(id));
					pa.setCount("1");
					pa.setPath(imgPath);
					pa.getBitList().add(new AlbumChildrenBean(Integer.valueOf(id), imgPath));
					countMap.put(dir_id, pa);
				} else {
					pa = countMap.get(dir_id);
					pa.setCount(String.valueOf(Integer.parseInt(pa.getCount()) + 1));
					pa.getBitList().add(new AlbumChildrenBean(Integer.valueOf(id), imgPath));
				}
			}
			cursor.close();
			Iterable<String> it = countMap.keySet();
			for (String key : it) {
				aibumList.add(countMap.get(key));
			}
		}
		return aibumList;
	}

	/**
	 * 跳转到自定义相册页
	 * @author :Atar
	 * @createTime:2014-9-26上午9:32:54
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @description:
	 */
	public static void startAlbumActivityForResult(Context context, int mSendMode, int remainingCount) {
		Intent intent = new Intent(context, AlbumActivity.class);
		// intent.putExtra(GlobeSettings.CURRENT_SEND_MODE_KEY, mSendMode);
		intent.putExtra(GlobeSettings.REMAINING_COUNT_KEY, remainingCount);
		IntentUtil.startOtherActivityForResult(context, intent, GlobeSettings.request_code_select_pic);
	}

	/**
	 * 跳转到自定义相册页
	 * @author :Atar
	 * @createTime:2014-9-26上午9:32:54
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @description:
	 */
	public static void startAlbumActivityForResult(Context context, int mSendMode, int remainingCount, int requestcode) {
		Intent intent = new Intent(context, AlbumActivity.class);
		// intent.putExtra(GlobeSettings.CURRENT_SEND_MODE_KEY, mSendMode);
		intent.putExtra(GlobeSettings.REMAINING_COUNT_KEY, remainingCount);
		IntentUtil.startOtherActivityForResult(context, intent, requestcode);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		AlbumChildrenActivity.startAlbumChildrenActivityForResult(this, aibumList.get(position), currentMode, remainingCount);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.img_common_top_right:// 拍照
			captureImage();
			break;
		default:
			break;
		}
	}

	/**
	 * 拍照
	 * @author :Atar
	 * @createTime:2014-9-29下午3:59:55
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	private void captureImage() {
		String status = Environment.getExternalStorageState();
		if (status.equals(Environment.MEDIA_MOUNTED)) {
			FileUtils.createDir(GlobeSettings.IMG_CAPTURE_PATH);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmssSSS");
			Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
			String fileName = formatter.format(curDate);
			File file = new File(GlobeSettings.IMG_CAPTURE_PATH, fileName + ".jpg");
			if (!file.exists()) {
				try {
					file.createNewFile();
				} catch (Exception e) {
					e.printStackTrace();
					return;
				}
			}
			upLoadImgFilePath = file.getAbsolutePath();
			/* 更新相册 */
			MediaScannerConnection.scanFile(this, new String[] { upLoadImgFilePath }, new String[] { "image/*" }, null);
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
			startActivityForResult(intent, request_capture_image);
		} else {
			ToastWhthCheck.show(this, "没有储存卡");
		}
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		SkinUtils.setImageDrawable(this, R.string.photo, skinType, imgCommonTopRight);
		imgCommonTopRight.setVisibility(View.VISIBLE);
		SkinUtils.setBackgroundColor(this, R.string.item_white_black_bg, skinType, listView);
		SkinUtils.setDivider(this, R.string.txt_line_drawable, skinType, listView);
		if (mAlbumAdapter != null) {
			mAlbumAdapter.setSkinType(skinType);
		}
	}
}

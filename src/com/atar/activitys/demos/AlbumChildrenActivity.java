/**
 * 
 */
package com.atar.activitys.demos;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.utils.DeviceManager;
import android.utils.ScreenUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.atar.activitys.AtarCommonActivity;
import com.atar.activitys.R;
import com.atar.globe.GlobeSettings;
import com.atar.photoscan.AlbumBean;
import com.atar.photoscan.AlbumChildrenAdapter;
import com.atar.photoscan.AlbumContainer;
import com.atar.utils.IntentUtil;
import com.atar.widgets.ToastWhthCheck;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2014-9-26上午9:45:17
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("ClickableViewAccessibility")
public class AlbumChildrenActivity extends AtarCommonActivity implements OnItemClickListener, OnClickListener {
	private static final String AIBUM_LIST_KEY = "AIBUM_LIST_KEY";
	private GridView gridView;
	private AlbumChildrenAdapter mAlbumItemAdapter;
	private AlbumBean mPhotoAlbumBean;
	public static int columnWidth;
	private int NumColumns = 3;
	private HorizontalScrollView mHorizontalScrollView;
	private Button btnComplete;
	private LinearLayout mMovieLayout;
	/* 当前模式 */
	private int currentMode;
	private int remainingCount;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		addContentView(R.layout.activity_album_children);
	}

	@Override
	protected void initControl() {
		gridView = (GridView) findViewById(R.id.common_gridview);
		mHorizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizon_srcoll);
		btnComplete = (Button) findViewById(R.id.btn_complete);
		mMovieLayout = (LinearLayout) findViewById(R.id.movie_layout);
	}

	@Override
	protected void bindEvent() {
		btnComplete.setOnClickListener(this);
		gridView.setOnItemClickListener(this);
		setOnBackFacusView(mHorizontalScrollView);
	}

	@Override
	protected void initValue() {
		columnWidth = (int) (DeviceManager.getScreenWidth(this) - ScreenUtils.getIntToDip(10)) / NumColumns;
		setTopRightText("取消");
		gridView.setColumnWidth(columnWidth);
		gridView.setNumColumns(NumColumns);
		mPhotoAlbumBean = (AlbumBean) getIntent().getSerializableExtra(AIBUM_LIST_KEY);
		// currentMode = getIntent().getIntExtra(GlobeSettings.CURRENT_SEND_MODE_KEY, 0);// (SendMode) getIntent().getSerializableExtra(GlobeSettings.CURRENT_SEND_MODE_KEY);
		remainingCount = getIntent().getIntExtra(GlobeSettings.REMAINING_COUNT_KEY, 0);
		// if (currentMode == SendEditActivity.REPLY_MSG_TEXT_MODE) {
		// mHorizontalScrollView.setVisibility(View.GONE);
		// btnComplete.setVisibility(View.GONE);
		// } else {
		mHorizontalScrollView.setVisibility(View.VISIBLE);
		btnComplete.setVisibility(View.VISIBLE);
		// }
		if (mPhotoAlbumBean != null && mPhotoAlbumBean.getBitList() != null) {
			if (AlbumContainer.getInstance().getList().size() > 0) {
				for (int i = 0; i < mPhotoAlbumBean.getBitList().size(); i++) {
					if (AlbumContainer.getInstance().getList().contains(mPhotoAlbumBean.getBitList().get(i))) {
						mPhotoAlbumBean.getBitList().get(i).setSelect(true);
					}
				}
				setSelectLayout();
			}
			btnComplete.setText("完成(" + AlbumContainer.getInstance().getList().size() + "/" + remainingCount + ")");
			mAlbumItemAdapter = new AlbumChildrenAdapter(mPhotoAlbumBean.getBitList());
			gridView.setAdapter(mAlbumItemAdapter);
		}
		setLoadingViewGone();
	}

	/**
	 * 添加到选中的区域内
	 * @author :Atar
	 * @createTime:2014-9-26下午8:38:12
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	private void setSelectLayout() {
		mMovieLayout.removeAllViews();
		for (int i = 0; i < AlbumContainer.getInstance().getList().size(); i++) {
			ImageView imageview = new ImageView(this);
			int size = (int) ScreenUtils.getIntToDip(68);
			imageview.setLayoutParams(new LinearLayout.LayoutParams(size, LinearLayout.LayoutParams.MATCH_PARENT));
			imageview.setPadding(2, 2, 2, 2);
			imageview.setScaleType(ScaleType.FIT_XY);
			mMovieLayout.addView(imageview);
			LoadImageView("file://" + AlbumContainer.getInstance().getList().get(i).getImgPath(), imageview, 0, 0);
		}
	}

	/**
	 * 跳转到相册具体页
	 * @author :Atar
	 * @createTime:2014-9-26上午10:32:05
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param context
	 * @param mPhotoAlbumBean
	 * @description:
	 */
	public static void startAlbumChildrenActivityForResult(Context context, AlbumBean mPhotoAlbumBean, int mSendMode, int remainingCount) {
		Intent intent = new Intent(context, AlbumChildrenActivity.class);
		// intent.putExtra(GlobeSettings.CURRENT_SEND_MODE_KEY, mSendMode);
		intent.putExtra(AIBUM_LIST_KEY, mPhotoAlbumBean);
		intent.putExtra(GlobeSettings.REMAINING_COUNT_KEY, remainingCount);
		IntentUtil.startOtherActivityForResult(context, intent, AlbumActivity.request_select_album);
	}

	@Override
	public void onClick(View v) {
		super.onClick(v);
		switch (v.getId()) {
		case R.id.txt_common_top_right:
			IntentUtil.finishActivityOfClass(AlbumActivity.class);
			IntentUtil.finish(this);
			break;
		case R.id.btn_complete:
			setResult(RESULT_OK);
			IntentUtil.finish(this);
			break;
		default:
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		if (currentMode == 0) {
			AlbumContainer.getInstance().add(mPhotoAlbumBean.getBitList().get(position));
			setResult(RESULT_OK);
			IntentUtil.finish(this);
		} else {
			try {
				if (mPhotoAlbumBean.getBitList().get(position).isSelect()) {
					mPhotoAlbumBean.getBitList().get(position).setSelect(false);
					AlbumContainer.getInstance().remove(mPhotoAlbumBean.getBitList().get(position));
				} else {
					if (AlbumContainer.getInstance().getList().size() == remainingCount) {
						ToastWhthCheck.show(this, "亲,不能再选了");
						return;
					}
					mPhotoAlbumBean.getBitList().get(position).setSelect(true);
					AlbumContainer.getInstance().add(mPhotoAlbumBean.getBitList().get(position));
				}
				mAlbumItemAdapter.notifyDataSetChanged();
				btnComplete.setText("完成(" + AlbumContainer.getInstance().getList().size() + "/" + remainingCount + ")");
				setSelectLayout();
			} catch (Exception e) {
			}
		}
	}

	@Override
	public void ChangeSkin(int skinType) {
		super.ChangeSkin(skinType);
		// LoadUtil.setTextColor(this, btnComplete, R.array.common_activity_title_color, skinType);
		// LoadUtil.setBackgroundColor(this, btnComplete, R.array.common_top_title_bar_bg_color, skinType);
		// LoadUtil.setBackgroundColor(this, listView, R.array.more_white_black_bg, skinType);
	}
}

package com.atar.photoscan;

import java.util.List;

import android.activity.CommonActivity;
import android.adapter.CommonAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.atar.activitys.R;
import com.atar.activitys.demos.AlbumChildrenActivity;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2014-9-26上午9:56:20
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AlbumChildrenAdapter extends CommonAdapter<AlbumChildrenBean> {

	public AlbumChildrenAdapter(List<?> list) {
		super(list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mViewHolder;
		if (convertView == null) {
			mViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_album_children_item, null);
			mViewHolder.imgPhoto = (ImageView) convertView.findViewById(R.id.img_photo);
			mViewHolder.imgPhoto.setLayoutParams(new FrameLayout.LayoutParams(AlbumChildrenActivity.columnWidth, AlbumChildrenActivity.columnWidth));
			mViewHolder.imgIsCheck = (ImageView) convertView.findViewById(R.id.img_is_check);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}

		AlbumChildrenBean info = getList().get(position);
		if (info != null) {
			// 通过ID 加载缩略图
			// Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(parent.getContext().getContentResolver(), info.getPhotoID(), Thumbnails.MICRO_KIND, null);
			// item.SetBitmap(bitmap);
			if (info.isSelect()) {
				mViewHolder.imgIsCheck.setVisibility(View.VISIBLE);
			} else {
				mViewHolder.imgIsCheck.setVisibility(View.GONE);
			}
			if (info.getImgPath() != null && !"".equals(info.getImgPath())) {
				((CommonActivity) parent.getContext()).LoadImageView("file://" + info.getImgPath(), mViewHolder.imgPhoto, 0, getImageLoadingListener(), 0);
			} else {
				// mViewHolder.imgPhoto.setImageResource(R.drawable.user_icon_60);
			}
		}
		return convertView;
	}

	static class ViewHolder {
		ImageView imgPhoto, imgIsCheck;
	}
}

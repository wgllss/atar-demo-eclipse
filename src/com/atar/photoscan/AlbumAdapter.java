/**
 * 
 */
package com.atar.photoscan;

import java.util.List;

import android.activity.CommonActivity;
import android.adapter.CommonAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atar.activitys.R;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2014-9-25下午6:09:32
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AlbumAdapter extends CommonAdapter<AlbumBean> {

	public AlbumAdapter(List<AlbumBean> list) {
		super(list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mViewHolder;
		if (convertView == null) {
			mViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_album_item, null);
			mViewHolder.imgAlbumFirst = (ImageView) convertView.findViewById(R.id.img_album);
			mViewHolder.txtDesc = (TextView) convertView.findViewById(R.id.txt_desc);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		AlbumBean info = getList().get(position);
		if (info != null) {
			// Bitmap bitmap = MediaStore.Images.Thumbnails.getThumbnail(parent.getContext().getContentResolver(), info.getBitmap(), Thumbnails.MICRO_KIND, null);
			// mViewHolder.imgAlbumFirst.setImageBitmap(bitmap);
			mViewHolder.txtDesc.setText(info.getName() + "  (" + info.getCount() + ")");
			// LoadUtil.setTextColor(parent.getContext(), mViewHolder.txtDesc, R.array.txt_day_grey_night_greyblack_color, getSkinType());
			if (info.getPath() != null && !"".equals(info.getPath())) {
				((CommonActivity) parent.getContext()).LoadImageView("file://" + info.getPath(), mViewHolder.imgAlbumFirst, 0, getImageLoadingListener(), 0);
			} else {
				// mViewHolder.imgAlbumFirst.setImageResource(R.drawable.user_icon_60);
			}
		}

		return convertView;
	}

	static class ViewHolder {
		TextView txtDesc;
		ImageView imgAlbumFirst;
	}
}

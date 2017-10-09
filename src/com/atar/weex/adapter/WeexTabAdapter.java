/**
 * 
 */
package com.atar.weex.adapter;

import java.util.List;

import android.adapter.CommonAdapter;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.skin.SkinUtils;
import android.util.TypedValue;
import android.utils.ScreenUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.config.TabMenuItemBean;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-6-27下午2:55:37
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class WeexTabAdapter extends CommonAdapter<TabMenuItemBean> {

	private int defaultResID = 0;// GlobeSettings.defaultLoadImg9090[getSkinType()];
	private ImageLoader mImageLoader = ImageLoader.getInstance();
	@SuppressWarnings("deprecation")
	private DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(defaultResID).showImageForEmptyUri(defaultResID).showImageOnFail(defaultResID).cacheInMemory().cacheOnDisc()
			.extraForDownloader(1).displayer(new RoundedBitmapDisplayer(0)).bitmapConfig(Bitmap.Config.RGB_565).build();

	public WeexTabAdapter(List<?> list) {
		super(list);
	}

	@SuppressWarnings("deprecation")
	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder mViewHolder = null;
		if (convertView == null) {
			mViewHolder = new ViewHolder();
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_menu_item, null);
			mViewHolder.menu_icon = (ImageView) convertView.findViewById(R.id.menu_icon);
			mViewHolder.menu_title = (TextView) convertView.findViewById(R.id.menu_title);
			mViewHolder.txt_num = (TextView) convertView.findViewById(R.id.txt_num);
			mViewHolder.menu_lineLayout_item = (LinearLayout) convertView.findViewById(R.id.menu_lineLayout_item);
			mViewHolder.img_huo_flag = (ImageView) convertView.findViewById(R.id.img_huo_flag);
			convertView.setTag(mViewHolder);
		} else {
			mViewHolder = (ViewHolder) convertView.getTag();
		}
		final TabMenuItemBean info = getList().get(position);
		if (info != null) {
			mViewHolder.menu_lineLayout_item.setOrientation(info.getOrientation());
			mViewHolder.menu_title.setText(info.getMenuName());
			mViewHolder.menu_title.setTextSize(TypedValue.COMPLEX_UNIT_DIP, info.getMenuNameTextSize());
			// mViewHolder.menu_lineLayout_item lp2 整个item背景属性
			FrameLayout.LayoutParams lp2 = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
			// mViewHolder.menu_title lp 文字属性
			LayoutParams lp = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
			if (info.isShowIcon()) {
				// mViewHolder.menu_icon lp4属性
				int width_height = (int) ScreenUtils.getIntToDip(info.getMenuIconWidthAndHeight());
				LayoutParams lp4 = new LayoutParams(width_height, width_height);
				mViewHolder.menu_icon.setLayoutParams(lp4);
				if (info.getMenuIcon() != null && info.getMenuIcon().length() > 0) {
					if (getCurrentPostiotn() == position) {
						mImageLoader.displayImage(info.getSelectMenuIcon(), mViewHolder.menu_icon, options);
					} else {
						mImageLoader.displayImage(info.getMenuIcon(), mViewHolder.menu_icon, options);
					}
					mViewHolder.menu_icon.setVisibility(View.VISIBLE);
				} else {
					mViewHolder.menu_icon.setVisibility(View.GONE);
				}

				int size = (int) ScreenUtils.getIntToDip(5);
				if (info.getOrientation() == LinearLayout.HORIZONTAL) {
					lp.leftMargin = size;
				} else {
					mViewHolder.menu_lineLayout_item.setPadding(0, size, 0, 0);
					// lp.topMargin = size;
					lp.leftMargin = 0;
				}

				// mViewHolder.txt_num lp3 小红点属性
				FrameLayout.LayoutParams lp3 = new FrameLayout.LayoutParams(3 * size, 3 * size);
				lp3.leftMargin = (int) ScreenUtils.getIntToDip(info.getInfoNumMarginLeft());
				lp3.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
				mViewHolder.txt_num.setLayoutParams(lp3);

				if (getList() != null && getList().size() > 5) {
					lp2.leftMargin = 2 * size;
					lp2.rightMargin = 2 * size;
				}
			} else {
				lp.leftMargin = 0;
				mViewHolder.menu_icon.setVisibility(View.GONE);
				int size = (int) ScreenUtils.getIntToDip(2);
				mViewHolder.menu_title.setPadding(4 * size, 2 * size, 4 * size, 2 * size);
				lp2.leftMargin = 2 * size;
				lp2.rightMargin = 2 * size;
				// mViewHolder.menu_lineLayout_item.setBackgroundDrawable(parent.getContext().getResources().getDrawable(R.drawable.corners_transparent1_radius14));
			}
			mViewHolder.menu_title.setLayoutParams(lp);
			/** 小红点数字 设置 start*/
			if (info.getInfoNum() > 0) {
				if (info.isShowIcon() && info.getOrientation() == LinearLayout.VERTICAL) {
					mViewHolder.menu_lineLayout_item.setPadding(0, (int) ScreenUtils.getIntToDip(5), 0, 0);
				} else {
					lp2.topMargin = (int) ScreenUtils.getIntToDip(3);
				}
				String strliveNum = info.getInfoNum() > 99 ? "99+" : info.getInfoNum() + "";
				mViewHolder.txt_num.setText(strliveNum);
				if (info.getInfoNumSize() > 0) {
					int size5 = (int) ScreenUtils.getIntToDip(info.getInfoNumSize());
					FrameLayout.LayoutParams lp5 = new FrameLayout.LayoutParams(size5, size5);
					lp5.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
					lp5.leftMargin = (int) ScreenUtils.getIntToDip(info.getInfoNumMarginLeft());
					mViewHolder.txt_num.setLayoutParams(lp5);
				}
				try {
					mViewHolder.txt_num.setTextColor(Color.parseColor(info.getInfoNumColor()));
				} catch (Exception e) {

				}
				mViewHolder.txt_num.setVisibility(View.VISIBLE);
			} else {
				mViewHolder.txt_num.setVisibility(View.GONE);
			}
			/** 小红点数字 设置 end*/
			/** 火 字图片 设置 start*/
			if (info.getHuo_flag_icon_url() != null && info.getHuo_flag_icon_url().length() > 0) {
				mImageLoader.displayImage(info.getHuo_flag_icon_url(), mViewHolder.img_huo_flag, options);
				int size = (int) ScreenUtils.getIntToDip(5);
				FrameLayout.LayoutParams lp4 = new FrameLayout.LayoutParams(3 * size, 3 * size);
				lp4.gravity = Gravity.TOP | Gravity.CENTER_HORIZONTAL;
				lp4.leftMargin = (int) ScreenUtils.getIntToDip(info.getHuo_flag_marginLeft());
				lp4.topMargin = (int) ScreenUtils.getIntToDip(info.getHuo_flag_marginTop());
				mViewHolder.img_huo_flag.setLayoutParams(lp4);
				mViewHolder.img_huo_flag.setVisibility(View.VISIBLE);
			} else {
				mViewHolder.img_huo_flag.setVisibility(View.GONE);
			}
			/** 火 字图片 设置 end*/
			mViewHolder.menu_lineLayout_item.setLayoutParams(lp2);
			if (getCurrentPostiotn() == position) {
				SkinUtils.setTextColor(getContext(), R.string.select_tab_txt_color, getSkinType(), mViewHolder.menu_title);
			} else {
				if (info.getMenuNameColor() != null && info.getMenuNameColor().length() > 0 && info.getMenuNameColor().contains(",")) {
					String[] menuNameColor = info.getMenuNameColor().split(",");
					mViewHolder.menu_title.setTextColor(Color.parseColor(menuNameColor[getSkinType()]));
				} else {
					SkinUtils.setTextColor(getContext(), R.string.tab_txt_color, getSkinType(), mViewHolder.menu_title);
				}
			}
		}
		return convertView;
	}

	static class ViewHolder {
		LinearLayout menu_lineLayout_item;
		ImageView menu_icon, img_huo_flag;
		TextView menu_title, txt_num;

	}
}

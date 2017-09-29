package com.atar.adapters;

import java.util.List;

import android.activity.CommonActivity;
import android.adapter.CommonAdapter;
import android.annotation.SuppressLint;
import android.skin.SkinUtils;
import android.utils.CommonStringUtil;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.beans.WonderfulTopicBean;
import com.atar.globe.GlobeSettings;
import com.atar.net.UrlParamCommon;

/**
 ******************************************************************************************
 * WonderfulTopicAdapter:
 * @author: Atar 
 * @createTime:2014年7月21日下午11:55:15
 * @modifyTime:
 * @version: 1.0.0
 * @description:
 ******************************************************************************************
 */
@SuppressLint({ "HandlerLeak", "InflateParams" })
public class WonderfulTopicAdapter extends CommonAdapter<WonderfulTopicBean> {

	public WonderfulTopicAdapter(List<?> list) {
		super(list);
	}

	static class ViewHoldrRecommend {
		TextView title, txt_content, txt_user_name, txt_reply_num, txt_topic_praise;
		ImageView img_user, img_ad;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHoldrRecommend mViewHoldrRecommend;
		if (convertView == null) {
			convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_item3_recommend, null);
			mViewHoldrRecommend = new ViewHoldrRecommend();
			mViewHoldrRecommend.img_ad = (ImageView) convertView.findViewById(R.id.img_ad);
			mViewHoldrRecommend.img_user = (ImageView) convertView.findViewById(R.id.img_user);
			mViewHoldrRecommend.title = (TextView) convertView.findViewById(R.id.title);
			mViewHoldrRecommend.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
			mViewHoldrRecommend.txt_reply_num = (TextView) convertView.findViewById(R.id.txt_reply_num);
			mViewHoldrRecommend.txt_topic_praise = (TextView) convertView.findViewById(R.id.txt_topic_praise);
			mViewHoldrRecommend.txt_user_name = (TextView) convertView.findViewById(R.id.txt_user_name);
			convertView.setTag(mViewHoldrRecommend);
		} else {
			mViewHoldrRecommend = (ViewHoldrRecommend) convertView.getTag();
		}

		final WonderfulTopicBean info = getList().get(position);
		if (info != null) {
			SkinUtils.setTextColor(parent.getContext(), R.string.txt_day_grey_night_greyblack_color, getSkinType(), mViewHoldrRecommend.txt_user_name);
			SkinUtils.setTextColor(parent.getContext(), R.string.txt_day_black_night_greywhite_color, getSkinType(), mViewHoldrRecommend.title);
			SkinUtils.setTextColor(parent.getContext(), R.string.txt_day_grey_night_greyblack_color, getSkinType(), mViewHoldrRecommend.txt_content);
			SkinUtils.setTextColor(parent.getContext(), R.string.txt_day_grey_night_greyblack_color, getSkinType(), mViewHoldrRecommend.txt_reply_num);
			SkinUtils.setTextColor(parent.getContext(), R.string.txt_day_grey_night_greyblack_color, getSkinType(), mViewHoldrRecommend.txt_topic_praise);
			if (info.getPoratrait() != null && !"".equals(info.getPoratrait())) {
				String imgUrl = UrlParamCommon.getPortrait(info.getPoratrait());
				if (imgUrl != null && !"".equals(imgUrl)) {
					((CommonActivity) parent.getContext()).LoadImageView(imgUrl, mViewHoldrRecommend.img_user, 0, getImageLoadingListener(), GlobeSettings.LOAD_IMG_ROUNDE);
				} else {
					// mViewHoldrRecommend.img_user.setImageResource(GlobeSettings.defaultPortrait[getSkinType()]);
				}
			} else {
				// mViewHoldrRecommend.img_user.setImageResource(GlobeSettings.defaultPortrait[getSkinType()]);
			}

			if (info.getImageUrl() != null && info.getImageUrl().length() > 0) {
				((CommonActivity) parent.getContext()).LoadImageView(info.getImageUrl(), mViewHoldrRecommend.img_ad, 0, getImageLoadingListener(), 0);
			} else {
				// mViewHoldrRecommend.img_ad.setImageResource(GlobeSettings.defaultLoadImg9068[getSkinType()]);
			}
			mViewHoldrRecommend.title.setText(CommonStringUtil.emptyIfNull(info.getSubject()));
			String strContent = info.getSubbody();
			if (strContent != null && strContent.length() > 0) {
				// strContent = strContent.length() > 23 ? strContent.substring(0, 23) + "..." : strContent;
				mViewHoldrRecommend.txt_content.setText(CommonStringUtil.emptyIfNull(strContent));
			}
			mViewHoldrRecommend.txt_topic_praise.setText(info.getUsefulNum() + "");
			String strReplyNum = info.getReplyNum() < 10000 ? info.getReplyNum() + "评" : "9999+评";
			mViewHoldrRecommend.txt_reply_num.setText(strReplyNum);
			mViewHoldrRecommend.txt_user_name.setText(info.getUserName());
		}
		return convertView;
	}
}

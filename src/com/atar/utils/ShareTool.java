/**
 * 
 */
package com.atar.utils;

import java.util.Map;

import android.app.Activity;
import android.widget.CommonToast;

import com.umeng.socialize.Config;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.shareboard.SnsPlatform;
import com.umeng.socialize.utils.ShareBoardlistener;

/**
 *****************************************************************************************************************************************************************************
 * 优化友盟分享工具 
 * @author :Atar
 * @createTime:2015-3-20下午4:04:48
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:一健分享到 QQ 微信 新浪微博 腾讯微博 QQ空间 微信朋友圈 设置 分享 标题 ,url,内容 分享图片
 *****************************************************************************************************************************************************************************
 */
public class ShareTool {
	private static ShareTool mShareTool;
	private SHARE_MEDIA platform;
	private UMShareAPI mShareAPI = null;

	public static ShareTool getInstance() {
		if (mShareTool == null) {
			Config.OpenEditor = true;
			// PlatformConfig.setWeixin(GlobeSettings.WeiXin_AppID, GlobeSettings.WeiXin_App_Secret);
			// PlatformConfig.setSinaWeibo(GlobeSettings.LOGIN_SINA_APP_KEY, GlobeSettings.WEIBO_APP_SECRET);
			// PlatformConfig.setQQZone(GlobeSettings.QQ_APP_ID, GlobeSettings.QQ_App_Secret);
			mShareTool = new ShareTool();
		}
		return mShareTool;
	}

	public SHARE_MEDIA getPlatform() {
		return platform;
	}

	/**
	* 设置分享
	* @author :Atar
	* @createTime:2015-3-20下午4:11:04
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param shareTitle
	* @param shareUrl
	* @param shareContent
	* @param ShareImgResID
	* @description:
	*/
	public void setShare(final Activity activity, final String shareTitle, final String shareUrl, final String shareContent, final int ShareImgResID) {
		platform = null;
		new ShareAction(activity).setDisplayList(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA, SHARE_MEDIA.QQ)
				.addButton("umeng_sharebutton_custom", "umeng_sharebutton_custom", "umeng_socialize_tx_on", "umeng_socialize_tx_on").setShareboardclickCallback(new ShareBoardlistener() {
					@Override
					public void onclick(SnsPlatform snsPlatform, SHARE_MEDIA share_media) {
						UMImage image = new UMImage(activity, ShareImgResID);
						if (snsPlatform.mShowWord.equals("umeng_sharebutton_custom")) {// 腾讯微博单独处理
							shareTencentWeibo(activity, shareTitle, shareUrl, shareContent, ShareImgResID, umShareListener);
						} else {
							new ShareAction(activity).setPlatform(share_media).withTitle(shareTitle).withText(shareContent).withMedia(image).withTargetUrl(shareUrl).share();
						}
					}
				}).open();
	}

	/**
	 * 分享到任意平台
	 * @author :Atar
	 * @createTime:2016-9-2下午5:41:12
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param activity
	 * @param shareTitle
	 * @param shareUrl
	 * @param shareContent
	 * @param ShareImgUrl
	 * @param medias
	 * @description:
	 */
	public void setShare(final Activity activity, final String shareTitle, final String shareUrl, final String shareContent, final String ShareImgUrl, SHARE_MEDIA... medias) {
		platform = null;
		new ShareAction(activity).setDisplayList(medias).withTitle(shareTitle).withText(shareContent).withMedia(new UMImage(activity, ShareImgUrl)).withTargetUrl(shareUrl)
				.setCallback(umShareListener).open();
	}

	private UMShareListener umShareListener = new UMShareListener() {
		@Override
		public void onResult(SHARE_MEDIA platform) {
			if (platform.name().equals("WEIXIN_FAVORITE")) {
				// Toast.makeText(ShareActivity.this, platform + " 收藏成功啦", Toast.LENGTH_SHORT).show();
			} else {
				CommonToast.show("分享成功啦");
			}
		}

		@Override
		public void onError(SHARE_MEDIA platform, Throwable t) {
			CommonToast.show("分享失败啦");
		}

		@Override
		public void onCancel(SHARE_MEDIA platform) {
			CommonToast.show("分享取消了");
		}
	};

	/**
	* 分享QQ空间
	* @author :Atar
	* @createTime:2015-12-9上午10:57:28
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param activity
	* @param shareTitle
	* @param shareUrl
	* @param shareContent
	* @param ShareImgResID
	* @param snsPostListener 分响监听
	* @description:
	*/
	public void shareQZone(Activity activity, String shareTitle, String shareUrl, String shareContent, int ShareImgResID, UMShareListener umShareListener) {
		platform = SHARE_MEDIA.QZONE;
		UMImage image = new UMImage(activity, ShareImgResID);
		new ShareAction(activity).setPlatform(platform).setCallback(umShareListener).withTitle(shareTitle).withText(shareContent).withMedia(image).withTargetUrl(shareUrl).share();
	}

	/**
	 * 分享到QQ
	 * @author :Atar
	 * @createTime:2015-12-9上午11:02:34
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param activity
	 * @param shareTitle
	 * @param shareUrl
	 * @param shareContent
	 * @param ShareImgResID
	 * @param snsPostListener
	 * @description:
	 */
	public void shareQQ(Activity activity, String shareTitle, String shareUrl, String shareContent, int ShareImgResID, UMShareListener umShareListener) {
		platform = SHARE_MEDIA.QQ;
		UMImage image = new UMImage(activity, ShareImgResID);
		new ShareAction(activity).setPlatform(platform).setCallback(umShareListener).withTitle(shareTitle).withText(shareContent).withMedia(image).withTargetUrl(shareUrl).share();
		// new ShareAction(activity).setPlatform(SHARE_MEDIA.QQ).setCallback(umShareListener).withTitle(shareTitle).withText(shareContent).withMedia(image).withMedia(music).withTargetUrl(shareUrl)
		// .share();
	}

	/**
	* 分享到微信
	* @author :Atar
	* @createTime:2015-12-9上午11:04:13
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param activity
	* @param shareTitle
	* @param shareUrl
	* @param shareContent
	* @param ShareImgResID
	* @param snsPostListener
	* @description:
	*/
	public void shareWeiXin(Activity activity, String shareTitle, String shareUrl, String shareContent, int ShareImgResID, UMShareListener umShareListener) {
		platform = SHARE_MEDIA.WEIXIN;
		UMImage image = new UMImage(activity, ShareImgResID);
		new ShareAction(activity).setPlatform(platform).setCallback(umShareListener).withTitle(shareTitle).withText(shareContent).withMedia(image).withTargetUrl(shareUrl).share();
	}

	/**
	* 分享到微信朋友圈
	* @author :Atar
	* @createTime:2015-12-9上午11:04:13
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param activity
	* @param shareTitle
	* @param shareUrl
	* @param shareContent
	* @param ShareImgResID
	* @param snsPostListener
	* @description:
	*/
	public void shareWeiXinCircle(Activity activity, String shareTitle, String shareUrl, String shareContent, int ShareImgResID, UMShareListener umShareListener) {
		platform = SHARE_MEDIA.WEIXIN_CIRCLE;
		UMImage image = new UMImage(activity, ShareImgResID);
		new ShareAction(activity).setPlatform(platform).setCallback(umShareListener).withTitle(shareTitle).withText(shareContent).withMedia(image).withTargetUrl(shareUrl).share();
	}

	/**
	* 分享到微信朋友圈
	* @author :Atar
	* @createTime:2015-12-9上午11:04:13
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param activity
	* @param shareTitle
	* @param shareUrl
	* @param shareContent
	* @param ShareImgResID
	* @param snsPostListener
	* @description:
	*/
	public void shareWeiXinCircle(Activity activity, String shareTitle, String shareUrl, String shareContent, String ShareImgUrl, UMShareListener umShareListener) {
		platform = SHARE_MEDIA.WEIXIN_CIRCLE;
		UMImage image = new UMImage(activity, ShareImgUrl);
		new ShareAction(activity).setPlatform(platform).setCallback(umShareListener).withTitle(shareTitle).withText(shareContent).withMedia(image).withTargetUrl(shareUrl).share();
	}

	/**
	* 分享到新浪微博
	* @author :Atar
	* @createTime:2015-12-9上午11:07:59
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param activity
	* @param shareTitle
	* @param shareUrl
	* @param shareContent
	* @param ShareImgResID
	* @param snsPostListener
	* @description:
	*/
	public void shareSina(Activity activity, String shareTitle, String shareUrl, String shareContent, int ShareImgResID, UMShareListener umShareListener) {
		platform = SHARE_MEDIA.SINA;
		UMImage image = new UMImage(activity, ShareImgResID);
		new ShareAction(activity).setPlatform(platform).setCallback(umShareListener).withTitle(shareTitle).withText(shareContent).withMedia(image).withTargetUrl(shareUrl).share();
	}

	/**
	* 分享到腾讯微博
	* @author :Atar
	* @createTime:2015-12-9上午11:08:40
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @param activity
	* @param shareTitle
	* @param shareUrl
	* @param shareContent
	* @param ShareImgResID
	* @param snsPostListener
	* @description:
	*/
	public void shareTencentWeibo(final Activity activity, final String shareTitle, final String shareUrl, final String shareContent, final int ShareImgResID, final UMShareListener umShareListener) {
		if (mShareAPI == null) {
			// PlatformConfig.setQQZone(GlobeSettings.QQ_APP_ID, GlobeSettings.QQ_App_Secret);
			mShareAPI = UMShareAPI.get(activity);
		}
		mShareAPI.doOauthVerify(activity, SHARE_MEDIA.QQ, new UMAuthListener() {
			@Override
			public void onError(SHARE_MEDIA arg0, int arg1, Throwable arg2) {
			}

			@Override
			public void onComplete(SHARE_MEDIA arg0, int arg1, Map<String, String> arg2) {
				platform = SHARE_MEDIA.TENCENT;
				UMImage image = new UMImage(activity, ShareImgResID);
				new ShareAction(activity).setPlatform(platform).setCallback(umShareListener).withTitle(shareTitle).withText(shareContent).withMedia(image).withTargetUrl(shareUrl).share();
			}

			@Override
			public void onCancel(SHARE_MEDIA arg0, int arg1) {

			}
		});
	}
}

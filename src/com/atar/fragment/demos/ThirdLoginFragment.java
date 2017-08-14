package com.atar.fragment.demos;
///**
// * 
// */
//package com.atar.fragments;
//
//import java.util.Map;
//
//import android.activity.ActivityManager;
//import android.appconfig.AppConfigSetting;
//import android.common.CommonStringUtil;
//import android.common.ShowLog;
//import android.content.Intent;
//import android.fragment.CommonFragment;
//import android.interfaces.HandleMessageListener;
//import android.os.Bundle;
//import android.os.Message;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.CommonToast;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.baidu.mobstat.StatService;
//import com.sina.weibo.sdk.auth.AuthInfo;
//import com.sina.weibo.sdk.auth.Oauth2AccessToken;
//import com.sina.weibo.sdk.auth.WeiboAuthListener;
//import com.sina.weibo.sdk.auth.sso.SsoHandler;
//import com.sina.weibo.sdk.exception.WeiboException;
//import com.taoguba.app.R;
//import com.taoguba.app.activity.AddInfoActivity;
//import com.taoguba.app.activity.AuthorizeActivity;
//import com.taoguba.app.activity.CheckMobileActivity;
//import com.taoguba.app.activity.CoolActivity;
//import com.taoguba.app.activity.DynamicActivity;
//import com.taoguba.app.activity.InterestActivity;
//import com.taoguba.app.activity.LoginActivity;
//import com.taoguba.app.activity.RegistActivity;
//import com.taoguba.app.activity.TaogubaCommonActivity;
//import com.taoguba.app.activity.TaogubaMainTabActivity;
//import com.taoguba.app.wxapi.WeiXinJson;
//import com.taoguba.application.TaogubaApplication;
//import com.taoguba.enums.EnumMsgWhat;
//import com.taoguba.globe.GlobeSettings;
//import com.taoguba.modle.LoginJson;
//import com.taoguba.net.NetWorkInterfaces;
//import com.taoguba.push.TaogubaPushTool;
//import com.taoguba.utils.IntentUtil;
//import com.taoguba.utils.LoadUtil;
//import com.tencent.connect.common.Constants;
//import com.tencent.mm.sdk.modelmsg.SendAuth;
//import com.umeng.socialize.PlatformConfig;
//import com.umeng.socialize.UMAuthListener;
//import com.umeng.socialize.UMShareAPI;
//import com.umeng.socialize.bean.SHARE_MEDIA;
//
///**
// *****************************************************************************************************************************************************************************
// * 
// * @author :Atar
// * @createTime:2015-10-19下午4:15:51
// * @version:1.0.0
// * @modifyTime:
// * @modifyAuthor:
// * @description:
// *****************************************************************************************************************************************************************************
// */
//public class ThirdLoginFragment extends CommonFragment implements OnClickListener, HandleMessageListener {
//	// private Handler netWorkHandler = NetWorkHandler.newInstance(this);
//	private ImageView imgSinaLogin, imgWeixinLogin, imgQQlogin;
//	private SsoHandler mSsoHandler;
//	private AuthInfo mAuthInfo;
//	private Oauth2AccessToken mAccessToken;
//	private String token, expires, openId;
//
//	private UMShareAPI mShareAPI = null;
//
//	/* 微信登陆 */
//	private WeiXinJson mWeiXinJson;
//	private String userType;
//	private View view;
//
//	private int current_from_to;
//	private int from;
//	public static final int From_Login = 0;
//	public static final int From_Regist = 1;
//	public static final int From_Author = 2;
//
//	public static ThirdLoginFragment newInstance(int current_from_to, int from) {
//		ThirdLoginFragment fragment = new ThirdLoginFragment();
//		fragment.current_from_to = current_from_to;
//		fragment.from = from;
//		return fragment;
//	}
//
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		view = inflater.inflate(R.layout.fragment_third_login, container, false);
//		imgSinaLogin = (ImageView) view.findViewById(R.id.img_longin_sina);
//		imgWeixinLogin = (ImageView) view.findViewById(R.id.img_login_weixin);
//		imgQQlogin = (ImageView) view.findViewById(R.id.img_login_qq);
//		return view;
//	}
//
//	@Override
//	public void onActivityCreated(Bundle savedInstanceState) {
//		super.onActivityCreated(savedInstanceState);
//		imgSinaLogin.setOnClickListener(this);
//		imgWeixinLogin.setOnClickListener(this);
//		imgQQlogin.setOnClickListener(this);
//	}
//
//	@Override
//	public void onActivityResult(int requestCode, int resultCode, Intent data) {
//		super.onActivityResult(requestCode, resultCode, data);
//		if (mSsoHandler != null) {// 新浪第三方登陆
//			mSsoHandler.authorizeCallBack(requestCode, resultCode, data);
//		}
//		if (mShareAPI != null) {
//			mShareAPI.onActivityResult(requestCode, resultCode, data);
//		}
//	}
//
//	@Override
//	public void onClick(View v) {
//		switch (v.getId()) {
//		case R.id.img_longin_sina:// 新浪第三方登陆
//			if (getActivity() != null) {
//				StatService.onEvent(getActivity().getApplicationContext(), "4005", "登陆-新浪微博", 1);
//				if (from == ThirdLoginFragment.From_Login) {
//					((LoginActivity) getActivity()).setLoading("新浪正在登录");
//				} else if (from == ThirdLoginFragment.From_Regist) {
//					((RegistActivity) getActivity()).setLoading("新浪正在登录");
//				} else if (from == ThirdLoginFragment.From_Author) {
//					((AuthorizeActivity) getActivity()).setLoading("新浪正在登录");
//				}
//			}
//			if (mAuthInfo == null) {
//				mAuthInfo = new AuthInfo(getActivity(), GlobeSettings.LOGIN_SINA_APP_KEY, GlobeSettings.REDIRECT_URL, GlobeSettings.SCOPE);
//			}
//			if (mSsoHandler == null) {
//				mSsoHandler = new SsoHandler(getActivity(), mAuthInfo);
//			}
//			mSsoHandler.authorizeClientSso(new AuthListener());
//			break;
//		case R.id.img_login_qq:// QQ第三方登陆
//			if (getActivity() != null) {
//				StatService.onEvent(getActivity().getApplicationContext(), "4007", "登陆-QQ", 1);
//				if (from == ThirdLoginFragment.From_Login) {
//					((LoginActivity) getActivity()).setLoading("QQ正在登录");
//				} else if (from == ThirdLoginFragment.From_Regist) {
//					((RegistActivity) getActivity()).setLoading("QQ正在登录");
//				} else if (from == ThirdLoginFragment.From_Author) {
//					((AuthorizeActivity) getActivity()).setLoading("QQ正在登录");
//				}
//			}
//			if (mShareAPI == null) {
//				PlatformConfig.setQQZone(GlobeSettings.QQ_APP_ID, GlobeSettings.QQ_App_Secret);
//				mShareAPI = UMShareAPI.get(getActivity());
//			}
//			mShareAPI.doOauthVerify(getActivity(), SHARE_MEDIA.QQ, umAuthListener);
//			break;
//		case R.id.img_login_weixin:// 微信第三方登陆
//			if (getActivity() != null) {
//				StatService.onEvent(getActivity().getApplicationContext(), "4006", "登陆-微信", 1);
//				if (from == ThirdLoginFragment.From_Login) {
//					((LoginActivity) getActivity()).setLoading("微信正在登录");
//				} else if (from == ThirdLoginFragment.From_Regist) {
//					((RegistActivity) getActivity()).setLoading("微信正在登录");
//				} else if (from == ThirdLoginFragment.From_Author) {
//					((AuthorizeActivity) getActivity()).setLoading("微信正在登录");
//				}
//			}
//			SendAuth.Req req = new SendAuth.Req();
//			req.scope = "snsapi_userinfo";
//			req.state = "wechat_sdk_demo_test";
//			TaogubaApplication.getApplication().getWxApi(getActivity()).sendReq(req);
//			break;
//		}
//	}
//
//	@Override
//	public void NetWorkHandlMessage(Message msg) {
//		switch (msg.what) {
//		case EnumMsgWhat.EInterface_Aauth_Submit:// 检查是否授权QQ or 新浪 or 微信
//			if (getActivity() != null) {
//				((TaogubaCommonActivity) getActivity()).setLoadingViewGone();
//			}
//			if (msg.obj != null) {
//				LoginJson mLoginJson = (LoginJson) msg.obj;
//				if (mLoginJson != null) {
//					if (mLoginJson.isStatus(getActivity())) {
//						if (getActivity() != null) {
//							if (from == ThirdLoginFragment.From_Login) {
//								((LoginActivity) getActivity()).setLoadingGone();
//							} else if (from == ThirdLoginFragment.From_Regist) {
//								((RegistActivity) getActivity()).setLoadingGone();
//							} else if (from == ThirdLoginFragment.From_Author) {
//								((AuthorizeActivity) getActivity()).setLoadingGone();
//							}
//						}
//						if (mLoginJson.getDto() != null) {
//							AppConfigSetting.getInstance().saveToken(CommonStringUtil.emptyIfNull(mLoginJson.getDto().getToken()));
//							AppConfigSetting.getInstance().saveLoginUserId(Integer.toString(mLoginJson.getDto().getUserID()));
//							LoadUtil.saveToDB(mLoginJson.getDto().getStockGroup(), CoolActivity.whichPage, "", "");
//							LoadUtil.saveToDB(mLoginJson.getDto().getActionGroup(), DynamicActivity.ColumnNamePageGroup, "", "");
//							AppConfigSetting.getInstance().putString(AppConfigSetting.LOGIN_NAME, CommonStringUtil.emptyIfNull(mLoginJson.getDto().getUserName()));// 保存好用户名，
//							Intent intent = null;
//
//							if (0 == mLoginJson.getDto().getNext()) {// 成功
//								ActivityManager.getActivityManager().popAllActivity();// 退出所有activity
//								AppConfigSetting.getInstance().setIsLogin(true);
//								TaogubaPushTool.getInstance().reSetPushStatus();
//								AppConfigSetting.getInstance().setIsVisitorLogin(false);
//								intent = new Intent(getActivity(), TaogubaMainTabActivity.class);
//							} else if (2 == mLoginJson.getDto().getNext()) {// 激活页面
//								// ActivityManager.getActivityManager().popAllActivity();// 退出所有activity
//								intent = new Intent(getActivity(), CheckMobileActivity.class);
//							} else if (3 == mLoginJson.getDto().getNext() || 4 == mLoginJson.getDto().getNext()) {// 引导
//								InterestActivity.startInterestActivity(getActivity(), InterestActivity.INTEREST_ALL, current_from_to);
//								IntentUtil.taogubaFinish(getActivity());// 结束当前的activity
//								return;
//							} else if (1 == mLoginJson.getDto().getNext()) {// 设置用户名
//								AddInfoActivity.startAddInfoActivity(getActivity(), current_from_to);
//								IntentUtil.taogubaFinish(getActivity());// 结束当前的activity
//								return;
//							} else {
//								return;
//							}
//							AppConfigSetting.getInstance().putString(AppConfigSetting.LOGIN_NAME, mLoginJson.getDto().getUserName());
//							IntentUtil.startActivityWithOutTween(getActivity(), intent);
//							IntentUtil.taogubaFinish(getActivity());// 结束当前的activity
//						}
//					} else {
//						if (3 == mLoginJson.getErrorCode()) {
//							ActivityManager.getActivityManager().popAllActivity();// 退出所有activity
//							if ("S".equals(userType)) {// 新浪 去授权
//								if (mAccessToken != null && mAccessToken.isSessionValid()) {
//									AuthorizeActivity.startAuthorizeActivity(getActivity(), mAccessToken.getToken(), mAccessToken.getUid(), "", userType, current_from_to);
//								}
//							} else if ("Q".equals(userType)) {// QQ 去授权
//								if (token != null && token.length() > 0 && openId != null && openId.length() > 0) {
//									// 去授权
//									AuthorizeActivity.startAuthorizeActivity(getActivity(), token, openId, "", userType, current_from_to);
//								}
//							} else if ("W".equals(userType)) {// 微信 去授权
//								if (mWeiXinJson != null) {
//									AuthorizeActivity
//											.startAuthorizeActivity(getActivity(), mWeiXinJson.getAccess_token(), mWeiXinJson.getOpenid(), mWeiXinJson.getUnionid(), userType, current_from_to);
//								}
//							}
//						}
//						CommonToast.show(mLoginJson.getErrorMessage());
//					}
//				}
//			}
//			break;
//		case EnumMsgWhat.EInterface_Get_Wei_Xin_Token:
//			if (msg.obj != null) {
//				mWeiXinJson = (WeiXinJson) msg.obj;
//				if (mWeiXinJson != null) {
//					String token = mWeiXinJson.getAccess_token();
//					String openID = mWeiXinJson.getOpenid();
//					String expires_in = mWeiXinJson.getExpires_in() + "";
//					String unionid = mWeiXinJson.getUnionid();
//					userType = "W";
//					NetWorkInterfaces.AauthSubmit(getActivity(), ThirdLoginFragment.this, expires_in, openID, token, unionid, userType);
//				}
//			}
//			break;
//		default:
//			break;
//		}
//	}
//
//	/**
//	 * 新浪第三方登陆
//	 * @author :Atar
//	 * @createTime:2014-11-6上午9:40:27
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @description:
//	 */
//	class AuthListener implements WeiboAuthListener {
//		@Override
//		public void onComplete(Bundle values) {
//			mAccessToken = Oauth2AccessToken.parseAccessToken(values);
//			if (mAccessToken != null && mAccessToken.isSessionValid()) {
//				ShowLog.i("TGB", "mAccessToken----->" + mAccessToken.toString());
//				userType = "S";// 检查是否获授权
//				NetWorkInterfaces.AauthSubmit(getActivity(), ThirdLoginFragment.this, "" + mAccessToken.getExpiresTime(), mAccessToken.getUid(), mAccessToken.getToken(), "", userType);
//			} else {
//				String code = values.getString("code");
//				ShowLog.i("TGB", "code----->" + code);
//				CommonToast.show(code);
//			}
//		}
//
//		@Override
//		public void onCancel() {
//		}
//
//		@Override
//		public void onWeiboException(WeiboException e) {
//			CommonToast.show("Auth exception : " + e.getMessage());
//		}
//	}
//
//	private UMAuthListener umAuthListener = new UMAuthListener() {
//		@Override
//		public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//			if (data != null && !data.isEmpty()) {
//				data.get("");
//				token = (String) data.get(Constants.PARAM_ACCESS_TOKEN);
//				expires = (String) data.get(Constants.PARAM_EXPIRES_IN);
//				openId = (String) data.get(Constants.PARAM_OPEN_ID);
//				if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires) && !TextUtils.isEmpty(openId)) {
//					userType = "Q";
//					NetWorkInterfaces.AauthSubmit(getActivity(), ThirdLoginFragment.this, expires, openId, token, "", userType);
//				}
//			}
//
//		}
//
//		@Override
//		public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//		}
//
//		@Override
//		public void onCancel(SHARE_MEDIA platform, int action) {
//		}
//	};
//
//	/**
//	 * 获取微信token
//	 * @author :Atar
//	 * @createTime:2015-9-25下午7:14:46
//	 * @version:1.0.0
//	 * @modifyTime:
//	 * @modifyAuthor:
//	 * @param code
//	 * @description:
//	 */
//	public void setCode(String code) {
//		if (getActivity() != null) {
//			((TaogubaCommonActivity) getActivity()).setLoadingViewVisible();
//		}
//		NetWorkInterfaces.GetWeiXinToken(ThirdLoginFragment.this, GlobeSettings.WeiXin_AppID, GlobeSettings.WeiXin_App_Secret, code);
//	}
//
//	@SuppressWarnings("deprecation")
//	@Override
//	public void OnChangeSkin(int skinType) {
//		super.OnChangeSkin(skinType);
//		if (view != null) {
//			if (from == ThirdLoginFragment.From_Login) {
//				if (getActivity() != null) {
//					((TextView) view.findViewById(R.id.txt_third_login)).setTextColor(getActivity().getResources().getColor(R.color.white));
//					view.findViewById(R.id.txt_third_login).setBackgroundColor(getActivity().getResources().getColor(R.color.transparent0));
//					view.findViewById(R.id.view_line_h).setBackgroundDrawable(getActivity().getResources().getDrawable(R.drawable.login_line));
//					((TextView) view.findViewById(R.id.txt_third_login)).setTextColor(getActivity().getResources().getColor(R.color.white));
//				}
//				imgSinaLogin.setImageResource(R.drawable.weibo02);
//				imgWeixinLogin.setImageResource(R.drawable.weixin02);
//				imgQQlogin.setImageResource(R.drawable.jiujiu02);
//
//				// imgSinaLogin.setImageResource(R.drawable.weibo);
//				// imgWeixinLogin.setImageResource(R.drawable.weixin);
//				// imgQQlogin.setImageResource(R.drawable.jiujiu);
//			} else {
//				LoadUtil.setBackgroundColor(getActivity(), view.findViewById(R.id.view_line_h), R.array.txt_line_color, skinType);
//				LoadUtil.setTextColor(getActivity(), (TextView) view.findViewById(R.id.txt_third_login), R.array.txt_day_grey_night_greyblack_color5, skinType);
//				LoadUtil.setBackgroundColor(getActivity(), view.findViewById(R.id.txt_third_login), R.array.common_content_bg_color, skinType);
//				imgSinaLogin.setImageResource(R.drawable.weibo);
//				imgWeixinLogin.setImageResource(R.drawable.weixin);
//				imgQQlogin.setImageResource(R.drawable.jiujiu);
//			}
//		}
//	}
//}

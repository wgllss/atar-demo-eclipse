/**
 * 
 */
package com.atar.fragment.demos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.reflection.NetWorkMsg;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.utils.ScreenUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atar.activitys.R;
import com.atar.adapters.ImageListAdapter;
import com.atar.adapters.WonderfulTopicAdapter;
import com.atar.beans.WonderfulTopicBean;
import com.atar.enums.EnumMsgWhat;
import com.atar.fragments.AtarRefreshListFragment;
import com.atar.interfaces.ADOnTouchlistener;
import com.atar.interfaces.ADOnTouchlistener.OnADItemClickListener;
import com.atar.modles.WonderfulTopicJson;
import com.atar.net.NetWorkInterfaces;
import com.atar.utils.LoadUtil;
import com.google.gson.reflect.TypeToken;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2015-3-11下午3:42:46
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
@SuppressLint("InflateParams")
public class WonderTopicFragment extends AtarRefreshListFragment implements OnPageChangeListener, OnADItemClickListener {

	private String whichPage = WonderTopicFragment.class.getSimpleName();

	private int currentPager = 1;
	private List<WonderfulTopicBean> listWonderful = new ArrayList<WonderfulTopicBean>();
	public List<WonderfulTopicBean> listWonderfulFirst = new ArrayList<WonderfulTopicBean>();
	private ImageListAdapter mImageListAdapter = new ImageListAdapter(listWonderfulFirst);
	private WonderfulTopicAdapter mWonderfulTopicAdapter = new WonderfulTopicAdapter(listWonderful);
	private int adNum = 3;

	private ViewPager mViewPager;
	private TextView txtAdTitle;
	private LinearLayout mLinearLayout;
	private RelativeLayout relIndicatorLayout;

	/* 单个线程池开启广告切换 */
	private ExecutorService exec = Executors.newSingleThreadExecutor();
	private ImplRounable mImplRounable = new ImplRounable();
	private int currentPosition = 0;// 当前页面的位置
	private boolean isVisibleToUser;

	public static WonderTopicFragment newInstance() {
		WonderTopicFragment fragment = new WonderTopicFragment();
		return fragment;
	}

	@Override
	public void onResume() {
		super.onResume();
		isVisibleToUser = true;
		exec.execute(mImplRounable);
	}

	@Override
	public void onPause() {
		super.onPause();
		isVisibleToUser = false;
	}

	@Override
	public void onStop() {
		super.onStop();
		isVisibleToUser = false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (getActivity() != null) {
			View head = LayoutInflater.from(getActivity()).inflate(R.layout.activity_wonder_topic_head, null);
			mViewPager = (ViewPager) head.findViewById(R.id.app_app_viewPager);
			txtAdTitle = (TextView) head.findViewById(R.id.txt_ad_title);
			mLinearLayout = (LinearLayout) head.findViewById(R.id.ad_indicator);
			relIndicatorLayout = (RelativeLayout) head.findViewById(R.id.rel_indicator_layout);
			if (getRefreshView() != null && getRefreshView().getHeaderViewsCount() == 0) {
				getRefreshView().addHeaderView(head);
			}
			mViewPager.setAdapter(mImageListAdapter);
		}
		setAdapter(mWonderfulTopicAdapter);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setOnTouchListener(new ADOnTouchlistener(getRefreshView(), this));
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			exec.execute(mImplRounable);
		}
	}

	@Override
	public void onHandlerData(Message msg) {
		switch (msg.what) {
		case EnumMsgWhat.LOAD_FROM_SQL:
			LoadUtil.QueryDB(this, whichPage, "", "");
			break;
		case EnumMsgWhat.LOAD_FROM_SQL_COMPLETE:
			List<WonderfulTopicBean> list = LoadUtil.loadFromSqlComelete(msg, new TypeToken<List<WonderfulTopicBean>>() {
			}.getType());
			if (list != null && list.size() > 0) {
				setUsefulAd(list);
			}
			setRefreshing();
			break;
		case EnumMsgWhat.REFRESH_PULL_DOWN:
			currentPager = 1;
			sendEmptyMessage(EnumMsgWhat.REFRESH_HANDLER);
			break;
		case EnumMsgWhat.REFRESH_PULL_UP:
			currentPager++;
			sendEmptyMessage(EnumMsgWhat.REFRESH_HANDLER);
			break;
		case EnumMsgWhat.REFRESH_HANDLER:
			NetWorkInterfaces.GetWonderTopicList(getActivity(), this, Integer.toString(currentPager));
			break;
		case EnumMsgWhat.REFRESH_HANDLER2:// 切换广告
			// if (mViewPager != null)
			// mViewPager.setCurrentItem(currentPosition);
			break;
		default:
			break;
		}
	}

	@Override
	public void NetWorkCall(NetWorkMsg msg) {
		super.NetWorkCall(msg);
		switch (msg.what) {
		case EnumMsgWhat.EInterface_Get_Wonder_Topic_List:
			if (msg.obj != null) {
				WonderfulTopicJson mWonderfulTopicJson = (WonderfulTopicJson) msg.obj;
				if (mWonderfulTopicJson != null) {
					if (mWonderfulTopicJson.isStatus(getActivity())) {
						if (mWonderfulTopicJson.getDto() != null && mWonderfulTopicJson.getDto() != null && mWonderfulTopicJson.getDto().size() > 0) {
							if (isPullDownRefresh()) {
								LoadUtil.saveToDB(mWonderfulTopicJson.getDto(), whichPage, "", "");
							}
							if (currentPager == 1) {// 第一页
								setUsefulAd(mWonderfulTopicJson.getDto());
							} else {
								listWonderful.addAll(mWonderfulTopicJson.getDto());
							}
							mImageListAdapter.notifyDataSetChanged();
							mWonderfulTopicAdapter.notifyDataSetChanged();
						}
					} else {
						// sendEmptyMessage(EnumMsgWhat.REFRESH_COMPLETE);
					}
				} else {
					sendEmptyMessage(EnumMsgWhat.REFRESH_COMPLETE);
				}
			}
			break;
		default:
			break;
		}
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		if (getRefreshView() != null) {
			if (arg0 == 1) {
				getRefreshView().requestDisallowInterceptTouchEvent(true);
			} else {
				getRefreshView().requestDisallowInterceptTouchEvent(false);
			}
		}
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	@Override
	public void onPageSelected(int arg0) {
		getRefreshView().requestDisallowInterceptTouchEvent(true);
		currentPosition = arg0;
		if (listWonderfulFirst != null && listWonderfulFirst.size() > 0) {
			txtAdTitle.setText(listWonderfulFirst.get(arg0).getSubject());
		}
		for (int i = 0; i < mLinearLayout.getChildCount(); i++) {
			// if (arg0 == i) {
			// ((ImageView) mLinearLayout.getChildAt(i)).setImageResource(R.drawable.jinr03);
			// } else {
			// ((ImageView) mLinearLayout.getChildAt(i)).setImageResource(R.drawable.jinr011);
			// }
		}
	}

	@Override
	public void onADItemClick() {
		onItemClickListView(listWonderfulFirst, mViewPager.getCurrentItem());
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		if (arg2 == 0) {
		} else {
			onItemClickListView(listWonderful, (int) arg3);
		}
	}

	class ImplRounable implements Runnable {
		@Override
		public void run() {
			while (isVisibleToUser) {
				try {
					Thread.sleep(3000);
					currentPosition++;
					currentPosition = currentPosition > 2 ? 0 : currentPosition;
					sendMessage(EnumMsgWhat.REFRESH_HANDLER2, currentPosition, 0);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 设置广告
	 * @author :Atar
	 * @createTime:2015-6-18上午9:38:05
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	private void setUsefulAd(List<WonderfulTopicBean> list) {
		mLinearLayout.removeAllViews();
		if (listWonderfulFirst.size() > 0) {
			listWonderfulFirst.clear();
		}
		if (listWonderful.size() > 0) {
			listWonderful.clear();
		}
		for (int i = 0; i < list.size(); i++) {
			if (i < adNum) {
				if (getActivity() != null) {
					ImageView imagerView = new ImageView(getActivity());
					int size = (int) ScreenUtils.getIntToDip(10);
					imagerView.setLayoutParams(new LinearLayout.LayoutParams(size, size));
					imagerView.setPadding(5, 0, 5, 0);
					imagerView.setScaleType(ScaleType.FIT_CENTER);
					// imagerView.setImageResource(R.drawable.jinr011);
					mLinearLayout.addView(imagerView);
					listWonderfulFirst.add(list.get(i));
				}
			} else {
				listWonderful.add(list.get(i));
			}
		}
		relIndicatorLayout.setVisibility(View.VISIBLE);
		mImageListAdapter.notifyDataSetChanged();
		if (listWonderfulFirst.size() > 0) {
			txtAdTitle.setText(listWonderfulFirst.get(0).getSubject());
			// ((ImageView) mLinearLayout.getChildAt(0)).setImageResource(R.drawable.jinr03);
		}
		mWonderfulTopicAdapter.notifyDataSetChanged();
	}

	/**
	 * 公共处理点击方法
	 * @author :Atar
	 * @createTime:2014-9-3下午6:38:33
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param list
	 * @param position
	 * @description:
	 */
	public void onItemClickListView(List<WonderfulTopicBean> list, int position) {
		// if (list.size() > 0 && list.get((int) position) != null) {
		// String topicType = ((WonderfulTopicBean) list.get((int) position)).getType();
		// String linkID = ((WonderfulTopicBean) list.get((int) position)).getLinkID();
		// String userID = Integer.toString(((WonderfulTopicBean) list.get((int) position)).getUserID());
		// if (getActivity() != null) {
		// if ("T".equals(topicType)) {
		// TaogubaTopicActivity.startTaogubaTopicActivity(getActivity(), ((WonderfulTopicBean) list.get((int) position)).getLinkID() + "", "");
		// } else if ("S".equals(topicType)) {
		// // StockActivity.startStockActivity(this, dh, isZiXuanGu);
		// } else if ("W".equals(topicType)) {
		// ShuoWebViewActivity.startShuoWebViewActivity(getActivity(), linkID, userID, false);
		// }
		// }
		// }
	}

	@Override
	public void OnChangeSkin(int skinType) {
		super.OnChangeSkin(skinType);
		// LoadUtil.setBackgroundColor(getActivity(), getRefreshView(), R.array.more_white_black_bg, skinType);
		// LoadUtil.setTextColor(getActivity(), txtAdTitle, R.array.txt_wondderful_ad_title, skinType);
		// if (mWonderfulTopicAdapter != null) {
		// mWonderfulTopicAdapter.setSkinType(skinType);
		// }
	}
}

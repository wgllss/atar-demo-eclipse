/**
 * 
 */
package com.atar.interfaces;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;

/**
 *****************************************************************************************************************************************************************************
 * ViewGroup 如listView 头部放viewPager 广告事件处理公共接口实现
 * @author :Atar
 * @createTime:2015-9-10上午10:13:25
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class ADOnTouchlistener implements OnTouchListener {

	private float mLastMotionX;
	private float mLastMotionY;
	private float dx;
	private float dy;

	private ViewGroup mViewGroup;
	private OnADItemClickListener mADOnItemClickListener;

	public ADOnTouchlistener(ViewGroup mViewGroup, OnADItemClickListener mADOnItemClickListener) {
		this.mViewGroup = mViewGroup;
		this.mADOnItemClickListener = mADOnItemClickListener;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		final float x = event.getX();
		final float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			mLastMotionX = x;
			mLastMotionY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			dx = x - mLastMotionX;
			dy = y - mLastMotionY;
			if (mViewGroup != null) {
				if (Math.abs(dx) < 6 && Math.abs(dy) < 30) {// 广告左右滑动和下拉事件拦截
					mViewGroup.requestDisallowInterceptTouchEvent(false);
				} else {
					if (Math.abs(dx) > Math.abs(dy)) {
						mViewGroup.requestDisallowInterceptTouchEvent(true);
					} else {
						mViewGroup.requestDisallowInterceptTouchEvent(false);
					}
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			if (Math.abs(dx) < 10 && Math.abs(dy) < 10) {// 广告点击事件
				if (mADOnItemClickListener != null) {
					mADOnItemClickListener.onADItemClick();
				}
			}
			dx = 0;
			dy = 0;
			mViewGroup.requestDisallowInterceptTouchEvent(false);
			break;
		case MotionEvent.ACTION_CANCEL:
		case MotionEvent.ACTION_OUTSIDE:
			mViewGroup.requestDisallowInterceptTouchEvent(false);
		default:
			break;
		}
		return false;
	}

	/**
	 * 广告点击事件接口
	 * @author :Atar
	 * @createTime:2015-9-10上午10:20:16
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public interface OnADItemClickListener {

		/**
		 * 点击广告
		 * @author :Atar
		 * @createTime:2015-9-10上午10:21:32
		 * @version:1.0.0
		 * @modifyTime:
		 * @modifyAuthor:
		 * @description:
		 */
		public void onADItemClick();
	}
}

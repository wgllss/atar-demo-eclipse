/**
 * 
 */
package com.atar.config;

import java.util.List;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-6-29上午9:47:55
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class HtmlsViewPagerJson {
	private String TITLE;// actitle 顶部标题
	private String TOP_RIGHT_IMG_URL;// 顶部右边图片URl TOP_RIGHT_IMG_URL 和 TOP_RIGHT_TXT 只能出现一个 不需要可不配置
	private String TOP_RIGHT_TXT;// 顶部图片文字 TOP_RIGHT_IMG_URL 和 TOP_RIGHT_TXT 只能出现一个 不需要可不配置
	private boolean shouldExpand; // PagerSlidingTabStrip 是否均分
	private boolean showDividerLine; // PagerSlidingTabStrip 是否显示中间竖线
	private String IndicatorColor;// PagerSlidingTabStrip 选中线条color颜色 如"#ffffff,#777777" 白天模式 和夜间模式
	private String underlineColor;// PagerSlidingTabStrip 下面底线条颜色 如"#ffffff,#777777" 白天模式 和夜间模式
	private int position;// 位置 0:tab在上方 1：tab在下方
	private List<TabMenuItemBean> listFragment;

	public String getTITLE() {
		return TITLE;
	}

	public String getTOP_RIGHT_IMG_URL() {
		return TOP_RIGHT_IMG_URL;
	}

	public String getTOP_RIGHT_TXT() {
		return TOP_RIGHT_TXT;
	}

	public boolean isShowDividerLine() {
		return showDividerLine;
	}

	public String getIndicatorColor() {
		return IndicatorColor;
	}

	public String getUnderlineColor() {
		return underlineColor;
	}

	public int getPosition() {
		return position;
	}

	public boolean isShouldExpand() {
		return shouldExpand;
	}

	public List<TabMenuItemBean> getListFragment() {
		return listFragment;
	}
}

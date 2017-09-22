/**
 * 
 */
package com.atar.config;

import java.util.List;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-5-23下午4:18:01
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AppConfigJson {
	private String versionName;// 版本号 和apk 的versionName一样的值
	private boolean isReplace = true;// 如果apk新发版本 这个配置也新发配置.txt文件，为true: 老版本要替换该配置.txt, false :老版本不替换该.txt
	private String skinVersion;// 皮肤版本
	private String replaceMinVersion;// 替换皮肤的最小版本

	private FunctionMenu topMenu;// 顶部功能菜单
	private FunctionMenu centerMenu;// 广告下面功能菜单
	private List<String> loading_images;// 开机引道页图片
	private HtmlsViewPagerJson CommunityActivityJson;// 社区配置json
	private String tpyTel;// 太平洋开户联系电话
	private String fmTel;// 福米开户联系电话

	public FunctionMenu getCenterMenu() {
		return centerMenu;
	}

	public FunctionMenu getTopMenu() {
		return topMenu;
	}

	public String getVersionName() {
		return versionName;
	}

	public boolean isReplace() {
		return isReplace;
	}

	public String getSkinVersion() {
		return skinVersion;
	}

	public String getReplaceMinVersion() {
		return replaceMinVersion;
	}

	public List<String> getLoading_images() {
		return loading_images;
	}

	public HtmlsViewPagerJson getCommunityActivityJson() {
		return CommunityActivityJson;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public void setReplace(boolean isReplace) {
		this.isReplace = isReplace;
	}

	public String getTpyTel() {
		return tpyTel;
	}

	public String getFmTel() {
		return fmTel;
	}

	public class FunctionMenu {
		private boolean showDividerLine; // 是否显示中间竖线
		private boolean shouldExpand; // PagerSlidingTabStrip 是否均分
		private List<TabMenuItemBean> menuList; // 一排功能菜单

		public boolean isShouldExpand() {
			return shouldExpand;
		}

		public List<TabMenuItemBean> getMenuList() {
			return menuList;
		}

		public boolean isShowDividerLine() {
			return showDividerLine;
		}
	}
}

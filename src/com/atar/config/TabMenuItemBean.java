/**
 * 
 */
package com.atar.config;

/**
 *****************************************************************************************************************************************************************************
 * 导航所用属性类bean
 * @author :Atar
 * @createTime:2017-5-22下午5:06:20
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 * menuIcon:
    String imageUri = "http://site.com/image.png"; // from Web  
    String imageUri = "file:///mnt/sdcard/image.png"; // from SD card  
    String imageUri = "content://media/external/audio/albumart/13"; // from content provider  
    String imageUri = "assets://image.png"; // from assets  
    String imageUri = "drawable://" + R.drawable.image; // from drawables (only images, non-9patch)  
 *****************************************************************************************************************************************************************************
 */
public class TabMenuItemBean {
	private int ID; // 可不必须 特殊点击事件根据ID可在原生写时必须填

	private String menuName;// 导航名称 必须
	private String menuNameColor;// 导航名称颜色 不需要可以不用配置 如 "#00000000,#ff3e1e"。"白天,间模式" 逗号隔开 不需要可以不用配置

	private boolean isShowIcon;// 是否显示 menuIcon 不需要可以不用配置
	private String menuIcon;// 导航url地址 不需要可以不用配置
	private int menuIconWidthAndHeight;// icon 图片大小 不需要可以不用配置
	private String selectMenuIcon;// 选中导航图片url地址 viewpager 和tabhost用到 首页导航菜单不需要不用配置该字段 不需要可以不用配置

	private int orientation;// LinearLayout.VERTICAL :1 LinearLayout.HORIZONTAL :0 必须

	private int infoNum;// 右上角是否有数字 不需要可以不用配置
	private String infoNumColor;// 右上角小红点数字颜色 可设为透明 or 和 背景同为 红色 就只有小红点 如 ："#00000000" ,"#ff3e1e"
	private int infoNumSize;// 控制红点数字大小 //默认可不配
	private int menuNameTextSize;// 导航名称字体大小 不需要可以不用配置
	private int infoNumMarginLeft;// 调整小红点在 显示图片时才有 距离左边距离 让小红点在图片右上角 默认0 可为正 负数 不需要可以不用配置
	private int infoNumMarginTop;// 调整小红点在 显示图片时才有 距离上边距离 让小红点在图片右上角 默认0 可为正 负数 不需要可以不用配置

	private String huo_flag_icon_url;// 火字图标 assets://images/huo_flag.png //默认assets下文件 可配置线上url 不需要可以不用配置
	private int huo_flag_marginLeft;// 调整huo_flag 图片 距离左边距离 让huo_flag在图片右上角 位置 默认0 可为正 负数 不需要可以不用配置
	private int huo_flag_marginTop;// 调整huo_flag 图片 距离上边距离 让huo_flag在图片右上角 默认0 可为正 负数 不需要可以不用配置
	private int huo_flag_size;// 调整huo_flag 图片 大小 不需要可以不用配置

	private OnClickInfo onClickInfo;// 点击所需要的信息 不需要可以不用配置

	public int getID() {
		return ID;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getInfoNumColor() {
		return infoNumColor;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}

	public String getMenuIcon() {
		return menuIcon;
	}

	public int getOrientation() {
		return orientation;
	}

	public boolean isShowIcon() {
		return isShowIcon;
	}

	public int getInfoNum() {
		return infoNum;
	}

	public void setInfoNum(int infoNum) {
		this.infoNum = infoNum;
	}

	public int getMenuNameTextSize() {
		return menuNameTextSize;
	}

	public int getMenuIconWidthAndHeight() {
		return menuIconWidthAndHeight;
	}

	public int getInfoNumMarginLeft() {
		return infoNumMarginLeft;
	}

	public String getSelectMenuIcon() {
		return selectMenuIcon;
	}

	public String getHuo_flag_icon_url() {
		return huo_flag_icon_url;
	}

	public int getHuo_flag_marginLeft() {
		return huo_flag_marginLeft;
	}

	public int getHuo_flag_marginTop() {
		return huo_flag_marginTop;
	}

	public int getHuo_flag_size() {
		return huo_flag_size;
	}

	public void setHuo_flag_size(int huo_flag_size) {
		this.huo_flag_size = huo_flag_size;
	}

	public String getMenuNameColor() {
		return menuNameColor;
	}

	public int getInfoNumSize() {
		return infoNumSize;
	}

	public int getInfoNumMarginTop() {
		return infoNumMarginTop;
	}

	public OnClickInfo getOnClickInfo() {
		return onClickInfo;
	}

	public void setOnClickInfo(OnClickInfo onClickInfo) {
		this.onClickInfo = onClickInfo;
	}
}

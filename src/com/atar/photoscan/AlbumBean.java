package com.atar.photoscan;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AlbumBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name; // 相册名字
	private String count; // 数量
	private int bitmap; // 相册第一张图片
	private String path;// 相册路径
	private List<AlbumChildrenBean> bitList = new ArrayList<AlbumChildrenBean>();// 子目录所有图片

	public List<AlbumChildrenBean> getBitList() {
		return bitList;
	}

	public void setBitList(List<AlbumChildrenBean> bitList) {
		this.bitList = bitList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public int getBitmap() {
		return bitmap;
	}

	public void setBitmap(int bitmap) {
		this.bitmap = bitmap;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}

package com.atar.photoscan;

import java.io.Serializable;

/**
 * 
 *****************************************************************************************************************************************************************************
 * 相册了目录信息
 * @author :Atar
 * @createTime:2014-9-26下午3:06:04
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AlbumChildrenBean implements Serializable {
	private static final long serialVersionUID = 8682674788506891598L;
	private int photoID;
	private String imgPath;
	private boolean select;

	public AlbumChildrenBean(int id, String imgPath) {
		photoID = id;
		this.imgPath = imgPath;
		select = false;
	}

	public AlbumChildrenBean(int id, boolean flag, String imgPath) {
		photoID = id;
		select = flag;
		this.imgPath = imgPath;
	}

	public int getPhotoID() {
		return photoID;
	}

	public void setPhotoID(int photoID) {
		this.photoID = photoID;
	}

	public boolean isSelect() {
		return select;
	}

	public void setSelect(boolean select) {
		this.select = select;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof AlbumChildrenBean) {
			AlbumChildrenBean p = (AlbumChildrenBean) obj;
			return this.photoID == p.photoID;
		}
		return super.equals(obj);
	}
}

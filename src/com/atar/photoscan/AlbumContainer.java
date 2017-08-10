/**
 * 
 */
package com.atar.photoscan;

import java.util.ArrayList;
import java.util.List;

/**
 *****************************************************************************************************************************************************************************
 * 选择相册临时变动数据
 * @author :Atar
 * @createTime:2014-9-26下午4:15:26
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class AlbumContainer {
	private static AlbumContainer mInstance;
	private List<AlbumChildrenBean> list = new ArrayList<AlbumChildrenBean>();

	/**
	 * 锁定单例模式
	 * @author :Atar
	 * @createTime:2014-9-26下午4:27:37
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @return
	 * @description:
	 */
	public static synchronized AlbumContainer getInstance() {
		if (mInstance == null)
			mInstance = new AlbumContainer();
		return mInstance;
	}

	/**
	 * 添加到临时缓存
	 * @author :Atar
	 * @createTime:2014-9-26下午4:26:38
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mAlbumChildrenBean
	 * @description:
	 */
	public void add(AlbumChildrenBean mAlbumChildrenBean) {
		if (mAlbumChildrenBean != null) {
			if (!list.contains(mAlbumChildrenBean)) {
				list.add(mAlbumChildrenBean);
			}
		}
	}

	/**
	 * 移除该对象
	 * @author :Atar
	 * @createTime:2014-9-26下午4:27:00
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param mAlbumChildrenBean
	 * @description:
	 */
	public void remove(AlbumChildrenBean mAlbumChildrenBean) {
		if (mAlbumChildrenBean != null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).equals(mAlbumChildrenBean)) {
					list.remove(i);
					break;
				}
			}
		}
	}

	/**
	 * 清除所有
	 * @author :Atar
	 * @createTime:2014-9-26下午4:40:57
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public void clearAll() {
		list.clear();
	}

	public List<AlbumChildrenBean> getList() {
		return list;
	}
}

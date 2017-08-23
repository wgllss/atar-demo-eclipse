/**
 * 
 */
package com.atar.adapters;

import java.util.List;

import android.adapter.CommonAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :Atar
 * @createTime:2017-8-23上午10:05:08
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DemoAdapter extends CommonAdapter<String> {

	public DemoAdapter(List<?> list) {
		super(list);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return convertView;
	}

}

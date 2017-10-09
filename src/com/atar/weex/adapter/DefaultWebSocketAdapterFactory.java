/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :fengguangjing
 * @createTime:2017-4-25上午10:19:04
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
package com.atar.weex.adapter;

import com.taobao.weex.appfram.websocket.IWebSocketAdapter;
import com.taobao.weex.appfram.websocket.IWebSocketAdapterFactory;

/**
 *****************************************************************************************************************************************************************************
 * 
 * @author :
 * @createTime:2017-4-25上午10:19:04
 * @version:4.2.4
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class DefaultWebSocketAdapterFactory   implements IWebSocketAdapterFactory
{
	  public IWebSocketAdapter createWebSocketAdapter()
	  {
	    return new DefaultWebSocketAdapter();
	  }
	}
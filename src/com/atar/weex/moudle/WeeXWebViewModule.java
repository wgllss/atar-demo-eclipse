/**
 */
package com.atar.weex.moudle;

import com.taobao.weex.WXSDKManager;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.WXComponent;

public class WeeXWebViewModule extends WXModule {

	private enum Action {
		reload, goBack, goForward, callnativeh5
	}

	@JSMethod(uiThread = true)
	public void goBack(String ref) {
		action(Action.goBack, ref, "");
	}

	@JSMethod(uiThread = true)
	public void goForward(String ref) {
		action(Action.goForward, ref, "");
	}

	@JSMethod(uiThread = true)
	public void reload(String ref) {
		action(Action.reload, ref, "");
	}

	@JSMethod(uiThread = true)
	public void callnativeh5(String ref, String json) {
		action(Action.callnativeh5, ref, json);
	}

	private void action(Action action, String ref, String json) {

		// WXComponent webComponent = WXSDKManager.getInstance().getWXRenderManager().getWXComponent(mWXSDKInstance.getInstanceId(), ref);
//		if (webComponent instanceof WeeXWeb) {
//			((WeeXWeb) webComponent).setAction(action.name(), ref, json);
//		}
	}

}

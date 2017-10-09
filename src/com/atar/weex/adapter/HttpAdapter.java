/**
 * 
 */
package com.atar.weex.adapter;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import android.application.CommonApplication;
import android.application.CrashHandler;
import android.http.HttpRequest;
import android.http.HttpUrlConnectionRequest;
import android.interfaces.HandlerListener;
import android.interfaces.NetWorkCallListener;
import android.os.Environment;
import android.os.Message;
import android.reflection.ErrorMsgEnum;
import android.reflection.NetWorkMsg;
import android.reflection.ThreadPoolTool;
import android.text.TextUtils;
import android.utils.FileUtils;
import android.utils.MDPassword;
import android.utils.ShowLog;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.atar.enums.EnumMsgWhat;
import com.atar.htmls.DynamicHtmlUtils;
import com.atar.net.UrlParamCommon;
import com.atar.utils.LoadUtil;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.common.WXRequest;
import com.taobao.weex.common.WXResponse;

/**
 *****************************************************************************************************************************************************************************
 * 原生实现weex中网络请求 加载js和网络请尔weex中统一调用 故原生分开写
 * @author :Atar
 * @createTime:2017-3-14下午5:22:25
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class HttpAdapter implements IWXHttpAdapter {
	private static String TAG = HttpAdapter.class.getSimpleName();
	public static String SAVE_JS_PATH = Environment.getExternalStorageDirectory() + "/.Android/.cache/.jnkmldfdfhtfilecj/";
	private Gson gson = new Gson();

	@Override
	public void sendRequest(final WXRequest request, final OnHttpListener listener) {
		if (listener != null) {
			listener.onHttpStart();
		}
		if (request != null && request.url != null && request.url.length() > 0) {
			if (request.url.contains(".js")) {// 加载js
				ShowLog.i(TAG, "js--url-->" + request.url);
				if (request.url.contains("taoguba") && request.url.contains("com.cn") && !request.url.contains("https") && request.url.contains("http")) {
					request.url = request.url.replace("http", "https");
				}
				ThreadPoolTool.getInstance().execute(new Runnable() {
					@Override
					public void run() {
						int requestJsCount = 0;
						requestJS(request, listener, requestJsCount);
					}
				});
			} else {// 网络接口请求
				try {
					final int msgWhat = 108502660;// 此处每一个请求对应new HandleMessageListener(callBack方法) 拿到返回数据， msgWhat=108502660为了出现异常时统一提示or日志 但不处理msg.obj
					final String method = "POST".equals(request.method) ? HttpUrlConnectionRequest.POST : HttpUrlConnectionRequest.GET;
					Map<String, String> map = null;
					if (request.body != null && request.body.length() > 0) {
						map = gson.fromJson(request.body, new TypeToken<HashMap<String, String>>() {
						}.getType());
					} else {
						map = new HashMap<String, String>();
					}
					String url = "";
					if (map.containsKey("url")) {
						if (map.containsKey("url") && map.get("url").contains("http") || map.get("url").contains("www.")) {// 如果是全路径
							url = map.get("url");
						} else {
							url = UrlParamCommon.API_HOST + map.get("url");
						}
						map.remove("url");
					}
					final String savrUrl = url + map.hashCode();
					Object[] params = new Object[] { url, map, UrlParamCommon.UTF_8, };
					ThreadPoolTool.getInstance().setAsyncTask(msgWhat, new NetWorkCallListener() {
						@Override
						public void NetWorkCall(NetWorkMsg msg) {
							final WXResponse response = new WXResponse();
							switch (msg.what) {
							case msgWhat:
								response.statusCode = "200";// 告诉weex页面成功
								if (msg.obj != null) {
									String result = (String) msg.obj;
									response.originalData = result.getBytes();
									if (!"POST".equals(method)) {// GET才接口数据，post不存入
										LoadUtil.saveToDB(result, savrUrl, "", "");
									}
								}
								if (listener != null) {
									listener.onHttpFinish(response);
								}
								break;
							case EnumMsgWhat.EMobileNetUseless_Msg:// 没有网络
								if (!"POST".equals(method)) {// GET才有接口数据，post不存入
									LoadUtil.QueryDB(new HandlerListener() {
										@Override
										public void onHandlerData(Message msg) {
											switch (msg.what) {
											case EnumMsgWhat.LOAD_FROM_SQL_COMPLETE:
												String result = LoadUtil.loadFromSqlComelete(msg, String.class);
												if (result != null && result.length() > 0) {
													response.statusCode = "200";// 告诉weex页面成功
													response.originalData = result.getBytes();
												} else {
													response.statusCode = "500";// 告诉weex页面失败 任意一个值 原码:(code >= 200 && code <= 299))为成功;
													response.errorCode = "-1";
													response.errorMsg = "Exception";
												}
												break;
											}
											if (listener != null) {
												listener.onHttpFinish(response);
											}
										}
									}, savrUrl, "", "");
								}
								break;
							default:
								response.statusCode = "500";// 告诉weex页面失败 任意一个值 原码:(code >= 200 && code <= 299))为成功;
								response.errorCode = "-1";
								response.errorMsg = "Exception";
								if (listener != null) {
									listener.onHttpFinish(response);
								}
								break;
							}
						}
					}, HttpUrlConnectionRequest.class.getName(), method, params, String.class);
				} catch (Exception e) {
					if (e instanceof JsonSyntaxException) {
						ThreadPoolTool.getInstance().toastException(ErrorMsgEnum.EJsonParser_Msg);// 请求数据异常
					}
				}
			}
		}
	}

	private int requestJS(WXRequest request, OnHttpListener listener, int requestJsCount) {
		WXResponse response = new WXResponse();
		int statusCode = 0;
		try {
			URL url = new URL(request.url);
			HttpURLConnection conn = null;
			if ("https".equals(url.getProtocol())) {
				HttpUrlConnectionRequest.trustAllHosts();
				HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
				HttpsURLConnection.setDefaultHostnameVerifier(HttpUrlConnectionRequest.hnv);
				https.setHostnameVerifier(HttpUrlConnectionRequest.hnv);
				HttpsURLConnection.setDefaultSSLSocketFactory(HttpUrlConnectionRequest.mSSLSocketFactory);
				https.setSSLSocketFactory(HttpUrlConnectionRequest.mSSLSocketFactory);

				https.setConnectTimeout(request.timeoutMs);
				https.setReadTimeout(request.timeoutMs);
				conn = https;
			} else {
				conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(request.timeoutMs);
				conn.setReadTimeout(request.timeoutMs);
			}
			conn.setDoInput(true);
			conn.setUseCaches(false);
			conn.setInstanceFollowRedirects(true);

			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

			/* header */
			if (request.paramMap != null) {
				Set<String> keySets = request.paramMap.keySet();
				for (String key : keySets) {
					conn.addRequestProperty(key, request.paramMap.get(key).toString());
				}
			}
			conn.connect();

			/* method */
			String method = request.method;
			if (!TextUtils.isEmpty(method)) {
				conn.setRequestMethod(method);
				/* body */
				if (method.equals("POST")) {
					if (listener != null) {
						listener.onHttpUploadProgress(0);
					}
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					out.write(request.body.getBytes());
					out.close();
					if (listener != null) {
						listener.onHttpUploadProgress(100);
					}
				}
			}
			statusCode = conn.getResponseCode();
			if (statusCode == -1 && requestJsCount % 2 == 0) {
				requestJsCount++;
				conn.disconnect();
				return requestJS(request, listener, requestJsCount);
			}
			ShowLog.i(TAG, "js-statusCode-->" + statusCode);
			response.statusCode = String.valueOf(statusCode);
			if (statusCode == 200 || statusCode == 202) {
				response.originalData = readInputStream(conn.getInputStream(), listener).getBytes();
				// 存储js文件
				try {
					if (request.url != null && request.url.length() > 0 && !DynamicHtmlUtils.map.containsKey(request.url)) {
						if (request.url.contains(".js")) {
							File file = new File(SAVE_JS_PATH);
							if (!FileUtils.exists(SAVE_JS_PATH)) {
								FileUtils.createDir(SAVE_JS_PATH);
							}
							String strLocalFileName = request.url.substring(request.url.lastIndexOf("/") + 1, request.url.length()).replace(".js", "");
							File jsFile = new File(file.getAbsolutePath(), MDPassword.getPassword32(strLocalFileName));
							jsFile.deleteOnExit();
							jsFile.createNewFile();
							FileOutputStream outStream = null;
							outStream = new FileOutputStream(jsFile);
							outStream.write(response.originalData);
							outStream.flush();
							outStream.close();
							DynamicHtmlUtils.map.put(request.url, "1");
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				response.errorMsg = readInputStream(conn.getErrorStream(), listener);
			}
			conn.disconnect();
			if (listener != null) {
				listener.onHttpFinish(response);
			}
		} catch (ConnectException e) {// 无法连接 服务没有开当作502处理 加载网络js异常绝大多数只有这一种情况
			if (HttpRequest.IsUsableNetWork(CommonApplication.getContext())) {
				ThreadPoolTool.getInstance().toastException(ErrorMsgEnum.EHttpRequestFail502);// 请求数据异常
			} else {
				ThreadPoolTool.getInstance().toastException(ErrorMsgEnum.EMobileNetUseless_Msg);// 手机没有网络
			}
		} catch (SocketTimeoutException e) {// 联网超时
			ThreadPoolTool.getInstance().toastException(ErrorMsgEnum.EConnectTimeout_Msg);//
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			response.statusCode = statusCode + "";
			response.errorCode = "-1";
			response.errorMsg = "服务器异常" + statusCode;
			if (listener != null) {
				listener.onHttpResponseProgress(100);
				listener.onHttpFinish(response);
			}
		}
		return statusCode;
	}

	// 读取数据
	private static String readInputStream(InputStream inputStream, OnHttpListener listener) {
		StringBuilder builder = new StringBuilder();
		try {
			if (inputStream != null) {
				int fileLen = inputStream.available();
				BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
				char[] data = new char[2048];
				int len;
				while ((len = localBufferedReader.read(data)) > 0) {
					builder.append(data, 0, len);
					if (listener != null && fileLen > 0) {
						listener.onHttpResponseProgress((builder.length() / fileLen) * 100);
					}
				}
				localBufferedReader.close();
				try {
					inputStream.close();
				} catch (Exception e) {
					ShowLog.e(TAG, "DefaultWXHttpAdapter: " + CrashHandler.crashToString(e));
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		String result = String.valueOf(builder);
		try {
			JSONObject jsonObject = JSON.parseObject(result);
			return String.valueOf(jsonObject);
		} catch (Exception e) {
			return result;
		}
	}

	/**
	 * 获取js缓存
	 * @author :Atar
	 * @createTime:2017-7-25下午1:56:17
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param url
	 * @return
	 * @description:
	 */
	public static String getJSCatch(String url) {
		if (url.contains(".js")) {
			try {
				File file = new File(SAVE_JS_PATH);
				if (!FileUtils.exists(SAVE_JS_PATH)) {
					FileUtils.createDir(SAVE_JS_PATH);
				}
				String strLocalFileName = url.substring(url.lastIndexOf("/") + 1, url.length()).replace(".js", "");
				File jsFile = new File(file.getAbsolutePath(), MDPassword.getPassword32(strLocalFileName));
				if (jsFile.exists()) {
					String originalData = readInputStream(new FileInputStream(jsFile), null);
					return originalData;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}

/**
 * 
 */
package com.atar.net;

import java.io.EOFException;
import java.io.IOException;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okio.Buffer;
import okio.BufferedSource;
import android.annotation.SuppressLint;
import android.appconfig.AppConfigSetting;
import android.application.CrashHandler;
import android.os.Handler;
import android.os.Looper;
import android.reflection.ThreadPoolTool;
import android.utils.ShowLog;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ws.WebSocket;
import com.squareup.okhttp.ws.WebSocketCall;
import com.squareup.okhttp.ws.WebSocketListener;
import com.taobao.weex.appfram.websocket.IWebSocketAdapter;
import com.taobao.weex.appfram.websocket.IWebSocketAdapter.EventListener;
import com.taobao.weex.appfram.websocket.WebSocketCloseCodes;

/**
 *****************************************************************************************************************************************************************************
 * websoket通用
 * @author :Atar
 * @createTime:2017-4-26上午10:24:16
 * @version:1.0.0
 * @modifyTime:
 * @modifyAuthor:
 * @description:
 *****************************************************************************************************************************************************************************
 */
public class OKHttpWebsocket {
	private String TAG = OKHttpWebsocket.class.getSimpleName();

	/** webSocketStatus 标志 默认(0) 正在连接(1) 连接成功(2) 正在退出(3)  连接失败 退出失败 webSocket中所有异常 退出成功 都变为默认(0) */
	private int webSocketStatus = 0;
	/**如果断开每隔8秒 连接8次后还是不能连接上就不管了 太耗电池和性能了*/
	private int connectedCount = 8;
	/** 单个线程池只允许一个线程进行心跳连接 */
	private ExecutorService exec = Executors.newSingleThreadExecutor();
	private ImpHeartBeatlConnect mImpHeartBeatlConnect = new ImpHeartBeatlConnect();
	private WebSocket webSocket;

	private String wsuri;
	/**  供weex所用 start*/
	private EventListener eventListener;
	private String protocol;
	/**  供weex所用 end*/

	private boolean isActivityDestroy;
	private Handler handler = new Handler(Looper.getMainLooper());

	/**
	 * @param wsuri
	 * @param eventListener
	 * @param protocol
	 */
	@SuppressWarnings("deprecation")
	public OKHttpWebsocket(String wsuri, String protocol, EventListener eventListener) {
		super();
		wsuri += "?token=" + URLEncoder.encode(AppConfigSetting.getInstance().getToken());
		this.wsuri = wsuri;
		this.protocol = protocol;
		this.eventListener = eventListener;
	}

	/**
	* 是否连接成功
	* @author :Atar
	* @createTime:2016-4-28下午4:00:04
	* @version:1.0.0
	* @modifyTime:
	* @modifyAuthor:
	* @return
	* @description:
	*/
	public boolean isConnectSuccess() {
		return webSocketStatus == 2;
	}

	/**
	 * 连接websocket
	 * @author :Atar
	 * @createTime:2016-4-28下午3:39:26
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	@SuppressLint("TrulyRandom")
	public void connect() {
		if (webSocketStatus == 0 && wsuri != null && wsuri.length() > 0) {
			try {
				ShowLog.i(TAG, "wsuri----->" + wsuri);
				OkHttpClient okHttpClient = new OkHttpClient();
				Request.Builder builder = new Request.Builder();
				if (protocol != null) {
					builder.addHeader(IWebSocketAdapter.HEADER_SEC_WEBSOCKET_PROTOCOL, protocol);
				}
				builder.url(wsuri);
				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null, new TrustManager[] { new X509TrustManager() {
					@Override
					public void checkClientTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
					}

					@Override
					public void checkServerTrusted(java.security.cert.X509Certificate[] arg0, String arg1) throws CertificateException {
					}

					@Override
					public java.security.cert.X509Certificate[] getAcceptedIssuers() {
						return null;
					}
				} }, new SecureRandom());
				SSLSocketFactory factory = sslContext.getSocketFactory();
				okHttpClient.setSslSocketFactory(factory);
				webSocketStatus = 1;
				WebSocketCall.create(okHttpClient, builder.build()).enqueue(new WebSocketListener() {
					@Override
					public void onOpen(WebSocket webSocket, Request request, Response response) throws IOException {
						OKHttpWebsocket.this.webSocket = webSocket;
						if (eventListener != null) {
							eventListener.onOpen();
						}
						webSocketStatus = 2;
						connectedCount = 8;
						ShowLog.i(TAG, "onOpen--连接成功---webSocketStatus---" + webSocketStatus);
					}

					@Override
					public void onMessage(BufferedSource bpayload, WebSocket.PayloadType type) throws IOException {
						final String payload = bpayload.readUtf8();
						if (eventListener != null) {
							eventListener.onMessage(payload);
						}
						if (handler != null) {
							handler.post(new Runnable() {
								@Override
								public void run() {
									OKHttpWebsocket.this.onMessage(payload);
								}
							});
						}
						bpayload.close();
						ShowLog.i(TAG, "onMessage------>" + payload);
					}

					@Override
					public void onPong(Buffer bpayload) {
						String payload = bpayload.readUtf8();
						ShowLog.i(TAG, "onPong------>" + payload);
					}

					@Override
					public void onClose(int code, String reason) {
						webSocketStatus = 0;
						ShowLog.i(TAG, "onClose---连接断开-->code--->" + code + "---reason--->" + reason + "---webSocketStatus---" + webSocketStatus);
						if (eventListener != null) {
							eventListener.onClose(code, reason, true);
						}
						onFailureCloseException();
					}

					@Override
					public void onFailure(IOException e) {
						webSocketStatus = 0;
						if (eventListener != null) {
							if (e instanceof EOFException) {
								eventListener.onClose(WebSocketCloseCodes.CLOSE_NORMAL.getCode(), WebSocketCloseCodes.CLOSE_NORMAL.name(), true);
							} else {
								eventListener.onError(e.getMessage());
							}
						}
						ShowLog.e(TAG, "onFailure--->" + CrashHandler.crashToString(e));
					}
				});
			} catch (Exception e) {
				webSocketStatus = 0;
				onFailureCloseException();
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}

	/**
	 * 接收消息
	 * @author :Atar
	 * @createTime:2017-4-26下午2:31:41
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param result
	 * @description:
	 */
	public void onMessage(String result) {
	}

	/**
	 * 异常失败关闭后重新连接
	 * @author :Atar
	 * @createTime:2017-4-26下午2:40:24
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public void onFailureCloseException() {
		if (connectedCount >= 0 && exec != null && !isActivityDestroy) {
			exec.execute(mImpHeartBeatlConnect);
		}
	}

	/**
	 * 发送消息
	 * @author :Atar
	 * @createTime:2016-4-28下午3:39:59
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param msssage
	 * @description:
	 */
	public void sendMessage(final String data) {
		if (webSocketStatus == 2 && webSocket != null && data != null && data.length() > 0) {
			ShowLog.i(TAG, "sendTextMessage--->" + data);
			ThreadPoolTool.getInstance().execute(new Runnable() {
				@Override
				public void run() {
					try {
						@SuppressWarnings("resource")
						Buffer buffer = new Buffer().writeUtf8(data);
						OKHttpWebsocket.this.webSocket.sendMessage(WebSocket.PayloadType.TEXT, buffer.buffer());
						buffer.flush();
						buffer.close();
					} catch (Exception e) {
						if (eventListener != null) {
							eventListener.onError(e.getMessage());
						}
						ShowLog.e(TAG, CrashHandler.crashToString(e));
					}
				}
			});
		}
	}

	/**
	 * 发送ping
	 * @author :Atar
	 * @createTime:2016-4-28下午3:39:59
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param msssage
	 * @description:
	 */
	public void sendPing(final String data) {
		ThreadPoolTool.getInstance().execute(new Runnable() {
			@Override
			public void run() {
				try {
					if (webSocketStatus == 2 && webSocket != null && data != null && data.length() > 0) {
						@SuppressWarnings("resource")
						Buffer buffer = new Buffer().writeUtf8(data);
						webSocket.sendPing(buffer);
						buffer.flush();
						buffer.close();
					}
				} catch (Exception e) {
					if (eventListener != null) {
						eventListener.onError(e.getMessage());
					}
					ShowLog.e(TAG, CrashHandler.crashToString(e));
				}
			}
		});
	}

	/**
	 * 重新连接
	 * @author :Atar
	 * @createTime:2017-4-26下午3:28:35
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public void reconnect() {
		if (connectedCount >= 0 && !isActivityDestroy) {
			connect();
		}
	}

	/**
	 * 断掉websocket
	 * @author :Atar
	 * @createTime:2016-4-28下午3:39:43
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:
	 */
	public void disconnect() {
		close(1001, "CLOSE_GOING_AWAY");
	}

	/**
	 * 关闭websocket
	 * @author :Atar
	 * @createTime:2017-4-26下午4:35:06
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @param code
	 * @param reason
	 * @description:
	 */
	public void close(final int code, final String reason) {
		isActivityDestroy = true;
		ThreadPoolTool.getInstance().execute(new Runnable() {
			@Override
			public void run() {
				try {
					if (webSocketStatus == 2 && webSocket != null) {
						connectedCount = 8;
						webSocketStatus = 3;
						webSocket.close(code, reason);
					}
				} catch (Exception e) {
					webSocketStatus = 0;
					if (eventListener != null) {
						eventListener.onError(e.getMessage());
					}
					ShowLog.e(TAG, CrashHandler.crashToString(e));
				}
			}
		});
	}

	/**
	 * 心跳连接
	 * @author :Atar
	 * @createTime:2016-5-6下午3:02:39
	 * @version:1.0.0
	 * @modifyTime:
	 * @modifyAuthor:
	 * @description:每隔8秒
	 */
	class ImpHeartBeatlConnect implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(8000);
				reconnect();
				connectedCount--;
			} catch (Exception e) {
				ShowLog.e(TAG, CrashHandler.crashToString(e));
			}
		}
	}
}

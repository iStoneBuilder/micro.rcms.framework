package com.stone.it.micro.rcms.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

/**
 * @author cj.stone
 * @Date 2022/12/14
 * @Desc
 */
public class RequestClient {

	private static HttpClient httpClient;

	private static HttpClient httpsClient;

	/**
	 * 设置连接超时时间，单位毫秒。
	 */
	private static final int CONNECT_TIMEOUT = 6000;

	/**
	 * 设置连接超时时间，单位毫秒。
	 */
	private static final int REQUEST_TIMEOUT = 6000;

	/**
	 * 请求获取数据的超时时间(即响应时间)，单位毫秒。
	 */
	private static final int SOCKET_TIMEOUT = 6000;


	public static synchronized HttpClient getClient() {
		return getClient(true);
	}

	public static synchronized HttpClient getClient(String uri) {
		return getClient(uri.startsWith("https://"));
	}

	public static synchronized HttpClient getClient(boolean isHttpsClient) {
		return isHttpsClient ? buildHttpsClient() : buildClient();
	}

	private static synchronized HttpClient buildClient() {
		if (httpClient == null) {
			httpClient = HttpClientBuilder.create()
					// 注释
					.setMaxConnTotal(200)
					// 注释
					.setMaxConnPerRoute(100)
					// 注释
					.setUserAgent("")
					// 注释
					.setDefaultRequestConfig(getRequestConfig())
					// 注释
					.disableAutomaticRetries()
					// 注释
					.build();
		}
		return httpClient;
	}

	private static synchronized HttpClient buildHttpsClient() {
		if (httpsClient == null) {
			httpsClient = HttpClients.custom()
					// 注释
					.setMaxConnTotal(200)
					// 注释
					.setMaxConnPerRoute(100)
					// 注释
					.setUserAgent("")
					// 注释
					.setDefaultRequestConfig(getRequestConfig())
					// 注释
					.disableAutomaticRetries()
					// 注释
					.build();
		}
		return httpsClient;
	}

	private static RequestConfig getRequestConfig() {
		return RequestConfig.custom()
				// CONNECT_TIMEOUT
				.setConnectTimeout(CONNECT_TIMEOUT)
				// REQUEST_TIMEOUT
				.setConnectionRequestTimeout(REQUEST_TIMEOUT)
				// SOCKET_TIMEOUT
				.setSocketTimeout(SOCKET_TIMEOUT)
				// BUILD
				.build();
	}

}

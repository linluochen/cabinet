package com.tools.common.wxtool.qywechat.utils;

import com.alibaba.fastjson.JSONObject;
import com.tools.common.wxtool.qywechat.enums.EnumMethod;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.SecureRandom;


public class HttpRequestUtil {
	
	/**
	 * 发起https请求并获取结果
	 * 
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		
		
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		
		InputStream inputStream = null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpURLConnection httpUrlConn =null;
		try {
			TrustManager[] tm = { new MyX509TrustManager() };
			//SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			//sslContext.init(null, tm, new SecureRandom());
			//SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			httpUrlConn = (HttpURLConnection) url.openConnection();
			//httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setRequestMethod(requestMethod);
			httpUrlConn.setConnectTimeout(60000);
			httpUrlConn.setReadTimeout(60000);
			httpUrlConn.setRequestProperty("connection", "close");
			//httpUrlConn.addRequestProperty("Keep-Alive", "")

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
	    	while ((str = bufferedReader.readLine()) != null) {
	    			buffer.append(str);
	    	}
			
			
			jsonObject = JSONObject.parseObject(buffer.toString());
		} catch (ConnectException ce) {
			ce.printStackTrace();
			System.out.println("网络链接失败！");
		}catch (UnknownHostException uhe) {
			uhe.printStackTrace();
			System.out.println("微信API无法访问....！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			
			try {
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
				inputStream = null;
				httpUrlConn.disconnect();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return jsonObject;
	}
	
	/**
	 * 发起https请求并获取字节数组结果
	 * @param requestUrl
	 * @param requestMethod
	 * @param data
	 * @return
	 */
	public static byte[] httpRequest_byte(String requestUrl, String requestMethod, byte[] data) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			if (requestMethod == EnumMethod.GET.name() && data != null && data.length > 0) {
				if (requestUrl.indexOf('?') > 0) {
					requestUrl += '&';
				} else {
					requestUrl += '?';
				}
				requestUrl += new String(data);
			}
			URL url = new URL(requestUrl);
			HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
			if (httpUrlConn instanceof HttpsURLConnection) {
				// 创建SSLContext对象，并使用我们指定的信任管理器初始化
				TrustManager[] tm = { new MyX509TrustManager() };
				SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
				sslContext.init(null, tm, new SecureRandom());
				// 从上述SSLContext对象中得到SSLSocketFactory对象
				SSLSocketFactory ssf = sslContext.getSocketFactory();
				((HttpsURLConnection) httpUrlConn).setSSLSocketFactory(ssf);
			}
			boolean truePost = requestMethod == EnumMethod.POST.name() && data != null && data.length > 0;
			httpUrlConn.setDoOutput(truePost);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			httpUrlConn.setConnectTimeout(60000);
			httpUrlConn.setReadTimeout(60000);
			httpUrlConn.setRequestProperty("connection", "close");
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if (requestMethod == EnumMethod.GET.name()) {
				httpUrlConn.connect();
			} else if (truePost) {
				// 提交数据
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(data);
				outputStream.close();
			}

			// 读取返回数据
			InputStream inputStream = httpUrlConn.getInputStream();
			byte[] buf = new byte[1024 * 2];
			int len;
			while ((len = inputStream.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			// 释放资源
			out.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
		} catch (ConnectException ce) {
		} catch (Exception e) {
		}
		return out.toByteArray();
	}
}

package com.frank.mmp.common.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.json.JSONObject;

public class HttpUtil { 
	private static Integer soketOut = 20000;
	private static Integer connOut = 20000;
	private static String enCode = "utf-8";
	private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
	
	public static void main(String[] args) {
		String url = "http://172.16.0.54:8801/phoneNumSplit/findDataSuper";
		String str = doPost(url,null);
		log.info("最终返回值："+str);
	}
	/**
	 * Http post请求
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doPost(String url, Map<String, String> params) {
		JSONObject resultJson = new JSONObject();
		String result = null;
		List<NameValuePair> nvps = buildNameValuePair(params);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		EntityBuilder builder = EntityBuilder.create();
		try {
			HttpPost httpPost = new HttpPost(url);
			builder.setParameters(nvps);
			httpPost.setEntity(builder.build());
			httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			if (params != null) {
				if (params.containsKey("soketOut")) {
					soketOut = Integer.valueOf(params.get("soketOut") + "");
				}
				if (params.containsKey("connOut")) {
					connOut = Integer.valueOf(params.get("connOut") + "");
				}
			}
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(soketOut).setConnectTimeout(connOut).build();// 设置请求和传输超时时间
			httpPost.setConfig(requestConfig);
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				HttpEntity entity = response.getEntity();
				if (response.getStatusLine().getReasonPhrase().equals("OK") && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					result = EntityUtils.toString(entity, "UTF-8");
					resultJson.put("data", result);
				}
				resultJson.put("statusCode", response.getStatusLine().getStatusCode());
				resultJson.put("message", response.getStatusLine().getReasonPhrase());
				EntityUtils.consume(entity);
			} catch (Exception e) {
				log.error("doPost请求异常：",e);
				resultJson.put("statusCode",901);
				resultJson.put("message", e.getMessage());
			} finally {
				response.close();
			}
		} catch (Exception e) {
			resultJson.put("statusCode",902);
			resultJson.put("message", e.getMessage());
			log.error("doPost请求异常：",e);
		} finally {
			try {
				httpclient.close();
			} catch (Exception e2) {
				log.error("httpclient 关闭异常：",e2);
			}
		}
		return resultJson.toString();
	}
	
	private static List<NameValuePair> buildNameValuePair(Map<String, String> params) {
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
		}
		return nvps;
	}
	
	
	public String doGet(String src, String outputencode, HashMap<String, String> headers) throws Exception {
		if(null == outputencode || "".equals(outputencode.trim())){
			outputencode = enCode;
		}
		StringBuffer result = new StringBuffer();
		URL url = new URL(src);
		URLConnection connection = url.openConnection();
		BufferedReader in = null;
		try {
			if (headers != null) {
				Iterator<String> iterators = headers.keySet().iterator();
				while (iterators.hasNext()) {
					String key = iterators.next();
					connection.setRequestProperty(key, headers.get(key));
				}
			}
			connection.connect();
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), outputencode));
			String line;
			while ((line = in.readLine()) != null) {
				result.append("\n" + line);
			}
			return result.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			if (in != null) {
				in.close();
			}
		}

	}
}

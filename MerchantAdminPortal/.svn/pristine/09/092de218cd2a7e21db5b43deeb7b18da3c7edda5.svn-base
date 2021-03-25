package com.npst.upi.portal.merchant.utility;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.HttpsURLConnection;

public class HttpUtility {
	public static String postHttpData(String str, Map<String, String> headerParams, String url_) {
		StringBuffer sb = null;
		String out = "";
		HttpURLConnection cn = null;
		try {
			URL url = new URL(url_);
			if ("https".equalsIgnoreCase(url.getProtocol())) {
				return postHttpsData(str, headerParams, url_);
			}
			cn = (HttpURLConnection) url.openConnection();
			cn.setDoInput(true);
			cn.setDoOutput(true);
			cn.setRequestMethod("POST");
			cn.setRequestProperty("Content-Type", "application/json");
			cn.setRequestProperty("accept", "application/json");
			cn.setRequestProperty("cache-control", "no-cache");
			cn.setConnectTimeout(50000);
			if (headerParams != null && headerParams.size() > 0) {
				for (Entry<String, String> e : headerParams.entrySet()) {
					if (e.getKey() == null)
						continue;
					cn.setRequestProperty(e.getKey(), (e.getValue() == null ? "" : e.getValue()));
				}
			}

			try (DataOutputStream wr = new DataOutputStream(cn.getOutputStream());) {
				wr.writeBytes(str);
				wr.flush();
			}

			InputStream inputStream = null;
			if (cn.getResponseCode() == 200) {
				inputStream = cn.getInputStream();
			} else {
				inputStream = cn.getErrorStream();
			}
			String l = null;
			try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));) {
				sb = new StringBuffer();
				while ((l = br.readLine()) != null) {
					sb.append(l + "\n");
				}
				out = sb.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}

	public static String postHttpsData(String str, Map<String, String> headerParams, String url_) {
		StringBuffer sb = null;
		String out = "";
		HttpsURLConnection cn = null;
		try {
			URL url = new URL(url_);
			if ("http".equalsIgnoreCase(url.getProtocol())) {
				return postHttpData(str, headerParams, url_);
			}
			cn = (HttpsURLConnection) url.openConnection();
			cn.setDoInput(true);
			cn.setDoOutput(true);
			cn.setRequestMethod("POST");
			cn.setRequestProperty("Content-Type", "application/json");
			cn.setRequestProperty("accept", "application/json");
			cn.setRequestProperty("cache-control", "no-cache");
			cn.setConnectTimeout(50000);
			if (headerParams != null && headerParams.size() > 0) {
				for (Entry<String, String> e : headerParams.entrySet()) {
					if (e.getKey() == null)
						continue;
					cn.setRequestProperty(e.getKey(), (e.getValue() == null ? "" : e.getValue()));
				}
			}

			try (DataOutputStream wr = new DataOutputStream(cn.getOutputStream());) {
				wr.writeBytes(str);
				wr.flush();
			}

			InputStream inputStream = null;
			if (cn.getResponseCode() == 200) {
				inputStream = cn.getInputStream();
			} else {
				inputStream = cn.getErrorStream();
			}
			String l = null;
			try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));) {
				sb = new StringBuffer();
				while ((l = br.readLine()) != null) {
					sb.append(l + "\n");
				}
				out = sb.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return out;
	}
}

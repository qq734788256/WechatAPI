package com.wx.base.util;

import java.net.URLEncoder;
import java.util.Random;

public class SmsUtil {
	/***           新版                         ****/
	private static String USER_ID = "100673";

	private static String USER_NAME = "DFSJH";

	private static String PASSWORD = "5f3bb4ded9ab0cde";

	public static int sendSMS(String phone, String message) {
		int statusCode = 0;
		try {
			message = "【酷听听书】" + message;
			String content = URLEncoder.encode(message, "GBK");

			String url = "http://42.96.248.183:8080/sendsms.php?userid=" + USER_ID + "&username=" + USER_NAME
					+ "&passwordMd5=" + PASSWORD + "&mobile=" + phone + "&message=" + content;
			String c = HttpClientUtil.doGet(url);
			System.out.println(c);
			if (c != null && c != "" && c.length() > 15) {
				statusCode = 1;
			}
		} catch (Exception e) {
			statusCode = -99;
		}

		return statusCode;
	}

	/**
	 * 生成随机数
	 * 
	 * @return
	 */
	public static String randomCode() {
		Random rand = new Random();
		String r = String.valueOf(rand.nextInt(999999));
		String temp = "";
		for (int i = 0; i < (6 - r.length()); i++) {
			temp += "0";
		}
		temp = temp + r;
		return temp;
	}
}

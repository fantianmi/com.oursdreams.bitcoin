package com.mvc.util;

import org.springframework.stereotype.Service;

@Service
public class Common {
	/**
	 * @功能 转换字符串值为int型值
	 * @参数 value为要转换的字符串
	 * @返回值 int型值
	 */
	public static String strChangeHTML(String value){
		value=value.replace("&","&");
		value=value.replace(" "," ");
		value=value.replace("<","<");
		value=value.replace(">",">");
		value=value.replace("\r\n","\r\n");
		return value;
	}
	public static String HTMLChangeStr(String value){
		value=value.replace("&amp;","&");
		value=value.replace("&nbsp;"," ");
		value=value.replace("&lt;","<");
		value=value.replace("&gt;",">");
		value=value.replace("<br>","\r\n");
		return value;
	}
}

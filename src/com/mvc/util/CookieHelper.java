package com.mvc.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Administrator
 * 
 */
// 一个帮助类，处理Cookie相关的业务逻辑

public class CookieHelper {
	private Cookie cookie = null;

	// 创建一个Cookie对象（名字，值，时间）
	public Cookie createCookie(String name, String value, int time) {
		cookie = new Cookie(name, value);
		cookie.setMaxAge(time);
		return cookie;
	}

	// 检索客户端是否存在一个Cookie对象
	public boolean searchCookie(HttpServletRequest request, String name) {
		boolean flag = false;
		Cookie[] allCookies = request.getCookies();
		if (allCookies != null) {
			for (int i = 0; i < allCookies.length; i++) {
				cookie = allCookies[i];
				if (cookie.getName().equals(name))
					flag = true;
			}
		}
		return flag;
	}

	public Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] allCookies = request.getCookies();
		if (allCookies != null) {
			for (int i = 0; i < allCookies.length; i++) {
				cookie = allCookies[i];
				if (cookie.getName().equals(name))
					return cookie;
			}
		}
		cookie = null;
		return cookie;
	}
	
	public Cookie removeCookie(HttpServletRequest request, String name) {
		Cookie[] allCookies = request.getCookies();
		if (allCookies != null) {
			for (int i = 0; i < allCookies.length; i++) {
				cookie = allCookies[i];
				if (cookie.getName().equals(name)){
				  allCookies[i].setMaxAge(0);  
				}
			}
		}
		return cookie;
	}
}

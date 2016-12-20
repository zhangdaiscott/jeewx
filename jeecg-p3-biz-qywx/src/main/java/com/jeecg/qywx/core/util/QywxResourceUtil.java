package com.jeecg.qywx.core.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * 
 */
public class QywxResourceUtil {

	private static final String domain;
	private static final ResourceBundle bundle = java.util.ResourceBundle.getBundle("qywxconfig");
	static {
		domain = getConfigByName("domain");
	}
	public static final String getConfigByName(String name) {
		return bundle.getString(name);
	}

	public static final String getDomain() {
		return domain;
	}

	public static void main(String[] args) {
		System.out.println(getDomain());
	}
}

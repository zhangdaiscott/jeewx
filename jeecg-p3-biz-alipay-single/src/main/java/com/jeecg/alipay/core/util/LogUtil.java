/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.jeecg.alipay.core.util;


/**
 * 日志打印工具
 * 
 * @author taixu.zqq
 * @version $Id: LogUtil.java, v 0.1 2014年7月25日 下午4:34:46 taixu.zqq Exp $
 */
public class LogUtil {

    /**
     * 信息日志打印
     * 
     * @param prefixName 前缀名称
     * @param params  参数
     */
    public static void log(String prefixName, String msgContent) {
        System.out.println(prefixName + " : " + msgContent);
    }
}

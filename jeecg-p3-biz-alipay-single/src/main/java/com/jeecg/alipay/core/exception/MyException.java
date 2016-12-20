/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.jeecg.alipay.core.exception;

/**
 * 自定义异常
 * 
 * @author taixu.zqq
 * @version $Id: MyException.java, v 0.1 2014年7月24日 下午6:58:39 taixu.zqq Exp $
 */
public class MyException extends Exception{

    private static final long serialVersionUID = -5710488447295073398L;
    
    public MyException(){
    }   
    
    public MyException(String message) {   
        super(message);           
    }
    
    public MyException(Throwable throwable){
        super(throwable);
    }
    
    public MyException(String message, Throwable throwable){
        super(message, throwable);
    }

}

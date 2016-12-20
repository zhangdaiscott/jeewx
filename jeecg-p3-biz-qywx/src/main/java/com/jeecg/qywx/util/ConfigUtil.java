package com.jeecg.qywx.util;

import java.util.Map;

public class ConfigUtil {
	    
	    private static Map<String,String> map =null;    
	    
	    static {
	        map= ResourceUtils.getResource("qywxconfig").getMap();
	    }  
	    
	    public  String getFtpServer(){
	    	return map.get("ftp_server");
	    }
	    
	    public  int getPort(){
	    	return Integer.valueOf(map.get("ftp_port"));
	    }
	    
	    public  String getUsername(){
	    	return map.get("ftp_user_name");
	    }
	    
	    public static String getPassword(){
	    	return map.get("ftp_password");
	    }
	    
	    public static  String getCharsetName(){
	    	return map.get("charset_name");
	    }
	    
	    public static  String getPathName(){
	    	return map.get("ftp_pathname");
	    }
	    
	    public static  String getProperty(String key){
	    	return map.get(key);
	    }
	    
}

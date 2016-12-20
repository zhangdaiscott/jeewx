package com.jeecg.qywx.core.weixin.util;

import java.net.*;
import java.io.*;
import java.util.Properties;
import java.util.Enumeration;

public class Http{   
	  
    protected Socket client;   
  
    protected BufferedOutputStream sender;   
  
    protected BufferedInputStream receiver;   
  
    protected ByteArrayInputStream byteStream;   
  
    protected URL target;   
  
    private int responseCode = -1;   
  
    private String responseMessage = "";   
  
    private String serverVersion = "";   
  
    private Properties header = new Properties();   
  
  
    public Http(){}   
  
  
    public Http(String url){   
  
        GET(url);   
  
    }   
  
  
    /* GET方法根据URL，会请求文件、数据库查询结果、程序运行结果等多种内容 */  
  
    public void GET(String url){   
  
        try{   
  
            checkHTTP(url);   
  
            openServer(target.getHost(),target.getPort(),4000);   
  
            String cmd = "GET " + getURLFormat(target) + " HTTP/1.1\r\n" +   
  
            getBaseHeads() + "\r\n";   
  
            sendMessage(cmd);   
  
            receiveMessage();   
  
        }   
        catch(ProtocolException p){   
  
            p.printStackTrace();   
  
            return;   
  
        }   
  
        catch(UnknownHostException e){   
  
            e.printStackTrace();   
  
            return;   
  
        }   
  
        catch(IOException i){   
  
            i.printStackTrace();   
  
            return;   
  
        }   
  
    }   
  
  
    /*  
 
     * HEAD方法只请求URL的元信息，不包括URL本身。若怀疑本机和服务器上的  
 
     * 文件相同，用这个方法检查最快捷有效。  
 
     */  
  
    public void HEAD(String url){   
  
        try{   
  
            checkHTTP(url);   
  
            openServer(target.getHost(),target.getPort(),20000);   
  
            String cmd = "HEAD " + getURLFormat(target) + " HTTP/1.1\r\n" +   
  
            getBaseHeads() + "\r\n";   
  
            sendMessage(cmd);   
  
            receiveMessage();   
  
        }   
  
        catch(ProtocolException p){   
  
            p.printStackTrace();   
  
            return;   
  
        }   
  
        catch(UnknownHostException e){   
  
            e.printStackTrace();   
  
            return;   
  
        }   
  
        catch(IOException i){   
  
            i.printStackTrace();   
  
            return;   
  
        }   
  
    }   
   
   
  
  
    /*  
 
     * POST方法是向服务器传送数据，以便服务器做出相应的处理。例如网页上常用的  
 
     * 提交表格。  
 
     */  
  
    public String POST(String url,String content){   
  
        try{   
  
            checkHTTP(url);   
  
            //openServer(target.getHost(),80); 
            openServer(target.getHost(),target.getPort(),20000);   
  
            //String cmd = "POST " + getURLFormat(target) + " HTTP/1.1\r\n" +   
  
            //getBaseHeads();   
  
            //cmd += "Content-type: application/x-www-form-urlencoded\r\n";   
  
            //cmd += "Content-length: " + content.length() + "\r\n\r\n";   
            String cmd = "POST /index.jsp HTTP/1.1\r\n";
            //String cmd = "POST /iams/portalAgent HTTP/1.1\r\n";
            cmd += "Accept-Language: zh-cn\r\n";
            cmd += "Connection: Keep-Alive\r\n";
            cmd += "Host: " + target.getHost() + "\r\n";
            //cmd += "Content-Length: " + content.getBytes().length + "\r\n";
            cmd += "Content-Length: " + content.trim().getBytes("UTF-8").length + "\r\n";
            cmd += "\r\n";
            cmd = content.trim() + "\r\n";
            cmd += "\r\n";
            
          
            sendMessage(cmd);   
 
            //return receiveMessage();   
            //return ReceiveClientMsg();
            //return ReceiveClientMsg_UTF8();
            return ReceiveClientMsg_UTF8_JiPiao();//
        }   
        
  
        catch(ProtocolException p){   
  
            p.printStackTrace();   
  
            return "";   
  
        }   
  
        catch(UnknownHostException e){   
  
            e.printStackTrace();   
  
            return "";   
  
        }   
  
        catch(IOException i){   
  
            i.printStackTrace();   

            return "";   
  
        }   
  
  
    }   
    
    
    
    public String POST_TOKEN(String url,String content){   
  
        try{   
  
            checkHTTP(url);   
  
            //openServer(target.getHost(),80); 
            openServer(target.getHost(),target.getPort(),30000);   
  
            //String cmd = "POST " + getURLFormat(target) + " HTTP/1.1\r\n" +   
  
            //getBaseHeads();   
  
            //cmd += "Content-type: application/x-www-form-urlencoded\r\n";   
  
            //cmd += "Content-length: " + content.length() + "\r\n\r\n";   
            //String cmd = "POST /index.jsp HTTP/1.1\r\n";
            String cmd = "POST /iams/portalAgent HTTP/1.1\r\n";
            cmd += "Accept-Language: zh-cn\r\n";
            cmd += "Connection: Keep-Alive\r\n";
            cmd += "Host: " + target.getHost() + "\r\n";
            cmd += "Content-Length: " + content.getBytes("UTF-8").length + "\r\n";
            cmd += "\r\n";
            cmd += content + "\r\n";
            cmd += "\r\n";
  
            System.out.println("发送信息到服务器="+cmd);
            sendMessage(cmd);   
            System.out.println("接收服务器信息");
            //return receiveMessage();   
            //return ReceiveClientMsg();
            return ReceiveClientMsg_UTF8();
  
        }   
  
        catch(ProtocolException p){   
  
            p.printStackTrace();   
  
            return "";   
  
        }   
  
        catch(UnknownHostException e){   
  
            e.printStackTrace();   
  
            return "";   
  
        }   
  
        catch(IOException i){   
  
            i.printStackTrace();   

            return "";   
  
        }   
  
  
    }  
  
    protected void checkHTTP(String url) throws ProtocolException{   
  
        try{   
  
            URL target = new URL(url);   
  
            if(target == null ||   
  
               !target.getProtocol().toUpperCase().equals("HTTP")){   
  
                throw new ProtocolException("这不是HTTP协议");   
  
            }   
  
            this.target = target;   
  
        }   
  
        catch(MalformedURLException m){   
  
            throw new ProtocolException("协议格式错误");   
  
        }   
  
    }   
  
  
    /*  
 
     * 与Web服务器连接。若找不到Web服务器，InetAddress会引发UnknownHostException  
 
     * 异常。若Socket连接失败，会引发IOException异常。  
 
     */  
  
    protected void openServer(String host,int port,int timeout) throws UnknownHostException,   
  
    IOException{   
  
    
        header.clear();   
  
        responseMessage = "";   
  
        responseCode = -1;   
  
  
        if(client != null){   
  
            closeServer();   
  
        }   
  
        if(byteStream != null){   
  
            byteStream.close();   
  
            byteStream = null;   
  
        }   
  
  
        InetAddress address = InetAddress.getByName(host);   
  
        client = new Socket(address,port == -1 ? 80 : port);   
  
        client.setSoTimeout(timeout);   
  
        sender = new BufferedOutputStream(client.getOutputStream());   
  
        receiver = new BufferedInputStream(client.getInputStream());   
  
    }   
  
  
    /* 关闭与Web服务器的连接 */  
  
    protected void closeServer() throws IOException{   
  
        if(client == null){   
  
            return;   
  
        }   
  
        try{   
  
            client.close();   
  
            sender.close();   
  
            receiver.close();   
  
        }   
  
        catch(IOException i){   
  
            throw i;   
  
        }   
  
  
        client = null;   
  
        sender = null;   
  
        receiver = null;   
  
    }   
  
  
    protected String getURLFormat(URL target){   
  
        String spec = "http://" + target.getHost();   
  
        if(target.getPort() != -1){   
  
            spec += ":" + target.getPort();   
  
        }   
  
  
        return spec += target.getFile();   
  
    }   
  
  
    /* 向Web服务器传送数据 */  
  
    protected void sendMessage(String data) throws IOException{   
  
        sender.write(data.getBytes("UTF-8"),0,data.getBytes("UTF-8").length);   
  
        sender.flush();   
  
    }   
  
  
    /* 接收来自Web服务器的数据 */  
  
    protected String receiveMessage() throws IOException{   
       try{
    	   String sRet = "";
        byte data[] = new byte[2048];   
  
        int count = 0;   
  
        int word = -1;   
  
        // 解析第一行   
  
        while( (word = receiver.read()) != -1){ 
        	
            if(word == '\r' || word == '\n'){   
  
                word = receiver.read();   
  
                if(word == '\n') {   
  
                    word = receiver.read();   
  
                }   
  
                break;   
  
            }   
  
            if(count == data.length) {   
  
                data = addCapacity(data);   
  
            }   
  
            data[count++] = (byte) word;   
  
        }   
        String message = new String(data,0,count);   
 
        System.out.println("报头="+message);
        int mark = message.indexOf(32);   
  
        serverVersion= message.substring(0,mark);   
  
        while(mark < message.length() && message.charAt(mark + 1) == 32) {   
  
            mark++;   
  
        }   
  
        responseCode = Integer.parseInt(message.substring(mark + 1,mark += 4));   
  
        responseMessage = message.substring(mark,message.length()).trim();   
  
  
        // 应答状态码和处理请读者添加   
  
        switch(responseCode){   
  
            case 400:   
                throw new IOException("错误请求");   
  
            case 404:   
                
                throw new FileNotFoundException(getURLFormat(target));   
  
            case 503:   
  
                throw new IOException("服务器不可用");   
  
        }   
  
        if(word == -1){   
  
            throw new ProtocolException("信息接收异常终止");   
  
        }   
  
        int symbol = -1;   
  
        count = 0;   
  
        // 解析元信息   
  
        while(word != '\r' && word != '\n' && word > -1){   
  
            if(word == '\t') {   
  
                word = 32;   
  
            }   
  
            if(count == data.length) {   
  
                data = addCapacity(data);   
  
            }   
  
            data[count++] = (byte) word;   
  
            parseLine:{   
  
                while( (symbol = receiver.read()) > -1){   
  
                    switch(symbol){   
  
                        case '\t':   
  
                            symbol = 32;   
  
                            break;   
  
                        case '\r':   
  
                        case '\n':   
  
                            word = receiver.read();   
  
                            if(symbol == '\r' && word == '\n'){   
  
                                word = receiver.read();   
  
                                if(word == '\r') {   
  
                                    word = receiver.read();   
  
                                }   
  
                            }   
  
                            if(word == '\r' || word == '\n' || word > 32){   
  
                                break parseLine;   
  
                            }   
  
                            symbol = 32;   
  
                            break;   
  
                    }   
  
                    if(count == data.length) {   
  
                        data = addCapacity(data);   
  
                    }   
  
                    data[count++] = (byte) symbol;   
  
                }   
  
                word = -1;   
  
            }   
  
            message = new String(data,0,count);   
            System.out.println("message="+message);
  
            mark = message.indexOf(':');   
  
            String key = null;   
  
            if(mark > 0) {   
  
                key = message.substring(0,mark);   
  
            }   
  
            mark++;   
  
            while(mark < message.length() && message.charAt(mark) <= 32) {   
  
                mark++;   
  
            }   
  
            String value = message.substring(mark,message.length());   
 
            System.out.println("value="+value);
            header.put(key,value);   
  
            count = 0;   
  
        }   
        System.out.println("获取正文数据");
        // 获得正文数据   
  
        while( (word = receiver.read()) != -1){   
  
            if(count == data.length) {   
  
                data = addCapacity(data);   
  
            }   
  
            data[count++] = (byte) word;   
  
        }   
  
        if(count > 0) {   
  
            byteStream = new ByteArrayInputStream(data,0,count);
            sRet=new String(data,"UTF-8");
            System.out.println(sRet);

            /*StringBuffer   sb   =   new   StringBuffer(); 
            for(int i=0;i<byteStream.available();i++) 
            { 
               sb.append((char)byteStream.read()); 
            } 
            System.out.println(sb.toString());*/
            /*InputStreamReader   input   =   new   InputStreamReader(byteStream); 
            BufferedReader   bf   =   new   BufferedReader(input); 
            String   line   =   null; 
            while((line=bf.readLine())   !=   null){ 

            System.out.println(line); 

            } */

        }   
  
        data = null;   
  
        closeServer(); 
        //byte[] bs = sRet.getBytes();
        //sRet = new String(bs, "UTF-8"); 
        return sRet;
       }
       catch(Exception e)
       {
    	   System.out.println("接收数据异常："+e.getMessage());
    	   return "";
       }
  
    }   
  
  
    public String getResponseMessage(){   
  
        return responseMessage;   
  
    }   
  
  
    public int getResponseCode(){   
  
        return responseCode;   
  
    }   
  
  
    public String getServerVersion(){   
  
        return serverVersion;   
  
    }   
  
  
    public InputStream getInputStream(){   
  
        return byteStream;   
  
    }   
  
  
    public synchronized String getHeaderKey(int i){   
  
        if(i >= header.size()){   
  
            return null;   
  
        }   
  
        Enumeration enumss = header.propertyNames();   
  
        String key = null;   
  
        for(int j = 0; j <= i; j++){   
  
            key = (String) enumss.nextElement();   
  
        }   
  
        return key;   
  
    }   
  
  
    public synchronized String getHeaderValue(int i){   
  
        if(i >= header.size()){   
  
            return null;   
  
        }   
  
        return header.getProperty(getHeaderKey(i));   
  
    }   
  
  
    public synchronized String getHeaderValue(String key){   
  
        return header.getProperty(key);   
  
    }   
  
  
    protected String getBaseHeads(){   
  
        String inf = "User-Agent: Http/1.1\r\nAccept: www/source; text/html; image/gif; */*\r\n";   
  
        return inf;   
  
    }   
  
  
    private byte[] addCapacity(byte rece[]){   
  
        byte temp[] = new byte[rece.length + 1024];   
  
        System.arraycopy(rece,0,temp,0,rece.length);   
  
        return temp;   
  
    }   
  
    //========================洛阳银行程序使用================================================
    private String ReceiveClientMsg()
    {
    	try
    	{
    		BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()) , 1024);   
	        StringWriter received = new StringWriter(1024);   
	        char[] charBuf = new char[1024];   
	        int size = 0;  
	        size = br.read(charBuf , 0 , 1024);
	        if(size>0)
	        {
	        	received.write(charBuf, 0, size); 
	        }
	        String sRet = received.toString().trim(); 
	        received.close();
	        br.close();
	        System.out.println("==>"+sRet);
    		return sRet; 
    	}
    	catch (IOException e)
        {//6222021702013088356
            System.out.println("接收信息失败,"+e.getMessage());  
            return "";
        }
    }
    
    protected String ReceiveClientMsg_UTF8() throws IOException{   
        try
        {
        	/*
        	//BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(),"utf-8") , 1024);   
        	BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(),"UTF-8") , 1024);   
	        StringWriter received = new StringWriter(1024);   
	        char[] charBuf = new char[1024];   
	        int size = 0;  
	        size = br.read(charBuf , 0 , 1024);
	        if(size>0)
	        {
	        	received.write(charBuf, 0, size); 
	        }
	        String sRet = received.toString().trim(); 
	        received.close();
	        br.close();
	        System.out.println("==>"+sRet);
	        //===机票订购使用================
	        if(sRet!=null&&!sRet.equals(""))
	        {
	        	String t1 = sRet.toUpperCase();
	        	int index = t1.indexOf("<MESSAGE>");
	        	if(index>=0)
	        		sRet = sRet.substring(index);
	        }
	        //============================
    		return sRet; 
    		*/
        	String htmlContent = "";
			java.io.InputStream inputStream;
			inputStream = client.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte bytes[] = new byte[1024]; 
			int len = 0;
			while(len !=-1)
			{
				len = inputStream.read(bytes, 0, 1024);
				if(len!=-1)
				{
					baos.write(bytes, 0, len);
					//baos.flush();
				}
			}
			baos.flush();
			htmlContent = new String(baos.toByteArray(), "UTF-8");
			//===机票订购使用================
	        if(htmlContent!=null&&!htmlContent.equals(""))
	        {
	        	String t1 = htmlContent.toUpperCase();
	        	int index = t1.indexOf("<MESSAGE>");
	        	if(index>=0)
	        		htmlContent = htmlContent.substring(index);
	        }
	        //============================
		    return htmlContent;
        }
        catch(Exception e)
        {
     	   System.out.println("2接收数据异常："+e.getMessage());
     	   return "";
        }
   
     } 
    
    protected String ReceiveClientMsg_UTF8_JiPiao() throws IOException{   
        try
        {
        	BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream(),"GBK") , 2048);   
	        StringWriter received = new StringWriter(2048);   
	        char[] charBuf = new char[2048];   
	        int size = 0;  
	        size = br.read(charBuf , 0 , 2048);
	        if(size>0)
	        {
	        	received.write(charBuf, 0, size); 
	        }
	        String sRet = received.toString().trim(); 
	        received.close();
	        br.close();
	        
	       //===机票订购使用================
	        if(sRet!=null&&!sRet.equals(""))
	        {
	        	String t1 = sRet.toUpperCase();
	        	int index = t1.indexOf("<MESSAGE>");
	        	if(index>=0)
	        		sRet = sRet.substring(index);
	        }
	        //============================
    		return sRet; 
    		
        }
        catch(Exception e)
        {
     	   System.out.println("1通过http接收数据异常："+e.getMessage());
     	   return "";
        }
   
     } 
    
    public String POST_SSO(){   
    	  
        try{   
  
            checkHTTP("http://218.206.204.66:9082/sso-core/ssoservlet");   
            openServer("218.206.204.66",9082,300000); 
            
            String content = "<samlp:ArtifactResolve xmlns:samlp=\"urn:oasis:names:tc:SAML:2.0:protocol\" "+
               "xmlns:saml=\"urn:oasis:names:tc:SAML:2.0:assertion\" "+
               "ID=\"id_1\" Version=\"2.0\" IssueInstant=\"2007-12-05T09:21:59Z\"> "+
               "<saml:Issuer>https://www.chinamobile.com/SSO</saml:Issuer> "+
               "<samlp:Artifact>STWXC0000001350453157378001000000000001jR053e56XVWkK0on8</samlp:Artifact> "+
               "</samlp:ArtifactResolve>";
  
            String cmd = "POST /sso-core/ssoservlet HTTP/1.1\r\n";
            cmd += "Accept-Language: zh-cn\r\n";
            cmd += "Connection: Keep-Alive\r\n";
            cmd += "Host: " + target.getHost() + "\r\n";
            cmd += "Content-Length: " + content.getBytes("UTF-8").length + "\r\n";
            cmd += "\r\n";
            cmd += content + "\r\n";
            cmd += "\r\n";
  
            System.out.println("发送信息到服务器="+cmd);
            sendMessage(cmd);   
            System.out.println("接收服务器信息");
            //return receiveMessage();   
            //return ReceiveClientMsg();
            return ReceiveClientMsg_SSO();
  
        }   
  
        catch(ProtocolException p){   
  
            p.printStackTrace();   
  
            return "";   
  
        }   
  
        catch(UnknownHostException e){   
  
            e.printStackTrace();   
  
            return "";   
  
        }   
  
        catch(IOException i){   
  
            i.printStackTrace();   

            return "";   
  
        }   
  
    }
    
    protected String ReceiveClientMsg_SSO() throws IOException{   
        try
        {
        	String htmlContent = "";
			java.io.InputStream inputStream;
			inputStream = client.getInputStream();
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte bytes[] = new byte[102400]; 
			int len = 0;
			while(len !=-1)
			{
				len = inputStream.read(bytes, 0, 102400);
				if(len!=-1)
				{
					baos.write(bytes, 0, len);
					//baos.flush();
				}
			}
			baos.flush();
			htmlContent = new String(baos.toByteArray(), "UTF-8");
			System.out.println("结果：\n"+htmlContent);
		    return htmlContent;
        }
        catch(Exception e)
        {
     	   System.out.println("2接收数据异常："+e.getMessage());
     	   return "";
        }
   
     }
} 

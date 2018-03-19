package weixin.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import net.sf.json.JSONObject;
import weixin.guanjia.core.util.WeixinUtil;
import weixin.guanjia.message.model.UploadGraphic;

/**
 * weixin工具类
 */
public class WeixinUtilOsc {

	private static String upload_news_update_url = "https://api.weixin.qq.com/cgi-bin/material/update_news?access_token=ACCESS_TOKEN";
	private static String upload_news_add_url = "https://api.weixin.qq.com/cgi-bin/material/add_news?access_token=ACCESS_TOKEN";
	private static String upload_material_url = "https://api.weixin.qq.com/cgi-bin/material/add_material?";
	
	/**
	   * 上传多媒体文件
	   * @param fileType
	   * @param filePath
	   * @param token
	   * @return
	   */
	  public static JSONObject sendMedia(String fileType,String filePath,String token) {
			String result = null;
			JSONObject jsonobject = new JSONObject();
			jsonobject = null;
			File file = new File(filePath);
			if(!file.exists()||!file.isFile()){
				jsonobject = null;
				org.jeecgframework.core.util.LogUtil.info("------------文件不存在------------------------");
			}else{
				HttpURLConnection con =null;
				OutputStream out =null;
				DataInputStream in = null;
				try{
					//
					String requestUrl="https://api.weixin.qq.com/cgi-bin/material/add_material?"+"access_token="+ token + "&type="+fileType;  
					//String requestUrl="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token="+ token + "&type="+fileType;
					org.jeecgframework.core.util.LogUtil.info("------------------requestUr------------"+requestUrl);
					URL urlObj = new URL(requestUrl);  
					con = (HttpURLConnection) urlObj.openConnection();  
					con.setRequestMethod("POST"); // 以Post方式提交表单，默认get方式  
				    con.setDoInput(true);  
				    con.setDoOutput(true);  
				    con.setUseCaches(false); // post方式不能使用缓存  
				    //设置网络超时
	                con.setConnectTimeout(8000);  
	                con.setReadTimeout(8000);
				    con.setRequestProperty("Connection", "Keep-Alive");// 设置请求头信息
				    con.setRequestProperty("Charset", "UTF-8");  
				    String BOUNDARY = "----------" + System.currentTimeMillis();// 设置边界  
				    con.setRequestProperty("Content-Type", "multipart/form-data; boundary="+ BOUNDARY);  
				    
				   //"Content-Disposition:form-data;name=\"media\";filelength=\"{1}\";filename=\"{0}\"\r\nContent-Type:application/octet-stream\r\n\r\n", fileName, fs.Length));
				    // 请求正文信息  
				    // 第一部分：  
				    StringBuilder sb = new StringBuilder();  
				    sb.append("--"); // 必须多两道线  
				    sb.append(BOUNDARY);  
				    sb.append("\r\n");  
				    
				    sb.append("Content-Disposition: form-data;name=\"media\";filelength=\""+file.length()+"\";filename=\""+ file.getName() + "\"\r\n");  
				    sb.append("Content-Type:application/octet-stream\r\n\r\n");  
				    byte[] head = sb.toString().getBytes("utf-8");  
				    // 获得输出流  
				    out = new DataOutputStream(con.getOutputStream());  
				    // 输出表头  
				    out.write(head);  
				    // 文件正文部分  
				    // 把文件已流文件的方式 推入到url中  
				    in = new DataInputStream(new FileInputStream(file));  
				    int bytes = 0;  
				    byte[] bufferOut = new byte[1024];  
				    while ((bytes = in.read(bufferOut)) != -1) {  
				    	out.write(bufferOut, 0, bytes);  
				    }  
				    in.close();  
				    // 结尾部分  
				    byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线  
				    out.write(foot);  
				    out.flush();  
				    out.close();  
				    StringBuffer buffer = new StringBuffer();  
				    BufferedReader reader = null;  
				    try {  
				        // 定义BufferedReader输入流来读取URL的响应  
				        reader = new BufferedReader(new InputStreamReader(con.getInputStream()));  
				        String line = null;  
				        while ((line = reader.readLine()) != null) {  
				        	buffer.append(line);  
				        }  
				        if(result==null){  
				        	result = buffer.toString();  
				        }  
			        } catch (IOException e) {  
				        org.jeecgframework.core.util.LogUtil.info("发送POST请求出现异常！" + e);  
				        e.printStackTrace();  
				        throw new IOException("数据读取异常");  
			        } finally {  
				        if(reader!=null){  
				        reader.close();  
			        }  
				  }  
			      jsonobject = JSONObject.fromObject(result);  
				}catch(Exception e){
					e.printStackTrace();
				}finally{
		        	try {
		        		if(in!=null){
		        			in.close();
						}
						if(out!=null){
							out.close();
						}
						if(con!=null){
							con.disconnect();
						}
					} catch (Exception e) {
					}
		        }
			}
			return jsonobject;
		}
	  
	  /**
	   * 新增永久图文素材
	   * @param graphic
	   * @param accessToken
	   * @return
	   */
	  public static JSONObject uploadNewsTemplate(UploadGraphic graphic,String accessToken) {
			if(accessToken!=null){
				String requestUrl = upload_news_add_url.replace("ACCESS_TOKEN", accessToken);
				JSONObject obj = JSONObject.fromObject(graphic);
				org.jeecgframework.core.util.LogUtil.info(obj.toString());
				JSONObject result = WeixinUtil.httpRequest(requestUrl, "POST", obj.toString());
				org.jeecgframework.core.util.LogUtil.info(result.toString());
				return result;
			}
			return null;
		}
	  
	  /**
	   * 修改永久图文素材 TODO 待写
	   * @param graphic
	   * @param accessToken
	   * @return
	   */
	  public static JSONObject uploadNewsTemplateForUpdate(UploadGraphic graphic,String accessToken) {
		//  TODO 待写
			return null;
		}
	  
}

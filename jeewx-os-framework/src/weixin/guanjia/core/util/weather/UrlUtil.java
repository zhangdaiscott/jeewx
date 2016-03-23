package weixin.guanjia.core.util.weather;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 描述:根据网页地址获取网页内容
 * 
 * @date 2013-12-26
 * */
public class UrlUtil {

	/**
	 * 描述:根据网页地址获取网页内容
	 * 
	 * @param path
	 *            输入网址
	 * @return 网页内容
	 */
	public static String getURLContent(String path) {
		URLConnection connectionData;
		StringBuilder sb = null;
		BufferedReader br;
		URL url = null;
		try {
			url = new URL(path);
			connectionData = url.openConnection();
			connectionData.setConnectTimeout(1000);
			br = new BufferedReader(new InputStreamReader(connectionData
					.getInputStream(), "UTF-8"));
			sb = new StringBuilder();
			String line = null;
			while ((line = br.readLine()) != null)
				sb.append(line);
		} catch (SocketTimeoutException e) {
			org.jeecgframework.core.util.LogUtil.info("连接超时");
		} catch (FileNotFoundException e) {
			org.jeecgframework.core.util.LogUtil.info("加载文件出错");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
}

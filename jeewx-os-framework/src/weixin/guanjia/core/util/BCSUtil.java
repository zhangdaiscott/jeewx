package weixin.guanjia.core.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import org.apache.log4j.Logger;


import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.BucketSummary;
import com.baidu.inf.iis.bcs.model.DownloadObject;
import com.baidu.inf.iis.bcs.model.ObjectListing;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.model.ObjectSummary;
import com.baidu.inf.iis.bcs.request.GetObjectRequest;
import com.baidu.inf.iis.bcs.request.ListBucketRequest;
import com.baidu.inf.iis.bcs.request.ListObjectRequest;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

public class BCSUtil {
	private static final Logger log = Logger.getLogger(BCSUtil.class);
	private static String host = "bcs.duapp.com";
	private static BCSCredentials credentials;// 开发者信息
	private static BaiduBCS baiduBCS;// 百度云服务

	/**
	 * TODO 将文件上传到云存储
	 * 
	 * @param accessKey
	 *            百度APP提供
	 * @param secretKey
	 *            百度APP提供
	 * @param uploadFile
	 *            上传文件
	 * @param bucket
	 *            上传到云存储的bucket名称
	 * @param object
	 *            上传文件路径，包括扩展名，默认路径要在第一位补"/"
	 * @throws FileNotFoundException
	 */
	public static void putObjectByInputStream(String accessKey,
			String secretKey, File uploadFile, String bucket, String object)
			throws FileNotFoundException {
		credentials = new BCSCredentials(accessKey, secretKey);
		baiduBCS = new BaiduBCS(credentials, host);
		// baiduBCS.setDefaultEncoding("GBK");
		baiduBCS.setDefaultEncoding("UTF-8"); // Default UTF-8
		InputStream fileContent = new FileInputStream(uploadFile);
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("text/html");
		objectMetadata.setContentLength(uploadFile.length());
		PutObjectRequest request = new PutObjectRequest(bucket, object,
				fileContent, objectMetadata);
		ObjectMetadata result = baiduBCS.putObject(request).getResult();
		log.info(result);
	}

	/**
	 * TODO 获取bucket列表
	 * 
	 * @param accessKey
	 * @param secretKey
	 * @return
	 */
	public static List<BucketSummary> listBucket(String accessKey,
			String secretKey) {
		credentials = new BCSCredentials(accessKey, secretKey);
		baiduBCS = new BaiduBCS(credentials, host);
		ListBucketRequest listBucketRequest = new ListBucketRequest();
		BaiduBCSResponse<List<BucketSummary>> response = baiduBCS
				.listBucket(listBucketRequest);
		for (BucketSummary bucket : response.getResult()) {
			log.info(bucket);
		}
		return response.getResult();
	}

	/**
	 * TODO 通过bucket模拟分页获取云存储文件列表
	 * 
	 * @param accessKey
	 * @param secretKey
	 * @param bucket
	 *            bucket名称
	 * @param start
	 *            起始位置
	 * @param limit
	 *            数量限制
	 * @return 文件列表
	 */
	public static List<ObjectSummary> listObject(String accessKey,
			String secretKey, String bucket, int start, int limit) {
		credentials = new BCSCredentials(accessKey, secretKey);
		baiduBCS = new BaiduBCS(credentials, host);
		ListObjectRequest listObjectRequest = new ListObjectRequest(bucket);
		listObjectRequest.setStart(start);
		listObjectRequest.setLimit(limit);
		// ------------------by dir
		{
			// prefix must start with '/' and end with '/'
			// listObjectRequest.setPrefix("/1/");
			// listObjectRequest.setListModel(2);
		}
		// ------------------only object
		{
			// prefix must start with '/'
			// listObjectRequest.setPrefix("/1/");
		}
		BaiduBCSResponse<ObjectListing> response = baiduBCS
				.listObject(listObjectRequest);
		return response.getResult().getObjectSummaries();
	}

	/**
	 * TODO 获取云存储的全部信息
	 * 
	 * @param accessKey
	 *            开发者信息
	 * @param secretKey
	 *            开发者信息
	 * @param bucket
	 *            云存储bucket名称
	 * @return 文件列表
	 */
	public static List<ObjectSummary> listObject(String accessKey,
			String secretKey, String bucket) {
		credentials = new BCSCredentials(accessKey, secretKey);
		baiduBCS = new BaiduBCS(credentials, host);
		ListObjectRequest listObjectRequest = new ListObjectRequest(bucket);
		BaiduBCSResponse<ObjectListing> response = baiduBCS
				.listObject(listObjectRequest);
		return response.getResult().getObjectSummaries();
	}

	public static InputStream getObject(String accessKey, String secretKey,
			String bucket, String object) {
		credentials = new BCSCredentials(accessKey, secretKey);
		baiduBCS = new BaiduBCS(credentials, host);
		GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, object);
		BaiduBCSResponse<DownloadObject> baiduBCSResponse = baiduBCS
				.getObject(getObjectRequest);
		InputStream in = baiduBCSResponse.getResult().getContent();
		return in;
	}

	/**
	 * TODO 删除文件
	 * 
	 * @param accessKey
	 * @param secretKey
	 * @param bucket
	 * @param object
	 */
	public static void deleteObject(String accessKey, String secretKey,
			String bucket, String object) {
		credentials = new BCSCredentials(accessKey, secretKey);
		baiduBCS = new BaiduBCS(credentials, host);
		com.baidu.inf.iis.bcs.model.Empty result = baiduBCS.deleteObject(bucket, object).getResult();
		log.info(result);
	}

	/**
	 * TODO 上传文件
	 * 
	 * @param accessKey
	 * @param secretKey
	 * @param uploadFile
	 *            上传的文件
	 * @param bucket
	 *            bucket名称
	 * @param object
	 */
	public static void putObjectByFile(String accessKey, String secretKey,
			File uploadFile, String bucket, String object) {
		credentials = new BCSCredentials(accessKey, secretKey);
		baiduBCS = new BaiduBCS(credentials, host);
		PutObjectRequest request = new PutObjectRequest(bucket, object,
				uploadFile);
		ObjectMetadata metadata = new ObjectMetadata();
		// metadata.setContentType("text/html");
		request.setMetadata(metadata);
		BaiduBCSResponse<ObjectMetadata> response = baiduBCS.putObject(request);
		ObjectMetadata objectMetadata = response.getResult();
		log.info("x-bs-request-id: " + response.getRequestId());
		log.info(objectMetadata);
	}

}

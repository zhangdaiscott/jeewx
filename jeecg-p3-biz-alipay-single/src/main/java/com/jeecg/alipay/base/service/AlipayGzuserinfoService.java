package com.jeecg.alipay.base.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.jeecg.alipay.base.entity.AlipayGzuserinfo;

/**
 * 
 * @author yangqj
 * 描述：微信成员管理service
 *
 */
public interface AlipayGzuserinfoService {

	public void saveGzuserinfo(AlipayGzuserinfo alipayGzuserinfo,MultipartFile uploadfile,HttpServletRequest request);
	
	public void updateGzuserinfo(AlipayGzuserinfo alipayGzuserinfo,MultipartFile uploadfile,HttpServletRequest request);
	
	public void deleteGzuserinfo(AlipayGzuserinfo alipayGzuserinfo);

	public void syncGzuserinfos();
}

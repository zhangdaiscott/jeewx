package com.jeecg.qywx.base.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.jeecg.qywx.base.entity.QywxGzuserinfo;

/**
 * 
 * @author yangqj
 * 描述：微信成员管理service
 *
 */
public interface QywxGzuserinfoService {

	public void saveGzuserinfo(QywxGzuserinfo qywxGzuserinfo,MultipartFile uploadfile,HttpServletRequest request);
	
	public void updateGzuserinfo(QywxGzuserinfo qywxGzuserinfo,MultipartFile uploadfile,HttpServletRequest request);
	
	public void deleteGzuserinfo(QywxGzuserinfo qywxGzuserinfo);

	public void syncGzuserinfos();
}

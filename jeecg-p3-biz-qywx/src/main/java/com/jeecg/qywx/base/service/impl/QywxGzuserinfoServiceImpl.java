package com.jeecg.qywx.base.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.jeecg.qywx.account.service.AccountService;
import com.jeecg.qywx.api.core.common.AccessToken;
import com.jeecg.qywx.api.core.util.WXUpload;
import com.jeecg.qywx.api.user.JwUserAPI;
import com.jeecg.qywx.api.user.vo.User;
import com.jeecg.qywx.base.dao.QywxGroupDao;
import com.jeecg.qywx.base.dao.QywxGzuserinfoDao;
import com.jeecg.qywx.base.entity.QywxGroup;
import com.jeecg.qywx.base.entity.QywxGzuserinfo;
import com.jeecg.qywx.base.service.QywxGzuserinfoService;
/**
 * 
 * @author yangqj
 * 描述：微信成员管理service实现类
 *
 */
@Service
public class QywxGzuserinfoServiceImpl implements QywxGzuserinfoService {
	
	 @Autowired
	 private AccountService accountService;
	 
	 @Autowired
	 private QywxGzuserinfoDao qywxGzuserinfoDao;
	 @Autowired
	 private QywxGroupDao 	   qywxGroupDao;
	 //需要进行事物控制(建议像SPRING那样，抛出异常了自动回滚数据)
	 public void saveGzuserinfo(QywxGzuserinfo qywxGzuserinfo,MultipartFile uploadfile,HttpServletRequest request){
		  //把值赋值给用户
		  User user=new User();
		  user.setUserid(qywxGzuserinfo.getUserid());
		  user.setName(qywxGzuserinfo.getName());
		  if(!StringUtils.isBlank(qywxGzuserinfo.getDepartment())){
			  user.setDepartment(new Integer[]{Integer.parseInt(qywxGzuserinfo.getDepartment())});  
		  }
		  user.setPosition(qywxGzuserinfo.getPosition());
		  user.setMobile(qywxGzuserinfo.getMobile());
//		  user.setGender((Integer.parseInt(qywxGzuserinfo.getGender())+1)+"");
		  user.setGender(qywxGzuserinfo.getGender());
		  user.setEmail(qywxGzuserinfo.getEmail());
		  user.setWeixinid(qywxGzuserinfo.getWeixinid());
//		  user.setEnable(1);
//		  user.setAvatar_mediaid(qywxGzuserinfo.getAvatar());
		  user.setAvatar(qywxGzuserinfo.getAvatar());
		  //状态
		  if(!StringUtils.isBlank(qywxGzuserinfo.getSubscribeStatus())){
			  user.setStatus(Integer.valueOf(qywxGzuserinfo.getSubscribeStatus()) );
		  }
//		  user.setExtattr(JSONObject.);// 扩展属性
		  AccessToken accessToken = accountService.getAccessToken();
		  //还要进行头像更新哦
		  //上传图片
	      if(null!=uploadfile && !uploadfile.isEmpty()){
			try {
				 byte[] bytes_array = uploadfile.getBytes();
//				 String current_dir=;//当前路径
				 String current_dir=request.getServletContext().getRealPath("/upload");//当前路径
		    	 String extension_name=uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().lastIndexOf("."));
		    	 //写入到临时文件
		    	 String filename=UUID.randomUUID().toString().replace("-", "")+extension_name;
		    	 WXUpload.writeFile(bytes_array, current_dir, filename, false);
		    	 //说明我们要对图片进行解析了
//		    	 JSONObject jsonobject = WXUpload.upload(accessToken.getAccesstoken(), "image", current_dir+"//"+filename);
//		    	 String url_temp = "http://127.0.0.1:8080/jeecg-p3-web/upload/"+filename;
//		    	 String url_temp = "http://127.0.0.1:8080/jeecg-p3-web/upload/"+filename;
		    	 String url_temp = "http://"+request.getServerName()+":"+request.getServerPort()+ "/jeecg-p3-web" + "/upload/"+filename;
		    	 JSONObject jsonobject = WXUpload.upload(accessToken.getAccesstoken(), "image", url_temp);
		    	 if(!StringUtils.isBlank(jsonobject.getString("media_id")) ){
		    		 user.setAvatar_mediaid(jsonobject.getString("media_id"));
		    		 user.setAvatar(url_temp);
		    		 qywxGzuserinfo.setAvatar(url_temp);
		    	 }
//		    	 User temp_user =  JwUserAPI.getUserByUserid(user.getUserid(), accessToken.getAccesstoken());
//		    	 if(temp_user)
			} catch (IOException e) {
				e.printStackTrace();
			}
	      }
	      int result = JwUserAPI.createUser(user, accessToken.getAccesstoken());
		  //只有保存成功了才把成员插入到本地数据库中
		  if(result==0){
			 //不知道这个事物怎么控制哦
			 qywxGzuserinfoDao.insert(qywxGzuserinfo);
		  }else{
			  throw new RuntimeException("微信添加成员失败，错误代码="+result);
		  }
	 }
	@Override
	public void updateGzuserinfo(QywxGzuserinfo qywxGzuserinfo,MultipartFile uploadfile,HttpServletRequest request) {
		 //把值赋值给用户
		  User user=new User();
		  user.setUserid(qywxGzuserinfo.getUserid());
		  user.setName(qywxGzuserinfo.getName());
		  if(!StringUtils.isBlank(qywxGzuserinfo.getDepartment())){
			  user.setDepartment(new Integer[]{Integer.parseInt(qywxGzuserinfo.getDepartment())});  
		  }
		  user.setPosition(qywxGzuserinfo.getPosition());
		  user.setMobile(qywxGzuserinfo.getMobile());
//		  user.setGender((Integer.parseInt(qywxGzuserinfo.getGender())+1)+"");
		  user.setGender(qywxGzuserinfo.getGender());
		  user.setEmail(qywxGzuserinfo.getEmail());
		  user.setWeixinid(qywxGzuserinfo.getWeixinid());
//		  user.setEnable(1);
//		  user.setAvatar_mediaid(qywxGzuserinfo.getAvatar());
		  user.setAvatar(qywxGzuserinfo.getAvatar());
		  //状态
		  if(!StringUtils.isBlank(qywxGzuserinfo.getSubscribeStatus())){
			  user.setStatus(Integer.valueOf(qywxGzuserinfo.getSubscribeStatus()) );
		  }
//		  user.setExtattr(extattr); 扩展属性
		  AccessToken accessToken = accountService.getAccessToken();
		  if(null!=uploadfile && !uploadfile.isEmpty()){
				try {
					 byte[] bytes_array = uploadfile.getBytes();
//					 String current_dir=;//当前路径
//					 String current_dir=request.getServletContext().getRealPath("/upload");//当前路径
					 String current_dir=request.getRealPath("/upload");//当前路径
			    	 String extension_name=uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().lastIndexOf("."));
			    	 //写入到临时文件
			    	 String filename=UUID.randomUUID().toString().replace("-", "")+extension_name;
			    	 WXUpload.writeFile(bytes_array, current_dir, filename, false);
			    	 //说明我们要对图片进行解析了
//			    	 JSONObject jsonobject = WXUpload.upload(accessToken.getAccesstoken(), "image", current_dir+"//"+filename);
//			    	 String url_temp = request.getRequestURI() "http://127.0.0.1:8080/jeecg-p3-web/upload/"+filename;
			    	 String url_temp = "http://"+request.getServerName()+":"+request.getServerPort()+ "/jeecg-p3-web" + "/upload/"+filename;
			    	 JSONObject jsonobject = WXUpload.upload(accessToken.getAccesstoken(), "image", url_temp);
			    	 if(!StringUtils.isBlank(jsonobject.getString("media_id")) ){
			    		 user.setAvatar_mediaid(jsonobject.getString("media_id"));
			    		 user.setAvatar(url_temp);
			    		 qywxGzuserinfo.setAvatar(url_temp);
			    	 }
//			    	 User temp_user =  JwUserAPI.getUserByUserid(user.getUserid(), accessToken.getAccesstoken());
//			    	 if(temp_user)
				} catch (IOException e) {
					e.printStackTrace();
				}
		  }
		  qywxGzuserinfoDao.update(qywxGzuserinfo);
		  int result = JwUserAPI.updateUser(user, accessToken.getAccesstoken());
		  //只有保存成功了才把成员插入到本地数据库中
		  if(result!=0){
			 //不知道这个事物怎么控制哦
			 throw new RuntimeException("本地成员更新成功，微信更新成员失败，错误代码="+result);
		  }
	}
	@Override
	public void deleteGzuserinfo(QywxGzuserinfo qywxGzuserinfo) {
		AccessToken accessToken = accountService.getAccessToken();
		//先获取bena的信息，然后进行删除
		QywxGzuserinfo temp_qywxGzuserinfo = qywxGzuserinfoDao.get(qywxGzuserinfo.getId());
		int result = JwUserAPI.deleteUser(temp_qywxGzuserinfo.getUserid(), accessToken.getAccesstoken());
		qywxGzuserinfoDao.delete(qywxGzuserinfo);
		//删除失败我们也不用管吧
		/*if(result!=0){
		  //不知道这个事物怎么控制哦
		  throw new RuntimeException("本地成员删除成功，微信删除成员失败，错误代码="+result);
	    }*/
	}

	//同步数据
	@Override
	public void syncGzuserinfos() {
		AccessToken accessToken = accountService.getAccessToken();
		List<QywxGroup> qywxGroups =  qywxGroupDao.getAll(new QywxGroup());
		for (QywxGroup qywxGroup : qywxGroups) {
//			List<User> users = JwUserAPI.getUsersByDepartid(qywxGroup.getId(), null, null, accessToken.getAccesstoken());
			List<User> users = JwUserAPI.getDetailUsersByDepartid(qywxGroup.getId(), null, null, accessToken.getAccesstoken());
			if(null==users || users.size() == 0){
				continue;
			}
			for (User user : users) {
				//保存
				String userid = user.getUserid();
				QywxGzuserinfo temp_userinfo = qywxGzuserinfoDao.getByUserid(userid);
				if(null==temp_userinfo){
					QywxGzuserinfo save_user = new QywxGzuserinfo();
					String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
					save_user.setId(randomSeed);
					save_user.setCreateDate(new Date());
					
					//其它字段
					save_user.setUserid(userid);
					save_user.setName(user.getName());
					if(null!=user.getDepartment()){
						save_user.setDepartment(user.getDepartment()[0]+"");
					}
					save_user.setPosition(user.getPosition());
					save_user.setMobile(user.getMobile());
					//province
					save_user.setGender(user.getGender());
					save_user.setEmail(user.getEmail());
					save_user.setWeixinid(user.getWeixinid());
					save_user.setAvatar(user.getAvatar());
					if(null!=user.getStatus()){
						save_user.setSubscribeStatus(user.getStatus()+"");
					}else{
						save_user.setSubscribeStatus("4");	
					}
					
//					save_user.setSubscribeTime(user.);
//					save_user.setAccountid(user.get);
					qywxGzuserinfoDao.insert(save_user);
				}
			}
		}
	}
	
	 

}

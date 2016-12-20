package com.jeecg.alipay.base.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.Data;
import com.alipay.api.response.AlipayMobilePublicFollowListResponse;
import com.jeecg.alipay.account.service.AlipayAccountService;
import com.jeecg.alipay.api.base.JwGetUserInforMationAPI;
import com.jeecg.alipay.api.base.vo.GetUserInfoMateonVo.GetUserInfoMateon;
import com.jeecg.alipay.base.dao.AlipayGzuserinfoDao;
import com.jeecg.alipay.base.entity.AlipayGzuserinfo;
import com.jeecg.alipay.base.service.AlipayGzuserinfoService;
import com.jeecg.alipay.util.SystemUtil;
/**
 * 
 * @author yangqj
 * 描述：微信成员管理service实现类
 *
 */
@Service
public class AlipayGzuserinfoServiceImpl implements AlipayGzuserinfoService {

	@Autowired
	private AlipayGzuserinfoDao alipayGzuserinfoDao;
	@Autowired
	private AlipayAccountService alipayAccountService;
	
	@Override
	public void saveGzuserinfo(AlipayGzuserinfo alipayGzuserinfo, MultipartFile uploadfile, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateGzuserinfo(AlipayGzuserinfo alipayGzuserinfo, MultipartFile uploadfile,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteGzuserinfo(AlipayGzuserinfo alipayGzuserinfo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void syncGzuserinfos() {
		// TODO Auto-generated method stub
		try {
			//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
			AlipayMobilePublicFollowListResponse response = JwGetUserInforMationAPI.followlistQuery(null, new GetUserInfoMateon(),alipayAccountService.getAlipayConfig());
			//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
			Data data  = response.getData();
			List<String> useridlist = data.getUserIdList();
			for(String id :useridlist){
				//update-begin--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
				AlipayGzuserinfo tempUser = alipayGzuserinfoDao.getByUserid(id);
				if(tempUser == null){
					AlipayGzuserinfo user = new AlipayGzuserinfo();
					user.setAccountid(SystemUtil.getOnlieAlipayAccountId());
					user.setUserid(id);
					user.setSubscribeStatus("1");
					String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
					user.setId(randomSeed);
					user.setCreateDate(new Date());
					alipayGzuserinfoDao.insert(user);
				}
				//update-end--author:zhangjiaqiang Date:20161108 for:#1487 【开源项目】开源支付窗服务窗单公众号项目
			}
		} catch (AlipayApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
//	 @Autowired
//	 private AccountService accountService;
//	 
//	 @Autowired
//	 private AlipayGzuserinfoDao alipayGzuserinfoDao;
//	 //需要进行事物控制(建议像SPRING那样，抛出异常了自动回滚数据)
//	 public void saveGzuserinfo(AlipayGzuserinfo alipayGzuserinfo,MultipartFile uploadfile,HttpServletRequest request){
//		  //把值赋值给用户
//		  User user=new User();
//		  user.setUserid(alipayGzuserinfo.getUserid());
//		  user.setName(alipayGzuserinfo.getName());
//		  if(!StringUtils.isBlank(alipayGzuserinfo.getDepartment())){
//			  user.setDepartment(new Integer[]{Integer.parseInt(alipayGzuserinfo.getDepartment())});  
//		  }
//		  user.setPosition(alipayGzuserinfo.getPosition());
//		  user.setMobile(alipayGzuserinfo.getMobile());
////		  user.setGender((Integer.parseInt(alipayGzuserinfo.getGender())+1)+"");
//		  user.setGender(alipayGzuserinfo.getGender());
//		  user.setEmail(alipayGzuserinfo.getEmail());
//		  user.setWeixinid(alipayGzuserinfo.getWeixinid());
////		  user.setEnable(1);
////		  user.setAvatar_mediaid(alipayGzuserinfo.getAvatar());
//		  user.setAvatar(alipayGzuserinfo.getAvatar());
//		  //状态
//		  if(!StringUtils.isBlank(alipayGzuserinfo.getSubscribeStatus())){
//			  user.setStatus(Integer.valueOf(alipayGzuserinfo.getSubscribeStatus()) );
//		  }
////		  user.setExtattr(JSONObject.);// 扩展属性
//		  AccessToken accessToken = accountService.getAccessToken();
//		  //还要进行头像更新哦
//		  //上传图片
//	      if(null!=uploadfile && !uploadfile.isEmpty()){
//			try {
//				 byte[] bytes_array = uploadfile.getBytes();
////				 String current_dir=;//当前路径
//				 String current_dir=request.getServletContext().getRealPath("/upload");//当前路径
//		    	 String extension_name=uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().lastIndexOf("."));
//		    	 //写入到临时文件
//		    	 String filename=UUID.randomUUID().toString().replace("-", "")+extension_name;
//		    	 WXUpload.writeFile(bytes_array, current_dir, filename, false);
//		    	 //说明我们要对图片进行解析了
////		    	 JSONObject jsonobject = WXUpload.upload(accessToken.getAccesstoken(), "image", current_dir+"//"+filename);
////		    	 String url_temp = "http://127.0.0.1:8080/jeecg-p3-web/upload/"+filename;
////		    	 String url_temp = "http://127.0.0.1:8080/jeecg-p3-web/upload/"+filename;
//		    	 String url_temp = "http://"+request.getServerName()+":"+request.getServerPort()+ "/jeecg-p3-web" + "/upload/"+filename;
//		    	 JSONObject jsonobject = WXUpload.upload(accessToken.getAccesstoken(), "image", url_temp);
//		    	 if(!StringUtils.isBlank(jsonobject.getString("media_id")) ){
//		    		 user.setAvatar_mediaid(jsonobject.getString("media_id"));
//		    		 user.setAvatar(url_temp);
//		    		 alipayGzuserinfo.setAvatar(url_temp);
//		    	 }
////		    	 User temp_user =  JwUserAPI.getUserByUserid(user.getUserid(), accessToken.getAccesstoken());
////		    	 if(temp_user)
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//	      }
//	      int result = JwUserAPI.createUser(user, accessToken.getAccesstoken());
//		  //只有保存成功了才把成员插入到本地数据库中
//		  if(result==0){
//			 //不知道这个事物怎么控制哦
//			 alipayGzuserinfoDao.insert(alipayGzuserinfo);
//		  }else{
//			  throw new RuntimeException("微信添加成员失败，错误代码="+result);
//		  }
//	 }
//	@Override
//	public void updateGzuserinfo(AlipayGzuserinfo alipayGzuserinfo,MultipartFile uploadfile,HttpServletRequest request) {
//		 //把值赋值给用户
//		  User user=new User();
//		  user.setUserid(alipayGzuserinfo.getUserid());
//		  user.setName(alipayGzuserinfo.getName());
//		  if(!StringUtils.isBlank(alipayGzuserinfo.getDepartment())){
//			  user.setDepartment(new Integer[]{Integer.parseInt(alipayGzuserinfo.getDepartment())});  
//		  }
//		  user.setPosition(alipayGzuserinfo.getPosition());
//		  user.setMobile(alipayGzuserinfo.getMobile());
////		  user.setGender((Integer.parseInt(alipayGzuserinfo.getGender())+1)+"");
//		  user.setGender(alipayGzuserinfo.getGender());
//		  user.setEmail(alipayGzuserinfo.getEmail());
//		  user.setWeixinid(alipayGzuserinfo.getWeixinid());
////		  user.setEnable(1);
////		  user.setAvatar_mediaid(alipayGzuserinfo.getAvatar());
//		  user.setAvatar(alipayGzuserinfo.getAvatar());
//		  //状态
//		  if(!StringUtils.isBlank(alipayGzuserinfo.getSubscribeStatus())){
//			  user.setStatus(Integer.valueOf(alipayGzuserinfo.getSubscribeStatus()) );
//		  }
////		  user.setExtattr(extattr); 扩展属性
//		  AccessToken accessToken = accountService.getAccessToken();
//		  if(null!=uploadfile && !uploadfile.isEmpty()){
//				try {
//					 byte[] bytes_array = uploadfile.getBytes();
////					 String current_dir=;//当前路径
////					 String current_dir=request.getServletContext().getRealPath("/upload");//当前路径
//					 String current_dir=request.getRealPath("/upload");//当前路径
//			    	 String extension_name=uploadfile.getOriginalFilename().substring(uploadfile.getOriginalFilename().lastIndexOf("."));
//			    	 //写入到临时文件
//			    	 String filename=UUID.randomUUID().toString().replace("-", "")+extension_name;
//			    	 WXUpload.writeFile(bytes_array, current_dir, filename, false);
//			    	 //说明我们要对图片进行解析了
////			    	 JSONObject jsonobject = WXUpload.upload(accessToken.getAccesstoken(), "image", current_dir+"//"+filename);
////			    	 String url_temp = request.getRequestURI() "http://127.0.0.1:8080/jeecg-p3-web/upload/"+filename;
//			    	 String url_temp = "http://"+request.getServerName()+":"+request.getServerPort()+ "/jeecg-p3-web" + "/upload/"+filename;
//			    	 JSONObject jsonobject = WXUpload.upload(accessToken.getAccesstoken(), "image", url_temp);
//			    	 if(!StringUtils.isBlank(jsonobject.getString("media_id")) ){
//			    		 user.setAvatar_mediaid(jsonobject.getString("media_id"));
//			    		 user.setAvatar(url_temp);
//			    		 alipayGzuserinfo.setAvatar(url_temp);
//			    	 }
////			    	 User temp_user =  JwUserAPI.getUserByUserid(user.getUserid(), accessToken.getAccesstoken());
////			    	 if(temp_user)
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//		  }
//		  alipayGzuserinfoDao.update(alipayGzuserinfo);
//		  int result = JwUserAPI.updateUser(user, accessToken.getAccesstoken());
//		  //只有保存成功了才把成员插入到本地数据库中
//		  if(result!=0){
//			 //不知道这个事物怎么控制哦
//			 throw new RuntimeException("本地成员更新成功，微信更新成员失败，错误代码="+result);
//		  }
//	}
//	@Override
//	public void deleteGzuserinfo(AlipayGzuserinfo alipayGzuserinfo) {
//		AccessToken accessToken = accountService.getAccessToken();
//		//先获取bena的信息，然后进行删除
//		AlipayGzuserinfo temp_alipayGzuserinfo = alipayGzuserinfoDao.get(alipayGzuserinfo.getId());
//		int result = JwUserAPI.deleteUser(temp_alipayGzuserinfo.getUserid(), accessToken.getAccesstoken());
//		alipayGzuserinfoDao.delete(alipayGzuserinfo);
//		//删除失败我们也不用管吧
//		/*if(result!=0){
//		  //不知道这个事物怎么控制哦
//		  throw new RuntimeException("本地成员删除成功，微信删除成员失败，错误代码="+result);
//	    }*/
//	}
//
//	//同步数据
//	@Override
//	public void syncGzuserinfos() {
//		AccessToken accessToken = accountService.getAccessToken();
//		List<QywxGroup> alipayGroups =  alipayGroupDao.getAll(new QywxGroup());
//		for (QywxGroup alipayGroup : alipayGroups) {
////			List<User> users = JwUserAPI.getUsersByDepartid(alipayGroup.getId(), null, null, accessToken.getAccesstoken());
//			List<User> users = JwUserAPI.getDetailUsersByDepartid(alipayGroup.getId(), null, null, accessToken.getAccesstoken());
//			if(null==users || users.size() == 0){
//				continue;
//			}
//			for (User user : users) {
//				//保存
//				String userid = user.getUserid();
//				AlipayGzuserinfo temp_userinfo = alipayGzuserinfoDao.getByUserid(userid);
//				if(null==temp_userinfo){
//					AlipayGzuserinfo save_user = new AlipayGzuserinfo();
//					String randomSeed = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
//					save_user.setId(randomSeed);
//					save_user.setCreateDate(new Date());
//					
//					//其它字段
//					save_user.setUserid(userid);
//					save_user.setName(user.getName());
//					if(null!=user.getDepartment()){
//						save_user.setDepartment(user.getDepartment()[0]+"");
//					}
//					save_user.setPosition(user.getPosition());
//					save_user.setMobile(user.getMobile());
//					//province
//					save_user.setGender(user.getGender());
//					save_user.setEmail(user.getEmail());
//					save_user.setWeixinid(user.getWeixinid());
//					save_user.setAvatar(user.getAvatar());
//					if(null!=user.getStatus()){
//						save_user.setSubscribeStatus(user.getStatus()+"");
//					}else{
//						save_user.setSubscribeStatus("4");	
//					}
//					
////					save_user.setSubscribeTime(user.);
////					save_user.setAccountid(user.get);
//					alipayGzuserinfoDao.insert(save_user);
//				}
//			}
//		}
//	}
//	
//	 

}

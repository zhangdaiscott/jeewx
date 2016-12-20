package com.jeecg.qywx.core.service.impl;

import org.jeecgframework.p3.core.logger.Logger;
import org.jeecgframework.p3.core.logger.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeecg.qywx.account.dao.QywxAccountDao;
import com.jeecg.qywx.account.dao.QywxAgentDao;
import com.jeecg.qywx.account.entity.QywxAccount;
import com.jeecg.qywx.account.entity.QywxAgent;
import com.jeecg.qywx.core.service.SignForWeixinService;
import com.jeecg.qywx.core.util.WXBizMsgCrypt;

@Service("signForWeixinService")
public class SignForWeixinServiceImpl implements SignForWeixinService {
	
	private static final Logger logger = LoggerFactory.getLogger(SignForWeixinServiceImpl.class);
	
	@Autowired
	private QywxAgentDao qywxAgentDao;
	@Autowired
	private QywxAccountDao qywxAccountDao;
	
    public String checkSignature(String signature, String timestamp, String nonce,String echostr,String corpid,Integer appid) {
    
    	QywxAccount accountInfo = qywxAccountDao.getQywxAccountByCorpid(corpid);
    	if(accountInfo!=null){
    		QywxAgent appInfo = qywxAgentDao.getQywxAgentByAccountIdAndAppid(accountInfo.getId(), appid.toString());
    		if (appInfo!=null) {
    			String aesKey = appInfo.getEncodingAESKey();
    			String token = appInfo.getToken();
    			Integer appID = Integer.valueOf(appInfo.getWxAgentid());
    			// 取得与回调参数中应用ID相同的记录
    			if (appid != null && appid.equals(appID)) {
    				try {
    					WXBizMsgCrypt wxcpt = new WXBizMsgCrypt(token, aesKey,corpid);
    					String sEchoStr = "";
    					sEchoStr = wxcpt.VerifyURL(signature, timestamp, nonce, echostr);
    					logger.info("[CHECKSIGNATURE]", "checkSignature verifyurl echostr:{}", sEchoStr);
    					return sEchoStr;
    				} catch (Exception e) {
    					// 验证URL失败，错误原因请查看异常
    					e.printStackTrace();
    				}
    			}
    		}
    	}
    	return "";
   
    }
    /**
     * 将字节数组转换为十六进制字符串
     * 
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
                strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }
    /**
     * 将字节转换为十六进制字符串
     * 
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];
        String s = new String(tempArr);
        return s;
    }

}

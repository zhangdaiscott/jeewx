package weixin.idea.extend.function;

import javax.servlet.http.HttpServletRequest;

import weixin.guanjia.core.entity.message.resp.TextMessageResp;

/**
 * 关键字功能接口
 * @author zhangdaihao
 *
 */
public interface KeyServiceI{

	/**
	 * 获取接口关键字，例如："翻译"
	 * @return
	 */
    String getKey();
	
   /**
    * 针对关键字的功能处理方法
    * @param content      请求文本
    * @param textMessage  默认回复此文本消息
    * @param request      请求
    * @return
    */
	String excute(String content,TextMessageResp defaultMessage,HttpServletRequest request);
}

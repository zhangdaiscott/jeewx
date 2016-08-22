package weixin.idea.extend.function.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.jeecgframework.core.util.ApplicationContextUtil;

import weixin.guanjia.account.service.WeixinAccountServiceI;
import weixin.guanjia.core.entity.message.resp.Article;
import weixin.guanjia.core.entity.message.resp.NewsMessageResp;
import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.util.MessageUtil;
import weixin.idea.extend.function.KeyServiceI;

/**
 * 微网站
 * @author zhangdaihao
 *
 */
public class CmsService implements KeyServiceI {
	
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		WeixinAccountServiceI weixinAccountService =(WeixinAccountServiceI)ApplicationContextUtil.getContext().getBean("weixinAccountService");
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("微网站");
		article.setDescription("");
		article.setPicUrl(bundler.getString("domain")+ "/webpage/weixin/cms/images/index.jpg");
		// 此userid后期需要通过高级接口获取到微信帐号，此处先以加密后的ID为参数进行传递
		String accountid = weixinAccountService.findByToUsername(defaultMessage.getFromUserName()).getId();
		article.setUrl(bundler.getString("domain")+ "/cmsController.do?goPage&page=index&accountid="+ accountid + "&userid="+ defaultMessage.getToUserName());
		articleList.add(article);
		NewsMessageResp newsMessage = new NewsMessageResp();
		newsMessage.setToUserName(defaultMessage.getToUserName());
		newsMessage.setFromUserName(defaultMessage.getFromUserName());
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		newsMessage.setArticleCount(articleList.size());
		newsMessage.setArticles(articleList);
		return MessageUtil.newsMessageToXml(newsMessage);
	}

	@Override
	public String getKey() {

		return "微信CMS,CMS";
	}

}

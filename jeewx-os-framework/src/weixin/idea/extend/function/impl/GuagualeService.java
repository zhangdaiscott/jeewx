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
 * 刮刮乐
 * @author zhangdaihao
 *
 */
public class GuagualeService implements KeyServiceI {

	@Override
	public String excute(String content, TextMessageResp defaultMessage,
			HttpServletRequest request) {
		WeixinAccountServiceI weixinAccountService = (WeixinAccountServiceI) ApplicationContextUtil.getContext().getBean("weixinAccountService");
		String accountid = weixinAccountService.findByToUsername(defaultMessage.getFromUserName()).getId();
		ResourceBundle bundler = ResourceBundle.getBundle("sysConfig");
		List<Article> articleList = new ArrayList<Article>();
		Article article = new Article();
		article.setTitle("刮刮乐");
		article.setDescription("刮刮乐咯");
		article
				.setPicUrl(bundler.getString("domain")
						+ "/plug-in/weixin/images/ggl/card.png");
		article.setUrl(bundler.getString("domain")
				+ "/zpController.do?goGglNew&accountid="+accountid+"&openId="+defaultMessage.getToUserName());
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

		return "大转盘";
	}

}

package weixin.idea.extend.function.impl;

import javax.servlet.http.HttpServletRequest;

import weixin.guanjia.core.entity.message.resp.TextMessageResp;
import weixin.guanjia.core.service.impl.BaiduTranslateService;
import weixin.guanjia.core.util.MessageUtil;
import weixin.idea.extend.function.KeyServiceI;

/**
 * 关键字：翻译 功能类：weixin.idea.key.function.impl.FanYiKeyService 描述：调用百度的翻译接口进行翻译
 * 
 * @author zhangdaihao
 * 
 */
public class FanYiKeyService implements KeyServiceI {

	@Override
	public String excute(String content, TextMessageResp textMessage,
			HttpServletRequest request) {
		String keyWord = content.replaceAll("^翻译", "").trim();
		String returnMessage = null;
		if ("".equals(keyWord)) {
			returnMessage = getTranslateUsage();
		} else {
			returnMessage = BaiduTranslateService.translate(keyWord);
		}
		textMessage.setContent(returnMessage);
		return MessageUtil.textMessageToXml(textMessage);
	}

	@Override
	public String getKey() {
		return "翻译";
	}

	/**
	 * Q译通使用指南
	 * 
	 * @return
	 */
	public static String getTranslateUsage() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("微译使用指南").append("\n\n");
		buffer.append("微译为用户提供专业的多语言翻译服务，目前支持以下翻译方向：").append("\n");
		buffer.append("    中 -> 英").append("\n");
		buffer.append("    英 -> 中").append("\n");
		buffer.append("    日 -> 中").append("\n\n");
		buffer.append("使用示例：").append("\n");
		buffer.append("    翻译我是中国人").append("\n");
		buffer.append("    翻译dream").append("\n");
		buffer.append("    翻译さようなら").append("\n\n");
		buffer.append("回复“?”显示主菜单");
		return buffer.toString();
	}

}

package weixin.guanjia.message.dao;

import org.jeecgframework.minidao.annotation.Arguments;
import org.jeecgframework.minidao.annotation.MiniDao;
import org.jeecgframework.minidao.annotation.Sql;

import weixin.guanjia.message.entity.TextTemplate;

@MiniDao
public interface TextTemplateDao {

	/**
	 * 根据[accountid][templateName]获取对应的文本消息
	 * @param accountid
	 * @return
	 */
	@Sql("select * from weixin_texttemplate where accountid=:accountid and templateName=:templateName")
	@Arguments( { "accountid", "templateName"})
	public TextTemplate getTextTemplate(String accountid,String templateName);
}

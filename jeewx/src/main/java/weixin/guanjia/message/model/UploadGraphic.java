package weixin.guanjia.message.model;

import java.util.List;

/**
 * 图文消息上传到微信对应参数实体
 */
public class UploadGraphic {

	private List<BaseGraphic> articles;

	public List<BaseGraphic> getArticles() {
		return articles;
	}

	public void setArticles(List<BaseGraphic> articles) {
		this.articles = articles;
	}
}
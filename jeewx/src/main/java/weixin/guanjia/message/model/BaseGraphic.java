package weixin.guanjia.message.model;

/**
 * 单个图文消息上传到微信对应参数实体
 */
public class BaseGraphic {
	private String title;//标题
	private String thumb_media_id;//	图文消息的封面图片素材id（必须是永久mediaID）
   	private String author;
	private String digest;//仅有单图文消息才有摘要，多图文此处为空。如果本字段为没有填写，则默认抓取正文前64个字
	/**
   	 * 是否显示封面，1为显示，0为不显示
   	 */
   	private String show_cover_pic;
	private String content;//图文消息的具体内容，支持HTML标签，必须少于2万字符
	private String content_source_url;//图文消息的原文地址，即点击“阅读原文”后的URL
   
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent_source_url() {
		return content_source_url;
	}
	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public String getShow_cover_pic() {
		return show_cover_pic;
	}
	public void setShow_cover_pic(String show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
}

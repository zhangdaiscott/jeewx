package weixin.guanjia.message.model;

public class TextMessageKf extends BaseMessage {
	private TextItem text;

	public TextItem getText() {
		return text;
	}
	public void setText(TextItem text) {
		this.text = text;
	}
}

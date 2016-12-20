package com.jeecg.qywx.core.weixin.model;

public class TextMessageKf extends BaseMessage {
	private TextItem text;

	public TextItem getText() {
		return text;
	}
	public void setText(TextItem text) {
		this.text = text;
	}
}

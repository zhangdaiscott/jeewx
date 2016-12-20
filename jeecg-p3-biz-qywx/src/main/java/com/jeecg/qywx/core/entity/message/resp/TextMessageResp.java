package com.jeecg.qywx.core.entity.message.resp;


public class TextMessageResp extends BaseMessageResp{
	// 回复的消息内容
    private String Content;
    public String getContent() {
            return Content;
    }
    public void setContent(String content) {
            Content = content;
    }
}

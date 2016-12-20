package com.jeecg.qywx.core.weixin.model.req;

/**
 * 图片小小
 * @author Administrator
 *
 */
public class ImageMessage extends BaseMessage{
	// 图片链接
    private String PicUrl;
    public String getPicUrl() {
        return PicUrl;
    }
    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }
}

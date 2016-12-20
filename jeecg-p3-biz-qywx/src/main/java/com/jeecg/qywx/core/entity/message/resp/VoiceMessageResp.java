package com.jeecg.qywx.core.entity.message.resp;

/**
 * 语音消息
 * @author Administrator
 *
 */
public class VoiceMessageResp extends BaseMessageResp{
	// 语音
    private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
    
}

package com.tools.common.wxtool.wechat.message.request;

/**
 * 请求消息之语音消息
 * 
 * @author sunlight
 *
 */
public class VoiceMessage extends BaseMessage {
	/**
	 * 媒体ID
	 */
	private String MediaId;
	/**
	 * 语音格式
	 */
	private String Format;

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getFormat() {
		return Format;
	}

	public void setFormat(String format) {
		Format = format;
	}

}

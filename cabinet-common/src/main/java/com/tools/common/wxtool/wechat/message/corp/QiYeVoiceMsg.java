package com.tools.common.wxtool.wechat.message.corp;

import lombok.Data;

/**
 * 语音消息
 * @author Sunlight
 *
 */
@Data
public class QiYeVoiceMsg extends QiYeBaseMsg {
	/**
	 * 媒体资源文件 ID
	 */
	private String media_id;

	public QiYeVoiceMsg(String mediaId) {
		super();
		media_id = mediaId;
	}
	
}

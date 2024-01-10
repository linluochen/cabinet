package com.tools.common.wxtool.wechat.message.corp;

import lombok.Data;

/**
 *视频消息
 * @author Sunlight
 *
 */
@Data
public class QiYeVideoMsg extends QiYeBaseMsg{
	private String media_id;
	private String title;
	private String description;
	
	public QiYeVideoMsg(String mediaId, String title, String description) {
		super();
		this.media_id = mediaId;
		this.title = title;
		this.description = description;
	}
}

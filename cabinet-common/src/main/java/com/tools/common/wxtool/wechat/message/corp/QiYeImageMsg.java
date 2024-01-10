package com.tools.common.wxtool.wechat.message.corp;

/**
 * 图片消息
 * @author Sunlight
 *
 */
public class QiYeImageMsg extends QiYeBaseMsg {
	private String media_id;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String mediaId) {
		media_id = mediaId;
	}

	public QiYeImageMsg(String mediaId) {
		super();
		media_id = mediaId;
	}
	
}

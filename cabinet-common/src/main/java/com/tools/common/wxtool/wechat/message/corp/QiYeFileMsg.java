package com.tools.common.wxtool.wechat.message.corp;

/**
 * 文件消息
 * @author Sunlight
 *
 */
public class QiYeFileMsg extends QiYeBaseMsg {
	private String media_id;

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String mediaId) {
		media_id = mediaId;
	}

	public QiYeFileMsg(String mediaId) {
		super();
		media_id = mediaId;
	}
	
	public QiYeFileMsg() {
		super();
	}
}

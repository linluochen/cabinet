package com.tools.common.wxtool.wechat.message.corp;

import lombok.Data;

import java.util.List;

/**
 * 多图文混排消息
 * 
 * @author Sunlight
 *
 */
@Data
public class QiYeMpNewsMsg extends QiYeBaseMsg {

	/**
	 * 图文消息个数，限制为10条以内
	 */
	private int mpArticleCount;
	/**
	 * 多条图文消息信息，默认第一个item为大图
	 */
	private List<MpArticle> articles;


	public QiYeMpNewsMsg() {
		super();
	}

}

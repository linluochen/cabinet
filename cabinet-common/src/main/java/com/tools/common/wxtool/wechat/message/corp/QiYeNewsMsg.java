package com.tools.common.wxtool.wechat.message.corp;

import lombok.Data;

import java.util.List;

/**
 * 图文混排的消息
 * 
 * @author Sunlight
 * 
 */
@Data
public class QiYeNewsMsg extends QiYeBaseMsg {

	/**
	 * 图文消息个数，限制为10条以内
	 */
	private int ArticleCount;

	/**
	 * 多条图文消息信息，默认第一个item为大图
	 */
	private List<Article> Articles;

	public QiYeNewsMsg(int articleCount, List<Article> articles) {
		super();
		ArticleCount = articleCount;
		Articles = articles;
	}

	public QiYeNewsMsg() {
		super();
	}
}

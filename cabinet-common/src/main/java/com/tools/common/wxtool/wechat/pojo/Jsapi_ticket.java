package com.tools.common.wxtool.wechat.pojo;

public class Jsapi_ticket {

	private Integer errcode;
	private String errmsg;
	private String ticket;
	private Integer expires_in;
	public Integer getErrcode() {
		return errcode;
	}
	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}
	public String getErrmsg() {
		return errmsg;
	}
	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}
	public String getTicket() {
		return ticket;
	}
	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expiresIn) {
		expires_in = expiresIn;
	}
	
	
}

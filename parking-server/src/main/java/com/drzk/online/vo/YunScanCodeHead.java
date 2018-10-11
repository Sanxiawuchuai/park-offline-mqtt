package com.drzk.online.vo;

import java.io.Serializable;

/** 扫码出入场头部 */
public class YunScanCodeHead implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 205001136617859487L;
	private String parkId;
	private String replyTopic;
	/** 具体方法 */
	private String method;
	
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	/** 车场编号 */
	public String getParkId()
	{
		return parkId;
	}
	/** 车场编号 */
	public void setParkId(String parkId)
	{
		this.parkId = parkId;
	}
	/** 回复主题 */
	public String getReplyTopic()
	{
		return replyTopic;
	}
	/** 回复主题 */
	public void setReplyTopic(String replyTopic)
	{
		this.replyTopic = replyTopic;
	}
	
}

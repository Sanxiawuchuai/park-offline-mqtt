package com.drzk.service.entity;

import java.io.Serializable;

/// <summary>
/// 通信头
/// </summary>
public class Head implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -327630079526817594L;
	//答应主题 
	private String replyTopic;
	//版本号
	private String version;
	//具体方法
	private String method;
	
	public String getReplyTopic() {
		return replyTopic;
	}
	public void setReplyTopic(String replyTopic) {
		this.replyTopic = replyTopic;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	} 

}

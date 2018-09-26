package com.drzk.service.entity;
/// <summary>
/// 通信头
/// </summary>
public class Head {
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

package com.drzk.service.entity;

import java.io.Serializable;

//读取系统时间返回实体
public class ReadSysTimeBodyReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8054067641134902119L;
	private String uId;
	//时间
	private String sysTime;
	public String getSysTime() {
		return sysTime;
	}
	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
}

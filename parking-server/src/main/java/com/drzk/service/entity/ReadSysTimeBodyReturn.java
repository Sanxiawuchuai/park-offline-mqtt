package com.drzk.service.entity;
//读取系统时间返回实体
public class ReadSysTimeBodyReturn  extends SuperBody{
	//时间
	private String sysTime;
	public String getSysTime() {
		return sysTime;
	}
	public void setSysTime(String sysTime) {
		this.sysTime = sysTime;
	}
	
}

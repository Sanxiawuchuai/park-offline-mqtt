package com.drzk.service.entity;

import java.util.Date;

//加载系统时间
public class LoadSysTimeBody  extends SuperBody{
	//时间
	private Date sysTime;
	
	public Date getSysTime() {
		return sysTime;
	}
	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}
	
}

package com.drzk.service.entity;

import java.io.Serializable;
import java.util.Date;

//加载系统时间
public class LoadSysTimeBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5108072201475871448L;
	private String uId;
	//时间
	private Date sysTime;
	
	public Date getSysTime() {
		return sysTime;
	}
	public void setSysTime(Date sysTime) {
		this.sysTime = sysTime;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
}

package com.drzk.service.entity;

import java.io.Serializable;

//读取系统时间
public class ReadSysTimeBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6504945436517776484L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}

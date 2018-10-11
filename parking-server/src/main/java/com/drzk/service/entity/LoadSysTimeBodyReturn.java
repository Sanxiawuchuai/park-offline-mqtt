package com.drzk.service.entity;

import java.io.Serializable;

//读取系统时间信息
public class LoadSysTimeBodyReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6778446263174220685L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}

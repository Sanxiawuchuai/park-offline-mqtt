package com.drzk.service.entity;

import java.io.Serializable;

//读取用户信息实体
public class ReadUserMsgBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5520666626257125486L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}

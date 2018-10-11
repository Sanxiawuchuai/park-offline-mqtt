package com.drzk.service.entity;

import java.io.Serializable;

//读取车场系统参数实体
public class ReadParkSysBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -428864478950694435L;

	private String uId;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}
}

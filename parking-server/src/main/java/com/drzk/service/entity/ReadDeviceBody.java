package com.drzk.service.entity;

import java.io.Serializable;

//读取设备信息
public class ReadDeviceBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2261736217469607883L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}

}

package com.drzk.service.entity;

import java.io.Serializable;

//2.1. 广播读取设备信息实体
public class BroadcastReadDeviceMsgBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -457203491338532630L;
	
    private String uId;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

	
}

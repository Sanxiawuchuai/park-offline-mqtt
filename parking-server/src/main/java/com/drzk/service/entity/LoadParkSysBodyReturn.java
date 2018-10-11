package com.drzk.service.entity;

import java.io.Serializable;

//加载车场系统参数返回实体
public class LoadParkSysBodyReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1245176875579630907L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}

}

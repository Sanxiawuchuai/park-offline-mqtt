package com.drzk.service.entity;

import java.io.Serializable;

// 升级文件实体
public class UpFileBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2842076165858525080L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}

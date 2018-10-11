package com.drzk.service.entity;

import java.io.Serializable;

//2.9. 加载车场系统参数返回实体
public class LoadParkParamReturnBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5851037126185863424L;

	private String uId;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}

}

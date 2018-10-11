package com.drzk.service.entity;

import java.io.Serializable;

//2.10. 读取车场系统参数
public class ReadParkParamBody  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5457219303399001063L;

	private String uId;

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}
}

package com.drzk.service.entity;

import java.io.Serializable;

//2.17. 车场收费测试返回实体
public class ParkChargeReturnBody  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -9205768169321157483L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}

}

package com.drzk.service.entity;

import java.io.Serializable;

//2.18. 加载收费标准返回实体
public class LoadChargeStandardReturnBody  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5421198139384337084L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}

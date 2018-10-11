package com.drzk.service.entity;

import java.io.Serializable;

//读取网络参数
public class ReadNetWorkBody  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7887347901363052533L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}

}

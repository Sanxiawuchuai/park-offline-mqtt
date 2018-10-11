package com.drzk.service.entity;

import java.io.Serializable;

//道闸控制返回实体
public class GateBodyReturn  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7862621821091489016L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
}

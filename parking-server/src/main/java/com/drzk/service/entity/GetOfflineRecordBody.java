package com.drzk.service.entity;

import java.io.Serializable;

//2.14. 提取脱机记录实体
public class GetOfflineRecordBody  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4206361188779129362L;

	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
}

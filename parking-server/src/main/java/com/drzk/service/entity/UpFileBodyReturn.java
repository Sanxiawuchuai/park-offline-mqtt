package com.drzk.service.entity;

import java.io.Serializable;

//升级文件实体
public class UpFileBodyReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1019255347743169387L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}

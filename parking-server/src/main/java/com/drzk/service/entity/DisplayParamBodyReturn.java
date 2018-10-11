package com.drzk.service.entity;

import java.io.Serializable;

//显示屏参数信息返回实体
public class DisplayParamBodyReturn  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2142615016968390571L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}


	
}

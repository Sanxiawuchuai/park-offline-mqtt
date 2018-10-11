package com.drzk.service.entity;

import java.io.Serializable;

//加载用户信息返回实体
public class LoadUserMsgBodyReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6305030634316640021L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}

}

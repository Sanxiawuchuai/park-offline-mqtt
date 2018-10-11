package com.drzk.service.entity;

import java.io.Serializable;

//删除用户信息返回实体
public class DeleteUserMsgBodyReturn  implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3364413290612304315L;

	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}

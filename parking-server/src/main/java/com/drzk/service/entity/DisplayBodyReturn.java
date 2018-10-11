package com.drzk.service.entity;

import java.io.Serializable;

// 显示输出返回实体
public class DisplayBodyReturn  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2858674900052320560L;

	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}

package com.drzk.service.entity;

import java.io.Serializable;

//2.15. 获取车场记录
public class GetParkRecordBody  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1743298557774979350L;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}

}

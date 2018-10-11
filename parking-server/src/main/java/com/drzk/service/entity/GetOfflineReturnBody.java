package com.drzk.service.entity;

import java.io.Serializable;

//2.14. 提取脱机记录
public class GetOfflineReturnBody implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4690021053990050829L;
	//记录数
	private String recordNo;
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
}

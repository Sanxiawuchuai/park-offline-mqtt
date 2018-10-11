package com.drzk.service.entity;

import java.io.Serializable;

//3.2. 上传记录回复(主板订阅)实体
public class UpRecordReplyBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5801889674395111078L;
	private String uId;
	private String recordNo;//流水号uId
	public String getRecordNo() {
		return recordNo;
	}
	public void setRecordNo(String recordNo) {
		this.recordNo = recordNo;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
}

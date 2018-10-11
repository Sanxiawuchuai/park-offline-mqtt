package com.drzk.service.entity;

import java.io.Serializable;

//3.8. 上传日志信息回复(主板订阅)实体
public class UpParkLogReplyBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4216298425412933681L;
	private String uId;
	private String recordNo;//流水号 
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

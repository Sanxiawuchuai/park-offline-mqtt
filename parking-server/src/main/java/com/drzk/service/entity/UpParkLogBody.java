package com.drzk.service.entity;

import java.io.Serializable;

//3.7. 上传日志信息(服务器订阅)实体
public class UpParkLogBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3921944662904116803L;
	private String uId;
	private String conIp;//控制器Ip
	private String recordNo;//流水号
	public String getConIp() {
		return conIp;
	}
	public void setConIp(String conIp) {
		this.conIp = conIp;
	}
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

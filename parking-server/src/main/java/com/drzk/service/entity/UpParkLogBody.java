package com.drzk.service.entity;
//3.7. 上传日志信息(服务器订阅)实体
public class UpParkLogBody  extends SuperBody{
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
	
}

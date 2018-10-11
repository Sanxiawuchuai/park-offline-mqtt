package com.drzk.service.entity;

import java.io.Serializable;

//2.16. 删除车场记录
public class DeleteParkRecordBody  implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 279296439940598310L;
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

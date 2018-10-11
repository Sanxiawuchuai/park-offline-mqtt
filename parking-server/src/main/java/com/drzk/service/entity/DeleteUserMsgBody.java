package com.drzk.service.entity;

import java.io.Serializable;

//删除用户信息实体
public class DeleteUserMsgBody implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4083368832339110691L;
	private String uId;
	//车辆类型 
	private String carType;
	//储值车工作模式 00-自动，01-确认
	private String carNo;
	//介质类型 0-无，1-IC卡，2-IC转ID卡，3-ID卡，4-ETC，5-CPU，6-纸票，7-身份证UID
	private String mediumType;
	//附加介质数据 十六进制，右对齐
	private String mediumData;
	//使用期限 起止年月日（包含）
	private String userTime;
	//使用期限 起止年月日（包含），截止日期
	private String UserTimeEnd;
	//储值金额，单位为分
	private String storedValue;
	//名单类型 00-白名单，01-黑名单
	private String listType;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getMediumType() {
		return mediumType;
	}
	public void setMediumType(String mediumType) {
		this.mediumType = mediumType;
	}
	public String getMediumData() {
		return mediumData;
	}
	public void setMediumData(String mediumData) {
		this.mediumData = mediumData;
	}
	public String getUserTime() {
		return userTime;
	}
	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}
	public String getUserTimeEnd() {
		return UserTimeEnd;
	}
	public void setUserTimeEnd(String userTimeEnd) {
		UserTimeEnd = userTimeEnd;
	}
	public String getStoredValue() {
		return storedValue;
	}
	public void setStoredValue(String storedValue) {
		this.storedValue = storedValue;
	}
	public String getListType() {
		return listType;
	}
	public void setListType(String listType) {
		this.listType = listType;
	}
}

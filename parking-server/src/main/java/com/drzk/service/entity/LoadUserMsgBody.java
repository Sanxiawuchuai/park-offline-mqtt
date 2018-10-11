package com.drzk.service.entity;

import java.io.Serializable;
import java.util.Date;

//加载用户信息实体
public class LoadUserMsgBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8283275045270653097L;
	private String uId;
	private String carType;//车辆类型 
	private String carNo; //车牌号
	private String mediumType;//介质类型 0-无，1-IC卡，2-IC转ID卡，3-ID卡，4-ETC，5-CPU，6-纸票，7-身份证UID
	private String mediumData;//附加介质数据 十六进制，右对齐
	private Date userTimeStart;//使用期限 起止年月日（包含）
	private Date userTimeEnd;//使用期限 起止年月日（包含），截止日期
	private String storedValue;//储值金额，单位为分
	private String listType;//名单类型 00-白名单，01-黑名单
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
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
	
	/**
	* carNo.
	*
	* @return the carNo
	* @since JDK 1.8
	*/
	public String getCarNo() {
		return carNo;
	}
	
	/**
	* carNo.
	*
	* @param carNo the carNo to set
	* @since JDK 1.8
	*/
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	
	/**
	* userTimeStart.
	*
	* @return the userTimeStart
	* @since JDK 1.8
	*/
	public Date getUserTimeStart() {
		return userTimeStart;
	}
	
	/**
	* userTimeStart.
	*
	* @param userTimeStart the userTimeStart to set
	* @since JDK 1.8
	*/
	public void setUserTimeStart(Date userTimeStart) {
		this.userTimeStart = userTimeStart;
	}
	
	/**
	* userTimeEnd.
	*
	* @return the userTimeEnd
	* @since JDK 1.8
	*/
	public Date getUserTimeEnd() {
		return userTimeEnd;
	}
	
	/**
	* userTimeEnd.
	*
	* @param userTimeEnd the userTimeEnd to set
	* @since JDK 1.8
	*/
	public void setUserTimeEnd(Date userTimeEnd) {
		this.userTimeEnd = userTimeEnd;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	


}

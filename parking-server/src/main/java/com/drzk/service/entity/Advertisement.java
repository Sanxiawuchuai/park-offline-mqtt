package com.drzk.service.entity;

import java.io.Serializable;

public class Advertisement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8585359279830362559L;
	//行号
	private String lineNumber;
	//广告内容
	private String advertisementDisplay;
	//是否长期有效 0,表示暂时有效 1,表示长期有效
	private String isValid;
	//有效开始赶时间
	private String sDate;
	//结束时间
	private String eDate;
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getAdvertisementDisplay() {
		return advertisementDisplay;
	}
	public void setAdvertisementDisplay(String advertisementDisplay) {
		this.advertisementDisplay = advertisementDisplay;
	}
	public String getIsValid() {
		return isValid;
	}
	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}

	
}


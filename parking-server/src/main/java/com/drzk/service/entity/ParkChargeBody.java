package com.drzk.service.entity;

import java.io.Serializable;

//2.17. 车场收费测试实体
public class ParkChargeBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6293658437711112285L;
	private String uId;
	private String carType;//车辆类型
	private String inTime;//入场时间
	private String outTime;//入场时间
	public String getCarType() {
		return carType;
	}
	public void setCarType(String carType) {
		this.carType = carType;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getOutTime() {
		return outTime;
	}
	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
}

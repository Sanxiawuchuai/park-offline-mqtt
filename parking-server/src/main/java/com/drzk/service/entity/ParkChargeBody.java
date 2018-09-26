package com.drzk.service.entity;
//2.17. 车场收费测试实体
public class ParkChargeBody  extends SuperBody{
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
	
}

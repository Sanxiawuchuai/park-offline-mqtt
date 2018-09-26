package com.drzk.service.entity;

import java.util.List;

import com.drzk.vo.ParkStandardCharge;

//2.18. 加载收费标准实体
public class LoadChargeStandardBody  extends SuperBody{
	private String standardType;//收费标准类型
	private String overTime;//超时收费时间
	private List<ParkStandardChargeBody> chargeData;//收费数据暂定
	public String getStandardType() {
		return standardType;
	}
	public void setStandardType(String standardType) {
		this.standardType = standardType;
	}
	public String getOverTime() {
		return overTime;
	}
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	public List<ParkStandardChargeBody> getChargeData() {
		return chargeData;
	}
	public void setChargeData(List<ParkStandardChargeBody> chargeData) {
		this.chargeData = chargeData;
	}
	
}

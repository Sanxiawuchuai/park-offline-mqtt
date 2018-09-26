package com.drzk.online.vo;

import java.util.Date;
import java.util.List;

public class FeescaleVO extends SuperBody {

    private String packageName;		//账户名称
	private String crossTimeType;
	private String freeTime;			//免费时间
	private Double mostMoney;			//最高计费
	private Double crossMoney;		//跨度收费金额
	private String isMoreTimesFree;		//多次出入
	private Date sTime;				//开始时间
	private Date eTime;				//结束时间
	private Integer packageId;		//账户下标
	private TimeoutFeeVO vo;			//超时收费
	private List<SegmentChargesVO> segmentCharges;			//24小时计费时间

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getCrossTimeType() {
		return crossTimeType;
	}

	public void setCrossTimeType(String crossTimeType) {
		this.crossTimeType = crossTimeType;
	}

	public String getFreeTime() {
		return freeTime;
	}

	public void setFreeTime(String freeTime) {
		this.freeTime = freeTime;
	}

	public Double getMostMoney() {
		return mostMoney;
	}

	public void setMostMoney(Double mostMoney) {
		this.mostMoney = mostMoney;
	}

	public Double getCrossMoney() {
		return crossMoney;
	}

	public void setCrossMoney(Double crossMoney) {
		this.crossMoney = crossMoney;
	}

	public String getIsMoreTimesFree() {
		return isMoreTimesFree;
	}

	public void setIsMoreTimesFree(String isMoreTimesFree) {
		this.isMoreTimesFree = isMoreTimesFree;
	}

	public Date getsTime() {
		return sTime;
	}

	public void setsTime(Date sTime) {
		this.sTime = sTime;
	}

	public Date geteTime() {
		return eTime;
	}

	public void seteTime(Date eTime) {
		this.eTime = eTime;
	}

	public Integer getPackageId() {
		return packageId;
	}

	public void setPackageId(Integer packageId) {
		this.packageId = packageId;
	}

	public TimeoutFeeVO getVo() {
		return vo;
	}

	public void setVo(TimeoutFeeVO vo) {
		this.vo = vo;
	}

	public List<SegmentChargesVO> getSegmentCharges() {
		return segmentCharges;
	}

	public void setSegmentCharges(List<SegmentChargesVO> segmentCharges) {
		this.segmentCharges = segmentCharges;
	}
}

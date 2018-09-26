package com.drzk.online.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author tf
 *临停计费
 */
public class PaidStopVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String packageName;//套餐名称
	private String crossTimeType;//跨段类型
	private String freeTime;//免费时间
	private Double mostMoney;//最高计费
	private Double crossMoney;//跨度收费金额
	private String isMoreTimesFree;//多次出入是否计费
	private Date sTime;//开始时间
	private Date eTime;//结束生效时间
	private String ruleNum;//对应数字
	private TimeoutFeeVO vo;
	private String id;
	private List<SegmentChargesVO> segmentCharges;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public TimeoutFeeVO getVo() {
		return vo;
	}
	public void setVo(TimeoutFeeVO vo) {
		this.vo = vo;
	}
	public String getRuleNum() {
		return ruleNum;
	}
	public void setRuleNum(String ruleNum) {
		this.ruleNum = ruleNum;
	}
	
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
	public List<SegmentChargesVO> getSegmentCharges() {
		return segmentCharges;
	}
	public void setSegmentCharges(List<SegmentChargesVO> segmentCharges) {
		this.segmentCharges = segmentCharges;
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
	
}

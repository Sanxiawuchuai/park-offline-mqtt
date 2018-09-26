package com.drzk.offline.vo;

import java.util.Date;


public class GetPayChargeBody extends SuperBody
{
	private String chargePay;//收费标准类型 1.标准收费
	private String equipmentID;//硬件设备编号
	private String cardType;//卡类型
	private Date inTime;//计费开始时间
	private Date outTime;//计费结束时间
	private String disId;//打折模式
	private String typeId;//商家
	public String getChargePay() {
		return chargePay;
	}
	public void setChargePay(String chargePay) {
		this.chargePay = chargePay;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public String getDisId() {
		return disId;
	}
	public void setDisId(String disId) {
		this.disId = disId;
	}
	public String getTypeId() {
		return typeId;
	}
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getEquipmentID() {
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}
}

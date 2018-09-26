package com.drzk.offline.vo;

import java.util.Date;

public class BoxGetInRecord extends SuperBody {
	private String carNo;
	private String equipmentID;
	private String type;
	private Date startDate;
	private Date endDate;
	private String cardType;

	
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	public String getCarNo()
	{
		return carNo;
	}
	/** 岗亭MAC */
	public void setEquipmentID(String equipmentID) {
		this.equipmentID = equipmentID;
	}

	/**
	 * 岗亭MAC
	 */
	public String getEquipmentID() {
		return equipmentID;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	/**
	 * 开始时间
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * 开始时间
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * 结束时间
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * 结束时间
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 卡类型
	 */
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	/**
	 * 卡类型
	 */
	public String getCardType() {
		return cardType;
	}
	
	
}

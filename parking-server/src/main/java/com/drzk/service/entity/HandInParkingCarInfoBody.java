package com.drzk.service.entity;

import java.io.Serializable;
import java.util.Date;

public class HandInParkingCarInfoBody implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7429564441480830067L;
	private String msgTypeID;//0 新增\更新场内表 1删除场内表
	private String parkingFlag;//车场标记 0--大车场场外 1-大车场内 2小车场内
	private String chargeFlag;//收费标记 0未收费 1已打折 2已收费
	private String carNo;//车牌
	private String mediumType;//介质类型 0-无，1-IC卡，2-IC转ID卡，3-ID卡，4-ETC，5-CPU，6-纸票，7-身份证UID
	private String mediumData;//附加介质数据 十六进制，右对齐
	private Date largeParkingInTime;//大车场入场时间
	private Date smallParkingInTime;//小车场入场时间
	private Date centerChargeTime;//中央收费时间
	private Date discountTime;//打折时间
	private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	/** 0 新增\更新场内表 1删除场内表 */
	public String getMsgTypeID()
	{
		return msgTypeID;
	}
	/** 0 新增\更新场内表 1删除场内表 */
	public void setMsgTypeID(String msgTypeID)
	{
		this.msgTypeID = msgTypeID;
	}
	/** 车场标记 0--大车场场外 1-大车场内 2小车场内 */
	public String getParkingFlag()
	{
		return parkingFlag;
	}
	/** 车场标记 0--大车场场外 1-大车场内 2小车场内 */
	public void setParkingFlag(String parkingFlag)
	{
		this.parkingFlag = parkingFlag;
	}
	/**  收费标记 0未收费 1已打折 2已收费  */
	public String getChargeFlag()
	{
		return chargeFlag;
	}
	/**  收费标记 0未收费 1已打折 2已收费  */
	public void setChargeFlag(String chargeFlag)
	{
		this.chargeFlag = chargeFlag;
	}
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	/** 介质类型 0-无，1-IC卡，2-IC转ID卡，3-ID卡，4-ETC，5-CPU，6-纸票，7-身份证UID */
	public String getMediumType()
	{
		return mediumType;
	}
	/** 介质类型 0-无，1-IC卡，2-IC转ID卡，3-ID卡，4-ETC，5-CPU，6-纸票，7-身份证UID */
	public void setMediumType(String mediumType)
	{
		this.mediumType = mediumType;
	}
	/** 附加介质数据 十六进制，右对齐  */
	public String getMediumData()
	{
		return mediumData;
	}
	/** 附加介质数据 十六进制，右对齐  */
	public void setMediumData(String mediumData)
	{
		this.mediumData = mediumData;
	}
	/** 大车场入场时间 */
	public Date getLargeParkingInTime()
	{
		return largeParkingInTime;
	}
	/** 大车场入场时间 */
	public void setLargeParkingInTime(Date largeParkingInTime)
	{
		this.largeParkingInTime = largeParkingInTime;
	}
	/** 小车场入场时间 */
	public Date getSmallParkingInTime()
	{
		return smallParkingInTime;
	}
	/** 小车场入场时间 */
	public void setSmallParkingInTime(Date smallParkingInTime)
	{
		this.smallParkingInTime = smallParkingInTime;
	}
	/** 中央收费时间 */
	public Date getCenterChargeTime()
	{
		return centerChargeTime;
	}
	/** 中央收费时间 */
	public void setCenterChargeTime(Date centerChargeTime)
	{
		this.centerChargeTime = centerChargeTime;
	}
	/** 打折时间  */
	public Date getDiscountTime()
	{
		return discountTime;
	}
	/** 打折时间  */
	public void setDiscountTime(Date discountTime)
	{
		this.discountTime = discountTime;
	}
	
	
	
	
}

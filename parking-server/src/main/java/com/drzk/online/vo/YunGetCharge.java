package com.drzk.online.vo;

import java.io.Serializable;

public class YunGetCharge implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5438964442263695488L;
	private String carNo;
	private String entryTime;
	private String chargeTime;
	private String endChargeTime;
	
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	public String getEntryTime()
	{
		return entryTime;
	}
	public void setEntryTime(String entryTime)
	{
		this.entryTime = entryTime;
	}
	public String getChargeTime()
	{
		return chargeTime;
	}
	public void setChargeTime(String chargeTime)
	{
		this.chargeTime = chargeTime;
	}
	public String getEndChargeTime()
	{
		return endChargeTime;
	}
	public void setEndChargeTime(String endChargeTime)
	{
		this.endChargeTime = endChargeTime;
	}
	
	
}

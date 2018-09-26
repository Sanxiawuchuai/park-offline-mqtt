package com.drzk.online.vo;

import java.util.Date;

public class ParkUnusualCarInVO extends SuperBody
{
	private String cardId;
	private String empName;
	private Integer carTypeId;
	private String carTypeName;
	private String carNo;
	private Date bInTime;
	private Date inTime;
	private String inControlName;
	private String inUserName;
	private String inWayName;
	private Integer inWay;
	private String inPic;
	
	
	public String getCardId()
	{
		return cardId;
	}
	public void setCardId(String cardId)
	{
		this.cardId = cardId;
	}
	
	public String getEmpName()
	{
		return empName;
	}
	public void setEmpName(String empName)
	{
		this.empName = empName;
	}
	public Integer getCarTypeId()
	{
		return carTypeId;
	}
	public void setCarTypeId(Integer carTypeId)
	{
		this.carTypeId = carTypeId;
	}
	public String getCarTypeName()
	{
		return carTypeName;
	}
	public void setCarTypeName(String carTypeName)
	{
		this.carTypeName = carTypeName;
	}
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	/** 原有入场时间 格式:yyyy-MM-dd HH:mm:ss */
	public Date getbInTime()
	{
		return bInTime;
	}
	/** 原有入场时间 格式:yyyy-MM-dd HH:mm:ss */
	public void setbInTime(Date bInTime)
	{
		this.bInTime = bInTime;
	}
	/** 入场时间 格式:yyyy-MM-dd HH:mm:ss */
	public Date getInTime()
	{
		return inTime;
	}
	/** 入场时间 格式:yyyy-MM-dd HH:mm:ss */
	public void setInTime(Date inTime)
	{
		this.inTime = inTime;
	}
	public String getInControlName()
	{
		return inControlName;
	}
	public void setInControlName(String inControlName)
	{
		this.inControlName = inControlName;
	}
	public String getInUserName()
	{
		return inUserName;
	}
	public void setInUserName(String inUserName)
	{
		this.inUserName = inUserName;
	}
	/** 入场方式 0,表示正常出场，1，校正入场 ，2，手工入场 */
	public String getInWayName()
	{
		return inWayName;
	}
	/** 入场方式 0,表示正常出场，1，校正入场 ，2，手工入场 */
	public void setInWayName(String inWayName)
	{
		this.inWayName = inWayName;
	}
	/** 入场方式 0,表示正常出场，1，校正入场 ，2，手工入场 */
	public Integer getInWay()
	{
		return inWay;
	}
	/** 入场方式 0,表示正常出场，1，校正入场 ，2，手工入场 */
	public void setInWay(Integer inWay)
	{
		this.inWay = inWay;
	}
	public String getInPic()
	{
		return inPic;
	}
	public void setInPic(String inPic)
	{
		this.inPic = inPic;
	}
	 
	
	
}

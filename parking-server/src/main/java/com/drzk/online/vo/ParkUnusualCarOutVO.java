package com.drzk.online.vo;

import java.util.Date;

public class ParkUnusualCarOutVO extends OnlineBody
{
	private String carNo;
	private String contactName;
	private Integer carTypeId;
	private String carTypeName;
	private Double payCharge;
	private Date inTime;
	private String entrance;
	private Date outTime;	
	private String appearances;
	private String inUserName;
	private String outUserName;
	private String outWayName;
	private String inPic;		
	private String outPic;
	
	
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	/** contactName */
	public String getContactName()
	{
		return contactName;
	}
	/** contactName */
	public void setContactName(String contactName)
	{
		this.contactName = contactName;
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
	public Double getPayCharge()
	{
		return payCharge;
	}
	public void setPayCharge(Double payCharge)
	{
		this.payCharge = payCharge;
	}
	public Date getInTime()
	{
		return inTime;
	}
	public void setInTime(Date inTime)
	{
		this.inTime = inTime;
	}
	/**  入口岗亭名称  */
	public String getEntrance()
	{
		return entrance;
	}
	/**  入口岗亭名称  */
	public void setEntrance(String entrance)
	{
		this.entrance = entrance;
	}
	public Date getOutTime()
	{
		return outTime;
	}
	public void setOutTime(Date outTime)
	{
		this.outTime = outTime;
	}
	/** 出口岗亭名称 */
	public String getAppearances()
	{
		return appearances;
	}
	/** 出口岗亭名称 */
	public void setAppearances(String appearances)
	{
		this.appearances = appearances;
	}
	public String getInUserName()
	{
		return inUserName;
	}
	public void setInUserName(String inUserName)
	{
		this.inUserName = inUserName;
	}
	public String getOutUserName()
	{
		return outUserName;
	}
	public void setOutUserName(String outUserName)
	{
		this.outUserName = outUserName;
	}
	public String getOutWayName()
	{
		return outWayName;
	}
	public void setOutWayName(String outWayName)
	{
		this.outWayName = outWayName;
	}
	public String getInPic()
	{
		return inPic;
	}
	public void setInPic(String inPic)
	{
		this.inPic = inPic;
	}
	public String getOutPic()
	{
		return outPic;
	}
	public void setOutPic(String outPic)
	{
		this.outPic = outPic;
	}
	
	
	
	
	
	
	
	
	
	
}

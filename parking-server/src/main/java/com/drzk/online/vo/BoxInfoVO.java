package com.drzk.online.vo;

public class BoxInfoVO extends SuperBody
{
	private String parkingLotName;
	private String boxIp;
	private Integer boxType;
	private String boxName;
	private String description;
	private String mac;
	
	/** 客户名称 */
	public String getParkingLotName()
	{
		return parkingLotName;
	}
	/** 客户名称 */
	public void setParkingLotName(String parkingLotName)
	{
		this.parkingLotName = parkingLotName;
	}
	public String getBoxIp()
	{
		return boxIp;
	}
	public void setBoxIp(String boxIp)
	{
		this.boxIp = boxIp;
	}
	/** 岗亭类型（0标准收费点，1中央收费点，2综合收费点） */
	public Integer getBoxType()
	{
		return boxType;
	}
	/** 岗亭类型（0标准收费点，1中央收费点，2综合收费点） */
	public void setBoxType(Integer boxType)
	{
		this.boxType = boxType;
	}
	public String getBoxName()
	{
		return boxName;
	}
	public void setBoxName(String boxName)
	{
		this.boxName = boxName;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getMac()
	{
		return mac;
	}
	public void setMac(String mac)
	{
		this.mac = mac;
	}
	
}

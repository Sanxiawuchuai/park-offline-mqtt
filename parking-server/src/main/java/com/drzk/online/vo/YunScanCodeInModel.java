package com.drzk.online.vo;

public class YunScanCodeInModel
{

	private Integer channelType;
	private String entryTime;
	private String carNo;
	
	/** 通道类型 0 入口 1 出口 */
	public Integer getChannelType()
	{
		return channelType;
	}
	/** 通道类型 0 入口 1 出口 */
	public void setChannelType(Integer channelType)
	{
		this.channelType = channelType;
	}
	/** 车辆入场时间 格式 yyyy-MM-dd HH:mm:ss  */
	public String getEntryTime()
	{
		return entryTime;
	}
	/** 车辆入场时间 格式 yyyy-MM-dd HH:mm:ss  */
	public void setEntryTime(String entryTime)
	{
		this.entryTime = entryTime;
	}
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	
	
}

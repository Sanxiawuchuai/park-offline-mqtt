package com.drzk.online.vo;

public class ParkComperTask 
{

	private String objectId;
	private String macAddress;
	private String comperIp;
	private String comCPU;
	private String network;
	private String memory;
	private String disk;
	
	/** 系统表ID uuid/guid */
	public String getObjectId()
	{
		return objectId;
	}
	/** 系统表ID uuid/guid */
	public void setObjectId(String objectId)
	{
		this.objectId = objectId;
	}
	public String getMacAddress()
	{
		return macAddress;
	}
	public void setMacAddress(String macAddress)
	{
		this.macAddress = macAddress;
	}
	public String getComperIp()
	{
		return comperIp;
	}
	public void setComperIp(String comperIp)
	{
		this.comperIp = comperIp;
	}
	public String getComCPU()
	{
		return comCPU;
	}
	public void setComCPU(String comCPU)
	{
		this.comCPU = comCPU;
	}
	public String getNetwork()
	{
		return network;
	}
	public void setNetwork(String network)
	{
		this.network = network;
	}
	public String getMemory()
	{
		return memory;
	}
	public void setMemory(String memory)
	{
		this.memory = memory;
	}
	public String getDisk()
	{
		return disk;
	}
	public void setDisk(String disk)
	{
		this.disk = disk;
	}
	
}

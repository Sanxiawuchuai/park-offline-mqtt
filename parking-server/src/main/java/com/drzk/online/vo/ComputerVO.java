package com.drzk.online.vo;


public class ComputerVO extends OnlineBody
{

	private String ipAddress;
	private String macAddress;
	private String cpuId;
	private String runType;
	
	public String getIpAddress()
	{
		return ipAddress;
	}
	public void setIpAddress(String ipAddress)
	{
		this.ipAddress = ipAddress;
	}
	public String getMacAddress()
	{
		return macAddress;
	}
	public void setMacAddress(String macAddress)
	{
		this.macAddress = macAddress;
	}
	public String getCpuId()
	{
		return cpuId;
	}
	public void setCpuId(String cpuId)
	{
		this.cpuId = cpuId;
	}
	/** 运行模式1服务软件模式 2 控制器直连云端 */
	public String getRunType()
	{
		return runType;
	}
	/** 运行模式1服务软件模式 2 控制器直连云端 */
	public void setRunType(String runType)
	{
		this.runType = runType;
	}
	
}

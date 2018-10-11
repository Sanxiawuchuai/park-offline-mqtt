package com.drzk.online.vo;


public class ParkCamerasVO extends OnlineBody
{
	private String cameIp;
	private String cameName;
	private String camePort;
	private String userName;
	private String passWord;
	private Integer controllerChanlId; //机号
	private String controllerId;//通道cuid
	private String controllerName;
	private String boxId;
	private String boxName;
	private String description;
	private String mac;
	public String realStatus;//在线或离线
	
	public String getCameIp()
	{
		return cameIp;
	}
	public void setCameIp(String cameIp)
	{
		this.cameIp = cameIp;
	}
	public String getCameName()
	{
		return cameName;
	}
	public void setCameName(String cameName)
	{
		this.cameName = cameName;
	}
	public String getCamePort()
	{
		return camePort;
	}
	public void setCamePort(String camePort)
	{
		this.camePort = camePort;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getPassWord()
	{
		return passWord;
	}
	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}
	/** 机号 */
	public Integer getControllerChanlId()
	{
		return controllerChanlId;
	}
	/** 机号 */
	public void setControllerChanlId(Integer controllerChanlId)
	{
		this.controllerChanlId = controllerChanlId;
	}
	/** 通道cuid */
	public String getControllerId()
	{
		return controllerId;
	}
	/** 通道 cuid */
	public void setControllerId(String controllerId)
	{
		this.controllerId = controllerId;
	}
	/** 通道名称 */
	public String getControllerName()
	{
		return controllerName;
	}
	/** 通道名称 */
	public void setControllerName(String controllerName)
	{
		this.controllerName = controllerName;
	}
	public String getBoxId()
	{
		return boxId;
	}
	public void setBoxId(String boxId)
	{
		this.boxId = boxId;
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
	/** 相机mac */
	public String getMac()
	{
		return mac;
	}
	/** 相机mac */
	public void setMac(String mac)
	{
		this.mac = mac;
	}
	/** 在线或离线 */
	public String getRealStatus()
	{
		return realStatus;
	}
	/** 在线或离线 */
	public void setRealStatus(String realStatus)
	{
		this.realStatus = realStatus;
	}
	
	
}

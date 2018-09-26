package com.drzk.online.vo;

public class ControllerInfoVO extends SuperBody
{
	private String contName;
	private String controllerIp;
	private Integer type;
	private String openMacNum;
	private Integer channelType;
	private String boxId;
	private String boxName;
	private String description;
	private String mac;
	private Integer tempCar;
	private Integer monthCar;
	private Integer freeCar;
	private Integer rechargeCar;
	
	public String getContName()
	{
		return contName;
	}
	public void setContName(String contName)
	{
		this.contName = contName;
	}
	public String getControllerIp()
	{
		return controllerIp;
	}
	public void setControllerIp(String controllerIp)
	{
		this.controllerIp = controllerIp;
	}
	/** 出入类型（0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道） */
	public Integer getType()
	{
		return type;
	}
	/** 出入类型（0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道） */
	public void setType(Integer type)
	{
		this.type = type;
	}
	/** 机号 */
	public String getOpenMacNum()
	{
		return openMacNum;
	}
	/** 机号 */
	public void setOpenMacNum(String openMacNum)
	{
		this.openMacNum = openMacNum;
	}
	/** 通道类型（0综合信道 1固定车信道，2临时卡通道） */
	public Integer getChannelType()
	{
		return channelType;
	}
	/** 通道类型（0综合信道 1固定车信道，2临时卡通道） */
	public void setChannelType(Integer channelType)
	{
		this.channelType = channelType;
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
	public String getMac()
	{
		return mac;
	}
	public void setMac(String mac)
	{
		this.mac = mac;
	}
	/** 临时车通行：0：通行 1： 不通行 */
	public Integer getTempCar()
	{
		return tempCar;
	}
	/** 临时车通行：0：通行 1： 不通行 */
	public void setTempCar(Integer tempCar)
	{
		this.tempCar = tempCar;
	}
	
	/** 月租车通行：0：通行 1： 不通行 */
	public Integer getMonthCar()
	{
		return monthCar;
	}
	/** 月租车通行：0：通行 1： 不通行 */
	public void setMonthCar(Integer monthCar)
	{
		this.monthCar = monthCar;
	}
	
	/** 免费车通行：0：通行 1： 不通行 */
	public Integer getFreeCar()
	{
		return freeCar;
	}
	/** 免费车通行：0：通行 1： 不通行 */
	public void setFreeCar(Integer freeCar)
	{
		this.freeCar = freeCar;
	}
	/** 储值卡车通行：0：通行 1： 不通行 */
	public Integer getRechargeCar()
	{
		return rechargeCar;
	}
	/** 储值卡车通行：0：通行 1： 不通行 */
	public void setRechargeCar(Integer rechargeCar)
	{
		this.rechargeCar = rechargeCar;
	}
	
	
	
}

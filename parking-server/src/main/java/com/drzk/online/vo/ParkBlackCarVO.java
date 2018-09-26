package com.drzk.online.vo;

public class ParkBlackCarVO extends SuperBody
{

	private String carNo;
	private Integer carNoType;
	private Integer isStop;
	private String description;
	private String remark;
	
	
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	/** 车牌类型(0无,1黑名单,2特种车辆)  */
	public Integer getCarNoType()
	{
		return carNoType;
	}
	/** 车牌类型(0无,1黑名单,2特种车辆)  */
	public void setCarNoType(Integer carNoType)
	{
		this.carNoType = carNoType;
	}
	/** 类型(0无,1禁止通行,2通行免费,3自由通行) */
	public Integer getIsStop()
	{
		return isStop;
	}
	/** 类型(0无,1禁止通行,2通行免费,3自由通行) */
	public void setIsStop(Integer isStop)
	{
		this.isStop = isStop;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getRemark()
	{
		return remark;
	}
	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	
}

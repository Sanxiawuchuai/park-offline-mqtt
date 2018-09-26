package com.drzk.online.vo;

import java.util.List;

public class ParkCaruser extends SuperBody
{
	private String contactName;
	private String contactPhone;
	private String carport;
	private Integer messageOnOff;
	private Integer isHaveCarport;
	private String remark;
	private Integer operationType;
	private Integer payType;
	private List<CarportAndCarVO> carportAndCarList;
	
	/** 车牌发行信息 */
	public List<CarportAndCarVO> getCarportAndCarList()
	{
		return carportAndCarList;
	}
	/** 车牌发行信息 */
	public void setCarportAndCarList(List<CarportAndCarVO> carportAndCarList)
	{
		this.carportAndCarList = carportAndCarList;
	}


	/** 车主姓名  */
	public String getContactName()
	{
		return contactName;
	}
	/** 车主姓名  */
	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}
	/** 车主电话  */
	public String getContactPhone()
	{
		return contactPhone;
	}
	/** 车主电话  */
	public void setContactPhone(String contactPhone)
	{
		this.contactPhone = contactPhone;
	}
	/** 车位组名称 */
	public String getCarport()
	{
		return carport;
	}
	/** 车位组名称 */
	public void setCarport(String carport)
	{
		this.carport = carport;
	}
	/** 短信通知开关(0表示不开通，1表示开通 */
	public Integer getMessageOnOff()
	{
		return messageOnOff;
	}
	/** 短信通知开关(0表示不开通，1表示开通 */
	public void setMessageOnOff(Integer messageOnOff)
	{
		this.messageOnOff = messageOnOff;
	}
	/** 是否开通车位组0 不开通，1开通 */
	public Integer getIsHaveCarport()
	{
		return isHaveCarport;
	}
	/** 是否开通车位组0 不开通，1开通 */
	public void setIsHaveCarport(Integer isHaveCarport)
	{
		this.isHaveCarport = isHaveCarport;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}
	/** 操作类型（0卡发行1卡延期 5退款6销户）默认开通租赁 */
	public Integer getOperationType()
	{
		return operationType;
	}
	/** 操作类型（0卡发行1卡延期 5退款6销户）默认开通租赁 */
	public void setOperationType(Integer operationType)
	{
		this.operationType = operationType;
	}
	/** 0现金1银联闪付2微信3支付宝 */
	public Integer getPayType()
	{
		return payType;
	}
	/** 0现金1银联闪付2微信3支付宝 */
	public void setPayType(Integer payType)
	{
		this.payType = payType;
	}
	
}

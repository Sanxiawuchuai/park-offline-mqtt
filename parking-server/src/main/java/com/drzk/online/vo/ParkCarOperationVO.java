package com.drzk.online.vo;

import java.util.Date;

public class ParkCarOperationVO extends OnlineBody
{
	private String carNo;
	private String cardNo;
	private String cardId;
	private Integer cardTypeId;
	private String cardTypeName;
	private Integer contactId;
	private String contactName;
	private String contactPhone;
	private String address;
	private Date frontDate;
	private Date startTime;
	private Date endTime;
	private Double rechargeMoney;
	private Double balanceMoney;
	private Double deposit;
	private Integer payType;
	private String transactionNumber;
	private Integer operationType;
	private String description;
	
	
	
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	public String getCardNo()
	{
		return cardNo;
	}
	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}
	public String getCardId()
	{
		return cardId;
	}
	public void setCardId(String cardId)
	{
		this.cardId = cardId;
	}
	public Integer getCardTypeId()
	{
		return cardTypeId;
	}
	public void setCardTypeId(Integer cardTypeId)
	{
		this.cardTypeId = cardTypeId;
	}
	public String getCardTypeName()
	{
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName)
	{
		this.cardTypeName = cardTypeName;
	}
	/** 车主ID */
	public Integer getContactId()
	{
		return contactId;
	}
	/** 车主ID */
	public void setContactId(Integer contactId)
	{
		this.contactId = contactId;
	}
	/** 车主  */
	public String getContactName()
	{
		return contactName;
	}
	/** 车主  */
	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}
	/** 车主手机 */
	public String getContactPhone()
	{
		return contactPhone;
	}
	/** 车主手机 */
	public void setContactPhone(String contactPhone)
	{
		this.contactPhone = contactPhone;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	/** 前结束日期（延期用）,格式：yyy-MM-dd HH:mm:ss */
	public Date getFrontDate()
	{
		return frontDate;
	}
	/** 前结束日期（延期用）,格式：yyy-MM-dd HH:mm:ss */
	public void setFrontDate(Date frontDate)
	{
		this.frontDate = frontDate;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	/**  发生前余额（储值卡用） */
	public Double getRechargeMoney()
	{
		return rechargeMoney;
	}
	/**  发生前余额（储值卡用） */
	public void setRechargeMoney(Double rechargeMoney)
	{
		this.rechargeMoney = rechargeMoney;
	}
	/** 发生金额 */
	public Double getBalanceMoney()
	{
		return balanceMoney;
	}
	/** 发生金额 */
	public void setBalanceMoney(Double balanceMoney)
	{
		this.balanceMoney = balanceMoney;
	}
	/** 押金 */
	public Double getDeposit()
	{
		return deposit;
	}
	/** 押金 */
	public void setDeposit(Double deposit)
	{
		this.deposit = deposit;
	}
	/** 付款方式(0现金1银联闪付2微信3支付宝) */
	public Integer getPayType()
	{
		return payType;
	}
	/** 付款方式(0现金1银联闪付2微信3支付宝) */
	public void setPayType(Integer payType)
	{
		this.payType = payType;
	}
	/** 云交易记录编号 */
	public String getTransactionNumber()
	{
		return transactionNumber;
	}
	/** 云交易记录编号 */
	public void setTransactionNumber(String transactionNumber)
	{
		this.transactionNumber = transactionNumber;
	}
	/** 操作类型（0卡发行1卡延期2挂失3解挂4补发5退款6销户） */
	public Integer getOperationType()
	{
		return operationType;
	}
	/** 操作类型（0卡发行1卡延期2挂失3解挂4补发5退款6销户） */
	public void setOperationType(Integer operationType)
	{
		this.operationType = operationType;
	}
	public String getDescription()
	{
		return description;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	
}

package com.drzk.online.vo;

import java.util.Date;

public class ParkCenterPayment extends SuperBody
{

	private String carNo;
	private Integer inMachNo;
	private String entrance;
	private String cardId;
	private String cardNo;
	private Integer cFlag;
	private String cardType;
	private String cardTypeName;
	private Integer freeType; 
	private Date inTime;
	private String inUserName;
	private Date overTimeS;
	private Date payChargeTime;
	private Integer payType;
	private String transactionId;
	private Double payCharge;
	private String discountNo;
	private String eqId;
	private Date  discountTime;
	private Double disAmount;
	private Double accountCharge;
	private String unusualMemo;
	private String description;
	
	
	
	
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	public Integer getInMachNo()
	{
		return inMachNo;
	}
	public void setInMachNo(Integer inMachNo)
	{
		this.inMachNo = inMachNo;
	}
	/** 入口名称 */
	public String getEntrance()
	{
		return entrance;
	}
	/** 入口名称 */
	public void setEntrance(String entrance)
	{
		this.entrance = entrance;
	}
	public String getCardId()
	{
		return cardId;
	}
	public void setCardId(String cardId)
	{
		this.cardId = cardId;
	}
	/** 卡编号 */
	public String getCardNo()
	{
		return cardNo;
	}
	/** 卡编号 */
	public void setCardNo(String cardNo)
	{
		this.cardNo = cardNo;
	}
	/**  卡介质(0为IC，1为ID，2IC做ID，3)  */
	public Integer getcFlag()
	{
		return cFlag;
	}
	/**  卡介质(0为IC，1为ID，2IC做ID，3)  */
	public void setcFlag(Integer cFlag)
	{
		this.cFlag = cFlag;
	}
	public String getCardType()
	{
		return cardType;
	}
	public void setCardType(String cardType)
	{
		this.cardType = cardType;
	}
	public String getCardTypeName()
	{
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName)
	{
		this.cardTypeName = cardTypeName;
	}
	/** 免费类型 */
	public Integer getFreeType()
	{
		return freeType;
	}
	/** 免费类型 */
	public void setFreeType(Integer freeType)
	{
		this.freeType = freeType;
	}
	public Date getInTime()
	{
		return inTime;
	}
	public void setInTime(Date inTime)
	{
		this.inTime = inTime;
	}
	public String getInUserName()
	{
		return inUserName;
	}
	public void setInUserName(String inUserName)
	{
		this.inUserName = inUserName;
	}
	/** 逾时开始时间 格式yyyy-MM-dd HH:mm:ss  */
	public Date getOverTimeS()
	{
		return overTimeS;
	}
	/** 逾时开始时间 格式yyyy-MM-dd HH:mm:ss  */
	public void setOverTimeS(Date overTimeS)
	{
		this.overTimeS = overTimeS;
	}
	/** 收费时间 格式yyyy-MM-dd HH:mm:ss */
	public Date getPayChargeTime()
	{
		return payChargeTime;
	}
	/** 收费时间 格式yyyy-MM-dd HH:mm:ss */
	public void setPayChargeTime(Date payChargeTime)
	{
		this.payChargeTime = payChargeTime;
	}
	/** 支付类型 0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它 */
	public Integer getPayType()
	{
		return payType;
	}
	/** 支付类型 0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它 */
	public void setPayType(Integer payType)
	{
		this.payType = payType;
	}
	/** 交易编号 */
	public String getTransactionId()
	{
		return transactionId;
	}
	/** 交易编号 */
	public void setTransactionId(String transactionId)
	{
		this.transactionId = transactionId;
	}
	/** 收费金额 */
	public Double getPayCharge()
	{
		return payCharge;
	}
	/** 收费金额 */
	public void setPayCharge(Double payCharge)
	{
		this.payCharge = payCharge;
	}
	/** 优惠券规则编号 */
	public String getDiscountNo()
	{
		return discountNo;
	}
	/** 优惠券规则编号 */
	public void setDiscountNo(String discountNo)
	{
		this.discountNo = discountNo;
	}
	/** 商家ID */
	public String getEqId()
	{
		return eqId;
	}
	/** 商家ID */
	public void setEqId(String eqId)
	{
		this.eqId = eqId;
	}
	public Date getDiscountTime()
	{
		return discountTime;
	}
	public void setDiscountTime(Date discountTime)
	{
		this.discountTime = discountTime;
	}
	/** 折扣金额 */
	public Double getDisAmount()
	{
		return disAmount;
	}
	/** 折扣金额 */
	public void setDisAmount(Double disAmount)
	{
		this.disAmount = disAmount;
	}
	/** 应收金额 */
	public Double getAccountCharge()
	{
		return accountCharge;
	}
	/** 应收金额 */
	public void setAccountCharge(Double accountCharge)
	{
		this.accountCharge = accountCharge;
	}
	/** 异常原因（1车闸故障2卡遗失等等） */
	public String getUnusualMemo()
	{
		return unusualMemo;
	}
	/** 异常原因（1车闸故障2卡遗失等等） */
	public void setUnusualMemo(String unusualMemo)
	{
		this.unusualMemo = unusualMemo;
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

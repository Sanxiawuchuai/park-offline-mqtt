package com.drzk.online.vo;

import java.io.Serializable;

public class PayMentCarFee implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2416267793531765987L;
	private String carNo;
	private String entryTime;
	private String payTime;
	private Integer duration;
	private float totalAmount;
	private float disAmount;
	private float couponAmount;
	private Integer paymentType;
	private String paymentTnx;
//	private Integer channelId;
	private Integer payType;
	private String dsn;
	
	/** 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它 */
	public Integer getPayType()
	{
		return payType;
	}
	/** 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它 */
	public void setPayType(Integer payType)
	{
		this.payType = payType;
	}
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	public String getEntryTime()
	{
		return entryTime;
	}
	public void setEntryTime(String entryTime)
	{
		this.entryTime = entryTime;
	}
	public String getPayTime()
	{
		return payTime;
	}
	public void setPayTime(String payTime)
	{
		this.payTime = payTime;
	}
	/** 停车时长 单位：分钟 */
	public Integer getDuration()
	{
		return duration;
	}
	/** 停车时长 单位：分钟 */
	public void setDuration(Integer duration)
	{
		this.duration = duration;
	}
	/** 应收金额 单位：元 */
	public float getTotalAmount()
	{
		return totalAmount;
	}
	/** 应收金额 单位：元 */
	public void setTotalAmount(float totalAmount)
	{
		this.totalAmount = totalAmount;
	}
	/** 优惠金额 单位：元 */
	public float getDisAmount()
	{
		return disAmount;
	}
	/** 优惠金额 单位：元 */
	public void setDisAmount(float disAmount)
	{
		this.disAmount = disAmount;
	}
	/** 优惠后金额 单位：元 */
	public float getCouponAmount()
	{
		return couponAmount;
	}
	/** 优惠后金额 单位：元 */
	public void setCouponAmount(float couponAmount)
	{
		this.couponAmount = couponAmount;
	}
	/** 0 定点缴费 1 超时缴费 */
	public Integer getPaymentType()
	{
		return paymentType;
	}
	/** 0 定点缴费 1 超时缴费 */
	public void setPaymentType(Integer paymentType)
	{
		this.paymentType = paymentType;
	}
	/** 支付订单编号 唯一 */
	public String getPaymentTnx()
	{
		return paymentTnx;
	}
	/** 支付订单编号 唯一 */
	public void setPaymentTnx(String paymentTnx)
	{
		this.paymentTnx = paymentTnx;
	}
	public String getDsn()
	{
		return dsn;
	}
	public void setDsn(String dsn)
	{
		this.dsn = dsn;
	}
	 
	
}

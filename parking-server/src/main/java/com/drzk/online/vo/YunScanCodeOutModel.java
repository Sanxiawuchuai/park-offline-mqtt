package com.drzk.online.vo;

public class YunScanCodeOutModel
{
	private Integer channelType;
	private String entryTime;
	private String carNo;
	private String chargeTime;
	private String endChargeTime;
	private Integer paymentType;
	private float totalAmount;
	private float disAmount;
	private float couponAmount;
	private Integer duration;
	private Integer channelId;
	
	
	
	public Integer getChannelId()
	{
		return channelId;
	}
	public void setChannelId(Integer channelId)
	{
		this.channelId = channelId;
	}
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
	/** 计费开始时间 格式 yyyy-MM-dd HH:mm:ss */
	public String getChargeTime()
	{
		return chargeTime;
	}
	/** 计费开始时间 格式 yyyy-MM-dd HH:mm:ss */
	public void setChargeTime(String chargeTime)
	{
		this.chargeTime = chargeTime;
	}
	/** 计费结束时间 格式 yyyy-MM-dd HH:mm:ss */
	public String getEndChargeTime()
	{
		return endChargeTime;
	}
	/** 计费结束时间 格式 yyyy-MM-dd HH:mm:ss */
	public void setEndChargeTime(String endChargeTime)
	{
		this.endChargeTime = endChargeTime;
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
	/** 缴费时长 单位：分钟 */
	public Integer getDuration()
	{
		return duration;
	}
	/** 缴费时长 单位：分钟 */
	public void setDuration(Integer duration)
	{
		this.duration = duration;
	}
	
	
	
	
}

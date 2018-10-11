package com.drzk.online.vo;



/**
 * @author tf
 * 优惠券使用明细
 */
public class CouponUseDetailsVO extends OnlineBody
{
	private String objectId; //编号
     private String openId;//微信的openId
     private String discountID;//折扣ID
     private String discountTime;//打折时间
     private String carNo; //车牌号码
     private String inTime;//入场时间
     private String couponId; //优惠券ID
     private String couponName;//优惠券名称
     private String commercialId;//商铺编号
     private String commercialName ;//商铺名称
     private Integer couponType; //折扣类型 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
     private Double couponAmount;//折扣金额   
     private String userName;//操作用户        
     private String dataOrigin;
 	private String description;
 	private Integer syncStatus;     
     
	public String getObjectId()
	{
		return objectId;
	}
	public void setObjectId(String objectId)
	{
		this.objectId = objectId;
	}
	public String getDataOrigin()
	{
		return dataOrigin;
	}
	public void setDataOrigin(String dataOrigin)
	{
		this.dataOrigin = dataOrigin;
	}
	public Integer getSyncStatus()
	{
		return syncStatus;
	}
	public void setSyncStatus(Integer syncStatus)
	{
		this.syncStatus = syncStatus;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getDiscountID() {
		return discountID;
	}
	public void setDiscountID(String discountID) {
		this.discountID = discountID;
	}
	public String getDiscountTime() {
		return discountTime;
	}
	public void setDiscountTime(String discountTime) {
		this.discountTime = discountTime;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public String getInTime() {
		return inTime;
	}
	public void setInTime(String inTime) {
		this.inTime = inTime;
	}
	public String getCouponId() {
		return couponId;
	}
	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}
	public String getCouponName() {
		return couponName;
	}
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	public String getCommercialId() {
		return commercialId;
	}
	public void setCommercialId(String commercialId) {
		this.commercialId = commercialId;
	}
	public String getCommercialName() {
		return commercialName;
	}
	public void setCommercialName(String commercialName) {
		this.commercialName = commercialName;
	}
	public Integer getCouponType() {
		return couponType;
	}
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	public Double getCouponAmount() {
		return couponAmount;
	}
	public void setCouponAmount(Double couponAmount) {
		this.couponAmount = couponAmount;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

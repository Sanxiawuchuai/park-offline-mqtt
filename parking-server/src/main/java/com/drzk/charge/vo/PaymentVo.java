
package com.drzk.charge.vo;

import java.util.Date;

/**
 * ClassName:PaymentVo <br>
 * Date: 2018年6月13日 下午5:33:58 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class PaymentVo {
	/** 车牌号*/
	private String carNo;
	
	/** 入场时间*/
	private Date inTime;
	/** 出场时间*/
	private Date outTime;
	/** 计费开始时间*/
	private Date payInTime;
	/** 计费结束时间*/
	private Date payOutTime;
	
	/**
     * 打折时间
     */
	private Date discountTime;
	/** 上次缴费时间*/
	private Date lastPayTime;
	/** 免费类开*/
	private Integer freeType;
	/** 实收金额*/
	private Integer payCharge;
	
	/**  优惠金额*/
	private Integer disAmount;
	/**
     * 打折机号
     */
	private String discountID;
	/**
     * 打折模式id
     */
	private Integer typeId;
	/**
     * 收费方式 0出口收费 1定点收费 2超时收费
     */
	private Integer payType=0;
	/**
     * 订单号
     */
	private String orderNum;
	
	/**
     * 备注
     */
	private String remark;
	public Integer getPayType() {
		return payType;
	}
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getInId() {
		return inId;
	}
	public void setInId(String inId) {
		this.inId = inId;
	}
	public int getCarRealType() {
		return carRealType;
	}
	public void setCarRealType(int carRealType) {
		this.carRealType = carRealType;
	}
	private String inId;  //入场ID
	private int carRealType; //车辆实际类型(用来计费的类型,比如月临卡时，这个值存临时卡的类型)
	
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
	}
	public Date getPayInTime() {
		return payInTime;
	}
	public void setPayInTime(Date payInTime) {
		this.payInTime = payInTime;
	}
	public Date getPayOutTime() {
		return payOutTime;
	}
	public void setPayOutTime(Date payOutTime) {
		this.payOutTime = payOutTime;
	}
	/** 应缴费用 */
	private Integer paidFee;
	
	
	public Integer getTypeId() {
		return typeId;
	}
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	public Date getDiscountTime() {
		return discountTime;
	}
	public void setDiscountTime(Date discountTime) {
		this.discountTime = discountTime;
	}
	public void setPaidFee(Integer paidFee) {
		this.paidFee = paidFee;
	}
	public Integer getFreeType()
	{
		return freeType;
	}
	public void setFreeType(Integer freeType)
	{
		this.freeType = freeType;
	}
	/** 上次缴费时间 */
	public Date getLastPayTime()
	{
		return lastPayTime;
	}
	/** 上次缴费时间 */
	public void setLastPayTime(Date lastPayTime)
	{
		this.lastPayTime = lastPayTime;
	}

	/** 折扣编号 */
	public String getDiscountID()
	{
		return discountID;
	}

	/** 折扣编号 */
	public void setDiscountID(String discountID)
	{
		this.discountID = discountID;
	}
	/** 实收金额*/
	public Integer getPayCharge() {
		return payCharge;
	}
	/** 实收金额*/
	public void setPayCharge(Integer payCharge) {
		this.payCharge = payCharge;
	}
	/**  优惠金额*/
	public Integer getDisAmount() {
		return disAmount;
	}
	/**  优惠金额*/
	public void setDisAmount(Integer disAmount) {
		this.disAmount = disAmount;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	/**
	* inTime.
	*
	* @return the inTime
	* @since JDK 1.8
	*/
	public Date getInTime() {
		return inTime;
	}

	
	/**
	* inTime.
	*
	* @param inTime the inTime to set
	* @since JDK 1.8
	*/
	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	
	/** 应缴费用 */
	public Integer getPaidFee() {
		return paidFee;
	}
	

}

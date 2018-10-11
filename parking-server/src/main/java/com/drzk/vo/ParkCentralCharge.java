package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class ParkCentralCharge implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4788114118146340009L;

	/**
     * 
     */
    private Integer id;

    /**
     * 开户id
     */
    private Integer yktId;

    /**
     * 入场guid
     */
    private String inId;

    /**
     * 岗亭编号
     */
    private Integer boxId;

    /**
     * 卡id
     */
    private String cardId;

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3)
     */
    private Integer cFlag;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 入场时间
     */
    private Date inTime;

    /**
     * 卡类型
     */
    private Integer cardType;

    /**
     * 入场机号
     */
    private Integer inMachNo;

    /**
     * 主车牌
     */
    private String carNo;

    /**
     * 副车牌
     */
    private String backCarNo;

    /**
     * 主车牌图片
     */
    private String inPic;

    /**
     * 副车牌图片
     */
    private String backInPic;

    /**
     * 缴费时间
     */
    private Date payChargeTime;

    /**
     * 超时时间
     */
    private Date overTime;

    /**
     * 免费类型
     */
    private Integer freeType;

    /**
     * 证件图片
     */
    private String credentialsPic;

    /**
     * 打折金额
     */
    private String discountNo;

    /**
     * 折扣id
     */
    private Integer typeId;

    /**
     * 打折时间
     */
    private Date discountTime;

    /**
     * 收费员
     */
    private String payUserName;

    /**
     * 实收金额
     */
    private Float payCharge;

    /**
     * 应收金额
     */
    private Float accountCharge;

    /**
     * 打折金额
     */
    private Float disAmount;

    /**
     * 账户金额
     */
    private Float balanceMoney;

    /**
     * 支付订单号
     */
    private String orderNum;

    /**
     * 备注
     */
    private String memo;

    /**
     * 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     */
    private Integer payType;
    
    
    private Integer isLoad;
    private String puid;
    private String inUserName;
    private String smallPic;
    
    public String getSmallPic() {
		return smallPic;
	}
	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}
	/** 入场操作员 */
    public String getInUserName()
	{
		return inUserName;
	}
    /** 入场操作员 */
	public void setInUserName(String inUserName)
	{
		this.inUserName = inUserName;
	}

	public Integer getIsLoad()
	{
		return isLoad;
	}

	public void setIsLoad(Integer isLoad)
	{
		this.isLoad = isLoad;
	}

	public String getPuid()
	{
		return puid;
	}

	public void setPuid(String puid)
	{
		this.puid = puid;
	}

	/**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 开户id
     * @return ykt_id 开户id
     */
    public Integer getYktId() {
        return yktId;
    }

    /**
     * 开户id
     * @param yktId 开户id
     */
    public void setYktId(Integer yktId) {
        this.yktId = yktId;
    }

    /**
     * 入场guid
     * @return in_id 入场guid
     */
    public String getInId() {
        return inId;
    }

    /**
     * 入场guid
     * @param inId 入场guid
     */
    public void setInId(String inId) {
        this.inId = inId == null ? null : inId.trim();
    }

    /**
     * 岗亭编号
     * @return box_id 岗亭编号
     */
    public Integer getBoxId() {
        return boxId;
    }

    /**
     * 岗亭编号
     * @param boxId 岗亭编号
     */
    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    /**
     * 卡id
     * @return card_id 卡id
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 卡id
     * @param cardId 卡id
     */
    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3)
     * @return c_flag 卡介质(0为IC，1为ID，2IC做ID，3)
     */
    public Integer getcFlag() {
        return cFlag;
    }

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3)
     * @param cFlag 卡介质(0为IC，1为ID，2IC做ID，3)
     */
    public void setcFlag(Integer cFlag) {
        this.cFlag = cFlag;
    }

    /**
     * 卡号
     * @return card_no 卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 卡号
     * @param cardNo 卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * 入场时间
     * @return in_time 入场时间
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * 入场时间
     * @param inTime 入场时间
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    /**
     * 卡类型
     * @return card_type 卡类型
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * 卡类型
     * @param cardType 卡类型
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * 入场机号
     * @return in_mach_no 入场机号
     */
    public Integer getInMachNo() {
        return inMachNo;
    }

    /**
     * 入场机号
     * @param inMachNo 入场机号
     */
    public void setInMachNo(Integer inMachNo) {
        this.inMachNo = inMachNo;
    }

    /**
     * 主车牌
     * @return car_no 主车牌
     */
    public String getCarNo() {
        return carNo;
    }

    /**
     * 主车牌
     * @param carNo 主车牌
     */
    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    /**
     * 副车牌
     * @return back_car_no 副车牌
     */
    public String getBackCarNo() {
        return backCarNo;
    }

    /**
     * 副车牌
     * @param backCarNo 副车牌
     */
    public void setBackCarNo(String backCarNo) {
        this.backCarNo = backCarNo == null ? null : backCarNo.trim();
    }

    /**
     * 主车牌图片
     * @return in_pic 主车牌图片
     */
    public String getInPic() {
        return inPic;
    }

    /**
     * 主车牌图片
     * @param inPic 主车牌图片
     */
    public void setInPic(String inPic) {
        this.inPic = inPic == null ? null : inPic.trim();
    }

    /**
     * 副车牌图片
     * @return back_in_pic 副车牌图片
     */
    public String getBackInPic() {
        return backInPic;
    }

    /**
     * 副车牌图片
     * @param backInPic 副车牌图片
     */
    public void setBackInPic(String backInPic) {
        this.backInPic = backInPic == null ? null : backInPic.trim();
    }

    /**
     * 缴费时间
     * @return pay_charge_time 缴费时间
     */
    public Date getPayChargeTime() {
        return payChargeTime;
    }

    /**
     * 缴费时间
     * @param payChargeTime 缴费时间
     */
    public void setPayChargeTime(Date payChargeTime) {
        this.payChargeTime = payChargeTime;
    }

    /**
     * 超时时间
     * @return over_time 超时时间
     */
    public Date getOverTime() {
        return overTime;
    }

    /**
     * 超时时间
     * @param overTime 超时时间
     */
    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    /**
     * 免费类型
     * @return free_type 免费类型
     */
    public Integer getFreeType() {
        return freeType;
    }

    /**
     * 免费类型
     * @param freeType 免费类型
     */
    public void setFreeType(Integer freeType) {
        this.freeType = freeType;
    }

    /**
     * 证件图片
     * @return credentials_pic 证件图片
     */
    public String getCredentialsPic() {
        return credentialsPic;
    }

    /**
     * 证件图片
     * @param credentialsPic 证件图片
     */
    public void setCredentialsPic(String credentialsPic) {
        this.credentialsPic = credentialsPic == null ? null : credentialsPic.trim();
    }

    /**
     * 打折金额
     * @return discount_no 打折金额
     */
    public String getDiscountNo() {
        return discountNo;
    }

    /**
     * 打折金额
     * @param discountNo 打折金额
     */
    public void setDiscountNo(String discountNo) {
        this.discountNo = discountNo;
    }

    /**
     * 折扣id
     * @return type_id 折扣id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 折扣id
     * @param typeId 折扣id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 打折时间
     * @return discount_time 打折时间
     */
    public Date getDiscountTime() {
        return discountTime;
    }

    /**
     * 打折时间
     * @param discountTime 打折时间
     */
    public void setDiscountTime(Date discountTime) {
        this.discountTime = discountTime;
    }

    /**
     * 收费员
     * @return pay_user_name 收费员
     */
    public String getPayUserName() {
        return payUserName;
    }

    /**
     * 收费员
     * @param payUserName 收费员
     */
    public void setPayUserName(String payUserName) {
        this.payUserName = payUserName == null ? null : payUserName.trim();
    }

    /**
     * 实收金额
     * @return pay_charge 实收金额
     */
    public Float getPayCharge() {
        return payCharge;
    }

    /**
     * 实收金额
     * @param payCharge 实收金额
     */
    public void setPayCharge(Float payCharge) {
        this.payCharge = payCharge;
    }

    /**
     * 应收金额
     * @return account_charge 应收金额
     */
    public Float getAccountCharge() {
        return accountCharge;
    }

    /**
     * 应收金额
     * @param accountCharge 应收金额
     */
    public void setAccountCharge(Float accountCharge) {
        this.accountCharge = accountCharge;
    }

    /**
     * 打折金额
     * @return dis_amount 打折金额
     */
    public Float getDisAmount() {
        return disAmount;
    }

    /**
     * 打折金额
     * @param disAmount 打折金额
     */
    public void setDisAmount(Float disAmount) {
        this.disAmount = disAmount;
    }

    /**
     * 账户金额
     * @return balance_money 账户金额
     */
    public Float getBalanceMoney() {
        return balanceMoney;
    }

    /**
     * 账户金额
     * @param balanceMoney 账户金额
     */
    public void setBalanceMoney(Float balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    /**
     * 支付订单号
     * @return order_num 支付订单号
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * 支付订单号
     * @param orderNum 支付订单号
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
    }

    /**
     * 备注
     * @return memo 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     * @return pay_type 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     * @param payType 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}
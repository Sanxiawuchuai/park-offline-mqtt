package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class ParkSumUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2431380213068228149L;

	/**
     * 
     */
    private Integer id;

    /**
     * 岗亭编号
     */
    private Integer boxId=0;

    /**
     * 登入时间
     */
    private Date loginDate;

    /**
     * 交班时间
     */
    private Date reliefDate;

    /**
     * 交班操作员
     */
    private String userName;

    /**
     * 接班操作员
     */
    private String reliefUserName;

    /**
     * 手动开闸
     */
    private Integer handGate=0;

    /**
     * 计算机开闸
     */
    private Integer computerGate=0;

    /**
     * 临时车入场数
     */
    private Integer tempCarIn=0;

    /**
     * 临时车出场数
     */
    private Integer tempCarOut=0;

    /**
     * 月租车入场数
     */
    private Integer monthCarIn=0;

    /**
     * 月租车出场数
     */
    private Integer monthCarOut=0;

    /**
     * 储值卡数
     */
    private Integer storedCarNum=0;

    /**
     * 储值车收费金额
     */
    private Float storedCarMoney=0f;

    /**
     * 临免卡张数
     */
    private Integer tempFree=0;

    /**
     * 免费卡数
     */
    private Integer freeCarNo=0;

    /**
     * 免费金额
     */
    private Float freeCharge=0f;

    /**
     * 折扣张数
     */
    private Integer disSum=0;

    /**
     * 折扣金额
     */
    private Float disCharge=0f;

    /**
     * 现金收费金额
     */
    private Float cashCharge=0f;

    /**
     * 微信收费金额
     */
    private Float wechatCharge=0f;

    /**
     * 支付定收费金额
     */
    private Float alipayCharge=0f;

    /**
     * 银联支付
     */
    private Float unionpayChagre=0f;

    /**
     * 第三方支付
     */
    private Float thirdpayCharge=0f;

    /**
     * 应收金额
     */
    private Float account=0f;
    
    
    private Integer isLoad=0;
    private String puid ;


    public Integer getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Integer isLoad) {
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
     * 登入时间
     * @return login_date 登入时间
     */
    public Date getLoginDate() {
        return loginDate;
    }

    /**
     * 登入时间
     * @param loginDate 登入时间
     */
    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 交班时间
     * @return relief_date 交班时间
     */
    public Date getReliefDate() {
        return reliefDate;
    }

    /**
     * 交班时间
     * @param reliefDate 交班时间
     */
    public void setReliefDate(Date reliefDate) {
        this.reliefDate = reliefDate;
    }

    /**
     * 交班操作员
     * @return user_name 交班操作员
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 交班操作员
     * @param userName 交班操作员
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 接班操作员
     * @return relief_user_name 接班操作员
     */
    public String getReliefUserName() {
        return reliefUserName;
    }

    /**
     * 接班操作员
     * @param reliefUserName 接班操作员
     */
    public void setReliefUserName(String reliefUserName) {
        this.reliefUserName = reliefUserName == null ? null : reliefUserName.trim();
    }

    /**
     * 手动开闸
     * @return hand_gate 手动开闸
     */
    public Integer getHandGate() {
        return handGate;
    }

    /**
     * 手动开闸
     * @param handGate 手动开闸
     */
    public void setHandGate(Integer handGate) {
        this.handGate = handGate;
    }

    /**
     * 计算机开闸
     * @return computer_gate 计算机开闸
     */
    public Integer getComputerGate() {
        return computerGate;
    }

    /**
     * 计算机开闸
     * @param computerGate 计算机开闸
     */
    public void setComputerGate(Integer computerGate) {
        this.computerGate = computerGate;
    }

    /**
     * 临时车入场数
     * @return temp_car_in 临时车入场数
     */
    public Integer getTempCarIn() {
        return tempCarIn;
    }

    /**
     * 临时车入场数
     * @param tempCarIn 临时车入场数
     */
    public void setTempCarIn(Integer tempCarIn) {
        this.tempCarIn = tempCarIn;
    }

    /**
     * 临时车出场数
     * @return temp_car_out 临时车出场数
     */
    public Integer getTempCarOut() {
        return tempCarOut;
    }

    /**
     * 临时车出场数
     * @param tempCarOut 临时车出场数
     */
    public void setTempCarOut(Integer tempCarOut) {
        this.tempCarOut = tempCarOut;
    }

    /**
     * 月租车入场数
     * @return month_car_in 月租车入场数
     */
    public Integer getMonthCarIn() {
        return monthCarIn;
    }

    /**
     * 月租车入场数
     * @param monthCarIn 月租车入场数
     */
    public void setMonthCarIn(Integer monthCarIn) {
        this.monthCarIn = monthCarIn;
    }

    /**
     * 月租车出场数
     * @return month_car_out 月租车出场数
     */
    public Integer getMonthCarOut() {
        return monthCarOut;
    }

    /**
     * 月租车出场数
     * @param monthCarOut 月租车出场数
     */
    public void setMonthCarOut(Integer monthCarOut) {
        this.monthCarOut = monthCarOut;
    }

    /**
     * 储值卡数
     * @return stored_car_num 储值卡数
     */
    public Integer getStoredCarNum() {
        return storedCarNum;
    }

    /**
     * 储值卡数
     * @param storedCarNum 储值卡数
     */
    public void setStoredCarNum(Integer storedCarNum) {
        this.storedCarNum = storedCarNum;
    }

    /**
     * 储值车收费金额
     * @return stored_car_money 储值车收费金额
     */
    public Float getStoredCarMoney() {
        return storedCarMoney;
    }

    /**
     * 储值车收费金额
     * @param storedCarMoney 储值车收费金额
     */
    public void setStoredCarMoney(Float storedCarMoney) {
        this.storedCarMoney = storedCarMoney;
    }

    /**
     * 临免卡张数
     * @return temp_free 临免卡张数
     */
    public Integer getTempFree() {
        return tempFree;
    }

    /**
     * 临免卡张数
     * @param tempFree 临免卡张数
     */
    public void setTempFree(Integer tempFree) {
        this.tempFree = tempFree;
    }

    /**
     * 免费卡数
     * @return free_car_no 免费卡数
     */
    public Integer getFreeCarNo() {
        return freeCarNo;
    }

    /**
     * 免费卡数
     * @param freeCarNo 免费卡数
     */
    public void setFreeCarNo(Integer freeCarNo) {
        this.freeCarNo = freeCarNo;
    }

    /**
     * 免费金额
     * @return free_charge 免费金额
     */
    public Float getFreeCharge() {
        return freeCharge;
    }

    /**
     * 免费金额
     * @param freeCharge 免费金额
     */
    public void setFreeCharge(Float freeCharge) {
        this.freeCharge = freeCharge;
    }

    /**
     * 折扣张数
     * @return dis_sum 折扣张数
     */
    public Integer getDisSum() {
        return disSum;
    }

    /**
     * 折扣张数
     * @param disSum 折扣张数
     */
    public void setDisSum(Integer disSum) {
        this.disSum = disSum;
    }

    /**
     * 折扣金额
     * @return dis_charge 折扣金额
     */
    public Float getDisCharge() {
        return disCharge;
    }

    /**
     * 折扣金额
     * @param disCharge 折扣金额
     */
    public void setDisCharge(Float disCharge) {
        this.disCharge = disCharge;
    }

    /**
     * 现金收费金额
     * @return cash_charge 现金收费金额
     */
    public Float getCashCharge() {
        return cashCharge;
    }

    /**
     * 现金收费金额
     * @param cashCharge 现金收费金额
     */
    public void setCashCharge(Float cashCharge) {
        this.cashCharge = cashCharge;
    }

    /**
     * 微信收费金额
     * @return wechat_charge 微信收费金额
     */
    public Float getWechatCharge() {
        return wechatCharge;
    }

    /**
     * 微信收费金额
     * @param wechatCharge 微信收费金额
     */
    public void setWechatCharge(Float wechatCharge) {
        this.wechatCharge = wechatCharge;
    }

    /**
     * 支付定收费金额
     * @return alipay_charge 支付定收费金额
     */
    public Float getAlipayCharge() {
        return alipayCharge;
    }

    /**
     * 支付定收费金额
     * @param alipayCharge 支付定收费金额
     */
    public void setAlipayCharge(Float alipayCharge) {
        this.alipayCharge = alipayCharge;
    }

    /**
     * 银联支付
     * @return unionpay_chagre 银联支付
     */
    public Float getUnionpayChagre() {
        return unionpayChagre;
    }

    /**
     * 银联支付
     * @param unionpayChagre 银联支付
     */
    public void setUnionpayChagre(Float unionpayChagre) {
        this.unionpayChagre = unionpayChagre;
    }

    /**
     * 第三方支付
     * @return thirdpay_charge 第三方支付
     */
    public Float getThirdpayCharge() {
        return thirdpayCharge;
    }

    /**
     * 第三方支付
     * @param thirdpayCharge 第三方支付
     */
    public void setThirdpayCharge(Float thirdpayCharge) {
        this.thirdpayCharge = thirdpayCharge;
    }

    /**
     * 应收金额
     * @return account 应收金额
     */
    public Float getAccount() {
        return account;
    }

    /**
     * 应收金额
     * @param account 应收金额
     */
    public void setAccount(Float account) {
        this.account = account;
    }
}
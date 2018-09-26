package com.drzk.vo;

import java.util.Date;

public class VwHandoverRecord {
    /**
     * 岗亭编号
     */
    private Integer boxId;

    /**
     * 岗亭名称
     */
    private String boxName;

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
    private Integer handGate;

    /**
     * 临时车入场数
     */
    private Integer tempCarIn;

    /**
     * 临时车出场数
     */
    private Integer tempCarOut;

    /**
     * 免费金额
     */
    private Float freeCharge;

    /**
     * 折扣张数
     */
    private Integer disSum;

    /**
     * 折扣金额
     */
    private Float disCharge;

    /**
     * 现金收费金额
     */
    private Float cashCharge;

    /**
     * 微信收费金额
     */
    private Float wechatCharge;

    /**
     * 支付定收费金额
     */
    private Float alipayCharge;

    /**
     * 银联支付
     */
    private Float unionpayChagre;

    /**
     * 第三方支付
     */
    private Float thirdpayCharge;

    /**
     * 应收金额
     */
    private Float account;

    /**
     * 
     */
    private Double totalCharge;

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
     * 岗亭名称
     * @return box_name 岗亭名称
     */
    public String getBoxName() {
        return boxName;
    }

    /**
     * 岗亭名称
     * @param boxName 岗亭名称
     */
    public void setBoxName(String boxName) {
        this.boxName = boxName == null ? null : boxName.trim();
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

    /**
     * 
     * @return total_charge 
     */
    public Double getTotalCharge() {
        return totalCharge;
    }

    /**
     * 
     * @param totalCharge 
     */
    public void setTotalCharge(Double totalCharge) {
        this.totalCharge = totalCharge;
    }
}
package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class ParkSumInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4508234846628406186L;

	private Integer id;

    private String puid;

    private Date loginDate;

    private Integer handGate;

    private Integer computerGate;

    private Integer tempCarIn;

    private Integer tempCarOut;

    private Integer monthCarIn;

    private Integer monthCarOut;

    private Integer storedCarNum;

    private Float storedCarMoney;

    private Integer tempFree;

    private Integer freeCarNo;

    private Float freeCharge;

    private Integer disSum;

    private Float disCharge;

    private Float cashCharge;

    private Float wechatCharge;

    private Float alipayCharge;

    private Float unionpayChagre;

    private Float thirdpayCharge;

    private Float account;

    private Byte isLoad;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid == null ? null : puid.trim();
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Integer getHandGate() {
        return handGate;
    }

    public void setHandGate(Integer handGate) {
        this.handGate = handGate;
    }

    public Integer getComputerGate() {
        return computerGate;
    }

    public void setComputerGate(Integer computerGate) {
        this.computerGate = computerGate;
    }

    public Integer getTempCarIn() {
        return tempCarIn;
    }

    public void setTempCarIn(Integer tempCarIn) {
        this.tempCarIn = tempCarIn;
    }

    public Integer getTempCarOut() {
        return tempCarOut;
    }

    public void setTempCarOut(Integer tempCarOut) {
        this.tempCarOut = tempCarOut;
    }

    public Integer getMonthCarIn() {
        return monthCarIn;
    }

    public void setMonthCarIn(Integer monthCarIn) {
        this.monthCarIn = monthCarIn;
    }

    public Integer getMonthCarOut() {
        return monthCarOut;
    }

    public void setMonthCarOut(Integer monthCarOut) {
        this.monthCarOut = monthCarOut;
    }

    public Integer getStoredCarNum() {
        return storedCarNum;
    }

    public void setStoredCarNum(Integer storedCarNum) {
        this.storedCarNum = storedCarNum;
    }

    public Float getStoredCarMoney() {
        return storedCarMoney;
    }

    public void setStoredCarMoney(Float storedCarMoney) {
        this.storedCarMoney = storedCarMoney;
    }

    public Integer getTempFree() {
        return tempFree;
    }

    public void setTempFree(Integer tempFree) {
        this.tempFree = tempFree;
    }

    public Integer getFreeCarNo() {
        return freeCarNo;
    }

    public void setFreeCarNo(Integer freeCarNo) {
        this.freeCarNo = freeCarNo;
    }

    public Float getFreeCharge() {
        return freeCharge;
    }

    public void setFreeCharge(Float freeCharge) {
        this.freeCharge = freeCharge;
    }

    public Integer getDisSum() {
        return disSum;
    }

    public void setDisSum(Integer disSum) {
        this.disSum = disSum;
    }

    public Float getDisCharge() {
        return disCharge;
    }

    public void setDisCharge(Float disCharge) {
        this.disCharge = disCharge;
    }

    public Float getCashCharge() {
        return cashCharge;
    }

    public void setCashCharge(Float cashCharge) {
        this.cashCharge = cashCharge;
    }

    public Float getWechatCharge() {
        return wechatCharge;
    }

    public void setWechatCharge(Float wechatCharge) {
        this.wechatCharge = wechatCharge;
    }

    public Float getAlipayCharge() {
        return alipayCharge;
    }

    public void setAlipayCharge(Float alipayCharge) {
        this.alipayCharge = alipayCharge;
    }

    public Float getUnionpayChagre() {
        return unionpayChagre;
    }

    public void setUnionpayChagre(Float unionpayChagre) {
        this.unionpayChagre = unionpayChagre;
    }

    public Float getThirdpayCharge() {
        return thirdpayCharge;
    }

    public void setThirdpayCharge(Float thirdpayCharge) {
        this.thirdpayCharge = thirdpayCharge;
    }

    public Float getAccount() {
        return account;
    }

    public void setAccount(Float account) {
        this.account = account;
    }

    public Byte getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Byte isLoad) {
        this.isLoad = isLoad;
    }
}
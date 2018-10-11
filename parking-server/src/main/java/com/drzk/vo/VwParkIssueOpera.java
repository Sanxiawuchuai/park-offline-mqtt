package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class VwParkIssueOpera implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8449984988042182038L;

	private String perName;

    private Integer yktId;

    private Integer cardType;

    private String carNo;

    private Integer operType;

    private Float beforeMoney;

    private Float balanceMoney;

    private Date startDate;

    private Date endDate;

    private Byte payType;

    private String placeName;

    private Date relaseTime;

    private String guid;

    private String operaName;

    private String memo;

    private Date frontDate;

    private Date createDate;
    
    
    private String address;
    private String phone;
    private String orderNo;
    private String cardId;
    private Double foregift;
    private Integer pid;
    private String createUserName;
    private Byte isLoad;
    
    
    public Byte getIsLoad()
	{
		return isLoad;
	}
	public void setIsLoad(Byte isLoad)
	{
		this.isLoad = isLoad;
	}
	public String getCreateUserName()
	{
		return createUserName;
	}
	public void setCreateUserName(String createUserName)
	{
		this.createUserName = createUserName;
	}
	/** 人员ID */
    public Integer getPid()
	{
		return pid;
	}
    /** 人员ID */
	public void setPid(Integer pid)
	{
		this.pid = pid;
	}

	public String getAddress()
	{
		return address;
	}

	public void setAddress(String address)
	{
		this.address = address;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getOrderNo()
	{
		return orderNo;
	}

	public void setOrderNo(String orderNo)
	{
		this.orderNo = orderNo;
	}

	public String getCardId()
	{
		return cardId;
	}

	public void setCardId(String cardId)
	{
		this.cardId = cardId;
	}

	public Double getForegift()
	{
		return foregift;
	}

	public void setForegift(Double foregift)
	{
		this.foregift = foregift;
	}


	public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }

    public Integer getYktId() {
        return yktId;
    }

    public void setYktId(Integer yktId) {
        this.yktId = yktId;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Integer getOperType() {
        return operType;
    }

    public void setOperType(Integer operType) {
        this.operType = operType;
    }

    public Float getBeforeMoney() {
        return beforeMoney;
    }

    public void setBeforeMoney(Float beforeMoney) {
        this.beforeMoney = beforeMoney;
    }

    public Float getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(Float balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Byte getPayType() {
        return payType;
    }

    public void setPayType(Byte payType) {
        this.payType = payType;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public Date getRelaseTime() {
        return relaseTime;
    }

    public void setRelaseTime(Date relaseTime) {
        this.relaseTime = relaseTime;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    public String getOperaName() {
        return operaName;
    }

    public void setOperaName(String operaName) {
        this.operaName = operaName == null ? null : operaName.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getFrontDate() {
        return frontDate;
    }

    public void setFrontDate(Date frontDate) {
        this.frontDate = frontDate;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
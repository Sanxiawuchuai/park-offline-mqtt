package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class ParkDisDetail implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4554498336728752855L;

	/**
     * 自增id
     */
    private Integer id;

    /**
     * 出场类型 0,出场.1，中心
     */
    private Byte outType;

    /**
     * 打折模式ID
     */
    private String discountId;

    /**
     * 卡id
     */
    private String cardId;

    /**
     * 出场时间
     */
    private Date outTime;

    /**
     * 打折时间
     */
    private Date discountTime;

    /**
     * 打折金额
     */
    private Float disAmount;

    /**
     * 0表示线下，1表示线下
     */
    private Byte disType;

    /**
     * 线上打折id号
     */
    private String onlineId;

    private String puid;
    private Integer isLoad; 
    private Byte delFrag;
    private String carNo;
    private Date inTime;
    
    public Date getInTime() {
		return inTime;
	}

	public void setInTime(Date inTime) {
		this.inTime = inTime;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getPuid()
	{
		return puid;
	}

	public void setPuid(String puid)
	{
		this.puid = puid;
	}

	public Integer getIsLoad()
	{
		return isLoad;
	}

	public void setIsLoad(Integer isLoad)
	{
		this.isLoad = isLoad;
	}

	public Byte getDelFrag()
	{
		return delFrag;
	}

	public void setDelFrag(Byte delFrag)
	{
		this.delFrag = delFrag;
	}

	/**
     * 自增id
     * @return id 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增id
     * @param id 自增id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 出场类型 0,出场.1，中心
     * @return out_type 出场类型 0,出场.1，中心
     */
    public Byte getOutType() {
        return outType;
    }

    /**
     * 出场类型 0,出场.1，中心
     * @param outType 出场类型 0,出场.1，中心
     */
    public void setOutType(Byte outType) {
        this.outType = outType;
    }

    /**
     * 打折模式ID
     * @return discount_id 打折模式ID
     */
    public String getDiscountId() {
        return discountId;
    }

    /**
     * 打折模式ID
     * @param discountId 打折模式ID
     */
    public void setDiscountId(String discountId) {
        this.discountId = discountId;
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
     * 出场时间
     * @return out_time 出场时间
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * 出场时间
     * @param outTime 出场时间
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
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
     * 0表示线下，1表示线下
     * @return dis_type 0表示线下，1表示线下
     */
    public Byte getDisType() {
        return disType;
    }

    /**
     * 0表示线下，1表示线下
     * @param disType 0表示线下，1表示线下
     */
    public void setDisType(Byte disType) {
        this.disType = disType;
    }

    /**
     * 线上打折id号
     * @return online_id 线上打折id号
     */
    public String getOnlineId() {
        return onlineId;
    }

    /**
     * 线上打折id号
     * @param onlineId 线上打折id号
     */
    public void setOnlineId(String onlineId) {
        this.onlineId = onlineId == null ? null : onlineId.trim();
    }
}
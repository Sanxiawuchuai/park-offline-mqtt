package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class YktCardIssuePark implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3965498145724659275L;

	/**
     * id，与主卡id的值一样
     */
    private Integer yktId;

    /**
     * 卡类型
     */
    private Integer cardType;

    /**
     * 月租类型
     */
    private Integer sType;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 车牌类型
     */
    private Integer carNoType;

    /**
     * 车颜色
     */
    private String carColour;

    /**
     * 有效起始日期
     */
    private Date startDate;

    /**
     * 有效终止日期
     */
    private Date endDate;

    /**
     * 权限级别
     */
    private Integer planId;

    /**
     * id，与主卡id的值一样
     * @return ykt_id id，与主卡id的值一样
     */
    public Integer getYktId() {
        return yktId;
    }

    /**
     * id，与主卡id的值一样
     * @param yktId id，与主卡id的值一样
     */
    public void setYktId(Integer yktId) {
        this.yktId = yktId;
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
     * 月租类型
     * @return s_type 月租类型
     */
    public Integer getsType() {
        return sType;
    }

    /**
     * 月租类型
     * @param sType 月租类型
     */
    public void setsType(Integer sType) {
        this.sType = sType;
    }

    /**
     * 车牌号
     * @return car_no 车牌号
     */
    public String getCarNo() {
        return carNo;
    }

    /**
     * 车牌号
     * @param carNo 车牌号
     */
    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    /**
     * 车牌类型
     * @return car_no_type 车牌类型
     */
    public Integer getCarNoType() {
        return carNoType;
    }

    /**
     * 车牌类型
     * @param carNoType 车牌类型
     */
    public void setCarNoType(Integer carNoType) {
        this.carNoType = carNoType;
    }

    /**
     * 车颜色
     * @return car_colour 车颜色
     */
    public String getCarColour() {
        return carColour;
    }

    /**
     * 车颜色
     * @param carColour 车颜色
     */
    public void setCarColour(String carColour) {
        this.carColour = carColour == null ? null : carColour.trim();
    }

    /**
     * 有效起始日期
     * @return start_date 有效起始日期
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 有效起始日期
     * @param startDate 有效起始日期
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 有效终止日期
     * @return end_date 有效终止日期
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 有效终止日期
     * @param endDate 有效终止日期
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 权限级别
     * @return plan_id 权限级别
     */
    public Integer getPlanId() {
        return planId;
    }

    /**
     * 权限级别
     * @param planId 权限级别
     */
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }
}
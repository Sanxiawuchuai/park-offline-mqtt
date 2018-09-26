package com.drzk.online.vo;

import java.util.Date;

public class CarportAndCarVO {

    private String carportNo;
    private Integer cardTypeId;
    private String cardTypeName;

    private Double deposit;
    private Double balanceMoney;
    private String carNo;
    private String plateStyle;
    private String stopNo;
    private Integer carStatus;
    private Date startTime;
    private Date endTime;
    private Integer chargeType;
    private String remark;
    private Integer status;            //卡状态
    private Integer stype;
    private Integer payType;        //支付类型

    /**
     * 1 连续性计费 如6月份正常月卡，7月份未交费，8月份需使用需要连7、8月的一起充值才可以用）
     * 2按天起计（如8月21号续期3个月，就到11月21日为月卡到期时间）
     * 3 自然月计费（足月续费，如1月份充值3个月，那就是1月份使用31天的月卡，2月份只有28天，3月31天，足月使用）
     *
     * @return
     */
    public Integer getChargeType() {
        return chargeType;
    }

    /**
     * 1 连续性计费 如6月份正常月卡，7月份未交费，8月份需使用需要连7、8月的一起充值才可以用）
     * 2按天起计（如8月21号续期3个月，就到11月21日为月卡到期时间）
     * 3 自然月计费（足月续费，如1月份充值3个月，那就是1月份使用31天的月卡，2月份只有28天，3月31天，足月使用）
     *
     * @return
     */
    public void setChargeType(Integer chargeType) {
        this.chargeType = chargeType;
    }

    /**
     * 车位坐标
     */
    public String getCarportNo() {
        return carportNo;
    }

    /**
     * 车位坐标
     */
    public void setCarportNo(String carportNo) {
        this.carportNo = carportNo;
    }

    public Integer getCardTypeId() {
        return cardTypeId;
    }

    public void setCardTypeId(Integer cardTypeId) {
        this.cardTypeId = cardTypeId;
    }

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * 押金
     */
    public Double getDeposit() {
        return deposit;
    }

    /**
     * 押金
     */
    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    /**
     * 金额
     */
    public Double getBalanceMoney() {
        return balanceMoney;
    }

    /**
     * 金额
     */
    public void setBalanceMoney(Double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    /**
     * 车辆颜色
     */
    public String getPlateStyle() {
        return plateStyle;
    }

    /**
     * 车辆颜色
     */
    public void setPlateStyle(String plateStyle) {
        this.plateStyle = plateStyle;
    }

    /**
     * 停车卡编号
     */
    public String getStopNo() {
        return stopNo;
    }

    /**
     * 停车卡编号
     */
    public void setStopNo(String stopNo) {
        this.stopNo = stopNo;
    }

    /**
     * 车辆状态    状态 默认待审核状态1,2处理中，3审核通过
     */
    public Integer getCarStatus() {
        return carStatus;
    }

    /**
     * 车辆状态    状态 默认待审核状态1,2处理中，3审核通过
     */
    public void setCarStatus(Integer carStatus) {
        this.carStatus = carStatus;
    }

    /**
     * 卡有限期 开始时间
     */
    public Date getStartTime() {
        return startTime;
    }

    /**
     * 卡有限期 开始时间
     */
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /**
     * 卡有限期 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 卡有限期 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStype() {
        return stype;
    }

    public void setStype(Integer stype) {
        this.stype = stype;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}

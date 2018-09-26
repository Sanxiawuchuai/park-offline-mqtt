package com.drzk.charge.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by drzk on 2018/06/27.
 */
public class PiecewiseTimes {
    //车辆进场时间
    private Date carInTime;
    //车辆出场时间
    private Date carOutTime;
    //开始时段
    private Date startTime;
    //结束时段
    private Date endTime;
    //设置时间长度
    private long timeLength;
    //开始时间长度
    private int startTimeLength;
    // 过0点补时
    private Integer crossh0Day ;
    //免费时间
    private int freeTime;
    //是否包含免费时间
    private Integer isFreeTime;
    //收费是否有小数
    private Integer isUnitType;
    //是否跨段补时
    private Integer isCrossTime;
    //最高收费
    private double topMoney;
    //跨段收费金额
    private double aZero;
    //跨段类型(1.按24小时计算 2.按过零点计算)
    private Integer aType;

    //小时数组
    private List<HourTime> hourTimeList;


    public Date getCarInTime() {
        return carInTime;
    }

    public void setCarInTime(Date carInTime) {
        this.carInTime = carInTime;
    }

    public Date getCarOutTime() {
        return carOutTime;
    }

    public void setCarOutTime(Date carOutTime) {
        this.carOutTime = carOutTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public int getStartTimeLength() {
        return startTimeLength;
    }

    public void setStartTimeLength(int startTimeLength) {
        this.startTimeLength = startTimeLength;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public double getTopMoney() {
        return topMoney;
    }

    public void setTopMoney(double topMoney) {
        this.topMoney = topMoney;
    }

    public double getaZero() {
        return aZero;
    }

    public void setaZero(double aZero) {
        this.aZero = aZero;
    }

    public Integer getaType() {
        return aType;
    }

    public void setaType(Integer aType) {
        this.aType = aType;
    }

    public Integer getCrossh0Day() {
        return crossh0Day;
    }

    public void setCrossh0Day(Integer crossh0Day) {
        this.crossh0Day = crossh0Day;
    }

    public Integer getIsFreeTime() {
        return isFreeTime;
    }

    public void setIsFreeTime(Integer isFreeTime) {
        this.isFreeTime = isFreeTime;
    }

    public Integer getIsUnitType() {
        return isUnitType;
    }

    public void setIsUnitType(Integer isUnitType) {
        this.isUnitType = isUnitType;
    }

    public Integer getIsCrossTime() {
        return isCrossTime;
    }

    public void setIsCrossTime(Integer isCrossTime) {
        this.isCrossTime = isCrossTime;
    }

    public List<HourTime> getHourTimeList() {
        return hourTimeList;
    }

    public void setHourTimeList(List<HourTime> hourTimeList) {
        this.hourTimeList = hourTimeList;
    }

    public long getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(long timeLength) {
        this.timeLength = timeLength;
    }
}

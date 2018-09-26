package com.drzk.charge.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by drzk on 2018/06/29.
 */
public class DayPiecewise {
    //收费类型
    String BillingMode;
    //该段最高收费
    double maxMoney;
    //天片段的开始时间
    Date startTime;
    //天片段的结束时间
    Date endTime;
    //车辆在该段的开始时间
    Date carStartTime;
    //车辆在该段的结束时间
    Date carEndTime;
    //停车时间
    long  carTimeLength;
    //分钟片段
    private MinPiecewise minPiecewise;
    //当天总收费
    double sumMoney;
    //序号
    int id;
    //周期计费
    boolean isPeriodiCharging;
    //周期之后有首段
    boolean isfirstParagraph;
    //免费时间
    int freeTime;
    //是否包含免费时间
    private boolean is_FreeTime;

    //是否包含跨段补时
    private boolean is_crossTime;

    public boolean isIs_crossTime() {
        return is_crossTime;
    }

    public void setIs_crossTime(boolean is_crossTime) {
        this.is_crossTime = is_crossTime;
    }

    public boolean isIs_FreeTime() {
        return is_FreeTime;
    }

    public void setIs_FreeTime(boolean is_FreeTime) {
        this.is_FreeTime = is_FreeTime;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }



    public String getBillingMode() {
        return BillingMode;
    }

    public void setBillingMode(String billingMode) {
        BillingMode = billingMode;
    }

    public double getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(double maxMoney) {
        this.maxMoney = maxMoney;
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

    public Date getCarStartTime() {
        return carStartTime;
    }

    public void setCarStartTime(Date carStartTime) {
        this.carStartTime = carStartTime;
    }

    public Date getCarEndTime() {
        return carEndTime;
    }

    public void setCarEndTime(Date carEndTime) {
        this.carEndTime = carEndTime;
    }

    public long getCarTimeLength() {
        return carTimeLength;
    }

    public void setCarTimeLength(long carTimeLength) {
        this.carTimeLength = carTimeLength;
    }

    public MinPiecewise getMinPiecewise() {
        return minPiecewise;
    }

    public void setMinPiecewise(MinPiecewise minPiecewise) {
        this.minPiecewise = minPiecewise;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isPeriodiCharging() {
        return isPeriodiCharging;
    }

    public void setPeriodiCharging(boolean periodiCharging) {
        isPeriodiCharging = periodiCharging;
    }

    public boolean isIsfirstParagraph() {
        return isfirstParagraph;
    }

    public void setIsfirstParagraph(boolean isfirstParagraph) {
        this.isfirstParagraph = isfirstParagraph;
    }
}

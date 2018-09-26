package com.drzk.charge.bean;

import java.util.List;

/**
 * Created by drzk on 2018/06/29.
 */
public class MinPiecewise {
    //总分钟数
    double sumMoney;
    //多少分钟为单位
    int min;
    //单位分钟收多少钱
    double money;
    //该时间段每次收费金额
    double frequencyMoney;
    //该时段的最高收费
    double maxMoney;
    //是否跨段补时
    boolean isCrossTime;
    //是否免费时间
    boolean isFreeTime;
    //免费时间
    int freeTime;
    //序号
    int id;
    //周期计费方式
    List<TimeList> timeLists;

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getFrequencyMoney() {
        return frequencyMoney;
    }

    public void setFrequencyMoney(double frequencyMoney) {
        this.frequencyMoney = frequencyMoney;
    }

    public double getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(double maxMoney) {
        this.maxMoney = maxMoney;
    }

    public boolean isCrossTime() {
        return isCrossTime;
    }

    public void setCrossTime(boolean crossTime) {
        isCrossTime = crossTime;
    }

    public boolean isFreeTime() {
        return isFreeTime;
    }

    public void setFreeTime(boolean freeTime) {
        isFreeTime = freeTime;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<TimeList> getTimeLists() {
        return timeLists;
    }

    public void setTimeLists(List<TimeList> timeLists) {
        this.timeLists = timeLists;
    }
}

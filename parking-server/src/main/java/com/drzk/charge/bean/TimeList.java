package com.drzk.charge.bean;

/**
 * Created by drzk on 2018/06/29.
 */
public class TimeList {
    //时段长度
    int timeLength;
    //计费单位
    int timeUnit;
    //计费金额
    double money;

    public int getTimeLength() {
        return timeLength;
    }

    public void setTimeLength(int timeLength) {
        this.timeLength = timeLength;
    }

    public int getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(int timeUnit) {
        this.timeUnit = timeUnit;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}

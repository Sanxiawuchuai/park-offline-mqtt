package com.drzk.charge.bean;

/**
 * Created by drzk on 2018/07/04.
 */
public class DynamicParam {
    //免费时间
    private int freeTime;
    //总金额
    private double sumMoney;
    //跨段时间
    private int surplusMinDate;
    //是否免费时间
    private boolean is_FreeTime;
    //是否跨段时间
    private boolean isSurplusMinDate;
    //出场时间
    private String strDateOut;

    public String getStrDateOut() {
        return strDateOut;
    }

    public void setStrDateOut(String strDateOut) {
        this.strDateOut = strDateOut;
    }

    public boolean isSurplusMinDate() {
        return isSurplusMinDate;
    }

    public void setSurplusMinDate(boolean surplusMinDate) {
        isSurplusMinDate = surplusMinDate;
    }

    public int getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(int freeTime) {
        this.freeTime = freeTime;
    }

    public double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public int getSurplusMinDate() {
        return surplusMinDate;
    }

    public void setSurplusMinDate(int surplusMinDate) {
        this.surplusMinDate = surplusMinDate;
    }

    public boolean isIs_FreeTime() {
        return is_FreeTime;
    }

    public void setIs_FreeTime(boolean is_FreeTime) {
        this.is_FreeTime = is_FreeTime;
    }
}

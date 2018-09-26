package com.drzk.charge.bean;

import java.util.Date;
import java.util.List;

/**
 * Created by drzk on 2018/06/29.
 */
public class DynamicPicwise {
    //用来判断是否是同一个周期段
    private byte charge_id;
    //车辆进场时间
    private Date carInTime;
    //车辆出场时间
    private Date carOutTime;
    //开始时段
    private Date startTime;
    //结束时段
    private Date endTime;
    //设置时间长度
    private int timeLength;
    //开始时间长度
    private int startTimeLength;
    //开始时间
    private Date starHour;
    //结束时间
    private Date endHour;
    //是否包含跨段补时
    private byte is_hour_cross;
    //是否周期计费
    private byte is_periodic_charge;
    //周期计费之后有首段
    private byte is_first_periodic;
    //是否首段
    private byte is_first_cycle;
    //计费类型(0按时间计费，1：按次计费)
    private byte charge_type;
    //最高收费
    private double top_money;
    //免费时间
    private int free_time;
    //是否包含免费时间
    private int is_free_time;
    //计费单位
    private int charge_unit;
    //计费金额
    private int billing_amount;
    //时间长度
    private int  time_length ;
    //是否月份跨段补时
    private byte is_month_cross;
    //天片段
    private List<DayPiecewise> dayPiecewiseList;



    public List<DayPiecewise> getDayPiecewiseList() {
        return dayPiecewiseList;
    }

    public void setDayPiecewiseList(List<DayPiecewise> dayPiecewiseList) {
        this.dayPiecewiseList = dayPiecewiseList;
    }




    public byte getCharge_id() {
        return charge_id;
    }
    public byte getIs_hour_cross() {
        return is_hour_cross;
    }

    public void setIs_hour_cross(byte is_hour_cross) {
        this.is_hour_cross = is_hour_cross;
    }

    public byte getIs_periodic_charge() {
        return is_periodic_charge;
    }

    public void setIs_periodic_charge(byte is_periodic_charge) {
        this.is_periodic_charge = is_periodic_charge;
    }
    public void setCharge_id(byte charge_id) {
        this.charge_id = charge_id;
    }

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

    public int getTimeLength() {
        return timeLength;
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

    public Date getStarHour() {
        return starHour;
    }

    public void setStarHour(Date starHour) {
        this.starHour = starHour;
    }

    public Date getEndHour() {
        return endHour;
    }

    public void setEndHour(Date endHour) {
        this.endHour = endHour;
    }




    public byte getIs_first_periodic() {
        return is_first_periodic;
    }

    public void setIs_first_periodic(byte is_first_periodic) {
        this.is_first_periodic = is_first_periodic;
    }

    public byte getIs_first_cycle() {
        return is_first_cycle;
    }

    public void setIs_first_cycle(byte is_first_cycle) {
        this.is_first_cycle = is_first_cycle;
    }

    public byte getCharge_type() {
        return charge_type;
    }

    public void setCharge_type(byte charge_type) {
        this.charge_type = charge_type;
    }

    public double getTop_money() {
        return top_money;
    }

    public void setTop_money(double top_money) {
        this.top_money = top_money;
    }

    public int getFree_time() {
        return free_time;
    }

    public void setFree_time(int free_time) {
        this.free_time = free_time;
    }



    public int getCharge_unit() {
        return charge_unit;
    }

    public void setCharge_unit(int charge_unit) {
        this.charge_unit = charge_unit;
    }

    public int getBilling_amount() {
        return billing_amount;
    }

    public void setBilling_amount(int billing_amount) {
        this.billing_amount = billing_amount;
    }

    public int getTime_length() {
        return time_length;
    }

    public void setTime_length(int time_length) {
        this.time_length = time_length;
    }

    public int getIs_free_time() {
        return is_free_time;
    }

    public void setIs_free_time(int is_free_time) {
        this.is_free_time = is_free_time;
    }

    public byte getIs_month_cross() {
        return is_month_cross;
    }

    public void setIs_month_cross(byte is_month_cross) {
        this.is_month_cross = is_month_cross;
    }
}

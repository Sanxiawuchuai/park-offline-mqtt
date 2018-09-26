package com.drzk.charge.bean;


import java.util.Date;
import java.util.List;

/**
 * Created by drzk on 2018/06/27.
 */
public class  ParkStarndardCharge<T> {

    //车辆进场时间
    private Date carInTime;
    //车辆出场时间
    private Date carOutTime;

    //时间集合
    private List<T> piecewiseTimes;

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

    public List<T> getPiecewiseTimes() {
        return piecewiseTimes;
    }

    public void setPiecewiseTimes(List<T> piecewiseTimes) {
        this.piecewiseTimes = piecewiseTimes;
    }
}

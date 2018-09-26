package com.drzk.online.vo;

/**
 * 车位组转化的vo类，实现线下与线上的json的联调
 */
public class ParkCarGroupVO extends SuperBody {

    private String carportGroupName;        //车位组名称
    private Integer carportNumber;          //车位数
    private Integer fullHold;               //车位类型
    private String remark;                  //备注

    public String getCarportGroupName() {
        return carportGroupName;
    }

    public void setCarportGroupName(String carportGroupName) {
        this.carportGroupName = carportGroupName;
    }

    public Integer getCarportNumber() {
        return carportNumber;
    }

    public void setCarportNumber(Integer carportNumber) {
        this.carportNumber = carportNumber;
    }

    public Integer getFullHold() {
        return fullHold;
    }

    public void setFullHold(Integer fullHold) {
        this.fullHold = fullHold;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

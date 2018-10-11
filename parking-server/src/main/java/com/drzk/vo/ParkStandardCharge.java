package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 计费规则设置
 */
public class ParkStandardCharge implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7329953407961887034L;
	private Integer id;                     //主键id
    private String puid;                    //uuid
    private Integer cardType;               //卡类型
    private Integer freeTime;               //免费时间
    private Integer isFreeTime;             //是否包含免费时间
    private Integer unitType;               //收费单位（0——》元，1——》角）
    private Integer isUnitType;             //是否收费有小数
    private Double topMoney;               //最高收费
    private Double chHour1;
    private Double chHour2;
    private Double chHour3;
    private Double chHour4;
    private Double chHour5;
    private Double chHour6;
    private Double chHour7;
    private Double chHour8;
    private Double chHour9;
    private Double chHour10;
    private Double chHour11;
    private Double chHour12;
    private Double chHour13;
    private Double chHour14;
    private Double chHour15;
    private Double chHour16;
    private Double chHour17;
    private Double chHour18;
    private Double chHour19;
    private Double chHour20;
    private Double chHour21;
    private Double chHour22;
    private Double chHour23;
    private Double chHour24;
    private Double aZero;                   //跨段收费金额
    private Integer isCrossTime;                //是否包含跨段收费金额
    private Integer aType;                  //跨段类型
    private Integer isLoad;             //上传标记 0.未上传 1.已上传
    private Date startTime;                 //开始时间
    private Date endTime;                   //结束时间
    private Integer delFrag;

    public Integer getDelFrag() {
		return delFrag;
	}

	public void setDelFrag(Integer delFrag) {
		this.delFrag = delFrag;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(Integer freeTime) {
        this.freeTime = freeTime;
    }

    public Integer getIsFreeTime() {
        return isFreeTime;
    }

    public void setIsFreeTime(Integer isFreeTime) {
        this.isFreeTime = isFreeTime;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    public Integer getIsUnitType() {
        return isUnitType;
    }

    public void setIsUnitType(Integer isUnitType) {
        this.isUnitType = isUnitType;
    }

    public Double getTopMoney() {
        return topMoney;
    }

    public void setTopMoney(Double topMoney) {
        this.topMoney = topMoney;
    }

    public Double getChHour1() {
        return chHour1;
    }

    public void setChHour1(Double chHour1) {
        this.chHour1 = chHour1;
    }

    public Double getChHour2() {
        return chHour2;
    }

    public void setChHour2(Double chHour2) {
        this.chHour2 = chHour2;
    }

    public Double getChHour3() {
        return chHour3;
    }

    public void setChHour3(Double chHour3) {
        this.chHour3 = chHour3;
    }

    public Double getChHour4() {
        return chHour4;
    }

    public void setChHour4(Double chHour4) {
        this.chHour4 = chHour4;
    }

    public Double getChHour5() {
        return chHour5;
    }

    public void setChHour5(Double chHour5) {
        this.chHour5 = chHour5;
    }

    public Double getChHour6() {
        return chHour6;
    }

    public void setChHour6(Double chHour6) {
        this.chHour6 = chHour6;
    }

    public Double getChHour7() {
        return chHour7;
    }

    public void setChHour7(Double chHour7) {
        this.chHour7 = chHour7;
    }

    public Double getChHour8() {
        return chHour8;
    }

    public void setChHour8(Double chHour8) {
        this.chHour8 = chHour8;
    }

    public Double getChHour9() {
        return chHour9;
    }

    public void setChHour9(Double chHour9) {
        this.chHour9 = chHour9;
    }

    public Double getChHour10() {
        return chHour10;
    }

    public void setChHour10(Double chHour10) {
        this.chHour10 = chHour10;
    }

    public Double getChHour11() {
        return chHour11;
    }

    public void setChHour11(Double chHour11) {
        this.chHour11 = chHour11;
    }

    public Double getChHour12() {
        return chHour12;
    }

    public void setChHour12(Double chHour12) {
        this.chHour12 = chHour12;
    }

    public Double getChHour13() {
        return chHour13;
    }

    public void setChHour13(Double chHour13) {
        this.chHour13 = chHour13;
    }

    public Double getChHour14() {
        return chHour14;
    }

    public void setChHour14(Double chHour14) {
        this.chHour14 = chHour14;
    }

    public Double getChHour15() {
        return chHour15;
    }

    public void setChHour15(Double chHour15) {
        this.chHour15 = chHour15;
    }

    public Double getChHour16() {
        return chHour16;
    }

    public void setChHour16(Double chHour16) {
        this.chHour16 = chHour16;
    }

    public Double getChHour17() {
        return chHour17;
    }

    public void setChHour17(Double chHour17) {
        this.chHour17 = chHour17;
    }

    public Double getChHour18() {
        return chHour18;
    }

    public void setChHour18(Double chHour18) {
        this.chHour18 = chHour18;
    }

    public Double getChHour19() {
        return chHour19;
    }

    public void setChHour19(Double chHour19) {
        this.chHour19 = chHour19;
    }

    public Double getChHour20() {
        return chHour20;
    }

    public void setChHour20(Double chHour20) {
        this.chHour20 = chHour20;
    }

    public Double getChHour21() {
        return chHour21;
    }

    public void setChHour21(Double chHour21) {
        this.chHour21 = chHour21;
    }

    public Double getChHour22() {
        return chHour22;
    }

    public void setChHour22(Double chHour22) {
        this.chHour22 = chHour22;
    }

    public Double getChHour23() {
        return chHour23;
    }

    public void setChHour23(Double chHour23) {
        this.chHour23 = chHour23;
    }

    public Double getChHour24() {
        return chHour24;
    }

    public void setChHour24(Double chHour24) {
        this.chHour24 = chHour24;
    }

    public Double getaZero() {
        return aZero;
    }

    public void setaZero(Double aZero) {
        this.aZero = aZero;
    }

    public Integer getIsCrossTime() {
        return isCrossTime;
    }

    public void setIsCrossTime(Integer isCrossTime) {
        this.isCrossTime = isCrossTime;
    }

    public Integer getaType() {
        return aType;
    }

    public void setaType(Integer aType) {
        this.aType = aType;
    }

    public Integer getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
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

}
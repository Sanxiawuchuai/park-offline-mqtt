package com.drzk.vo;

import java.io.Serializable;

public class ParkOverTimeSet implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3572678712453679688L;

	private Integer id;

    private String puid;

    private Integer cardType;

    private Integer overTimeMinute;

    private Integer freeInclude;

    private Double overTimeAmount;

    private Integer overTimeUnit;

    private Integer freeMinute;
    
    private Integer isLoad;
    
    public String getPuid() {
		return puid;
	}

	public void setPuid(String puid) {
		this.puid = puid;
	}

	public Integer getIsLoad() {
		return isLoad;
	}

	public void setIsLoad(Integer isLoad) {
		this.isLoad = isLoad;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getOverTimeMinute() {
        return overTimeMinute;
    }

    public void setOverTimeMinute(Integer overTimeMinute) {
        this.overTimeMinute = overTimeMinute;
    }

    public Integer getFreeInclude() {
        return freeInclude;
    }

    public void setFreeInclude(Integer freeInclude) {
        this.freeInclude = freeInclude;
    }

    public Double getOverTimeAmount() {
        return overTimeAmount;
    }

    public void setOverTimeAmount(Double overTimeAmount) {
        this.overTimeAmount = overTimeAmount;
    }

    public Integer getOverTimeUnit() {
        return overTimeUnit;
    }

    public void setOverTimeUnit(Integer overTimeUnit) {
        this.overTimeUnit = overTimeUnit;
    }

    public Integer getFreeMinute() {
        return freeMinute;
    }

    public void setFreeMinute(Integer freeMinute) {
        this.freeMinute = freeMinute;
    }
}
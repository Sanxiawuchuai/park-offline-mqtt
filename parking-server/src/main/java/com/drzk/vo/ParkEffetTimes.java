package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class ParkEffetTimes implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2041864414413300737L;

	private Integer id;

    private Byte cardType;

    private Date bTime;

    private Date eTime;

    private Byte sType;

    private Byte multiple;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Byte getCardType() {
        return cardType;
    }

    public void setCardType(Byte cardType) {
        this.cardType = cardType;
    }

    public Date getbTime() {
        return bTime;
    }

    public void setbTime(Date bTime) {
        this.bTime = bTime;
    }

    public Date geteTime() {
        return eTime;
    }

    public void seteTime(Date eTime) {
        this.eTime = eTime;
    }

    public Byte getsType() {
        return sType;
    }

    public void setsType(Byte sType) {
        this.sType = sType;
    }

    public Byte getMultiple() {
        return multiple;
    }

    public void setMultiple(Byte multiple) {
        this.multiple = multiple;
    }
}
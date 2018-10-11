package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 卡类型
 */
public class CardType implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5698900787433224751L;
	private Integer cardTypeId; //卡类型编号
    private String  cardTypeName;//卡类型名称
	public Integer getCardTypeId() {
		return cardTypeId;
	}
	public void setCardTypeId(Integer cardTypeId) {
		this.cardTypeId = cardTypeId;
	}
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
}

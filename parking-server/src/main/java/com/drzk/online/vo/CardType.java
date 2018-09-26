package com.drzk.online.vo;

/**
 * @author tf
 * 卡类型
 */
public class CardType {
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

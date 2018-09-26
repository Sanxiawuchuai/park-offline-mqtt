package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 超时收费
 */
public class TimeoutFeeVO extends SuperBody implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer cardType;	//账户类型
	private Integer deadTime;//停滞时间  分钟
	private Double timeoutFee;//超时费用 元
	private Integer timeout;//超时时间   分钟
	private Integer isfree;//是否包含免费时间
	public Integer getDeadTime() {
		return deadTime;
	}
	public void setDeadTime(Integer deadTime) {
		this.deadTime = deadTime;
	}
	public Double getTimeoutFee() {
		return timeoutFee;
	}
	public void setTimeoutFee(Double timeoutFee) {
		this.timeoutFee = timeoutFee;
	}
	public Integer getTimeout() {
		return timeout;
	}
	public void setTimeout(Integer timeout) {
		this.timeout = timeout;
	}
	public Integer getIsfree() {
		return isfree;
	}
	public void setIsfree(Integer isfree) {
		this.isfree = isfree;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
}

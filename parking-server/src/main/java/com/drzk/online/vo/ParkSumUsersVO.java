package com.drzk.online.vo;

import java.util.Date;

public class ParkSumUsersVO extends OnlineBody
{
	private String boxId;
	private Date loginDate;
	private String userName;
	private Date reliefDate;
	private String reUserName;
	private Integer handGate;
	private Integer computerGate;
	private Integer tempCardIn;
	private Integer tempCardOut;
	private Integer monthCardIn;
	private Integer monthCardOut;
	private Integer storedCardNum;
	private Double storedCardMoney;
	private Integer tempFree;
	private Integer freeCarNo;
	private double freeCharge;
	private Integer discountNum;
	private Double discountCharge;
	private Double posMoney;
	private Double totalCharge;
	private Double account;
	private Integer tNominal;
	public String getBoxId()
	{
		return boxId;
	}
	public void setBoxId(String boxId)
	{
		this.boxId = boxId;
	}
	/** 原登录时间  */
	public Date getLoginDate()
	{
		return loginDate;
	}
	/** 原登录时间  */
	public void setLoginDate(Date loginDate)
	{
		this.loginDate = loginDate;
	}
	/** 原操作员 */
	public String getUserName()
	{
		return userName;
	}
	/** 原操作员 */
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	/**  接班时间 */
	public Date getReliefDate()
	{
		return reliefDate;
	}
	/**  接班时间 */
	public void setReliefDate(Date reliefDate)
	{
		this.reliefDate = reliefDate;
	}
	/** 接班操作员 */
	public String getReUserName()
	{
		return reUserName;
	}
	/** 接班操作员 */
	public void setReUserName(String reUserName)
	{
		this.reUserName = reUserName;
	}
	/** 手动开闸  */
	public Integer getHandGate()
	{
		return handGate;
	}
	/** 手动开闸  */
	public void setHandGate(Integer handGate)
	{
		this.handGate = handGate;
	}
	/** 电脑开闸 */
	public Integer getComputerGate()
	{
		return computerGate;
	}
	/** 电脑开闸 */
	public void setComputerGate(Integer computerGate)
	{
		this.computerGate = computerGate;
	}
	/** 临时卡入场张数  */
	public Integer getTempCardIn()
	{
		return tempCardIn;
	}
	/** 临时卡入场张数  */
	public void setTempCardIn(Integer tempCardIn)
	{
		this.tempCardIn = tempCardIn;
	}
	/** 临时卡出场张数  */
	public Integer getTempCardOut()
	{
		return tempCardOut;
	}
	/** 临时卡出场张数  */
	public void setTempCardOut(Integer tempCardOut)
	{
		this.tempCardOut = tempCardOut;
	}
	/** 月租车出场数 */
	public Integer getMonthCardIn()
	{
		return monthCardIn;
	}
	/** 月租车入场数 */
	public void setMonthCardIn(Integer monthCardIn)
	{
		this.monthCardIn = monthCardIn;
	}
	/** 月租车出场数 */
	public Integer getMonthCardOut()
	{
		return monthCardOut;
	}
	/** 月租车出场数 */
	public void setMonthCardOut(Integer monthCardOut)
	{
		this.monthCardOut = monthCardOut;
	}
	/** 储值卡数 */
	public Integer getStoredCardNum()
	{
		return storedCardNum;
	}
	/** 储值卡数 */
	public void setStoredCardNum(Integer storedCardNum)
	{
		this.storedCardNum = storedCardNum;
	}
	/** 储值卡收费金额 */
	public Double getStoredCardMoney()
	{
		return storedCardMoney;
	}
	/** 储值卡收费金额 */
	public void setStoredCardMoney(Double storedCardMoney)
	{
		this.storedCardMoney = storedCardMoney;
	}
	/** 临免卡张数 */
	public Integer getTempFree()
	{
		return tempFree;
	}
	/** 临免卡张数 */
	public void setTempFree(Integer tempFree)
	{
		this.tempFree = tempFree;
	}
	/** 免费卡数 */
	public Integer getFreeCarNo()
	{
		return freeCarNo;
	}
	/** 免费卡数 */
	public void setFreeCarNo(Integer freeCarNo)
	{
		this.freeCarNo = freeCarNo;
	}
	public double getFreeCharge()
	{
		return freeCharge;
	}
	public void setFreeCharge(double freeCharge)
	{
		this.freeCharge = freeCharge;
	}
	/** 折扣张数 */
	public Integer getDiscountNum()
	{
		return discountNum;
	}
	/** 折扣张数 */
	public void setDiscountNum(Integer discountNum)
	{
		this.discountNum = discountNum;
	}
	public Double getDiscountCharge()
	{
		return discountCharge;
	}
	public void setDiscountCharge(Double discountCharge)
	{
		this.discountCharge = discountCharge;
	}
	/** POS消费金额（电商抵扣） */
	public Double getPosMoney()
	{
		return posMoney;
	}
	/** POS消费金额（电商抵扣） */
	public void setPosMoney(Double posMoney)
	{
		this.posMoney = posMoney;
	}
	/**  现金金额 */
	public Double getTotalCharge()
	{
		return totalCharge;
	}
	/**  现金金额 */
	public void setTotalCharge(Double totalCharge)
	{
		this.totalCharge = totalCharge;
	}
	/** 应收金额 */
	public Double getAccount()
	{
		return account;
	}
	/** 应收金额 */
	public void setAccount(Double account)
	{
		this.account = account;
	}
	/** 工本费 */
	public Integer gettNominal()
	{
		return tNominal;
	}
	/** 工本费 */
	public void settNominal(Integer tNominal)
	{
		this.tNominal = tNominal;
	}
	 
	
	
	
	
	
}

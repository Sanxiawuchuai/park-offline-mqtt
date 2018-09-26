package com.drzk.online.vo;

import java.util.Date;

/**
 * @author tf 
 * 出场纪录
 */
public class ParkCarOutVO extends SuperBody {
	
	private String entranceId; //控制器编号
	private String entranceName;//入场控制器名称
	private String entranceUserName;//入场操作员名称
	private String appearancesId;//出口编号
	private String appearancesName;//出口名称
	private String appearancesUserName;//出场操作员
	private String cardId;//卡ID（7Byte）
	private String carNo;//车牌号码
	private String nextCarNo;//从车牌
	private String correctCarNo;//矫正车牌
	private String contactName;//车主名称
	private String cardTypeName; //卡类名称
	private String carTypeId;//卡类型
	private String carNoType;//车牌类型
	private Date inTime;//入场时间
	private String inPic;//入场图片路径
	private Date outTime ;//出场时间
	private String outPic ;//出场图片路径	
	private Date centralTime ;//定点收费时间
	private String freeType;//免费类型
	private String inWay;//入场方式 0正常入场，1校正入场 ，2手工入场 3,扫码入场,4手持机入场
	private String inWayName;//入场方式 inWay：0正常入场，1校正入场 ，2手工入场 3,扫码入场,4手持机入场
	private String payType ;//支付类型 0，现金支付，1微信支付;2，支付宝支付;3;银联闪付，4，交通卡支付，5;自助缴费机;6，其它
	private Double payCharge ; //收费金额
	private Double balanceMoney ;//卡上金额
	private String discountNo ;//打折机号
	private String typeId ;//模式ID
	private Date discountTime ;//折扣时间
	private Double disAmount ;//折扣金额
	private Double accountCharge ;//应收金额
	private String isOut ;//是否出场（中央收费时为0，出场后更新为1）					
	private String unusualMemo ; //异常原因（1车闸故障2卡遗失等等
	private String outWay ;//出场方式 0;表示正常出场，1，手工放行，2，异常放行;4手持机放行
	private String outWayName ; //出场方式  0,表示正常出场，1，手工放行，2，异常放行,4手持机放行
	private String inControlIp ;//入场IP
	private String outCcontrolIp ;//出场IP
	private String dataOrigin;//线下或线上
	public String getEntranceId()
	{
		return entranceId;
	}
	public void setEntranceId(String entranceId)
	{
		this.entranceId = entranceId;
	}
	public String getEntranceName()
	{
		return entranceName;
	}
	public void setEntranceName(String entranceName)
	{
		this.entranceName = entranceName;
	}
	public String getEntranceUserName()
	{
		return entranceUserName;
	}
	public void setEntranceUserName(String entranceUserName)
	{
		this.entranceUserName = entranceUserName;
	}
	public String getAppearancesId()
	{
		return appearancesId;
	}
	public void setAppearancesId(String appearancesId)
	{
		this.appearancesId = appearancesId;
	}
	public String getAppearancesName()
	{
		return appearancesName;
	}
	public void setAppearancesName(String appearancesName)
	{
		this.appearancesName = appearancesName;
	}
	public String getAppearancesUserName()
	{
		return appearancesUserName;
	}
	public void setAppearancesUserName(String appearancesUserName)
	{
		this.appearancesUserName = appearancesUserName;
	}
	public String getCardId()
	{
		return cardId;
	}
	public void setCardId(String cardId)
	{
		this.cardId = cardId;
	}
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	public String getNextCarNo()
	{
		return nextCarNo;
	}
	public void setNextCarNo(String nextCarNo)
	{
		this.nextCarNo = nextCarNo;
	}
	public String getCorrectCarNo()
	{
		return correctCarNo;
	}
	public void setCorrectCarNo(String correctCarNo)
	{
		this.correctCarNo = correctCarNo;
	}
	public String getContactName()
	{
		return contactName;
	}
	public void setContactName(String contactName)
	{
		this.contactName = contactName;
	}
	public String getCardTypeName()
	{
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName)
	{
		this.cardTypeName = cardTypeName;
	}
	public String getCarTypeId()
	{
		return carTypeId;
	}
	public void setCarTypeId(String carTypeId)
	{
		this.carTypeId = carTypeId;
	}
	public String getCarNoType()
	{
		return carNoType;
	}
	public void setCarNoType(String carNoType)
	{
		this.carNoType = carNoType;
	}
	public Date getInTime()
	{
		return inTime;
	}
	public void setInTime(Date inTime)
	{
		this.inTime = inTime;
	}
	public String getInPic()
	{
		return inPic;
	}
	public void setInPic(String inPic)
	{
		this.inPic = inPic;
	}
	public Date getOutTime()
	{
		return outTime;
	}
	public void setOutTime(Date outTime)
	{
		this.outTime = outTime;
	}
	public String getOutPic()
	{
		return outPic;
	}
	public void setOutPic(String outPic)
	{
		this.outPic = outPic;
	}
	public Date getCentralTime()
	{
		return centralTime;
	}
	public void setCentralTime(Date centralTime)
	{
		this.centralTime = centralTime;
	}
	public String getFreeType()
	{
		return freeType;
	}
	public void setFreeType(String freeType)
	{
		this.freeType = freeType;
	}
	public String getInWay()
	{
		return inWay;
	}
	public void setInWay(String inWay)
	{
		this.inWay = inWay;
	}
	public String getInWayName()
	{
		return inWayName;
	}
	public void setInWayName(String inWayName)
	{
		this.inWayName = inWayName;
	}
	public String getPayType()
	{
		return payType;
	}
	public void setPayType(String payType)
	{
		this.payType = payType;
	}
	public Double getPayCharge()
	{
		return payCharge;
	}
	public void setPayCharge(Double payCharge)
	{
		this.payCharge = payCharge;
	}
	public Double getBalanceMoney()
	{
		return balanceMoney;
	}
	public void setBalanceMoney(Double balanceMoney)
	{
		this.balanceMoney = balanceMoney;
	}
	public String getDiscountNo()
	{
		return discountNo;
	}
	public void setDiscountNo(String discountNo)
	{
		this.discountNo = discountNo;
	}
	public String getTypeId()
	{
		return typeId;
	}
	public void setTypeId(String typeId)
	{
		this.typeId = typeId;
	}
	public Date getDiscountTime()
	{
		return discountTime;
	}
	public void setDiscountTime(Date discountTime)
	{
		this.discountTime = discountTime;
	}
	public Double getDisAmount()
	{
		return disAmount;
	}
	public void setDisAmount(Double disAmount)
	{
		this.disAmount = disAmount;
	}
	public Double getAccountCharge()
	{
		return accountCharge;
	}
	public void setAccountCharge(Double accountCharge)
	{
		this.accountCharge = accountCharge;
	}
	public String getIsOut()
	{
		return isOut;
	}
	public void setIsOut(String isOut)
	{
		this.isOut = isOut;
	}
	public String getUnusualMemo()
	{
		return unusualMemo;
	}
	public void setUnusualMemo(String unusualMemo)
	{
		this.unusualMemo = unusualMemo;
	}
	public String getOutWay()
	{
		return outWay;
	}
	public void setOutWay(String outWay)
	{
		this.outWay = outWay;
	}
	public String getOutWayName()
	{
		return outWayName;
	}
	public void setOutWayName(String outWayName)
	{
		this.outWayName = outWayName;
	}
	public String getInControlIp()
	{
		return inControlIp;
	}
	public void setInControlIp(String inControlIp)
	{
		this.inControlIp = inControlIp;
	}
	public String getOutCcontrolIp()
	{
		return outCcontrolIp;
	}
	public void setOutCcontrolIp(String outCcontrolIp)
	{
		this.outCcontrolIp = outCcontrolIp;
	}
	public String getDataOrigin()
	{
		return dataOrigin;
	}
	public void setDataOrigin(String dataOrigin)
	{
		this.dataOrigin = dataOrigin;
	}
	
	
	
}

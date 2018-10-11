package com.drzk.online.vo;


/**
 * @author tf
 * 入场记录
 */
public class ParkCarInVO extends OnlineBody
{
	private String entranceId; //控制器编号
	private String entranceName;//入场控制器名称
	private String entranceUserName;//入场操作员名称
	private String cardId;//卡ID（7Byte）
	private String carNo;//车牌号码
	private String nextCarNo;//从车牌
	private String correctCarNo;//矫正车牌
	private String contactName;//车主名称
	private String cardTypeName; //卡类名称
	private Integer carTypeId;//卡类型
	private String carNoType;//车牌类型
	private String inTime;//入场时间
	private String inPic;//入场图片路径
	private String inWay;//入场方式 0正常入场，1校正入场 ，2手工入场 3,扫码入场,4手持机入场
	private String inWayName;//入场方式 inWay：0正常入场，1校正入场 ，2手工入场 3,扫码入场,4手持机入场
	private String small;//小车场内
	private String controlIp;//入场IP
	private String dataOrigin;//线下或线上
	
	
	/** 控制器编号 */
	public String getEntranceId()
	{
		return entranceId;
	}
	/** 控制器编号 */
	public void setEntranceId(String entranceId)
	{
		this.entranceId = entranceId;
	}
	/** 入场控制器名称 */
	public String getEntranceName()
	{
		return entranceName;
	}
	/** 入场控制器名称 */
	public void setEntranceName(String entranceName)
	{
		this.entranceName = entranceName;
	}
	/** 入场操作员名称 */
	public String getEntranceUserName()
	{
		return entranceUserName;
	}
	/** 入场操作员名称 */
	public void setEntranceUserName(String entranceUserName)
	{
		this.entranceUserName = entranceUserName;
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
	/** 从车牌 */
	public String getNextCarNo()
	{
		return nextCarNo;
	}
	/** 从车牌 */
	public void setNextCarNo(String nextCarNo)
	{
		this.nextCarNo = nextCarNo;
	}
	/** 矫正车牌 */
	public String getCorrectCarNo()
	{
		return correctCarNo;
	}
	/** 矫正车牌 */
	public void setCorrectCarNo(String correctCarNo)
	{
		this.correctCarNo = correctCarNo;
	}
	/** 车主名称 */
	public String getContactName()
	{
		return contactName;
	}
	/** 车主名称 */
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

	public Integer getCarTypeId() {
		return carTypeId;
	}

	public void setCarTypeId(Integer carTypeId) {
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
	public String getInTime()
	{
		return inTime;
	}
	public void setInTime(String inTime)
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
	/** 入场方式 0正常入场，1校正入场 ，2手工入场 3,扫码入场,4手持机入场 */
	public String getInWay()
	{
		return inWay;
	}
	/** 入场方式 0正常入场，1校正入场 ，2手工入场 3,扫码入场,4手持机入场 */
	public void setInWay(String inWay)
	{
		this.inWay = inWay;
	}
	/** 入场方式 0正常入场，1校正入场 ，2手工入场 3,扫码入场,4手持机入场 */
	public String getInWayName()
	{
		return inWayName;
	}
	/** 入场方式 0正常入场，1校正入场 ，2手工入场 3,扫码入场,4手持机入场 */
	public void setInWayName(String inWayName)
	{
		this.inWayName = inWayName;
	}
	public String getSmall()
	{
		return small;
	}
	public void setSmall(String small)
	{
		this.small = small;
	}
	public String getControlIp()
	{
		return controlIp;
	}
	public void setControlIp(String controlIp)
	{
		this.controlIp = controlIp;
	}
	/** 线下或线上 */
	public String getDataOrigin()
	{
		return dataOrigin;
	}
	/** 线下或线上 */
	public void setDataOrigin(String dataOrigin)
	{
		this.dataOrigin = dataOrigin;
	}
	
	
}

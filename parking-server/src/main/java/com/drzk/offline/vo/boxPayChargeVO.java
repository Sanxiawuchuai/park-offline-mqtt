package com.drzk.offline.vo;


import com.drzk.fact.CentreRealTimeBase;
import com.drzk.fact.OutRealTimeBase;

/** 收费改变实体 */
public class boxPayChargeVO {
	private String uId;
	private Integer type;
	private Integer changeType;
	private String controlIP;
	private String equipmentID;
	private String boxIP;
	private String equipmentMac;
	private OutRealTimeBase outRealTimeBase;
	private CentreRealTimeBase centreRealTimeBase;
	
	public String getuId()
	{
		return uId;
	}
	public void setuId(String uId)
	{
		this.uId = uId;
	}
	/** 0为出口收费，1为中央收费 当为1时，控制器IP可以为空 */
	public Integer getType()
	{
		return type;
	}
	/** 0为出口收费，1为中央收费 当为1时，控制器IP可以为空 */
	public void setType(Integer type)
	{
		this.type = type;
	}
	/** 1.车类型改变 --2优惠 3--免费 */
	public Integer getChangeType()
	{
		return changeType;
	}
	/** 1.车类型改变 --2优惠 3--免费 */
	public void setChangeType(Integer changeType)
	{
		this.changeType = changeType;
	}
	/** 控制器IP */
	public String getControlIP()
	{
		return controlIP;
	}
	/** 控制器IP */
	public void setControlIP(String controlIP)
	{
		this.controlIP = controlIP;
	}
	public String getEquipmentID()
	{
		return equipmentID;
	}
	public void setEquipmentID(String equipmentID)
	{
		this.equipmentID = equipmentID;
	}

	public OutRealTimeBase getOutRealTimeBase() {
		return outRealTimeBase;
	}

	public void setOutRealTimeBase(OutRealTimeBase outRealTimeBase) {
		this.outRealTimeBase = outRealTimeBase;
	}

	public CentreRealTimeBase getCentreRealTimeBase() {
		return centreRealTimeBase;
	}

	public void setCentreRealTimeBase(CentreRealTimeBase centreRealTimeBase) {
		this.centreRealTimeBase = centreRealTimeBase;
	}

	public String getBoxIP() {
		return boxIP;
	}
	public void setBoxIP(String boxIP) {
		this.boxIP = boxIP;
	}
	public String getEquipmentMac() {
		return equipmentMac;
	}
	public void setEquipmentMac(String equipmentMac) {
		this.equipmentMac = equipmentMac;
	}
	
	
	
}

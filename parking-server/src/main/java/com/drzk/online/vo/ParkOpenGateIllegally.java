package com.drzk.online.vo;

import java.util.Date;
import java.util.List;

public class ParkOpenGateIllegally extends OnlineBody
{
	private String boxId;
	private String boxName;
	private String controllerId;
	private String controllerName;
	private Integer openType;
	private Date openTime;
	private Integer openModel;
	private Integer auditStatus;
	private String auditor;
	private Date auditTime;
	private String controllerIP;
	private String carNo;
	private String carOutObjectId;
	private Integer isOut;
	private List<PicPathsBody> pics;
	private String remark;
	
	
	public String getBoxId()
	{
		return boxId;
	}


	public void setBoxId(String boxId)
	{
		this.boxId = boxId;
	}


	public String getBoxName()
	{
		return boxName;
	}


	public void setBoxName(String boxName)
	{
		this.boxName = boxName;
	}


	/** 控制器编号 */
	public String getControllerId()
	{
		return controllerId;
	}

	/** 控制器编号 */
	public void setControllerId(String controllerId)
	{
		this.controllerId = controllerId;
	}


	public String getControllerName()
	{
		return controllerName;
	}


	public void setControllerName(String controllerName)
	{
		this.controllerName = controllerName;
	}


	/** 开闸类型： 1、非法开闸 2、异常开闸 3、冲卡 */
	public Integer getOpenType()
	{
		return openType;
	}

	/** 开闸类型： 1、非法开闸 2、异常开闸 3、冲卡 */
	public void setOpenType(Integer openType)
	{
		this.openType = openType;
	}


	public Date getOpenTime()
	{
		return openTime;
	}


	public void setOpenTime(Date openTime)
	{
		this.openTime = openTime;
	}

	/** 开闸方式 1云端2、遥控器 3、冲卡 */
	public Integer getOpenModel()
	{
		return openModel;
	}

	/** 开闸方式 1云端2、遥控器 3、冲卡 */
	public void setOpenModel(Integer openModel)
	{
		this.openModel = openModel;
	}


	/** 审核状态 0：待审核 1：已经审核 */
	public Integer getAuditStatus()
	{
		return auditStatus;
	}

	/** 审核状态 0：待审核 1：已经审核 */
	public void setAuditStatus(Integer auditStatus)
	{
		this.auditStatus = auditStatus;
	}

	/** 审核人 */
	public String getAuditor()
	{
		return auditor;
	}

	/** 审核人 */
	public void setAuditor(String auditor)
	{
		this.auditor = auditor;
	}

	/** 审核时间 */
	public Date getAuditTime()
	{
		return auditTime;
	}

	/** 审核时间 */
	public void setAuditTime(Date auditTime)
	{
		this.auditTime = auditTime;
	}


	public String getControllerIP()
	{
		return controllerIP;
	}


	public void setControllerIP(String controllerIP)
	{
		this.controllerIP = controllerIP;
	}


	public String getCarNo()
	{
		return carNo;
	}


	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}


	/**  出场纪录表ID */
	public String getCarOutObjectId()
	{
		return carOutObjectId;
	}

	/**  出场纪录表ID */
	public void setCarOutObjectId(String carOutObjectId)
	{
		this.carOutObjectId = carOutObjectId;
	}

	/** 是否出场，出场状态 0 出场 1 未出场 */
	public Integer getIsOut()
	{
		return isOut;
	}

	/** 是否出场，出场状态 0 出场 1 未出场 */
	public void setIsOut(Integer isOut)
	{
		this.isOut = isOut;
	}


	public List<PicPathsBody> getPics()
	{
		return pics;
	}


	public void setPics(List<PicPathsBody> pics)
	{
		this.pics = pics;
	}


	public String getRemark()
	{
		return remark;
	}


	public void setRemark(String remark)
	{
		this.remark = remark;
	}


	
	
	
	
	
}

package com.drzk.online.vo;

import java.util.Date;

public class OnlineBody {

	private String objectId;
	private String dataOrigin;
	private Integer syncStatus;
	
	private Integer createId;
	private String creator="system";
	private Date createTime=new Date();
	
	private Date lastUpdateTime=new Date();
	private Integer lastUpdateId;
	private String lastUpdateName="system";
	
	private Integer parkingId;
	private String parkingName;
	private String parkingNo;
	
	private Integer deleteCode;
	
	/** 是否已经删除：0正常 1 已删除 */
	public Integer getDeleteCode()
	{
		return deleteCode;
	}
	/** 是否已经删除：0正常 1 已删除 */
	public void setDeleteCode(Integer deleteCode)
	{
		this.deleteCode = deleteCode;
	}
	/** 新建人员编号 */
	public Integer getCreateId()
	{
		return createId;
	}
	/** 新建人员编号 */
	public void setCreateId(Integer createId)
	{
		this.createId = createId;
	}
	/** 新建人员 */
	public String getCreator()
	{
		return creator;
	}
	/** 新建人员 */
	public void setCreator(String creator)
	{
		this.creator = creator;
	}
	/** 新建时间 */
	public Date getCreateTime()
	{
		return createTime;
	}
	/** 新建时间 */
	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}
	/** 更新时间 */
	public Date getLastUpdateTime()
	{
		return lastUpdateTime;
	}
	/** 更新时间 */
	public void setLastUpdateTime(Date lastUpdateTime)
	{
		this.lastUpdateTime = lastUpdateTime;
	}
	/** 更新人员编号 */
	public Integer getLastUpdateId()
	{
		return lastUpdateId;
	}
	/** 更新人员编号 */
	public void setLastUpdateId(Integer lastUpdateId)
	{
		this.lastUpdateId = lastUpdateId;
	}
	/** 自增人员  */
	public String getLastUpdateName()
	{
		return lastUpdateName;
	}
	/** 自增人员  */
	public void setLastUpdateName(String lastUpdateName)
	{
		this.lastUpdateName = lastUpdateName;
	}
	/** 车场ID */
	public Integer getParkingId()
	{
		return parkingId;
	}
	/** 车场ID */
	public void setParkingId(Integer parkingId)
	{
		this.parkingId = parkingId;
	}
	/** 车场名称 */
	public String getParkingName()
	{
		return parkingName;
	}
	/** 车场名称 */
	public void setParkingName(String parkingName)
	{
		this.parkingName = parkingName;
	}
	/** 车场编号  */
	public String getParkingNo()
	{
		return parkingNo;
	}
	/** 车场编号  */
	public void setParkingNo(String parkingNo)
	{
		this.parkingNo = parkingNo;
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

	/** 同步状态1待同步 2 已同步 3同步失败 */
	public Integer getSyncStatus()
	{
		return syncStatus;
	}
	/** 同步状态1待同步 2 已同步 3同步失败 */
	public void setSyncStatus(Integer syncStatus)
	{
		this.syncStatus = syncStatus;
	}

	public String getObjectId()
	{
		return objectId;
	}

	public void setObjectId(String objectId)
	{
		this.objectId = objectId;
	}

	
	
}

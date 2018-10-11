package com.drzk.online.onlineVo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2018/7/23 cx
 */
public class DeviceBoxVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 4102609320245736568L;
	public static Map<String,String> map=new HashMap<>(  );
    static {
        map.put( "boxIp","boxIp" );
        map.put( "boxName","boxName" );
        map.put( "boxType","boxType" );
        map.put( "objectId","luid" );
        map.put( "realStatus","online" );

        map.put( "createTime","createDate" );
        map.put( "creator","createUserName" );
        map.put( "lastUpdateTime","modifyDate" );
        map.put( "lastUpdateName","modifyUserName" );
    }

    private String  id;
    //项目编号
    private String parkingNo;
    //项目Id
    private Integer parkingId;
    //项目名称
    private String parkingName;
    private String objectId; //岗亭编号  线下id

    private Integer boxType; //	岗亭类型（0标准收费点，1中央收费点，2综合收费点）
    private String boxName; //岗亭名称
    private String parkingLotName; //客户名称

    private String description; //描述
    private String mac; //岗亭mac地址
    private String boxIp; //岗亭电脑IP

    private String realStatus; //状态

    private Integer exitCount; //出口数量
    private Integer entranceCount; //入口数量

    private Integer status; //启用状态 0 禁用  1 启用

    private String dataOrigin; //1线下 2云端
    private Integer syncStatus;  //1待同步 2 已同步 3同步失败
    private Integer isLoad=1;
    private Integer deleteCode;

    // 创建者id
    private Integer createId;
    //创建者
    private String creator;
    // 创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    // 最后更新者id
    private Integer lastUpdateId;
    // 最后更新时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastUpdateTime;
    // 最后更新者名
    private String lastUpdateName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParkingNo() {
        return parkingNo;
    }

    public void setParkingNo(String parkingNo) {
        this.parkingNo = parkingNo;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getBoxType() {
        return boxType;
    }

    public void setBoxType(Integer boxType) {
        this.boxType = boxType;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getBoxIp() {
        return boxIp;
    }

    public void setBoxIp(String boxIp) {
        this.boxIp = boxIp;
    }

    public String getRealStatus() {
        return realStatus;
    }

    public void setRealStatus(String realStatus) {
        this.realStatus = realStatus;
    }

    public Integer getExitCount() {
        return exitCount;
    }

    public void setExitCount(Integer exitCount) {
        this.exitCount = exitCount;
    }

    public Integer getEntranceCount() {
        return entranceCount;
    }

    public void setEntranceCount(Integer entranceCount) {
        this.entranceCount = entranceCount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getDataOrigin() {
        return dataOrigin;
    }

    public void setDataOrigin(String dataOrigin) {
        this.dataOrigin = dataOrigin;
    }

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public Integer getDeleteCode() {
        return deleteCode;
    }

    public void setDeleteCode(Integer deleteCode) {
        this.deleteCode = deleteCode;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(Integer lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateName() {
        return lastUpdateName;
    }

    public void setLastUpdateName(String lastUpdateName) {
        this.lastUpdateName = lastUpdateName;
    }

    public Integer getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
    }
}

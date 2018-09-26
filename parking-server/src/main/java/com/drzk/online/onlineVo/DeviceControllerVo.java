package com.drzk.online.onlineVo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2018/7/23 cx
 */
public class DeviceControllerVo implements Serializable {

    public static Map<String,String> map=new HashMap<>(  );
    static {
        map.put( "objectId","cuid" );
        map.put( "controllerIp","channelIp" );
        map.put( "contName","channelName" );
        map.put( "type","inOut" );
        map.put( "channelType","gateType" );
        map.put( "parkingId","parkNo" );

        map.put( "createTime","createDate" );
        map.put( "creator","createUserName" );
        map.put( "lastUpdateTime","modifyDate" );
        map.put( "lastUpdateName","modifyUserName" );
    }

    //项目编号
    private String parkingNo;
    //项目Id
    private Integer parkingId;
    //项目名称
    private String parkingName;

    private String objectId; //岗亭编号  线下id

    private String contName; //控制器名称
    private Integer type; //出入类型（0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道）
    private String openMacNum; //开闸机号 通道号
    private Integer channelType; //通道类型（0综合信道 1固定车信道，2临时卡通道）

    private String boxId; //岗亭编号
    private String boxName; //岗亭名称

    private String description; //描述
    private String mac; //岗亭mac地址
    private String controllerIp; //控制器IP

    private String realStatus; //状态

    private Integer tempCar;//	临时车通行：0：通行 1： 不通行
    private Integer monthCar;//	月租车通行：0：通行 1： 不通行
    private Integer freeCar; //	免费车通行：0：通行 1： 不通行
    private Integer rechargeCar;//	储值卡车通行：0：通行 1： 不通行

    private Integer tempCarModel;//	临时车通行：0：自动 1： 受控 2： 在线
    private Integer monthCarModel;//	月租车通行：0：自动 1： 受控 2： 在线
    private Integer freeCarModel; //	免费车通行：0：自动 1： 受控 2： 在线
    private Integer rechargeCarModel;//	储值卡车通行：0：自动 1： 受控 2： 在线

    private Integer status; //启用状态 0 禁用  1 启用
    private Integer cameraCount; //相机数量

    private String workPattern; //工作模式

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

    public String getContName() {
        return contName;
    }

    public void setContName(String contName) {
        this.contName = contName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getOpenMacNum() {
        return openMacNum;
    }

    public void setOpenMacNum(String openMacNum) {
        this.openMacNum = openMacNum;
    }

    public Integer getChannelType() {
        return channelType;
    }

    public void setChannelType(Integer channelType) {
        this.channelType = channelType;
    }

    public String getBoxId() {
        return boxId;
    }

    public void setBoxId(String boxId) {
        this.boxId = boxId;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
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

    public String getControllerIp() {
        return controllerIp;
    }

    public void setControllerIp(String controllerIp) {
        this.controllerIp = controllerIp;
    }

    public String getRealStatus() {
        return realStatus;
    }

    public void setRealStatus(String realStatus) {
        this.realStatus = realStatus;
    }

    public Integer getTempCar() {
        return tempCar;
    }

    public void setTempCar(Integer tempCar) {
        this.tempCar = tempCar;
    }

    public Integer getMonthCar() {
        return monthCar;
    }

    public void setMonthCar(Integer monthCar) {
        this.monthCar = monthCar;
    }

    public Integer getFreeCar() {
        return freeCar;
    }

    public void setFreeCar(Integer freeCar) {
        this.freeCar = freeCar;
    }

    public Integer getRechargeCar() {
        return rechargeCar;
    }

    public void setRechargeCar(Integer rechargeCar) {
        this.rechargeCar = rechargeCar;
    }

    public Integer getTempCarModel() {
        return tempCarModel;
    }

    public void setTempCarModel(Integer tempCarModel) {
        this.tempCarModel = tempCarModel;
    }

    public Integer getMonthCarModel() {
        return monthCarModel;
    }

    public void setMonthCarModel(Integer monthCarModel) {
        this.monthCarModel = monthCarModel;
    }

    public Integer getFreeCarModel() {
        return freeCarModel;
    }

    public void setFreeCarModel(Integer freeCarModel) {
        this.freeCarModel = freeCarModel;
    }

    public Integer getRechargeCarModel() {
        return rechargeCarModel;
    }

    public void setRechargeCarModel(Integer rechargeCarModel) {
        this.rechargeCarModel = rechargeCarModel;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCameraCount() {
        return cameraCount;
    }

    public void setCameraCount(Integer cameraCount) {
        this.cameraCount = cameraCount;
    }

    public String getWorkPattern() {
        return workPattern;
    }

    public void setWorkPattern(String workPattern) {
        this.workPattern = workPattern;
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

package com.drzk.online.onlineVo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2018/7/23 cx
 */
public class DeviceCameraVo implements Serializable {

    public static Map<String,String> map=new HashMap<>(  );
    static {
        map.put( "objectId","cuid" );
        map.put( "cameIp","camIp" );
        map.put( "cameName","camName" );
        map.put( "camePort","camPort" );

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

    private String cameName; //摄像头名称
    private String camePort; //设备名端口号卡类ID
    private String userName; //摄像机用户名
    private String passWord; //摄像机密码

    private String controllerName; //控制器名称
    private String controllerId; //控制器id
    private Integer controllerChanlId; //通道类型（0综合信道 1固定车信道，2临时卡通道）

    private String boxId; //岗亭编号
    private String boxName; //岗亭名称

    private String description; //描述
    private String mac; //岗亭mac地址
    private String cameIp; //摄像头IP

    private String realStatus; //状态

    private Integer status; //启用状态 0 禁用  1 启用

    private String liveUrl; //视频播放地址

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

    public String getCameName() {
        return cameName;
    }

    public void setCameName(String cameName) {
        this.cameName = cameName;
    }

    public String getCamePort() {
        return camePort;
    }

    public void setCamePort(String camePort) {
        this.camePort = camePort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getControllerName() {
        return controllerName;
    }

    public void setControllerName(String controllerName) {
        this.controllerName = controllerName;
    }

    public String getControllerId() {
        return controllerId;
    }

    public void setControllerId(String controllerId) {
        this.controllerId = controllerId;
    }

    public Integer getControllerChanlId() {
        return controllerChanlId;
    }

    public void setControllerChanlId(Integer controllerChanlId) {
        this.controllerChanlId = controllerChanlId;
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

    public String getCameIp() {
        return cameIp;
    }

    public void setCameIp(String cameIp) {
        this.cameIp = cameIp;
    }

    public String getRealStatus() {
        return realStatus;
    }

    public void setRealStatus(String realStatus) {
        this.realStatus = realStatus;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
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

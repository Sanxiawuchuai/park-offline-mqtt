package com.drzk.online.onlineVo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 车场配置
 * 2018/7/12 cx
 */

public class ParkConfigVo implements Serializable {

    //线上vsftp ip
    public static final String VSFTP_HOST="VSFTP_HOST";
    //线上vsftp 端口
    public static final String VSFTP_PORT="VSFTP_PORT";
    //线上vsftp 用户
    public static final String VSFTP_USER="VSFTP_USER";
    //线上vsftp 秘密
    public static final String VSFTP_PASSPW="VSFTP_PASSPW";
    //线上vsftp  目录
    public static final String VSFTP_ONLINE_HOME="VSFTP_ONLINE_HOME";
    //线下vsftp 目录
    public static final String VSFTP_OFFLINE_HOME="VSFTP_OFFLINE_HOME";
    public static final String PARKING_NO="PARK_NUM";


	private static final long serialVersionUID = -7674270835827732393L;
	public static Map<String,String> map=new HashMap<>(  );
    static {
        //map.put( "objectId","FILE_SIZE" );//上传文件大小
        //map.put( "companyName","FILE_PATH" );//上传文件的路径

        map.put( "parkingName","PROJECT_NAME" );//车场名称
        //map.put( "parkingNo","PARK_NUM" );//车场编号
        map.put( "city","OWNED_CTIY" ); //城市

        map.put( "totalSpaces","TOTAL_CARS" ); //总车位
        map.put( "fixedSpaces","FIXED_CARS" ); //固定车位
        map.put( "tempSpaces","TEMP_CARS" );//临时车位
        map.put( "spacesType","CAR_TYPE" );//车位类型

        map.put( "allowExpiresDay","FIXED_CAR_EXPIR" );//允许固定卡过期天数
        map.put( "monthCardChargeType","CHARGE_TYPE" );//月临卡收费类型
        map.put( "normalCharge","CHARGE_STAND_TYPE" );//收费标准类型
        map.put( "temporaryFreeType","FREE_TYPE_TEMPCAR" );//临时车免费类型
        map.put( "discountModel","PREFER_MODEL" );//优惠模式

        map.put( "carNoMatchType","LICENCE_MATCH_TYPE" );//车牌匹配类型
        map.put( "defaultCarPrefix","LICENCE_PRE" );//默认车牌前缀
        map.put( "freeCarPrefix","HEAD_TAIL_LICENCE" );//自由通行车牌首尾字符

       // map.put( "totalSpaces","CARAGE_FUNC" );//车场功能

        map.put( "udpUpPort","UP_PORT" );//UDP上行端口
        map.put( "udpDownPort","DOWN_PORT" );//UDP下行端口
        map.put( "tcpPort","TCP_PORT" );//TCP端口
        map.put( "compensationControl","SLU_CONTROL" );//补闸控制
        map.put( "barrierInterface","GATE_INTEF" );//道闸接口
        map.put( "mainboardType","BOARD_TYPE" );//主板类型

        map.put( "mqttIp","MQTT_UPLINE" );//线下MQTT服务IP
        map.put( "cloudmqttIp","MQTT_ONLINE" );//线上MQTT服务IP
        map.put( "isUploadCloud","UPLOAD_DATA_CLOUD" );//是否上传数据云端
        map.put( "isUploadImage","UPLOAD_IMG" );//是否上传图片

        map.put( "dyQrKeys","ENCYP_KEY" );//动态二维码加密密钥
        map.put( "dyQrCodeUrl","URL_PATH" );//动态二维码URL地址
        map.put( "autoPayFirstModel","PAY_PRO_MODEL" );//无感支付优先模式
       // map.put( "isAutoPay,isAutoPayByWechart,autoPayAfterExit","PAY_PRO_PARMS" );//无感支付参数

        map.put( "userName","CAM_USER" );//摄像机用户名
        map.put( "password","CAM_PWD" );//摄像机密码
        map.put( "cameraType","CAM_TYPE" );//摄像机设备类型

        map.put( "fullSpaceEnter","ISALLOW" );//满位允许进场
        map.put( "broadcastLicensePlate","BROAD_LICENCE" );//播报车牌
        map.put( "prefabricatedLicensePlate","PREF_LICENCE" );//预制车牌
        map.put( "viewCurrentUserMoney","SHOW_MONEY" );//显示当班收费金额
        map.put( "fastExit","QUICK_APPERA" );//快速出场
        map.put( "carLog","TARKING_LOG" );//车场跟踪日志
        map.put( "fixedSpcMEnterMExit","MORE_INT_MORE_OUT" );//固定车多进多出
        map.put( "delayType","DELAY_TYPE" );//延期类型

        //map.put( "userName","START_VALIDITY" );//车场有效期始
       // map.put( "password","END_VALIDITY" );//车场有效期止
       // map.put( "cameraType","EFFECTIVE_DAYS" );//车场有效天数

        //map.put( "userName","FTP_IP" );//FTP的IP地址
        //map.put( "password","FTP_USER" );//FTP的用户名
        //map.put( "cameraType","FTP_PWD" );//FTP的密码
        //map.put( "cameraType","DELAY_TYPE" );//延期类型
    }

    // 主键id
    private String id;
    //项目Id
    private Integer parkingId;
    //项目编号
    private String parkingNo;
    //项目名称
    private String parkingName;

    //线下Id
    private String objectId;
    //1待同步 2 已同步 3同步失败
    private Integer syncStatus;
    private Integer isLoad=1;
    // 数据来源 线下，线上
    private String dataOrigin;

    //基础信息配置
    //所在城市
    private String city;
    //gis信息
    private String gisInfo;

    //车位配置
    //总车位
    private Integer totalSpaces;
    //固定车位
    private Integer fixedSpaces;
    //临时车位
    private Integer tempSpaces;
    //车位类型  1混合车位 2临时车位
    private Integer spacesType;

    //相机配置
    //用户名
    private String userName;
    //密码
    private String password;
    //相机类型
    private Integer cameraType;

    //通信配置
    //线下MQTT服务器IP
    private String mqttIp;
    //云端MQTT
    private String cloudmqttIp;
    //是否上传数据至云端
    private Integer isUploadCloud;
    //是否上传图片  0否 1是
    private Integer isUploadImage;

    //功能配置
    // 允许固定车过期天数
    private Integer allowExpiresDay;
    //月临卡收费类型  "临时类A" "临时类"
    private Integer monthCardChargeType;
    //收费标准类型
    private String normalCharge;
    // 临时车免费类型 1手工免费 2自动设置
    private Integer temporaryFreeType;
    //优惠模式  0 简易打折 1 无
    private Integer discountModel;
    //车牌匹配类型 1六位 2五位
    private Integer carNoMatchType;
    //默认车牌前缀 "粤"
    private String defaultCarPrefix;
    //自由通行车牌前缀 "WJ"
    private String freeCarPrefix;


    //播报车牌 0 |1
    private Integer broadcastLicensePlate;
    //预制车牌 0 |1
    private Integer prefabricatedLicensePlate;
    //车位满位入场 0 | 1
    private Integer fullSpaceEnter;
    //显示当班收费金额 0 | 1
    private Integer viewCurrentUserMoney;
    //快速出场 0 | 1
    private Integer fastExit;
    //车场日志跟踪 0 | 1
    private Integer carLog;
    //固定车多进多出 0 | 1
    private Integer fixedSpcMEnterMExit;

    //硬件配置
    //UDP上行端口
    private Integer udpUpPort;
    //UDP下行端口
    private Integer udpDownPort;
    //TCP端口
    private Integer tcpPort;
    //补闸控制  1禁止 2补开闸
    private Integer compensationControl;
    //道闸接口 1电平 2 485接口
    private Integer barrierInterface;
    //主板类型 1  S板 2 Linux板
    private Integer mainboardType;



    //支付配置
    //动态二维码加密密钥 "ASJJ"
    private String dyQrKeys;
    //动态二维码url地址
    private String dyQrCodeUrl;
    //无感支付优先模式 0 无|  1微信
    private Integer autoPayFirstModel;
    //支付无感支付 0 |1
    private Integer isAutoPay;
    //微信支付无感支付  0 |1
    private Integer isAutoPayByWechart;
    //无感支付先付费后出场 0 |1
    private Integer autoPayAfterExit;
    //1按天 | 2连续性 3 自然月
    private Integer delayType;



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

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingNo() {
        return parkingNo;
    }

    public void setParkingNo(String parkingNo) {
        this.parkingNo = parkingNo;
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

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getDataOrigin() {
        return dataOrigin;
    }

    public void setDataOrigin(String dataOrigin) {
        this.dataOrigin = dataOrigin;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGisInfo() {
        return gisInfo;
    }

    public void setGisInfo(String gisInfo) {
        this.gisInfo = gisInfo;
    }

    public Integer getTotalSpaces() {
        return totalSpaces;
    }

    public void setTotalSpaces(Integer totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    public Integer getFixedSpaces() {
        return fixedSpaces;
    }

    public void setFixedSpaces(Integer fixedSpaces) {
        this.fixedSpaces = fixedSpaces;
    }

    public Integer getTempSpaces() {
        return tempSpaces;
    }

    public void setTempSpaces(Integer tempSpaces) {
        this.tempSpaces = tempSpaces;
    }

    public Integer getSpacesType() {
        return spacesType;
    }

    public void setSpacesType(Integer spacesType) {
        this.spacesType = spacesType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCameraType() {
        return cameraType;
    }

    public void setCameraType(Integer cameraType) {
        this.cameraType = cameraType;
    }

    public String getMqttIp() {
        return mqttIp;
    }

    public void setMqttIp(String mqttIp) {
        this.mqttIp = mqttIp;
    }

    public String getCloudmqttIp() {
        return cloudmqttIp;
    }

    public void setCloudmqttIp(String cloudmqttIp) {
        this.cloudmqttIp = cloudmqttIp;
    }

    public Integer getIsUploadCloud() {
        return isUploadCloud;
    }

    public void setIsUploadCloud(Integer isUploadCloud) {
        this.isUploadCloud = isUploadCloud;
    }

    public Integer getIsUploadImage() {
        return isUploadImage;
    }

    public void setIsUploadImage(Integer isUploadImage) {
        this.isUploadImage = isUploadImage;
    }

    public Integer getAllowExpiresDay() {
        return allowExpiresDay;
    }

    public void setAllowExpiresDay(Integer allowExpiresDay) {
        this.allowExpiresDay = allowExpiresDay;
    }

    public Integer getMonthCardChargeType() {
        return monthCardChargeType;
    }

    public void setMonthCardChargeType(Integer monthCardChargeType) {
        this.monthCardChargeType = monthCardChargeType;
    }

    public String getNormalCharge() {
        return normalCharge;
    }

    public void setNormalCharge(String normalCharge) {
        this.normalCharge = normalCharge;
    }

    public Integer getTemporaryFreeType() {
        return temporaryFreeType;
    }

    public void setTemporaryFreeType(Integer temporaryFreeType) {
        this.temporaryFreeType = temporaryFreeType;
    }

    public Integer getDiscountModel() {
        return discountModel;
    }

    public void setDiscountModel(Integer discountModel) {
        this.discountModel = discountModel;
    }

    public Integer getCarNoMatchType() {
        return carNoMatchType;
    }

    public void setCarNoMatchType(Integer carNoMatchType) {
        this.carNoMatchType = carNoMatchType;
    }

    public String getDefaultCarPrefix() {
        return defaultCarPrefix;
    }

    public void setDefaultCarPrefix(String defaultCarPrefix) {
        this.defaultCarPrefix = defaultCarPrefix;
    }

    public String getFreeCarPrefix() {
        return freeCarPrefix;
    }

    public void setFreeCarPrefix(String freeCarPrefix) {
        this.freeCarPrefix = freeCarPrefix;
    }

    public Integer getBroadcastLicensePlate() {
        return broadcastLicensePlate;
    }

    public void setBroadcastLicensePlate(Integer broadcastLicensePlate) {
        this.broadcastLicensePlate = broadcastLicensePlate;
    }

    public Integer getPrefabricatedLicensePlate() {
        return prefabricatedLicensePlate;
    }

    public void setPrefabricatedLicensePlate(Integer prefabricatedLicensePlate) {
        this.prefabricatedLicensePlate = prefabricatedLicensePlate;
    }

    public Integer getFullSpaceEnter() {
        return fullSpaceEnter;
    }

    public void setFullSpaceEnter(Integer fullSpaceEnter) {
        this.fullSpaceEnter = fullSpaceEnter;
    }

    public Integer getViewCurrentUserMoney() {
        return viewCurrentUserMoney;
    }

    public void setViewCurrentUserMoney(Integer viewCurrentUserMoney) {
        this.viewCurrentUserMoney = viewCurrentUserMoney;
    }

    public Integer getFastExit() {
        return fastExit;
    }

    public void setFastExit(Integer fastExit) {
        this.fastExit = fastExit;
    }

    public Integer getCarLog() {
        return carLog;
    }

    public void setCarLog(Integer carLog) {
        this.carLog = carLog;
    }

    public Integer getFixedSpcMEnterMExit() {
        return fixedSpcMEnterMExit;
    }

    public void setFixedSpcMEnterMExit(Integer fixedSpcMEnterMExit) {
        this.fixedSpcMEnterMExit = fixedSpcMEnterMExit;
    }

    public Integer getUdpUpPort() {
        return udpUpPort;
    }

    public void setUdpUpPort(Integer udpUpPort) {
        this.udpUpPort = udpUpPort;
    }

    public Integer getUdpDownPort() {
        return udpDownPort;
    }

    public void setUdpDownPort(Integer udpDownPort) {
        this.udpDownPort = udpDownPort;
    }

    public Integer getTcpPort() {
        return tcpPort;
    }

    public void setTcpPort(Integer tcpPort) {
        this.tcpPort = tcpPort;
    }

    public Integer getCompensationControl() {
        return compensationControl;
    }

    public void setCompensationControl(Integer compensationControl) {
        this.compensationControl = compensationControl;
    }

    public Integer getBarrierInterface() {
        return barrierInterface;
    }

    public void setBarrierInterface(Integer barrierInterface) {
        this.barrierInterface = barrierInterface;
    }

    public Integer getMainboardType() {
        return mainboardType;
    }

    public void setMainboardType(Integer mainboardType) {
        this.mainboardType = mainboardType;
    }

    public String getDyQrKeys() {
        return dyQrKeys;
    }

    public void setDyQrKeys(String dyQrKeys) {
        this.dyQrKeys = dyQrKeys;
    }

    public String getDyQrCodeUrl() {
        return dyQrCodeUrl;
    }

    public void setDyQrCodeUrl(String dyQrCodeUrl) {
        this.dyQrCodeUrl = dyQrCodeUrl;
    }

    public Integer getAutoPayFirstModel() {
        return autoPayFirstModel;
    }

    public void setAutoPayFirstModel(Integer autoPayFirstModel) {
        this.autoPayFirstModel = autoPayFirstModel;
    }

    public Integer getIsAutoPay() {
        return isAutoPay;
    }

    public void setIsAutoPay(Integer isAutoPay) {
        this.isAutoPay = isAutoPay;
    }

    public Integer getIsAutoPayByWechart() {
        return isAutoPayByWechart;
    }

    public void setIsAutoPayByWechart(Integer isAutoPayByWechart) {
        this.isAutoPayByWechart = isAutoPayByWechart;
    }

    public Integer getAutoPayAfterExit() {
        return autoPayAfterExit;
    }

    public void setAutoPayAfterExit(Integer autoPayAfterExit) {
        this.autoPayAfterExit = autoPayAfterExit;
    }

    public Integer getDelayType() {
        return delayType;
    }

    public void setDelayType(Integer delayType) {
        this.delayType = delayType;
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

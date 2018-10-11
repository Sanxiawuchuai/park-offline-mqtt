package com.drzk.online.vo;


public class ParkSettingVO extends OnlineBody{
	private String city;
    private String gisInfo;
    private Integer totalSpaces;
    private Integer fixedSpaces;
    private Integer tempSpaces;
    private Integer spacesType;
    private String userName;
    private String password;
    private Integer cameraType;
    private String mqttIp;
    private String cloudmqttIp;
    private Integer isUploadCloud;
    private Integer isUploadImage;
    private Integer allowExpiresDay;
    private Integer monthCardChargeType;
    private String normalCharge;
    private Integer temporaryFreeType;
    private Integer discountModel;
    private Integer carNoMatchType;
    private String defaultCarPrefix;
    private String freeCarPrefix;
    private Integer broadcastLicensePlate;
    private Integer fullSpaceEnter;
    private Integer viewCurrentUserMoney;
    private Integer fastExit;
    private Integer carLog;
    private Integer fixedSpcMEnterMExit;
    private Integer udpUpPort;
    private Integer udpDownPort;
    private Integer tcpPort;
    private Integer compensationControl;
    private Integer barrierInterface;
    private Integer mainboardType;
    private String dyQrKeys;
    private String dyQrCodeUrl;
    private Integer autoPayFirstModel;
    private Integer isAutoPay;
    private Integer isAutoPayByWechart;
    private Integer autoPayAfterExit;
    private Integer delayType;        //续费类型


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    /**
     * gis信息
     */
    public String getGisInfo() {
        return gisInfo;
    }

    /**
     * gis信息
     */
    public void setGisInfo(String gisInfo) {
        this.gisInfo = gisInfo;
    }

    /**
     * 总车位数
     */
    public Integer getTotalSpaces() {
        return totalSpaces;
    }

    /**
     * 总车位数
     */
    public void setTotalSpaces(Integer totalSpaces) {
        this.totalSpaces = totalSpaces;
    }

    /**
     * 固定车车位数
     */
    public Integer getFixedSpaces() {
        return fixedSpaces;
    }

    /**
     * 固定车车位数
     */
    public void setFixedSpaces(Integer fixedSpaces) {
        this.fixedSpaces = fixedSpaces;
    }

    /**
     * 临时车位数
     */
    public Integer getTempSpaces() {
        return tempSpaces;
    }

    /**
     * 临时车位数
     */
    public void setTempSpaces(Integer tempSpaces) {
        this.tempSpaces = tempSpaces;
    }

    /**
     * 车位类型 1混合车位 2临时车位
     */
    public Integer getSpacesType() {
        return spacesType;
    }

    /**
     * 车位类型 1混合车位 2临时车位
     */
    public void setSpacesType(Integer spacesType) {
        this.spacesType = spacesType;
    }

    /**
     * 相机 用户
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 相机 用户
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 相机密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 相机密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 相机类型
     */
    public Integer getCameraType() {
        return cameraType;
    }

    /**
     * 相机类型
     */
    public void setCameraType(Integer cameraType) {
        this.cameraType = cameraType;
    }

    /**
     * 线下MQTT服务器IP
     */
    public String getMqttIp() {
        return mqttIp;
    }

    /**
     * 线下MQTT服务器IP
     */
    public void setMqttIp(String mqttIp) {
        this.mqttIp = mqttIp;
    }

    /**
     * 云端MQTT
     */
    public String getCloudmqttIp() {
        return cloudmqttIp;
    }

    /**
     * 云端MQTT
     */
    public void setCloudmqttIp(String cloudmqttIp) {
        this.cloudmqttIp = cloudmqttIp;
    }

    /**
     * 是否上传数据至云端 0否 1是
     */
    public Integer getIsUploadCloud() {
        return isUploadCloud;
    }

    /**
     * 是否上传数据至云端 0否 1是
     */
    public void setIsUploadCloud(Integer isUploadCloud) {
        this.isUploadCloud = isUploadCloud;
    }

    /**
     * 是否上传图片 0否 1是
     */
    public Integer getIsUploadImage() {
        return isUploadImage;
    }

    /**
     * 是否上传图片 0否 1是
     */
    public void setIsUploadImage(Integer isUploadImage) {
        this.isUploadImage = isUploadImage;
    }

    /**
     * 允许固定车过期天数
     */
    public Integer getAllowExpiresDay() {
        return allowExpiresDay;
    }

    /**
     * 允许固定车过期天数
     */
    public void setAllowExpiresDay(Integer allowExpiresDay) {
        this.allowExpiresDay = allowExpiresDay;
    }

    /**
     * 月临卡收费类型 "临时类A" "临时类"
     */
    public Integer getMonthCardChargeType() {
        return monthCardChargeType;
    }

    /**
     * 月临卡收费类型 "临时类A" "临时类"
     */
    public void setMonthCardChargeType(Integer monthCardChargeType) {
        this.monthCardChargeType = monthCardChargeType;
    }

    /**
     * 收费标准类型
     */
    public String getNormalCharge() {
        return normalCharge;
    }

    /**
     * 收费标准类型
     */
    public void setNormalCharge(String normalCharge) {
        this.normalCharge = normalCharge;
    }

    /**
     * 临时车免费类型 1手工免费 2自动设置
     */
    public Integer getTemporaryFreeType() {
        return temporaryFreeType;
    }

    /**
     * 临时车免费类型 1手工免费 2自动设置
     */
    public void setTemporaryFreeType(Integer temporaryFreeType) {
        this.temporaryFreeType = temporaryFreeType;
    }

    /**
     * 优惠模式 0 简易打折 1 无
     */
    public Integer getDiscountModel() {
        return discountModel;
    }

    /**
     * 优惠模式 0 简易打折 1 无
     */
    public void setDiscountModel(Integer discountModel) {
        this.discountModel = discountModel;
    }

    /**
     * 车牌匹配类型 1六位 2五位
     */
    public Integer getCarNoMatchType() {
        return carNoMatchType;
    }

    /**
     * 车牌匹配类型 1六位 2五位
     */
    public void setCarNoMatchType(Integer carNoMatchType) {
        this.carNoMatchType = carNoMatchType;
    }

    /**
     * 默认车牌前缀 "粤"
     */
    public String getDefaultCarPrefix() {
        return defaultCarPrefix;
    }

    /**
     * 默认车牌前缀 "粤"
     */
    public void setDefaultCarPrefix(String defaultCarPrefix) {
        this.defaultCarPrefix = defaultCarPrefix;
    }

    /**
     * 自由通行车牌前缀 "WJ"
     */
    public String getFreeCarPrefix() {
        return freeCarPrefix;
    }

    /**
     * 自由通行车牌前缀 "WJ"
     */
    public void setFreeCarPrefix(String freeCarPrefix) {
        this.freeCarPrefix = freeCarPrefix;
    }

    /**
     * 播报车牌 0/1
     */
    public Integer getBroadcastLicensePlate() {
        return broadcastLicensePlate;
    }

    /**
     * 播报车牌 0/1
     */
    public void setBroadcastLicensePlate(Integer broadcastLicensePlate) {
        this.broadcastLicensePlate = broadcastLicensePlate;
    }

    /**
     * 车位满位入场 0/1
     */
    public Integer getFullSpaceEnter() {
        return fullSpaceEnter;
    }

    /**
     * 车位满位入场 0/1
     */
    public void setFullSpaceEnter(Integer fullSpaceEnter) {
        this.fullSpaceEnter = fullSpaceEnter;
    }

    /**
     * 显示当班收费金额 0/1
     */
    public Integer getViewCurrentUserMoney() {
        return viewCurrentUserMoney;
    }

    /**
     * 显示当班收费金额 0/1
     */
    public void setViewCurrentUserMoney(Integer viewCurrentUserMoney) {
        this.viewCurrentUserMoney = viewCurrentUserMoney;
    }

    /**
     * 快速出场 0/1
     */
    public Integer getFastExit() {
        return fastExit;
    }

    /**
     * 快速出场 0/1
     */
    public void setFastExit(Integer fastExit) {
        this.fastExit = fastExit;
    }

    /**
     * 车场日志跟踪 0 / 1
     */
    public Integer getCarLog() {
        return carLog;
    }

    /**
     * 车场日志跟踪 0 / 1
     */
    public void setCarLog(Integer carLog) {
        this.carLog = carLog;
    }

    /**
     * 固定车多进多出 0 / 1
     */
    public Integer getFixedSpcMEnterMExit() {
        return fixedSpcMEnterMExit;
    }

    /**
     * 固定车多进多出 0 / 1
     */
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

    /**
     * 补闸控制 1禁止 2补开闸
     */
    public Integer getCompensationControl() {
        return compensationControl;
    }

    /**
     * 补闸控制 1禁止 2补开闸
     */
    public void setCompensationControl(Integer compensationControl) {
        this.compensationControl = compensationControl;
    }

    /**
     * 道闸接口 1电平 2 485接口
     */
    public Integer getBarrierInterface() {
        return barrierInterface;
    }

    /**
     * 道闸接口 1电平 2 485接口
     */
    public void setBarrierInterface(Integer barrierInterface) {
        this.barrierInterface = barrierInterface;
    }

    /**
     * 主板类型 1 S板 2 Linux板
     */
    public Integer getMainboardType() {
        return mainboardType;
    }

    /**
     * 主板类型 1 S板 2 Linux板
     */
    public void setMainboardType(Integer mainboardType) {
        this.mainboardType = mainboardType;
    }

    /**
     * 动态二维码加密密钥 "ASJJ"
     */
    public String getDyQrKeys() {
        return dyQrKeys;
    }

    /**
     * 动态二维码加密密钥 "ASJJ"
     */
    public void setDyQrKeys(String dyQrKeys) {
        this.dyQrKeys = dyQrKeys;
    }

    /**
     * 动态二维码url地址
     */
    public String getDyQrCodeUrl() {
        return dyQrCodeUrl;
    }

    /**
     * 动态二维码url地址
     */
    public void setDyQrCodeUrl(String dyQrCodeUrl) {
        this.dyQrCodeUrl = dyQrCodeUrl;
    }

    /**
     * 无感支付优先模式 0 无| 1微信
     */
    public Integer getAutoPayFirstModel() {
        return autoPayFirstModel;
    }

    /**
     * 无感支付优先模式 0 无| 1微信
     */
    public void setAutoPayFirstModel(Integer autoPayFirstModel) {
        this.autoPayFirstModel = autoPayFirstModel;
    }

    /**
     * 支付无感支付 0 |1
     */
    public Integer getIsAutoPay() {
        return isAutoPay;
    }

    /**
     * 支付无感支付 0 |1
     */
    public void setIsAutoPay(Integer isAutoPay) {
        this.isAutoPay = isAutoPay;
    }

    /**
     * 微信支付无感支付 0 |1
     */
    public Integer getIsAutoPayByWechart() {
        return isAutoPayByWechart;
    }

    /**
     * 微信支付无感支付 0 |1
     */
    public void setIsAutoPayByWechart(Integer isAutoPayByWechart) {
        this.isAutoPayByWechart = isAutoPayByWechart;
    }

    /**
     * 无感支付先付费后出场 0 |1
     */
    public Integer getAutoPayAfterExit() {
        return autoPayAfterExit;
    }

    /**
     * 无感支付先付费后出场 0 |1
     */
    public void setAutoPayAfterExit(Integer autoPayAfterExit) {
        this.autoPayAfterExit = autoPayAfterExit;
    }

    public Integer getDelayType() {
        return delayType;
    }

    public void setDelayType(Integer delayType) {
        this.delayType = delayType;
    }
}

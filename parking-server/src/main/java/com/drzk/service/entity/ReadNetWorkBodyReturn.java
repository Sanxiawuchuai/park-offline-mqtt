package com.drzk.service.entity;
//读取网络参数返回实体
public class ReadNetWorkBodyReturn  extends SuperBody{
	//--本机IP
	private String localIp;
	// --子网掩码
	private String subnetMask ;
	//  --默认网关
	private String defaultGateway;
	// --主摄像机IP
	private String mainCameraIP;
	// --副相机Ip
	private String viceCameraIP;
	//  --尾相机Ip
	private String backCameraIP;
	// --平台服务Ip
	private String platformServerIP;
	// --平台端口号
	private String platformPort;
	public String getLocalIp() {
		return localIp;
	}
	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}
	public String getSubnetMask() {
		return subnetMask;
	}
	public void setSubnetMask(String subnetMask) {
		this.subnetMask = subnetMask;
	}
	public String getDefaultGateway() {
		return defaultGateway;
	}
	public void setDefaultGateway(String defaultGateway) {
		this.defaultGateway = defaultGateway;
	}
	public String getMainCameraIP() {
		return mainCameraIP;
	}
	public void setMainCameraIP(String mainCameraIP) {
		this.mainCameraIP = mainCameraIP;
	}
	public String getViceCameraIP() {
		return viceCameraIP;
	}
	public void setViceCameraIP(String viceCameraIP) {
		this.viceCameraIP = viceCameraIP;
	}
	public String getBackCameraIP() {
		return backCameraIP;
	}
	public void setBackCameraIP(String backCameraIP) {
		this.backCameraIP = backCameraIP;
	}
	public String getPlatformServerIP() {
		return platformServerIP;
	}
	public void setPlatformServerIP(String platformServerIP) {
		this.platformServerIP = platformServerIP;
	}
	public String getPlatformPort() {
		return platformPort;
	}
	public void setPlatformPort(String platformPort) {
		this.platformPort = platformPort;
	}
}

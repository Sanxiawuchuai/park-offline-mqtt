package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 设备状态信息
 */
public class DeviceStatusVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -714157528364610143L;
	private Integer plControllerId;//控制器机号(1-255)
    private String controllerName;//控制器名称
    private String boxName;//岗亭名称
    private String ipAddress;//控制器IP
    private String realStatus; //1;0;0;0;0|0;0前四位，通讯 地感  道闸  卡机
    private String description;//描述
	public Integer getPlControllerId() {
		return plControllerId;
	}
	public void setPlControllerId(Integer plControllerId) {
		this.plControllerId = plControllerId;
	}
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
	}
	public String getBoxName() {
		return boxName;
	}
	public void setBoxName(String boxName) {
		this.boxName = boxName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getRealStatus() {
		return realStatus;
	}
	public void setRealStatus(String realStatus) {
		this.realStatus = realStatus;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}

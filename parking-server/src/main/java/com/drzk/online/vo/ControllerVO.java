package com.drzk.online.vo;

/**
 * @author tf
 * 控制器信息
 */
public class ControllerVO {
	 private Integer controllerId ; //控制器机号(1-255)
	 private String  ContName ; //控制器名称
	 private String controllerIp ;//控制器IP
     private Integer  type ;//出入类型（0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道）
     private Integer  openMacNum ;//开闸机号
     private Integer channelType ;//通道类型（0综合信道 1固定车信道，2临时卡通道）
     private Integer  boxId ; //岗亭编号(1-64)
     private String boxName ;//岗亭名称
     private String description ; //描述
     private String mac;//控制器MAC地址
	public Integer getControllerId() {
		return controllerId;
	}
	public void setControllerId(Integer controllerId) {
		this.controllerId = controllerId;
	}
	public String getContName() {
		return ContName;
	}
	public void setContName(String contName) {
		ContName = contName;
	}
	public String getControllerIp() {
		return controllerIp;
	}
	public void setControllerIp(String controllerIp) {
		this.controllerIp = controllerIp;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getOpenMacNum() {
		return openMacNum;
	}
	public void setOpenMacNum(Integer openMacNum) {
		this.openMacNum = openMacNum;
	}
	public Integer getChannelType() {
		return channelType;
	}
	public void setChannelType(Integer channelType) {
		this.channelType = channelType;
	}
	public Integer getBoxId() {
		return boxId;
	}
	public void setBoxId(Integer boxId) {
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
     
}

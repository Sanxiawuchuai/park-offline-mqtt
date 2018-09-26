package com.drzk.online.vo;

/**
 * @author tf
 * 相机信息
 */
public class cameVO {
	private String cameIp ; //摄像头IP
	private String cameName ; //摄像头名称
	private String camePort ;//设备名端口号卡类ID
	private String userName ;//摄像机用户名
	private String passWord;//摄像机密码
	private Integer controllerChanlId ;//开闸机号
	private Integer  controllerId ;//控制器编号
	private String  controllerName ;//控制器名称
	private Integer boxId ; //岗亭编号(1-64)
	private String  boxName ;//岗亭名称
	private String  description ; //描述
	private String  mac; //相机MAC地址
	public String getCameIp() {
		return cameIp;
	}
	public void setCameIp(String cameIp) {
		this.cameIp = cameIp;
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
	public Integer getControllerChanlId() {
		return controllerChanlId;
	}
	public void setControllerChanlId(Integer controllerChanlId) {
		this.controllerChanlId = controllerChanlId;
	}
	public Integer getControllerId() {
		return controllerId;
	}
	public void setControllerId(Integer controllerId) {
		this.controllerId = controllerId;
	}
	public String getControllerName() {
		return controllerName;
	}
	public void setControllerName(String controllerName) {
		this.controllerName = controllerName;
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

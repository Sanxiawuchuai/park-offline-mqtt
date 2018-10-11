package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 岗亭信息
 */
public class BoxVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2430854083906731954L;
	private Integer boxId; // 岗亭编号(1-64)
	private String boxName;// 岗亭名称
	private String parkingLotName; // 客户名称
	private String BoxIP;// 岗亭电脑IP
	private Integer BoxType;// 岗亭类型（0标准收费点，1中央收费点，2综合收费点）
	private String description; // 描述
	private String mac; // 岗亭mac地址
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
	public String getParkingLotName() {
		return parkingLotName;
	}
	public void setParkingLotName(String parkingLotName) {
		this.parkingLotName = parkingLotName;
	}
	public String getBoxIP() {
		return BoxIP;
	}
	public void setBoxIP(String boxIP) {
		BoxIP = boxIP;
	}
	public Integer getBoxType() {
		return BoxType;
	}
	public void setBoxType(Integer boxType) {
		BoxType = boxType;
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

package com.drzk.vo;

import java.util.Date;

public class ParkDeviceStatus {
	/**
	 * 
	 */
	private Integer id;

	/**
	 * 设备编号
	 */
	private Integer macNo;

	/**
	 * 设备IP
	 */
	private String macIp;

	/**
	 * 岗亭编号
	 */
	private Integer boxId;

	/**
	 * 道闸状态 0-关到位，1-中间位，2-开到位
	 */
	private Byte roadGateState;

	/**
	 * 卡机状态 0-正常，1-少卡，2-无卡，3-吞卡箱满，4-故障，5-堵卡
	 */
	private Byte cardMachineState;

	/**
	 * 纸票机状态 0-正常，1-纸少，2-无纸，3-出票口有票，4-故障，5-忙，6-打印失败
	 */
	private Byte paperMachineState;

	/**
	 * 主板上电复位,自检结果和关健配置
	 */
	private Byte powerReset;

	/**
	 * 地感异常 00-恢复，01-异常
	 */
	private Byte groudSenseUnnormal;

	/**
	 * 摄像机异常 00-恢复，01-异常
	 */
	private Byte cameraUnnormal;

	/**
	 * 岗亭PC连接异常 00-恢复，01-异常
	 */
	private Byte clientConnectUnnormal;

	/**
	 * 服务器连接异常 00-恢复，01-异常
	 */
	private Byte serverConnectUnnormal;

	/**
	 * 显示模块连接异常 00-恢复，01-异常
	 */
	private Byte disModuleConUnnormal;

	/**
	 * 语音模块连接异常 00-恢复，01-异常
	 */
	private Byte voicemoduleConUnnormal;

	/**
	 * 道闸连接异常 00-恢复，01-异常
	 */
	private Byte roadGateConUnnormal;

	/**
	 * 卡机连接异常 00-恢复，01-异常
	 */
	private Byte cardMachConUnormal;

	/**
	 * 纸票机连接异常 00-恢复，01-异常
	 */
	private Byte paperMachConUnormal;

	/**
	 * 主板网络异常 00-恢复，01-异常
	 */
	private Byte mainBoardNetUnnormal;

	/**
	 * 系统时钟故障 00-恢复，01-异常
	 */
	private Byte clockUnnormal;

	/**
	 * 系统存储故障 00-恢复，01-异常
	 */
	private Byte storageUnnormal;

	/**
	 * 在线时间
	 */
	private Date onlineTime;

	/**
	 * 最后一次更新时间
	 */
	private Date lastUpdateTime;

	/**
	 * 备注
	 */
	private String memo;
	/**
	 * 备注
	 */
	private boolean isOnLine;

	public boolean isOnLine() {
		return isOnLine;
	}

	public void setOnLine(boolean isOnLine) {
		this.isOnLine = isOnLine;
	}

	/**
	 * 
	 * @return id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * 设备编号
	 * 
	 * @return mac_no 设备编号
	 */
	public Integer getMacNo() {
		return macNo;
	}

	/**
	 * 设备编号
	 * 
	 * @param macNo 设备编号
	 */
	public void setMacNo(Integer macNo) {
		this.macNo = macNo;
	}

	/**
	 * 设备IP
	 * 
	 * @return mac_ip 设备IP
	 */
	public String getMacIp() {
		return macIp;
	}

	/**
	 * 设备IP
	 * 
	 * @param macIp 设备IP
	 */
	public void setMacIp(String macIp) {
		this.macIp = macIp == null ? null : macIp.trim();
	}

	/**
	 * 岗亭编号
	 * 
	 * @return box_id 岗亭编号
	 */
	public Integer getBoxId() {
		return boxId;
	}

	/**
	 * 岗亭编号
	 * 
	 * @param boxId 岗亭编号
	 */
	public void setBoxId(Integer boxId) {
		this.boxId = boxId;
	}

	/**
	 * 道闸状态 0-关到位，1-中间位，2-开到位
	 * 
	 * @return road_gate_state 道闸状态 0-关到位，1-中间位，2-开到位
	 */
	public Byte getRoadGateState() {
		return roadGateState;
	}

	/**
	 * 道闸状态 0-关到位，1-中间位，2-开到位
	 * 
	 * @param roadGateState 道闸状态 0-关到位，1-中间位，2-开到位
	 */
	public void setRoadGateState(Byte roadGateState) {
		this.roadGateState = roadGateState;
	}

	/**
	 * 卡机状态 0-正常，1-少卡，2-无卡，3-吞卡箱满，4-故障，5-堵卡
	 * 
	 * @return card_machine_state 卡机状态 0-正常，1-少卡，2-无卡，3-吞卡箱满，4-故障，5-堵卡
	 */
	public Byte getCardMachineState() {
		return cardMachineState;
	}

	/**
	 * 卡机状态 0-正常，1-少卡，2-无卡，3-吞卡箱满，4-故障，5-堵卡
	 * 
	 * @param cardMachineState 卡机状态 0-正常，1-少卡，2-无卡，3-吞卡箱满，4-故障，5-堵卡
	 */
	public void setCardMachineState(Byte cardMachineState) {
		this.cardMachineState = cardMachineState;
	}

	/**
	 * 纸票机状态 0-正常，1-纸少，2-无纸，3-出票口有票，4-故障，5-忙，6-打印失败
	 * 
	 * @return paper_machine_state 纸票机状态 0-正常，1-纸少，2-无纸，3-出票口有票，4-故障，5-忙，6-打印失败
	 */
	public Byte getPaperMachineState() {
		return paperMachineState;
	}

	/**
	 * 纸票机状态 0-正常，1-纸少，2-无纸，3-出票口有票，4-故障，5-忙，6-打印失败
	 * 
	 * @param paperMachineState 纸票机状态 0-正常，1-纸少，2-无纸，3-出票口有票，4-故障，5-忙，6-打印失败
	 */
	public void setPaperMachineState(Byte paperMachineState) {
		this.paperMachineState = paperMachineState;
	}

	/**
	 * 主板上电复位,自检结果和关健配置
	 * 
	 * @return power_reset 主板上电复位,自检结果和关健配置
	 */
	public Byte getPowerReset() {
		return powerReset;
	}

	/**
	 * 主板上电复位,自检结果和关健配置
	 * 
	 * @param powerReset 主板上电复位,自检结果和关健配置
	 */
	public void setPowerReset(Byte powerReset) {
		this.powerReset = powerReset;
	}

	/**
	 * 地感异常 00-恢复，01-异常
	 * 
	 * @return groud_sense_unnormal 地感异常 00-恢复，01-异常
	 */
	public Byte getGroudSenseUnnormal() {
		return groudSenseUnnormal;
	}

	/**
	 * 地感异常 00-恢复，01-异常
	 * 
	 * @param groudSenseUnnormal 地感异常 00-恢复，01-异常
	 */
	public void setGroudSenseUnnormal(Byte groudSenseUnnormal) {
		this.groudSenseUnnormal = groudSenseUnnormal;
	}

	/**
	 * 摄像机异常 00-恢复，01-异常
	 * 
	 * @return camera_unnormal 摄像机异常 00-恢复，01-异常
	 */
	public Byte getCameraUnnormal() {
		return cameraUnnormal;
	}

	/**
	 * 摄像机异常 00-恢复，01-异常
	 * 
	 * @param cameraUnnormal 摄像机异常 00-恢复，01-异常
	 */
	public void setCameraUnnormal(Byte cameraUnnormal) {
		this.cameraUnnormal = cameraUnnormal;
	}

	/**
	 * 岗亭PC连接异常 00-恢复，01-异常
	 * 
	 * @return client_connect_unnormal 岗亭PC连接异常 00-恢复，01-异常
	 */
	public Byte getClientConnectUnnormal() {
		return clientConnectUnnormal;
	}

	/**
	 * 岗亭PC连接异常 00-恢复，01-异常
	 * 
	 * @param clientConnectUnnormal 岗亭PC连接异常 00-恢复，01-异常
	 */
	public void setClientConnectUnnormal(Byte clientConnectUnnormal) {
		this.clientConnectUnnormal = clientConnectUnnormal;
	}

	/**
	 * 服务器连接异常 00-恢复，01-异常
	 * 
	 * @return server_connect_unnormal 服务器连接异常 00-恢复，01-异常
	 */
	public Byte getServerConnectUnnormal() {
		return serverConnectUnnormal;
	}

	/**
	 * 服务器连接异常 00-恢复，01-异常
	 * 
	 * @param serverConnectUnnormal 服务器连接异常 00-恢复，01-异常
	 */
	public void setServerConnectUnnormal(Byte serverConnectUnnormal) {
		this.serverConnectUnnormal = serverConnectUnnormal;
	}

	/**
	 * 显示模块连接异常 00-恢复，01-异常
	 * 
	 * @return Dis_module_con_unnormal 显示模块连接异常 00-恢复，01-异常
	 */
	public Byte getDisModuleConUnnormal() {
		return disModuleConUnnormal;
	}

	/**
	 * 显示模块连接异常 00-恢复，01-异常
	 * 
	 * @param disModuleConUnnormal 显示模块连接异常 00-恢复，01-异常
	 */
	public void setDisModuleConUnnormal(Byte disModuleConUnnormal) {
		this.disModuleConUnnormal = disModuleConUnnormal;
	}

	/**
	 * 语音模块连接异常 00-恢复，01-异常
	 * 
	 * @return voicemodule_con_unnormal 语音模块连接异常 00-恢复，01-异常
	 */
	public Byte getVoicemoduleConUnnormal() {
		return voicemoduleConUnnormal;
	}

	/**
	 * 语音模块连接异常 00-恢复，01-异常
	 * 
	 * @param voicemoduleConUnnormal 语音模块连接异常 00-恢复，01-异常
	 */
	public void setVoicemoduleConUnnormal(Byte voicemoduleConUnnormal) {
		this.voicemoduleConUnnormal = voicemoduleConUnnormal;
	}

	/**
	 * 道闸连接异常 00-恢复，01-异常
	 * 
	 * @return road_gate_Con_unnormal 道闸连接异常 00-恢复，01-异常
	 */
	public Byte getRoadGateConUnnormal() {
		return roadGateConUnnormal;
	}

	/**
	 * 道闸连接异常 00-恢复，01-异常
	 * 
	 * @param roadGateConUnnormal 道闸连接异常 00-恢复，01-异常
	 */
	public void setRoadGateConUnnormal(Byte roadGateConUnnormal) {
		this.roadGateConUnnormal = roadGateConUnnormal;
	}

	/**
	 * 卡机连接异常 00-恢复，01-异常
	 * 
	 * @return card_mach_con_unormal 卡机连接异常 00-恢复，01-异常
	 */
	public Byte getCardMachConUnormal() {
		return cardMachConUnormal;
	}

	/**
	 * 卡机连接异常 00-恢复，01-异常
	 * 
	 * @param cardMachConUnormal 卡机连接异常 00-恢复，01-异常
	 */
	public void setCardMachConUnormal(Byte cardMachConUnormal) {
		this.cardMachConUnormal = cardMachConUnormal;
	}

	/**
	 * 纸票机连接异常 00-恢复，01-异常
	 * 
	 * @return paper_mach_con_unormal 纸票机连接异常 00-恢复，01-异常
	 */
	public Byte getPaperMachConUnormal() {
		return paperMachConUnormal;
	}

	/**
	 * 纸票机连接异常 00-恢复，01-异常
	 * 
	 * @param paperMachConUnormal 纸票机连接异常 00-恢复，01-异常
	 */
	public void setPaperMachConUnormal(Byte paperMachConUnormal) {
		this.paperMachConUnormal = paperMachConUnormal;
	}

	/**
	 * 主板网络异常 00-恢复，01-异常
	 * 
	 * @return main_board_net_unnormal 主板网络异常 00-恢复，01-异常
	 */
	public Byte getMainBoardNetUnnormal() {
		return mainBoardNetUnnormal;
	}

	/**
	 * 主板网络异常 00-恢复，01-异常
	 * 
	 * @param mainBoardNetUnnormal 主板网络异常 00-恢复，01-异常
	 */
	public void setMainBoardNetUnnormal(Byte mainBoardNetUnnormal) {
		this.mainBoardNetUnnormal = mainBoardNetUnnormal;
	}

	/**
	 * 系统时钟故障 00-恢复，01-异常
	 * 
	 * @return clock_unnormal 系统时钟故障 00-恢复，01-异常
	 */
	public Byte getClockUnnormal() {
		return clockUnnormal;
	}

	/**
	 * 系统时钟故障 00-恢复，01-异常
	 * 
	 * @param clockUnnormal 系统时钟故障 00-恢复，01-异常
	 */
	public void setClockUnnormal(Byte clockUnnormal) {
		this.clockUnnormal = clockUnnormal;
	}

	/**
	 * 系统存储故障 00-恢复，01-异常
	 * 
	 * @return storage_unnormal 系统存储故障 00-恢复，01-异常
	 */
	public Byte getStorageUnnormal() {
		return storageUnnormal;
	}

	/**
	 * 系统存储故障 00-恢复，01-异常
	 * 
	 * @param storageUnnormal 系统存储故障 00-恢复，01-异常
	 */
	public void setStorageUnnormal(Byte storageUnnormal) {
		this.storageUnnormal = storageUnnormal;
	}

	/**
	 * 在线时间
	 * 
	 * @return online_time 在线时间
	 */
	public Date getOnlineTime() {
		return onlineTime;
	}

	/**
	 * 在线时间
	 * 
	 * @param onlineTime 在线时间
	 */
	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}

	/**
	 * 最后一次更新时间
	 * 
	 * @return last_update_time 最后一次更新时间
	 */
	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	/**
	 * 最后一次更新时间
	 * 
	 * @param lastUpdateTime 最后一次更新时间
	 */
	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	/**
	 * 备注
	 * 
	 * @return memo 备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 备注
	 * 
	 * @param memo 备注
	 */
	public void setMemo(String memo) {
		this.memo = memo == null ? null : memo.trim();
	}

	@Override
	public int hashCode() {
		return this.macIp.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof ParkDeviceStatus) {
			ParkDeviceStatus deviceStatus = (ParkDeviceStatus)obj;
			if(this.macIp == null || deviceStatus == null || deviceStatus.getMacIp() == null) {
				return false;
			}else {
				return this.macIp.equals(deviceStatus.getMacIp());
			}
		}else {
			return false;
		}
	}

}
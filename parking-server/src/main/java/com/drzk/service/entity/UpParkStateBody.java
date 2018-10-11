package com.drzk.service.entity;

import java.io.Serializable;

//3.5. 上传车场状态(服务器订阅)
public class UpParkStateBody implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 4481992634668576717L;
	private String uId;
	private String conIp;//控制器Ip
	
	private String roadGate;//道闸状态：0-关到位,1-中间位,2-开到位
	private String groundSense;//地感异常: 0-恢复,1-异常
	private String camera;//摄像机异常:0-恢复,1-异常，
	private String serverConnect;//服务器连接异常:0-恢复,1-异常
	private String displayConnect;//显示模块连接异常:0-恢复,1-异常
	private String voiceConnect;//语音模块连接异常:0-恢复,1-异常
	private String roadGateConnect ;//道闸连接异常:0-恢复,1-异常
	private String mainBoardNet;//主板网络异常:0-恢复,1-异常
	private String sysClock;//系统时钟故障:0-恢复,1-异常
	private String syssStorage;//系统存储故障:0-恢复,1-异常
	private String tempCarFull;//临时车满位状态:0-正常,1-满位
	private String fixedCarFull; //固定车满位状态:0-正常,1-满位
	
	public String getConIp() {
		return conIp;
	}
	public void setConIp(String conIp) {
		this.conIp = conIp;
	}
	
	/**
	* roadGate.
	*
	* @return the roadGate
	* @since JDK 1.8
	*/
	public String getRoadGate() {
		return roadGate;
	}
	
	/**
	* roadGate.
	*
	* @param roadGate the roadGate to set
	* @since JDK 1.8
	*/
	public void setRoadGate(String roadGate) {
		this.roadGate = roadGate;
	}
	
	/**
	* groundSense.
	*
	* @return the groundSense
	* @since JDK 1.8
	*/
	public String getGroundSense() {
		return groundSense;
	}
	
	/**
	* groundSense.
	*
	* @param groundSense the groundSense to set
	* @since JDK 1.8
	*/
	public void setGroundSense(String groundSense) {
		this.groundSense = groundSense;
	}
	
	/**
	* camera.
	*
	* @return the camera
	* @since JDK 1.8
	*/
	public String getCamera() {
		return camera;
	}
	
	/**
	* camera.
	*
	* @param camera the camera to set
	* @since JDK 1.8
	*/
	public void setCamera(String camera) {
		this.camera = camera;
	}
	
	/**
	* serverConnect.
	*
	* @return the serverConnect
	* @since JDK 1.8
	*/
	public String getServerConnect() {
		return serverConnect;
	}
	
	/**
	* serverConnect.
	*
	* @param serverConnect the serverConnect to set
	* @since JDK 1.8
	*/
	public void setServerConnect(String serverConnect) {
		this.serverConnect = serverConnect;
	}
	
	/**
	* displayConnect.
	*
	* @return the displayConnect
	* @since JDK 1.8
	*/
	public String getDisplayConnect() {
		if(displayConnect == null || displayConnect.equals("")) {
			return "0";
		}
		return displayConnect;
	}
	
	/**
	* displayConnect.
	*
	* @param displayConnect the displayConnect to set
	* @since JDK 1.8
	*/
	public void setDisplayConnect(String displayConnect) {
		this.displayConnect = displayConnect;
	}
	
	/**
	* voiceConnect.
	*
	* @return the voiceConnect
	* @since JDK 1.8
	*/
	public String getVoiceConnect() {
		if(voiceConnect == null || voiceConnect.equals("")) {
			return "0";
		}
		return voiceConnect;
	}
	
	/**
	* voiceConnect.
	*
	* @param voiceConnect the voiceConnect to set
	* @since JDK 1.8
	*/
	public void setVoiceConnect(String voiceConnect) {
		this.voiceConnect = voiceConnect;
	}
	
	/**
	* roadGateConnect.
	*
	* @return the roadGateConnect
	* @since JDK 1.8
	*/
	public String getRoadGateConnect() {
		if(roadGateConnect == null || roadGateConnect.equals("")) {
			return "0";
		}
		return roadGateConnect;
	}
	
	/**
	* roadGateConnect.
	*
	* @param roadGateConnect the roadGateConnect to set
	* @since JDK 1.8
	*/
	public void setRoadGateConnect(String roadGateConnect) {
		this.roadGateConnect = roadGateConnect;
	}
	
	/**
	* mainBoardNet.
	*
	* @return the mainBoardNet
	* @since JDK 1.8
	*/
	public String getMainBoardNet() {
		if(mainBoardNet == null || mainBoardNet.equals("")) {
			return "0";
		}
		return mainBoardNet;
	}
	
	/**
	* mainBoardNet.
	*
	* @param mainBoardNet the mainBoardNet to set
	* @since JDK 1.8
	*/
	public void setMainBoardNet(String mainBoardNet) {
		this.mainBoardNet = mainBoardNet;
	}
	
	/**
	* sysClock.
	*
	* @return the sysClock
	* @since JDK 1.8
	*/
	public String getSysClock() {
		if(sysClock == null || sysClock.equals("")) {
			return "0";
		}
		return sysClock;
	}
	
	/**
	* sysClock.
	*
	* @param sysClock the sysClock to set
	* @since JDK 1.8
	*/
	public void setSysClock(String sysClock) {
		this.sysClock = sysClock;
	}
	
	/**
	* syssStorage.
	*
	* @return the syssStorage
	* @since JDK 1.8
	*/
	public String getSyssStorage() {
		if(syssStorage == null || syssStorage.equals("")) {
			return "0";
		}
		return syssStorage;
	}
	
	/**
	* syssStorage.
	*
	* @param syssStorage the syssStorage to set
	* @since JDK 1.8
	*/
	public void setSyssStorage(String syssStorage) {
		this.syssStorage = syssStorage;
	}
	
	/**
	* tempCarFull.
	*
	* @return the tempCarFull
	* @since JDK 1.8
	*/
	public String getTempCarFull() {
		if(tempCarFull == null || tempCarFull.equals("")) {
			return "0";
		}
		return tempCarFull;
	}
	
	/**
	* tempCarFull.
	*
	* @param tempCarFull the tempCarFull to set
	* @since JDK 1.8
	*/
	public void setTempCarFull(String tempCarFull) {
		this.tempCarFull = tempCarFull;
	}
	
	/**
	* fixedCarFull.
	*
	* @return the fixedCarFull
	* @since JDK 1.8
	*/
	public String getFixedCarFull() {
		if(fixedCarFull == null || fixedCarFull.equals("")) {
			return "0";
		}
		return fixedCarFull;
	}
	
	/**
	* fixedCarFull.
	*
	* @param fixedCarFull the fixedCarFull to set
	* @since JDK 1.8
	*/
	public void setFixedCarFull(String fixedCarFull) {
		this.fixedCarFull = fixedCarFull;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	
	
}

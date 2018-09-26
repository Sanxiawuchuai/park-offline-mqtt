package com.drzk.service.entity;
//显示屏参数实体
public class DisplayParamBody  extends SuperBody{
	//显示模式 0-单层显示屏，1-又层显示屏,2-三层显示屏,3余位屏
	private String disPalyType;
	//时钟显示模式，0-现时:2015年12月10日10时50分星期三,1-20YY-MM_DD HH:MM 周 02：“20YY-MM-DD HH:MM ”03：“MM-DD HH:MM ”04：“HH:MM ”
	private String timeType;
	//剩余车位显示模式 00“剩余车位9999”01空位9999”02“空位９９９９”03“9999”04“９９９９”
	private String carPlaceType;
	//空闲显示 00-不显示，01-显示广告，02-显示时钟，03-显示广告和时钟
	private String freeTimeType;
	//入场语音设置 0-欢迎光临！1-欢迎您！2-欢迎您回家！ 3-祝小朋友早日康复！4-祝您早日康复！
	private String inVoiceType;
//	/显示模式 0-单层显示屏，1-又层显示屏,2-三层显示屏,3余位屏
	private String outVoiceType;
	//广告屏参数
	private Advertisement advertisement;
	public String getDisPalyType() {
		return disPalyType;
	}
	public void setDisPalyType(String disPalyType) {
		this.disPalyType = disPalyType;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getCarPlaceType() {
		return carPlaceType;
	}
	public void setCarPlaceType(String carPlaceType) {
		this.carPlaceType = carPlaceType;
	}
	public String getFreeTimeType() {
		return freeTimeType;
	}
	public void setFreeTimeType(String freeTimeType) {
		this.freeTimeType = freeTimeType;
	}
	public String getInVoiceType() {
		return inVoiceType;
	}
	public void setInVoiceType(String inVoiceType) {
		this.inVoiceType = inVoiceType;
	}
	public String getOutVoiceType() {
		return outVoiceType;
	}
	public void setOutVoiceType(String outVoiceType) {
		this.outVoiceType = outVoiceType;
	}
	public Advertisement getAdvertisement() {
		return advertisement;
	}
	public void setAdvertisement(Advertisement advertisement) {
		this.advertisement = advertisement;
	}


	
}

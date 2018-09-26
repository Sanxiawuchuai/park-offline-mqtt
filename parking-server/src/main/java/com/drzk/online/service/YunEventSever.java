package com.drzk.online.service;

/** 云端扫码出入场，扫码支付  */
public interface YunEventSever{

	/**扫码出入场  */
	void scanCodesInOut(String jsonStr);
	/** 获取费用 */
	void getCentrilChargeData(String jsonStr);
	/** 下发的缴费纪录 */
	void writeChargeData(String jsonStr);
	/**线上续费下发*/
	public void onlineRenewal(String jsonStr);

}

package com.drzk.online.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.drzk.online.service.YunEventSever;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.SpringUtil;

public class YunScanCodeEvent {
	
	private static Logger logger = LoggerFactory.getLogger(YunScanCodeEvent.class);

	public static void receiveJson(String jsonStr) {
		YunEventSever server = SpringUtil.getBean(YunEventSever.class);
		try {
			String method = JsonUtil.getMethodByJsonStr(jsonStr);
			switch (method) {
			case "park/scanqrcode/scancodesinout": // 扫码出入
				server.scanCodesInOut(jsonStr);
				break;
			case "park/getcaradmissioninfo":// 获取预缴费信息
				server.getCentrilChargeData(jsonStr);
				break;
			case "park/userpaymentcarfee":// 下发缴费信息到线下
				server.writeChargeData(jsonStr);
				break;
			case "user/monthlyrentaccount":// 月租手机端开户
			case "user/monthlycarfee":// 线上获取月租费用信息
			case "user/monthlycarpayment":// 线上续费下发
				server.onlineRenewal(jsonStr);
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("error", e);
		}
		finally{
			server = null;
		}
	}
}

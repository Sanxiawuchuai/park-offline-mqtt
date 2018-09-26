package com.drzk.online.impl;

import com.drzk.online.service.YunEventSever;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 扫码出入场、云端缴费处理
 * @author yxf
 *
 */
@Component
@Scope("prototype")
public class YunScanCodeTask implements Runnable
{
	private static Logger logger = Logger.getLogger("userLog");	
	private String json;
	
	@Autowired
	private YunEventSever yunEventSever;
	 
	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public void run() {
		String method = JsonUtil.getMethodByJsonStr(json);
		switch (method) {
		case "park/scanqrcode/scancodesinout": //扫码出入
			method = "scanCodesInOut";
			break;
		case "park/getcaradmissioninfo"://获取预缴费信息
			method = "getCentrilChargeData";
			break;
		case "park/userpaymentcarfee"://下发缴费信息到线下
			method = "writeChargeData";
			break;
		case "user/monthlyrentaccount"://月租手机端开户
		case "user/monthlycarfee"://线上获取月租费用信息
		case "user/monthlycarpayment"://线上续费下发
			method = "onlineRenewal";
			break;
		}
		Class<YunEventSever> clazz = YunEventSever.class;
		try {
			Method classMethod = clazz.getMethod(method, String.class);			
			classMethod.invoke(yunEventSever, json);
		} catch (Exception e) {
			LoggerUntils.error(logger, e);
		}
	}
}

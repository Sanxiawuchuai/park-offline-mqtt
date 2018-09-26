package com.drzk.online.impl;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.drzk.timer.TestTask;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
import com.drzk.utils.SpringUtil;

public class YunScanCodeEvent
{

	private static Logger logger = Logger.getLogger("userLog");
	
	public static void receiveJson(String jsonStr) {
		try {  
			String method = JsonUtil.getMethodByJsonStr(jsonStr);
			ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
			switch (method) {
			case "park/scanqrcode/scancodesinout": //扫码出入
			case "park/getcaradmissioninfo"://获取预缴费信息
			case "park/userpaymentcarfee"://下发缴费信息到线下
			case "user/monthlyrentaccount"://月租手机端开户
			case "user/monthlycarfee"://线上获取月租费用信息
			case "user/monthlycarpayment"://线上续费下发
				YunScanCodeTask yunTask = SpringUtil.getBean(YunScanCodeTask.class);
				yunTask.setJson(jsonStr);
				threadPool.execute(yunTask);
				break;
			default:
				TestTask testtask = SpringUtil.getBean(TestTask.class);
				threadPool.execute(testtask);
				break;
			}
		}catch (Exception e) {
			LoggerUntils.error(logger, e);
		}
	}
}

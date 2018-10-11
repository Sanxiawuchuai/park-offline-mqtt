package com.drzk.online.impl;

import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import com.drzk.online.service.OnlineDSReplaySever;
import com.drzk.timer.TestTask;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
import com.drzk.utils.SpringUtil;



public class DSReplayEventImpl
{
	private static Logger logger = Logger.getLogger("userLog");
	public  static void receiveJson(String jsonStr) {
		try {  
			String method = JsonUtil.getMethodByJsonStr(jsonStr);
			ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
			switch (method) {
			case "park/parkcarin/v1"://同步入场记录
			case "park/parkcarout/v1"://出场纪录
			case "park/boxinfo/v1":
			case "park/controllerinfo/v1":
			case "park/parkcameras/v1":
			case "park/parkblackcar/v1":
			case "park/parkcaruser/v1":
			case "park/couponeqinfo/v1":
			case "park/couponrule/v1":
			case "park/couponuserlist/v1":
			case "park/parkcaroperation/v1":
			case "park/parkunusualcarin/v1":
			case "park/parksumusers/v1":
			case "park/parkcenterpayment/v1":
			case "park/parkmonthlyfeetype/v1":
			case "park/parkcompertask/v1":
			case "park/parkdeletecarin/v1":
			case "park/companyinfo/v1":
			case "park/departmentinfo/v1":
			case "park/parkopengateillegally/v1":
			case "park/personnelinfo/v1":
			case "park/parksetting/v1":
			case "park/feescale/v1":
			case "park/parkcargroup/v1":
			case "park/computer/v1":
			    OnlineDSReplaySever onlineDSReplaySever = SpringUtil.getBean(OnlineDSReplaySever.class);
				threadPool.execute(new Runnable()
				{
					
					@Override
					public void run()
					{
						onlineDSReplaySever.OnlineDSReplay(jsonStr);
						
					}
				});
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

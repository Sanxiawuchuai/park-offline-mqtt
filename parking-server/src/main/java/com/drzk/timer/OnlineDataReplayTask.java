package com.drzk.timer;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.drzk.online.service.OnlineDSReplaySever;
import com.drzk.online.service.OnlineDSScanSever;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;

@Component
@Scope("prototype")
public class OnlineDataReplayTask implements Runnable
{

	private static Logger logger = Logger.getLogger("userLog");
	
	private String json;
	public void setJson(String json) {
		this.json = json;
	}
	
	@Autowired
	private OnlineDSReplaySever onlineDSReplaySever;
	
	@Override
	public void run()
	{
		String method = JsonUtil.getMethodByJsonStr(json);
		Class<OnlineDSScanSever> clazz = OnlineDSScanSever.class;
		try {
//			Method classMethod = clazz.getMethod(method, String.class);
//			
//			classMethod.getName().replace("park/", "");
//			classMethod.getName().replace("/v1", "");
//			classMethod.invoke(onlineDSReplaySever, json);
			
		} catch (Exception ex) {
			logger.error("OnlineDataReplayTask:",ex);
		}

	}

}

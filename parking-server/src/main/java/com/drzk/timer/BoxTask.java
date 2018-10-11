
package com.drzk.timer;

import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.drzk.offline.service.IBoxOperation;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;

/**
 * 岗亭处理 <br>
 * Date: 2018年8月2日 上午11:21:20 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */

@Component
@Scope("prototype")
public class BoxTask implements Runnable {
	private static Logger logger = Logger.getLogger("userLog");
	
	private String json;

	@Autowired
	private IBoxOperation boxOperation;

	/**
	 * json.
	 *
	 * @param json the json to set
	 * @since JDK 1.8
	 */
	public void setJson(String json) {
		this.json = json;
	}

	@Override
	public void run() {
		String method = JsonUtil.getMethodByJsonStr(json);
		Class<IBoxOperation> clazz = IBoxOperation.class;
		try {
			Method classMethod = clazz.getMethod(method, String.class);
			classMethod.invoke(boxOperation, json);
		} catch (Exception ex) {
			logger.error("岗亭通讯线程:", ex);
		}
	}
}

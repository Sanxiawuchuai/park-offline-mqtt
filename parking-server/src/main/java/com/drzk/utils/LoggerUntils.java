
package com.drzk.utils;

import org.apache.log4j.Logger;

/**
 * 封装日志打印<br>
 * 根据数据库中sys_parameters表IS_NEED_LOG的值决定是否打印<br>
 * Date: 2018年5月25日 下午3:50:35 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class LoggerUntils{
	
	private static String isNeedLog = GlobalPark.properties.getProperty("IS_NEED_LOG","1");
	  
	public static void debug(Logger logger,Object message) {
		if("1".equals(isNeedLog)) {
			logger.debug(message);
		}
	}
	
	public static void error(Logger logger,Object message) {
		if (message instanceof Exception) {
			Exception e = (Exception) message;
		   logger.error(message);
		} else {
			logger.error(message);
		}

	}
	
	public static void info(Logger logger,Object message) {
		if("1".equals(isNeedLog)) {
			logger.info(message);
		}
	}
	
	public static void trace(Logger logger,Object message) {
		if("1".equals(isNeedLog)) {
			logger.trace(message);
		}
	}
	
	public static void fatal(Logger logger,Object message) {
		if("1".equals(isNeedLog)) {
			logger.fatal(message);
		}
	}
	
	public static void warn(Logger logger,Object message) {
		if("1".equals(isNeedLog)) {
			logger.warn(message);
		}
	}
	
	
}

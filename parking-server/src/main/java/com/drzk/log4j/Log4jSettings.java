package com.drzk.log4j;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.NDC;

/**
 * log4j 日志级别修改
 * 
 * @author huanglf
 * @since 2018-09-13
 *
 */
public class Log4jSettings {
	/**
	 * 传入INFO、DEBUG、ERROR，FATAL作为参数调整系统日志级别 实时修改
	 * 
	 * @param targetLevel
	 */
	public static void changeLogLevel(String targetLevel) {
		Level level = Level.toLevel(targetLevel);
		LogManager.getRootLogger().setLevel(level);
		NDC.clear();
	}

}

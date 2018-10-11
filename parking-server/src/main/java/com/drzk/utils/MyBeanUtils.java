package com.drzk.utils;

import java.lang.reflect.Field;
import java.util.Date;

import org.apache.log4j.Logger;

import com.drzk.service.entity.ParkStandardChargeBody;
import com.drzk.vo.ParkStandardCharge;

/**
 * 自己定制的对象操作工具类
 * @author chenlong
 * 2018年9月12日
 */
public class MyBeanUtils {
	private static Logger logger = Logger.getLogger("userLog");
	/**
	 * 此方法用于在给硬件加载收费标准时，协议定义的字段是String，数据库是int或者double
	 * 用此方法将数据库的对象转换成协议的对象
	 * @author chenlong
	 * 2018年9月12日
	 */
	public static void copyProperties(ParkStandardCharge source, ParkStandardChargeBody target) {
		try {
			target.setCardType(source.getCardType().toString());// 卡类型
			if (source.getFreeTime() == null) { // 免费分钟数
				target.setFreeTime("0");
			} else {
				target.setFreeTime(source.getFreeTime().toString());
			}
			if(source.getIsFreeTime()==null){//是否包含免费时间 0不包含 1包含
				target.setIsFreeTime("0");
			}else{
				target.setIsFreeTime(source.getIsFreeTime().toString());
			}
			if(source.getUnitType()==null){//是否收费有小数 0无小数 1有小数
				target.setUnitType("0");
			}else{
				target.setUnitType(source.getUnitType().toString());
			}
			if(source.getTopMoney()==null){//最高收费
				
			}else{
				
			}
		} catch (Exception ex) {
			logger.error("车场服务端启动失败:" + ex);
		}
	}
	
	public static void main(String[] args) {
		ParkStandardCharge source = new ParkStandardCharge();
		ParkStandardChargeBody target = new ParkStandardChargeBody();
		copyProperties(source, target);
	}
	
}

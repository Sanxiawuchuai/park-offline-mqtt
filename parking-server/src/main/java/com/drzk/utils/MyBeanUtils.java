package com.drzk.utils;

import java.lang.reflect.Field;
import java.util.Date;

import com.drzk.service.entity.ParkStandardChargeBody;
import com.drzk.vo.ParkStandardCharge;

/**
 * 自己定制的对象操作工具类
 * @author chenlong
 * 2018年9月12日
 */
public class MyBeanUtils {
	
	/**
	 * 此方法用于在给硬件加载收费标准时，协议定义的字段是String，数据库是int或者double
	 * 用此方法将数据库的对象转换成协议的对象
	 * @author chenlong
	 * 2018年9月12日
	 */
	public static void copyProperties(ParkStandardCharge source, ParkStandardChargeBody target) {
		Field[] targetField = target.getClass().getDeclaredFields();
		for (Field field : targetField) {
			String fieldName = field.getName();
			try {
				Field sourceFiled =  ParkStandardCharge.class.getDeclaredField(fieldName);
				sourceFiled.setAccessible(true);
				Object value = sourceFiled.get(source);
				field.setAccessible(true);
				if("java.util.Date".equals(field.getType().getName())) {
					field.set(target, value);
				}else {
					field.set(target, value.toString());
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
			
		}
	}
	
	public static void main(String[] args) {
		ParkStandardCharge source = new ParkStandardCharge();
		ParkStandardChargeBody target = new ParkStandardChargeBody();
		copyProperties(source, target);
	}
	
}

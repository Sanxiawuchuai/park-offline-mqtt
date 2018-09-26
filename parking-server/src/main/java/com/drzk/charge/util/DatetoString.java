package com.drzk.charge.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DatetoString {
	/**
	   * 获取现在时间
	   * 
	   * @return 返回时间类型 yyyy-MM-dd HH:mm:ss
	   */
	public  Date getNowDate() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);
	   ParsePosition pos = new ParsePosition(8);
	   Date currentTime_2 = formatter.parse(dateString, pos);
	   return currentTime_2;
	}


/**
   * 获取现在时间
   * 
   * @return返回字符串格式 yyyy-MM-dd HH:mm:ss
   */
	public  String getStringDate() {
	   Date currentTime = new Date();
	   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   String dateString = formatter.format(currentTime);
	   return dateString;
	}
/**
   * 获取现在时间
   * 
   * @return 返回短时间字符串格式yyyy-MM-dd
   */
public  String getStringDateShort() {
   Date currentTime = new Date();
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
   String dateString = formatter.format(currentTime);
   return dateString;
}


    public  java.sql.Date Str2Date(String str){
        return java.sql.Date.valueOf(str);
    }

/**
   * 得到现在时间
   * 
   * @return
   */
public  Date getNow() {
   Date currentTime = new Date();
   return currentTime;
}
/**
   * 提取一个月中的最后一天
   * 
   * @param day
   * @return
   */
public  Date getLastDate(long day) {
   Date date = new Date();
   long date_3_hm = date.getTime() - 3600000 * 34 * day;
   Date date_3_hm_date = new Date(date_3_hm);
   return date_3_hm_date;
}
/**
   * 得到现在时间
   * 
   * @return 字符串 yyyyMMdd HHmmss
   */
public  String getStringToday() {
   Date currentTime = new Date();
   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd HHmmss");
   String dateString = formatter.format(currentTime);
   return dateString;
}
/**
   * 得到现在小时
   */
public  String getHour() {
   Date currentTime = new Date();
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String dateString = formatter.format(currentTime);
   String hour;
   hour = dateString.substring(11, 13);
   return hour;
}
/**
   * 得到现在分钟
   * 
   * @return
   */
public  String getTime() {
   Date currentTime = new Date();
   SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
   String dateString = formatter.format(currentTime);
   String min;
   min = dateString.substring(14, 16);
   return min;
}
/**
   * 根据用户传入的时间表示格式，返回当前时间的格式 如果是yyyyMMdd，注意字母y不能大写。
   * 
   * @param sformat
   *             yyyyMMddhhmmss
   * @return
   */
public  String getUserDate(String sformat) {
   Date currentTime = new Date();
   SimpleDateFormat formatter = new SimpleDateFormat(sformat);
   String dateString = formatter.format(currentTime);
   return dateString;
}




 /**
  * 获取时间 小时:分;秒 HH:mm:ss
  * 
  * @return
  */
 public  String getTimeShort() {
  SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
  Date currentTime = new Date();
  String dateString = formatter.format(currentTime);
  return dateString;
 }

 /**
  * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
  * 
  * @param strDate
  * @return
  */
 public  Date strToDateLong(String strDate) {
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  ParsePosition pos = new ParsePosition(0);
  Date strtodate = formatter.parse(strDate, pos);
  return strtodate;
 }

 /**
  * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
  * 
  * @param dateDate
  * @return
  */
 public  String dateToStrLong(Date dateDate) {
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
  String dateString = formatter.format(dateDate);
  return dateString;
 }

 /**
  * 将短时间格式时间转换为字符串 yyyy-MM-dd
  * 
  * @param dateDate
  * @param k
  * @return
  */
 public  String dateToStr(Date dateDate) {
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  String dateString = formatter.format(dateDate);
  return dateString;
 }

 /**
  * 将短时间格式字符串转换为时间 yyyy-MM-dd 
  * 
  * @param strDate
  * @return
  */
 public  Date strToDate(String strDate) {
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
  ParsePosition pos = new ParsePosition(0);
  Date strtodate = formatter.parse(strDate, pos);
  return strtodate;
 }
    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public  Date strToDateShort(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
//sql.date 格式转 Util.date
	public  Date SqlDateToUtilDate(java.sql.Date date) {
		Date utilDate = new Date();
		try {
			SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");// 小写的mm表示的是分钟
			String dst = date.toString();
			utilDate = sd.parse(dst);
		} catch (Exception ex) {

		}
		return utilDate;
	}


}

 
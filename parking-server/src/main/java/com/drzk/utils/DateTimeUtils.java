
package com.drzk.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.joda.time.DateTime;

/**
 * ClassName:DateTimeUtils <br>
 * Date: 2018年6月19日 下午5:38:41 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class DateTimeUtils {

	/**
	 * 
	 * 两个日期是否是同一天<br>
	 *
	 * @author chenlong
	 * @param start
	 * @param end
	 * @return
	 * @since JDK 1.8
	 */
	public static boolean isTheSameDay(DateTime start, DateTime end) {
		DateTime s = start.millisOfDay().withMaximumValue();
		DateTime e = end.millisOfDay().withMaximumValue();
		return s.isEqual(e);
	}

	/**
	 * 
	 * 判断某个时间是否在[start, end]区间<br>
	 * 先将传入的Date转换成LocalTime(只判断 时分秒) <br>
	 * eg.<br>
	 * 判断 12:25:12 在 [ 8:00:00,18:59:59] 区间 ,返回true
	 * 
	 * @author chenlong
	 * @param now
	 * @param start
	 * @param end
	 * @return
	 * @since JDK 1.8
	 */
	public static boolean isEffectiveDate(Date now, Date start, Date end) {
		LocalDateTime localDateTime = LocalDateTime.ofInstant(now.toInstant(), ZoneId.systemDefault());
		LocalTime nowTime = localDateTime.toLocalTime();
		localDateTime = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
		LocalTime startTime = localDateTime.toLocalTime();

		localDateTime = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault());
		LocalTime endTime = localDateTime.toLocalTime();
		if (nowTime.equals(startTime) || nowTime.equals(endTime)) {
			return true;
		}
		return nowTime.isAfter(startTime) && nowTime.isBefore(endTime);
	}

	/**
	 * 计算两个时间相差的天数。<br>
	 * 比如 2018-9-3 5:00:00 2018-9-5 23:59:59 3天(不满一天按一天算)
	 * 
	 * @author chenlong 2018年9月4日
	 */
	public static int differDay(Date start, Date end) {

		long s = start.getTime();
		long e = end.getTime();

		long diff = e - s;

		int day = (int) (diff / (24 * 60 * 60 * 1000));
		int temp = (int) (diff % (24 * 60 * 60 * 1000));

		return temp > 0 ? day + 1 : day;
	}

	public static int differScenod(Date start, Date endDate) {
		long s = start.getTime();
		long e = endDate.getTime();
		long diff = e - s;
		return (int) diff / 1000;

	}

	/**
	 * 计算两个日期的有效时间
	 * 
	 * @author chenlong 2018年9月4日
	 */
	public static int countEffectMinute(Date start, Date end, Date startTime, Date endTime) {
		LocalTime sDay = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()).toLocalTime();
		LocalTime eDay = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault()).toLocalTime();

		LocalTime sTime = LocalDateTime.ofInstant(startTime.toInstant(), ZoneId.systemDefault()).toLocalTime();
		LocalTime eTime = LocalDateTime.ofInstant(endTime.toInstant(), ZoneId.systemDefault()).toLocalTime();

		// 求时间区间的分钟数，先算两个时间是否同一天
		// 开始时间在结束时间之前，表示时段没跨天
		int dayMinute = 0; // 一个完整自然日的在区间的分钟数
		if (sTime.isBefore(eTime)) {
			dayMinute = (int) ChronoUnit.MINUTES.between(sTime, eTime);
		} else if (sTime.isAfter(eTime)) {
			LocalTime temp = LocalTime.parse("23:59:59");
			dayMinute = (int) ChronoUnit.MINUTES.between(sTime, temp);
			temp = LocalTime.parse("00:00:00");
			dayMinute = dayMinute + (int) ChronoUnit.MINUTES.between(temp, eTime);
		}

		// 计算相差的自然天
		int day = getBetweenDate(start, end);

		int minute = 0; // 用来保存整个时间区间的分钟数
		// 先判断两个日期是不是同一天
		if (day == 1) {
			if (sTime.isBefore(eTime)) { // 时段不跨天
				PeriodTime periodTime = getPeriod(sDay, eDay, sTime, eTime);
				if (periodTime != null) {
					minute = (int) ChronoUnit.MINUTES.between(periodTime.getStartTime(), periodTime.getEndTim());
				}
			} else if (sTime.isAfter(eTime)) {
				LocalTime temp = LocalTime.parse("23:59:59");
				PeriodTime periodTime = getPeriod(sDay, eDay, sTime, temp);
				if (periodTime != null) {
					minute = (int) ChronoUnit.MINUTES.between(periodTime.getStartTime(), periodTime.getEndTim());
				}
				temp = LocalTime.parse("00:00:00");
				periodTime = getPeriod(sDay, eDay, temp, eTime);
				if (periodTime != null) {
					minute = minute
							+ (int) ChronoUnit.MINUTES.between(periodTime.getStartTime(), periodTime.getEndTim());
				}
			}

		} else if (day >= 2) {
			// 先求首尾两天的
			if (sTime.isBefore(eTime)) { // 时段不跨天
				LocalTime temp = LocalTime.parse("23:59:59");
				PeriodTime periodTime = getPeriod(sDay, temp, sTime, eTime);
				if (periodTime != null) {
					minute = (int) ChronoUnit.MINUTES.between(periodTime.getStartTime(), periodTime.getEndTim());
				}
				temp = LocalTime.parse("00:00:00");
				periodTime = getPeriod(temp, eDay, sTime, eTime);
				if (periodTime != null) {
					minute = minute
							+ (int) ChronoUnit.MINUTES.between(periodTime.getStartTime(), periodTime.getEndTim());
				}
			} else if (sTime.isAfter(eTime)) {
				LocalTime eTemp = LocalTime.parse("23:59:59");
				LocalTime sTemp = LocalTime.parse("00:00:00");

				// 第一天
				PeriodTime periodTime = getPeriod(sDay, eTemp, sTime, eTemp);
				if (periodTime != null) {
					minute = (int) ChronoUnit.MINUTES.between(periodTime.getStartTime(), periodTime.getEndTim());
				}
				periodTime = getPeriod(sDay, eTemp, sTemp, eTime);
				if (periodTime != null) {
					minute = minute
							+ (int) ChronoUnit.MINUTES.between(periodTime.getStartTime(), periodTime.getEndTim());
				}

				// 最后一天
				periodTime = getPeriod(sTemp, eDay, sTemp, eTime);
				if (periodTime != null) {
					minute = minute
							+ (int) ChronoUnit.MINUTES.between(periodTime.getStartTime(), periodTime.getEndTim());
				}

				periodTime = getPeriod(sTemp, eDay, sTime, eTemp);
				if (periodTime != null) {
					minute = minute
							+ (int) ChronoUnit.MINUTES.between(periodTime.getStartTime(), periodTime.getEndTim());
				}
			}

			minute = minute + (day - 2) * dayMinute;
		}

		return minute;
	}

	/**
	 * 计算两个日期相差的自然天
	 * 
	 * @author chenlong 2018年9月4日
	 */
	public static int getBetweenDate(Date start, Date end) {
		// List<String> list = new ArrayList<>();

		LocalDate startDate = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault()).toLocalDate();
		LocalDate endDate = LocalDateTime.ofInstant(end.toInstant(), ZoneId.systemDefault()).toLocalDate();

		int distance = (int) ChronoUnit.DAYS.between(startDate, endDate);
//        Stream.iterate(startDate, d -> {  
//            return d.plusDays(1);  
//        }).limit(distance + 1).forEach(f -> {  
//            list.add(f.toString());  
//        });  
		return distance + 1;
	}

	public static PeriodTime getPeriod(LocalTime s1, LocalTime e1, LocalTime s2, LocalTime e2) {
		PeriodTime periodTime = new PeriodTime();
		if (!e1.isAfter(s2) || !s1.isBefore(e2)) {
			return null;
		}
		LocalTime s = s1.isAfter(s2) ? s1 : s2;
		LocalTime e = e1.isBefore(e2) ? e1 : e2;

		periodTime.setStartTime(s);
		periodTime.setEndTim(e);
		return periodTime;
	}
	/**
	 * 计算两个时间的时间差
	 * 
	 * @author chenlong 2018年9月4日
	 */
	public static int getPeriodMinute(Date stratTime, Date endTime) {
		
		if(endTime == null) endTime=new Date();
		int minute = (int) ((endTime.getTime() - stratTime.getTime()) / (60 * 1000));
		return minute;
	}
	/**
	 * 在一个时间加上分钟数
	 * 
	 * @author chenlong 2018年9月4日
	 */
	public static Date dateAddMinute(Date start, int minute) {

		LocalDateTime localDateTime = LocalDateTime.ofInstant(start.toInstant(), ZoneId.systemDefault());
		localDateTime.plusMinutes(minute);

		ZoneId zoneId = ZoneId.systemDefault();
		ZonedDateTime zdt = localDateTime.atZone(zoneId);

		Date date = Date.from(zdt.toInstant());

		return date;

	}

	/**
	 * 字符串格式化为日期
	 * 默认yyyy-MM-dd HH:mm:ss 格式日期
	 * @param date
	 * @param format
	 * @return
	 * Date
	 * 2018年9月12日
	 */
	public static Date parseDate(String date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	try {
			return df.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
        return null;
	}
	
	/**
	 * 格式化日期为
	 * yyyy-MM-dd HH:mm:ss 格式字符串
	 * @param date
	 * @return
	 * String
	 * 2018年9月14日
	 */
	public static String formatDate(Date date) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}
	
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date s1 = df.parse("2018-9-9 9:20:20");

		Date e1 = df.parse("2018-9-11 20:59:59");
		Date s2 = df.parse("2018-9-9 22:00:00");
		Date e2 = df.parse("2018-9-9 8:59:59");
		// System.out.println(countEffectMinute(s1, e1, s2, e2));
		// int day = getBetweenDate(s,e);
		// System.out.println(day);
	}
}

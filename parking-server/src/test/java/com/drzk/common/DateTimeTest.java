package com.drzk.common;

import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * ClassName:DateTimeTest <br>
 * Date: 2018年6月15日 上午9:40:41 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class DateTimeTest {
	public static void main(String[] args) {
		DateTime start = new DateTime(2018,6,15,9,30,10,0);
		Date s = start.toDate();
		DateTime end   = new DateTime(2018,6,16,9,20,10,0);
		Period p = new Period(start, end,PeriodType.days()); 
		int year = p.getYears();
		int month = p.getMonths();
		int day = p.getDays();
		int hour = p.getHours();
		int minute = p.getMinutes();
		int second = p.getSeconds();
		
		System.out.println(year);
		System.out.println(month);
		System.out.println(day);
		System.out.println(hour);
		System.out.println(minute);
		System.out.println(second);
		
		int d = Days.daysBetween(start, end).getDays();
		System.out.println(d);
		//DateTimeFormatter fmatter = DateTimeFormat.forPattern("MM/dd/yyyy hh:mm:ss");
		//System.out.println(start.secondOfDay().withMaximumValue().toString(fmatter));
		
	}
}

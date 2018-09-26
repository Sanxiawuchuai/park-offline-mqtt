
package com.drzk.app;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * ClassName:JodaTimeTest <br>
 * Date: 2018年5月29日 下午1:45:43 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class JodaTimeTest {
	public static void main(String[] args) {
		long t1 = new Date().getTime();
		DateTime now = new DateTime();
		DateTime then = now.minusYears(3) // five years ago
				.monthOfYear() // get monthOfYear property
				.setCopy(2) // set it to February
				.dayOfMonth() // get dayOfMonth property
				.withMaximumValue();// the last day of the month
		System.out.println(then.toDate());
		long t2 = new Date().getTime();
		System.out.println(t2-t1);
	}

}

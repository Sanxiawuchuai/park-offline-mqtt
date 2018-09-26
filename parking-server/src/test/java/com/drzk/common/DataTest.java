
package com.drzk.common;

import java.math.BigDecimal;

import org.jbpm.process.instance.impl.demo.SystemOutWorkItemHandler;

/**
 * ClassName:DataTest <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年8月30日 上午11:56:24 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class DataTest {
	
	public static void main(String[] args) {
		float a = 12.22f;
		BigDecimal a1 = new BigDecimal(a);
		
		float b = 1.22f;
		BigDecimal b1 = new BigDecimal(b);
		
		BigDecimal result = a1.add(b1);
		float c= a+b;
		float d = 13.44f;
		System.out.println("12.22+1.22=13.44?" + (c==d));
		
		//System.out.println();
		
		BigDecimal d1 = new BigDecimal(d);
		System.out.println("d1 reslut:" + d1.compareTo(result));
		System.out.println(d1.setScale(2,BigDecimal.ROUND_HALF_DOWN).compareTo(result.setScale(2,BigDecimal.ROUND_HALF_DOWN)));
		
		//System.out.println(c);
		//System.out.println(result.setScale(2,BigDecimal.ROUND_HALF_DOWN));
		
	}

}

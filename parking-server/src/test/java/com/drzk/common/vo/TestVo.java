
package com.drzk.common.vo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import com.drzk.common.InOutRealTimeBase.Step;

/**
 * ClassName:TestVo <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年7月14日 下午4:44:48 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class TestVo {
	private String name;
	private String id;
	private int day;
	private Step nextStep;

	/**
	 * id.
	 *
	 * @return the id
	 * @since JDK 1.8
	 */
	public String getId() {
		return id;
	}

	/**
	 * id.
	 *
	 * @param id the id to set
	 * @since JDK 1.8
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * day.
	 *
	 * @return the day
	 * @since JDK 1.8
	 */
	public int getDay() {
		return day;
	}

	/**
	 * day.
	 *
	 * @param day the day to set
	 * @since JDK 1.8
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * name.
	 *
	 * @return the name
	 * @since JDK 1.8
	 */
	public String getName() {
		return name;
	}

	/**
	 * name.
	 *
	 * @param name the name to set
	 * @since JDK 1.8
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * nextStep.
	 *
	 * @return the nextStep
	 * @since JDK 1.8
	 */
	public Step getNextStep() {
		return nextStep;
	}

	/**
	 * nextStep.
	 *
	 * @param nextStep the nextStep to set
	 * @since JDK 1.8
	 */
	public void setNextStep(Step nextStep) {
		this.nextStep = nextStep;
	}

	@Override
	public int hashCode() {
		int hashcode = this.getId().hashCode();
		System.out.println(hashcode);
		return hashcode;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TestVo ) {
			String id = ((TestVo)obj).getId();
			return id.equals(this.getId());
		}else {
			return false;
		}
	}

	public static void main(String[] args) {
		Set<TestVo> test = new HashSet<>();
		TestVo t1 = new TestVo();
		t1.setId("1");
		t1.setName("s1");
		test.add(t1);
		//t1.hashCode();
		TestVo t2 = new TestVo();
		t2.setId("1");
		t2.setName("s2");
		
//		if(test.contains(t2)) {
//			test.remove(t2);
//		}
		test.add(t2);
		//System.out.println(test.contains(t2));
		//t2.hashCode();
		
		//System.out.println(t1.equals(t2));
		System.out.println(test.size());
		
	}

}

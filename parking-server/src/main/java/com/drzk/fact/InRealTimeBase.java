package com.drzk.fact;

import com.drzk.vo.ParkCarIn;

/**
 * ClassName:IParkingInService <br>
 * Date: 2018年5月17日 下午2:26:58 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */

public class InRealTimeBase extends AbsRealTimeBase{ 
    
	private ParkCarIn in; //将要保存到数据库的实体

	/**
	* in.
	*
	* @return the in
	* @since JDK 1.8
	*/
	public ParkCarIn getIn() {
		return in;
	}
	
	/**
	* in.
	*
	* @param in the in to set
	* @since JDK 1.8
	*/
	public void setIn(ParkCarIn in) {
		this.in = in;
	}
	
}
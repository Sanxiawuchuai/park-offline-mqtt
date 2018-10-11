
package com.drzk.service.entity;

import java.io.Serializable;

/**
 * 删除脱机记录返回 <br>
 * Date: 2018年7月31日 下午6:01:01 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class DeleteParkRecordBodyReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5637640717356992378L;
    private String uId;
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	
}

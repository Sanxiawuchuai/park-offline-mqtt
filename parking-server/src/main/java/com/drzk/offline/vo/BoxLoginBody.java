
package com.drzk.offline.vo;

import java.io.Serializable;

/**
 * 岗亭登录 <br>
 * Date: 2018年7月30日 上午9:23:13 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class BoxLoginBody implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5825752634032499312L;
	private String uId;
	private String boxIP; // 岗亭Ip
	private String equipmentMac; // 设备编号
	private String loginType; // 登入类型 0-用户，1，扫码
	private String userName; // 用户名
	private String userPwd; // 用户密码
	private String qRCode; // 二维码
	
	/**
	* boxIP.
	*
	* @return the boxIP
	* @since JDK 1.8
	*/
	public String getBoxIP() {
		return boxIP;
	}
	
	/**
	* boxIP.
	*
	* @param boxIP the boxIP to set
	* @since JDK 1.8
	*/
	public void setBoxIP(String boxIP) {
		this.boxIP = boxIP;
	}
	
	/**
	* equipmentMac.
	*
	* @return the equipmentMac
	* @since JDK 1.8
	*/
	public String getEquipmentMac() {
		return equipmentMac;
	}
	
	/**
	* equipmentMac.
	*
	* @param equipmentMac the equipmentMac to set
	* @since JDK 1.8
	*/
	public void setEquipmentMac(String equipmentMac) {
		this.equipmentMac = equipmentMac;
	}
	
	/**
	* loginType.
	*
	* @return the loginType
	* @since JDK 1.8
	*/
	public String getLoginType() {
		return loginType;
	}
	
	/**
	* loginType.
	*
	* @param loginType the loginType to set
	* @since JDK 1.8
	*/
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	
	/**
	* userName.
	*
	* @return the userName
	* @since JDK 1.8
	*/
	public String getUserName() {
		return userName;
	}
	
	/**
	* userName.
	*
	* @param userName the userName to set
	* @since JDK 1.8
	*/
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	* userPwd.
	*
	* @return the userPwd
	* @since JDK 1.8
	*/
	public String getUserPwd() {
		return userPwd;
	}
	
	/**
	* userPwd.
	*
	* @param userPwd the userPwd to set
	* @since JDK 1.8
	*/
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	/**
	* qRCode.
	*
	* @return the qRCode
	* @since JDK 1.8
	*/
	public String getqRCode() {
		return qRCode;
	}
	
	/**
	* qRCode.
	*
	* @param qRCode the qRCode to set
	* @since JDK 1.8
	*/
	public void setqRCode(String qRCode) {
		this.qRCode = qRCode;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}
	
	
	
}

package com.drzk.offline.vo;

import java.io.Serializable;

public class BoxChangeshiftsBody implements Serializable
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5097019325519861095L;
	private String uId;
	private String boxIP; // 岗亭Ip
	private String equipmentMac; // 设备编号
	private String loginType; // 登入类型 0-用户，1，扫码
	
	private String oldUserName;//原用户名
	private String oldUserPwd;//原密码
	
	private String newUserName;//新用户名
	private String newUserPwd;//新密码
	
	private String oldQRCode;//原二维码
	private String newQRCode;//新二维码
	
	
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
	
	
	public void setOldUserName(String oldUserName)
	{
		this.oldUserName = oldUserName;
	}
	public String GetOldUserName()
	{
		return oldUserName;
	}
	
	public void setOldUserPwd(String oldUserPwd)
	{
		this.oldUserPwd = oldUserPwd;
	}
	public String GetOldUserPwd()
	{
		return oldUserPwd;
	}
	
	public void setNewUserName(String newUserName)
	{
		this.newUserName = newUserName;
	}
	public String GetNewUserName()
	{
		return newUserName;
	}
	
	public void setNewUserPwd(String newUserPwd)
	{
		this.newUserPwd = newUserPwd;
	}
	public String GetNewUserPwd()
	{
		return newUserPwd;
	}
	
	/**
	* qRCode.
	*
	* @return the qRCode
	* @since JDK 1.8
	*/
	public String getoldQRCode() {
		return oldQRCode;
	}
	
	/**
	* qRCode.
	*
	* @param qRCode the qRCode to set
	* @since JDK 1.8
	*/
	public void setoldQRCode(String oldQRCode) {
		this.oldQRCode = oldQRCode;
	}
	
	/**
	* qRCode.
	*
	* @return the qRCode
	* @since JDK 1.8
	*/
	public String getnewQRCode() {
		return newQRCode;
	}
	
	/**
	* qRCode.
	*
	* @param qRCode the qRCode to set
	* @since JDK 1.8
	*/
	public void setnewQRCode(String newQRCode) {
		this.newQRCode = newQRCode;
	}

	public String getuId() {
		return uId;
	}

	public void setuId(String uId) {
		this.uId = uId;
	}
	
}

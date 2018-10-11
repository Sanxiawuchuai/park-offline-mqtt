
package com.drzk.offline.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import com.drzk.vo.ParkAccountType;
import com.drzk.vo.ParkCamSet;
import com.drzk.vo.ParkChannelSet;
import com.drzk.vo.ParkDisInfo;
import com.drzk.vo.ParkEquipments;
import com.drzk.vo.ParkFreeType;
import com.drzk.vo.ParkLocalSet;

/**
 * 岗亭登录回复 <br>
 * Date: 2018年7月30日 上午10:13:05 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class BoxLoginReturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2663103502999716737L;
	private String uId; // 记录唯一编号,32位字符串
	private Date loginTime; // 登入时间
	private Properties parkSysSet; // 参照车场参数设置实体类
	private ParkLocalSet parkLocalSet; // ParkLocalSet参照车场岗亭设置实体类
	private List<ParkChannelSet> parkContset; // 参照控制器实体
	private List<ParkCamSet> parkCamSet; // 参照相机实体
	private List<ParkAccountType> parkAccountType; // 参照车类型实体
	private List<ParkFreeType> parkFreeType; // 参照免费实体
	private List<ParkEquipments> parkEquipments; // 参照商家实体
	private List<ParkDisInfo> parkDisInfo; // 参照打折实体
	
	/**
	* uId.
	*
	* @return the uId
	* @since JDK 1.8
	*/
	public String getuId() {
		return uId;
	}
	
	/**
	* uId.
	*
	* @param uId the uId to set
	* @since JDK 1.8
	*/
	public void setuId(String uId) {
		this.uId = uId;
	}
	
	/**
	* loginTime.
	*
	* @return the loginTime
	* @since JDK 1.8
	*/
	public Date getLoginTime() {
		return loginTime;
	}
	
	/**
	* loginTime.
	*
	* @param loginTime the loginTime to set
	* @since JDK 1.8
	*/
	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	/**
	* parkSysSet.
	*
	* @return the parkSysSet
	* @since JDK 1.8
	*/
	public Properties getParkSysSet() {
		return parkSysSet;
	}
	
	/**
	* parkSysSet.
	*
	* @param parkSysSet the parkSysSet to set
	* @since JDK 1.8
	*/
	public void setParkSysSet(Properties parkSysSet) {
		this.parkSysSet = parkSysSet;
	}
	
	/**
	* parkLocalSet.
	*
	* @return the parkLocalSet
	* @since JDK 1.8
	*/
	public ParkLocalSet getParkLocalSet() {
		return parkLocalSet;
	}
	
	/**
	* parkLocalSet.
	*
	* @param parkLocalSet the parkLocalSet to set
	* @since JDK 1.8
	*/
	public void setParkLocalSet(ParkLocalSet parkLocalSet) {
		this.parkLocalSet = parkLocalSet;
	}
	
	/**
	* parkContset.
	*
	* @return the parkContset
	* @since JDK 1.8
	*/
	public List<ParkChannelSet> getParkContset() {
		return parkContset;
	}
	
	/**
	* parkContset.
	*
	* @param parkContset the parkContset to set
	* @since JDK 1.8
	*/
	public void setParkContset(List<ParkChannelSet> parkContset) {
		this.parkContset = parkContset;
	}
	
	/**
	* parkCamSet.
	*
	* @return the parkCamSet
	* @since JDK 1.8
	*/
	public List<ParkCamSet> getParkCamSet() {
		return parkCamSet;
	}
	
	/**
	* parkCamSet.
	*
	* @param parkCamSet the parkCamSet to set
	* @since JDK 1.8
	*/
	public void setParkCamSet(List<ParkCamSet> parkCamSet) {
		this.parkCamSet = parkCamSet;
	}
	
	/**
	* parkAccountType.
	*
	* @return the parkAccountType
	* @since JDK 1.8
	*/
	public List<ParkAccountType> getParkAccountType() {
		return parkAccountType;
	}
	
	/**
	* parkAccountType.
	*
	* @param parkAccountType the parkAccountType to set
	* @since JDK 1.8
	*/
	public void setParkAccountType(List<ParkAccountType> parkAccountType) {
		this.parkAccountType = parkAccountType;
	}
	
	/**
	* parkFreeType.
	*
	* @return the parkFreeType
	* @since JDK 1.8
	*/
	public List<ParkFreeType> getParkFreeType() {
		return parkFreeType;
	}
	
	/**
	* parkFreeType.
	*
	* @param parkFreeType the parkFreeType to set
	* @since JDK 1.8
	*/
	public void setParkFreeType(List<ParkFreeType> parkFreeType) {
		this.parkFreeType = parkFreeType;
	}
	
	/**
	* parkEquipments.
	*
	* @return the parkEquipments
	* @since JDK 1.8
	*/
	public List<ParkEquipments> getParkEquipments() {
		return parkEquipments;
	}
	
	/**
	* parkEquipments.
	*
	* @param parkEquipments the parkEquipments to set
	* @since JDK 1.8
	*/
	public void setParkEquipments(List<ParkEquipments> parkEquipments) {
		this.parkEquipments = parkEquipments;
	}
	
	/**
	* parkDisInfo.
	*
	* @return the parkDisInfo
	* @since JDK 1.8
	*/
	public List<ParkDisInfo> getParkDisInfo() {
		return parkDisInfo;
	}
	
	/**
	* parkDisInfo.
	*
	* @param parkDisInfo the parkDisInfo to set
	* @since JDK 1.8
	*/
	public void setParkDisInfo(List<ParkDisInfo> parkDisInfo) {
		this.parkDisInfo = parkDisInfo;
	}

	
}

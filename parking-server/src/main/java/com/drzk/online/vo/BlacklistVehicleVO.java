package com.drzk.online.vo;

/**
 * @author tf
 * 黑名单车辆
 */
public class BlacklistVehicleVO {
	 private Integer cardIssueBlackId;//编号
     private String carNo;//车牌号码
     private Integer carNoType;//车牌类型(0无,1黑名单,2特种车辆)
     private Integer isStop;//类型(0无,1禁止通行,2通行免费,3自由通行)
     private String userName;//创建时间
     private String userDate;//创建人
     private String description;//描述
	public Integer getCardIssueBlackId() {
		return cardIssueBlackId;
	}
	public void setCardIssueBlackId(Integer cardIssueBlackId) {
		this.cardIssueBlackId = cardIssueBlackId;
	}
	public String getCarNo() {
		return carNo;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public Integer getCarNoType() {
		return carNoType;
	}
	public void setCarNoType(Integer carNoType) {
		this.carNoType = carNoType;
	}
	public Integer getIsStop() {
		return isStop;
	}
	public void setIsStop(Integer isStop) {
		this.isStop = isStop;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserDate() {
		return userDate;
	}
	public void setUserDate(String userDate) {
		this.userDate = userDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

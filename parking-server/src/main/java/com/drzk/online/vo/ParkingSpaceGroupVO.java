package com.drzk.online.vo;

/**
 * @author tf
 * 车位组
 */
public class ParkingSpaceGroupVO {
	private Integer pid; //人员表ID
    private String conctactID;//人事编号
    private String conctactName; //人事姓名
    private String conctactTel;//人事电话
    private Integer familyGroupId ; //家庭组编号
    private String familyGroupName;//家庭组地址
    private Integer placeNumber; //车位数量
    private Integer CarNumber; //车数量
    private Integer noVacancyDispose ;//--0，按临时车入场  1禁止入场       
    private String startDate;//开始日期
    private String userName ;//创建人
    private String endDate ; //结束日期
    private String description ; //描述
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getConctactID() {
		return conctactID;
	}
	public void setConctactID(String conctactID) {
		this.conctactID = conctactID;
	}
	public String getConctactName() {
		return conctactName;
	}
	public void setConctactName(String conctactName) {
		this.conctactName = conctactName;
	}
	public String getConctactTel() {
		return conctactTel;
	}
	public void setConctactTel(String conctactTel) {
		this.conctactTel = conctactTel;
	}
	public Integer getFamilyGroupId() {
		return familyGroupId;
	}
	public void setFamilyGroupId(Integer familyGroupId) {
		this.familyGroupId = familyGroupId;
	}
	public String getFamilyGroupName() {
		return familyGroupName;
	}
	public void setFamilyGroupName(String familyGroupName) {
		this.familyGroupName = familyGroupName;
	}
	public Integer getPlaceNumber() {
		return placeNumber;
	}
	public void setPlaceNumber(Integer placeNumber) {
		this.placeNumber = placeNumber;
	}
	public Integer getCarNumber() {
		return CarNumber;
	}
	public void setCarNumber(Integer carNumber) {
		CarNumber = carNumber;
	}
	public Integer getNoVacancyDispose() {
		return noVacancyDispose;
	}
	public void setNoVacancyDispose(Integer noVacancyDispose) {
		this.noVacancyDispose = noVacancyDispose;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}

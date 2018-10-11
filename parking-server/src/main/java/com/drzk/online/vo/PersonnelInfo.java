package com.drzk.online.vo;

import java.util.Date;

public class PersonnelInfo extends OnlineBody
{
	private String parentId;
	private String companyId;
	private String companyName;
	private String departmentId;
	private String departmentName;
	private String contactName;
	private String contactPhone;
	private String contactNo;
	private Date entryDate;
	private Date conversionDate;
	private Integer sex;
	private Integer marry;
	private String birthplace;
	private Integer idType;
	private String idNum;
	private String carNo;
	private String emergencyContact;
	private String emergencyPhone;
	private Integer education;
	private String nationality;
	private Integer employeeStatus;
	private String carportGroupId;
	private String carportGroupName;
	
	/**部门上级id */
	public String getParentId()
	{
		return parentId;
	}
	/**部门上级id */
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	public String getCompanyId()
	{
		return companyId;
	}
	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}
	/** 公司名称 */
	public String getCompanyName()
	{
		return companyName;
	}
	/** 公司名称 */
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	public String getDepartmentId()
	{
		return departmentId;
	}
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}
	/** 部门名称 */
	public String getDepartmentName()
	{
		return departmentName;
	}
	/** 部门名称 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	/** 用户入职日期 */
	public Date getEntryDate()
	{
		return entryDate;
	}
	/** 用户入职日期 */
	public void setEntryDate(Date entryDate)
	{
		this.entryDate = entryDate;
	}
	/** 用户转正日期 */
	public Date getConversionDate()
	{
		return conversionDate;
	}
	/** 用户转正日期 */
	public void setConversionDate(Date conversionDate)
	{
		this.conversionDate = conversionDate;
	}
	/** 性别,0 未知 1 男 2 女 */
	public Integer getSex()
	{
		return sex;
	}
	/** 性别,0 未知 1 男 2 女 */
	public void setSex(Integer sex)
	{
		this.sex = sex;
	}
	/** 婚姻状况 1未婚2已婚 3离异 */
	public Integer getMarry()
	{
		return marry;
	}
	/** 婚姻状况 1未婚2已婚 3离异 */
	public void setMarry(Integer marry)
	{
		this.marry = marry;
	}
	/** 户籍地址 */
	public String getBirthplace()
	{
		return birthplace;
	}
	/** 户籍地址 */
	public void setBirthplace(String birthplace)
	{
		this.birthplace = birthplace;
	}
	/** 证件类型 1 身份证 2 护照 3 其他 */
	public Integer getIdType()
	{
		return idType;
	}
	/** 证件类型 1 身份证 2 护照 3 其他 */
	public void setIdType(Integer idType)
	{
		this.idType = idType;
	}
	/** 证件号码 */
	public String getIdNum()
	{
		return idNum;
	}
	/** 证件号码 */
	public void setIdNum(String idNum)
	{
		this.idNum = idNum;
	}
	public String getCarNo()
	{
		return carNo;
	}
	public void setCarNo(String carNo)
	{
		this.carNo = carNo;
	}
	/** 紧急联系人姓名 */
	public String getEmergencyContact()
	{
		return emergencyContact;
	}
	/** 紧急联系人姓名 */
	public void setEmergencyContact(String emergencyContact)
	{
		this.emergencyContact = emergencyContact;
	}
	/** 紧急联系人电话 */
	public String getEmergencyPhone()
	{
		return emergencyPhone;
	}
	/** 紧急联系人电话 */
	public void setEmergencyPhone(String emergencyPhone)
	{
		this.emergencyPhone = emergencyPhone;
	}
	/** 学历 1小学2初中3高中4大专5本科以上6其他 */
	public Integer getEducation()
	{
		return education;
	}
	public void setEducation(Integer education)
	{
		this.education = education;
	}
	public String getNationality()
	{
		return nationality;
	}
	public void setNationality(String nationality)
	{
		this.nationality = nationality;
	}
	public Integer getEmployeeStatus()
	{
		return employeeStatus;
	}
	public void setEmployeeStatus(Integer employeeStatus)
	{
		this.employeeStatus = employeeStatus;
	}
	public String getCarportGroupId()
	{
		return carportGroupId;
	}
	public void setCarportGroupId(String carportGroupId)
	{
		this.carportGroupId = carportGroupId;
	}
	public String getCarportGroupName()
	{
		return carportGroupName;
	}
	/** 学历 1小学2初中3高中4大专5本科以上6其他 */
	public void setCarportGroupName(String carportGroupName)
	{
		this.carportGroupName = carportGroupName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
}

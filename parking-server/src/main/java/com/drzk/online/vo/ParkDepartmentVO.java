package com.drzk.online.vo;


public class ParkDepartmentVO extends SuperBody
{

	private String companyId;
	private String companyName;
	private String departmentName;	
	private String parentId;
	
	/** 公司编号  系统参数表里UUID*/
	public String getCompanyId()
	{
		return companyId;
	}
	/** 公司编号  系统参数表里UUID*/
	public void setCompanyId(String companyId)
	{
		this.companyId = companyId;
	}
	/** 公司  系统参数表里*/
	public String getCompanyName()
	{
		return companyName;
	}
	/** 公司  系统参数表里*/
	public void setCompanyName(String companyName)
	{
		this.companyName = companyName;
	}
	public String getDepartmentName()
	{
		return departmentName;
	}
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}
	/** 上级部门ID */
	public String getParentId()
	{
		return parentId;
	}
	/** 上级部门ID */
	public void setParentId(String parentId)
	{
		this.parentId = parentId;
	}
	
}

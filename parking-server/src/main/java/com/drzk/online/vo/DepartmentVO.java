package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author tf
 * 部门信息
 */
public class DepartmentVO implements Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -5968577149100489524L;
	private Integer organizeId;//部门编号
	 private Integer  parentId;//上级部门编号
	 private String organizeName; //部门名称
	 private String categoryCode;//类型
	 private String userName;//
	 private String userDate; //
	 private String description; //描述
	public Integer getOrganizeId() {
		return organizeId;
	}
	public void setOrganizeId(Integer organizeId) {
		this.organizeId = organizeId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getOrganizeName() {
		return organizeName;
	}
	public void setOrganizeName(String organizeName) {
		this.organizeName = organizeName;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
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

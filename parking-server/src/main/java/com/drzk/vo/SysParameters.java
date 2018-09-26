package com.drzk.vo;

import java.util.Date;

public class SysParameters {
    /**
     * 
     */
    private Integer id;

    /**
     * 编号
     */
    private String parameterCode;

    /**
     * 字段名称
     */
    private String parameterName;

    /**
     * 字段值
     */
    private String parameterValue;

    /**
     * 1系统设置 2车场设置
     */
    private Integer typeId;

    /**
     * 是否更改
     */
    private Integer isEdit;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String createUserName;

    /**
     * 修改时间
     */
    private Date modifyDate;

    /**
     * 修改人
     */
    private String modifyUserName;

    /**
     * 备注
     */
    private String memo;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    

    
	/**
	* parameterCode.
	*
	* @return the parameterCode
	* @since JDK 1.8
	*/
	public String getParameterCode() {
		return parameterCode;
	}

	
	/**
	* parameterCode.
	*
	* @param parameterCode the parameterCode to set
	* @since JDK 1.8
	*/
	public void setParameterCode(String parameterCode) {
		this.parameterCode = parameterCode;
	}

	/**
     * 字段名称
     * @return parameter_name 字段名称
     */
    public String getParameterName() {
        return parameterName;
    }

    /**
     * 字段名称
     * @param parameterName 字段名称
     */
    public void setParameterName(String parameterName) {
        this.parameterName = parameterName == null ? null : parameterName.trim();
    }
    
    

    /**
     * 字段值
     * @return parameter_value 字段值
     */
    public String getParameterValue() {
        return parameterValue;
    }

    /**
     * 字段值
     * @param parameterValue 字段值
     */
    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue == null ? null : parameterValue.trim();
    }

    /**
     * 1系统设置 2车场设置
     * @return type_id 1系统设置 2车场设置
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 1系统设置 2车场设置
     * @param typeId 1系统设置 2车场设置
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 是否更改
     * @return is_edit 是否更改
     */
    public Integer getIsEdit() {
        return isEdit;
    }

    /**
     * 是否更改
     * @param isEdit 是否更改
     */
    public void setIsEdit(Integer isEdit) {
        this.isEdit = isEdit;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 创建人
     * @return create_user_name 创建人
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 创建人
     * @param createUserName 创建人
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    /**
     * 修改时间
     * @return modify_date 修改时间
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 修改时间
     * @param modifyDate 修改时间
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 修改人
     * @return modify_user_name 修改人
     */
    public String getModifyUserName() {
        return modifyUserName;
    }

    /**
     * 修改人
     * @param modifyUserName 修改人
     */
    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName == null ? null : modifyUserName.trim();
    }

    /**
     * 备注
     * @return memo 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}
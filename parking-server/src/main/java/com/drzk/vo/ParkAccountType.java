package com.drzk.vo;

import java.util.Date;

public class ParkAccountType {
    /**
     * 
     */
    private Integer id;

    /**
     * 卡类ID
     */
    private Integer aType;

    /**
     * 卡类名称
     */
    private String aName;

    /**
     * 自定名称
     */
    private String aCustname;

    /**
     * 是否不启用 0启用1不启用
     */
    private Integer noUse;

    /**
     * 是否可发行
     */
    private Integer isSend;

    /**
     * ID模式(IC转ID用)
     */
    private Integer idMode;

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
     * 卡类ID
     * @return a_type 卡类ID
     */
    public Integer getaType() {
        return aType;
    }

    /**
     * 卡类ID
     * @param aType 卡类ID
     */
    public void setaType(Integer aType) {
        this.aType = aType;
    }

    /**
     * 卡类名称
     * @return a_name 卡类名称
     */
    public String getaName() {
        return aName;
    }

    /**
     * 卡类名称
     * @param aName 卡类名称
     */
    public void setaName(String aName) {
        this.aName = aName == null ? null : aName.trim();
    }

    /**
     * 自定名称
     * @return a_custname 自定名称
     */
    public String getaCustname() {
        return aCustname;
    }

    /**
     * 自定名称
     * @param aCustname 自定名称
     */
    public void setaCustname(String aCustname) {
        this.aCustname = aCustname == null ? null : aCustname.trim();
    }

    /**
     * 是否不启用 0启用1不启用
     * @return no_use 是否不启用 0启用1不启用
     */
    public Integer getNoUse() {
        return noUse;
    }

    /**
     * 是否不启用 0启用1不启用
     * @param noUse 是否不启用 0启用1不启用
     */
    public void setNoUse(Integer noUse) {
        this.noUse = noUse;
    }

    /**
     * 是否可发行
     * @return is_send 是否可发行
     */
    public Integer getIsSend() {
        return isSend;
    }

    /**
     * 是否可发行
     * @param isSend 是否可发行
     */
    public void setIsSend(Integer isSend) {
        this.isSend = isSend;
    }

    /**
     * ID模式(IC转ID用)
     * @return id_mode ID模式(IC转ID用)
     */
    public Integer getIdMode() {
        return idMode;
    }

    /**
     * ID模式(IC转ID用)
     * @param idMode ID模式(IC转ID用)
     */
    public void setIdMode(Integer idMode) {
        this.idMode = idMode;
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
}
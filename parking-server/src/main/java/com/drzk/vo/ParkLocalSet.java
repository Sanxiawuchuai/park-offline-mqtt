package com.drzk.vo;

import java.util.Date;

public class ParkLocalSet {
    /**
     * 岗亭编号
     */
    private Integer boxId;

    /**
     * 岗亭IP
     */
    private String boxIp;

    /**
     * 岗亭名称
     */
    private String boxName;

    /**
     * 岗亭类型0标准收费点1中央收费点2综合收费点
     */
    private Integer boxType;

    /**
     * 扫描枪串口号
     */
    private Integer ticketCom;

    /**
     * 车位类型（0混合车位、1临时车位、2固定车位）
     */
    private Integer placeType;

    /**
     * 车位模式（0大车场、小车场）
     */
    private Integer placeMode;

    /**
     * 车位数(总数量)
     */
    private Integer placeNum;

    /**
     * 证件抓拍类型(0无1usb摄像头)
     */
    private Integer credentialsType;

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
     * 是否上传0未上传1正在上传2上传成功
     */
    private Integer isLoad;
    
    
    /*
     * 岗亭当前登录用户
     */
    private String loginName;
    /*
     * 岗亭在线状态 0离线 1在线
     */
    private Integer online;
    
    private String equipmentID;//岗亭设备编号
    /*
     * 登入时间
     */
    private Date loginDate;
    
    public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	private String luid;
    
    private Integer delFrag;
    
    

	public String getLuid()
	{
		return luid;
	}

	public void setLuid(String luid)
	{
		this.luid = luid;
	}


	/**
     * 岗亭编号
     * @return box_id 岗亭编号
     */
    public Integer getBoxId() {
        return boxId;
    }

    /**
     * 岗亭编号
     * @param boxId 岗亭编号
     */
    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    /**
     * 岗亭IP
     * @return box_ip 岗亭IP
     */
    public String getBoxIp() {
        return boxIp;
    }

    /**
     * 岗亭IP
     * @param boxIp 岗亭IP
     */
    public void setBoxIp(String boxIp) {
        this.boxIp = boxIp == null ? null : boxIp.trim();
    }

    /**
     * 岗亭名称
     * @return box_name 岗亭名称
     */
    public String getBoxName() {
        return boxName;
    }

    /**
     * 岗亭名称
     * @param boxName 岗亭名称
     */
    public void setBoxName(String boxName) {
        this.boxName = boxName == null ? null : boxName.trim();
    }


    /**
     * 车位数(总数量)
     * @return place_num 车位数(总数量)
     */
    public Integer getPlaceNum() {
        return placeNum;
    }

    /**
     * 车位数(总数量)
     * @param placeNum 车位数(总数量)
     */
    public void setPlaceNum(Integer placeNum) {
        this.placeNum = placeNum;
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
     * 岗亭当前用户
     * @param loginName
     */
    public void setLoginName(String loginName)
    {
    	this.loginName = loginName;
    }
    /**
     * 岗亭当前用户
     * @return
     */
    public String getLoginName()
    {
    	return loginName;
    }

    
    /** 岗亭设备地址 可变 不存库 */
    public void setEquipmentID(String equipmentID)
    {
    	this.equipmentID =equipmentID;
    }
    /** 岗亭设备地址 可变 不存库 */
    public String getEquipmentID()
    {
    	return equipmentID;
    }

    public Integer getBoxType() {
        return boxType;
    }

    public void setBoxType(Integer boxType) {
        this.boxType = boxType;
    }

    public Integer getTicketCom() {
        return ticketCom;
    }

    public void setTicketCom(Integer ticketCom) {
        this.ticketCom = ticketCom;
    }

    public Integer getPlaceType() {
        return placeType;
    }

    public void setPlaceType(Integer placeType) {
        this.placeType = placeType;
    }

    public Integer getPlaceMode() {
        return placeMode;
    }

    public void setPlaceMode(Integer placeMode) {
        this.placeMode = placeMode;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
    }

    public Integer getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public Integer getDelFrag() {
        return delFrag;
    }

    public void setDelFrag(Integer delFrag) {
        this.delFrag = delFrag;
    }
}
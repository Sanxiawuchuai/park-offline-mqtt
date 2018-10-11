package com.drzk.vo;

import java.util.Date;

public class ParkCamSet{


	/**
     * 自增长ID
     */
    private Integer id;

    /**
     * 
     */
    private Integer boxId;

    /**
     * 
     */
    private Integer sortId;

    /**
     * 
     */
    private String camIp;

    /**
     * 
     */
    private String camName;

    /**
     * 
     */
    private Integer camPort;
    
    private Integer touchType;

    /**
     * 是否是人像
     */
    private Integer fecPic;

    /**
     * 
     */
    private Integer machNo;

    /**
     * 
     */
    private Integer channelId;

    /**
     * 创建人
     */
    private Date createDate;

    /**
     * 创建时间
     */
    private String createUserName;

    /**
     * 修改人
     */
    private Date modifyDate;

    /**
     * 修改时间
     */
    private String modifyUserName;

    /**
     * 是否上传0未上传1已上传
     */
    private Integer isLoad;
    
    private String cuid;
    
    private Integer delFrag;


	public String getCuid()
	{
		return cuid;
	}

	public void setCuid(String cuid)
	{
		this.cuid = cuid;
	}

    /**
     * 自增长ID
     * @return id 自增长ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增长ID
     * @param id 自增长ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 
     * @return box_id 
     */
    public Integer getBoxId() {
        return boxId;
    }

    /**
     * 
     * @param boxId 
     */
    public void setBoxId(Integer boxId) {
        this.boxId = boxId;
    }

    /**
     * 
     * @return sort_id 
     */
    public Integer getSortId() {
        return sortId;
    }

    /**
     * 
     * @param sortId 
     */
    public void setSortId(Integer sortId) {
        this.sortId = sortId;
    }

    /**
     * 
     * @return cam_ip 
     */
    public String getCamIp() {
        return camIp;
    }

    /**
     * 
     * @param camIp 
     */
    public void setCamIp(String camIp) {
        this.camIp = camIp == null ? null : camIp.trim();
    }

    /**
     * 
     * @return cam_name 
     */
    public String getCamName() {
        return camName;
    }

    /**
     * 
     * @param camName 
     */
    public void setCamName(String camName) {
        this.camName = camName == null ? null : camName.trim();
    }

    /**
     * 
     * @return cam_port 
     */
    public Integer getCamPort() {
        return camPort;
    }

    /**
     * 
     * @param camPort 
     */
    public void setCamPort(Integer camPort) {
        this.camPort = camPort;
    }


    /**
     * 
     * @return mach_no 
     */
    public Integer getMachNo() {
        return machNo;
    }

    /**
     * 
     * @param machNo 
     */
    public void setMachNo(Integer machNo) {
        this.machNo = machNo;
    }

    /**
     * 
     * @return channel_id 
     */
    public Integer getChannelId() {
        return channelId;
    }

    /**
     * 
     * @param channelId 
     */
    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    /**
     * 创建人
     * @return create_date 创建人
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建人
     * @param createDate 创建人
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 创建时间
     * @return create_user_name 创建时间
     */
    public String getCreateUserName() {
        return createUserName;
    }

    /**
     * 创建时间
     * @param createUserName 创建时间
     */
    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    /**
     * 修改人
     * @return modify_date 修改人
     */
    public Date getModifyDate() {
        return modifyDate;
    }

    /**
     * 修改人
     * @param modifyDate 修改人
     */
    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    /**
     * 修改时间
     * @return modify_user_name 修改时间
     */
    public String getModifyUserName() {
        return modifyUserName;
    }

    /**
     * 修改时间
     * @param modifyUserName 修改时间
     */
    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName == null ? null : modifyUserName.trim();
    }
	
	/**
	* touchType.
	*
	* @return the touchType
	* @since JDK 1.8
	*/
	public Integer getTouchType() {
		return touchType;
	}

	
	/**
	* touchType.
	*
	* @param touchType the touchType to set
	* @since JDK 1.8
	*/
	public void setTouchType(Integer touchType) {
		this.touchType = touchType;
	}

    public Integer getFecPic() {
        return fecPic;
    }

    public void setFecPic(Integer fecPic) {
        this.fecPic = fecPic;
    }

    public Integer getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
    }

    public Integer getDelFrag() {
        return delFrag;
    }

    public void setDelFrag(Integer delFrag) {
        this.delFrag = delFrag;
    }
}
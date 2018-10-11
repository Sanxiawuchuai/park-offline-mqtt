package com.drzk.vo;

import java.util.Date;

public class ParkChannelSet{


	/**
     * 自增id
     */
    private Integer id;

    /**
     * 岗亭编号
     */
    private Integer boxId;

    /**
     * 机号
     */
    private Integer machNo;

    /**
     * 通道ip
     */
    private String channelIp;

    /**
     * 通道名
     */
    private String channelName;

    /**
     * 通道序列号
     */
    private String dsn;

    /**
     * 出入类型（0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道入、7单通道出）
     */
    private Integer inOut;

    /**
     * 通道类型（0综合信道 1固定车信道，2临时卡通道）
     */
    private Integer gateType;

    /**
     * 车场编号
     */
    private Integer parkNo;

    /**
     * 是否禁止收费
     */
    private Integer noMoney;

    /**
     * 确定开闸
     */
    private String strobeSet;

    /**
     * 开闸机号
     */
    private Integer strobeNo;

    /**
     * 工作模式
     */
    private String workModel;

    /**
     * 附件序列
     */
    private String enclosure;

    /**
     * 视频序列
     */
    private String videoList;

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
     * 上传标记0未上传1正在上传2已上传
     */
    private Integer isLoad=0;
    
    private String cuid;
    
    private Integer delFrag=0;
    
    private boolean isOnline = true;


    public Integer getInOut() {
        return inOut;
    }

    public void setInOut(Integer inOut) {
        this.inOut = inOut;
    }

    public Integer getGateType() {
        return gateType;
    }

    public void setGateType(Integer gateType) {
        this.gateType = gateType;
    }

    public Integer getParkNo() {
        return parkNo;
    }

    public void setParkNo(Integer parkNo) {
        this.parkNo = parkNo;
    }

    public Integer getNoMoney() {
        return noMoney;
    }

    public void setNoMoney(Integer noMoney) {
        this.noMoney = noMoney;
    }

    public Integer getStrobeNo() {
        return strobeNo;
    }

    public void setStrobeNo(Integer strobeNo) {
        this.strobeNo = strobeNo;
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

    /**
	 * @return the isOnline
	 */
	public boolean isOnline() {
		return isOnline;
	}
	/**
	 * @param isOnline the isOnline to set
	 */
	public void setOnline(boolean isOnline) {
		this.isOnline = isOnline;
	}


	public String getCuid()
	{
		return cuid;
	}

	public void setCuid(String cuid)
	{
		this.cuid = cuid;
	}

	/**
     * 自增id
     * @return id 自增id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增id
     * @param id 自增id
     */
    public void setId(Integer id) {
        this.id = id;
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
     * 机号
     * @return mach_no 机号
     */
    public Integer getMachNo() {
        return machNo;
    }

    /**
     * 机号
     * @param machNo 机号
     */
    public void setMachNo(Integer machNo) {
        this.machNo = machNo;
    }

    /**
     * 通道ip
     * @return channel_ip 通道ip
     */
    public String getChannelIp() {
        return channelIp;
    }

    /**
     * 通道ip
     * @param channelIp 通道ip
     */
    public void setChannelIp(String channelIp) {
        this.channelIp = channelIp == null ? null : channelIp.trim();
    }

    /**
     * 通道名
     * @return channel_name 通道名
     */
    public String getChannelName() {
        return channelName;
    }

    /**
     * 通道名
     * @param channelName 通道名
     */
    public void setChannelName(String channelName) {
        this.channelName = channelName == null ? null : channelName.trim();
    }

    /**
     * 通道序列号
     * @return dsn 通道序列号
     */
    public String getDsn() {
        return dsn;
    }

    /**
     * 通道序列号
     * @param dsn 通道序列号
     */
    public void setDsn(String dsn) {
        this.dsn = dsn == null ? null : dsn.trim();
    }


    /**
     * 确定开闸
     * @return strobe_set 确定开闸
     */
    public String getStrobeSet() {
        return strobeSet;
    }

    /**
     * 确定开闸
     * @param strobeSet 确定开闸
     */
    public void setStrobeSet(String strobeSet) {
        this.strobeSet = strobeSet == null ? null : strobeSet.trim();
    }


    /**
     * 工作模式
     * @return work_model 工作模式
     */
    public String getWorkModel() {
        return workModel;
    }

    /**
     * 工作模式
     * @param workModel 工作模式
     */
    public void setWorkModel(String workModel) {
        this.workModel = workModel == null ? null : workModel.trim();
    }

    /**
     * 附件序列
     * @return enclosure 附件序列
     */
    public String getEnclosure() {
        return enclosure;
    }

    /**
     * 附件序列
     * @param enclosure 附件序列
     */
    public void setEnclosure(String enclosure) {
        this.enclosure = enclosure == null ? null : enclosure.trim();
    }

    /**
     * 视频序列
     * @return video_list 视频序列
     */
    public String getVideoList() {
        return videoList;
    }

    /**
     * 视频序列
     * @param videoList 视频序列
     */
    public void setVideoList(String videoList) {
        this.videoList = videoList == null ? null : videoList.trim();
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
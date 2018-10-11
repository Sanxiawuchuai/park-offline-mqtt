package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class YktCardIssue implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8200626670983830377L;

	/**
     * 自增id
     */
    private Integer id;

    private String cuid;

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3.身份证，4.纯车牌，5.ETC，6.CPU)
     */
    private Integer cFlag;

    /**
     * 人事ID
     */
    private Integer pId;

    /**
     * 押金
     */
    private Float foregift;

    /**
     * 账户余额
     */
    private Double balanceMoney;

    /**
     * 卡状态（0正常、1正在挂失、2已挂失、3正在解挂、4已补卡、5挂失退款、6已销户
     */
    private Integer status;

    /**
     * 系统权限(车场；门禁；消费；考勤；巡更；访客；水控；）
     */
    private String sysRight;

    /**
     * 0不共享车位1共享车位
     */
    private Integer isShare;

    /**
     * 车位编号
     */
    private String placeNum;

    /**
     * 创建人
     */
    private Date createDate;

    /**
     * 创建时间
     */
    private String createUserName;

    /**
     * 备注
     */
    private String memo;
    private String cardId;
    
    public String getCardId()
	{
		return cardId;
	}
	public void setCardId(String cardId)
	{
		this.cardId = cardId;
	}

	private Integer isLoad;
    
    /** 0-未上传；1-正在上传  */
    public Integer getIsLoad()
	{
		return isLoad;
	}
    /** 0-未上传；1-正在上传  */
	public void setIsLoad(Integer isLoad)
	{
		this.isLoad = isLoad;
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
     * 卡介质(0为IC，1为ID，2IC做ID，3.身份证，4.纯车牌，5.ETC，6.CPU)
     * @return c_flag 卡介质(0为IC，1为ID，2IC做ID，3.身份证，4.纯车牌，5.ETC，6.CPU)
     */
    public Integer getcFlag() {
        return cFlag;
    }

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3.身份证，4.纯车牌，5.ETC，6.CPU)
     * @param cFlag 卡介质(0为IC，1为ID，2IC做ID，3.身份证，4.纯车牌，5.ETC，6.CPU)
     */
    public void setcFlag(Integer cFlag) {
        this.cFlag = cFlag;
    }

    /**
     * 人事ID
     * @return p_id 人事ID
     */
    public Integer getpId() {
        return pId;
    }

    /**
     * 人事ID
     * @param pId 人事ID
     */
    public void setpId(Integer pId) {
        this.pId = pId;
    }

    /**
     * 押金
     * @return foregift 押金
     */
    public Float getForegift() {
        return foregift;
    }

    /**
     * 押金
     * @param foregift 押金
     */
    public void setForegift(Float foregift) {
        this.foregift = foregift;
    }

    public Double getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(Double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    /**
     * 卡状态（0正常、1正在挂失、2已挂失、3正在解挂、4已补卡、5挂失退款、6已销户
     * @return status 卡状态（0正常、1正在挂失、2已挂失、3正在解挂、4已补卡、5挂失退款、6已销户
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 卡状态（0正常、1正在挂失、2已挂失、3正在解挂、4已补卡、5挂失退款、6已销户
     * @param status 卡状态（0正常、1正在挂失、2已挂失、3正在解挂、4已补卡、5挂失退款、6已销户
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 系统权限(车场；门禁；消费；考勤；巡更；访客；水控；）
     * @return sys_right 系统权限(车场；门禁；消费；考勤；巡更；访客；水控；）
     */
    public String getSysRight() {
        return sysRight;
    }

    /**
     * 系统权限(车场；门禁；消费；考勤；巡更；访客；水控；）
     * @param sysRight 系统权限(车场；门禁；消费；考勤；巡更；访客；水控；）
     */
    public void setSysRight(String sysRight) {
        this.sysRight = sysRight == null ? null : sysRight.trim();
    }

    /**
     * 0不共享车位1共享车位
     * @return is_share 0不共享车位1共享车位
     */
    public Integer getIsShare() {
        return isShare;
    }

    /**
     * 0不共享车位1共享车位
     * @param isShare 0不共享车位1共享车位
     */
    public void setIsShare(Integer isShare) {
        this.isShare = isShare;
    }

    /**
     * 车位编号
     * @return place_num 车位编号
     */
    public String getPlaceNum() {
        return placeNum;
    }

    /**
     * 车位编号
     * @param placeNum 车位编号
     */
    public void setPlaceNum(String placeNum) {
        this.placeNum = placeNum == null ? null : placeNum.trim();
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

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }
}
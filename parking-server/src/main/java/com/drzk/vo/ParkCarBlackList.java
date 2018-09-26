package com.drzk.vo;

import java.util.Date;

public class ParkCarBlackList {
    /**
     * 
     */
    private Integer id;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 车牌类型(0无,1黑名单,2特种车辆)
     */
    private Integer carNoType;

    /**
     * 类型(0无,1禁止通行,2通行免费,3自由通行)
     */
    private Integer isStop;

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
    
    private String cuid;
    
    private Integer delFrag;
    private Integer isLoad;
    

    public Integer getIsLoad() {
		return isLoad;
	}

	public void setIsLoad(Integer isLoad) {
		this.isLoad = isLoad;
	}

	

	public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	/** 0-正常；1-删除  */
	public Integer getDelFrag()
	{
		return delFrag;
	}
	/** 0-正常；1-删除  */
	public void setDelFrag(Integer delFrag)
	{
		this.delFrag = delFrag;
	}

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
     * 车牌号
     * @return car_no 车牌号
     */
    public String getCarNo() {
        return carNo;
    }

    /**
     * 车牌号
     * @param carNo 车牌号
     */
    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    /**
     * 车牌类型(0无,1黑名单,2特种车辆)
     * @return car_no_type 车牌类型(0无,1黑名单,2特种车辆)
     */
    public Integer getCarNoType() {
        return carNoType;
    }

    /**
     * 车牌类型(0无,1黑名单,2特种车辆)
     * @param carNoType 车牌类型(0无,1黑名单,2特种车辆)
     */
    public void setCarNoType(Integer carNoType) {
        this.carNoType = carNoType;
    }

    /**
     * 类型(0无,1禁止通行,2通行免费,3自由通行)
     * @return is_stop 类型(0无,1禁止通行,2通行免费,3自由通行)
     */
    public Integer getIsStop() {
        return isStop;
    }

    /**
     * 类型(0无,1禁止通行,2通行免费,3自由通行)
     * @param isStop 类型(0无,1禁止通行,2通行免费,3自由通行)
     */
    public void setIsStop(Integer isStop) {
        this.isStop = isStop;
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
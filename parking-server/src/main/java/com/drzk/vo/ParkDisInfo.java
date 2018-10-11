package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class ParkDisInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 432353889004348997L;

	/**
     * 
     */
    private Integer id;

    /**
     * 设备ID
     */
    private Integer eqid;

    /**
     * 折扣ID
     */
    private String discountId;

    /**
     * 折扣名称
     */
    private String discountName;

    /**
     * 折扣模式 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
     */
    private Integer discountType;

    /**
     * 折扣值
     */
    private Double discountAmount;

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
    
    
    private String puid;
    private Integer isLoad; 
    private Integer delFrag;
    
    

    public String getPuid()
	{
		return puid;
	}

	public void setPuid(String puid)
	{
		this.puid = puid;
	}

	public Integer getIsLoad()
	{
		return isLoad;
	}

	public void setIsLoad(Integer isLoad)
	{
		this.isLoad = isLoad;
	}

	public Integer getDelFrag()
	{
		return delFrag;
	}

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
     * 设备ID
     * @return eqid 设备ID
     */
    public Integer getEqid() {
        return eqid;
    }

    /**
     * 设备ID
     * @param eqid 设备ID
     */
    public void setEqid(Integer eqid) {
        this.eqid = eqid;
    }

    /**
     * 折扣ID
     * @return discount_id 折扣ID
     */
    public String getDiscountId() {
        return discountId;
    }

    /**
     * 折扣ID
     * @param discountId 折扣ID
     */
    public void setDiscountId(String discountId) {
        this.discountId = discountId;
    }

    /**
     * 折扣名称
     * @return discount_name 折扣名称
     */
    public String getDiscountName() {
        return discountName;
    }

    /**
     * 折扣名称
     * @param discountName 折扣名称
     */
    public void setDiscountName(String discountName) {
        this.discountName = discountName == null ? null : discountName.trim();
    }

    /**
     * 折扣模式 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
     * @return discount_type 折扣模式 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
     */
    public Integer getDiscountType() {
        return discountType;
    }

    /**
     * 折扣模式 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
     * @param discountType 折扣模式 1全免(0)  2 折扣率(<100) 3免时间(<65535) 4免金额(<65535)
     */
    public void setDiscountType(Integer discountType) {
        this.discountType = discountType;
    }

    /**
     * 折扣值
     * @return discount_amount 折扣值
     */
    public Double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * 折扣值
     * @param discountAmount 折扣值
     */
    public void setDiscountAmount(Double discountAmount) {
        this.discountAmount = discountAmount;
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
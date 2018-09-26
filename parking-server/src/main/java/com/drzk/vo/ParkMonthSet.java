package com.drzk.vo;

import java.util.Date;

public class ParkMonthSet {
    /**
     * 自增加id
     */
    private Integer id;

    /**
     * 月租卡类型(11,12,13,14)
     */
    private Integer accountType;

    /**
     * 类型(0月卡1季度卡2半年卡3年卡)
     */
    private Integer sType;

    /**
     * 金额
     */
    private Double chargeMoney;

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
    private String cardTypeName;
    

    public String getPuid()
	{
		return puid;
	}

	public void setPuid(String puid)
	{
		this.puid = puid;
	}
	/** 0-未同步；1-已同步 */
	public Integer getIsLoad()
	{
		return isLoad;
	}
	/** 0-未同步；1-已同步 */
	public void setIsLoad(Integer isLoad)
	{
		this.isLoad = isLoad;
	}

	/**
     * 自增加id
     * @return id 自增加id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增加id
     * @param id 自增加id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 月租卡类型(11,12,13,14)
     * @return account_type 月租卡类型(11,12,13,14)
     */
    public Integer getAccountType() {
        return accountType;
    }

    /**
     * 月租卡类型(11,12,13,14)
     * @param accountType 月租卡类型(11,12,13,14)
     */
    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    /**
     * 类型(0月卡1季度卡2半年卡3年卡)
     * @return s_type 类型(0月卡1季度卡2半年卡3年卡)
     */
    public Integer getsType() {
        return sType;
    }

    /**
     * 类型(0月卡1季度卡2半年卡3年卡)
     * @param sType 类型(0月卡1季度卡2半年卡3年卡)
     */
    public void setsType(Integer sType) {
        this.sType = sType;
    }

    /**
     * 金额
     * @return charge_money 金额
     */
    public Double getChargeMoney() {
        return chargeMoney;
    }

    /**
     * 金额
     * @param chargeMoney 金额
     */
    public void setChargeMoney(Double chargeMoney) {
        this.chargeMoney = chargeMoney;
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

    public String getCardTypeName() {
        return cardTypeName;
    }

    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName;
    }
}
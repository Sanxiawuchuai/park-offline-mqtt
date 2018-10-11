package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class VwParkCarIsuse implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2912075341213691286L;

	private Integer id;

    private String cuid;
    private String cardId;

    private Byte cFlag;

    private Integer pId;

    private Float foregift;

    private Float balanceMoney;

    private Integer status;

    private String placeNum;

    private String sysRight;

    private Byte isShare;

    private String memo;

    private Date createDate;

    private String createUserName;

    private Integer yktId;

    private String carNo;

    private Integer cardType;

    private Integer sType;

    private Integer carNoType;

    private String carColour;

    private Date startDate;

    private Date endDate;

    private Integer planId;

    private String perName;

    private String perTel;

    private String perIdCode;

    private String wechatNo;

    private String landLineNum;

    private String perIdNo;

    private String sex;

    private Date birthday;

    private String perNation;

    private String perPlace;

    private String perZipCode;

    private String marry;

    private String perEmail;

    private Byte perType;

    private Date enterDate;

    private Date formatDate;

    private String eduLevel;

    private String politicalStatus;

    private String positions;

    private Byte isLeave;

    private Date leaveDate;

    private String leaveCause;

    private Integer placeId;

    private Integer pNum;

    private String placeName;

    private String deptName;

    private Byte pType;
    private Byte isLoad;
    private Integer delayType;
    private String puid;


    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    /** 人员puid */
    public String getPuid()
	{
		return puid;
	}
    /** 人员puid */
	public void setPuid(String puid)
	{
		this.puid = puid;
	}
	/**
	 * 1 连续性计费 如6月份正常月卡，7月份未交费，8月份需使用需要连7、8月的一起充值才可以用）
	 * 2按天起计（如8月21号续期3个月，就到11月21日为月卡到期时间）
	 * 3 自然月计费（足月续费，如1月份充值3个月，那就是1月份使用31天的月卡，2月份只有28天，3月31天，足月使用）
	 * @return
	 */
    public Integer getDelayType()
	{
		return delayType;
	}
    /**
	 * 1 连续性计费 如6月份正常月卡，7月份未交费，8月份需使用需要连7、8月的一起充值才可以用）
	 * 2按天起计（如8月21号续期3个月，就到11月21日为月卡到期时间）
	 * 3 自然月计费（足月续费，如1月份充值3个月，那就是1月份使用31天的月卡，2月份只有28天，3月31天，足月使用）
	 * @return
	 */
	public void setDelayType(Integer delayType)
	{
		this.delayType = delayType;
	}

	public Byte getIsLoad()
	{
		return isLoad;
	}

	public void setIsLoad(Byte isLoad)
	{
		this.isLoad = isLoad;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    public Byte getcFlag() {
        return cFlag;
    }

    public void setcFlag(Byte cFlag) {
        this.cFlag = cFlag;
    }

    public Integer getpId() {
        return pId;
    }

    public void setpId(Integer pId) {
        this.pId = pId;
    }

    public Float getForegift() {
        return foregift;
    }

    public void setForegift(Float foregift) {
        this.foregift = foregift;
    }

    public Float getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(Float balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(String placeNum) {
        this.placeNum = placeNum == null ? null : placeNum.trim();
    }

    public String getSysRight() {
        return sysRight;
    }

    public void setSysRight(String sysRight) {
        this.sysRight = sysRight == null ? null : sysRight.trim();
    }

    public Byte getIsShare() {
        return isShare;
    }

    public void setIsShare(Byte isShare) {
        this.isShare = isShare;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Integer getYktId() {
        return yktId;
    }

    public void setYktId(Integer yktId) {
        this.yktId = yktId;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Integer getCardType() {
        return cardType;
    }

    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    public Integer getsType() {
        return sType;
    }

    public void setsType(Integer sType) {
        this.sType = sType;
    }

    public Integer getCarNoType() {
        return carNoType;
    }

    public void setCarNoType(Integer carNoType) {
        this.carNoType = carNoType;
    }

    public String getCarColour() {
        return carColour;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour == null ? null : carColour.trim();
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getPlanId() {
        return planId;
    }

    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }

    public String getPerTel() {
        return perTel;
    }

    public void setPerTel(String perTel) {
        this.perTel = perTel == null ? null : perTel.trim();
    }

    public String getPerIdCode() {
        return perIdCode;
    }

    public void setPerIdCode(String perIdCode) {
        this.perIdCode = perIdCode == null ? null : perIdCode.trim();
    }

    public String getWechatNo() {
        return wechatNo;
    }

    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo == null ? null : wechatNo.trim();
    }

    public String getLandLineNum() {
        return landLineNum;
    }

    public void setLandLineNum(String landLineNum) {
        this.landLineNum = landLineNum == null ? null : landLineNum.trim();
    }

    public String getPerIdNo() {
        return perIdNo;
    }

    public void setPerIdNo(String perIdNo) {
        this.perIdNo = perIdNo == null ? null : perIdNo.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getPerNation() {
        return perNation;
    }

    public void setPerNation(String perNation) {
        this.perNation = perNation == null ? null : perNation.trim();
    }

    public String getPerPlace() {
        return perPlace;
    }

    public void setPerPlace(String perPlace) {
        this.perPlace = perPlace == null ? null : perPlace.trim();
    }

    public String getPerZipCode() {
        return perZipCode;
    }

    public void setPerZipCode(String perZipCode) {
        this.perZipCode = perZipCode == null ? null : perZipCode.trim();
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry == null ? null : marry.trim();
    }

    public String getPerEmail() {
        return perEmail;
    }

    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail == null ? null : perEmail.trim();
    }

    public Byte getPerType() {
        return perType;
    }

    public void setPerType(Byte perType) {
        this.perType = perType;
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    public Date getFormatDate() {
        return formatDate;
    }

    public void setFormatDate(Date formatDate) {
        this.formatDate = formatDate;
    }

    public String getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel == null ? null : eduLevel.trim();
    }

    public String getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus == null ? null : politicalStatus.trim();
    }

    public String getPositions() {
        return positions;
    }

    public void setPositions(String positions) {
        this.positions = positions == null ? null : positions.trim();
    }

    public Byte getIsLeave() {
        return isLeave;
    }

    public void setIsLeave(Byte isLeave) {
        this.isLeave = isLeave;
    }

    public Date getLeaveDate() {
        return leaveDate;
    }

    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    public String getLeaveCause() {
        return leaveCause;
    }

    public void setLeaveCause(String leaveCause) {
        this.leaveCause = leaveCause == null ? null : leaveCause.trim();
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public Integer getpNum() {
        return pNum;
    }

    public void setpNum(Integer pNum) {
        this.pNum = pNum;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Byte getpType() {
        return pType;
    }

    public void setpType(Byte pType) {
        this.pType = pType;
    }
}
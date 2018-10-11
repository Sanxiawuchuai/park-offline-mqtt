package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class VwPersoninfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3255938988966118562L;

	private Integer pid;

    private String puid;

    private String perId;

    private String perName;

    private String perIdCode;

    private String perAddr;

    private String wechatNo;

    private String perTel;

    private String landLineNum;

    private String perIdNo;

    private String sex;

    private String carNo;

    private Date birthday;

    private String perNation;

    private String perPlace;

    private String perZipCode;

    private String marry;

    private String perEmail;

    private Integer perType;

    private Date enterDate;

    private Date formatDate;

    private String eduLevel;

    private String politicalStatus;

    private Integer deptId;

    private String positions;

    private Byte isLeave;

    private Date leaveDate;

    private String leaveCause;

    private Date createDate;

    private String createUserName;

    private Date modifyDate;

    private String modifyUserName;

    private String memo;

    private Integer isload;

    private String perPic;

    private String placeId;

    private Integer delFrag;

    private String duid;
    private String deptName;

    private Integer topDeptId;

    private String placeName;

    private Integer placeNum;

    private Integer placeIn;

    private Byte pType;
    private String cuid;
    private String companyName;
    private Integer compId;


    public String getDuid() {
        return duid;
    }

    public void setDuid(String duid) {
        this.duid = duid;
    }

    public String getCuid() {
		return cuid;
	}

	public void setCuid(String cuid) {
		this.cuid = cuid;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getCompId() {
		return compId;
	}

	public void setCompId(Integer compId) {
		this.compId = compId;
	}

	public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getPuid() {
        return puid;
    }

    public void setPuid(String puid) {
        this.puid = puid == null ? null : puid.trim();
    }

    public String getPerId() {
        return perId;
    }

    public void setPerId(String perId) {
        this.perId = perId == null ? null : perId.trim();
    }

    public String getPerName() {
        return perName;
    }

    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }
    /** 识别码  */
    public String getPerIdCode() {
        return perIdCode;
    }
    /** 识别码  */
    public void setPerIdCode(String perIdCode) {
        this.perIdCode = perIdCode == null ? null : perIdCode.trim();
    }

    public String getPerAddr() {
        return perAddr;
    }

    public void setPerAddr(String perAddr) {
        this.perAddr = perAddr == null ? null : perAddr.trim();
    }
    /** 微信号  */
    public String getWechatNo() {
        return wechatNo;
    }
    /** 微信号  */
    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo == null ? null : wechatNo.trim();
    }

    public String getPerTel() {
        return perTel;
    }

    public void setPerTel(String perTel) {
        this.perTel = perTel == null ? null : perTel.trim();
    }
    /** 座机号  */
    public String getLandLineNum() {
        return landLineNum;
    }
    /** 座机号  */
    public void setLandLineNum(String landLineNum) {
        this.landLineNum = landLineNum == null ? null : landLineNum.trim();
    }
    /** 身份证  */
    public String getPerIdNo() {
        return perIdNo;
    }
    /** 身份证  */
    public void setPerIdNo(String perIdNo) {
        this.perIdNo = perIdNo == null ? null : perIdNo.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
    /**民族  */
    public String getPerNation() {
        return perNation;
    }
    /**民族  */
    public void setPerNation(String perNation) {
        this.perNation = perNation == null ? null : perNation.trim();
    }
    /** 籍贯  */
    public String getPerPlace() {
        return perPlace;
    }
    /** 籍贯  */
    public void setPerPlace(String perPlace) {
        this.perPlace = perPlace == null ? null : perPlace.trim();
    }
    /** 邮编  */
    public String getPerZipCode() {
        return perZipCode;
    }
    /** 邮编  */
    public void setPerZipCode(String perZipCode) {
        this.perZipCode = perZipCode == null ? null : perZipCode.trim();
    }

    public String getMarry() {
        return marry;
    }

    public void setMarry(String marry) {
        this.marry = marry == null ? null : marry.trim();
    }
    /** 邮箱  */
    public String getPerEmail() {
        return perEmail;
    }
    /** 邮箱 */
    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail == null ? null : perEmail.trim();
    }
    /** 员工类型  */
    public Integer getPerType() {
        return perType;
    }
    /** 员工类型  */
    public void setPerType(Integer perType) {
        this.perType = perType;
    }
    /** 入职日期 */
    public Date getEnterDate() {
        return enterDate;
    }
    /** 入职日期 */
    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }
    /** 转正日期 */
    public Date getFormatDate() {
        return formatDate;
    }
    /** 转正日期 */
    public void setFormatDate(Date formatDate) {
        this.formatDate = formatDate;
    }
    /** 学历 */
    public String getEduLevel() {
        return eduLevel;
    }
    /** 学历 */
    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel == null ? null : eduLevel.trim();
    }
   /** 政治面貌  */
    public String getPoliticalStatus() {
        return politicalStatus;
    }
    /** 政治面貌  */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus == null ? null : politicalStatus.trim();
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
    /** 职务  */
    public String getPositions() {
        return positions;
    }
    /** 职务  */
    public void setPositions(String positions) {
        this.positions = positions == null ? null : positions.trim();
    }
    /** 是否离职  */
    public Byte getIsLeave() {
        return isLeave;
    }
    /** 是否离职  */
    public void setIsLeave(Byte isLeave) {
        this.isLeave = isLeave;
    }
    /** 离职日期  */
    public Date getLeaveDate() {
        return leaveDate;
    }
    /** 离职日期  */
    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }
    /** 离职原因  */
    public String getLeaveCause() {
        return leaveCause;
    }
    /** 离职原因  */
    public void setLeaveCause(String leaveCause) {
        this.leaveCause = leaveCause == null ? null : leaveCause.trim();
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

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName == null ? null : modifyUserName.trim();
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    public Integer getIsload() {
        return isload;
    }

    public void setIsload(Integer isload) {
        this.isload = isload;
    }
    /** 人员头像上传的路径 */
    public String getPerPic() {
        return perPic;
    }
    /** 人员头像上传的路径 */
    public void setPerPic(String perPic) {
        this.perPic = perPic == null ? null : perPic.trim();
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /** 0-正常；1-已删除*/
    public Integer getDelFrag() {
        return delFrag;
    }
    /** 0-正常；1-已删除*/
    public void setDelFrag(Integer delFrag) {
        this.delFrag = delFrag;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }
    /** 上级部门ID */
    public Integer getTopDeptId() {
        return topDeptId;
    }
    /** 上级部门ID */
    public void setTopDeptId(Integer topDeptId) {
        this.topDeptId = topDeptId;
    }
    /** 车位组名称  */
    public String getPlaceName() {
        return placeName;
    }
    /** 车位组名称  */
    public void setPlaceName(String placeName) {
        this.placeName = placeName == null ? null : placeName.trim();
    }
    /** 车位组内车位数量 */
    public Integer getPlaceNum() {
        return placeNum;
    }
    /** 车位组内车位数量 */
    public void setPlaceNum(Integer placeNum) {
        this.placeNum = placeNum;
    }
    /** 车位组内入场车位数量 */
    public Integer getPlaceIn() {
        return placeIn;
    }
    /** 车位组内入场车位数量 */
    public void setPlaceIn(Integer placeIn) {
        this.placeIn = placeIn;
    }
    /** 0-按临时车入场 1-禁止入场 */
    public Byte getpType() {
        return pType;
    }
    /** 0-按临时车入场 1-禁止入场 */
    public void setpType(Byte pType) {
        this.pType = pType;
    }
}
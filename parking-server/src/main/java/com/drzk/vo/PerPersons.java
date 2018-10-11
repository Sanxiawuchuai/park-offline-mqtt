package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class PerPersons implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1407749970537491115L;

	/**
     * 
     */
    private Integer pid;

    /**
     * 编号
     */
    private String perId;

    /**
     * 姓名
     */
    private String perName;

    /**
     * 识别码
     */
    private String perIdCode;

    /**
     * 住址
     */
    private String perAddr;

    /**
     * 微信号
     */
    private String wechatNo;

    /**
     * 手机
     */
    private String perTel;

    /**
     * 座机号
     */
    private String landLineNum;

    /**
     * 身份证
     */
    private String perIdNo;

    /**
     * 性别
     */
    private String sex;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 民族
     */
    private String perNation;

    /**
     * 籍贯
     */
    private String perPlace;

    /**
     * 邮编
     */
    private String perZipCode;

    /**
     * 婚姻状况
     */
    private String marry;

    /**
     * 邮箱
     */
    private String perEmail;

    /**
     * 员工类型
     */
    private Integer perType;

    /**
     * 入职日期
     */
    private Date enterDate;

    /**
     * 转正日期
     */
    private Date formatDate;

    /**
     * 学历
     */
    private String eduLevel;

    /**
     * 政治面貌
     */
    private String politicalStatus;

    /**
     * 部门编号
     */
    private Integer deptId;

    /**
     * 公司编号
     */
    private Integer compId;

    /**
     * 职务
     */
    private String positions;

    /**
     * 是否离职
     */
    private Integer isLeave;

    /**
     * 离职日期
     */
    private Date leaveDate;

    /**
     * 离职原因
     */
    private String leaveCause;

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
     * 备注
     */
    private String memo;

    /**
     * 是否上传
     */
    private Integer isload;
    
    /*
     * 车位组编号
     */
    private Integer placeId;
    
    private String puid;

    private Integer delFrag=0;
    
    

    public String getPuid()
	{
		return puid;
	}

	public void setPuid(String puid)
	{
		this.puid = puid;
	}

    public Integer getDelFrag() {
        return delFrag;
    }

    public void setDelFrag(Integer delFrag) {
        this.delFrag = delFrag;
    }

    /**
     * 
     * @return pid 
     */
    public Integer getPid() {
        return pid;
    }

    /**
     * 
     * @param pid 
     */
    public void setPid(Integer pid) {
        this.pid = pid;
    }

    /**
     * 编号
     * @return per_id 编号
     */
    public String getPerId() {
        return perId;
    }

    /**
     * 编号
     * @param perId 编号
     */
    public void setPerId(String perId) {
        this.perId = perId == null ? null : perId.trim();
    }

    /**
     * 姓名
     * @return per_name 姓名
     */
    public String getPerName() {
        return perName;
    }

    /**
     * 姓名
     * @param perName 姓名
     */
    public void setPerName(String perName) {
        this.perName = perName == null ? null : perName.trim();
    }

    /**
     * 识别码
     * @return per_id_code 识别码
     */
    public String getPerIdCode() {
        return perIdCode;
    }

    /**
     * 识别码
     * @param perIdCode 识别码
     */
    public void setPerIdCode(String perIdCode) {
        this.perIdCode = perIdCode == null ? null : perIdCode.trim();
    }

    /**
     * 住址
     * @return per_addr 住址
     */
    public String getPerAddr() {
        return perAddr;
    }

    /**
     * 住址
     * @param perAddr 住址
     */
    public void setPerAddr(String perAddr) {
        this.perAddr = perAddr == null ? null : perAddr.trim();
    }

    /**
     * 微信号
     * @return wechat_no 微信号
     */
    public String getWechatNo() {
        return wechatNo;
    }

    /**
     * 微信号
     * @param wechatNo 微信号
     */
    public void setWechatNo(String wechatNo) {
        this.wechatNo = wechatNo == null ? null : wechatNo.trim();
    }

    /**
     * 手机
     * @return per_tel 手机
     */
    public String getPerTel() {
        return perTel;
    }

    /**
     * 手机
     * @param perTel 手机
     */
    public void setPerTel(String perTel) {
        this.perTel = perTel == null ? null : perTel.trim();
    }

    /**
     * 座机号
     * @return land_line_num 座机号
     */
    public String getLandLineNum() {
        return landLineNum;
    }

    /**
     * 座机号
     * @param landLineNum 座机号
     */
    public void setLandLineNum(String landLineNum) {
        this.landLineNum = landLineNum == null ? null : landLineNum.trim();
    }

    /**
     * 身份证
     * @return per_id_no 身份证
     */
    public String getPerIdNo() {
        return perIdNo;
    }

    /**
     * 身份证
     * @param perIdNo 身份证
     */
    public void setPerIdNo(String perIdNo) {
        this.perIdNo = perIdNo == null ? null : perIdNo.trim();
    }

    /**
     * 性别
     * @return sex 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 性别
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
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
     * 出生日期
     * @return birthday 出生日期
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * 出生日期
     * @param birthday 出生日期
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * 民族
     * @return per_nation 民族
     */
    public String getPerNation() {
        return perNation;
    }

    /**
     * 民族
     * @param perNation 民族
     */
    public void setPerNation(String perNation) {
        this.perNation = perNation == null ? null : perNation.trim();
    }

    /**
     * 籍贯
     * @return per_place 籍贯
     */
    public String getPerPlace() {
        return perPlace;
    }

    /**
     * 籍贯
     * @param perPlace 籍贯
     */
    public void setPerPlace(String perPlace) {
        this.perPlace = perPlace == null ? null : perPlace.trim();
    }

    /**
     * 邮编
     * @return per_zip_code 邮编
     */
    public String getPerZipCode() {
        return perZipCode;
    }

    /**
     * 邮编
     * @param perZipCode 邮编
     */
    public void setPerZipCode(String perZipCode) {
        this.perZipCode = perZipCode == null ? null : perZipCode.trim();
    }

    /**
     * 婚姻状况
     * @return marry 婚姻状况
     */
    public String getMarry() {
        return marry;
    }

    /**
     * 婚姻状况
     * @param marry 婚姻状况
     */
    public void setMarry(String marry) {
        this.marry = marry == null ? null : marry.trim();
    }

    /**
     * 邮箱
     * @return per_email 邮箱
     */
    public String getPerEmail() {
        return perEmail;
    }

    /**
     * 邮箱
     * @param perEmail 邮箱
     */
    public void setPerEmail(String perEmail) {
        this.perEmail = perEmail == null ? null : perEmail.trim();
    }

    /**
     * 入职日期
     * @return enter_date 入职日期
     */
    public Date getEnterDate() {
        return enterDate;
    }

    /**
     * 入职日期
     * @param enterDate 入职日期
     */
    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }

    /**
     * 转正日期
     * @return format_date 转正日期
     */
    public Date getFormatDate() {
        return formatDate;
    }

    /**
     * 转正日期
     * @param formatDate 转正日期
     */
    public void setFormatDate(Date formatDate) {
        this.formatDate = formatDate;
    }

    /**
     * 学历
     * @return edu_level 学历
     */
    public String getEduLevel() {
        return eduLevel;
    }

    /**
     * 学历
     * @param eduLevel 学历
     */
    public void setEduLevel(String eduLevel) {
        this.eduLevel = eduLevel == null ? null : eduLevel.trim();
    }

    /**
     * 政治面貌
     * @return political_status 政治面貌
     */
    public String getPoliticalStatus() {
        return politicalStatus;
    }

    /**
     * 政治面貌
     * @param politicalStatus 政治面貌
     */
    public void setPoliticalStatus(String politicalStatus) {
        this.politicalStatus = politicalStatus == null ? null : politicalStatus.trim();
    }

    /**
     * 职务
     * @return positions 职务
     */
    public String getPositions() {
        return positions;
    }

    /**
     * 职务
     * @param positions 职务
     */
    public void setPositions(String positions) {
        this.positions = positions == null ? null : positions.trim();
    }


    /**
     * 离职日期
     * @return leave_date 离职日期
     */
    public Date getLeaveDate() {
        return leaveDate;
    }

    /**
     * 离职日期
     * @param leaveDate 离职日期
     */
    public void setLeaveDate(Date leaveDate) {
        this.leaveDate = leaveDate;
    }

    /**
     * 离职原因
     * @return leave_cause 离职原因
     */
    public String getLeaveCause() {
        return leaveCause;
    }

    /**
     * 离职原因
     * @param leaveCause 离职原因
     */
    public void setLeaveCause(String leaveCause) {
        this.leaveCause = leaveCause == null ? null : leaveCause.trim();
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

    /**
     * 是否上传
     * @return isload 是否上传
     */
    public Integer getIsload() {
        return isload;
    }

    /**
     * 是否上传
     * @param isload 是否上传
     */
    public void setIsload(Integer isload) {
        this.isload = isload;
    }

	
	/**
	* placeId.
	*
	* @return the placeId
	* @since JDK 1.8
	*/
	public Integer getPlaceId() {
		return placeId;
	}

	
	/**
	* placeId.
	*
	* @param placeId the placeId to set
	* @since JDK 1.8
	*/
	public void setPlaceId(Integer placeId) {
		this.placeId = placeId;
	}

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public Integer getPerType() {
        return perType;
    }

    public void setPerType(Integer perType) {
        this.perType = perType;
    }

    public Integer getIsLeave() {
        return isLeave;
    }

    public void setIsLeave(Integer isLeave) {
        this.isLeave = isLeave;
    }
}
package com.drzk.online.onlineVo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 车场用户
 * 2018/7/16 cx
 */
public class ParkPersonnelVo implements Serializable {

    public static Map<String,String> map=new HashMap<>(  );
    static {
        map.put( "objectId","puid" );
        map.put( "contactNo","perId" );
        map.put( "contactName","perName" );
        map.put( "contactPhone","perTel" );
        map.put( "idNum","perIdNo" );
        map.put( "sex","sex" );
        map.put( "carNo","carNo" );
        map.put( "birthday","birthday" );
        map.put( "nationality","perNation" );
        map.put( "birthplace","perPlace" );
        map.put( "marry","marry" );
        map.put( "employeeStatus","perType" );
        map.put( "entryDate","enterDate" );
        map.put( "conversionDate","formatDate" );
        map.put( "education","eduLevel" );
        map.put( "createTime","createDate" );
        map.put( "creator","createUserName" );
        map.put( "lastUpdateTime","modifyDate" );
        map.put( "lastUpdateName","modifyUserName" );
        map.put( "deleteCode","delFrag" );

    }

    // 主键id
    private String id;
    //线下id
    private String objectId;
    //项目Id
    private Integer parkingId;
    //项目编号
    private String parkingNo;
    //项目名称
    private String parkingName;

    //上级id
    private String parentId;

    //公司id
    private String companyId;

    //公司名称
    private String companyName;

    //部门id
    private String departmentId;

    //部门名称
    private String departmentName;

    //姓名
    private String contactName;

    //人员编号
    private String contactNo;

    //电话
    private String contactPhone;

    //员工状态 1试用期员工 2 正式员3工  离职员工
    private Integer employeeStatus;

    //入职时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date entryDate;

    //转正时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date conversionDate;

    //出生日期
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date birthday; // 出生日期

    //性别  0 未知 1  男  2  女
    private Integer sex;

    //婚姻状况  1未婚2已婚 3离异
    private Integer marry;

    //学历 1小学2初中3高中4大专5本科以上6其他
    private Integer education;
    //籍贯
    private String birthplace;

    //证件类型 1 身份证 2 护照 3 其他
    private Integer idType;

    //证件号码
    private String idNum;

    //民族
    private String nationality;

    //车牌号码
    private String carNo;

    //紧急联系人
    private String emergencyContact;

    //紧急联系电话
    private String emergencyPhone;

    //车位组id
    private String carportGroupId;

    //车位组名称
    private String carportGroupName;

    //1待同步 2 已同步 3同步失败
    private Integer syncStatus;
    private Integer isLoad=1;
    // 数据来源 线下，线上
    private String dataOrigin;

    private Integer deleteCode=0;
    // 创建者id
    private Integer createId;
    //创建者
    private String creator;
    // 创建时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;
    // 最后更新者id
    private Integer lastUpdateId;
    // 最后更新时间
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date lastUpdateTime;
    // 最后更新者名
    private String lastUpdateName;

    private Integer pageNum; // 页码

    private Integer pageSize; // 页大小

    private Integer skipRow; // 开始页码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingNo() {
        return parkingNo;
    }

    public void setParkingNo(String parkingNo) {
        this.parkingNo = parkingNo;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Integer getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(Integer employeeStatus) {
        this.employeeStatus = employeeStatus;
    }


    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getMarry() {
        return marry;
    }

    public void setMarry(Integer marry) {
        this.marry = marry;
    }

    public Integer getEducation() {
        return education;
    }

    public void setEducation(Integer education) {
        this.education = education;
    }

    public String getBirthplace() {
        return birthplace;
    }

    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public String getCarportGroupId() {
        return carportGroupId;
    }

    public void setCarportGroupId(String carportGroupId) {
        this.carportGroupId = carportGroupId;
    }

    public String getCarportGroupName() {
        return carportGroupName;
    }

    public void setCarportGroupName(String carportGroupName) {
        this.carportGroupName = carportGroupName;
    }

    public Integer getSyncStatus() {
        return syncStatus;
    }

    public void setSyncStatus(Integer syncStatus) {
        this.syncStatus = syncStatus;
    }

    public String getDataOrigin() {
        return dataOrigin;
    }

    public void setDataOrigin(String dataOrigin) {
        this.dataOrigin = dataOrigin;
    }

    public Integer getDeleteCode() {
        return deleteCode;
    }

    public void setDeleteCode(Integer deleteCode) {
        this.deleteCode = deleteCode;
    }

    public Integer getCreateId() {
        return createId;
    }

    public void setCreateId(Integer createId) {
        this.createId = createId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLastUpdateId() {
        return lastUpdateId;
    }

    public void setLastUpdateId(Integer lastUpdateId) {
        this.lastUpdateId = lastUpdateId;
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateName() {
        return lastUpdateName;
    }

    public void setLastUpdateName(String lastUpdateName) {
        this.lastUpdateName = lastUpdateName;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSkipRow() {
        return skipRow;
    }

    public void setSkipRow(Integer skipRow) {
        this.skipRow = skipRow;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public Date getConversionDate() {
        return conversionDate;
    }

    public void setConversionDate(Date conversionDate) {
        this.conversionDate = conversionDate;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Integer getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
    }
}

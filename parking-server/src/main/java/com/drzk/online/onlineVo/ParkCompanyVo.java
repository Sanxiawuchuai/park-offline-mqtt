package com.drzk.online.onlineVo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 车场公司
 * 2018/7/16 cx
 */
public class ParkCompanyVo implements Serializable {

    public static Map<String,String> map=new HashMap<>(  );
    static {
        map.put( "objectId","cuid" );
        map.put( "companyName","companyName" );
        map.put( "remark","memo" );
        map.put( "deleteCode","delFrag" );
        map.put( "createTime","createDate" );
        map.put( "creator","createUserName" );
        map.put( "lastUpdateTime","modifyDate" );
        map.put( "lastUpdateName","modifyUserName" );
        map.put( "companyMail","email" );
        map.put( "companyPhone","compPhone" );
        map.put( "companyContacts","legelPerson" );
        map.put( "companyAddress","companyAddr" );
    }

    // 主键id
    private String id;
    //线下id
    private String objectId;
    //项目编号
    private String parkingNo;
    //项目Id
    private Integer parkingId;
    //项目名称
    private String parkingName;


    //公司名称
    private String companyName;
    //公司英文名称
    private String companyNameEng;
    //公司地址
    private String companyAddress;
    //联系人
    private String companyContacts;
    //联系人电话
    private String contactsPhone;

    //公司电话
    private String companyPhone;
    //备注
    private String remark;
    //状态
    private Integer status;
    //类别
    private Integer type;
    //公司邮箱
    private String companyMail;

    //是否有有部门
    private Integer hasChild;
    private Integer deleteCode;


    //1待同步 2 已同步 3同步失败
    private Integer syncStatus;
    private Integer isLoad=1;
    // 数据来源 线下，线上
    private String dataOrigin;

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

    public String getParkingNo() {
        return parkingNo;
    }

    public void setParkingNo(String parkingNo) {
        this.parkingNo = parkingNo;
    }

    public Integer getParkingId() {
        return parkingId;
    }

    public void setParkingId(Integer parkingId) {
        this.parkingId = parkingId;
    }

    public String getParkingName() {
        return parkingName;
    }

    public void setParkingName(String parkingName) {
        this.parkingName = parkingName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyNameEng() {
        return companyNameEng;
    }

    public void setCompanyNameEng(String companyNameEng) {
        this.companyNameEng = companyNameEng;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyContacts() {
        return companyContacts;
    }

    public void setCompanyContacts(String companyContacts) {
        this.companyContacts = companyContacts;
    }

    public String getContactsPhone() {
        return contactsPhone;
    }

    public void setContactsPhone(String contactsPhone) {
        this.contactsPhone = contactsPhone;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getCompanyMail() {
        return companyMail;
    }

    public void setCompanyMail(String companyMail) {
        this.companyMail = companyMail;
    }

    public Integer getHasChild() {
        return hasChild;
    }

    public void setHasChild(Integer hasChild) {
        this.hasChild = hasChild;
    }

    public Integer getDeleteCode() {
        return deleteCode;
    }

    public void setDeleteCode(Integer deleteCode) {
        this.deleteCode = deleteCode;
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

    public Integer getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
    }
}

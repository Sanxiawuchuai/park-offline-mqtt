package com.drzk.vo;

public class VwDeptCompany {
    private Integer deptId;

    private String deptPuid;

    private String deptName;

    private Integer deptParentid;

    private String deptOrgId;

    private Integer deptUpdateFlag;

    private Integer deptIsLoad;

    private Integer compId;

    private String companyName;

    private String companyCuid;

    private String companyAddr;

    private String email;

    private String compPhone;

    private String legelPerson;

    private String deptParentPuid;

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptPuid() {
        return deptPuid;
    }

    public void setDeptPuid(String deptPuid) {
        this.deptPuid = deptPuid == null ? null : deptPuid.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Integer getDeptParentid() {
        return deptParentid;
    }

    public void setDeptParentid(Integer deptParentid) {
        this.deptParentid = deptParentid;
    }

    public String getDeptOrgId() {
        return deptOrgId;
    }

    public void setDeptOrgId(String deptOrgId) {
        this.deptOrgId = deptOrgId == null ? null : deptOrgId.trim();
    }

    public Integer getDeptUpdateFlag() {
        return deptUpdateFlag;
    }

    public void setDeptUpdateFlag(Integer deptUpdateFlag) {
        this.deptUpdateFlag = deptUpdateFlag;
    }

    public Integer getDeptIsLoad() {
        return deptIsLoad;
    }

    public void setDeptIsLoad(Integer deptIsLoad) {
        this.deptIsLoad = deptIsLoad;
    }

    public Integer getCompId() {
        return compId;
    }

    public void setCompId(Integer compId) {
        this.compId = compId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyCuid() {
        return companyCuid;
    }

    public void setCompanyCuid(String companyCuid) {
        this.companyCuid = companyCuid == null ? null : companyCuid.trim();
    }

    public String getCompanyAddr() {
        return companyAddr;
    }

    public void setCompanyAddr(String companyAddr) {
        this.companyAddr = companyAddr == null ? null : companyAddr.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getCompPhone() {
        return compPhone;
    }

    public void setCompPhone(String compPhone) {
        this.compPhone = compPhone == null ? null : compPhone.trim();
    }

    public String getLegelPerson() {
        return legelPerson;
    }

    public void setLegelPerson(String legelPerson) {
        this.legelPerson = legelPerson == null ? null : legelPerson.trim();
    }

    public String getDeptParentPuid() {
        return deptParentPuid;
    }

    public void setDeptParentPuid(String deptParentPuid) {
        this.deptParentPuid = deptParentPuid == null ? null : deptParentPuid.trim();
    }
}
package com.drzk.service.entity;

import java.io.Serializable;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 线上下发车场授权信息的类
 * @date 2018/10/5 16:41
 */
public class LowerBody implements Serializable {
    private static final long serialVersionUID = -1219620372608498044L;

    private String projectNo;           //车场编号
    private String projectName;         //车场名称
    private String operatorNo;          //运营商编号
    private String license;             //车场的licence
    private String address;             //车场地址
    private Long expiresTime;           //过期日期
    private String version;            //版本号
    private Integer versionType;        //1标准版 2非标版本

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getOperatorNo() {
        return operatorNo;
    }

    public void setOperatorNo(String operatorNo) {
        this.operatorNo = operatorNo;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getExpiresTime() {
        return expiresTime;
    }

    public void setExpiresTime(Long expiresTime) {
        this.expiresTime = expiresTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Integer getVersionType() {
        return versionType;
    }

    public void setVersionType(Integer versionType) {
        this.versionType = versionType;
    }
}

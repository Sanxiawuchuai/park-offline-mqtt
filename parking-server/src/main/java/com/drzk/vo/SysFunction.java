package com.drzk.vo;

import java.util.Date;

public class SysFunction {
    /**
     * 
     */
    private Integer id;

    /**
     * 权限编号(一级01、02,二级0101、0102、0001、0002)
     */
    private String funCode;

    /**
     * 功能名称
     */
    private String funName;

    /**
     * 权限类型(0功能1权限)
     */
    private Byte funType;

    /**
     * 权限值
     */
    private Integer funNo;

    /**
     * 是否有子权限（0代表一个权限，1代表4个权限）
     */
    private Byte funSon;

    /**
     * 模块名称方便程序调用
     */
    private String funModName;

    /**
     * 功能模块权限：1公共/2停車場/3門禁/4消費/5考勤/6巡更/7訪客
     */
    private String funRight;

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
     * 权限编号(一级01、02,二级0101、0102、0001、0002)
     * @return fun_code 权限编号(一级01、02,二级0101、0102、0001、0002)
     */
    public String getFunCode() {
        return funCode;
    }

    /**
     * 权限编号(一级01、02,二级0101、0102、0001、0002)
     * @param funCode 权限编号(一级01、02,二级0101、0102、0001、0002)
     */
    public void setFunCode(String funCode) {
        this.funCode = funCode == null ? null : funCode.trim();
    }

    /**
     * 功能名称
     * @return fun_name 功能名称
     */
    public String getFunName() {
        return funName;
    }

    /**
     * 功能名称
     * @param funName 功能名称
     */
    public void setFunName(String funName) {
        this.funName = funName == null ? null : funName.trim();
    }

    /**
     * 权限类型(0功能1权限)
     * @return fun_type 权限类型(0功能1权限)
     */
    public Byte getFunType() {
        return funType;
    }

    /**
     * 权限类型(0功能1权限)
     * @param funType 权限类型(0功能1权限)
     */
    public void setFunType(Byte funType) {
        this.funType = funType;
    }

    /**
     * 权限值
     * @return fun_no 权限值
     */
    public Integer getFunNo() {
        return funNo;
    }

    /**
     * 权限值
     * @param funNo 权限值
     */
    public void setFunNo(Integer funNo) {
        this.funNo = funNo;
    }

    /**
     * 是否有子权限（0代表一个权限，1代表4个权限）
     * @return fun_son 是否有子权限（0代表一个权限，1代表4个权限）
     */
    public Byte getFunSon() {
        return funSon;
    }

    /**
     * 是否有子权限（0代表一个权限，1代表4个权限）
     * @param funSon 是否有子权限（0代表一个权限，1代表4个权限）
     */
    public void setFunSon(Byte funSon) {
        this.funSon = funSon;
    }

    /**
     * 模块名称方便程序调用
     * @return fun_mod_name 模块名称方便程序调用
     */
    public String getFunModName() {
        return funModName;
    }

    /**
     * 模块名称方便程序调用
     * @param funModName 模块名称方便程序调用
     */
    public void setFunModName(String funModName) {
        this.funModName = funModName == null ? null : funModName.trim();
    }

    /**
     * 功能模块权限：1公共/2停車場/3門禁/4消費/5考勤/6巡更/7訪客
     * @return fun_right 功能模块权限：1公共/2停車場/3門禁/4消費/5考勤/6巡更/7訪客
     */
    public String getFunRight() {
        return funRight;
    }

    /**
     * 功能模块权限：1公共/2停車場/3門禁/4消費/5考勤/6巡更/7訪客
     * @param funRight 功能模块权限：1公共/2停車場/3門禁/4消費/5考勤/6巡更/7訪客
     */
    public void setFunRight(String funRight) {
        this.funRight = funRight == null ? null : funRight.trim();
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
}
package com.drzk.online.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2018/9/12 14:34
 */
public class ParkCarGroup implements Serializable {
    private static final long serialVersionUID = -6802662152290329333L;

    private Integer id;
    private String cuid;                //车位组uuid
    private String placeName;           //车位组名称
    private Integer pType;              //车位组类型
    private Integer placeNum;           //车位数
    private Integer placeIn;
    private Date sDate;
    private Date eDate;
    private Date createDate;
    private String createUserName;
    private Date modifyDate;
    private String modifyUserName;
    private String memo;
    private Integer isLoad;
    private Integer delFrag;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCuid() {
        return cuid;
    }

    public void setCuid(String cuid) {
        this.cuid = cuid;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Integer getpType() {
        return pType;
    }

    public void setpType(Integer pType) {
        this.pType = pType;
    }

    public Integer getPlaceNum() {
        return placeNum;
    }

    public void setPlaceNum(Integer placeNum) {
        this.placeNum = placeNum;
    }

    public Integer getPlaceIn() {
        return placeIn;
    }

    public void setPlaceIn(Integer placeIn) {
        this.placeIn = placeIn;
    }

    public Date getsDate() {
        return sDate;
    }

    public void setsDate(Date sDate) {
        this.sDate = sDate;
    }

    public Date geteDate() {
        return eDate;
    }

    public void seteDate(Date eDate) {
        this.eDate = eDate;
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
        this.createUserName = createUserName;
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
        this.modifyUserName = modifyUserName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Integer getIsLoad() {
        return isLoad;
    }

    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
    }

    public Integer getDelFrag() {
        return delFrag;
    }

    public void setDelFrag(Integer delFrag) {
        this.delFrag = delFrag;
    }
}

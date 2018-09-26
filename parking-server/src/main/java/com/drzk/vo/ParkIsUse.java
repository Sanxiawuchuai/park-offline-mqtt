package com.drzk.vo;

import java.util.Date;

public class ParkIsUse {
    /**
     * 
     */
    private Integer id;

    /**
     * 与开户主表关联
     */
    private Integer yktId;

    /**
     * 卡id
     */
    private String cardId;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 卡类型
     */
    private Byte cardType;

    /**
     * 车牌类型
     */
    private Byte carNoType;

    /**
     * 车位号
     */
    private String carPlace;

    /**
     * 车颜色
     */
    private String carColor;

    /**
     * 开始时间
     */
    private Date startDate;

    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 下载标记(控制器)
     */
    private String downLoad;

    /**
     * 上传标记
     */
    private Byte isLoad;

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
     * 0线下，1线上
     */
    private Byte isUseType;

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
     * 与开户主表关联
     * @return ykt_id 与开户主表关联
     */
    public Integer getYktId() {
        return yktId;
    }

    /**
     * 与开户主表关联
     * @param yktId 与开户主表关联
     */
    public void setYktId(Integer yktId) {
        this.yktId = yktId;
    }

    /**
     * 卡id
     * @return card_id 卡id
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 卡id
     * @param cardId 卡id
     */
    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    /**
     * 卡号
     * @return card_no 卡号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 卡号
     * @param cardNo 卡号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
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
     * 卡类型
     * @return card_type 卡类型
     */
    public Byte getCardType() {
        return cardType;
    }

    /**
     * 卡类型
     * @param cardType 卡类型
     */
    public void setCardType(Byte cardType) {
        this.cardType = cardType;
    }

    /**
     * 车牌类型
     * @return car_no_type 车牌类型
     */
    public Byte getCarNoType() {
        return carNoType;
    }

    /**
     * 车牌类型
     * @param carNoType 车牌类型
     */
    public void setCarNoType(Byte carNoType) {
        this.carNoType = carNoType;
    }

    /**
     * 车位号
     * @return car_place 车位号
     */
    public String getCarPlace() {
        return carPlace;
    }

    /**
     * 车位号
     * @param carPlace 车位号
     */
    public void setCarPlace(String carPlace) {
        this.carPlace = carPlace == null ? null : carPlace.trim();
    }

    /**
     * 车颜色
     * @return car_color 车颜色
     */
    public String getCarColor() {
        return carColor;
    }

    /**
     * 车颜色
     * @param carColor 车颜色
     */
    public void setCarColor(String carColor) {
        this.carColor = carColor == null ? null : carColor.trim();
    }

    /**
     * 开始时间
     * @return start_date 开始时间
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * 开始时间
     * @param startDate 开始时间
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * 结束时间
     * @return end_date 结束时间
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * 结束时间
     * @param endDate 结束时间
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * 下载标记(控制器)
     * @return down_load 下载标记(控制器)
     */
    public String getDownLoad() {
        return downLoad;
    }

    /**
     * 下载标记(控制器)
     * @param downLoad 下载标记(控制器)
     */
    public void setDownLoad(String downLoad) {
        this.downLoad = downLoad == null ? null : downLoad.trim();
    }

    /**
     * 上传标记
     * @return is_load 上传标记
     */
    public Byte getIsLoad() {
        return isLoad;
    }

    /**
     * 上传标记
     * @param isLoad 上传标记
     */
    public void setIsLoad(Byte isLoad) {
        this.isLoad = isLoad;
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
     * 0线下，1线上
     * @return is_use_type 0线下，1线上
     */
    public Byte getIsUseType() {
        return isUseType;
    }

    /**
     * 0线下，1线上
     * @param isUseType 0线下，1线上
     */
    public void setIsUseType(Byte isUseType) {
        this.isUseType = isUseType;
    }
}
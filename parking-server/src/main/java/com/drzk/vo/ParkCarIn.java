package com.drzk.vo;


import java.io.Serializable;
import java.util.Date;

public class ParkCarIn implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6982887142239154088L;

	/**
     * 自增长id
     */
    private Integer id;

    /**
     * 入场唯一标识
     */
    private String guid;

    /**
     * 入场机号
     */
    private Integer machNo;

    /**
     * 开户id
     */
    private Integer yktId;

    /**
     * 卡ID
     */
    private String cardId;

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3)
     */
    private Integer cFlag;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 卡类型
     */
    private Integer cardType;

    /**
     * 车主姓名
     */
    private String empName;

    /**
     * 入场时间
     */
    private Date inTime;

    /**
     * 入场操作员
     */
    private String inUserName;

    /**
     * 主车牌号码
     */
    private String carNo;

    /**
     * 副车牌号码
     */
    private String assistantCarNo;

    /**
     * 修正前的车牌
     */
    private String modifyCarNo;

    /**
     * 主车牌图片路径
     */
    private String inPic;

    /**
     * 副车牌图片路径
     */
    private String backInPic;

    /**
     * 主小车牌图片路径
     */
    private String smallPic;

    /**
     * 副小车牌图片路径
     */
    private String assistantSmallPic;

    /**
     * 0蓝色1黄色2白色3黑色
     */
    private Integer carNoType;

    /**
     * 0大车场1小车场
     */
    private Integer small;

    /**
     * 打折机号
     */
    private String discountNo;

    /**
     * 打折模式id
     */
    private Integer typeId;

    /**
     * 打折时间
     */
    private Date discountTime;

    /**
     * 0未补录1已补录
     */
    private Integer makeup;

    /**
     * 车牌补录人
     */
    private String makeupUserName;

    /**
     * 0未锁1已锁
     */
    private Integer isLocked;

    /**
     * 入场方式0正常入场，1校正入场，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录 6，异常开闸
     */
    private Integer inWay;

    /**
     * 0未上传1正在上传,2已上传成功
     */
    private Integer isLoad=0;

    
    private Integer isDelete=0;
    private Integer isImgUpload=0;
    
    
    
    
    /** 是否已经删除：0正常 1 已删除  */
    public Integer getIsDelete()
	{
		return isDelete;
	}
    /** 是否已经删除：0正常 1 已删除  */
	public void setIsDelete(Integer isDelete)
	{
		this.isDelete = isDelete;
	}

	/**
     * 自增长id
     * @return id 自增长id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增长id
     * @param id 自增长id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 入场唯一标识
     * @return guid 入场唯一标识
     */
    public String getGuid() {
        return guid;
    }

    /**
     * 入场唯一标识
     * @param guid 入场唯一标识
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * 入场机号
     * @return mach_no 入场机号
     */
    public Integer getMachNo() {
        return machNo;
    }

    /**
     * 入场机号
     * @param machNo 入场机号
     */
    public void setMachNo(Integer machNo) {
        this.machNo = machNo;
    }

    /**
     * 开户id
     * @return ykt_id 开户id
     */
    public Integer getYktId() {
        return yktId;
    }

    /**
     * 开户id
     * @param yktId 开户id
     */
    public void setYktId(Integer yktId) {
        this.yktId = yktId;
    }

    /**
     * 卡ID
     * @return card_id 卡ID
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 卡ID
     * @param cardId 卡ID
     */
    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3)
     * @return c_flag 卡介质(0为IC，1为ID，2IC做ID，3)
     */
    public Integer getcFlag() {
        return cFlag;
    }

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3)
     * @param cFlag 卡介质(0为IC，1为ID，2IC做ID，3)
     */
    public void setcFlag(Integer cFlag) {
        this.cFlag = cFlag;
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
     * 卡类型
     * @return card_type 卡类型
     */
    public Integer getCardType() {
        return cardType;
    }

    /**
     * 卡类型
     * @param cardType 卡类型
     */
    public void setCardType(Integer cardType) {
        this.cardType = cardType;
    }

    /**
     * 车主姓名
     * @return emp_name 车主姓名
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 车主姓名
     * @param empName 车主姓名
     */
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    /**
     * 入场时间
     * @return in_time 入场时间
     */
    public Date getInTime() {
        return inTime;
    }

    /**
     * 入场时间
     * @param inTime 入场时间
     */
    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    /**
     * 入场操作员
     * @return in_user_name 入场操作员
     */
    public String getInUserName() {
        return inUserName;
    }

    /**
     * 入场操作员
     * @param inUserName 入场操作员
     */
    public void setInUserName(String inUserName) {
        this.inUserName = inUserName == null ? null : inUserName.trim();
    }

    /**
     * 主车牌号码
     * @return car_no 主车牌号码
     */
    public String getCarNo() {
        return carNo;
    }

    /**
     * 主车牌号码
     * @param carNo 主车牌号码
     */
    public void setCarNo(String carNo) {
        this.carNo = carNo == null ? null : carNo.trim();
    }

    /**
     * 副车牌号码
     * @return assistant_car_no 副车牌号码
     */
    public String getAssistantCarNo() {
        return assistantCarNo;
    }

    /**
     * 副车牌号码
     * @param assistantCarNo 副车牌号码
     */
    public void setAssistantCarNo(String assistantCarNo) {
        this.assistantCarNo = assistantCarNo == null ? null : assistantCarNo.trim();
    }

    /**
     * 修正前的车牌
     * @return modify_car_no 修正前的车牌
     */
    public String getModifyCarNo() {
        return modifyCarNo;
    }

    /**
     * 修正前的车牌
     * @param modifyCarNo 修正前的车牌
     */
    public void setModifyCarNo(String modifyCarNo) {
        this.modifyCarNo = modifyCarNo == null ? null : modifyCarNo.trim();
    }

    /**
     * 主车牌图片路径
     * @return in_pic 主车牌图片路径
     */
    public String getInPic() {
        return inPic;
    }

    /**
     * 主车牌图片路径
     * @param inPic 主车牌图片路径
     */
    public void setInPic(String inPic) {
        this.inPic = inPic == null ? null : inPic.trim();
    }

    /**
     * 副车牌图片路径
     * @return back_in_pic 副车牌图片路径
     */
    public String getBackInPic() {
        return backInPic;
    }

    /**
     * 副车牌图片路径
     * @param backInPic 副车牌图片路径
     */
    public void setBackInPic(String backInPic) {
        this.backInPic = backInPic == null ? null : backInPic.trim();
    }

    /**
     * 主小车牌图片路径
     * @return small_pic 主小车牌图片路径
     */
    public String getSmallPic() {
        return smallPic;
    }

    /**
     * 主小车牌图片路径
     * @param smallPic 主小车牌图片路径
     */
    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic == null ? null : smallPic.trim();
    }

    /**
     * 副小车牌图片路径
     * @return assistant_small_pic 副小车牌图片路径
     */
    public String getAssistantSmallPic() {
        return assistantSmallPic;
    }

    /**
     * 副小车牌图片路径
     * @param assistantSmallPic 副小车牌图片路径
     */
    public void setAssistantSmallPic(String assistantSmallPic) {
        this.assistantSmallPic = assistantSmallPic == null ? null : assistantSmallPic.trim();
    }

    /**
     * 0蓝色1黄色2白色3黑色
     * @return car_no_type 0蓝色1黄色2白色3黑色
     */
    public Integer getCarNoType() {
        return carNoType;
    }

    /**
     * 0蓝色1黄色2白色3黑色
     * @param carNoType 0蓝色1黄色2白色3黑色
     */
    public void setCarNoType(Integer carNoType) {
        this.carNoType = carNoType;
    }

    /**
     * 0大车场1小车场
     * @return small 0大车场1小车场
     */
    public Integer getSmall() {
        return small;
    }

    /**
     * 0大车场1小车场
     * @param small 0大车场1小车场
     */
    public void setSmall(Integer small) {
        this.small = small;
    }

    /**
     * 打折机号
     * @return discount_no 打折机号
     */
    public String getDiscountNo() {
        return discountNo;
    }

    /**
     * 打折机号
     * @param discountNo 打折机号
     */
    public void setDiscountNo(String discountNo) {
        this.discountNo = discountNo;
    }

    /**
     * 打折模式id
     * @return type_id 打折模式id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 打折模式id
     * @param typeId 打折模式id
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * 打折时间
     * @return discount_time 打折时间
     */
    public Date getDiscountTime() {
        return discountTime;
    }

    /**
     * 打折时间
     * @param discountTime 打折时间
     */
    public void setDiscountTime(Date discountTime) {
        this.discountTime = discountTime;
    }

    /**
     * 0未补录1已补录
     * @return makeup 0未补录1已补录
     */
    public Integer getMakeup() {
        return makeup;
    }

    /**
     * 0未补录1已补录
     * @param makeup 0未补录1已补录
     */
    public void setMakeup(Integer makeup) {
        this.makeup = makeup;
    }

    /**
     * 车牌补录人
     * @return makeup_user_name 车牌补录人
     */
    public String getMakeupUserName() {
        return makeupUserName;
    }

    /**
     * 车牌补录人
     * @param makeupUserName 车牌补录人
     */
    public void setMakeupUserName(String makeupUserName) {
        this.makeupUserName = makeupUserName == null ? null : makeupUserName.trim();
    }

    /**
     * 0未锁1已锁
     * @return is_locked 0未锁1已锁
     */
    public Integer getIsLocked() {
        return isLocked;
    }

    /**
     * 0未锁1已锁
     * @param isLocked 0未锁1已锁
     */
    public void setIsLocked(Integer isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * 入场方式0正常入场，1校正入场，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录 6，异常开闸
     * @return in_way 入场方式0正常入场，1校正入场，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录 6，异常开闸
     */
    public Integer getInWay() {
        return inWay;
    }

    /**
     * 入场方式0正常入场，1校正入场，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录 6，异常开闸
     * @param inWay 入场方式0正常入场，1校正入场，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录 6，异常开闸
     */
    public void setInWay(Integer inWay) {
        this.inWay = inWay;
    }

    /**
     * 0未上传1正在上传,2已上传成功
     * @return is_load 0未上传1正在上传,2已上传成功
     */
    public Integer getIsLoad() {
        return isLoad;
    }

    /**
     * 0未上传1正在上传,2已上传成功
     * @param isLoad 0未上传1正在上传,2已上传成功
     */
    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
    }

    public Integer getIsImgUpload() {
        return isImgUpload;
    }

    public void setIsImgUpload(Integer isImgUpload) {
        this.isImgUpload = isImgUpload;
    }

}
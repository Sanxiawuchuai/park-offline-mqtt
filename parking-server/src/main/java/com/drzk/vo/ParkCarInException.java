package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class ParkCarInException implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2299634042664304914L;

	/**
     * 自增长id
     */
    private Integer id;

    /**
     * 控制器编号
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
     * 车主姓名
     */
    private String empName;

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
     * 卡类型
     */
    private Integer cardType;

    /**
     * 0蓝色1黄色2白色3黑色
     */
    private Byte carNoType;

    /**
     * 入场时间
     */
    private Date inTime;

    /**
     * 入场操作员
     */
    private String inUserName;

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
     * 原主车牌图片路径
     */
    private String bInPic;

    /**
     * 原副车牌图片路径
     */
    private String bBackInPic;

    /**
     * 原主小车牌图片路径
     */
    private String bSmallPic;

    /**
     * 原副小车牌图片路径
     */
    private String bAssistantSmallPic;

    /**
     * 原入场时间
     */
    private Date bInTime;

    /**
     * 入场方式0,表示正常出场，1，校正入场 ，2，手工入场 3,非法开闸入场,4,入场回退 5,未授权入场
     */
    private Integer inWay;
    
    private Integer isLoad;
    private Integer isDelete;
    private String guid;
    
    
    public String getGuid()
	{
		return guid;
	}
	public void setGuid(String guid)
	{
		this.guid = guid;
	}
	/** 0未上传；1-已上传 */
    public Integer getIsLoad()
	{
		return isLoad;
	}
    /** 0未上传；1-已上传 */
	public void setIsLoad(Integer isLoad)
	{
		this.isLoad = isLoad;
	}
	/** 0正常；1-删除 */
	public Integer getIsDelete()
	{
		return isDelete;
	}
	/** 0正常；1-删除 */
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
     * 控制器编号
     * @return mach_no 控制器编号
     */
    public Integer getMachNo() {
        return machNo;
    }

    /**
     * 控制器编号
     * @param machNo 控制器编号
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
     * 0蓝色1黄色2白色3黑色
     * @return car_no_type 0蓝色1黄色2白色3黑色
     */
    public Byte getCarNoType() {
        return carNoType;
    }

    /**
     * 0蓝色1黄色2白色3黑色
     * @param carNoType 0蓝色1黄色2白色3黑色
     */
    public void setCarNoType(Byte carNoType) {
        this.carNoType = carNoType;
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
     * 原主车牌图片路径
     * @return b_in_pic 原主车牌图片路径
     */
    public String getbInPic() {
        return bInPic;
    }

    /**
     * 原主车牌图片路径
     * @param bInPic 原主车牌图片路径
     */
    public void setbInPic(String bInPic) {
        this.bInPic = bInPic == null ? null : bInPic.trim();
    }

    /**
     * 原副车牌图片路径
     * @return b_back_in_pic 原副车牌图片路径
     */
    public String getbBackInPic() {
        return bBackInPic;
    }

    /**
     * 原副车牌图片路径
     * @param bBackInPic 原副车牌图片路径
     */
    public void setbBackInPic(String bBackInPic) {
        this.bBackInPic = bBackInPic == null ? null : bBackInPic.trim();
    }

    /**
     * 原主小车牌图片路径
     * @return b_small_pic 原主小车牌图片路径
     */
    public String getbSmallPic() {
        return bSmallPic;
    }

    /**
     * 原主小车牌图片路径
     * @param bSmallPic 原主小车牌图片路径
     */
    public void setbSmallPic(String bSmallPic) {
        this.bSmallPic = bSmallPic == null ? null : bSmallPic.trim();
    }

    /**
     * 原副小车牌图片路径
     * @return b_assistant_small_pic 原副小车牌图片路径
     */
    public String getbAssistantSmallPic() {
        return bAssistantSmallPic;
    }

    /**
     * 原副小车牌图片路径
     * @param bAssistantSmallPic 原副小车牌图片路径
     */
    public void setbAssistantSmallPic(String bAssistantSmallPic) {
        this.bAssistantSmallPic = bAssistantSmallPic == null ? null : bAssistantSmallPic.trim();
    }

    /**
     * 原入场时间
     * @return b_in_time 原入场时间
     */
    public Date getbInTime() {
        return bInTime;
    }

    /**
     * 原入场时间
     * @param bInTime 原入场时间
     */
    public void setbInTime(Date bInTime) {
        this.bInTime = bInTime;
    }

    /**
     * 入场方式0,表示正常出场，1，校正入场 ，2，手工入场 3,非法开闸入场,4,入场回退 5,未授权入场
     * @return in_way 入场方式0,表示正常出场，1，校正入场 ，2，手工入场 3,非法开闸入场,4,入场回退 5,未授权入场
     */
    public Integer getInWay() {
        return inWay;
    }

    /**
     * 入场方式0,表示正常出场，1，校正入场 ，2，手工入场 3,非法开闸入场,4,入场回退 5,未授权入场
     * @param inWay 入场方式0,表示正常出场，1，校正入场 ，2，手工入场 3,非法开闸入场,4,入场回退 5,未授权入场
     */
    public void setInWay(Integer inWay) {
        this.inWay = inWay;
    }
}
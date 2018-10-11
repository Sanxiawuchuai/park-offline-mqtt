package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class VwParkCarOut implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2510893451568909637L;

	/**
     * 
     */
    private Integer id;

    /**
     * 入场guid
     */
    private String inId;

    /**
     * 唯一标识
     */
    private String guid;

    /**
     * 入场机号
     */
    private Byte inMachNo;

    /**
     * 出场机号
     */
    private Byte outMachNo;

    /**
     * 开户id
     */
    private Integer yktId;

    /**
     * 卡id
     */
    private String cardId;

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3)
     */
    private Byte cFlag;

    /**
     * 卡号
     */
    private String cardNo;

    /**
     * 入场主车牌号
     */
    private String inCarNo;

    /**
     * 入场副车牌号
     */
    private String backInCarNo;

    /**
     * 出场主车牌号
     */
    private String outCarNo;

    /**
     * 出场副车牌号
     */
    private String backOutCarNo;

    /**
     * 车牌类型
     */
    private Byte carNoType;

    /**
     * 卡类型
     */
    private Byte cardType;

    /**
     * 免费类型
     */
    private Byte freeType;

    /**
     * 入场时间
     */
    private Date inTime;

    /**
     * 出场时间
     */
    private Date outTime;

    /**
     * 入场主车牌图片
     */
    private String inPic;

    /**
     * 入场副车牌图片
     */
    private String backInPic;

    /**
     * 出场主车牌图片
     */
    private String outPic;

    /**
     * 出场副车牌图片
     */
    private String backOutPic;

    /**
     * 入场操作员
     */
    private String inUserName;

    /**
     * 出场操作员
     */
    private String outUserName;

    /**
     * 证件图片路径
     */
    private String credentialsPic;

    /**
     * 应收金额
     */
    private Float accountCharge;

    /**
     * 实收金额
     */
    private Float payCharge;

    /**
     * 账户金额
     */
    private Float balanceMoney;

    /**
     * 打折机号
     */
    private Byte discountNo;

    /**
     * 折扣id
     */
    private Byte typeId;

    /**
     * 打折时间
     */
    private Date discountTime;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 备注
     */
    private String memo;

    /**
     * 0未上传，1正在上传，2上传成功
     */
    private Byte isLoad;

    /**
     * 
     */
    private Double disAmount;

    /**
     * 
     */
    private Double freeAmount;

    /**
     * 通道名
     */
    private String inChannelName;

    /**
     * 通道名
     */
    private String outChannelName;

    /**
     * 自定名称
     */
    private String cardTypeName;

    /**
     * 出场类型
     */
    private Byte outWay;

    /**
     * 
     */
    private String outWayName;
    private Date centrialTime;
    private Byte isOut;
    
    /** 1-已出场 */
    public Byte getIsOut()
	{
		return isOut;
	}
    /** 1-已出场 */
	public void setIsOut(Byte isOut)
	{
		this.isOut = isOut;
	}

	public Date getCentrialTime()
	{
		return centrialTime;
	}

	public void setCentrialTime(Date centrialTime)
	{
		this.centrialTime = centrialTime;
	}

	/**
     * 免费类型名称
     */
    private String freeName;
    
    private String perName;
    

    public String getPerName()
	{
		return perName;
	}

	public void setPerName(String perName)
	{
		this.perName = perName;
	}

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
     * 入场guid
     * @return in_id 入场guid
     */
    public String getInId() {
        return inId;
    }

    /**
     * 入场guid
     * @param inId 入场guid
     */
    public void setInId(String inId) {
        this.inId = inId == null ? null : inId.trim();
    }

    /**
     * 唯一标识
     * @return guid 唯一标识
     */
    public String getGuid() {
        return guid;
    }

    /**
     * 唯一标识
     * @param guid 唯一标识
     */
    public void setGuid(String guid) {
        this.guid = guid == null ? null : guid.trim();
    }

    /**
     * 入场机号
     * @return in_mach_no 入场机号
     */
    public Byte getInMachNo() {
        return inMachNo;
    }

    /**
     * 入场机号
     * @param inMachNo 入场机号
     */
    public void setInMachNo(Byte inMachNo) {
        this.inMachNo = inMachNo;
    }

    /**
     * 出场机号
     * @return out_mach_no 出场机号
     */
    public Byte getOutMachNo() {
        return outMachNo;
    }

    /**
     * 出场机号
     * @param outMachNo 出场机号
     */
    public void setOutMachNo(Byte outMachNo) {
        this.outMachNo = outMachNo;
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
     * 卡介质(0为IC，1为ID，2IC做ID，3)
     * @return c_flag 卡介质(0为IC，1为ID，2IC做ID，3)
     */
    public Byte getcFlag() {
        return cFlag;
    }

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，3)
     * @param cFlag 卡介质(0为IC，1为ID，2IC做ID，3)
     */
    public void setcFlag(Byte cFlag) {
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
     * 入场主车牌号
     * @return in_car_no 入场主车牌号
     */
    public String getInCarNo() {
        return inCarNo;
    }

    /**
     * 入场主车牌号
     * @param inCarNo 入场主车牌号
     */
    public void setInCarNo(String inCarNo) {
        this.inCarNo = inCarNo == null ? null : inCarNo.trim();
    }

    /**
     * 入场副车牌号
     * @return back_in_car_no 入场副车牌号
     */
    public String getBackInCarNo() {
        return backInCarNo;
    }

    /**
     * 入场副车牌号
     * @param backInCarNo 入场副车牌号
     */
    public void setBackInCarNo(String backInCarNo) {
        this.backInCarNo = backInCarNo == null ? null : backInCarNo.trim();
    }

    /**
     * 出场主车牌号
     * @return out_car_no 出场主车牌号
     */
    public String getOutCarNo() {
        return outCarNo;
    }

    /**
     * 出场主车牌号
     * @param outCarNo 出场主车牌号
     */
    public void setOutCarNo(String outCarNo) {
        this.outCarNo = outCarNo == null ? null : outCarNo.trim();
    }

    /**
     * 出场副车牌号
     * @return back_out_car_no 出场副车牌号
     */
    public String getBackOutCarNo() {
        return backOutCarNo;
    }

    /**
     * 出场副车牌号
     * @param backOutCarNo 出场副车牌号
     */
    public void setBackOutCarNo(String backOutCarNo) {
        this.backOutCarNo = backOutCarNo == null ? null : backOutCarNo.trim();
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
     * 免费类型
     * @return free_type 免费类型
     */
    public Byte getFreeType() {
        return freeType;
    }

    /**
     * 免费类型
     * @param freeType 免费类型
     */
    public void setFreeType(Byte freeType) {
        this.freeType = freeType;
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
     * 出场时间
     * @return out_time 出场时间
     */
    public Date getOutTime() {
        return outTime;
    }

    /**
     * 出场时间
     * @param outTime 出场时间
     */
    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    /**
     * 入场主车牌图片
     * @return in_pic 入场主车牌图片
     */
    public String getInPic() {
        return inPic;
    }

    /**
     * 入场主车牌图片
     * @param inPic 入场主车牌图片
     */
    public void setInPic(String inPic) {
        this.inPic = inPic == null ? null : inPic.trim();
    }

    /**
     * 入场副车牌图片
     * @return back_in_pic 入场副车牌图片
     */
    public String getBackInPic() {
        return backInPic;
    }

    /**
     * 入场副车牌图片
     * @param backInPic 入场副车牌图片
     */
    public void setBackInPic(String backInPic) {
        this.backInPic = backInPic == null ? null : backInPic.trim();
    }

    /**
     * 出场主车牌图片
     * @return out_pic 出场主车牌图片
     */
    public String getOutPic() {
        return outPic;
    }

    /**
     * 出场主车牌图片
     * @param outPic 出场主车牌图片
     */
    public void setOutPic(String outPic) {
        this.outPic = outPic == null ? null : outPic.trim();
    }

    /**
     * 出场副车牌图片
     * @return back_out_pic 出场副车牌图片
     */
    public String getBackOutPic() {
        return backOutPic;
    }

    /**
     * 出场副车牌图片
     * @param backOutPic 出场副车牌图片
     */
    public void setBackOutPic(String backOutPic) {
        this.backOutPic = backOutPic == null ? null : backOutPic.trim();
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
     * 出场操作员
     * @return out_user_name 出场操作员
     */
    public String getOutUserName() {
        return outUserName;
    }

    /**
     * 出场操作员
     * @param outUserName 出场操作员
     */
    public void setOutUserName(String outUserName) {
        this.outUserName = outUserName == null ? null : outUserName.trim();
    }

    /**
     * 证件图片路径
     * @return credentials_pic 证件图片路径
     */
    public String getCredentialsPic() {
        return credentialsPic;
    }

    /**
     * 证件图片路径
     * @param credentialsPic 证件图片路径
     */
    public void setCredentialsPic(String credentialsPic) {
        this.credentialsPic = credentialsPic == null ? null : credentialsPic.trim();
    }

    /**
     * 应收金额
     * @return account_charge 应收金额
     */
    public Float getAccountCharge() {
        return accountCharge;
    }

    /**
     * 应收金额
     * @param accountCharge 应收金额
     */
    public void setAccountCharge(Float accountCharge) {
        this.accountCharge = accountCharge;
    }

    /**
     * 实收金额
     * @return pay_charge 实收金额
     */
    public Float getPayCharge() {
        return payCharge;
    }

    /**
     * 实收金额
     * @param payCharge 实收金额
     */
    public void setPayCharge(Float payCharge) {
        this.payCharge = payCharge;
    }

    /**
     * 账户金额
     * @return balance_money 账户金额
     */
    public Float getBalanceMoney() {
        return balanceMoney;
    }

    /**
     * 账户金额
     * @param balanceMoney 账户金额
     */
    public void setBalanceMoney(Float balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    /**
     * 打折机号
     * @return discount_no 打折机号
     */
    public Byte getDiscountNo() {
        return discountNo;
    }

    /**
     * 打折机号
     * @param discountNo 打折机号
     */
    public void setDiscountNo(Byte discountNo) {
        this.discountNo = discountNo;
    }

    /**
     * 折扣id
     * @return type_id 折扣id
     */
    public Byte getTypeId() {
        return typeId;
    }

    /**
     * 折扣id
     * @param typeId 折扣id
     */
    public void setTypeId(Byte typeId) {
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
     * 订单号
     * @return order_num 订单号
     */
    public String getOrderNum() {
        return orderNum;
    }

    /**
     * 订单号
     * @param orderNum 订单号
     */
    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum == null ? null : orderNum.trim();
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
     * 0未上传，1正在上传，2上传成功
     * @return is_load 0未上传，1正在上传，2上传成功
     */
    public Byte getIsLoad() {
        return isLoad;
    }

    /**
     * 0未上传，1正在上传，2上传成功
     * @param isLoad 0未上传，1正在上传，2上传成功
     */
    public void setIsLoad(Byte isLoad) {
        this.isLoad = isLoad;
    }

    /**
     * 
     * @return dis_amount 
     */
    public Double getDisAmount() {
        return disAmount;
    }

    /**
     * 
     * @param disAmount 
     */
    public void setDisAmount(Double disAmount) {
        this.disAmount = disAmount;
    }

    /**
     * 
     * @return free_amount 
     */
    public Double getFreeAmount() {
        return freeAmount;
    }

    /**
     * 
     * @param freeAmount 
     */
    public void setFreeAmount(Double freeAmount) {
        this.freeAmount = freeAmount;
    }

    /**
     * 通道名
     * @return in_channel_name 通道名
     */
    public String getInChannelName() {
        return inChannelName;
    }

    /**
     * 通道名
     * @param inChannelName 通道名
     */
    public void setInChannelName(String inChannelName) {
        this.inChannelName = inChannelName == null ? null : inChannelName.trim();
    }

    /**
     * 通道名
     * @return out_channel_name 通道名
     */
    public String getOutChannelName() {
        return outChannelName;
    }

    /**
     * 通道名
     * @param outChannelName 通道名
     */
    public void setOutChannelName(String outChannelName) {
        this.outChannelName = outChannelName == null ? null : outChannelName.trim();
    }

    /**
     * 自定名称
     * @return card_type_name 自定名称
     */
    public String getCardTypeName() {
        return cardTypeName;
    }

    /**
     * 自定名称
     * @param cardTypeName 自定名称
     */
    public void setCardTypeName(String cardTypeName) {
        this.cardTypeName = cardTypeName == null ? null : cardTypeName.trim();
    }

    /**
     * 出场类型
     * @return out_way 出场类型
     */
    public Byte getOutWay() {
        return outWay;
    }

    /**
     * 出场类型
     * @param outWay 出场类型
     */
    public void setOutWay(Byte outWay) {
        this.outWay = outWay;
    }

    /**
     * 
     * @return out_way_name 
     */
    public String getOutWayName() {
        return outWayName;
    }

    /**
     * 
     * @param outWayName 
     */
    public void setOutWayName(String outWayName) {
        this.outWayName = outWayName == null ? null : outWayName.trim();
    }

    /**
     * 免费类型名称
     * @return free_name 免费类型名称
     */
    public String getFreeName() {
        return freeName;
    }

    /**
     * 免费类型名称
     * @param freeName 免费类型名称
     */
    public void setFreeName(String freeName) {
        this.freeName = freeName == null ? null : freeName.trim();
    }
}
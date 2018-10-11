package com.drzk.vo;



import com.drzk.common.InOutRealTimeBase;

import java.io.Serializable;
import java.util.Date;

public class ParkCarOut implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3433969904184508218L;

	/**
     * 
     */
    private Integer id;

    /**
     * 唯一标识
     */
    private String guid;

    /**
     * 入场guid
     */
    private String inId;

    /**
     * 入场机号
     */
    private Integer inMachNo;

    /**
     * 出场机号
     */
    private Integer outMachNo;

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
    private Integer cFlag;

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
    private Integer carNoType;

    /**
     * 卡类型
     */
    private Integer cardType = InOutRealTimeBase.TEMP_CAR_A;

    /**
     * 免费类型
     */
    private Integer freeType;

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
     * 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     */
    private Integer payType;

    /**
     * 应收金额
     */
    private Float accountCharge;

    /**
     * 实收金额
     */
    private Float payCharge;

    /**
     * 打折金额
     */
    private Float disAmount;

    /**
     * 账户金额
     */
    private Float balanceMoney;

    /**
     * 打折机号
     */
    private String discountNo;

    /**
     * 折扣id
     */
    private Integer typeId;

    /**
     * 打折时间
     */
    private Date discountTime;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 出场类型
     */
    private Integer outWay;

    /**
     * 备注
     */
    private String memo;

    /**
     * 0未上传，1正在上传，2上传成功
     */
    private Integer isLoad=0;
    
    
    private Date centrialTime;
    private Integer isOut;
    
    private String smallInPic;
    private String smallOutPic;
    private Integer isImgUpload=0;
    

    /** 是否出场（中央收费时为0，出场后更新为1） */
    public Integer getIsOut()
	{
		return isOut;
	}
    /** 是否出场（中央收费时为0，出场后更新为1） */
	public void setIsOut(Integer isOut)
	{
		this.isOut = isOut;
	}

	/** 定点缴费时间 */
    public Date getCentrialTime()
	{
		return centrialTime;
	}

	public void setCentrialTime(Date centrialTime)
	{
		this.centrialTime = centrialTime;
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
     * 入场机号
     * @return in_mach_no 入场机号
     */
    public Integer getInMachNo() {
        return inMachNo;
    }

    /**
     * 入场机号
     * @param inMachNo 入场机号
     */
    public void setInMachNo(Integer inMachNo) {
        this.inMachNo = inMachNo;
    }

    /**
     * 出场机号
     * @return out_mach_no 出场机号
     */
    public Integer getOutMachNo() {
        return outMachNo;
    }

    /**
     * 出场机号
     * @param outMachNo 出场机号
     */
    public void setOutMachNo(Integer outMachNo) {
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
    public Integer getCarNoType() {
        return carNoType;
    }

    /**
     * 车牌类型
     * @param carNoType 车牌类型
     */
    public void setCarNoType(Integer carNoType) {
        this.carNoType = carNoType;
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
     * 免费类型
     * @return free_type 免费类型
     */
    public Integer getFreeType() {
        return freeType;
    }

    /**
     * 免费类型
     * @param freeType 免费类型
     */
    public void setFreeType(Integer freeType) {
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
     * 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     * @return pay_type 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     * @param payType 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
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
     * 打折金额
     * @return dis_amount 打折金额
     */
    public Float getDisAmount() {
        return disAmount;
    }

    /**
     * 打折金额
     * @param disAmount 打折金额
     */
    public void setDisAmount(Float disAmount) {
        this.disAmount = disAmount;
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
     * 折扣id
     * @return type_id 折扣id
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * 折扣id
     * @param typeId 折扣id
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
     * 出场类型
     * @return out_way 出场类型
     */
    public Integer getOutWay() {
        return outWay;
    }

    /**
     * 出场类型
     * @param outWay 出场类型
     */
    public void setOutWay(Integer outWay) {
        this.outWay = outWay;
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
    public Integer getIsLoad() {
        return isLoad;
    }

    /**
     * 0未上传，1正在上传，2上传成功
     * @param isLoad 0未上传，1正在上传，2上传成功
     */
    public void setIsLoad(Integer isLoad) {
        this.isLoad = isLoad;
    }
	
	/**
	* smallInPic.
	*
	* @return the smallInPic
	* @since JDK 1.8
	*/
	public String getSmallInPic() {
		return smallInPic;
	}
	
	/**
	* smallInPic.
	*
	* @param smallInPic the smallInPic to set
	* @since JDK 1.8
	*/
	public void setSmallInPic(String smallInPic) {
		this.smallInPic = smallInPic;
	}
	
	/**
	* smallOutPic.
	*
	* @return the smallOutPic
	* @since JDK 1.8
	*/
	public String getSmallOutPic() {
		return smallOutPic;
	}
	
	/**
	* smallOutPic.
	*
	* @param smallOutPic the smallOutPic to set
	* @since JDK 1.8
	*/
	public void setSmallOutPic(String smallOutPic) {
		this.smallOutPic = smallOutPic;
	}

    public Integer getIsImgUpload() {
        return isImgUpload;
    }

    public void setIsImgUpload(Integer isImgUpload) {
        this.isImgUpload = isImgUpload;
    }
}
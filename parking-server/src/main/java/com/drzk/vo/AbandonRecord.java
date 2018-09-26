package com.drzk.vo;

import java.util.Date;

public class AbandonRecord {
    /**
     * 自增长id
     */
    private Integer id;

    /**
     * 入口机号
     */
    private Byte inMachNo;

    /**
     * 出口机号
     */
    private Byte outMachNo;

    /**
     * 开户ID号
     */
    private Integer yktId;

    /**
     * 关联遥控开闸记录的ID
     */
    private Integer outId;

    /**
     * 卡ID（7Byte）
     */
    private String cardId;

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，4纯车牌)
     */
    private Byte cFlag;

    /**
     * 卡编号
     */
    private String cardNo;

    /**
     * 车主名称
     */
    private String empName;

    /**
     * 车牌号
     */
    private String carNo;

    /**
     * 车牌类型 0蓝色1黄色2白色3黑色
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
     * 入场主车牌图片路径
     */
    private String inPic;

    /**
     * 入场操作员
     */
    private String inUserName;

    /**
     * 定点收费时间
     */
    private Date centralTime;

    /**
     * 出场时间
     */
    private Date outTime;

    /**
     * 出场主车牌图片路径
     */
    private String outPic;

    /**
     * 出场操作员
     */
    private String outUserName;

    /**
     * 证件图片路径
     */
    private String zjPic;

    /**
     * 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     */
    private Byte payType;

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
     * 打折金额
     */
    private Float disAmount;

    /**
     * 应收金额
     */
    private Float accountCharge;

    /**
     * 是否出场（中央收费时为0，出场后更新为1）
     */
    private Byte isOut;

    /**
     * 订单号
     */
    private String orderNum;

    /**
     * 异常原因（1车闸故障2卡遗失等等）
     */
    private String memo;

    /**
     * 出场方式 0,表示正常出场，1，手工放行，2，异常放行
     */
    private Byte outWay;

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
     * 入口机号
     * @return in_mach_no 入口机号
     */
    public Byte getInMachNo() {
        return inMachNo;
    }

    /**
     * 入口机号
     * @param inMachNo 入口机号
     */
    public void setInMachNo(Byte inMachNo) {
        this.inMachNo = inMachNo;
    }

    /**
     * 出口机号
     * @return out_mach_no 出口机号
     */
    public Byte getOutMachNo() {
        return outMachNo;
    }

    /**
     * 出口机号
     * @param outMachNo 出口机号
     */
    public void setOutMachNo(Byte outMachNo) {
        this.outMachNo = outMachNo;
    }

    /**
     * 开户ID号
     * @return ykt_id 开户ID号
     */
    public Integer getYktId() {
        return yktId;
    }

    /**
     * 开户ID号
     * @param yktId 开户ID号
     */
    public void setYktId(Integer yktId) {
        this.yktId = yktId;
    }

    /**
     * 关联遥控开闸记录的ID
     * @return out_id 关联遥控开闸记录的ID
     */
    public Integer getOutId() {
        return outId;
    }

    /**
     * 关联遥控开闸记录的ID
     * @param outId 关联遥控开闸记录的ID
     */
    public void setOutId(Integer outId) {
        this.outId = outId;
    }

    /**
     * 卡ID（7Byte）
     * @return card_id 卡ID（7Byte）
     */
    public String getCardId() {
        return cardId;
    }

    /**
     * 卡ID（7Byte）
     * @param cardId 卡ID（7Byte）
     */
    public void setCardId(String cardId) {
        this.cardId = cardId == null ? null : cardId.trim();
    }

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，4纯车牌)
     * @return c_flag 卡介质(0为IC，1为ID，2IC做ID，4纯车牌)
     */
    public Byte getcFlag() {
        return cFlag;
    }

    /**
     * 卡介质(0为IC，1为ID，2IC做ID，4纯车牌)
     * @param cFlag 卡介质(0为IC，1为ID，2IC做ID，4纯车牌)
     */
    public void setcFlag(Byte cFlag) {
        this.cFlag = cFlag;
    }

    /**
     * 卡编号
     * @return card_no 卡编号
     */
    public String getCardNo() {
        return cardNo;
    }

    /**
     * 卡编号
     * @param cardNo 卡编号
     */
    public void setCardNo(String cardNo) {
        this.cardNo = cardNo == null ? null : cardNo.trim();
    }

    /**
     * 车主名称
     * @return emp_name 车主名称
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * 车主名称
     * @param empName 车主名称
     */
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
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
     * 车牌类型 0蓝色1黄色2白色3黑色
     * @return car_no_type 车牌类型 0蓝色1黄色2白色3黑色
     */
    public Byte getCarNoType() {
        return carNoType;
    }

    /**
     * 车牌类型 0蓝色1黄色2白色3黑色
     * @param carNoType 车牌类型 0蓝色1黄色2白色3黑色
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
     * 入场主车牌图片路径
     * @return in_pic 入场主车牌图片路径
     */
    public String getInPic() {
        return inPic;
    }

    /**
     * 入场主车牌图片路径
     * @param inPic 入场主车牌图片路径
     */
    public void setInPic(String inPic) {
        this.inPic = inPic == null ? null : inPic.trim();
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
     * 定点收费时间
     * @return central_time 定点收费时间
     */
    public Date getCentralTime() {
        return centralTime;
    }

    /**
     * 定点收费时间
     * @param centralTime 定点收费时间
     */
    public void setCentralTime(Date centralTime) {
        this.centralTime = centralTime;
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
     * 出场主车牌图片路径
     * @return out_pic 出场主车牌图片路径
     */
    public String getOutPic() {
        return outPic;
    }

    /**
     * 出场主车牌图片路径
     * @param outPic 出场主车牌图片路径
     */
    public void setOutPic(String outPic) {
        this.outPic = outPic == null ? null : outPic.trim();
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
     * @return zj_pic 证件图片路径
     */
    public String getZjPic() {
        return zjPic;
    }

    /**
     * 证件图片路径
     * @param zjPic 证件图片路径
     */
    public void setZjPic(String zjPic) {
        this.zjPic = zjPic == null ? null : zjPic.trim();
    }

    /**
     * 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     * @return pay_type 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     */
    public Byte getPayType() {
        return payType;
    }

    /**
     * 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     * @param payType 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
     */
    public void setPayType(Byte payType) {
        this.payType = payType;
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
     * 是否出场（中央收费时为0，出场后更新为1）
     * @return is_out 是否出场（中央收费时为0，出场后更新为1）
     */
    public Byte getIsOut() {
        return isOut;
    }

    /**
     * 是否出场（中央收费时为0，出场后更新为1）
     * @param isOut 是否出场（中央收费时为0，出场后更新为1）
     */
    public void setIsOut(Byte isOut) {
        this.isOut = isOut;
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
     * 异常原因（1车闸故障2卡遗失等等）
     * @return memo 异常原因（1车闸故障2卡遗失等等）
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 异常原因（1车闸故障2卡遗失等等）
     * @param memo 异常原因（1车闸故障2卡遗失等等）
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }

    /**
     * 出场方式 0,表示正常出场，1，手工放行，2，异常放行
     * @return out_way 出场方式 0,表示正常出场，1，手工放行，2，异常放行
     */
    public Byte getOutWay() {
        return outWay;
    }

    /**
     * 出场方式 0,表示正常出场，1，手工放行，2，异常放行
     * @param outWay 出场方式 0,表示正常出场，1，手工放行，2，异常放行
     */
    public void setOutWay(Byte outWay) {
        this.outWay = outWay;
    }
}
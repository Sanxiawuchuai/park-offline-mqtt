package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class YktCardRsmoney implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6272140547050938929L;

	private Integer id;

    /**
     * 开户ID号
     */
    private Integer yktid;

    /**
     * 操作类型（0卡发行1卡延期2挂失3解挂4补发5退款6销户）
     */
    private Integer sType;

    /**
     * 前结束日期（延期用）
     */
    private Date frontDate;

    /**
     * 新起始日期（延期用）
     */
    private Date newStartDate;

    /**
     * 新终止日期（延期用)
     */
    private Date newEndDate;

    /**
     * 发生金额
     */
    private Double balanceMoney;

    /**
     * 付款方式(0现金1银联闪付2微信3支付宝)
     */
    private Integer payType;

    /**
     * 押金
     */
    private Double foregift;

    /**
     * 发生前余额（储值卡用）
     */
    private Double beforeMoney;

    /**
     * 操作时间
     */
    private Date createDate;

    /**
     * 操作员
     */
    private String createUserName;

    /**
     * 交易订单号
     */
    private String orderNo;

    /**
     * 备注
     */
    private String memo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getYktid() {
        return yktid;
    }

    public void setYktid(Integer yktid) {
        this.yktid = yktid;
    }

    public Integer getsType() {
        return sType;
    }

    public void setsType(Integer sType) {
        this.sType = sType;
    }

    public Date getFrontDate() {
        return frontDate;
    }

    public void setFrontDate(Date frontDate) {
        this.frontDate = frontDate;
    }

    public Date getNewStartDate() {
        return newStartDate;
    }

    public void setNewStartDate(Date newStartDate) {
        this.newStartDate = newStartDate;
    }

    public Date getNewEndDate() {
        return newEndDate;
    }

    public void setNewEndDate(Date newEndDate) {
        this.newEndDate = newEndDate;
    }

    public Double getBalanceMoney() {
        return balanceMoney;
    }

    public void setBalanceMoney(Double balanceMoney) {
        this.balanceMoney = balanceMoney;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Double getForegift() {
        return foregift;
    }

    public void setForegift(Double foregift) {
        this.foregift = foregift;
    }

    public Double getBeforeMoney() {
        return beforeMoney;
    }

    public void setBeforeMoney(Double beforeMoney) {
        this.beforeMoney = beforeMoney;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }
}
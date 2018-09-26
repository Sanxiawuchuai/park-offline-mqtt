
package com.drzk.fact;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.vo.ParkChannelSet;
import com.drzk.vo.VwParkCarIsuse;

/**
 * ClassName:ARealTimeBase <br>
 * Date: 2018年7月23日 下午3:53:12 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public abstract class AbsRealTimeBase {
	
	private Date nowDate;
	private boolean inOrOutFlag; // true 表示入场 ,false 表示出场
	
	private int carRealType; //车辆实际类型(用来计费的类型,比如月临卡时，这个值存临时卡的类型)
	
	/**
	 * 车辆的大类型,比如月卡，临时卡，免费卡等，对应InOutRealTimeBase 中的 MONTH_CAR等值
	 */
	private int carBigType; 
	
	private String carNo; //车牌号
	
	private ParkChannelSet channelSet;
	
	private Step nextStep; //记录运行步骤

	private VwParkCarIsuse cardInfo;  //车辆卡类型信息
	
	private boolean isSmall; // 是否小车场
	
	private boolean isDeal; //是否处理 用岗亭处理完返回再走规则
	
	private Integer frmType;
	
	/** 
	 *  1.入口校正 2.入口确认开闸 3.定点缴费  4.定点超时  5.出口校正 6.出口开闸 7.收费改变 8.模糊匹配
	 */
	public Integer getFrmType()
	{
		return frmType;
	}
	/** 
	 *  1.入口校正 2.入口确认开闸 3.定点缴费  4.定点超时  5.出口校正 6.出口开闸 7.收费改变 8.模糊匹配
	 */
	public void setFrmType(Integer frmType)
	{
		this.frmType = frmType;
	}

	private  Map<String,Object> statusMap = new HashMap<String, Object>();
	
	

	

	/**
	 * @return the inOrOutFlag
	 */
	public boolean isInOrOutFlag() {
		return inOrOutFlag;
	}

	/**
	 * @param inOrOutFlag the inOrOutFlag to set
	 */
	public void setInOrOutFlag(boolean inOrOutFlag) {
		this.inOrOutFlag = inOrOutFlag;
	}

	/**
	 * @return the carNo
	 */
	public String getCarNo() {
		return carNo;
	}

	/**
	 * @param carNo the carNo to set
	 */
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	/**
	 * @return the nowDate
	 */
	public Date getNowDate() {
		return nowDate;
	}

	/**
	 * @param nowDate the nowDate to set
	 */
	public void setNowDate(Date nowDate) {
		this.nowDate = nowDate;
	}

	/**
	 * @return the carRealType
	 */
	public int getCarRealType() {
		return carRealType;
	}

	/**
	 * @param carRealType the carRealType to set
	 */
	public void setCarRealType(int carRealType) {
		this.carRealType = carRealType;
	}

	/**
	 * @return the carBigType
	 */
	public int getCarBigType() {
		return carBigType;
	}

	/**
	 * @param carBigType the carBigType to set
	 */
	public void setCarBigType(int carBigType) {
		this.carBigType = carBigType;
	}


	/**
	 * @return the channelSet
	 */
	public ParkChannelSet getChannelSet() {
		return channelSet;
	}

	/**
	 * @param channelSet the channelSet to set
	 */
	public void setChannelSet(ParkChannelSet channelSet) {
		this.channelSet = channelSet;
	}

	/**
	 * @return the step
	 */
	public Step getNextStep() {
		return nextStep;
	}

	/**
	 * @param step the step to set
	 */
	public void setNextStep(Step nextStep) {
		this.nextStep = nextStep;
	}

	/**
	 * @return the cardInfo
	 */
	public VwParkCarIsuse getCardInfo() {
		return cardInfo;
	}

	/**
	 * @param cardInfo the cardInfo to set
	 */
	public void setCardInfo(VwParkCarIsuse cardInfo) {
		this.cardInfo = cardInfo;
	}

	/**
	 * @return the isSmall
	 */
	public boolean isSmall() {
		return isSmall;
	}

	/**
	 * @param isSmall the isSmall to set
	 */
	public void setSmall(boolean isSmall) {
		this.isSmall = isSmall;
	}

	/**
	 * @return the isDeal
	 */
	public boolean isDeal() {
		return isDeal;
	}

	/**
	 * @param isDeal the isDeal to set
	 */
	public void setDeal(boolean isDeal) {
		this.isDeal = isDeal;
	}

	/**
	 * @return the statusMap
	 */
	public Map<String, Object> getStatusMap() {
		return statusMap;
	}

	/**
	 * @param statusMap the statusMap to set
	 */
	public void setStatusMap(Map<String, Object> statusMap) {
		this.statusMap = statusMap;
	}
	
	
}

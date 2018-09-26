
package com.drzk.charge;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drzk.charge.vo.PaymentVo;
import com.drzk.common.ParkMethod;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.mapper.ParkCentralChargeMapper;
import com.drzk.mapper.ParkDisInfoMapper;
import com.drzk.mapper.ParkOverTimeSetMapper;
import com.drzk.vo.ParkCentralCharge;
import com.drzk.vo.ParkDisInfo;
import com.drzk.vo.ParkOverTimeSet;

/**
 * ClassName:Charge <br>
 * Date: 2018年6月13日 下午4:44:50 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
public class Charge {
	
	@Autowired
	private ParkCentralChargeMapper centralChargeMapper;
	@Autowired
	ParkDisInfoMapper parkDisInfoMapper;
	@Autowired
	ParkOverTimeSetMapper parkOverTimeSetMapper;
	/**
	 *计费
	 * @param in
	 * @param cardType
	 * @param chargeMode
	 * @param discountId 折扣编号
	 * @param fee 打折前费用
	 * @return
	 */
	public PaymentVo getFeeByCarNo(PaymentVo paymentVo,AbstractChargeStandard chargeMode,String carNo) {
		
		PaymentVo payVo = paymentVo;
		//1, 查询中央缴费记录
		ParkCentralCharge condition = new ParkCentralCharge();
		//condition.setCarNo(carNo);
		condition.setInId(payVo.getInId());
		List<ParkCentralCharge> centralChargerecord = centralChargeMapper.getRecordByCondition(condition);
		//2,若无缴费记录,则计算应缴费用
		int money  = 0;
		if(centralChargerecord == null || centralChargerecord.size() ==0) { //无中央收费
			money  = chargeMode.getCharge(payVo.getPayInTime(), payVo.getPayOutTime(), payVo.getCarRealType());
			payVo.setPaidFee(money);
			payVo.setDisAmount(0);
			payVo.setPaidFee(money);
			payVo.setPayCharge(money);
		}else { //有中央收费
			getOverCharge(payVo,centralChargerecord.get(0),chargeMode);
		}
		return payVo;
	}
	
	/**
	 * @param in
	 * @param cardType
	 * @param chargeMode
	 * @param discountId 折扣编号
	 * @param fee 打折前费用
	 * @return
	 */
	private void getOverCharge(PaymentVo paymentVo, ParkCentralCharge centralRecord,AbstractChargeStandard chargeMode) {
		int money  = 0;
		//获取超时收费规则
		ParkOverTimeSet overRule=parkOverTimeSetMapper.selectByCardType(paymentVo.getCarRealType());
		if(overRule!=null) { //存在收费标准
			money=getOvertimeCharge(centralRecord.getPayChargeTime(),paymentVo.getPayOutTime(),overRule);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
			paymentVo.setRemark("超时缴费，上次缴费时间:"+sdf.format(centralRecord.getPayChargeTime()));
		}else { //不存在按收费标准计费
			money  = chargeMode.getCharge(paymentVo.getInTime(), paymentVo.getOutTime(), paymentVo.getCarRealType());
			money=money-(int)(centralRecord.getAccountCharge()*100);
			paymentVo.setRemark("未设置超时收费标准,按标准收费计算");
		}
		paymentVo.setPaidFee(money);
		paymentVo.setDisAmount(0);
		paymentVo.setPaidFee(money);
		paymentVo.setPayCharge(money);
		paymentVo.setPayType(2); //超时收费
	}
	
	/**
	 * payInTime 计费开始时间
	 *  payOutTime 计费结束时间
	 *rule 超时收费规则
	 * @author chenlong
	 * @return (单位:分)
	 * @since JDK 1.8
	 */
	public int getOvertimeCharge(Date payInTime,Date payOutTime, ParkOverTimeSet rule) {
		int money = 0; 
		if(rule != null && payInTime!=null && payOutTime!=null)
		 {
			if(rule.getOverTimeUnit()==null || rule.getOverTimeAmount()==null)return money=0;
			long timesec = payOutTime.getTime() - payInTime.getTime(); //获得停车时长
			if (timesec > 0) {
				double minu = (double) (timesec / (1000 * 60)); 
				int period = (int) Math.ceil(minu / rule.getOverTimeMinute());
				int periodfree = (int) Math.ceil((minu - rule.getFreeMinute()) / rule.getOverTimeMinute());

				if (rule.getFreeInclude() == 0)
					money = period * rule.getOverTimeAmount().intValue();
				else
					money = periodfree * rule.getOverTimeAmount().intValue();
				return money*100;
			}
		 }
		return money;
	}
	
	
	/** 
	 * 计算打折费用
	 * @param in
	 * @param cardType
	 * @param chargeMode
	 * @param discountID 折扣ID
	 * @param outTime
	 * @return
	 */
	public PaymentVo getDisCountFee(OutRealTimeBase outFact,AbstractChargeStandard chargeMode,String discountID,Date outTime)
	{
		// Date inTime = in.getInTime();
		Double disAmount = null;
		Integer disType = 0;
		PaymentVo retVo = getFeeByCarNo(outFact.getPayMentVo(), chargeMode, outFact.getCarNo());
		Integer pay = retVo.getPaidFee();
		Integer money = pay;
		if (discountID != null) {
			ParkDisInfo disInfo = parkDisInfoMapper.selectByDiscountId(discountID);
			if (disInfo != null) {
				disType = disInfo.getDiscountType();
				disAmount = disInfo.getDiscountAmount().doubleValue();
			}
			if (disType > 0) {
				switch (disType) {
				case 1:  //全免
					money = 0;
					break;
				case 2: //折扣率
					Double mon = (pay * disAmount) / 100;
					money = mon.intValue();
					break;
				case 3:  //免時間
					Integer hours = disAmount.intValue();
					outTime = ParkMethod.GetDateByHours(outTime, -hours);
					retVo = getFeeByCarNo(outFact.getPayMentVo(), chargeMode, outFact.getCarNo());
					money = retVo.getPaidFee();
					break;
				case 4: //免金額
					Double dis = disAmount / 100;
					money = pay - dis.intValue();
					break;
				default:
					money = pay;
					break;
				}
			}
		}
		retVo.setDiscountID(discountID);
		retVo.setDisAmount(pay - money);
		retVo.setPayCharge(money);
		retVo.setPaidFee(pay);
		return retVo;
		
	}
}

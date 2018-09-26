package com.drzk.charge.standard;

import java.lang.reflect.Field;
import java.util.Date;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.drzk.charge.AbstractChargeStandard;
import com.drzk.mapper.ParkStandardChargeMapper;
import com.drzk.utils.DateTimeUtils;
import com.drzk.vo.ParkStandardCharge;

/**
 * ClassName:StandardChargingMode <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年6月13日 下午4:18:29 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
public class StandardChargingMode extends AbstractChargeStandard {
	
	@Autowired
	private ParkStandardChargeMapper mapper;
	
	/** 
	@Override
	public int getCharge(Date startTime, Date endTime, int cardType) {
		// 判断参数合法性 开始时间一定要早于结束时间
		if (startTime == null || endTime == null || startTime.after(endTime))
			return 0;

		// 根据卡类型查询收费标准
		ParkStandardCharge condition = new ParkStandardCharge();
		condition.setCardType((byte)cardType);
		ParkStandardCharge standarCharge = mapper.selectByCondition(condition);

		DateTime startDateTime = new DateTime(startTime.getTime());
		DateTime endDateTime = new DateTime(endTime.getTime());

		// 获取跨段类型 1.按24小时计算 2.按过零点计算
		int aType = standarCharge.getaType();

		int money = 0;
		// 处理跨段
		if (aType == 1) {// 按24小时计算
			// 不管有没有跨24小时，都可以用以下算法
			Period period = new Period(startDateTime, endDateTime,PeriodType.seconds());
			int seconds = period.getSeconds(); //相差秒数
			int days = seconds/(24*60*60);
			int hours = (seconds%(24*60*60))/(60*60);
			int remainder = (seconds%(24*60*60))%(60*60);
			hours = hours + (remainder==0? 0:1);
			
			money = standarCharge.getChHour24() * days + getMoneyByHour(standarCharge, hours);
		} else {// 按过零点计算
			
			//开始时间当天的最后一刻
			DateTime temp = startDateTime.secondOfDay().withMaximumValue();
			DateTime start = startDateTime;
			DateTime end = temp;
			
			//判断当前日期与结束时间是否是同一天
			while(!DateTimeUtils.isTheSameDay(end,endDateTime)) {
				money = money + getSameDayMoney(start.toDate(), end.toDate(), standarCharge);
				temp = temp.plusDays(1);
				start = temp.secondOfDay().withMinimumValue();
				end = temp;
			}
			money = money + getSameDayMoney(start.toDate(), endDateTime.toDate(), standarCharge);
		}

		return money;
	}
	*/

	/**
	 * 
	 * 获取在同一天中两个时段的金额 <br>
	 * 
	 * @author chenlong
	 * @param startTime
	 * @param endTime
	 * @param standarCharge
	 * @return
	 * @since JDK 1.8
	 */
	private int getSameDayMoney(Date startTime, Date endTime, ParkStandardCharge standarCharge) {
		DateTime startDateTime = new DateTime(startTime.getTime());
		DateTime endDateTime = new DateTime(endTime.getTime());

		Period period = new Period(startDateTime, endDateTime,PeriodType.seconds());
		int seconds = period.getSeconds(); //相差秒数
		int divide = seconds/(60*60);
		int remainder = seconds%(60*60);
		
		int hours = divide + (remainder==0? 0:1);
		return getMoneyByHour(standarCharge, hours);
	}

	/**
	 * 
	 * 根据小时,获取对应的金额<br>
	 * 
	 * @author chenlong
	 * @return
	 * @since JDK 1.8
	 */
	public int getMoneyByHour(ParkStandardCharge standarCharge, int hours) {
		if (hours <= 0) {
			return 0;
		}
		String fieldName = "chHour" + hours;
		int money = 0;
		Field field;
		try {
			field = standarCharge.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			Byte obj =  (Byte) field.get(standarCharge);
			money = obj.intValue();
		} catch (Exception e) {
			return 0;
		}
		return money;
	}

	/**
	 * 
	 * 获取跨段共产生的金额 <br>
	 *
	 * @author chenlong
	 * @param startTime
	 * @param endTime
	 * @param acrossMoney
	 * @param aType
	 * @return
	 * @since JDK 1.8
	 */
	public int getAcrossDayMoney(Date startTime, Date endTime, int acrossMoney,int aType) {
		DateTime startDateTime = new DateTime(startTime.getTime());
		DateTime endDateTime = new DateTime(endTime.getTime());
		
		int money = 0;
		if(aType == 1) { //按24小时算
			Period period = new Period(startDateTime, endDateTime,PeriodType.seconds());
			int seconds = period.getSeconds(); //相差秒数
			if(seconds == 0) {
				return 0;
			}
			int divide = seconds/(24*60*60);
			int remainder = seconds%(24*60*60);
			if(remainder == 0) {
				money = money + acrossMoney * (divide-1);
			}else {
				money = money + acrossMoney * divide;
			}
			
		}else { // 按过0点计算
			
			//开始时间当天的最后一刻
			DateTime temp = startDateTime.secondOfDay().withMaximumValue();
			
			while (!temp.isAfter(endDateTime)) {
				money = money + acrossMoney;
				temp = temp.plusDays(1);
			}
		}
		
		return money;
	}
	
	

}

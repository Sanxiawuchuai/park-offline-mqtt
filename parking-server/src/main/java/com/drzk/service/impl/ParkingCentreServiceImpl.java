package com.drzk.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.drzk.charge.AbstractChargeStandard;
import com.drzk.charge.Charge;
import com.drzk.charge.standard2.StandChargeRule;
import com.drzk.charge.vo.PaymentVo;
import com.drzk.common.InOutRealTimeBase;
import com.drzk.common.ParkMethod;
import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.fact.AbsRealTimeBase;
import com.drzk.fact.CentreRealTimeBase;
import com.drzk.fact.InRealTimeBase;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.mapper.ParkFamilyGroupChargeMapper;
import com.drzk.utils.DateTimeUtils;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.LoggerUntils;
import com.drzk.utils.SpringUtil;
import com.drzk.utils.StringUtils;
import com.drzk.vo.ParkCarIn;
import com.drzk.vo.ParkCarOut;
import com.drzk.vo.ParkCentralCharge;
import com.drzk.vo.ParkEffetTimes;
import com.drzk.vo.ParkFamilyGroupCharge;
import com.drzk.vo.VwParkCarIsuse;

/**
 * ClassName:ParkingCentreServiceImpl <br>
 * Date: 2018年9月12日 下午2:28:11 <br>
 * 
 * @author wangchengxi
 * @version
 * @since JDK 1.8
 * @see
 */
@Service("parkingCentreService")
public class ParkingCentreServiceImpl extends AbstractParkingService {

	@KSession("ksession_centre")
	private KieSession kSession;
	
	private static Logger logger = Logger.getLogger("userLog");
	
	@Autowired
	private ParkFamilyGroupChargeMapper parkFamilyGroupChargeMapper;
	//时间格式化
    SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 查询是否有入场记录. <br>
	 * @author wangchengxi
	 * @param carNo    车牌
	 * @return
	 * @since JDK 1.8
	 */
	public boolean getSimilarCar(CentreRealTimeBase centre) {
		logger.debug("getSimilarCar:"+centre.getCarNo());
		boolean isExist=false;
    	try {
    		String carNo = null;
			if (centre != null) {
				carNo = centre.getCarNo();
			}
			if (carNo == null || carNo.isEmpty()) {
				isExist=false;
			}
			if (centre.getInRecord() != null) { //中央
				inRecodeToParkCentre(centre.getInRecord(), centre.getCentreRecord());
				initPayMentVo(centre);
				isExist = true;
			}
			// 先进行精确查找，如果有记录，直接返回
			ParkCarIn in = hasParkInRecord(carNo);
			if (in != null) {
				centre.setInRecord(in);
				inRecodeToParkCentre(in, centre.getCentreRecord());
				initPayMentVo(centre);
				isExist = true;
			}
    	}catch (Exception e) {
    		logger.error("查询是否有入场记录", e);
		}
    	return isExist;
    }
	
	
	/**
	 * 获取入场记录
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	private ParkCarIn hasParkInRecord(String carNo) {
		String rCarNo=carNo.trim().substring(1);
		ParkCarIn inModelGet = parkCarInMapper.selectTop(rCarNo,0);
		return inModelGet;
	}
	/**
	 * 将入场记录的值复制到出场保存实体中
	 * @author chenlong
	 * 2018年9月3日
	 */
	private void inRecodeToParkCentre(ParkCarIn in , ParkCentralCharge centre) {
		//centre.setInCarNo(in.getCarNo());
		centre.setInId(in.getGuid());
		centre.setInMachNo(in.getMachNo()==null ? 0 : in.getMachNo());
		centre.setInPic(in.getInPic());
		centre.setInTime(in.getInTime());
		centre.setInUserName(in.getInUserName());
		centre.setSmallPic(in.getSmallPic());
		centre.setDiscountNo(in.getDiscountNo());
		centre.setDiscountTime(in.getDiscountTime());
		centre.setTypeId(in.getTypeId());
		centre.setcFlag(in.getcFlag());
		centre.setCarNo(in.getCarNo());
		centre.setYktId(in.getYktId());
		centre.setCardType(in.getCardType());
	}
	/**
	 * 将入场记录的值复制到收费对象
	 * @author wangchengxi
	 * 2018年9月3日
	 */
	private void initPayMentVo(CentreRealTimeBase centre) {
		centre.getPayMentVo().setPayInTime(centre.getInRecord().getInTime());
		centre.getPayMentVo().setPayOutTime(centre.getNowDate());
		centre.getPayMentVo().setInTime(centre.getInRecord().getInTime());
		centre.getPayMentVo().setOutTime(centre.getNowDate());
		centre.getPayMentVo().setDiscountID(centre.getInRecord().getDiscountNo());
		centre.getPayMentVo().setDiscountTime(centre.getInRecord().getDiscountTime());
		centre.getPayMentVo().setTypeId(centre.getInRecord().getTypeId());
		centre.getPayMentVo().setInId(centre.getInRecord().getGuid());
		centre.getPayMentVo().setPayType(1);//中央收费
	}
	
	
	/**
	 * 获取车牌的类型
	 * @author wangchengxi
	 * 2018年9月3日
	 */
	@Override
	public int getCardTypeInfo(AbsRealTimeBase fact) {
		logger.debug("getCardTypeInfo :"+fact.getCarNo());
		String carNo = fact.getCarNo();
		Integer cardType = InOutRealTimeBase.TEMP_CAR_A;
		// 如果车牌为空，做临时车处理
		if (StringUtils.isNullOrEempty(carNo)) {
			return cardType;
		}
		try {
			VwParkCarIsuse model = getVwParkCarIsuse(carNo);
			if (model != null) {
				fact.setCardInfo(model); // 授权数据
				cardType = model.getCardType();
				fact.setCarNo(model.getCarNo()); //当前首字识别出错时，
				((CentreRealTimeBase)fact).getCentreRecord().setYktId(model.getYktId());
				((CentreRealTimeBase)fact).getCentreRecord().setCardType(cardType);
				((CentreRealTimeBase)fact).getCentreRecord().setCardNo(model.getCarNo());
			}
		} catch (Exception e) {
			logger.error("获取车牌的类型", e);
		}
		fact.setCarRealType(cardType);
		((CentreRealTimeBase)fact).getPayMentVo().setCarRealType(cardType);
		return cardType;
	}
	
	/** 卡类是否过期 */
	/** wangchengxi
	 * EXPIRE 固定车过期 
	 * UNDUE 未到期 
	 * TIMENOTALLOW 时间段禁止进入 
	 * EFFECTIVE 有效期内
	 */
	@Override
	public Step isCardTypeExpireCentre(CentreRealTimeBase fact) {
		try {
			logger.debug("isCardTypeExpireCentre:"+fact.getCarNo());
			Date inTime = fact.getInRecord().getInTime();// 入场时间
			Date outTime = fact.getNowDate(); //出场时间
			Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
			Date sTime = fact.getCardInfo().getStartDate(); // 开始有效期
			if(!inTime.before(sTime) && !outTime.after(eTime)) //在有效期内
			{
				return getInEffective(fact);
			}else { //不在有效期
				if(!outTime.after(sTime)) { //在有效期之前 所有固定车按临时车处理
					return getBeforeStartDate (fact);
				}
				//入场时间在有效期开始之前，出场时间在有效期之内  按临时车处理
				if(!inTime.after(sTime) && outTime.after(sTime) && outTime.before(eTime)) {
					return getInBeforeStartDate(fact);
				}
				//入场时间在有效期开始之前，出场时间有效期结束之后
				if(inTime.before(sTime) && outTime.after(eTime) ) { 
					return getUnEffective(fact);
				}
				//入场时间在有效期结束之后
				if(inTime.after(eTime)) {
					return getInAfterEndDate(fact);
				}
				//入场时间在有效期内，出场时间在有效期结束之后
				if(inTime.after(sTime) && !inTime.after(eTime) && outTime.after(eTime)) { 
					return getOutAfterEndDate(fact);
				}
			}
			return Step.EFFECTIVE;
		} catch (Exception e) {
			logger.error("中央收费判断是否在有效期内:", e);
			return Step.EFFECTIVE;
		}
	}
	
	/** 固定车入场时间在有效期内，出场时间在有效期结束之后 */
	/** wangchengxi
	 */
	private Step getOutAfterEndDate(CentreRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
		int overDays = Integer.parseInt(GlobalPark.properties.getProperty("FIXED_CAR_EXPIR", "0"));//固定车过期天数
		int monthTemp = Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE", "0"));//启用月临卡功能 	
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(eTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		fact.getPayMentVo().setPayOutTime(outTime); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
			if(monthTemp==0) { //未启用月临卡功能
				if(ParkMethod.GetDate(eTime,overDays).before(fact.getNowDate())) {
					return Step.EXPIRE; //过期
				}else {
					return Step.EFFECTIVE; //按月租车处理
				}
			}else { //启用月临卡
				int carType=setMonthCardType(fact.getCarRealType(),monthTemp);
				fact.setCarRealType(carType);
				fact.getPayMentVo().setCarRealType(carType);
				fact.getPayMentVo().setRemark("月租车过期按月临车计费，计费开始时间:"
				+timeFormat.format(eTime)+",计费结束时间:"+timeFormat.format(outTime));
				fact.getCentreRecord().setCardType(carType-10);
				fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
				return Step.UNEFFECTIVE; //不在有效期内
			}
		case InOutRealTimeBase.FREE_CAR: //免费车
			if(ParkMethod.GetDate(eTime,overDays).before(fact.getNowDate())) {
				return Step.EXPIRE; //过期
			}else {
				return Step.EFFECTIVE;
			}
		case InOutRealTimeBase.STORED_CAR : //储值车	
			int carType=fact.getCarRealType();
			fact.setCarRealType(carType-20);
			fact.getPayMentVo().setCarRealType(carType-20);
			fact.getPayMentVo().setRemark("储值车过期按储临车计费，计费开始时间:"
			+timeFormat.format(eTime)+",计费结束时间:"+timeFormat.format(outTime));
			fact.getCentreRecord().setCardType(carType+10);
			fact.setCarBigType(InOutRealTimeBase.STORED_TEMP_CAR);
			return Step.UNEFFECTIVE; //不在有效期内
	    default:
	    	return Step.EXPIRE; //过期
		}
	}
	
	/** 固定车入场时间在有效期结束之后 */
	/** wangchengxi
	 */
	private Step getInAfterEndDate(CentreRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
		int overDays = Integer.parseInt(GlobalPark.properties.getProperty("FIXED_CAR_EXPIR", "0"));//固定车过期天数
		int monthTemp = Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE", "0"));//启用月临卡功能 	
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		fact.getPayMentVo().setPayOutTime(outTime); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
			if(monthTemp==0) { //未启用月临卡功能
				if(ParkMethod.GetDate(eTime,overDays).before(fact.getNowDate())) {
					return Step.EXPIRE; //过期
				}else {
					return Step.EFFECTIVE; //按月租车处理
				}
			}else { //启用月临卡
				int carType=setMonthCardType(fact.getCarRealType(),monthTemp);
				fact.setCarRealType(carType);
				fact.getPayMentVo().setCarRealType(carType);
				fact.getPayMentVo().setRemark("月租车过期按月临车计费，计费开始时间:"
				+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(outTime));
				fact.getCentreRecord().setCardType(carType-10);
				fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
				return Step.UNEFFECTIVE; //不在有效期内
			}
		case InOutRealTimeBase.FREE_CAR: //免费车
			if(ParkMethod.GetDate(eTime,overDays).before(fact.getNowDate())) {
				return Step.EXPIRE; //过期
			}else {
				return Step.EFFECTIVE;
			}
		case InOutRealTimeBase.STORED_CAR : //储值车	
			int carType=fact.getCarRealType();
			fact.setCarRealType(carType-20);
			fact.getPayMentVo().setCarRealType(carType-20);
			fact.getPayMentVo().setRemark("储值车过期按储临车计费，计费开始时间:"
			+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(outTime));
			fact.getCentreRecord().setCardType(carType+10);
			fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
			return Step.UNEFFECTIVE; //不在有效期内
		default:
			return Step.EXPIRE; //过期
		}
	}
	
	/** 固定车入场时间在有效期开始之前，出场时间有效期结束之后 */
	/** wangchengxi
	 */
	private Step getUnEffective(CentreRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
		Date sTime = fact.getCardInfo().getStartDate(); // 开始有效期
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		int minute=DateTimeUtils.getPeriodMinute(sTime,eTime);
		fact.getPayMentVo().setPayOutTime(DateTimeUtils.dateAddMinute(inTime,minute)); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
		case InOutRealTimeBase.FREE_CAR: //免费车
		case InOutRealTimeBase.STORED_CAR : //储值车
			fact.setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setRemark("固定车过期按临时车计费，计费开始时间:"
			+timeFormat.format(inTime)+",计费结束时间:"
					+timeFormat.format(fact.getPayMentVo().getPayOutTime()));
			fact.getCentreRecord().setCardType(InOutRealTimeBase.TEMP_CAR_A);
			fact.setCarBigType(InOutRealTimeBase.TEMP_CAR);
		}
		return Step.UNDUE;
	}
	
	/** 固定车入场时间在有效期开始之前，出场时间在有效期之内 */
	/** wangchengxi
	 */
	private Step getInBeforeStartDate(CentreRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		Date sTime = fact.getCardInfo().getStartDate(); // 开始有效期
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		fact.getPayMentVo().setPayOutTime(sTime); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
		case InOutRealTimeBase.FREE_CAR: //免费车
		case InOutRealTimeBase.STORED_CAR : //储值车
			fact.setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setRemark("固定车过期按临时车计费，计费开始时间:"
			+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(outTime));
			fact.getCentreRecord().setCardType(InOutRealTimeBase.TEMP_CAR_A);
			fact.setCarBigType(InOutRealTimeBase.TEMP_CAR);
		}
		return Step.UNDUE; 
	}
	/** 固定车入,出场在有效期之前 */
	/** wangchengxi
	 */
	private Step getBeforeStartDate(CentreRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		fact.getPayMentVo().setPayOutTime(outTime); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
		case InOutRealTimeBase.FREE_CAR: //免费车
		case InOutRealTimeBase.STORED_CAR : //储值车
			fact.setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setRemark("固定车过期按临时车计费，计费开始时间:"
			+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(outTime));
			fact.getCentreRecord().setCardType(InOutRealTimeBase.TEMP_CAR_A);
			fact.setCarBigType(InOutRealTimeBase.TEMP_CAR);
		}
		return Step.UNDUE; 
	}
	
	/** 固定车在有效期内 */
	/** wangchengxi
	 */
	private Step getInEffective(CentreRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
		ParkEffetTimes parkEffetTimes = parkEffetTimesMapper.selectByCardType(fact.getCarRealType());
		if(fact.getCarBigType()==1) { //月租车
			if(parkEffetTimes==null) { //不存在时段禁止
				return Step.EFFECTIVE;
			}//&&
			Date start = parkEffetTimes.getbTime(); //开始时间
			Date end = parkEffetTimes.geteTime(); //时段结束时间
			if(parkEffetTimes.getsType()==1) //时间段禁止进入
			{	
				if(DateTimeUtils.isEffectiveDate(fact.getNowDate(), start, end))
					return Step.TIMENOTALLOW; // 时段内禁止入场 ，入场还是月卡处理
			    else
			    	return Step.EFFECTIVE; //按有正常车出入场
			
			}else { //按对应的卡类收费
				int effetTime = DateTimeUtils.countEffectMinute(inTime, outTime, start, end);
				if (effetTime == 0) {
					return Step.EFFECTIVE; // 按有正常车出入场
				} else {
					fact.getPayMentVo().setInTime(inTime); // 实际入场时间
					fact.getPayMentVo().setPayInTime(inTime); // 计费开始时间
					fact.getPayMentVo().setOutTime(outTime); // 实际出场时间
					fact.getPayMentVo().setPayOutTime(DateTimeUtils.dateAddMinute(inTime, effetTime)); // 计费线束时间
					fact.getPayMentVo().setRemark("月租出入受限按临时车计费，计费开始时间:"
					+ timeFormat.format(inTime) + ",计费结束时间:" + 
					timeFormat.format(fact.getPayMentVo().getPayOutTime()));
					int carType = setEffeciTiveCardType(fact.getCarRealType(), parkEffetTimes.getsType());
					fact.setCarRealType(carType);
					fact.getPayMentVo().setCarRealType(carType);
					fact.getCentreRecord().setCardType(carType - 10);
					fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
					return Step.UNEFFECTIVE; // 不在有效期内
				}
			}
		}else {
			return Step.EFFECTIVE;
		}
	}
	
	/**
	 * 判断是家庭组月卡还是临时卡 <br>
	 * @author wangchengxi
	 * @return true 临时，false 月租
	 * @since JDK 1.8
	 */
	public boolean centreFamilyTemp(CentreRealTimeBase fact) {
		try {
			logger.debug("centreFamilyTemp:"+fact.getCarNo());
			int monthTemp = Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE", "0"));//启用月临卡功能 	
			Date inTime = fact.getInRecord().getInTime();// 入场时间
			Date outTime = fact.getNowDate(); //缴费时间
			int inCardType=fact.getInRecord().getCardType();//
			int OutCardType=fact.getCarRealType(); //出场卡类型
			if(inCardType/10==3 && OutCardType/10==1) { //入场临时车，出场月租车
				fact.getPayMentVo().setInTime(inTime); //实际入场时间
				fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
				fact.getPayMentVo().setOutTime(outTime); //实际出场时间
				fact.getPayMentVo().setPayOutTime(outTime); //计费线束时间
				fact.getPayMentVo().setRemark("固定车过期按临时车计费，计费开始时间:"
						+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(outTime));
				int carType=setMonthCardType(fact.getCarRealType(),monthTemp);
				fact.setCarRealType(carType);  //计费类型
				fact.getPayMentVo().setCarRealType(carType);
				fact.getCentreRecord().setCardType(carType-10);  //实际保存的类型
				fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
				return true;
			}
			else {
				return false;
			}
		}catch (Exception e) {
			logger.error("判断是家庭组月卡还是临时卡:", e);
			return false;
		}
	}
    
    /**
	 * 判断是家庭组月卡是否存在临时车计费 <br>
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
    public boolean centreFamilyMonth(CentreRealTimeBase fact) {
    	try {
    		logger.debug("centreFamilyMonth:"+fact.getCarNo());
    		ParkFamilyGroupCharge payModel=new ParkFamilyGroupCharge();
    		payModel.setCarNo(fact.getCarNo());
    		payModel.setInTime(fact.getInRecord().getInTime());
    		ParkFamilyGroupCharge model=parkFamilyGroupChargeMapper.select(payModel);
    		if(model!=null) { //
    			int monthTemp = Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE", "0"));//启用月临卡功能 	
    			Date inTime = fact.getInRecord().getInTime();// 入场时间
    			Date outTime = fact.getNowDate(); //出场时间
    			fact.getPayMentVo().setInTime(inTime); //实际入场时间
    			fact.getPayMentVo().setPayInTime(model.getInTime()); //计费开始时间
    			fact.getPayMentVo().setOutTime(outTime); //实际出场时间
    			fact.getPayMentVo().setPayOutTime(model.getEndTime()); //计费线束时间
    			fact.getPayMentVo().setRemark("家庭组以临时车入场，计费开始时间:"
						+timeFormat.format(model.getInTime())+",计费结束时间:"
    					+timeFormat.format(model.getEndTime()));
				int carType=setMonthCardType(fact.getCarRealType(),monthTemp);
				fact.setCarRealType(carType);
				fact.getPayMentVo().setCarRealType(carType);
				fact.getCentreRecord().setCardType(carType-10);
				fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
				return true;
    		}else {
    			return false;
    		}
    	}catch (Exception e) {
    		logger.error("判断是家庭组月卡是否存在临时车计费:", e);
			return false;
		}
    }
    
    
    @Override
	public boolean isArrears(CentreRealTimeBase fact) {
		boolean isArre=false;
    	try {
    		logger.debug("isArrears:"+fact.getCarNo());
			Charge charge = SpringUtil.getBean(Charge.class);
			AbstractChargeStandard mode = SpringUtil.getBean(StandChargeRule.class);
			PaymentVo vo = charge.getFeeByCarNo(fact.getPayMentVo(), mode, fact.getCarNo()); // 查询是否缴费
			fact.setPayMentVo(vo);
			Integer fee = vo.getPaidFee();
			if(fee <= 0) {
				isArre=true;
			}else {
				isArre=false;
			}	
		} catch (Exception e) {
			logger.error("判断费用是否结清:", e);
		}
    	return isArre;
	}
    
    /**
	 *  储值卡扣费处理, 如果费用够 或者根本不是储值卡,返回true(转到开闸是否要确认出理)<br>
	 *  如果不够用 ，将卡类型转成储临卡,交由计费出理，再算一遍费用, 返回 false
	 *  
	 */
	@Override
	public boolean storecarCharge(CentreRealTimeBase out) {
		logger.debug("storecarCharge:"+out.getCarNo());
		if (out.getCarBigType() != InOutRealTimeBase.STORED_CAR) { // 如果非储值车不需要判断
			return true;
		} else {
			// 余额
			int balance = (int) (out.getCardInfo().getBalanceMoney() * 100);
			// 应缴金额
			int paid = out.getPayMentVo().getPaidFee();

			if (balance < paid) {
				int carType = out.getCarRealType();
				out.setCarRealType(carType +10);
				out.getPayMentVo().setCarRealType(carType - 20);
				out.getCentreRecord().setCardType(carType + 10);
				out.setCarBigType(InOutRealTimeBase.STORED_TEMP_CAR);
				return false;
			}
		}
		return true;
	}
}

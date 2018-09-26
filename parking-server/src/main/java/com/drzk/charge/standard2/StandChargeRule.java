package com.drzk.charge.standard2;

import com.drzk.charge.AbstractChargeStandard;
import com.drzk.charge.bean.ChargPama;
import com.drzk.charge.bean.HourTime;
import com.drzk.charge.bean.ParkStarndardCharge;
import com.drzk.charge.bean.PiecewiseTimes;
import com.drzk.charge.util.DateUtil;
import com.drzk.mapper.ParkStandardChargeMapper;
import com.drzk.vo.ParkStandardCharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by drzk on 2018/06/28.
 */
@Component
public class StandChargeRule extends AbstractChargeStandard {
	@Autowired
	private ParkStandardChargeMapper mapper;

	/**
	 * 计费入口
	 * @param startTime 计费开始时间
	 * @param endTime 计费结束时间
	 * @param cardType 计费卡类型
	 * @return 返回值为分
	 */
	@Override
	public int getCharge(Date startTime, Date endTime, int cardType) {

		// 根据卡类型查询收费标准
		ParkStandardCharge condition = new ParkStandardCharge();
		condition.setCardType(cardType);
		List<ParkStandardCharge> standarCharge = mapper.selectByCondition(condition);
		
		if(standarCharge==null)return 0; //如果没有相应的收费标准直接返回0
		//初始化收费标准
		ParkStarndardCharge<PiecewiseTimes> piecewiseTimes = initParkStarndardCharge(startTime, endTime, standarCharge);
		//算法
		return (int) (mainRule(piecewiseTimes) * 100);
	}

	private ParkStarndardCharge<PiecewiseTimes> initParkStarndardCharge(Date startTime, Date endTime, List<ParkStandardCharge> list) {
		ParkStarndardCharge<PiecewiseTimes> parkStarndardCharge = new ParkStarndardCharge<>();
		List<PiecewiseTimes> piecewiseTimes = new ArrayList<PiecewiseTimes>();
		parkStarndardCharge.setCarInTime(startTime);
		parkStarndardCharge.setCarOutTime(endTime);
		for (int i = 0; i < list.size(); i++) {
			ParkStandardCharge temp = list.get(i);
			PiecewiseTimes piecewiseTimes1 = new PiecewiseTimes();
			piecewiseTimes1.setaType(list.get(i).getaType());
			piecewiseTimes1.setaZero(list.get(i).getaZero());
			piecewiseTimes1.setCrossh0Day(1);
			piecewiseTimes1.setEndTime(list.get(i).getEndTime());
			piecewiseTimes1.setStartTime(list.get(i).getStartTime());
			piecewiseTimes1.setFreeTime(list.get(i).getFreeTime());
			piecewiseTimes1.setIsFreeTime(list.get(i).getIsFreeTime());
			ArrayList<HourTime> hourTimeList = new ArrayList<HourTime>();
			
			//modify by chenlong at 2018-07-09
			//用反射的方式将24个小时对应的金额赋值
			for (int j = 0; j < 24; j++) {
				HourTime hourTime = new HourTime();
				hourTime.setHuorName(j);
				int methodNum = j + 1;
				String methodName = "getChHour" + methodNum;
				try {
					Method method = ParkStandardCharge.class.getMethod(methodName);
					double value = (double)method.invoke(temp);
					 
					hourTime.setCharge(Double.parseDouble(value+""));
				} catch (Exception e) {
					e.printStackTrace();
				}
				hourTimeList.add(hourTime);
			}
			piecewiseTimes1.setHourTimeList(hourTimeList);
			piecewiseTimes1.setIsCrossTime(1);
			piecewiseTimes1.setIsUnitType(list.get(i).getIsUnitType());
			piecewiseTimes1.setTopMoney(list.get(i).getTopMoney());
			piecewiseTimes1.setIsUnitType(list.get(i).getIsUnitType());
			piecewiseTimes.add(piecewiseTimes1);

		}
		parkStarndardCharge.setPiecewiseTimes(piecewiseTimes);
		return parkStarndardCharge;
	}
	/**
	 * 计费
	 * @return 
	 */
	public double mainRule(ParkStarndardCharge parkStarndardCharge) {
		double sumMoney = 0;
		try {
			ChargPama chargPama = new ChargPama();
			//计费开始时间
			chargPama.setInTime(parkStarndardCharge.getCarInTime());
			//计费结束时间
			chargPama.setOutTime(parkStarndardCharge.getCarOutTime());
			
			PiecewiseTimes piecewiseTimes=setFreePiecewiseTimes(parkStarndardCharge,chargPama);
			DateUtil dateUtil=new DateUtil();
			if(piecewiseTimes!=null) {
				piecewiseTimes.setTimeLength(dateUtil.timeRule(piecewiseTimes.getCarInTime(), piecewiseTimes.getCarOutTime()));
				System.out.println("停车总时长:" + piecewiseTimes.getTimeLength());
				if (piecewiseTimes.getaType() == 0) {//24小时计费
					sumMoney = allDayTime(piecewiseTimes);
				} else { //跨0点计x
					sumMoney = crossZero(piecewiseTimes);
				}
			}

		} catch (Exception e) {
			System.out.println("收费异常：" + e.getMessage());
		}
		System.out.println("总金额：" + sumMoney);
		return sumMoney;
	}

	//设置免费时间
	public PiecewiseTimes setFreePiecewiseTimes(ParkStarndardCharge parkStarndardCharge, ChargPama chargPama) {
		PiecewiseTimes piecewiseTimes=(PiecewiseTimes)parkStarndardCharge.getPiecewiseTimes().get(0);

		long carIn=chargPama.getInTime().getTime()+piecewiseTimes.getFreeTime()*1000*60;//计费开始时间加上免费时间
		//在免费时间内
		if(carIn-chargPama.getOutTime().getTime()>=0) {
			System.out.println("入场时间小于免费时间分钟数!");
			return null;
		}
		//免费时间大于0且包括免费时间
		if(piecewiseTimes.getFreeTime()>0 && piecewiseTimes.getIsFreeTime()==0) {
			DateUtil dateUtil=new DateUtil();
			Date carInNew=dateUtil.setDate(carIn);
			//24小时收费 才能做不包括免费时间
			if(piecewiseTimes.getaType() == 0) {
				piecewiseTimes.setCarInTime(carInNew);
			}else {
				piecewiseTimes.setCarInTime(chargPama.getInTime()); //取原始时间
			}
			piecewiseTimes.setCarOutTime(chargPama.getOutTime());
		}
		else {
			piecewiseTimes.setCarInTime(chargPama.getInTime());
			piecewiseTimes.setCarOutTime(chargPama.getOutTime());
		}
		System.out.println("停车免费时长："+piecewiseTimes.getFreeTime());
		return piecewiseTimes;
	}

	//24小时计费
	public double allDayTime(PiecewiseTimes piecewiseTimes) {
		double sumMoney=0;
		try {
			long dayNumber = piecewiseTimes.getTimeLength() / 1440;
			//天收费金额
			sumMoney = dayNumber * piecewiseTimes.getHourTimeList().get(23).getCharge();
			//夸天收费金额
			sumMoney+=piecewiseTimes.getaZero()*dayNumber;
			sumMoney+=hourTime(piecewiseTimes,dayNumber);

			System.out.println("停车天数:"+dayNumber);
		}catch (Exception e){
			System.out.println("总额异常：" + e.getMessage());
		}
		sumMoney=allDayTimeRule(piecewiseTimes,sumMoney);

		return sumMoney;
	}

	public double allDayTimeRule(PiecewiseTimes piecewiseTimes,double sumMoney){
		try{
			if(piecewiseTimes.getIsUnitType()==0)
			{
				sumMoney=(int)sumMoney;
			}
			if(piecewiseTimes.getTopMoney()<sumMoney)
			{
				sumMoney=piecewiseTimes.getTopMoney();
			}

		}catch (Exception e)
		{

			System.out.println("规则判断异常：" + e.getMessage());
		}
		return sumMoney;
	}
	//过0点计费
	public double crossZero(PiecewiseTimes piecewiseTimes) {
		double sumMoney=0;
		try{
			DateUtil dateUtil=new DateUtil();
			long day=piecewiseTimes.getTimeLength()/1440;
			sumMoney=day*piecewiseTimes.getaZero();
			//不足一天却过0点
			long subday=dateUtil.getDaySub(piecewiseTimes.getCarInTime(),piecewiseTimes.getCarOutTime())-day;
			if(subday>0)
			{
				sumMoney+=piecewiseTimes.getaZero();
			}
			sumMoney+=fillTime(piecewiseTimes,day);
			sumMoney=allDayTimeRule(piecewiseTimes,sumMoney);
			System.out.println("停车天数:"+day);
		}catch (Exception e) {
			System.out.println("过0点计费异常："+e.getMessage());
		}
		return sumMoney;
	}
	//过0点剩余时间
	public double fillTime(PiecewiseTimes piecewiseTimes,long day){
		double sumMoney=0;
		try {
			//剩余收费金额
			long overHour = (piecewiseTimes.getTimeLength() - day * 1440) /60;
			if ((piecewiseTimes.getTimeLength() - day * 1440) % 60 > 0) {
				overHour++;
			}
			sumMoney = piecewiseTimes.getHourTimeList().get((int) overHour - 1).getCharge();
			sumMoney+=day*piecewiseTimes.getHourTimeList().get(23).getCharge();
		}catch (Exception e){
			System.out.println("剩余时间补足异常："+e.getMessage());
		}
		return sumMoney;
	}
	//24小时剩余时间
	public double hourTime(PiecewiseTimes piecewiseTimes,long day){
		double sumMoney=0;
		try{
			long overHour=(piecewiseTimes.getTimeLength()-day*1440)/60;
			if((piecewiseTimes.getTimeLength()-day*1440)%60>0)
			{
				overHour++;
			}
			sumMoney = piecewiseTimes.getHourTimeList().get((int) overHour - 1).getCharge();
		}catch (Exception e){
			System.out.println("24小时剩余时间异常:"+e.getMessage());
		}
		return sumMoney;
	}
}

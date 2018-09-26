
package com.drzk.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.joda.time.DateTime;

import com.drzk.charge.bean.ChargPama;
import com.drzk.charge.bean.HourTime;
import com.drzk.charge.util.DatetoString;
import com.drzk.vo.ParkStandardCharge;

/**
 * ClassName:ChargeTest <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年6月27日 下午2:18:39 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class ChargeTest {
	
	/** 
	public static void main(String[] args) {
		

		ChargPama chargPama=new ChargPama();
		chargPama.setCardType(31);
		String string="2018-01-21 2:6:0";
		Date date= DatetoString. (string);
		string="2018-01-22 16:2:0";
		chargPama.setInTime(date);
		date= DatetoString.strToDateLong(string);
		chargPama.setOutTime(date);
		//======================================
		
		StandardCharge standard = new StandardCharge();
		CardType cardType = retCardType();
		
		standard.dateTimeRule(cardType, chargPama);
		
		System.out.println("第一次："+standard.carFeeMoney);
		
		StandardCharge standard1 = new StandardCharge();
		System.out.println("第二次："+standard1.carFeeMoney);
		
	}
	
	public static CardType retCardType() {
		CardType cardType=initPama();
		return  cardType;
		
	}
	public static CardType initPama(){
		List<ParkStandardCharge> list = new ArrayList<ParkStandardCharge>();
		ParkStandardCharge charge = new ParkStandardCharge();
		
		charge.setId(1);
		charge.setaType((byte)1);
		charge.setaZero(100);
		charge.setCardType((byte)31);
		charge.setChHour1((byte)1);
		charge.setChHour2((byte)2);
		charge.setChHour3((byte)3);
		charge.setChHour4((byte)4);
		charge.setChHour5((byte)5);
		charge.setChHour6((byte)6);
		charge.setChHour7((byte)7);
		charge.setChHour8((byte)8);
		charge.setChHour9((byte)9);
		charge.setChHour10((byte)10);
		charge.setChHour11((byte)11);
		charge.setChHour12((byte)12);
		charge.setChHour13((byte)13);
		charge.setChHour14((byte)14);
		charge.setChHour15((byte)15);
		charge.setChHour16((byte)16);
		charge.setChHour17((byte)17);
		charge.setChHour18((byte)18);
		charge.setChHour19((byte)19);
		charge.setChHour20((byte)20);
		charge.setChHour21((byte)21);
		charge.setChHour22((byte)22);
		charge.setChHour23((byte)23);
		charge.setChHour24((byte)24);
		charge.setEndTime(new DateTime(2018, 1, 25, 23, 59, 59, 0).toDate());
		charge.setFreeTime(15);
		charge.setIsAZero((byte)0);
		charge.setIsFreeTime((byte)0);
		charge.setIsUnitType((byte)0);
		charge.setStartTime(new DateTime(2018, 1, 18, 0, 0, 0, 0).toDate());
		charge.setTopMoney(30);
		charge.setUnitType((byte)0);
		list.add(charge);
		CardType cardType=new CardType();
		for(int i=0;i<list.size();i++) {
			cardType.setCardType(list.get(i).getCardType());
			ArrayList<PiecewiseTime> piecewiseTimes = new ArrayList<PiecewiseTime>();
			PiecewiseTime piecewiseTime1 = new PiecewiseTime();
			piecewiseTime1.setCardType(list.get(i).getCardType());
			piecewiseTime1.setPiecewiseStarttime(list.get(i).getStartTime());
			piecewiseTime1.setPiecewiseEndTime(list.get(i).getEndTime());
			piecewiseTime1.setCrossCharge(list.get(i).getaZero());
			//是否包含免费时间点(true: 不含有,false:含有)
			if(list.get(i).getIsFreeTime()==0)
			{
				piecewiseTime1.setNotIncluded(true);
			}
			else
			{
				piecewiseTime1.setNotIncluded(false);
			}
			if (list.get(i).getIsCrossTime() == 0)
			{
				piecewiseTime1.setCrossFillTime(true);
				piecewiseTime1.setCrossh0Day(true);
			}else{
				piecewiseTime1.setCrossFillTime(false);
				piecewiseTime1.setCrossh0Day(false);
			}
			piecewiseTime1.setCrossFillTime(true);
			piecewiseTime1.setCrossh0Day(true);
			piecewiseTime1.setCrossMoney(list.get(i).getaZero());
			if(list.get(i).getaType()==0){
				piecewiseTime1.setCrossType("24小时计算");
			}else
			{
				piecewiseTime1.setCrossType("过0点计算");
			}
			piecewiseTime1.setFreeTime(list.get(i).getFreeTime());
			ArrayList<HourTime>hourTimeList=new ArrayList<HourTime>();
			HourTime hourTime1=new HourTime();
			hourTime1.setHuorName(0);
			hourTime1.setCharge(list.get(i).getChHour1());
			hourTimeList.add(hourTime1);

			HourTime hourTime2=new HourTime();
			hourTime2.setHuorName(1);
			hourTime2.setCharge(list.get(i).getChHour2());
			hourTimeList.add(hourTime2);

			HourTime hourTime3=new HourTime();
			hourTime3.setHuorName(2);
			hourTime3.setCharge(list.get(i).getChHour3());
			hourTimeList.add(hourTime3);

			HourTime hourTime4=new HourTime();
			hourTime4.setHuorName(3);
			hourTime4.setCharge(list.get(i).getChHour4());
			hourTimeList.add(hourTime4);

			HourTime hourTime5=new HourTime();
			hourTime5.setHuorName(4);
			hourTime5.setCharge(list.get(i).getChHour5());
			hourTimeList.add(hourTime5);

			HourTime hourTime6=new HourTime();
			hourTime6.setHuorName(5);
			hourTime6.setCharge(list.get(i).getChHour6());
			hourTimeList.add(hourTime6);

			HourTime hourTime7=new HourTime();
			hourTime7.setHuorName(6);
			hourTime7.setCharge(list.get(i).getChHour7());
			hourTimeList.add(hourTime7);

			HourTime hourTime8=new HourTime();
			hourTime8.setHuorName(7);
			hourTime8.setCharge(list.get(i).getChHour8());
			hourTimeList.add(hourTime8);

			HourTime hourTime9=new HourTime();
			hourTime9.setHuorName(8);
			hourTime9.setCharge(list.get(i).getChHour9());
			hourTimeList.add(hourTime9);

			HourTime hourTime10=new HourTime();
			hourTime10.setHuorName(9);
			hourTime10.setCharge(list.get(i).getChHour10());
			hourTimeList.add(hourTime10);

			HourTime hourTime11=new HourTime();
			hourTime11.setHuorName(10);
			hourTime11.setCharge(list.get(i).getChHour10());
			hourTimeList.add(hourTime11);

			HourTime hourTime12=new HourTime();
			hourTime12.setHuorName(11);
			hourTime12.setCharge(list.get(i).getChHour12());
			hourTimeList.add(hourTime12);

			HourTime hourTime13=new HourTime();
			hourTime13.setHuorName(12);
			hourTime13.setCharge(list.get(i).getChHour13());
			hourTimeList.add(hourTime13);

			HourTime hourTime14=new HourTime();
			hourTime14.setHuorName(13);
			hourTime14.setCharge(list.get(i).getChHour14());
			hourTimeList.add(hourTime14);

			HourTime hourTime15=new HourTime();
			hourTime15.setHuorName(14);
			hourTime15.setCharge(list.get(i).getChHour15());
			hourTimeList.add(hourTime15);

			HourTime hourTime16=new HourTime();
			hourTime15.setHuorName(15);
			hourTime15.setCharge(list.get(i).getChHour16());
			hourTimeList.add(hourTime16);

			HourTime hourTime17=new HourTime();
			hourTime17.setHuorName(16);
			hourTime17.setCharge(list.get(i).getChHour17());
			hourTimeList.add(hourTime17);

			HourTime hourTime18=new HourTime();
			hourTime18.setHuorName(17);
			hourTime18.setCharge(list.get(i).getChHour18());
			hourTimeList.add(hourTime18);

			HourTime hourTime19=new HourTime();
			hourTime19.setHuorName(18);
			hourTime19.setCharge(list.get(i).getChHour19());
			hourTimeList.add(hourTime19);

			HourTime hourTime20=new HourTime();
			hourTime20.setHuorName(19);
			hourTime20.setCharge(list.get(i).getChHour20());
			hourTimeList.add(hourTime20);

			HourTime hourTime21=new HourTime();
			hourTime21.setHuorName(20);
			hourTime21.setCharge(list.get(i).getChHour21());
			hourTimeList.add(hourTime21);

			HourTime hourTime22=new HourTime();
			hourTime22.setHuorName(21);
			hourTime22.setCharge(list.get(i).getChHour22());
			hourTimeList.add(hourTime22);

			HourTime hourTime23=new HourTime();
			hourTime23.setHuorName(22);
			hourTime23.setCharge(list.get(i).getChHour23());
			hourTimeList.add(hourTime23);

			HourTime hourTime24=new HourTime();
			hourTime24.setHuorName(23);
			hourTime24.setCharge(list.get(i).getChHour24());
			hourTimeList.add(hourTime24);

			piecewiseTime1.setHourTimeList(hourTimeList);
			piecewiseTime1.setId(list.get(i).getId());
			piecewiseTime1.setMaxCharge(list.get(i).getTopMoney());

			piecewiseTimes.add(piecewiseTime1);

			cardType.setPiecewises(piecewiseTimes);
		}
		return cardType;
	}
	
	*/

}

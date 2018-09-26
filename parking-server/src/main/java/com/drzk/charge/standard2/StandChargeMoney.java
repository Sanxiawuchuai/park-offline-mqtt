package com.drzk.charge.standard2;

import com.drzk.charge.bean.ChargPama;
import com.drzk.charge.bean.PiecewiseTimes;
import com.drzk.charge.util.DateUtil;
import com.drzk.charge.util.DatetoString;

import java.util.Date;

/**
 * Created by drzk on 2018/06/28.
 */
public class StandChargeMoney {
    //选择计费类型
    public  double ChooseCalculationFunction(PiecewiseTimes piecewiseTime, ChargPama chargPama)
    {
        double money=0;
        DateUtil dateUtil=new DateUtil();
        if(piecewiseTime.getStartTimeLength()<1440 && piecewiseTime.getaType()==0)
        {
            money=calculationH24andLessthanRule(piecewiseTime);
        }
        else if (piecewiseTime.getStartTimeLength()>=1440 && piecewiseTime.getaType()==0)
        {
            money=calculationH24andMorethanRule(piecewiseTime);
        }
        else if (piecewiseTime.getaType()==1 && !dateUtil.dateCompareTime(piecewiseTime))
        {
            money=calculationH0andlessthan(piecewiseTime);
        }
        else if (piecewiseTime.getaType()==1 && dateUtil.dateCompareTime(piecewiseTime))
        {
            money=calculationH0andMorethan(piecewiseTime);
        }
        return money;
    }

    // 类型="24小时计算" 且停车时长小于1440
    public  double calculationH24andLessthanRule(PiecewiseTimes piecewiseTime) {
        // 金额
        double sumMoney = 0;
        DateUtil dateUtil=new DateUtil();
        if(piecewiseTime.getIsFreeTime()==0)
        {
            //piecewiseTime.setTimeLength(piecewiseTime.getTimeLength()-piecewiseTime.getFreeTime());
            long timeLong= dateUtil.timeRule(piecewiseTime.getCarInTime(),piecewiseTime.getCarOutTime());
            piecewiseTime.setTimeLength((int)timeLong);

        }
        // 停车时长获取小时数
        int hour = dateUtil.getHour((int)piecewiseTime.getTimeLength());
        // 通过小时数获取相应金额
        if(hour>0)
        {
            sumMoney = piecewiseTime.getHourTimeList().get(hour - 1).getCharge();
        }
        if(piecewiseTime.getStartTimeLength()>1440)
        {
            sumMoney+=piecewiseTime.getaZero();
        }
        //System.out.println("时间段开始："+piecewiseTime.getStartTime());
        //System.out.println("时间段结束："+piecewiseTime.getEndTime());
        //System.out.println("计费类型："+piecewiseTime.getaType());
        //System.out.println("时间:"+hour);

        return sumMoney;
    }

    // 类型="24小时计算" 且停车时长大于1440
    public  double calculationH24andMorethanRule(PiecewiseTimes piecewiseTime) {
        // 不足天数的分钟数
        int delTime=0;
        //总金额
        double sumMoney = 0;
        DateUtil dateUtil=new DateUtil();
        int TimeLength=piecewiseTime.getStartTimeLength();
        // 获取停车天数
        int timeDay = TimeLength/ 1440;
        //剩余分钟数
        double reMin=TimeLength%1440;
        // 跨段收费金额*天数
        sumMoney=piecewiseTime.getaZero()*timeDay;
        //每天收费
        sumMoney+=timeDay*piecewiseTime.getHourTimeList().get(23).getCharge();
        if((piecewiseTime.getEndTime().getDay()-piecewiseTime.getStartTime().getDay()>=timeDay)|| reMin!=0)
        {

            int sumTimeLength=((int)(piecewiseTime.getTimeLength()))%1440;
            if(sumTimeLength>0)
            {
                if(piecewiseTime.getIsFreeTime()==0)
                {
                    delTime = sumTimeLength % 1440;

                }
                else
                {
                    delTime = sumTimeLength % 1440;
                }
                // 不足天数分钟数对应小时
                int hour = dateUtil.getHour(delTime);
                double  tmpMoney=0;
                if(hour>0)
                {
                    tmpMoney = piecewiseTime.getHourTimeList().get(hour - 1).getCharge();
                    sumMoney+=tmpMoney;
                }
            }
        }
        System.out.println("计费类型："+piecewiseTime.getaType());
        DatetoString datetoString=new DatetoString();
        System.out.println("车辆入场时间："+datetoString.dateToStrLong(piecewiseTime.getCarInTime()));
        System.out.println("车辆出场时间："+datetoString.dateToStrLong(piecewiseTime.getCarOutTime()));
        System.out.println("停车天数:" + timeDay);
        System.out.println("跨段收费金额:" + piecewiseTime.getaZero()*timeDay);
        System.out.println("停车剩余分钟数:" + delTime);
        System.out.println("金额"+sumMoney);
        return sumMoney;
    }

    // 类型="跨0点" ，且0点之前就出场
    public  double calculationH0andlessthan(PiecewiseTimes piecewiseTime) {
        double sumMoney = 0;
        DateUtil dateUtil=new DateUtil();
        // 总分钟数划算成相应小时
        if(piecewiseTime.getIsFreeTime()==0)
        {
            piecewiseTime.setTimeLength(piecewiseTime.getTimeLength());
        }
        int hour = dateUtil.getHour((int)piecewiseTime.getTimeLength());
        sumMoney=piecewiseTime.getHourTimeList().get(hour - 1).getCharge();
        DatetoString datetoString=new DatetoString();
        System.out.println("计费类型："+piecewiseTime.getaType());
        System.out.println("车辆入场时间："+datetoString.dateToStrLong(piecewiseTime.getCarInTime()));
        System.out.println("车辆出场时间："+datetoString.dateToStrLong(piecewiseTime.getCarOutTime()));
        System.out.println("该段停车时长："+piecewiseTime.getTimeLength());
        System.out.println("金额"+sumMoney);


        return sumMoney;

    }
    // 类型="跨0点" ，且跨0点之后继续停车
    // chargPama 进出场
    // 进场当天总分钟 (跨动态汇率段时间不会补齐)
    public  double calculationH0andMorethan(PiecewiseTimes piecewiseTime ) {
        DateUtil dateUtil=new  DateUtil();
        double sumMoney=0;
        System.out.println("计费类型："+piecewiseTime.getaType());
        DatetoString datetoString=new DatetoString();
        System.out.println("车辆入场时间："+datetoString.dateToStrLong(piecewiseTime.getCarInTime()));
        System.out.println("车辆出场时间："+datetoString.dateToStrLong(piecewiseTime.getCarOutTime()));
        System.out.println("该段停车时长："+piecewiseTime.getTimeLength());
        System.out.println("=======================================");
        //入场时间
        Date inTime=piecewiseTime.getCarInTime();
        //出场时间
        Date outTime=piecewiseTime.getCarOutTime();

        int day=outTime.getDate()-inTime.getDate();
        if(day>0)
        {
            sumMoney+=piecewiseTime.getHourTimeList().get(23).getCharge()*(day-1);
        }
        sumMoney+=piecewiseTime.getaZero()*day;
        /*
        * 出入场在同一天
        * */
        if(day==0)
        {
            long min=dateUtil.timeRule(inTime,outTime);
            if(min>0)
            {
                int  hour=dateUtil.getHour((int)min);
                sumMoney+=piecewiseTime.getHourTimeList().get(hour - 1).getCharge();
            }
        }else
        {
            //入场时间
            int  InMIn=1440-(inTime.getHours()*60+inTime.getMinutes());
            int  hour=dateUtil.getHour(InMIn);
            sumMoney+=piecewiseTime.getHourTimeList().get(hour - 1).getCharge();
            if(day>0)
            {
                //出场时间
                int InOut=outTime.getHours()*60+outTime.getMinutes();
                hour=dateUtil.getHour(InOut);
                sumMoney+=piecewiseTime.getHourTimeList().get(hour - 1).getCharge();
            }
        }


        return sumMoney;
    }

    //收费有小数
    public  double calculationDecimalCharges(boolean isYesdecimal,double money)
    {
        if(isYesdecimal)
        {
            return money;
        }
        else
        {
            double t=money-(int)money;
            if(t*10>1)
            {
                money++;
                return (int)money;
            }
            else
            {
                return (int)money;
            }

        }

    }


}

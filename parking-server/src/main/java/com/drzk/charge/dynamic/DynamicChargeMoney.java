package com.drzk.charge.dynamic;

import com.drzk.charge.bean.DayPiecewise;

/**
 * Created by drzk on 2018/06/29.
 */
public class DynamicChargeMoney {
    //周期计算
    public  double periodicRule(DayPiecewise dayPiecewise, long sumMin)
    {
        DynamicChargeTime dynamicChargeTime=new DynamicChargeTime();
        double money=0;
        //停车时长小于一个周期
        long timePeriodic=dynamicChargeTime.timeCompare(dayPiecewise.getEndTime(),dayPiecewise.getStartTime());
        if(sumMin>timePeriodic)
        {
            sumMin=timePeriodic;
        }
        //周期计算方法
        money=billingCycleMode(dayPiecewise,sumMin);
        //最大值
        money=cycleMaxMoney(dayPiecewise,money);
        return money;
    }
    //判断计费类型
    public  double calculationMoney(DayPiecewise dayPiecewise, long timeLength)
    {
        double money=0;
        if(dayPiecewise.getBillingMode()=="按时间计费")
        {
            money=calculationMinMoney(dayPiecewise,timeLength);
        }
        else
        {
            money=calculationFrequencyMoney(dayPiecewise);
        }
        return money;
    }
    //"按单位时间" 计算分钟段金额
    public  double calculationMinMoney(DayPiecewise dayPiecewise,long timeLength){

        int min=dayPiecewise.getMinPiecewise().getMin();
        double money=0;
        if(timeLength%min==0)
        {
            money=timeLength/min*dayPiecewise.getMinPiecewise().getMoney();
        }
        else
        {
            money=(timeLength/min+1)*dayPiecewise.getMinPiecewise().getMoney();
        }
        money=retMaxMoney(dayPiecewise,money);
        return money;
    }
    //"按次计费" 计算分钟段金额金额
    public  double calculationFrequencyMoney(DayPiecewise dayPiecewise)
    {
        double money=0;
        try {
            money=dayPiecewise.getMinPiecewise().getMoney();
        } catch (Exception e) {
            // TODO: handle exception
        }
        return money;
    }
    //判断当天是否超过应收金额的最大值
    public  double retMaxMoney(DayPiecewise dayPiecewise,double money)
    {
        if(money>dayPiecewise.getMinPiecewise().getMaxMoney())
        {
            money=dayPiecewise.getMinPiecewise().getMaxMoney();
        }
        return money;
    }


    //周期计费方法
    public  double billingCycleMode(DayPiecewise dayPiecewise,long timeLength) {
        //循环
        boolean retBillingCycle=true;
        double sumMoney=0;
        //周期段计算
        int paragraphI=0;
        while (retBillingCycle) {
			if(timeLength<=0)
            {
                retBillingCycle=false;
            }
            if(paragraphI<dayPiecewise.getMinPiecewise().getTimeLists().size())
            {

                if((timeLength-dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getTimeLength())>0)
                {
                    timeLength-=dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getTimeLength();
                    int length=dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getTimeLength()/dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getTimeUnit();
                    if(dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getTimeLength()%dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getTimeUnit()>0)
                    {
                        length++;
                    }
                    sumMoney+=length*dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getMoney();
                }
                else
                {
                    int length=(int)timeLength/dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getTimeUnit();
                    if(timeLength%dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getTimeUnit()>0)
                    {
                        length++;
                    }
                    sumMoney+=length*dayPiecewise.getMinPiecewise().getTimeLists().get(paragraphI).getMoney();
                    timeLength=0;
                }
                paragraphI++;
            }else{
			    if(dayPiecewise.isIsfirstParagraph()) {
                    paragraphI = 0;
                }else{
                    paragraphI = 1;
                }
            }
        }
        return sumMoney;
    }
    //周期内是否超出最大金额
    public  double cycleMaxMoney(DayPiecewise dayPiecewise,double sumMoney)
    {
        if(sumMoney>dayPiecewise.getMinPiecewise().getMaxMoney())
        {
            sumMoney=dayPiecewise.getMinPiecewise().getMaxMoney();
        }
        return sumMoney;
    }
}

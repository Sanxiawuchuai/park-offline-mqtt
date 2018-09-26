package com.drzk.charge.dynamic;

import com.drzk.charge.bean.*;
import com.drzk.charge.util.DateUtil;
import com.drzk.charge.util.DatetoString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;

/**
 * Created by drzk on 2018/06/29.
 */
public class DynamicChargeRule {

    public double mainRule(ParkStarndardCharge<DynamicPicwise> dynamicCharge) {
        DynamicParam dynamicParam = new DynamicParam();
        dynamicParam.setIs_FreeTime(false);
        // double sumMoney = 0;
        try {
            ChargPama chargPama = new ChargPama();
            chargPama.setInTime(dynamicCharge.getCarInTime());
            chargPama.setOutTime(dynamicCharge.getCarOutTime());
            List<DynamicPicwise> piecewiseTimesList = dateTimeRule(dynamicCharge.getPiecewiseTimes(), chargPama);
            System.out.println("车辆入场时间：" + dynamicCharge.getCarInTime());
            System.out.println("车辆出场时间：" + dynamicCharge.getCarOutTime());
            if (chargPama.getInTime().after(chargPama.getOutTime())) {
                System.out.println("车辆出场时间早于车辆入场时间！");
                //return sumMoney;
                dynamicParam.getSumMoney();
            }
            chargPama.setInTime(chargPama.getInTime());
            DynamicChargeTime dynamicChargeTime = new DynamicChargeTime();
            int crossDay = dynamicChargeTime.crossDay(chargPama);
            System.out.println("停车天数：" + crossDay);
            System.out.println("===========================");
            //是否最后一个日期段
            boolean isFinally = false;
            //跨日期段补时分钟数
            long surplusMinDate = 0;

            for (int i = 0; i < piecewiseTimesList.size(); i++) {
                if (dynamicChargeTime.compareTime(chargPama.getInTime(), piecewiseTimesList.get(i).getStartTime()) >= 0
                        ) {
                    //不是最后一个日期段
                    if (i < (piecewiseTimesList.size() - 1)) {
                        //设置日期段结束时间23:59:59
                        Date dateOut = dynamicChargeTime.setOutDate(
                                piecewiseTimesList.get(i).getStartTime(), chargPama.getOutTime());
                        chargPama.setOutTime(dateOut);
                        //日期段跨段补时
                        if (piecewiseTimesList.get(i).getIs_month_cross() == 0) {
                            for (int j = 0; j < piecewiseTimesList.get(i).getDayPiecewiseList().size(); j++) {
                                if (dynamicChargeTime.timeCompare(piecewiseTimesList.get(i).getDayPiecewiseList().get(j).getStartTime(), chargPama.getInTime()) <= 0
                                        && dynamicChargeTime.timeCompare(piecewiseTimesList.get(i).getDayPiecewiseList().get(j).getEndTime(), chargPama.getInTime()) > 0
                                        ) {

                                    surplusMinDate = (chargPama.getInTime().getHours() * 60 + chargPama.getInTime().getMinutes()) % piecewiseTimesList.get(i).getDayPiecewiseList().get(j).getMinPiecewise().getMin();
                                    chargPama.getOutTime().setTime(chargPama.getOutTime().getTime() + surplusMinDate * 60 * 1000 + 1000);
                                }
                            }
                        }
                    } else {

                        chargPama.setOutTime(dynamicCharge.getCarOutTime());
                        isFinally = true;
                        dynamicParam = daysTimeRule(piecewiseTimesList.get(i), chargPama, isFinally, dynamicParam);
                    }
                }
            }
            System.out.println("总金额:" + dynamicParam.getSumMoney());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
        //return sumMoney;
        return dynamicParam.getSumMoney();
    }

    // 返回日期段
    public List<DynamicPicwise> dateTimeRule(List<DynamicPicwise> dynamicPicwiseList, ChargPama chargPama) {
        try {

            List<DynamicPicwise> dynamicPicwises = new ArrayList<DynamicPicwise>();
            dynamicPicwises = decompositionTimePiecewises(dynamicPicwiseList, chargPama, dynamicPicwises);
            return dynamicPicwises;

        } catch (Exception e) {
            System.out.println("日期分段异常：" + e.getMessage());
        }
        return null;

    }

    // 获取日期段 (跨日期段：时间不会补齐)
    //piecewiseTimesList (cardType中参数)
    //chargPama (车辆信息)
    //piecewiseTimess(返回参数)
    public List<DynamicPicwise> decompositionTimePiecewises(List<DynamicPicwise> piecewiseTimesList,
                                                            ChargPama chargPama, List<DynamicPicwise> piecewiseTimess) {
        DynamicChargeTime dynamicChargeTime = new DynamicChargeTime();
        // 入场时间
        Date dateInTime = chargPama.getInTime();
        // 出场时间
        Date dateOutTime = chargPama.getOutTime();
        for (int i = 0; i < piecewiseTimesList.size(); i++) {
            Date dateIn = piecewiseTimesList.get(i).getStartTime();
            Date dateOut = piecewiseTimesList.get(i).getEndTime();
            try {
                // 入场时间大于等于开始时间段，出场时间小于等于结束时间段
                if (dynamicChargeTime.compareTime(dateInTime, dateIn) >= 0 && dynamicChargeTime.compareTime(dateOutTime, dateOut) <= 0) {
                    // 判断是否是第一个时间段大于0(将时间段的免费停车时间设为0)
                    if (piecewiseTimess.size() > 0) {
                        // 时间段的免费停车时间设为
                        piecewiseTimesList.get(i).setFree_time(0);
                        // 设置将车辆入场时间格式
                    }
                    //车辆入场时间变成该时间段的开始时间
                    piecewiseTimesList.get(i).setCarInTime(dateInTime);
                    //出场时间为车辆出场时间
                    piecewiseTimesList.get(i).setCarOutTime(dateOutTime);
                    //添加时间段进时间段list

                    piecewiseTimess.add(piecewiseTimesList.get(i));
                    return piecewiseTimess;
                } else {
                    //入场时间大于时间段的开始时间，入场时间小于时间段的结束时间，出场时间大于时间段的结束时间
                    if (dynamicChargeTime.compareTime(dateInTime, dateIn) >= 0 && dynamicChargeTime.compareTime(dateInTime, dateOut) <= 0 && dynamicChargeTime.compareTime(dateOutTime, dateOut) > 0) {
                        //如果是不是首段时间段 (该时间段的车辆入场时间为该段的开始时间，该时间段的车辆出场时间为时间段的结束时间)
                        if (piecewiseTimess.size() > 0) {
                            //设置车辆入场时间
                            Date carDateCarIn = dynamicChargeTime.setPiecewicesCarInTime(piecewiseTimesList.get(i).getStartTime(),
                                    "true");
                            piecewiseTimesList.get(i).setCarInTime(carDateCarIn);
                            //设置时间段中车辆免费停车时间
                            piecewiseTimesList.get(i).setFree_time(0);
                        } else {
                            piecewiseTimesList.get(i).setCarInTime(dateInTime);
                        }
                        //设置车辆信息的入场时间
                        Date setCarInDay = dynamicChargeTime.setPiecewicesCarInTime(piecewiseTimesList.get(i).getEndTime(), false);
                        piecewiseTimesList.get(i).setCarOutTime(setCarInDay);
                        Date addDay = dynamicChargeTime.setPiecewicesCarInTime(piecewiseTimesList.get(i).getEndTime(), true);
                        //将入场时间设置为时间段结束时间的天数的后一天
                        chargPama.setInTime(dynamicChargeTime.addDate(addDay, 1));
                        //设置时间段中车辆信息的出场时间
                        Date date3Out = dynamicChargeTime.setPiecewicesCarInTime(piecewiseTimesList.get(i).getEndTime(),
                                false);
                        //加入符合条件时间段集合
                        piecewiseTimess.add(piecewiseTimesList.get(i));
                        //循环执行查找符合条件时间段
                        return decompositionTimePiecewises(piecewiseTimesList, chargPama, piecewiseTimess);
                    }
                }
            } catch (Exception e) {
                System.out.println("获取日期段:" + e.getMessage());
                // TODO: handle exception
            }
        }
        return piecewiseTimess;

    }

    public DynamicParam daysTimeRule(DynamicPicwise piecewiseTime, ChargPama chargPama, boolean isFinally, DynamicParam dynamicParam) {
        try {
            DynamicChargeTime dynamicChargeTime = new DynamicChargeTime();
            DynamicChargeMoney dynamicChargeMoney = new DynamicChargeMoney();
            int crossDay = dynamicChargeTime.crossDay(chargPama);
            DynamicParam dateCrossTime=new DynamicParam();
            dateCrossTime=crossTimeChargPama(chargPama,piecewiseTime);
            String dateOut="";
            if(crossDay==0&&!dateCrossTime.getStrDateOut().equals(""))
            {
                dateOut=dateCrossTime.getStrDateOut();
            }else{
                dateOut = dynamicChargeTime.utilDateToString(chargPama.getOutTime());
            }
            chargPama.setInTime(piecewiseTime.getCarInTime());
            for (int i = 0; i <= crossDay; i++) {
                if (crossDay > 0) {
                    DateUtil dateUtil = new DateUtil();
                    if (i < crossDay) {
                        Date date = dynamicChargeTime.setOutDate(chargPama.getInTime(), chargPama.getOutTime());
                        chargPama.setOutTime(date);
                        if (i > 0) {
                            chargPama.getInTime().setTime(chargPama.getInTime().getTime() + 1000);
                            date = dateUtil.setPiecewicesCarInTime(chargPama.getInTime(), "CarOutTime");
                            chargPama.setOutTime(date);
                        }
                    } else {
                        chargPama.setOutTime(dynamicChargeTime.stringToUtilDate(dateOut));
                        Date date = dateUtil.setPiecewicesCarInTime(chargPama.getOutTime(), "CarInTime");
                        chargPama.setInTime(date);
                    }
                }else{
                    chargPama.setOutTime(dynamicChargeTime.stringToUtilDate(dateOut));
                }
                for (int j = 0; j < piecewiseTime.getDayPiecewiseList().size(); j++) {
                    DayPiecewise dayPiecewise = piecewiseTime.getDayPiecewiseList().get(j);
                    dayPiecewise.setCarStartTime(chargPama.getInTime());
                    if (dynamicChargeTime.timeCompare(dayPiecewise.getStartTime(), chargPama.getInTime()) <= 0
                            && dynamicChargeTime.timeCompare(dayPiecewise.getEndTime(), chargPama.getOutTime()) >= 0
                            ) {
                        //周期计费
                        if (piecewiseTime.getDayPiecewiseList().get(j).isPeriodiCharging()) {
                            long timeLength = dynamicChargeTime.timeLength(chargPama.getOutTime(), chargPama.getInTime());
                            if ((int) timeLength < piecewiseTime.getDayPiecewiseList().get(j).getFreeTime()) {
                                return dynamicParam;
                            }
                            if (piecewiseTime.getDayPiecewiseList().get(j).isIs_FreeTime()) {
                                if (!dynamicParam.isIs_FreeTime()) {
                                    chargPama.getInTime().setTime(chargPama.getInTime().getTime() + piecewiseTime.getDayPiecewiseList().get(j).getFreeTime() * 1000 * 60);
                                }
                                dynamicParam.setIs_FreeTime(true);
                            }
                            for (int k = 0; k < piecewiseTime.getDayPiecewiseList().size(); k++) {
                                if (dynamicChargeTime.timeCompare(piecewiseTime.getDayPiecewiseList().get(k).getStartTime(), chargPama.getInTime()) <= 0
                                        && dynamicChargeTime.timeCompare(piecewiseTime.getDayPiecewiseList().get(k).getEndTime(), chargPama.getOutTime()) >= 0
                                        && dynamicChargeTime.timeLength(chargPama.getOutTime(), chargPama.getInTime()) > 0
                                        ) {
                                    //总车辆入场分钟
                                    long sumMin = dynamicChargeTime.timeLength(chargPama.getOutTime(), chargPama.getInTime());
                                    double money = 0;
                                    money = dynamicChargeMoney.periodicRule(dayPiecewise, sumMin);
                                    System.out.println("周期开始时间：" + chargPama.getInTime());
                                    System.out.println("周期结束时间：" + chargPama.getOutTime());
                                    System.out.println("周期金额：" + money);
                                    System.out.println("============================");
                                    double tmpSumMoney = dynamicParam.getSumMoney() + money;
                                    dynamicParam.setSumMoney(tmpSumMoney);
                                    chargPama.getInTime().setTime(chargPama.getInTime().getTime() + sumMin * 60 * 1000);
                                }
                            }
                        } else {
                            long sumMin = dynamicChargeTime.timeLength(chargPama.getOutTime(), chargPama.getInTime());
                            //停车出场时间段
                            long timeLength = dynamicChargeTime.timeCompare(dayPiecewise.getEndTime(), chargPama.getInTime());
                            if ((int) sumMin < piecewiseTime.getDayPiecewiseList().get(j).getFreeTime()) {
                                return dynamicParam;
                            }
                            if (piecewiseTime.getDayPiecewiseList().get(j).isIs_FreeTime()) {
                                if (!dynamicParam.isIs_FreeTime()) {
                                    chargPama.getInTime().setTime(chargPama.getInTime().getTime() + piecewiseTime.getDayPiecewiseList().get(j).getFreeTime() * 1000 * 60);
                                }
                                dynamicParam.setIs_FreeTime(true);
                            }
                            int min = dayPiecewise.getMinPiecewise().getMin();
                            if (((sumMin - timeLength) <= 0)) {
                                double money = 0;
                                System.out.println("===========================");
                                System.out.println("车辆时间段入场：" + chargPama.getInTime());
                                money = dynamicChargeMoney.calculationMoney(dayPiecewise, sumMin);
                                double tmpSumMoney = dynamicParam.getSumMoney() + money;
                                dynamicParam.setSumMoney(tmpSumMoney);
                                if (isFinally) {
                                    System.out.println("车辆时间段出场：" + chargPama.getOutTime());
                                    String string = "停车时长" + sumMin;
                                    System.out.println(string);
                                    chargPama.setInTime(chargPama.getOutTime());
                                } else {
                                    chargPama.getInTime().setTime(chargPama.getOutTime().getTime());
                                }
                                System.out.println("每段时间段：" + dayPiecewise.getMinPiecewise().getMoney() + "/" + min);
                                System.out.println("金额：" + money);
                                System.out.println("============================");
                            }
                        }
                    }
                    long timeLength = dynamicChargeTime.timeCompare(chargPama.getOutTime(), chargPama.getInTime());
                    if ((int) timeLength < piecewiseTime.getDayPiecewiseList().get(j).getFreeTime()) {
                        return dynamicParam;
                    }
                    if (piecewiseTime.getDayPiecewiseList().get(j).isIs_FreeTime()) {
                        if (!dynamicParam.isIs_FreeTime()) {
                            chargPama.getInTime().setTime(chargPama.getInTime().getTime() + piecewiseTime.getDayPiecewiseList().get(j).getFreeTime() * 1000 * 60);
                        }
                        dynamicParam.setIs_FreeTime(true);
                    }
                    //车辆入场时间超过时间段
                    if (dynamicChargeTime.timeCompare(dayPiecewise.getStartTime(), chargPama.getInTime()) <= 0
                            && dynamicChargeTime.timeCompare(dayPiecewise.getEndTime(), chargPama.getInTime()) > 0
                            && dynamicChargeTime.timeCompare(dayPiecewise.getEndTime(), chargPama.getOutTime()) < 0
                            ) {
                        if (chargPama.getInTime().getTime() >= chargPama.getOutTime().getTime()) {
                            return dynamicParam;
                        }
                       /* if (!dynamicParam.isSurplusMinDate() && dayPiecewise.isIs_crossTime()) {
                          *//*
                          设置跨段补时时间
                        补时分钟数
                        *//*
                            long surplusMin1 = 0;
                            int min = dayPiecewise.getMinPiecewise().getMin();
                            if (j == piecewiseTime.getDayPiecewiseList().size() - 1) {
                                timeLength = dynamicChargeTime.timeCompare(chargPama.getOutTime(), chargPama.getInTime());
                            }
                            if (dayPiecewise.getMinPiecewise().isCrossTime()) {
                                //停车补时时间
                                surplusMin1 = timeLength % min;
                                if (surplusMin1 != 0) {
                                    surplusMin1 = min - surplusMin1;
                                }
                            } else {
                                surplusMin1 = 0;
                            }
                            DatetoString datetoString=new DatetoString();
                            //补时分钟数
                            Date date = datetoString.strToDateLong(dateOut);
                            date.setTime(date.getTime() - surplusMin1 * 60000);
                            dateOut = datetoString.dateToStrLong(date);
                            dynamicParam.setSurplusMinDate(true);
                            dynamicParam.setSurplusMinDate((int) surplusMin1);
                        }*/
                        //周期计费
                        if (piecewiseTime.getDayPiecewiseList().get(j).isPeriodiCharging()) {
                            timeLength = dynamicChargeTime.timeLength(chargPama.getOutTime(), chargPama.getInTime());
                            if ((int) timeLength < piecewiseTime.getDayPiecewiseList().get(j).getFreeTime()) {
                                return dynamicParam;
                            }
                            if (piecewiseTime.getDayPiecewiseList().get(j).isIs_FreeTime()) {
                                if (!dynamicParam.isIs_FreeTime()) {
                                    chargPama.getInTime().setTime(chargPama.getInTime().getTime() + piecewiseTime.getDayPiecewiseList().get(j).getFreeTime() * 1000 * 60);
                                }
                                dynamicParam.setIs_FreeTime(true);
                            }
                            for (int k = 0; k < piecewiseTime.getDayPiecewiseList().size(); k++) {
                                if (dynamicChargeTime.timeCompare(piecewiseTime.getDayPiecewiseList().get(k).getStartTime(), chargPama.getInTime()) <= 0
                                        //&& dynamicChargeTime.timeCompare(piecewiseTime.getDayPiecewiseList().get(k).getEndTime(), chargPama.getOutTime()) >= 0
                                        && dynamicChargeTime.timeLength(chargPama.getOutTime(), chargPama.getInTime()) > 0
                                        ) {
                                    //总车辆入场分钟
                                    long sumMin = dynamicChargeTime.timeCompare(dayPiecewise.getEndTime(), chargPama.getInTime());
                                    long sumTime = dynamicChargeTime.timeCompare(chargPama.getOutTime(), chargPama.getInTime());
                                    if (sumMin > 0) {

                                        double money = dynamicChargeMoney.periodicRule(dayPiecewise, sumMin);
                                        System.out.println("周期开始时间：" + chargPama.getInTime());
                                        double tmpSumMoney = dynamicParam.getSumMoney() + money;
                                        dynamicParam.setSumMoney(tmpSumMoney);
                                        chargPama.getInTime().setTime(chargPama.getInTime().getTime() + sumMin * 60 * 1000);
                                        /*
                                        * */
                                        if(dateCrossTime.getSurplusMinDate()==-1)
                                        {
                                            chargPama.setOutTime(chargPama.getInTime());
                                        }
                                        /**/
                                        System.out.println("周期结束时间：" + chargPama.getInTime());
                                        System.out.println("周期金额：" + money);
                                        System.out.println("============================");
                                    }
                                }
                            }
                        } else {
                            long sumMin = dynamicChargeTime.timeLength(chargPama.getOutTime(), chargPama.getInTime());
                            //停车出场时间段
                            timeLength = dynamicChargeTime.timeCompare(dayPiecewise.getEndTime(), chargPama.getInTime());
                            if ((int) sumMin < piecewiseTime.getDayPiecewiseList().get(j).getFreeTime()) {
                                return dynamicParam;
                            }
                            if (piecewiseTime.getDayPiecewiseList().get(j).isIs_FreeTime()) {
                                if (!dynamicParam.isIs_FreeTime()) {
                                    chargPama.getInTime().setTime(chargPama.getInTime().getTime() + piecewiseTime.getDayPiecewiseList().get(j).getFreeTime() * 1000 * 60);
                                }
                                dynamicParam.setIs_FreeTime(true);
                            }
                            //跨段补时
                            double money = 0;
                            System.out.println("===========================");
                            System.out.println("车辆时间段入场：" + chargPama.getInTime());
                            money = dynamicChargeMoney.calculationMoney(dayPiecewise, timeLength);
                            double tmpSumMoney = dynamicParam.getSumMoney() + money;
                            dynamicParam.setSumMoney(tmpSumMoney);

                            if (isFinally) {
                                chargPama.getInTime().setTime(chargPama.getInTime().getTime() + timeLength * 60 * 1000);
                                System.out.println("车辆时间段出场：" + chargPama.getInTime());
                                String string = "停车时长" + timeLength;
                                System.out.println(string);
                            } else {
                                chargPama.getInTime().setTime(chargPama.getInTime().getTime() + timeLength * 60 * 1000);
                            }
                            /*
                            * */
                            if(dateCrossTime.getSurplusMinDate()==-1){
                                chargPama.setOutTime(chargPama.getInTime());
                            }
                            /*
                            *
                            * */
                            int min = dayPiecewise.getMinPiecewise().getMin();
                            System.out.println("每段时间段：" + dayPiecewise.getMinPiecewise().getMoney() + "/" + min);
                            System.out.println("金额：" + money);
                            System.out.println("============================");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("计费异常：" + e.getMessage());
        }
        return dynamicParam;
    }

    private DynamicParam crossTimeChargPama(ChargPama chargPama, DynamicPicwise piecewiseTime) {
        DynamicChargeTime dynamicChargeTime = new DynamicChargeTime();
        DynamicParam dynamicParam=new DynamicParam();
        try {
            int crossDay = dynamicChargeTime.crossDay(chargPama);
            String dateOut = dynamicChargeTime.utilDateToString(chargPama.getOutTime());
            for (int i = 0; i <= crossDay; i++) {
                if (crossDay > 0) {
                    if (i < crossDay) {
                        Date date = dynamicChargeTime.setOutDate(chargPama.getInTime(), chargPama.getOutTime());
                        chargPama.setOutTime(date);
                        if (i > 0) {
                            chargPama.getInTime().setTime(chargPama.getInTime().getTime() + 1000);
                            DateUtil dateUtil = new DateUtil();
                            date = dateUtil.setPiecewicesCarInTime(chargPama.getInTime(), "CarOutTime");
                            chargPama.setOutTime(date);
                        }
                    } else {
                        chargPama.setOutTime(dynamicChargeTime.stringToUtilDate(dateOut));
                        DateUtil dateUtil = new DateUtil();
                        Date date = dateUtil.setPiecewicesCarInTime(chargPama.getOutTime(), "CarInTime");
                        chargPama.setInTime(date);
                    }
                }
                for (int j = 0; j < piecewiseTime.getDayPiecewiseList().size(); j++) {
                    DayPiecewise dayPiecewise = piecewiseTime.getDayPiecewiseList().get(j);
                    dayPiecewise.setCarStartTime(chargPama.getInTime());
                    long timeLength = dynamicChargeTime.timeCompare(chargPama.getOutTime(), chargPama.getInTime());
                    //车辆入场时间超过时间段
                    if (dynamicChargeTime.timeCompare(dayPiecewise.getStartTime(), chargPama.getInTime()) <= 0
                            && dynamicChargeTime.timeCompare(dayPiecewise.getEndTime(), chargPama.getInTime()) > 0
                            && dynamicChargeTime.timeCompare(dayPiecewise.getEndTime(), chargPama.getOutTime()) < 0
                            ) {
                        long sumMin = dynamicChargeTime.timeLength(chargPama.getOutTime(), chargPama.getInTime());

                        if (!dynamicParam.isSurplusMinDate() && dayPiecewise.isIs_crossTime()) {
                        /*
                          设置跨段补时时间
                        //补时分钟数
                        **/
                            long surplusMin1 = 0;
                            int min = dayPiecewise.getMinPiecewise().getMin();
                            if (j == piecewiseTime.getDayPiecewiseList().size() - 1) {
                                timeLength = dynamicChargeTime.timeCompare(chargPama.getOutTime(), chargPama.getInTime());
                            }
                            if (dayPiecewise.getMinPiecewise().isCrossTime()) {
                                //停车补时时间
                                surplusMin1 = timeLength % min;
                                if (surplusMin1 != 0) {
                                    surplusMin1 = min - surplusMin1;
                                }
                            } else {
                                surplusMin1 = 0;
                            }
                            DatetoString datetoString=new DatetoString();
                            //补时分钟数
                            Date date = datetoString.strToDateLong(dateOut);
                            if(sumMin>surplusMin1) {
                                date.setTime(date.getTime() - surplusMin1 * 60000);
                                dateOut = datetoString.dateToStrLong(date);
                                dynamicParam.setSurplusMinDate(true);
                                dynamicParam.setSurplusMinDate((int) surplusMin1);
                                dynamicParam.setStrDateOut(dateOut);
                            }else{
                                date.setTime(date.getTime());
                                dateOut = datetoString.dateToStrLong(date);
                                dynamicParam.setSurplusMinDate(true);
                                dynamicParam.setSurplusMinDate(-1);
                                dynamicParam.setStrDateOut(dateOut);
                            }
                        }

                    }
                }
            }

        } catch (Exception e) {
            System.out.println("计设置跨段时间异常：" + e.getMessage());
        }
        return dynamicParam;
    }
    
    public static void main(String[] args) {
    	DynamicCharge dynamicCharge = new DynamicCharge();
    	
    	
    	dynamicCharge.setCarInTime(new DateTime().toDate());
    	dynamicCharge.setCarOutTime(new DateTime().toDate());
    	//dynamicCharge.setDynamicPicwise(dynamicPicwise);
    	
    	DynamicChargeRule test = new DynamicChargeRule();
    	//test.mainRule(dynamicCharge);
	}
}

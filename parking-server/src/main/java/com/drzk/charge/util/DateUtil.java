package com.drzk.charge.util;

import com.drzk.charge.bean.ChargPama;
import com.drzk.charge.bean.PiecewiseTimes;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * Created by drzk on 2018/06/28.
 */
public class DateUtil {
    public  long  compareTime(Date DATE1, Date DATE2) {
        try {
        	if(DATE1 ==null || DATE2 == null) {
        		return 0;
        	}
            long diff = DATE1.getTime() - DATE2.getTime();
            return diff;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }
    // 设置时间
    public  Date setPiecewicesCarInTime(Date date, String string) {
        String String =dateToStrLong(date);
        Date carTime;
        String[] strings= String.toString().split(" ");
        DateUtil dateUtil=new DateUtil();
        if (string == "CarInTime") {
            String carInTime=strings[0]+" 00:00:00";
            carTime=dateUtil.stringToUtilDate(carInTime);

        } else {
            String carInTime=strings[0]+" 23:59:59";
            carTime=dateUtil.stringToUtilDate(carInTime);
        }
        return carTime;
    }

    /**
     * 将长时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @return
     */
    public  String dateToStrLong(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /**
     * 将长时间格式时间转换为字符串自己处理的格式
     *
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(Date dateDate,String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    /*
	 * //设置车辆入场时间
	 */
    public  Date setCarInTime(ChargPama chargPama, long count) {

        long diff = chargPama.getInTime().getTime() +count*60*1000;
        chargPama.getInTime().setTime(diff);
        /*if(chargPama.getInTime().getTime()-chargPama.getOutTime().getTime()>0)
        {

            System.out.println("出场时间：大于入场时间");
            System.out.println(chargPama.getInTime());
            System.out.println(chargPama.getOutTime());
            return null;
        }*/
         chargPama.getInTime().setTime(diff);
         return chargPama.getInTime();
        //return chargPama.getInTime();
    }

    public  Date stringToUtilDate(String string) {
        Date date=new Date();
        try {
            SimpleDateFormat bartDateFormat =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = bartDateFormat.parse(string);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return date;
    }

    public static Date stringToUtilDate(String string,String format) {
        Date date=new Date();
        try {
            SimpleDateFormat bartDateFormat = new SimpleDateFormat(format);
            date = bartDateFormat.parse(string);
        } catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return date;
    }

    // 停车分钟数
    public  long timeRule(Date date1,Date date2) {
        try {

            long nd = 1000 * 24 * 60 * 60;
            long nh = 1000 * 60 * 60;
            long nm = 1000 * 60;
            long diff = date2.getTime() - date1.getTime();

            // 计算差多少天
            long day = diff / nd;

            // 计算差多少小时
            long hour = diff % nd / nh;

            // 计算差多少分钟
            long min = diff % nd % nh / nm;

            // 计算差多少秒//输出结果
            long sec = diff % nd % nh % nm / 1000;
            if(sec>0)
            {
                min=min+1;
            }

            min = day * 24 * 60 + hour * 60 + min;

            return min;
        } catch (Exception e) {

            // TODO: handle exception
            return 0;
        }
    }
    /*
	 * 需要增加的天数
	 * */
    public  Date addDate(Date date,int counts){
        Date addDate=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, counts);
        addDate = calendar.getTime();
        return addDate;
    }
    // 将入场时间 去掉 时分秒后再进行时间对比
    public  long dateCompareTime(Date date, Date date2) {
        long day=-1;
        try {
            long nd = 1000 * 24 * 60 * 60;

            long diff = date.getTime() - date2.getTime();

            // 计算差多少天
            day = diff / nd;


        } catch (Exception e) {

            // TODO: handle exception

        }
        return day;
    }
    // 计算停车天数
    public  boolean dateCompareTime(PiecewiseTimes piecewiseTime) {
        boolean retDay=false;
        try {
            long day=-1;
            if(piecewiseTime.getIsFreeTime()==0){
                piecewiseTime.getCarInTime().setTime(piecewiseTime.getCarInTime().getTime()-piecewiseTime.getFreeTime()*60*1000);
            }
            day = piecewiseTime.getCarOutTime().getDate()- piecewiseTime.getCarInTime().getDate();
            if(day==0)
            {
                retDay=false;
            }else
            {
                piecewiseTime.getCarInTime().setTime(piecewiseTime.getCarInTime().getTime()+piecewiseTime.getFreeTime()*60*1000);
                retDay=true;
            }

        } catch (Exception e) {

            // TODO: handle exception

        }
        return retDay;
    }
    /*/
	 * / 分钟数换算小时数
	 */
    public  int getHour(int lengthTime) {
        int hour = lengthTime / 60;
        if (lengthTime % 60 > 0) {
            hour += 1;
        }
        return hour;
    }

    /**
     * <li>功能描述：时间相减得到天数
     * @param beginDate
     * @param endDate
     * @return
     * long
     * @author Administrator
     */
    public  long getDaySub(Date beginDate,Date endDate) {
        long day=0;
        int hour=beginDate.getHours();
        int min=beginDate.getMinutes();
        int sec=beginDate.getSeconds();
        beginDate.setHours(0);
        beginDate.setMinutes(0);
        beginDate.setSeconds(0);
        day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        beginDate.setHours(hour);
        beginDate.setMinutes(min);
        beginDate.setSeconds(sec);
        //System.out.println("相隔的天数="+day);
        return day;
    }
    public Date setDate(long timeLength){
        Date day = new Date(timeLength);
        return day;
    }
}

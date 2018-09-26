package com.drzk.charge.dynamic;

import com.drzk.charge.bean.ChargPama;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by drzk on 2018/06/29.
 */
public class DynamicChargeTime {
    public  long  compareTime(Date DATE1, Date DATE2) {

        try {
            long diff = DATE1.getTime() - DATE2.getTime();
            return diff;
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    // 设置时间
    public Date setPiecewicesCarInTime(Date date, String string) {
        Date utilDate = new Date();
        try {
            String[] strings = date.toString().split("-");
            Calendar calendar3 = Calendar.getInstance();

            if (string == "CarInTime") {
                calendar3.set(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]) - 1,
                        Integer.parseInt(strings[2]), 0, 0, 0);
            } else {
                calendar3.set(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]) - 1,
                        Integer.parseInt(strings[2]), 23, 59, 59);
            }
            utilDate = calendar3.getTime();
        } catch (Exception ex) {

        }
        return utilDate;
    }


    public  Date setPiecewicesCarInTime(java.util.Date date, boolean isDay) {
        Date utilDate = new Date();
        try {
            String[] strings = date.toString().split("-");
            Calendar calendar3 = Calendar.getInstance();
            if(isDay)
            {

                calendar3.set(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]) - 1,
                        Integer.parseInt(strings[2]), 0, 0, 0);
            }
            else
            {
                calendar3.set(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]) - 1,
                        Integer.parseInt(strings[2]), 23, 59, 0);
            }
            utilDate = calendar3.getTime();
        } catch (Exception ex) {

        }
        return utilDate;
    }
    public  Date addDate(Date date,int counts){
        Date addDate=new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, counts);
        addDate = calendar.getTime();
        return addDate;
    }
    public  int crossDay(ChargPama chargPama){
        Date dateCarOut=chargPama.getOutTime();
        Date dateIn=chargPama.getInTime();
        Calendar aCalendar = Calendar.getInstance();
        aCalendar.setTime(dateCarOut);
        int day1 = aCalendar.get(Calendar.DAY_OF_YEAR);
        aCalendar.setTime(dateIn);
        int day2 = aCalendar.get(Calendar.DAY_OF_YEAR);
        int days=day1-day2;
        return days;
    }
    public  long timeCompare(Date date1,Date date2)
    {
        long data1=0;
        if(date1.getSeconds()-date2.getSeconds()>0)
        {
            data1=date1.getHours()*60+date1.getMinutes()+1-date2.getHours()*60-date2.getMinutes();
        }
        else
        {
            data1=date1.getHours()*60+date1.getMinutes()-date2.getHours()*60-date2.getMinutes();
        }
        return data1;

    }
    public  Date stringToUtilDate(String string)
    {
        java.util.Date date=new Date();
        try {
            SimpleDateFormat bartDateFormat =
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            date = bartDateFormat.parse(string);
        }
        catch (Exception e) {
            // TODO: handle exception
            return null;
        }
        return date;
    }

    public  Date setOutDate(Date date1,Date date2)
    {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strInTime=sdf.format(date1);
        String strday=sdf.format(date1);
        String[] pStrings=strday.split(" ");
        String[] pStrings2=pStrings[0].split("-");
        date2.setYear(date1.getYear());
        date2.setMonth(date1.getMonth());
        date2.setDate( Integer.parseInt(pStrings2[2]));
        date2.setHours(23);
        date2.setMinutes(59);
        date2.setSeconds(59);
        return date2;
    }
    public  long timeLength(Date date1,Date date2) {
        try {
            long nd = 1000 * 24 * 60 * 60;
            long nh = 1000 * 60 * 60;
            long nm = 1000 * 60;
            long diff = date1.getTime() - date2.getTime();

            long day = diff / nd;


            long hour = diff % nd / nh;


            long min = diff % nd % nh / nm;

            long sec=diff % nd % nh % nm/1000;

            if(sec>0)
            {
                min = day * 24 * 60 + hour * 60 + min+1;
            }
            else
            {
                min = day * 24 * 60 + hour * 60 + min;
            }

            return min;
        } catch (Exception e) {

            // TODO: handle exception
            return 0;
        }
    }
    public  String utilDateToString(Date date)
    {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strTime=sdf.format(date);
        return strTime;
    }

}

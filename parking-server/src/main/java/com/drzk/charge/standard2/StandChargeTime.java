package com.drzk.charge.standard2;

import com.drzk.charge.bean.ChargPama;
import com.drzk.charge.bean.PiecewiseTimes;
import com.drzk.charge.util.DateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by drzk on 2018/06/28.
 */
public class StandChargeTime {
    /*
	 * 获取时间段 (跨时间段：时间不会补齐)
	*/
    public List<PiecewiseTimes> decompositionTimePiecewise(List<PiecewiseTimes> piecewiseTimes, ChargPama chargPama, List<PiecewiseTimes>lists){
        try{
            // 入场时间
            Date dateInTime = chargPama.getInTime();
            // 出场时间
            Date dateOutTime = chargPama.getOutTime();
            DateUtil dateUtil=new DateUtil();
            for(int i = 0; i< piecewiseTimes.size(); i++)
            {
                // 开始时间段
                Date dateIn= piecewiseTimes.get(i).getStartTime();
                // 结束时间段
                Date dateOut= piecewiseTimes.get(i).getEndTime();
                // 入场时间大于等于开始时间段，出场时间小于等于结束时间段
                if(dateUtil.compareTime(dateInTime,dateIn)>=0 && dateUtil.compareTime(dateOutTime,dateOut)<=0){
                    // 判断是否是第一个时间段大于0(将时间段的免费停车时间设为0)
                    if(lists.size()>0)
                    {
                        // 时间段的免费停车时间设为0
                        piecewiseTimes.get(i).setFreeTime(0);
                        // 设置将车辆入场时间格式
                        dateInTime = dateUtil.setPiecewicesCarInTime(piecewiseTimes.get(i).getStartTime(),
                                "CarInTime");
                    }
                    // 车辆入场时间变成该时间段的开始时间
                    piecewiseTimes.get(i).setCarInTime(dateInTime);
                    // 出场时间为车辆出场时间
                    piecewiseTimes.get(i).setCarOutTime(dateOutTime);
                    //添加时间段进时间段list
                    lists.add(piecewiseTimes.get(i));

                    return lists;
                }else {
                    //入场时间大于时间段的开始时间，入场时间小于时间段的结束时间，出场时间大于时间段的结束时间
                    if(dateUtil.compareTime(dateInTime,dateIn)>0 && dateUtil.compareTime(dateInTime,dateOut)<0 && dateUtil.compareTime(dateOutTime, dateOut) > 0){
                        //如果是不是首段时间段 (该时间段的车辆入场时间为该段的开始时间，该时间段的车辆出场时间为时间段的结束时间)
                        if (lists.size() > 0) {
                            //设置车辆入场时间
                            Date date3In = dateUtil.setPiecewicesCarInTime(piecewiseTimes.get(i).getStartTime(),
                                    "CarInTime");
                            piecewiseTimes.get(i).setCarInTime(date3In);
                            //设置时间段中车辆免费停车时间
                            piecewiseTimes.get(i).setFreeTime(0);

                        }
                        else {
                            piecewiseTimes.get(i).setCarInTime(dateInTime);
                        }
                        //设置车辆信息的入场时间
                        Date d = dateUtil.setPiecewicesCarInTime(piecewiseTimes.get(i).getEndTime(),"CarInTime");
                        //增加一天
                        Date date = dateUtil.addDate(d,1);
                        //将入场时间设置为时间段结束时间的天数的后一天
                        chargPama.setInTime(date);
                        //设置时间段中车辆信息的出场时间
                        Date date3Out = dateUtil.setPiecewicesCarInTime(piecewiseTimes.get(i).getEndTime(),
                                "CarOutTime");
                        piecewiseTimes.get(i).setCarOutTime(date3Out);
                        //加入符合条件时间段集合
                        lists.add(piecewiseTimes.get(i));
                        //循环执行查找符合条件时间段
                        return decompositionTimePiecewise(piecewiseTimes, chargPama, lists);
                    }

                }
            }

        }catch (Exception ex)
        {
            //System.out.println("获取时间段异常："+ex.getMessage());
        }
        return lists;
    }
}

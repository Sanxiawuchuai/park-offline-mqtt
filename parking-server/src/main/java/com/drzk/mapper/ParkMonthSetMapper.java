package com.drzk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkMonthSet;

public interface ParkMonthSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkMonthSet record);

    int insertSelective(ParkMonthSet record);

    ParkMonthSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkMonthSet record);

    int updateByPrimaryKey(ParkMonthSet record);
    
    int updatePrimaryKey(ParkMonthSet record);
    
    /** 获取100条未上传的数据 */
    List<ParkMonthSet> selectTopDS();
    
    /** 更新失败后更改状态为0*/
    int updateDSStatusFalied(@Param("status") Integer status,@Param("list") List<String> list);
    
    ParkMonthSet selectByGuid(String puid);
}
package com.drzk.mapper;

import java.util.Date;

import com.drzk.vo.ParkSumInfo;

public interface ParkSumInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkSumInfo record);

    int insertSelective(ParkSumInfo record);

    ParkSumInfo selectByPrimaryKey(Integer id);
    
    ParkSumInfo selectByLoginDate(Date loginDate);

    int updateByPrimaryKeySelective(ParkSumInfo record);

    int updateByPrimaryKey(ParkSumInfo record);
}
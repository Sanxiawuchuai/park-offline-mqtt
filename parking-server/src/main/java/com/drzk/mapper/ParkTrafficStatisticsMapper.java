package com.drzk.mapper;

import com.drzk.vo.ParkTrafficStatistics;

public interface ParkTrafficStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkTrafficStatistics record);

    int insertSelective(ParkTrafficStatistics record);

    ParkTrafficStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkTrafficStatistics record);

    int updateByPrimaryKey(ParkTrafficStatistics record);
}
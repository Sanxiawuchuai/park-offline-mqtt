package com.drzk.mapper;

import com.drzk.vo.ParkUsageStatistics;

public interface ParkUsageStatisticsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkUsageStatistics record);

    int insertSelective(ParkUsageStatistics record);

    ParkUsageStatistics selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkUsageStatistics record);

    int updateByPrimaryKey(ParkUsageStatistics record);
}
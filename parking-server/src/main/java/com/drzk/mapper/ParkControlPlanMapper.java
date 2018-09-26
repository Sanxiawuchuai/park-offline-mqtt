package com.drzk.mapper;

import com.drzk.vo.ParkControlPlan;

public interface ParkControlPlanMapper {
    int deleteByPrimaryKey(Integer planId);

    int insert(ParkControlPlan record);

    int insertSelective(ParkControlPlan record);

    ParkControlPlan selectByPrimaryKey(Integer planId);

    int updateByPrimaryKeySelective(ParkControlPlan record);

    int updateByPrimaryKey(ParkControlPlan record);
}
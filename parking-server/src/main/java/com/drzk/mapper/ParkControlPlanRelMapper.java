package com.drzk.mapper;

import java.util.List;

import com.drzk.vo.ParkControlPlanRel;

public interface ParkControlPlanRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkControlPlanRel record);

    int insertSelective(ParkControlPlanRel record);

    ParkControlPlanRel selectByPrimaryKey(Integer id);
    
    List<ParkControlPlanRel> selectByPlanId(Integer PlanId);

    int updateByPrimaryKeySelective(ParkControlPlanRel record);

    int updateByPrimaryKey(ParkControlPlanRel record);
}
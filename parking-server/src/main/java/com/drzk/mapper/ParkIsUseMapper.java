package com.drzk.mapper;

import com.drzk.vo.ParkIsUse;

public interface ParkIsUseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkIsUse record);

    int insertSelective(ParkIsUse record);

    ParkIsUse selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkIsUse record);

    int updateByPrimaryKey(ParkIsUse record);
}
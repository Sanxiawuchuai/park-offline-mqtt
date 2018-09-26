package com.drzk.mapper;

import com.drzk.vo.ParkOverTimeSet;

import java.util.List;

public interface ParkOverTimeSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkOverTimeSet record);

    int insertSelective(ParkOverTimeSet record);

    ParkOverTimeSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkOverTimeSet record);

    int updateByPrimaryKey(ParkOverTimeSet record);
    
    ParkOverTimeSet selectByCardType(Integer cardType);

    int updatePrimaryKey(ParkOverTimeSet po);

    /**
     * 查询所有的
     * @return
     */
    public List<ParkOverTimeSet> findAllOverTime();
}
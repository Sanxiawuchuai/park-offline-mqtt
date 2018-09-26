package com.drzk.mapper;

import com.drzk.vo.ParkChanelTemp;

public interface ParkChanelTempMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkChanelTemp record);

    int insertSelective(ParkChanelTemp record);

    ParkChanelTemp selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkChanelTemp record);

    int updateByPrimaryKey(ParkChanelTemp record);
}
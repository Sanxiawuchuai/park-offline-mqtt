package com.drzk.mapper;

import com.drzk.vo.BackParkCarOut;

public interface BackParkCarOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BackParkCarOut record);

    int insertSelective(BackParkCarOut record);

    BackParkCarOut selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackParkCarOut record);

    int updateByPrimaryKey(BackParkCarOut record);
}
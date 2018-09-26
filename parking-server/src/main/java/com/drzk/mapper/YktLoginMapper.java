package com.drzk.mapper;

import com.drzk.vo.YktLogin;

public interface YktLoginMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YktLogin record);

    int insertSelective(YktLogin record);

    YktLogin selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YktLogin record);

    int updateByPrimaryKey(YktLogin record);
}
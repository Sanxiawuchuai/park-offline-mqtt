package com.drzk.mapper;

import com.drzk.vo.SysVersion;

public interface SysVersionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysVersion record);

    int insertSelective(SysVersion record);

    SysVersion selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysVersion record);

    int updateByPrimaryKey(SysVersion record);
}
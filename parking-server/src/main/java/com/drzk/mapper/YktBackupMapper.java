package com.drzk.mapper;

import com.drzk.vo.YktBackup;

public interface YktBackupMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YktBackup record);

    int insertSelective(YktBackup record);

    YktBackup selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YktBackup record);

    int updateByPrimaryKey(YktBackup record);
}
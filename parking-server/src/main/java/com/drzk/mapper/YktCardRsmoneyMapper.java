package com.drzk.mapper;

import com.drzk.vo.YktCardRsmoney;

public interface YktCardRsmoneyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YktCardRsmoney record);

    int insertSelective(YktCardRsmoney record);

    YktCardRsmoney selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(YktCardRsmoney record);

    int updateByPrimaryKey(YktCardRsmoney record);
}
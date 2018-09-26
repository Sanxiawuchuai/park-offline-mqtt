package com.drzk.mapper;

import com.drzk.vo.AbandonRecord;

public interface AbandonRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AbandonRecord record);

    int insertSelective(AbandonRecord record);

    AbandonRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AbandonRecord record);

    int updateByPrimaryKey(AbandonRecord record);
}
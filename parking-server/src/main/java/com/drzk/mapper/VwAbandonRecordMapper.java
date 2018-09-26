package com.drzk.mapper;

import com.drzk.vo.VwAbandonRecord;

public interface VwAbandonRecordMapper {
    int insert(VwAbandonRecord record);

    int insertSelective(VwAbandonRecord record);
}
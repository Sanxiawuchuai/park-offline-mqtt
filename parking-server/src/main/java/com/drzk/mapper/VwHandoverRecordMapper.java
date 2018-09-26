package com.drzk.mapper;

import com.drzk.vo.VwHandoverRecord;

public interface VwHandoverRecordMapper {
    int insert(VwHandoverRecord record);

    int insertSelective(VwHandoverRecord record);
}
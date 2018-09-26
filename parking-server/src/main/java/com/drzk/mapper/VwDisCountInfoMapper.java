package com.drzk.mapper;

import java.util.List;

import com.drzk.vo.VwDisCountInfo;

public interface VwDisCountInfoMapper {
    int insert(VwDisCountInfo record);

    int insertSelective(VwDisCountInfo record);
    /** 获取为同步的数据*/
    List<VwDisCountInfo> selectTopDS();
}
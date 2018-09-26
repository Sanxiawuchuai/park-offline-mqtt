package com.drzk.mapper;

import java.util.List;

import com.drzk.vo.VwPersoninfo;

public interface VwPersoninfoMapper {
    int insert(VwPersoninfo record);

    int insertSelective(VwPersoninfo record);
    /** 获取未同步数据 */
    List<VwPersoninfo> selectByIsload( );
}
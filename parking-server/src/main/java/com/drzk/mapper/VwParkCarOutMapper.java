package com.drzk.mapper;

import java.util.List;

import com.drzk.vo.VwParkCarOut;

public interface VwParkCarOutMapper {
    int insert(VwParkCarOut record);

    int insertSelective(VwParkCarOut record);
    /** 查找未同步的非法开闸记录 */
    List<VwParkCarOut> selectByIllegaOpenGate();
}
package com.drzk.mapper;

import com.drzk.vo.VwCarInException;

public interface VwCarInExceptionMapper {
    int insert(VwCarInException record);

    int insertSelective(VwCarInException record);
}
package com.drzk.mapper;

import java.math.BigDecimal;

import com.drzk.vo.VwParkCarcharge;

public interface VwParkCarchargeMapper {
    int insert(VwParkCarcharge record);

    int insertSelective(VwParkCarcharge record);
    
    BigDecimal selectByCarNo(String carNo);
    
}
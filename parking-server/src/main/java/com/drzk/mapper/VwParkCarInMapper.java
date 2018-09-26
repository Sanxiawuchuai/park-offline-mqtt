package com.drzk.mapper;

import java.util.Date;
import java.util.List;

import com.drzk.vo.VwParkCarIn;

public interface VwParkCarInMapper {
    int insert(VwParkCarIn record);

    int insertSelective(VwParkCarIn record);
    
    /** 此方法仅适用于查找场内记录   */
    List<VwParkCarIn> GetByCarNo(String carNo);
    /** 此方法仅适用于查找场内记录   */
    List<VwParkCarIn> GetByNull();
}
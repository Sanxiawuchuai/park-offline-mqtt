package com.drzk.mapper;

import java.util.Date;

import com.drzk.vo.ParkFamilyGroupCharge;

public interface ParkFamilyGroupChargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkFamilyGroupCharge record);

    int insertSelective(ParkFamilyGroupCharge record);

    ParkFamilyGroupCharge selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkFamilyGroupCharge record);

    int updateByPrimaryKey(ParkFamilyGroupCharge record);
    
    ParkFamilyGroupCharge selectByCarNo(String carNo);
    
    ParkFamilyGroupCharge select(ParkFamilyGroupCharge record);
    
}
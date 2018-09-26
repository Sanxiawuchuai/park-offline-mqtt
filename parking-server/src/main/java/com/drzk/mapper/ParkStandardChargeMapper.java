package com.drzk.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkStandardCharge;

public interface ParkStandardChargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkStandardCharge record);

    int insertSelective(ParkStandardCharge record);

    ParkStandardCharge selectByPrimaryKey(Integer id);
    
    ParkStandardCharge selectByCardType(Integer cardType);

    int updateByPrimaryKeySelective(ParkStandardCharge record);

    int updateByPrimaryKey(ParkStandardCharge record);
    
    ParkStandardCharge selectByGuid(String pid);
    
    List<ParkStandardCharge> selectByCondition(ParkStandardCharge condition);
    List<ParkStandardCharge> selectDSTop();
    int updateDSStatus(@Param("status") Integer status,@Param("list") List<String> list);
    List<ParkStandardCharge> selectGroupByCardType();

	int updateParkStandard(ParkStandardCharge vo);
}
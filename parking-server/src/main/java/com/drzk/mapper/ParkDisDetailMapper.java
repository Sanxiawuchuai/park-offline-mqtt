package com.drzk.mapper;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkDisDetail;

public interface ParkDisDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkDisDetail record);

    int insertSelective(ParkDisDetail record);

    ParkDisDetail selectByPrimaryKey(Integer id);
    
    List<ParkDisDetail> selectByRecond(ParkDisDetail record);

    int updateByPrimaryKeySelective(ParkDisDetail record);

    int updateByPrimaryKey(ParkDisDetail record);
    
    Vector<ParkDisDetail> selectDSTop();
	
	int updateDSStatus(@Param("status") Integer status,@Param("list") List<String> list);
}
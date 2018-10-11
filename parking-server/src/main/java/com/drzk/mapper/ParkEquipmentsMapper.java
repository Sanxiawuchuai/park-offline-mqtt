package com.drzk.mapper;

import java.util.List;
import java.util.Vector;


import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkEquipments;

public interface ParkEquipmentsMapper {
    int deleteByPrimaryKey(Integer eqId);

    int insert(ParkEquipments record);

    int insertSelective(ParkEquipments record);

    ParkEquipments selectByPrimaryKey(Integer eqId);

    int updateByPrimaryKeySelective(ParkEquipments record);

    int updateByPrimaryKey(ParkEquipments record);
    
    int updatePrimaryKey(ParkEquipments record);
    /**
	 * 查询所有的商家
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	Vector<ParkEquipments> selectAllEquipmentsInfo();
	/** 获取未同步的数据 */
	Vector<ParkEquipments> selectDSTop();
	
	int updateDSStatus(@Param("status") Integer status,@Param("list") List<String> list);
	
	int updateDeleteFlag(@Param("list") List<String> list);

	ParkEquipments selectByGuid(String euid);
	
}
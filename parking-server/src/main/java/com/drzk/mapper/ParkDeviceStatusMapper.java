package com.drzk.mapper;

import java.util.List;

import com.drzk.vo.ParkDeviceStatus;

public interface ParkDeviceStatusMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ParkDeviceStatus record);

	int insertSelective(ParkDeviceStatus record);

	ParkDeviceStatus selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(ParkDeviceStatus record);

	int updateByPrimaryKey(ParkDeviceStatus record);
	
	/** 根据控制器ip查询最后一次的状态*/
	ParkDeviceStatus selectLastByConIp(String ip);
	
	/** 根据岗亭编号查询设备状态 */
	List<ParkDeviceStatus> selectByBoxId(Integer boxId);
}
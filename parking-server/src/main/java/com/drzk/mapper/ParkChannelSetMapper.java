package com.drzk.mapper;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkChannelSet;

public interface ParkChannelSetMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(ParkChannelSet record);

	int insertSelective(ParkChannelSet record);

	ParkChannelSet selectByPrimaryKey(Integer id);
	
	List<ParkChannelSet> selectByCondition(ParkChannelSet obj);

	int updateByPrimaryKeySelective(ParkChannelSet record);

	int updateByPrimaryKey(ParkChannelSet record);
	
	
	/**
	 * 查询所有的控制器信
	 * @author chenlong
	 * @return
	 * @since JDK 1.8
	 */
	Vector<ParkChannelSet> selectAllChannelInfo();
	/** 未同步数据 */
	List<ParkChannelSet> selectDSTop();
	/** 更新上传状态  */
	int updateDSStatus(@Param("status") Integer status,@Param("list") List<String> list);
	ParkChannelSet selectByGuid(String guid);

    Integer selectByUuid(@Param("cuid")String cuid);

    int deleteByUuid(ParkChannelSet param);

    int updateByUuid(ParkChannelSet param);
}
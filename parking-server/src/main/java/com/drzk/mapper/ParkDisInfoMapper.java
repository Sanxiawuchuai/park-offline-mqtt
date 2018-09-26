package com.drzk.mapper;

import java.util.List;
import java.util.Vector;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkDisInfo;

public interface ParkDisInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkDisInfo record);

    int insertSelective(ParkDisInfo record);

    ParkDisInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkDisInfo record);

    int updateByPrimaryKey(ParkDisInfo record);
    
    ParkDisInfo selectByGuid(String puid);
    
    int  updatePrimaryKey(ParkDisInfo record);
    
    /**
	 * 查询所有的打折信息
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	Vector<ParkDisInfo> selectAllDisInfo();
	
	Vector<ParkDisInfo> selectDSTop();
	
	int updateDSStatus(@Param("status") Integer status,@Param("list") List<String> list);
	/** 根据折扣ID 获取实体 */
	ParkDisInfo selectByDiscountId(String discountId);
}
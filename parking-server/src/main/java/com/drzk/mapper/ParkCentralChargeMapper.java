package com.drzk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkCentralCharge;
import com.drzk.vo.ParkSumUser;

public interface ParkCentralChargeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkCentralCharge record);

    int insertSelective(ParkCentralCharge record);

    ParkCentralCharge selectByPrimaryKey(Integer id);
    
    List<ParkCentralCharge> selectByCondition(ParkCentralCharge record);

    int updateByPrimaryKeySelective(ParkCentralCharge record);

    int updateByPrimaryKey(ParkCentralCharge record);
    
    List<ParkCentralCharge> getRecordByCondition(ParkCentralCharge condition);
    
    /** 获取100条未上传的数据 */
    List<ParkCentralCharge> selectTopDS();
    
    /** 更新失败后更改状态为0*/
    int updateDSStatusFalied(@Param("status") Integer status,@Param("list") List<String> list);
    
    ParkCentralCharge selectByGuid(String puid);
    
    
}
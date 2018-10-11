package com.drzk.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkSumUser;

public interface ParkSumUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkSumUser record);

    int insertSelective(ParkSumUser record);

    ParkSumUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkSumUser record);

    int updateByPrimaryKey(ParkSumUser record);
    ParkSumUser selectByBoxId(int boxId);
    
    ParkSumUser selectByUser(ParkSumUser record);
    
    List<ParkSumUser> selectByCondition(ParkSumUser record);
    
    
    /** 获取100条未上传的数据 */
    List<ParkSumUser> selectTopDS();
    
    /** 更新失败后更改状态为0*/
    int updateDSStatusFalied(@Param("status") Integer status,@Param("list") List<String> list);
    ParkSumUser selectByGuid(String guid);
    
    
}
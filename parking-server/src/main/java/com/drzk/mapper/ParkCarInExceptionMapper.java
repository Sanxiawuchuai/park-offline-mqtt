package com.drzk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkCarInException;

public interface ParkCarInExceptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkCarInException record);

    int insertSelective(ParkCarInException record);

    ParkCarInException selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkCarInException record);

    int updateByPrimaryKey(ParkCarInException record);
    
    /** 获取100条未上传的数据 */
    List<ParkCarInException> selectTopDS();
    
    /** 更新失败后更改状态为0*/
    int updateDSStatusFalied(@Param("status") Integer status,@Param("list") List<String> list);
    
    ParkCarInException selectByGuid(String guid);
    
    
}
package com.drzk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkCarBlackList;

public interface ParkCarBlackListMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkCarBlackList record);

    int insertSelective(ParkCarBlackList record);

    ParkCarBlackList selectByPrimaryKey(Integer id);
    
    ParkCarBlackList selectByCarNo(String CarNo);
    
    int updateByPrimaryKeySelective(ParkCarBlackList record);

    int updateByPrimaryKey(ParkCarBlackList record);
    
    int updateByCuid(ParkCarBlackList record);
    
    ParkCarBlackList selectByConditions(ParkCarBlackList condition);
    
    /** 获取100条未上传的数据 */
    List<ParkCarBlackList> selectTopDS();
    
    /** 更新失败后更改状态为0*/
    int updateDSStatusFalied(@Param("status") Integer status,@Param("list") List<String> list);
    
    ParkCarBlackList selectByGuid(String guid);
    
}
package com.drzk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.BackUpParkCarIn;

public interface BackUpParkCarInMapper {
	int deleteByPrimaryKey(Integer id);

    int insert(BackUpParkCarIn record);

    int insertSelective(BackUpParkCarIn record);

    BackUpParkCarIn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackUpParkCarIn record);

    int updateByPrimaryKey(BackUpParkCarIn record);
    
    /** 把删除后同步到云端的数据移到备份表 */
    int insertF(@Param("list") List<String> list);
    
    
    
}
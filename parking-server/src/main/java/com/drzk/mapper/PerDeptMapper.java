package com.drzk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.PerDept;

public interface PerDeptMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PerDept record);

    int insertSelective(PerDept record);

    PerDept selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PerDept record);

    int updateByPrimaryKey(PerDept record);
    
    /** 获取未同步数据 */
    List<PerDept> selectTopDS();
    
    int  updateStatus(@Param("status") Integer status,@Param("list") List<String> list);

    Integer selectByUuid(@Param("puid")String puid);

    int deleteByUuid(PerDept param);

    int updateByUuid(PerDept param);
    
}
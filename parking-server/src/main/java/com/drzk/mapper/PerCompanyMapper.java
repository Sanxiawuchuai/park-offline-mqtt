package com.drzk.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.drzk.vo.PerCompany;

public interface PerCompanyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PerCompany record);

    int insertSelective(PerCompany record);

    PerCompany selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PerCompany record);

    int updateByPrimaryKey(PerCompany record);
    /** 获取未同步数据 */
    List<PerCompany> selectTopDS();
    
    int  updateStatus(@Param("status") Integer status,@Param("list") List<String> list);

    Integer selectByUuid(@Param("cuid")String cuid);

    int deleteByUuid(PerCompany param);

    int updateByUuid(PerCompany param);

}
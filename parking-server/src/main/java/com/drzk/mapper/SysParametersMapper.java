package com.drzk.mapper;

import java.util.List;

import com.drzk.vo.SysParameters;

public interface SysParametersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysParameters record);

    int insertSelective(SysParameters record);

    int saveList(List<SysParameters> list);
    
    SysParameters selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysParameters record);

    int updateByPrimaryKey(SysParameters record);
    
    List<SysParameters> selectAll();

    int updateByCode(SysParameters record);

    int saveConfig(SysParameters record);

}
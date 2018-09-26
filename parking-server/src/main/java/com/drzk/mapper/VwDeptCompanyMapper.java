package com.drzk.mapper;

import java.util.List;

import com.drzk.vo.VwDeptCompany;

public interface VwDeptCompanyMapper {
    int insert(VwDeptCompany record);

    int insertSelective(VwDeptCompany record);
    /** 获取未同步的数据 */
    List<VwDeptCompany> selectTopDS();
}
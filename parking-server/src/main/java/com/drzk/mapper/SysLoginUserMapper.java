package com.drzk.mapper;

import com.drzk.vo.SysLoginUser;

public interface SysLoginUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysLoginUser record);

    int insertSelective(SysLoginUser record);

    SysLoginUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysLoginUser record);

    int updateByPrimaryKey(SysLoginUser record);
    
    SysLoginUser selectByNameAndPwd(String loginName,String pssWord);
}
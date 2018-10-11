package com.drzk.mapper;

import com.drzk.vo.SysVersion;
import org.apache.ibatis.annotations.Param;

public interface SysVersionMapper {
    int delete(Integer id);

    int insert(SysVersion record);

    SysVersion findById(Integer id);

    int update(SysVersion record);

    /**
     * 查询版本号是否存在
     * @param vSoft
     * @return
     */
    SysVersion getVerBySoft(@Param("vSoft") String vSoft);
}
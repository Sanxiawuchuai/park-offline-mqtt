package com.drzk.mapper;

import com.drzk.vo.PerPersons;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PerPersonsMapper {
    int delete(Integer pid);

    int insert(PerPersons record);

    PerPersons findById(Integer pid);

    int update(PerPersons perPersons);

    int updateByPrimaryKey(PerPersons record);
    
    PerPersons selectByCarNo(String carNo);
    
    List<String> selectPidByPuid(List<String> puids);

    int updateStatus(@Param("status") Integer status,@Param("list") List<String> list);

    PerPersons selectByUuid(@Param("puid")String puid);

    int insertOnline(PerPersons record);

    int deleteByUuid(PerPersons param);

    int updateByUuid(PerPersons param);

    public PerPersons getPerInfo(@Param("perName") String perName,@Param("perTel") String perTel);
}
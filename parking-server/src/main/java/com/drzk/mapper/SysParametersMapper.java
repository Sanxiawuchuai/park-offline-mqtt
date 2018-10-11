package com.drzk.mapper;

import com.drzk.vo.SysParameters;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SysParametersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysParameters record);

    int insertSelective(SysParameters record);

    int saveList(List<SysParameters> list);
    
    SysParameters selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysParameters record);

    int updateByPrimaryKey(SysParameters record);
    
    List<SysParameters> selectAll();

    int updateByCode(@Param("paramsCode") String paramsCode, @Param("paramsValue") String paramsValue);

    int saveConfig(SysParameters record);

    /**
     * 批量更新相关的数据
     * @param map
     * @return
     */
    public int batchUpdate(@Param("map") Map<String,String> map);
}
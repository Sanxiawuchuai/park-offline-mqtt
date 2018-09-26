package com.drzk.mapper;

import com.drzk.vo.ParkCarOut;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParkCarOutMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkCarOut record);

    ParkCarOut selectByPrimaryKey(Integer id);
    
    List<ParkCarOut> selectByCondition(ParkCarOut record);

    int updateByPrimaryKeySelective(ParkCarOut record);

    int updateByPrimaryKey(ParkCarOut record);
    /**
     * 更新上传状态
     * @param status 0未上传；1正在上传；
     * @param list 需要更新的guid集合
     * @return
     */
    int updateDSStatus(@Param("status") Integer status,@Param("list") List<String> list);
    /** 获取100条未同步的数据  */
    List<ParkCarOut> selectDSTop();
    ParkCarOut selectByGuid(String guid);
}
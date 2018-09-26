package com.drzk.mapper;

import java.util.List;
import java.util.Vector;

import com.drzk.vo.ParkChannelSet;
import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkLocalSet;

public interface ParkLocalSetMapper {
    int deleteByPrimaryKey(Integer boxId);

    int insert(ParkLocalSet record);

    int insertSelective(ParkLocalSet record);

    ParkLocalSet selectByPrimaryKey(Integer boxId);

    int updateByPrimaryKeySelective(ParkLocalSet record);

    int updateByPrimaryKey(ParkLocalSet record);
    
    /**
     * 更新当前岗亭的用户和在线状态
     * @param record
     * @return
     */
    int updateOnlineState(ParkLocalSet record);
    
    //查询所有的岗亭
    Vector<ParkLocalSet> selectAllLocalSet();
    
    /** 获取未同步的信息 */
    List<ParkLocalSet> selectDSTop();
    /** 修改数据同步在状态  */
    int updateDS(@Param("status") Integer status, @Param("list") List<String> list);
    
    ParkLocalSet selectByGuid(String guid);
    ParkLocalSet  selectByBoxId(Integer boxId);

    Integer selectByUuid(@Param("luid")String luid);

    int deleteByUuid(ParkLocalSet param);

    int updateByUuid(ParkLocalSet param);
}
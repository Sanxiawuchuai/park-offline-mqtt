package com.drzk.mapper;

import java.util.List;
import java.util.Vector;

import com.drzk.vo.PerDept;
import org.apache.ibatis.annotations.Param;

import com.drzk.vo.ParkCamSet;
import com.drzk.vo.ParkChannelSet;

public interface ParkCamSetMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkCamSet record);

    int insertSelective(ParkCamSet record);

    ParkCamSet selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkCamSet record);

    int updateByPrimaryKey(ParkCamSet record);
    
    Vector<ParkCamSet> selectAllCameraInfo();
    /** 未同步的数据  */
    Vector<ParkCamSet> selectDSTop();
    /** 更新相机上传状态  */
    int updateDSStatus(@Param("status") Integer status,@Param("list") List<String> list);
    
    ParkCamSet selectByGuid(String guid);

    Integer selectByUuid(@Param("guid")String guid);

    int deleteByUuid(ParkCamSet param);

    int updateByUuid(ParkCamSet param);
}
package com.drzk.mapper;

import com.drzk.vo.ParkCarIn;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface ParkCarInMapper {
    int deleteByPrimaryKey(Integer id);
    
    int deleteByCondition(ParkCarIn obj);

    int insert(ParkCarIn record);

    int insertSelective(ParkCarIn record);

    ParkCarIn selectByPrimaryKey(Integer id);
    
    List<ParkCarIn> selectByCondition(ParkCarIn obj);
    
    List<ParkCarIn> selectByCarNos(List<String> carNos);
    
    List<ParkCarIn> getNewNopNo();
    
    ParkCarIn selectTop(ParkCarIn obj);
    
    int updateByPrimaryKeySelective(ParkCarIn record);

    int updateByPrimaryKey(ParkCarIn record);
    
    int selectAllParkIn();
//    int deleteByGuid(@Param("list") List<String> list);
    
    /**
     * 查询大车场已用车位情况
     */
    Map<String,BigDecimal> getBigParkPlaceNumUsing();
    /**
     * 查询小车场车位使用情况
     * @return
     */
    Map<String,BigDecimal> getSmallParkPlaceNumUsing();
    
    /** 查找所有场内车的车牌  */
    List<String> selectAllParkInCarNo();
    
    /** 查询同一家庭组车辆的入场信息*/
    ParkCarIn selectParkInByGroup(Map<String,Object> condition);
    
    
    
    
    
    /** 获取100条未上传的数据 */
    List<ParkCarIn> selectTopDS();
    
    /** 更新失败后更改状态为0*/
    int updateDSStatusFalied(@Param("status") Integer status,@Param("list") List<String> list);
    
    ParkCarIn selectByGuid(String guid);
    /** 取已删除未同步的数据 */
    List<String> selectTopDSDelete();
    /** 删除移到备份表的数据 */
    int deleteDSDelete(List<String> guids);
    
    /** 获取删除后同步到云端成功之后的数据 */
//    List<String> selectDSDelete();
    
}
package com.drzk.mapper;

import com.drzk.vo.BackUpParkCarIn;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BackUpParkCarInMapper {
	int deleteByPrimaryKey(Integer id);

    int insert(BackUpParkCarIn record);

    int insertSelective(BackUpParkCarIn record);

    BackUpParkCarIn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BackUpParkCarIn record);

    int updateByPrimaryKey(BackUpParkCarIn record);
    
    /** 把删除后同步到云端的数据移到备份表 */
    int insertF(@Param("list") List<String> list);

    /**
     * 查询所有未同步入场数据
     * @return
     */
    List<BackUpParkCarIn> selectTopDS();

    /**
     * 查询所有未上传图片的入场数据
     * @return
     */
    List<BackUpParkCarIn> selectTopImg();

    /** 更新失败后更改状态为0*/
    int updateDSStatusFalied(@Param("status") Integer status,@Param("list") List<String> list);

    /** 更新图片上传状态为0 未上传 1 上传成功 2上传失败*/
    int updateUploadImgStatus(@Param("status") Integer status,@Param("list") List<String> list);
}
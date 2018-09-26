package com.drzk.mapper;

import com.drzk.vo.YktCardIssue;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YktCardIssueMapper {

    int delete(Integer id);

    int insert(YktCardIssue yktCardIssue);

    YktCardIssue findById(Integer id);

    YktCardIssue findByObjectId(@Param("objectId") String objectId);
    
    List<YktCardIssue> selectBySelective(YktCardIssue yktCardIssue);
    
    int update(YktCardIssue yktCardIssue);

    /** 获取100条未上传的数据 */
    List<YktCardIssue> selectTopDS();
    
    /** 更新失败后更改状态为0*/
    int updateDSStatusFalied(@Param("status") Integer status,@Param("list") List<String> list);
    int updateDSStatusBypid(@Param("status") Integer status,@Param("list") List<String> list);
    
    YktCardIssue selectByGuid(String guid);
}
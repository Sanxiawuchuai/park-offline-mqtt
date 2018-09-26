package com.drzk.mapper;

import com.drzk.vo.YktCardIssueRel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface YktCardIssueRelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(YktCardIssueRel record);

    YktCardIssueRel selectByPrimaryKey(Integer id);

    public int deleteByYktId(@Param("yktId") Integer yktId);

    int updateByPrimaryKeySelective(YktCardIssueRel record);

    int updateByPrimaryKey(YktCardIssueRel record);
    
    List<YktCardIssueRel> selectByCondition(YktCardIssueRel condition);
    
}
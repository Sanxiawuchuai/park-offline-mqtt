package com.drzk.mapper;

import java.util.List;

import com.drzk.vo.VwParkIssueOpera;

public interface VwParkIssueOperaMapper {
    int insert(VwParkIssueOpera record);

    int insertSelective(VwParkIssueOpera record);
    /** 获取同一个yktid的所有操作记录 按照create_date 倒序返回  */
    List<VwParkIssueOpera> selectByYktid(Integer yktId);
    
    VwParkIssueOpera selectByguid(String guid);
    /** 找未上传信息 */
    List<VwParkIssueOpera> selectByIsload();
}
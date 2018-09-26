package com.drzk.mapper;

import com.drzk.vo.YktCardIssuePark;
import org.apache.ibatis.annotations.Param;

public interface YktCardIssueParkMapper {

    int delete(Integer yktId);

    int insert(YktCardIssuePark record);

    YktCardIssuePark findById(Integer yktId);

    /**
     * 根据
     * @param carNo
     * @return
     */
    public YktCardIssuePark findCardByCarNo(@Param("carNo") String carNo);
    
    YktCardIssuePark selectByCondition(YktCardIssuePark obj);
    
    int update(YktCardIssuePark record);

}
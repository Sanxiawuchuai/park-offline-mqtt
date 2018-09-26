package com.drzk.mapper;

import com.drzk.vo.ParkEffetTimes;

public interface ParkEffetTimesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkEffetTimes record);

    int insertSelective(ParkEffetTimes record);

    ParkEffetTimes selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkEffetTimes record);

    int updateByPrimaryKey(ParkEffetTimes record);
    
    ParkEffetTimes selectByCardType(int cardType);
}
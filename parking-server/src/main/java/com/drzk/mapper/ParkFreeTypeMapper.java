package com.drzk.mapper;

import java.util.Vector;

import com.drzk.vo.ParkAccountType;
import com.drzk.vo.ParkFreeType;

public interface ParkFreeTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkFreeType record);

    int insertSelective(ParkFreeType record);

    ParkFreeType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkFreeType record);

    int updateByPrimaryKey(ParkFreeType record);
    /**
	 * 查询所有的免费类型
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	Vector<ParkFreeType> selectAllParkFreeType();
}
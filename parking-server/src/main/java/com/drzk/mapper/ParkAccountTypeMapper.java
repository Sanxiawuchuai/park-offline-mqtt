package com.drzk.mapper;

import com.drzk.vo.ParkAccountType;
import org.apache.ibatis.annotations.Param;

import java.util.Vector;

public interface ParkAccountTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ParkAccountType record);

    int insertSelective(ParkAccountType record);

    ParkAccountType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ParkAccountType record);

    int updateByPrimaryKey(ParkAccountType record);
    
    int updateName(ParkAccountType record);

    /**
     * 根据账户类型得到账户对象
     * @param aType
     * @return
     */
    public ParkAccountType getAccName(@Param("aType") Integer aType);

    /**
	 * 查询所有的车类型
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	Vector<ParkAccountType> selectAllAccountType();
}
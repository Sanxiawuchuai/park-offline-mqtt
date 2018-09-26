package com.drzk.mapper;

import java.util.List;

import com.drzk.vo.VwParkCarIsuse;

public interface VwParkCarIsuseMapper {
	int insert(VwParkCarIsuse record);

	int insertSelective(VwParkCarIsuse record);

	/**
	 * 查询固定卡资料
	 */
	VwParkCarIsuse selectMonthCar(VwParkCarIsuse record);

	/**
	 * 
	 * 根据车位组id,查询同一组的所有车牌 <br>
	 *
	 * @author chenlong
	 * @param groupId
	 * @return
	 * @since JDK 1.8
	 */
	List<String> selectCarsByGroupId(int groupId);
	
	/** 获取改变未同步的用户数据 */
	List<VwParkCarIsuse> selectChange();
	/** 查找用户的所有车牌记录 */
	List<VwParkCarIsuse>selectByPid(Integer pid);
	
	List<VwParkCarIsuse> selectByCardId(String cardId);
}
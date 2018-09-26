package com.drzk.mapper;

import com.drzk.online.vo.ParkCarGroup;
import com.drzk.online.vo.ParkCarGroupVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 车位组实现类
 */
public interface ParkCarGroupMapper {

    /**
     * 保存车位组
     * @param parkCarGroup
     * @return
     */
    public int insert(ParkCarGroup parkCarGroup);

    /**
     * 根据id，查询车位组信息
     * @param id
     * @return
     */
    public ParkCarGroupVO findById(Integer id);

    /**
     * 根据线上主键查询
     * @param cuid
     * @return
     * ParkCarGroupVO
     * 2018年9月12日
     */
    public ParkCarGroup getByCuid(String cuid);
    
    /**
     * 查询所有没有同步的车位组信息
     * @return
     */
    public List<ParkCarGroup> findAllGroup();

    /**
     * 修改车位组
     * @param parkCarGroup
     * @return
     */
    public int update(ParkCarGroup parkCarGroup);

    /**
     * 发布成功后，更新状态
     * @param isLoad
     * @param list
     * @return
     */
    public int updateStatus(@Param("isLoad") Integer isLoad, @Param("list") List<String> list);

    /**
     * 根据组名查询
     * @param placeName
     * @return
     * ParkCarGroup
     * 2018年9月20日
     */
	public ParkCarGroup getByGroupName(String placeName);

}
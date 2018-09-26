package com.drzk.online.service;


import com.drzk.online.onlineVo.ParkConfigVo;

/**
 * 2018/7/12 cx
 */
public interface ParkConfigService {

    /**
     * 新增车场信息
     */
    Integer insertParkConfig(ParkConfigVo config);

    /**
     * 修改车场信息
     */
    Integer saveParkConfig(ParkConfigVo config);

    /**
     * 删除车场配置
     */
    Integer deleteParkConfig(ParkConfigVo config);

    /**
     * 同步车场设置
     */
    void syncParkConfig(String message);

}

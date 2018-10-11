package com.drzk.online.service;


import com.drzk.online.onlineVo.ParkConfigVo;
import com.drzk.service.entity.LowerBody;

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

    /**
     * 同步车场编号、版本信息
     * @param lowerBody
     */
    public void syncParkNum(LowerBody lowerBody);

    /**
     * 重新加载车场参数到缓存中
     */
    public void reloadSysParams();

    /**
     * 更新车场参数
     * @param paramsValue
     * @param paramsCode
     * @return
     */
    public int updateByCode(String paramsCode,String paramsValue);
}

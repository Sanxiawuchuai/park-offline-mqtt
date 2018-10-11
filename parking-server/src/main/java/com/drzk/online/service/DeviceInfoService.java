package com.drzk.online.service;

import com.drzk.online.onlineVo.DeviceBoxVo;
import com.drzk.online.onlineVo.DeviceCameraVo;
import com.drzk.online.onlineVo.DeviceControllerVo;

/**
 * describe: 设备管理
 * date:2018/5/25
 * author:CaoXu
 */
public interface DeviceInfoService {

    /**
     * 新增岗亭
     */
    Integer insertBox(DeviceBoxVo device);

    /**
     * 新增控制器
     */
    Integer insertController(DeviceControllerVo device);

    /**
     * 新增相机
     */
    Integer insertCamera(DeviceCameraVo device);


    /**
     * 保存岗亭
     */
    Integer saveBox(DeviceBoxVo device);

    /**
     * 保存控制器
     */
    Integer saveController(DeviceControllerVo device);

    /**
     * 保存相机
     */
    Integer saveCamera(DeviceCameraVo device);


    /**
     * 删除车场岗亭
     * insert batch
     */
    Integer deleteParkBox(DeviceBoxVo config);

    /**
     * 删除车场控制器
     * insert batch
     */
    Integer deleteParkController(DeviceControllerVo config);

    /**
     * 删除车场相机
     * insert batch
     */
    Integer deleteParkCamera(DeviceCameraVo config);


    /**
     * 同步出入口设备信息
     */
    void syncParkDevice(String message);

    /**
     * 同步出入口设备信息
     */
    void syncOpenDoor(String message);


}

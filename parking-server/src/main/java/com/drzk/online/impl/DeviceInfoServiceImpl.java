package com.drzk.online.impl;

import com.drzk.mapper.ParkCamSetMapper;
import com.drzk.mapper.ParkChannelSetMapper;
import com.drzk.mapper.ParkLocalSetMapper;
import com.drzk.online.constant.ConstantUtil;
import com.drzk.online.onlineVo.*;
import com.drzk.online.service.DeviceInfoService;
import com.drzk.online.service.OfMqttService;
import com.drzk.online.utils.ReflectionUtil;
import com.drzk.utils.JacksonUtils;
import com.drzk.vo.ParkCamSet;
import com.drzk.vo.ParkChannelSet;
import com.drzk.vo.ParkLocalSet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 2018/9/11 cx
 */
@Service("deviceInfoService")
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Resource
    private ParkLocalSetMapper parkLocalSetMapper;

    @Resource
    private ParkChannelSetMapper parkChannelSetMapper;

    @Resource
    private ParkCamSetMapper parkCamSetMapper;

    @Resource
    private OfMqttService ofMqttService;


    @Override
    public Integer insertBox(DeviceBoxVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( DeviceBoxVo.map, map );
        ParkLocalSet perCompany = new ParkLocalSet();
        perCompany.setDelFrag( ConstantUtil.ENABLE );
        ReflectionUtil.setMapToEntity( newMap, perCompany );
        perCompany.setIsLoad( ConstantUtil.SQL_SUCCESS );
        int result = parkLocalSetMapper.insert( perCompany );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer insertController(DeviceControllerVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( DeviceControllerVo.map, map );
        ParkChannelSet vo = new ParkChannelSet();
        ReflectionUtil.setMapToEntity( newMap, vo );
        vo.setDelFrag( ConstantUtil.ENABLE );
        //开闸方式
        StringBuilder openBrakeType = new StringBuilder();
        openBrakeType.append( config.getTempCar() ).append( config.getMonthCar() ).append( config.getRechargeCar() ).append( config.getFreeCar() );
        openBrakeType.append( "0000" );
        vo.setStrobeSet( openBrakeType.toString() );
        //工作模式
        StringBuilder workModel = new StringBuilder();
        workModel.append( config.getTempCarModel() ).append( config.getMonthCarModel() ).append( config.getRechargeCarModel() ).append( config.getFreeCarModel() );
        workModel.append( "0000" );
        vo.setWorkModel( workModel.toString() );

        Integer boxId = parkLocalSetMapper.selectByUuid( config.getBoxId() );
        if (boxId != null) {
            vo.setBoxId( boxId );
        }
        vo.setIsLoad( ConstantUtil.SQL_SUCCESS );
        int result = parkChannelSetMapper.insert( vo );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer insertCamera(DeviceCameraVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( DeviceCameraVo.map, map );
        ParkCamSet vo = new ParkCamSet();
        ReflectionUtil.setMapToEntity( newMap, vo );
        vo.setDelFrag( ConstantUtil.ENABLE );
        vo.setIsLoad( ConstantUtil.SQL_SUCCESS );
        int result = parkCamSetMapper.insert( vo );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer saveBox(DeviceBoxVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( DeviceBoxVo.map, map );
        ParkLocalSet perCompany = new ParkLocalSet();
        ReflectionUtil.setMapToEntity( newMap, perCompany );
        int result = parkLocalSetMapper.updateByUuid( perCompany );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer saveController(DeviceControllerVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( DeviceControllerVo.map, map );
        ParkChannelSet vo = new ParkChannelSet();
        ReflectionUtil.setMapToEntity( newMap, vo );
        //开闸方式
        StringBuilder openBrakeType = new StringBuilder();
        openBrakeType.append( config.getTempCar() ).append( config.getMonthCar() ).append( config.getRechargeCar() ).append( config.getFreeCar() );
        openBrakeType.append( "0000" );
        vo.setStrobeSet( openBrakeType.toString() );
        /* 工作模式 */
        StringBuilder workModel = new StringBuilder();
        workModel.append( config.getTempCarModel() ).append( config.getMonthCarModel() ).append( config.getRechargeCarModel() ).append( config.getFreeCarModel() );
        workModel.append( "0000" );
        vo.setWorkModel( workModel.toString() );
        int result = parkChannelSetMapper.updateByUuid( vo );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer saveCamera(DeviceCameraVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( DeviceCameraVo.map, map );
        ParkCamSet perCompany = new ParkCamSet();
        ReflectionUtil.setMapToEntity( newMap, perCompany );
        int result = parkCamSetMapper.updateByUuid( perCompany );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer deleteParkBox(DeviceBoxVo config) {
        ParkLocalSet perCompany = new ParkLocalSet();
        perCompany.setLuid( config.getObjectId() );
        perCompany.setDelFrag( ConstantUtil.DISABLE );
        int result = parkLocalSetMapper.deleteByUuid( perCompany );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer deleteParkController(DeviceControllerVo config) {
        ParkChannelSet perCompany = new ParkChannelSet();
        perCompany.setCuid( config.getObjectId() );
        perCompany.setDelFrag( ConstantUtil.DISABLE );
        int result = parkChannelSetMapper.deleteByUuid( perCompany );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer deleteParkCamera(DeviceCameraVo config) {
        ParkCamSet perCompany = new ParkCamSet();
        perCompany.setCuid( config.getObjectId() );
        perCompany.setDelFrag( ConstantUtil.DISABLE );
        int result = parkCamSetMapper.deleteByUuid( perCompany );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public void syncParkDevice(String message) {
        MqttPayloadVo payloadVo = JacksonUtils.jsonToMqttHead( message );
        if (payloadVo != null) {
            MqttHeadVo head = payloadVo.getHead();
            String method = head.getMethod();
            String executeType = head.getExecuteType();
            if (method.equals( ConstantUtil.BOX_METHOD )) {
                DeviceBoxVo vo = JacksonUtils.jsonToMqttObject( message, DeviceBoxVo.class ).getBody();
                ParkLocalSet exists = parkLocalSetMapper.selectByGuid( head.getSubId() );
                if (executeType.equals( ConstantUtil.INSERT_OPERATION ) || executeType.equals( ConstantUtil.UPDATE_OPERATION )) {
                    //判断是否存在数据 存在就更新  不存在就新增
                    if (exists != null) {
                        saveBox( vo );
                    } else {
                        insertBox( vo );
                    }
                } else if (executeType.equals( ConstantUtil.DELETE_OPERATION )) {
                    vo = new DeviceBoxVo();
                    vo.setObjectId( head.getSubId() );
                    vo.setParkingNo( head.getParkingNo() );
                    deleteParkBox( vo );
                }
            } else if (method.equals( ConstantUtil.CONTROLLER_METHOD )) {
                DeviceControllerVo vo = JacksonUtils.jsonToMqttObject( message, DeviceControllerVo.class ).getBody();
                ParkChannelSet exists = parkChannelSetMapper.selectByGuid( head.getSubId() );
                if (executeType.equals( ConstantUtil.INSERT_OPERATION ) || executeType.equals( ConstantUtil.UPDATE_OPERATION )) {
                    if (exists != null) {
                        saveController( vo );
                    } else {
                        insertController( vo );
                    }
                } else if (executeType.equals( ConstantUtil.DELETE_OPERATION )) {
                    vo = new DeviceControllerVo();
                    vo.setObjectId( head.getSubId() );
                    vo.setParkingNo( head.getParkingNo() );
                    deleteParkController( vo );
                }
            } else if (method.equals( ConstantUtil.CAMERA_METHOD )) {
                DeviceCameraVo vo = JacksonUtils.jsonToMqttObject( message, DeviceCameraVo.class ).getBody();
                ParkCamSet exists = parkCamSetMapper.selectByUuid( head.getSubId() );
                if (executeType.equals( ConstantUtil.INSERT_OPERATION ) || executeType.equals( ConstantUtil.UPDATE_OPERATION )) {
                    if (exists != null) {
                        insertCamera( vo );
                    } else {
                        saveCamera( vo );
                    }
                } else if (executeType.equals( ConstantUtil.DELETE_OPERATION )) {
                    vo = new DeviceCameraVo();
                    vo.setObjectId( head.getSubId() );
                    vo.setParkingNo( head.getParkingNo() );
                    deleteParkCamera( vo );
                }
            }
        }
    }

    @Override
    public void syncOpenDoor(String message) {
        MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(message, Map.class);
        Map data = dataMqtt.getBody();
        if(data!=null){
            String mac=data.getOrDefault( "macAddr","" ).toString();
            String ip=data.getOrDefault( "ipAddr","" ).toString();
            String parkingNo=data.getOrDefault( "parkingNo","" ).toString();

        }
    }


    /**
     * 同步结果
     *
     * @param objectId  objectId
     * @param method    method
     * @param result    2 成功 3 失败
     * @param parkingNo 车场编号
     */
    private void sendSyncResult(String objectId, String method, Integer result, String parkingNo) {
        MqttHeadVo head = new MqttHeadVo();
        head.setSubId( objectId );
        head.setMethod( method );
        head.setStatus( result.toString() );
        head.setParkingNo( parkingNo );
        MqttPayloadVo replay = new MqttPayloadVo();
        replay.setHead( head );
        ofMqttService.sendMessage( ConstantUtil.PUBLISH_ASYNC_STATUS_TO_ONLINE, JacksonUtils.objectToJson( replay ), 0 );
    }
}

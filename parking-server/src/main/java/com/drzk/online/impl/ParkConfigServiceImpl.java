package com.drzk.online.impl;

import com.drzk.mapper.SysParametersMapper;
import com.drzk.online.constant.ConstantUtil;
import com.drzk.online.onlineVo.MqttHeadVo;
import com.drzk.online.onlineVo.MqttPayloadVo;
import com.drzk.online.onlineVo.ParkConfigVo;
import com.drzk.online.service.OfMqttService;
import com.drzk.online.service.ParkConfigService;
import com.drzk.online.utils.ReflectionUtil;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.utils.JacksonUtils;
import com.drzk.utils.StringUtils;
import com.drzk.vo.SysParameters;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 2018/9/11 cx
 */
@Service("parkConfigService")
public class ParkConfigServiceImpl implements ParkConfigService {


    @Resource
    private SysParametersMapper sysParametersMapper;

    @Resource
    private OfMqttService ofMqttService;

    @Override
    public Integer insertParkConfig(ParkConfigVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( ParkConfigVo.map, map );
        String payParam="";
        //支付宝
        if(config.getIsAutoPay()>0){
            payParam+="1,";
        }
       //微信
        if(config.getIsAutoPayByWechart()>0){
            payParam+="2,";
        }
        //无感支付
        if(config.getAutoPayAfterExit()>0){
            payParam+="3,";
        }
        if(StringUtils.isNullOrEempty( payParam )){
            payParam=payParam.substring( 0,payParam.length()-1 );
        }
        newMap.put("PAY_PRO_PARMS",payParam );

        newMap.forEach( (key,val)->{
            SysParameters vo = new SysParameters();
            vo.setTypeId( 2 );
            vo.setParameterCode( key );
            vo.setParameterValue( val.toString() );
            vo.setCreateDate( config.getCreateTime() );
            vo.setCreateUserName( config.getCreator() );
            vo.setModifyDate( config.getLastUpdateTime() );
            vo.setModifyUserName( config.getLastUpdateName() );
            sysParametersMapper.saveConfig( vo );
        } );
        sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        return ConstantUtil.SQL_FAILED;
    }

    @Override
    public Integer saveParkConfig(ParkConfigVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( ParkConfigVo.map, map );
        String payParam="";
        //支付宝
        if(config.getIsAutoPay()>0){
            payParam+="1,";
        }
        //微信
        if(config.getIsAutoPayByWechart()>0){
            payParam+="2,";
        }
        //无感支付
        if(config.getAutoPayAfterExit()>0){
            payParam+="3,";
        }
        if(!StringUtils.isNullOrEempty( payParam )){
            payParam=payParam.substring( 0,payParam.length()-1 );
        }
        newMap.put("PAY_PRO_PARMS",payParam );

        newMap.forEach( (key,val)->{
            SysParameters vo = new SysParameters();
            vo.setTypeId( 2 );
            vo.setParameterCode( key );
            vo.setParameterValue( val.toString() );
            vo.setModifyDate( config.getLastUpdateTime() );
            vo.setModifyUserName( config.getLastUpdateName() );
            sysParametersMapper.updateByCode( vo );
        } );
        sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        return  ConstantUtil.SQL_FAILED;
    }

    @Override
    public Integer deleteParkConfig(ParkConfigVo config) {
        return null;
    }

    @Override
    public void syncParkConfig(String message) {
        MqttPayloadVo payloadVo = JacksonUtils.jsonToMqttHead( message );
        if (payloadVo != null) {
            MqttHeadVo head = payloadVo.getHead();
            String method = head.getMethod();
            String executeType = head.getExecuteType();
            if (method.equals( ConstantUtil.CONFIG_METHOD )) {
                ParkConfigVo vo = JacksonUtils.jsonToMqttObject( message, ParkConfigVo.class ).getBody();
                if (executeType.equals( ConstantUtil.INSERT_OPERATION )) {
                    insertParkConfig( vo );
                } else if (executeType.equals( ConstantUtil.UPDATE_OPERATION )) {
                    saveParkConfig( vo );
                }  else if (executeType.equals( ConstantUtil.DELETE_OPERATION )) {
                    vo = new ParkConfigVo();
                    vo.setObjectId( head.getSubId() );
                    vo.setParkingNo( head.getParkingNo() );
                    deleteParkConfig( vo );
                }
            }
        }
    }

    /**
     * 同步结果
     * @param objectId  objectId
     * @param method  method
     * @param result 2 成功 3 失败
     * @param parkingNo 车场编号
     */
    private void sendSyncResult(String objectId,String method,Integer result, String parkingNo) {
        MqttHeadVo head = new MqttHeadVo();
        head.setSubId( objectId );
        head.setMethod( method );
        head.setStatus( result.toString() );
        head.setParkingNo( parkingNo );
        MqttPayloadVo replay=new MqttPayloadVo();
        replay.setHead( head );
        ofMqttService.sendMessage(ConstantUtil.PUBLISH_ASYNC_STATUS_TO_ONLINE,JacksonUtils.objectToJson( replay ),0);
    }
}

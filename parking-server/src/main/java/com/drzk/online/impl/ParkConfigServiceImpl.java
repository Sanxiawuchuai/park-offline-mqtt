package com.drzk.online.impl;

import com.drzk.common.AESUtil;
import com.drzk.mapper.SysParametersMapper;
import com.drzk.mapper.SysVersionMapper;
import com.drzk.offline.constant.OfClientMQTT;
import com.drzk.online.constant.ConstantUtil;
import com.drzk.online.onlineVo.MqttHeadVo;
import com.drzk.online.onlineVo.MqttPayloadVo;
import com.drzk.online.onlineVo.ParkConfigVo;
import com.drzk.online.service.OfMqttService;
import com.drzk.online.service.ParkConfigService;
import com.drzk.online.utils.ReflectionUtil;
import com.drzk.service.entity.LowerBody;
import com.drzk.service.impl.ClientMQTT;
import com.drzk.utils.DateTimeUtils;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JacksonUtils;
import com.drzk.utils.StringUtils;
import com.drzk.vo.SysParameters;
import com.drzk.vo.SysVersion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
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
    @Autowired
    private SysVersionMapper sysVersionMapper;

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
        });
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
            int result= sysParametersMapper.updateByCode(key,val.toString());
            //如果更新失败就插入数据
            if(result==0){
                vo.setCreateDate( config.getCreateTime() );
                vo.setCreateUserName( config.getCreator() );
                sysParametersMapper.insert( vo );
            }
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

    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public void syncParkNum(LowerBody lowerBody) {
        String expirtDate=DateTimeUtils.formatDate(new Date((long) lowerBody.getExpiresTime()),"yyyyMMddHHmmss");     //获取过期的日期
        String genLicense=AESUtil.encrypt(GlobalPark.sysMap.get("cpuid")+GlobalPark.sysMap.get("mac")+expirtDate,ConstantUtil.PUBLIC_KEY);

        if(genLicense.equals(lowerBody.getLicense())) {
            updateByCode("PARK_NUM",lowerBody.getProjectNo());
            updateByCode("PARK_LICENSE",lowerBody.getLicense());
            updateByCode("PROJECT_NAME",lowerBody.getProjectName());
            updateByCode("OWNED_CTIY",lowerBody.getAddress());
            updateByCode("OPERA_NO",lowerBody.getOperatorNo());
            updateByCode("START_VALIDITY",DateTimeUtils.formatDate(new Date(),"yyyy-MM-dd"));
            updateByCode("END_VALIDITY",DateTimeUtils.formatDate(new Date((long) lowerBody.getExpiresTime()),"yyyy-MM-dd"));
            updateByCode("OPERA_NO",lowerBody.getOperatorNo());

//            Map<String, String> map = new HashMap<>();
//            map.put("PROJECT_NAME", lowerBody.getProjectName());
//            map.put("PARK_NUM", lowerBody.getProjectNo());
//            map.put("PARK_LICENSE",lowerBody.getLicense());
//            //map.put("OWNED_CTIY", lowerBody.getAddress());
//            map.put("OPERA_NO", lowerBody.getOperatorNo());
//            map.put("START_VALIDITY",DateTimeUtils.formatDate(new Date(),"yyyy-MM-dd"));         //起始日期
//            map.put("END_VALIDITY",DateTimeUtils.formatDate(new Date((long) lowerBody.getExpiresTime()),"yyyy-MM-dd"));                                //截止日期
//            sysParametersMapper.batchUpdate(map);       //修改相关车场配置

            SysVersion sysVersion=sysVersionMapper.getVerBySoft(lowerBody.getVersion());        //判断当前的版本号是否存在
            if(sysVersion==null){
                SysVersion version=new SysVersion();
                version.setvSoft(lowerBody.getVersion());
                version.setvType(lowerBody.getVersionType());
                version.setCreateDate(new Date());
                sysVersionMapper.insert(version);
            }

            OfClientMQTT.unSubScript();        //取消线下订阅主题信息
            ClientMQTT.unScribe();            //取消线上订阅主题信息
            reloadSysParams();                //重新加载配置文件

            OfClientMQTT.subScipt();        //重新订阅线下的主题
            ClientMQTT.subScipt();          //重新订阅线上的主题
        }
    }

    /**
     * 重新加载车场参数
     */
    @Override
    public void reloadSysParams(){
        List<SysParameters> sysPara = sysParametersMapper.selectAll();
        GlobalPark.properties.clear();          //清除相关的配置
        // 服务器内存更新
        for (SysParameters sysParameters : sysPara) {
            String key = sysParameters.getParameterCode();
            String value = sysParameters.getParameterValue();
            GlobalPark.properties.setProperty(key, value);
        }
    }

    @Override
    public int updateByCode(String paramsCode,String paramsValue) {
        return sysParametersMapper.updateByCode(paramsCode,paramsValue);
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

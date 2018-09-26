package com.drzk.online.impl;

import com.drzk.mapper.ParkCarGroupMapper;
import com.drzk.mapper.PerCompanyMapper;
import com.drzk.mapper.PerDeptMapper;
import com.drzk.mapper.PerPersonsMapper;
import com.drzk.online.constant.ConstantUtil;
import com.drzk.online.onlineVo.*;
import com.drzk.online.service.OfMqttService;
import com.drzk.online.service.ParkStructureService;
import com.drzk.online.utils.ReflectionUtil;
import com.drzk.online.vo.ParkCarGroup;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.utils.JacksonUtils;
import com.drzk.utils.StringUtils;
import com.drzk.vo.PerCompany;
import com.drzk.vo.PerDept;
import com.drzk.vo.PerPersons;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 2018/9/11 cx
 */
@Service("parkStructureService")
public class ParkStructureServiceImpl implements ParkStructureService {

    @Resource
    private PerCompanyMapper perCompanyMapper;

    @Resource
    private PerDeptMapper perDeptMapper;

    @Resource
    private PerPersonsMapper perPersonsMapper;

    @Resource
    private ParkCarGroupMapper parkCarGroupMapper;

    @Resource
    private OfMqttService ofMqttService;


    @Override
    public Integer insertParkCompany(ParkCompanyVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( ParkCompanyVo.map, map );
        PerCompany perCompany = new PerCompany();
        ReflectionUtil.setMapToEntity( newMap, perCompany );
        perCompany.setIsLoad( ConstantUtil.SQL_SUCCESS );
        int result = perCompanyMapper.insert( perCompany );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer insertParkDepartment(ParkDepartmentVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( ParkDepartmentVo.map, map );
        PerDept perDept = new PerDept();
        ReflectionUtil.setMapToEntity( newMap, perDept );
        //获取公司id
        Integer companyId = perCompanyMapper.selectByUuid( config.getCompanyId() );
        Integer departmentId = perDeptMapper.selectByUuid( config.getParentId() );
        if (companyId != null) {
            perDept.setCompId( companyId );
        }
        if (departmentId != null) {
            perDept.setTopDeptId( departmentId );
        }
        perDept.setIsLoad( ConstantUtil.SQL_SUCCESS );
        int result = perDeptMapper.insert( perDept );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.DEPARTMENT_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.DEPARTMENT_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer insertParkPersonnel(ParkPersonnelVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( ParkPersonnelVo.map, map );
        PerPersons perPersons = new PerPersons();
        ReflectionUtil.setMapStrToEntity( newMap, perPersons );
        //获取公司id
        Integer companyId = perCompanyMapper.selectByUuid( config.getCompanyId() );
        Integer departmentId = perDeptMapper.selectByUuid( config.getParentId() );
        //车位
        ParkCarGroup carport =parkCarGroupMapper.getByCuid( config.getCarportGroupId());
        if(carport!=null&&carport.getId()!=null){
            perPersons.setPlaceId( carport.getId() );
        }
        if (companyId != null) {
            perPersons.setCompId( companyId);
        }
        if (departmentId != null) {
            perPersons.setDeptId( departmentId);
        }
        perPersons.setIsload( ConstantUtil.SQL_SUCCESS );
        //加密
        perPersons.setPerEmail( StringUtils.encode(perPersons.getPerEmail()) );
        perPersons.setPerTel( StringUtils.encode(perPersons.getPerTel()) );
        if(config.getEducation()!=null){
            perPersons.setEduLevel( EduLevelEnum.get( config.getEducation() ).getDesc() );
        }
        int result = perPersonsMapper.insertOnline( perPersons );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.PERSONNEL_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.PERSONNEL_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer saveParkCompany(ParkCompanyVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( ParkCompanyVo.map, map );
        PerCompany perCompany = new PerCompany();
        ReflectionUtil.setMapToEntity( newMap, perCompany );
        int result = perCompanyMapper.updateByUuid( perCompany );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer saveParkDepartment(ParkDepartmentVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( ParkDepartmentVo.map, map );
        PerDept perDept = new PerDept();
        ReflectionUtil.setMapToEntity( newMap, perDept );
        int result = perDeptMapper.updateByUuid( perDept );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.DEPARTMENT_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.DEPARTMENT_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer saveParkPersonnel(ParkPersonnelVo config) {
        Map<String, Object> map = ReflectionUtil.setEntityToMap( config );
        Map<String, Object> newMap = ReflectionUtil.setMapToMap( ParkPersonnelVo.map, map );
        PerPersons perPersons = new PerPersons();
        ReflectionUtil.setMapStrToEntity( newMap, perPersons );
        //获取公司id
        Integer companyId = perCompanyMapper.selectByUuid( config.getCompanyId() );
        Integer departmentId = perDeptMapper.selectByUuid( config.getParentId() );
        //车位
        ParkCarGroup carport =parkCarGroupMapper.getByCuid( config.getCarportGroupId());
        if(carport!=null&&carport.getId()!=null){
            perPersons.setPlaceId( carport.getId() );
        }
        if (companyId != null) {
            perPersons.setCompId( companyId);
        }
        if (departmentId != null) {
            perPersons.setDeptId( departmentId);
        }
        if(config.getEducation()!=null){
            perPersons.setEduLevel( EduLevelEnum.get( config.getEducation() ).getDesc() );
        }
        //加密
        perPersons.setPerEmail( StringUtils.encode(perPersons.getPerEmail()) );
        perPersons.setPerTel( StringUtils.encode(perPersons.getPerTel()) );
        int result = perPersonsMapper.updateByUuid( perPersons );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.PERSONNEL_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.PERSONNEL_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer deleteParkCompany(ParkCompanyVo config) {
        PerCompany perCompany = new PerCompany();
        perCompany.setCuid( config.getObjectId() );
        perCompany.setDelFrag( ConstantUtil.DISABLE );
        int result = perCompanyMapper.deleteByUuid( perCompany );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.COMPANY_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer deleteParkDepartment(ParkDepartmentVo config) {
        PerDept perDept = new PerDept();
        perDept.setPuid( config.getObjectId() );
        perDept.setUpdateFlag( ConstantUtil.DISABLE );
        int result = perDeptMapper.deleteByUuid( perDept );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.DEPARTMENT_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.DEPARTMENT_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public Integer deleteParkPersonnel(ParkPersonnelVo config) {
        PerPersons perPersons = new PerPersons();
        perPersons.setPuid( config.getObjectId() );
        perPersons.setDelFrag( ConstantUtil.DISABLE );
        int result = perPersonsMapper.deleteByUuid( perPersons );
        if (result > ConstantUtil.SQL_FAILED) {
            sendSyncResult( config.getObjectId(), ConstantUtil.PERSONNEL_METHOD, ConstantUtil.SQL_SUCCESS_STATUS, config.getParkingNo() );
        } else {
            sendSyncResult( config.getObjectId(), ConstantUtil.PERSONNEL_METHOD, ConstantUtil.SQL_FAILED_STATUS, config.getParkingNo() );
        }
        return result;
    }

    @Override
    public void syncParkPersonnel(String message) {
        MqttPayloadVo payloadVo = JacksonUtils.jsonToMqttHead( message );
        if (payloadVo != null) {
            MqttHeadVo head = payloadVo.getHead();
            String method = head.getMethod();
            String executeType = head.getExecuteType();
            if (method.equals( ConstantUtil.COMPANY_METHOD )) {
                ParkCompanyVo vo = JacksonUtils.jsonToMqttObject( message, ParkCompanyVo.class ).getBody();
                if (executeType.equals( ConstantUtil.INSERT_OPERATION )) {
                    insertParkCompany( vo );
                } else if (executeType.equals( ConstantUtil.UPDATE_OPERATION )) {
                    saveParkCompany( vo );
                } else if (executeType.equals( ConstantUtil.DELETE_OPERATION )) {
                    vo = new ParkCompanyVo();
                    vo.setObjectId( head.getSubId() );
                    vo.setParkingNo( head.getParkingNo() );
                    deleteParkCompany( vo );
                }
            } else if (method.equals( ConstantUtil.DEPARTMENT_METHOD )) {
                ParkDepartmentVo vo = JacksonUtils.jsonToMqttObject( message, ParkDepartmentVo.class ).getBody();
                if (executeType.equals( ConstantUtil.INSERT_OPERATION )) {
                    insertParkDepartment( vo );
                } else if (executeType.equals( ConstantUtil.UPDATE_OPERATION )) {
                    saveParkDepartment( vo );
                } else if (executeType.equals( ConstantUtil.DELETE_OPERATION )) {
                    vo = new ParkDepartmentVo();
                    vo.setObjectId( head.getSubId() );
                    vo.setParkingNo( head.getParkingNo() );
                    deleteParkDepartment( vo );
                }
            } else if (method.equals( ConstantUtil.PERSONNEL_METHOD )) {
                ParkPersonnelVo vo = JacksonUtils.jsonToMqttObject( message, ParkPersonnelVo.class ).getBody();
                if (executeType.equals( ConstantUtil.INSERT_OPERATION )) {
                    insertParkPersonnel( vo );
                } else if (executeType.equals( ConstantUtil.UPDATE_OPERATION )) {
                    saveParkPersonnel( vo );
                } else if (executeType.equals( ConstantUtil.DELETE_OPERATION )) {
                    vo = new ParkPersonnelVo();
                    vo.setObjectId( head.getSubId() );
                    vo.setParkingNo( head.getParkingNo() );
                    deleteParkPersonnel( vo );
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

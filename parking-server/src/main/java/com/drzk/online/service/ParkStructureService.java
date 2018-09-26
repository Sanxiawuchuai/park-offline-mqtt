package com.drzk.online.service;


import com.drzk.online.onlineVo.ParkCompanyVo;
import com.drzk.online.onlineVo.ParkDepartmentVo;
import com.drzk.online.onlineVo.ParkPersonnelVo;

/**
 * 车场人员 service
 * 2018/7/16 cx
 */
public interface ParkStructureService {

    /**
     * 新增车场公司
     */
    Integer insertParkCompany(ParkCompanyVo config);

    /**
     * 新增车场部门
     */
    Integer insertParkDepartment(ParkDepartmentVo config);

    /**
     * 新增车场人员
     */
    Integer insertParkPersonnel(ParkPersonnelVo config);

    /**
     * 保存车场公司
     */
    Integer saveParkCompany(ParkCompanyVo config);

    /**
     * 保存车场部门
     */
    Integer saveParkDepartment(ParkDepartmentVo config);

    /**
     * 保存车场人员
     */
    Integer saveParkPersonnel(ParkPersonnelVo config);

    /**
     * 删除车场公司
     */
    Integer deleteParkCompany(ParkCompanyVo config);

    /**
     * 删除车场部门
     * insert batch
     */
    Integer deleteParkDepartment(ParkDepartmentVo config);

    /**
     * 删除车场人员
     */
    Integer deleteParkPersonnel(ParkPersonnelVo config);

    /**
     * 同步人事信息
     */
    void syncParkPersonnel(String message);

}

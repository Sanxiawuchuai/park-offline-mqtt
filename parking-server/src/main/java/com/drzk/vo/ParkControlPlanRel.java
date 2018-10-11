package com.drzk.vo;

import java.io.Serializable;

public class ParkControlPlanRel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 328939963343670234L;

	/**
     * 主键id
     */
    private Integer id;

    /**
     * 级别id
     */
    private Integer planId;

    /**
     * 控制器id
     */
    private Integer machNo;

    /**
     * 主键id
     * @return id 主键id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键id
     * @param id 主键id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 级别id
     * @return plan_id 级别id
     */
    public Integer getPlanId() {
        return planId;
    }

    /**
     * 级别id
     * @param planId 级别id
     */
    public void setPlanId(Integer planId) {
        this.planId = planId;
    }

    /**
     * 控制器id
     * @return mach_no 控制器id
     */
    public Integer getMachNo() {
        return machNo;
    }

    /**
     * 控制器id
     * @param machNo 控制器id
     */
    public void setMachNo(Integer machNo) {
        this.machNo = machNo;
    }
}
package com.drzk.vo;

import java.io.Serializable;

public class ParkControlPlan implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7558799112848226498L;

	/**
     * 级别id
     */
    private Integer planId;

    /**
     * 级别名称
     */
    private String planName;

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
     * 级别名称
     * @return plan_name 级别名称
     */
    public String getPlanName() {
        return planName;
    }

    /**
     * 级别名称
     * @param planName 级别名称
     */
    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }
}
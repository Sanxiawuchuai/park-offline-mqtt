package com.drzk.vo;

import java.io.Serializable;

public class YktCardIssueRel implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7212945544690351375L;

	/**
     * 主键id
     */
    private Integer id;

    /**
     * 卡id
     */
    private Integer yktId;

    /**
     * 控制器id
     */
    private Integer machNo;

    /**
     * 下载的标志（0表示未下载 1表示已下载）
     */
    private Integer sign;

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
     * 卡id
     * @return ykt_id 卡id
     */
    public Integer getYktId() {
        return yktId;
    }

    /**
     * 卡id
     * @param yktId 卡id
     */
    public void setYktId(Integer yktId) {
        this.yktId = yktId;
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

    /**
     * 下载的标志（0表示未下载 1表示已下载）
     * @return sign 下载的标志（0表示未下载 1表示已下载）
     */
    public Integer getSign() {
        return sign;
    }

    /**
     * 下载的标志（0表示未下载 1表示已下载）
     * @param sign 下载的标志（0表示未下载 1表示已下载）
     */
    public void setSign(Integer sign) {
        this.sign = sign;
    }
}
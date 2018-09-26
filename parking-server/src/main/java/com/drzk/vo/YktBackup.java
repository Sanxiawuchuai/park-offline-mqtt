package com.drzk.vo;

import java.util.Date;

public class YktBackup {
    /**
     * 
     */
    private Integer id;

    /**
     * 备份的日期
     */
    private Date backDate;

    /**
     * 备注文件名
     */
    private String backName;

    /**
     * 备份路径
     */
    private String backPath;

    /**
     * 备注
     */
    private String remark;

    /**
     * 
     * @return id 
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id 
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 备份的日期
     * @return back_date 备份的日期
     */
    public Date getBackDate() {
        return backDate;
    }

    /**
     * 备份的日期
     * @param backDate 备份的日期
     */
    public void setBackDate(Date backDate) {
        this.backDate = backDate;
    }

    /**
     * 备注文件名
     * @return back_name 备注文件名
     */
    public String getBackName() {
        return backName;
    }

    /**
     * 备注文件名
     * @param backName 备注文件名
     */
    public void setBackName(String backName) {
        this.backName = backName == null ? null : backName.trim();
    }

    /**
     * 备份路径
     * @return back_path 备份路径
     */
    public String getBackPath() {
        return backPath;
    }

    /**
     * 备份路径
     * @param backPath 备份路径
     */
    public void setBackPath(String backPath) {
        this.backPath = backPath == null ? null : backPath.trim();
    }

    /**
     * 备注
     * @return remark 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 备注
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}
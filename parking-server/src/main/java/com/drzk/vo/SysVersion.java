package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class SysVersion implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2981890565044378087L;

	/**
     * 
     */
    private Integer id;

    /**
     * 版本号
     */
    private String vSoft;

    /**
     * 类型0软件版本1硬件版本
     */
    private Integer vType;

    /**
     * 发布时间
     */
    private Date pulishDate;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 备注
     */
    private String memo;

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
     * 版本号
     * @return v_soft 版本号
     */
    public String getvSoft() {
        return vSoft;
    }

    /**
     * 版本号
     * @param vSoft 版本号
     */
    public void setvSoft(String vSoft) {
        this.vSoft = vSoft == null ? null : vSoft.trim();
    }

    public Integer getvType() {
        return vType;
    }

    public void setvType(Integer vType) {
        this.vType = vType;
    }

    /**
     * 发布时间
     * @return pulish_date 发布时间
     */
    public Date getPulishDate() {
        return pulishDate;
    }

    /**
     * 发布时间
     * @param pulishDate 发布时间
     */
    public void setPulishDate(Date pulishDate) {
        this.pulishDate = pulishDate;
    }

    /**
     * 创建时间
     * @return create_date 创建时间
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * 创建时间
     * @param createDate 创建时间
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 更新时间
     * @return update_date 更新时间
     */
    public Date getUpdateDate() {
        return updateDate;
    }

    /**
     * 更新时间
     * @param updateDate 更新时间
     */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    /**
     * 备注
     * @return memo 备注
     */
    public String getMemo() {
        return memo;
    }

    /**
     * 备注
     * @param memo 备注
     */
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
}
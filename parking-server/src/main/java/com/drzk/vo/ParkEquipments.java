package com.drzk.vo;

import java.io.Serializable;

public class ParkEquipments implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7262920897163542242L;

	/**
     * 自增长设备id
     */
    private Integer eqId;

    /**
     * 设备名称
     */
    private String eqName;

    /**
     * 备注
     */
    private String memo;

    /**
     * 终端标识
     */
    private String clientNo;

    /**
     * 下载标记(控制器)
     */
    private String downLoad;
    
    private String euid;
    private Integer isLoad; 
    private Integer delFrag;
    

    public String getEuid()
	{
		return euid;
	}

	public void setEuid(String euid)
	{
		this.euid = euid;
	}

	public Integer getIsLoad()
	{
		return isLoad;
	}

	public void setIsLoad(Integer isLoad)
	{
		this.isLoad = isLoad;
	}

	public Integer getDelFrag()
	{
		return delFrag;
	}

	public void setDelFrag(Integer delFrag)
	{
		this.delFrag = delFrag;
	}

	/**
     * 自增长设备id
     * @return eq_id 自增长设备id
     */
    public Integer getEqId() {
        return eqId;
    }

    /**
     * 自增长设备id
     * @param eqId 自增长设备id
     */
    public void setEqId(Integer eqId) {
        this.eqId = eqId;
    }

    /**
     * 设备名称
     * @return eq_name 设备名称
     */
    public String getEqName() {
        return eqName;
    }

    /**
     * 设备名称
     * @param eqName 设备名称
     */
    public void setEqName(String eqName) {
        this.eqName = eqName == null ? null : eqName.trim();
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

    /**
     * 终端标识
     * @return client_no 终端标识
     */
    public String getClientNo() {
        return clientNo;
    }

    /**
     * 终端标识
     * @param clientNo 终端标识
     */
    public void setClientNo(String clientNo) {
        this.clientNo = clientNo == null ? null : clientNo.trim();
    }

    /**
     * 下载标记(控制器)
     * @return down_load 下载标记(控制器)
     */
    public String getDownLoad() {
        return downLoad;
    }

    /**
     * 下载标记(控制器)
     * @param downLoad 下载标记(控制器)
     */
    public void setDownLoad(String downLoad) {
        this.downLoad = downLoad == null ? null : downLoad.trim();
    }
}
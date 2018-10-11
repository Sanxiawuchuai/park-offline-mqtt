package com.drzk.online.vo;

import java.io.Serializable;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 服务器的一些基本参数
 * @date 2018/9/29 10:47
 */
public class ServerInfoVo extends OnlineBody implements Serializable {
    private static final long serialVersionUID = -2015921081045566844L;

    private String ipAddress;       //ip地址
    private String macAddress;      //mac地址
    private String cpuId;           //cpuId编号
    private String runType;         //运行模式

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getCpuId() {
        return cpuId;
    }

    public void setCpuId(String cpuId) {
        this.cpuId = cpuId;
    }

    public String getRunType() {
        return runType;
    }

    public void setRunType(String runType) {
        this.runType = runType;
    }
}

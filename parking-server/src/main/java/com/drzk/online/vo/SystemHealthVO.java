package com.drzk.online.vo;

/**
 * @author tf
 * 系统健康信息
 */
public class SystemHealthVO {
	 private Integer id;//编号
     private String comperIp;//电脑IP
     private String comCPU;//电脑CPU使用率
     private String network;//电脑网络
     private String memory;//电脑内存
     private String disk;//电脑磁盘
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getComperIp() {
		return comperIp;
	}
	public void setComperIp(String comperIp) {
		this.comperIp = comperIp;
	}
	public String getComCPU() {
		return comCPU;
	}
	public void setComCPU(String comCPU) {
		this.comCPU = comCPU;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getMemory() {
		return memory;
	}
	public void setMemory(String memory) {
		this.memory = memory;
	}
	public String getDisk() {
		return disk;
	}
	public void setDisk(String disk) {
		this.disk = disk;
	}
}

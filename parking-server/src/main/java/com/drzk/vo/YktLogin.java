package com.drzk.vo;

import java.io.Serializable;
import java.util.Date;

public class YktLogin implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1633225311925804084L;

	/**
     * 主键ID
     */
    private Integer id;

    /**
     * 操作日期
     */
    private Date logdate;

    /**
     * 操作员编号
     */
    private String username;

    /**
     * 计算机名（电脑IP）
     */
    private String computer;

    /**
     * 操作对象
     */
    private String logobj;

    /**
     * 操作方法
     */
    private String logmod;

    /**
     * 操作内容
     */
    private String logcon;

    /**
     * 主键ID
     * @return id 主键ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 主键ID
     * @param id 主键ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 操作日期
     * @return LogDate 操作日期
     */
    public Date getLogdate() {
        return logdate;
    }

    /**
     * 操作日期
     * @param logdate 操作日期
     */
    public void setLogdate(Date logdate) {
        this.logdate = logdate;
    }

    /**
     * 操作员编号
     * @return UserName 操作员编号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 操作员编号
     * @param username 操作员编号
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 计算机名（电脑IP）
     * @return Computer 计算机名（电脑IP）
     */
    public String getComputer() {
        return computer;
    }

    /**
     * 计算机名（电脑IP）
     * @param computer 计算机名（电脑IP）
     */
    public void setComputer(String computer) {
        this.computer = computer == null ? null : computer.trim();
    }

    /**
     * 操作对象
     * @return LogObj 操作对象
     */
    public String getLogobj() {
        return logobj;
    }

    /**
     * 操作对象
     * @param logobj 操作对象
     */
    public void setLogobj(String logobj) {
        this.logobj = logobj == null ? null : logobj.trim();
    }

    /**
     * 操作方法
     * @return LogMod 操作方法
     */
    public String getLogmod() {
        return logmod;
    }

    /**
     * 操作方法
     * @param logmod 操作方法
     */
    public void setLogmod(String logmod) {
        this.logmod = logmod == null ? null : logmod.trim();
    }

    /**
     * 操作内容
     * @return LogCon 操作内容
     */
    public String getLogcon() {
        return logcon;
    }

    /**
     * 操作内容
     * @param logcon 操作内容
     */
    public void setLogcon(String logcon) {
        this.logcon = logcon == null ? null : logcon.trim();
    }
}
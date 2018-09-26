package com.drzk.vo;

public class ParkChanelTemp {
    /**
     * 自增ID
     */
    private Integer id;

    /**
     * 通道名
     */
    private String chanelName;

    /**
     * 通道号
     */
    private Integer chanelId;

    /**
     * 自增ID
     * @return id 自增ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 自增ID
     * @param id 自增ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 通道名
     * @return chanel_name 通道名
     */
    public String getChanelName() {
        return chanelName;
    }

    /**
     * 通道名
     * @param chanelName 通道名
     */
    public void setChanelName(String chanelName) {
        this.chanelName = chanelName == null ? null : chanelName.trim();
    }

    /**
     * 通道号
     * @return chanel_id 通道号
     */
    public Integer getChanelId() {
        return chanelId;
    }

    /**
     * 通道号
     * @param chanelId 通道号
     */
    public void setChanelId(Integer chanelId) {
        this.chanelId = chanelId;
    }
}
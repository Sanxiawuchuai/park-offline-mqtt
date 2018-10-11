package com.drzk.online.service;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 线上下发的实现方法
 * @date 2018/9/11 11:37
 */
public interface DownOfService {

    /**
     * 黑白名单数据同步
     */
    public void syCarBlackInfo(String json);

    /**
     * 长租计费数据同步
     */
    public void syncLongRental(String json);

    /**
     * 车位组同步线上
     *
     * @param json void
     *             2018年9月12日
     */
    public void syCarportGroup(String json);

    /**
     * 临停计费数据同步
     */
    public void syncPaidStop(String json);

    /**
     * 商户数据同步
     */
    public void syncMerchants(String json);

    /**
     * 打折数据同步
     */
    public void syncDiscount(String json);

    /**
     * 超时设置数据同步
     */
    public void syncTimeOut(String json);


    /**
     * 车场设置同步
     */
    void syncParkConfig(String json);

    /**
     * 人事资料同步
     */
    void syncParkPersonnel(String json);

    /**
     * 出入口设备同步
     */
    void syncParkDevice(String json);

    /**
     * 发行车牌同步
     *
     * @param json
     */
    public void syncIssueInfo(String json);

    /**
     * 批量导入发行的车牌信息
     *
     * @param json
     */
    public void syncBatchRentParking(String json);

    /**
     * 车场编号
     *
     * @param json
     */
    public void lowerNum(String json);

    //远程开闸
    void syncOpenDoor(String json);

}

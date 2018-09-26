package com.drzk.online.service;


/** 定时扫描数据 */
public interface OnlineDSScanSever {

	/** 同步入场数据 (最多100条) */
    void parkcarin();
	/** 同步出场数据（最多100）  */
    void parkcarout();
	/** 岗亭信息 */
    void boxinfo();
	/** 控制器*/
    void controllerinfo();
	/** 相机 */
    void parkcameras();
	/** 黑名单 */
    void parkblackcar();
	/** 异常入场 */
    void parkunusualcarin();
	/** 充值信息 */
    void parkcaroperation();
	/** 交班信息  */
    void parksumusers();
	/** 中央缴费 */
    void parkcenterpayment();
	/** 月租费率 */
    void parkmonthlyfeetype();
	/** 系统健康 */
    void parkcompertask();
	/** 公司信息 */
    void companyinfo();
	/** 部门信息 */
    void departmentinfo();
	/** 同步删除的入场数据 */
    void parkdeletecarin();
	/** 非法开闸 */
    void parkopengateillegally();
	/** 车主用户信息 */
    void parkcaruser();
	/** 人员信息 */
    void personnelinfo();
	/** 车场配置信息  */
    void parksetting();
	/** 商家信息  */
    void couponeqinfo();
	/** 优惠券信息 */
    void couponrule();
	/** 优惠券使用明细 */
    void couponuserlist();
	/** 收费标准 */
    void feescale();
	/** 车位组信息 */
    void carGroupInfo();
    /**超时收费测试*/
    void pushOverTimes();
}

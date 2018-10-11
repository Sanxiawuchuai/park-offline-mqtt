package com.drzk.online.constant;


public class ConstantUtil {
   public static final String OFFLINE_TOPIC="device/server/data";
   public static final String OFFLINE_METHO="yun/parkcarin/v1";
   public static final String DS_TOPIC ="%s/park/%s/v1";
   public static final String DSReplayStart ="%s/park/";
   public static final String DSReplayEnd ="/v1";
   public static final String ScanCodeInOut ="server/data/publish/phone/%s";
   public static final String getCharge = "park/getcaradmissioninfo/%s";
   public static final String ONLINE_LOWER="%s/publish/offData";                     //线上主动下发的接口
   public static final String OFFLINE_PARKNO="registry/cloudPrivileges/%s";         //获取车场编号主题


    // 车场设置
    public static final String  CONFIG_METHOD = "syncParkingConfig";
    // 车场公司
    public static final String  COMPANY_METHOD = "syncParkCompany";
    // 车场部门
    public static final String  DEPARTMENT_METHOD = "syncParkDepartment";
    // 车场人事
    public static final String  PERSONNEL_METHOD = "syncParkPersonnel";
    // 车场岗亭
    public static final String  BOX_METHOD = "syncParkBox";
    // 车场控制器
    public static final String  CONTROLLER_METHOD = "syncParkController";
    // 车场相机
    public static final String  CAMERA_METHOD = "syncParkCamera";


    //线下发布同步状态数据
    public static final String  PUBLISH_ASYNC_STATUS_TO_ONLINE = "async/online/status/data";


    // 成功
    public static final String SUCCESS_STATUS = "2";
    // 失败
    public static final String FAILED_STATUS = "3";

    // 新增
    public static final String INSERT_OPERATION = "1";
    // 修改
    public static final String UPDATE_OPERATION = "2";
    // 删除
    public static final String DELETE_OPERATION = "3";
    //数据同步状态回调
    public static final String CALLBACK_OPERATION = "4";

    // 成功
    public static final int SQL_SUCCESS = 1;
    // 失败
    public static final int SQL_FAILED = 0;

    // 成功
    public static final int ENABLE =0;
    // 失败
    public static final int DISABLE =1;

    // 成功
    public static final int SQL_SUCCESS_STATUS = 2;
    // 失败
    public static final int SQL_FAILED_STATUS = 3;


   public static final String BLACK_CAR_METHOD = "specialVehicles"; // 黑名单车
   // 临停计费
   public static final String DEVICE_PAID_STOP_METHOD = "paidStop";
   // 长租计费
   public static final String DEVICE_LONG_RENTAL_METHOD = "longRental";
   // 打折
   public static final String DEVICE_DISCOUNT_METHOD = "discount";
   // 商户
   public static final String DEVICE_MERCHANTS_METHOD = "merchants";
   // 超时计费
   public static final String DEVICE_TIME_OUT_METHOD = "timeout";
   public static final String CARPORT_GROUP_METHOD = "syncCarGroup"; // 同步线上车位组信息
   public static final String PROJECT_METHOD = "baseProject"; // 同步线上基础项目信息
   public static final String PARK_NUM = "projectNo"; // 项目编号，车场编号
   public static final String PROJECT_NAME = "projectName"; // 项目名称，车场名称
   public static final String OPERA_NO = "operatorNo"; // 运营商编号
   public static final String OWNED_CTIY = "address"; // 项目所属地址
   public static final String END_VALIDITY = "expiresTime"; // 项目有效结束时间
   public static final String HAIRPIN_METHOD="syncRentParking";     //云端发行车牌同步
   public static final String BATCH__RENT_PARKING="syncBatchRentParking";       //批量导入发卡信息

   public static final String PUBLIC_KEY="123456";          //加密密钥

    // 车场相机
    public static final String  OPEN_DOOR = "openDoor";

}

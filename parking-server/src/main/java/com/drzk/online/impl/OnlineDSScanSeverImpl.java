package com.drzk.online.impl;

import com.drzk.common.ParkMethod;
import com.drzk.mapper.*;
import com.drzk.online.constant.ConstantUtil;
import com.drzk.online.service.OfMqttService;
import com.drzk.online.service.OnlineDSScanSever;
import com.drzk.online.utils.UploadUtil;
import com.drzk.online.vo.*;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.StringUtils;
import com.drzk.vo.*;
import com.drzk.vo.ParkCarOut;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

/**
 * 同步数据到云端
 *
 * @author yxf
 */
@Service
public class OnlineDSScanSeverImpl implements OnlineDSScanSever {

    private Logger log = Logger.getLogger("userLog");

    @Autowired
    ParkCarInMapper parkCarInMapper;
    @Autowired
    ConversionParameterClass conversionParameterClass;
    @Autowired
    OfMqttService ofMqttService;
    @Autowired
    ParkCarOutMapper parkCarOutMapper;
    @Autowired
    ParkLocalSetMapper parkLocalSetMapper;
    @Autowired
    ParkChannelSetMapper parkChannelSetMapper;
    @Autowired
    ParkCamSetMapper parkCamSetMapper;
    @Autowired
    ParkCarBlackListMapper parkCarBlackListMapper;
    @Autowired
    ParkCarInExceptionMapper parkCarInExceptionMapper;
    @Autowired
    VwParkIssueOperaMapper vwParkIssueOperaMapper;
    @Autowired
    YktCardIssueMapper yktCardIssueMapper;
    @Autowired
    ParkSumUserMapper parkSumUserMapper;
    @Autowired
    ParkCentralChargeMapper parkCentralChargeMapper;
    @Autowired
    ParkMonthSetMapper parkMonthSetMapper;
    @Autowired
    PerDeptMapper perDeptMapper;
    @Autowired
    VwParkCarOutMapper vwParkCarOutMapper;
    @Autowired
    VwParkCarIsuseMapper vwParkCarIsuseMapper;
    @Autowired
    VwPersoninfoMapper vwPersoninfoMapper;
    @Autowired
    ParkEquipmentsMapper parkEquipmentsMapper;
    @Autowired
    ParkDisInfoMapper parkDisInfoMapper;
    @Autowired
    ParkDisDetailMapper parkDisDetailMapper;
    @Autowired
    ParkStandardChargeMapper parkStandardChargeMapper;
    @Autowired
    VwDisCountInfoMapper vwDisCountInfoMapper;
    @Autowired
    PerCompanyMapper perCompanyMapper;
    @Autowired
    VwDeptCompanyMapper vwDeptCompanyMapper;
    @Autowired
    private ParkCarGroupMapper parkCarGroupMapper;
    @Autowired
    private ParkOverTimeSetMapper parkOverTimeSetMapper;
    @Autowired
    private BackUpParkCarInMapper backUpParkCarInMapper;

    /**
     * 收费标准推送
     */
    @Override
    public void feescale() {
        try {
            List<ParkStandardCharge> data = parkStandardChargeMapper.selectDSTop();
            if (data == null || data.size() == 0) {
                return;
            }
            List<FeescaleVO> sendData = data.stream().map(parkStandardCharge -> {
                return conversionParameterClass.getFeescaleVO(parkStandardCharge);
            }).collect(Collectors.toList());
            sendInfo(sendData, "park/feescale/v1", "feescale");
        } catch (Exception ex) {
			log.error("上传收费标准数据异常：",ex);
        }
    }

    @Override
    public void carGroupInfo() {
        try {
            List<ParkCarGroup> groupList = parkCarGroupMapper.findAllGroup();        //得到没有上传的车位组
            if (groupList.size() > 0 && groupList != null) {
                List<ParkCarGroupVO> list = groupList.stream().map(parkCarGroup -> {
                    return conversionParameterClass.convertGroup(parkCarGroup);
                }).collect(Collectors.toList());
                sendInfo(list, "park/parkcargroup/v1", "parkcargroup");            //发布车位组
            } else {
                return;
            }
        } catch (Exception e) {
            log.error("发布车位组信息异常:" ,e);
        }
    }

    //超时收费设置
    @Override
    public void pushOverTimes() {
        List<ParkOverTimeSet> overList=parkOverTimeSetMapper.findAllOverTime();		//超时收费
        try {
            if (overList != null && overList.size() > 0) {                    //组装超时收费
                List<TimeoutFeeVO> list = overList.stream().map(parkOverTimeSet -> {
                    return conversionParameterClass.convertTimeOut(parkOverTimeSet);
                }).collect(Collectors.toList());
                sendInfo(list, "park/timeout/v1", "timeout");
            }else{
                return;
            }
        }catch (Exception e){
            log.error("发布超时收费信息异常：",e);
        }
    }

    @Override
    public void reportAuth() {
        try{
            List<ServerInfoVo> list=new ArrayList<>();
            list.add(conversionParameterClass.convertServerInfo());
            sendInfo(list, "park/computer/v1", "computer");
        }catch (Exception e){
            log.error("上传云端授权信息异常：",e);
        }
    }

    @Override
    public void pushParkCarInImg() {
        try {
            List<BackUpParkCarIn> carInList = backUpParkCarInMapper.selectTopImg();
            if (carInList != null) {
                List<String> guidList = carInList.stream().map(backUpParkCarIn -> {
                    return UploadUtil.syFtpImg(backUpParkCarIn.getGuid(), backUpParkCarIn.getInPic());
                }).collect(Collectors.toList());
                guidList = guidList.stream().filter(str -> str != null).collect(Collectors.toList());
                log.debug("入场图片的guid是："+guidList);
                if(guidList != null && guidList.size()>0){
                    backUpParkCarInMapper.updateUploadImgStatus(1, guidList);        //更改图片上传的状态
                }
            }
        } catch (Exception ex) {
            log.error("发布同步入场图片异常：",ex);
        }
    }

    @Override
    public void pushParkCarOutImg() {
        try {
            List<ParkCarOut> carOutList = parkCarOutMapper.selectTopImg();
            if (carOutList != null) {
                List<String> cuidList = carOutList.stream().map(parkCarOut -> {
                    return UploadUtil.syFtpImg(parkCarOut.getGuid(), parkCarOut.getInPic());
                }).collect(Collectors.toList());
                cuidList = cuidList.stream().filter(str -> str != null).collect(Collectors.toList());
                log.debug("出场图片的guid是："+cuidList);
                if(cuidList != null && cuidList.size()>0) {
                    parkCarOutMapper.updateUploadImgStatus(1, cuidList);        //更改图片上传的状态
                }
            }
        } catch (Exception ex) {
            log.error("发布同步出场图片异常：",ex);
        }
    }

    @Override
    public void couponeqinfo() {
        try {
            Vector<ParkEquipments> data = parkEquipmentsMapper.selectDSTop();
            if (data == null || data.size() == 0) return;
            List<CouPonEqInfo> sendData = new ArrayList<CouPonEqInfo>();
            for (ParkEquipments model : data) {
                CouPonEqInfo vo = new CouPonEqInfo();
                vo = conversionParameterClass.getCouPonEqInfo(model);
                if (vo != null && vo.getObjectId() != null)
                    sendData.add(vo);
            }
            if (sendData != null && sendData.size() == 0) return;
            sendInfo(sendData,"park/couponeqinfo/v1","couponeqinfo");
        } catch (Exception ex) {
            log.error("发布商家信息异常：",ex);
        }
    }

    @Override
    public void couponrule() {
        try {
            Vector<ParkDisInfo> data = parkDisInfoMapper.selectDSTop();
            if (data == null || data.size() == 0) return;
            List<CouponRules> sendData = new ArrayList<CouponRules>();
            for (ParkDisInfo model : data) {
                CouponRules vo = new CouponRules();
                vo = conversionParameterClass.getCouponRules(model);
                if (vo != null && vo.getObjectId() != null)
                    sendData.add(vo);
            }
            if (sendData != null && sendData.size() == 0) return;
            sendInfo(sendData,"park/couponrule/v1","couponrule");
        } catch (Exception ex) {
            log.error("发布优惠券异常：",ex);
        }
    }

    @Override
    public void couponuserlist() {
        try {
            List<VwDisCountInfo> data = vwDisCountInfoMapper.selectTopDS();
            if (data == null || data.size() == 0) return;
            List<CouponUseDetailsVO> sendData = new ArrayList<CouponUseDetailsVO>();
            for (VwDisCountInfo model : data) {
                CouponUseDetailsVO vo = new CouponUseDetailsVO();
                vo = conversionParameterClass.getCouponUseDetailsVO(model);
                if (vo != null && vo.getObjectId() != null)
                    sendData.add(vo);
            }
            if (sendData != null && sendData.size() == 0) return;
            sendInfo(sendData,"park/couponuserlist/v1","couponuserlist");
        } catch (Exception ex) {
            log.error("发布优惠券使用明细：",ex);
        }
    }

    @Override
    public void parksetting() {
        try {
            ParkSettingVO vo = new ParkSettingVO();
            vo.setObjectId(GlobalPark.properties.getProperty("UUID"));
            vo.setCity(GlobalPark.properties.getProperty("OWNED_CTIY"));
            vo.setTotalSpaces(Integer.parseInt(GlobalPark.properties.getProperty("TOTAL_CARS")));
            vo.setTempSpaces(Integer.parseInt(GlobalPark.properties.getProperty("TEMP_CARS")));
            vo.setFixedSpaces(Integer.parseInt(GlobalPark.properties.getProperty("FIXED_CARS")));
            vo.setSpacesType(Integer.parseInt(GlobalPark.properties.getProperty("CAR_TYPE")));
            vo.setUserName(GlobalPark.properties.getProperty("CAM_USER"));
            vo.setPassword(GlobalPark.properties.getProperty("CAM_PWD"));
            vo.setCameraType(Integer.parseInt(GlobalPark.properties.getProperty("CAM_TYPE")));
            vo.setMqttIp(GlobalPark.properties.getProperty("MQTT_UPLINE"));
            vo.setCloudmqttIp(GlobalPark.properties.getProperty("MQTT_ONLINE"));
            vo.setIsUploadCloud(Integer.parseInt(GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD")));
            vo.setIsUploadImage(Integer.parseInt(GlobalPark.properties.getProperty("UPLOAD_IMG")));
            vo.setAllowExpiresDay(Integer.parseInt(GlobalPark.properties.getProperty("UPLOAD_IMG")));
            vo.setMonthCardChargeType(Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE")));
            vo.setNormalCharge(GlobalPark.properties.getProperty("CHARGE_STAND_TYPE"));
            vo.setTemporaryFreeType(Integer.parseInt(GlobalPark.properties.getProperty("FREE_TYPE_TEMPCAR")));
            vo.setDiscountModel(Integer.parseInt(GlobalPark.properties.getProperty("PREFER_MODEL")));
            vo.setCarNoMatchType(Integer.parseInt(GlobalPark.properties.getProperty("LICENCE_MATCH_TYPE")));
            vo.setDefaultCarPrefix(GlobalPark.properties.getProperty("LICENCE_PRE"));
            vo.setFreeCarPrefix(GlobalPark.properties.getProperty("HEAD_TAIL_LICENCE"));
            vo.setBroadcastLicensePlate(Integer.parseInt(GlobalPark.properties.getProperty("BROAD_LICENCE")));
            vo.setFullSpaceEnter(Integer.parseInt(GlobalPark.properties.getProperty("ISALLOW")));
            vo.setViewCurrentUserMoney(Integer.parseInt(GlobalPark.properties.getProperty("SHOW_MONEY")));
            vo.setFastExit(Integer.parseInt(GlobalPark.properties.getProperty("QUICK_APPERA")));
            vo.setCarLog(Integer.parseInt(GlobalPark.properties.getProperty("TARKING_LOG")));
            vo.setFixedSpcMEnterMExit(Integer.parseInt(GlobalPark.properties.getProperty("MORE_INT_MORE_OUT")));
            vo.setUdpDownPort(Integer.parseInt(GlobalPark.properties.getProperty("DOWN_PORT")));
            vo.setUdpUpPort(Integer.parseInt(GlobalPark.properties.getProperty("UP_PORT")));
            vo.setTcpPort(Integer.parseInt(GlobalPark.properties.getProperty("TCP_PORT")));
            vo.setCompensationControl(Integer.parseInt(GlobalPark.properties.getProperty("SLU_CONTROL")));
            vo.setBarrierInterface(Integer.parseInt(GlobalPark.properties.getProperty("GATE_INTEF")));
            vo.setMainboardType(Integer.parseInt(GlobalPark.properties.getProperty("BOARD_TYPE")));
            vo.setDyQrKeys(GlobalPark.properties.getProperty("ENCYP_KEY"));
            vo.setDyQrCodeUrl(GlobalPark.properties.getProperty("URL_PATH"));
            vo.setDelayType(Integer.parseInt(GlobalPark.properties.getProperty("DELAY_TYPE")));
            if (GlobalPark.properties.getProperty("PAY_PRO_MODEL") != null&&!GlobalPark.properties.getProperty("PAY_PRO_MODEL").equals("")) {
                vo.setAutoPayFirstModel(Integer.parseInt(GlobalPark.properties.getProperty("PAY_PRO_MODEL","0")));
                String paystr = GlobalPark.properties.getProperty("PAY_PRO_MODEL");
                if (paystr == null || paystr.contains("0")) {
                } else {
                    if (paystr.contains("1"))
                        vo.setAutoPayAfterExit(1);
                    if (paystr.contains("2"))
                        vo.setIsAutoPay(1);
                    if (paystr.contains("3"))
                        vo.setIsAutoPayByWechart(1);
                }
            }
            String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
            vo.setParkingNo(parkNo);
            vo.setDataOrigin("线下");
            if (vo != null) {
                List<ParkSettingVO> sendData = new ArrayList<ParkSettingVO>();
                sendData.add(vo);
                sendInfo(sendData,"park/parksetting/v1","parksetting");
            }
        } catch (Exception ex) {
            log.error("发布车场设置异常：",ex);
        }
    }

    @Override
    public void personnelinfo() {
        try {
            List<VwPersoninfo> perList = vwPersoninfoMapper.selectByIsload();
            if (perList == null || perList.size() == 0) return;
            List<PersonnelInfo> sendData = new ArrayList<PersonnelInfo>();
            for (VwPersoninfo model : perList) {
                PersonnelInfo vo = conversionParameterClass.getPersonnelInfo(model);
                sendData.add(vo);
            }
            sendInfo(sendData,"park/personnelinfo/v1","personnelinfo");
        } catch (Exception ex) {
            log.error("人事档案信息异常：",ex);
        }
    }

    @Override
    public void parkcaruser() {
        try {
            List<VwParkCarIsuse> changeVo = vwParkCarIsuseMapper.selectChange();
            if (changeVo == null || changeVo.size() == 0) return;
            List<ParkCaruser> sendData = new ArrayList<>();
            Integer delayType = Integer.parseInt(GlobalPark.properties.getProperty("DELAY_TYPE"));        //续费类型
            for (VwParkCarIsuse model : changeVo) {
                ParkCaruser vo = new ParkCaruser();
                vo.setContactName(model.getPerName());
                vo.setContactPhone(StringUtils.dencode(model.getPerTel()));
                vo.setCarport(model.getPlaceName());
                if (model.getPlaceName() != null)
                    vo.setIsHaveCarport(1);
                vo.setMessageOnOff(0);
                vo.setRemark(model.getMemo());
                vo.setCreateTime(new Date());
                vo.setParkingNo(GlobalPark.properties.getProperty("PARK_NUM"));
                vo.setDataOrigin("线下");
                vo.setObjectId(model.getCuid());
                vo.setRemark(model.getMemo());
                vo.setOperationType(model.getStatus());
                List<VwParkCarIsuse> carList = vwParkCarIsuseMapper.selectByPid(model.getpId());   //只取当前1条记录存放
                List<CarportAndCarVO> carPortandCar = new ArrayList<>();
                if (carList != null && carList.size() > 0) {
                    for (VwParkCarIsuse body : carList) {
                        CarportAndCarVO carPort = new CarportAndCarVO();
                        carPort.setCardTypeId(model.getCardType());
                        ParkAccountType cardType = ParkMethod.ParkAccountTypeByType(body.getCardType());
                        if (cardType != null)
                            carPort.setCardTypeName(cardType.getaName());
                        if(body.getStatus()==6){
                            carPort.setStatus(-1);
                        }else{
                            carPort.setStatus(body.getStatus());
                        }
                        carPort.setChargeType(delayType);
                        carPort.setBalanceMoney(body.getBalanceMoney().doubleValue());
                        carPort.setDeposit(body.getForegift().doubleValue());
                        carPort.setCarNo(body.getCarNo());
                        carPort.setCarportNo(body.getPlaceNum());
                        carPort.setPlateStyle(body.getCarColour());
                        carPort.setCarStatus(3);
                        carPort.setStartTime(body.getStartDate());
                        carPort.setEndTime(body.getEndDate());
                        carPort.setStype(body.getsType());
                        carPortandCar.add(carPort);
                    }

                }
                vo.setCarportAndCarList(carPortandCar);
                sendData.add(vo);
            }

            sendInfo(sendData,"park/parkcaruser/v1","parkcaruser");
        } catch (Exception ex) {
            log.error("发布车主用户异常：",ex);
        }
    }

    @Override
    public void departmentinfo() {
        try {
            List<VwDeptCompany> sumModelList = vwDeptCompanyMapper.selectTopDS();
            if (sumModelList == null || sumModelList.size() == 0) return;
            List<ParkDepartmentVO> sendList = new ArrayList<ParkDepartmentVO>();
            for (VwDeptCompany blackVO : sumModelList) {
                ParkDepartmentVO VO = conversionParameterClass.getParkDepartmentVO(blackVO);
                if (VO != null && VO.getObjectId() != null) {
                    sendList.add(VO);
                }
            }
            sendInfo(sendList,"park/departmentinfo/v1","departmentinfo");
        } catch (Exception ex) {
            log.error("发布部门管理异常：",ex);
        }
    }

    @Override
    public void parkopengateillegally() {
        try {
            List<VwParkCarOut> sumModelList = vwParkCarOutMapper.selectByIllegaOpenGate();
            if (sumModelList == null || sumModelList.size() == 0) return;
            List<String> guids = new ArrayList<String>();
            List<ParkOpenGateIllegally> sendList = new ArrayList<ParkOpenGateIllegally>();
            for (VwParkCarOut blackVO : sumModelList) {
                ParkOpenGateIllegally VO = conversionParameterClass.getParkOpenGateIllegally(blackVO);
                if (VO != null) {
                    guids.add(blackVO.getGuid());
                    sendList.add(VO);
                }
            }

            sendInfo(sendList,"park/parkopengateillegally/v1","parkopengateillegally");
        } catch (Exception ex) {
            log.error("发布非法开闸异常：",ex);
        }
    }

    @Override
    public void parkdeletecarin() {
        try {
            List<String> guids = parkCarInMapper.selectTopDSDelete();
            if (guids != null && guids.size() > 0) {
                List<ObjectBody> Vo = new ArrayList<>();
                for (String guid : guids) {
                    if (guid != null) {
                        ObjectBody model = new ObjectBody();
                        model.setObjectId(guid);
                        Vo.add(model);
                    }
                }
                sendInfo(Vo,"park/parkdeletecarin/v1","parkdeletecarin");
            }
        } catch (Exception ex) {
            log.error("同步删除入场数据异常：",ex);
        }
    }

    @Override
    public void companyinfo() {
        try {
            List<PerCompany> data = perCompanyMapper.selectTopDS();
            if (data == null || data.size() == 0) return;
            List<CompanyInfo> sendList = new ArrayList<CompanyInfo>();
            for (PerCompany blackVO : data) {
                CompanyInfo VO = conversionParameterClass.getCompanyInfo(blackVO);
                if (VO != null && VO.getObjectId() != null) {
                    sendList.add(VO);
                }
            }
            sendInfo(sendList,"park/companyinfo/v1","companyinfo");
        } catch (Exception ex) {
            log.error("发布公司数据异常：",ex);
        }
    }

    @Override
    public void parkcompertask() {
        try {
            ParkComperTask Vo = new ParkComperTask();
            Vo.setObjectId(GlobalPark.properties.getProperty("UUID"));
            Vo.setComCPU(GlobalPark.properties.getProperty("COMP_CPU"));
            Vo.setComperIp(GlobalPark.properties.getProperty("COMP_IP"));
            Vo.setDisk(GlobalPark.properties.getProperty("COMP_DISK"));
            Vo.setMacAddress(GlobalPark.properties.getProperty("COMP_ADD"));
            Vo.setMemory(GlobalPark.properties.getProperty("COMP_MEM"));
            Vo.setNetwork(GlobalPark.properties.getProperty("COMP_NET"));
            List<ParkComperTask> sendList = new ArrayList<ParkComperTask>();
            sendList.add(Vo);

            sendInfo(sendList,"park/parkcompertask/v1","parkcompertask");
        } catch (Exception ex) {
            log.error("发布系统配置信息异常：",ex);
        }
    }

    @Override
    public void parkmonthlyfeetype() {
        try {
            List<ParkMonthSet> sumModelList = parkMonthSetMapper.selectTopDS();
            if (sumModelList == null || sumModelList.size() == 0) return;
            List<ParkMonthlyFeeType> sendList = new ArrayList<ParkMonthlyFeeType>();
            for (ParkMonthSet blackVO : sumModelList) {
                ParkMonthlyFeeType VO = conversionParameterClass.getparkmonthlyfeetype(blackVO);
                if (VO != null && VO.getObjectId() != null) {
                    sendList.add(VO);
                }
            }
            sendInfo(sendList,"park/parkmonthlyfeetype/v1","parkmonthlyfeetype");
        } catch (Exception ex) {
            log.error("发布月租费率异常：",ex);
        }
    }

    @Override
    public void parkcenterpayment() {
        try {
            List<ParkCentralCharge> sumModelList = parkCentralChargeMapper.selectTopDS();
            if (sumModelList == null || sumModelList.size() == 0) return;
            List<String> guids = new ArrayList<String>();
            List<ParkCenterPayment> sendList = new ArrayList<ParkCenterPayment>();
            for (ParkCentralCharge blackVO : sumModelList) {
                ParkCenterPayment VO = conversionParameterClass.getParkCenterPayment(blackVO);
                if (VO != null && VO.getObjectId() != null) {
                    guids.add(blackVO.getPuid());
                    sendList.add(VO);
                }
            }
            sendInfo(sendList,"park/parkcenterpayment/v1","parkcenterpayment");
        } catch (Exception ex) {
            log.error("发布中央缴费异常：",ex);
        }
    }

    @Override
    public void parksumusers() {
        try {
            List<ParkSumUser> sumModelList = parkSumUserMapper.selectTopDS();
            if (sumModelList == null || sumModelList.size() == 0) return;
            List<ParkSumUsersVO> sendList = new ArrayList<ParkSumUsersVO>();
            for (ParkSumUser blackVO : sumModelList) {
                ParkSumUsersVO VO = conversionParameterClass.getParkSumUsersVO(blackVO);
                if (VO != null && VO.getObjectId() != null) {
                    sendList.add(VO);
                }
            }
            sendInfo(sendList,"park/parksumusers/v1","parksumusers");
        } catch (Exception ex) {
            log.error("发布交班记录异常：",ex);
        }
    }

    @Override
    public void parkblackcar() {
        try {
            List<ParkCarBlackList> blackModel = parkCarBlackListMapper.selectTopDS();
            if (blackModel == null || blackModel.size() == 0) return;
            List<ParkBlackCarVO> sendList = new ArrayList<ParkBlackCarVO>();
            for (ParkCarBlackList blackVO : blackModel) {
                ParkBlackCarVO VO = conversionParameterClass.getParkBlackCarVO(blackVO);
                if (VO != null && VO.getObjectId() != null) {
                    sendList.add(VO);
                }
            }
            sendInfo(sendList,"park/parkblackcar/v1","parkblackcar");
        } catch (Exception ex) {
            log.error("发布黑白名单异常：",ex);         ;
        }
    }

    @Override
    public void parkunusualcarin() {
        try {
            List<ParkCarInException> carInEModelList = parkCarInExceptionMapper.selectTopDS();
            if (carInEModelList == null || carInEModelList.size() == 0) return;
            List<String> guids = new ArrayList<String>();
            List<ParkUnusualCarInVO> sendList = new ArrayList<ParkUnusualCarInVO>();
            for (ParkCarInException blackVO : carInEModelList) {
                ParkUnusualCarInVO VO = conversionParameterClass.getParkUnusualCarInVO(blackVO);
                if (VO != null) {
                    guids.add(blackVO.getGuid());
                    sendList.add(VO);
                }
            }
            sendInfo(sendList,"park/parkunusualcarin/v1","parkunusualcarin");
        } catch (Exception ex) {
            log.error("发布异常入场数据异常：",ex);
        }
    }

    /**
     * 同步入场数据
     */
    @Override
    public void parkcarin() {
        try {
            List<ParkCarInVO> sendList = new ArrayList<ParkCarInVO>();
            List<BackUpParkCarIn> carInList = backUpParkCarInMapper.selectTopDS();            //查询所有未上传同步的数据
            if (carInList != null) {
                for (BackUpParkCarIn carIn : carInList) {
                    ParkCarInVO info = conversionParameterClass.getParkCarIn(carIn);
                    if (info != null && info.getObjectId() != null)
                        sendList.add(info);
                }
                sendInfo(sendList,"park/parkcarin/v1","parkcarin");
            }
        } catch (Exception ex) {
            log.error("发布同步入场数据异常：",ex);
        }
    }

    @Override
    public void parkcaroperation() {
        try {
            List<ParkCarOperationVO> sendList = new ArrayList<ParkCarOperationVO>();
            List<VwParkIssueOpera> carOutList = vwParkIssueOperaMapper.selectByIsload();
            if (carOutList != null && carOutList.size() > 0) {
                for (int k = 0; k < carOutList.size(); k++) {
                    ParkCarOperationVO vo = conversionParameterClass.getParkCarOperationVO(carOutList.get(k));
                    if (vo != null && vo.getObjectId() != null)
                        sendList.add(vo);
                }
                sendInfo(sendList,"park/parkcaroperation/v1","parkcaroperation");
            }
        } catch (Exception ex) {
            log.error("发布充值数据异常：",ex);
        }
    }

    @Override
    public void parkcarout() {
        try {
            List<ParkCarOutVO> sendList = new ArrayList<ParkCarOutVO>();
            List<ParkCarOut> carOutList = parkCarOutMapper.selectDSTop();
            if (carOutList != null && carOutList.size() > 0) {
                for (int k = 0; k < carOutList.size(); k++) {
                    ParkCarOutVO info = conversionParameterClass.getParkCarOutModel(carOutList.get(k));
                    if (info != null && info.getObjectId() != null) {
                        sendList.add(info);
                    }
                }
                sendInfo(sendList,"park/parkcarout/v1","parkcarout");
            }
        } catch (Exception ex) {
            log.error("发布出场数据异常：",ex);
        }

    }

    @Override
    public void boxinfo() {
        try {
            List<BoxInfoVO> sendList = new ArrayList<BoxInfoVO>();
            List<ParkLocalSet> localList = parkLocalSetMapper.selectDSTop();
            List<String> ids = new ArrayList<>();
            if (localList != null && localList.size() > 0) {
                for (ParkLocalSet model : localList) {
                    sendList.add(conversionParameterClass.getBoxInfoVO(model));
                    ids.add(model.getLuid());
                }
                sendInfo(sendList,"park/boxinfo/v1","boxinfo");
            }
        } catch (Exception ex) {
            log.error("发布岗亭数据异常：",ex);
        }
    }

    @Override
    public void controllerinfo() {
        try {
            List<ControllerInfoVO> sendList = new ArrayList<ControllerInfoVO>();
            List<ParkChannelSet> localList = parkChannelSetMapper.selectDSTop();
            if (localList != null && localList.size() > 0) {
                for (ParkChannelSet model : localList) {
                    ControllerInfoVO info = conversionParameterClass.getControlVO(model);
                    if (info != null && info.getObjectId() != null) {
                        sendList.add(info);
                    }
                }
                sendInfo(sendList,"park/controllerinfo/v1","controllerinfo");
            }
        } catch (Exception ex) {
            log.error("发布控制器数据异常：",ex);
        }
    }

    @Override
    public void parkcameras() {
        try {
            List<ParkCamerasVO> sendList = new ArrayList<ParkCamerasVO>();
            List<ParkCamSet> camList = parkCamSetMapper.selectDSTop();
            List<String> ids = new ArrayList<>();
            if (camList != null && camList.size() > 0) {
                for (ParkCamSet model : camList) {
                    ParkCamerasVO info = conversionParameterClass.getCamVO(model);
                    if (info != null && info.getObjectId() != null) {
                        sendList.add(info);
                    }
                }
                sendInfo(sendList,"park/parkcameras/v1","parkcameras");
            }
        } catch (Exception ex) {
            log.error("发布相机数据异常：",ex);
        }
    }

    /**
     * 全局统一的发布主题的方法
     *
     * @param sendData   发布的body
     * @param sendMethod 发布头部的方法
     * @param topicName  发布的主题标识
     * @param <T>
     */
    @Override
    public <T> void sendInfo(List<T> sendData, String sendMethod, String topicName) {
        if("1".equals(GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD"))) {        //如果连云端，则推送消息
            String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
            SendHead head = new SendHead();
            head.setMethod(sendMethod);
            String replyTopic = String.format(ConstantUtil.DS_TOPIC, parkNo, topicName);
            head.setReplyTopic(replyTopic);
            head.setParkingNo(parkNo);

            DataClass<T> test = new DataClass<>();
            test.setHead(head);
            test.setBody(sendData);
            String json = JsonUtil.objectToJsonStr(test);
            ofMqttService.sendMessage(ConstantUtil.OFFLINE_TOPIC, json,0);
        }
    }
}
package com.drzk.online.impl;

import com.drzk.mapper.*;
import com.drzk.online.constant.ConstantUtil;
import com.drzk.online.onlineVo.MqttHeadVo;
import com.drzk.online.onlineVo.MqttPayloadVo;
import com.drzk.online.service.*;
import com.drzk.online.vo.CarportAndCarVO;
import com.drzk.online.vo.ParkCarGroup;
import com.drzk.online.vo.ParkCaruser;
import com.drzk.utils.DateTimeUtils;
import com.drzk.utils.JacksonUtils;
import com.drzk.utils.JsonUtils;
import com.drzk.utils.StringUtils;
import com.drzk.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author zhangbin
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: 线上同步的具体实现方法
 * @date 2018/9/11 13:37
 */
@Service("downOfService")
public class DownOfServiceImpl implements DownOfService {
	private Logger log=LoggerFactory.getLogger(DownOfServiceImpl.class);

	@Resource
	private ParkCarBlackListMapper blackCarMapper;
	@Resource
	private ParkCarGroupMapper carGroupMapper;
	
	@Resource
	ParkMonthSetMapper parkMonthSetMapper;
	
	@Resource
	ParkAccountTypeMapper parkAccountTypeMapper;
	
	@Resource
	ParkStandardChargeMapper parkStandardChargeMapper;
	
	@Resource
	ParkEquipmentsMapper parkEquipmentsMapper;
	
	@Resource
	ParkDisInfoMapper parkDisInfoMapper;
	
	@Resource
	ParkOverTimeSetMapper parkOverTimeSetMapper;


    @Resource
    DeviceInfoService deviceInfoService;

    @Resource
    ParkStructureService parkStructureService;

    @Resource
    ParkConfigService parkConfigService;
    @Resource
    public YktIssueService yktIssueService;

    @Resource
    private SysParametersMapper sysParameterMapper;
    
    @SuppressWarnings("rawtypes")
	@Resource
    private OfMqttService ofMqttService;
    

	
	private static final String ID = "id";
	private static final String CARNO = "carNo";
	private static final String CARNOTYPE = "carNoType";
	private static final String CREATOR = "creator";
	private static final String CREATETIME = "createTime";
	private static final String DEL_METHOD = "3"; // 删除操作
	private static final Logger logger = LoggerFactory.getLogger(DownOfServiceImpl.class);

	/**
	 * 特殊车辆同步
	 */
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void syCarBlackInfo(String json) {
		System.out.println(json);
		@SuppressWarnings("rawtypes")
		MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(json, Map.class);
		MqttHeadVo head = dataMqtt.getHead();
		String method = head.getExecuteType();
		@SuppressWarnings("rawtypes")
		Map data = dataMqtt.getBody();
		ParkCarBlackList vo = new ParkCarBlackList();
		vo.setCarNo((String) data.get(CARNO)); // 车牌
		Integer carNoType = (Integer) data.get(CARNOTYPE);// 车牌类型(0无,1黑名单,2特种车辆)
		vo.setCarNoType(carNoType);
		vo.setCreateUserName("system");
		String date = (String) data.get(CREATETIME);
		vo.setCreateDate(DateTimeUtils.parseDate(date));
		vo.setModifyUserName("system");
		String modifyDate = (String) data.get("lastUpdateTime");
		vo.setModifyDate(DateTimeUtils.parseDate(modifyDate));
		Integer isStop = (Integer) data.get("isStop");
		vo.setIsStop(isStop);
		vo.setMemo((String) data.get("description"));
		vo.setIsLoad(1);
		if(method.equals(DEL_METHOD)) { // 删除
			vo.setDelFrag(1);
		}else {
			vo.setDelFrag(0);
		}
		String uuid = (String) data.get(ID);
		vo.setCuid(uuid);
		ParkCarBlackList car = blackCarMapper.selectByCarNo(vo.getCarNo());
		Integer result;
		// 存在则更新
		if (car != null) {
			result = blackCarMapper.updateByCuid(vo);
		} else {
			result = blackCarMapper.insert(vo);
		}
		if (result >= 1) {
			logger.debug("syCarBlackInfo success");
			sendSyncResult(vo.getCuid(), ConstantUtil.BLACK_CAR_METHOD, 2, dataMqtt.getHead().getParkingNo()); // 2成功
		} else {
			logger.debug("sycarblack info failed {}", vo);
			sendSyncResult(vo.getCuid(), ConstantUtil.BLACK_CAR_METHOD, 3, dataMqtt.getHead().getParkingNo()); // 3失败
		}
	}
	
	/**
	 * 长租计费同步
	 */
	@SuppressWarnings("rawtypes")
	@Override
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	public void syncLongRental(String json) {
		MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(json, Map.class);
		Map data = dataMqtt.getBody();
		ParkMonthSet month=parkMonthSetMapper.selectByGuid((String)data.get("objectId"));
		ParkAccountType parkAccount=new ParkAccountType();
		parkAccount.setaCustname((String) data.get("packageName"));
		parkAccount.setaType(Integer.valueOf((String) data.get("packageType")));
		ParkMonthSet parkMonth=new ParkMonthSet();
		parkMonth.setAccountType(Integer.valueOf((String) data.get("packageType")));
		parkMonth.setMemo((String) data.get("remark"));
		parkMonth.setChargeMoney(Double.valueOf((String)data.get("packageCharge")));
		parkMonth.setsType(Integer.valueOf((String)data.get("packageDuration")));
		parkMonth.setCreateDate(DateTimeUtils.parseDate((String) data.get("createTime")));
		parkMonth.setCreateUserName((String) data.get("creator"));
		parkMonth.setModifyDate(DateTimeUtils.parseDate((String) data.get("lastUpdateTime")));
		parkMonth.setModifyUserName((String) data.get("lastUpdateName"));
		parkMonth.setIsLoad(1);
		int result=3;
		if(month!=null){
			parkMonth.setPuid((String)data.get("objectId"));
			int count=parkMonthSetMapper.updatePrimaryKey(parkMonth);
			parkAccountTypeMapper.updateName(parkAccount);
			if(count>0){
				result=2;
			}
		}else{
			parkMonth.setPuid((String)data.get("objectId"));
			int count=parkMonthSetMapper.insertSelective(parkMonth);
			if(count>0){
				result=2;
			}
		}
		sendSyncResult((String)data.get("objectId"),ConstantUtil.DEVICE_LONG_RENTAL_METHOD,result,null);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	/**
	 * 临停计费同步
	 */
	public void syncPaidStop(String json) {
		// TODO Auto-generated method stub
		logger.debug(json);
		MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(json, Map.class);
		Map data = dataMqtt.getBody();
		ParkStandardCharge ps=parkStandardChargeMapper.selectByGuid((String)data.get("objectId"));
		ParkStandardCharge vo=new ParkStandardCharge();
		vo.setaType(Integer.valueOf((String) data.get("crossTimeType")));
		vo.setaZero((Double) data.get("crossMoney"));
		vo.setCardType((Integer) data.get("packageId"));
		vo.setTopMoney((Double) data.get("mostMoney"));
		vo.setStartTime(DateTimeUtils.parseDate((String) data.get("sTime")));
		vo.setPuid((String)data.get("objectId"));
		vo.setIsLoad(1);
		int result=3;
		vo.setFreeTime((Integer) data.get("freeTime"));
		vo.setEndTime(DateTimeUtils.parseDate((String) data.get("eTime")));
		vo.setIsCrossTime((Integer)data.get("isMoreTimesFree"));
		vo.setDelFrag((Integer)data.get("deleteCode"));
		Object map=data.get("segmentCharges");
		List<LinkedHashMap<String, Object>> list=(List<LinkedHashMap<String, Object>>) map;
		for(LinkedHashMap<String, Object> obj:list){
			 int value=0;
		for (Map.Entry<String, Object> entry : obj.entrySet()) { 
			if(entry.getKey().equals("time")){
				value=(int) entry.getValue();
			}if(entry.getKey().equals("charge")){
				if(value==1){
					vo.setChHour1((Double) entry.getValue());
				}else if(value==2){
					vo.setChHour2((Double) entry.getValue());
				}else if(value==3){
					vo.setChHour3((Double) entry.getValue());
				}else if(value==4){
					vo.setChHour4((Double) entry.getValue());
				}else if(value==5){
					vo.setChHour5((Double) entry.getValue());
				}else if(value==6){
					vo.setChHour6((Double) entry.getValue());
				}else if(value==7){
					vo.setChHour7((Double) entry.getValue());
				}else if(value==8){
					vo.setChHour8((Double) entry.getValue());
				}else if(value==9){
					vo.setChHour9((Double) entry.getValue());
				}else if(value==10){
					vo.setChHour10((Double) entry.getValue());
				}else if(value==11){
					vo.setChHour11((Double) entry.getValue());
				}else if(value==12){
					vo.setChHour12((Double) entry.getValue());
				}else if(value==13){
					vo.setChHour13((Double) entry.getValue());
				}else if(value==14){
					vo.setChHour14((Double) entry.getValue());
				}else if(value==15){
					vo.setChHour15((Double) entry.getValue());
				}else if(value==16){
					vo.setChHour16((Double) entry.getValue());
				}else if(value==17){
					vo.setChHour17((Double) entry.getValue());
				}else if(value==18){
					vo.setChHour18((Double) entry.getValue());
				}else if(value==19){
					vo.setChHour19((Double) entry.getValue());
				}else if(value==20){
					vo.setChHour20((Double) entry.getValue());
				}else if(value==21){
					vo.setChHour21((Double) entry.getValue());
				}else if(value==22){
					vo.setChHour22((Double) entry.getValue());
				}else if(value==23){
					vo.setChHour23((Double) entry.getValue());
				}else if(value==24){
					vo.setChHour24((Double) entry.getValue());
				}
				}
			}
		}
		if(ps!=null){
			int count=parkStandardChargeMapper.updateParkStandard(vo);
			if(count>0){
				result=2;
			}
		}else{
			int count=parkStandardChargeMapper.insertSelective(vo);
			if(count>0){
				result=2;
			}
		}
		sendSyncResult((String)data.get("objectId"),ConstantUtil.DEVICE_PAID_STOP_METHOD,result,null);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public void syncTimeOut(String json) {
		// TODO Auto-generated method stub
		logger.debug(json);
		MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(json, Map.class);
		Map data = dataMqtt.getBody();
		ParkOverTimeSet pt=parkOverTimeSetMapper.selectByCardType((Integer)data.get("cardType"));
		ParkOverTimeSet po=new ParkOverTimeSet();
		po.setCardType((Integer) data.get("cardType"));
		po.setFreeInclude((Integer) data.get("isfree"));
		po.setOverTimeMinute((Integer) data.get("timeout"));
		po.setOverTimeAmount((Double)data.get("timeoutFee"));
		po.setIsLoad(1);
		po.setPuid((String)data.get("objectId"));
		int result=3;
		if(pt!=null){
			int count=parkOverTimeSetMapper.updatePrimaryKey(po);
			if(count>0){
				result=2;
			}
		}else{
			int count=parkOverTimeSetMapper.insertSelective(po);
			if(count>0){
				result=2;
			}
		}
		sendSyncResult((String)data.get("objectId"),ConstantUtil.DEVICE_TIME_OUT_METHOD,result,null);
	}
	
	/**
	 * 商户
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void syncMerchants(String json) {
		// TODO Auto-generated method stub
		logger.debug(json);
		MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(json, Map.class);
		Map data = dataMqtt.getBody();
		ParkEquipments pe=parkEquipmentsMapper.selectByGuid((String)data.get("objectId"));
		ParkEquipments parkEquipment=new ParkEquipments();
		parkEquipment.setEuid((String)data.get("objectId"));
		parkEquipment.setEqName((String)data.get("businessName"));
		parkEquipment.setIsLoad(1);
		int result=3;
		parkEquipment.setDelFrag((Integer)data.get("deleteCode"));
		if(pe!=null){
			int count=parkEquipmentsMapper.updatePrimaryKey(parkEquipment);
			if(count>0){
				result=2;
			}
		}else{
			int count=parkEquipmentsMapper.insertSelective(parkEquipment);
			if(count>0){
				result=2;
			}
		}
		sendSyncResult((String)data.get("objectId"),ConstantUtil.DEVICE_MERCHANTS_METHOD,result,null);
	}

	/**
	 * 打折
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void syncDiscount(String json) {
		// TODO Auto-generated method stub
		logger.debug(json);
		MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(json, Map.class);
		Map data = dataMqtt.getBody();
		ParkDisInfo pd=parkDisInfoMapper.selectByGuid((String)data.get("objectId"));
		ParkDisInfo discount=new ParkDisInfo();
		discount.setPuid((String)data.get("objectId"));
		discount.setDiscountName((String)data.get("disconutName"));
		discount.setMemo((String)data.get("disconutRmark"));
		discount.setDiscountId((String)data.get("disconutNo"));
		discount.setDiscountType(Integer.valueOf((String)data.get("disconutType")));
		discount.setDiscountAmount(Double.valueOf((String)data.get("disconutValue")));
		discount.setIsLoad(1);
		int result=3;
		discount.setDelFrag((Integer)data.get("deleteCode"));
		ParkEquipments pe=parkEquipmentsMapper.selectByGuid((String)data.get("businessId"));
		if(pe!=null){
			discount.setEqid(pe.getEqId());
		}
		discount.setCreateDate(DateTimeUtils.parseDate((String) data.get("createTime")));
		discount.setCreateUserName((String) data.get("creator"));
		discount.setModifyDate(DateTimeUtils.parseDate((String) data.get("lastUpdateTime")));
		discount.setModifyUserName((String) data.get("lastUpdateName"));
		if(pd!=null){
			int count=parkDisInfoMapper.updatePrimaryKey(discount);
			if(count>0){
				result=2;
			}
		}else{
			int count=parkDisInfoMapper.insertSelective(discount);
			if(count>0){
				result=2;
			}
		}
		sendSyncResult((String)data.get("objectId"),ConstantUtil.DEVICE_DISCOUNT_METHOD,result,null);
	}


    /**
	 * 同步线上车位组 
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public void syCarportGroup(String json) {
        System.out.println(json);
        @SuppressWarnings("rawtypes")
		MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(json, Map.class);
		@SuppressWarnings("rawtypes")
		Map data = dataMqtt.getBody();
		MqttHeadVo head = dataMqtt.getHead();
		String method = head.getExecuteType();
		ParkCarGroup vo = new ParkCarGroup();
		vo.setCuid((String) data.get(ID));
		vo.setCreateDate(DateTimeUtils.parseDate((String) data.get(CREATETIME)));
		vo.setCreateUserName("system");
		if(method.equals(DEL_METHOD)) { // 删除
			vo.setDelFrag(1);
		}else{
		    vo.setDelFrag(0);
		}
		vo.setIsLoad(1);
		vo.setPlaceName((String) data.get("carportGroupName"));
		vo.setMemo((String) data.get("remark"));
		vo.setModifyDate(DateTimeUtils.parseDate((String) data.get("lastUpdateTime")));
		vo.setModifyUserName("system");
		vo.setPlaceNum((Integer) data.get("carportNumber"));
		vo.setpType((Integer) data.get("fullHold"));
		ParkCarGroup group = carGroupMapper.getByGroupName(vo.getPlaceName());
		Integer result;
		if(group != null) {
			result = carGroupMapper.update(vo);
		}else {
			result = carGroupMapper.insert(vo);
		}
		if (result >= 1) {
			logger.debug("sync carport group success");
			sendSyncResult(vo.getCuid(), ConstantUtil.CARPORT_GROUP_METHOD, 2, dataMqtt.getHead().getParkingNo());
		} else {
			logger.debug("sync carport group failed {}", vo);
			sendSyncResult(vo.getCuid(), ConstantUtil.CARPORT_GROUP_METHOD, 3, dataMqtt.getHead().getParkingNo());
		}
	}

	/**
	 * 同步线上基础项目信息
	 */
	@Transactional(readOnly = false, rollbackFor = Exception.class)
	@Override
	public void syBaseProject(String json) {
		System.out.println(json);
		@SuppressWarnings("rawtypes")
		MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(json, Map.class);
		@SuppressWarnings("unchecked" )
		Map<String,Object> data = dataMqtt.getBody();
		int result = 0;
		for(Entry<String,Object> entry : data.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			SysParameters sysPara;
			switch(key) {
			case ConstantUtil.PARK_NUM:
				sysPara = new SysParameters();
				sysPara.setParameterCode("PARK_NUM");
				sysPara.setParameterValue((String) value);
				result += sysParameterMapper.updateByCode(sysPara);
				break;
			case ConstantUtil.PROJECT_NAME:
				sysPara = new SysParameters();
				sysPara.setParameterCode("PROJECT_NAME");
				sysPara.setParameterValue((String) value);
				result += sysParameterMapper.updateByCode(sysPara);
				break;
			case ConstantUtil.OPERA_NO:
				sysPara = new SysParameters();
				sysPara.setParameterCode("OPERA_NO");
				sysPara.setParameterValue((String) value);
				result += sysParameterMapper.updateByCode(sysPara);
				break;
			case ConstantUtil.OWNED_CTIY:
				sysPara = new SysParameters();
				sysPara.setParameterCode("OWNED_CTIY");
				sysPara.setParameterValue((String) value);
				result += sysParameterMapper.updateByCode(sysPara);
				break;
			case ConstantUtil.END_VALIDITY:
				sysPara = new SysParameters();
				sysPara.setParameterCode("END_VALIDITY");
				sysPara.setParameterValue(DateTimeUtils.formatDate(new Date((long) value)));
				result += sysParameterMapper.updateByCode(sysPara);
				break;
			default :
				sysPara = new SysParameters();
				sysPara.setParameterCode("START_VALIDITY");
				sysPara.setParameterValue(DateTimeUtils.formatDate(new Date()));
				result += sysParameterMapper.updateByCode(sysPara);
				break;
			}
		}
		if(result >=1) {
			System.out.println("save project success");
			sendSyncResult(dataMqtt.getHead().getSubId(), ConstantUtil.PROJECT_METHOD, 2, dataMqtt.getHead().getParkingNo());
		}else{
			System.out.println("save project failed");
			sendSyncResult(dataMqtt.getHead().getSubId(), ConstantUtil.PROJECT_METHOD, 3, dataMqtt.getHead().getParkingNo());
		}
	}


    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void syncParkConfig(String json) {
        parkConfigService.syncParkConfig( json );
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void syncParkPersonnel(String json) {
		System.out.println(json);
        parkStructureService.syncParkPersonnel( json );
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void syncParkDevice(String json) {
        deviceInfoService.syncParkDevice( json );
    }

	@Override
	@Transactional(readOnly = false,rollbackFor = Exception.class)
	public void syncIssueInfo(String json) {
		try {
			MqttPayloadVo<Map> dataMqtt = JacksonUtils.jsonToMqttObject(json, Map.class);
			Map<String, Object> data = dataMqtt.getBody();        //得到回调的body对象属性
            ParkCaruser parkCaruser= (ParkCaruser) StringUtils.map2Object(data,ParkCaruser.class);
            List<CarportAndCarVO> list=JsonUtils.toList(JsonUtils.toJson(parkCaruser.getCarportAndCarList()),CarportAndCarVO.class);
			String objectId=(String) data.get("id");			//主键ID
			String perName=parkCaruser.getContactName();			//人员名称
			String perTel=parkCaruser.getContactPhone();			//人员电话
			Integer operationType=parkCaruser.getOperationType();			//操作类型

			list.stream().forEach(carportAndCarVO -> {
			    carportAndCarVO.setPayType(parkCaruser.getPayType());
				int result=yktIssueService.saveYktIssue(carportAndCarVO,objectId,perName,perTel,operationType);
				if(result==1){
					sendSyncResult(objectId,ConstantUtil.HAIRPIN_METHOD,2,dataMqtt.getHead().getParkingNo());		//发送成功
				}else{
					sendSyncResult(objectId,ConstantUtil.HAIRPIN_METHOD,3,dataMqtt.getHead().getParkingNo());		//发送失败
				}
			});
			yktIssueService.reloadCarGrantData();           //推送到硬件，控制器类

		}catch (Exception e){
			log.debug("保存发行信息失败："+e.getMessage());
		}
	}

    /**
     * 同步结果
     * @param objectId  objectId
     * @param method  method
     * @param result 2 成功 3 失败
     * @param parkingNo 车场编号
     */
    @SuppressWarnings("rawtypes")
	private void sendSyncResult(String objectId,String method,Integer result, String parkingNo) {
        MqttHeadVo head = new MqttHeadVo();
        head.setSubId( objectId );
        head.setMethod( method );
        head.setStatus( result.toString() );
        head.setParkingNo( parkingNo );
        MqttPayloadVo replay=new MqttPayloadVo();
        replay.setHead( head );
        ofMqttService.sendMessage(ConstantUtil.PUBLISH_ASYNC_STATUS_TO_ONLINE,JacksonUtils.objectToJson( replay ),0);
    }
}

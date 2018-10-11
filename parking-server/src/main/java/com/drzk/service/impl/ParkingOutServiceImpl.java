package com.drzk.service.impl;

import com.drzk.bean.MqttMessageVO;
import com.drzk.charge.AbstractChargeStandard;
import com.drzk.charge.Charge;
import com.drzk.charge.standard2.StandChargeRule;
import com.drzk.charge.vo.PaymentVo;
import com.drzk.common.InOutRealTimeBase;
import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.mapper.*;
import com.drzk.offline.vo.ParkOutChanelCorrectingBody;
import com.drzk.online.impl.ConversionParameterClass;
import com.drzk.online.service.OfMqttService;
import com.drzk.online.service.OnlineDSScanSever;
import com.drzk.online.utils.UploadUtil;
import com.drzk.online.vo.ParkCarOutVO;
import com.drzk.service.entity.*;
import com.drzk.utils.BeanCopierUtil;
import com.drzk.utils.DateTimeUtils;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.SpringUtil;
import com.drzk.vo.*;
import org.apache.log4j.Logger;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service("parkingOutService")
public class ParkingOutServiceImpl extends AbstractParkingService {

	@KSession("ksession_parkout")
	private KieSession kSession;

	@Autowired
	private ParkCarInMapper parkCarInMapper;
	@Autowired
	private ParkCarOutMapper parkCarOutMapper;
	@Autowired
	private BackUpParkCarInMapper backUpParkCarInMapper;
	@Autowired
	private ParkDisInfoMapper parkDisInfoMapper;
	
	@Autowired
	private YktCardIssueMapper yktCardIssueMapper;
	
	@Autowired
	private ParkFamilyGroupChargeMapper parkFamilyGroupChargeMapper;
    @Autowired
    private ConversionParameterClass conversionParameterClass;
    @Autowired
    private OfMqttService ofMqttService;
    @Autowired
    private OnlineDSScanSever onlineDSScanSever;

	private static Logger logger = Logger.getLogger("userLog");
	//时间格式化
	SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	/**
	 * 获取入场记录
	 * @author chenlong
	 * @return
	 * @since JDK 1.8
	 */
	private ParkCarIn hasParkInRecord(OutRealTimeBase out) {
		String rCarNo=out.getCarNo().trim().substring(1);
		ParkCarIn inModelGet = parkCarInMapper.selectTop(rCarNo,out.isSmall()? 1:0);
		return inModelGet;
	}
	/** 免费车获取入场记录 */
	@Override
	public void getFreeInRecord(OutRealTimeBase out) {
        try {
        	logger.debug("getFreeInRecord:"+out.getCarNo());
        	ParkCarIn in=new ParkCarIn();
        	String carNo = null;
			if (out != null) {
				carNo = out.getCarNo();
			}
			// 先进行精确查找，如果有记录，直接返回
			in = hasParkInRecord(out);
			if (in != null) {
				out.setInRecord(in);
				inRecodeToParkOut(in, out.getOut());
			}else { //没有入场记录
				in=new ParkCarIn();
				in.setCarNo(out.getCarNo());
				in.setYktId(0);
				in.setEmpName("");
				in.setInTime(out.getNowDate());
				in.setCardType(InOutRealTimeBase.FREE_CAR_A);
				in.setInUserName(ParkMethod.getLoginName(out.getChannelSet().getBoxId()));
				out.setInRecord(in);
				inRecodeToParkOut(in, out.getOut());
			}
        } catch (Exception ex) {
        	logger.error("免费车获取入场记录:", ex);
		}
	}
	
	/**
	 * 查找入场记录
	 */
	@Override
	public List<ParkCarIn> getSimilarCar(OutRealTimeBase out) {
		List<ParkCarIn> result = new ArrayList<>();
		try {
			logger.debug("getSimilarCar:"+out.getCarNo());
			String carNo = null;
			if (out != null) {
				carNo = out.getCarNo();
			}
          
			if (carNo == null || carNo.isEmpty())
				return result;

			// 此种情况是手工出场时，岗亭端选择了入场记录，不需要再查询
			if (out.getInRecord() != null) {
				result.add(out.getInRecord());
				inRecodeToParkOut(out.getInRecord(), out.getOut());
				initPayMentVo(out);
				return result;
			}
			// 先进行精确查找，如果有记录，直接返回
			ParkCarIn in = hasParkInRecord(out);
			if (in != null) {
				out.setInRecord(in);
				result.add(in);
				inRecodeToParkOut(in, out.getOut());
				initPayMentVo(out);
				return result;
			}

			List<String> allCarNo = parkCarInMapper.selectAllParkInCarNo();
			if (allCarNo == null || allCarNo.size() == 0)
				return result;
			int distance = Integer.parseInt(GlobalPark.properties.getProperty("LICENCE_MATCH_TYPE"));
			Map<Integer, List<String>> selectCarNo = ParkMethod.findAllSimilarCarNo(carNo, allCarNo, distance);
			if (selectCarNo == null || selectCarNo.size() == 0) {
				out.setSimilarCarin(result);
				return result;
			}
			List<String> selectedCarNo = new ArrayList<>();
			for (int key : selectCarNo.keySet()) {
				selectedCarNo.addAll(selectCarNo.get(key));
			}

			result = parkCarInMapper.selectByCarNos(selectedCarNo);
			if (result.size() == 1) {
				inRecodeToParkOut(result.get(0), out.getOut());
				out.setInRecord(result.get(0));
			} else {
				out.setSimilarCarin(result);
			}
			return result;
		} catch (Exception ex) {
			logger.error("查找入场记录:", ex);
			return result;
		}
	}

	/*
	 * 2018/05/24 yangxf 实时处理出场
	 */
	@Transactional(rollbackFor = Exception.class)
	public int saveParkOutRecord(OutRealTimeBase outRealTimeBase) {
		try {
			logger.debug("saveParkOutRecord:"+outRealTimeBase.getCarNo());
			ParkCarOut parkCar_out = outRealTimeBase.getOut();
			int OutChannelType = -1;
			ParkCarIn InModel = outRealTimeBase.getInRecord();
			if (InModel != null)// 存在入场记录
			{
				int InChannelType = 0;// 入口类型
				ParkChannelSet parkChannelset = ParkMethod
						.getChannelSetByControlIndex(InModel.getMachNo() == null ? 0 : InModel.getMachNo().byteValue());
				if (parkChannelset != null)
					InChannelType = parkChannelset.getInOut();// 入口类型
				parkChannelset = ParkMethod.getChannelSetByControlIndex(parkCar_out.getOutMachNo());
				// parkChannelset =
				// ParkMethod.getChannelSetByIp(outRealTimeBase.getRecord().getConIp());
				OutChannelType = parkChannelset.getInOut();// 出口类型
				if (InChannelType == 4 && OutChannelType == 3) {
					InModel.setSmall(0);
					parkCarInMapper.updateByPrimaryKeySelective(InModel);
				} else {
					parkCarInMapper.deleteByPrimaryKey(InModel.getId());
					BackUpParkCarIn backUpIn = new BackUpParkCarIn();
					BeanCopierUtil.copyProperties(InModel, backUpIn);
					backUpIn.setId(null);
					backUpParkCarInMapper.insert(backUpIn);
				}
			}
			// 半嵌套出口
			if (OutChannelType == 5) {
				parkCarInMapper.deleteByCondition(InModel);
				BackUpParkCarIn backUpIn = new BackUpParkCarIn();
				BeanUtils.copyProperties(InModel, backUpIn);
				backUpIn.setId(null);
				backUpParkCarInMapper.insert(backUpIn);
			}

			PaymentVo paymentVo = outRealTimeBase.getPayMentVo();
			if (paymentVo != null&&paymentVo.getPayType()==0) { //出口收费
				if (paymentVo.getPaidFee() == null) {
					parkCar_out.setAccountCharge((float) 0);
				} else {
					parkCar_out.setAccountCharge( paymentVo.getPaidFee() /(float) 100);
				}
				if (paymentVo.getPayCharge() == null) {
					parkCar_out.setPayCharge((float) 0);
				} else {
					parkCar_out.setPayCharge( paymentVo.getPayCharge() /(float) 100);
				}
				parkCar_out.setDiscountNo(outRealTimeBase.getInRecord().getDiscountNo());
				parkCar_out.setDiscountTime(outRealTimeBase.getInRecord().getDiscountTime());
				parkCar_out.setTypeId(outRealTimeBase.getInRecord().getTypeId());
			} else {
				parkCar_out.setAccountCharge((float) 0);
				parkCar_out.setPayCharge((float) 0);
				//超时收费
				if(paymentVo.getPayType()==2&& paymentVo.getPaidFee()>0 ){
					saveCenterRecord(outRealTimeBase);
				}
			}
			// 保存出场纪录成功后,判断如果是储值车，还要进行扣费处理
			if (outRealTimeBase.getCarBigType() == InOutRealTimeBase.STORED_CAR) {
				// 余额
				int balance = (int) (outRealTimeBase.getCardInfo().getBalanceMoney() * 100);
				// 应缴费用
				int money = outRealTimeBase.getPayMentVo().getPaidFee();
				// 剩余金额
				balance = balance - money;
				double fBalance = new BigDecimal(balance / (float) 100).setScale(2, BigDecimal.ROUND_HALF_UP)
						.floatValue();
				// 保存剩余金额
				YktCardIssue record = new YktCardIssue();
				record.setId(outRealTimeBase.getCardInfo().getId());
				record.setBalanceMoney(fBalance);
				yktCardIssueMapper.update(record);
				parkCar_out.setBalanceMoney((float)fBalance);
			}
			int k= parkCarOutMapper.insert(parkCar_out);
			saveDisInfo(outRealTimeBase);
			afterFamilyGroupOut(outRealTimeBase);
			logger.debug("saveParkOutRecord:"+outRealTimeBase.getCarNo()+"成功");
			pushCarOutInfo(parkCar_out);		//上传数据
			//pushCarOutImg(parkCar_out);			//上传图片
		} catch (Exception ex) {
			logger.error("保存出场数据:", ex);
		}
		return 0;

	}
	/** 出口有超时收费时保存记录*/
	/** wangchengxi
	 */
	private void saveCenterRecord(OutRealTimeBase outRealTimeBase){
		try{
			ParkCentralCharge centre =new ParkCentralCharge();
			centre.setInId(outRealTimeBase.getInRecord().getGuid());
			centre.setInMachNo(outRealTimeBase.getInRecord().getMachNo()==null ? 0 : outRealTimeBase.getInRecord().getMachNo());
			centre.setInPic(outRealTimeBase.getInRecord().getInPic());
			centre.setInTime(outRealTimeBase.getInRecord().getInTime());
			centre.setInUserName(outRealTimeBase.getInRecord().getInUserName());
			centre.setSmallPic(outRealTimeBase.getInRecord().getSmallPic());
			centre.setDiscountNo(outRealTimeBase.getInRecord().getDiscountNo());
			centre.setDiscountTime(outRealTimeBase.getInRecord().getDiscountTime());
			centre.setTypeId(outRealTimeBase.getInRecord().getTypeId());
			centre.setcFlag(outRealTimeBase.getInRecord().getcFlag());
			centre.setCarNo(outRealTimeBase.getInRecord().getCarNo());
			centre.setYktId(outRealTimeBase.getInRecord().getYktId());
			centre.setCardType(outRealTimeBase.getOut().getCardType());
			PaymentVo payModel = outRealTimeBase.getPayMentVo();
			if (payModel != null) {
				if (payModel.getPaidFee() == null) {
					centre.setAccountCharge((float) 0);
				} else {
					centre.setAccountCharge( payModel.getPaidFee() /(float) 100);
				}
				if (payModel.getPayCharge() == null) {
					centre.setPayCharge((float) 0);
				} else {
					centre.setPayCharge( payModel.getPayCharge() /(float) 100);
				}
				centre.setOverTime(payModel.getLastPayTime());
			} else {
				centre.setAccountCharge((float) 0);
				centre.setPayCharge((float) 0);
			}
			centre.setPayChargeTime(outRealTimeBase.getOut().getOutTime());
			centre.setPayUserName(outRealTimeBase.getOut().getOutUserName());
			centre.setPuid(ParkMethod.getUUID());
			centre.setBoxId(outRealTimeBase.getChannelSet().getBoxId());
			centre.setOrderNum(payModel.getOrderNum());
			parkCentralChargeMapper.insertSelective(centre);
			//刷新當班收費
		    centreStatisticsRefresh(centre);
		    ParkLocalSet local= ParkMethod.getLocalSetByBoxId(outRealTimeBase.getChannelSet().getBoxId());
		    centreSumUser(centre,local.getBoxIp());
		}catch (Exception ex) {
			logger.error("出场超时收费数据保存:", ex);
		}
	}
	/*
	 * 写打折信息
	 */
	private void saveDisInfo(OutRealTimeBase outRealTimeBase)
	{
		PaymentVo payModel = outRealTimeBase.getPayMentVo();
		if(payModel != null && payModel.getDiscountID() != null)
		{
			ParkDisDetail disModel = new ParkDisDetail();
			disModel.setOutType(payModel.getPayType().byteValue());
			disModel.setDiscountId(payModel.getDiscountID());
			disModel.setCardId(outRealTimeBase.getInRecord().getCardId());
			disModel.setOutTime(payModel.getOutTime());
			disModel.setDiscountTime(payModel.getOutTime());
			disModel.setDisAmount(payModel.getDisAmount().floatValue()/100);
			
			ParkDisInfo disInfo = parkDisInfoMapper.selectByDiscountId(payModel.getDiscountID());
			if(disInfo != null)
			{
				disModel.setDisType((byte)0);
			}
			else
			{
				disModel.setDisType((byte)1);
				disModel.setOnlineId(payModel.getDiscountID());
			}
			disModel.setPuid(ParkMethod.getUUID());
			disModel.setIsLoad(0);
			disModel.setDelFrag((byte)0);
			disModel.setCarNo(outRealTimeBase.getInRecord().getCarNo());
			disModel.setInTime(outRealTimeBase.getInRecord().getInTime());
			parkDisDetailMapper.insert(disModel);
		}
	}
	
	/**
	 * 推送出场记录
	 */
	public void sendParkCarOutRecordToBox(OutRealTimeBase outRealTimeBase)
	{
		try
		{
			ParkCarOut parkCar_out = outRealTimeBase.getOut();
			Head head = new Head();
			head.setMethod("parkCarOutRecord");
			ParkCarOutRecordBody body = new ParkCarOutRecordBody();
			body.setOutRecord(parkCar_out);
			ParkChannelSet channel = outRealTimeBase.getChannelSet();
			if(channel !=null)
			{
				parkCar_out.setOutMachNo(channel.getMachNo());
				ParkLocalSet local = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
				if(local != null&& local.getOnline() != null && local.getOnline()==1) {
					MainBoardMessage<Head,ParkCarOutRecordBody> returnInfo = new MainBoardMessage<>(head,body);
					String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
					String equipmentID = local.getEquipmentID();
					String replyTopic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
					MqttMessageVO reply = MqttServiceImpl.sendMessage(equipmentID,replyTopic, jsonBody, null, 0);
					logger.debug("推送出场记录:"+replyTopic+","+jsonBody);
				}
			}
		}catch(Exception ex)
		{
			logger.error("推送出场记录:", ex);
		}
	}
	
	/**
	 * 出场计费处理
	 */
	@Override
	public boolean isArrears(OutRealTimeBase out) {
		try {
			logger.debug("出场计费处理:"+out.getCarNo());
			Charge charge = SpringUtil.getBean(Charge.class);
			AbstractChargeStandard mode = SpringUtil.getBean(StandChargeRule.class);
			PaymentVo vo = charge.getFeeByCarNo(out.getPayMentVo(), mode, out.getCarNo()); // 查询是否缴费
			out.setPayMentVo(vo);
			// Integer fee = vo.getPaidFee();
		} catch (Exception ex) {
			logger.error("出场计费处理:", ex);
		}
		return true;
	}
	private void afterFamilyGroupOut(OutRealTimeBase out) {
		VwParkCarIsuse model = out.getCardInfo();
		if (model == null) {
			return;
		}
		Integer groupId = model.getPlaceId(); // 家庭组Id
		Integer cardType = out.getInRecord().getCardType(); // 入场时卡类型
		//如果不是家庭组，或者家庭组入场时是临时卡，则不处理
		if (groupId == null || groupId == 0 || cardType % 10 == InOutRealTimeBase.TEMP_CAR) {
			return;
		}
		// 查询出同一组中第一辆不是月卡类型的车辆(即家庭组满位后进来的第一辆组中的车)
		// 在入场表中查询此车位组其他车辆的数量
		List<String> carNos = vwParkCarIsuseMapper.selectCarsByGroupId(groupId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("carNos", carNos);
		map.put("cardType", InOutRealTimeBase.TEMP_CAR_A);
		ParkCarIn in = parkCarInMapper.selectParkInByGroup(map);
		if (in != null) {
			ParkFamilyGroupCharge condition = new ParkFamilyGroupCharge();
			condition.setCarNo(in.getCarNo());
			ParkFamilyGroupCharge parkFamilyGroupCharge = parkFamilyGroupChargeMapper.select(condition);
			
			boolean isInsert = false;
			if(parkFamilyGroupCharge == null) {
				parkFamilyGroupCharge = new ParkFamilyGroupCharge();
				isInsert = true;
			}
			parkFamilyGroupCharge.setInId(in.getId());
			parkFamilyGroupCharge.setInTime(in.getInTime());
			parkFamilyGroupCharge.setCarNo(in.getCarNo());
			parkFamilyGroupCharge.setEndTime(new Date());
			parkFamilyGroupCharge.setCardType(in.getCardType().intValue());
			if(isInsert) {
				parkFamilyGroupChargeMapper.insert(parkFamilyGroupCharge);
			}else {
				parkFamilyGroupChargeMapper.updateByPrimaryKeySelective(parkFamilyGroupCharge);
			}
			in.setCardType(model.getCardType());
			parkCarInMapper.updateByPrimaryKeySelective(in);			
		}
	}

	@Override
	public boolean parkErrorMessage(OutRealTimeBase out)
	{
		Head replyHead = new Head();		
		replyHead.setMethod("parkErrorMessage");
		
		ParkErrorMessageBody body = new ParkErrorMessageBody();
        Object isSuccess =out.getStatusMap().get("isSuccess");
        if (isSuccess!=null&&isSuccess.equals(1)) {
			body.setInOut("1");			
			String msg = out.getStatusMap().get("Msg").toString();
			body.setDisMessage(msg);
			MainBoardMessage<Head,ParkErrorMessageBody> returnInfo = new MainBoardMessage<>(replyHead,body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			String equipmentID;
			ParkChannelSet channel = out.getChannelSet();
			if(channel !=null)
			{
				ParkLocalSet local = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
				if(local != null)
				{
					equipmentID = local.getEquipmentID();
					String replyTopic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
					MqttMessageVO reply = MqttServiceImpl.sendMessage(equipmentID,replyTopic, jsonBody, null, 3);
					logger.debug("出场错误 信息推送:"+replyTopic+","+jsonBody);
				}
			}					 			
		}				 				 		
		return true;
	}
	

	/**
	 * 发送开闸确认到岗亭
	 */
	@Override
	public void boxOutIsOpen(OutRealTimeBase out)
	{
		try {
			BoxOutIsOpenBody body = new BoxOutIsOpenBody();
			ParkChannelSet channel = out.getChannelSet();
			if (channel != null && channel.getBoxId() != null) {
				if (GlobalPark.outChannelEvent.containsKey(channel.getChannelIp())) {
					GlobalPark.outChannelEvent.remove(channel.getChannelIp());
				}
				out.setFrmType(6);
				GlobalPark.outChannelEvent.put(channel.getChannelIp(), out);
				ParkLocalSet local = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
				if (local.getOnline() != null && local.getOnline() == 1) {
					Head head = new Head();
					head.setMethod("parkOutIsOpen");
					body.setControlIP(channel.getChannelIp());
					body.setOutRecord(out);

					String equipmentID = local.getEquipmentID();
					body.setEquipmentID(equipmentID);
					String topic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
					MainBoardMessage<Head, BoxOutIsOpenBody> returnInfo = new MainBoardMessage<>(head, body);
					String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
					MqttMessageVO retVo = MqttServiceImpl.sendMessage(equipmentID, topic, jsonBody, null, 1);
					logger.debug("发送开闸确认到岗亭:"+topic+","+jsonBody);
				}
			}
		} catch (Exception ex) {
			logger.error("发送开闸确认到岗亭:", ex);
		}
	}
	
	/**
	 * 发送相似车牌到岗亭(出场车牌校正)
	 */
	@Override
	public void sendSimilarCar(OutRealTimeBase out)
	{	
		try {
			ParkChannelSet channel = out.getChannelSet();
			if (channel != null && channel.getBoxId() != null) {
				ParkLocalSet local = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
				if (local != null && local.getOnline() != null && local.getOnline() == 1) {
					Head head = new Head();
					head.setMethod("parkOutChanelCorrecting");
					ParkOutChanelCorrectingBody body = new ParkOutChanelCorrectingBody();
					body.setType("0");
					body.setControlIP(channel.getChannelIp());
					String equipmentID = local.getEquipmentID();
					body.setEquipmentID(equipmentID);
					body.setOutRecord(out);
					String topic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
					MainBoardMessage<Head, ParkOutChanelCorrectingBody> returnInfo = new MainBoardMessage<>(head, body);
					String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
					MqttMessageVO retVo = MqttServiceImpl.sendMessage(equipmentID, topic, jsonBody, null, 1);
					logger.debug("出场车牌校正:"+topic+","+jsonBody);
				}
			}
		} catch (Exception ex) {
			logger.error("发送相似车牌到岗亭(出场车牌校正):", ex);
		}
	}
	/**
	 * 
	 * 出场时如果入场的时候车辆类型为月卡，那么出场时也做月卡处理 <br>
	 *
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	@Override
	public boolean getTypeByIn(OutRealTimeBase out) {
		try {
			logger.debug("getTypeByIn:"+out.getCarNo());
			String inCarNo = out.getInRecord().getCarNo();// 入场车牌
			String outCarNo = out.getCarNo();// 出场车牌
			if (outCarNo.equals(inCarNo)) { // 入场与出场车牌相同
//				out.setCarRealType(out.getInRecord().getCardType());
//				out.getOut().setCardType( out.getInRecord().getCardType());
//				out.getPayMentVo().setCarRealType(out.getInRecord().getCardType());
				return false;
			} else { // 不相同
				VwParkCarIsuse model = getVwParkCarIsuse(inCarNo);
				if (model != null) {
					out.setCardInfo(model); // 授权数据
					int cardType = model.getCardType();
					out.setCarRealType(cardType);
					out.getOut().setYktId(model.getYktId());
					out.getOut().setCardType( cardType);
					out.setCarBigType(cardType / 10);
					out.getPayMentVo().setCarRealType(cardType);
					return true;
				} else {
					out.setCarRealType(out.getInRecord().getCardType());
					out.getPayMentVo().setCarRealType(out.getInRecord().getCardType());
					out.getOut().setCardType( out.getInRecord().getCardType());
					return false;
				}
			}
		} catch (Exception ex) {
			logger.error("出场时判断是临时卡还是月租卡:", ex);
			return false;
		}
	}
	/** 获取固定车的类型 (月卡 or 储值卡 or 免费卡) 
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 * */
	@Override
	public int getRegularCarType(int cardType) {
		logger.debug("getRegularCarType:"+cardType);
		try {
			switch (cardType/10) {
			case 1:  //月租车
				return 1;
			case 2: //月临车
				return 2;
			case 3: //临时车
				return 3;
			case 4:   //免费车
				return 4;
			case 5: //储值车
				return 5;
			case 6: //储临车
				return 6;
			default://
				return 1;
			}
        } catch (Exception ex) {
        	logger.error("获取固定车的类型:", ex);
			return 1;
		}
	
	}
	
	
	/**
	 *  储值卡扣费处理, 如果费用够 或者根本不是储值卡,返回true(转到开闸是否要确认出理)<br>
	 *  如果不够用 ，将卡类型转成储临卡,交由计费出理，再算一遍费用, 返回 false
	 *  
	 */
	@Override
	public boolean storecarCharge(OutRealTimeBase out) {
		try {
			logger.debug("storecarCharge:"+out.getCarNo());
			if (out.getCarBigType()!= InOutRealTimeBase.STORED_CAR) { //如果非储值车不需要判断
				return true;
			} else {
				// 余额
				int balance = (int) (out.getCardInfo().getBalanceMoney() * 100);
				// 应缴金额
				int paid = out.getPayMentVo().getPaidFee();

				if (balance < paid) {
					int carType = out.getCarRealType();
					out.setCarRealType(carType + 10);
					out.getPayMentVo().setCarRealType(carType - 20);
					out.getPayMentVo().setRemark("储值车不能够扣费，按储临车计费");
					out.getOut().setCardType(carType + 10);
					out.setCarBigType(InOutRealTimeBase.STORED_TEMP_CAR);
					return false;
				}
			}
		} catch (Exception ex) {
			logger.error("判断储值车是否够扣费:", ex);
			return false;
		}
		return true;
	}

	/**
	 * 将入场记录的值复制到出场保存实体中
	 * @author chenlong
	 * 2018年9月3日
	 */
	private void inRecodeToParkOut(ParkCarIn in , ParkCarOut out) {
		out.setInCarNo(in.getCarNo());
		out.setInId(in.getGuid());
		out.setInMachNo(in.getMachNo()==null ? 0 : in.getMachNo());
		out.setInPic(in.getInPic());
		out.setInTime(in.getInTime());
		out.setInUserName(in.getInUserName());
		out.setSmallInPic(in.getSmallPic());
		out.setDiscountNo(in.getDiscountNo());
		out.setDiscountTime(in.getDiscountTime());
		out.setTypeId(in.getTypeId());
	}
	
	private void initPayMentVo(OutRealTimeBase out) {
		out.getPayMentVo().setPayInTime(out.getInRecord().getInTime());
		out.getPayMentVo().setPayOutTime(out.getNowDate());
		out.getPayMentVo().setInTime(out.getInRecord().getInTime());
		out.getPayMentVo().setOutTime(out.getNowDate());
		out.getPayMentVo().setDiscountID(out.getInRecord().getDiscountNo());
		out.getPayMentVo().setDiscountTime(out.getInRecord().getDiscountTime());
		out.getPayMentVo().setTypeId(out.getInRecord().getTypeId());
		out.getPayMentVo().setInId(out.getInRecord().getGuid());
		out.getPayMentVo().setPayType(0); //支付方式
	}
	
	/** 卡类是否过期 */
	/** wangchengxi
	 * EXPIRE 固定车过期 
	 * UNDUE 未到期 
	 * TIMENOTALLOW 时间段禁止进入 
	 * EFFECTIVE 有效期内
	 */
	@Override
	public Step isCardTypeExpireOut(OutRealTimeBase fact) {
		try {
			logger.debug("isCardTypeExpireOut:"+fact.getCarNo());
			Date inTime = fact.getInRecord().getInTime();// 入场时间
			Date outTime = fact.getNowDate(); //出场时间
			Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
			Date sTime = fact.getCardInfo().getStartDate(); // 开始有效期
			if(!inTime.before(sTime) && !outTime.after(eTime)) //在有效期内
			{
				return getInEffective(fact);
			}else { //不在有效期
				if(!outTime.after(sTime)) { //在有效期之前 所有固定车按临时车处理
					return getBeforeStartDate (fact);
				}
				//入场时间在有效期开始之前，出场时间在有效期之内  按临时车处理
				if(!inTime.after(sTime) && outTime.after(sTime) && outTime.before(eTime)) {
					return getInBeforeStartDate(fact);
				}
				//入场时间在有效期开始之前，出场时间有效期结束之后
				if(inTime.before(sTime) && outTime.after(eTime) ) { 
					return getUnEffective(fact);
				}
				//入场时间在有效期结束之后
				if(inTime.after(eTime)) {
					return getInAfterEndDate(fact);
				}
				//入场时间在有效期内，出场时间在有效期结束之后
				if(inTime.after(sTime) && !inTime.after(eTime) && outTime.after(eTime)) { 
					return getOutAfterEndDate(fact);
				}
			}
			return Step.EFFECTIVE;
		} catch (Exception ex) {
			logger.error("出场判断有效期:", ex);
			return Step.EFFECTIVE;
		}
	}
	
	/** 固定车入场时间在有效期内，出场时间在有效期结束之后 */
	/** wangchengxi
	 */
	private Step getOutAfterEndDate(OutRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
		Date sTime = fact.getCardInfo().getStartDate(); // 开始有效期
		int overDays = Integer.parseInt(GlobalPark.properties.getProperty("FIXED_CAR_EXPIR", "0"));//固定车过期天数
		int monthTemp = Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE", "0"));//启用月临卡功能 	
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(eTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		fact.getPayMentVo().setPayOutTime(outTime); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
			if(monthTemp==0) { //未启用月临卡功能
				if(ParkMethod.GetDate(eTime,overDays).before(fact.getNowDate())) {
					return Step.EXPIRE; //过期
				}else {
					return Step.EFFECTIVE; //按月租车处理
				}
			}else { //启用月临卡
				int carType=setMonthCardType(fact.getCarRealType(),monthTemp);
				fact.setCarRealType(carType);
				fact.getPayMentVo().setCarRealType(carType);
				fact.getPayMentVo().setRemark("月租车出场过期按月临车计费，计费开始时间:"
				+timeFormat.format(eTime)+",计费结束时间:"+timeFormat.format(outTime));
				fact.getOut().setCardType(carType-10);
				fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
				return Step.UNEFFECTIVE; //不在有效期内
			}
		case InOutRealTimeBase.FREE_CAR: //免费车
			if(ParkMethod.GetDate(eTime,overDays).before(fact.getNowDate())) {
				return Step.EXPIRE; //过期
			}else {
				return Step.EFFECTIVE;
			}
		case InOutRealTimeBase.STORED_CAR : //储值车	
			int carType=fact.getCarRealType();
			fact.setCarRealType(carType-20);
			fact.getPayMentVo().setCarRealType(carType-20);
			fact.getPayMentVo().setRemark("储值车出场过期按储临车计费，计费开始时间:"
			+timeFormat.format(eTime)+",计费结束时间:"+timeFormat.format(outTime));
			fact.getOut().setCardType(carType+10);
			fact.setCarBigType(InOutRealTimeBase.STORED_TEMP_CAR);
			return Step.UNEFFECTIVE; //不在有效期内
	    default:
	    	return Step.EXPIRE; //过期
		}
	}
	
	/** 固定车入场时间在有效期结束之后 */
	/** wangchengxi
	 */
	private Step getInAfterEndDate(OutRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
		int overDays = Integer.parseInt(GlobalPark.properties.getProperty("FIXED_CAR_EXPIR", "0"));//固定车过期天数
		int monthTemp = Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE", "0"));//启用月临卡功能 	
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		fact.getPayMentVo().setPayOutTime(outTime); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
			if(monthTemp==0) { //未启用月临卡功能
				if(ParkMethod.GetDate(eTime,overDays).before(fact.getNowDate())) {
					return Step.EXPIRE; //过期
				}else {
					return Step.EFFECTIVE; //按月租车处理
				}
			}else { //启用月临卡
				int carType=setMonthCardType(fact.getCarRealType(),monthTemp);
				fact.setCarRealType(carType);
				fact.getPayMentVo().setCarRealType(carType);
				fact.getPayMentVo().setRemark("月租车过期按月临车计费，计费开始时间:"
				+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(outTime));
				fact.getOut().setCardType(carType-10);
				fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
				return Step.UNEFFECTIVE; //不在有效期内
			}
		case InOutRealTimeBase.FREE_CAR: //免费车
			if(ParkMethod.GetDate(eTime,overDays).before(fact.getNowDate())) {
				return Step.EXPIRE; //过期
			}else {
				return Step.EFFECTIVE;
			}
		case InOutRealTimeBase.STORED_CAR : //储值车	
			int carType=fact.getCarRealType();
			fact.getPayMentVo().setCarRealType(carType-20);
			fact.getPayMentVo().setRemark("储值车过期按储临车计费，计费开始时间:"
			+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(outTime));
			fact.setCarRealType(carType-20);
			fact.getOut().setCardType(carType+10);
			fact.setCarBigType(InOutRealTimeBase.STORED_TEMP_CAR);
			return Step.UNEFFECTIVE; //不在有效期内
		default:
			return Step.EXPIRE; //过期
		}
	}
	
	/** 固定车入场时间在有效期开始之前，出场时间有效期结束之后 */
	/** wangchengxi
	 */
	private Step getUnEffective(OutRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
		Date sTime = fact.getCardInfo().getStartDate(); // 开始有效期
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		int minute=DateTimeUtils.getPeriodMinute(sTime,eTime);
		fact.getPayMentVo().setPayOutTime(DateTimeUtils.dateAddMinute(inTime,minute)); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
		case InOutRealTimeBase.FREE_CAR: //免费车
		case InOutRealTimeBase.STORED_CAR : //储值车
			fact.setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setRemark("过期按临时车计费，计费开始时间:"
			+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(fact.getPayMentVo().getPayOutTime()));
			fact.getOut().setCardType(InOutRealTimeBase.TEMP_CAR_A);
			fact.setCarBigType(InOutRealTimeBase.TEMP_CAR);
		}
		return Step.UNDUE;
	}
	
	/** 固定车入场时间在有效期开始之前，出场时间在有效期之内 */
	/** wangchengxi
	 */
	private Step getInBeforeStartDate(OutRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		Date sTime = fact.getCardInfo().getStartDate(); // 开始有效期
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		fact.getPayMentVo().setPayOutTime(sTime); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
		case InOutRealTimeBase.FREE_CAR: //免费车
		case InOutRealTimeBase.STORED_CAR : //储值车
			fact.setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setRemark("过期按临时车计费，计费开始时间:"
			+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(fact.getPayMentVo().getPayOutTime()));
			fact.getOut().setCardType(InOutRealTimeBase.TEMP_CAR_A);
			fact.setCarBigType(InOutRealTimeBase.TEMP_CAR);
		}
		return Step.UNDUE; 
	}
	/** 固定车入,出场在有效期之前 */
	/** wangchengxi
	 */
	private Step getBeforeStartDate(OutRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		fact.getPayMentVo().setInTime(inTime); //实际入场时间
		fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
		fact.getPayMentVo().setOutTime(outTime); //实际出场时间
		fact.getPayMentVo().setPayOutTime(outTime); //计费线束时间
		switch(fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: //月车
		case InOutRealTimeBase.FREE_CAR: //免费车
		case InOutRealTimeBase.STORED_CAR : //储值车
			fact.setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
			fact.getPayMentVo().setRemark("未到有效期按临时车计费，计费开始时间:"
			+timeFormat.format(inTime)+",计费结束时间:"+timeFormat.format(fact.getPayMentVo().getPayOutTime()));
			fact.getOut().setCardType(InOutRealTimeBase.TEMP_CAR_A);
			fact.setCarBigType(InOutRealTimeBase.TEMP_CAR);
		}
		return Step.UNDUE; 
	}
	
	/** 固定车在有效期内 */
	/** wangchengxi
	 */
	private Step getInEffective(OutRealTimeBase fact) {
		Date inTime = fact.getInRecord().getInTime();// 入场时间
		Date outTime = fact.getNowDate(); //出场时间
		ParkEffetTimes parkEffetTimes = parkEffetTimesMapper.selectByCardType(fact.getCarRealType());
		if(fact.getCarBigType()==1) { //月租车
			if(parkEffetTimes==null) { //不存在时段禁止
				return Step.EFFECTIVE;
			}//&&
			Date start = parkEffetTimes.getbTime(); //开始时间
			Date end = parkEffetTimes.geteTime(); //时段结束时间
			if(parkEffetTimes.getsType()==1) //时间段禁止进入
			{	
				if(DateTimeUtils.isEffectiveDate(fact.getNowDate(), start, end))
					return Step.TIMENOTALLOW; // 时段内禁止入场 ，入场还是月卡处理
			    else
			    	return Step.EFFECTIVE; //按有正常车出入场
			
			}else { //按对应的卡类收费
				int effetTime=DateTimeUtils.countEffectMinute(inTime,outTime,start,end);
				if(effetTime==0) {
					return Step.EFFECTIVE; //按有正常车出入场
				} else {
					fact.getPayMentVo().setInTime(inTime); //实际入场时间
					fact.getPayMentVo().setPayInTime(inTime); //计费开始时间
					fact.getPayMentVo().setOutTime(outTime); //实际出场时间
					fact.getPayMentVo().setPayOutTime(DateTimeUtils.dateAddMinute(inTime, effetTime)); // 计费线束时间
					fact.getPayMentVo().setRemark(
							"月租车受限按临时车计费，计费开始时间:" + timeFormat.format(inTime) 
							+ ",计费结束时间:" +timeFormat.format(fact.getPayMentVo().getPayOutTime()));
					int carType = setEffeciTiveCardType(fact.getCarRealType(), parkEffetTimes.getsType());
					fact.setCarRealType(carType);
					fact.getPayMentVo().setCarRealType(carType);
					fact.getOut().setCardType(carType - 10);
					fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
					return Step.UNEFFECTIVE; // 不在有效期内
				}
			}
		}else {
			return Step.EFFECTIVE;
		}
	}
	
	/** 固定车多出判断 */
	/** wangchengxi
	 */
	public boolean isMonthMoreOut(OutRealTimeBase out) {
		try {
			logger.debug("isMonthMoreOut:"+out.getCarNo());
			//多进多出
			int moreOut = Integer.parseInt(GlobalPark.properties.getProperty("MORE_INT_MORE_OUT", "0"));
			if (moreOut == 1) { // 需要多进多出
				int carTye = out.getCarRealType() / 10;
				if (carTye == 1 || carTye == 4) { // 月卡或者免费车
					// 在有效期内才能多进多出
					if (!out.getNowDate().before(out.getCardInfo().getStartDate())
							&& !out.getNowDate().after(out.getCardInfo().getEndDate())) { 
						ParkCarIn in = new ParkCarIn();
						in.setCarNo(out.getCarNo());
						in.setMachNo(out.getOut().getOutMachNo().intValue());
						in.setYktId(out.getCardInfo().getYktId());
						in.setEmpName(out.getCardInfo().getPerName());
						in.setInTime(out.getNowDate());
						in.setCardType(out.getCardInfo().getCardType());
						in.setInUserName(ParkMethod.getLoginName(out.getChannelSet().getBoxId()));
						out.setInRecord(in);
						inRecodeToParkOut(in, out.getOut());
						return true;
					} else {
						return false;
					}
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			logger.error("固定车多进多出:", ex);
			return false;
		}
	}

	
	/**
	 * 判断是家庭组月卡还是临时卡 <br>
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	public boolean isFamilyTemp(OutRealTimeBase out) {
		try {
			logger.debug("isFamilyTemp:"+out.getCarNo());
			int monthTemp = Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE", "0"));//启用月临卡功能 	
			Date inTime = out.getInRecord().getInTime();// 入场时间
			Date outTime = out.getNowDate(); //出场时间
			int inCardType=out.getInRecord().getCardType();//
			int OutCardType=out.getCarRealType(); //出场卡类型
			if(inCardType/10==3 && OutCardType/10==1) { //入场临时车，出场月租车
				out.getPayMentVo().setInTime(inTime); //实际入场时间
				out.getPayMentVo().setPayInTime(inTime); //计费开始时间
				out.getPayMentVo().setOutTime(outTime); //实际出场时间
				out.getPayMentVo().setPayOutTime(outTime); //计费线束时间
				out.getPayMentVo().setRemark("月租车家庭组按临时车计费，计费开始时间:"
				+timeFormat.format(out.getPayMentVo().getPayInTime())+",计费结束时间:"
						+timeFormat.format(out.getPayMentVo().getPayOutTime()));
				int carType=setMonthCardType(out.getCarRealType(),monthTemp);
				out.setCarRealType(carType);
				out.getPayMentVo().setCarRealType(carType);
				out.getOut().setCardType(carType-10);
				out.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
				return true;
			}
			else {
				return false;
			}
		}catch (Exception ex) {
			logger.error("判断是家庭组月卡还是临时卡:", ex);
			return false;
		}
	}
	/**
	 * 判断是家庭组月卡是否存在临时车计费 <br>
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
    public boolean isFamilyMonth(OutRealTimeBase out) {
    	try {
    		logger.debug("isFamilyMonth:"+out.getCarNo());
    		ParkFamilyGroupCharge payModel=new ParkFamilyGroupCharge();
    		payModel.setCarNo(out.getCarNo());
    		payModel.setInTime(out.getInRecord().getInTime());
    		ParkFamilyGroupCharge model=parkFamilyGroupChargeMapper.select(payModel);
    		if(model!=null) { //
    			int monthTemp = Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE", "0"));//启用月临卡功能 	
    			Date inTime = out.getInRecord().getInTime();// 入场时间
    			Date outTime = out.getNowDate(); //出场时间
    			out.getPayMentVo().setInTime(inTime); //实际入场时间
				out.getPayMentVo().setPayInTime(model.getInTime()); //计费开始时间
				out.getPayMentVo().setOutTime(outTime); //实际出场时间
				out.getPayMentVo().setPayOutTime(model.getEndTime()); //计费线束时间
				out.getPayMentVo().setRemark("家庭组以临时车入场，计费开始时间:"
						+timeFormat.format(model.getInTime())+",计费结束时间:"
								+timeFormat.format(model.getEndTime()));
				int carType=setMonthCardType(out.getCarRealType(),monthTemp);
				out.setCarRealType(carType);
				out.getPayMentVo().setCarRealType(carType);
				out.getOut().setCardType(carType-10);
				out.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
				return true;
    		}else {
    			return false;
    		}
    	}catch (Exception ex) {
    		logger.error("判断是家庭组月卡是否存在临时车计费:", ex);
			return false;
		}
    }

    /**
     * 实时推送出场数据
     * @param parkCarOut
     */
	@Transactional(propagation = Propagation.SUPPORTS)
    public void pushCarOutInfo(ParkCarOut parkCarOut){
    	try {
			List<ParkCarOutVO> sendList = new ArrayList<ParkCarOutVO>();
			ParkCarOutVO info = conversionParameterClass.getParkCarOutModel(parkCarOut);
			if (info != null && info.getObjectId() != null) {
				sendList.add(info);
				onlineDSScanSever.sendInfo(sendList, "park/parkcarout/v1", "parkcarout");
				logger.debug("pushCarOutInfo:"+parkCarOut.getOutCarNo()+"推送出场数据到云端成功");
			}
		}catch (Exception e){
    		logger.error("实时推送出场数据异常:",e);
		}
    }

	/**
	 * 实时推送入场图片
	 * @param parkCarOut
	 */
	@Transactional(propagation = Propagation.SUPPORTS)
	public void pushCarOutImg(ParkCarOut parkCarOut){
		try {
			if("1".equals(GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD"))&&"1".equals(GlobalPark.properties.getProperty("CLOUD_STATUS"))) {
				String result = UploadUtil.syFtpImg(parkCarOut.getGuid(), parkCarOut.getInPic());          //上传入场图片
				if (result != null) {
					parkCarOut.setIsImgUpload(1);
					parkCarOutMapper.updateByPrimaryKey(parkCarOut);          //更新数据状态
					logger.debug("pushCarOutImg:" + parkCarOut.getOutCarNo() + "推送出场图片到云端成功");
				}
			}
		}catch (Exception e){
			logger.error("实时推送出场图片异常：",e);
		}
	}

}

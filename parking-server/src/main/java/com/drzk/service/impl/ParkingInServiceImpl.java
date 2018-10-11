
package com.drzk.service.impl;

import com.drzk.bean.MqttMessageVO;
import com.drzk.common.InOutRealTimeBase;
import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.fact.InRealTimeBase;
import com.drzk.mapper.BackUpParkCarInMapper;
import com.drzk.mapper.ParkCarInMapper;
import com.drzk.mapper.VwParkCarIsuseMapper;
import com.drzk.online.impl.ConversionParameterClass;
import com.drzk.online.service.OfMqttService;
import com.drzk.online.service.OnlineDSScanSever;
import com.drzk.online.utils.UploadUtil;
import com.drzk.online.vo.ParkCarInVO;
import com.drzk.service.entity.*;
import com.drzk.utils.BeanCopierUtil;
import com.drzk.utils.DateTimeUtils;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.vo.*;
import org.apache.log4j.Logger;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * ClassName:ParkingInServiceImpl <br>
 * Date: 2018年5月17日 下午2:28:11 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
@Service("parkingInService")
public class ParkingInServiceImpl extends AbstractParkingService {

	@KSession("ksession_parkin")
	private KieSession kSession;

	@Autowired
	private ParkCarInMapper parkCarInMapper;

	@Autowired
	private VwParkCarIsuseMapper vwParkCarIsuseMapper;
//	@Autowired
//	ParkCarInDSMapper parkCarInDSMapper;

	private static Logger logger = Logger.getLogger("userLog");
	@Autowired
	private BackUpParkCarInMapper backUpParkCarInMapper;
	@Autowired
	private ConversionParameterClass conversionParameterClass;
	@Autowired
	private OfMqttService ofMqttService;
	@Autowired
	private OnlineDSScanSever onlineDSScanSever;

	/**
	 * 满位是否还让进<br>
	 * 
	 * @author wangchengxi
	 * @return 过期返回true
	 * @since JDK 1.8
	 */
	@Override
	public boolean isParkFullPass(int cardType) {
		boolean ret = false;
		try {
			logger.debug("isParkFullPass:"+cardType);
			int carType = Integer.parseInt(GlobalPark.properties.getProperty("CAR_TYPE", "1"));
			if (GlobalPark.properties.getProperty("ISALLOW","0").toLowerCase().equals("0")) {
				// int cardType = getCardType(carNo);
				int type = cardType / 10;
				switch (carType) {
				case 1: // 混合车位
					if(type==3||type==2||type==6) { //临时车
						if(GlobalPark.p_TempFullNum) 
							ret=false;
						else
							ret=true;
					}else {
						if(GlobalPark.p_monthFullNum)
							ret=false;
						else
							ret=true;
					}
					break;
				case 2: // 临时车位
					if (type == 1 || type == 4 || type == 5)
						ret = true;
					if(type==3||type==2||type==6) {
						if(GlobalPark.p_TempFullNum) 
							ret=false;
						else
							ret=true;
					}	
					break;
				case 3: // 固定车位
					if (type == 4)
						ret = true;
					break;
				}
			} else
				ret = true;
		} catch (Exception e) {
			logger.error("满位是否允许入场:", e);
		}
		return ret;
	}

	/**
	 * 检测1秒内是否有入场记录
	 */
	@Override
	public boolean isRecordDuplicate(String carNo, int controlIndex, Date inTime, int flag) {
		try {
			ParkCarIn model = new ParkCarIn();
			if (flag == 1) {
				model.setInTime(inTime);
				model.setMachNo(controlIndex);
				//model = parkCarInMapper.selectTop(model);
				if (model != null) {
					long timeSpan = inTime.getTime() - model.getInTime().getTime();
					return timeSpan <= 1000;
				} else
					return false;
			} else {
				String rCarNo = carNo.trim().substring(1);
				model.setCarNo(rCarNo);
				//model = parkCarInMapper.selectTop(model);
				return model != null;
			}

		} catch (Exception e) {
			logger.error("检测1秒内是否有入场记录:", e);
			return false;
		}
	}
	
	//获取新的无牌车号码
	@Override
	public String getNewNOPNo() {
		try {
			String carNO;
            int i;
            int k;
			List<ParkCarIn> modellist = parkCarInMapper.getNewNopNo();
			if (modellist.size() > 0) {
				k = modellist.size();
                for (i = 0; i < k; i++)
                {
                    if (Integer.parseInt(modellist.get(i).getCarNo().substring(4)) != i + 1)
                    {
                        carNO = "NOP" + String.format("%04d", i + 1);
                        break;
                    }
                }
                if (i == k + 1)
                {
                    carNO = "NOP" + String.format("%04d", i);
                }
                else
                {
                    carNO = "NOP" + String.format("%04d", i + 1);
                }
                return carNO;
			} else {
				return "NOP0001";
			}
		} catch (Exception e) {
			logger.error("获取NOP车牌号码:", e);
			return "NOP0001";
		}
	}

	/**
	 * 保存入场记录
	 */
	@Override
	public boolean saveParkInRecord(InRealTimeBase in) {
		try {
			logger.debug("saveParkInRecord:"+in.getCarNo());
			ParkCarIn model = in.getIn();
			ParkCarIn condition = new ParkCarIn();
			String rCarNo = in.getCarNo().trim();
			condition.setCarNo(rCarNo);
			condition.setSmall(in.isSmall() ?  1 : 0);
			List<ParkCarIn> modelGet = parkCarInMapper.selectByCondition(condition);
			if (modelGet != null && modelGet.size() > 0) {
				parkCarInMapper.deleteByPrimaryKey(modelGet.get(0).getId());
				BackUpParkCarIn backUpIn = new BackUpParkCarIn();
				BeanCopierUtil.copyProperties(modelGet.get(0), backUpIn);
				//backUpIn.setId(null);
				backUpParkCarInMapper.insertSelective(backUpIn);
			}
			int row = parkCarInMapper.insert(model);
			if (row > 0) {
				if(GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD").equals("1")) {
					pushCarInfo(model);        //推送数据
					//pushCarInImg(model);
				}
                return true;
            }
		} catch (Exception e) {
			logger.error("保存入场记录:", e);
		}

		return false;
	}

	/**
	 * 判断是否为家庭组
	 */
	@Override
	public boolean getFamilyCarType(InRealTimeBase in) {
		boolean isFamilyTemp = false;
		try {
			logger.debug("getFamilyCarType:"+in.getCarNo());
			VwParkCarIsuse model = in.getCardInfo();
			int groupId = model.getPlaceId();
			int placeNum = model.getpNum();
			List<String> carNos = vwParkCarIsuseMapper.selectCarsByGroupId(groupId);

			// 在入场表中查询此车位组其他车辆的数量
			List<ParkCarIn> parkIns = parkCarInMapper.selectByCarNos(carNos);

			// 在大小车场的情况下，一辆车可能2条记录，以下操作为去重
			HashSet<String> carNoSet = new HashSet<>();
			for (ParkCarIn parkCarIn : parkIns) {
				String carNo = parkCarIn.getCarNo();
				if (!carNo.equals(in.getCarNo())) {
					carNoSet.add(carNo);
				}
			}
			int cardType = 0;
			// 如果家庭组车位没满位，入场时按月卡类型计算
			if (carNoSet.size() < placeNum) { // 没满位
				isFamilyTemp = false;
			} else {// 如果入场时，家庭组已满位，入场时按临时卡类型计费
				// 临时卡
				cardType = InOutRealTimeBase.TEMP_CAR_A;
				in.setCarRealType(cardType);
				in.setCarBigType(InOutRealTimeBase.TEMP_CAR);
				((InRealTimeBase) in).getIn().setCardType( InOutRealTimeBase.TEMP_CAR_A);
				isFamilyTemp = true;
			}
		} catch (Exception e) {
			logger.error("判断是否为家庭组:", e);
			isFamilyTemp = false;
		}
		return isFamilyTemp;
	}

	/**
	 * 家庭组是否允许入场
	 */
	@Override
	public boolean isFamilyAllowIn(InRealTimeBase in) {
		try {
			logger.debug("isFamilyAllowIn:"+in.getCarNo());
			VwParkCarIsuse model = in.getCardInfo();
			Byte pType = model.getpType();
			return pType != null && pType == 0;
		} catch (Exception e) {
			logger.error("家庭组是否允许入场:", e);
			return false;
		}
	}

	/**
	 * 推送异常信息到岗亭
	 */
	@Override
	public boolean parkErrorMessage(InRealTimeBase in) {
		try {
			Head replyHead = new Head();
			replyHead.setMethod("parkErrorMessage");
			ParkErrorMessageBody body = new ParkErrorMessageBody();
			Object isSuccess =in.getStatusMap().get("isSuccess");
			if (isSuccess!=null&&isSuccess.equals(1)) {
				body.setInOut("0");
				String msg = in.getStatusMap().get("Msg").toString();
				body.setDisMessage(msg);
				MainBoardMessage<Head, ParkErrorMessageBody> returnInfo = new MainBoardMessage<>(replyHead, body);
				String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
				String equipmentID;
				ParkChannelSet channel = in.getChannelSet();
				if (channel != null) {
					ParkLocalSet local = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
					if (local != null && local.getOnline() != null && local.getOnline() == 1) // 判断岗亭是否在线
					{
						equipmentID = local.getEquipmentID();
						String topic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
						MqttMessageVO reply = MqttServiceImpl.sendMessage(equipmentID,topic, jsonBody, null, 3);
						logger.debug("parkErrorMessage:"+jsonBody);
					}
				}
			}
			return true;
		} catch (Exception ex) {
			logger.error("推送异常信息到岗亭:", ex);
			return false;
		}
	}

	/**
	 * 推送入场记录
	 */
	@Override
	public boolean sendParkCarInRecordToBox(InRealTimeBase in) {
		try {
			Head head = new Head();
			head.setMethod("parkCarInRecord");
			ParkCarInRecordBody body = new ParkCarInRecordBody();
			body.setInRecord(in.getIn());
			MainBoardMessage<Head, ParkCarInRecordBody> returnInfo = new MainBoardMessage<>(head, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			ParkChannelSet channel = in.getChannelSet();
			for (ParkLocalSet local : GlobalPark.parkLocalSet) {
				if (local.getBoxId() == channel.getBoxId()) {
					if (local.getOnline() == 1 && local.getEquipmentID() != null) {
						String topic = String.format(TopicsDefine.BOX_ERROR, local.getEquipmentID());
						MqttMessageVO retVo = MqttServiceImpl.sendMessage(local.getEquipmentID(),topic, jsonBody, null, 3);
						logger.debug("推送入场记录:"+topic+","+jsonBody);
						break;
					}
				}
			}
		} catch (Exception ex) {
			logger.error("推送入场记录:", ex);
		}

		return true;
	}

	/**
	 * 发送开闸确认到岗亭 入场校正推送
	 */
	@Override
	public void boxInIsOpen(InRealTimeBase fact) {
		try {
			BoxInIsOpenBody body = new BoxInIsOpenBody();
			ParkChannelSet channel = fact.getChannelSet();
			fact.setFrmType(1);
			if (channel != null && channel.getBoxId() != null) {
				if (GlobalPark.inChannelEvent.containsKey(channel.getChannelIp())) {
					GlobalPark.inChannelEvent.remove(channel.getChannelIp());
				}
				GlobalPark.inChannelEvent.put(channel.getChannelIp(), fact);
				ParkLocalSet local = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
				if (local != null && local.getOnline() != null && local.getOnline() == 1) {
					body.setEquipmentID(local.getEquipmentID());
					body.setControlIP(channel.getChannelIp());
					Head head = new Head();
					head.setMethod("parkInIsOpen");
					body.setInRecord(fact);
					String equipmentID = local.getEquipmentID();
					String topic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
					MainBoardMessage<Head, BoxInIsOpenBody> returnInfo = new MainBoardMessage<>(head, body);
					String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
					System.out.println(topic);
					System.out.println(jsonBody);
					MqttMessageVO retVo = MqttServiceImpl.sendMessage(equipmentID,topic, jsonBody, null, 1);
					logger.debug("入场校正推送:"+topic+","+jsonBody);
				}
			}
		} catch (Exception ex) {
			logger.error("入场确认开闸推送:", ex);
		}
	}


	/** 卡类是否过期 */
	/**
	 * wangchengxi EXPIRE 固定车过期 UNDUE 未到期 TIMENOTALLOW 时间段禁止进入 EFFECTIVE 有效期内
	 */
	@Override
	public Step isCardTypeExpireIn(InRealTimeBase fact) {
		try {
			logger.debug("isCardTypeExpireIn:"+fact.getCarNo());
			Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
			Date sTime = fact.getCardInfo().getStartDate(); // 开始有效期
			ParkEffetTimes parkEffetTimes = parkEffetTimesMapper.selectByCardType(fact.getCarRealType());

			int overDays = Integer.parseInt(GlobalPark.properties.getProperty("FIXED_CAR_EXPIR", "0"));// 固定车过期天数
			int monthTemp = Integer.parseInt(GlobalPark.properties.getProperty("CHARGE_TYPE", "0"));// 启用月临卡功能
			if (fact.getNowDate().before(sTime)) { // 在有效期之前 所有固定车按临时车处理
				switch (fact.getCarBigType()) {
				case InOutRealTimeBase.MONTH_CAR: // 月车
				case InOutRealTimeBase.FREE_CAR: // 免费车
				case InOutRealTimeBase.STORED_CAR: // 储值车
					fact.setCarRealType(InOutRealTimeBase.TEMP_CAR_A);
					((InRealTimeBase) fact).getIn().setCardType( InOutRealTimeBase.TEMP_CAR_A);
					fact.setCarBigType(InOutRealTimeBase.TEMP_CAR);
					return Step.UNDUE;
				}
			} else if (!eTime.after(fact.getNowDate())) { // 有效期之后,入场还是月卡处理
				switch (fact.getCarBigType()) {
				case 1: // 月卡
					if (monthTemp == 0) { // 未启用月临卡功能
						if (ParkMethod.GetDate(eTime, overDays).before(fact.getNowDate())) {
							return Step.EXPIRE; // 过期
						} else {
							return Step.EFFECTIVE;
						}
					} else {
						int carType = setMonthCardType(fact.getCarRealType(), monthTemp);
						fact.setCarRealType(carType);
						fact.getIn().setCardType( (carType - 10));
						fact.setCarBigType(InOutRealTimeBase.MONTH_TEMP_CAR);
						return Step.UNEFFECTIVE; // 不在有效期内
					}
				case 4:// 免费车
					if (ParkMethod.GetDate(eTime, overDays).before(fact.getNowDate())) {
						return Step.EXPIRE; // 过期
					}
					break;
				case 5: // 储值车
					return Step.EXPIRE; // 过期
				}
				return Step.EFFECTIVE; // 在有效期内
			} else { // 在有效内
				if (fact.getCarBigType() == 1) {
					if (parkEffetTimes == null) { // 不存在时段禁止
						return Step.EFFECTIVE;
					}
					Date start = parkEffetTimes.getbTime(); // 开始时间
					Date end = parkEffetTimes.geteTime(); // 时段结束时间
					if (parkEffetTimes.getsType() == 1 && DateTimeUtils.isEffectiveDate(fact.getNowDate(), start, end)) // 时间段禁止进入
					{
						return Step.TIMENOTALLOW; // 时段内禁止入场 ，入场还是月卡处理
					}
				}

				return Step.EFFECTIVE;

			}
		} catch (Exception ex) {
			logger.error("入场有效期判断:", ex);
			return Step.EFFECTIVE;
		}
		
		return Step.EFFECTIVE;
	}

	/**
	 * 实时推送入场数据
	 * @param parkCarIn
	 */
	public void pushCarInfo(ParkCarIn parkCarIn){
	    try {
            BackUpParkCarIn backUpIn = new BackUpParkCarIn();
            BeanUtils.copyProperties(parkCarIn, backUpIn);
            ParkCarInVO info = conversionParameterClass.getParkCarIn(backUpIn);
            List<ParkCarInVO> sendList = new ArrayList<ParkCarInVO>();
            if (info != null && info.getObjectId() != null) {
                sendList.add(info);
                onlineDSScanSever.sendInfo(sendList, "park/parkcarin/v1", "parkcarin");            //传送数据
                logger.debug("推送入场数据到云端成功");
            }
        }catch (Exception e){
	        logger.error("实时推送入场数据异常：",e);
        }
	}

    /**
     * 实时推送入场数据图片上传
     * @param parkCarIn
     */
    public void pushCarInImg(ParkCarIn parkCarIn){
        try {
			if("1".equals(GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD"))&&"1".equals(GlobalPark.properties.getProperty("CLOUD_STATUS"))) {
				String result = UploadUtil.syFtpImg(parkCarIn.getGuid(), parkCarIn.getInPic());          //上传入场图片
				if (result != null) {
					parkCarIn.setIsImgUpload(1);
					parkCarInMapper.updateByPrimaryKey(parkCarIn);          //更新数据状态
					logger.debug("推送入场图片到云端成功");
				}
			}
        }catch (Exception e){
            logger.error("实时推送入场图片异常：",e);
        }
    }
}
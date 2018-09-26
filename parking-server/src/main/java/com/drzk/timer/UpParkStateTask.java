
package com.drzk.timer;

import com.drzk.common.InOutRealTimeBase;
import com.drzk.common.ParkMethod;
import com.drzk.mapper.*;
import com.drzk.parklib.send.MainBoardSdk;
import com.drzk.service.entity.*;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
import com.drzk.vo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 上传车场状态的处理 <br>
 * Date: 2018年7月31日 上午9:07:22 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
@Scope("singleton")
public class UpParkStateTask implements Runnable {
	private static Logger logger = Logger.getLogger("userLog");
	@Autowired
	private ParkDeviceStatusMapper mapper;
	@Autowired
	private ParkCarInMapper parkInMapper;
	@Autowired
	private ParkCarOutMapper parkOutMapper;
	
	@Autowired
	private ParkCarInMapper parkCarInMapper;
	
	@Autowired
	VwParkCarIsuseMapper vwParkCarIsuseMapper;
	
	@Autowired
	private BackUpParkCarInMapper backUpParkCarInMapper;
	
	private UpParkStateBody parkStateBody;
    private Head head;
	public void setHead(Head head) {
		this.head = head;
	}

	/**
	 * parkStateBody.
	 *
	 * @param parkStateBody the parkStateBody to set
	 * @since JDK 1.8
	 */
	public void setParkStateBody(UpParkStateBody parkStateBody) {
		this.parkStateBody = parkStateBody;
	}

	@Override
	public void run() {
		/*
		 * 在上传状态时，判断本次的时间与上一次上传的时间相处是否大于9秒(每3秒上传一次)
		 * 判断记录是入场还是出场
		 * 入场逻辑：提取到入场记录时，
		 * 			1，判断出场表是否有入场时间与车牌都相同的记录，如果有,表示车已经出场,则该条入场记录不保存
		 * 			2，判断入场表中是否有相同的车牌的记录 如果有，判断入场时间。如果已存在的记录的入场时间比当前记录的入场时间晚，则丢弃当前记录
		 * 			否则删掉之前的入场记录，保存当前入场记录
		 * 			如果没有相同车牌的入场记录，直接保存
		 * 			
		 * 			
		 * 出场逻辑：提取到出场记录时,保存出场记录。然后查询对应的入场表，查询是否有入场时间相同的车牌号，有则删除
		 * 			没有则不管
		 */
		
		
		try {
			String ip = parkStateBody.getConIp();
			ParkChannelSet channel = ParkMethod.getChannelSetByIp(ip);
			if (channel != null) {
				channel.setOnline(true);
				ParkDeviceStatus record ;
				record = mapper.selectLastByConIp(ip); //根据IP去查找是否有记录存在
				if (record == null) { // 为空表示第一次上传
					record = new ParkDeviceStatus();
					record.setRoadGateState((byte)Integer.parseInt(parkStateBody.getRoadGate()));//道闸状态 0-关到位，1-中间位，2-开到位
					record.setGroudSenseUnnormal((byte)Integer.parseInt(parkStateBody.getGroundSense())); //地感异常 00-恢复，01-异常
					record.setCameraUnnormal((byte)Integer.parseInt(parkStateBody.getCamera())); //摄像机异常 00-恢复，01-异常
					record.setDisModuleConUnnormal((byte)Integer.parseInt(parkStateBody.getDisplayConnect()));// 显示模块连接异常 00-恢复，01-异常
					record.setVoicemoduleConUnnormal((byte)Integer.parseInt(parkStateBody.getVoiceConnect()));// 语音模块连接异常 00-恢复，01-异常
					record.setRoadGateConUnnormal((byte)Integer.parseInt(parkStateBody.getRoadGateConnect())); // 道闸连接异常 00-恢复，01-异常
					record.setMainBoardNetUnnormal((byte)Integer.parseInt(parkStateBody.getMainBoardNet())); // 主板网络异常 00-恢复，01-异常
					record.setClockUnnormal((byte)Integer.parseInt(parkStateBody.getSysClock())); // 系统时钟故障 00-恢复，01-异常
					record.setStorageUnnormal((byte)Integer.parseInt(parkStateBody.getSyssStorage())); // 系统存储故障 00-恢复，01-异常
					record.setBoxId(channel.getBoxId()); //岗亭
					record.setMacIp(ip);
					record.setOnlineTime(new Date());
					record.setLastUpdateTime(new Date());
					mapper.insertSelective(record);
					// TODO 提取脱机记录
					getParkRecord(ip);
				} else {
					// 比较当前时间与最后一次更新时间，相差9秒说明有3次心跳没上传，判断为掉线
					Date lastUpdateTime = record.getLastUpdateTime();
					long now = new Date().getTime();
					long lastUpdate = lastUpdateTime.getTime();
					if (now - lastUpdate > 9 * 1000) {
						// 断线重连，需要重新保存一条记录
						record = new ParkDeviceStatus();
						record.setRoadGateState((byte)Integer.parseInt(parkStateBody.getRoadGate()));//道闸状态 0-关到位，1-中间位，2-开到位
						record.setGroudSenseUnnormal((byte)Integer.parseInt(parkStateBody.getGroundSense())); //地感异常 00-恢复，01-异常
						record.setCameraUnnormal((byte)Integer.parseInt(parkStateBody.getCamera())); //摄像机异常 00-恢复，01-异常
						record.setDisModuleConUnnormal((byte)Integer.parseInt(parkStateBody.getDisplayConnect()));// 显示模块连接异常 00-恢复，01-异常
						record.setVoicemoduleConUnnormal((byte)Integer.parseInt(parkStateBody.getVoiceConnect()));// 语音模块连接异常 00-恢复，01-异常
						record.setRoadGateConUnnormal((byte)Integer.parseInt(parkStateBody.getRoadGateConnect())); // 道闸连接异常 00-恢复，01-异常
						record.setMainBoardNetUnnormal((byte)Integer.parseInt(parkStateBody.getMainBoardNet())); // 主板网络异常 00-恢复，01-异常
						record.setClockUnnormal((byte)Integer.parseInt(parkStateBody.getSysClock())); // 系统时钟故障 00-恢复，01-异常
						record.setStorageUnnormal((byte)Integer.parseInt(parkStateBody.getSyssStorage())); // 系统存储故障 00-恢复，01-异常
						record.setBoxId(channel.getBoxId()); //岗亭ID
						record.setMacIp(ip);
						record.setOnlineTime(new Date());
						record.setLastUpdateTime(new Date());
						mapper.insertSelective(record);
						
						// 提取脱机记录
						getParkRecord(ip);
					} else {
						// 持续在线,只需要更新最后修改时间,不需要提取脱机记录
						record.setRoadGateState((byte)Integer.parseInt(parkStateBody.getRoadGate()));//道闸状态 0-关到位，1-中间位，2-开到位
						record.setGroudSenseUnnormal((byte)Integer.parseInt(parkStateBody.getGroundSense())); //地感异常 00-恢复，01-异常
						record.setCameraUnnormal((byte)Integer.parseInt(parkStateBody.getCamera())); //摄像机异常 00-恢复，01-异常
						record.setDisModuleConUnnormal((byte)Integer.parseInt(parkStateBody.getDisplayConnect()));// 显示模块连接异常 00-恢复，01-异常
						record.setVoicemoduleConUnnormal((byte)Integer.parseInt(parkStateBody.getVoiceConnect()));// 语音模块连接异常 00-恢复，01-异常
						record.setRoadGateConUnnormal((byte)Integer.parseInt(parkStateBody.getRoadGateConnect())); // 道闸连接异常 00-恢复，01-异常
						record.setMainBoardNetUnnormal((byte)Integer.parseInt(parkStateBody.getMainBoardNet())); // 主板网络异常 00-恢复，01-异常
						record.setClockUnnormal((byte)Integer.parseInt(parkStateBody.getSysClock())); // 系统时钟故障 00-恢复，01-异常
						record.setStorageUnnormal((byte)Integer.parseInt(parkStateBody.getSyssStorage())); // 系统存储故障 00-恢复，01-异常
						record.setBoxId(channel.getBoxId()); //岗亭ID
						record.setLastUpdateTime(new Date());
						record.setOnLine(true);
						mapper.updateByPrimaryKeySelective(record);
						uploadStatusReply(channel.getDsn(),head.getReplyTopic(),parkStateBody.getuId());
					}
				}
				
				//更新状态列表
				if(GlobalPark.parkDeviceStatus.contains(record)) {
					GlobalPark.parkDeviceStatus.remove(record);
				}
				GlobalPark.parkDeviceStatus.add(record); 
			}
			
		} catch (Exception e) {
			LoggerUntils.error(logger, e);
		}finally {
			//	收到心跳后需要给硬件回复
			//uploadStatusReply(head.getReplyTopic(),parkStateBody.getuId());
		}
	}
	/**
	 * 
	 * 状态回复 . <br>
	 *
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
	private void uploadStatusReply (String mac,String replyTopic,String uId ) {
		Head head=new Head();
		head.setReplyTopic("");
		head.setMethod("uploadEventReply");
		head.setVersion("V10");
		UpParkStateReplyBody body=new UpParkStateReplyBody();
		body.setuId(uId);
		body.setSysTime(new Date());
		MainBoardMessage<Head,UpParkStateReplyBody> returnInfo = new MainBoardMessage<>(head,body);
		String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
		MqttServiceImpl.sendMessage(mac,replyTopic, jsonBody, null, 3);
	}
	
	/**
	 * 
	 * 提取脱机记录 . <br>
	 *
	 * @author chenlong
	 * @since JDK 1.8
	 */
	private void getParkRecord(String ip) {
		ParkChannelSet	channelSet = ParkMethod.getChannelSetByIp(ip);
		if(channelSet == null) {
			return;
		}
		String equipmentID = channelSet.getDsn();
		/**
		 * 1,获取记录条数
		 * 2,循环获取
		 * 3,删除
		 */
		GetOfflineRecordBody getOfflineRecord = new GetOfflineRecordBody();
		MainBoardMessage<ReplyHead, GetOfflineReturnBody> replyVo = MainBoardSdk.sendAndGet(equipmentID, "readRecordCount", getOfflineRecord,
				GetOfflineReturnBody.class);
		if(replyVo != null) {
			int recordNum = Integer.parseInt(replyVo.getBody().getRecordNo());
			//循环取脱机记录
			for(int i = 0;i < recordNum; i++) {
				GetParkRecordBody getParkRecord = new GetParkRecordBody();
				MainBoardMessage<ReplyHead, GetOfflineRecordReturnBody> replyRecord = MainBoardSdk.sendAndGet(equipmentID, "getParkingRecord", getParkRecord,
						GetOfflineRecordReturnBody.class);
				
				if(replyRecord != null) {
					GetOfflineRecordReturnBody recordBody = replyRecord.getBody();
					String recordType = recordBody.getRecordType();
					String recordNo = recordBody.getRecordNo();
					switch (recordType) {
					case "0":
						dealParkIn(recordBody,channelSet);
						break;
					case "1": //
						dealParkOut(recordBody,channelSet);
						break;
					default:
						break;
					}
					//删除
					DeleteParkRecordBody deleteRecord = new DeleteParkRecordBody();
					deleteRecord.setRecordNo(recordNo);
					MainBoardMessage<ReplyHead, DeleteParkRecordBodyReturn> deletereply = MainBoardSdk.sendAndGet(equipmentID, "delParkingRecord", deleteRecord,
							DeleteParkRecordBodyReturn.class);
				}
			}
		}
	}
	
	/**
	 * 
	 * 根据车牌获取固定车信息 <br>
	 *
	 * @author chenlong
	 * @param carNo
	 * @return
	 * @since JDK 1.8
	 */
	private  VwParkCarIsuse getVwParkCarIsuse(String carNo) {
		VwParkCarIsuse model = new VwParkCarIsuse();
		String rCarNo=carNo.trim().substring(1);
		model.setCarNo(rCarNo);
		model.setStatus(0);
		return vwParkCarIsuseMapper.selectMonthCar(model);
	}
	
	
	/**
	 * 脱机记录入场处理
	 * wangchengxi
	 * 
	 */
	private void dealParkIn(GetOfflineRecordReturnBody body,ParkChannelSet channel) {
		try {
			int small = ParkMethod.getSmallOrBig(channel);// 获取大小车场
			ParkCarIn carIn = new ParkCarIn();
			carIn.setMachNo(channel.getMachNo());
			carIn.setCarNo(body.getCarNo());
			carIn.setCardId(ParkMethod.CarNotoCardID(body.getCarNo()));
			carIn.setInTime(body.getInTime());
			carIn.setInPic(body.getInPic());
			carIn.setSmallPic(body.getSmallInPic());
			carIn.setInWay(InOutRealTimeBase.IN_OUT_OFFLINE); // 脱机记录
			carIn.setSmall(small); // 设置大小车场
			carIn.setGuid(ParkMethod.getUUID());
			carIn.setcFlag( 4);
			
			carIn.setCarNoType(Integer.valueOf(body.getCarColorType()));
			carIn.setInUserName(ParkMethod.getLoginName(channel.getBoxId()));
			VwParkCarIsuse model = getVwParkCarIsuse(carIn.getCarNo());
			if (model != null) { // 固定车
				carIn.setYktId(model.getYktId());
				carIn.setEmpName(model.getPerName());
				carIn.setCardType(model.getCardType());
				carIn.setCarNo(model.getCarNo());
			} else {
				carIn.setYktId(0);
				carIn.setEmpName("临时用户");
				carIn.setCardType( InOutRealTimeBase.TEMP_CAR_A);
			}
			ParkCarOut condition = new ParkCarOut();
			condition.setInCarNo(body.getCarNo());
			condition.setInTime(body.getInTime());
			List<ParkCarOut> out = parkOutMapper.selectByCondition(condition);
			if (out == null || out.size() == 0) {
				ParkCarIn inCondition = new ParkCarIn();
				String rCarNo = body.getCarNo().trim().substring(1);
				inCondition.setCarNo(rCarNo);
				inCondition.setSmall(small);
				ParkCarIn in = parkInMapper.selectTop(inCondition);
				if (in != null) {
					Date time = in.getInTime();
					// 如果数据库中的记录入场时间在当前记录的时间之前
					// 则删除原记录，保存新记录。否则保留原纪录，丢弃现记录
					// 总结，保留最新的
					if (time.before(body.getInTime())) {
						parkInMapper.deleteByPrimaryKey(in.getId());
						parkInMapper.insert(carIn);
					}
				} else {
					parkInMapper.insert(carIn);
				}
			}
		} catch (Exception e) {
			LoggerUntils.error(logger, e);
		}
	}
	
	/**
	 * 脱机记录入场处理
	 * wangchengxi
	 * 
	 */
	private void dealParkOut(GetOfflineRecordReturnBody body,ParkChannelSet channel) {
		try {
			int inChannelType = 0;// 入口类型
            int outChannelType = 0; //出口类型
			ParkCarOut carOut = new ParkCarOut();
			int small = ParkMethod.getSmallOrBig(channel);// 获取大小车场
			VwParkCarIsuse model = getVwParkCarIsuse(body.getCarNo());
			if (model != null) { // 固定车
				carOut.setYktId(model.getYktId());
			} else {
				carOut.setYktId(0);
			}
			outChannelType=channel.getInOut();
			ParkCarIn condition = new ParkCarIn();
			String rCarNo = body.getCarNo().trim().substring(1);
			condition.setCarNo(rCarNo);
			condition.setSmall( small);
			ParkCarIn modelGet = parkCarInMapper.selectTop(condition);
			if (modelGet != null) { // 存在入场记录
				ParkChannelSet parkChannelset = ParkMethod.getChannelSetByControlIndex(
						modelGet.getMachNo() == null ? 0 : modelGet.getMachNo().byteValue());
				if (parkChannelset != null)
					inChannelType = parkChannelset.getInOut();// 入口类型

				if (inChannelType == 4 && outChannelType == 3) { // 入口为半嵌套入口，出口为嵌套出口，
					modelGet.setSmall( 0);
					parkCarInMapper.updateByPrimaryKeySelective(modelGet);
				} else {
					parkCarInMapper.deleteByPrimaryKey(modelGet.getId());
					BackUpParkCarIn backUpIn = new BackUpParkCarIn();
					BeanUtils.copyProperties(modelGet, backUpIn);
					backUpIn.setId(null);
					backUpParkCarInMapper.insertSelective(backUpIn);
				}
				carOut.setInTime(modelGet.getInTime());
				carOut.setInMachNo(modelGet.getMachNo()==null ? 0 :modelGet.getMachNo());
				carOut.setInUserName(modelGet.getInUserName());
				carOut.setInPic(modelGet.getInPic());
				carOut.setSmallInPic(modelGet.getSmallPic());
				carOut.setInId(modelGet.getGuid());
			}else {
				carOut.setInTime(body.getInTime());
				carOut.setInMachNo(0);
				carOut.setInUserName(ParkMethod.getLoginName(channel.getBoxId())); //入场操作员
				carOut.setInPic(body.getInPic());
				carOut.setSmallInPic(body.getSmallInPic());
				carOut.setInId(ParkMethod.getLoginName(channel.getBoxId()));
			}
			carOut.setOutWay(0);
			carOut.setGuid(ParkMethod.getLoginName(channel.getBoxId()));
			carOut.setOutUserName(ParkMethod.getLoginName(channel.getBoxId()));//出场操作员
			carOut.setCardType(Integer.valueOf(body.getCarType())); //卡类型
			carOut.setOutMachNo(channel.getMachNo());
			carOut.setInCarNo(body.getCarNo());
			carOut.setOutCarNo(body.getCarNo());
			carOut.setOutPic(body.getOutPic());
			carOut.setSmallOutPic(body.getSmallOutPic());
			carOut.setcFlag(4);
			carOut.setOutTime(body.getOutTime());
			Float chargeMoney = Float.valueOf(body.getChargeableMoney());
			carOut.setAccountCharge(chargeMoney);
			Float payCharge = Float.valueOf(body.getRealChargeMoney());
			carOut.setPayCharge(payCharge);
			carOut.setDisAmount(chargeMoney-payCharge);
			carOut.setBalanceMoney(Float.valueOf(body.getStoredValueRemain()));
//			carOut.setDiscountNo(body.getDiscountNumber());
//			carOut.setTypeId(Integer.valueOf(body.getDiscountID()));
//			carOut.setDiscountTime(body.getDiscountTime());
			int row=parkOutMapper.insert(carOut);
		}catch (Exception e) {
			LoggerUntils.error(logger, e);
		}
		
	}
	

}

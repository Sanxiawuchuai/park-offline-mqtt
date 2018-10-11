
package com.drzk.parklib.event;

import com.drzk.common.InOutRealTimeBase;
import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.common.ParkMethod;
import com.drzk.fact.InRealTimeBase;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.service.IParkingService;
import com.drzk.service.entity.*;
import com.drzk.timer.*;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
import com.drzk.utils.SpringUtil;
import com.drzk.vo.ParkCarIn;
import com.drzk.vo.ParkCarOut;
import com.drzk.vo.ParkChannelSet;
import org.apache.log4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.Date;

/**
 * 主板上传车场事件处理类<br>
 * Date: 2018年6月28日 下午5:34:06 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class UploadParkingEvent {
	private static Logger logger = Logger.getLogger("userLog");
	
	/**
	 * 
	 * 主板上传事件后,由此函数处理<br>
	 * 
	 * @author chenlong
	 * @param jsonStr
	 * @since JDK 1.8
	 */
	public static void receiveJson(String jsonStr) {
		try {  
			String method = JsonUtil.getMethodByJsonStr(jsonStr);
			ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
			if(threadPool==null){
				return;
			}
			switch (method) {
			case "uploadRecord": // 上传记录(服务器订阅)
				uploadRecord(jsonStr);
				break;
			case "uploadEvent": // 上传事件
				uploadEvent(jsonStr);
				break;
			case "uploadStatus": // 上传状态
				unloadStatus(jsonStr);
				break;
			case "uploadlog": // 上传日志
				break;
			case "boxLogin": //岗亭登入
			case "boxLogout":// 岗亭登出
			case "boxChangeshifts": // 岗亭交班
			case "boxReshoot": //软件触发相机
			case "boxHandIn": //手工入场
			case "boxHandOut": //手工出场
			case "boxComputerGate": //电脑控制道闸
			case "boxPayCharge":  //收费改变(卡类，打折，免费)
			case "boxPeakMode":  //高峰模式设置
			case "boxInIsOpen":  //入场确认开闸确认
			case "boxOutIsOpen":  //出场确认开闸确认
			case "boxInChanelCorrecting":  //入场校正
			case "boxOutChanelCorrecting":  //出场校正
			case "boxGetInRecord"://查找场内记录
			case "centrialCharge"://中央缴费
			case "centrialChargePaySuccess"://岗亭中央缴费返回
			case "getNoCarNo"://获取新的无牌车号码
			case "boxSpeechSounds"://播报收费语音
			case "boxModifyCarNo"://修改入场车牌
			case "getPayCharge"://计费
				logger.debug("收到岗亭数据:"+jsonStr);
				BoxTask boxTask = SpringUtil.getBean(BoxTask.class);
				boxTask.setJson(jsonStr);
				threadPool.execute(boxTask);
				break;
			case "administrationCenterUpdate":  //后台更新数据通知
				logger.debug("收到后台数据更新:"+jsonStr);
				//如果数据库设置需要往云端传数据,再往云端传输数据
				CenterUpdateTask centerUpdateTask = SpringUtil.getBean(CenterUpdateTask.class);
				centerUpdateTask.setJson(jsonStr);
				threadPool.execute(centerUpdateTask);
				break;
			default:
				//TestTask testtask = SpringUtil.getBean(TestTask.class);
				//threadPool.execute(testtask);
				break;
			}
		}catch (Exception ex) {
			logger.error("主板上传事件处理:",ex);
		}
	}
	
	/** 上传记录(服务器订阅) */
	private static void uploadRecord(String jsonStr) {
		try {
			logger.debug("uploadRecord:" + jsonStr);
			MainBoardMessage<Head, UpRecordBody> boardMessage = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					UpRecordBody.class);
			ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
			UpRecordBody body = boardMessage.getBody();
			String recordType = body.getRecordType();// 0 表示入场 1 出场记录 2 异常开闸记录
			String conIp = body.getConIp();
			ParkChannelSet channel = ParkMethod.getChannelSetByIp(conIp);
			if (channel == null) {
				return;
			}
			channel.setOnline(true);
			switch (recordType) {
			case "0": // 入场记录
				AutoParkIn parkInTask = SpringUtil.getBean(AutoParkIn.class);
				parkInTask.setChannel(channel);
				parkInTask.setBody(body);
				threadPool.execute(parkInTask);
				break;
			case "1": // 出场记录
				AutoParkOut parkOutTask = SpringUtil.getBean(AutoParkOut.class);
				parkOutTask.setChannel(channel);
				parkOutTask.setBody(body);
				threadPool.execute(parkOutTask);
				break;
			case "2":  //异常开闸记录
				UnusualInOut unusualTask = SpringUtil.getBean(UnusualInOut.class);
				unusualTask.setChannel(channel);
				unusualTask.setBody(body);
				threadPool.execute(unusualTask);
				break;
			default:
				break;
			}
		} catch (Exception ex) {
			logger.error("主板上传记录处理:",ex);
		}
	}
	

	
	/** 上传事件 */
	private static void uploadEvent(String jsonStr) {
		try {
			logger.debug("uploadEvent:" + jsonStr);
			Date nowTime = new Date();
			ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
			MainBoardMessage<Head, UpParkEventBody> boardMessage = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					UpParkEventBody.class);
			UpParkEventBody body = boardMessage.getBody();
			String recordType = body.getEventType();// 1,为入场;2为出场
			String conIp = body.getConIp();
			ParkChannelSet channel = ParkMethod.getChannelSetByIp(conIp);
			// 如果通道没在数据库中保存，直接不处理
			if (channel == null) {
				return;
			}
			channel.setOnline(true);
			switch (recordType) {
			case "1":
				InRealTimeBase in = new InRealTimeBase();

				// 将事件中对应的属性设置进carIn对象中
				ParkCarIn carIn = changeEventBodyToCarIn(body, channel, nowTime);
				// 将通道封装类设置进in
				in.setChannelSet(channel);

				// 设置当前时间
				in.setNowDate(nowTime);
				in.setCarNo(body.getCarNo());
				in.setIn(carIn);
				in.setInOrOutFlag(true);

				GlobalPark.inChannelEvent.put(conIp, in);
				in.setNextStep(Step.START);

				if (GlobalPark.inChannelEvent.containsKey(conIp)) {
					GlobalPark.inChannelEvent.remove(conIp);
				}
				GlobalPark.inChannelEvent.put(conIp, in);
				ParkingInTask parkingInTask = SpringUtil.getBean(ParkingInTask.class);
				parkingInTask.setParkIn(in);
				threadPool.execute(parkingInTask);
				break;
			case "5":
				OutRealTimeBase out = new OutRealTimeBase();

				// TODO 将事件中的信息设置进carOut对象
				ParkCarOut carOut = changeEventBodyToCarOut(body, channel, nowTime);
				// ====================================
				out.setInOrOutFlag(false);
				out.setChannelSet(channel);
				out.setNowDate(nowTime);
				out.setCarNo(body.getCarNo());
				out.setOut(carOut);

				if (GlobalPark.outChannelEvent.containsKey(conIp)) {
					GlobalPark.outChannelEvent.remove(conIp);
				}
				GlobalPark.outChannelEvent.put(conIp, out);
				ParkingOutTask parkingOutTask = SpringUtil.getBean(ParkingOutTask.class);
				out.setNextStep(Step.START);
				parkingOutTask.setParkOut(out);
				threadPool.execute(parkingOutTask);
				break;

			case "9":// 无牌车入场 直接推送前端
				threadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							InRealTimeBase noCarIn = new InRealTimeBase();

							// 将事件中对应的属性设置进carIn对象中
							ParkCarIn noParkCarIn = changeEventBodyToCarIn(body, channel, nowTime);
							// 将通道封装类设置进in
							noCarIn.setChannelSet(channel);

							// 设置当前时间
							noCarIn.setNowDate(nowTime);
							noCarIn.setIn(noParkCarIn);
							noCarIn.setInOrOutFlag(true);
							noCarIn.setNextStep(Step.NOCARNO);
							noCarIn.getStatusMap().put("method", "parkInChanelCorrecting");
							if (GlobalPark.inChannelEvent.containsKey(conIp)) {
								GlobalPark.inChannelEvent.remove(conIp);
							}
							GlobalPark.inChannelEvent.put(conIp, noCarIn);
							IParkingService parkingInService = (IParkingService) SpringUtil.getBean("parkingInService");
							parkingInService.boxInIsOpen(noCarIn);
							parkingInService.broadcastVoice(noCarIn); //播报无牌请扫码
						} catch (Exception e) {
							LoggerUntils.error(logger, e);
						}
					}
				});
				break;
			case "10": //无牌车出场，直接信送前端
				threadPool.execute(new Runnable() {
					@Override
					public void run() {
						try {
							OutRealTimeBase outNoCarNo = new OutRealTimeBase();

							// TODO 将事件中的信息设置进carOut对象
							ParkCarOut noCarNocarOut = changeEventBodyToCarOut(body, channel, nowTime);
							// ====================================
							outNoCarNo.setInOrOutFlag(false);
							outNoCarNo.setChannelSet(channel);
							outNoCarNo.setNowDate(nowTime);
							outNoCarNo.setCarNo(body.getCarNo());
							outNoCarNo.setOut(noCarNocarOut);
							outNoCarNo.setFrmType(5);
							if (GlobalPark.outChannelEvent.containsKey(conIp)) {
								GlobalPark.outChannelEvent.remove(conIp);
							}
							GlobalPark.outChannelEvent.put(conIp, outNoCarNo);
							// ParkingOutTask parkingOutTask = SpringUtil.getBean(ParkingOutTask.class);
							outNoCarNo.setNextStep(Step.NOCARNO);
							IParkingService parkingOutService = (IParkingService) SpringUtil
									.getBean("parkingOutService");
							parkingOutService.sendSimilarCar(outNoCarNo);
							parkingOutService.broadcastVoice(outNoCarNo); //播报无牌请扫码
						} catch (Exception e) {
							logger.error("uploadEvent:",e);
						}
					}
				});
				break;
			default:
				break;
			}
		} catch (Exception e) {
			logger.error("uploadEvent:",e);
		}
	}
	/** 上传状态*/
	public static void unloadStatus(String jsonStr) {
		try {
			ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
			MainBoardMessage<Head, UpParkStateBody> parkState = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					UpParkStateBody.class);
			UpParkStateBody parkStateBody = parkState.getBody(); //得到body
			Head head =parkState.getHead(); //得到头
			UpParkStateTask parkStateTask = SpringUtil.getBean(UpParkStateTask.class);
			parkStateTask.setParkStateBody(parkStateBody);
			parkStateTask.setHead(head);
			threadPool.execute(parkStateTask);
		}catch (Exception e) {
			logger.error("上传状态:",e);
		}
	}
	
	/**
	 * 将硬件上传的记录中的值赋值到数据库入场实体中
	 * @author chenlong
	 * 2018年9月1日
	 */
	private static ParkCarIn changeEventBodyToCarIn(UpParkEventBody eventBody,ParkChannelSet channel,Date nowtime) {
		ParkCarIn carIn = new ParkCarIn();
		
		/**
		 * 	"uId": "f49a13cca0b6444a80f4c3f8abfc4c0c",
			"parkingNo": "X11403100001",
			"equipmentID": "202",
			"conIp": "192.168.16.202",
			"recordNo": "201",
			"eventType": "9",
			"eventTime": "2018-09-11 09:20:17"
		 */
		
		//carIn.setAssistantCarNo(assistantCarNo);
		//carIn.setAssistantSmallPic(assistantSmallPic);
		//carIn.setBackInPic(backInPic);
		String carNo = eventBody.getCarNo();
//		if(!StringUtils.isNullOrEempty(carNo)) {
			carIn.setCarNo(carNo);
			if(carNo != null)
				carIn.setCardId(ParkMethod.CarNotoCardID(carNo));
			if(eventBody.getCarType() != null)
				carIn.setCarNoType(Integer.valueOf(eventBody.getCarType()));
			carIn.setInPic(eventBody.getFrontPic());
			carIn.setSmallPic(eventBody.getSmallFrontPic());
//		}
		
		carIn.setYktId(0);
		carIn.setCardType(InOutRealTimeBase.TEMP_CAR_A);
		carIn.setEmpName("临时用户");
		
		carIn.setcFlag(4);
		//carIn.setDiscountNo(discountNo);
		//carIn.setDiscountTime(discountTime);
		//carIn.setEmpName(empName);
		carIn.setGuid(ParkMethod.getUUID());
		
		carIn.setInTime(nowtime);
		//carIn.setInUserName(inUserName);
		carIn.setInWay(InOutRealTimeBase.IN_OUT_NORMAL);
		//carIn.setIsLocked(isLocked);
		carIn.setMachNo(channel.getMachNo());
		//carIn.setSmall(small);
		
		carIn.setInUserName(ParkMethod.getLoginName(channel.getBoxId()));
		return carIn;
	}
	
	private static ParkCarOut changeEventBodyToCarOut(UpParkEventBody eventBody,ParkChannelSet channel,Date nowtime) {
		ParkCarOut carOut = new ParkCarOut();
		//carOut.setAccountCharge(accountCharge);
		//carOut.setBackInCarNo(backInCarNo);
		//carOut.setBackInPic(backInPic);
		//carOut.setBackOutCarNo(backOutCarNo);
		//carOut.setBackOutPic(backOutPic);
		//carOut.setBalanceMoney(balanceMoney);
		String carNo = eventBody.getCarNo();
		
		carOut.setCardId(ParkMethod.CarNotoCardID(carNo));
		//carOut.setCardNo(cardNo);
		//carOut.setCardType(cardType);
		if(eventBody.getCarType()==null) {
			carOut.setCarNoType(0);
		}else {
		    carOut.setCarNoType(Integer.valueOf(eventBody.getCarType()));
		}
		//carOut.setCentrialTime(centrialTime);
		carOut.setcFlag(4);
		//carOut.setCredentialsPic(credentialsPic);
		//carOut.setDisAmount(disAmount);
		//carOut.setDiscountNo(discountNo);
		//carOut.setDiscountTime(discountTime);
		//carOut.setFreeType(freeType);
		carOut.setGuid(ParkMethod.getUUID());
		carOut.setOutCarNo(carNo);
		carOut.setOutMachNo(channel.getMachNo());
		carOut.setOutPic(eventBody.getFrontPic());
		carOut.setOutTime(nowtime);
		//carOut.setOutUserName(outUserName);
		carOut.setOutWay(InOutRealTimeBase.IN_OUT_NORMAL);
		carOut.setSmallOutPic(eventBody.getSmallFrontPic());
		carOut.setOutUserName(ParkMethod.getLoginName(channel.getBoxId()));
		return carOut;
		
	}

}

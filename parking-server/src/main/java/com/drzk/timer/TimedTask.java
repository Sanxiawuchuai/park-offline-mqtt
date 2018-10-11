
package com.drzk.timer;


import com.drzk.bean.MqttMessageVO;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.mapper.ParkCarInMapper;
import com.drzk.online.service.OnlineDSScanSever;
import com.drzk.service.ILoadDeviceListService;
import com.drzk.service.entity.Head;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.entity.ParkCarPlaceBody;
import com.drzk.service.entity.ParkDeviceStatusBody;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.vo.ParkChannelSet;
import com.drzk.vo.ParkDeviceStatus;
import com.drzk.vo.ParkLocalSet;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 定时任务类 <br>
 * 
 * Date: 2018年5月24日 上午8:58:07 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
public class TimedTask{
	private static Logger logger = Logger.getLogger("taskLog");
	@Autowired
	private ParkCarInMapper parkCarInMapper;
	@Autowired
	OnlineDSScanSever onlineDSScanSever;
	@Autowired
	private ILoadDeviceListService loadDeviceListService;
	
	
	/**
	 * 数据刷新线程,包括车场是否满位，与岗亭定时推送数据<br>
	 * Date: 2018年7月3日 上午9:33:09 <br>
	 * @author wangchengxi
	 * @version
	 * @since JDK 1.8
	 * @see
	 */
	//@Scheduled(cron="*/5 * * * * ?") 每天的时间点执行
    //@Scheduled(fixedDelay = 5000)  //间隔时间
    //@Lazy(value=false) 
	@Scheduled(fixedDelay = 1000)
	public void refreshData() {
	    try {
	    	int[] placeNum=getPlaceNum(); //获取车位数
 	    	sendCarPalceToBox(placeNum); //给岗亭推车位
			//logger.debug("刷新线程完成");
	    }catch (Exception ex) {
	    	logger.error("数据刷新线程:",ex);
		}
	}
	
	//实时更新各个设备的在线状态
	@Scheduled(fixedDelay = 1000)
	public void refreshDeviceStatus() {
		try {
			for (ParkDeviceStatus deviceStatus : GlobalPark.parkDeviceStatus) {
				Date lastUpdateTiem = deviceStatus.getLastUpdateTime();
				long now = new Date().getTime();
				long lastUpdate = lastUpdateTiem.getTime();
				if ((now - lastUpdate) > 9 * 1000) {
					String ip = deviceStatus.getMacIp();
					ParkChannelSet channel = ParkMethod.getChannelSetByIp(ip);
					if(channel!=null)channel.setOnline(false);
				}
			}
			//logger.debug("更新设备完成");
		} catch (Exception ex) {
			logger.error("更新设备状态:",ex);
		}
	}

	final long sencond =24*60*60*1000;
	@Scheduled(fixedDelay = sencond)
	public void pushDataToYun() {
		try {
			String flag = GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD");
			if (flag == null) return;
			Integer flagUpdate = Integer.parseInt(flag);
			if (flagUpdate == 1) {
				/** 岗亭信息 */
				onlineDSScanSever.boxinfo();
				/** 控制器*/
				onlineDSScanSever.controllerinfo();
				/** 相机 */
				onlineDSScanSever.parkcameras();
				/** 黑名单 */
				onlineDSScanSever.parkblackcar();
				/** 异常入场 */
				onlineDSScanSever.parkunusualcarin();
				/** 充值信息 */
				onlineDSScanSever.parkcaroperation();
				/** 月租费率 */
				onlineDSScanSever.parkmonthlyfeetype();
				/** 系统健康 */
				onlineDSScanSever.parkcompertask();
				/** 公司信息 */
				onlineDSScanSever.companyinfo();
				/** 部门信息 */
				onlineDSScanSever.departmentinfo();
				/** 同步删除的入场数据 */
				onlineDSScanSever.parkdeletecarin();
				/** 车主用户信息 */
				onlineDSScanSever.parkcaruser();
				/** 人员信息 */
				onlineDSScanSever.personnelinfo();
				/** 车场配置信息  */
				onlineDSScanSever.parksetting();
				/** 商家信息  */
				onlineDSScanSever.couponeqinfo();
				/** 优惠券信息 */
				onlineDSScanSever.couponrule();
				/** 优惠券使用明细 */
				onlineDSScanSever.couponuserlist();
				/** 收费标准 */
				onlineDSScanSever.feescale();
				/** 车位组信息 */
				onlineDSScanSever.carGroupInfo();
				/**超时收费测试*/
				onlineDSScanSever.pushOverTimes();
				logger.debug("车场其他配置定时上传完成");
			}
		}catch (Exception e){
			logger.error("定时执行上传任务出现异常:",e);
		}
	}

	/**
	 * 上传车场记录的数据
	 */
	@Scheduled(fixedDelay = 5*60*1000)
	public void pushCarInfo(){
		try {
			String flag = GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD");
			if (flag == null) return;
			Integer flagUpdate = Integer.parseInt(flag);
			if (flagUpdate == 1) {
				/** 同步入场数据 (最多100条) */
				onlineDSScanSever.parkcarin();
				/** 同步出场数据（最多100）  */
				onlineDSScanSever.parkcarout();
				/** 交班信息  */
				onlineDSScanSever.parksumusers();
				/** 中央缴费 */
				onlineDSScanSever.parkcenterpayment();
				logger.debug("车场相关报表数据定时上传完成");
			}
		}catch (Exception e){
			logger.error("同步车场数据异常：",e);
		}
	}

	/**
	 * 同步出入场图片数据
	 */
	@Scheduled(fixedDelay = 10*60*1000)
	public void pushCarImg(){
		try {
			String flag = GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD");
			String isUpImg=GlobalPark.properties.getProperty("UPLOAD_IMG");         //是否上传图片
			String cloudStatus=GlobalPark.properties.getProperty("CLOUD_STATUS");         //是否云端有通信记录
			if (flag == null) return;
			if ("1".equals(flag)&&"1".equals(isUpImg)&&"1".equals(cloudStatus)) {
				/**同步入场图片信息*/
				onlineDSScanSever.pushParkCarInImg();
				/**同步出场图片信息*/
				onlineDSScanSever.pushParkCarOutImg();
				logger.debug("车场相关报表图片定时上传完成");
			}
		}catch (Exception e){
			logger.error("同步车场图片异常，",e);
		}
	}

	/**
	 * 下发车主用户到硬件上
	 */
	@Scheduled(fixedDelay = 10*60*1000)
	public void sendCardInfo(){
		loadDeviceListService.loadUserInfo();			//下发给用户
		logger.debug("车场定时下发硬件成功");
	}

	/**
	 * 扫描车场是否开启云端通信、车场过期后，是否终止其他的参数配置
	 */
	@Scheduled(fixedDelay = 10*60*1000)
	public void cloudTaskInfo(){
		String uploadCloud = GlobalPark.properties.getProperty("UPLOAD_DATA_CLOUD");		//是否连云端
		String cloudStatus = GlobalPark.properties.getProperty("CLOUD_STATUS");			//云端与线下通信状态
		if("1".equals(uploadCloud)&&"0".equals(cloudStatus)){						//开通云端的、并且没有第一次通信成功，重发
			onlineDSScanSever.reportAuth();			//重新发送
		}
	}

	/**
	 * 发送车位信息到岗亭 <br>
	 * @author 王诚喜
	 * @since JDK 1.8
	 */
	public void sendCarPalceToBox (int[] placeNum) throws Exception {
		try {
			// 0-总车位，1-剩余车位，2-固定车剩余车位，3-临时车剩余车位,4-预约车，5-共享车
			// * 6-临时车，7-月租车，8-储值车，9-免费车
			ParkCarPlaceBody bodyPlace = new ParkCarPlaceBody();
			Head headPlace = new Head();
			headPlace.setMethod("parkCarPlace");
			bodyPlace.setPlaceNumSum(Integer.toString(placeNum[0])); //
			bodyPlace.setOverPlusNum(Integer.toString(placeNum[1]));
			bodyPlace.setMonSumNum(Integer.toString(placeNum[2]));
			bodyPlace.setTemSumNum(Integer.toString(placeNum[3]));
			bodyPlace.setBespeakPlusNum(Integer.toString(placeNum[4]));
			bodyPlace.setShareCarNum(Integer.toString(placeNum[5]));
			bodyPlace.setTempCarNum(Integer.toString(placeNum[6]));
			bodyPlace.setMonCarNum(Integer.toString(placeNum[7]));
			bodyPlace.setOverStoreNum(Integer.toString(placeNum[8]));
			bodyPlace.setFreeCarNum(Integer.toString(placeNum[9]));
			for (ParkLocalSet local : GlobalPark.parkLocalSet) {
				if (local.getOnline() != null && local.getOnline() == 1 && local.getEquipmentID() != null) {
					// 实时车位
					MainBoardMessage<Head, ParkCarPlaceBody> returnInfo = new MainBoardMessage<>(headPlace, bodyPlace);
					String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
					String equipmentID = local.getEquipmentID();
					String replyTopic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
					MqttMessageVO reply = MqttServiceImpl.sendMessage(equipmentID, replyTopic, jsonBody, null, 3);

					// 实时设备状态推送给岗亭
					List<ParkDeviceStatus> parkDeviceStatus = ParkMethod.getStatusByBoxId(local.getBoxId());
					headPlace.setMethod("parkDeviceStatus");
					ParkDeviceStatusBody statusBody = new ParkDeviceStatusBody();
					statusBody.setuId(ParkMethod.getUUID());
					statusBody.setDeviceStatus(parkDeviceStatus);
					MainBoardMessage<Head, ParkDeviceStatusBody> returnInfoStatus = new MainBoardMessage<>(headPlace,
							statusBody);
					String jsonBodyStatus = JsonUtil.objectToJsonStr(returnInfoStatus);
					MqttMessageVO replyStatus = MqttServiceImpl.sendMessage(equipmentID, replyTopic, jsonBodyStatus,
							null, 3);
				}
			}
		} catch (Exception ex) {
			logger.error("发送车位信息到岗亭:", ex);
		}
		
	}
	/**
	 * 获取车位数 <br>
	 * return 返回数组;0-总车位，1-剩余车位，2-固定车剩余车位，3-临时车剩余车位,4-预约车，5-共享车
	 * 6-临时车，7-月租车，8-储值车，9-免费车
	 * @author 王诚喜
	 * @since JDK 1.8
	 */
	public int[] getPlaceNum() throws Exception {
		Map<String, BigDecimal> placeNum = new HashMap<String, BigDecimal>();
		int [] placeInfo=new int[10];
		placeNum = parkCarInMapper.getBigParkPlaceNumUsing();
		if (placeNum == null) {
			placeInfo[6] = 0; // 临时车
			placeInfo[7] = 0; // 月卡车
			placeInfo[8] = 0; // 储储车
			placeInfo[9] = 0; // 免费车
		} else {
			placeInfo[6] = placeNum.get("TmpCar").intValue(); // 临时车
			placeInfo[7] = placeNum.get("MonthCar").intValue(); // 月卡车
			placeInfo[8] = placeNum.get("MoneyCar").intValue(); // 储储车
			placeInfo[9] = placeNum.get("FreeCar").intValue(); // 免费车
		}
         //总车位数
		int placeNumSum = Integer.parseInt(GlobalPark.properties.getProperty("TOTAL_CARS", "100"));
		//临时车位数
		int tempSum = Integer.parseInt(GlobalPark.properties.getProperty("TEMP_CARS", "100"));
		//月租车位数
		int monSum = Integer.parseInt(GlobalPark.properties.getProperty("FIXED_CARS", "100"));
		placeInfo[0]=placeNumSum;
		switch (Integer.parseInt(GlobalPark.properties.getProperty("CAR_TYPE", "1"))) {
		case 1: // 0混合车位
			placeInfo[1] = placeNumSum - placeInfo[6] - placeInfo[7] - placeInfo[8] - placeInfo[9];
			placeInfo[2] = monSum - placeInfo[7] - placeInfo[8] - placeInfo[9];
			placeInfo[3] = tempSum - placeInfo[6];
			break;
		case 2: // 1临时车位
			placeInfo[1] = placeNumSum - placeInfo[6];
			placeInfo[2] = monSum - placeInfo[7] - placeInfo[8] - placeInfo[9];
			placeInfo[3] = tempSum - placeInfo[6];
			break;
		case 3: // 2固定车位
			placeInfo[1] = placeNumSum - placeInfo[7] - placeInfo[8] - placeInfo[9];
			placeInfo[2] = monSum - placeInfo[7] - placeInfo[8] - placeInfo[9];
			placeInfo[3] = tempSum - placeInfo[6];
			break;
		}
        // 临时车满位判断
        GlobalPark.p_TempFullNum = placeInfo[3] <= 0;
        // 固定车满位判断
        GlobalPark.p_monthFullNum = placeInfo[2] <= 0;
		return placeInfo;
		
	}
}

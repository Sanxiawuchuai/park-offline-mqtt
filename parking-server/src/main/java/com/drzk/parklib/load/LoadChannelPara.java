
package com.drzk.parklib.load;

import com.drzk.common.ParkMethod;
import com.drzk.mapper.VwParkCarIsuseMapper;
import com.drzk.mapper.YktCardIssueRelMapper;
import com.drzk.parklib.send.MainBoardSdk;
import com.drzk.service.entity.*;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.LoggerUntils;
import com.drzk.utils.MyBeanUtils;
import com.drzk.vo.ParkCamSet;
import com.drzk.vo.ParkChannelSet;
import com.drzk.vo.ParkStandardCharge;
import com.drzk.vo.VwParkCarIsuse;
import com.drzk.vo.YktCardIssueRel;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

/**
 * 加载主板参数 <br>
 * Date: 2018年8月3日 上午11:39:01 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class LoadChannelPara {
	private static Logger logger = Logger.getLogger("userLog");
	/** 循环将车场参数加载到主板*/
	public static void loadPara() throws Exception {
		try {
			Vector<ParkChannelSet> channelVector = GlobalPark.parkContset;
			for (ParkChannelSet parkChannelSet : channelVector) {
				String workModel = parkChannelSet.getWorkModel();
				int id = parkChannelSet.getId();
				LoadParkSysBody parkSysBody = new LoadParkSysBody();
				parkSysBody.setTemporaryCarWorkingModel(workModel.substring(0, 1));
				parkSysBody.setMonthlyCarWorkingModel(workModel.substring(1, 2));
				parkSysBody.setStoredValueCarWorkingModel(workModel.substring(3, 4));
				parkSysBody.setVipCarWorkingModel(workModel.substring(2, 3));
				parkSysBody.setEntranceType(parkChannelSet.getInOut().toString());			
				parkSysBody.setIsCarReadCard("0");
				parkSysBody.setNoCarNoPass("0");			
				parkSysBody.setSupplyGateControl(GlobalPark.properties.getProperty("SLU_CONTROL", "0"));
				parkSysBody.setRoadGateInterface(GlobalPark.properties.getProperty("GATE_INTEF", "0"));			
				parkSysBody.setGeteSense("0");			
				parkSysBody.setMainCameraType(GlobalPark.properties.getProperty("CAM_TYPE", "0"));
//			parkSysBody.setViceCameraType("");
//			parkSysBody.setLastCameraType("");			
				ParkCamSet camera = ParkMethod.getCameraSetByChannelId(id);
				if(camera != null && camera.getTouchType() !=null) {
					//主相机触发类型
					parkSysBody.setMainCameraType(camera.getTouchType().toString());
				}		
				parkSysBody.setOutNoCharge("");
				parkSysBody.setChannelType(parkChannelSet.getGateType().toString());
				parkSysBody.setFixedCardWantonly(GlobalPark.properties.getProperty("MORE_INT_MORE_OUT", "0"));
				parkSysBody.setMonthToTempType("");
				parkSysBody.setMonthOverDays("");
				parkSysBody.setStartValidity(GlobalPark.properties.getProperty("START_VALIDITY", "2018-08-01 00:00:00")); // 车场有效开始日期 START_VALIDITY
				parkSysBody.setEndValidity(GlobalPark.properties.getProperty("END_VALIDITY", "2080-08-01 00:00:00")); // 车场有效结束日期
				parkSysBody.setEffectiveDays(GlobalPark.properties.getProperty("EFFECTIVE_DAYS", "0")); // 车场有效天数
				parkSysBody.setFtpIP(GlobalPark.properties.getProperty("FTP_IP", "127.0.0.1")); // ftp服务器访问IP
				parkSysBody.setFtpUser(GlobalPark.properties.getProperty("FTP_USER", "anonymous")); // ftp服务器访问用户名
				parkSysBody.setFtpPassWord(GlobalPark.properties.getProperty("FTP_PWD", "")); // ftp服务器访问密码
				parkSysBody.setParkingNo(GlobalPark.properties.getProperty("PARK_NUM", "X22100300001")); // 车场编号
				
				String equipmentID = parkChannelSet.getDsn();
				if(equipmentID == null || "".equals(equipmentID)) {
					continue;
				}
				MainBoardMessage<ReplyHead, LoadParkSysBodyReturn> reply = MainBoardSdk.sendAndGet(equipmentID, "loadParkSysConfing", parkSysBody, LoadParkSysBodyReturn.class);
				if(reply == null) {
					logger.debug("load systems param fail:" + parkChannelSet.getChannelIp());
					//return;
				}else if(!reply.getHead().getStatus().equals("0")) {
					logger.debug("load systems param fail:" + parkChannelSet.getChannelIp() + "reason：" + reply.getHead().getMessage());
				}
			}
		} catch (Exception e) {
			logger.error("循环将车场参数加载到主板:",e);
		}
	}
	
	/** 循环加载时间到主板*/
	public static void loadSysTime() throws Exception {
		try {
			for (ParkChannelSet parkChannelSet : GlobalPark.parkContset) {
				LoadSysTimeBody body = new LoadSysTimeBody();
				body.setSysTime(new Date());
				String equipmentID = parkChannelSet.getDsn();
				if(equipmentID == null || "".equals(equipmentID)) {
					continue;
				}
				MainBoardMessage<ReplyHead, LoadSysTimeBodyReturn> replyVo = MainBoardSdk.sendAndGet(equipmentID, "loadTime", body,
						LoadSysTimeBodyReturn.class);
				if(replyVo == null || !replyVo.getHead().getStatus().equals("0")) {
					logger.debug("load systems param fail:" + parkChannelSet.getChannelIp());
				}
			}
		} catch (Exception e) {
			logger.error("循环加载时间到主板:",e);
		}
	}
	/** 加载收费标准*/
	public static void loadChargeStandard(List<ParkStandardCharge> parkStandardCharge) {		
		try {
			LoadChargeStandardBody  body = new LoadChargeStandardBody();
			body.setStandardType("1");
			List<ParkStandardChargeBody> chargeData = new ArrayList<ParkStandardChargeBody>();
			for (ParkStandardCharge temp : parkStandardCharge) {
				ParkStandardChargeBody standardChargeBody = new ParkStandardChargeBody();
				MyBeanUtils.copyProperties(temp, standardChargeBody);
				chargeData.add(standardChargeBody);
			}
			//BeanUtils.copyProperties(parkStandardCharge, chargeData);
			body.setChargeData(chargeData);
			
			for (ParkChannelSet parkChannelSet : GlobalPark.parkContset) {
				String equipmentID = parkChannelSet.getDsn();
				MainBoardMessage<ReplyHead, LoadChargeStandardReturnBody> replyVo = MainBoardSdk.sendAndGet(equipmentID, "loadParkChargeStandard", body,
						LoadChargeStandardReturnBody.class);
				if(replyVo == null || !replyVo.getHead().getStatus().equals("0")) {
					logger.debug("load ChargeStandard fail:" + parkChannelSet.getChannelIp());				
				}
			}
		} catch (Exception e) {
			logger.error("加载收费标准:",e);
		}
	}
	
}

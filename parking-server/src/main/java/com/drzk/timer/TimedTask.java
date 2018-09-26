
package com.drzk.timer;


import com.drzk.bean.MqttMessageVO;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.mapper.ParkCarInMapper;
import com.drzk.online.service.OnlineDSScanSever;
import com.drzk.service.entity.Head;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.entity.ParkCarPlaceBody;
import com.drzk.service.entity.ParkDeviceStatusBody;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
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
	private static Logger logger = Logger.getLogger("userLog");
	@Autowired
	private ParkCarInMapper parkCarInMapper;
	@Autowired
	OnlineDSScanSever onlineDSScanSever;
	
	
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
	public void refreshData()
	{
	    try {
	    	int[] placeNum=getPlaceNum(); //获取车位数
	    	sendCarPalceToBox(placeNum); //给岗亭推车位
	    }catch (Exception e) {
			LoggerUntils.error(logger, e);
		}
	}
	
	//实时更新各个设备的在线状态
//	@Scheduled(fixedDelay = 9000)
	public void refreshDeviceStatus() {
		try {
			for (ParkDeviceStatus deviceStatus : GlobalPark.parkDeviceStatus) {
				Date lastUpdateTiem = deviceStatus.getLastUpdateTime();
				long now = new Date().getTime();
				long lastUpdate = lastUpdateTiem.getTime();
				if ((now - lastUpdate) > 9 * 1000) {
					String ip = deviceStatus.getMacIp();
					ParkChannelSet channel = ParkMethod.getChannelSetByIp(ip);
					channel.setOnline(false);
				}
			}
		} catch (Exception ex) {
			LoggerUntils.error(logger, ex);
		}
	}

	final long sencond =10*60*1000;
	
//	@Scheduled(fixedDelay =  sencond)
	public void pushDBoxinfo()
	{
		boolean flag = false;
		if(flag) {
			onlineDSScanSever.boxinfo();//OK
		}
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushControllerinfo()
	{
		onlineDSScanSever.controllerinfo();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkcameras()
	{
		onlineDSScanSever.parkcameras();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkcarin()
	{
		onlineDSScanSever.parkcarin();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushparkunusualcarin()
	{
		onlineDSScanSever.parkunusualcarin();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkcarout()
	{
		onlineDSScanSever.parkcarout();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkdeletecarin()
	{
		onlineDSScanSever.parkdeletecarin();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkopengateillegally()
	{
		onlineDSScanSever.parkopengateillegally();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkcenterpayment()
	{
		onlineDSScanSever.parkcenterpayment();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkblackcar()
	{
		onlineDSScanSever.parkblackcar();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushCouponeqinfo()
	{
		onlineDSScanSever.couponeqinfo();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushCouponrule()
	{
		onlineDSScanSever.couponrule();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushCouponuserlist()
	{
		onlineDSScanSever.couponuserlist();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParksumusers()
	{
		onlineDSScanSever.parksumusers();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkmonthlyfeetype()
	{
		onlineDSScanSever.parkmonthlyfeetype();//OK
		
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkcompertask()
	{
		onlineDSScanSever.parkcompertask();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushPersonnelinfo()
	{
		onlineDSScanSever.personnelinfo();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParksetting()
	{
		onlineDSScanSever.parksetting();//OK
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushFeescale()
	{
		onlineDSScanSever.feescale();//OK
		
	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushParkcaruser()
	{
		onlineDSScanSever.parkcaruser();//OK
	}
//	//@Scheduled(fixedDelay =  sencond)
//	public void pushParkcaroperation()
//	{
//		onlineDSScanSever.parkcaroperation();//OK
//	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushDepartmentinfo()
	{
		onlineDSScanSever.departmentinfo();//OK

	}
	//@Scheduled(fixedDelay =  sencond)
	public void pushCompanyinfo() {
		onlineDSScanSever.companyinfo();//OK
	}

	//@Scheduled(fixedDelay =  sencond)
	public void pushCarGroupInfo(){
		onlineDSScanSever.carGroupInfo();
	}
	
	/**
	 * 发送车位信息到岗亭 <br>
	 * @author 王诚喜
	 * @since JDK 1.8
	 */
	public void sendCarPalceToBox (int[] placeNum) throws Exception
	{
		//0-总车位，1-剩余车位，2-固定车剩余车位，3-临时车剩余车位,4-预约车，5-共享车
		 //* 6-临时车，7-月租车，8-储值车，9-免费车
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
    	for(ParkLocalSet local : GlobalPark.parkLocalSet)
		{
			if( local.getOnline()!= null&& local.getOnline()==1 && local.getEquipmentID()!= null)
			{
				//实时车位
				MainBoardMessage<Head,ParkCarPlaceBody> returnInfo = new MainBoardMessage<>(headPlace,bodyPlace);
				String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
				String equipmentID = local.getEquipmentID();
				String replyTopic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
				MqttMessageVO reply =MqttServiceImpl.sendMessage(equipmentID,replyTopic, jsonBody, null, 3);
				
				//实时设备状态推送给岗亭
				List<ParkDeviceStatus> parkDeviceStatus = ParkMethod.getStatusByBoxId(local.getBoxId());
				headPlace.setMethod("parkDeviceStatus");
				ParkDeviceStatusBody statusBody = new ParkDeviceStatusBody();
				statusBody.setDeviceStatus(parkDeviceStatus);
				MainBoardMessage<Head,ParkDeviceStatusBody> returnInfoStatus = new MainBoardMessage<>(headPlace,statusBody);
				String jsonBodyStatus = JsonUtil.objectToJsonStr(returnInfoStatus);			
				MqttMessageVO replyStatus = MqttServiceImpl.sendMessage(equipmentID,replyTopic, jsonBodyStatus, null, 3);
			}	 
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
		if (placeNum == null)
		{
			placeInfo[6] = 0; // 临时车
			placeInfo[7] = 0; // 月卡车
			placeInfo[8] = 0; // 储储车
			placeInfo[9] = 0; // 免费车
		}
		else {
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


package com.drzk.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.drzk.fact.InRealTimeBase;
import com.drzk.service.entity.LoadParkSysBody;
import com.drzk.vo.ParkStandardCharge;

/**
 * ClassName:ParkMethodTest <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年5月25日 下午4:48:56 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class ParkMethodTest {

	public static void main(String[] args) {
//		 "temporaryCarWorkingModel" :"0"   有          --时租车工作模式 0-自动，1-确认 2-受控
//	     "monthlyCarWorkingModel" :"0"   有            --月租车工作模式 0-自动，1-确认 2-受控
//	     "storedValueCarWorkingModel" :"0"  有         --储值车工作模式 0-自动，1-确认  2-受控
//	     "vipCarWorkingModel" :"0"     有              --免费车工作模式 0-自动， 1-确认 2-受控
//	     "entranceType" :"0"       有                  --出入口类型 0-标准入口，1-标准出口，2-嵌套入口，3-嵌套出口，4-半嵌套入口，5-半嵌套出口，6-双向通行口
//	     "isCarReadCard" :"0"    无                    --有车读卡 0 禁止,1,允许出入场
//	     "noCarNoPass" :"0"     无                     --无牌车通行 0,软件处理 1，扫码,2 取卡，3 取票
//	     "supplyGateControl" :"0"   有                 --补闸控制 0-禁止，1-补开闸，2-补关闸，3-补开关闸
//	     "roadGateInterface" :"0"   有                 --道闸接口 0-电平，1-485接口，2-CAN接口
//	     "geteSense" :"0"       无                     --道闸地感 0-禁止 1使能
//	     "mainCameraType" :"0"  共用系统参数表相机类型                 --主摄像机类型 0-无，1-大华，2-臻识，3-海康，4-华夏智信，5-信路威
//	     "mainCameraTrigger" :"0"   无           --主摄像机触发类型 0，地感触发 1,视频流触发 2,混合触发
//	     "viceCameraType" :"0"       共用系统参数表相机类型                 --副摄像机类型 0-无，1-大华，2-臻识，3-海康，4-华夏智信，5-信路威
//	     "viceCameraTrigger" :"0"    无                 --副摄像机触发类型 0，地感触发 1,视频流触发 2,混合触发
//	     "lastCameraType" :"0"      共用系统参数表相机类型                  --尾摄像机类型 0-无，1-大华，2-臻识，3-海康，4-华夏智信，5-信路威
//	    "lastCameraTrigger" :"0"   无                  --尾摄像机触发类型 0，地感触发 1,视频流触发 2,混合触发
//	     "outNoCharge" :"0"    park_channel_set(no_money)   --禁止收费 0可能收费，1 不可收费
//	     "channelType" :"0"   有   --通道类型 0-综合通道，1-固定车道 2-临时通道
//	     "fixedCardWantonly" :"0"  MORE_INT_MORE_OUT  --固定卡多进多出 0 禁止，1允许
//	     "monthToTempType" :"0"     --月租车转临时车 0-禁止，1-4对应临时卡A-D，9-转成对应卡类
//	     "monthOverDays" :"0"    --仅当月租车转临时车为禁止时有效，即配置为转临则按临时车处理
//	     "startValidity " :"2018-01-02 00:00:00" 无    --车场有效开始日期
//	     "endValidity " :"2018-01-02 00:00:00"  无     --车场有效结束日期
//	     "effectiveDays  " :"3650"                   --车场有效天数
//	     "ftpUser":"3650"          无                  --ftp服务器访问用户名
//	     "ftpPassWord":"3650"       无                 --ftp服务器访问用户名
//	     "parkingNo":"12547744"   有(通道表和系统参数表都有，以哪个为准)              --车场编号
		
		LoadParkSysBody parkSysBody = new LoadParkSysBody();
		
		
		parkSysBody.setTemporaryCarWorkingModel("");
		parkSysBody.setMonthlyCarWorkingModel("");
		parkSysBody.setStoredValueCarWorkingModel("");
		parkSysBody.setVipCarWorkingModel("");
		parkSysBody.setEntranceType("");
		parkSysBody.setIsCarReadCard("");
		parkSysBody.setNoCarNoPass("");
		parkSysBody.setSupplyGateControl("");
		parkSysBody.setRoadGateInterface("");
		parkSysBody.setGeteSense("");
		parkSysBody.setMainCameraType("");
		parkSysBody.setViceCameraType("");
		parkSysBody.setLastCameraType("");
		parkSysBody.setOutNoCharge("");
		parkSysBody.setChannelType("");
		parkSysBody.setFixedCardWantonly("");
		parkSysBody.setMonthToTempType("");
		parkSysBody.setMonthOverDays("");
		parkSysBody.setStartValidity(""); // 车场有效开始日期
		parkSysBody.setEndValidity(""); // 车场有效结束日期
		parkSysBody.setEffectiveDays(""); // 车场有效天数
		parkSysBody.setFtpIP(""); // ftp服务器访问IP
		parkSysBody.setFtpUser(""); // ftp服务器访问用户名
		parkSysBody.setFtpPassWord(""); // ftp服务器访问用户名
		parkSysBody.setParkingNo(""); // 车场编号
		
		
	}


}

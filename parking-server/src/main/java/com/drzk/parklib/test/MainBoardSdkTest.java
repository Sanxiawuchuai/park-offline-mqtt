
package com.drzk.parklib.test;

import com.drzk.parklib.send.MainBoardSdk;
import com.drzk.service.entity.*;
import com.drzk.utils.JsonUtil;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName:MainBoardSdkTest <br>
 * Date: 2018年7月5日 上午8:58:59 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class MainBoardSdkTest {

	public static String parkingNo = "X11403100001";

	public static void test(String methodName) {
		Class<MainBoardSdkTest> clazz = MainBoardSdkTest.class;
		try {
			Method method = clazz.getMethod(methodName, null);
			method.invoke(null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	/** 广播 */
//	public static void broadcastEquipmentInfo() {
//		Head head = new Head();
//		head.setMethod("broadcastEquipmentInfo");
//		head.setReplyTopic("P0089118/aysnReply/data");
//		head.setVersion("v1");
//
//		BroadcastReadDeviceMsgBody body = new BroadcastReadDeviceMsgBody();
//		body.setuId("1251710147441114444444444");
//		MainBoardMessage<Head, BroadcastReadDeviceMsgBody> vo = new MainBoardMessage<>(head, body);
//		// 将发送的对象转换成json
//		String jsonBody = JsonUtil.objectToJsonStr(vo);
//
//		MqttMessageVO reply = MqttServiceImpl.sendMessage("broadcast/controller", jsonBody, "P0089118/aysnReply/data",
//				3);
//		if (reply.getStatus() == 0) {
//			System.out.println("失败");
//		}
//		String replyjson = reply.getBody();
//		MainBoardMessage<ReplyHead, BroadcastReadDeviceMsgReturnBody> replyVo = JsonUtil.jsonToBoardMessage(replyjson,
//				ReplyHead.class, BroadcastReadDeviceMsgReturnBody.class);
//		String result = JsonUtil.objectToJsonStr(replyVo);
//		System.out.println(result);
//	}

	/** 加载时间 */
	public static void loadTime() {
		String equipmentID = "202";
		String method = "loadTime";

		LoadSysTimeBody body = new LoadSysTimeBody();
		body.setSysTime(new Date());

		MainBoardMessage<ReplyHead, LoadSysTimeBodyReturn> replyVo = MainBoardSdk.sendAndGet(equipmentID, method, body,
				LoadSysTimeBodyReturn.class);

		String result = JsonUtil.objectToJsonStr(replyVo);
		System.out.println(result);
	}

	/** 获取时间 */
	public static void getTime() {
		String equipmentID = "202";
		String method = "getTime";
		ReadSysTimeBody body = new ReadSysTimeBody();
		MainBoardMessage<ReplyHead, ReadSysTimeBodyReturn> replyVo = MainBoardSdk.sendAndGet(equipmentID, method, body,
				ReadSysTimeBodyReturn.class);
		String result = JsonUtil.objectToJsonStr(replyVo);
		System.out.println(result);
	}
	
	/**读取网络参数*/
	public static void getNetParameter() {
		String equipmentID = "202";
		String method = "getNetParameter";
		ReadNetWorkBody body = new ReadNetWorkBody();
		
		MainBoardMessage<ReplyHead, ReadNetWorkBodyReturn> replyVo = MainBoardSdk.sendAndGet(equipmentID, method, body,
				ReadNetWorkBodyReturn.class);
		
		String result = JsonUtil.objectToJsonStr(replyVo);
		System.out.println(result);
	}
	
	/** 显示输出*/
	public static void displayOutPut() {
		String equipmentID = "202";
		String method = "displayOutPut";
		//long start = new Date().getTime();
		DisplayBody body  = new DisplayBody();
		List<DisplayChar> displayChar = new ArrayList<>();
		DisplayChar a = new DisplayChar();
		a.setVoiceType("25");
		
		DisplayChar b = new DisplayChar();
		b.setVoiceType("81");
		b.setDisValue("粤C54120");
		
		DisplayChar c = new DisplayChar();
		c.setVoiceType("01");
		
		displayChar.add(a);
		displayChar.add(b);
		displayChar.add(c);
		
		body.setDisplayChar(displayChar);
		MainBoardMessage<ReplyHead, DisplayBodyReturn> replyVo = MainBoardSdk.sendAndGet(equipmentID, method, body,
				DisplayBodyReturn.class);
		
		String result = JsonUtil.objectToJsonStr(replyVo);
		System.out.println(result);
	}
	
	/** 道闸控制*/
	public static void roadGateControl() {
		String equipmentID = "202";
		String method = "roadGateControl";
		
		GateBody body = new GateBody();
		body.setOrder("1");
		
		MainBoardMessage<ReplyHead, GateBodyReturn> replyVo = MainBoardSdk.sendAndGet(equipmentID, method, body,
				GateBodyReturn.class);
		
		String result = JsonUtil.objectToJsonStr(replyVo);
		System.out.println(result);
	}
	
	/** 显示屏参数*/
	public static void parkingDisParameter() {
		
		DisplayParamBody body = new DisplayParamBody();
		Advertisement advertisement = new Advertisement();
		advertisement.setAdvertisementDisplay("拼多多");
		advertisement.seteDate("2018-10-02 11:11:10");
		advertisement.setIsValid("1");
		advertisement.setLineNumber("1");
		advertisement.setsDate("2018-07-02 11:11:10");
		
		body.setAdvertisement(advertisement);
		body.setCarPlaceType("01");
		body.setDisPalyType("0");
		body.setFreeTimeType("01");
		body.setInVoiceType("01");
		body.setOutVoiceType("2");
		body.setTimeType("0");
		
		System.out.println(JsonUtil.objectToJsonStr(body));
		
		String equipmentID = "202";
		String method = "parkingDisParameter";
		
		MainBoardMessage<ReplyHead, DisplayParamBodyReturn> replyVo = MainBoardSdk.sendAndGet(equipmentID, method, body,
				DisplayParamBodyReturn.class);
		
		String result = JsonUtil.objectToJsonStr(replyVo);
		System.out.println(result);
		
	}
	
	/** 加载车场系统参数*/
	public static void loadParkSysConfing() {
		LoadParkParamBody body = new LoadParkParamBody();
		
		String equipmentID = "2";
		String method = "loadParkSysConfing";
		
		body.setTemporaryCarWorkingModel("2");
		body.setMonthlyCarWorkingModel("2");
		body.setVipCarWorkingModel("2");
		body.setEntranceType("0");
		
		body.setParkingNo("P0089228");
		MainBoardMessage<ReplyHead, LoadParkParamReturnBody> replyVo = MainBoardSdk.sendAndGet(equipmentID, method, body,
				LoadParkParamReturnBody.class);
		
		String result = JsonUtil.objectToJsonStr(replyVo);
		System.out.println(result);
		
	}
	
	/** 读取车场系统参数*/
	public static void getParkSysConfing() {
		ReadParkParamBody body = new ReadParkParamBody();
		
		String equipmentID = "202";
		String method = "getParkSysConfing";
		
		MainBoardMessage<ReplyHead, ReadParkParamReturnBody> replyVo = MainBoardSdk.sendAndGet(equipmentID, method, body,
				ReadParkParamReturnBody.class);
		
		String result = JsonUtil.objectToJsonStr(replyVo);
		System.out.println(result);
	}
	
//	public static void captureMiniPhotoJpeg() {
//		HandCapBody body = new HandCapBody();
//		body.setCamIP("192.168.16.202");
//		Head head = new Head();
//		head.setMethod("captureMiniPhotoJpeg");
//		String topic = String.format(TopicsDefine.DOWN_SEND, 202);
//
//		MainBoardMessage<Head, HandCapBody> returnInfo = new MainBoardMessage<>(head, body);
//
//		String replayTopic = String.format(TopicsDefine.DOWN_RECIVE, parkingNo);
//		head.setReplyTopic(replayTopic);
//		String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
//		MqttMessageVO reply = MqttServiceImpl.sendMessage(topic, jsonBody, replayTopic, 3);
//		System.out.println(reply.getBody());
//	}
	
	public static void main(String[] args) {
		//captureMiniPhotoJpeg();
	}
	

}


package com.drzk.parklib.send;

import com.drzk.bean.MqttMessageVO;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.service.entity.*;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.StringUtils;
import com.drzk.vo.ParkChannelSet;

import java.util.Date;

/**
 * 和主板通信的mqtt协议封装<br>
 * Date: 2018年7月2日 上午9:33:09 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public class MainBoardSdk {

	// 加载系统时间
	public boolean loadTime(String equipmentID, Date time) {
		String topic = String.format(TopicsDefine.DOWN_SEND, equipmentID);
		Head head = new Head();
		head.setMethod("loadTime");
		String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
		String replyTopic = String.format(TopicsDefine.DOWN_RECIVE, parkNo);
		head.setReplyTopic(replyTopic);

		LoadSysTimeBody body = new LoadSysTimeBody();
		body.setSysTime(time);

		MainBoardMessage<Head, LoadSysTimeBody> loadTime = new MainBoardMessage<>(head, body);

		String jsonBody = JsonUtil.objectToJsonStr(loadTime);

		MqttMessageVO reply = MqttServiceImpl.sendMessage(equipmentID,topic, jsonBody, replyTopic, 3);
		if (reply.getStatus() == 0) {
			return false;
		}
		String replyjson = reply.getBody();
		MainBoardMessage<ReplyHead, LoadSysTimeBodyReturn> replyVo = JsonUtil.jsonToBoardMessage(replyjson,
				ReplyHead.class, LoadSysTimeBodyReturn.class);
		String loadTimeResult = replyVo.getHead().getStatus();
        return !"0".equals(loadTimeResult);
		
	}

	// 获取系统时间
	public static Date getTime(String equipmentID) {
		String topic = String.format(TopicsDefine.DOWN_SEND, equipmentID);
		Head head = new Head();
		head.setMethod("");
		return null;
	}

	/**
	 * 与主板通信发送的统一入口<br>
	 * 
	 * @author chenlong
	 * @param equipmentID 设备编号
	 * @param method mqtt协议中定义的method
	 * @param sendVo 需要发送的对象
	 * @param rclazz  接收信息的对象
	 * @return
	 * @since JDK 1.8
	 */
	public static <S, R> MainBoardMessage<ReplyHead, R> sendAndGet(String equipmentID, String method, S sendVo,
			Class<R> rclazz) {
		
		ParkChannelSet channnel = ParkMethod.getChannelSetByEquipmentID(equipmentID);
		if(channnel == null || !channnel.isOnline()) {
			return null;
		}
		//参数检查
		if(StringUtils.isNullOrEempty(equipmentID) || StringUtils.isNullOrEempty(method)) {
			return null;
		}
		if(sendVo == null || rclazz == null) {
			return null;
		}
		
		// 发送的主题
		String topic = String.format(TopicsDefine.DOWN_SEND, equipmentID);
		// 组装发送的头部
		Head head = new Head();
		head.setMethod(method);
		String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
		String replyTopic = String.format(TopicsDefine.DOWN_RECIVE, parkNo);
		head.setReplyTopic(replyTopic);
		
		//sendVo.setuId(ParkMethod.getUUID());
		// 发送的对象
		MainBoardMessage<Head, S> send = new MainBoardMessage<>(head, sendVo);

		// 将发送的对象转换成json
		String jsonBody = JsonUtil.objectToJsonStr(send);
		
		MqttMessageVO reply = MqttServiceImpl.sendMessage(equipmentID,topic, jsonBody, replyTopic, 3);
		
		
		if (reply.getStatus() == 0) {
			return null;
		}
		String replyjson = reply.getBody();
		MainBoardMessage<ReplyHead, R> replyVo = JsonUtil.jsonToBoardMessage(replyjson, ReplyHead.class, rclazz);
		return replyVo;

	}
}

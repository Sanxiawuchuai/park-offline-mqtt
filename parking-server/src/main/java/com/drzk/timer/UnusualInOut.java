package com.drzk.timer;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.drzk.bean.MqttMessageVO;
import com.drzk.common.InOutRealTimeBase;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.mapper.BackUpParkCarInMapper;
import com.drzk.mapper.ParkCarInMapper;
import com.drzk.mapper.ParkCarOutMapper;
import com.drzk.mapper.VwParkCarIsuseMapper;
import com.drzk.service.entity.Head;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.entity.ParkCarInRecordBody;
import com.drzk.service.entity.ParkCarOutRecordBody;
import com.drzk.service.entity.UpRecordBody;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.service.impl.ParkingInServiceImpl;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
import com.drzk.vo.BackUpParkCarIn;
import com.drzk.vo.ParkCarIn;
import com.drzk.vo.ParkCarOut;
import com.drzk.vo.ParkChannelSet;
import com.drzk.vo.ParkLocalSet;
import com.drzk.vo.VwParkCarIsuse;

/**
 * UnusualInOut <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年09月19日 上午9:43:04 <br>
 * 异常开闸记录
 * @author wangchenxi
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
@Scope("prototype")
public class UnusualInOut implements Runnable {
	private static Logger logger = Logger.getLogger("userLog");
	@Autowired
	private ParkCarInMapper parkCarInMapper;
	@Autowired
	private ParkCarOutMapper parkCarOutMapper;
	@Autowired
	ParkingInServiceImpl parking;
	
	// 通道 相关设置
	private ParkChannelSet channel;
	//硬件上传
	private UpRecordBody body;
	
	public ParkChannelSet getChannel() {
		return channel;
	}

	public void setChannel(ParkChannelSet channel) {
		this.channel = channel;
	}

	public UpRecordBody getBody() {
		return body;
	}

	public void setBody(UpRecordBody body) {
		this.body = body;
	}

	@Override
	public void run() {
		try {
			logger.debug("UnusualInOut:" + body.getCarNo());
			int inOut = 0; //出入类型  0-入；1-出
			int small = ParkMethod.getSmallOrBig(channel);// 获取大小车场
			switch (channel.getInOut()) //出入类型（0标准入口，1标准出口，2标准嵌套入口，3标准嵌套出口、4半嵌套入口，5半嵌套出口、6单通道）
            {
                case 0: //标准入口
                	inOut = 0;
                    break;
                case 1: //标准出口
                	inOut = 1;
                    break;
                case 2: //标准嵌套入口
                	inOut = 0;
                    break;
                case 3: //标准嵌套出口
                	inOut = 1;
                    break;
                case 4:  //半嵌套入口
                	inOut = 0;
                    break;
                case 5://半嵌套出口
                	inOut = 1;
                    break;
                case 6: //单通道入
                	inOut = 0;
                    break;
                case 7:   //单通首出
                	inOut = 1;
                    break;
            }
			if(inOut == 0)//入口
			{
				ParkCarOut carOut = new ParkCarOut();
				ParkCarIn carIn = new ParkCarIn();
				carIn.setYktId(0);
				carIn.setCardType(InOutRealTimeBase.HAND_OPEN_GATE);
				carIn.setEmpName("临时用户");
				carIn.setMachNo(channel.getMachNo());
				carIn.setCarNo(body.getCarNo());
				carIn.setCardId(ParkMethod.CarNotoCardID(body.getCarNo()));
				carIn.setInTime(body.getInTime());
				carIn.setInPic(body.getInPic());
				carIn.setSmallPic(body.getSmallInPic());
				carIn.setInWay(InOutRealTimeBase.IN_OUT_ABNORMALOPENING);
				carIn.setSmall( small); // 设置大小车场
				carIn.setGuid(ParkMethod.getUUID());
				carIn.setcFlag(4);
				carIn.setCarNoType(body.getCarColorType()==null?0:Integer.parseInt(body.getCarColorType()));
				carIn.setInUserName(ParkMethod.getLoginName(channel.getBoxId()));
				
				carOut.setOutWay(0);
				carOut.setGuid(ParkMethod.getUUID());
				carOut.setInUserName(ParkMethod.getLoginName(channel.getBoxId()));
				carOut.setOutUserName(ParkMethod.getLoginName(channel.getBoxId()));//出场操作员
				carOut.setCardType(InOutRealTimeBase.HAND_OPEN_GATE); //卡类型
				carOut.setInMachNo(channel.getMachNo());
				carOut.setOutMachNo(channel.getMachNo());
				carOut.setInCarNo(body.getCarNo());
				carOut.setOutCarNo(body.getCarNo());
				carOut.setOutPic(body.getOutPic());
				carOut.setSmallOutPic(body.getSmallOutPic());
				carOut.setcFlag(4);
				carOut.setInTime(body.getInTime());
				carOut.setOutTime(body.getInTime());
				carOut.setAccountCharge(0f);
				carOut.setPayCharge(0f);
				carOut.setDisAmount(0f);
				carOut.setBalanceMoney(0f);
				carOut.setFreeType(0);
				int row=parkCarOutMapper.insert(carOut);
				if (row > 0) {
					sendParkCarInRecordToBox(carIn); //推送入场记录
					parking.pushInData(channel, carIn);
					parking.inStatisticsRefresh(carIn);
					logger.debug("保存入口异常开闸记录成功");
				}
				else
					logger.debug("保存入口异常开闸记录失败");
			}else
			{
				ParkCarOut carOut = new ParkCarOut();				
				carOut.setOutWay(0);
				carOut.setGuid(ParkMethod.getUUID());
				carOut.setInUserName(ParkMethod.getLoginName(channel.getBoxId()));
				carOut.setOutUserName(ParkMethod.getLoginName(channel.getBoxId()));//出场操作员
				carOut.setCardType(InOutRealTimeBase.HAND_OPEN_GATE); //卡类型
				carOut.setInMachNo(channel.getMachNo());
				carOut.setOutMachNo(channel.getMachNo());
				carOut.setInCarNo(body.getCarNo());
				carOut.setOutCarNo(body.getCarNo());
				carOut.setOutPic(body.getOutPic());
				carOut.setSmallOutPic(body.getSmallOutPic());
				carOut.setcFlag(4);
				carOut.setInTime(body.getOutTime());
				carOut.setOutTime(body.getOutTime());
				carOut.setAccountCharge(0f);
				carOut.setPayCharge(0f);
				carOut.setDisAmount(0f);
				carOut.setBalanceMoney(0f);
				carOut.setFreeType(0);
				int row=parkCarOutMapper.insert(carOut);
				if (row > 0) {
					sendParkCarOutRecordToBox(carOut); //推送出场记录
					parking.pushOutData(channel, carOut);
					parking.outStatisticsRefresh(carOut);
					logger.debug("保存出口异常开闸记录成功");
				}
				else
					logger.debug("保存出口异常开闸记录失败");
			}
		}catch (Exception ex) {
			logger.error("异常开闸数据:", ex);
		}
	}
	
	/**
	 * 
	 * 主板上传事件后,由此函数处理<br>
	 * 
	 * @author chenlong
	 * @param in
	 * @since JDK 1.8
	 */
	private void sendParkCarInRecordToBox(ParkCarIn in)
	{
		try
		{
			Head head = new Head();
			head.setMethod("parkCarInRecord");
			ParkCarInRecordBody body = new ParkCarInRecordBody();
			body.setInRecord(in);
			MainBoardMessage<Head,ParkCarInRecordBody> returnInfo = new MainBoardMessage<>(head,body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			for(ParkLocalSet local:GlobalPark.parkLocalSet)
			{
				if(local.getBoxId()==channel.getBoxId())
				{
					if(local != null&& local.getOnline() != null && local.getOnline()==1)
					{
						String topic = String.format(TopicsDefine.BOX_ERROR, local.getEquipmentID());
						MqttMessageVO retVo = MqttServiceImpl.sendMessage(local.getEquipmentID(),topic, jsonBody, null, 0);
						break;
					}
				}
			}
		}
		catch(Exception ex)
		{
			logger.error("推送入场数据:", ex);
		}
	}
	
	/**
	 * 
	 * 推送出场记录 <br>
	 *
	 * @author chenlong
	 * @param
	 * @return
	 * @since JDK 1.8
	 */
	public void sendParkCarOutRecordToBox(ParkCarOut carOut)
	{
		try
		{
			Head head = new Head();
			head.setMethod("parkCarOutRecord");
			ParkCarOutRecordBody body = new ParkCarOutRecordBody();
			body.setOutRecord(carOut);
			if(channel !=null)
			{
				carOut.setOutMachNo(channel.getMachNo());
				ParkLocalSet local = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
				if(local != null&& local.getOnline() != null && local.getOnline()==1)
				{
					MainBoardMessage<Head,ParkCarOutRecordBody> returnInfo = new MainBoardMessage<>(head,body);
					String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
					String equipmentID = local.getEquipmentID();
					String replyTopic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
					MqttMessageVO reply = MqttServiceImpl.sendMessage(equipmentID,replyTopic, jsonBody, null, 0);
					logger.debug("异常开闸记录推送:"+replyTopic+","+jsonBody);
				}
			}
		}
		catch(Exception ex)
		{
			logger.error("推送出场数据:", ex);
		}
	}
}

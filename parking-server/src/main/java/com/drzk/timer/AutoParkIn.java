package com.drzk.timer;

import com.drzk.bean.MqttMessageVO;
import com.drzk.common.InOutRealTimeBase;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.mapper.BackUpParkCarInMapper;
import com.drzk.mapper.ParkCarInMapper;
import com.drzk.mapper.VwParkCarIsuseMapper;
import com.drzk.service.entity.Head;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.entity.ParkCarInRecordBody;
import com.drzk.service.entity.UpRecordBody;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.service.impl.ParkingInServiceImpl;
import com.drzk.utils.BeanCopierUtil;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
import com.drzk.vo.BackUpParkCarIn;
import com.drzk.vo.ParkCarIn;
import com.drzk.vo.ParkChannelSet;
import com.drzk.vo.ParkLocalSet;
import com.drzk.vo.VwParkCarIsuse;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * AutoParkIn <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年6月14日 上午9:43:04 <br>
 * 自动模式下的情况下,处理硬件直接传过来的入场记录
 * @author wangchenxi
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
@Scope("prototype")
public class AutoParkIn implements Runnable {
	private static Logger logger = Logger.getLogger("userLog");
	@Autowired
	private ParkCarInMapper parkCarInMapper;
	@Autowired
	VwParkCarIsuseMapper vwParkCarIsuseMapper;
	@Autowired
	ParkingInServiceImpl parking;
	// 通道 相关设置
	private ParkChannelSet channel;
	
	@Autowired
	private BackUpParkCarInMapper backUpParkCarInMapper;

	public void setChannel(ParkChannelSet channel) {
		this.channel = channel;
	}
	public void setBody(UpRecordBody body) {
		this.body = body;
	}
	private UpRecordBody body;
	
	@Override
	public void run() {
		try {
			logger.debug("AutoParkIn:" + body.getCarNo());
			int small = ParkMethod.getSmallOrBig(channel);// 获取大小车场

			ParkCarIn carIn = new ParkCarIn();
			carIn.setYktId(0);
			carIn.setCardType(InOutRealTimeBase.TEMP_CAR_A);
			carIn.setEmpName("临时用户");
			carIn.setMachNo(channel.getMachNo());
			carIn.setCarNo(body.getCarNo());
			carIn.setCardId(ParkMethod.CarNotoCardID(body.getCarNo()));
			carIn.setInTime(body.getInTime());
			carIn.setInPic(body.getInPic());
			carIn.setSmallPic(body.getSmallInPic());
			carIn.setInWay(InOutRealTimeBase.IN_OUT_NORMAL);
			carIn.setSmall( small); // 设置大小车场
			carIn.setGuid(ParkMethod.getUUID());
			carIn.setcFlag( 4);
			carIn.setCarNoType(body.getCarColorType()==null?0:Integer.parseInt(body.getCarColorType()));
			carIn.setInUserName(ParkMethod.getLoginName(channel.getBoxId()));
			VwParkCarIsuse model = parking.getVwParkCarIsuse(carIn.getCarNo());
			if (model != null) { // 固定车
				carIn.setYktId(model.getYktId());
				carIn.setEmpName(model.getPerName());
				carIn.setCardType(model.getCardType());
				carIn.setCarNo(model.getCarNo());
			} else {
				carIn.setYktId(0);
				carIn.setEmpName("临时用户");
				carIn.setCardType(InOutRealTimeBase.TEMP_CAR_A);
			}
			// 判断是否存在记录
			ParkCarIn condition = new ParkCarIn();
			String rCarNo = body.getCarNo().trim();
			condition.setCarNo(rCarNo);
			condition.setSmall(small);
			List<ParkCarIn> modelGet = parkCarInMapper.selectByCondition(condition);
			if (modelGet != null && modelGet.size() > 0) { // 存在入场记录
				if (body.getInTime().before(modelGet.get(0).getInTime()))
					return; // 当前记录的入场时间比数据时间早，不需保存
				parkCarInMapper.deleteByCondition(modelGet.get(0));
				BackUpParkCarIn backUpIn = new BackUpParkCarIn();
				BeanCopierUtil.copyProperties(modelGet, backUpIn);
				backUpIn.setId(null);
				backUpParkCarInMapper.insert(backUpIn);
			}
			int row = parkCarInMapper.insert(carIn); // 保存记录
			if (row > 0) {
				sendParkCarInRecordToBox(carIn); //推送入场记录
				parking.pushInData(channel, carIn);
				parking.inStatisticsRefresh(carIn);
				logger.debug("入场记录成功!");
			}
			else
				logger.debug("入场记录失败!");
		} catch (Exception ex) {
			logger.error("硬件直接传过来的入场记录:", ex);
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
						logger.debug("入场记录推送:"+topic+","+jsonBody);
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
}

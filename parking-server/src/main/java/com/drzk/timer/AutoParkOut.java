package com.drzk.timer;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.drzk.bean.MqttMessageVO;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.mapper.BackUpParkCarInMapper;
import com.drzk.mapper.ParkCarInMapper;
import com.drzk.mapper.ParkCarOutMapper;
import com.drzk.mapper.VwParkCarIsuseMapper;
import com.drzk.service.entity.Head;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.entity.ParkCarOutRecordBody;
import com.drzk.service.entity.UpRecordBody;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.service.impl.ParkingInServiceImpl;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.LoggerUntils;
import com.drzk.vo.BackUpParkCarIn;
import com.drzk.vo.ParkCarIn;
import com.drzk.vo.ParkCarOut;
import com.drzk.vo.ParkChannelSet;
import com.drzk.vo.ParkLocalSet;
import com.drzk.vo.VwParkCarIsuse;

/**
 * AutoParkIn <br>
 * Function: TODO ADD FUNCTION. <br>
 * Reason: TODO ADD REASON. <br>
 * Date: 2018年6月14日 上午9:43:04 <br>
 * 自动模式下的情况下,处理硬件直接传过来的出场记录 
 * 暂时不做储值车,硬件有存储车线上处理
 * @author wangchenxi
 * @version
 * @since JDK 1.8
 * @see
 */
@Component
@Scope("singleton")
public class AutoParkOut implements Runnable {
	private static Logger logger = Logger.getLogger("userLog");
	@Autowired
	private ParkCarInMapper parkCarInMapper;
	@Autowired
	VwParkCarIsuseMapper vwParkCarIsuseMapper;
	@Autowired
	private BackUpParkCarInMapper backUpParkCarInMapper;
	@Autowired
	ParkingInServiceImpl parking;
	
	@Autowired
	private ParkCarOutMapper parkCarOutMapper;
	// 通道 相关设置
	private ParkChannelSet channel;

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
			logger.debug("AutoParkOut:" + body.getCarNo());
			int inChannelType = 0;// 入口类型
            int outChannelType = 0; //出口类型
			ParkCarOut carOut = new ParkCarOut();
			int small = ParkMethod.getSmallOrBig(channel);// 获取大小车场
			VwParkCarIsuse model = parking.getVwParkCarIsuse(body.getCarNo());
			if (model != null) { // 固定车
				carOut.setYktId(model.getYktId());
			} else {
				carOut.setYktId(0);
			}
			outChannelType=channel.getInOut();
			ParkCarIn condition = new ParkCarIn();
			String rCarNo = body.getCarNo().trim().substring(1);
			condition.setCarNo(rCarNo);
			condition.setSmall(small);
			ParkCarIn modelGet = parkCarInMapper.selectTop(condition);
			if (modelGet != null) { // 存在入场记录
				ParkChannelSet parkChannelset = ParkMethod.getChannelSetByControlIndex(
						modelGet.getMachNo() == null ? 0 : modelGet.getMachNo().byteValue());
				if (parkChannelset != null)
					inChannelType = parkChannelset.getInOut();// 入口类型

				if (inChannelType == 4 && outChannelType == 3) { // 入口为半嵌套入口，出口为嵌套出口，
					modelGet.setSmall(0);
					parkCarInMapper.updateByPrimaryKeySelective(modelGet);
				} else {
					parkCarInMapper.deleteByPrimaryKey(modelGet.getId());
					BackUpParkCarIn backUpIn = new BackUpParkCarIn();
					BeanUtils.copyProperties(modelGet, backUpIn);
					backUpIn.setId(null);
					backUpParkCarInMapper.insert(backUpIn);
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
			carOut.setGuid(ParkMethod.getUUID());
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
			carOut.setDiscountNo(body.getDiscountNumber());
			carOut.setTypeId(Integer.valueOf(body.getDiscountID()));
			carOut.setDiscountTime(body.getDiscountTime());
			int row=parkCarOutMapper.insert(carOut);
			if (row > 0) {
				sendParkCarOutRecordToBox(carOut); //推送入场记录
				parking.pushOutData(channel, carOut);
				parking.outStatisticsRefresh(carOut);
				LoggerUntils.error(logger, "保存出场记录成功");
			}
			else
				LoggerUntils.error(logger, "保存出场记录失败");
		} catch (Exception e) {
			LoggerUntils.error(logger, e);
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
				}
			}
		}
		catch(Exception ex)
		{
			LoggerUntils.error(logger, ex.toString());
		}
	}
}

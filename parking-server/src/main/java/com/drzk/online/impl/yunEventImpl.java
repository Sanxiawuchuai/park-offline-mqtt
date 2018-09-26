package com.drzk.online.impl;

import com.drzk.charge.Charge;
import com.drzk.charge.vo.PaymentVo;
import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.fact.CentreRealTimeBase;
import com.drzk.fact.InRealTimeBase;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.mapper.ParkCarInMapper;
import com.drzk.mapper.ParkCentralChargeMapper;
import com.drzk.mapper.ParkOverTimeSetMapper;
import com.drzk.mapper.VwParkCarIsuseMapper;
import com.drzk.offline.vo.ParkOutChanelCorrectingBody;
import com.drzk.online.service.OfMqttService;
import com.drzk.online.service.YktIssueService;
import com.drzk.online.service.YunEventSever;
import com.drzk.online.vo.*;
import com.drzk.service.IParkingService;
import com.drzk.service.entity.BoxInIsOpenBody;
import com.drzk.service.entity.BoxOutIsOpenBody;
import com.drzk.service.entity.Head;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.timer.ParkingInTask;
import com.drzk.timer.ParkingOutTask;
import com.drzk.utils.*;
import com.drzk.vo.*;
import com.drzk.vo.ParkCarOut;

import org.apache.log4j.Logger;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class yunEventImpl implements YunEventSever {

	@Autowired
	OfMqttService ofMqttService;
	@Autowired
	ParkCarInMapper parkCarInMapper;
	@Autowired
	Charge charge;
	@Autowired
	ParkOverTimeSetMapper parkOverTimeSetMapper;
	@Autowired
	VwParkCarIsuseMapper vwParkCarIsuseMapper;
	@Autowired
	ParkCentralChargeMapper parkCentralChargeMapper;	
	@KSession("ksession_centre")
	private KieSession kSession;	
	@Autowired
	private IParkingService parkingCentreService;
	@Autowired
	private YktIssueService yktIssueService;
    
	private static Logger logger = Logger.getLogger("yunEventImpl");

	@Override
	public void scanCodesInOut(String jsonStr) {
		MainBoardMessage<YunScanCodeHead, YunScanCodeInOutBody> messgeVo = 
				JsonUtil.jsonToBoardMessage(jsonStr, YunScanCodeHead.class,
						YunScanCodeInOutBody.class);
		YunScanCodeInOutBody model  = messgeVo.getBody();
		ParkChannelSet channel = ParkMethod.getChannelSetByID(Integer.parseInt(model.getChannelId()));
		YunScanCodeInOutReplayHead yunHead  = new YunScanCodeInOutReplayHead();
		if(channel != null)
		{
			ParkLocalSet local = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
			InRealTimeBase in = GlobalPark.inChannelEvent.get(channel.getChannelIp());
			OutRealTimeBase out = GlobalPark.outChannelEvent.get(channel.getChannelIp());
			if(in != null && in.getFrmType()==1)
			{
				//取消岗亭弹框
				if(local != null && local.getOnline()==1) {
					sendToBoxIn(local.getEquipmentID(),channel.getChannelIp());
				}
				
				YunScanCodeInModel body = new YunScanCodeInModel();
				body.setCarNo(model.getCarNo());
				body.setChannelType(0);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				if(in.getIn().getInTime() != null)
					body.setEntryTime(sdf.format(in.getIn().getInTime()));
				else
					body.setEntryTime(sdf.format( new Date()));
				yunHead.setStatus(1);
				YunScanCode<YunScanCodeInOutReplayHead,YunScanCodeInModel> retInfo = 
						new YunScanCode<>(yunHead,body);
				String json = JsonUtil.objectToJsonStr(retInfo);
				ofMqttService.sendMessage(messgeVo.getHead().getReplyTopic(), json, null, 0);
				
				in.setInOrOutFlag(true);
				in.setDeal(true);
				in.setCarNo(model.getCarNo());
				in.setChannelSet(channel);
				in.getIn().setCardId(ParkMethod.CarNotoCardID(model.getCarNo()));
				in.getIn().setCarNo(model.getCarNo());
				in.getIn().setInWay(3);
				in.getIn().setCarNoType(0);
				
				in.setNextStep(Step.START);
				ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
				ParkingInTask parkingInTask = SpringUtil.getBean(ParkingInTask.class);
				parkingInTask.setParkIn(in);
				threadPool.execute(parkingInTask);
				
				GlobalPark.inChannelEvent.remove(channel.getChannelIp());
			}
			else if(out != null && out.getFrmType()==5) {				
				CentreRealTimeBase center = getCharge(model.getCarNo());
				Object isSuccess = center.getStatusMap().get("isSuccess");
				if((int)isSuccess == 0)
				{
					out.setPayMentVo(center.getPayMentVo());
					if(local != null && local.getOnline()==1) {
						sendToBoxOut(local.getEquipmentID(),channel.getChannelIp());
					}
					if(out.getInRecord()== null)
					{
						ParkCarIn parkInModel = new ParkCarIn();
						parkInModel.setCarNo(model.getCarNo());
						List<ParkCarIn> parkInList = parkCarInMapper.selectByCondition(parkInModel);	
						if(parkInList != null && parkInList.size()>0)
						{
							out.setInRecord(parkInList.get(0));
						}
					}
					out.setDeal(true);
					out.setInOrOutFlag(false);
					out.setCarNo(model.getCarNo());
					out.getOut().setCardId(ParkMethod.CarNotoCardID(model.getCarNo()));
					out.setChannelSet(channel);
					out.getOut().setOutCarNo(model.getCarNo());
					out.getOut().setOutWay(3);
					out.getOut().setCarNoType(0);
					out.getOut().setOutCarNo(model.getCarNo());
					
					out.setNextStep(Step.START);
					pushBackChargeData( out,messgeVo.getHead().getReplyTopic(),Integer.parseInt(model.getChannelId()));
					GlobalPark.outChannelEvent.remove(channel.getChannelIp());
					GlobalPark.outChannelEvent.put(channel.getChannelIp(),out);
					ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
					ParkingOutTask parkingOutTask = SpringUtil.getBean(ParkingOutTask.class);
					parkingOutTask.setParkOut(out);
					threadPool.execute(parkingOutTask);	
					GlobalPark.outChannelEvent.remove(channel.getChannelIp());
					GlobalPark.outChannelEvent.put(channel.getChannelIp(), out);
				}
				else
				{
					yunHead.setMessage((String) center.getStatusMap().get("Msg")); //取失败原因
					sendToYunChageData(yunHead,messgeVo.getHead().getReplyTopic());
				}
			}
			else if (out != null && out.getFrmType()==6)
			{
				pushBackChargeData( out,messgeVo.getHead().getReplyTopic(),Integer.parseInt(model.getChannelId()));
			}
		}
		else
		{
			yunHead.setMessage("没有找到对应通道");
			sendToYunChageData(yunHead,messgeVo.getHead().getReplyTopic());
		}
	}
	
	private void sendToYunChageData(YunScanCodeInOutReplayHead yunHead,String topic)
	{
		yunHead.setStatus(0);
		YunScanCodeInModel body = new YunScanCodeInModel();
		YunScanCode<YunScanCodeInOutReplayHead,YunScanCodeInModel> retInfo = 
				new YunScanCode<>(yunHead,body);
		String json = JsonUtil.objectToJsonStr(retInfo);
		ofMqttService.sendMessage(topic, json, null, 0);
	}
	//入场推送
	private void sendToBoxIn(String equipmentID,String channelIP)
	{
		BoxInIsOpenBody body = new BoxInIsOpenBody();
		body.setEquipmentID(equipmentID );
		body.setControlIP(channelIP );
		body.setType("1");
		Head head = new Head();
		head.setMethod("parkInChanelCorrecting");				 
//		String equipmentID = local.getEquipmentID();
		String topic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
		MainBoardMessage<Head,BoxInIsOpenBody> returnInfo = new MainBoardMessage<>(head,body);
		String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
		System.out.println(topic);
		System.out.println(jsonBody);
		MqttServiceImpl.sendMessage(equipmentID,topic, jsonBody, null, 0);
	}

	//出场校正
	private void sendToBoxOut(String equipmentID,String channelIp)
	{
		Head head = new Head();
		head.setMethod("parkOutChanelCorrecting");
		ParkOutChanelCorrectingBody body = new ParkOutChanelCorrectingBody();
		body.setType("1");
		body.setControlIP(channelIp);
//		String equipmentID = local.getEquipmentID();
		body.setEquipmentID(equipmentID);
		String topic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
		MainBoardMessage<Head,ParkOutChanelCorrectingBody> returnInfo = new MainBoardMessage<>(head,body);
		String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
		MqttServiceImpl.sendMessage(equipmentID,topic, jsonBody, null, 0);
	}
	/**
	 * 出场弹框
	 * @param out
	 * @param flag 0需要处理，1不需要处理
	 */
	private void sendToBoxCancelCharge(OutRealTimeBase out,Integer flag)
	{
		BoxOutIsOpenBody body = new BoxOutIsOpenBody();
		ParkChannelSet channel = out.getChannelSet();
		if (channel != null && channel.getBoxId() != null) {
			out.setFrmType(6);
			ParkLocalSet local = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
			if (local.getOnline() != null && local.getOnline() == 1) {
				Head head = new Head();
				head.setMethod("parkOutIsOpen");
				body.setControlIP(channel.getChannelIp());
				body.setOutRecord(out);
				body.setType(flag.toString());

				String equipmentID = local.getEquipmentID();
				body.setEquipmentID(equipmentID);
				String topic = String.format(TopicsDefine.BOX_ERROR, equipmentID);
				MainBoardMessage<Head, BoxOutIsOpenBody> returnInfo = new MainBoardMessage<>(head, body);
				String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
				MqttServiceImpl.sendMessage(equipmentID, topic, jsonBody, null, 1);
			}
		}
	}
	/** 获取费用 */
	@Override
	public void getCentrilChargeData(String jsonStr)
	{
		YunGetChargeReplay retVo = new YunGetChargeReplay();
		YunScanCodeInOutReplayHead yunHead = new YunScanCodeInOutReplayHead();
		CentreRealTimeBase center = new CentreRealTimeBase();
		MainBoardMessage<YunScanCodeHead, YunGetCharge> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr,
				YunScanCodeHead.class, YunGetCharge.class);
		try {
			YunGetCharge model = messgeVo.getBody();
			if (model != null) {
				center.setCarNo(model.getCarNo());
				center.getPayMentVo().setPayType(1);
				center.setNowDate(new Date());
				center.setNextStep(Step.START); //设置中央收费规则执行步骤
				center.setCentreRecord(new ParkCentralCharge());
				kSession.setGlobal("parkCentreService", parkingCentreService);
				kSession.insert(center); //插入规则
				kSession.fireAllRules(); // 执行规则完成
				// 取中央收费处理结果
				Object isSuccess = center.getStatusMap().get("isSuccess");
				if ((int)isSuccess == 0) {
					yunHead.setStatus(1);// 状态正常
					retVo.setCouponAmount(center.getPayMentVo().getPayCharge() / (float) 100); // 实收
					retVo.setDisAmount(center.getPayMentVo().getDisAmount() / (float) 100);// 优惠
					retVo.setTotalAmount(center.getPayMentVo().getPaidFee() / (float) 100); // 应收
					int seconds = DateTimeUtils.differScenod(center.getPayMentVo().getInTime(),
							center.getPayMentVo().getOutTime());
					retVo.setDuration(seconds / 60);
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
					retVo.setChargeTime(sdf.format(center.getPayMentVo().getPayInTime()));
					retVo.setEndChargeTime(sdf.format(center.getPayMentVo().getPayOutTime()));
					retVo.setEntryTime(sdf.format(center.getPayMentVo().getInTime()));
					retVo.setCarNo(model.getCarNo());
					retVo.setPaymentType(center.getPayMentVo().getPayType());
				} else {
					yunHead.setStatus(0);// 失败
					yunHead.setMessage((String) center.getStatusMap().get("Msg")); //取失败原因
				}
			} else {
				yunHead.setStatus(0);// 失败
				yunHead.setMessage("请求对象为空");
			}
		} catch (Exception ex) {
			LoggerUntils.error(logger, ex.toString());
			yunHead.setStatus(0);// 失败
			yunHead.setMessage("线下yf");
		}finally {
			YunScanCode<YunScanCodeInOutReplayHead, YunGetChargeReplay> retInfo = new YunScanCode<>(yunHead, retVo);
			String json = JsonUtil.objectToJsonStr(retInfo);
			ofMqttService.sendMessage(messgeVo.getHead().getReplyTopic(), json, null, 0);
		}
		
	}

	/** 下发的缴费纪录 */
	@Override
	public void writeChargeData(String jsonStr)
	{
		MainBoardMessage<YunScanCodeHead, PayMentCarFee> messgeVo = 
				JsonUtil.jsonToBoardMessage(jsonStr, YunScanCodeHead.class,
						PayMentCarFee.class);
		PayMentCarFee model = messgeVo.getBody();
		YunScanCodeInOutReplayHead yunHead  = new YunScanCodeInOutReplayHead();
		try {
			if (model != null) {
				ParkCarIn inModel = new ParkCarIn();
				inModel.setCarNo(model.getCarNo().substring(1));
				ParkCarIn in = parkCarInMapper.selectTop(inModel);
				if (in == null)
					return;				
				saveCentrialChargeData(model,in);				
				if (model.getChannelId() > 0)// 出口扫码存在
				{
					ParkChannelSet channel = ParkMethod.getChannelSetById(model.getChannelId());
					if (channel != null) {
						OutRealTimeBase out = GlobalPark.outChannelEvent.get(channel.getChannelIp());
						ParkCarOut parkout = getParkCarOurModel(in,out.getOut(),model,channel);
						out.setOut(parkout);
						PaymentVo payModel = out.getPayMentVo();
						payModel.setPaidFee(0);
						payModel.setDisAmount(0);
						payModel.setPayCharge(0);
						payModel.setPayType(0);
						payModel.setOrderNum(null);
						out.setPayMentVo(payModel);	
						out.setInRecord(in);
						out.setSmall(false);
						//取消出口收费弹框
						sendToBoxCancelCharge(out,1);
						out.setNextStep(Step.SAVE);
						ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
						ParkingOutTask parkingOutTask = SpringUtil.getBean(ParkingOutTask.class);
						parkingOutTask.setParkOut(out);
						threadPool.execute(parkingOutTask);	
						GlobalPark.outChannelEvent.remove(channel.getChannelIp());
					}
				}
				yunHead.setStatus(1);
			} else
				yunHead.setStatus(0);
		} catch (Exception ex) {
			yunHead.setStatus(0);
		} finally {
			YunScanCode<YunScanCodeInOutReplayHead, YunScanCodeInModel> retInfo = new YunScanCode<>(yunHead, null);
			String json = JsonUtil.objectToJsonStr(retInfo);
			ofMqttService.sendMessage(messgeVo.getHead().getReplyTopic(), json, null, 0);
		}
	}

	private void saveCentrialChargeData(PayMentCarFee model,ParkCarIn in)
	{
		ParkCentralCharge Vo = new ParkCentralCharge();
		Vo.setAccountCharge(model.getTotalAmount());
		Vo.setBackCarNo(in.getAssistantCarNo());
		Vo.setBackInPic(in.getBackInPic());
		List<VwParkCarIsuse> issueList = vwParkCarIsuseMapper.selectByCardId(in.getCardId());
		if (issueList != null && issueList.size() > 0) {
			Vo.setBalanceMoney(issueList.get(0).getBalanceMoney());
			Vo.setYktId(issueList.get(0).getYktId());
		}
		else
			Vo.setYktId(0);
		Vo.setCardId(in.getCardId());
		Vo.setCardNo(in.getCardNo());
		Vo.setCardType(in.getCardType());
		Vo.setCarNo(model.getCarNo());
		Vo.setcFlag(in.getcFlag());
		Vo.setDisAmount(model.getDisAmount());
		Vo.setDiscountTime(StrToDate(model.getPayTime()));
		Vo.setInId(in.getGuid());
		Vo.setInMachNo(in.getMachNo());
		Vo.setInPic(in.getInPic());
		Vo.setInTime(in.getInTime());
		Vo.setInUserName(in.getInUserName());
		Vo.setOrderNum(model.getPaymentTnx());
		Vo.setPayCharge(model.getCouponAmount());
		if (model.getPaymentType() == 1) {
			ParkCentralCharge cenVo = new ParkCentralCharge();
			cenVo.setCardId(in.getCardId());
			cenVo.setInTime(in.getInTime());
			List<ParkCentralCharge> list = parkCentralChargeMapper.selectByCondition(cenVo);
			if (list != null && list.size() > 0) {
				Vo.setOverTime(list.get(0).getPayChargeTime());
			}
		}
		String boxIp = null;
		if (model.getChannelId() != null && model.getChannelId() > 0)// 出口扫码存在
		{
			ParkChannelSet channel = ParkMethod.getChannelSetById(model.getChannelId());
			if (channel != null) {
				Vo.setBoxId(channel.getBoxId());
				Vo.setPayUserName(ParkMethod.getLocalSetByBoxId(channel.getBoxId()).getLoginName());
				boxIp = ParkMethod.getLocalSetByBoxId(channel.getBoxId()).getBoxIp();
			}
		}else{
			Vo.setBoxId(0);
			Vo.setPayUserName("云端缴费");
		}
		Vo.setPayChargeTime(new Date());
		if (model.getPayTime() != null)
			Vo.setPayChargeTime(StrToDate(model.getPayTime()));
		Vo.setPayType(model.getPayType());
		Vo.setPuid(ParkMethod.getUUID());
		// Vo.setTypeId(typeId);
		parkCentralChargeMapper.insertSelective(Vo);
		parkingCentreService.centreStatisticsRefresh(Vo);
		parkingCentreService.centreSumUser(Vo,boxIp);

	}
	
	private ParkCarOut getParkCarOurModel(ParkCarIn in,ParkCarOut out,PayMentCarFee model,ParkChannelSet channel)
	{
		out.setOutMachNo(channel.getMachNo());
		out.setOutUserName(ParkMethod.getLocalSetByBoxId(channel.getBoxId()).getLoginName());
		out.setPayCharge((float)0);
		out.setAccountCharge((float)0);
		out.setDisAmount((float)0);
		out.setPayType(0);
		out.setInId(in.getGuid());
		out.setInMachNo(in.getMachNo());
		out.setOutCarNo(model.getCarNo());
		out.setYktId(in.getYktId());
		out.setCardId(in.getCardId());
		out.setcFlag(in.getcFlag());
		out.setCardNo(in.getCardNo());
		out.setInCarNo(in.getCarNo());
		out.setCardType(in.getCardType());
		out.setInTime(in.getInTime());
		out.setOutTime(new Date());
		out.setInPic(in.getInPic());
		out.setInUserName(in.getInUserName());
		out.setPayType(model.getPayType());
		out.setAccountCharge((float)0);
		out.setPayCharge((float)0);
		out.setDisAmount((float)0);
		out.setOutWay(3);
		out.setIsOut(1);
		return out;
	}
	
	/**线上续费下发*/
	@Override
	public void onlineRenewal(String jsonStr) {
		MainBoardMessage<YunScanCodeHead,RenewalVO> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, YunScanCodeHead.class, RenewalVO.class);
		RenewalVO renewalVO=messgeVo.getBody();			//得到Body的相关值信息
		YunScanCodeInOutReplayHead yunHead  = new YunScanCodeInOutReplayHead();
		try{
			if(renewalVO!=null){
				Integer result=yktIssueService.delay(renewalVO);
				if(result==1){
					yunHead.setStatus(1);
					yktIssueService.reloadCarGrantData();           //重新加载控制器
				}else{
					yunHead.setStatus(0);
				}
			}else{
				yunHead.setStatus(0);
			}
		}catch (Exception e){
			logger.info("线上续费下发异常："+e.getMessage());
			yunHead.setStatus(0);
			yunHead.setMessage("续费异常");
		}finally {
			YunScanCode<YunScanCodeInOutReplayHead, YunScanCodeInModel> retInfo = new YunScanCode<>(yunHead, null);
			String json = JsonUtil.objectToJsonStr(retInfo);
			ofMqttService.sendMessage(messgeVo.getHead().getReplyTopic(), json, 0);		//回调消息
		}
	}

	private CentreRealTimeBase getCharge(String carNo)
	{
		CentreRealTimeBase center = new CentreRealTimeBase();
		center.setCarNo(carNo);
		center.getPayMentVo().setPayType(1);
		center.setNowDate(new Date());
		center.setNextStep(Step.START); //设置中央收费规则执行步骤
		center.setCentreRecord(new ParkCentralCharge());
		kSession.setGlobal("parkCentreService", parkingCentreService);
		kSession.insert(center); //插入规则
		kSession.fireAllRules(); // 执行规则完成
		return center;
	}
	
	private void pushBackChargeData(OutRealTimeBase out,String replayTopic,Integer channelId)
	{
		YunScanCodeInOutReplayHead yunHead  = new YunScanCodeInOutReplayHead();
		yunHead.setStatus(1);
		YunScanCodeOutModel body = new YunScanCodeOutModel();
		body.setCarNo(out.getCarNo());
		body.setChannelType(1);
		body.setChannelId(channelId);
		Date sDate ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");	
		if(out.getPayMentVo().getLastPayTime() == null)
		{
			body.setChargeTime(sdf.format(out.getPayMentVo().getInTime()));
			body.setPaymentType(0);
			sDate = out.getPayMentVo().getInTime();
		}
		else
		{
			body.setChargeTime(sdf.format(out.getPayMentVo().getLastPayTime()));
			body.setPaymentType(1);
			sDate = out.getPayMentVo().getLastPayTime();
		}
		body.setCouponAmount(out.getPayMentVo().getPayCharge()/100);
		body.setDisAmount(out.getPayMentVo().getDisAmount()/100);
		body.setEndChargeTime(sdf.format(out.getOut().getOutTime()));
		body.setEntryTime(sdf.format(out.getInRecord().getInTime()));
		int seconds = DateTimeUtils.differScenod(sDate, out.getOut().getOutTime());
		body.setDuration(seconds/60);
		body.setTotalAmount(out.getPayMentVo().getPaidFee()/100);
		YunScanCode<YunScanCodeInOutReplayHead,YunScanCodeOutModel> retInfo = 
				new YunScanCode<>(yunHead,body);
		String json = JsonUtil.objectToJsonStr(retInfo);
		ofMqttService.sendMessage(replayTopic, json, null, 0);
	}

	/**
	* 字符串转换成日期
	* @param str
	* @return date
	*/
	public static Date StrToDate(String str) {

	    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = null;
	    try {
	        date = format.parse(str);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return date;
	}

}

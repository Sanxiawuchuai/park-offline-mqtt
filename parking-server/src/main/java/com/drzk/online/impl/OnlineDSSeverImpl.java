package com.drzk.online.impl;

import com.drzk.mapper.*;
import com.drzk.offline.vo.CenterUpdateVO;
import com.drzk.online.constant.ConstantUtil;
import com.drzk.online.service.OfMqttService;
import com.drzk.online.service.OnlineDSSever;
import com.drzk.online.vo.*;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.vo.*;
import com.drzk.vo.ParkCarOut;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OnlineDSSeverImpl implements OnlineDSSever
{
	@Autowired
	ParkCarInMapper parkCarInMapper;
	@Autowired
	ConversionParameterClass conversionParameterClass;
	@Autowired
	OfMqttService ofMqttService;
	@Autowired
	ParkCarOutMapper parkCarOutMapper;
	@Autowired
	ParkLocalSetMapper parkLocalSetMapper;
	@Autowired
	ParkChannelSetMapper parkChannelSetMapper;
	@Autowired
	ParkCamSetMapper parkCamSetMapper;
	@Autowired
	ParkCarBlackListMapper parkCarBlackListMapper;
	@Autowired
	ParkCarInExceptionMapper parkCarInExceptionMapper;
	@Autowired
	VwParkIssueOperaMapper vwParkIssueOperaMapper;
	@Autowired
	YktCardIssueMapper yktCardIssueMapper;
	
	
	@Override
	public void parkcaroperation(CenterUpdateVO model)
	{
		try
		{
		SendHead head = new SendHead();
		head.setMethod("park/parkcaroperation/v1");
		String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
		String replyTopic = String.format(ConstantUtil.DS_TOPIC, parkNo,"parkcaroperation");
		head.setReplyTopic(replyTopic);
		head.setParkingNo(parkNo);
		
		if(model == null || model.getuId() != null)
			return;
		VwParkIssueOpera operaModel = vwParkIssueOperaMapper.selectByguid(model.getuId());
		if(operaModel ==null) return;
		ParkCarOperationVO VO = conversionParameterClass.getParkCarOperationVO(operaModel);
		if(VO == null) return ;
		List<String> guids = new ArrayList<String>();
		guids.add(model.getuId());
		int row = yktCardIssueMapper.updateDSStatusFalied(1,guids);//更新上传状态为1
		DataClass<ParkCarOperationVO> test = new DataClass<>();
		test.setHead(head);
		List<ParkCarOperationVO> sendList = new ArrayList<ParkCarOperationVO>();
		sendList.add(VO);
		test.setBody(sendList);
		String json = JsonUtil.objectToJsonStr(test);
		ofMqttService.sendMessage(ConstantUtil.OFFLINE_TOPIC, json, null, 0);
		}
		catch(Exception ex)
		{}
	}

	@Override
	public void parkunusualcarin(CenterUpdateVO model)
	{
		try
		{
		SendHead head = new SendHead();
		head.setMethod("park/parkunusualcarin/v1");
		String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
		String replyTopic = String.format(ConstantUtil.DS_TOPIC, parkNo,"parkunusualcarin");
		head.setReplyTopic(replyTopic);
		head.setParkingNo(parkNo);
		
		if(model == null || model.getuId() != null)
			return;
		ParkCarInException carInExceptionModel = parkCarInExceptionMapper.selectByGuid(model.getuId());
		if(carInExceptionModel ==null) return;
		ParkUnusualCarInVO VO = conversionParameterClass.getParkUnusualCarInVO(carInExceptionModel);
		if(VO == null) return ;
		List<String> guids = new ArrayList<String>();
		guids.add(model.getuId());
		int row = parkCarInExceptionMapper.updateDSStatusFalied(1,guids);//更新上传状态为1
		DataClass<ParkUnusualCarInVO> test = new DataClass<>();
		test.setHead(head);
		List<ParkUnusualCarInVO> sendList = new ArrayList<ParkUnusualCarInVO>();
		sendList.add(VO);
		test.setBody(sendList);
		String json = JsonUtil.objectToJsonStr(test);
		ofMqttService.sendMessage(ConstantUtil.OFFLINE_TOPIC, json, null, 0);
		}
		catch(Exception ex) {}
	}

	@Override
	public void parkcarin(CenterUpdateVO model)
	{
		try
		{
		SendHead head = new SendHead();
		head.setMethod("park/parkcarin/v1");
		String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
		String replyTopic = String.format(ConstantUtil.DS_TOPIC, parkNo,"parkcarin");
		head.setReplyTopic(replyTopic);
		head.setParkingNo(parkNo);
		
		if(model == null || model.getuId() != null)
			return;
		ParkCarIn inModel = parkCarInMapper.selectByGuid(model.getuId());
		if(inModel ==null) return;
		ParkCarInVO VO = conversionParameterClass.getParkCarIn(inModel);
		if(VO == null) return ;
		List<String> guids = new ArrayList<String>();
		guids.add(model.getuId());
		int row = parkCarInMapper.updateDSStatusFalied(1,guids);//更新上传状态为1
		DataClass<ParkCarInVO> test = new DataClass<>();
		test.setHead(head);
		List<ParkCarInVO> sendList = new ArrayList<ParkCarInVO>();
		sendList.add(VO);
		test.setBody(sendList);
		String json = JsonUtil.objectToJsonStr(test);
		ofMqttService.sendMessage(ConstantUtil.OFFLINE_TOPIC, json, null, 0);
		}
		catch(Exception ex)
		{}
	}

	@Override
	public void parkblackcar(CenterUpdateVO model)
	{
		try
		{
		SendHead head = new SendHead();
		head.setMethod("park/parkblackcar/v1");
		String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
		String replyTopic = String.format(ConstantUtil.DS_TOPIC, parkNo,"parkblackcar");
		head.setReplyTopic(replyTopic);
		head.setParkingNo(parkNo);
		
		if(model == null || model.getuId() != null)
			return;
		ParkCarBlackList blackModel = parkCarBlackListMapper.selectByGuid(model.getuId());
		if(blackModel ==null) return;
		ParkBlackCarVO VO = conversionParameterClass.getParkBlackCarVO(blackModel);
		if(VO == null) return ;
		List<String> guids = new ArrayList<String>();
		guids.add(model.getuId());
		int row = parkCarBlackListMapper.updateDSStatusFalied(1,guids);//更新上传状态为1
		DataClass<ParkBlackCarVO> test = new DataClass<>();
		test.setHead(head);
		List<ParkBlackCarVO> sendList = new ArrayList<ParkBlackCarVO>();
		sendList.add(VO);
		test.setBody(sendList);
		String json = JsonUtil.objectToJsonStr(test);
		ofMqttService.sendMessage(ConstantUtil.OFFLINE_TOPIC, json, null, 0);
		}
		catch(Exception ex)
		{}
	}

	@Override
	public void parkcarout(CenterUpdateVO model)
	{
		try
		{
		SendHead head = new SendHead();
		head.setMethod("park/parkcarout/v1");
		String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
		String replyTopic = String.format(ConstantUtil.DS_TOPIC, parkNo,"parkcarin");
		head.setReplyTopic(replyTopic);
		head.setParkingNo(parkNo);
		
		if(model == null || model.getuId() != null)
			return;
		ParkCarOut outModel = parkCarOutMapper.selectByGuid(model.getuId());
		if(outModel ==null) return;
		ParkCarOutVO VO = conversionParameterClass.getParkCarOutModel(outModel);
		if(VO == null) return;
		
		List<String> guids = new ArrayList<String>();
		guids.add(model.getuId());
		int row = parkCarOutMapper.updateDSStatus(1,guids);//更新上传状态为1
		DataClass<ParkCarOutVO> test = new DataClass<>();
		test.setHead(head);
		List<ParkCarOutVO> sendList = new ArrayList<ParkCarOutVO>();
		sendList.add(VO);
		test.setBody(sendList);
		String json = JsonUtil.objectToJsonStr(test);
		ofMqttService.sendMessage(ConstantUtil.OFFLINE_TOPIC, json, null, 0);
		}
		catch(Exception ex)
		{}
		
	}

	@Override
	public void boxinfo(CenterUpdateVO model)
	{
		try
		{
		SendHead head = new SendHead();
		head.setMethod("park/boxinfo/v1");
		String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
		String replyTopic = String.format(ConstantUtil.DS_TOPIC, parkNo,"boxinfo");
		head.setReplyTopic(replyTopic);
		head.setParkingNo(parkNo);
		
		if(model == null || model.getuId() != null)
			return;
		ParkLocalSet localModel = parkLocalSetMapper.selectByGuid(model.getuId());
		if(localModel ==null) return;
		BoxInfoVO VO = conversionParameterClass.getBoxInfoVO(localModel);
		if(VO == null) return;
		
		List<String> guids = new ArrayList<String>();
		guids.add(model.getuId());
//		int row = parkLocalSetMapper.updateDS(1,guids);//更新上传状态为1
		DataClass<BoxInfoVO> test = new DataClass<>();
		test.setHead(head);
		List<BoxInfoVO> sendList = new ArrayList<BoxInfoVO>();
		sendList.add(VO);
		test.setBody(sendList);
		String json = JsonUtil.objectToJsonStr(test);
		ofMqttService.sendMessage(ConstantUtil.OFFLINE_TOPIC, json, null, 0);
		}
		catch(Exception ex)
		{}
		
	}

	@Override
	public void controllerinfo(CenterUpdateVO model)
	{
		try
		{
			SendHead head = new SendHead();
			head.setMethod("park/controllerinfo/v1");
			String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
			String replyTopic = String.format(ConstantUtil.DS_TOPIC, parkNo,"controllerinfo");
			head.setReplyTopic(replyTopic);
			head.setParkingNo(parkNo);
			
			if(model == null || model.getuId() != null)
				return;
			ParkChannelSet channelModel = parkChannelSetMapper.selectByGuid(model.getuId());
			if(channelModel ==null) return;
			ControllerInfoVO VO = conversionParameterClass.getControlVO(channelModel);
			if(VO == null) return;
			List<String> guids = new ArrayList<String>();
			guids.add(model.getuId());
			int row = parkChannelSetMapper.updateDSStatus(1,guids);//更新上传状态为1
			DataClass<ControllerInfoVO> test = new DataClass<>();
			test.setHead(head);
			List<ControllerInfoVO> sendList = new ArrayList<ControllerInfoVO>();
			sendList.add(VO);
			test.setBody(sendList);
			String json = JsonUtil.objectToJsonStr(test);
			ofMqttService.sendMessage(ConstantUtil.OFFLINE_TOPIC, json, null, 0);
		}
		catch(Exception ex)
		{}

	}

	@Override
	public void parkcameras(CenterUpdateVO model)
	{
		try
		{
			SendHead head = new SendHead();
			head.setMethod("park/parkcameras/v1");
			String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
			String replyTopic = String.format(ConstantUtil.DS_TOPIC, parkNo,"parkcameras");
			head.setReplyTopic(replyTopic);
			head.setParkingNo(parkNo);
			
			if(model == null || model.getuId() != null)
				return;
			ParkCamSet camModel = parkCamSetMapper.selectByGuid(model.getuId());
			if(camModel ==null) return;
			ParkCamerasVO VO = conversionParameterClass.getCamVO(camModel);
			if(VO == null) return;
			List<String> guids = new ArrayList<String>();
			guids.add(model.getuId());
			int row = parkCamSetMapper.updateDSStatus(1,guids);//更新上传状态为1
			DataClass<ParkCamerasVO> test = new DataClass<>();
			test.setHead(head);
			List<ParkCamerasVO> sendList = new ArrayList<ParkCamerasVO>();
			sendList.add(VO);
			test.setBody(sendList);
			String json = JsonUtil.objectToJsonStr(test);
			ofMqttService.sendMessage(ConstantUtil.OFFLINE_TOPIC, json, null, 0);
		}
		catch(Exception ex)
		{}
	}

}

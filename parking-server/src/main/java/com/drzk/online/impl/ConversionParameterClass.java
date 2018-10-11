package com.drzk.online.impl;

import com.drzk.common.ParkMethod;
import com.drzk.mapper.*;
import com.drzk.online.vo.*;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.StringUtils;
import com.drzk.vo.*;
import com.drzk.vo.ParkCarOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 此类仅用于同步数据时候的实体参数名称不同时候的转换
 * @author yxf
 *
 */
@Service
public class ConversionParameterClass {
	@Autowired
	VwParkCarInMapper vwParkCarInMapper;
	@Autowired
	VwParkCarIsuseMapper vwParkCarIsuseMapper;
	@Autowired
	ParkCarInMapper parkCarInMapper;
	@Autowired
	ParkEquipmentsMapper parkEquipmentsMapper;
	@Autowired
	ParkLocalSetMapper parkLocalSetMapper;
	@Autowired
	ParkChannelSetMapper parkChannelSetMapper;
	@Autowired
	private ParkAccountTypeMapper parkAccountTypeMapper;
	
	static String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
	static String parkName=GlobalPark.properties.getProperty("PROJECT_NAME");
	static String dataOrign="线下";
	
	
	public  ParkCarInVO getParkCarIn(BackUpParkCarIn in) {
		try {
			if(in == null||in.getGuid()==null){
				return null;
			}

			ParkCarInVO retIn = new ParkCarInVO();
			retIn.setObjectId(in.getGuid());
			if(in.getMachNo() != null) {
				retIn.setEntranceId(in.getMachNo().toString());
			}

			ParkChannelSet channel = parkChannelSetMapper.selectByMachNo(in.getMachNo());
			if(channel != null) {
				retIn.setEntranceName(channel.getChannelName());
				retIn.setControlIp(channel.getChannelIp());
			}

			retIn.setEntranceUserName(in.getInUserName());
			retIn.setCardId(in.getCardId());
			retIn.setCarNo(in.getCarNo());
			retIn.setNextCarNo(in.getAssistantCarNo());
			retIn.setCorrectCarNo(in.getModifyCarNo());
			List<VwParkCarIn> vwParkCarInList = vwParkCarInMapper.GetByCarNo(in.getCarNo());
			if(vwParkCarInList != null && vwParkCarInList.size()>0) {
				retIn.setContactName(vwParkCarInList.get(0).getEmpName());
			}else {
				retIn.setContactName("临时用户");
			}
			ParkAccountType cardType = ParkMethod.ParkAccountTypeByType(in.getCardType());
			if(cardType != null) {
				retIn.setCardTypeName(cardType.getaName());
			}
			retIn.setCarTypeId(in.getCardType());
			if(in.getCarNoType()!= null) {
				retIn.setCarNoType(in.getCarNoType().toString());
			}
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			retIn.setInTime(sdf.format(in.getInTime()));
			if(in.getInPic()!=null) {
				retIn.setInPic("/" + parkNo + in.getInPic());
			}

			if(in.getInWay() != null) {
				retIn.setInWay(in.getInWay().toString());
				retIn.setInWayName(ParkMethod.GetInWayName(in.getInWay()));
			}
			if(in.getSmall() != null) {
				retIn.setSmall(in.getSmall().toString());
			}
			retIn.setDataOrigin(dataOrign);
			retIn.setCreator(in.getInUserName());
			retIn.setCreateTime(in.getInTime());
			retIn.setParkingNo(parkNo);
			if(in.getIsDelete() != null){
				retIn.setDeleteCode( in.getIsDelete().intValue());
			}
			return retIn;
		}
		catch(Exception ex) {
			return null;
		}
	}
	
	public ParkCarOutVO getParkCarOutModel(ParkCarOut out) {
		try {
			if(out == null||out.getGuid()==null){
				return null;
			}

			ParkCarOutVO retOut = new ParkCarOutVO();
			retOut.setObjectId(out.getGuid());
			retOut.setEntranceId(out.getInMachNo().toString());
			ParkChannelSet channel = parkChannelSetMapper.selectByMachNo(out.getInMachNo());

			if(channel != null) {
				retOut.setEntranceName(channel.getChannelName());
				retOut.setInControlIp(channel.getChannelIp());
			}
			retOut.setEntranceName(out.getInUserName());
			retOut.setAppearancesId(out.getOutMachNo().toString());

			ParkChannelSet channelout = parkChannelSetMapper.selectByMachNo(out.getOutMachNo());
			if(channelout!=null) {
				retOut.setAppearancesName(channelout.getChannelName());
				retOut.setOutCcontrolIp(channelout.getChannelIp());
			}

			retOut.setAppearancesUserName(out.getOutUserName());
			retOut.setCardId(out.getCardId());
			retOut.setCarNo(out.getOutCarNo());
			retOut.setNextCarNo(out.getBackOutCarNo());
			retOut.setCorrectCarNo(null);
			VwParkCarIsuse carIssue = new VwParkCarIsuse();
			carIssue.setCarNo(out.getOutCarNo());
			VwParkCarIsuse vwParkCarIsuse = vwParkCarIsuseMapper.selectMonthCar(carIssue);
			if(vwParkCarIsuse != null){
				retOut.setContactName(vwParkCarIsuse.getPerName());
			}else{
				retOut.setContactName("临时用户");
			}
			retOut.setCarTypeId(out.getCardType());
			ParkAccountType cardType = ParkMethod.ParkAccountTypeByType(out.getCardType());
			if(cardType != null) {
				retOut.setCardTypeName(cardType.getaName());
			}
			if(out.getCarNoType() != null) {
				retOut.setCarNoType(out.getCarNoType().toString());
			}
			if(out.getFreeType() != null) {
				retOut.setFreeType(out.getFreeType().toString());
			}
			retOut.setInTime(out.getInTime());
	//		retOut.setInWay(out.getinw().toString());
	//		retOut.setInWayName(ParkMethod.GetInWayName(out.getInWay()));
			if(out.getInPic()!=null) {
				retOut.setInPic("/" + parkNo + out.getInPic());
			}
			retOut.setCentralTime(out.getCentrialTime());
			retOut.setOutTime(out.getOutTime());
			if(out.getOutPic()!=null) {
				retOut.setOutPic("/" + parkNo + out.getOutPic());
			}
			if(out.getPayType() != null) {
				retOut.setPayType(out.getPayType().toString());
			}
			if(out.getPayCharge() != null) {
				retOut.setPayCharge(Double.parseDouble(out.getPayCharge().toString()));
			}
			if(out.getBalanceMoney() != null) {
				retOut.setBalanceMoney(Double.parseDouble(out.getBalanceMoney().toString()));
			}
			if(out.getDiscountNo() != null) {
				retOut.setDiscountNo(out.getDiscountNo().toString());
			}
			if(out.getTypeId() != null) {
				retOut.setTypeId(out.getTypeId().toString());
				retOut.setDiscountTime(out.getDiscountTime());
			}
			if(out.getDisAmount() != null) {
				retOut.setDisAmount(Double.parseDouble(out.getDisAmount().toString()));
			}
			if(out.getAccountCharge() != null) {
				retOut.setAccountCharge(Double.parseDouble(out.getAccountCharge().toString()));
			}
			if(out.getIsOut() != null) {
				retOut.setIsOut(out.getIsOut().toString());
			}
			retOut.setUnusualMemo(out.getMemo());
			if(out.getOutWay() != null) {
				retOut.setOutWay(out.getOutWay().toString());
				retOut.setOutWayName(ParkMethod.GetOutWayName(out.getOutWay()));
			}
			retOut.setDataOrigin(dataOrign);
			retOut.setCreator("system");
			retOut.setCreateTime(out.getOutTime());
			retOut.setParkingNo(parkNo);
			return retOut;
		}catch(Exception ex) {
			return null;
		}
	}
	
	public BoxInfoVO getBoxInfoVO(ParkLocalSet model)
	{
		try
		{
		if(model == null)
			return null;
		BoxInfoVO VO = new BoxInfoVO();
		VO.setBoxIp(model.getBoxIp());
		VO.setBoxName(model.getBoxName());
		VO.setBoxType(Integer.parseInt(model.getBoxType().toString()));
		VO.setMac(model.getEquipmentID());
		VO.setObjectId(model.getLuid());
		VO.setCreator("system");
		VO.setCreateTime(model.getCreateDate());
		VO.setLastUpdateName(model.getModifyUserName());
		VO.setLastUpdateTime(model.getModifyDate());
		VO.setParkingNo(parkNo);
		VO.setDeleteCode( model.getDelFrag().intValue());
		VO.setDataOrigin(dataOrign);
		return VO;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public ControllerInfoVO getControlVO(ParkChannelSet model)
	{
		try
		{
		if(model == null)
			return null;
		 
		ControllerInfoVO VO = new ControllerInfoVO();
//		VO.setBoxId(model.getBoxId().toString());
		VO.setObjectId(model.getCuid());
		ParkLocalSet local = parkLocalSetMapper.selectByBoxId(model.getBoxId());
		if(local != null)
		{
			VO.setBoxName(local.getBoxName());
			VO.setBoxId(local.getLuid());
		}
		if(model.getGateType() != null)
			VO.setChannelType(Integer.parseInt(model.getGateType().toString()));
		VO.setContName(model.getChannelName());
		VO.setControllerIp(model.getChannelIp());
		VO.setDataOrigin(dataOrign);
//		VO.setDescription(description);
		if(model.getStrobeSet() != null)
		{
		if(model.getStrobeSet().substring(0, 1).equals("0"))
			VO.setTempCar(0);
		else
			VO.setTempCar(1);
		if(model.getStrobeSet().substring(1, 2).equals("0"))
			VO.setMonthCar(0);
		else
			VO.setMonthCar(1);
		if(model.getStrobeSet().substring(2, 3).equals("0"))
			VO.setFreeCar(0);
		else
			VO.setFreeCar(1);
		if(model.getStrobeSet().substring(3, 4).equals("0"))
			VO.setRechargeCar(0);
		else
			VO.setRechargeCar(1);
		}
		if(model.getMachNo() != null)
			VO.setOpenMacNum(model.getMachNo().toString());
		
		VO.setMac(model.getDsn());
		if(model.getInOut() != null)
			VO.setType(Integer.parseInt(model.getInOut().toString()));
		VO.setCreator("system");
		VO.setCreateTime(model.getCreateDate());
		VO.setLastUpdateName(model.getModifyUserName());
		VO.setLastUpdateTime(model.getModifyDate());
		VO.setParkingNo(parkNo);
		VO.setDeleteCode(model.getDelFrag().intValue());
		return VO;
		}
		catch(Exception ex)
		{
			return null;
		}
		
	}
	
	public ParkCamerasVO getCamVO(ParkCamSet model)
	{
		if(model == null)
			return null;
		try
		{
		ParkCamerasVO VO = new ParkCamerasVO();
		VO.setBoxId(model.getBoxId().toString());
		ParkLocalSet localModel = parkLocalSetMapper.selectByBoxId(model.getBoxId());
		if(localModel != null)
			VO.setBoxName(localModel.getBoxName());
		VO.setCameIp(model.getCamIp());
		VO.setCameName(model.getCamName());
		if(model.getCamPort()!= null)
			VO.setCamePort(model.getCamPort().toString());
		VO.setUserName(GlobalPark.properties.getProperty("CAM_USER"));
		VO.setPassWord(GlobalPark.properties.getProperty("CAM_PWD"));
		VO.setObjectId(model.getCuid());
//		VO.setMac(model.get);
//		VO.setRealStatus(realStatus);
//		ParkChannelSet channel = ParkMethod.getChannelSetByControlIndex(model.getMachNo());
		ParkChannelSet channelout = new ParkChannelSet();
		channelout.setMachNo(model.getMachNo().intValue());
		List<ParkChannelSet> channelouts  = parkChannelSetMapper.selectByCondition(channelout);
		if(channelouts != null && channelouts.size()>0)
		{
			ParkChannelSet channel = channelouts.get(0);
			VO.setControllerChanlId(channel.getMachNo());
			VO.setControllerName(channel.getChannelName());
			VO.setControllerId(channel.getCuid());
		}
		VO.setCreator("system");
		VO.setCreateTime(model.getCreateDate());
		VO.setLastUpdateName("system");
		VO.setLastUpdateTime(model.getModifyDate());
		VO.setParkingNo(parkNo);
		if(model.getDelFrag() != null)
			VO.setDeleteCode(model.getDelFrag().intValue());
		VO.setDataOrigin(dataOrign);
		
		return VO;
		
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public ParkBlackCarVO getParkBlackCarVO(ParkCarBlackList model)
	{
		try
		{
			ParkBlackCarVO VO = new ParkBlackCarVO();
			if(model == null) return null;
			VO.setObjectId(model.getCuid());
			VO.setCarNo(model.getCarNo());
			if(model.getCarNoType() != null)
				VO.setCarNoType(model.getCarNoType().intValue());
			if(model.getIsStop() != null)
				VO.setIsStop(model.getIsStop().intValue());
			VO.setDescription(model.getMemo());
			VO.setDataOrigin(dataOrign);
			VO.setRemark(model.getMemo());
//			VO.setCreateId(createId);
			VO.setCreateTime(model.getCreateDate());
			VO.setCreator("system");
			VO.setLastUpdateName("system");
			VO.setLastUpdateTime(model.getModifyDate());
			VO.setParkingNo(parkNo);
			if(model.getDelFrag() != null)
				VO.setDeleteCode(model.getDelFrag().intValue());
			
			return VO;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public ParkUnusualCarInVO getParkUnusualCarInVO(ParkCarInException model)
	{
		try
		{
			if(model == null) return null;
			ParkUnusualCarInVO Vo = new ParkUnusualCarInVO();
			Vo.setObjectId(model.getGuid());
			Vo.setCardId(model.getCardId());
			Vo.setEmpName(model.getEmpName());
			if(model.getCardType() != null)
				Vo.setCarTypeId(model.getCardType().intValue());
			ParkAccountType cardType = ParkMethod.ParkAccountTypeByType(model.getCardType());
			if(cardType != null)
				Vo.setCarTypeName(cardType.getaName());
			Vo.setCarNo(model.getCarNo());
			Vo.setbInTime(model.getbInTime());
			Vo.setInTime(model.getInTime());
//			ParkChannelSet channel = ParkMethod.getChannelSetByControlIndex(model.getMachNo());
			ParkChannelSet channelout = new ParkChannelSet();
			channelout.setMachNo(model.getMachNo().intValue());
			List<ParkChannelSet> channelouts  = parkChannelSetMapper.selectByCondition(channelout);
			if(channelouts!= null && channelouts.size()>0)
				Vo.setInControlName(channelouts.get(0).getChannelName());
			Vo.setInUserName(model.getInUserName());
			Vo.setInWay(model.getInWay().intValue());
			Vo.setInWayName(ParkMethod.GetInWayName(model.getInWay()));
			Vo.setInPic(model.getInPic());
			Vo.setDataOrigin(dataOrign);
			Vo.setCreator("system");
			Vo.setCreateTime(model.getInTime());
			Vo.setParkingNo(parkNo);
			
			return Vo;
			
			
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public ParkCarOperationVO getParkCarOperationVO(VwParkIssueOpera model)
	{
		if(model == null) return null;
		try
		{
			ParkCarOperationVO VO = new ParkCarOperationVO();
			VO.setObjectId(ParkMethod.getUUID());
			VO.setCarNo(model.getCarNo());
			VO.setCardId(model.getCardId());
			VO.setCardNo(model.getCarNo());
			VO.setCardTypeId(model.getCardType());
			ParkAccountType cardType = ParkMethod.ParkAccountTypeByType(model.getCardType());
			if(cardType != null)
				VO.setCardTypeName(cardType.getaName());
			VO.setContactId(model.getPid());
			VO.setContactName(model.getPerName());
			VO.setContactPhone(StringUtils.dencode(model.getPhone()));
			VO.setAddress(model.getAddress());
			VO.setFrontDate(model.getFrontDate());
			VO.setStartTime(model.getStartDate());
			VO.setEndTime(model.getEndDate());
			if(model.getBalanceMoney() !=  null)
				VO.setRechargeMoney(model.getBalanceMoney().doubleValue());
			if(model.getBeforeMoney() != null)
				VO.setBalanceMoney(model.getBeforeMoney().doubleValue());
			if(model.getForegift() != null)
				VO.setDeposit(model.getForegift().doubleValue());
			if(model.getPayType() != null)
				VO.setPayType(model.getPayType().intValue());
			VO.setTransactionNumber(model.getOrderNo());
			if(model.getOperType() != null){
				VO.setOperationType(model.getOperType());
			}

			VO.setDataOrigin(dataOrign);
			VO.setCreateTime(model.getCreateDate());
			VO.setCreator("system");
			VO.setParkingNo(parkNo);
			VO.setParkingName(parkName);
			return VO;
		}
		catch(Exception ex)
		{
			return null;
		}
	}
	
	public ParkSumUsersVO getParkSumUsersVO(ParkSumUser model)
	{
		if(model == null)  return null;
		ParkSumUsersVO VO = null;
		try
		{
			VO = new ParkSumUsersVO();
			VO.setObjectId(model.getPuid());
			VO.setLoginDate(model.getLoginDate());
			VO.setReliefDate(model.getReliefDate());
			VO.setUserName(model.getUserName());
			VO.setReUserName(model.getReliefUserName());
			VO.setHandGate(model.getHandGate());
			VO.setComputerGate(model.getComputerGate());
			VO.setTempCardIn(model.getTempCarIn());
			VO.setTempCardOut(model.getTempCarOut());
			VO.setMonthCardIn(model.getMonthCarIn());
			VO.setMonthCardOut(model.getMonthCarOut());
			if(model.getStoredCarMoney() != null)
				VO.setStoredCardMoney(model.getStoredCarMoney().doubleValue());
			VO.setStoredCardNum(model.getStoredCarNum());
			VO.setTempFree(model.getTempFree());
			VO.setFreeCarNo(model.getFreeCarNo());
			if(model.getFreeCharge() != null)
				VO.setFreeCharge(model.getFreeCharge().doubleValue());
			VO.setDiscountNum(model.getDisSum());
			if(model.getDisCharge() != null)
				VO.setDiscountCharge(model.getDisCharge().doubleValue());
			if(model.getThirdpayCharge() != null)
				VO.setPosMoney(model.getThirdpayCharge().doubleValue());
			if(model.getCashCharge() != null)
				VO.setTotalCharge(model.getCashCharge().doubleValue());
			if(model.getAccount() != null)
				VO.setAccount(model.getAccount().doubleValue());
			VO.setDataOrigin(dataOrign);
			VO.setParkingNo(parkNo);
		}
		catch(Exception ex)
		{}
		return VO;
	}
	
	public ParkCenterPayment getParkCenterPayment(ParkCentralCharge model)
	{
		if(model == null||model.getPuid()==null) return null;
		ParkCenterPayment Vo =null;
		try
		{
			Vo = new ParkCenterPayment();
			Vo.setObjectId(model.getPuid());
			Vo.setInMachNo(model.getInMachNo());
//			ParkChannelSet channel = ParkMethod.getChannelSetByControlIndex(model.getInMachNo());
			ParkChannelSet channelout = new ParkChannelSet();
			channelout.setMachNo(model.getInMachNo().intValue());			
			Vo.setCarNo(model.getCarNo());
			Vo.setCardId(model.getCardId());
			Vo.setCardNo(model.getCardNo());
			Vo.setcFlag(model.getcFlag().intValue());
			Vo.setCardType(model.getCardType());
			Vo.setFreeType(model.getFreeType());
			Vo.setInTime(model.getInTime());			
			Vo.setOverTimeS(model.getOverTime());
			Vo.setPayChargeTime(model.getPayChargeTime());
			Vo.setPayType(model.getPayType().intValue());
			Vo.setTransactionId(model.getOrderNum());
			if(model.getPayCharge()!= null)
				Vo.setPayCharge(model.getPayCharge().doubleValue());
			if(model.getAccountCharge() != null)
				Vo.setAccountCharge(model.getAccountCharge().doubleValue());
			if(model.getDiscountNo() != null)
				Vo.setDiscountNo(model.getDiscountNo().toString());
			if(model.getTypeId() != null)
				Vo.setEqId(model.getTypeId().toString());
			Vo.setDiscountTime(model.getDiscountTime());
			if(model.getAccountCharge() != null)
				Vo.setDisAmount(model.getAccountCharge().doubleValue());
			if(model.getAccountCharge() != null)
				Vo.setAccountCharge(model.getAccountCharge().doubleValue());
			Vo.setUnusualMemo(model.getMemo());
			Vo.setDataOrigin(dataOrign);
			Vo.setCreator("system");
			Vo.setCreateTime(model.getPayChargeTime());
			Vo.setParkingNo(parkNo);
			List<ParkChannelSet> channelouts  = parkChannelSetMapper.selectByCondition(channelout);
			if(channelouts != null && channelouts.size()>0)
				Vo.setEntrance(channelouts.get(0).getChannelName());
			ParkAccountType cardType = ParkMethod.ParkAccountTypeByType(model.getCardType());
			if(cardType != null)
				Vo.setCardTypeName(cardType.getaName());			
			ParkCarIn carIn= parkCarInMapper.selectByGuid(model.getInId());
			if(carIn != null)
			{
				Vo.setInUserName(carIn.getInUserName());
			}
		}
		catch(Exception ex)
		{}
		return Vo;
	}
	
	public ParkMonthlyFeeType getparkmonthlyfeetype(ParkMonthSet model)
	{
		if(model == null) return null;
		ParkMonthlyFeeType Vo = null;
		try
		{
			Vo = new ParkMonthlyFeeType();
			Vo.setObjectId(model.getPuid());
			Vo.setPackageType(model.getAccountType().toString());
			Vo.setPackageDuration(model.getsType().toString());
			Vo.setPackageName(model.getCardTypeName());

			if(model.getChargeMoney() != null) {
				Vo.setPackageCharge(model.getChargeMoney().toString());
			}

			Vo.setChargeModel("2");
			Vo.setDataOrigin(dataOrign);
			Vo.setRemark(model.getMemo());
			Vo.setCreator("system");
			Vo.setCreateTime(model.getCreateDate());
			Vo.setLastUpdateName("system");
			Vo.setLastUpdateTime(model.getModifyDate());
		}
		catch(Exception ex)
		{}
		return Vo;
	
	}
	
	public ParkDepartmentVO getParkDepartmentVO(VwDeptCompany model)
	{
		if(model == null) return null;
		ParkDepartmentVO Vo =null;
		try
		{
			Vo = new ParkDepartmentVO();
			Vo.setDataOrigin(dataOrign);
			Vo.setParkingNo(parkNo);
			Vo.setCompanyId(model.getCompanyCuid());
			Vo.setCompanyName(model.getCompanyName());
			Vo.setDepartmentName(model.getDeptName());
			Vo.setObjectId(model.getDeptPuid());
			if(model.getDeptParentPuid()!=null) {
				Vo.setParentId(model.getDeptParentPuid());
			}else{
				Vo.setParentId(model.getCompanyCuid());
			}
			Vo.setDeleteCode(model.getDeptUpdateFlag());
		}
		catch(Exception ex)
		{}
		return Vo;
	}
	
	public ParkOpenGateIllegally getParkOpenGateIllegally(VwParkCarOut model)
	{
		if(model == null) return null;
		ParkOpenGateIllegally Vo = null;
		try
		{
			Vo = new ParkOpenGateIllegally();
			Vo.setObjectId(model.getGuid());
//			ParkChannelSet channel = ParkMethod.getChannelSetByControlIndex(model.getInMachNo());
			ParkChannelSet channelout = new ParkChannelSet();
			channelout.setMachNo(model.getInMachNo().intValue());
			List<ParkChannelSet> channelouts  = parkChannelSetMapper.selectByCondition(channelout);
			if(channelouts  != null && channelouts.size()>0)
			{
				ParkChannelSet channel = channelouts.get(0);
				ParkLocalSet local = parkLocalSetMapper.selectByBoxId(channel.getBoxId());
				if(local != null)
				{
					Vo.setBoxName(local.getBoxName());
				}
				Vo.setBoxId(channel.getBoxId().toString());
				Vo.setControllerId(channel.getCuid());
				Vo.setControllerIP(channel.getChannelIp());
				Vo.setControllerName(channel.getChannelName());
			}
			
			if(model.getInUserName()!= null)
			{
				Vo.setAuditor(model.getInUserName());
				Vo.setCreator(model.getInUserName());
			}
			else
			{
				Vo.setAuditor(model.getOutUserName());
				Vo.setCreator(model.getOutUserName());
			}
			Vo.setAuditStatus(1);
			if(model.getOutWay()!= null)
				Vo.setOpenType(model.getOutWay().intValue());
			Vo.setOpenModel(1);
			Vo.setOpenTime(model.getOutTime());
			Vo.setCarNo(model.getCardNo());
			List<PicPathsBody> pics = new ArrayList<>();			
			PicPathsBody pic = new PicPathsBody();
			pic.setPicPath(model.getInPic());
			PicPathsBody pic2 = new PicPathsBody();
			pic2.setPicPath(model.getOutPic());
			pics.add(pic2);
			pics.add(pic);
			Vo.setPics(pics);
			if(model.getIsOut() != null)
				Vo.setIsOut(model.getIsOut().intValue());
			Vo.setCreateTime(model.getOutTime());
			Vo.setCreator("system");
		}
		catch(Exception ex)
		{}
		return Vo;
	}
	
	public PersonnelInfo getPersonnelInfo(VwPersoninfo model) {
		if(model == null) return null;
		PersonnelInfo vo = null;
		try {
			vo = new PersonnelInfo();
			vo.setObjectId(model.getPuid());
			//人员上级ip，默认为部门，如果没设部门，默认为公司，公司没设，默认车场编号
			if(model.getDuid()!=null){
				vo.setParentId(model.getDuid());
			}else{
				if(model.getCuid()!=null){
					vo.setParentId(model.getCuid());
				}else{
					vo.setParentId(parkNo);
				}
			}

			vo.setDepartmentId(model.getDuid()==null?"":model.getDuid());
			vo.setDepartmentName(model.getDeptName());
			vo.setCompanyName(model.getCompanyName());
			vo.setCompanyId(model.getCuid());
			vo.setContactName(model.getPerName());
			vo.setContactPhone(StringUtils.dencode(model.getPerTel()));
			vo.setContactNo(model.getPerId());
			vo.setEntryDate(model.getEnterDate());
			vo.setConversionDate(model.getFormatDate());
			switch(model.getSex()) {
			case "未知":
				vo.setSex(0);
				break;
			case "男":
				vo.setSex(1);
				break;
			case "女":
				vo.setSex(2);
				break;
			}
			vo.setMarry(model.getMarry()==null?0:Integer.parseInt(model.getMarry()));
			vo.setBirthplace(model.getPerPlace());
			vo.setIdType(1);
			vo.setIdNum(model.getPerIdNo());
			vo.setCarNo(model.getCarNo());
			Integer edu=null;
			if(model.getEduLevel()!=null) {
				switch (model.getEduLevel()) {
					case "博士":
						edu = 1;
						break;
					case "硕士":
						edu = 2;
						break;
					case "学士":
						edu = 3;
						break;
					case "本科":
						edu = 4;
						break;
					case "大专":
						edu = 5;
						break;
					case "高中":
						edu = 6;
						break;
					case "中专":
						edu = 7;
						break;
					case "其他":
						edu = 8;
						break;
					default:
						break;
				}
				vo.setEducation(edu);
			}

			vo.setNationality(model.getPerNation());
			vo.setIdType(model.getPerType());
			vo.setCarportGroupId(model.getPlaceId());
			vo.setCarportGroupName(model.getPlaceName());
			vo.setCreateTime(model.getCreateDate());
			vo.setCreator("system");
			vo.setLastUpdateTime(model.getModifyDate());
			vo.setLastUpdateName("system");
			vo.setDeleteCode(model.getDelFrag());
			vo.setParkingNo(parkNo);
			
		}
		catch(Exception ex) {}
		return vo;
	}
	
	public CouPonEqInfo getCouPonEqInfo(ParkEquipments model)
	{
		if(model== null) return null;
		CouPonEqInfo vo = null;
		try
		{
			vo = new CouPonEqInfo();
			vo.setBusinessName(model.getEqName());
			vo.setRemark(model.getMemo());
			if(model.getDelFrag() != null) {
                vo.setDeleteCode(model.getDelFrag().intValue());
            }
            vo.setStatus(0);
			vo.setDataOrigin(dataOrign);
			vo.setObjectId(model.getEuid());
		}
		catch(Exception ex)
		{}
		return vo;
	}
	
	public CouponRules getCouponRules(ParkDisInfo model)
	{
		if(model== null) return null;
		CouponRules vo = null;
		try
		{
			vo = new CouponRules();
			vo.setDataOrigin(dataOrign);
			vo.setObjectId(model.getPuid());
			vo.setDiscountId(model.getDiscountId());
			vo.setDiscountName(model.getDiscountName());
			if(model.getDiscountType() != null)
				vo.setDisType(model.getDiscountType().intValue());
			if(model.getDiscountAmount() != null)
				vo.setDisAmount(model.getDiscountAmount().doubleValue());
			vo.setDescription(model.getMemo());
			if(model.getDelFrag() != null)
				vo.setDeleteCode(model.getDelFrag().intValue());
			vo.setCreateTime(model.getCreateDate());
			vo.setCreator("system");
			vo.setLastUpdateName("system");
			vo.setLastUpdateTime(model.getModifyDate());
			ParkEquipments eqModel = parkEquipmentsMapper.selectByPrimaryKey(model.getEqid());
			vo.setBusinessName(eqModel.getEqName());
			vo.setBusinessId(eqModel.getEuid());
		}
		catch(Exception ex)
		{}
		return vo;
	}
	
	public CouponUseDetailsVO getCouponUseDetailsVO(VwDisCountInfo model) {
		if(model == null) return null;
		CouponUseDetailsVO vo =new CouponUseDetailsVO();
		vo.setObjectId(model.getPuid());
		if(model.getDelFrag() != null)
			vo.setDeleteCode(model.getDelFrag().intValue());
		vo.setDataOrigin(dataOrign);
		vo.setOpenId(model.getOnlineId());
		//vo.setDiscountID(model.getDiscountId());
		if(model.getDiscountTime() != null)
			vo.setDiscountTime(model.getDiscountTime().toString());
		vo.setCarNo(model.getCarNo());
		if(model.getInTime() != null)
			vo.setInTime(model.getInTime().toString());
		vo.setCouponId(model.getDisInfoPuid());
		vo.setCouponName(model.getDiscountName());
		vo.setCommercialId(model.getEuid());
		vo.setCommercialName(model.getEqName());
		if(model.getDiscountType() != null)
			vo.setCouponType(model.getDiscountType().intValue());
		if(model.getDisAmount() != null)
			vo.setCouponAmount(model.getDisAmount().doubleValue());
		vo.setParkingNo(parkNo);
		vo.setDataOrigin(dataOrign);
		return vo;
	}

	//转化成线上的标准收费格式
	public FeescaleVO getFeescaleVO(ParkStandardCharge model) {
		FeescaleVO vo = null;
		try {
			vo = new FeescaleVO();
			ParkAccountType parkAccountType=parkAccountTypeMapper.getAccName(model.getCardType());
			vo.setObjectId(model.getPuid());
			vo.seteTime(model.getEndTime());
			vo.setsTime(model.getStartTime());
			vo.setPackageName(parkAccountType.getaCustname());
			vo.setPackageId(model.getCardType());
			vo.setCrossTimeType(model.getaType()+"");
			vo.setFreeTime(model.getFreeTime()+"");
			vo.setMostMoney(model.getTopMoney());
			vo.setCrossMoney(model.getaZero());
			List<SegmentChargesVO> segmentCharges=new ArrayList<>();		//计时的费用

			Field[] field = model.getClass().getDeclaredFields();			//获取当前类的所有属性
			String index="";
			for(int i = 0; i < field.length; i++){
				SegmentChargesVO chargesVO=new SegmentChargesVO();
				String name=field[i].getName();			//获取属性的名称
				field[i].setAccessible(true);
				if(name.length()>6){
					index=name.substring(6);			//截取数据之后的值
				}else{
					index=name;
				}

				if(index.matches("^[0-9]*$")){			//判断最后一位是数字，如果是，就直接保存
					Object value=field[i].get(model);
					chargesVO.setTime(Integer.parseInt(index));
					chargesVO.setCharge((Double) value);
					segmentCharges.add(chargesVO);
				}
			}
			vo.setDeleteCode(model.getDelFrag());
			vo.setSegmentCharges(segmentCharges);
			vo.setCreator("system");
			vo.setCreateTime(new Date());
			vo.setLastUpdateName("system");
			vo.setLastUpdateTime(new Date());
			vo.setParkingNo(parkNo);
			vo.setDataOrigin(dataOrign);
		} catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		return vo;
	}


	public CompanyInfo getCompanyInfo(PerCompany model) {
		if(model == null) return null;
		CompanyInfo vo = new CompanyInfo();
		vo.setCompanyName(model.getCompanyName());
		vo.setObjectId(model.getCuid());
		vo.setCreateTime(model.getCreateDate());
		vo.setCreator("system");
		vo.setLastUpdateName("system");
		vo.setLastUpdateTime(model.getModifyDate());
		vo.setDeleteCode(model.getDelFrag().intValue());
		vo.setParkingNo(parkNo);
		vo.setDataOrigin(dataOrign);
		return vo;
	}

	/**
	 * 车位组转化成
	 * @param parkCarGroup
	 * @return
	 */
	public ParkCarGroupVO convertGroup(ParkCarGroup parkCarGroup){
		ParkCarGroupVO vo=new ParkCarGroupVO();
		vo.setCarportGroupName(parkCarGroup.getPlaceName());
		vo.setCarportNumber(parkCarGroup.getPlaceNum());
		vo.setFullHold(parkCarGroup.getpType());
		vo.setRemark(parkCarGroup.getMemo());
		vo.setDeleteCode(parkCarGroup.getDelFrag());
		vo.setCreateTime(parkCarGroup.getCreateDate());
		vo.setCreator("system");
		vo.setLastUpdateName("system");
		vo.setLastUpdateTime(parkCarGroup.getModifyDate());
		vo.setParkingNo(parkNo);
		vo.setDataOrigin(dataOrign);
		vo.setObjectId(parkCarGroup.getCuid());
		return vo;
	}

	/**
	 * 超时收费转化到线上数据
	 * @param parkOverTimeSet
	 * @return
	 */
	public TimeoutFeeVO convertTimeOut(ParkOverTimeSet parkOverTimeSet){
		TimeoutFeeVO timeoutFeeVO = new TimeoutFeeVO();
		timeoutFeeVO.setObjectId(parkOverTimeSet.getPuid());
		timeoutFeeVO.setCardType(parkOverTimeSet.getCardType());
		timeoutFeeVO.setDeadTime(parkOverTimeSet.getFreeMinute());
		timeoutFeeVO.setTimeoutFee(parkOverTimeSet.getOverTimeAmount());
		timeoutFeeVO.setTimeout(parkOverTimeSet.getOverTimeMinute());
		timeoutFeeVO.setIsfree(parkOverTimeSet.getFreeInclude());
		timeoutFeeVO.setParkingNo(parkNo);
		timeoutFeeVO.setDataOrigin(dataOrign);
		return timeoutFeeVO;
	}

    /**
     * 车场授权数据的上传
     * @return
     */
	public ServerInfoVo convertServerInfo(){
		this.parkName=GlobalPark.properties.getProperty("PROJECT_NAME");
	    ServerInfoVo serverInfoVo=new ServerInfoVo();
	    serverInfoVo.setObjectId(GlobalPark.sysMap.get("cpuid")+GlobalPark.sysMap.get("mac"));
        serverInfoVo.setCpuId(GlobalPark.sysMap.get("cpuid"));
        serverInfoVo.setIpAddress(GlobalPark.sysMap.get("ip"));
        serverInfoVo.setMacAddress(GlobalPark.sysMap.get("mac"));
        serverInfoVo.setRunType("1");
        serverInfoVo.setParkingNo("");
        serverInfoVo.setParkingName(parkName);
        serverInfoVo.setDataOrigin(dataOrign);
        serverInfoVo.setDeleteCode(0);
        return serverInfoVo;
    }
}

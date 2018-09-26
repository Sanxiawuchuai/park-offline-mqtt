package com.drzk.offline.impl;

import com.drzk.charge.AbstractChargeStandard;
import com.drzk.charge.Charge;
import com.drzk.charge.standard2.StandChargeRule;
import com.drzk.charge.vo.PaymentVo;
import com.drzk.common.AESUtil;
import com.drzk.common.InOutRealTimeBase;
import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.fact.CentreRealTimeBase;
import com.drzk.fact.InRealTimeBase;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.mapper.*;
import com.drzk.offline.service.IBoxOperation;
import com.drzk.offline.vo.*;
import com.drzk.offline.vo.Head;
import com.drzk.offline.vo.SuperBody;
import com.drzk.parklib.send.MainBoardSdk;
import com.drzk.service.IParkingService;
import com.drzk.service.entity.*;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.service.impl.ParkingInServiceImpl;
import com.drzk.timer.ParkingInTask;
import com.drzk.timer.ParkingOutTask;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.SpringUtil;
import com.drzk.utils.StringUtils;
import com.drzk.vo.*;
import org.apache.log4j.Logger;
import org.kie.api.cdi.KSession;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BoxOperationImpl implements IBoxOperation {
	@Autowired
	private VwParkCarInMapper vwParkCarInMapper;
	@Autowired
	private SysLoginUserMapper sysLoginUserMapper;
	@Autowired
	private ParkSumUserMapper parkSumUserMapper;
	@Autowired
	private ParkCarInMapper parkCarInMapper;
	@Autowired
	private ParkCarOutMapper parkCarOutMapper;
	@Autowired
	private ParkLocalSetMapper parkLocalSetMapper;
	@Autowired
	private IParkingService parkingInService;
	@Autowired
	private IParkingService parkingOutService;
	@Autowired
	Charge charge;
	@Autowired
	VwParkCarIsuseMapper vwParkCarIsuseMapper;
	@Autowired
	ParkCentralChargeMapper parkCentralChargeMapper;
	
	@KSession("ksession_centre")
	private KieSession kSession;
	
	@Autowired
	private IParkingService parkingCentreService;
	@Autowired
	ParkingInServiceImpl parking;
	private static Logger logger = Logger.getLogger("userLog");


	
	/**
	 * 中央收费确认<br>
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
	@Override
	public void centrialChargePaySuccess(String jsonStr)
	{
		try {
			MainBoardMessage<Head, CentrialPayCharge> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					CentrialPayCharge.class);
			CentrialPayCharge model = messgeVo.getBody();
			Head receivehead = messgeVo.getHead();
			ReplyHead head = new ReplyHead();
			head.setMethod("centrialChargePaySuccess");
			head.setStatus("1");
			head.setMessage("缴费失败");
			ChargePaySuccess body=new ChargePaySuccess(); //
			body.setBoxIP(model.getBoxIp());
			body.setuId(model.getuId());
			if (model != null) {
				ParkLocalSet local = ParkMethod.getLocalSetByBoxIP(model.getBoxIp());
				if (local != null && local.getEquipmentID() != null) {
					ParkCentralCharge centerModel = new ParkCentralCharge();
					if (model.getChargeData() != null && model.getChargeData().getInRecord() != null
							&& model.getChargeData().getPayMentVo() != null) {
						ParkCarIn parkIn = model.getChargeData().getInRecord();
						PaymentVo payModel = model.getChargeData().getPayMentVo();
						centerModel=model.getChargeData().getCentreRecord(); //
						if (payModel != null) {
							if (payModel.getPaidFee() == null) {
								centerModel.setAccountCharge((float) 0);
							} else {
								centerModel.setAccountCharge( payModel.getPaidFee() /(float) 100);
							}
							if (payModel.getPayCharge() == null) {
								centerModel.setPayCharge((float) 0);
							} else {
								centerModel.setPayCharge( payModel.getPayCharge() /(float) 100);
							}
							centerModel.setOverTime(payModel.getLastPayTime());
						} else {
							centerModel.setAccountCharge((float) 0);
							centerModel.setPayCharge((float) 0);
						}
						centerModel.setPayChargeTime(model.getChargeData().getNowDate());
						centerModel.setPayUserName(local.getLoginName());
						centerModel.setPuid(ParkMethod.getUUID());
						centerModel.setBoxId(local.getBoxId());
						centerModel.setCardType(centerModel.getCardType());
						centerModel.setCardNo(centerModel.getCarNo());
						centerModel.setOrderNum(centerModel.getOrderNum());
						parkCentralChargeMapper.insertSelective(centerModel);
						//修改入场记录卡类型
						ParkCarIn record = new ParkCarIn();
						record.setCardType(centerModel.getCardType());
						record.setId(parkIn.getId());
						parkCarInMapper.updateByPrimaryKeySelective(record);
						//刷新當班收費
						parkingCentreService.centreStatisticsRefresh(centerModel);
						parkingCentreService.centreSumUser(centerModel,model.getBoxIp());
						head.setStatus("0");
						head.setMessage("缴费成功");
						body.setuId(model.getuId());
					}
				}
				MainBoardMessage<ReplyHead, ChargePaySuccess> returnInfo = new MainBoardMessage<>(head, body);
				String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
				MqttServiceImpl.sendMessage(local.getEquipmentID(),receivehead.getReplyTopic(), jsonBody, null, 3);
			}
		} catch (Exception ex) {
			logger.error("中央收费确认:", ex);
		}
	}

	/**
	 * 中央缴费<br>
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
	@Override
	public void centrialCharge(String jsonStr)
	{
		try {
			MainBoardMessage<Head, CentrailChargeVO> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					CentrailChargeVO.class);
			CentrailChargeVO model = messgeVo.getBody();
			Head receivehead = messgeVo.getHead();
			if (model != null) {
				CentreRealTimeBase center=new CentreRealTimeBase();
				center.setInRecord(model.getParkCarIn());
				center.setCarNo(model.getParkCarIn().getCarNo());
				center.setNowDate(new Date());
				center.setNextStep(Step.START);
				center.setCentreRecord(new ParkCentralCharge());
				center.getPayMentVo().setPayType(1);
				kSession.setGlobal("parkCentreService", parkingCentreService);
				kSession.insert(center);
				kSession.fireAllRules(); //执行规则
				ParkLocalSet local = ParkMethod.getLocalSetByBoxIP(model.getBoxIP());
				if (local != null ) { //&& local.getEquipmentID() != null
					ReplyHead head = new ReplyHead();
					CentrialPayCharge body = new CentrialPayCharge();
					//取中央收费处理结果
					Object isSuccess =center.getStatusMap().get("isSuccess");
					if (isSuccess == null) {
						head.setMethod("centrialCharge");
						head.setStatus("1");
					} else {
						if (isSuccess.equals(0)) { // 有收费
							head.setMethod("centrialCharge");
							head.setStatus("0");
							body.setBoxIp(model.getBoxIP());
							body.setChargeData(center);
							body.setEquipmentID(model.getEquipmentID());
						} else { // 无收费
							head.setMethod("centrialCharge");
							head.setStatus("1");
							head.setMessage((String) center.getStatusMap().get("Msg"));
						}
					}
					MainBoardMessage<ReplyHead, CentrialPayCharge> returnInfo = new MainBoardMessage<>(head, body);
					String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
					MqttServiceImpl.sendMessage(local.getEquipmentID(),receivehead.getReplyTopic(), jsonBody, null, 3);
				}
			}
		} catch (Exception ex) {
			logger.error("中央缴费:",ex);
		}
	}

	/**
	 * 收费改变<br>
	 * @author tanfei
	 * @since JDK 1.8
	 */
	@Override
	public void boxPayCharge(String jsonStr)
	{
		try {
			System.out.println(jsonStr);
			MainBoardMessage<Head, boxPayChargeVO> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					boxPayChargeVO.class);
			boxPayChargeVO model = messgeVo.getBody();
			Head receivehead = messgeVo.getHead();
			boxPayChargeReturn retBody = new boxPayChargeReturn();
			retBody.setuId(model.getuId());
			retBody.setType(model.getType());
			if (model != null) {
				if (model.getType() == 0)// 出口收费
				{
					System.out.println("出口收费");
					OutRealTimeBase chargeData = model.getOutRealTimeBase();
					AbstractChargeStandard mode = SpringUtil.getBean(StandChargeRule.class);
					PaymentVo money = chargeData.getPayMentVo();
					switch (model.getChangeType()) {
					case 1: // 车型改变
						money = charge.getFeeByCarNo(chargeData.getPayMentVo(), mode,
								chargeData.getPayMentVo().getCarNo());
						String cardTypeName = String.valueOf(chargeData.getPayMentVo().getCarRealType());
						ParkAccountType parkAccountType = ParkMethod
								.getParkCardType(chargeData.getPayMentVo().getCarRealType());
						if (parkAccountType != null)
							cardTypeName = parkAccountType.getaCustname();
						chargeData.getPayMentVo().setRemark("车辆类型改变为:" + cardTypeName);
						chargeData.getOut().setCardType(chargeData.getPayMentVo().getCarRealType());
						System.out.println("车类型改变");
						break;
					case 2: // 优惠
						money = charge.getDisCountFee(chargeData, mode, chargeData.getPayMentVo().getDiscountID(),
								chargeData.getPayMentVo().getPayOutTime());
						chargeData.getPayMentVo().setRemark("优惠金额:" + (money.getDisAmount() / 100));
						chargeData.getOut().setDiscountNo(chargeData.getPayMentVo().getDiscountID());
						chargeData.getOut().setTypeId(chargeData.getPayMentVo().getTypeId());
						chargeData.getOut().setDiscountTime(new Date());
						System.out.println("优惠");
						break;
					case 3: // 免费
						money.setPaidFee(0);
						money.setDisAmount(chargeData.getPayMentVo().getPayCharge());
						money.setPayCharge(0);
						chargeData.getPayMentVo().setRemark("特殊车辆免费");
						chargeData.getOut().setCardType(chargeData.getPayMentVo().getCarRealType());
						chargeData.getOut().setFreeType(chargeData.getPayMentVo().getFreeType());
						money.setCarRealType(chargeData.getPayMentVo().getCarRealType());
						String freeTypeName = String.valueOf(chargeData.getPayMentVo().getFreeType());
						ParkFreeType parkFreeType = ParkMethod.getFreeType(chargeData.getOut().getFreeType());
						if (parkFreeType != null)
							freeTypeName = parkFreeType.getFreeName();
						chargeData.getOut().setMemo(freeTypeName);
						System.out.println("免费");
						break;
					default:
						money = chargeData.getPayMentVo();
						break;
					}
					chargeData.setPayMentVo(money);
					if (GlobalPark.outChannelEvent.containsKey(model.getControlIP())) {
						GlobalPark.outChannelEvent.remove(model.getControlIP());
					}
					GlobalPark.outChannelEvent.put(model.getControlIP(), chargeData);
					retBody.setChargeData(chargeData);
				} else {
					System.out.println("中央收费");
					CentreRealTimeBase chargeData = model.getCentreRealTimeBase();
					AbstractChargeStandard mode = SpringUtil.getBean(StandChargeRule.class);
					PaymentVo money = chargeData.getPayMentVo();
					switch (model.getChangeType()) {
					case 1: // 车型改变
						money = charge.getFeeByCarNo(chargeData.getPayMentVo(), mode, chargeData.getCarNo());
						String cardTypeName = String.valueOf(chargeData.getPayMentVo().getCarRealType());
						ParkAccountType parkAccountType = ParkMethod
								.getParkCardType(chargeData.getPayMentVo().getCarRealType());
						if (parkAccountType != null)
							cardTypeName = parkAccountType.getaCustname();
						chargeData.getPayMentVo().setRemark("车辆类型改变为:" + cardTypeName);
						chargeData.getCentreRecord().setCardType(chargeData.getPayMentVo().getCarRealType());
						System.out.println("车类型改变");
						break;
					case 2: // 优惠
						/*
						money = charge.getDisCountFee(chargeData, mode, chargeData.getPayMentVo().getDiscountID(), chargeData.getPayMentVo().getPayOutTime());
						chargeData.getPayMentVo().setRemark("优惠金额:" + (money.getDisAmount()/100));
						chargeData.getCentreRecord().setDiscountNo(chargeData.getPayMentVo().getDiscountID());
						chargeData.getCentreRecord().setTypeId(chargeData.getPayMentVo().getTypeId());
						chargeData.getCentreRecord().setDiscountTime(new Date());
						System.out.println("优惠");
						*/
						break;
					case 3: // 免费
						money.setPaidFee(0);
						money.setDisAmount(chargeData.getPayMentVo().getPayCharge());
						money.setPayCharge(0);
						chargeData.getPayMentVo().setRemark("特殊车辆免费");
						chargeData.getCentreRecord().setCardType(chargeData.getPayMentVo().getCarRealType());
						chargeData.getCentreRecord().setFreeType(chargeData.getPayMentVo().getFreeType());
						money.setCarRealType(chargeData.getPayMentVo().getCarRealType());
						String freeTypeName = String.valueOf(chargeData.getPayMentVo().getFreeType());
						ParkFreeType parkFreeType = ParkMethod.getFreeType(chargeData.getCentreRecord().getFreeType());
						if (parkFreeType != null)
							freeTypeName = parkFreeType.getFreeName();
						chargeData.getCentreRecord().setMemo(freeTypeName);
						break;
					default:
						money = chargeData.getPayMentVo();
						break;
					}
					chargeData.setPayMentVo(money);
					retBody.setChargeData(chargeData);
				}
				ReplyHead head = new ReplyHead();
				head.setMethod("boxPayCharge");
				head.setStatus("0");
				MainBoardMessage<ReplyHead, boxPayChargeReturn> returnInfo = new MainBoardMessage<>(head, retBody);
				String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
				System.out.println(jsonBody);
				MqttServiceImpl.sendMessage(model.getEquipmentID(), receivehead.getReplyTopic(), jsonBody, null, 3);
			} 
		} catch (Exception ex) {
			logger.error("收费改变:",ex);
		}
	}
	
	
	/*
	 * 岗亭登录
	 * 
	 * @see com.drzk.offline.service.IBoxOperation#boxLogin(com.drzk.offline.vo.
	 * BoxLoginBody, com.drzk.offline.vo.Head)
	 */
	@Override
	public void boxLogin(String jsonStr) {

		try {
			MainBoardMessage<Head, BoxLoginBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxLoginBody.class);

			BoxLoginBody model = messgeVo.getBody();
			Head receivehead = messgeVo.getHead();

			ReplyHead head = new ReplyHead();
			head.setStatus("1");
			head.setMethod(receivehead.getMethod());
			BoxLoginReturn body = new BoxLoginReturn();

			if (!StringUtils.isNullOrEempty(model.getUserName())) {
				String pwd = null;
				if (!StringUtils.isNullOrEempty(model.getUserPwd()))
					pwd = AESUtil.encryptSHA(model.getUserPwd());
				SysLoginUser sysLoginModel = sysLoginUserMapper.selectByNameAndPwd(model.getUserName(), pwd);
				if (sysLoginModel == null) {
					head.setMessage("用户名或密码错误");
				}else {
					if (sysLoginModel.getIsStop() == 0) {
						head.setMessage("该用户已停用");
					}else if(sysLoginModel.getIsLoginBox()==0){
						head.setMessage("该用户没有登录的权限");
					}else {
						// 判断岗亭是否存在
						ParkLocalSet parkLocalSet = null;

						for (ParkLocalSet modellocal : GlobalPark.parkLocalSet) {
							if (modellocal.getBoxIp()!=null&&modellocal.getBoxIp().equals(model.getBoxIP())) {
								parkLocalSet = modellocal;
								break;
							}
						}
						if (parkLocalSet == null) {
							head.setMessage("该岗亭不存在");
						} else {
							// 登录成功
							head.setStatus("0");
							ParkSumUser sumUser= boxLoginCheck(parkLocalSet.getBoxId(), model.getUserName());
							body = getLoginParam(model, parkLocalSet.getBoxId(),sumUser.getLoginDate());
							pushSumUser(sumUser,parkLocalSet);
						}
					}
				}
			} else {
				head.setMessage("用户名不能为空");
			}
			MainBoardMessage<ReplyHead, BoxLoginReturn> returnInfo = new MainBoardMessage<>(head, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttServiceImpl.sendMessage(model.getEquipmentMac(),receivehead.getReplyTopic(), jsonBody, null, 3);
		} catch (Exception ex) {
			logger.error("岗亭登录:",ex);
		}
	}
	
	/**   
	  * 推送当前收费<br>
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
	private void pushSumUser(ParkSumUser sumUser,ParkLocalSet parkLocalSet) {
		try {
			Head head = new Head();
			head.setMethod("parkSumUser");
			ParkSumUserBody body = new ParkSumUserBody();
			body.setSunUser(sumUser);
			body.setuId(ParkMethod.getUUID());
			MainBoardMessage<Head, ParkSumUserBody> returnInfo = new MainBoardMessage<>(head, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			if (parkLocalSet != null && parkLocalSet.getOnline() == 1 && parkLocalSet.getEquipmentID() != null) {
				String topic = String.format(TopicsDefine.BOX_ERROR, parkLocalSet.getEquipmentID());
				MqttServiceImpl.sendMessage(parkLocalSet.getEquipmentID(),topic, jsonBody, null, 0);
			}
		}catch (Exception ex) {
			logger.error("推送当前收费",ex);
		}
	}
	
	private BoxLoginReturn getLoginParam(BoxLoginBody model, int boxId ,Date loginDate) {
		BoxLoginReturn body = new BoxLoginReturn();
		body.setuId(model.getuId());
		body.setLoginTime(loginDate);
		for (int k = 0; k < GlobalPark.parkLocalSet.size(); k++) {
			if (GlobalPark.parkLocalSet.get(k) != null
					&& GlobalPark.parkLocalSet.get(k).getBoxIp().equals(model.getBoxIP())) {
				body.setParkLocalSet(GlobalPark.parkLocalSet.get(k));
				GlobalPark.parkLocalSet.get(k).setOnline( 1);
				GlobalPark.parkLocalSet.get(k).setLoginName(model.getUserName());
				GlobalPark.parkLocalSet.get(k).setEquipmentID(model.getEquipmentMac());
				GlobalPark.parkLocalSet.get(k).setLoginDate(loginDate);
				// 更新岗亭用户状态
				ParkLocalSet parkLocalSetModel = new ParkLocalSet();
				parkLocalSetModel.setOnline(1);
				parkLocalSetModel.setLoginName(model.getUserName());
//				parkLocalSetModel.setEquipmentID(model.getEquipmentMac());
				parkLocalSetModel.setBoxId(boxId);
				parkLocalSetMapper.updateOnlineState(parkLocalSetModel);
				break;
			}
		}
		List<ParkChannelSet> controlSet = new ArrayList<ParkChannelSet>();
		for (int h = 0; h < GlobalPark.parkContset.size(); h++) {
			if (GlobalPark.parkContset.get(h) != null && GlobalPark.parkContset.get(h).getBoxId() == boxId) {
				controlSet.add(GlobalPark.parkContset.get(h));
			}
		}
		body.setParkContset(controlSet);

		List<ParkCamSet> camSet = new ArrayList<ParkCamSet>();
		for (int q = 0; q < GlobalPark.parkCamset.size(); q++) {
			if (GlobalPark.parkCamset.get(q) != null && GlobalPark.parkCamset.get(q).getBoxId() == boxId)
				camSet.add(GlobalPark.parkCamset.get(q));
		}

		body.setParkCamSet(camSet);
		body.setParkAccountType(GlobalPark.parkAccountType);
		body.setParkFreeType(GlobalPark.parkFreeType);
		body.setParkEquipments(GlobalPark.parkEquipments);
		body.setParkDisInfo(GlobalPark.parkDisInfo); //
		body.setParkSysSet(GlobalPark.properties);  //车场参数

		return body;
	}

	/*
	 * 处理交班事务
	 */
	private ParkSumUser boxLoginCheck(Integer boxId, String loginName) {
		ParkSumUser model = new ParkSumUser();
		model.setBoxId(boxId);
		Date tDate=new Date();
		ParkSumUser sumUser=new ParkSumUser();
		List<ParkSumUser> parkSumUserList = parkSumUserMapper.selectByCondition(model);
		if (parkSumUserList != null && parkSumUserList.size() > 0) {
			// 之前有登陆，且接班操作员为空
			if (parkSumUserList.get(0).getReliefUserName() == null) {
				if (parkSumUserList.get(0).getUserName().equals(loginName)) {
					sumUser=parkSumUserList.get(0);
				} else {
					parkSumUserList.get(0).setReliefUserName(loginName);
					parkSumUserList.get(0).setReliefDate(tDate);
					parkSumUserList.get(0).setIsLoad((byte) 0);
					
					parkSumUserMapper.updateByPrimaryKeySelective(parkSumUserList.get(0));
					ParkSumUser modelmody = new ParkSumUser();
					modelmody.setBoxId(boxId);
					modelmody.setUserName(loginName);
					modelmody.setLoginDate(tDate);
					modelmody.setPuid(ParkMethod.getUUID());
					parkSumUserMapper.insert(modelmody);///////////////
					sumUser=modelmody;
				}
			} else {
				ParkSumUser modelmody = new ParkSumUser();
				modelmody.setBoxId(boxId);
				modelmody.setUserName(loginName);
				modelmody.setLoginDate(tDate);
				modelmody.setPuid(ParkMethod.getUUID());
				parkSumUserMapper.insert(modelmody);//////////////
				sumUser=modelmody;
			}
		} else {
			// 第一次登陆
			ParkSumUser modelmody = new ParkSumUser();
			modelmody.setBoxId(boxId);
			modelmody.setUserName(loginName);
			modelmody.setLoginDate(tDate);
			modelmody.setPuid(ParkMethod.getUUID());
			parkSumUserMapper.insert(modelmody);
			sumUser=modelmody;
		}
		return sumUser;
	}
	
	/*
	 * 岗亭交班
	 * 
	 * @see
	 * com.drzk.offline.service.IBoxOperation#boxChangeshifts(com.drzk.offline.vo.
	 * BoxChangeshiftsBody, com.drzk.offline.vo.Head)
	 */
	@Override
	public void boxChangeshifts(String jsonStr) {

		try {
			MainBoardMessage<Head, BoxChangeshiftsBody> messages = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxChangeshiftsBody.class);
			BoxChangeshiftsBody model = messages.getBody();
			Head receivehead = messages.getHead();

			ReplyHead head = new ReplyHead();
			head.setStatus("1");
			head.setMethod(receivehead.getMethod());
			SuperBody body = new SuperBody();

			if (!StringUtils.isNullOrEempty(model.GetNewUserName())) {
				String pwd = null;
				if (!StringUtils.isNullOrEempty(model.GetNewUserPwd()))
					pwd = AESUtil.encryptSHA(model.GetNewUserPwd());
				SysLoginUser sysLoginModel = sysLoginUserMapper.selectByNameAndPwd(model.GetNewUserName(), pwd);
				if (sysLoginModel == null) {
					head.setMessage("用户名或密码错误");
				} else {
					if (sysLoginModel.getIsStop() == 0) {
						head.setMessage("该用户已停用");
					} else {
						// 判断岗亭是否存在
						ParkLocalSet parkLocalSet = null;
						for (ParkLocalSet modellocal : GlobalPark.parkLocalSet) {
							if (modellocal.getBoxIp().equals(model.getBoxIP())) {
								parkLocalSet = modellocal;
								break;
							}
						}
						if (parkLocalSet == null) {
							head.setMessage("该岗亭不存在");
						} else {
							ReplyHead ret = isOldUser(model.GetOldUserName(), model.GetOldUserPwd(),
									parkLocalSet.getBoxId());
							if (ret.getStatus() == "0") {
								// 登录成功
								head.setStatus("0");
								ParkSumUser sumUser= boxLoginCheck(parkLocalSet.getBoxId(), model.GetNewUserName());
								setLogin(parkLocalSet.getBoxIp(),model.GetNewUserName(),model.getEquipmentMac(),sumUser.getLoginDate(),parkLocalSet.getBoxId());
							} else {
								head.setMessage(ret.getMessage());
							}
						}
					}
				}
			} else {
				head.setMessage("用户名不能为空");
			}
			MainBoardMessage<ReplyHead, SuperBody> returnInfo = new MainBoardMessage<>(head, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttServiceImpl.sendMessage(model.getEquipmentMac(),receivehead.getReplyTopic(), jsonBody, null, 3);
		} catch (Exception ex) {
			logger.error("岗亭交班:",ex);
		}
	}
	
	/**
	 * 登出
	 * wangchengxi
	 */
	private void setLogin(String boxIP,String loignName,String equipmentMac,Date loginDate,int boxId) {
		for (int k = 0; k < GlobalPark.parkLocalSet.size(); k++) {
			if (GlobalPark.parkLocalSet.get(k) != null
					&& GlobalPark.parkLocalSet.get(k).getBoxIp().equals(boxIP) ){
				GlobalPark.parkLocalSet.get(k).setLoginName(loignName);
				GlobalPark.parkLocalSet.get(k).setEquipmentID(equipmentMac);
				GlobalPark.parkLocalSet.get(k).setLoginDate(loginDate);
				// 更新岗亭用户状态
				ParkLocalSet parkLocalSetModel = new ParkLocalSet();
				parkLocalSetModel.setOnline( 1);
				parkLocalSetModel.setLoginName(loignName);
				parkLocalSetModel.setBoxId(boxId);
				parkLocalSetMapper.updateOnlineState(parkLocalSetModel);
				break;
			}
		}
	}
	
	
	/**
	 * 登出
	 */
	@Override
	public void boxLogout(String jsonStr) {
		try {
			MainBoardMessage<Head, BoxLoginBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxLoginBody.class);
			BoxLoginBody model = messgeVo.getBody();
			// Head head = messgeVo.getHead();
			String pwd = null;
			if (!StringUtils.isNullOrEempty(model.getUserPwd()))
				pwd = AESUtil.encryptSHA(model.getUserPwd());
			SysLoginUser sysLoginModel = sysLoginUserMapper.selectByNameAndPwd(model.getUserName(), pwd);
			if (sysLoginModel != null) {
				for (int k = 0; k < GlobalPark.parkLocalSet.size(); k++) {
					if (GlobalPark.parkLocalSet.get(k) != null
							&& GlobalPark.parkLocalSet.get(k).getBoxIp().equals(model.getBoxIP())) {
						//GlobalPark.parkLocalSet.get(k).setLoginName(null);
						GlobalPark.parkLocalSet.get(k).setOnline( 0);
						GlobalPark.parkLocalSet.get(k).setEquipmentID(null);
						// 更新岗亭用户状态
						ParkLocalSet parkLocalSetModel = new ParkLocalSet();
						parkLocalSetModel.setOnline( 0);
						//parkLocalSetModel.setLoginName(null);
						parkLocalSetModel.setBoxId(GlobalPark.parkLocalSet.get(k).getBoxId());
						parkLocalSetMapper.updateOnlineState(parkLocalSetModel);
						break;
					}
				}
			}
		} catch (Exception ex) {
			logger.error("登出:",ex);
		}
	}

	/**
	 * 软件触发相机
	 */
	@Override
	public void boxReshoot(String jsonStr) {
		try {
			MainBoardMessage<Head, BoxReshootBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxReshootBody.class);
			BoxReshootBody model = messgeVo.getBody();
			ParkChannelSet channel = ParkMethod.getChannelSetByIp(model.getConIp());
			if (channel != null) {
				Integer[] conIndex = IsBoxOnlineByControlIP(channel.getChannelIp());
				if (conIndex[1] >= 0) {
					HandCapBody body = new HandCapBody();
					body.setuId(ParkMethod.getUUID());
					body.setEquipmentID(channel.getDsn());
					MainBoardSdk.sendAndGet(channel.getDsn(), "handCap", body, com.drzk.service.entity.SuperBody.class);
				}
			}
		} catch (Exception ex) {
			logger.error("软件触发相机",ex);
		}
	}

	

	/**
	 * 手工入场
	 */
	@Override
	public void boxHandIn(String jsonStr) {

		try {
			MainBoardMessage<Head, BoxHandInBody> messages = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxHandInBody.class);
			BoxHandInBody model = messages.getBody();
			//Head receivehead = messages.getHead();
			Integer[] conIndex = IsBoxOnlineByControlIP(model.getControlIP());
			if (conIndex[0] >= 0 && conIndex[1] >= 0) {
				InRealTimeBase inRealTimeBase = new InRealTimeBase();
				inRealTimeBase.setDeal(true);
				ParkCarIn in = new ParkCarIn();
				in.setYktId(0);
				in.setCardType(InOutRealTimeBase.TEMP_CAR_A);
				in.setEmpName("临时用户");
				in.setCarNo(model.getCarNo());
				in.setInTime(new Date());
				in.setMachNo(GlobalPark.parkContset.get(conIndex[0]).getMachNo());
				in.setInWay(InOutRealTimeBase.IN_OUT_HAND);
				in.setCarNoType(Integer.parseInt(model.getCarNoType()));
				in.setGuid(ParkMethod.getUUID());
				String picPath = getpicPath(model.getControlIP());
				in.setInPic(picPath);
				in.setCarNo(model.getCarNo());
				in.setcFlag(4);
				in.setCardId(ParkMethod.CarNotoCardID(model.getCarNo()));
				in.setInUserName(ParkMethod.getLoginName(GlobalPark.parkContset.get(conIndex[0]).getBoxId()));
				inRealTimeBase.setIn(in);
				inRealTimeBase.setCarNo(model.getCarNo());
				inRealTimeBase.setChannelSet(GlobalPark.parkContset.get(conIndex[0]));
				inRealTimeBase.setInOrOutFlag(true);
				inRealTimeBase.setNowDate(new Date());
				inRealTimeBase.setNextStep(Step.START);
				if (GlobalPark.inChannelEvent.containsKey(model.getControlIP())) {
					GlobalPark.inChannelEvent.remove(model.getControlIP());
				}
				GlobalPark.inChannelEvent.put(model.getControlIP(), inRealTimeBase);
				ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
				ParkingInTask parkingInTask = SpringUtil.getBean(ParkingInTask.class);
				parkingInTask.setParkIn(inRealTimeBase);
				threadPool.execute(parkingInTask);

			}
		} catch (Exception ex) {
			logger.error("手工入场:",ex);
		}
	}

	/**
	 * 手工 出场
	 */
	@Override
	public void boxHandOut(String jsonStr) {

		try {
			MainBoardMessage<Head, BoxHandOutBody> messages = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxHandOutBody.class);
			BoxHandOutBody model = messages.getBody();
			//Head head = messages.getHead();
			Integer[] conIndex = IsBoxOnlineByControlIP(model.getControlIP());
			if (conIndex[0] >= 0 && conIndex[1] >= 0) {
				OutRealTimeBase outRealTimeBase = new OutRealTimeBase();
				outRealTimeBase.setDeal(true);
				outRealTimeBase.setInOrOutFlag(false);
				outRealTimeBase.setNextStep(Step.START);
				outRealTimeBase.setCarNo(model.getCarNo());
				outRealTimeBase.setChannelSet(GlobalPark.parkContset.get(conIndex[0]));
				outRealTimeBase.setNowDate(new Date());
				ParkCarOut out = new ParkCarOut();
				out.setOutMachNo(GlobalPark.parkContset.get(conIndex[0]).getMachNo());
				String picPath = getpicPath(model.getControlIP());
				out.setOutPic(picPath);
				//out.setOutWay((byte) 1);
				out.setOutWay(InOutRealTimeBase.IN_OUT_HAND);
				out.setOutCarNo(model.getCarNo());
				out.setGuid(ParkMethod.getUUID());
				out.setcFlag(4);
				out.setOutTime(new Date());
				out.setCardId(ParkMethod.CarNotoCardID(model.getCarNo()));
				out.setOutUserName(ParkMethod.getLoginName(GlobalPark.parkContset.get(conIndex[0]).getBoxId()));
				outRealTimeBase.setOut(out);

				if (model.getID() != null) {
					ParkCarIn inModel = parkCarInMapper.selectByPrimaryKey(Integer.parseInt(model.getID()));
					outRealTimeBase.setInRecord(inModel);
				}
				// UpParkEventBody outModel = new UpParkEventBody();
				// outModel.setEquipmentID(model.getEquipmentID());
				// outModel.setConIp(model.getControlIP());
				// outModel.setRecordNo(model.getuId());
				// outModel.setEventTime(new Date().toString());
				// out.setRecord(outModel);
				if (GlobalPark.outChannelEvent.containsKey(model.getControlIP())) {
					GlobalPark.outChannelEvent.remove(model.getControlIP());
				}
				GlobalPark.outChannelEvent.put(model.getControlIP(), outRealTimeBase);
				ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
				ParkingOutTask parkingOutTask = SpringUtil.getBean(ParkingOutTask.class);
				parkingOutTask.setParkOut(outRealTimeBase);
				threadPool.execute(parkingOutTask);
			}
		} catch (Exception ex) {
			logger.error("手工出场:",ex);
		}
	}

	/**
	 * 电脑开闸
	 */
	@Override
	public void boxComputerGate(String jsonStr) {

		try {
			MainBoardMessage<Head, BoxComputerGateBody> messages = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxComputerGateBody.class);
			BoxComputerGateBody model = messages.getBody();
			Head receivehead = messages.getHead();

			ReplyHead head = new ReplyHead();
			head.setStatus("1");
			head.setMethod(receivehead.getMethod());
			SuperBody body = new SuperBody();
			Date tDate=new Date();
			Integer[] conIndex = IsBoxOnlineByControlIP(model.getControlIP());
			if (conIndex[0] >= 0 && conIndex[1] >= 0) {
				if (model != null && model.getType().equals("0")) {
					int inOutType = GlobalPark.parkContset.get(conIndex[0]).getInOut();
					ParkCarOut parkCarOutModel = new ParkCarOut();
					parkCarOutModel.setInTime(tDate);
					parkCarOutModel.setOutTime(tDate);
					parkCarOutModel.setDiscountTime(tDate);
					int machno = Byte.parseByte(GlobalPark.parkContset.get(conIndex[0]).getMachNo().toString());
					parkCarOutModel.setInMachNo(machno);
					parkCarOutModel.setOutMachNo(machno);
					parkCarOutModel.setInUserName(GlobalPark.parkLocalSet.get(conIndex[1]).getLoginName());
					parkCarOutModel.setOutUserName(GlobalPark.parkLocalSet.get(conIndex[1]).getLoginName());
					parkCarOutModel.setYktId(0);
					parkCarOutModel.setInCarNo(model.getCarNo());
					parkCarOutModel.setOutCarNo(model.getCarNo());
					parkCarOutModel.setCardType( 2);
					parkCarOutModel.setMemo("车闸故障");
					parkCarOutModel.setCardId(ParkMethod.CarNotoCardID(model.getCarNo()));
					parkCarOutModel.setGuid(ParkMethod.getUUID());
					parkCarOutMapper.insert(parkCarOutModel);

					if (inOutType == 0 || inOutType == 4 || inOutType == 6 || inOutType == 2) {
						// 入场流水
						InRealTimeBase inRealTimeBase = new InRealTimeBase();
						ParkCarIn in = new ParkCarIn();
						in.setCarNo(model.getCarNo());
						in.setMachNo(GlobalPark.parkContset.get(conIndex[0]).getMachNo());
						in.setInTime(tDate);
						in.setCardType(2);
						inRealTimeBase.setIn(in);
						inRealTimeBase.setChannelSet(GlobalPark.parkContset.get(conIndex[0]));
						parkingInService.sendParkCarInRecordToBox(inRealTimeBase);
					} else {
						// 出场流水
						OutRealTimeBase outRealTimeBase = new OutRealTimeBase();
						outRealTimeBase.setOut(parkCarOutModel);
						outRealTimeBase.setChannelSet(GlobalPark.parkContset.get(conIndex[0]));
						parkingOutService.sendParkCarOutRecordToBox(outRealTimeBase);
					}
				}
				// 控制道闸
				GateBody bodyGate = new GateBody();
				bodyGate.setOrder(model.getType());
				//MainBoardMessage<ReplyHead, GateBodyReturn> replyVo = MainBoardSdk.sendAndGet(model.getEquipmentID(), "roadGateControl", bodyGate, GateBodyReturn.class);
				head.setStatus("0");
			} else {
				head.setMessage("岗亭不在线或岗亭不存在");
			}
			MainBoardMessage<ReplyHead, SuperBody> returnInfo = new MainBoardMessage<>(head, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttServiceImpl.sendMessage(model.getEquipmentID(),receivehead.getReplyTopic(), jsonBody, null, 3);
		} catch (Exception ex) {
			logger.error("电脑开闸:",ex);
		}
	}

	/**
	 * 高峰模式设置
	 */

	@Override
	public void boxPeakMode(String jsonStr) {
		try {
			MainBoardMessage<Head, BoxPeakModeBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxPeakModeBody.class);
			BoxPeakModeBody model = messgeVo.getBody();
			Head head = messgeVo.getHead();
			ReplyHead retHead = new ReplyHead();
			retHead.setStatus("1");
			SuperBody body = new SuperBody();
			body.setuId(model.getuId());
			Integer[] conIndex = IsBoxOnlineByControlIP(model.getControlIP());
			if (conIndex[0] >= 0 && conIndex[1] >= 0) {
				GateBody bodyGate = new GateBody();
				if (model.getType().equals("0"))
					bodyGate.setOrder("4");
				else
					bodyGate.setOrder("5");
				bodyGate.setOrder(model.getType());
				MainBoardMessage<ReplyHead, GateBodyReturn> replyVo = MainBoardSdk.sendAndGet(model.getEquipmentID(),
						"roadGateControl", bodyGate, GateBodyReturn.class);
				if (replyVo == null || !"0".equals(replyVo.getHead().getStatus())) {
					retHead.setStatus("1");
				} else
					retHead.setStatus("0");
			}
			MainBoardMessage<ReplyHead, SuperBody> returnInfo = new MainBoardMessage<>(retHead, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttServiceImpl.sendMessage(model.getEquipmentID(),head.getReplyTopic(), jsonBody, null, 3);
		} catch (Exception ex) {
			logger.error("高峰模式设置:",ex);
		}
	}

	/**
	 * 1.入场确认开闸岗亭返回 2.入场校正推送
	 * 
	 * @param jsonStr
	 */
	@Override
	public void boxInIsOpen(String jsonStr) {
		try {
			MainBoardMessage<Head, BoxInIsOpenBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxInIsOpenBody.class);
			BoxInIsOpenBody model = messgeVo.getBody();
			//Head head = messgeVo.getHead();
			if (model != null && model.getType().equals("0")) {
				Integer[] conIndex = IsBoxOnlineByControlIP(model.getControlIP());
				if (conIndex[0] >= 0 && conIndex[0] >= 0) {
					InRealTimeBase inRealTimeBase = model.getInRecord();
					if(inRealTimeBase.getStatusMap().containsKey("method") && inRealTimeBase.getStatusMap().get("method").equals("parkInChanelCorrecting")) {
						inRealTimeBase.setNextStep(Step.START);
						String carNo = inRealTimeBase.getCarNo();
						if(!StringUtils.isNullOrEempty(carNo)) {
							inRealTimeBase.getIn().setCarNo(carNo);
							inRealTimeBase.getIn().setCardId(ParkMethod.CarNotoCardID(carNo));
						}
						inRealTimeBase.getIn().setInWay(InOutRealTimeBase.IN_OUT_CORRECTING);
						inRealTimeBase.setDeal(true);
						inRealTimeBase.getStatusMap().remove("method");
					}else {
						inRealTimeBase.setNextStep(Step.SAVE);
					}
					
					if (GlobalPark.inChannelEvent.containsKey(model.getControlIP())) {
						GlobalPark.inChannelEvent.remove(model.getControlIP());
					}
//					GlobalPark.inChannelEvent.put(model.getControlIP(), inRealTimeBase);
					ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
					ParkingInTask parkingInTask = SpringUtil.getBean(ParkingInTask.class);
					parkingInTask.setParkIn(inRealTimeBase);
					threadPool.execute(parkingInTask);
				}
			} else {
				if (GlobalPark.inChannelEvent.containsKey(model.getControlIP())) {
					GlobalPark.inChannelEvent.remove(model.getControlIP());
				}
			}
		} catch (Exception ex) {
			logger.error("入场确认开闸:",ex);
		}
	}

	/**
	 * 入场校正车牌
	 * 
	 * @param jsonStr
	 */
	@Override
	public void boxInChanelCorrecting(String jsonStr) {
		try {
			MainBoardMessage<Head, BoxInIsOpenBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxInIsOpenBody.class);
			BoxInIsOpenBody model = messgeVo.getBody();
			//Head head = messgeVo.getHead();
			if (model != null && model.getType().equals("0")) {
				Integer[] conIndex = IsBoxOnlineByControlIP(model.getControlIP());
				if (conIndex[0] >= 0 && conIndex[0] >= 0) {
					InRealTimeBase inRealTimeBase = new InRealTimeBase();
					inRealTimeBase.setDeal(true);
					inRealTimeBase.setCarNo(model.getInRecord().getCarNo());
					inRealTimeBase.setInOrOutFlag(true);
					ParkChannelSet channel = ParkMethod.getChannelSetByIp(model.getControlIP());
					inRealTimeBase.setChannelSet(channel);
					inRealTimeBase.setNextStep(Step.START);
					if (GlobalPark.inChannelEvent.containsKey(model.getControlIP())) {
						GlobalPark.inChannelEvent.remove(model.getControlIP());
					}
					GlobalPark.inChannelEvent.put(model.getControlIP(), inRealTimeBase);
					ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
					ParkingInTask parkingInTask = SpringUtil.getBean(ParkingInTask.class);
					parkingInTask.setParkIn(inRealTimeBase);
					threadPool.execute(parkingInTask);
				}
			} else {
				if (GlobalPark.inChannelEvent.containsKey(model.getControlIP())) {
					GlobalPark.inChannelEvent.remove(model.getControlIP());
				}
			}
		} catch (Exception ex) {
			logger.error("入场校正车牌:",ex);
		}
	}

	/**
	 * 出场确认开闸
	 * 
	 * @param jsonStr
	 */
	@Override
	public void boxOutIsOpen(String jsonStr) {
		ReplyHead replyHead = new ReplyHead();
		MainBoardMessage<Head, BoxOutIsOpenBody> messages = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
				BoxOutIsOpenBody.class);
		replyHead.setMethod(messages.getHead().getMethod());
		String retTopic = messages.getHead().getReplyTopic();
		String uId = messages.getBody().getuId();
		BoxOutIsOpenBody model = messages.getBody();
		try {
			if (model != null && model.getType().equals("0")) {
				Integer[] conIndex = IsBoxOnlineByControlIP(model.getControlIP());
				if (conIndex[0] >= 0 && conIndex[0] >= 0) {
					OutRealTimeBase out = model.getOutRecord();
					out.setNextStep(Step.SAVE);
					if (GlobalPark.outChannelEvent.containsKey(model.getControlIP())) {
						GlobalPark.outChannelEvent.remove(model.getControlIP());
					}
//					GlobalPark.outChannelEvent.put(model.getControlIP(), out);
					ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
					ParkingOutTask parkingOutTask = SpringUtil.getBean(ParkingOutTask.class);
					parkingOutTask.setParkOut(out);
					threadPool.execute(parkingOutTask);
					replyHead.setStatus("0");
				}
			} else {
				if (GlobalPark.outChannelEvent.containsKey(model.getControlIP())) {
					GlobalPark.outChannelEvent.remove(model.getControlIP());
				}
			}
		} catch (Exception ex) {
			replyHead.setStatus("1");
			replyHead.setMessage("处理失败");
			logger.error("出场确认开闸:",ex);
		} finally {
			SuperBody returnBody = new SuperBody();
			returnBody.setuId(uId);
			MainBoardMessage<ReplyHead, SuperBody> returnInfo = new MainBoardMessage<>(replyHead, returnBody);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttServiceImpl.sendMessage(model.getEquipmentID(),retTopic, jsonBody, null, 0);
		}

	}

	/**
	 * 查找场内车辆
	 * 
	 * @param jsonStr
	 */
	@Override
	public void boxGetInRecord(String jsonStr) {
		List<VwParkCarIn> data = null;
		String retTopic = null;
		ReplyHead retHead = new ReplyHead();
		MainBoardMessage<Head, BoxGetInRecord> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
				BoxGetInRecord.class);
		BoxGetInRecordReturn returnBody = new BoxGetInRecordReturn();
		BoxGetInRecord model = messgeVo.getBody();
		try {
			Head head = messgeVo.getHead();
			if (model != null) {
				String carNo = null;
				if (!StringUtils.isNullOrEempty(model.getCarNo()))
					carNo = model.getCarNo();
				if (carNo != null && carNo.equals("无牌车"))
					carNo = "NOP";

				if (carNo != null)
					data = vwParkCarInMapper.GetByCarNo(carNo);
				else
					data = vwParkCarInMapper.GetByNull();
				retHead.setStatus("0");
			} else {
				retHead.setStatus("1");
				retHead.setMessage("body不能为null");
			}
			if (head != null)
				retTopic = head.getReplyTopic();

			returnBody.setParkInRecord(data);
			retHead.setMethod("boxGetInRecord");
		} catch (Exception ex) {
			retHead.setStatus("1");
			retHead.setMessage("处理失败");
			logger.error("查找场内车辆:",ex);
		} finally {
			MainBoardMessage<ReplyHead, BoxGetInRecordReturn> returnInfo = new MainBoardMessage<>(retHead, returnBody);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttServiceImpl.sendMessage(model.getEquipmentID(),retTopic, jsonBody, null, 0);
		}
	}

	/**
	 *  出场校正车牌
	 */
	@Override
	public void boxOutChanelCorrecting(String jsonStr) {
		try {
			MainBoardMessage<Head, ParkOutChanelCorrectingReturnBody> messages = JsonUtil.jsonToBoardMessage(jsonStr,
					Head.class, ParkOutChanelCorrectingReturnBody.class);
			ParkOutChanelCorrectingReturnBody model = messages.getBody();
			//Head head = messages.getHead();
			if (model.getType().equals("0")) {
				OutRealTimeBase out = model.getOutRecord();
				String carNo = model.getOutRecord().getCarNo();
				ParkCarIn in = model.getSimilarInRecord();
				if (in == null)
					return;
				out.setInRecord(in);
				String inCarNo = in.getCarNo(); // 入场时车牌
				if (!inCarNo.equals(carNo)) {
					ParkCarIn record = new ParkCarIn();
					record.setCarNo(carNo);
					record.setModifyCarNo(inCarNo);
					record.setId(in.getId());
					parkCarInMapper.updateByPrimaryKeySelective(record);
					out.getInRecord().setCarNo(carNo);
				}
				out.setCarNo(carNo);
				ParkChannelSet channel = ParkMethod.getChannelSetByIp(model.getControlIP());
				out.setChannelSet(channel);
				if (channel != null) {
					out.getOut().setOutMachNo(channel.getMachNo());
				}
				//String picPath = getpicPath(model.getControlIP());
				//out.getOut().setOutPic(picPath);

				out.getOut().setOutWay(1);
				out.setInRecord(model.getSimilarInRecord());
				out.setDeal(true);

				if (GlobalPark.outChannelEvent.containsKey(model.getControlIP())) {
					GlobalPark.outChannelEvent.remove(model.getControlIP());
				}
				GlobalPark.outChannelEvent.put(model.getControlIP(), out);
				ThreadPoolTaskExecutor threadPool = SpringUtil.getBean(ThreadPoolTaskExecutor.class);
				ParkingOutTask parkingOutTask = SpringUtil.getBean(ParkingOutTask.class);
				out.setNextStep(Step.START);
				parkingOutTask.setParkOut(out);
				threadPool.execute(parkingOutTask);

			} else {
				if (GlobalPark.outChannelEvent.containsKey(model.getControlIP())) {
					GlobalPark.outChannelEvent.remove(model.getControlIP());
				}
			}
		} catch (BeansException ex) {
			logger.error("出场校正车牌:",ex);
		}
	}

	
	

	/*
	 * 判断原用户是否正确
	 */
	private ReplyHead isOldUser(String oldUserName, String oldPwd, int boxId) {
		ReplyHead ret = new ReplyHead();
		ret.setStatus("1");
		String pwd = null;
		if (!StringUtils.isNullOrEempty(oldPwd))
			pwd = AESUtil.encryptSHA(oldPwd);
		SysLoginUser sysLoginModel = sysLoginUserMapper.selectByNameAndPwd(oldUserName, pwd);
		if (sysLoginModel == null)
			ret.setMessage("原密码或原用户名错误");
		else {
			ParkSumUser model = new ParkSumUser();
			model.setBoxId(boxId);
			List<ParkSumUser> parkSumUserList = parkSumUserMapper.selectByCondition(model);
			if (parkSumUserList != null && parkSumUserList.size() > 0) {
				// 之前有登陆，且接班操作员为空
				if (parkSumUserList.get(0).getReliefUserName() == null) {
					if (parkSumUserList.get(0).getUserName().equals(oldUserName)) {
						ret.setStatus("0");
						return ret;
					}

				}
			}
		}
		if (!StringUtils.isNullOrEempty(ret.getMessage()))
			ret.setMessage("原用户非" + oldUserName);
		return ret;
	}

	/**
	 * 根据主板IP确定对应岗亭是否在线
	 * 
	 * @param controlIP
	 * @return list[0] 控制器索引 ,list[1]岗亭索引
	 */
	private Integer[] IsBoxOnlineByControlIP(String controlIP) {
		Integer[] list = new Integer[] { -1, -1 };
		for (int p = 0; p < GlobalPark.parkContset.size(); p++) {
			if (GlobalPark.parkContset.get(p).getChannelIp().equals(controlIP)) {
				int boxId = GlobalPark.parkContset.get(p).getBoxId();
				for (int l = 0; l < GlobalPark.parkLocalSet.size(); l++) {
					if (GlobalPark.parkLocalSet.get(l).getBoxId() == boxId) {
						if (GlobalPark.parkLocalSet.get(l).getOnline() !=null 
								&& GlobalPark.parkLocalSet.get(l).getOnline() == 1
								//&& GlobalPark.parkLocalSet.get(l).getEquipmentID() != null
								) {
							list[0] = p;
							list[1] = l;
							return list;
						}

					}
				}
				break;
			}
		}
		return list;
	}

	/**
	 * 控制器IP 抓拍图片
	 * 
	 * @param controlIp
	 * @return 返回图片路径
	 */
	private String getpicPath(String controlIp) {
		//HandCapBody capBody = new HandCapBody();
		Integer[] conIndex = IsBoxOnlineByControlIP(controlIp);
		if (conIndex[0] >= 0 && conIndex[1] >= 0) {
			int machNo = GlobalPark.parkContset.get(conIndex[0]).getMachNo();
			String equipmentID = GlobalPark.parkContset.get(conIndex[0]).getDsn();
			ParkCamSet cam = ParkMethod.getParkCamSetByMachNo(machNo);
			if (cam != null && cam.getCamIp() != null) {
				HandCapBody body = new HandCapBody();
				body.setCamIP(controlIp);

				MainBoardMessage<ReplyHead, CaptureMiniPhotoJpegBody> retBody = MainBoardSdk.sendAndGet(equipmentID,
						"captureMiniPhotoJpeg", body, CaptureMiniPhotoJpegBody.class);

				if (retBody != null) {
					String picPath = retBody.getBody().getPicPath();
					return picPath;
				}
			}
		}
		return null;
	}

	/**
	 * 获取新的无牌车号码
	 */
	@Override
	public void getNoCarNo(String jsonStr) {
		try {
			MainBoardMessage<Head, GetNoCarNoBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					GetNoCarNoBody.class);
			GetNoCarNoBody model = messgeVo.getBody();
			Head head = messgeVo.getHead();
			ReplyHead retHead = new ReplyHead();
			GetNoCarNoBody body = new GetNoCarNoBody();
			body.setuId(model.getuId());
			body.setControlIP(model.getControlIP());
			body.setEquipmentID(model.getEquipmentID());
			String carno = ParkMethod.getNewNopNo();
			retHead.setStatus("0");
			retHead.setMessage("成功");
			retHead.setMethod("getNoCarNo");
			body.setNopCarNo(carno);
			MainBoardMessage<ReplyHead, SuperBody> returnInfo = new MainBoardMessage<>(retHead, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttServiceImpl.sendMessage(model.getEquipmentID(),head.getReplyTopic(), jsonBody, null, 3);
		} catch (Exception ex) {
			logger.error("获取新的无牌车号码:",ex);
		}
	}

	/**
	 * 播报收费语音
	 */
	@Override
	public void boxSpeechSounds(String jsonStr) {
		try {
			MainBoardMessage<Head, BoxSpeechSoundsBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxSpeechSoundsBody.class);
			BoxSpeechSoundsBody model = messgeVo.getBody();
			//Head head = messgeVo.getHead();
			OutRealTimeBase outRealTimeBase = new OutRealTimeBase();
			Integer[] conIndex = IsBoxOnlineByControlIP(model.getControlIP());
			if (conIndex[0] >= 0 && conIndex[1] >= 0) {
				outRealTimeBase.setChannelSet(GlobalPark.parkContset.get(conIndex[0]));
				outRealTimeBase.setNextStep(Step.ISARREARS);
				outRealTimeBase.setCarNo(model.getCarNo());
				PaymentVo pVo = new PaymentVo();
				pVo.setCarNo(model.getCarNo());
				pVo.setInTime(model.getInDate());
				pVo.setOutTime(model.getOutDate());
				pVo.setPayCharge(model.getPayMoney());
				outRealTimeBase.setPayMentVo(pVo);
				//调用播报收费语音方法
				parkingOutService.broadcastVoice(outRealTimeBase);
			}
		} catch (Exception ex) {
			logger.error("播报收费语音:",ex);
		}
	}
	
	/**
	 * 修改入场车牌
	 */
	@Override
	public void boxModifyCarNo(String jsonStr) {
		try {
			MainBoardMessage<Head, BoxModifyCarNoBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					BoxModifyCarNoBody.class);
			BoxModifyCarNoBody model = messgeVo.getBody();
			Head head = messgeVo.getHead();
			ReplyHead retHead = new ReplyHead();
			BoxModifyCarNoBody body = new BoxModifyCarNoBody();
			body.setuId(model.getuId());
			body.setControlIP(model.getControlIP());
			body.setEquipmentID(model.getEquipmentID());
			ParkCarIn parkCarIn = model.getParkCarIn();
			List<VwParkCarIn> data = vwParkCarInMapper.GetByCarNo(model.getParkCarIn().getCarNo());
			if(data != null && data.size() > 0)
			{
				body.setParkCarIn(parkCarIn);
				retHead.setStatus("1");
				retHead.setMessage("场内已存在此车牌号");
				retHead.setMethod("boxModifyCarNo");
			}else
			{
				VwParkCarIsuse theCar = parking.getVwParkCarIsuse(model.getParkCarIn().getCarNo());
				if (theCar != null)// 新车牌为固定车辆
				{
					parkCarIn.setYktId(theCar.getYktId());
					parkCarIn.setEmpName(theCar.getPerName());
					parkCarIn.setCardType(theCar.getCardType());
					parkCarIn.setCardId(theCar.getCardId());
					parkCarIn.setIsLoad(0);
				} else// 新车牌为临时车辆
				{
					parkCarIn.setYktId(0);
					parkCarIn.setEmpName("临时用户");
					parkCarIn.setCardType(InOutRealTimeBase.TEMP_CAR_A);
					parkCarIn.setCardId(ParkMethod.CarNotoCardID(parkCarIn.getCarNo()));
					parkCarIn.setIsLoad(0);
				}
				parkCarInMapper.updateByPrimaryKeySelective(parkCarIn);
				body.setParkCarIn(parkCarIn);
				retHead.setStatus("0");
				retHead.setMessage("成功");
				retHead.setMethod("boxModifyCarNo");
			}
			MainBoardMessage<ReplyHead, SuperBody> returnInfo = new MainBoardMessage<>(retHead, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttServiceImpl.sendMessage(model.getEquipmentID(),head.getReplyTopic(), jsonBody, null, 3);
		} catch (Exception ex) {
			logger.error("修改入场车牌:",ex);
		}
	}
	
	/**
	 * 计费
	 */
	@Override
	public void getPayCharge(String jsonStr) {
		try {
			MainBoardMessage<Head, GetPayChargeBody> messgeVo = JsonUtil.jsonToBoardMessage(jsonStr, Head.class,
					GetPayChargeBody.class);
			GetPayChargeBody model = messgeVo.getBody();
			Head head = messgeVo.getHead();
			
			ReplyHead retHead = new ReplyHead();
			GetPayChargeReturn body = new GetPayChargeReturn();
			body.setuId(model.getuId());
			
		    AbstractChargeStandard chargeMode = SpringUtil.getBean(StandChargeRule.class);
		    int money = chargeMode.getCharge(model.getInTime(), model.getOutTime(), Integer.parseInt(model.getCardType()));
		    float paymoney = money / 100f;
		    DecimalFormat df = new DecimalFormat("#########.#");
		    String pay = df.format(paymoney);
		    
		    body.setAccMoney(pay);
		    body.setPayMoney(pay);
		    body.setDisMoney("0");
		    
			retHead.setStatus("0");
			retHead.setMessage("成功");
			retHead.setMethod("getPayCharge");
			MainBoardMessage<ReplyHead, SuperBody> returnInfo = new MainBoardMessage<>(retHead, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttServiceImpl.sendMessage(model.getEquipmentID(),head.getReplyTopic(), jsonBody, null, 3);
		} catch (Exception ex) {
			logger.error("计费:",ex);
		}
	}
}

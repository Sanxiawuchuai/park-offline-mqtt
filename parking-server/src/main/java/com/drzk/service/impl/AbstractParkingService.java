
package com.drzk.service.impl;

import com.drzk.bean.MqttMessageVO;
import com.drzk.common.InOutRealTimeBase;
import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.common.ParkMethod;
import com.drzk.common.TopicsDefine;
import com.drzk.fact.AbsRealTimeBase;
import com.drzk.fact.CentreRealTimeBase;
import com.drzk.fact.InRealTimeBase;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.mapper.*;
import com.drzk.offline.vo.ParkSumUserBody;
import com.drzk.parklib.send.MainBoardSdk;
import com.drzk.service.IParkingService;
import com.drzk.service.entity.*;
import com.drzk.utils.*;
import com.drzk.vo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ClassName:AbstractParkingInService <br>
 * Date: 2018年6月8日 上午9:25:49 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public abstract class AbstractParkingService implements IParkingService {

	@Autowired
	ParkCarBlackListMapper parkCarBlackListMapper;
	@Autowired
	YktCardIssueParkMapper yktCardIssueParkMapper;
	@Autowired
	ParkControlPlanRelMapper parkControlPlanRelMapper;
	@Autowired
	PerPersonsMapper personMapper;
	@Autowired
	VwParkCarIsuseMapper vwParkCarIsuseMapper;
	@Autowired
	ParkEffetTimesMapper parkEffetTimesMapper;
	@Autowired
	ParkCarInMapper parkCarInMapper;
	@Autowired
	ParkCentralChargeMapper parkCentralChargeMapper;
	@Autowired
	ParkDisDetailMapper parkDisDetailMapper;
	@Autowired
	private ParkSumUserMapper parkSumUserMapper;
	@Autowired
	private ParkSumInfoMapper parkSumInfoMapper;

	private static Logger logger = Logger.getLogger("userLog");

	@Override
	public boolean isReadSuccess(AbsRealTimeBase in) {
		return true;
	}

	@Override
	public boolean isParkFull() {

		return false;
	}

	@Override
	public boolean storecarCharge(CentreRealTimeBase out) {
		return false;
	}

	@Override
	public boolean centreFamilyTemp(CentreRealTimeBase fact) {
		return false;
	}

	@Override
	/** 费用是否结清 true表示结清,false 表示欠费 */
	public boolean isArrears(CentreRealTimeBase fact) {
		return false;
	}

	@Override
	public boolean centreFamilyMonth(CentreRealTimeBase fact) {
		return false;
	}

	@Override
	public boolean isParkFullPass(int cardType) {

		return false;
	}

	@Override
	public boolean isRecordDuplicate(String carNo, int controlIndex, Date inTime, int flag) {

		return false;
	}

	@Override
	public String getNewNOPNo() {

		return "";
	}

	@Override
	public boolean saveParkInRecord(InRealTimeBase in) {

		return false;
	}

	@Override
	public boolean isArrears(OutRealTimeBase out) {

		return true;
	}

	@Override
	public List<ParkCarIn> getSimilarCar(OutRealTimeBase out) {

		return null;
	}

	@Override
	public int saveParkOutRecord(OutRealTimeBase in) {

		return 0;
	}

	@Override
	public void sendArrearsToLocal(int controlIndex) {

		// TODO Auto-generated method stub

	}

	@Override
	public Step isCardTypeExpireIn(InRealTimeBase in) {
		return Step.NULL;
	}

	@Override
	public Step isCardTypeExpireOut(OutRealTimeBase out) {
		return Step.NULL;
	}

	@Override
	public Step isCardTypeExpireCentre(CentreRealTimeBase centre) {
		return Step.NULL;
	}

	@Override
	public void getFreeInRecord(OutRealTimeBase out) {

	}

	public boolean isFamilyTemp(OutRealTimeBase out) {
		return true;
	}

	public boolean isFamilyMonth(OutRealTimeBase out) {
		return true;
	}

	@Override
	public boolean isMonthMoreOut(OutRealTimeBase out) {
		return true;
	}

	/** 获取大小车场 */
	@Override
	public boolean isSmallPark(AbsRealTimeBase fact) {
		logger.debug("isSmallPark :" + fact.getCarNo());
		boolean small = false;
		try {
			ParkChannelSet channelModel = fact.getChannelSet();
			if (channelModel != null) {
				switch (channelModel.getInOut()) {
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
					small = false;
					break;
				default:
					small = true;
					break;
				}
			}
		} catch (Exception ex) {
			logger.error("判断大小车场:", ex);
		}
		fact.setSmall(small);
		if (fact.isInOrOutFlag()) {
			((InRealTimeBase) fact).getIn().setSmall(small ? 1 : 0);
		}
		return small;
	}

	/** 获取车辆是特种车或者黑名单 */
	@Override
	public Step getSpecialOrBlackCar(AbsRealTimeBase fact) {
		try {
			logger.debug("getSpecialOrBlackCar :" + fact.getCarNo());
			ParkCarBlackList model = parkCarBlackListMapper.selectByCarNo(fact.getCarNo());
			if (model != null) {
				switch (model.getIsStop()) {
				case 1: // 黑名单
					return Step.BLACKLIST;
				case 2: // 免费车辆
					fact.setCarRealType(InOutRealTimeBase.FREE_CAR_A);
					if (fact.isInOrOutFlag())
						((InRealTimeBase) fact).getIn().setCardType(InOutRealTimeBase.FREE_CAR_A);
					else
						((OutRealTimeBase) fact).getOut().setCardType(InOutRealTimeBase.FREE_CAR_A);
					return Step.FREECAR;
				case 3: // 特殊车辆
					return Step.SPECIALCAR;
				default:
					return Step.NULL;
				}
			}
			String freeChar = GlobalPark.properties.getProperty("HEAD_TAIL_LICENCE");
			if (freeChar == null || freeChar.trim() == "") {
				return Step.NULL;
			} else {
				String firstChar = fact.getCarNo().substring(0, 1);
				if (freeChar.contains(firstChar)) {
					return Step.FREECAR;
				}

				if (fact.getCarNo().endsWith("警"))
					return Step.FREECAR;
			}
			return Step.NULL;
		} catch (Exception ex) {
			logger.error("获取车辆是特种车或者黑名单:", ex);
			return Step.NULL;
		}
	}

	/** 检查是否是固定车 */
	@Override
	public boolean isRegularCar(AbsRealTimeBase fact) {
		logger.debug("isRegularCar :" + fact.getCarNo());
		// 1 月卡;2月临卡;3临时卡;4免费卡;5储值卡;6储临卡
		try {
			int cardType = fact.getCarRealType();
			int type = cardType / 10;
			fact.setCarBigType(type);
			return type == 1 || type == 4 || type == 5;
		} catch (Exception ex) {
			logger.error("检查是否是固定车:", ex);
			return false;
		}
	}

	/** 获取车牌的卡类型 */
	@Override
	public int getCardTypeInfo(AbsRealTimeBase fact) {
		logger.debug("getCardTypeInfo :" + fact.getCarNo());
		String carNo = fact.getCarNo();
		Integer cardType = InOutRealTimeBase.TEMP_CAR_A;
		// 如果车牌为空，做临时车处理
		if (StringUtils.isNullOrEempty(carNo)) {
			return cardType;
		}
		try {
			VwParkCarIsuse model = getVwParkCarIsuse(carNo);
			if (model != null) {
				fact.setCardInfo(model); // 授权数据
				cardType = model.getCardType();
				fact.setCarNo(model.getCarNo()); // 当前首字识别出错时，
				if (fact.isInOrOutFlag()) {
					((InRealTimeBase) fact).getIn().setYktId(model.getYktId()); // 设置yktId
					((InRealTimeBase) fact).getIn().setEmpName(model.getPerName());
					((InRealTimeBase) fact).getIn().setCardType(cardType);
					((InRealTimeBase) fact).getIn().setCarNo(model.getCarNo());
				} else {
					((OutRealTimeBase) fact).getOut().setYktId(model.getYktId());
					((OutRealTimeBase) fact).getOut().setCardType(cardType);
					((OutRealTimeBase) fact).getOut().setCardNo(model.getCarNo());
					((OutRealTimeBase) fact).getPayMentVo().setCarRealType(cardType);
				}
			}else{
				if (!fact.isInOrOutFlag()){
					((OutRealTimeBase) fact).getPayMentVo().setCarRealType(cardType);
					((OutRealTimeBase) fact).getOut().setCardType(cardType);
				}
			}
		} catch (Exception e) {
			logger.error("获取车牌的卡类型:", e);
		}
		fact.setCarRealType(cardType);
		logger.debug("getCardTypeInfo:" + fact.getCarNo() + cardType);
		return cardType;
	}

	/** 检查通道是否有该种卡类通过的权限 */
	@Override
	public boolean isCarTypeChannelPower(AbsRealTimeBase fact) {
		try {
			logger.debug("isCarTypeChannelPower :" + fact.getCarNo());
			int cartType = fact.getCarRealType(); // 得到车类型
			ParkChannelSet channelModel = fact.getChannelSet();
			if (channelModel != null && channelModel.getGateType() == 1) {
				if (cartType > 30 && cartType < 40)
					return false;
			}
			if (cartType > 30 && cartType < 40)
				return true;
			// YktCardIssuePark model = getYktCardIssueParkModel(carNo);
			VwParkCarIsuse model = fact.getCardInfo(); // 判断固定车通道权限
			if (model != null) {
				List<ParkControlPlanRel> pcPlanModel = parkControlPlanRelMapper.selectByPlanId(model.getPlanId());
				if (pcPlanModel.size() > 0) {
					for (ParkControlPlanRel modelItem : pcPlanModel) {
						if (modelItem.getMachNo() == fact.getChannelSet().getMachNo())
							return true;
					}
				}
			}
		} catch (Exception e) {
			logger.error("检查通道是否有该种卡类通过的权限:", e);
		}
		return false;
	}

	/**
	 * 播报语音
	 * 
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	@Override
	public boolean broadcastVoice(AbsRealTimeBase fact) {
		try {
			long start = System.currentTimeMillis();
			logger.debug("broadcastVoice :" + fact.getCarNo());
			Step step = fact.getNextStep();
			DisplayBody body = new DisplayBody();
			List<DisplayChar> charList = new ArrayList<DisplayChar>();
			DisplayChar voice;
			switch (step) {
			case NOCARNO: // 无牌车，报播请扫码
				voice = new DisplayChar();
				voice.setVoiceType("12"); // 无牌车请扫码
				charList.add(voice);
				voice = new DisplayChar();
				voice.setVoiceType("89"); // 无牌车请扫码
				voice.setDisValue(ParkMethod.getTwoCode(fact.getChannelSet().getDsn()));
				charList.add(voice);
				break;
			case SENDSIMILAR: // 发送相似车牌
				voice = new DisplayChar();
				voice.setVoiceType("10"); // 请稍后
				charList.add(voice);
				break;
			case NEEDCONFIRM: // 确认开闸 播报请稍后
				voice = new DisplayChar();
				voice.setVoiceType("10"); // 请稍后
				charList.add(voice);
				break;
			case SPECIALCAR: // 特殊车辆 需区别出入场
				voice = new DisplayChar();
				if (fact.isInOrOutFlag()) { // 入场
					voice.setVoiceType("01"); // 入场语音
				} else { // 出场
					voice.setVoiceType("02"); // 出场语音
				}
				charList.add(voice);
				break;
			case BLACKLIST: // 黑名单
				voice = new DisplayChar();
				voice.setVoiceType("22"); // 禁止通行
				charList.add(voice);
				break;
			case FULLNOTALLOWIN: // 车场满位
				voice = new DisplayChar();
				voice.setVoiceType("21"); // 车场满位
				charList.add(voice);
				break;
			case NOGULARPOWER: // 无固定车通道权限
				voice = new DisplayChar();
				voice.setVoiceType("1E"); // 固定车，请到专用通道
				charList.add(voice);
				break;
			case EXPIRE: // 车辆过期
				voice = new DisplayChar();
				voice.setVoiceType("18"); // 已过期
				charList.add(voice);
				voice = new DisplayChar();
				voice.setVoiceType("22"); // 禁止通行
				charList.add(voice);
				break;
			case UNDUE: // 未到有效期
				voice = new DisplayChar();
				voice.setVoiceType("19"); // 未到有效期
				charList.add(voice);
				voice = new DisplayChar();
				voice.setVoiceType("22"); // 禁止通行
				charList.add(voice);
				break;
			case TIMENOTALLOW: // 时间段禁止进入
				voice = new DisplayChar();
				voice.setVoiceType("1A"); // 时间段禁止进入
				charList.add(voice);
				break;
			case FAMILYFULL: // 家庭组满位
				voice = new DisplayChar();
				voice.setVoiceType("28"); // 家庭组满位,禁止入场
				charList.add(voice);
				break;
			case NOTEMPPOWER: // 无临时车通道权限
				voice = new DisplayChar();
				voice.setVoiceType("1D"); // 临时车，请到专用通道
				charList.add(voice);
				break;
			case LOCK: // 锁车
				voice = new DisplayChar();
				voice.setVoiceType("29"); // 此车已锁，需要先解锁才能通行
				charList.add(voice);
				break;
			case VOICE: // 正常通行
				charList = getVoiceInOut(fact);
				break;
			case ISARREARS: // 报播收费语音
				charList = getVoicePay(fact);
				break;
			default:
				break;
			}
			body.setuId(ParkMethod.getUUID());
			body.setDisplayChar(charList);
			String equipmentID = fact.getChannelSet().getDsn();
			String method = "displayOutPut";
			MainBoardMessage<ReplyHead, DisplayBodyReturn> replyVo = MainBoardSdk.sendAndGet(equipmentID, method, body,
					DisplayBodyReturn.class);
			long end = System.currentTimeMillis();
			System.out.println("voice time:" + (end - start));
			logger.debug("播报语音:" + body);
			return !(replyVo == null || !"0".equals(replyVo.getHead().getStatus()));
		} catch (Exception e) {
			logger.error("播报语音:", e);
			return false;
		}
	}

	/**
	 * 收费语音
	 * 
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	private List<DisplayChar> getVoicePay(AbsRealTimeBase fact) {
		List<DisplayChar> charList = new ArrayList<DisplayChar>();
		// 是否播报车牌
		int IsVoiceCarNo = Integer.parseInt(GlobalPark.properties.getProperty("BROAD_LICENCE", "0"));
		DisplayChar voice;
		// 车牌号码
		if (IsVoiceCarNo == 1) // 播报车牌
		{
			voice = new DisplayChar();
			voice.setVoiceType("81"); // 车牌
			voice.setDisValue(fact.getCarNo());
			charList.add(voice);
		}
		// 收费金额
		voice = new DisplayChar();
		voice.setVoiceType("84");
		voice.setDisValue(((OutRealTimeBase) fact).getPayMentVo().getPayCharge() + "");
		charList.add(voice);

		// 计费时间
		voice = new DisplayChar();
		voice.setVoiceType("86");
		int parkTime = DateTimeUtils.getPeriodMinute(((OutRealTimeBase) fact).getPayMentVo().getInTime(),
				((OutRealTimeBase) fact).getPayMentVo().getOutTime());
		voice.setDisValue(parkTime + "");
		charList.add(voice);

		// 二维码 
		if (((OutRealTimeBase) fact).getPayMentVo().getPayCharge()!=null&&((OutRealTimeBase) fact).getPayMentVo().getPayCharge() > 0) {
			voice = new DisplayChar();
			voice.setVoiceType("89");
			voice.setDisValue(ParkMethod.getTwoCode(fact.getChannelSet().getDsn()));
			charList.add(voice);
		}

		return charList;
	}

	/**
	 * 入场语音
	 * 
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	private List<DisplayChar> getVoiceInOut(AbsRealTimeBase fact) {
		List<DisplayChar> charList = new ArrayList<DisplayChar>();
		switch (fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: // 月租车
		case InOutRealTimeBase.MONTH_TEMP_CAR: // 月临车
		case InOutRealTimeBase.FREE_CAR: // 免费车
		case InOutRealTimeBase.STORED_CAR: // 储值车
		case InOutRealTimeBase.STORED_TEMP_CAR: // 储临车
			Date eTime = fact.getCardInfo().getEndDate(); // 结束有效期
			Date sTime = fact.getCardInfo().getStartDate(); // 开始有效期
			if (fact.getNowDate().before(sTime)) { // 在有效期之前
				charList = inUndue(fact);
			} else if (!eTime.after(fact.getNowDate())) { // 有效期之后
				charList = inExpire(fact);
			} else // 有效期之内
			{
				charList = inEffective(fact);
			}
			break;
		case InOutRealTimeBase.TEMP_CAR: // 临时车 不判断有效期
		default: // 其它类型
			charList = inEffective(fact);
			break;
		}
		return charList;
	}

	/**
	 * 固定车过期
	 * 
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	private List<DisplayChar> inExpire(AbsRealTimeBase fact) {
		List<DisplayChar> charList = new ArrayList<DisplayChar>();
		DisplayChar voice;
		voice = new DisplayChar();
		voice.setVoiceType("18"); // 车牌
		charList.add(voice);
		return charList;
	}

	/**
	 * 未到有效期
	 * 
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	private List<DisplayChar> inUndue(AbsRealTimeBase fact) {
		List<DisplayChar> charList = new ArrayList<DisplayChar>();
		DisplayChar voice;
		voice = new DisplayChar();
		voice.setVoiceType("19"); // 车牌
		charList.add(voice);
		return charList;
	}

	/**
	 * 在效期内
	 * 
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	private List<DisplayChar> inEffective(AbsRealTimeBase fact) {
		List<DisplayChar> charList = new ArrayList<DisplayChar>();
		DisplayChar voice;

		// 是否播报车牌
		int IsVoiceCarNo = Integer.parseInt(GlobalPark.properties.getProperty("BROAD_LICENCE", "0"));

		// 车类型
		switch (fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: // 月租车
			voice = new DisplayChar();
			voice.setVoiceType("2B"); // 固定车
			charList.add(voice);
			break;
		case InOutRealTimeBase.FREE_CAR: // 免费车
			voice = new DisplayChar();
			voice.setVoiceType("2C"); // 固定车
			charList.add(voice);
			break;
		case InOutRealTimeBase.STORED_CAR: // 储值车
			voice = new DisplayChar();
			voice.setVoiceType("2D"); // 固定车
			charList.add(voice);
			break;
		case InOutRealTimeBase.TEMP_CAR: // 临时车
		case InOutRealTimeBase.MONTH_TEMP_CAR: // 临时车
		case InOutRealTimeBase.STORED_TEMP_CAR: // 临时车
			voice = new DisplayChar();
			voice.setVoiceType("25"); // 临时车
			charList.add(voice);
			break;
		default: // 其它类型
			break;
		}
		// 车牌号码
		if (IsVoiceCarNo == 1) // 播报车牌
		{
			voice = new DisplayChar();
			voice.setVoiceType("81"); // 车牌
			voice.setDisValue(fact.getCarNo());
			charList.add(voice);
		}
		// 入场语音
		voice = new DisplayChar();
		if (fact.isInOrOutFlag()) {
			voice.setVoiceType("01"); // 入场欢迎语
		} else {
			voice.setVoiceType("01"); // 出场欢迎语
		}
		charList.add(voice);
		// 其它
		switch (fact.getCarBigType()) {
		case InOutRealTimeBase.MONTH_CAR: // 月租车
		case InOutRealTimeBase.FREE_CAR: // 免费车
		case InOutRealTimeBase.MONTH_TEMP_CAR: // 月临车
			// 有效期
			DisplayChar effectDay = new DisplayChar();
			effectDay.setVoiceType("83");
			effectDay.setDisValue(DateTimeUtils.differDay(fact.getNowDate(), fact.getCardInfo().getEndDate()) + "");
			charList.add(effectDay);
			break;
		case InOutRealTimeBase.TEMP_CAR: // 临时车
			break;
		case InOutRealTimeBase.STORED_CAR: // 储值车
		case InOutRealTimeBase.STORED_TEMP_CAR: // 储临车
			if (fact.isInOrOutFlag()) {
				// 余额
				voice = new DisplayChar();
				voice.setVoiceType("85");
				voice.setDisValue((int) (fact.getCardInfo().getBalanceMoney() * 100) + "");
				charList.add(voice);
			} else {
				// 缴费金额
				int payMoney = ((OutRealTimeBase) fact).getPayMentVo().getPayCharge();
				voice = new DisplayChar();
				voice.setVoiceType("84");
				voice.setDisValue(payMoney + "");
				charList.add(voice);
				// 余额
				voice = new DisplayChar();
				voice.setVoiceType("85");
				voice.setDisValue((int) (fact.getCardInfo().getBalanceMoney() * 100) - payMoney + "");
				charList.add(voice);
			}
			break;
		default:
			break;
		}
		return charList;
	}

	/** 开闸 */
	@Override
	public boolean open(String equipmentID) {
		try {
			long start = System.currentTimeMillis();
			logger.debug("open :" + equipmentID);
			GateBody body = new GateBody();
			body.setOrder("1");
			MainBoardMessage<ReplyHead, GateBodyReturn> replyVo = MainBoardSdk.sendAndGet(equipmentID,
					"roadGateControl", body, GateBodyReturn.class);
			long end = System.currentTimeMillis();
			System.out.println("open time:" + (end - start));
			logger.debug("开闸:" + body);
			return !(replyVo == null || !"0".equals(replyVo.getHead().getStatus()));
		} catch (Exception e) {
			logger.error("开闸:", e);
			return false;
		}
	}

	/**
	 * 判断是否需要确认
	 * 
	 * @param in
	 * @param out
	 * @return
	 */
	public boolean isNeedConfirm(InRealTimeBase in, OutRealTimeBase out) {
		try {
			boolean isDeal = false;
			ParkChannelSet channel = null;
			Integer carNoType = 0;
			boolean isOut = false; // 是否为出场
			// 快速出场
			int quickPass = Integer.parseInt(GlobalPark.properties.getProperty("QUICK_APPERA", "0"));// 固定车过期天数
			if (in != null) {
				channel = in.getChannelSet();
				isDeal = in.isDeal();
				carNoType = in.getCarRealType();
			} else {
				channel = out.getChannelSet();
				//isDeal = out.isDeal();
				carNoType = out.getCarRealType();
				isOut = true;
			}
			if (isDeal)
				return false;
			if (channel != null) {
				Integer type = carNoType / 10;// 1,临时卡 2，储值卡.3,月卡,4免费卡
				String card = "0";
				switch (type) {
				case 1: // 月卡
					if (!channel.getWorkModel().substring(1, 2).equals("0"))
						card = channel.getStrobeSet().substring(1, 2);
					break;
				case 2: // 月临卡
				case 3: // 临时卡
				case 6: // 储临卡
					if (!channel.getWorkModel().substring(0, 1).equals("0")) {
						card = channel.getStrobeSet().substring(0, 1);
						// 出场快速出场 收费为0，选择了快速出场
						if (isOut && quickPass == 1
								&& (out.getPayMentVo() == null || out.getPayMentVo().getPayCharge() == 0)) {
							card = "0";
						}
					}
					break;
				case 4:
					if (!channel.getWorkModel().substring(3, 4).equals("0"))
						card = channel.getStrobeSet().substring(3, 4);
					break;
				case 5:
					if (!channel.getWorkModel().substring(2, 3).equals("0"))
						card = channel.getStrobeSet().substring(2, 3);
					break;
				}
				return card.equals("1");
			}
			return false;
		} catch (Exception e) {
			logger.error("判断是否需要确认:", e);
			return false;
		}
	}

	@Override
	public void boxInIsOpen(InRealTimeBase fact) {

	}

	@Override
	public void boxOutIsOpen(OutRealTimeBase out) {

	}

	/**
	 * 
	 * 根据车牌获取固定车信息 <br>
	 *
	 * @author chenlong
	 * @param
	 * @return
	 * @since JDK 1.8
	 */
	public VwParkCarIsuse getVwParkCarIsuse(String carNo) {
		VwParkCarIsuse model = new VwParkCarIsuse();
		String rCarNo = carNo.trim().substring(1);
		model.setCarNo(rCarNo);
		model.setStatus(0);
		return vwParkCarIsuseMapper.selectMonthCar(model);
	}

	private YktCardIssuePark getYktCardIssueParkModel(String carNo) {
		try {
			YktCardIssuePark model = new YktCardIssuePark();
			model.setCarNo(carNo);
			model = yktCardIssueParkMapper.selectByCondition(model);
			if (model != null) {
				return model;
			} else
				return null;
		} catch (Exception e) {
			LoggerUntils.error(logger, e.getMessage());
			return null;
		}
	}

	@Override
	public void sendSimilarCar(OutRealTimeBase out) {

	}

	/*
	 * 判断车辆是否属于家庭组 <br>
	 *
	 * @author chenlong
	 * 
	 * @param AbsRealTimeBase fact
	 * 
	 * @return
	 * 
	 * @since JDK 1.8
	 */
	@Override
	public boolean isFamilyGroup(AbsRealTimeBase fact) {
		try {
			VwParkCarIsuse model = fact.getCardInfo();
			Integer groupId = model.getPlaceId();
			return !(groupId == null || groupId == 0);
		} catch (Exception e) {
			logger.error("判断车辆是否属于家庭组:", e);
			return false;
		}
	}

	@Override
	public boolean getFamilyCarType(InRealTimeBase in) {

		return false;
	}

	@Override
	public boolean isFamilyAllowIn(InRealTimeBase in) {

		return false;
	}

	@Override
	public void presetCarType() {

	}

	@Override
	public boolean isLock(String carNo) {

		return false;
	}

	/**
	 * 获取固定车的类型 (月卡 or 储值卡 or 免费卡)
	 * 
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	@Override
	public int getRegularCarType(int cardType) {
		try {
			switch (cardType / 10) {
			case 1: // 月租车
				return 1;
			case 2: // 月临车
				return 2;
			case 3: // 临时车
				return 3;
			case 4: // 免费车
				return 4;
			case 5: // 储值车
				return 5;
			case 6: // 储临车
				return 6;
			default://
				return 1;
			}
		} catch (Exception e) {
			logger.error("获取固定车的类型:", e);
			return 1;
		}

	}

	@Override
	public boolean monthCarOverdue() {

		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getTypeByIn(OutRealTimeBase out) {

		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean parkErrorMessage(InRealTimeBase in) {
		return true;
	}

	@Override
	public boolean parkErrorMessage(OutRealTimeBase out) {
		return true;
	}

	@Override
	public boolean sendParkCarInRecordToBox(InRealTimeBase in) {
		return true;
	}

	@Override
	public void sendParkCarOutRecordToBox(OutRealTimeBase out) {

	}

	/**
	 * 查询是否有入场记录. <br>
	 * 
	 * @author wangchengxi
	 * @param centre
	 *            车牌
	 * @return
	 * @since JDK 1.8
	 */
	@Override
	public boolean getSimilarCar(CentreRealTimeBase centre) {
		return false;
	}

	/**
	 * 出入场往硬件发送纪录
	 * 
	 * @param in
	 *            入场实体
	 * @param out
	 *            出场实体
	 */
	@Override
	public void handInParkingCarInfo(InRealTimeBase in, OutRealTimeBase out) {
		try {
			Head head = new Head();
			head.setMethod("handInParkingCarInfo");
			String parkNo = GlobalPark.properties.getProperty("PARK_NUM");
			String replyTopic = String.format(TopicsDefine.DOWN_RECIVE, parkNo);
			head.setReplyTopic(replyTopic);
			HandInParkingCarInfoBody body = new HandInParkingCarInfoBody();
			String mac;
			if (in != null) { // 入场增加记录
				mac = in.getChannelSet().getDsn();
				body = addParkRecord(in);
			} else { // 出场删除记录
				mac = out.getChannelSet().getDsn();
				body = delParkRecord(out);
			}
			MainBoardMessage<Head, HandInParkingCarInfoBody> returnInfo = new MainBoardMessage<>(head, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			MqttMessageVO reply = MqttServiceImpl.sendMessage(mac, replyTopic, jsonBody, null, 3);
		} catch (Exception e) {
			logger.error("出入场往硬件发送纪录:", e);
		}
	}

	/**
	 * 增加入场记录<br>
	 * 
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
	private HandInParkingCarInfoBody addParkRecord(InRealTimeBase in) {
		HandInParkingCarInfoBody body = new HandInParkingCarInfoBody();
		ParkCarIn carIn = in.getIn();
		body.setCarNo(in.getCarNo());
		body.setLargeParkingInTime(in.getNowDate());
		body.setParkingFlag("2");
		boolean small = in.isSmall();
		if (!small) {
			ParkCarIn model = new ParkCarIn();
			model.setCarNo(in.getCarNo());
			if (carIn != null) {
				body.setLargeParkingInTime(in.getNowDate());
				body.setParkingFlag("1");
			}
		}
		body.setMediumType("4");
		body.setMsgTypeID("0");
		body.setMediumData(ParkMethod.CarNotoCardID(in.getCarNo()));
		return body;
	}

	/**
	 * 删除入场记录<br>
	 * 
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
	private HandInParkingCarInfoBody delParkRecord(OutRealTimeBase out) {
		HandInParkingCarInfoBody body = new HandInParkingCarInfoBody();
		body.setCarNo(out.getCarNo());
		body.setLargeParkingInTime(out.getNowDate());
		body.setParkingFlag("1");
		boolean small = out.isSmall();
		if (!small) {
			ParkCarIn model = new ParkCarIn();
			model.setCarNo(out.getCarNo());
			List<ParkCarIn> carIn = parkCarInMapper.selectByCondition(model);
			if (carIn != null && carIn.size() > 0) {
				body.setLargeParkingInTime(carIn.get(0).getInTime());
				body.setSmallParkingInTime(out.getNowDate());
				body.setParkingFlag("2");
			}
		}
		ParkCentralCharge centrialModel = new ParkCentralCharge();
		centrialModel.setCarNo(out.getCarNo());
		List<ParkCentralCharge> centerChargeList = parkCentralChargeMapper.selectByCondition(centrialModel);
		if (centerChargeList != null && centerChargeList.size() > 0) {
			body.setCenterChargeTime(centerChargeList.get(0).getPayChargeTime());
		}
		ParkDisDetail disDetialModel = new ParkDisDetail();
		disDetialModel.setCardId(ParkMethod.CarNotoCardID(out.getCarNo()));
		List<ParkDisDetail> disDetialList = parkDisDetailMapper.selectByRecond(disDetialModel);
		if (disDetialList != null && disDetialList.size() > 0) {
			body.setDiscountTime(disDetialList.get(0).getDiscountTime());
		}
		body.setMediumType(out.getOut().getcFlag().toString());
		body.setMsgTypeID("1");
		body.setMediumData(ParkMethod.CarNotoCardID(out.getCarNo()));
		return body;
	}

	/**
	 * 
	 * 推送当班收费记录<br>
	 * 包括车辆进出次数,收费情况等 <br>
	 * 
	 * @author chenlong
	 * @since JDK 1.8
	 */
	@Override
	public void pushChargeData(AbsRealTimeBase fact) {
		// 1,当有车辆进入时，判断车辆类型，对应的数值加1
		// 2,当有车辆离开时，对应的车辆类型加1，改变收费金额
		// 3,查询当前岗亭
		// 4,推送消息到岗亭端
		try {
			ParkChannelSet channel = fact.getChannelSet();
			if (fact.isInOrOutFlag()) {
				pushInData(channel, ((InRealTimeBase) fact).getIn());
				inStatisticsRefresh(((InRealTimeBase) fact).getIn());
			} else {
				pushOutData(channel, ((OutRealTimeBase) fact).getOut());
				outStatisticsRefresh(((OutRealTimeBase) fact).getOut());
			}
		} catch (Exception e) {
			logger.error("推送当班收费记录:", e);
		}
	}

	/**
	 * 
	 * 入场当班统计<br>
	 * 
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
	public void pushInData(ParkChannelSet channel, ParkCarIn in) {
		try {
			ParkLocalSet box = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
			ParkSumUser model = new ParkSumUser();
			model.setBoxId(box.getBoxId());
			model.setLoginDate(box.getLoginDate());
			model.setUserName(box.getLoginName());
			ParkSumUser sumUser = parkSumUserMapper.selectByUser(model);
			if(sumUser==null)return;
			int carType = in.getCardType();
			if (carType == 1) { // 异常开闸
				Integer handGate = sumUser.getHandGate();
				if (handGate == null)
					handGate = 0;
				sumUser.setHandGate(handGate + 1);
			}
			if (carType == 2) { // 电脑开闸
				Integer computerGate = sumUser.getComputerGate();
				if (computerGate == null)
					computerGate = 0;
				sumUser.setComputerGate(computerGate + 1);
			}
			if (carType > 10 && carType < 20) { // 月租类型
				Integer monthCarIn = sumUser.getMonthCarIn();
				if (monthCarIn == null)
					monthCarIn = 0;
				sumUser.setMonthCarIn(monthCarIn + 1);
			}
			if (carType > 20 && carType < 40) { // 临时
				Integer tempCarIn = sumUser.getTempCarIn();
				if (tempCarIn == null)
					tempCarIn = 0;
				sumUser.setTempCarIn(tempCarIn + 1);
			}
			// 保存数据库
			parkSumUserMapper.updateByPrimaryKey(sumUser);
			Head head = new Head();
			head.setMethod("parkSumUser");
			ParkSumUserBody body = new ParkSumUserBody();
			body.setSunUser(sumUser);
			body.setuId(ParkMethod.getUUID());
			MainBoardMessage<Head, ParkSumUserBody> returnInfo = new MainBoardMessage<>(head, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			if (box != null && box.getOnline() == 1 && box.getEquipmentID() != null) {
				String topic = String.format(TopicsDefine.BOX_ERROR, box.getEquipmentID());
				MqttMessageVO reply = MqttServiceImpl.sendMessage(box.getEquipmentID(), topic, jsonBody, null, 0);
			}
		} catch (Exception e) {
			logger.error("入场当班统计:", e);
		}
	}

	/**
	 * 
	 * 出场当班统计<br>
	 * 
	 * @author wangchengxi
	 * @since JDK 1.8
	 */
	public void pushOutData(ParkChannelSet channel, ParkCarOut out) {
		try {
			ParkLocalSet box = ParkMethod.getLocalSetByBoxId(channel.getBoxId());
			ParkSumUser model = new ParkSumUser();
			model.setBoxId(box.getBoxId());
			model.setLoginDate(box.getLoginDate());
			model.setUserName(box.getLoginName());
			ParkSumUser sumUser = parkSumUserMapper.selectByUser(model);
			if(sumUser==null)return;
			int carType = out.getCardType();
			Float accMoney = out.getAccountCharge();
			if (accMoney == null)
				accMoney = (float) 0;
			Float payMoney = out.getPayCharge();
			if (payMoney == null)
				payMoney = (float) 0;
			if (carType == 1) { // 异常开闸
				Integer handGate = sumUser.getHandGate();
				if (handGate == null)
					handGate = 0;
				sumUser.setHandGate(handGate + 1);
			}
			if (carType == 2) { // 电脑开闸
				Integer computerGate = sumUser.getComputerGate();
				if (computerGate == null)
					computerGate = 0;
				sumUser.setComputerGate(computerGate + 1);
			}
			if (carType > 10 && carType < 20) { // 月租类型
				Integer monthCarOut = sumUser.getMonthCarOut();
				if (monthCarOut == null)
					monthCarOut = 0;
				sumUser.setMonthCarOut(monthCarOut + 1);
			}
			if ((carType > 20 && carType < 40) || (carType > 60 && carType < 70)) { // 临时
				Integer tempCarOut = sumUser.getTempCarOut();
				if (tempCarOut == null)
					tempCarOut = 0;
				sumUser.setTempCarOut(tempCarOut + 1);
			}
			if (carType == 40) { // 临免车
				Integer tempFree = sumUser.getTempFree();
				if (tempFree == null)
					tempFree = 0;
				sumUser.setTempFree(tempFree + 1);
			}
			if (carType > 50 && carType < 60) { // 储值车
				Integer storedCarNum = sumUser.getStoredCarNum();
				if (storedCarNum == null)
					storedCarNum = 0;
				sumUser.setStoredCarNum(storedCarNum + 1);
				Float storedCarMoney = sumUser.getStoredCarMoney();
				if (storedCarMoney == null)
					storedCarMoney = (float) 0;
				sumUser.setStoredCarMoney(storedCarMoney + payMoney);
			} else {
				Float account = sumUser.getAccount();
				if (account == null)
					account = (float) 0;
				sumUser.setAccount(account + accMoney);
				int pautWay = 0;
				if (out.getPayType() != null)
					pautWay = out.getPayType();
				// 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
				if (pautWay == 0) {// 现金支付
					Float cashCharge = sumUser.getCashCharge();
					if (cashCharge == null)
						cashCharge = (float) 0;
					sumUser.setCashCharge(cashCharge + payMoney);
				}
				if (pautWay == 1) { // 微信支付
					Float wechatCharge = sumUser.getWechatCharge();
					if (wechatCharge == null)
						wechatCharge = (float) 0;
					sumUser.setWechatCharge(wechatCharge + payMoney);
				}
				if (pautWay == 2) { // 支付宝支付
					Float alipayCharge = sumUser.getAlipayCharge();
					if (alipayCharge == null)
						alipayCharge = (float) 0;
					sumUser.setAlipayCharge(alipayCharge + payMoney);
				}
			}
			if (accMoney - payMoney > 0) {
				if (carType == 0) {
					Float freeCharge = sumUser.getFreeCharge();
					if (freeCharge == null)
						freeCharge = (float) 0;
					sumUser.setFreeCharge(freeCharge + (accMoney - payMoney));
				} else {
					Integer disSum = sumUser.getDisSum();
					if (disSum == null)
						disSum = 0;
					sumUser.setDisSum(disSum + 1);
					Float disCharge = sumUser.getDisCharge();
					if (disCharge == null)
						disCharge = (float) 0;
					sumUser.setDisCharge(disCharge + (accMoney - payMoney));
				}
			}
			// 保存数据库
			parkSumUserMapper.updateByPrimaryKey(sumUser);
			Head head = new Head();
			head.setMethod("parkSumUser");
			ParkSumUserBody body = new ParkSumUserBody();
			body.setSunUser(sumUser);
			body.setuId(ParkMethod.getUUID());
			MainBoardMessage<Head, ParkSumUserBody> returnInfo = new MainBoardMessage<>(head, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			if (box != null && box.getOnline() == 1 && box.getEquipmentID() != null) {
				String topic = String.format(TopicsDefine.BOX_ERROR, box.getEquipmentID());
				MqttMessageVO reply = MqttServiceImpl.sendMessage(box.getEquipmentID(), topic, jsonBody, null, 0);
			}
		} catch (Exception e) {
			logger.error("出场当班统计:", e);
		}
	}

	@Override
	public boolean storecarCharge(OutRealTimeBase out) {
		// TODO Auto-generated method stub
		return false;
	}

	/** 获取月临车卡类型 */
	/**
	 * wangchengxi
	 */
	public int setMonthCardType(int cardType, int monthType) {
		int yCardType = 0;
		switch (monthType) {
		case 0:
			yCardType = InOutRealTimeBase.TEMP_CAR_A;
			break;
		case 1:
			yCardType = InOutRealTimeBase.TEMP_CAR_A;
			break;
		case 2:
			yCardType = InOutRealTimeBase.TEMP_CAR_B;
			break;
		case 3:
			yCardType = InOutRealTimeBase.TEMP_CAR_C;
			break;
		case 4:
			yCardType = InOutRealTimeBase.TEMP_CAR_D;
			break;
		case 5:
			yCardType = InOutRealTimeBase.TEMP_CAR_E;
			break;
		case 6:
			yCardType = InOutRealTimeBase.TEMP_CAR_F;
			break;
		case 7:
			yCardType = InOutRealTimeBase.TEMP_CAR_G;
			break;
		case 8:
			yCardType = InOutRealTimeBase.TEMP_CAR_H;
			break;
		case 9:
			yCardType = cardType + 20;
			break;
		}
		return yCardType;
	}

	/** 获取月临车卡类型 */
	/**
	 * wangchengxi EFFECTIVE
	 */
	public int setEffeciTiveCardType(int cardType, int monthType) {
		int yCardType = 0;
		switch (monthType) {
		case 2:
			yCardType = InOutRealTimeBase.TEMP_CAR_A;
			break;
		case 3:
			yCardType = InOutRealTimeBase.TEMP_CAR_B;
			break;
		case 4:
			yCardType = InOutRealTimeBase.TEMP_CAR_C;
			break;
		case 5:
			yCardType = InOutRealTimeBase.TEMP_CAR_D;
			break;
		case 6:
			yCardType = InOutRealTimeBase.TEMP_CAR_E;
			break;
		case 7:
			yCardType = InOutRealTimeBase.TEMP_CAR_F;
			break;
		case 8:
			yCardType = InOutRealTimeBase.TEMP_CAR_G;
			break;
		case 9:
			yCardType = InOutRealTimeBase.TEMP_CAR_H;
			break;
		}
		return yCardType;
	}

	/**
	 * 
	 * 入场统计刷新<br>
	 * 
	 * @author tanfei
	 * @since JDK 1.8
	 */
	public void inStatisticsRefresh(ParkCarIn in) {
		try {
			ParkSumInfo model = new ParkSumInfo();
			// 查询入场日期当天是否已存在统计信息，已存在则更新，不存在创建
			model = parkSumInfoMapper.selectByLoginDate(in.getInTime());
			if (model == null) {
				model = new ParkSumInfo();
				model.setPuid(ParkMethod.getUUID());
				model.setLoginDate(in.getInTime());
				model.setHandGate(0);
				model.setComputerGate(0);
				model.setTempCarIn(0);
				model.setTempCarOut(0);
				model.setMonthCarIn(0);
				model.setMonthCarOut(0);
				model.setStoredCarNum(0);
				model.setStoredCarMoney(0f);
				model.setTempFree(0);
				model.setFreeCarNo(0);
				model.setFreeCharge(0f);
				model.setDisSum(0);
				model.setDisCharge(0f);
				model.setCashCharge(0f);
				model.setWechatCharge(0f);
				model.setAlipayCharge(0f);
				model.setUnionpayChagre(0f);
				model.setThirdpayCharge(0f);
				model.setAccount(0f);
				model.setIsLoad(Byte.parseByte("0"));
				int carType = in.getCardType();
				if (carType == 1) // 异常开闸
					model.setHandGate(1);
				if (carType == 2) // 电脑开闸
					model.setComputerGate(1);
				if (carType > 10 && carType < 20) // 月租类型
					model.setMonthCarIn(1);
				if (carType > 20 && carType < 40) // 临时
					model.setTempCarIn(1);
				// 保存数据库
				parkSumInfoMapper.insert(model);
			} else {
				int carType = in.getCardType();
				if (carType == 1) { // 异常开闸
					Integer handGate = model.getHandGate();
					if (handGate == null)
						handGate = 0;
					model.setHandGate(handGate + 1);
				}
				if (carType == 2) { // 电脑开闸
					Integer computerGate = model.getComputerGate();
					if (computerGate == null)
						computerGate = 0;
					model.setComputerGate(computerGate + 1);
				}
				if (carType > 10 && carType < 20) { // 月租类型
					Integer monthCarIn = model.getMonthCarIn();
					if (monthCarIn == null)
						monthCarIn = 0;
					model.setMonthCarIn(monthCarIn + 1);
				}
				if (carType > 20 && carType < 40) { // 临时
					Integer tempCarIn = model.getTempCarIn();
					if (tempCarIn == null)
						tempCarIn = 0;
					model.setTempCarIn(tempCarIn + 1);
				}
				// 保存数据库
				parkSumInfoMapper.updateByPrimaryKey(model);
			}
		} catch (Exception e) {
			logger.error("入场统计刷新:", e);
		}
	}

	/**
	 * 中央收费统计刷新<br>
	 * 
	 * @author tanfei
	 * @since JDK 1.8
	 */
	public void centreStatisticsRefresh(ParkCentralCharge centre) {
		try {
			ParkSumInfo model = new ParkSumInfo();
			// 查询入场日期当天是否已存在统计信息，已存在则更新，不存在创建
			model = parkSumInfoMapper.selectByLoginDate(centre.getPayChargeTime());
			if (model == null) {
				model = new ParkSumInfo();
				model.setPuid(ParkMethod.getUUID());
				model.setLoginDate(centre.getPayChargeTime());
				model.setHandGate(0);
				model.setComputerGate(0);
				model.setTempCarIn(0);
				model.setTempCarOut(0);
				model.setMonthCarIn(0);
				model.setMonthCarOut(0);
				model.setStoredCarNum(0);
				model.setStoredCarMoney(0f);
				model.setTempFree(0);
				model.setFreeCarNo(0);
				model.setFreeCharge(0f);
				model.setDisSum(0);
				model.setDisCharge(0f);
				model.setCashCharge(0f);
				model.setWechatCharge(0f);
				model.setAlipayCharge(0f);
				model.setUnionpayChagre(0f);
				model.setThirdpayCharge(0f);
				model.setAccount(0f);
				model.setIsLoad(Byte.parseByte("0"));

				int carType = centre.getCardType();
				Float accMoney = centre.getAccountCharge();
				if (accMoney == null)
					accMoney = (float) 0;
				Float payMoney = centre.getPayCharge();
				if (payMoney == null)
					payMoney = (float) 0;
				if (carType == 40) { // 临免车
					Integer tempFree = model.getTempFree();
					if (tempFree == null)
						tempFree = 0;
					model.setTempFree(tempFree + 1);
				}
				Float account = model.getAccount();
				if (account == null)
					account = (float) 0;
				model.setAccount(account + accMoney);
				int outWay = 0;
				if (centre.getPayType() != null)
					centre.getPayType();
				// 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
				if (outWay == 0) {// 现金支付
					Float cashCharge = model.getCashCharge();
					if (cashCharge == null)
						cashCharge = (float) 0;
					model.setCashCharge(cashCharge + payMoney);
				}
				if (outWay == 1) { // 微信支付
					Float wechatCharge = model.getWechatCharge();
					if (wechatCharge == null)
						wechatCharge = (float) 0;
					model.setWechatCharge(wechatCharge + payMoney);
				}
				if (outWay == 2) { // 支付宝支付
					Float alipayCharge = model.getAlipayCharge();
					if (alipayCharge == null)
						alipayCharge = (float) 0;
					model.setAlipayCharge(alipayCharge + payMoney);
				}
				if (accMoney - payMoney > 0) {
					if (carType == 0) {
						Float freeCharge = model.getFreeCharge();
						if (freeCharge == null)
							freeCharge = (float) 0;
						model.setFreeCharge(freeCharge + (accMoney - payMoney));
					} else {
						Integer disSum = model.getDisSum();
						if (disSum == null)
							disSum = 0;
						model.setDisSum(disSum + 1);
						Float disCharge = model.getDisCharge();
						if (disCharge == null)
							disCharge = (float) 0;
						model.setDisCharge(disCharge + (accMoney - payMoney));
					}
				}
				// 保存数据库
				parkSumInfoMapper.insert(model);
			} else {
				int carType = centre.getCardType();
				Float accMoney = centre.getAccountCharge();
				if (accMoney == null)
					accMoney = (float) 0;
				Float payMoney = centre.getPayCharge();
				if (payMoney == null)
					payMoney = (float) 0;
				if (carType == 40) { // 临免车
					Integer tempFree = model.getTempFree();
					if (tempFree == null)
						tempFree = 0;
					model.setTempFree(tempFree + 1);
				}
				Float account = model.getAccount();
				if (account == null)
					account = (float) 0;
				model.setAccount(account + accMoney);
				int outWay = 0;
				if (centre.getPayType() != null)
					outWay = centre.getPayType();
				// 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
				if (outWay == 0) {// 现金支付
					Float cashCharge = model.getCashCharge();
					if (cashCharge == null)
						cashCharge = (float) 0;
					model.setCashCharge(cashCharge + payMoney);
				}
				if (outWay == 1) { // 微信支付
					Float wechatCharge = model.getWechatCharge();
					if (wechatCharge == null)
						wechatCharge = (float) 0;
					model.setWechatCharge(wechatCharge + payMoney);
				}
				if (outWay == 2) { // 支付宝支付
					Float alipayCharge = model.getAlipayCharge();
					if (alipayCharge == null)
						alipayCharge = (float) 0;
					model.setAlipayCharge(alipayCharge + payMoney);
				}
				if (accMoney - payMoney > 0) {
					if (carType == 0) {
						Float freeCharge = model.getFreeCharge();
						if (freeCharge == null)
							freeCharge = (float) 0;
						model.setFreeCharge(freeCharge + (accMoney - payMoney));
					} else {
						Integer disSum = model.getDisSum();
						if (disSum == null)
							disSum = 0;
						model.setDisSum(disSum + 1);
						Float disCharge = model.getDisCharge();
						if (disCharge == null)
							disCharge = (float) 0;
						model.setDisCharge(disCharge + (accMoney - payMoney));
					}
				}
				// 保存数据库
				parkSumInfoMapper.updateByPrimaryKey(model);
			}
		} catch (Exception e) {
			logger.error("中央收费统计刷新:", e);
		}
	}

	/**
	 * 中央收费当班刷新<br>
	 * 
	 * @author tanfei
	 * @since JDK 1.8
	 */
	public void centreSumUser(ParkCentralCharge centre, String boxIp) {
		try {
			ParkLocalSet box = ParkMethod.getLocalSetByBoxIP(boxIp);
			ParkSumUser sumUser = new ParkSumUser();
			sumUser.setBoxId(box.getBoxId());
			sumUser.setLoginDate(box.getLoginDate());
			sumUser.setUserName(box.getLoginName());
			ParkSumUser model = parkSumUserMapper.selectByUser(sumUser);
			if(model==null) return;
			int carType = centre.getCardType();
			Float accMoney = centre.getAccountCharge();
			if (accMoney == null)
				accMoney = (float) 0;
			Float payMoney = centre.getPayCharge();
			if (payMoney == null)
				payMoney = (float) 0;
			if (carType == 40) { // 临免车
				Integer tempFree = model.getTempFree();
				if (tempFree == null)
					tempFree = 0;
				model.setTempFree(tempFree + 1);
			}
			Float account = model.getAccount();
			if (account == null)
				account = (float) 0;
			model.setAccount(account + accMoney);
			int outWay = 0;
			if (centre.getPayType() != null)
				outWay = centre.getPayType();
			// 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
			if (outWay == 0) {// 现金支付
				Float cashCharge = model.getCashCharge();
				if (cashCharge == null)
					cashCharge = (float) 0;
				model.setCashCharge(cashCharge + payMoney);
			}
			if (outWay == 1) { // 微信支付
				Float wechatCharge = model.getWechatCharge();
				if (wechatCharge == null)
					wechatCharge = (float) 0;
				model.setWechatCharge(wechatCharge + payMoney);
			}
			if (outWay == 2) { // 支付宝支付
				Float alipayCharge = model.getAlipayCharge();
				if (alipayCharge == null)
					alipayCharge = (float) 0;
				model.setAlipayCharge(alipayCharge + payMoney);
			}
			if (accMoney - payMoney > 0) {
				if (carType == 0) {
					Float freeCharge = model.getFreeCharge();
					if (freeCharge == null)
						freeCharge = (float) 0;
					model.setFreeCharge(freeCharge + (accMoney - payMoney));
				} else {
					Integer disSum = model.getDisSum();
					if (disSum == null)
						disSum = 0;
					model.setDisSum(disSum + 1);
					Float disCharge = model.getDisCharge();
					if (disCharge == null)
						disCharge = (float) 0;
					model.setDisCharge(disCharge + (accMoney - payMoney));
				}
			}
			// 保存数据库
			parkSumUserMapper.updateByPrimaryKey(model);
			Head head = new Head();
			head.setMethod("parkSumUser");
			ParkSumUserBody body = new ParkSumUserBody();
			body.setSunUser(model);
			body.setuId(ParkMethod.getUUID());
			MainBoardMessage<Head, ParkSumUserBody> returnInfo = new MainBoardMessage<>(head, body);
			String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
			if (box != null && box.getOnline() == 1 && box.getEquipmentID() != null) {
				String topic = String.format(TopicsDefine.BOX_ERROR, box.getEquipmentID());
				MqttMessageVO reply = MqttServiceImpl.sendMessage(box.getEquipmentID(), topic, jsonBody, null, 0);
			}
		} catch (Exception e) {
			logger.error("中央收费当班刷新:", e);
		}
	}

	/**
	 * 
	 * 出场统计刷新<br>
	 * 
	 * @author tanfei
	 * @since JDK 1.8
	 */
	public void outStatisticsRefresh(ParkCarOut out) {
		try {
			ParkSumInfo model = new ParkSumInfo();
			// 查询入场日期当天是否已存在统计信息，已存在则更新，不存在创建
			model = parkSumInfoMapper.selectByLoginDate(out.getOutTime());
			if (model == null) {
				model = new ParkSumInfo();
				model.setPuid(ParkMethod.getUUID());
				model.setLoginDate(out.getOutTime());
				model.setHandGate(0);
				model.setComputerGate(0);
				model.setTempCarIn(0);
				model.setTempCarOut(0);
				model.setMonthCarIn(0);
				model.setMonthCarOut(0);
				model.setStoredCarNum(0);
				model.setStoredCarMoney(0f);
				model.setTempFree(0);
				model.setFreeCarNo(0);
				model.setFreeCharge(0f);
				model.setDisSum(0);
				model.setDisCharge(0f);
				model.setCashCharge(0f);
				model.setWechatCharge(0f);
				model.setAlipayCharge(0f);
				model.setUnionpayChagre(0f);
				model.setThirdpayCharge(0f);
				model.setAccount(0f);
				model.setIsLoad(Byte.parseByte("0"));

				int carType = out.getCardType();
				Float accMoney = out.getAccountCharge();
				if (accMoney == null)
					accMoney = (float) 0;
				Float payMoney = out.getPayCharge();
				if (payMoney == null)
					payMoney = (float) 0;
				if (carType == 1) { // 异常开闸
					Integer handGate = model.getHandGate();
					if (handGate == null)
						handGate = 0;
					model.setHandGate(handGate + 1);
				}
				if (carType == 2) { // 电脑开闸
					Integer computerGate = model.getComputerGate();
					if (computerGate == null)
						computerGate = 0;
					model.setComputerGate(computerGate + 1);
				}
				if (carType > 10 && carType < 20) { // 月租类型
					Integer monthCarOut = model.getMonthCarOut();
					if (monthCarOut == null)
						monthCarOut = 0;
					model.setMonthCarOut(monthCarOut + 1);
				}
				if ((carType > 20 && carType < 40) || (carType > 60 && carType < 70)) { // 临时
					Integer tempCarOut = model.getTempCarOut();
					if (tempCarOut == null)
						tempCarOut = 0;
					model.setTempCarOut(tempCarOut + 1);
				}
				if (carType == 40) { // 临免车
					Integer tempFree = model.getTempFree();
					if (tempFree == null)
						tempFree = 0;
					model.setTempFree(tempFree + 1);
				}
				if (carType > 50 && carType < 60) { // 储值车
					Integer storedCarNum = model.getStoredCarNum();
					if (storedCarNum == null)
						storedCarNum = 0;
					model.setStoredCarNum(storedCarNum + 1);
					Float storedCarMoney = model.getStoredCarMoney();
					if (storedCarMoney == null)
						storedCarMoney = (float) 0;
					model.setStoredCarMoney(storedCarMoney + payMoney);
				} else {
					Float account = model.getAccount();
					if (account == null)
						account = (float) 0;
					model.setAccount(account + accMoney);
					int outWay = 0;
					if (out.getOutWay() != null)
						outWay = out.getOutWay();
					// 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
					if (outWay == 0) {// 现金支付
						Float cashCharge = model.getCashCharge();
						if (cashCharge == null)
							cashCharge = (float) 0;
						model.setCashCharge(cashCharge + payMoney);
					}
					if (outWay == 1) { // 微信支付
						Float wechatCharge = model.getWechatCharge();
						if (wechatCharge == null)
							wechatCharge = (float) 0;
						model.setWechatCharge(wechatCharge + payMoney);
					}
					if (outWay == 2) { // 支付宝支付
						Float alipayCharge = model.getAlipayCharge();
						if (alipayCharge == null)
							alipayCharge = (float) 0;
						model.setAlipayCharge(alipayCharge + payMoney);
					}
				}
				if (accMoney - payMoney > 0) {
					if (carType == 0) {
						Float freeCharge = model.getFreeCharge();
						if (freeCharge == null)
							freeCharge = (float) 0;
						model.setFreeCharge(freeCharge + (accMoney - payMoney));
					} else {
						Integer disSum = model.getDisSum();
						if (disSum == null)
							disSum = 0;
						model.setDisSum(disSum + 1);
						Float disCharge = model.getDisCharge();
						if (disCharge == null)
							disCharge = (float) 0;
						model.setDisCharge(disCharge + (accMoney - payMoney));
					}
				}
				// 保存数据库
				parkSumInfoMapper.insert(model);
			} else {
				int carType = out.getCardType();
				Float accMoney = out.getAccountCharge();
				if (accMoney == null)
					accMoney = (float) 0;
				Float payMoney = out.getPayCharge();
				if (payMoney == null)
					payMoney = (float) 0;
				if (carType == 1) { // 异常开闸
					Integer handGate = model.getHandGate();
					if (handGate == null)
						handGate = 0;
					model.setHandGate(handGate + 1);
				}
				if (carType == 2) { // 电脑开闸
					Integer computerGate = model.getComputerGate();
					if (computerGate == null)
						computerGate = 0;
					model.setComputerGate(computerGate + 1);
				}
				if (carType > 10 && carType < 20) { // 月租类型
					Integer monthCarOut = model.getMonthCarOut();
					if (monthCarOut == null)
						monthCarOut = 0;
					model.setMonthCarOut(monthCarOut + 1);
				}
				if ((carType > 20 && carType < 40) || (carType > 60 && carType < 70)) { // 临时
					Integer tempCarOut = model.getTempCarOut();
					if (tempCarOut == null)
						tempCarOut = 0;
					model.setTempCarOut(tempCarOut + 1);
				}
				if (carType == 40) { // 临免车
					Integer tempFree = model.getTempFree();
					if (tempFree == null)
						tempFree = 0;
					model.setTempFree(tempFree + 1);
				}
				if (carType > 50 && carType < 60) { // 储值车
					Integer storedCarNum = model.getStoredCarNum();
					if (storedCarNum == null)
						storedCarNum = 0;
					model.setStoredCarNum(storedCarNum + 1);
					Float storedCarMoney = model.getStoredCarMoney();
					if (storedCarMoney == null)
						storedCarMoney = (float) 0;
					model.setStoredCarMoney(storedCarMoney + payMoney);
				} else {
					Float account = model.getAccount();
					if (account == null)
						account = (float) 0;
					model.setAccount(account + accMoney);
					int outWay = 0;
					if (out.getOutWay() != null)
						outWay = out.getOutWay();
					// 支付类型0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
					if (outWay == 0) {// 现金支付
						Float cashCharge = model.getCashCharge();
						if (cashCharge == null)
							cashCharge = (float) 0;
						model.setCashCharge(cashCharge + payMoney);
					}
					if (outWay == 1) { // 微信支付
						Float wechatCharge = model.getWechatCharge();
						if (wechatCharge == null)
							wechatCharge = (float) 0;
						model.setWechatCharge(wechatCharge + payMoney);
					}
					if (outWay == 2) { // 支付宝支付
						Float alipayCharge = model.getAlipayCharge();
						if (alipayCharge == null)
							alipayCharge = (float) 0;
						model.setAlipayCharge(alipayCharge + payMoney);
					}
				}
				if (accMoney - payMoney > 0) {
					if (carType == 0) {
						Float freeCharge = model.getFreeCharge();
						if (freeCharge == null)
							freeCharge = (float) 0;
						model.setFreeCharge(freeCharge + (accMoney - payMoney));
					} else {
						Integer disSum = model.getDisSum();
						if (disSum == null)
							disSum = 0;
						model.setDisSum(disSum + 1);
						Float disCharge = model.getDisCharge();
						if (disCharge == null)
							disCharge = (float) 0;
						model.setDisCharge(disCharge + (accMoney - payMoney));
					}
				}
				// 保存数据库
				parkSumInfoMapper.updateByPrimaryKey(model);
			}
		} catch (Exception e) {
			logger.error("出场统计刷新:", e);
		}
	}

}


package com.drzk.service.impl;

import com.drzk.common.ParkMethod;
import com.drzk.mapper.*;
import com.drzk.parklib.send.MainBoardSdk;
import com.drzk.service.ILoadDeviceListService;
import com.drzk.service.entity.LoadUserMsgBody;
import com.drzk.service.entity.LoadUserMsgBodyReturn;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.entity.ReplyHead;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.LoggerUntils;
import com.drzk.vo.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Vector;


/**
 * 
 */
@Service
public class LoadDeviceListServiceImpl implements ILoadDeviceListService {
	private static Logger logger = Logger.getLogger("userLog");
	@Autowired
	private ParkChannelSetMapper channelSetMapper;//通道(控制器)数据库操作类
	@Autowired
	private ParkLocalSetMapper localSetMapper; //岗亭数据库操作
	@Autowired
	private ParkCamSetMapper cameraSetMapper; //相机数据库操作
	@Autowired
	private ParkDisInfoMapper disInfoMapper; //优惠列表数据操作类
	@Autowired
	private ParkAccountTypeMapper accountTypeMapper; //车场车类型列表数据库操作类
	@Autowired
	private ParkEquipmentsMapper equipmentsMapper; //商家数据库操作类
	@Autowired
	private ParkFreeTypeMapper freeTypeMapper; //免费类型数据库操作类
	@Autowired
	private YktCardIssueRelMapper cardIssueRelMapper;
	@Autowired
	private VwParkCarIsuseMapper vwParkCarIsuseMapper;
	
	@Autowired
	private ParkSumUserMapper parkSumUserMapper;
	
	/**
	 * 加载控制器到缓存
	 */
	@Override
	public void getContolInfo() {
		try {
			Vector<ParkChannelSet> channelList = channelSetMapper.selectAllChannelInfo();
			GlobalPark.parkContset.addAll(channelList);
		} catch (Exception e) {
			logger.error("加载控制器到缓存:",e);
		}
	}

	/**
	 * 加载岗亭参数到缓存
	 */
	@Override
	public void getWorkStation() {
		try {
			Vector<ParkLocalSet> localSetList = localSetMapper.selectAllLocalSet();
			GlobalPark.parkLocalSet.addAll(localSetList);
			Date tDate=new Date();//当前时间
			for(int i=0;i< GlobalPark.parkLocalSet.size(); i++) {
				ParkSumUser model = new ParkSumUser();
				model.setBoxId(GlobalPark.parkLocalSet.get(i).getBoxId());
				List<ParkSumUser> parkSumUserList = parkSumUserMapper.selectByCondition(model);
				if(parkSumUserList != null && parkSumUserList.size() > 0) { //存在记录
					if(parkSumUserList.get(0).getReliefUserName() == null) { //有一条记录
						GlobalPark.parkLocalSet.get(i).setLoginName(parkSumUserList.get(0).getUserName());
						GlobalPark.parkLocalSet.get(i).setLoginDate(parkSumUserList.get(0).getLoginDate());;
					}else { //
						ParkSumUser modelmody = new ParkSumUser();
						modelmody.setBoxId(GlobalPark.parkLocalSet.get(i).getBoxId());
						modelmody.setUserName("admin");
						modelmody.setLoginDate(tDate);
						modelmody.setPuid(ParkMethod.getUUID());
						parkSumUserMapper.insert(modelmody);
						GlobalPark.parkLocalSet.get(i).setLoginName("admin");
						GlobalPark.parkLocalSet.get(i).setLoginDate(tDate);;
					}
					
				}else {//第一次登录
					ParkSumUser modelmody = new ParkSumUser();
					modelmody.setBoxId(GlobalPark.parkLocalSet.get(i).getBoxId());
					modelmody.setUserName("admin");
					modelmody.setLoginDate(tDate);
					modelmody.setPuid(ParkMethod.getUUID());
					parkSumUserMapper.insert(modelmody);
					GlobalPark.parkLocalSet.get(i).setLoginName("admin");
					GlobalPark.parkLocalSet.get(i).setLoginDate(tDate);;
				}
			}
		} catch (Exception e) {
			logger.error("加载岗亭参数到缓存:",e);
		}
	}

	/**
	 * 加载相机信息到缓存
	 */
	@Override
	public void getCamInfo() {
		try {
			Vector<ParkCamSet> cameraSetList = cameraSetMapper.selectAllCameraInfo();
			GlobalPark.parkCamset.addAll(cameraSetList);
		} catch (Exception e) {
			logger.error("加载相机信息到缓存:",e);
		}
	}

	/**
	 * 加载车辆类型到缓存
	 */
	@Override
	public void getAccountTypeInfo() {
		try {
			Vector<ParkAccountType> accountTypeList = accountTypeMapper.selectAllAccountType();
			GlobalPark.parkAccountType.addAll(accountTypeList);
		} catch (Exception e) {
			logger.error("加载车辆类型到缓存:",e);
		}
	}
	
	/**
	 * 加载免费类型到缓存
	 */
	@Override
	public void getFreeTypeInfo() {
		try {
			Vector<ParkFreeType> freeTypeList  = freeTypeMapper.selectAllParkFreeType();
			GlobalPark.parkFreeType.addAll(freeTypeList);
		}catch (Exception e) {
			logger.error("加载免费类型到缓存:",e);
		}
	}
	
	/**
	 * 加载打折商家信息到缓存
	 */
	@Override
	public void getEquipmentsInfo() {
		try {
			Vector<ParkEquipments> EquipmentsList = equipmentsMapper.selectAllEquipmentsInfo();
			GlobalPark.parkEquipments.addAll(EquipmentsList);
		} catch (Exception e) {
			logger.error("加载打折商家信息到缓存:",e);
		}
	}
	
	/**
	 * 加载折扣信息到缓存
	 */
	@Override
	public void getDisInfo() {
		try {
			Vector<ParkDisInfo> DisInfoList = disInfoMapper.selectAllDisInfo();
			GlobalPark.parkDisInfo.addAll(DisInfoList);
		} catch (Exception e) {
			logger.error("加载打折商家信息到缓存:",e);
		}
	}
	/**
	 * 车牌授权 Date: 2018年09月23日 下午2:09:11 <br>
	 * 
	 * @author wangchengxi
	 */
	public void loadUserInfo() {
		try {
			YktCardIssueRel condition = new YktCardIssueRel();
			condition.setSign(0);
			// 查询有改变的内容
			List<YktCardIssueRel> cardRel = cardIssueRelMapper.selectByCondition(condition);
			for (YktCardIssueRel yktCardIssueRel : cardRel) {
				Integer yktId = yktCardIssueRel.getYktId();// 一卡通id
				Integer macNo = yktCardIssueRel.getMachNo(); // 控制器机号
				if (macNo == null) {
					return;
				}
				// 查询一卡通信息
				VwParkCarIsuse parkCardCondition = new VwParkCarIsuse();
				parkCardCondition.setId(yktId);
				VwParkCarIsuse result = vwParkCarIsuseMapper.selectMonthCar(parkCardCondition);
				LoadUserMsgBody userMsgBody = new LoadUserMsgBody();

				userMsgBody.setCarType(result.getCardType().toString());
				userMsgBody.setCarNo(result.getCarNo());
				userMsgBody.setUserTimeStart(result.getStartDate());
				userMsgBody.setUserTimeEnd(result.getEndDate());
				userMsgBody.setStoredValue(result.getBalanceMoney().toString());
				userMsgBody.setListType("0");

				ParkChannelSet channel = ParkMethod.getChannelSetByControlIndex(macNo);

				if (channel == null || !channel.isOnline()) {
					break;
				}

				int status = result.getStatus();// 0正常、1正在挂失、2已挂失、3正在解挂、4已补卡、5挂失退款、6已销户
				String method = "loadUserInfo";
				if (status == 6) {
					method = "deleteUserInfo";
				}
				// 下发硬件
				MainBoardMessage<ReplyHead, LoadUserMsgBodyReturn> replyVo = MainBoardSdk.sendAndGet(channel.getDsn(),
						method, userMsgBody, LoadUserMsgBodyReturn.class);
				if (replyVo != null) {
					if (method == "deleteUserInfo") {// 删除记录无需判断状态
						yktCardIssueRel.setSign(1);
						cardIssueRelMapper.updateByPrimaryKey(yktCardIssueRel);
					} else { // 正常授权需要判断状态
						if ("0".equals(replyVo.getHead().getStatus())) {
							// 修改标志位
							yktCardIssueRel.setSign(1);
							cardIssueRelMapper.updateByPrimaryKey(yktCardIssueRel);
						}
					}
				}
			}
		} catch (Exception e) {
			LoggerUntils.error(logger, e);
		}
	}
}

package com.drzk.common;

import com.drzk.offline.vo.BoxParamVo;
import com.drzk.offline.vo.Head;
import com.drzk.service.IParkingService;
import com.drzk.service.entity.MainBoardMessage;
import com.drzk.service.impl.MqttServiceImpl;
import com.drzk.utils.GlobalPark;
import com.drzk.utils.JsonUtil;
import com.drzk.utils.SpringUtil;
import com.drzk.utils.StringUtils;
import com.drzk.vo.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.stereotype.Component;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

/**
 * 车场公共方法 Date: 2018年5月16日 下午2:09:11 <br>
 * 
 * @author wangchengxi
 */
@Component
public class ParkMethod {
	private static Logger logger = Logger.getLogger("userLog");

	/**
	 * 根据cardType获取卡类型 <br>
	 * 逻辑 Date: 2018年9月17日 下午2:09:11 <br>
	 * @author tanfei
	 */
	public static ParkAccountType getParkCardType(int CardType)
	{
		ParkAccountType retType = null;
		Iterator<ParkAccountType> ite=GlobalPark.parkAccountType.iterator();
	    while(ite.hasNext()) {
	    	ParkAccountType parkAccountType =ite.next();
	        if(parkAccountType.getaType() == CardType)
	        {
	        	retType = parkAccountType;
	        	break;
	        }
	    }
	    return retType;
	}
	
	/**
	 * 根据freeType获取免费类型 <br>
	 * 逻辑 Date: 2018年9月17日 下午2:09:11 <br>
	 * @author tanfei
	 */
	public static ParkFreeType getFreeType(int freeType)
	{
		ParkFreeType retType = null;
		Iterator<ParkFreeType> ite=GlobalPark.parkFreeType.iterator();
	    while(ite.hasNext()) {
	        ParkFreeType parkFreeType =ite.next();
	        if(parkFreeType.getFreeType() == freeType)
	        {
	        	retType = parkFreeType;
	        	break;
	        }
	    }
	    return retType;
	}
	
	/**
	 * 车场参数推送到岗亭 <br>
	 * 逻辑 Date: 2018年9月17日 下午2:09:11 <br>
	 * @author tanfei
	 */
	public static void pushToBoxs() {
		for (int k = 0; k < GlobalPark.parkLocalSet.size(); k++) {
			try {
				if (GlobalPark.parkLocalSet.get(k) != null && GlobalPark.parkLocalSet.get(k).getOnline() != null && GlobalPark.parkLocalSet.get(k).getOnline() == 1) {
					BoxParamVo body = new BoxParamVo();
					//当前岗亭信息
					body.setParkLocalSet(GlobalPark.parkLocalSet.get(k));
					//当前岗亭的控制器集合
					List<ParkChannelSet> controlSet = new ArrayList<ParkChannelSet>();
					for (int h = 0; h < GlobalPark.parkContset.size(); h++) {
						if (GlobalPark.parkContset.get(h) != null && GlobalPark.parkContset.get(h).getBoxId() == GlobalPark.parkLocalSet.get(k).getBoxId()) {
							controlSet.add(GlobalPark.parkContset.get(h));
						}
					}
					body.setParkContset(controlSet);
					//当前岗亭的相机集合
					List<ParkCamSet> camSet = new ArrayList<ParkCamSet>();
					for (int q = 0; q < GlobalPark.parkCamset.size(); q++) {
						if (GlobalPark.parkCamset.get(q) != null && GlobalPark.parkCamset.get(q).getBoxId() == GlobalPark.parkLocalSet.get(k).getBoxId())
							camSet.add(GlobalPark.parkCamset.get(q));
					}
					body.setParkCamSet(camSet);
					//车类型集合
					body.setParkAccountType(GlobalPark.parkAccountType);
					//免费类型集合
					body.setParkFreeType(GlobalPark.parkFreeType);
					//商家集合
					body.setParkEquipments(GlobalPark.parkEquipments);
					//折扣集合
					body.setParkDisInfo(GlobalPark.parkDisInfo); 
					//车场参数
					body.setParkSysSet(GlobalPark.properties);  
					Head head = new Head();
					head.setReplyTopic("");
					head.setVersion("V11");
					head.setMethod("parkSystem");
					MainBoardMessage<Head, BoxParamVo> returnInfo = new MainBoardMessage<>(head, body);
					String jsonBody = JsonUtil.objectToJsonStr(returnInfo);
					MqttServiceImpl.sendMessage(GlobalPark.parkLocalSet.get(k).getEquipmentID(), GlobalPark.parkLocalSet.get(k).getEquipmentID() + "/publish/box/data", jsonBody, null, 3);
					logger.debug("车场参数推送到岗亭:"+jsonBody);
				}
			} catch (Exception ex) {
				logger.error("车场参数推送到岗亭:",ex);
			}
		}
	}
	
	/**
	 * 动态二维码拼装 <br>
	 * 逻辑 Date: 2018年8月14日 下午2:09:11 <br>
	 * @author tanfei
	 */
	public static String getTwoCode(String Dsn) {
		String result = null;
		try {
			String url = GlobalPark.properties.getProperty("URL_PATH");//动态码url
			String parknum = GlobalPark.properties.getProperty("PARK_NUM");//车场编号
			String e_key = GlobalPark.properties.getProperty("ENCYP_KEY");//加密密钥(必须是16位)
			String op_no = GlobalPark.properties.getProperty("OPERA_NO");//运营商编号
			long timestmp = System.currentTimeMillis()/1000;
			String datas = op_no + "," + Dsn + "," + timestmp;
//			String EncryptStr = encrypt(datas,e_key);
//			if(EncryptStr == null)
//				return null;
			result = url + "?parkId=" + parknum + "&dsn=" + datas + "&t=" + timestmp;
		} catch (Exception ex) {
			logger.error("动态二维码生成:",ex);
		}
		return result;
	}
	
    /**
     * 加密
     * 
     * @param content 需要加密的内容
     * @param password  加密密码
     * @return
     */
    public static String encrypt(String content, String password) {
            try {           
                    KeyGenerator kgen = KeyGenerator.getInstance("AES");
                    kgen.init(128, new SecureRandom(password.getBytes()));
                    SecretKey secretKey = kgen.generateKey();
                    byte[] enCodeFormat = secretKey.getEncoded();
                    SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
                    Cipher cipher = Cipher.getInstance("AES");// 创建密码器
                    byte[] byteContent = content.getBytes("utf-8");
                    cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
                    byte[] result = cipher.doFinal(byteContent);
                    return new Base64().encodeToString(result);//此处使用BASE64做转码功能，同时能起到2次加密的作用。
            } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
            } catch (InvalidKeyException e) {
                    e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
            } catch (IllegalBlockSizeException e) {
                    e.printStackTrace();
            } catch (BadPaddingException e) {
                    e.printStackTrace();
            }
            return null;
    }

    /**解密
     * @param content  待解密内容
     * @param password 解密密钥
     * @return
     */
    public static String decrypt(String content, String password) {
            try {
                     KeyGenerator kgen = KeyGenerator.getInstance("AES");
                     kgen.init(128, new SecureRandom(password.getBytes()));
                     SecretKey secretKey = kgen.generateKey();
                     byte[] enCodeFormat = secretKey.getEncoded();
                     SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");            
                     Cipher cipher = Cipher.getInstance("AES");// 创建密码器
                    cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
                    byte[] encrypted1 = new Base64().decode(content);//先用base64解密
                    try {
                    	byte[] result = cipher.doFinal(encrypted1);
                        return new String(result,"utf-8");
                    } catch (Exception e) {
                        System.out.println(e.toString());
                        return null;
                    }
            } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
            } catch (NoSuchPaddingException e) {
                    e.printStackTrace();
            } catch (InvalidKeyException e) {
                    e.printStackTrace();
            }
            return null;
    }
	
	/**
	 * 获取无牌车号码 <br>
	 * 逻辑 Date: 2018年8月14日 下午2:09:11 <br>
	 * @author tanfei
	 */
	public static String getNewNopNo() {
		String result = null;
		try {		
			IParkingService parkingService= (IParkingService) SpringUtil.getBean("parkingInService");
			result = parkingService.getNewNOPNo();
		} catch (Exception ex) {
			logger.error("获取无牌车号码:",ex);
		}
		return result;
	}
	
	/**
	 * 车牌转卡ID 实现方法，把字符串转16进字符取后16位 <br>
	 * 逻辑 Date: 2018年5月16日 下午2:09:11 <br>
	 * 
	 * @author wangchengxi
	 */
	public static String CarNotoCardID(String carNo) {
		String result = null;
		try {
			if (carNo == null || carNo.trim().length() == 0)
				return null;
			byte[] bytes = carNo.trim().getBytes("UTF-8");
			result = bytesToHex(bytes).trim();
			if (result.length() >= 16) {
				if (result.length() > 20) {
					result = result.trim().substring(result.length() - 20);
				} else {
					result = result.trim();
				}
			} else {
				int dif = 16 - result.length();
				for (int i = 0; i < dif; i++) {
					result = "0" + result;
				}
			}
		} catch (Exception ex) {
			logger.error("车牌号码生成cardid:",ex);
		}
		return result;
	}

	/**
	 * 
	 * 获取本机ip,获取失败则返回空<br>
	 * 此方法还未经过双网卡的情况的测试
	 * 
	 * @author chenlong
	 * @return
	 * @since JDK 1.8
	 */
	public static String getIPAddress() {
		InetAddress addr = null;
		String ip = "";
		try {
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress().toString(); // 获取本机ip
		} catch (UnknownHostException ex) {
			logger.error("获取本机IP:",ex);
		}
		return ip;
	}

	/**
	 * 获取分钟(显示)
	 */
	public static int GetMinuteValue(DateTime InTime, DateTime OutTime) {
		int ParkTime = 0;
		try {
			long intime = InTime.getMillis();
			long outtime = OutTime.getMillis();
			long dif = (outtime - intime) / 1000;
			ParkTime = (int) dif / 60;
		} catch (Exception ex) {
			logger.error("获取分钟数:",ex);
		}
		return ParkTime;
	}

	/**
	 * 获取显示分钟数
	 */
	public static String getMinuteString(DateTime inTime, DateTime outTime) {
		String ret = null;
		try {
			long intime = inTime.getMillis();
			long outtime = outTime.getMillis();
			long dif = (outtime - intime) / 1000;
			long day = dif / (24 * 60 * 60);
			long hour = dif / (60 * 60) - day * 24;
			long min = dif / 60 - day * 24 * 60 - hour * 60;
			ret = day + "天" + hour + "小时" + min + "分";
		} catch (Exception ex) {
			logger.error("获取分钟数:",ex);
		}
		return ret;
	}

	/**
	 * 得到卡类型编号 卡类型编号 CardType 返回 1,临时卡 2，月卡.3,免费卡,4储值卡
	 */
	public byte getCardTypeNo(int cardType) {
		byte type = 0;
		switch (cardType) {
		case 31:
		case 32:
		case 33:
		case 34:
		case 35:
		case 36:
		case 37:
		case 38:
			type = 1;
			break;
		case 21:
		case 22:
		case 23:
		case 24:
		case 25:
		case 26:
		case 27:
		case 28:
			type = 1;
			break;
		case 61:
		case 62:
		case 63:
		case 64:
			type = 1;
			break;
		case 11:
		case 12:
		case 13:
		case 14:
		case 15:
		case 16:
		case 17:
		case 18:
			type = 2;
			break;
		case 51:
		case 52:
		case 53:
		case 54:
		case 55:
		case 56:
		case 57:
		case 58:
			type = 4;
			break;
		case 40:
		case 41:
		case 42:
		case 43:
		case 44:
		case 45:
		case 46:
		case 47:
		case 48:
			type = 3;
			break;
		default:
			type = 0;
			break;

		}
		return type;
	}

	/**
	 * 查找所有相似的车牌<br>
	 * 
	 * @param carNo    需要对比的车牌
	 * @param allCarNo 所有与carNo对比的车牌集合
	 * @param distance 自定义过滤最短编辑距离的值
	 */
	public static Map<Integer, List<String>> findAllSimilarCarNo(String carNo, List<String> allCarNo, int distance) {
		Map<Integer, List<String>> result = new HashMap<Integer, List<String>>();
		List<String> carNoList0 = new ArrayList<String>();
		List<String> carNoList1 = new ArrayList<String>();
		List<String> carNoList2 = new ArrayList<String>();
		List<String> carNoList3 = new ArrayList<String>();
		List<String> carNoList4 = new ArrayList<String>();
		List<String> carNoList5 = new ArrayList<String>();
		List<String> carNoList6 = new ArrayList<String>();
		List<String> carNoList7 = new ArrayList<String>();
		List<String> carNoList8 = new ArrayList<String>();
		for (String string : allCarNo) {
			int dis = StringUtils.getDistance(carNo.substring(1), string.substring(1));// 首个中文字符不比较
			if (dis <= distance) {
				switch (dis) {
				case 0:
					carNoList0.add(string);
				case 1:
					carNoList1.add(string);
					break;
				case 2:
					carNoList2.add(string);
					break;
				case 3:
					carNoList3.add(string);
					break;
				case 4:
					carNoList4.add(string);
					break;
				case 5:
					carNoList5.add(string);
					break;
				case 6:
					carNoList6.add(string);
					break;
				case 7:
					carNoList7.add(string);
					break;
				case 8:
					carNoList8.add(string);
					break;

				default:
					break;
				}
			}
		}

		if (carNoList0.size() != 0) {
			result.put(0, carNoList0);
		}
		
		if (carNoList1.size() != 0) {
			result.put(1, carNoList1);
		}
		if (carNoList2.size() != 0) {
			result.put(2, carNoList2);
		}
		if (carNoList3.size() != 0) {
			result.put(3, carNoList3);
		}
		if (carNoList4.size() != 0) {
			result.put(4, carNoList4);
		}
		if (carNoList5.size() != 0) {
			result.put(5, carNoList5);
		}
		if (carNoList6.size() != 0) {
			result.put(6, carNoList6);
		}
		if (carNoList7.size() != 0) {
			result.put(7, carNoList7);
		}
		if (carNoList8.size() != 0) {
			result.put(8, carNoList8);
		}
		return result;
	}

	/**
	 * 
	 * 根据控制器索引获取对应的控制器<br>
	 *
	 * @author chenlong
	 * @return
	 * @since JDK 1.8
	 */
	public static ParkChannelSet getChannelSetByControlIndex(int controlIndex) {

		for (ParkChannelSet channel : GlobalPark.parkContset) {
			if (channel.getMachNo() == controlIndex) {
				return channel;
			}
		}
		return null;
	}
	/**
	 * 
	 * 根据控制器索引获取对应的控制器<br>
	 *
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
	public static ParkChannelSet getChannelSetByEquipmentID(String equipmentID) {

		for (ParkChannelSet channel : GlobalPark.parkContset) {
			if (equipmentID.equals(channel.getDsn())) {
				return channel;
			}
		}
		return null;
	}

	public static ParkChannelSet getChannelSetById(int Id) {

		for (ParkChannelSet channel : GlobalPark.parkContset) {
			if (channel.getId() == Id) {
				return channel;
			}
		}
		return null;
	}
	/**
	 * 
	 * 根据控制器ip获取对应的控制器. <br>
	 *
	 * @author chenlong
	 * @param ip
	 * @return
	 * @since JDK 1.8
	 */
	public static ParkChannelSet getChannelSetByIp(String ip) {
		if (StringUtils.isNullOrEempty(ip)) {
			return null;
		}
		for (ParkChannelSet channel : GlobalPark.parkContset) {
			if (ip.equals(channel.getChannelIp())) {
				return channel;
			}
		}
		return null;
	}
	/**
	 * 
	 * 获取当班操作人员 <br>
	 *
	 * @author wangchengxi
	 * @param boxId
	 * @return
	 * @since JDK 1.8
	 */
	public static String getLoginName(Integer boxId) {
		for (ParkLocalSet local : GlobalPark.parkLocalSet) {
			if (local.getBoxId()==boxId) {
				return local.getLoginName();
			}
		}
		return "";
	}
	public static ParkChannelSet getChannelSetByID(Integer id) {
		if (id <=0) {
			return null;
		}
		for (ParkChannelSet channel : GlobalPark.parkContset) {
			if (channel.getId()==id) {
				return channel;
			}
		}
		return null;
	}

	/** 根据控制器id获取该控制器的第一个相机(主相机) */
	public static ParkCamSet getCameraSetByChannelId(int channelId) {
		for (ParkCamSet parkCamSet : GlobalPark.parkCamset) {
			if (parkCamSet.getChannelId() == channelId) {
				return parkCamSet;
			}
		}
		return null;
	}

	/**
	 * 控制器机号找相机
	 * 
	 * @param machNo
	 * @return
	 */
	public static ParkCamSet getParkCamSetByMachNo(Integer machNo) {
		for (ParkCamSet model : GlobalPark.parkCamset) {
			if (machNo.equals(model.getMachNo()))
				return model;
		}
		return null;
	}

	/**
	 * 岗亭索引找岗亭实体
	 * 
	 * @param boxId
	 * @return
	 */
	public static ParkLocalSet getLocalSetByBoxId(int boxId) {
		if (boxId <= 0)
			return null;
		for (ParkLocalSet local : GlobalPark.parkLocalSet) {
			if (boxId == local.getBoxId())
				return local;
		}
		return null;
	}
	public static ParkLocalSet getLocalSetByBoxIP(String boxIP) {
		if (boxIP == null)
			return null;
		for (ParkLocalSet local : GlobalPark.parkLocalSet) {
			if (local.getBoxIp().equals(boxIP))
				return local;
		}
		return null;
	}
	/**
	 * 获取岗亭的设备状态
	 * 
	 * @param boxId
	 * @return
	 */
	public static List<ParkDeviceStatus> getStatusByBoxId(int boxId) {
		if (boxId <= 0)
			return null;
		List<ParkDeviceStatus> list = new ArrayList<ParkDeviceStatus>();
		for (ParkDeviceStatus model : GlobalPark.parkDeviceStatus) {
			if (model!=null&&boxId == model.getBoxId())
				list.add(model);
		}
		return list;
	}

	/** 生成uuid */
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 在时间上加上天数
	 * 
	 * @param t1
	 * @param days
	 * @return
	 */
	public static Date GetDate(Date t1, Integer days) {
		Calendar ca = new GregorianCalendar();// 得到一个Calendar的实例
		ca.setTime(t1); // 设置时间为当前时间
		ca.add(Calendar.DATE, days); //
		Date lastMonth = ca.getTime(); // 结果
		return lastMonth;
	}
	public static Date GetDateByHours(Date t1, Integer hours) {
		Calendar ca = new GregorianCalendar();// 得到一个Calendar的实例
		ca.setTime(t1); // 设置时间为当前时间
		ca.add(Calendar.HOUR, hours); //
		Date lastMonth = ca.getTime(); // 结果
		return lastMonth;
	}

	/**
	 * 卡ID 找卡实体
	 * 
	 * @param type
	 * @return
	 */
	public static ParkAccountType ParkAccountTypeByType(Integer type) {
		for (ParkAccountType model : GlobalPark.parkAccountType) {
			if (model.getaType().equals(type))
				return model;
		}
		return null;
	}

	/**
	 * 0正常入场，1校正入场 ，2手工入场 3,扫码入场,4手持机入场 0正常入场，1校正入场，2手工入场 3,扫码入场 4,脱机记录，5，相机异常记录
	 * 6，异常开闸
	 * 
	 * @param inWay
	 * @return
	 */
	public static String GetInWayName(Integer inWay) {
		String inWayName = "";
		switch (inWay) {
		case 0:
			inWayName = "正常入场";
			break;
		case 1:
			inWayName = "校正入场";
			break;
		case 2:
			inWayName = "手工入场";
			break;
		case 3:
			inWayName = "扫码入场";
			break;
		case 4:
			inWayName = "手持机入场";
			break;
		default:
			inWayName = "未知";
			break;
		}
		return inWayName;
	}

	/**
	 * 0,表示正常出场，1，手工放行，2，异常放行,4手持机放行
	 * 
	 * @param outWay
	 */
	public static String GetOutWayName(Integer outWay) {
		String outWayName = "";
		switch (outWay) {
		case 0:
			outWayName = "正常出场";
			break;
		case 1:
			outWayName = "手工放行";
			break;
		case 2:
			outWayName = "异常放行";
			break;
		case 3:
			outWayName = "";
			break;
		case 4:
			outWayName = "手持机放行";
			break;
		}
		return outWayName;
	}

	/**
	 * 支付类型 0，现金支付，1微信支付,2，支付宝支付,3,银联闪付，4，交通卡支付，5,自助缴费机,6，其它
	 * 
	 * @param payType
	 * @return
	 */
	public static String GetPayTypeName(Byte payType) {
		String payTypeName = "";
		switch (payType) {
		case 0:
			payTypeName = "现金支付";
			break;
		case 1:
			payTypeName = "微信支付";
			break;
		case 2:
			payTypeName = "支付宝支付";
			break;
		case 3:
			payTypeName = "银联支付";
			break;
		case 4:
			payTypeName = "交通卡支付";
			break;
		case 5:
			payTypeName = "自助缴费机";
			break;
		default:
			payTypeName = "其它";
			break;

		}
		return payTypeName;
	}

	public static String bytesToHex(byte[] bytes) {
		final char[] hexArray = "0123456789ABCDEF".toCharArray();
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	
	/**
	 * 获取大小车场<br>
	 * @author wangchengxi
	 * @param channelModel
	 * @since JDK 1.8
	 */
	public static int getSmallOrBig(ParkChannelSet channelModel) {
		int small = 0;
		try {
			if (channelModel != null) {
				switch (channelModel.getInOut()) {
				case 0:
				case 1:
				case 4:
				case 5:
				case 6:
				case 7:
					small = 0;
					break;
				default:
					small = 1;
					break;
				}
			}
		} catch (Exception ex) {
			logger.error("获取大小车场:",ex);
		}
		return small;
	}
}


package com.drzk.service;

import java.util.Date;
import java.util.List;

import com.drzk.common.InOutRealTimeBase.Step;
import com.drzk.fact.AbsRealTimeBase;
import com.drzk.fact.CentreRealTimeBase;
import com.drzk.fact.InRealTimeBase;
import com.drzk.fact.OutRealTimeBase;
import com.drzk.vo.ParkCarIn;
import com.drzk.vo.ParkCentralCharge;

/**
 * ClassName:IParkingService <br>
 * Date: 2018年6月8日 上午9:09:01 <br>
 * 
 * @author chenlong
 * @version
 * @since JDK 1.8
 * @see
 */
public interface IParkingService {

	/** 是否识别成功 */
    boolean isReadSuccess(AbsRealTimeBase model);

	/** 判断车场是否满位 */
    boolean isParkFull();

	/** 是否锁车 */
    boolean isLock(String carNo);

	/** 满位是否还让进 */
    boolean isParkFullPass(int cardType);

	/** 获取车辆是特种车或者黑名单 */
    Step getSpecialOrBlackCar(AbsRealTimeBase fact);

	boolean isSmallPark(AbsRealTimeBase fact);

	/** 是否是特种车 */
	// public boolean isSpecialCar(String carNo);

	/** 是否是黑名单 */
	// public boolean isBlackList(String carNo);

	/**
	 * 压地感入场的时候，检测1秒内是否有入场记录，不需要参数 carNo 手工入场的时候，检测场内是否有入场数据，只需要参数 carNo
	 * 检查记录是否重复(几秒内是否有重复数据) flag=1,压地感进场；flag=2，手工入场
	 */
    boolean isRecordDuplicate(String carNo, int controlIndex, Date inTime, int flag);

    /**
     * 获取新的无牌车号码
     * @return 无牌车号码
     */
    String getNewNOPNo();
    
	/** 检查是否是固定车 */
    boolean isRegularCar(AbsRealTimeBase fact);

	/** 获取车牌的卡类型 */
    int getCardTypeInfo(AbsRealTimeBase fact);

	/** 获取固定车的类型 (月卡 or 储值卡 or 免费卡) */
    int getRegularCarType(int cardType);

	/** 月卡过期处理类型 */
    boolean monthCarOverdue();

	/** 检查通道是否有该种卡类通过的权限 */
    boolean isCarTypeChannelPower(AbsRealTimeBase fact);

	/**
	 * 
	 * isCardTypeExpire:卡类是否过期 <br>
	 * 
	 * @author chenlong
	 * @param cartType
	 * @param carNo
	 * @return 过期返回true
	 * @since JDK 1.8
	 */
    Step isCardTypeExpireIn(InRealTimeBase in);
    /**
	 * 
	 * isCardTypeExpire:卡类是否过期 <br>
	 * 
	 * @author wangchengxi
	 * @param cartType
	 * @param carNo
	 * @return 过期返回true
	 * @since JDK 1.8
	 */
    Step isCardTypeExpireOut(OutRealTimeBase out);

    /**
	 * isCardTypeExpire:卡类是否过期 <br>
	 * 
	 * @author wangchengxi
	 * @param cartType
	 * @param carNo
	 * @return 过期返回true
	 * @since JDK 1.8
	 */
    Step isCardTypeExpireCentre(CentreRealTimeBase centre);
	/** 是否需要人工确认 */
    boolean isNeedConfirm(InRealTimeBase in, OutRealTimeBase out);

	/** 通知岗亭确认开闸 */
    void boxInIsOpen(InRealTimeBase fact);
	void boxOutIsOpen(OutRealTimeBase out);

	/** 播报语音 */
    boolean broadcastVoice(AbsRealTimeBase fact);

	/** 开闸 */
    boolean open(String equipmentID);

	/** 保存入场记录 */
    boolean saveParkInRecord(InRealTimeBase in);

	/** 费用是否结清 true表示结清,false 表示欠费 */
    boolean isArrears(OutRealTimeBase out);
    
    /** 费用是否结清 true表示结清,false 表示欠费 */
    boolean isArrears(CentreRealTimeBase fact);


	/**
	 * 查询相似车牌的入场记录. <br>
	 * @author chenlong
	 * @param carNo    车牌
	 * @param distance 允许错几位
	 * @return
	 * @since JDK 1.8
	 */
    List<ParkCarIn> getSimilarCar(OutRealTimeBase out);
    
    /**
	 * 查询是否有入场记录. <br>
	 * @author wangchengxi
	 * @param carNo    车牌
	 * @return
	 * @since JDK 1.8
	 */
    boolean getSimilarCar(CentreRealTimeBase centre);
    
    /**
	 * 通行免费车查找入场记录 <br>
	 * @author wangchengxi
	 * @param carNo    车牌
	 * @return
	 * @since JDK 1.8
	 */
    void getFreeInRecord(OutRealTimeBase out);
    
    /**
	 * 判断是家庭组月卡还是临时卡 <br>
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
    boolean centreFamilyTemp(CentreRealTimeBase fact);
    /**
	 * 判断是家庭组月卡还是临时卡 <br>
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
    boolean isFamilyTemp(OutRealTimeBase out);
    /**
	 * 判断是家庭组月卡是否存在临时车计费 <br>
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
    boolean isFamilyMonth(OutRealTimeBase out);
    
    /**
   	 * 判断是家庭组月卡是否存在临时车计费 <br>
   	 * @author wangchengxi
   	 * @return
   	 * @since JDK 1.8
   	 */
     boolean centreFamilyMonth(CentreRealTimeBase fact);
    /**
	 * 固定车多出判断 <br>
	 * @author wangchengxi
	 * @return
	 * @since JDK 1.8
	 */
    boolean isMonthMoreOut(OutRealTimeBase out);

	/** 发送相似车牌的入场记录 */
    void sendSimilarCar(OutRealTimeBase out);
	/** 保存出场记录 */
    int saveParkOutRecord(OutRealTimeBase in);

	/** 发送欠费情况到岗亭 */
    void sendArrearsToLocal(int controlIndex);

	/**
	 * 
	 * 判断车辆是否属于家庭组 <br>
	 *
	 * @author chenlong
	 * @param AbsRealTimeBase fact
	 * @return
	 * @since JDK 1.8
	 */
    boolean isFamilyGroup(AbsRealTimeBase fact);

	/**
	 * 如果是家庭组,判断车辆的入场类型 <br>
	 *
	 * @author chenlong
	 * @param carNo
	 * @param groupId
	 * @return true表示临时车(家庭组满位)，false表示固定车
	 * @since JDK 1.8
	 */
    boolean getFamilyCarType(InRealTimeBase in);

	/** 家庭组临时车是否允许入场 */
    boolean isFamilyAllowIn(InRealTimeBase in);

	/** 预置车辆类型 */
    void presetCarType();

	/**
	 * 
	 * 出场时如果入场的时候车辆类型为月卡，那么出场时也做月卡处理 <br>
	 *
	 * @author chenlong
	 * @return
	 * @since JDK 1.8
	 */
    boolean getTypeByIn(OutRealTimeBase out);
	
	/**
	 * 发送实时错误信息到岗亭
	 * @param in
	 * @return
	 */
    boolean parkErrorMessage(InRealTimeBase in);
	boolean parkErrorMessage(OutRealTimeBase in);
	
	 
	/**
	 * 推送入场流水到岗亭
	 * @param in
	 * @return
	 */
    boolean sendParkCarInRecordToBox(InRealTimeBase in);
	
	/**
	 * 推送出场流水到岗亭
	 * @param out
	 * @return
	 */
    void sendParkCarOutRecordToBox(OutRealTimeBase out);
	
	/**
	 * 出入场往硬件发送纪录
	 * @param in 入场实体
	 * @param out 出场实体
	 */
    void handInParkingCarInfo(InRealTimeBase in, OutRealTimeBase out);
    /**
	 * 推送当班记录
	 *
	 */
    public void pushChargeData(AbsRealTimeBase fact);

//	/**
//	 * 缴费预处理 <br>
//	 *
//	 * @author chenlong
//	 * @param out
//	 * @since JDK 1.8
//	 */
//	public void beforeCharge(OutRealTimeBase out);
//
//	/**
//	 * 当家庭组的车辆离开后，将其组内其他车辆的计费结束时间记录下来 <br>
//	 *
//	 * @author chenlong
//	 * @since JDK 1.8
//	 */
//	public void afterFamilyGroupOut(OutRealTimeBase out);

    /** 储值车计费处理*/
    public boolean storecarCharge(OutRealTimeBase out);
    public boolean storecarCharge(CentreRealTimeBase out);
    
    /**
	 * 中央收费统计刷新<br>
	 * 
	 * @author tanfei
	 * @since JDK 1.8
	 */
    void centreStatisticsRefresh(ParkCentralCharge centre);
    /**
	 * 中央收费当班刷新<br>
	 * 
	 * @author tanfei
	 * @since JDK 1.8
	 */
	void centreSumUser(ParkCentralCharge centre, String boxIp);
}
